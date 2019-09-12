package com.kyle.im.friend.controller;

import com.kyle.im.common.config.PublicConfig;
import com.kyle.im.common.config.RouterName;
import com.kyle.im.common.response.MyResponse;
import com.kyle.im.common.router.RouterAttribute;
import com.kyle.im.friend.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-09-12 22:35:39
 */
@RestController
public class FriendController {
    @Resource
    private FriendService friendService;

    @RouterAttribute(name = "添加好友",description = "添加好友接口，登录用户可以访问，向要添加好友的用户发送添加好友的通知，对方同意后，可以添加为好友"
            ,authority = PublicConfig.AUTHORITY_LOGON)
    @PostMapping(RouterName.FRIEND_ADD)
    public ResponseEntity addFriend(Integer userId,Integer friendId){
        return MyResponse.ok();
    }

    @RouterAttribute(name = "处理好友申请",description = "同意或拒绝添加对方为好友",authority = PublicConfig.AUTHORITY_LOGON)
    @PostMapping(RouterName.FRIEND_ACTION_ADD)
    public ResponseEntity actionAddFriendNotify(Integer userId,Integer friendId,int mask){
        return MyResponse.ok();
    }








}
