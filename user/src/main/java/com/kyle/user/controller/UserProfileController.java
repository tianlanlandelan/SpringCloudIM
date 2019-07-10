package com.kyle.user.controller;

import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.router.MyRouter;
import com.kyle.mycommon.router.RouterAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {
    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @RouterAttribute(id=MyRouter.A_SET_USER_PROFILE,name = "设置用户名片",description = "")
    @PutMapping("/setUserProfile")
    public ResponseEntity setUserProfile(){
        return MyResponse.ok();
    }

    @RouterAttribute(id=MyRouter.A_GET_USER_PROFILE,name = "获取用户名片",description = "")
    @GetMapping("/getUserProfile")
    public ResponseEntity getUserProfile(){
        return MyResponse.ok();
    }

}
