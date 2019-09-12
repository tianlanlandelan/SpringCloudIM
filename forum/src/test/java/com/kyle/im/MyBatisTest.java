package com.kyle.im;

import com.kyle.im.common.mybatis.SqlFieldReader;
import com.kyle.im.user.entity.UserInfo;
import org.junit.Test;

public class MyBatisTest {
    @Test
    public void createTable(){
        SqlFieldReader.getCreateTableSql(new UserInfo().getClass());
    }
}
