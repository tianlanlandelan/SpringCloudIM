package com.kyle.common.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kyle.common.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * ResponseEntity解析类
 * @author yangkaile
 * @date 2018-10-12 09:55:02
 */
public class MyResponseReader {
    /**
     * 判断是否是成功应答
     * 要同时满足两个条件：1.http返回码200  2.业务返回码 0
     * @param response
     * @return
     */
    public static boolean isSuccess(ResponseEntity response){
        if(response.getStatusCode() != HttpStatus.OK ){
            return false;
        }
        JSONObject jsonObject = getJSONObject(response);
        if(jsonObject.get(Constants.SUCCESS_KEY) == null){
            return false;
        }
        return jsonObject.getInteger(Constants.SUCCESS_KEY) == Constants.RESPONSE_RESULT_CODE_SUCCESS_VALUE;
    }

    /**
     * 将ResponseEntity中的data数据转换成list
     * @param response
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(ResponseEntity response, Class<T> clazz){
        JSONObject jsonObject = JSON.parseObject(getJSONString(response));
        return JSON.parseArray(jsonObject.getString(Constants.DATA_KEY), clazz);
    }

    /**
     * 将ResponseEntity中的data数据转换成Integer
     * @param responseEntity
     * @return
     */
    public static Integer getInteger(ResponseEntity responseEntity){
        return getJSONObject(responseEntity).getInteger(Constants.DATA_KEY);
    }

    /**
     * 将ResponseEntity中的data数据转换成指定的对象
     * @param responseEntity
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getObject(ResponseEntity responseEntity,Class<T> clazz){
        return getJSONObject(responseEntity).getObject(Constants.DATA_KEY,clazz);
    }

    /**
     * 将ResponseEntity的Body转换成JSONString
     * 注意：如果ResponseEntity已经是String类型的实例，直接返回body就行，
     * 若强制进行toJSONString转换会将引号转义（"" -> \"），导致转换成JSONObject失败
     * @param responseEntity
     * @return
     */
    public static String getJSONString(ResponseEntity responseEntity){
        Object object = responseEntity.getBody();
        if(object instanceof String){
            return (String)object;
        }
        return JSON.toJSONString(object);
    }
    private static JSONObject getJSONObject(ResponseEntity responseEntity){
        return JSON.parseObject(getJSONString(responseEntity));
    }

    /**
     * 将ResponseEntity中的data数据转换成JSONObject
     * @param responseEntity
     * @return
     */
    public static JSONObject getDataJSONObject(ResponseEntity responseEntity){
        return JSON.parseObject(getJSONString(responseEntity)).getJSONObject(Constants.DATA_KEY);
    }

    /**
     * 从ResponseEntity中data数据转换成的JSONObject中取某个属性的值
     * @param responseEntity
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getDataObject(ResponseEntity responseEntity,String key,Class<T> clazz){
        return getDataJSONObject(responseEntity).getObject(key,clazz);
    }
}
