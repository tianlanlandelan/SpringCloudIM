package com.kyle.mycommon.mybatis.provider;


import com.kyle.mycommon.mybatis.BaseEntity;
import com.kyle.mycommon.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangkaile on 2019/7/12.
 */
public class BaseDeleteProvider {

    public static Map<String,String> deleteByIdMap = new ConcurrentHashMap<>(16);

    /**
     * 根据Id 删除数据，要求必须有id字段
     * @param entity
     * @param <T>
     * @return DELETE FROM router  WHERE id = #{id}
     */
    public static <T> String deleteById(T entity){
        Class cls = entity.getClass();
        String className = cls.getName();
        String sql = deleteByIdMap.get(className);
        if(StringUtils.isNotEmpty(sql)){
            return sql;
        }
        sql = getDeletePrefix(cls) + " WHERE id = #{id} ";
        deleteByIdMap.put(className,sql);
        return sql;
    }

    /**
     * 根据条件删除，该查询为动态查询，不可缓存
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * 多个查询条件用And连接
     * @param entity 实体对象
     * param and 多个查询条件组合方式 null:不指定查询条件  true:多个查询条件用AND连接  false:多个查询条件用OR连接
     * @param <T> 对象类型
     * @return DELETE FROM router  WHERE name = #{name} AND serviceName = #{serviceName}
     */
    public static <T extends BaseEntity> String deleteByCondition(T entity){
        return getDeletePrefix(entity.getClass()) + ProviderUtil.getConditionSuffix(entity);
    }


    private static String getDeletePrefix(Class cls){
        return "DELETE FROM " + ProviderUtil.getTableName(cls) + " ";
    }

    public static void main(String[] args){

    }

}
