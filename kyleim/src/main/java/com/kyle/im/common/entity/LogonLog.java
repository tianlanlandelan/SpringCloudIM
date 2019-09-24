package com.kyle.im.common.entity;

import com.kyle.im.common.mybatis.BaseEntity;
import com.kyle.im.common.mybatis.annotation.IndexAttribute;
import com.kyle.im.common.mybatis.annotation.KeyAttribute;
import com.kyle.im.common.mybatis.annotation.SortAttribute;
import com.kyle.im.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * Created by yangkaile on 2019/7/23.
 */
@TableAttribute(name = "logon_log")
public class LogonLog extends BaseEntity {
    @KeyAttribute
    private int id;

    @IndexAttribute
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LogonLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                '}';
    }
}
