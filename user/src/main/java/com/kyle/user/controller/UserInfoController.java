package com.kyle.user.controller;

import com.kyle.user.entity.UserInfo;
import com.kyle.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    ResponseEntity getById(Integer id){
        UserInfo result = userInfoService.getById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/getByUserName",method = RequestMethod.GET)
    ResponseEntity getByUserName(String userName){
        UserInfo result = userInfoService.getByUserName(userName);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
    @RequestMapping(value = "/getByPhone",method = RequestMethod.GET)
    ResponseEntity getByPhone(String phone){
        UserInfo result = userInfoService.getByPhone(phone);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
    @RequestMapping(value = "/getByWxId",method = RequestMethod.GET)
    ResponseEntity getByWxId(String wxId){
        UserInfo result = userInfoService.getByWxId(wxId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
    @RequestMapping(value = "/getByEmail",method = RequestMethod.GET)
    ResponseEntity getByEmail(String email){
        UserInfo result = userInfoService.getByEmail(email);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    ResponseEntity getAll(){
        List<UserInfo> result = userInfoService.getAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    ResponseEntity insert(UserInfo userInfo){
        Integer result = userInfoService.insert(userInfo);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.DELETE)
    ResponseEntity deleteById(Integer id){
        Integer result = userInfoService.deleteById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    ResponseEntity update(UserInfo userInfo){
        Integer result = userInfoService.update(userInfo);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

}
