package com.kyle.im.proxy.controller;


import com.kyle.im.common.config.PublicConfig;
import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.config.ServiceName;
import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.response.ResponseReader;
import com.kyle.im.common.util.Console;
import com.kyle.im.common.util.StringUtils;
import com.kyle.im.common.util.ValidUserName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-11-23 14:34:52
 * 登录、注册等非登录状态下可调用的接口
 */
@RestController
@CrossOrigin(origins = "*",allowedHeaders="*", maxAge = 3600)
public class BaseController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancer;



    /**
     * 账号密码登录
     * 去User组件验证账号密码，验证通过后保存用户登录状态
     * @param userName  手机号或邮箱
     * @param password  密码
     * @return
     */
    @RequestMapping(value = "/logon",method = RequestMethod.POST)
    public ResponseEntity logon(String userName, String password){
        Console.print("logon",userName,password);
        if(StringUtils.isEmpty(userName,password) || ValidUserName.notPhoneOrEmail(userName)){
            return MyResponse.badRequest();
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("userName", userName);
        map.put("password",password);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                PublicConfig.SERVICE_URL + RouterName.USER_LOGON,null,String.class,map);
        if(!ResponseReader.isSuccess(responseEntity)){
            return MyResponse.badRequest();
        }

        return responseEntity;
    }

    /**
     * 注册接口
     * @param userName  手机号或邮箱
     * @param code  验证码
     * @return
     */
    @RequestMapping(value = "/logonWithVCode",method = RequestMethod.POST)
    public ResponseEntity logonWithVCode(String userName,String code){
        Console.print("login",userName,code);
        if(StringUtils.isEmpty(userName,code) || ValidUserName.notPhoneOrEmail(userName)){
            return MyResponse.badRequest();
        }

        Map<String, String> map = new HashMap<>(16);
        map.put("userName", userName);
        map.put("code",code);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                PublicConfig.SERVICE_URL + RouterName.USER_LOGON_BY_CODE,null,String.class,map);
        if(!ResponseReader.isSuccess(responseEntity)){
            return MyResponse.badRequest();
        }

        return responseEntity;
    }

    /**
     * 发送登录验证码
     * 查询用户是否已注册，如果已注册，发送登录验证码，
     *  如果未注册，发送注册验证码
     * @param userName
     * @return
     */
    @RequestMapping(value = "/sendLoginCode",method = RequestMethod.POST)
    public ResponseEntity sendLoginCode(String userName){
        Console.info("sendLoginCode,userName:",userName);
        //用户名不合法
        if(ValidUserName.notPhoneOrEmail(userName)){
            return MyResponse.badRequest();
        }
        int type = hasUser(userName)?PublicConfig.LoginType:PublicConfig.RegisterType;
        ResponseEntity<String>  responseEntity;
        //发送邮件验证码
        if(ValidUserName.isEmail(userName)){
            responseEntity = restTemplate.postForEntity(
                    ServiceName.getUserUrl(loadBalancer) + RouterName.GATEWAY_SEND_LOGON_EMAIL
                            + "?type=" + type + "&email=" + userName
                    ,null,String.class);
            return responseEntity;
        }
        else{
            //TODO 发送短信验证码
            return MyResponse.error("暂时只支持邮箱");
        }
    }

    private Boolean hasUser(String userName){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                ServiceName.getUserUrl(loadBalancer) + RouterName.USER_GET_BY_NAME + "?userName=" + userName
                ,String.class);
        return ResponseReader.isSuccess(responseEntity);
    }

    /**
     * 发送注册验证码
     * @param userName 手机号或邮箱
     * @return
     */
    @RequestMapping(value = "/sendRegisterCode",method = RequestMethod.POST)
    public ResponseEntity sendRegisterCode(String userName){
        //用户名不合法
        if(ValidUserName.notPhoneOrEmail(userName)){
            return MyResponse.badRequest();
        }

        if(hasUser(userName)){
            return MyResponse.badRequest();
        }
        ResponseEntity<String>  responseEntity;
        if(ValidUserName.isEmail(userName)){
            responseEntity = restTemplate.postForEntity(
                    ServiceName.getUserUrl(loadBalancer) + RouterName.GATEWAY_SEND_LOGON_EMAIL
                            + "?type=" + PublicConfig.RegisterType + "&email=" + userName
                    ,null,String.class);
            return responseEntity;
        }else {
            //TODO 短信验证码
            return MyResponse.error("暂时只支持邮箱");
        }


    }


    @RequestMapping(value = "/checkVCode",method = RequestMethod.GET)
    public ResponseEntity checkVCode(String userName,String code){
        Console.print("checkVCode",userName,code);
        return  MyResponse.ok();
    }





}
