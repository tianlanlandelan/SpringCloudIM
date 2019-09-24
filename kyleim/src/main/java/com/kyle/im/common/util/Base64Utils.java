package com.kyle.im.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Utils {
    public static String encode(String str) throws Exception{
        return new BASE64Encoder().encodeBuffer(str.getBytes("utf-8"));
    }

    public static String decode(String str) throws Exception{
        return new String(new BASE64Decoder().decodeBuffer(str),"utf-8");
    }
}
