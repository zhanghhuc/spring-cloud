package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserFriend;

public interface UserFriendMapper {

    int insertSelective(UserFriend record);

    UserFriend selectByCode(String code);
    
    List<UserFriend> selectMyFriends(UserFriend record);
    
    int selectMyFriendsCount(UserFriend record);
    
    /** 删除好友   我是你的好友 和 你是我的好友 */
    int delectFriend(UserFriend record);
    
    /** 删除分组 */
    int deleteGroup(String groupCode);
    
    /** 变更分组 */
    int updateGroup(UserFriend record);
    
    List<String> selectFriends(String userCode);

}