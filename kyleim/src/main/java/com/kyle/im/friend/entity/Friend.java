package com.kyle.im.friend.entity;

import com.kyle.im.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.kyle.im.common.mybatis.annotation.FieldAttribute;
import com.kyle.im.common.mybatis.annotation.IndexAttribute;
import com.kyle.im.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * 好友关系
 * @author yangkaile
 * @date 2019-09-12 22:15:07
 */
@TableAttribute(name = "friend",comment = "好友关系")
public class Friend {

    @FieldAttribute("id")
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute(value = "用户ID",notNull = true)
    @IndexAttribute
    private int userId;

    @FieldAttribute(value = "好友ID",notNull = true)
    @IndexAttribute
    private int friendId;

    @FieldAttribute("好友备注名")
    private String nickName;

    @FieldAttribute("好友标签")
    private String mark;

    @FieldAttribute("对好友的设置")
    private int switchs;

    @FieldAttribute("好友关系确立时间")
    private Date crateTime = new Date();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getSwitchs() {
        return switchs;
    }

    public void setSwitchs(int switchs) {
        this.switchs = switchs;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", userId=" + userId +
                ", friendId=" + friendId +
                ", nickName='" + nickName + '\'' +
                ", mark='" + mark + '\'' +
                ", switchs=" + switchs +
                ", crateTime=" + crateTime +
                '}';
    }
}
