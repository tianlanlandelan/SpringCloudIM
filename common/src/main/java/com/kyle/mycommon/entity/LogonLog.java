package com.kyle.mycommon.entity;

import com.kyle.mycommon.mybatis.BaseEntity;
import com.kyle.mycommon.mybatis.annotation.IndexAttribute;
import com.kyle.mycommon.mybatis.annotation.KeyAttribute;
import com.kyle.mycommon.mybatis.annotation.SortAttribute;
import com.kyle.mycommon.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * Created by yangkaile on 2019/7/23.
 */
@TableAttribute("logon_log")
public class LogonLog extends BaseEntity{
    @KeyAttribute
    private int id;
    @IndexAttribute
    private String userName;
    @IndexAttribute
    private String phone;
    @IndexAttribute
    private String email;
    private int type;
    @SortAttribute
    private Date createTime = new Date();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "LogonLog{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                '}';
    }
}
