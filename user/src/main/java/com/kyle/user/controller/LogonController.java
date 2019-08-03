package com.kyle.user.controller;


import com.kyle.mycommon.config.ServiceName;
import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.config.RouterName;
import com.kyle.mycommon.response.MyResponseReader;
import com.kyle.mycommon.router.RouterAttribute;
import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.ResponseUtils;
import com.kyle.mycommon.util.StringUtils;
import com.kyle.mycommon.util.ValidUserName;
import com.kyle.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yangkaile
 * @date 2019-04-18 19:53:43
 */
@RestController
public class LogonController {
    @Autowired
    private LoadBalancerClient loadBalancer;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("test")
    public ResponseEntity test(HttpServletRequest request){
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



    /**
     *  验证码登录
     * @param userName
     * @param code
     * @return
     */
    @RouterAttribute(name = "验证码登录接口",description = "发现手机号或邮箱没有注册时自动注册")
    @PostMapping(value = RouterName.USER_LOGON_WITH_VALIDATE_CODE)
    public ResponseEntity logonWithCode(String userName,String code){

        if(StringUtils.isEmpty(userName,code) || ValidUserName.notPhoneOrEmail(userName)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(userInfoService.checkCodeAndLogon(userName,code));
    }

    /**
     * 账号密码登录
     * @param userName
     * @param password
     * @return
     */
    @RouterAttribute(name = "登录",description = "")
    @PostMapping(value = RouterName.USER_LOGON)
    public ResponseEntity logon(String userName,String password){
        if(StringUtils.isEmpty(userName,password)){
            return MyResponse.badRequest();
        }
        if(ValidUserName.isPhoneNo(userName)){
            return MyResponse.ok(userInfoService.logonWithPhone(userName,password));
        }else if(ValidUserName.isEmail(userName)){
            return MyResponse.ok(userInfoService.logonWithEmail(userName,password));
        }else {
            return MyResponse.badRequest();
        }
    }
}
