package com.kyle.user;

import com.kyle.user.test.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {
    @Autowired
    private Sender sender;
    @Test
    public void contextLoads() {
    }

    @Test
    public void testSender(){
        sender.send();
    }


}
