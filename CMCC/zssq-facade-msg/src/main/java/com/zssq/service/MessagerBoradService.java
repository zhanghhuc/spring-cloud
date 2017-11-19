package com.zssq.service;

import com.zssq.dao.pojo.UserBoardRemind;
import com.zssq.dao.pojo.UserMsgBoardReply;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageBoradModel;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: MessagerBoradService  
 * @Description: 留言板  
 * @author YDB  
 * @date 2017年3月24日  
 *
 */

public interface MessagerBoradService {

	
	/**
	 * 
	 * @Title: addMessageBoardInfo  
	 * @Description: 
	 * @param beMsgUserCode 被留言用户code
	 * @param userCode   留言用户code
	 * @param content    留言内容
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean addMessageBoardInfo(String msgCode,String beMsgUserCode,String userCode, String content,String orgCode,String tenantCode)throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: getMyPublishMessageList  
	 * @Description: 获取留言墙列表
	 * @return    参数  
	 * @return: userMsgBoard    返回类型
	 */
	public PageBean  getMyPublishMessageList(MessageBoradModel msgBoradModel) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: delMsgBoard  
	 * @Description: 删除留言板
	 * @param boardCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean delMsgBoard(String boardCode) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: delMyMsgBoard  
	 * @Description: 我的留言删除
	 * @param boardCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean delMyMsgBoard(String boardCode) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: addBzBoard  
	 * @Description: 班组添加 留言板
	 * @param board
	 * @throws BusinessException    参数  
	 * @return: void    返回类型
	 */
	public  void  addBzBoard(UserBoardRemind boardRemind) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: addBoardReply  
	 * @Description:  添加留言回复
	 * @param reply
	 * @throws BusinessException    参数  
	 * @return: void    返回类型
	 */
	public boolean addBoardReply(UserMsgBoardReply reply,String orgContent,String userCode)throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: getBoardRemind  
	 * @Description: 获取留言板消息提醒
	 * @param userCode
	 * @param pageNo
	 * @param pageSize
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getBoardRemind(String userCode,Integer pageNo,Integer pageSize)  throws BusinessException;
	
	
	/**
	 * 
	 * @Title: delBoardRemind  
	 * @Description: 删除消息类型 
	 * @param msgCoude
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean delBoardRemind(String msgCoude) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: ReportBoardAndReplyMsg  
	 * @Description: 留言，留言回复举报
	 * @param msgCode
	 * @param type 1-留言举报  2-回复举报
	 * @param delete
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean ReportBoardAndReplyMsg(String msgCode,int type,int delete);
	
	
	
	
	
	/**
	 * 
	 * @Title: ManagementBoardMsg  
	 * @Description: 后台管理个人留言板
	 * @param pageSize
	 * @param pageNo
	 * @param orgCode
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean ManagementBoardMsg(Integer pageSize,Integer pageNo,String orgCode);

	
	
	/**
	 * 
	 * @Title: ManagementBoardComment  
	 * @Description: 
	 * @param pageSize
	 * @param pageNo
	 * @param comment
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getReportBoardList(Integer pageSize,Integer pageNo,String boradCode);
	
	
	
	/**
	 * 
	 * @Title: getReportBoradReply  
	 * @Description: 获取举报留言回复
	 * @param pageSize
	 * @param pageNo
	 * @param replyCode
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getReportBoradReply(String boradCode,Integer pageSize,Integer id,String replyCode);
	
	
}