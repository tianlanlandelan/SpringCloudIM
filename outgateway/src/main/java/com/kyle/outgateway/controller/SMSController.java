package com.kyle.outgateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangkaile
 * @date 2018-09-29 14:07:35
 */
@RestController
@RequestMapping("/SMS")
public class SMSController {

    @Autowired
    RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(SMSController.class);

    /**
     * 发送短信验证码
     * 发送完毕后，将验证码及发送的结果通过LogCenter记录到数据库
     * 并根据返回的结果判断短信是否发送成功
     * @param phone
     * @return
     */
//    @RouterAttribute(id = MyPublicServiceRouter.SEND_VERIFICATION_CODE_SMS, description = "发送验证码短信")
//    @RequestMapping(value = "/sendVerificationCode",method = RequestMethod.GET)
//    public ResponseEntity sendVerificationCode(String phone){
//        if(phone == null || phone.isEmpty()){
//            return MyResponse.badRequest();
//        }
//        SMSEntity entity = SendSMSUtils.sendVerificationCode(phone);
//        Map<String,Object> map = new HashMap<>(16);
//        map.put("phone",entity.getPhone());
//        map.put("type",entity.getType());
//        map.put("codeStr",entity.getCodeStr());
//        map.put("result",entity.getResult());
//        map.put("statusCode",entity.getStatusCode());
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(MyRouters.getRouterUrl(MyLogRouter.INSERT_SMS_SEND_LOG) +
//                "?phone={phone}&type={type}&message={message}&codeStr={codeStr}" +
//                "&result={result}&statusCode={statusCode}",null,String.class,map);
//        logger.info("SMSEntity Ok  Response:" + responseEntity.getBody() + ",entity:" + entity);
//
//        if(SendSMSUtils.RESULT_SUCCESS_CODE.equals(entity.getStatusCode())){
//            return MyResponse.ok(ResultData.success());
//        }else {
//            return MyResponse.ok(ResultData.error("验证码发送失败"));
//        }
//
//    }
}
