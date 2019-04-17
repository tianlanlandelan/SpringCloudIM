package com.kyle.mycommon.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author yangkaile
 * @date 2018-08-28 16:15:10
 */
public class StringUtils {
    public static final String numbersChar = "0123456789";
    public static final String allChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 判断一系列字符串中是否有空的（包含:空字符串、null、纯空格字符）
     * @param parameters 需要判断的字符串，可以是多个
     * @return
     */
    public static boolean isEmpty(String... parameters){
        boolean result = false;
        for(String str:parameters){
            if(str == null || str.isEmpty() || str.trim().isEmpty()){
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 生成指定长度的数字字符串
     * @param length
     *  字符串长度
     * @return
     */
    public static String getNumbserString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(numbersChar.charAt(random.nextInt(numbersChar.length())));
        }
        return sb.toString();
    }

    public static String getAllCharString(int length){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
