package com.kyle.im.user.service;

import com.kyle.im.common.config.PublicConfig;
import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.entity.LogonLog;
import com.kyle.im.common.response.ResponseReader;
import com.kyle.im.common.response.ResultData;
import com.kyle.im.common.util.*;
import com.kyle.im.user.entity.UserInfo;
import com.kyle.im.user.mapper.UserInfoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
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

    @Resource
    private RestTemplate restTemplate;

    /**
     *
     * @param userName
     * @param code
     * @return
     */
    public ResultData checkCodeAndLogon(String userName, String code){
        try{
            ResponseEntity responseEntity;
            if(ValidUserName.isEmail(userName)) {
                responseEntity = restTemplate.getForEntity(PublicConfig.SERVICE_URL
                                + RouterName.LOG_CHECK_EMAIL_VALIDATE_CODE
                                + "?email=" + userName + "&code=" + code
                        , String.class);
            }else {
                responseEntity = restTemplate.getForEntity(PublicConfig.SERVICE_URL
                                + RouterName.LOG_CHECK_SMS_VALIDATE_CODE
                                + "?phone=" + userName + "&code=" + code
                        , String.class);
            }
            Console.info("responseEntity",responseEntity.toString());

            /*
               验证码有效
             */
            if(ResponseReader.isSuccess(responseEntity)){
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
                return ResultData.success(userInfo.getId());
            }
        }
        return ResultData.error("账号密码错误");
    }

    /**
     * 添加用户时，查看是否已存在有相同的用户
     * @param userName
     * @param password
     * @return
     */
    public ResultData insert(String userName,String password){
        UserInfo userInfo = getByPhoneOrEmail(userName);
        if(userInfo != null){
            return ResultData.error("该用户已存在，请直接登录");
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
    public ResultData getUserSwitch(int id){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        if(userInfo == null){
            return ResultData.error("用户不存在");
        }else{
            return ResultData.success(userInfo.getSwitchs());
        }
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
