package com.kyle.im.log.controller;

import com.kyle.log.service.SMSService;
import com.kyle.mycommon.config.RouterName;
import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.util.StringUtils;
import com.kyle.mycommon.util.ValidUserName;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-07-28 13:06:07
 */
@Controller
public class SMSController {

    @Resource
    SMSService smsService;

    /**
     *
     * @param phone
     * @param code
     * @return
     */
    @GetMapping(RouterName.LOG_CHECK_EMAIL_VALIDATE_CODE)
    public ResponseEntity checkVCode(String phone,String code){
        if(StringUtils.isEmpty(phone,code) || !ValidUserName.isPhoneNo(phone)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(smsService.checkValidateCode(phone,code));
    }
}
