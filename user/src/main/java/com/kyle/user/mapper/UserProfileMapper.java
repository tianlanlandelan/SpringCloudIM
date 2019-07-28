package com.kyle.user.mapper;

import com.kyle.mycommon.mybatis.BaseMapper;
import com.kyle.user.entity.UserProfile;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangkaile
 * @date 2019-07-28 13:09:39
 */
@Mapper
public interface UserProfileMapper extends BaseMapper<UserProfile> {

}
