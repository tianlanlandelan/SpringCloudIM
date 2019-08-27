package com.kyle.im.common.mybatis.provider;

import com.kyle.im.common.util.Console;

import java.util.List;

/**
 * @author yangkaile
 * @date 2019-07-18 14:27:30
 */
public class BaseUpdateProvider {

    /**
     * 根据id 更新数据，空值不更新 ，要求数据有id字段
     * @param entity
     * @param <T>
     * @return UPDATE router SET methodName = #{methodName} ,createTime = #{createTime} WHERE id = #{id}
     */
    public static <T> String updateById(T entity){
        String sql = getUpdatePrefix(entity) + " WHERE id = #{id}";
        Console.info("updateById",sql,entity);
        return sql;
    }

    /**
     * 根据主键更新数据，空值不更新，要求数据至少有一个主键，且主键有值
     * @param entity
     * @param <T>
     * @return
     */
    public static  <T> String updateByKey(T entity){
        try {
            String sql = getUpdatePrefix(entity) + ProviderUtil.getConditionByKeySuffix(entity);
            Console.info("updateByKey",sql,entity);
            return sql;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取更新操作的前缀部分
     * @param entity
     * @return UPDATE 表名 SET
     */
    private static <T> String getUpdatePrefix(T entity){
        Class cls = entity.getClass();
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ").append(ProviderUtil.getTableName(cls)).append(" SET ");
        List<String> fields = ProviderUtil.getFields(cls);
        try{
            for(String field:fields){
                if(ProviderUtil.hasValue(entity,field)){
                    builder.append(field).append(" = #{")
                            .append(field).append("} ").append(",");
                }
            }
            return builder.substring(0,builder.lastIndexOf(",")) ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){

    }

}
