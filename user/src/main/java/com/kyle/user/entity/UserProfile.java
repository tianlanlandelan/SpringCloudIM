package com.kyle.user.entity;

import com.kyle.mycommon.mybatis.BaseEntity;
import com.kyle.mycommon.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * 用户简介
 * @author yangkaile
 * @date 2019年07月09日14:03:33
 *
 */
@TableAttribute("user_profile")
public class UserProfile extends BaseEntity {
    private int id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 在名片中展示的手机号，与登录手机号无关
     */
    private String phone;
    /**
     * 在名片中展示的邮箱，与登录邮箱无关
     */
    private String email;
    /**
     * 在名片中展示的个人主页，与该平台主页无关
     */
    private String homepage;
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

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", homepage='" + homepage + '\'' +
                ", region='" + region + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", signature='" + signature + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                '}';
    }
}
