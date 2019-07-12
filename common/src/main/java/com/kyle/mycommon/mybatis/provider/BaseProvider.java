package com.kyle.mycommon.mybatis.provider;

import com.kyle.mycommon.mybatis.annotation.FieldAttribute;
import com.kyle.mycommon.mybatis.annotation.TableAttribute;
import com.kyle.mycommon.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangkaile on 2019/7/12.
 */
public class BaseProvider {
    /**
     * 读取表名，要求类上有@TableAttribute注解
     * @param cls
     * @return
     */
    public static String getTableName(Class cls){
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
    public static String getFieldStr(Class cls){
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

    /**
     * 获取所有字段列表
     * 读取类中带@FieldAttribute注解的字段，如果都没有带该注解，则返回类中所有字段
     * @param cls
     * @return
     */
    public static List<String> getFields(Class cls){
        Field[] fields = cls.getDeclaredFields();
        List<String> fieldList = new ArrayList<>();
        List<String> allList = new ArrayList<>();
        //带@FieldAttribute注解的属性名
        for(Field field:fields){
            allList.add(field.getName());
            if(field.getAnnotation(FieldAttribute.class) != null){
                fieldList.add(field.getName());
            }
        }
        if(fieldList.size() == 0){
            return allList;
        }
        return fieldList;
    }

    /**
     * 判断一个对象的指定字段有没有值
     * @param entity 对象
     * @param fieldName 对象的字段名
     * @param <T>
     * @return
     */
    public static <T> boolean hasValue(T entity,String fieldName){
        try {
            Class cls = entity.getClass();
            Method method = cls.getMethod("get" + StringUtils.captureName(fieldName));
            if(method.invoke(entity) == null){
                return false;
            }else {
                return true;
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
       return false;
    }
}
