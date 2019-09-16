package com.kyle.im.gateway.controller;

import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.util.StringUtils;
import com.kyle.im.gateway.service.SendSMSService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendSMSController {

    @Resource
    SendSMSService sendSMSService;

    @GetMapping(RouterName.GATEWAY_SEND_LOGON_SMS)
    public ResponseEntity sendLogonSMS(String phone){
        if(StringUtils.isEmpty(phone)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(sendSMSService.sendLogonCode(phone));
    }
}
