package com.kyle.im.user.controller;


import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.router.RouterAttribute;
import com.kyle.im.common.util.StringUtils;
import com.kyle.im.common.util.ValidUserName;
import com.kyle.im.user.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-04-18 19:53:43
 */
@RestController
 public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    /**
     *
     * @param userName
     * @param password
     * @return
     */
    @RouterAttribute(name = "",description = "")
    @PostMapping(RouterName.USER_INSERT_USERINFO)
    public ResponseEntity insert(String userName ,String password){
        if(StringUtils.isEmpty(userName,password)){
            return MyResponse.badRequest();
        }
        if(ValidUserName.isPhoneOrEmail(userName)){
            return MyResponse.ok(userInfoService.insert(userName,password));
        }
        return MyResponse.badRequest();
    }

    @RouterAttribute(name = "",description = "")
    @PostMapping(RouterName.USER_ADD_EMAIL)
    public ResponseEntity addEmail(int id,String email){
        return MyResponse.ok();
    }
    @RouterAttribute(name = "",description = "")
    @PostMapping(RouterName.USER_ADD_PHONE)
    public ResponseEntity addPhoneNo(int id,String phoneNo){
        return MyResponse.ok();
    }
    @RouterAttribute(name = "",description = "")
    @PutMapping(RouterName.USER_UPDATE_EMAIL)
    public ResponseEntity updateEmail(int id,String email){
        return MyResponse.ok();
    }
    @RouterAttribute(name = "",description = "")
    @PutMapping(RouterName.USER_UPDATE_PHONE)
    public ResponseEntity updatePhoneNo(int id,String phoneNo){
        return MyResponse.ok();
    }

    @GetMapping("/getById")
    public ResponseEntity getById(Integer id){
        return MyResponse.ok(userInfoService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return MyResponse.ok(userInfoService.getAll());
    }

    @GetMapping("/getByPhone")
    public ResponseEntity getByPhone(String phone){
        if(ValidUserName.isPhoneNo(phone)){
            return MyResponse.ok(userInfoService.getByPhone(phone));
        }else {
            return MyResponse.badRequest();
        }
    }







}
