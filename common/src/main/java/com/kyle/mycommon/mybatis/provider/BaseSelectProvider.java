package com.kyle.mycommon.mybatis.provider;

import com.kyle.mycommon.entity.Router;
import com.kyle.mycommon.mybatis.annotation.FieldAttribute;
import com.kyle.mycommon.mybatis.annotation.IndexAttribute;
import com.kyle.mycommon.mybatis.annotation.TableAttribute;
import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

    public static void main(String[] args){
        Router router = new Router();
        router.setName("routerName");
        router.setControllerName("Cdd");
        Console.print("selectById",selectById(router));
        Console.print("",selectByIndex(router));
    }





    public static  <T> String selectById(T entity){
        Class cls = entity.getClass();
        String className = cls.getName();

        if(StringUtils.isNotEmpty(selectByIdMap.get(className))){
            return selectByIdMap.get(className);
        }
        String sql = getSelectPrefix(cls) + " WHERE id = #{id}";
        selectByIdMap.put(className,sql);
        return sql;
    }

    public static <T> String selectAll(T entity){
        Class cls = entity.getClass();
        String className = cls.getName();

        if(StringUtils.isNotEmpty(selectAllMap.get(className))){
            return selectAllMap.get(className);
        }
        String sql = getSelectPrefix(cls);
        selectAllMap.put(className,sql);
        return sql;
    }

    /**
     * 根据索引字段查询，该查询为动态查询，不可缓存
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> String selectByIndex(T entity){
        Class cls = entity.getClass();
        Field[] fields = cls.getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        builder.append(getSelectPrefix(cls)).append(" WHERE ");
        try {
            for(Field field:fields){
                if(field.getAnnotation(IndexAttribute.class) != null){
                    Method method = cls.getMethod("get" + StringUtils.captureName(field.getName()));
                    if(method.invoke(entity) != null){
                        builder.append(field.getName())
                                .append(" = #{").append(field.getName()).append("} ")
                                .append("AND ");
                    }

                }
            }
            return builder.substring(0,builder.lastIndexOf("AND"));

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }





    private static String getSelectPrefix(Class cls){
        return "SELECT " + BaseProvider.getFieldStr(cls) + " FROM " + BaseProvider.getTableName(cls) + " ";
    }

    private static String getUpdatePrefix(Class cls){
        return "UPDATE " + BaseProvider.getTableName(cls) + " SET ";
    }
}
