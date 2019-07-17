package com.kyle.log.controller;

import com.kyle.log.service.EmailService;
import com.kyle.mycommon.config.RouterName;
import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.router.RouterAttribute;
import com.kyle.mycommon.util.StringUtils;
import com.kyle.mycommon.util.ValidUserName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EmailController {
    @Resource
    private EmailService emailService;

    @RouterAttribute(id=RouterName.LOG_CHECK_VALIDATE_CODE,name = "校验验证码",description = "")
    @PostMapping(value = RouterName.LOG_CHECK_VALIDATE_CODE)
    public ResponseEntity register(String userName, String code){
        if(StringUtils.isEmpty(userName,code)){
            return MyResponse.badRequest();
        }
        if(ValidUserName.isEmail(userName)){
            return MyResponse.ok(emailService.checkValidateCode(userName,code));
        }
        return MyResponse.ok();
    }

}
