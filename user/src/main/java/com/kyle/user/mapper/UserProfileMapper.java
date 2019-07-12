package com.kyle.user.mapper;

import com.kyle.mycommon.mybatis.MyBaseMapper;
import com.kyle.user.entity.UserProfile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProfileMapper extends MyBaseMapper<UserProfile> {

}
