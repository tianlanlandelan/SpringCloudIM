package com.kyle.mycommon.router;

/**
 * 维护组件名
 * 新增的组件在这里注册
 */
public class MyServiceName {
    /**
     * 服务注册中心
     * 维护所有已启动的组件ServiceRegistryCenter
     */
    public final static String SERVICE_REGISTRY_CENTER = "ServiceRegistryCenter";

    /**
     * 用户管理中心
     * 维护用户信息，负责用户登录、注册UserManagerCenter
     */
    public final static String USER_MANAGER_CENTER = "UserManagerCenter";

    /**
     * 日志中心
     * 负责所有组件的日志记录、查询等功能LogCenter
     */
    public final static String LOG_CENTER = "LogCenter";

    /**
     * 外部服务中心
     * 负责提供邮件发送、短信发送等对外服务PublicServiceCenter
     */
    public final static String PUBLIC_SERVICE_CENTER = "PublicServiceCenter";



}
