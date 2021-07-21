package io.renren.modules.app.v1.interceptor;


import cn.hutool.core.collection.CollectionUtil;
import io.renren.common.enums.ErrorMsgEnum;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.RedisKeys;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.app.v1.annotation.Permitted;
import io.renren.modules.app.v1.utils.JwtUtils;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 用户权限验证
 * @author kang
 */
@Component
public class PermitionInterceptor extends HandlerInterceptorAdapter {

    public static final String USER_KEY = "userId";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 判断是否有 Permitted 注解
        Permitted annotation = ((HandlerMethod) handler).getMethodAnnotation(Permitted.class);
        if (annotation == null) {
            return true;
        }
        Long userId = jwtUtils.validateToken(request);
        request.setAttribute(USER_KEY, userId);

        // 超级管理员拥有全部权限
        if (userId == Constant.SUPER_ADMIN) {
            return true;
        }

        // 注解中需要验证的权限,若为空，则只验证是否登录
        String permission = annotation.value();
        if (StringUtils.isEmpty(permission)) {
            return true;
        }

        // 根据userID查询缓存权限
        String roleCache = redisUtils.get(RedisKeys.getUserRoleKey(userId));
        if (StringUtils.isEmpty(roleCache)) {
            Set<String> perms = sysUserService.queryAllPerms(userId);
            if (CollectionUtil.isEmpty(perms)) {
                throw new RRException(ErrorMsgEnum.FORBIDDEN.getMsg(), ErrorMsgEnum.FORBIDDEN.getCode());
            }
            redisUtils.set(RedisKeys.getUserRoleKey(userId), perms);
            if (!perms.contains(permission)) {
                throw new RRException(ErrorMsgEnum.FORBIDDEN.getMsg(), ErrorMsgEnum.FORBIDDEN.getCode());
            }
        } else {
            if (!roleCache.contains(permission)) {
                throw new RRException(ErrorMsgEnum.FORBIDDEN.getMsg(), ErrorMsgEnum.FORBIDDEN.getCode());
            }
        }
        return true;
    }
}
