package com.kyle.im.log.service;

import com.kyle.im.common.entity.EmailLog;
import com.kyle.im.common.response.ResultData;
import com.kyle.im.log.mapper.EmailMapper;
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

    public ResultData checkValidateCode(String email, String code){
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

    public ResultData save(EmailLog log){
        emailMapper.baseInsertAndReturnKey(log);
        return ResultData.success();
    }

}
