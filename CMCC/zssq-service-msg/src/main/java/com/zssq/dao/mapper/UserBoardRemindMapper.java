package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserBoardRemind;
import com.zssq.model.QueryBoardRemindModel;

public interface UserBoardRemindMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserBoardRemind record);

    int insertSelective(UserBoardRemind record);

    UserBoardRemind selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBoardRemind record);

    int updateByPrimaryKey(UserBoardRemind record);
    

    List<UserBoardRemind> selectPage(QueryBoardRemindModel record);
    
    int selectCount(QueryBoardRemindModel record);
    
    int delBoardRemind(String msgCode);
    
}