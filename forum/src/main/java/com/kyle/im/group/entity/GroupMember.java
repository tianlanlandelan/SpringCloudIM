package com.kyle.im.group.entity;

import com.kyle.im.common.mybatis.annotation.FieldAttribute;
import com.kyle.im.common.mybatis.annotation.IndexAttribute;
import com.kyle.im.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * 群成员信息
 * @author yangkaile
 * @date 2019-09-12 17:01:44
 */
@TableAttribute(name = "group_member",comment = "群成员表")
public class GroupMember {
    @FieldAttribute("id")
    private int id;

    @IndexAttribute
    @FieldAttribute(value = "群ID",notNull = true)
    private int groupId;

    @IndexAttribute
    @FieldAttribute(value = "用户ID",notNull = true)
    private int userId;

    @FieldAttribute("群昵称")
    private String nickname;

    @FieldAttribute("群成员信息版本号，每次修改群昵称，版本号加1")
    private int version;

    @FieldAttribute("群消息版本号，每次收到群消息，同步为群消息的版本号")
    private int messageVersion;

    @FieldAttribute("群个人设置")
    private int switchs;

    @FieldAttribute("入群时间")
    private Date createTime = new Date();

    @FieldAttribute("最后修改时间")
    private Date lastUpdateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getMessageVersion() {
        return messageVersion;
    }

    public void setMessageVersion(int messageVersion) {
        this.messageVersion = messageVersion;
    }

    public int getSwitchs() {
        return switchs;
    }

    public void setSwitchs(int switchs) {
        this.switchs = switchs;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", version=" + version +
                ", messageVersion=" + messageVersion +
                ", switchs=" + switchs +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
