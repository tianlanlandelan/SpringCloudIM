package com.kyle.user.entity;

import com.kyle.mycommon.mybatis.TableAttribute;

import java.util.Date;

/**
 * 用户简介
 * @author yangkaile
 * @date 2019年07月09日14:03:33
 *
 */
@TableAttribute("user_profile")
public class UserProfile {
    private int id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 国家/地区
     */
    private String region;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 个性签名
     */
    private String signature;

    /**
     * 性别 0:男  1:女
     */
    private int gender;

    /**
     * 生日
     */
    private Date birthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", region='" + region + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", signature='" + signature + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                '}';
    }
}
