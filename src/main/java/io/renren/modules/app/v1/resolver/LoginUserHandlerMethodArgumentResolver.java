package io.renren.modules.app.v1.resolver;

import cn.hutool.json.JSONUtil;
import io.renren.common.utils.RedisKeys;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.app.v1.annotation.LoginUser;
import io.renren.modules.app.v1.entity.AppUserEntity;
import io.renren.modules.app.v1.interceptor.AuthorizationInterceptor;
import io.renren.modules.app.v1.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 * @author kang
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private AppUserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(AppUserEntity.class)
                && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        Object userId = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if(userId == null){
            return null;
        }
        String redisUserStr = redisUtils.get(RedisKeys.getUserInfoKey((Long)userId));
        AppUserEntity redisUser = JSONUtil.toBean(redisUserStr, AppUserEntity.class);
        AppUserEntity dbUser = userService.getById((Long) userId);
        dbUser.setToken(redisUser.getToken());
        return dbUser;
    }
}
