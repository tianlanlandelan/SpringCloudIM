package com.kyle.user.mapper;

import com.kyle.mycommon.mybatis.MyBaseMapper;
import com.kyle.user.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yangkaile
 * @date 2019-04-17 14:33:54
 */
@Mapper
public interface UserInfoMapper extends MyBaseMapper {
    String tableName = "user_info";

     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask FROM " + tableName + " WHERE id = #{id}")
     UserInfo getById(Integer Id);


     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask FROM " + tableName + " WHERE userName = #{userName}")
     UserInfo getByUserName(String userName);


     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask FROM " + tableName + " WHERE phone = #{phone}")
     UserInfo getByPhone(String phone);


     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask FROM " + tableName + " WHERE wxId = #{wxId}")
     UserInfo getByWxId(String wxId);


     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask FROM " + tableName + " WHERE email = #{email}")
     UserInfo getByEmail(String email);


     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask FROM " + tableName)
     List<UserInfo> getAll();

     @Insert("INSERT INTO " + tableName + "(id, userName, phone, wxId, email, password, createTime, mask) VALUES (#{id}, #{userName}, #{phone}, #{wxId}, #{email}, #{password}, #{createTime}, #{mask})")
     Integer insert(UserInfo userInfo);

     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);
     @Update("UPDATE " + tableName + " SET userName=#{userName}, phone=#{phone}, wxId=#{wxId}, email=#{email}, password=#{password}, createTime=#{createTime}, mask=#{mask} WHERE id = #{id}")
     Integer update(UserInfo userInfo);


}
