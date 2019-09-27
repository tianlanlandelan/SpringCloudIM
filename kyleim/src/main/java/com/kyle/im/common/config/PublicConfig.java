package com.kyle.im.common.config;

/**
 * @author yangkaile
 * @date 2019-09-16 14:41:23
 */
public class PublicConfig {

    public static final String AppName = "Chat";

    public static final int SUCCESS  = 0;
    public static final int FAILED = 1;

    public static final int RegisterType = 0;
    public static final int LoginType = 1;
    public static final int ResetPasswordType = 2;


    public static final String SERVICE_URL = "http://127.0.0.1:8801";

    /**
     * 验证码有效期，5分钟
     */
    public static final int CodeValidatyTime = 5;

    /**
     * 通用，不做访问权限设置
     */
    public static final int AUTHORITY_COMMON = 1 << 0;

    /**
     * 用户登录后可以访问
     */
    public static final int AUTHORITY_LOGON  = 1 << 1;

    /**
     * 管理员可以访问
     */
    public static final int AUTHORITY_ADMIN   = 1 << 2;

    /**
     * 超级管理员可以访问
     */
    public static final int AUTHORITY_SUPPER_ADMIN = 1 << 3;


}
