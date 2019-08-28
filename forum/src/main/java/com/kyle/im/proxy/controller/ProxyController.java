package com.kyle.im.proxy.controller;

import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.util.Console;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("proxy")
/**
 * @author yangkaile
 * @date 2019-08-28 08:54:52
 * 请求转发
 */
public class ProxyController {
    @GetMapping()
    public ResponseEntity get(HttpServletRequest request){
        String requestUrl = request.getRequestURL().toString();//得到请求的URL地址
        Console.info("requestUrl",requestUrl);

        String requestUri = request.getRequestURI();//得到请求的资源
        Console.info("requestUri",requestUri);

        String queryString = request.getQueryString();//得到请求的URL地址中附带的参数
        Console.info("queryString",queryString);

        String remoteAddr = request.getRemoteAddr();//得到来访者的IP地址
        Console.info("remoteAddr",remoteAddr);

        String remoteHost = request.getRemoteHost();
        Console.info("remoteHost",remoteHost);

        int remotePort = request.getRemotePort();
        Console.info("remotePort",remotePort);

        String remoteUser = request.getRemoteUser();
        Console.info("remoteUser",remoteUser);

        String method = request.getMethod();//得到请求URL地址时使用的方法
        Console.info("method",method);

        String pathInfo = request.getPathInfo();
        Console.info("pathInfo",pathInfo);

        String localAddr = request.getLocalAddr();//获取WEB服务器的IP地址
        Console.info("localAddr",localAddr);

        String localName = request.getLocalName();//获取WEB服务器的主机名
        Console.info("localName",localName);

        return MyResponse.ok();
    }
}
