package com.kyle.ingateway.test;

import com.alibaba.fastjson.JSON;
import com.kyle.common.util.ConsoleLogUtils;
import com.kyle.common.util.QueuesNames;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
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
        ConsoleLogUtils.print("Receiver",content);
    }
    @RabbitListener(queues = QueuesNames.IM_GATEWAY_IN)
    public void inGatewayHandler(String content){
        ConsoleLogUtils.print("inGatewayHandler Receiver",content);
        ConsoleLogUtils.print("Object",JSON.parseObject(content,UserInfo.class));
    }



}
