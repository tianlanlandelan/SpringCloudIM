package com.kyle.im.proxy.controller;
import com.kyle.im.common.util.Console;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
/**
 * @author yangkaile
 * @date 2019-08-28 09:05:54
 */
@RestController
@RequestMapping("test")
public class Test {

    @GetMapping
    public String get(HttpServletRequest request, HttpServletResponse response){
        printHeader(request);
        printQueryString(request);
        printPostParam(request);
        printCustomHeader(request);
        return "ok";
    }
    @PostMapping
    public String post(HttpServletRequest request){
        printHeader(request);
        printPostParam(request);
        printCustomHeader(request);
        return "OK";
    }
    @PutMapping
    public String put(HttpServletRequest request){
        printHeader(request);
        printPostParam(request);
        printCustomHeader(request);
        return "OK";
    }
    @DeleteMapping
    public String delete(HttpServletRequest request){
        printHeader(request);
        printPostParam(request);
        printCustomHeader(request);
        return "OK";
    }


    public void printHeader(HttpServletRequest request){
        //得到请求的URL地址
        String requestUrl = request.getRequestURL().toString();
        //得到请求的资源
        String requestUri = request.getRequestURI();
        //得到来访者的IP地址
        String remoteAddr = request.getRemoteAddr();

        String remoteHost = request.getRemoteHost();

        int remotePort = request.getRemotePort();

        String remoteUser = request.getRemoteUser();
        //得到请求URL地址时使用的方法
        String requestMethod = request.getMethod();

        Console.print("printHeader",requestUrl,requestUri,remoteAddr,remoteHost,remotePort,remoteUser,requestMethod);
    }

    public void printCustomHeader(HttpServletRequest request){
        String method =  request.getHeader("method");
        Console.print("method",method);

        String token =  request.getHeader("token");
        Console.print("token",token);
    }

    public void printPostParam(HttpServletRequest request){
        //request对象封装的参数是以Map的形式存储的
        Map<String, String[]> paramMap = request.getParameterMap();
        for(Map.Entry<String, String[]> entry :paramMap.entrySet()){
            String paramName = entry.getKey();
            String paramValue = "";
            String[] paramValueArr = entry.getValue();
            for (int i = 0; paramValueArr!=null && i < paramValueArr.length; i++) {
                if (i == paramValueArr.length-1) {
                    paramValue+=paramValueArr[i];
                }else {
                    paramValue+=paramValueArr[i]+",";
                }
            }
            Console.print(paramName,paramValue);
        }
    }

    public void printQueryString(HttpServletRequest request){
        //得到请求的URL地址中附带的参数
        String queryString = request.getQueryString();
        Console.print("queryString",queryString);
    }


}
