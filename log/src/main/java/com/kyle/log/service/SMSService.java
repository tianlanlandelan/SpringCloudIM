package com.kyle.log.service;

import com.kyle.log.mapper.SMSMapper;
import com.kyle.mycommon.entity.SMS;
import com.kyle.mycommon.response.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class SMSService {
    @Resource
    private SMSMapper smsMapper;
    public ResultData checkValidateCode(String phone,String code){
        SMS sms = new SMS();
        sms.setPhone(phone);
        sms.setCodeStr(code);
        List<SMS> list = smsMapper.baseSelectByCondition(sms,null,true);
        if(list != null && list.size() > 0){
            if(list.get(0).isEfficientVerificationCode()){
                return ResultData.success();
            }
        }
        return ResultData.error("验证码无效");
    }
}
