package com.kyle.log.mapper;

import com.kyle.mycommon.entity.EmailLog;
import com.kyle.mycommon.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailMapper extends BaseMapper<EmailLog> {

}
