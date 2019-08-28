package com.kyle.im.log.controller;


import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.router.RouterAttribute;
import com.kyle.im.common.util.Console;
import com.kyle.im.common.util.StringUtils;
import com.kyle.im.common.util.ValidUserName;
import com.kyle.im.log.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EmailController {
    @Resource
    private EmailService emailService;

    @RouterAttribute(name = "校验验证码",description = "")
    @GetMapping(RouterName.LOG_CHECK_EMAIL_VALIDATE_CODE)
    public ResponseEntity checkEmailValidateCode(String email, String code){
        Console.info("checkEmailValidateCode",email,code);
        if(StringUtils.isEmpty(email,code) || !ValidUserName.isEmail(email)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(emailService.checkValidateCode(email,code));
    }

}
