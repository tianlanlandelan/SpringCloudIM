package com.kyle.im.log.controller;


import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.util.StringUtils;
import com.kyle.im.common.util.ValidUserName;
import com.kyle.im.log.service.SMSService;
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
    @GetMapping(RouterName.LOG_CHECK_SMS_VALIDATE_CODE)
    public ResponseEntity checkVCode(String phone,String code){
        if(StringUtils.isEmpty(phone,code) || !ValidUserName.isPhoneNo(phone)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(smsService.checkValidateCode(phone,code));
    }
}
