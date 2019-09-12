package com.kyle.im;

import com.kyle.im.common.util.Console;
import com.kyle.im.group.entity.GroupInfo;
import com.kyle.im.group.entity.GroupMember;
import com.kyle.im.group.mapper.GroupInfoMapper;
import com.kyle.im.group.mapper.GroupMemberMapper;
import com.kyle.im.user.entity.UserInfo;
import com.kyle.im.user.mapper.UserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    GroupInfoMapper groupInfoMapper;

    @Resource
    GroupMemberMapper groupMemberMapper;

    @Test
    public void contextLoads() {
        ConcurrentHashMap map = new ConcurrentHashMap(16);
    }

    @Test
    public void createUserInfoTable(){
        userInfoMapper.baseCreate(new UserInfo());
    }

    @Test
    public void createGroupInfoTable(){
        groupInfoMapper.baseCreate(new GroupInfo());
    }
    @Test
    public void createGroupMemberTable(){
        groupMemberMapper.baseCreate(new GroupMember());
    }

    @Test
    public void insertUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("email");
        userInfo.setPassword("password");
        userInfoMapper.baseInsertAndReturnKey(userInfo);
        Console.print("insertUserInfo",userInfo);
    }

}
