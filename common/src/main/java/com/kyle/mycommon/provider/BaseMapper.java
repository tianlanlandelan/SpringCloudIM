package com.kyle.mycommon.provider;

import com.kyle.mycommon.entity.Router;
import com.kyle.mycommon.mybatis.FieldAttribute;
import com.kyle.mycommon.mybatis.TableAttribute;
import com.kyle.mycommon.util.Console;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BaseMapper {

    public static void main(String[] args){
//        provider();
        Router router = new Router();
        router.setName("routerName");
        Console.print("selectById",selectById(router));
        Console.print("insert",insert(router));
    }


    public static <T> String insert(T entity) {
        Class cls = entity.getClass();
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
        return builder.toString();
    }

    public static  <T> String selectById(T entity){
        Class cls = entity.getClass();
        return getSelectPrefix(cls) + " WHERE id = #{id}";
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
