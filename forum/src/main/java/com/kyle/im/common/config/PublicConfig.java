package com.kyle.im.common.config;

public class PublicConfig {
    public static final int EMAIL_SEND_STATUSCODE_SUCCESS = 0;
    public static final int EMAIL_SEND_STATUSCODE_FAILED = 1;

    public static final String SERVICE_URL = "http://127.0.0.1:8801";

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
