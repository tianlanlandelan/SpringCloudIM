package com.kyle.im;

import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.response.ResponseReader;
import com.kyle.im.common.response.ResultData;
import com.kyle.im.common.util.Console;
import com.kyle.im.user.entity.UserInfo;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ResponseReaderTest {

    @Test
    public void getList(){
        List<UserInfo>  list = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("guyexing@foxmail.com");
        list.add(userInfo);
        list.add(userInfo);
        ResponseEntity entity = MyResponse.ok(ResultData.success(list));
        Console.print("",ResponseReader.getList(entity,UserInfo.class));
    }
    @Test
    public void getIntegerList(){
        List<Long>  list = new ArrayList<>();

        list.add(1l);
        list.add(1l);
        ResponseEntity entity = MyResponse.ok(ResultData.success(list));
        Console.print("",ResponseReader.getList(entity,Long.class));
    }

    @Test
    public void getUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone("123");
        ResponseEntity entity = MyResponse.ok(ResultData.success(userInfo));
        Console.print("",ResponseReader.getObject(entity,UserInfo.class));
    }

    @Test
    public void getMessage(){
        ResponseEntity entity = MyResponse.ok(ResultData.error("用户不存在"));
        Console.print("",ResponseReader.getMessage(entity));
    }
}
