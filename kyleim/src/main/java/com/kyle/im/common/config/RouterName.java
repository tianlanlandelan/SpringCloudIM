package com.kyle.im.common.config;

/**
 * @author yangkaile
 * @date 2019年07月09日13:53:00
 */
public class RouterName {
    /**
     * 验证码登录、自动注册
     */
    public static final String USER_LOGON_BY_CODE = "/A1001";
    /**
     * 账号密码登录
     */
    public static final String USER_LOGON = "/A1002";
    /**
     * 获取用户个人设置
     */
    public static final String USER_GET_SWITCH = "/A1003";

    public static final String USER_SET_USER_PROFILE = "/A1007";
    public static final String USER_GET_USER_PROFILE = "/A1008";
    /**
     * 添加userInfo,仅超级管理员有权限
     */
    public static final String USER_INSERT_USERINFO = "/A1009";
    public static final String USER_GETBYID = "/A1010";
    public static final String USER_GET_BY_NAME = "/A1011";

    public static final String LOG_CHECK_EMAIL_VALIDATE_CODE = "/B1001";
    public static final String LOG_CHECK_SMS_VALIDATE_CODE = "/B1002";
    public static final String LOG_SAVE_SMS = "/B1003";
    public static final String LOG_SAVE_EMAIL = "/B1004";

    public static final String FRIEND_ADD = "/C1001";
    public static final String FRIEND_DELETE = "/C1002";
    public static final String FRIEND_SET_NICKNAME = "/C1003";
    public static final String FRIEND_GETLIST = "/C1004";
    public static final String FRIEND_GET = "/C1005";
    public static final String FRIEND_ACTION_ADD = "/C1006";


    /**
     * 注册登录的验证码
     */
    public static final String GATEWAY_SEND_LOGON_SMS = "/D1001";
    public static final String GATEWAY_SEND_RESET_PASSWORD_SMS = "/D1002";
    public static final String GATEWAY_SEND_LOGON_EMAIL = "/D1003";
    public static final String GATEWAY_SEND_RESET_PASSWORD_EMAIL = "/D1004";


}
