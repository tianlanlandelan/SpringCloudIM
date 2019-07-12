package com.kyle.mycommon.mybatis.provider;

import com.kyle.mycommon.entity.Router;
import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangkaile on 2019/7/12.
 */
public class BaseInsertProvider {
    /**
     * 缓存insert语句
     */
    public static Map<String,String> insertMap = new ConcurrentHashMap<>(16);

    /**
     * 基础的添加语句
     * 读取对象的所有字段属性，生成insert语句
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> String insert(T entity) {
        Class cls = entity.getClass();
        String className = cls.getName();

        if(StringUtils.isNotEmpty(insertMap.get(className))){
            return insertMap.get(className);
        }
        String fieldStr = BaseProvider.getFieldStr(cls);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ")
                .append(BaseProvider.getTableName(cls)).append(" ")
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


    public static void main(String[] args){
        Router router = new Router();
        router.setName("routerName");
        router.setControllerName("Cdd");
        Console.print("selectById",insert(router));
    }
}