package com.kyle.user.service;

import javax.annotation.Resource;

import com.kyle.mycommon.response.ResultData;
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
        return ResultData.success(userInfoMapper.baseDeleteByCondition(userInfo,true));
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
        List<UserInfo> list = userInfoMapper.baseSelectByCondition(userInfo,false,null);
        return ResultData.success(list);
    }


}
