package com.kyle.log.controller;

import com.kyle.mycommon.entity.EmailEntity;
import com.kyle.mycommon.entity.SMSEntity;
import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.JsonUtils;
import com.kyle.mycommon.util.QueuesNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQHandler {
    private Logger logger = LoggerFactory.getLogger(RabbitMQHandler.class);
    /**
     * 保存短信验证码
     * @param content
     */
    @RabbitListener(queues = QueuesNames.SAVE_SMS_VERIFICATION_CODE)
    public void saveSMSVerificationCode(String content){
        logger.info("saveSMSVerificationCode",content);
        SMSEntity entity = JsonUtils.parseObject(content,SMSEntity.class);

    }

    /**
     * 保存邮件验证码
     * @param content
     */
    @RabbitListener(queues = QueuesNames.SAVE_EMAIL_VERIFICATION_CODE)
    public void saveEmailVerificationCode(String content){
        logger.info("saveEmailVerificationCode",content);
        EmailEntity entity = JsonUtils.parseObject(content,EmailEntity.class);
    }
}
