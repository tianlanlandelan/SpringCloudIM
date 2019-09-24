package com.kyle.im;

import com.kyle.im.common.entity.SMSLog;
import com.kyle.im.common.switchs.UserSwitch;
import com.kyle.im.common.util.BitMap;
import com.kyle.im.common.util.Console;
import com.kyle.im.friend.entity.Friend;
import com.kyle.im.friend.mapper.FriendMapper;
import com.kyle.im.group.entity.GroupInfo;
import com.kyle.im.group.entity.GroupMember;
import com.kyle.im.group.mapper.GroupInfoMapper;
import com.kyle.im.group.mapper.GroupMemberMapper;
import com.kyle.im.log.mapper.SMSMapper;
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

    @Resource
    FriendMapper friendMapper;

    @Resource
    SMSMapper smsMapper;

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
    public void createFriendTable(){
        friendMapper.baseCreate(new Friend());
    }

    @Test
    public void createSMSLogTable(){
        smsMapper.baseCreate(new SMSLog());
    }

    @Test
    public void insertUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("email");
        userInfo.setPassword("password");
        BitMap bitMap = new BitMap(16);
        bitMap.atPut(UserSwitch.addGroupNoVerify,true);
        userInfo.setSwitchs(bitMap.getData());
        userInfoMapper.baseInsertAndReturnKey(userInfo);
        Console.print("insertUserInfo",userInfo);
    }

    @Test
    public void findByIdUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        Console.print("",userInfo);
        BitMap bitMap = new BitMap(userInfo.getSwitchs());
        Console.print("",bitMap);
    }

    @Test
    public void updateById(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        Console.print("修改前",userInfo,new BitMap(userInfo.getSwitchs()));

        BitMap bitMap = new BitMap(UserSwitch.MAX);
        bitMap.atPut(UserSwitch.addFriendNoVerify,true);
        bitMap.atPut(UserSwitch.addGroupNoVerify,true);
        userInfo.setSwitchs(bitMap.getData());
        userInfoMapper.baseUpdateById(userInfo);

        userInfo = userInfoMapper.baseSelectById(userInfo);
        Console.print("修改后",userInfo,new BitMap(userInfo.getSwitchs()));
    }

}
