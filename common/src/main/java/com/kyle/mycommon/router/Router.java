package com.kyle.mycommon.router;

import com.kyle.mycommon.mybatis.TableAttribute;

import java.util.Date;

/**
 * @author 杨凯乐
 * @date 2018-08-02 09:33:01
 */
@TableAttribute("router")
public class Router {
    /**
     * 接口ID
     */
    private Integer id;
    /**
     * 接口名称
     */
    private String name;
    /**
     * 组件名称
     */
    private String serviceName;
    /**
     * Controller名称
     */
    private String controllerName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 路由
     */
    private String routerUrl;
    /**
     * 请求类型
     */
    private String requestType;
    /**
     * 请求参数
     */
    private String parameters;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createTime = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRouterUrl() {
        return routerUrl;
    }

    public void setRouterUrl(String routerUrl) {
        this.routerUrl = routerUrl;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Router{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", controllerName='" + controllerName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", routerUrl='" + routerUrl + '\'' +
                ", requestType='" + requestType + '\'' +
                ", parameters='" + parameters + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
