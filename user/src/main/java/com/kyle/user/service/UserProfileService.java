package com.kyle.user.service;

import com.kyle.mycommon.mybatis.MyBaseEntity;
import com.kyle.mycommon.mybatis.MyBaseUtils;
import com.kyle.mycommon.response.ResultData;
import com.kyle.user.entity.UserProfile;
import com.kyle.user.mapper.UserProfileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserProfileService {
    @Resource
    private UserProfileMapper userProfileMapper;

    MyBaseEntity baseEntity = MyBaseUtils.getBaseEntity(UserProfile.class);

    public ResultData getById(int id){
        baseEntity.setId(id);
        UserProfile profile = userProfileMapper.baseGetById(baseEntity);
        return ResultData.success();
    }

}
