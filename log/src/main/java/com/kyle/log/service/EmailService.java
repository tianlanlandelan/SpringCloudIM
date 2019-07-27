package com.kyle.log.service;

import com.kyle.log.mapper.EmailMapper;
import com.kyle.mycommon.entity.EmailLog;
import com.kyle.mycommon.response.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangkaile
 * @date 2019-07-27 22:30:19
 */
@Service
public class EmailService {
    @Resource
    private EmailMapper emailMapper;

    public ResultData checkValidateCode(String email,String code){
        EmailLog entity = new EmailLog();
        entity.setEmail(email);
        entity.setCode(code);
        entity.setBaseKyleUseASC(false);
        List<EmailLog> list = emailMapper.baseSelectByCondition(entity);
        if(list != null && list.size() > 0){
            if(list.get(0).isEfficientVerificationCode()){
                return ResultData.success();
            }
        }
        return ResultData.error("验证码无效");
    }

}
