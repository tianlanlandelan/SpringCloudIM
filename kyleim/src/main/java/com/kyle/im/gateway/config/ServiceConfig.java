package com.kyle.im.gateway.config;


import com.kyle.im.common.config.PublicConfig;

/**
 * 暂时放一些静态变量配置
 * @author yangkaile
 * @date 2018-08-28 15:15:57
 */
public class ServiceConfig {
    /**
     * 注册时的短信验证码发送类型
     */
    public static final int SMS_SEND_TYPE_REGISTER = 0;
    /**
     * 注册时的短信模板ID
     */
    public static final String SMS_SEND_TEMPLATE_ID_REGISTER = "1";
    /**
     * 注册时的短信有效期
     */
    public static final String SMS_SEND_MINUTE_REGISTER = "5";

    /**
     * 验证码长度
     */
    public static final int SMS_VERIFICATIONCODE_LENGTH = 6;



    /**
     * 邮件验证码标题
     */
    public static final String EMAIL_VERIFICATIONCODE_TITLE = "请查收您的验证码";

    /**
     * 邮件验证码正文内容
     */
    public static final String RegisterBody =
            "验证码:%1$s，用于" + PublicConfig.AppName + " %2$s 注册，泄露有风险。"
                    + PublicConfig.CodeValidatyTime + "分钟内使用有效。";

    public static final String LoginBody =
            "验证码:%1$s，用于" + PublicConfig.AppName + " %2$s 登录，泄露有风险。"
                    + PublicConfig.CodeValidatyTime + "分钟内使用有效。";

    public static final String ResetPasswordBody =
            "验证码:%1$s，用于" + PublicConfig.AppName + " %2$s 找回密码，泄露有风险。"
                    + PublicConfig.CodeValidatyTime + "分钟内使用有效。";

    /**
     * 邮件验证码长度
     */
    public static final int EMAIL_VERIFICATIONCODE_LENGTH = 6;

}
