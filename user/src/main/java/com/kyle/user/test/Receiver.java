package com.kyle.user.test;

import com.kyle.mycommon.util.ConsoleLogUtils;
import com.kyle.mycommon.util.QueuesNames;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yangkaile
 * @date 2019-04-16 11:02:06
 * RabbitMQ 接收工具类
 */
@Component
@RabbitListener(queues = QueuesNames.IM_GATEWAY_IN)
public class Receiver {
    @RabbitHandler
    public void process(String hello){
        ConsoleLogUtils.print("Receiver",hello);
    }

}
