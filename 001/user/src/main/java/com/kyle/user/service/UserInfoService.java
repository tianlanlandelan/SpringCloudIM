package com.kyle.user.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.kyle.user.entity.UserInfo;
import com.kyle.user.mapper.UserInfoMapper;
import java.util.List;

@Service
public class UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    public UserInfo getById(Integer id){

        return userInfoMapper.getById(id);
    }

    public UserInfo getByUserName(String userName){
        return userInfoMapper.getByUserName(userName);
    }

    public UserInfo getByPhone(String phone){
        return userInfoMapper.getByPhone(phone);
    }

    public UserInfo getByWxId(String wxId){
        return userInfoMapper.getByWxId(wxId);
    }

    public UserInfo getByEmail(String email){
        return userInfoMapper.getByEmail(email);
    }


    public List<UserInfo> getAll(){
        return userInfoMapper.getAll();
    }

    public Integer insert(UserInfo userInfo){
        return userInfoMapper.insert(userInfo);
    }

    public Integer deleteById(Integer id){
        return userInfoMapper.deleteById(id);
    }

    public Integer update(UserInfo userInfo){
        return userInfoMapper.update(userInfo);
    }

}
