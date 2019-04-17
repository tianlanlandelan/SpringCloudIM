package com.kyle.mycommon.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * @author yangkaile
 * @date 2018-11-15 21:56:32
 */
public class JsonUtils {


    /**
     * 将Json字符串转换为Java对象
     * @param json  json字符串
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz){
        return JSON.parseObject(json,clazz);
    }

    /**
     * 将Json字符串转换为对象数组
     * @param json  json字符串
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz){
        return JSON.parseArray(json, clazz);
    }
    /**
     * 将Java对象转换为Json字符串
     * @param object
     * @return
     */
    public static String toJSONString(Object object){
        return JSON.toJSONString(object);
    }

    /**
     * 将json字符串转换为json对象
     * @param json
     * @return
     */
    public static JSONObject toJSONObject(String json){
        return JSON.parseObject(json);
    }

    /**
     * 从一个json字符串中，将某个key对应的值转换成字符串
     * @param json
     * @param key
     * @return
     */
    public static String getStringByKey(String json, String key){
        return toJSONObject(json).getString(key);
    }

    /**
     * 从一个json字符串中，将某个key对应的值转换成Integer
     * @param json
     * @param key
     * @return
     */
    public static Integer getIntegerByKey(String json, String key){
        return toJSONObject(json).getInteger(key);
    }

    /**
     * 从一个json字符串中，将某个key对应的值转换成Date
     * @param json  json字符串
     * @param key   要转换的key
     * @return 转换后的Date
     */
    public static Date getDateByKey(String json, String key){
        return toJSONObject(json).getDate(key);
    }

    /**
     * 从一个json字符串中，将某个key对应的值转换成java对象
     * @param json  json字符串
     * @param key   要转换的key
     * @param clazz
     * @param <T>   要转换的类型
     * @return  转换完成的java对象
     */
    public static <T> T getObjectByKey(String json, String key, Class<T> clazz){
        return toJSONObject(json).getObject(key,clazz);
    }







    public static <T> List<T> parseArrayByKey(String json, String key, Class<T> clazz){
        return JSON.parseArray(getStringByKey(json,key), clazz);
    }


    public static void main(String[] arges){
        String json = "{\"userName\":\"guyexing@foxmail.com\",\"id\":123456}";
        System.out.println(getStringByKey(json,"userName"));
        System.out.println(getIntegerByKey(json,"id"));
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
        System.out.println(parseArray(json,TestA.class));
        json = "{\"list\":[{\"id\":1,\"name\":\"user1\"},{\"id\":2,\"name\":\"user2\"}]}";
        System.out.println(parseArrayByKey(json,"list",TestA.class));

        json = "{\"user\":{\"id\":2,\"name\":\"user2\"}}";
        System.out.println(getObjectByKey(json,"user",TestA.class));

    }

}
