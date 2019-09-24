package com.kyle.im.log.mapper;


import com.kyle.im.common.entity.EmailLog;
import com.kyle.im.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailMapper extends BaseMapper<EmailLog> {

}
