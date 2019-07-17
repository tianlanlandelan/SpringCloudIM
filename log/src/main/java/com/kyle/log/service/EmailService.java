package com.kyle.log.service;

import com.kyle.log.mapper.EmailMapper;
import com.kyle.mycommon.entity.Email;
import com.kyle.mycommon.response.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmailService {
    @Resource
    private EmailMapper emailMapper;

    public ResultData checkValidateCode(String email,String code){
        Email entity = new Email();
        entity.setEmail(email);
        entity.setCode(code);
        List<Email> list = emailMapper.baseSelectByCondition(entity,null,false);
        if(list != null && list.size() > 0){
            if(list.get(0).isEfficientVerificationCode()){
                return ResultData.success();
            }
        }
        return ResultData.error("验证码无效");
    }

}
