package com.kyle.im.common.router;

import com.kyle.im.common.config.PublicConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangkaile
 * @date 2019-05-10 08:53:23
 * Controller中RestFul Api接口的路由信息
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RouterAttribute {
    /**
     * 接口名
     * @return
     */
    String name();

    /**
     * 权限
     * 默认不设置访问权限
     * @return
     */
    int authority() default PublicConfig.AUTHORITY_COMMON;

    /**
     * 接口描述
     * @return
     */
    String description() default "";
}
