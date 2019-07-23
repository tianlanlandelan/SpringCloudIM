package com.kyle.mycommon.util;

/**
 * @author yangkaile
 * @date 2019-04-16 20:20:24
 * RabbitMQ固定的队列名
 */
public class QueuesNames {
    /**
     * 发送验证码
     */
    public final static String SEND_VERIFICATION_CODE = "SEND_VERIFICATION_CODE";

    /**
     * 保存邮件验证码
     */
    public final static String SAVE_EMAIL_VERIFICATION_CODE = "SAVE_EMAIL_VERIFICATION_CODE";

    /**
     * 保存短信验证码
     */
    public final static String SAVE_SMS_VERIFICATION_CODE = "SAVE_SMS_VERIFICATION_CODE";

    /**
     * 保存登录日志
     */
    public final static String SAVE_LOGON_LOG = "SAVE_LOGON_LOG";


    /**
     * 用户管理
     * TODO 测试用
     */
    public final static String IM_USER = "IM_USER";

    /**
     * 入访火墙
     * TODO 测试用
     */
    public final static String IM_GATEWAY_IN = "IM_GATEWAY_IN";


}
