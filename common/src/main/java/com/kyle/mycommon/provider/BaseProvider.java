package com.kyle.mycommon.provider;

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
public class BaseProvider {

    public static Map<String,String> insertMap = new ConcurrentHashMap<>(16);

    public static Map<String,String> selectByIdMap = new ConcurrentHashMap<>(16);

    public static Map<String,String> deleteByIdMap = new ConcurrentHashMap<>(16);

    public static Map<String,String> selectAllMap = new ConcurrentHashMap<>(16);

    public static void main(String[] args){
//        provider();
        Router router = new Router();
        router.setName("routerName");
        router.setServiceName("ddd");
        Console.print("selectById",selectById(router));
        Console.print("insert",insert(router));
        Console.print("",selectByIndexWithAnd(router));
        Console.print("",selectByIndexWithOr(router));

    }


    public static <T> String insert(T entity) {
        Class cls = entity.getClass();
        String className = cls.getName();

        if(StringUtils.isNotEmpty(insertMap.get(className))){
            return insertMap.get(className);
        }
        String fieldStr = getFieldStr(cls);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ")
                .append(getTableName(cls)).append(" ")
                .append("(").append(fieldStr).append(") ")
                .append("VALUES(");

        StringBuilder valuesStr = new StringBuilder();
        String[] arrays = fieldStr.split(",");
        for(String str:arrays){
            valuesStr.append("#{").append(str).append("}").append(",");
        }
        builder.append(valuesStr.substring(0,valuesStr.length() - 1))
                .append(") ");
        String sql = builder.toString();
        insertMap.put(className,sql);
        return sql;
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

    public static  <T> String deleteById(T entity){
        Class cls = entity.getClass();
        String className = cls.getName();

        if(StringUtils.isNotEmpty(deleteByIdMap.get(className))){
            return deleteByIdMap.get(className);
        }
        String sql = getDeletePrefix(cls) + " WHERE id = #{id}";
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
    private static <T> String selectByIndex(T entity,boolean isAnd){
        String condition ;
        if(isAnd){
            condition = "AND";
        }else {
            condition = "OR";
        }
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
                                .append(condition).append(" ");
                    }

                }
            }
            return builder.substring(0,builder.lastIndexOf(condition));

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 多个查询条用 OR 连接
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> String selectByIndexWithOr(T entity){
       return selectByIndex(entity,false);
    }

    /**
     * 多个查询条件用 AND 连接
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> String selectByIndexWithAnd(T entity){
        return selectByIndex(entity,true);
    }



    /**
     * 读取表名，要求类上有@TableAttribute注解
     * @param cls
     * @return
     */
    private static String getTableName(Class cls){
        TableAttribute table = (TableAttribute) cls.getAnnotation(TableAttribute.class);
        if(table == null){
            return null;
        }
        return table.value();
    }
    /**
     * 将所有字段名以逗号拼接起来返回
     * 从属性前的@FieldAttribute注解解析要查询的字段名
     * 当所有属性都没有@FieldAttribute注解时，解析所有属性名作为字段名
     * @param cls
     * @return
     */
    private static String getFieldStr(Class cls){
        Field[] fields = cls.getDeclaredFields();
        //带@FieldAttribute注解的属性名
        StringBuilder builder = new StringBuilder();
        //所有属性名
        StringBuilder allFields = new StringBuilder();
        for(Field field:fields){
            allFields.append(field.getName()).append(",");
            if(field.getAnnotation(FieldAttribute.class) != null){
                builder.append(field.getName()).append(",");
            }
        }
        if(builder.length() > 0){
            return builder.substring(0,builder.length() - 1);
        }else if(allFields.length() > 0){
            return allFields.substring(0,allFields.length() - 1);
        }else {
            return  null;
        }
    }

    private static String getSelectPrefix(Class cls){
        return "SELECT " + getFieldStr(cls) + " FROM " + getTableName(cls) + " ";
    }

    private static String getDeletePrefix(Class cls){
        return "DELETE FROM " + getTableName(cls) + " ";
    }

    private static String getUpdatePrefix(Class cls){
        return "UPDATE " + getTableName(cls) + " SET ";
    }
}
