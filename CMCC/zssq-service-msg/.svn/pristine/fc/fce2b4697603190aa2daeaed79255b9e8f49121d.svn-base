package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserMsgAT;

public interface UserMsgATMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMsgAT record);

    int insertSelective(UserMsgAT record);

    UserMsgAT selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMsgAT record);

    int updateByPrimaryKey(UserMsgAT record);
    
    List<UserMsgAT> selectPage(UserMsgAT record);
    
    int selectCount(UserMsgAT record);
    
    /**
     * 
     * @Title: delMessageAT  
     * @Description: 删除消息
     * @param msgCode
     * @return    参数  
     * @return: int    返回类型
     */
    int delMessageAT(String msgCode);
    
    
    
    int batchMsg(List<UserMsgAT> list);
}