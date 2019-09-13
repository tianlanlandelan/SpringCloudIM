package com.kyle.im.common.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * ResponseEntity解析类
 * @author yangkaile
 * @date 2018-10-12 09:55:02
 */
public class ResponseReader {
    /**
     * 判断是否是成功应答
     * 要同时满足两个条件：1.http返回码200  2.业务返回码 0
     * @param response
     * @return
     */
    public static boolean isSuccess(ResponseEntity response){
        ResultData result = getResultData(response);
        if(result == null){
            return  false;
        }
        return result.isSuccess();
    }
    public static String getMessage(ResponseEntity response){
        ResultData result = getResultData(response);
        if(result == null){
            return  null;
        }
        return result.getMessage();
    }

    /**
     * 获取业务返回数据，如果 http 返回码不是200,直接返回null
     * 这个方法不能被外部直接调用，因为转换完成的ResultData对象中 data 字段是一个com.alibaba.fastjson.JSONObject对象
     * @param response
     * @return
     */
    private static ResultData getResultData(ResponseEntity response){
        if(response.getStatusCode() != HttpStatus.OK ){
            return null;
        }
        return JSON.parseObject(getJSONString(response),ResultData.class);
    }

    /**
     * 将ResponseEntity中的data数据转换成list
     * @param response
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(ResponseEntity response,Class<T> cls){
        if(!isSuccess(response)){
            return null;
        }
        ResultData resultData = getResultData(response);
        if(resultData == null){
            return null;
        }
        return JSON.parseArray(JSON.toJSONString(resultData.getData()),cls);
    }


    /**
     * 将ResponseEntity中的data数据转换成指定的对象
     * @param responseEntity
     * @return
     */
    public static <T> T getObject(ResponseEntity responseEntity,Class<T> cls){
        if(!isSuccess(responseEntity)){
            return null;
        }
        ResultData resultData = getResultData(responseEntity);
        if(resultData == null){
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(resultData.getData()),cls);
    }

    /**
     * 将ResponseEntity的Body转换成JSONString
     * 注意：如果ResponseEntity已经是String类型的实例，直接返回body就行，
     * 若强制进行toJSONString转换会将引号转义（"" -> \"），导致转换成JSONObject失败
     * @param responseEntity
     * @return
     */
    private static String getJSONString(ResponseEntity responseEntity){
        Object object = responseEntity.getBody();
        if(object instanceof String){
            return (String)object;
        }
        return JSON.toJSONString(object);
    }
}
