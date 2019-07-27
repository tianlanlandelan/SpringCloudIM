package com.kyle.log.controller;

import com.kyle.log.service.EmailService;
import com.kyle.mycommon.config.RouterName;
import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.router.RouterAttribute;
import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.StringUtils;
import com.kyle.mycommon.util.ValidUserName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EmailController {
    @Resource
    private EmailService emailService;

    @RouterAttribute(name = "校验验证码",description = "")
    @GetMapping(value = RouterName.LOG_CHECK_EMAIL_VALIDATE_CODE)
    public ResponseEntity checkEmailValidateCode(String email, String code){
        Console.info("checkEmailValidateCode",email,code);
        if(StringUtils.isEmpty(email,code)){
            return MyResponse.badRequest();
        }
        if(ValidUserName.isEmail(email)){
            return MyResponse.ok(emailService.checkValidateCode(email,code));
        }
        return MyResponse.ok();
    }

}
