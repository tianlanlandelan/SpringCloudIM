package com.kyle.im.friend;

import com.kyle.im.common.response.ResultData;
import com.kyle.im.friend.mapper.FriendMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-09-12 22:33:43
 */
@Service
public class FriendService {
    @Resource
    private FriendMapper friendMapper;

    /**
     * 添加好友
     * 从user组件查找 id 为 friendId 的用户是否存在，
     * 如果存在，查看添加该用户为好友是否需要同意
     *  如果不需要同意，直接添加，确立双方的好友关系；
     *  如果需要同意，创建好友关系的记录，标记为好友关系为未确认状态，并向该用户发送好友申请
     * 如果不存在，返回用户不存在
     * @param userId
     * @param friendId
     * @return
     */
    public ResultData addFriend(int userId,int friendId){

        return ResultData.success();
    }

    /**
     * 处理好友申请
     * 如果 mask = 0 ，表示同意添加为好友，双方好友关系正式确立
     * 如果 mask = 1 ，表示拒绝添加好友，双方好友关系建立失败，删除好友关系记录
     * @param userId
     * @param friendId
     * @param mask
     * @return
     */
    public ResultData actionAddFriendNotify(int userId,int friendId,int mask){
        return ResultData.success();
    }

    public ResultData deleteFriend(int userId,int friendId){
        return ResultData.success();
    }

}
