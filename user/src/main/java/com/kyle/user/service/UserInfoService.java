package com.kyle.user.service;

import javax.annotation.Resource;

import com.kyle.mycommon.config.RouterName;
import com.kyle.mycommon.config.ServiceName;
import com.kyle.mycommon.entity.LogonLog;
import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.response.MyResponseReader;
import com.kyle.mycommon.response.ResultData;
import com.kyle.mycommon.util.*;
import com.kyle.user.entity.UserProfile;
import com.kyle.user.mapper.UserProfileMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.kyle.user.entity.UserInfo;
import com.kyle.user.mapper.UserInfoMapper;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author yangkaile
 * @date 2019-07-12 21:20:43
 */
@Service
public class UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Resource
    private RestTemplate restTemplate;

    /**
     *
     * @param userName
     * @param code
     * @return
     */
    public ResultData checkCodeAndLogon(String userName,String code){
        ServiceInstance serviceInstance = loadBalancer.choose(ServiceName.LOG);
        try{
            ResponseEntity responseEntity;
            if(ValidUserName.isEmail(userName)) {
                responseEntity = restTemplate.getForEntity(serviceInstance.getUri().toString()
                                + RouterName.LOG_CHECK_EMAIL_VALIDATE_CODE
                                + "?email=" + userName + "&code=" + code
                        , String.class);
            }else {
                responseEntity = restTemplate.getForEntity(serviceInstance.getUri().toString()
                                + RouterName.LOG_CHECK_SMS_VALIDATE_CODE
                                + "?phone=" + userName + "&code=" + code
                        , String.class);
            }
            Console.info("responseEntity",responseEntity.toString());

            /*
               验证码有效
             */
            if(MyResponseReader.isSuccess(responseEntity)){
                UserInfo userInfo = getByPhoneOrEmail(userName);
                if(userInfo != null){
                    //记录登录日志
                    LogonLog logonLog = new LogonLog();
                    logonLog.setUserId(userInfo.getId());
                    if(ValidUserName.isPhoneNo(userName)){
                        logonLog.setPhone(userName);
                    }else {
                        logonLog.setEmail(userName);
                    }

                    rabbitTemplate.convertAndSend(QueuesNames.SAVE_LOGON_LOG,JsonUtils.toJSONString(logonLog));
                    return ResultData.success(userInfo.getId());
                }
            }
            /*
              验证码无效
             */
            return ResultData.error("验证码无效");
        }catch (HttpClientErrorException e){
            e.printStackTrace();
            return ResponseUtils.getResultDataFromException(e);
        }
    }

    /**
     * 手机号登录
     * 验证手机号密码是否正确，验证通过后返回OK，并记录登录日志
     * @param phone
     * @param password
     * @return
     */
    public ResultData logonWithPhone(String phone,String password){
        UserInfo userInfo = getByPhoneOrEmail(phone);
        if(userInfo != null ){
            if(MD5Utils.checkQual(password,userInfo.getPassword())){
                //记录登录日志
                LogonLog logonLog = new LogonLog();
                logonLog.setUserId(userInfo.getId());
                logonLog.setPhone(phone);

                rabbitTemplate.convertAndSend(QueuesNames.SAVE_LOGON_LOG,JsonUtils.toJSONString(logonLog));
                return ResultData.success(userInfo.getId());
            }
        }
        return ResultData.error("账号密码错误");
    }
    private UserInfo getByPhoneOrEmail(String userName){
        if(ValidUserName.notPhoneOrEmail(userName)) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        if(ValidUserName.isPhoneNo(userName)) {
            userInfo.setPhone(userName);
        }else {
            userInfo.setEmail(userName);
        }
        List<UserInfo> userInfos = userInfoMapper.baseSelectByCondition(userInfo);
        if(userInfos != null && userInfos.size() > 0){
            return userInfos.get(0);
        }else {
            return null;
        }
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    public ResultData logonWithEmail(String email,String password){
        UserInfo userInfo = getByPhoneOrEmail(email);
        if(userInfo != null ){
            if(MD5Utils.checkQual(password,userInfo.getPassword())){
                //记录登录日志
                LogonLog logonLog = new LogonLog();
                logonLog.setUserId(userInfo.getId());
                logonLog.setEmail(email);
                rabbitTemplate.convertAndSend(QueuesNames.SAVE_LOGON_LOG,JsonUtils.toJSONString(logonLog));
                return ResultData.success(userInfo.getId());
            }
        }
        return ResultData.error("账号密码错误");
    }

    /**
     *
     * @param userName
     * @param password
     * @return
     */
    public ResultData insert(String userName,String password){
        UserInfo userInfo = getByPhoneOrEmail(userName);
        if(userInfo != null){
            return ResultData.success(userInfo.getId());
        }
        userInfo = new UserInfo();
        if(ValidUserName.isPhoneNo(userName)){
            userInfo.setPhone(userName);
        }
        if(ValidUserName.isEmail(userName)){
            userInfo.setEmail(userName);
        }
        try {
            userInfo.setPassword(MD5Utils.EncoderByMd5(password));
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return ResultData.error("数据异常");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return ResultData.error("数据异常");
        }
        userInfoMapper.baseInsertAndReturnKey(userInfo);
        return ResultData.success(userInfo.getId());
    }

    public ResultData deleteById(int id){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        return ResultData.success(userInfoMapper.baseDeleteById(userInfo));
    }

    public ResultData deleteByPhone(String phone){
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(phone);
        return ResultData.success(userInfoMapper.baseDeleteByCondition(userInfo));
    }

    public ResultData getById(int id){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        return ResultData.success(userInfo);
    }
    public ResultData getAll(){
        UserInfo userInfo = new UserInfo();
        List<UserInfo> list = userInfoMapper.baseSelectAll(userInfo);
        return ResultData.success(list);
    }
    public ResultData getByPhone(String phone){
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(phone);
        List<UserInfo> list = userInfoMapper.baseSelectByCondition(userInfo);
        return ResultData.success(list);
    }


}
