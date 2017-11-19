package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserMsgPraise;

public interface UserMsgPraiseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMsgPraise record);

    int insertSelective(UserMsgPraise record);

    UserMsgPraise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMsgPraise record);

    int updateByPrimaryKey(UserMsgPraise record);
    
    List<UserMsgPraise> selectPage(UserMsgPraise pl);
    
    int selectCount(UserMsgPraise pl);
    
    int delPraise(UserMsgPraise record);
    
    
    
}