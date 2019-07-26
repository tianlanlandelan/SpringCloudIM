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
