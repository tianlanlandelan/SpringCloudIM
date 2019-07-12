package com.kyle.mycommon.mybatis.provider;

import com.kyle.mycommon.entity.Router;
import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangkaile on 2019/7/12.
 */
public class BaseDeleteProvider {

    public static Map<String,String> deleteByIdMap = new ConcurrentHashMap<>(16);

    public static <T> String deleteById(T entity){
        Class cls = entity.getClass();
        String className = cls.getName();
        String sql = deleteByIdMap.get(className);
        if(StringUtils.isNotEmpty(sql)){
            return sql;
        }
        sql = getDeletePrefix(cls) + " WHERE id = #{id} ";
        deleteByIdMap.put(className,sql);
        return sql;
    }

    private static String getDeletePrefix(Class cls){
        return "DELETE FROM " + BaseProvider.getTableName(cls) + " ";
    }

    public static void main(String[] args){
        Router router = new Router();
        router.setId("1");
        Console.print("selectById",deleteById(router));
    }

}
