package com.kyle.mycommon.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MAMIAN
 * 检查传入的字符串是否是用户名、手机号、邮箱
 */
public class ValidUserName {


    /**
     * 允许1开头的11位数字
     */
    private static final String PHONE_NUMBER_PATTERN = "^1+\\d{10}$";

    /**
     * 下划线位置不限
     */
    private static final String USER_NAME_PATTERN = "^[a-zA-Z0-9_]+$";

    private static final String EMAIL_ADDRESS_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|" +
            "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    public static boolean isUserName(String userName) {
        Pattern pattern = Pattern.compile(USER_NAME_PATTERN);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    public static boolean isPhoneNo(String userName) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();

    }

    public static boolean notPhoneOrEmail(String userName){
        return !isEmail(userName) && !isPhoneNo(userName);
    }

    public static boolean isPhoneOrEmail(String userName){
        return isEmail(userName) || isPhoneNo(userName);
    }

    /**
     * 允许A-Z a-z 0-9 + - . @
     * @param userName
     * @return
     */
    public static boolean isEmail(String userName) {
        Pattern pattern = Pattern.compile(EMAIL_ADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();

    }

    public static void main(String[] args){
        Console.print("",notPhoneOrEmail("guyexing@cc.com"));
    }




}
