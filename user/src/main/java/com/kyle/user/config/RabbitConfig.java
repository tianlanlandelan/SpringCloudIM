package com.kyle.user.config;

import com.kyle.common.util.QueuesNames;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangkaile
 * @date 2019-04-16 11:08:09
 *
 */
@Configuration
public class RabbitConfig {


    @Bean
    public Queue helloQueue(){
        return new Queue(QueuesNames.IM_GATEWAY_IN);
    }
}