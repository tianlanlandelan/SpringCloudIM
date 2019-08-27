package com.kyle.im.user.controller;

import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.router.RouterAttribute;
import com.kyle.im.common.util.Console;
import com.kyle.im.common.util.StringUtils;
import com.kyle.im.common.util.ValidUserName;
import com.kyle.im.user.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yangkaile
 * @date 2019-04-18 19:53:43
 */
@RestController
public class LogonController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("test")
    public ResponseEntity test(HttpServletRequest request){
        String requestUrl = request.getRequestURL().toString();//得到请求的URL地址
        Console.info("requestUrl",requestUrl);

        String requestUri = request.getRequestURI();//得到请求的资源
        Console.info("requestUri",requestUri);

        String queryString = request.getQueryString();//得到请求的URL地址中附带的参数
        Console.info("queryString",queryString);

        String remoteAddr = request.getRemoteAddr();//得到来访者的IP地址
        Console.info("remoteAddr",remoteAddr);

        String remoteHost = request.getRemoteHost();
        Console.info("remoteHost",remoteHost);

        int remotePort = request.getRemotePort();
        Console.info("remotePort",remotePort);

        String remoteUser = request.getRemoteUser();
        Console.info("remoteUser",remoteUser);

        String method = request.getMethod();//得到请求URL地址时使用的方法
        Console.info("method",method);

        String pathInfo = request.getPathInfo();
        Console.info("pathInfo",pathInfo);

        String localAddr = request.getLocalAddr();//获取WEB服务器的IP地址
        Console.info("localAddr",localAddr);

        String localName = request.getLocalName();//获取WEB服务器的主机名
        Console.info("localName",localName);

        return MyResponse.ok();
    }



    /**
     *  验证码登录
     * @param userName
     * @param code
     * @return
     */
    @RouterAttribute(name = "验证码登录接口",description = "发现手机号或邮箱没有注册时自动注册")
    @PostMapping(value = RouterName.USER_LOGON_WITH_VALIDATE_CODE)
    public ResponseEntity logonWithCode(String userName,String code){

        if(StringUtils.isEmpty(userName,code) || ValidUserName.notPhoneOrEmail(userName)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(userInfoService.checkCodeAndLogon(userName,code));
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
