package com.kyle.mycommon.mybatis.provider;

import com.kyle.mycommon.entity.Router;
import com.kyle.mycommon.mybatis.annotation.IndexAttribute;
import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基础的 Mybatis SQL 提供类
 * @author yangkaile
 * @date 2019-07-12 15:44:55
 *
 */
public class BaseSelectProvider {

    /**
     * 缓存selectById语句
     * @param args
     */
    public static Map<String,String> selectByIdMap = new ConcurrentHashMap<>(16);

    public static Map<String,String> selectAllMap = new ConcurrentHashMap<>(16);

    public static Map<String,String> selectPrefixMap = new ConcurrentHashMap<>(16);


    /**
     * 根据ID 查询数据
     * @param entity 实体对象
     * @param <T> 实体类型
     * @return SELECT id,name... FROM route WHERE id = #{id}
     */
    public static  <T> String selectById(T entity){
        Class cls = entity.getClass();
        String className = cls.getName();
        String sql = selectByIdMap.get(className);
        if(StringUtils.isNotEmpty(sql)){
            return sql;
        }
        sql = getSelectPrefix(cls) + " WHERE id = #{id}";
        selectByIdMap.put(className,sql);
        return sql;
    }


    /**
     * 查询所有数据，不带条件
     * @param entity 实体对象
     * @param <T> 实体类型
     * @return SELECT id,name... FROM router
     */
    public static <T> String selectAll(T entity){
        Class cls = entity.getClass();
        String className = cls.getName();
        String sql = selectAllMap.get(className);
        if(StringUtils.isNotEmpty(sql)){
            return sql;
        }
        sql = getSelectPrefix(cls);
        selectAllMap.put(className,sql);
        return sql;
    }

    /**
     *
     * 带条件的查询，该查询为动态查询，不可缓存
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * 多个查询条件用And连接
     * @param entity 实体对象
     * @param <T> 实体类型
     * @return SELECT id,name... FROM router  WHERE name = #{name} AND serviceName = #{serviceName}
     */
    public static <T> String selectByConditionAnd(T entity){
        return  getSelectPrefix(entity.getClass()) + ProviderUtil.getQueryConditions(entity,true);
    }
    /**
     *
     * 带条件的查询，该查询为动态查询，不可缓存
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * 多个查询条件用Or连接
     * @param entity 实体对象
     * @param <T> 实体类型
     * @return SELECT id,name... FROM router  WHERE name = #{name} OR serviceName = #{serviceName}
     */
    public static <T> String selectByConditionOr(T entity){
        return  getSelectPrefix(entity.getClass()) + ProviderUtil.getQueryConditions(entity,false);
    }

    public static <T> String selectCount(T entity){
        return "SELECT COUNT(1) FROM " + ProviderUtil.getTableName(entity.getClass());
    }

    /**
     * 不加条件的分页查询
     * @param entity 实体对象
     * @param startRows 起始行
     * @param pageSize 查询页大小
     * @param <T> 实体类型
     * @return  SELECT id,name... FROM router  LIMIT #{startRows},#{pageSize}
     */
    public static <T> String selectPageList(T entity,int startRows,int pageSize){
        return selectAll(entity) + " LIMIT #{startRows},#{pageSize}";
    }

    /**
     * 获取通用查询前缀
     * @param cls 实体类类型
     * @return SELECT 所有字段 FROM 表名
     */
    private static <T> String getSelectPrefix(Class cls){
        String className = cls.getName();
        String sql = selectPrefixMap.get(className);
        if(StringUtils.isNotEmpty(sql)){
            return sql;
        }else {
            sql = "SELECT " + ProviderUtil.getFieldStr(cls) + " FROM " + ProviderUtil.getTableName(cls) + " ";
            return sql;
        }
    }

    public static void main(String[] args){
        Router router = new Router();
        router.setName("routerName");
        router.setServiceName("Cdd");
        Console.print("selectById",selectById(router));
        Console.print("selectByIndexAnd",selectByConditionAnd(router));
        Console.print("selectByIndexOr",selectByConditionOr(router));
        Console.print("selectCount",selectCount(router));
        Console.print("selectPageList",selectPageList(router,1,10));
    }

}
