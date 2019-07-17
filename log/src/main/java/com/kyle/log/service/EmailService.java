package com.kyle.log.service;

import com.kyle.log.mapper.EmailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmailService {
    @Resource
    private EmailMapper emailMapper;

}
