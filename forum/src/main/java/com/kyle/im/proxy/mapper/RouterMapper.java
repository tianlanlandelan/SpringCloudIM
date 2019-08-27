package com.kyle.im.proxy.mapper;


import com.kyle.mycommon.entity.Router;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RouterMapper {
     String tableName = "router";

     @Select("SELECT id,name, serviceName, controllerName, methodName, routerUrl,requestType,parameters,description FROM " + tableName + " WHERE id = #{id}")
     Router getById(Integer Id);

     @Select("SELECT id,name, serviceName, controllerName, methodName, routerUrl,requestType,parameters,description FROM " + tableName)
     List<Router> getAll();

     @Insert("INSERT INTO " + tableName + "(id,name, serviceName, controllerName, methodName, routerUrl,requestType,parameters,description,createTime) " +
             "VALUES (#{id},#{name}, #{serviceName}, #{controllerName}, #{methodName}, #{routerUrl},#{requestType},#{parameters},#{description},#{createTime})")
     Integer insert(Router router);

     @Delete("DELETE FROM " + tableName  + " WHERE serviceName = #{serviceName}")
     Integer deleteByServiceName(String serviceName);

}
