package com.kyle.im.gateway.service;

import com.kyle.im.common.config.PublicConfig;
import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.config.ServiceName;
import com.kyle.im.common.entity.EmailLog;
import com.kyle.im.common.response.ResponseReader;
import com.kyle.im.common.response.ResultData;
import com.kyle.im.common.util.JsonUtils;
import com.kyle.im.gateway.utils.SendEmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangkaile
 * @date 2019-09-27 08:39:27
 */
@Service
public class SendEmailService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancer;

    public ResultData sendCode(int type,String email){
        EmailLog log = SendEmailUtils.sendVCode(type,email);

        //邮件发送后，成功失败都记录日志
        if(log != null){
            ResponseEntity response = restTemplate.postForEntity(
                    ServiceName.getLogUrl(loadBalancer) + RouterName.LOG_SAVE_EMAIL + "?log={log}",
                    null,String.class,JsonUtils.toJSONString(log)
            );
            //如果Log记录成功，则
            if(ResponseReader.isSuccess(response) && log.getStatusCode() == PublicConfig.SUCCESS){
                return ResultData.success();
            }else {
                return ResultData.error("发送失败,log.getResult:" + log.getResult()
                        + ",response:" + response.toString());
            }
            //邮件未发送
        }else {
            return ResultData.error("类型错误，邮件未发送");
        }
    }

}
