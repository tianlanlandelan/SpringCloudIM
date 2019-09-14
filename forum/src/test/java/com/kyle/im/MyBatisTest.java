package com.kyle.im;

import com.kyle.im.common.mybatis.SqlFieldReader;
import com.kyle.im.common.util.Console;
import com.kyle.im.user.entity.UserInfo;
import org.junit.Test;

public class MyBatisTest {
    Class cls = new UserInfo().getClass();

    @Test
    public void createTable(){
        SqlFieldReader.getCreateTableSql(cls);
    }

    @Test
    public void getFieldAnnotationList(){
        Console.print("",SqlFieldReader.getFieldAnnotationList(cls));
    }
}
