package com.kyle.user;

import com.kyle.mycommon.util.Console;
import org.junit.Test;

public class MyTest {
    @Test
    public void a(){
        int a = 0b1001;
        Console.print("1<<0",1<<0);
        //将指定位置1
        Console.print("a | (1 << 1))",a | (1 << 1));
        //将指定位置0
        Console.print("a & ~(1 << 3))",a & ~(1 << 3));
    }

}
