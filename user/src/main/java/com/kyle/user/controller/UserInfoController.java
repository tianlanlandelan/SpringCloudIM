package com.kyle.user.controller;

import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.router.MyRouter;
import com.kyle.mycommon.router.RouterAttribute;
import com.kyle.user.entity.UserInfo;
import com.kyle.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-04-18 19:53:43
 */
@RestController
@RequestMapping("/info")
public class UserInfoController {

    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/getById")
    public ResponseEntity getById(Integer id){
        return MyResponse.ok(userInfoService.getById(id));
    }


    @RouterAttribute(id=MyRouter.A_ADD_EMAIL,name = "",description = "")
    @PostMapping("/addEmail")
    public ResponseEntity addEmail(int id,String email){
        return MyResponse.ok();
    }
    @RouterAttribute(id=MyRouter.A_ADD_PHONE,name = "",description = "")
    @PostMapping("/addPhoneNo")
    public ResponseEntity addPhoneNo(int id,String phoneNo){
        return MyResponse.ok();
    }
    @RouterAttribute(id=MyRouter.A_UPDATE_EMAIL,name = "",description = "")
    @PutMapping("/updateEmail")
    public ResponseEntity updateEmail(int id,String email){
        return MyResponse.ok();
    }
    @RouterAttribute(id=MyRouter.A_UPDATE_PHONE,name = "",description = "")
    @PutMapping("/updatePhoneNo")
    public ResponseEntity updatePhoneNo(int id,String phoneNo){
        return MyResponse.ok();
    }



}
