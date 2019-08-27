package com.kyle.im.common.router;


import com.kyle.im.common.entity.Router;
import com.kyle.im.common.response.MyResponseReader;
import com.kyle.im.common.util.Constants;
import com.kyle.im.common.util.StringUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-10-09 09:37:33
 */
public class MyRouters {
    public static Map<String,Router> routerMap = new HashMap();
    public static void initRouterMap(List<Router> list){
        routerMap.clear();
        for (Router router:list){
            routerMap.put(router.getId(),router);
        }
    }

    /**
     * 获取一个方法的参数类型和参数列表 格式: 参数类型:参数名,参数类型:参数名......
     * @param method Method对象
     * @return
     */
    public static  String getMethodParametersStr(Method method){
        StringBuilder builder = new StringBuilder();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer
                = new LocalVariableTableParameterNameDiscoverer();
        //取参数名列表
        String[] params = parameterNameDiscoverer.getParameterNames(method);
        //取参数类型列表
        Class[] paramTypes = method.getParameterTypes();

        for (int i = 0; i < params.length; i++) {
            builder.append(paramTypes[i].getSimpleName())
                    .append(":")
                    .append(params[i]);
            if(i < params.length - 1){
                builder.append(",");
            }
        }
        return builder.toString();
    }


    /**
     * 初始化路由
     * 解析传入的Controller对象，解析类名前的@RequestMapping注解
     * 遍历该类的方法：
     * 1.解析有@RouterAttribute注解的方法，
     * 2.将@RouterAttribute和@RequestMapping注解信息取出
     * 3.将方法名、参数类型、参数名取出
     * 4.将解析出的信息组装成一个Router对象
     *
     * @param serviceName 组件名
     * @param controllers Controller类
     * @return
     */
    public static  List<Router> initRouters(String serviceName, Class... controllers){
        cleanByServiceName(serviceName);
        Class[] controllerArray = controllers;
        List<Router> list = new ArrayList<>();
        for(Class t:controllerArray){
            //获取Class上的RequestMapping信息（路由前缀）

            String urlPrefix = getRequestUrlPrefix(t);
            //获取Class的方法列表
            Method[] methods = t.getDeclaredMethods();
            for(Method method:methods){
                //获取@RouterAttribute注解信息
                RouterAttribute routerAttribute = method.getAnnotation(RouterAttribute.class);
                if(routerAttribute != null){
                    Router routerObject = new Router();

                    routerObject.setName(routerAttribute.name());
                    routerObject.setDescription(routerAttribute.description());
                    routerObject.setServiceName(serviceName);
                    routerObject.setControllerName(t.getSimpleName());
                    routerObject.setMethodName(method.getName());
                    String[] request = readRequestMapping(method);
                    String requestUrl = request[0];
                    routerObject.setId(requestUrl);
                    String requestMethod = request[1];
                    if(StringUtils.isEmpty(urlPrefix)){
                        routerObject.setRouterUrl(Constants.HTTP_UTL_PREFIX + serviceName + requestUrl);
                    }else {
                        routerObject.setRouterUrl(Constants.HTTP_UTL_PREFIX + serviceName + urlPrefix + requestUrl);
                    }
                    routerObject.setRequestType(requestMethod);
                    routerObject.setParameters(getMethodParametersStr(method));

                    list.add(routerObject);
                    //TODO 注册路由
                    registerRouters(routerObject);
                }
            }
        }
        return list;
    }

    private static void cleanByServiceName(String serviceName){
//        RestTemplate restTemplate = getRestTemplate();
//        restTemplate.getForEntity(
//                Constants.CLEAN_ROUTERS_BY_SERVICENAME + "?serviceName=" + serviceName,String.class);
    }
    private static void registerRouters(Router object){
//        RestTemplate restTemplate = getRestTemplate();
//        restTemplate.postForObject(Constants.ROUTER_REGISTER_URL,object,String.class);
    }


    public static List<Router> getRouters(){
        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("",String.class);
        List<Router> list = MyResponseReader.getList(responseEntity,Router.class);
        MyRouters.initRouterMap(list);
        return list;
    }

    public static  String getRouterUrl(Integer id){
        if(routerMap == null || routerMap.isEmpty()){
            getRouters();
        }
       return routerMap.get(id).getRouterUrl();
    }

    public static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    private static String getRequestUrlPrefix(Class t){
        RequestMapping requestMapping =  (RequestMapping)t.getAnnotation(RequestMapping.class);
        if(requestMapping == null){
            return null;
        }
        return requestMapping.value()[0];
    }

    private static String[] readRequestMapping(Method method){
        String[] result = new String[2];
        GetMapping getMapping =  method.getAnnotation(GetMapping.class);
        if(getMapping != null){
            result[0] = "GET";
            result[1] = getMapping.value()[0];
            return result;
        }

        PostMapping postMapping =  method.getAnnotation(PostMapping.class);
        if(getMapping != null){
            result[0] = "POST";
            result[1] = postMapping.value()[0];
            return result;
        }

        PutMapping putMapping =  method.getAnnotation(PutMapping.class);
        if(getMapping != null){
            result[0] = "PUT";
            result[1] = putMapping.value()[0];
            return result;
        }

        DeleteMapping deleteMapping =  method.getAnnotation(DeleteMapping.class);
        if(getMapping != null){
            result[0] = "DELETE";
            result[1] = deleteMapping.value()[0];
            return result;
        }
        RequestMapping requestMapping =  method.getAnnotation(RequestMapping.class);
        if(getMapping != null){
            result[0] = requestMapping.method()[0].name();
            result[1] = requestMapping.value()[0];
            return result;
        }
        return result;
    }

    public static void  main(String[] args){
    }

}

