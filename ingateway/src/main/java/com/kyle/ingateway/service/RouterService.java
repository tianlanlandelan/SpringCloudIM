package com.kyle.ingateway.service;


import com.kyle.ingateway.mapper.RouterMapper;
import com.kyle.mycommon.entity.Router;
import com.kyle.mycommon.response.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2018-10-08 15:45:46
 */
@Service
public class RouterService {
    @Resource
    RouterMapper routerMapper;

    public ResultData insert(Router object){
       return ResultData.success(routerMapper.insert(object));
    }

    public ResultData getAll(){
        return ResultData.success(routerMapper.getAll());
    }

    public ResultData deleteByServiceName(String serviceName){
        return ResultData.success(routerMapper.deleteByServiceName(serviceName));
    }

}
