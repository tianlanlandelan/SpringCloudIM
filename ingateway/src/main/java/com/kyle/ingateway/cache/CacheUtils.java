package com.kyle.ingateway.cache;


import com.kyle.mycommon.entity.Router;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-11-07 09:23:00
 */
public class CacheUtils {

    public final static String USER_ID = "userId";

    public final static String ROLE_MANAGER = "Manager";
    public final static String ROLE_USER = "User";

    private static Map<String,Router> routerMap = new HashMap<>();
    public static Map<Integer,List<Integer>> roleRouterMap = new HashMap<>();
    public static Map<Integer,String> userRoleMap = new HashMap<>();

    private static String lock = "lock";
    public static Map<String,Router> getRouterMap(){
        synchronized (lock){
            return routerMap;
        }
    }

    public static void initRouterMap(List<Router> list){
        synchronized (lock){
            routerMap.clear();
            for(Router object:list){
                routerMap.put(object.getName(),object);
            }
        }
    }
}
