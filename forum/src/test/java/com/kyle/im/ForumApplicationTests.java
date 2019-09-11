package com.kyle.im;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ConcurrentHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForumApplicationTests {

    @Test
    public void contextLoads() {
        ConcurrentHashMap map = new ConcurrentHashMap(16);
    }

}
