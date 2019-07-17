package com.kyle.mycommon.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库主键的注解
 * 暂时只支持单主键设计，不支持联合主键
 * 建议每个数据库实体类都有主键注解且使用id字段作为主键
 * @author yangkaile
 * @date 2019-7-17 20:39:40
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface KeyAttribute {
}
