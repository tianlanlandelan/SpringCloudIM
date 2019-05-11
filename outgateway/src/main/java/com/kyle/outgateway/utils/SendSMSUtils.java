package com.kyle.outgateway.utils;
//import com.cloopen.rest.sdk.CCPRestSmsSDK;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 短信发送工具类
 * @author yangkaile
 * @date 2018-08-28 14:54:46
 */
public class SendSMSUtils {


    private static Logger logger = LoggerFactory.getLogger(SendSMSUtils.class);

//    private static final String SERVER_IP     = "app.cloopen.com";
//    private static final String SERVER_PORT   = "8883";
//    private static final String ACCOUNT_SID   = "8aaf07085b5fee9a015b85e01170103c";
//    private static final String ACCOUNT_TOKEN = "07b5328498f943769fd1f631ef737fba";
//    public static final  String App_ID        = "8aaf07085b5fee9a015b85e011ea1042";
    private static final String SERVER_IP     = "sandboxapp.cloopen.com";
    private static final String SERVER_PORT   = "8883";
    private static final String ACCOUNT_SID   = "8a48b5514e5298b9014e6b8debd0176f";
    private static final String ACCOUNT_TOKEN = "06a24f7f78d9450aae9c9d8d904f7b6a";
    public static final  String App_ID        = "8a48b5514e5298b9014e6b8f5585177a";

    public static final String RESULT_STATUS_CODE = "statusCode";
    public static final String RESULT_SUCCESS_CODE = "000000";

    /**
     * 初始化短信发送SDK
     * @return
     */
//    public static CCPRestSmsSDK init(){
//        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
//        restAPI.init(SERVER_IP, SERVER_PORT);
//        restAPI.setAccount(ACCOUNT_SID, ACCOUNT_TOKEN);
//        restAPI.setAppId(App_ID);
//        return restAPI;
//    }

    /**
     * 通用的短信发送逻辑
     * @param entity
     * @return
     */
//    public static SMSEntity sendSMS(SMSEntity entity){
//        HashMap<String, Object> result;
//        result = init().sendTemplateSMS(entity.getPhone(),entity.getTemplateId(),
//                new String[]{entity.getCodeStr(),entity.getMinuteStr()});
//        logger.info(result.toString());
//        entity.setResult(result.toString());
//        entity.setStatusCode(result.get(RESULT_STATUS_CODE).toString());
//        return entity;
//    }

    /**
     * 发送短信验证码
     * @param phone 手机号
     * @return
     */
//    public static SMSEntity sendVerificationCode(String phone){
//        SMSEntity entity = new SMSEntity(phone,ServiceConfig.SMS_SEND_TYPE_REGISTER);
//        entity.setTemplateId(ServiceConfig.SMS_SEND_TEMPLATE_ID_REGISTER);
//        entity.setCodeStr(StringUtils.getNumbserString(ServiceConfig.SMS_SEND_LENGTH));
//        entity.setMinuteStr(ServiceConfig.SMS_SEND_MINUTE_REGISTER);
//        return sendSMS(entity);
//    }

    /**
     * @param args
     */
    public static void main(String[] args) {
//            SMSEntity entity = new SMSEntity("17600109114",ServiceConfig.SMS_SEND_TYPE_REGISTER);
//            entity.setTemplateId("1");
//            entity.setCodeStr(StringUtils.getNumbserString(ServiceConfig.SMS_SEND_LENGTH));
//            entity.setMinuteStr("10");
//            sendSMS(entity);
//        HashMap<String, Object> result = null;

        //初始化SDK
//        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

        //******************************注释*********************************************
        //*初始化服务器地址和端口                                                       *
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
        //*******************************************************************************
//        restAPI.init(SERVER_IP, SERVER_PORT);

        //******************************注释*********************************************
        //*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
        //*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        //*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
        //*******************************************************************************
//        restAPI.setAccount(ACCOUNT_SID, ACCOUNT_TOKEN);


        //******************************注释*********************************************
        //*初始化应用ID                                                                 *
        //*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
        //*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        //*******************************************************************************
//        restAPI.setAppId(App_ID);


        //******************************注释****************************************************************
        //*调用发送模板短信的接口发送短信                                                                  *
        //*参数顺序说明：                                                                                  *
        //*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
        //*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
        //*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
        //*第三个参数是要替换的内容数组。																														       *
        //**************************************************************************************************

        //**************************************举例说明***********************************************************************
        //*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
        //*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
        //*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
        //*********************************************************************************************************************
//        result = restAPI.sendTemplateSMS("17600109114","1" ,new String[]{"123456","5"});
//
//        System.out.println("SDKTestGetSubAccounts result=" + result);
//        if("000000".equals(result.get("statusCode"))){
//            //正常返回输出data包体信息（map）
//            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//            Set<String> keySet = data.keySet();
//            for(String key:keySet){
//                Object object = data.get(key);
//                System.out.println(key +" = "+object);
//            }
//        }else{
//            //异常返回输出错误码和错误信息
//            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
//        }
    }

}
