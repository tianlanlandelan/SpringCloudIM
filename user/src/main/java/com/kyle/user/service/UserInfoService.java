package com.kyle.user.service;

import javax.annotation.Resource;

import com.kyle.mycommon.entity.LogonLog;
import com.kyle.mycommon.response.ResultData;
import com.kyle.mycommon.util.MD5Utils;
import com.kyle.mycommon.util.QueuesNames;
import com.kyle.mycommon.util.StringUtils;
import com.kyle.mycommon.util.ValidUserName;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kyle.user.entity.UserInfo;
import com.kyle.user.mapper.UserInfoMapper;

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
                logonLog.setPhone(phone);
                rabbitTemplate.convertAndSend(QueuesNames.SAVE_LOGON_LOG,logonLog);
                ResultData.success(userInfo.getId());
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
                logonLog.setEmail(email);
                rabbitTemplate.convertAndSend(QueuesNames.SAVE_LOGON_LOG,logonLog);
                ResultData.success(userInfo.getId());
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
