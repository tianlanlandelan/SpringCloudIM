package com.kyle.im.gateway.service;

import com.kyle.im.common.config.PublicConfig;
import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.config.ServiceName;
import com.kyle.im.common.entity.SMSLog;
import com.kyle.im.common.response.ResultData;
import com.kyle.im.common.util.Console;
import com.kyle.im.common.util.JsonUtils;
import com.kyle.im.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class SendSMSService {

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Resource
    RestTemplate restTemplate;

    public ResultData sendLogonCode(String phone){
        String code = StringUtils.getNumbserString(6);

        //PublicConfig.TERM_OF_VALIDITY;

        // TODO 发送短信验证码

        SMSLog log = new SMSLog();
        log.setPhone(phone);
        log.setCodeStr(code);

        ServiceInstance serviceInstance = loadBalancer.choose(ServiceName.LOG);
        Console.print("serviceInstance",serviceInstance);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json;charset=utf-8");
//        HttpEntity<String> entity = new HttpEntity<>(postBody, headers);

        ResponseEntity responseEntity = restTemplate.postForEntity(
                serviceInstance.getUri().toString() + RouterName.LOG_SAVE_SMS + "?log={log}" ,
                null,String.class,JsonUtils.toJSONString(log));

        Console.print("sendLogonCode",responseEntity);
        return ResultData.success();
    }

}
