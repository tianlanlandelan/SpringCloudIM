package com.kyle.mycommon.provider;

import com.kyle.mycommon.entity.Router;
import com.kyle.mycommon.mybatis.FieldAttribute;
import com.kyle.mycommon.mybatis.TableAttribute;
import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基础的 Mybatis SQL 提供类
 * @author yangkaile
 * @date 2019-07-12 15:44:55
 *
 */
public class BaseProvider {
    /**
     * 缓存insert语句
     */
    public static Map<String,String> insertMap = new ConcurrentHashMap<>(16);

    /**
     * 缓存selectById语句
     * @param args
     */
    public static Map<String,String> selectByIdMap = new ConcurrentHashMap<>(16);

    public static Map<String,String> selectAllMap = new ConcurrentHashMap<>(16);

    public static void main(String[] args){
//        provider();
        Router router = new Router();
        router.setName("routerName");
        Console.print("selectById",selectById(router));
        Console.print("insert",insert(router));
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


//    public static  <T> String selectById(T entity){
//        Class cls = entity.getClass();
//        try {
//            Method method = cls.getMethod("getId");
//            Object object = method.invoke(entity);
//            return getSelectPrefix(cls) + " WHERE id = #{id}";
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

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
        Map<String,String> map = new HashMap<>(16);
        for(Field field:fields){
            map.put(field.getName(),field.getType().getSimpleName());
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
}
