package com.kyle.im.user.controller;

import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.response.ResultData;
import com.kyle.im.common.router.RouterAttribute;
import com.kyle.im.common.util.StringUtils;
import com.kyle.im.common.util.ValidUserName;
import com.kyle.im.user.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-04-18 19:53:43
 */
@RestController
public class LogonController {

    @Resource
    private UserInfoService userInfoService;

    /**
     *  验证码登录
     * @param userName
     * @param code
     * @return
     */
    @RouterAttribute(name = "验证码登录接口",description = "发现手机号或邮箱没有注册时自动注册")
    @PostMapping(value = RouterName.USER_LOGON_BY_CODE)
    public ResponseEntity logonWithCode(String userName,String code){

        if(StringUtils.isEmpty(userName,code) || ValidUserName.notPhoneOrEmail(userName)){
            return MyResponse.badRequest();
        }
        // TODO 记录登录日志，返回JWT
        ResultData result = userInfoService.checkCodeAndLogon(userName,code);
        if(result.isSuccess()){

        }
        return MyResponse.ok(result);
    }

    /**
     * 账号密码登录
     * @param userName
     * @param password
     * @return
     */
    @RouterAttribute(name = "登录",description = "")
    @PostMapping(value = RouterName.USER_LOGON)
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
