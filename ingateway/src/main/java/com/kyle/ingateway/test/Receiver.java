package com.kyle.ingateway.test;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yangkaile
 * @date 2019-04-16 11:02:06
 * RabbitMQ 接收工具类
 */
@Component
@RabbitListener(queues = "Hello")
public class Receiver {
    @RabbitHandler
    public void process(String hello){
        ConsoleLogUtils.print("Receiver",hello);
    }

}
