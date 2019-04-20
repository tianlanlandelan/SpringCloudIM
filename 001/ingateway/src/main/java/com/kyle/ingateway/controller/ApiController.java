package com.kyle.ingateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/application")
public class ApiController {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancer;

    @Resource
    RestTemplate restTemplate;
    /**
     * 获取所有服务
     */
    @RequestMapping(value = "/services",method = RequestMethod.GET)
    public Object services() {
        return discoveryClient.getServices();
    }

    @RequestMapping(value = "/relay")
    public ResponseEntity relay(){
        ServiceInstance serviceInstance = loadBalancer.choose("User");
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());

        return restTemplate.getForEntity(
                serviceInstance.getUri().toString() + "/login", String.class);
    }


    @RequestMapping("/home")
    public String home() {
        return "Hello World";
    }

}
