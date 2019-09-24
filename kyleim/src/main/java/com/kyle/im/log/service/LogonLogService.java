package com.kyle.im.log.service;


import com.kyle.im.common.entity.LogonLog;
import com.kyle.im.common.response.ResultData;
import com.kyle.im.log.mapper.LogonLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yangkaile on 2019/7/25.
 */
@Service
public class LogonLogService {

    @Resource
    LogonLogMapper logonLogMapper;

    public ResultData insert(LogonLog logonLog){
        logonLogMapper.baseInsertAndReturnKey(logonLog);
        return ResultData.success("添加登录日志成功");
    }

}
