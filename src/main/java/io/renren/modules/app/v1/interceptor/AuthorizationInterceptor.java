/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.v1.interceptor;


import io.renren.common.utils.RedisUtils;
import io.renren.modules.app.v1.utils.JwtUtils;
import io.renren.modules.app.v1.annotation.Login;
import io.renren.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    public static final String USER_KEY = "userId";

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SysUserService sysUserService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        Login login = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        if(login == null){
            return true;
        }
        // 验证token是否有效
        Long userId = jwtUtils.validateToken(request);
        request.setAttribute(USER_KEY, userId);
        return true;
    }
}
