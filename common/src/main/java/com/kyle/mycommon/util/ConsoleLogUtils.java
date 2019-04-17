package com.kyle.mycommon.util;

/**
 * @author yangkaile
 * @date 2019-04-16 11:00:56
 * 控制台日志输出工具类
 */
public class ConsoleLogUtils {
    public static void print(String title,Object ... objects){
        System.out.println("=======" + title + "=======begin========");
        for(Object object : objects){
            System.out.print(object + " , ");
        }
        System.out.println("=======" + title + "=======end==========");

    }

}
