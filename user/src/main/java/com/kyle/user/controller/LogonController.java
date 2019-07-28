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
