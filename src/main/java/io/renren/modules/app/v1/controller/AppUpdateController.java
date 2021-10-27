package io.renren.modules.app.v1.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.renren.common.enums.ErrorMsgEnum;
import io.renren.common.utils.R;
import io.renren.common.utils.RedisKeys;
import io.renren.common.utils.RedisUtils;
import io.renren.common.utils.UploadUtils;
import io.renren.modules.app.v1.annotation.Login;
import io.renren.modules.app.v1.annotation.LoginUser;
import io.renren.modules.app.v1.entity.AppUserEntity;
import io.renren.modules.app.v1.entity.AppVersionEntity;
import io.renren.modules.app.v1.enums.FileTypeEnum;
import io.renren.modules.app.v1.service.AppNoticeService;
import io.renren.modules.app.v1.service.AppUserService;
import io.renren.modules.app.v1.service.AppVersionService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.AppNoticeForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * APP更新
 * @author kang
 */
@RestController
@RequestMapping("/app/v1/update")
@Api("APP更新使用接口")
public class AppUpdateController extends AbstractController {

    @Autowired
    private AppVersionService appVersionService;

    @Autowired
    private UploadUtils uploadUtils;

    @Value("${yjs.app.avatarPath}")
    private String avatarPath;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppNoticeService appNoticeService;

    @Value("${yjs.app.apkPath}")
    private String apkPath;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${yjs.redis-key-expire.token}")
    private Long tokenExpire;

    /**
     * 上传安装包文件
     * @param file 文件
     * @return 返回下载路径
     */
    @PostMapping("/apk")
    @ApiOperation("上传安装包文件")
    public R uploadApk(@RequestParam("file") MultipartFile file) {
        String suffix = uploadUtils.getSuffix(file);
        //上传文件
        if (!FileTypeEnum.APK.getType().equalsIgnoreCase(suffix)
                && !FileTypeEnum.WGT.getType().equalsIgnoreCase(suffix)) {
            return ErrorMsgEnum.ERROR_APK.getR();
        }
        String url = uploadUtils.upload(file, apkPath);
        return R.ok().put("url", url);
    }

    /**
     * 获取当前app最新版本号
     * @return 最新版本号
     */

    @GetMapping("/version")
    @ApiOperation("获取APP最新版本号")
    public R getLatestVersion() {
        return R.ok(appVersionService.getLatestAppVersionNo());
    }

    /**
     * 保存新版本信息，并发送系统通知
     * @return 发送成功通知
     */
    @PostMapping("/publish")
    @ApiOperation("发布新版本信息，并发送系统通知")
    public R publish(@RequestBody AppVersionEntity entity) {
        appVersionService.save(entity);
        AppNoticeForm noticeForm = new AppNoticeForm();
        noticeForm.setTitle("版本更新");
        noticeForm.setContent(entity.getVersionInfo());
        return R.ok(appNoticeService.toApp(noticeForm));
    }



    /**
     * 修改用户昵称
     * @param name 新昵称
     * @param user 登陆用户
     * @return 修改成功
     */
    @PostMapping("/updateNickName/{name}")
    @Login
    @ApiOperation("修改用户昵称")
    public R updateName(@PathVariable("name")String name, @LoginUser AppUserEntity user) {
        LambdaUpdateWrapper<AppUserEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(AppUserEntity::getUserId, user.getUserId())
                .set(AppUserEntity::getNickName, name);
        appUserService.update(updateWrapper);
        //更新redis中用户信息
        String redisKey = RedisKeys.getSysUserTokenKey(user.getToken());
        SysUserEntity redisUser = redisUtils.get(redisKey, SysUserEntity.class);
        if (redisUser != null) {
            redisUser.setUsername(name);
            redisUtils.set(redisKey, redisUser, tokenExpire);
        }
        return R.ok();
    }

    /**
     * 修改用户密码
     * @param pwd 新密码
     * @param user 登陆用户
     * @return 修改成功
     */
    @PostMapping("/updatePwd/{pwd}")
    @Login
    @ApiOperation("修改用户密码")
    public R updatePwd(@PathVariable("pwd")String pwd, @LoginUser AppUserEntity user) {
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        LambdaUpdateWrapper<AppUserEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(AppUserEntity::getUserId, user.getUserId())
                .set(AppUserEntity::getPassword, new Sha256Hash(pwd, salt).toHex())
                .set(AppUserEntity::getSalt, salt);
        appUserService.update(updateWrapper);
        // 踢用户下线
        String redisKey = RedisKeys.getSysUserTokenKey(user.getToken());
        redisUtils.delete(redisKey);
        redisUtils.delete(RedisKeys.getUserRoleKey(user.getUserId()));
        redisUtils.delete(RedisKeys.getUserInfoKey(user.getUserId()));
        return R.ok();
    }

    /**
     * 用户上传新头像
     * @param file 用户头像文件
     * @param user 登陆用户
     * @return 用户头像访问url
     */
    @PostMapping("/updateAvatar")
    @Login
    @ApiOperation("修改用户头像")
    public R updateAvatar(@RequestParam("file") MultipartFile file, @LoginUser AppUserEntity user) {
        String suffix = uploadUtils.getSuffix(file);
        if (!FileTypeEnum.JPEG.getType().equalsIgnoreCase(suffix)
                && !FileTypeEnum.JPG.getType().equalsIgnoreCase(suffix)
                && !FileTypeEnum.PNG.getType().equalsIgnoreCase(suffix)) {
            return ErrorMsgEnum.ERROR_JPG.getR();
        }
        String url = uploadUtils.upload(file, avatarPath);
        LambdaUpdateWrapper<AppUserEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(AppUserEntity::getUserId, user.getUserId())
                .set(AppUserEntity::getAvatar, url);
        appUserService.update(updateWrapper);
        //更新redis中用户信息
        String redisKey = RedisKeys.getSysUserTokenKey(user.getToken());
        SysUserEntity redisUser = redisUtils.get(redisKey, SysUserEntity.class);
        if (redisUser != null) {
            redisUser.setAvatar(url);
            redisUtils.set(redisKey, redisUser, tokenExpire);
        }
        return R.ok().put("url", url);
    }
}
