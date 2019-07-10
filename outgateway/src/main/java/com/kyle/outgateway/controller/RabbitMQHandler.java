package com.kyle.outgateway.controller;

import com.kyle.mycommon.entity.EmailEntity;
import com.kyle.mycommon.entity.SMSEntity;
import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.response.ResultData;
import com.kyle.mycommon.util.*;
import com.kyle.outgateway.utils.SendEmailUtils;
import com.kyle.outgateway.utils.SendSMSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 处理RabbitMQ队列中与本组件相关的业务
 * @author yangkaile
 * @date 2019-07-10 10:28:13
 *
 */
@Component
public class RabbitMQHandler {

    private Logger logger = LoggerFactory.getLogger(RabbitMQHandler.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;


    /**
     *
     * @param content
     */
    @RabbitListener(queues = QueuesNames.SEND_VERIFICATION_CODE)
    public void sendVerificationCode(String content){
        logger.info("sendVerificationCode",content);
        if(StringUtils.isEmpty(content)){
            return;
        }
        if(ValidUserName.isEmail(content)){
            EmailEntity entity = SendEmailUtils.sendVerificationCode(content);
            rabbitTemplate.convertAndSend(JsonUtils.toJSONString(entity));
        }else if(ValidUserName.isPhoneNo(content)){
            SMSEntity entity =  SendSMSUtils.sendVerificationCode(content);
            rabbitTemplate.convertAndSend(JsonUtils.toJSONString(entity));
        }
    }

    public ResponseEntity sendText(String emailAddress, String title, String content){
        try{
            if(emailAddress == null || title == null || content == null){
                return MyResponse.badRequest();
            }else{
                SendEmailUtils.sendSimpleMail(emailAddress,title,content);
            }
        }catch (Exception e){
            e.printStackTrace();
            return MyResponse.serverError();
        }
        return MyResponse.ok(ResultData.success());
    }


}
