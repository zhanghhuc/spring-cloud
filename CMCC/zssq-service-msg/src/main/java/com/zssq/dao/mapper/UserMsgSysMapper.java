package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserMsgSys;
import com.zssq.model.MessageSysUserMsgModel;
import com.zssq.model.MessageSystemModel;

public interface UserMsgSysMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMsgSys record);

    int insertSelective(UserMsgSys record);

    UserMsgSys selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMsgSys record);

    int updateByPrimaryKeyWithBLOBs(UserMsgSys record);

    int updateByPrimaryKey(UserMsgSys record);
    
    List<UserMsgSys> selectPage(MessageSystemModel record);
    
    int selectCount(MessageSystemModel record);
    
    int delMessageSystem(UserMsgSys record);
   
    List<UserMsgSys> selectUserMsg(List<String> list);
    
    List<UserMsgSys> selectUserMsgInfo(List<String> list);

    UserMsgSys getMsgSysInfo(String msgCode);
    
}