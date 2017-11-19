package com.zssq.filing.dao.mapper;

import java.util.List;

import com.zssq.filing.pojo.UserFriend;

public interface UserFriendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);

    UserFriend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserFriend record);

    int updateByPrimaryKey(UserFriend record);

    /**
     * 
     * @Function bathInsert
     * @Description 批量新增
     * @param userFriends
     */
	void bathInsert(List<UserFriend> userFriends);
}