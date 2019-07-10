package com.kyle.ingateway;

import com.kyle.mycommon.util.Console;
import com.kyle.mycommon.util.JsonUtils;
import com.kyle.ingateway.test.Sender;
import com.kyle.ingateway.test.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class IngatewayApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSender(){
        sender.send();
    }

    @Test
    public void testJson(){
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(29);
        userInfo.setName("王刚");
        userInfo.setCreateTime(new Date());
        Console.print("json",JsonUtils.toJSONString(userInfo));

    }
}
