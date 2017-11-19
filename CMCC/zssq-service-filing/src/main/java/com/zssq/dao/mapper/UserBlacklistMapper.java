package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserBlacklist;

public interface UserBlacklistMapper {

    int insertSelective(UserBlacklist record);

    UserBlacklist selectByCode(String code);
    
    List<UserBlacklist> selectByRecord(UserBlacklist record);
    
    int deleteBlack(UserBlacklist record);
    
    List<UserBlacklist> selectPage(UserBlacklist record);
    
    int selectCount(String userCode);
}