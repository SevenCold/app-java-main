package io.renren.modules.app.v1.annotation;

import java.lang.annotation.*;

/**
 * 判断App用户是否有权限
 */
@Login
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permitted {

    /**
     * 权限信息
     * @return
     */
    String value() default "";
}
