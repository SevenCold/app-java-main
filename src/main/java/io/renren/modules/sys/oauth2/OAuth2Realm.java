/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.oauth2;

import io.renren.common.enums.ErrorMsgEnum;
import io.renren.common.enums.UserStatusEnum;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.app.v1.utils.ManagerUtils;
import io.renren.modules.sys.service.ShiroService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUserTokenEntity;
import io.renren.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 认证
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
@Slf4j
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ManagerUtils managerUtils;

    @Autowired
    private SysUserService sysUserService;

    @Value("${yjs.redis-key-expire.token}")
    private Long tokenExpire;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();
        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        SysUserTokenEntity userTokenEntity = shiroService.queryByToken(accessToken);
        if (userTokenEntity == null) {
            throw new IncorrectCredentialsException(ErrorMsgEnum.TOKEN_INVALID.getMsg());
        }
        SysUserEntity userEntity = sysUserService.getUserById(userTokenEntity.getUserId());
        if (userEntity == null) {
            throw new IncorrectCredentialsException(ErrorMsgEnum.TOKEN_INVALID.getMsg());
        }
        // 账号被禁用
        if (userEntity.getStatus() == UserStatusEnum.DISABLE.getValue()){
            throw new LockedAccountException(ErrorMsgEnum.ACCOUNT_DIS.getMsg());
        }
        return new SimpleAuthenticationInfo(userEntity, accessToken, getName());
    }
}
