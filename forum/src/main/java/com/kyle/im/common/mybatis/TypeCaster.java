package com.kyle.im.common.mybatis;

import java.util.HashMap;
import java.util.Map;

/**
 * Java类型和mysql类型间的转换
 * @author yangkaile
 * @date 2019-09-12 09:08:43
 */
public class TypeCaster {
    private static Map<String,String> map = new HashMap<>(16);
    static {
        map.put("String","varchar(50)");
        map.put("int","int");
        map.put("Integer","int");
        map.put("long","bigint");
        map.put("Long","bigint");
        map.put("Date","datetime");
        map.put("byte[]","varbinary(50)");
    }
    public static String getType(String key){
        return map.get(key);
    }
}
