package com.kyle.im.group.entity;

import com.kyle.im.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.kyle.im.common.mybatis.annotation.FieldAttribute;
import com.kyle.im.common.mybatis.annotation.IndexAttribute;
import com.kyle.im.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * 群信息
 * @author yangkaile
 * @date 2019-09-12 16:30:06
 */
@TableAttribute(name = "group_info",comment = "群信息表，存储群ID、名称等基本信息")
public class GroupInfo {
    @FieldAttribute("群ID")
    @AutoIncrKeyAttribute
    private int id;

    @IndexAttribute
    @FieldAttribute("群名称")
    private String name;

    @IndexAttribute
    @FieldAttribute("创建时间，不可更改，记录该群最初创建的时间")
    private Date createTime = new Date();

    @IndexAttribute
    @FieldAttribute(value = "创建人",notNull = true)
    private int createUserId;

    @FieldAttribute("群公告，管理员可修改")
    private String notice;

    @FieldAttribute("最多允许加入的群成员数量，默认20")
    private int maxSize = 20;

    @FieldAttribute("当前群成员数量")
    private int size;

    @FieldAttribute("群版本号，每次群信息，该版本号加1")
    private int version;

    @FieldAttribute("群成员信息版本号，每次群成员信息有修改，该版本号加1，" +
            "用于用户获取有修改的群成员信息")
    private int memberInfoVersion;

    @FieldAttribute("群成员列表版本号，每次群成员列表有变化（加人、减人），该版本号加1，" +
            "用于用户获取有变化的群成员信息")
    private int memberListVersion;

    @FieldAttribute("群消息版本号，每次有新的群消息，该版本号加1，" +
            "用于用户获取新的群消息")
    private int messageVersion;

    @FieldAttribute("群的设置")
    private int switchs;

    @FieldAttribute("群信息最后修改时间")
    private Date lastUpdateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getMemberInfoVersion() {
        return memberInfoVersion;
    }

    public void setMemberInfoVersion(int memberInfoVersion) {
        this.memberInfoVersion = memberInfoVersion;
    }

    public int getMemberListVersion() {
        return memberListVersion;
    }

    public void setMemberListVersion(int memberListVersion) {
        this.memberListVersion = memberListVersion;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", createUserId=" + createUserId +
                ", notice='" + notice + '\'' +
                ", maxSize=" + maxSize +
                ", size=" + size +
                ", version=" + version +
                ", memberInfoVersion=" + memberInfoVersion +
                ", memberListVersion=" + memberListVersion +
                ", messageVersion=" + messageVersion +
                ", switchs=" + switchs +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
