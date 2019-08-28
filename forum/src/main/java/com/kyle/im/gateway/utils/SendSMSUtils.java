package com.kyle.im.gateway.utils;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;


import java.io.IOException;

/**
 * 短信发送工具类
 * @author yangkaile
 * @date 2018-08-28 14:54:46
 */
public class SendSMSUtils {
    // 短信应用 SDK AppID
    public static int appid = 1400235328; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    public static String appkey = "12b5145fd49701c33a435bb1cc92a4f0";
    // 需要发送短信的手机号码
    public static String[] phoneNumbers = {"21212313123", "12345678902", "12345678903"};
    // 短信模板 ID，需要在短信应用中申请
    public static int templateId = 7839; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
    // 签名
    public static String smsSign = "腾讯云"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请


    public static void main(String[] args){
        try {
            String[] params = {"5678"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
    }

}
