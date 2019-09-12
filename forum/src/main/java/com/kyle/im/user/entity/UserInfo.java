package com.kyle.im.user.entity;


import com.kyle.im.common.mybatis.BaseEntity;
import com.kyle.im.common.mybatis.annotation.FieldAttribute;
import com.kyle.im.common.mybatis.annotation.IndexAttribute;
import com.kyle.im.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-04-17 14:33:54
 */
@TableAttribute("user_info")
public class UserInfo extends BaseEntity {
    /**
    * id
    */
    @FieldAttribute
     private Integer id;
    /**
    * 用户名,不重复，默认为手机号，可用于登录
    */
    @FieldAttribute
    @IndexAttribute
    private String userName;
    /**
    * 手机号，不重复，可用于登录
    */
    @FieldAttribute
    @IndexAttribute
     private String phone;

    /**
    * 邮箱，不重复，可用于登录
    */
    @FieldAttribute
    @IndexAttribute
     private String email;
    /**
    * 密码
    */
    @FieldAttribute
     private String password;
    /**
    * 创建时间
    */
    @FieldAttribute
     private Date createTime = new Date();
    /**
    * 掩码值，用来表示一系列开关（如：是否开启用户名登录、是否已删除、是否开启邮箱登录等）
    */
    @FieldAttribute
     private int switchs;

     public Integer getId(){
           return this.id;
     }
     public void setId(Integer id){
           this.id = id;
     }
     public String getUserName(){
           return this.userName;
     }
     public void setUserName(String userName){
           this.userName = userName;
     }
     public String getPhone(){
           return this.phone;
     }
     public void setPhone(String phone){
           this.phone = phone;
     }
     public String getEmail(){
           return this.email;
     }
     public void setEmail(String email){
           this.email = email;
     }
     public String getPassword(){
           return this.password;
     }
     public void setPassword(String password){
           this.password = password;
     }
     public Date getCreateTime(){
           return this.createTime;
     }
     public void setCreateTime(Date createTime){
           this.createTime = createTime;
     }
     public int getSwitchs(){
           return this.switchs;
     }
     public void setSwitchs(int switchs){
           this.switchs = switchs;
     }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", switchs=" + switchs +
                '}';
    }
}
