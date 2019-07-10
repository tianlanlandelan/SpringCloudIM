package com.kyle.ingateway.test;

import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.JsonUtils;
import com.kyle.mycommon.util.QueuesNames;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yangkaile
 * @date 2019-04-16 11:02:06
 * RabbitMQ 接收工具类
 */
@Component
public class Receiver {


    @RabbitListener(queues = QueuesNames.IM_USER)
    public void userHandler(String content){
        Console.print("Receiver",content);
    }
    @RabbitListener(queues = QueuesNames.IM_GATEWAY_IN)
    public void inGatewayHandler(String content){
        Console.print("inGatewayHandler Receiver",content);
        Console.print("Object",JsonUtils.parseObject(content,UserInfo.class));
    }
    @RabbitListener(queues = QueuesNames.SEND_VERIFICATION_CODE)
    public void sendVerificationCode(String content){
        Console.print("Receiver",content);
    }



}
