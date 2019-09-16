package com.kyle.im.log.service;


import com.kyle.im.common.entity.SMSLog;
import com.kyle.im.common.response.ResultData;
import com.kyle.im.log.mapper.SMSMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SMSService {
    @Resource
    private SMSMapper smsMapper;

    /**
     *
     * @param phone
     * @param code
     * @return
     */
    public ResultData checkValidateCode(String phone, String code){
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
    public ResultData save(SMSLog log){
        smsMapper.baseInsertAndReturnKey(log);
        return ResultData.success();
    }


}
