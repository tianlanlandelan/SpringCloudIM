package com.kyle.user.service;

import com.kyle.user.mapper.UserProfileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserProfileService {
    @Resource
    private UserProfileMapper userProfileMapper;



}
