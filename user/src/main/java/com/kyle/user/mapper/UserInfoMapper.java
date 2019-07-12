package com.kyle.user.mapper;

import com.kyle.mycommon.provider.BaseMapper;
import com.kyle.user.entity.UserInfo;
import org.apache.ibatis.annotations.*;

/**
 * @author yangkaile
 * @date 2019-04-17 14:33:54
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    String tableName = "user_info";

     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask FROM " + tableName + " WHERE id = #{id}")
     UserInfo getById(Integer Id);

}
