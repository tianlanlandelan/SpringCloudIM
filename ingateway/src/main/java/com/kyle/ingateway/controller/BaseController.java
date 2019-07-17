package com.kyle.ingateway.controller;

import com.kyle.mycommon.config.RouterName;
import com.kyle.mycommon.config.ServiceName;
import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.response.MyResponseReader;
import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.QueuesNames;
import com.kyle.mycommon.util.StringUtils;
import com.kyle.mycommon.util.ValidUserName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-11-23 14:34:52
 */
@RestController
@RequestMapping("/base")
@CrossOrigin(origins = "*",allowedHeaders="*", maxAge = 3600)
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private HttpServletRequest request;

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

        ServiceInstance serviceInstance = loadBalancer.choose(ServiceName.USER);

        Map<String, String> map = new HashMap<>(16);
        map.put("userName", userName);
        map.put("password",password);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                serviceInstance.getUri().toString() + RouterName.USER_LOGON,null,String.class,map);
        if(!MyResponseReader.isSuccess(responseEntity)){
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

        ServiceInstance serviceInstance = loadBalancer.choose(ServiceName.USER);

        Map<String, String> map = new HashMap<>(16);
        map.put("userName", userName);
        map.put("code",code);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                serviceInstance.getUri().toString() + RouterName.USER_LOGON_WITH_VALIDATE_CODE,null,String.class,map);
        if(!MyResponseReader.isSuccess(responseEntity)){
            return MyResponse.badRequest();
        }

        return responseEntity;
    }

    /**
     * 发送验证码
     * @param userName 手机号或邮箱
     * @return
     */
    @RequestMapping(value = "/sendVCode",method = RequestMethod.GET)
    public ResponseEntity sendVCode(String userName){
        Console.print("sendVCode",userName);
        if(StringUtils.isEmpty(userName)){
            return MyResponse.badRequest();
        }
        if(ValidUserName.isEmail(userName) || ValidUserName.isPhoneNo(userName)){
            rabbitTemplate.convertAndSend(QueuesNames.SEND_VERIFICATION_CODE,userName);
            return  MyResponse.ok();
        }
        return MyResponse.badRequest();
    }
    @RequestMapping(value = "/checkVCode",method = RequestMethod.GET)
    public ResponseEntity checkVCode(String userName,String code){
        Console.print("checkVCode",userName,code);
        return  MyResponse.ok();
    }





}
