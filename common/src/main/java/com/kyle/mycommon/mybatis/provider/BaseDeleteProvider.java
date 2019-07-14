package com.kyle.mycommon.mybatis.provider;

import com.kyle.mycommon.entity.Router;
import com.kyle.mycommon.util.Console;
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
     * @param <T> 对象类型
     * @return DELETE FROM router  WHERE name = #{name} AND serviceName = #{serviceName}
     */
    public static <T> String deleteByConditionAnd(T entity){
        return getDeletePrefix(entity.getClass()) + ProviderUtil.getConditionSuffix(entity,true);
    }

    /**
     * 根据条件删除，该查询为动态查询，不可缓存
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * 多个查询条件用And连接
     * @param entity 实体对象
     * @param <T> 对象类型
     * @return DELETE FROM router  WHERE name = #{name} OR serviceName = #{serviceName}
     */
    public static <T> String deleteByConditionOr(T entity){
        return getDeletePrefix(entity.getClass()) + ProviderUtil.getConditionSuffix(entity,false);
    }

    private static String getDeletePrefix(Class cls){
        return "DELETE FROM " + ProviderUtil.getTableName(cls) + " ";
    }

    public static void main(String[] args){
        Router router = new Router();
        router.setId("1");
        router.setServiceName("dd");
        router.setName("dd");
        Console.print("deleteById",deleteById(router));
        Console.print("deleteByIndexAnd",deleteByConditionAnd(router));
        Console.print("deleteByIndexOr",deleteByConditionOr(router));
    }

}
