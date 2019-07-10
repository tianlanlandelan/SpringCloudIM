package com.kyle.user.controller;


import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.router.MyRouter;
import com.kyle.mycommon.router.RouterAttribute;
import com.kyle.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RouterAttribute(id=MyRouter.A_REGISTER,name = "注册",description = "")
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ResponseEntity register(String userName,String password){
        return MyResponse.ok();
    }

    @RouterAttribute(id=MyRouter.A_LOGON,name = "登录",description = "")
    @RequestMapping(value = "/logon",method = RequestMethod.GET)
    public ResponseEntity logon(String userName,String password){
        return MyResponse.ok();
    }
}
