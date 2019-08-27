package com.kyle.im.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Utils {
    public static String encode(String str) throws Exception{
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encodeBuffer(str.getBytes("utf-8"));
    }

    public static String decode(String str) throws Exception{
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(str),"utf-8");
    }
}
