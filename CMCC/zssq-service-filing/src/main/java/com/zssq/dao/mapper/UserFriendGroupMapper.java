package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.MyGroup;
import com.zssq.dao.pojo.UserFriendGroup;

public interface UserFriendGroupMapper {

    int insertSelective(UserFriendGroup record);

    UserFriendGroup selectByCode(String code);
    
    int deleteByCode(String code);
    
    List<MyGroup> selectMyGroup(Map<String,Object> map);
    

}