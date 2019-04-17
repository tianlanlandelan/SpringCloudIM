package com.kyle.ingateway;

import com.kyle.mycommon.util.ConsoleLogUtils;
import com.kyle.mycommon.util.JsonUtils;
import com.kyle.ingateway.test.Sender;
import com.kyle.ingateway.test.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
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
        ConsoleLogUtils.print("json",JsonUtils.toJSONString(userInfo));

    }
}
