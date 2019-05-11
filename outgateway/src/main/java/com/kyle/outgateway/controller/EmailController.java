package com.kyle.outgateway.controller;

import com.kyle.mycommon.config.PublicConfig;
import com.kyle.mycommon.entity.EmailEntity;
import com.kyle.mycommon.response.MyResponse;
import com.kyle.mycommon.response.ResultData;
import com.kyle.mycommon.router.MyLogRouter;
import com.kyle.mycommon.router.MyPublicServiceRouter;
import com.kyle.mycommon.router.MyRouters;
import com.kyle.mycommon.router.RouterAttribute;
import com.kyle.mycommon.util.StringUtils;
import com.kyle.mycommon.util.ValidUserName;
import com.kyle.outgateway.utils.SendEmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-11-05 15:20:03
 */
@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(EmailController.class);


    /**
     * 发送文本邮件
     * @param emailAddress  邮箱地址
     * @param title         邮件标题
     * @param content       邮件内容
     * @return
     */
    @RouterAttribute(id = MyPublicServiceRouter.SENT_TEXT_EMAIL,
            name="",description = "发送文本邮件")
    @RequestMapping(value = "/sendText",method = RequestMethod.GET)
    public ResponseEntity sendText(String emailAddress, String title, String content){
        try{
            if(emailAddress == null || title == null || content == null){
                return MyResponse.badRequest();
            }else{
                SendEmailUtils.sendSimpleMail(emailAddress,title,content);
            }
        }catch (Exception e){
            e.printStackTrace();
            return MyResponse.serverError();
        }
        return MyResponse.ok(ResultData.success());
    }

    /**
     * 发送邮件验证码
     * @param email
     * @return
     */
    @RouterAttribute(id = MyPublicServiceRouter.SEND_VERIFICATION_CODE_EMAIL,
            name="", description = "发送验证码邮件")
    @RequestMapping(value = "/sendVerificationCode" ,method = RequestMethod.GET)
    public ResponseEntity sendVerificationCode(String email){
        if(StringUtils.isEmpty(email) || !ValidUserName.isValidEmailAddress(email)){
            return MyResponse.badRequest();
        }
        EmailEntity entity = SendEmailUtils.sendVerificationCode(email);
        Map<String,Object> map = new HashMap<>(16);
        map.put("email",entity.getEmail());
        map.put("type",entity.getType());
        map.put("title",entity.getTitle());
        map.put("content",entity.getContent());
        map.put("code",entity.getCode());
        map.put("result",entity.getResult());
        map.put("statusCode",entity.getStatusCode());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(MyRouters.getRouterUrl(MyLogRouter.INSERT_EMAIL_SEND_LOG) +
                "?email={email}&type={type}&title={title}&content={content}" +
                "&code={code}&result={result}&statusCode={statusCode}",null,String.class,map);
        logger.info("smsLog Ok  Response:" + responseEntity.getBody() + ",entity:" + entity);

        if(entity.getStatusCode() == PublicConfig.EMAIL_SEND_STATUSCODE_SUCCESS){
            return MyResponse.ok(ResultData.success());
        }else {
            return MyResponse.ok(ResultData.error("验证码发送失败"));
        }
    }
}
