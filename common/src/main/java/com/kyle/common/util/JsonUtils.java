package com.kyle.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * @author yangkaile
 * @date 2018-11-15 21:56:32
 */
public class JsonUtils {

    public static JSONObject getJSONObject(String json){
        return JSON.parseObject(json);
    }
    public static String getString(String json,String key){
        return getJSONObject(json).getString(key);
    }
    public static Integer getInteger(String json,String key){
        return getJSONObject(json).getInteger(key);
    }
    public static Date getDate(String json, String key){
        return getJSONObject(json).getSqlDate(key);
    }
    public static <T> T getObject(String json, String key, Class<T> clazz){
        return getJSONObject(json).getObject(key,clazz);
    }
    public static <T> List<T> getList(String json, String key,Class<T> clazz){
        return JSON.parseArray(getString(json,key), clazz);
    }
    public static <T> List<T> getList(String json,Class<T> clazz){
        return JSON.parseArray(json, clazz);
    }

    public static void main(String[] arges){
        String json = "{\"userName\":\"guyexing@foxmail.com\",\"id\":123456}";
        System.out.println(getString(json,"userName"));
        System.out.println(getInteger(json,"id"));
        class TestA{
            String name ;
            int id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return "TestA{" +
                        "name='" + name + '\'' +
                        ", id=" + id +
                        '}';
            }
        }
        json = "[{\"id\":1,\"name\":\"user1\"},{\"id\":2,\"name\":\"user2\"}]";
        System.out.println(getList(json,TestA.class));
        json = "{\"list\":[{\"id\":1,\"name\":\"user1\"},{\"id\":2,\"name\":\"user2\"}]}";
        System.out.println(getList(json,"list",TestA.class));

        json = "{\"user\":{\"id\":2,\"name\":\"user2\"}}";
        System.out.println(getObject(json,"user",TestA.class));

    }

}
