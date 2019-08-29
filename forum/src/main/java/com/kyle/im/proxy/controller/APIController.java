package com.kyle.im.proxy.controller;
import com.kyle.im.common.config.PublicConfig;
import com.kyle.im.common.config.RequestConfig;
import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.util.Console;
import com.kyle.im.common.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * @author yangkaile
 * @date 2019-08-28 09:05:54
 * 接口转发
 */
@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*",allowedHeaders="*", maxAge = 3600)
public class APIController {

    @Resource
    HttpServletRequest request;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity get(){
        readRequest();
        try {
            return restTemplate.getForEntity(getUrl(), String.class, getParam());
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
    }

    @PostMapping
    public ResponseEntity post(){
        readRequest();
        try {
            return restTemplate.postForEntity(getUrl(),null,String.class,getParam());
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
    }
    @PutMapping
    public ResponseEntity put(){
        readRequest();
        try {
            restTemplate.put(getUrl(),null,getParam());
            return MyResponse.ok();
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
    }
    @DeleteMapping
    public ResponseEntity delete(){
        readRequest();
        try {
            restTemplate.delete(getUrl(),getParam());
            return  MyResponse.ok();
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
    }

    public void readRequest(){
        Console.print("header",getHeader());
        Console.print("param",getParam());
    }

    public HashMap<String,String> getHeader(){
        HashMap<String,String> headerMap = new HashMap<>(16);
        //请求的URL地址
        headerMap.put(RequestConfig.URL,request.getRequestURL().toString());
        //请求的资源
        headerMap.put(RequestConfig.URI,request.getRequestURI());
        //请求方式 GET/POST
        headerMap.put(RequestConfig.REQUEST_METHOD,request.getMethod());

        //来访者的IP地址
        headerMap.put(RequestConfig.REMOTE_ADDR,request.getRemoteAddr());
        //来访者的HOST
        headerMap.put(RequestConfig.REMOTE_HOST,request.getRemoteHost());
        //来访者的端口
        headerMap.put(RequestConfig.REMOTE_PORT,request.getRemotePort() + "");
        //来访者的用户名
        headerMap.put(RequestConfig.REMOTE_USER,request.getRemoteUser());


        //自定义的Header （接口名）
        headerMap.put(RequestConfig.METHOD,request.getHeader(RequestConfig.METHOD));
        //自定义的Header （TOKEN）
        headerMap.put(RequestConfig.TOKEN,request.getHeader(RequestConfig.TOKEN));
        return headerMap;
    }


    public HashMap<String,String> getParam(){
        HashMap<String,String> paramMap = new HashMap<>(16);
        //request对象封装的参数是以Map的形式存储的
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry :map.entrySet()){
            String paramName = entry.getKey();
            String paramValue = "";
            String[] paramValueArr = entry.getValue();
            for (int i = 0; paramValueArr!=null && i < paramValueArr.length; i++) {
                if (i == paramValueArr.length-1) {
                    paramValue += paramValueArr[i];
                }else {
                    paramValue += paramValueArr[i]+",";
                }
            }
            paramMap.put(paramName,paramValue);
        }
        return paramMap;
    }
    private String getUrl(){
        HashMap<String,String> headers = getHeader();
        HashMap<String,String> params = getParam();
        StringBuilder builder = new StringBuilder();
        builder.append(PublicConfig.SERVICE_URL)
                .append("/")
                .append(headers.get(RequestConfig.METHOD))
                .append("?");
        for(String key :params.keySet()){
            builder.append(key)
                    .append("={")
                    .append(key)
                    .append("}&");
        }
        Console.info(builder.toString());
        return builder.toString();
    }
}
