package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.UserMsgBoardReply;
import com.zssq.model.GetReportBoradReplyModel;
import com.zssq.model.MessageBoardReplyModel;
import com.zssq.model.QueryBoradReplyMode;
import com.zssq.model.ReporyReplyModel;

public interface UserMsgBoardReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMsgBoardReply record);

    int insertSelective(UserMsgBoardReply record);

    UserMsgBoardReply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMsgBoardReply record);

    int updateByPrimaryKeyWithBLOBs(UserMsgBoardReply record);

    int updateByPrimaryKey(UserMsgBoardReply record);
    
    /**
     * 
     * @Title: selectBoardCount  
     * @Description:留言板内的回复数
     * @param list
     * @return    参数  
     * @return: int    返回类型
     */
    List<Map<String, Object>> selectBoardReplyCount(List<String> list);
    
    /**
     * 
     * @Title: selectBoardReply  
     * @Description:留言板内的回复信息
     * @param list
     * @return    参数  
     * @return: List<HashMap<String,Object>>    返回类型
     */
    List<Map<String, Object>> selectBoardReply(QueryBoradReplyMode qbm);
    
    
    /**
     * 
     * @Title: selectCount  
     * @Description: 回复分页查询
     * @param mbrModel
     * @return    参数  
     * @return: int    返回类型
     */
    int selectCount(MessageBoardReplyModel mbrModel);
    
    
    /**
     * 
     * @Title: selectPage  
     * @Description: TODO
     * @param mbrModel
     * @return    参数  
     * @return: List<UserMsgBoardReply>    返回类型
     */
    List<UserMsgBoardReply> selectPage(MessageBoardReplyModel mbrModel);
    
    
    
    
    /**
     * 
     * @Title: delBoardReply  
     * @Description: 删除回复
     * @param replyCode
     * @return    参数  
     * @return: int    返回类型
     */
    int delBoardReply(UserMsgBoardReply record);
    
    
    
    /**
     * 
     * @Title: reportRely  
     * @Description: 举报
     * @param record
     * @return    参数  
     * @return: int    返回类型
     */
    
    int reportRely(ReporyReplyModel record);
    
    
    
    /**
     * 
     * @Title: getReportBoradReplyInfo  
     * @Description: 获取举报回复详细
     * @param reocrd
     * @return    参数  
     * @return: UserMsgBoardReply    返回类型
     */
    UserMsgBoardReply getReportBoradReplyInfo(String replyCode);
    
    
    
    
    /**
     * 
     * @Title: getReportBoradReplyInfo  
     * @Description: 举报列表
     * @param reocrd
     * @return    参数  
     * @return: List<UserMsgBoardReply>    返回类型
     */
    List<UserMsgBoardReply> getReportBoradReplyList(GetReportBoradReplyModel reocrd);
    
    int getReportBoradReplyCount(GetReportBoradReplyModel reocrd);
    
    
}