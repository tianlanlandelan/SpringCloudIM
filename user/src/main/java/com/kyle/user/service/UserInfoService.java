package com.kyle.user.service;

import javax.annotation.Resource;

import com.kyle.mycommon.entity.LogonLog;
import com.kyle.mycommon.response.ResultData;
import com.kyle.mycommon.util.MD5Utils;
import com.kyle.mycommon.util.QueuesNames;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kyle.user.entity.UserInfo;
import com.kyle.user.mapper.UserInfoMapper;
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
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(phone);
        List<UserInfo> userInfos = userInfoMapper.baseSelectByCondition(userInfo);
        if(userInfos != null && userInfos.size() > 0){
            if(MD5Utils.checkQual(password,userInfos.get(0).getPassword())){
                //记录登录日志
                LogonLog logonLog = new LogonLog();
                logonLog.setPhone(phone);
                rabbitTemplate.convertAndSend(QueuesNames.SAVE_LOGON_LOG,logonLog);
                ResultData.success(userInfos.get(0).getId());
            }
        }
        return ResultData.error("账号密码错误");
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    public ResultData logonWithEmail(String email,String password){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        List<UserInfo> userInfos = userInfoMapper.baseSelectByCondition(userInfo);
        if(userInfos != null && userInfos.size() > 0){
            if(MD5Utils.checkQual(password,userInfos.get(0).getPassword())){
                //记录登录日志
                LogonLog logonLog = new LogonLog();
                logonLog.setEmail(email);
                rabbitTemplate.convertAndSend(QueuesNames.SAVE_LOGON_LOG,logonLog);
                ResultData.success(userInfos.get(0).getId());
            }
        }
        return ResultData.error("账号密码错误");
    }

    public ResultData insert(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(100);
        userInfo.setPhone("12345678901");
        userInfo.setPassword("123456");
        userInfoMapper.baseInsert(userInfo);
        return ResultData.success(userInfo);
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
