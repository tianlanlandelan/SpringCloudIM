package com.kyle.im.gateway.service;

import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.config.ServiceName;
import com.kyle.im.common.entity.SMSLog;
import com.kyle.im.common.response.ResultData;
import com.kyle.im.common.util.Console;
import com.kyle.im.common.util.JsonUtils;
import com.kyle.im.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-09-27 10:28:48
 */
@Service
public class SendSMSService {
    @Resource
    RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancer;

    public ResultData sendLogonCode(String phone){
        String code = StringUtils.getNumbserString(6);
        // TODO 发送短信验证码
        SMSLog log = new SMSLog();
        log.setPhone(phone);
        log.setCodeStr(code);
        ResponseEntity responseEntity = restTemplate.postForEntity(
                ServiceName.getLogUrl(loadBalancer) + RouterName.LOG_SAVE_SMS + "?log={log}" ,
                null,String.class,JsonUtils.toJSONString(log));
        Console.print("sendLogonCode",responseEntity);
        return ResultData.success();
    }

}
