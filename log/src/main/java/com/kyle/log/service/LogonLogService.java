package com.kyle.log.service;

import com.kyle.log.mapper.LogonLogMapper;
import com.kyle.mycommon.entity.LogonLog;
import com.kyle.mycommon.response.ResultData;
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
