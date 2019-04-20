package com.kyle.ingateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class ApiController {
    @Autowired
    DiscoveryClient discoveryClient;
    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getServices();
    }

    @RequestMapping("/home")
    public String home() {
        return "Hello World";
    }

}
