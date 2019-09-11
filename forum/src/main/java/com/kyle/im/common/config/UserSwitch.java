package com.kyle.im.common.config;

/**
 * 用户掩码值，存储一系列与用户相关的设置
 * 其中：低8位是系统设置相关，不允许用户自行设置
 * @author yangkaile
 * @date 2019-09-11 13:58:51
 */
public enum UserSwitch {
    /**
     *
     */
    A(1 << 0),
    /**
     *
     */
    B(1 << 1),

    SHOW_PHONE( 1 << 9),
    SHOW_EMAIL( 1 << 10);

    private int value;
    UserSwitch(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
