package com.kyle.im.log.config;

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
    public Queue saveEmailVerificationCodeQueue(){
        return new Queue(QueuesNames.SAVE_EMAIL_VERIFICATION_CODE);
    }
    @Bean
    public Queue saveSMSVerificationCodeQueue(){
        return new Queue(QueuesNames.SAVE_SMS_VERIFICATION_CODE);
    }
    @Bean
    public Queue saveLogonLogQueue(){
        return new Queue(QueuesNames.SAVE_LOGON_LOG);
    }


}
