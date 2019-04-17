package com.kyle.mycommon.mybatis;

import com.kyle.mycommon.response.ResultData;
import com.kyle.mycommon.util.Constants;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 负责存储和解析MyBaseEntity对象
 * @author yangkaile
 * @date 2018-11-29 14:06:11
 */
public class MyBaseUtils {
    private static HashMap<String,MyBaseEntity> baseEntityMap = new HashMap<>(16);

    /**
     * 获取指定的MyBaseEntity对象
     * 每次获取都会返回一个新对象，不会有并发问题
     * @param entity    Entity实体类 该类必须添加@TableAttribute注解 类中的属性要有@FieldAttribute注解
     * @return
     */
    public static MyBaseEntity getBaseEntity(Class entity){
        MyBaseEntity baseEntity = baseEntityMap.get(entity.getSimpleName());
        if(baseEntity == null){
           return  putBaseEntity(entity);
        }
        return new MyBaseEntity(baseEntity.getTableName(),baseEntity.getAllFields(),baseEntity.getFieldMap());
    }

    /**
     * 将一个Entity对象解析成MyBaseEntity对象
     * 从类前的@TableAttribute注解中解析出表名
     * 从属性前的@FieldAttribute注解解析要查询的字段名,当所有属性都没有@FieldAttribute注解时，解析所有属性名作为字段名
     * @param entity
     * @return
     */
    private static MyBaseEntity putBaseEntity(Class entity){
        String key = entity.getSimpleName();
        TableAttribute table = (TableAttribute) entity.getAnnotation(TableAttribute.class);
        if(table == null){
            return null;
        }
        String tableName =  table.value();
        String fieldsStr;
        Field[] fields = entity.getDeclaredFields();
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
            fieldsStr = builder.substring(0,builder.length() - 1);
        }else if(allFields.length() > 0){
            fieldsStr = allFields.substring(0,allFields.length() - 1);
        }else {
            return  null;
        }
        MyBaseEntity baseEntity = new MyBaseEntity(tableName,fieldsStr,map);
        baseEntityMap.put(key,baseEntity);
        return new MyBaseEntity(tableName,fieldsStr,map);
    }

    /**
     * 统一的分页查询方法，可以在Service层直接调用
     * @param currentPage
     * @param pageSize
     * @param mapper
     * @param baseEntity
     * @param <T>
     * @return
     */
    public static <T extends MyBaseMapper> ResultData getPageList(int currentPage, int pageSize, T mapper, MyBaseEntity baseEntity){
        if(currentPage < 1 || pageSize < 0 || pageSize > Constants.MAX_PAGE_SIZE){
            return ResultData.error("非法数据");
        }else {
            PageList pageList = new PageList();
            //查询第一页数据时返回记录总条数
            if(currentPage == 1){
                pageList.setTotal(mapper.baseGetCount(baseEntity));
            }
            baseEntity.setPageSize(pageSize);
            baseEntity.setStartRows((currentPage - 1) * pageSize);
            pageList.setData(mapper.baseGetPageList(baseEntity));
            return ResultData.success(pageList);
        }
    }

    public static void main(String[] args){
//        MyBaseEntity entity = MyBaseUtils.getBaseEntity(Router.class);
//        System.out.println(entity);
    }
}
