package com.kyle.im;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.kyle.im.common.response.ResponseReader;
import com.kyle.im.common.util.*;
import com.kyle.im.user.entity.UserInfo;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpsTest {

    public RestTemplate httpsRestTemplate() throws Exception{
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                 .loadTrustMaterial(null, acceptingTrustStrategy)
                 .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                 .setSSLSocketFactory(csf)
                 .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                 new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().clear();
        restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
        restTemplate.setErrorHandler(
                new ResponseErrorHandler() {
                    @Override
                    public boolean hasError(ClientHttpResponse clientHttpResponse) {
                        return false;
                    }

                    @Override
                    public void handleError(ClientHttpResponse clientHttpResponse) {
                        // 默认处理非200的返回，会抛异常
                    }
                });
        return restTemplate;
    }



    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().clear();
        restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
        return restTemplate;
    }


    @Test
    public void get(){
       RestTemplate restTemplate = restTemplate();
        Map<String,Object> map = new HashMap<>(16);
        map.put("id",1);
       ResponseEntity response = restTemplate.getForEntity("http://127.0.0.1:8801/A1010?id={id}",String.class,map);
        Console.print("",response.getStatusCode(),response.getBody());
       UserInfo userInfo = ResponseReader.getObject(response,UserInfo.class);
       Console.print("",userInfo);
    }
    @Test
    public void httpsGet() throws Exception{
        RestTemplate restTemplate = httpsRestTemplate();
        ResponseEntity response = restTemplate.getForEntity("https://www.cnblogs.com/softidea/p/10663849.html",String.class);
        Console.print("",response.getStatusCode(),response.getBody());
    }

    @Test
    public void d() throws Exception{
        RestTemplate restTemplate = httpsRestTemplate();

        String accountSid = "8a48b5514e5298b9014e6b8debd0176f";
        String token = "06a24f7f78d9450aae9c9d8d904f7b6a";
        String appId = "8a48b5514e5298b9014e6b8f5585177a";

        String dateString = DateUtils.getTimeMaskSecond();
        String sig = MD5Utils.getMd5String(accountSid + token + dateString);
        String authorization = Base64Utils.encode(accountSid + ":" + dateString);

        String url = "https://app.cloopen.com:8883/2013-12-26/Accounts/"+accountSid +
                "/SMS/TemplateSMS?sig="+sig;

        Map<String,String> body = new HashMap<>(16);
        body.put("to","17600109114");
        body.put("appId",appId);
        body.put("templateId","1");
        List<String> list = new ArrayList<>();
        list.add("123456");
        list.add("5");
        body.put("datas",JsonUtils.toJSONString(list));


        String postBody = JsonUtils.toJSONString(body);
        Console.print("",sig,authorization,postBody);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json;charset=utf-8");
        headers.add("Authorization", authorization);


        HttpEntity<String> entity = new HttpEntity<>(postBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        Console.print("",response);
    }
}
