package com.kyle.common.util;

/**
 * 配置类，存储系统用到的一些常量和配置参数
 * @author yangkaile
 * @date 2018-11-28 14:09:07
 */
public class ConfigUtils {
    public final static String SERVICE_REGISTY_CENTER_URL = "http://127.0.0.1:8801/";

    public final static String SUCCESS_KEY = "success";
    public final static String DATA_KEY = "data";
    public final static String MESSAGE_KEY = "message";


    /**
     * HTTP地址前缀
     */
    public final static String HTTP_UTL_PREFIX = "http://";

    /**
     * get请求
     */
    public final static String REQUEST_METHOD_GET = "GET";
    /**
     * post请求
     */
    public final static String REQUEST_METHOD_POST = "POST";
    /**
     * delete 请求
     */
    public final static String REQUEST_METHOD_DELETE = "DELETE";
    /**
     * put请求
     */
    public final static String REQUEST_METHOD_PUT = "PUT";



    /**
     * session保存用户ID的key值
     */
    public final static String SESSION_USER_ID_KEY = "id";


    /**
     * Response里表示成功的返回码
     */
    public final static int RESPONSE_RESULT_CODE_SUCCESS_VALUE = 0;

    /**
     * 注册路由的接口地址
     */
    public final static String ROUTER_REGISTER_URL = SERVICE_REGISTY_CENTER_URL + "routerRegister";

    /**
     * 清理路由的接口地址
     */
    public final static String CLEAN_ROUTERS_BY_SERVICENAME =  SERVICE_REGISTY_CENTER_URL + "cleanByServiceName";

    /**
     * 获取路由的接口地址
     */
    public final static String GET_ROUTERS_URL = SERVICE_REGISTY_CENTER_URL +  "getRouters";

    /**
     * 邮件发送成功状态码
     */
    public static final int EMAIL_SEND_STATUSCODE_SUCCESS = 0;

    /**
     * 邮件发送失败状态码
     */
    public static final int EMAIL_SEND_STATUSCODE_FAILED = 1;

    /**
     * 分页查询每页最大数据量
     */
    public static final int MAX_PAGE_SIZE = 100;

}
