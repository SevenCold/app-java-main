package io.renren.modules.sys.controller;

import io.renren.common.enums.UserStatusEnum;
import io.renren.common.utils.Constant;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.RegistGroup;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regist")
public class RegisterController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取所有角色名称
     * @return
     */
    @GetMapping("/role/list")
    public R type(){
        List<SysRoleEntity> page = sysRoleService.queryRoles();
        return R.ok().put("list", page);
    }
    /**
     * 根据用户名判断是否已存在用户
     * @return
     */
    @GetMapping("/user/{userName}")
    public R user(@PathVariable("userName") String userName){
        return R.ok().put("existed", sysUserService.checkExistenceByName(userName));
    }

    @PostMapping("/save")
    public R save(@RequestBody SysUserEntity user){
        ValidatorUtils.validateEntity(user, RegistGroup.class);
        //注册状态为未启用，需要审批后才能使用
        user.setStatus(UserStatusEnum.NOT_ENABLED.getValue());
        //标记注册用户
        user.setCreateUserId(Constant.REGISTER);
        sysUserService.saveUser(user);
        return R.ok();
    }
}
