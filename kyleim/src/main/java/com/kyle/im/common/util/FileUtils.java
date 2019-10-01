package com.kyle.im.common.util;

/**
 * @author yangkaile
 * @date 2019-05-30 09:10:12
 */
public class FileUtils {
    /**
     * 文件后缀名分隔符
     */
    public static final String SUFFIX_SPLIT = ".";

    /**
     * 获取文件名的后缀，如 jpg/txt等
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf(SUFFIX_SPLIT) + 1);
    }
    /**
     * 获取文件名的后缀和分隔符，如 .jpg/.txt等
     * @param fileName
     * @return
     */
    public static String getSuffixWithSpilt(String fileName){
        return fileName.substring(fileName.lastIndexOf(SUFFIX_SPLIT));
    }
    public static void main(String[] args){
        Console.print("getSuffix",getSuffix("abc.jpg"));
    }
}
