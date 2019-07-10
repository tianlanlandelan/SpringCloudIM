package com.kyle.outgateway.config;

import com.kyle.mycommon.util.QueuesNames;
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
    public Queue sendVerificationCodeQueue(){
        return new Queue(QueuesNames.SEND_VERIFICATION_CODE);
    }

}
