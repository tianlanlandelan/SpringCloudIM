package com.kyle.im.common.switchs;

/**
 * 用户个人设置，存储一系列开关
 * @author yangkaile
 * @date 2019-09-11 13:58:51
 */
public class UserSwitch {
    /**
     * 最大开关数
     */
    public static final int MAX = 1 << 6;

    /**
     * 前16位(0 ~ 15)预留为系统设置，其他的是用户个人设置
     */
    public static final int ADMIN_SWITCH = 1 << 4;


    /**
     * 添加我为好友，不需要我同意
     */

    public static final int addFriendNoVerify = 16;
    /**
     * 拉我进群，不需要我同意
     */
    public static final int addGroupNoVerify = 17;
}
