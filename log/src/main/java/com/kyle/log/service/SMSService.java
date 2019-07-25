package com.kyle.log.service;

import com.kyle.log.mapper.SMSMapper;
import com.kyle.mycommon.entity.SMSLog;
import com.kyle.mycommon.response.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SMSService {
    @Resource
    private SMSMapper smsMapper;
    public ResultData checkValidateCode(String phone,String code){
        SMSLog sms = new SMSLog();
        sms.setPhone(phone);
        sms.setCodeStr(code);
        sms.setBaseKyleUseASC(false);
        List<SMSLog> list = smsMapper.baseSelectByCondition(sms);
        if(list != null && list.size() > 0){
            if(list.get(0).isEfficientVerificationCode()){
                return ResultData.success();
            }
        }
        return ResultData.error("验证码无效");
    }
}
