package com.kyle.user.service;

import com.kyle.mycommon.response.ResultData;
import com.kyle.user.entity.UserProfile;
import com.kyle.user.mapper.UserProfileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-07-28 13:14:45
 */
@Service
public class UserProfileService {
    @Resource
    private UserProfileMapper userProfileMapper;


    public UserProfile getById(int id){
        UserProfile userProfile = new UserProfile();
        userProfile.setId(id);
        return userProfileMapper.baseSelectById(userProfile);
    }

}
