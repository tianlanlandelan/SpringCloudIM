package com.kyle.im.common.config;

import com.kyle.im.common.util.Console;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

/**
 * @author yangkaile
 * @date 2019-09-27 10:25:25
 * 组件名，在Consul注册的名称
 */
public class ServiceName {

    public static final String USER = "IM";
    public static final String LOG = "IM";
    public static final String GATEWAY = "IM";

    private static String getUrl(LoadBalancerClient loadBalancer,String serviceName){
        ServiceInstance serviceInstance =  loadBalancer.choose(serviceName);
        String url = serviceInstance.getUri().toString();
        Console.print("ServiceName.getUrl()",serviceName,serviceInstance.getServiceId(),url);
        return url;
    }
    public static String getUserUrl(LoadBalancerClient loadBalancer){
        return getUrl(loadBalancer,USER);
    }
    public static String getLogUrl(LoadBalancerClient loadBalancer){
        return getUrl(loadBalancer,LOG);
    }
    public static String getGatewayUrl(LoadBalancerClient loadBalancer){
        return getUrl(loadBalancer,GATEWAY);
    }
}
