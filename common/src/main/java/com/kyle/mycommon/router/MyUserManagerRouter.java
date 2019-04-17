package com.kyle.mycommon.router;

/**
 * @author yangkaile
 * @date 2018-09-18 13:46:58
 */
public class MyUserManagerRouter {

    /**
     * 登录
     */
    public final static int LOGON                           = 10001;
    /**
     * 注册
     */
    public final static int REGISTER                        = 10002;
    /**
     * 获取所有角色
     */
    public final static int GET_ALL_ROLES                   = 10003;
    /**
     * 获取用户的角色
     */
    public final static int GET_ROLE_BY_USER_ID             = 10004;
    /**
     * 查询包含某权限的角色
     */
    public final static int GET_ROLES_BY_ROUTER_ID          = 10005;
    /**
     * 查询包含某角色的用户
     */
    public final static int GET_USER_BY_ROLE_ID             = 10006;
    /**
     * 获取所有权限
     */
    public final static int GET_ALL_ROUTERS                 = 10007;
    /**
     * 查询角色的权限
     */
    public final static int GET_ROUTERS_BY_ROLE_ID          = 10008;
    /**
     * 查询用户的权限ID列表
     */
    public final static int GET_ROUTER_IDS_BY_USER_ID       = 10009;
    /**
     * 查询所有的用户名和角色名
     */
    public final static int GET_ALL_USER_NAME_AND_ROLE_NAME = 10010;
    /**
     * 添加角色
     */
    public final static int ADD_ROLE                        = 10011;
    /**
     * 为用户添加角色
     */
    public final static int ADD_ROLE_FOR_USER               = 10012;
    /**
     * 为角色添加权限
     */
    public final static int ADD_ROUTER_FOR_ROLE             = 10013;
    /**
     * 删除角色
     */
    public final static int DELETE_ROLE_BY_ID               = 10014;
    /**
     * 修改角色
     */
    public final static int UPDATE_ROLE                     = 10015;
    /**
     * 查询用户详情
     */
    public final static int GET_USER_INFO_BY_ID             = 10016;
    /**
     * 修改用户详情
     */
    public final static int UPDATE_USER_INFO                = 10017;
    /**
     * 获取所有用户详情
     */
    public final static int GET_ALL_USER_INFO               = 10018;

    /**
     * 检查用户名、手机号、密码是否已注册
     */
    public final static int CHECK_USER_REGISTERED = 10019;

    /**
     * 分页查询权限
     */
    public final static int GET_ROUTERS_BY_PAGE = 10020;

}
