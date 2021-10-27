package io.renren.modules.app.v1.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.renren.common.enums.ErrorMsgEnum;
import io.renren.common.enums.UserStatusEnum;
import io.renren.common.utils.Constant;
import io.renren.common.utils.R;
import io.renren.common.utils.RedisKeys;
import io.renren.common.utils.RedisUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.app.v1.VO.AppUserVo;
import io.renren.modules.app.v1.annotation.Login;
import io.renren.modules.app.v1.annotation.LoginUser;
import io.renren.modules.app.v1.annotation.Permitted;
import io.renren.modules.app.v1.entity.AppUserEntity;
import io.renren.modules.app.v1.entity.AppVersionEntity;
import io.renren.modules.app.v1.enums.RoleEnum;
import io.renren.modules.app.v1.group.AppLoginGroup;
import io.renren.modules.app.v1.group.AppRegistGroup;
import io.renren.modules.app.v1.service.AppNoticeService;
import io.renren.modules.app.v1.service.AppUserService;
import io.renren.modules.app.v1.service.AppVersionService;
import io.renren.modules.app.v1.service.BusInfoService;
import io.renren.modules.app.v1.utils.JwtUtils;
import io.renren.modules.app.v1.utils.NoticeUtils;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.form.AppNoticeForm;
import io.renren.modules.sys.form.SysLoginForm;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * App
 * @author kang
 */
@RestController
@RequestMapping("/app/v1")
@Api("APP登录注册接口")
public class AppController extends AbstractController {

    @Autowired
    private AppVersionService appVersionService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private AppNoticeService appNoticeService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private BusInfoService busInfoService;

    @Autowired
    private AppUserService appUserService;

    /**
     * APP注册
     * @param user 注册用户表单
     * @return 成功信息
     */
    @PostMapping("/register")
    @ApiOperation("注册")
    public R register(@RequestBody AppUserEntity user) {
        //表单校验
        ValidatorUtils.validateEntity(user, AppRegistGroup.class);
        String newCid = user.getClientInfo().getClientid();
        if (StringUtils.isEmpty(newCid)) {
            return ErrorMsgEnum.NO_DEVICE_INFO.getR();
        }
        // 查看手机号是否被注册
        AppUserEntity existedUser = appUserService.queryByMobile(user.getMobile());
        if (existedUser != null) {
            return ErrorMsgEnum.MOBILE_EXIST.getR();
        }
        //注册状态为未启用，需要审批后才能使用
        user.setStatus(UserStatusEnum.NOT_ENABLED.getValue());
        //标记注册用户
        user.setCreateUserId(Constant.REGISTER);
        //用户名、昵称默认为手机号
        user.setUsername(user.getMobile());
        user.setNickName(user.getMobile());
        user.setCid(user.getClientInfo().getClientid());
        appUserService.saveUser(user);
        // 给管理员发送系统通知审批
        noticeManager();
        return R.ok();
    }

    /**
     * 根据手机号判断是否已注册
     * @param mobile 手机号
     * @return existed ： 存在为 true，反之为 false
     */
    @GetMapping("/regist/{mobile}")
    @ApiOperation("根据手机号判断是否已注册")
    public R getUserByMobile(@PathVariable  String mobile) {
        AppUserEntity user = appUserService.queryByMobile(mobile);
        return R.ok().put("existed", user != null);
    }

    /**
     * APP用户登陆
     *          1.
     * @param form
     *          包括：  1.手机号、密码
     *                  2.设备信息
     * @return 登陆的用户信息
     */
    @PostMapping("/login")
    @ApiOperation("登陆")
    public R login(@RequestBody SysLoginForm form) {
        //表单校验
        ValidatorUtils.validateEntity(form, AppLoginGroup.class);
        String newCid = form.getClientInfo().getClientid();
        if (StringUtils.isEmpty(newCid)) {
            return ErrorMsgEnum.NO_DEVICE_INFO.getR();
        }
        AppUserEntity existedUser = appUserService.queryByMobile(form.getMobile());
        // 验证账号是否正确有效
        R validateResult = validateAccount(existedUser, form.getPassword());
        if (validateResult != null) {
            return validateResult;
        }
        // 登陆成功
        // db中账号没有设备标识或不相同，更新成新的标识
        if (StringUtils.isEmpty(existedUser.getCid()) || !existedUser.getCid().equals(newCid)) {
            LambdaUpdateWrapper<AppUserEntity> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(AppUserEntity::getUserId, existedUser.getUserId())
                    .set(AppUserEntity::getCid, newCid);
            appUserService.update(updateWrapper);
        }
        existedUser.setCid(newCid);
        Long userId = existedUser.getUserId();
        // 用户登陆后，信息存在redis中，依据redis判断是否已登陆
        String redisUserStr = redisUtils.get(RedisKeys.getUserInfoKey(userId));
        AppUserVo sysUserVo = new AppUserVo();
        if (StringUtils.isEmpty(redisUserStr)) {
            // 用户尚未登陆
             doLogin(existedUser, sysUserVo);
        } else {
            // 用户登陆过
            AppUserEntity redisUser = JSONUtil.toBean(redisUserStr, AppUserEntity.class);
            // 若新的设备和redis中不同，踢出上一位的登陆状态，保证一个账号一个设备
            if (!newCid.equals(redisUser.getCid())) {
                redisUser.setCid(newCid);
                kickOff(redisUser);
            }
            redisUser.setCid(newCid);
            Set perms = redisUtils.get(RedisKeys.getUserRoleKey(userId), Set.class);
            setSysUserVo(sysUserVo, redisUser, perms);
        }
        return R.ok(sysUserVo);
    }

