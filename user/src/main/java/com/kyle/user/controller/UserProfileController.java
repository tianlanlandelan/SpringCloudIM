package com.kyle.user.controller;

import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.config.RouterName;
import com.kyle.mycommon.router.RouterAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserProfileController {
    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @RouterAttribute(name = "设置用户名片",description = "")
    @PutMapping(RouterName.USER_SET_USER_PROFILE)
    public ResponseEntity setUserProfile(){
        return MyResponse.ok();
    }

    @RouterAttribute(name = "获取用户名片",description = "")
    @GetMapping(RouterName.USER_GET_USER_PROFILE)
    public ResponseEntity getUserProfile(){
        return MyResponse.ok();
    }

}
