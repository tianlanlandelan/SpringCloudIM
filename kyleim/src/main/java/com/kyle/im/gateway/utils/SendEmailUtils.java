package com.kyle.im.gateway.utils;


import com.kyle.im.common.config.PublicConfig;
import com.kyle.im.common.util.StringUtils;
import com.kyle.im.gateway.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author yangkaile
 * @date 2018-09-11 14:34:03
 */
public class SendEmailUtils {
    private static Logger logger = LoggerFactory.getLogger(SendEmailUtils.class);

    /**
     * 邮件服务器地址
     */
    private static final String HOST = "smtp.qq.com";
    /**
     * 发件人名称
     */
    private static final String USER = "guyexing@foxmail.com";
    /**
     * 发件人密码
     */
    private static final String PASSWORD = "pmjpoliwxjyadifd";

    /**
     * 初始化Session
     * @return
     */
    private static  Session initSession(){
        Session session = null;
        try{
            Properties prop = new Properties();
            prop.setProperty("mail.host", "smtp.qq.com");
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            //创建session
            session = Session.getInstance(prop);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return session;
    }

    /**
     * 发送简单邮件
     * @param toEmailAddress 收件人地址
     * @param title 邮件标题
     * @param content 邮件内容
     * @throws Exception
     */
    public static void sendSimpleMail(String toEmailAddress,String title,String content)    throws Exception {
        Session session = initSession();
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(USER));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmailAddress));
        //邮件的标题
        message.setSubject(title);
        //邮件的文本内容
        message.setContent(content, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        Transport transport = session.getTransport();
        //使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器,用户名和密码都通过验证之后才能够正常发送邮件给收件人
        transport.connect(HOST,USER,PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    /**
     * 发送邮件验证码
     * 1.随机生成指定位数的验证码
     * 2.创建邮件实体类，设置收件人地址、邮件标题、邮件内容、验证码
     * 3.发送邮件并记录发送结果
     * 4.返回包含发送结果的邮件实体类
     * @param email 收件人邮箱
     * @return 邮件实体类EmailEntity
     */
//    public static EmailEntity sendVerificationCode(String email){
//        String code = StringUtils.getAllCharString(ServiceConfig.EMAIL_VERIFICATIONCODE_LENGTH);
//        EmailEntity entity = new EmailEntity();
//        entity.setEmail(email);
//        entity.setType(ServiceConfig.EMAIL_SEND_TYPE_REGISTER);
//        entity.setTitle(ServiceConfig.EMAIL_VERIFICATIONCODE_TITLE);
//        entity.setContent(String.format(ServiceConfig.EMAIL_VERIFICATIONCODE_BODY,code));
//        entity.setCode(code);
//        try {
//            sendSimpleMail(entity.getEmail(),entity.getTitle(),entity.getContent());
//        }catch (Exception e){
//            e.printStackTrace();
//            logger.error("send sendVerificationCode error :" + e.getMessage());
//            entity.setResult(e.getMessage());
//            entity.setStatusCode(PublicConfig.EMAIL_SEND_STATUSCODE_FAILED);
//        }finally {
//            return entity;
//        }
//    }
}
