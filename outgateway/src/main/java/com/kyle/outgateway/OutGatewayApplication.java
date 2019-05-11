package com.kyle.outgateway;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
/**
 * @author yangkaile
 * @date 2019-05-11 09:41:11
 */
public class OutGatewayApplication {
    @Bean
    /**
     * 引入RestTemplate Bean
     * 用来进行服务间的Http通信
     * 同时重新定义其解析时用到的字符集，防止中文乱码
     */
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().clear();
        restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
        return restTemplate;

    }
    public static void main(String[] args) {
        SpringApplication.run(OutGatewayApplication.class, args);
    }

}
