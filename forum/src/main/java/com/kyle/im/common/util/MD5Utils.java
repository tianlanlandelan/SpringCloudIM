package com.kyle.im.common.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 规定统一的MD5加密方法
 */
public class MD5Utils {

    /**
     * 加密字符串
     * @param str 原字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException,UnsupportedEncodingException 加密过程中抛的异常
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException,UnsupportedEncodingException {
        return new BASE64Encoder().encode(
                MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8"))
        );
    }

    /**
     * 检查原字符串和加密后的字符串是否匹配
     * @param str   原字符串
     * @param encodeStr 加密后的字符串
     * @return 匹配 true,不匹配 false
     * @throws NoSuchAlgorithmException,UnsupportedEncodingException 加密过程中可能抛异常
     */
    public static boolean checkQual(String str,String encodeStr){
        try {
            if(EncoderByMd5(str).equals(encodeStr)){
                return true;
            }
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return false;
    }

}
