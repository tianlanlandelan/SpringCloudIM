package com.kyle.im.log.controller;


import com.kyle.im.common.entity.EmailLog;
import com.kyle.im.common.entity.LogonLog;
import com.kyle.im.common.entity.SMSLog;
import com.kyle.im.common.util.Console;
import com.kyle.im.common.util.JsonUtils;
import com.kyle.im.log.service.LogonLogService;
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
    public void saveSMSVerificationCode(String content){
        Console.info("saveSMSVerificationCode",content);
        SMSLog entity = JsonUtils.parseObject(content,SMSLog.class);
    }

    /**
     * 保存邮件验证码
     * @param content
     */
    public void saveEmailVerificationCode(String content){
        Console.info("saveEmailVerificationCode",content);
        EmailLog entity = JsonUtils.parseObject(content,EmailLog.class);
    }
    public void saveLogonLog(String content){
        Console.info("saveLogonLog",content);
        LogonLog logonLog = JsonUtils.parseObject(content,LogonLog.class);
        logonLogService.insert(logonLog);

    }
}
