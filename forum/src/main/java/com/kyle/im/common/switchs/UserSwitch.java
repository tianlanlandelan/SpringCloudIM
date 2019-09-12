package com.kyle.im.common.switchs;

/**
 * 用户个人设置，存储一系列开关
 * @author yangkaile
 * @date 2019-09-11 13:58:51
 */
public enum UserSwitch {
    /**
     * 添加我为好友，不需要我同意
     */
    addFriendNoVerify(0),
    /**
     * 拉我进群，不需要我同意
     */
    addGroupNoVerify(1);

    private int value;
    UserSwitch(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
