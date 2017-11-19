package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserMsgSubscribe;
import com.zssq.model.MessageSubscribeModel;

public interface UserMsgSubscribeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMsgSubscribe record);

    int insertSelective(UserMsgSubscribe record);

    UserMsgSubscribe selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMsgSubscribe record);

    int updateByPrimaryKey(UserMsgSubscribe record);
    
    List<UserMsgSubscribe> selectPage(MessageSubscribeModel  record);
    
    int selectCount(MessageSubscribeModel  record);
    
}