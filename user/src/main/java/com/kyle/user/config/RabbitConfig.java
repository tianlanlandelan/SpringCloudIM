package com.kyle.user.config;

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
    public Queue saveEmailVerificationCode(){
        return new Queue(QueuesNames.SAVE_EMAIL_VERIFICATION_CODE);
    }
    @Bean
    public Queue saveSMSVerificationCode(){
        return new Queue(QueuesNames.SAVE_SMS_VERIFICATION_CODE);
    }

}
