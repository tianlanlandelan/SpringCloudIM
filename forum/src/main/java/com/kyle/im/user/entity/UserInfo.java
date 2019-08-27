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
     private long mask;

    /**
     * 已删除
     */
    private static int delete = 1 << 0;
    /**
     * 允许使用手机号登录
     */
     private static int phoneAvailable = 1 << 1;
    /**
     * 允许使用邮箱登录
     */
    private static int emailAvailable = 1 << 2;
    /**
     * 加我为好友需要同意
     */
    private static int addFriendUseAgree = 1 << 3;
    /**
     * 拉我进群需要同意
     */
    private static int addGroupUseAgree = 1 << 4;

    /**
     * 判断是否已删除
     * @return
     */
     public boolean isDelete(){
         return (mask & delete) == delete;
     }

    /**
     * 设置用户是否删除
     */
     public void setDelete(boolean yes){
         if(yes){
             mask = mask | delete;
         }else {
             mask = mask & ~delete;
         }
     }

    /**
     * 判断用户手机号登录是否可用
     * @return
     */
     public boolean isPhoneAvailable(){
         return (mask & phoneAvailable) == phoneAvailable;
     }

    /**
     * 设置是否允许手机号登录
     */
    public void setPhoneAvailable(boolean yes){
        if(yes){
            mask = mask | phoneAvailable;
        }else {
            mask = mask & ~phoneAvailable;
        }

     }

     /**
     * 判断是否允许用户使用邮箱登录
     * @return
     */
     public boolean isEmailAvailable(){
         return (mask & emailAvailable) == emailAvailable;
     }

    /**
     * 设置是否允许使用邮箱登录
     */
    public void setEmailAvailable(boolean yes){
        if(yes){
            mask = mask | emailAvailable;
        }else {
            mask = mask & ~emailAvailable;
        }

     }


    /**
     * 判断加我为好友时是否需要同意
     * @return
     */
     public boolean isAddFriendUseAgree(){
        return (mask & addFriendUseAgree) == addFriendUseAgree;
     }

    /**
     * 设置加我为好友时是否需要同意
     * @param yes
     */
     public void setAddFriendUseAgree(boolean yes) {
         if (yes) {
             mask = mask | addFriendUseAgree;
         } else {
             mask = mask & ~addFriendUseAgree;
         }

     }

    /**
     * 判断加我进群时是否需要同意
     * @return
     */
     public boolean isAddGroupUseAgree(){
        return (mask & addGroupUseAgree) == addGroupUseAgree;
     }

    /**
     * 设置加我进群时是否需要同意
     * @param yes
     */
     public void setAddGroupUseAgree(boolean yes){
         if (yes) {
             mask = mask | addGroupUseAgree;
         } else {
             mask = mask & ~addGroupUseAgree;
         }
     }
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
     public long getMask(){
           return this.mask;
     }
     public void setMask(long mask){
           this.mask = mask;
     }

@Override
    public String toString() {
        return "UserInfo{" +
            "  id:" + id + "  userName:" + userName + "  phone:" + phone + "  email:" + email + "  password:" + password + "  createTime:" + createTime + "  mask:" + mask +
        "}";
    }
  }
