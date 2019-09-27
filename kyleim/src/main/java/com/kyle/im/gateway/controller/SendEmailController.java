package com.kyle.im.gateway.controller;

import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.response.MyResponse;
import com.kyle.im.gateway.service.SendEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-09-27 09:41:53
 */
@RestController
public class SendEmailController {
    @Resource
    private SendEmailService sendEmailService;

    @PostMapping(RouterName.GATEWAY_SEND_LOGON_EMAIL)
    public ResponseEntity sendLoginEmail(int type,String email){
        sendEmailService.sendCode(type,email);
        return MyResponse.ok();
    }
}
