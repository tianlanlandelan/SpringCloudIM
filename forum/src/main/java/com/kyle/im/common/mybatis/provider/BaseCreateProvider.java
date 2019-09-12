package com.kyle.im.common.mybatis.provider;

import com.kyle.im.common.mybatis.SqlFieldReader;

/**
 * 创建表的Provider
 * @author yangkaile
 * @date 2019-09-12 15:07:09
 */
public class BaseCreateProvider {

    /**
     *
     * 创建表的同时要创建索引，会执行多条语句，在application.properties中要设置 allowMultiQueries=true
     * spring.datasource.url = jdbc:mysql://localhost:3306/my_core
     * ?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> String create(T entity){
        return SqlFieldReader.getCreateTableSql(entity.getClass());
    }
}
