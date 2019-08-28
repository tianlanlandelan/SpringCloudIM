package com.kyle.im.proxy.service;



import com.kyle.im.common.entity.Router;
import com.kyle.im.common.response.ResultData;
import com.kyle.im.proxy.mapper.RouterMapper;
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
       return ResultData.success(routerMapper.baseInsert(object));
    }

    public ResultData getAll(){
        return ResultData.success(routerMapper.baseSelectAll(new Router()));
    }

    public ResultData deleteByServiceName(String serviceName){
        Router router = new Router();
        router.setServiceName(serviceName);
        return ResultData.success(routerMapper.baseDeleteByCondition(router));
    }

}
