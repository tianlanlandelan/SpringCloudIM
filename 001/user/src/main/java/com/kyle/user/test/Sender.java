package com.kyle.user.test;

import com.kyle.mycommon.util.ConsoleLogUtils;
import com.kyle.mycommon.util.QueuesNames;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-04-16 10:53:57
 * RabbitMQ 发送测试类
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String content = "Hello " + new Date();
        ConsoleLogUtils.print("Sender",content);
        rabbitTemplate.convertAndSend(QueuesNames.IM_GATEWAY_IN,content);
    }

}
