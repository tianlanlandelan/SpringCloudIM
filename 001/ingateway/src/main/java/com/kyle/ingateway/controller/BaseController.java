package com.kyle.ingateway.controller;

import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.response.MyResponseReader;
import com.kyle.mycommon.response.ResultData;
import com.kyle.mycommon.router.MyRouters;
import com.kyle.mycommon.router.MyUserManagerRouter;
import com.kyle.mycommon.util.ConsoleLogUtils;
import com.kyle.mycommon.util.JsonUtils;
import com.kyle.mycommon.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-11-23 14:34:52
 */
@RestController
@RequestMapping("/base")
@CrossOrigin(origins = "*",allowedHeaders="*", maxAge = 3600)
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request;

    /**
     * 统一的登录接口
     * @param userName  用户名、手机号或邮箱
     * @param password  密码
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(String userName, String password){
        ConsoleLogUtils.print("login",userName,password);
        return MyResponse.ok();
    }

    /**
     * 注册接口
     * @param userName  用户名
     * @param password  密码
     * @param verificationCode  验证码
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity register(String userName,String password,String verificationCode){
        ConsoleLogUtils.print("login",userName,password,verificationCode);
        return  MyResponse.ok();
    }

    @RequestMapping(value = "/sendVCode",method = RequestMethod.GET)
    public ResponseEntity sendVCode(String userName){
        ConsoleLogUtils.print("sendVCode",userName);
        return  MyResponse.ok();
    }
    @RequestMapping(value = "/checkVCode",method = RequestMethod.GET)
    public ResponseEntity checkVCode(String userName,String code){
        ConsoleLogUtils.print("checkVCode",userName,code);
        return  MyResponse.ok();
    }




    /**
     * 根据组件返回的错误码重组应答报文
     * @param exception
     * @return
     */
    public static ResponseEntity getResponseFromException(HttpClientErrorException exception){
        ResponseEntity response;
        switch (exception.getStatusCode()){
            case FORBIDDEN:  response = MyResponse.forbidden(); break;
            case BAD_REQUEST: response = MyResponse.badRequest();break;
            case UNAUTHORIZED: response = MyResponse.unauthorized();break;
            default:{
                ResultData resultData = ResultData.error("未知错误");
                response = ResponseEntity.status(exception.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(resultData);
            }
        }
        return  response;
    }
}
