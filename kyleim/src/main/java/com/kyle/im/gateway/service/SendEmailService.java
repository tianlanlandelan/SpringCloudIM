package com.kyle.im.gateway.service;

import com.kyle.im.common.config.PublicConfig;
import com.kyle.im.common.entity.EmailLog;
import com.kyle.im.common.response.ResultData;
import com.kyle.im.gateway.utils.SendEmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ResultData sendCode(int type,String email){
        EmailLog log = SendEmailUtils.sendVCode(type,email);

        if(log == null){
            return ResultData.error("类型错误");
        }else if(log.getStatusCode() == PublicConfig.FAILED){
            return ResultData.error("发送失败," + log.getResult());
        }else{
            return ResultData.success();
        }
    }

}
