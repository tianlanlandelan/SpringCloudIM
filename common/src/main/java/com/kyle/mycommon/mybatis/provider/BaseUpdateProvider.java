package com.kyle.mycommon.mybatis.provider;

import com.kyle.mycommon.entity.Router;
import com.kyle.mycommon.util.Console;

import java.util.Date;
import java.util.List;

/**
 * Created by yangkaile on 2019/7/12.
 */
public class BaseUpdateProvider {

    public static <T> String updateById(T entity){
        Class cls = entity.getClass();
        StringBuilder builder = new StringBuilder();
        builder.append(getUpdatePrefix(cls));
        List<String> fields = BaseProvider.getFields(cls);
        try{
            for(String field:fields){
                if("id".equals(field)){
                    continue;
                }
                if(BaseProvider.hasValue(entity,field)){
                    builder.append(field).append(" = #{")
                            .append(field).append("} ").append(",");
                }
            }
            return builder.substring(0,builder.lastIndexOf(","));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String getUpdatePrefix(Class cls){
        return "UPDATE " + BaseProvider.getTableName(cls) + " SET ";
    }

    public static void main(String[] args){
        Router router = new Router();
        router.setId("1");
        router.setCreateTime(new Date());
        router.setMethodName("me");

        Console.print("selectById",updateById(router));
    }

}
