package com.zssq.filing.dao.mapper;

import java.util.List;

import com.zssq.filing.pojo.UserFriendGroup;

public interface UserFriendGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFriendGroup record);

    int insertSelective(UserFriendGroup record);

    UserFriendGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserFriendGroup record);

    int updateByPrimaryKey(UserFriendGroup record);

    /**
     * 
     * @Function bathInsert
     * @Description 批量添加
     * @param userFriendGroups
     */
	void bathInsert(List<UserFriendGroup> userFriendGroups);
}