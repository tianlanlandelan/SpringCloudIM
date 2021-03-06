package com.kyle.im.common.entity;

import com.kyle.im.common.mybatis.BaseEntity;
import com.kyle.im.common.mybatis.annotation.IndexAttribute;
import com.kyle.im.common.mybatis.annotation.KeyAttribute;
import com.kyle.im.common.mybatis.annotation.SortAttribute;
import com.kyle.im.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * @author 杨凯乐
 * @date 2018-08-02 09:33:01
 */
@TableAttribute(name = "router")
public class Router extends BaseEntity {
    /**
     * 接口ID
     */
    @KeyAttribute
    private String id;
    /**
     * 接口名称
     */
    @IndexAttribute
    private String name;
    /**
     * 组件名称
     */
    @IndexAttribute
    private String serviceName;
    /**
     * Controller名称
     */
    @IndexAttribute
    private String controllerName;
    /**
     * 方法名称
     */
    @IndexAttribute
    private String methodName;
    /**
     * 路由
     */
    private String routerUrl;
    /**
     * 请求类型
     */
    @IndexAttribute
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
    @SortAttribute
    private Date createTime = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
