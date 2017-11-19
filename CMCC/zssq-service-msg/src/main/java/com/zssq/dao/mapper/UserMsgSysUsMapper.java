package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserMsgSysUs;
import com.zssq.model.MessageSysUserMsgModel;
import com.zssq.model.TempDelMsgSysModel;

public interface UserMsgSysUsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMsgSysUs record);

    int insertSelective(UserMsgSysUs record);

    UserMsgSysUs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMsgSysUs record);

    int updateByPrimaryKey(UserMsgSysUs record);
    
    /**
     * 
     * @Title: selectUseNewsMsg  
     * @Description: 查询最新的用户新闻
     * @return    参数  
     * @return: List<UserMsgSysUs>    返回类型
     */
    List<UserMsgSysUs> selectUseNewsMsg(String userCode);
    
    
    /**
     * 
     * @Title: updateUserSysMsg  
     * @Description: 更新用户系统消息
     * @param record
     * @return    参数  
     * @return: int    返回类型
     */
    int updateUserSysMsg(MessageSysUserMsgModel  record);
    
    
    
    /**
     * 
     * @Title: batchMsg  
     * @Description: 批量插入
     * @param list
     * @return    参数  
     * @return: int    返回类型
     */
    int batchMsg(List<UserMsgSysUs> list);
    
    //分页查询
    List<UserMsgSysUs> selectPage(MessageSysUserMsgModel  record);
    //分页查询
    int selectCount( MessageSysUserMsgModel record);
    
    
    //查询未删除的数量
    int selCountDelete(TempDelMsgSysModel model);
    //删除消息
    int delUserMsg(TempDelMsgSysModel model);
    //删除最后一条消息
    int delLastUserMsg(TempDelMsgSysModel model);
    //获取当前用户最新消息的创建时间
    UserMsgSysUs getUserMsgTime(TempDelMsgSysModel model);
    //更新创建时间
    int updateUserMsgTime(TempDelMsgSysModel model);
    // 删除垃圾数据
    int delGarbage(TempDelMsgSysModel model);
}