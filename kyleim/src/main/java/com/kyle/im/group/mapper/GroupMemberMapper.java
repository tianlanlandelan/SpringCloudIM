package com.kyle.im.group.mapper;

import com.kyle.im.common.mybatis.BaseMapper;
import com.kyle.im.group.entity.GroupMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMemberMapper extends BaseMapper<GroupMember> {
}
