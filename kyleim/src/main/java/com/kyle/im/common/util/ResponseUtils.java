package com.kyle.im.common.util;

import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.response.ResultData;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

public class ResponseUtils {
    /**
     * 根据组件返回的错误码重组应答报文
     * @param exception
     * @return
     */
    public static ResponseEntity getResponseFromException(HttpClientErrorException exception){
        ResponseEntity response;
        switch (exception.getStatusCode()){
            case FORBIDDEN:  response = MyResponse.forbidden(); break;
            case BAD_REQUEST: response = MyResponse.badRequest();break;
            case UNAUTHORIZED: response = MyResponse.unauthorized();break;
            default:{
                ResultData resultData = ResultData.error("未知错误");
                response = ResponseEntity.status(exception.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(resultData);
            }
        }
        return  response;
    }

    public static ResultData getResultDataFromException(HttpClientErrorException exception){
        return  (ResultData)getResponseFromException(exception).getBody();
    }
}
