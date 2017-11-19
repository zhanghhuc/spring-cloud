package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserBordAndReply;
import com.zssq.dao.pojo.UserMsgBoard;
import com.zssq.dao.pojo.UserMsgBoardReply;
import com.zssq.model.DelBoardMsgModel;
import com.zssq.model.GetReportBoradListModel;
import com.zssq.model.ManagementBoardModel;
import com.zssq.model.MessageBoradModel;
import com.zssq.model.ReportBoardModel;

public interface UserMsgBoardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMsgBoard record);

    int insertSelective(UserMsgBoard record);

    UserMsgBoard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMsgBoard record);

    int updateByPrimaryKeyWithBLOBs(UserMsgBoard record);

    int updateByPrimaryKey(UserMsgBoard record);
    
    List<UserMsgBoard> selectPage(MessageBoradModel record);
    
    int selectCount(MessageBoradModel record);
    
    int delBoard(DelBoardMsgModel record);
    
    
    List<UserBordAndReply> getlistAll();
    
    List<UserBordAndReply> selectBordList(MessageBoradModel record);
    
    List<UserMsgBoardReply> selectReplyList(String code);
    
    int reportBoard(ReportBoardModel record);
    
    
    List<UserMsgBoard> selectManagementBoardList(ManagementBoardModel record);
    
    int selectManagementBoardCount(ManagementBoardModel record);
    
    /**
     * 
     * @Title: getReportBoradMsg  
     * @Description: 获取当前举报留言内容
     * @param boardCode
     * @return    参数  
     * @return: UserMsgBoard    返回类型
     */
    UserMsgBoard getReportBoradMsg(String boardCode);
    
    
    /**
     * 
     * @Title: getReportBoradMsg  
     * @Description: 获取举报留言
     * @param record
     * @return    参数  
     * @return: List<UserMsgBoard>    返回类型
     */
    List<UserMsgBoard> getReportBoradList(GetReportBoradListModel record);
    
    int getReportBoradCount(GetReportBoradListModel record);

}