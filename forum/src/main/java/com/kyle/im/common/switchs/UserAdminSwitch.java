package com.kyle.im.common.switchs;

/**
 * 管理员对用户的设置，存储一系列开关
 */
public enum  UserAdminSwitch {
    /**
     * 限制登录
     */
    refuseLogon(0);
    private int value;

    UserAdminSwitch(int value){
        this.value = value;
    }
    public int getValue(){
       return value;
    }

}
