package com.kyle.im.log.controller;


import com.kyle.log.service.LogonLogService;
import com.kyle.mycommon.entity.EmailLog;
import com.kyle.mycommon.entity.LogonLog;
import com.kyle.mycommon.entity.SMSLog;
import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.JsonUtils;
import com.kyle.mycommon.util.QueuesNames;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitMQHandler {

    @Resource
    LogonLogService logonLogService;
    /**
     * 保存短信验证码
     * @param content
     */
    @RabbitListener(queues = QueuesNames.SAVE_SMS_VERIFICATION_CODE)
    public void saveSMSVerificationCode(String content){
        Console.info("saveSMSVerificationCode",content);
        SMSLog entity = JsonUtils.parseObject(content,SMSLog.class);
    }

    /**
     * 保存邮件验证码
     * @param content
     */
    @RabbitListener(queues = QueuesNames.SAVE_EMAIL_VERIFICATION_CODE)
    public void saveEmailVerificationCode(String content){
        Console.info("saveEmailVerificationCode",content);
        EmailLog entity = JsonUtils.parseObject(content,EmailLog.class);
    }
    @RabbitListener(queues = QueuesNames.SAVE_LOGON_LOG)
    public void saveLogonLog(String content){
        Console.info("saveLogonLog",content);
        LogonLog logonLog = JsonUtils.parseObject(content,LogonLog.class);
        logonLogService.insert(logonLog);

    }
}
