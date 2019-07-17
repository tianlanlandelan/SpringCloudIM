package com.kyle.user.controller;


import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.config.RouterName;
import com.kyle.mycommon.router.RouterAttribute;
import com.kyle.mycommon.util.StringUtils;
import com.kyle.mycommon.util.ValidUserName;
import com.kyle.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-04-18 19:53:43
 */
@RestController
public class LogonController {
    private Logger logger = LoggerFactory.getLogger(LogonController.class);

    @Resource
    private UserInfoService userInfoService;

    @RouterAttribute(id=RouterName.A_REGISTER,name = "注册",description = "")
    @PostMapping(value = RouterName.A_REGISTER)
    public ResponseEntity register(String userName,String password){

        return MyResponse.ok();
    }

    @RouterAttribute(id=RouterName.A_LOGON,name = "登录",description = "")
    @PostMapping(value = RouterName.A_LOGON)
    public ResponseEntity logon(String userName,String password){
        if(StringUtils.isEmpty(userName,password)){
            return MyResponse.badRequest();
        }
        if(ValidUserName.isPhoneNo(userName)){
            return MyResponse.ok(userInfoService.logonWithPhone(userName,password));
        }else if(ValidUserName.isEmail(userName)){
            return MyResponse.ok(userInfoService.logonWithEmail(userName,password));
        }else {
            return MyResponse.badRequest();
        }
    }
}