    /**
     * APP发送系统通知
     * @param noticeForm 发送内容
     * @return 发送结果
     */
    @PostMapping("/notice")
    @Permitted("app:notice")
    @ApiOperation("发送系统通知")
    public R notice(@RequestBody AppNoticeForm noticeForm) {
        return R.ok(appNoticeService.toApp(noticeForm));
    }

    /**
     * 获取大于当前版本的最新版本信息
     * @param entity 当前版本信息
     * @return 版本信息
     */
    @GetMapping("/version")
    @ApiOperation("获取版本号")
    public R getVersion(AppVersionEntity entity) {
        String currentNo = entity.getVersionCode();
        return R.ok(appVersionService.getLatestAppVersion(currentNo));
    }

    /**
     * 退出登录，清空redis信息
     */
    @GetMapping("/logout")
    @ApiOperation("获取版本号")
    @Login
    public R getVersion(@LoginUser AppUserEntity user) {
        // 踢用户下线
        redisUtils.delete(RedisKeys.getSysUserTokenKey(user.getToken()));
        redisUtils.delete(RedisKeys.getUserRoleKey(user.getUserId()));
        redisUtils.delete(RedisKeys.getUserInfoKey(user.getUserId()));
        return R.ok();
    }


    /**
     * 进行实际的登陆操作
     * @param user 登陆用户
     * @param sysUserVo 登陆后返回给前端的用户信息
     */
    private void doLogin(AppUserEntity user, AppUserVo sysUserVo) {
        if (user != null) {
            //生成token
            String token = jwtUtils.generateToken(user.getUserId());
            user.setToken(token);
            // 用户权限信息
            Set<String> perms = sysUserService.queryAllPerms(user.getUserId());

            setSysUserVo(sysUserVo, user, perms);
            // 登录后将用户信息和权限信息存入redis
            user.setPassword(null);
            user.setSalt(null);
            redisUtils.set(RedisKeys.getUserInfoKey(user.getUserId()), user);
            redisUtils.set(RedisKeys.getUserRoleKey(user.getUserId()), perms);
            redisUtils.set(RedisKeys.getSysUserTokenKey(token), user);
        }
    }

    /**
     * 复制用户属性值，脱敏处理
     * @param sysUserVo 返回给前端的用户信息
     * @param user 登陆用户
     * @param perms 用户权限信息
     */
    private void setSysUserVo(AppUserVo sysUserVo, AppUserEntity user, Set<String> perms) {
        BeanUtil.copyProperties(user, sysUserVo);
        sysUserVo.setPermList(perms);
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(user.getUserId());
        sysUserVo.setRoleIdList(roleIdList);
        // 用户如果没有头像，请求结果返回"",而不是null，便于前端判断
        sysUserVo.setAvatar(sysUserVo.getAvatar() == null ? "" : sysUserVo.getAvatar());
    }

    /**
     * 给管理员发送系统通知
     */
    private void noticeManager() {
        List<String> cidList = appUserService.getCidListByRole(RoleEnum.MANAGER.getRoleId());
        AppNoticeForm noticeForm = NoticeUtils.noticeToApprove();
        appNoticeService.toList(cidList, noticeForm);
    }

    /**
     * 验证账号是否可用
     * @param user 用户信息
     * @param password 密码
     * @return 可用返回null，否则返回错误信息
     */
    private R validateAccount(AppUserEntity user, String password) {
        //账号不存在、密码错误
        if(user == null) {
            return ErrorMsgEnum.MOBILE_NOT_EXIST.getR();
        }
        if (!new Sha256Hash(password, user.getSalt()).toHex().equals(user.getPassword())) {
            return ErrorMsgEnum.MOBILE_PWD_ERROR.getR();
        }
        if(user.getStatus() == UserStatusEnum.DISABLE.getValue()){
            return ErrorMsgEnum.ACCOUNT_DISABLE.getR();
        }
        if(user.getStatus() == UserStatusEnum.NOT_ENABLED.getValue()){
            return ErrorMsgEnum.ACCOUNT_NOT_ENABLE.getR();
        }
        return  null;
    }

    /**
     * 将账号踢下线
     * @param redisUser 存在redis中的用户信息
     */
    private void kickOff(AppUserEntity redisUser) {
        Long userId = redisUser.getUserId();
        // 存oldtoken是因为，获取权限时，只能根据id查redis，如果信息中只有新token就无法对旧的token key操作
        String oldToken = redisUser.getToken();
        redisUser.setOldToken(oldToken);
        // 1.生成新的token给新设备
        String newToken = jwtUtils.generateToken(userId);
        redisUser.setToken(newToken);
        // 2.redis添加值为新token的key
        redisUtils.set(RedisKeys.getSysUserTokenKey(newToken), redisUser);
        // 3.更新redis中key为用户id的值 有oldtoken代表被踢下线
        redisUtils.set(RedisKeys.getUserInfoKey(userId), redisUser);
    }
}
