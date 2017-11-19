package com.zssq.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageCommentAddModel;
import com.zssq.model.MessageCommentModel;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: MessageCommentService  
 * @Description: 消息评论 
 * @author YDB  
 * @date 2017年3月22日  
 *
 */
public interface MessageCommentService {
	
	
	/**
	 * 
	 * @Title: getMsgCommentList  
	 * @Description: 评论查询
	 * @param messageCommentModel
	 * @return    参数  
	 * @return: UserMsgComment    返回类型
	 */
	
	public PageBean getMsgCommentList(MessageCommentModel messageCommentModel) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: addMsgComment  
	 * @Description: 添加评论消息
	 * @param MsgCommentAddModel
	 * @return    参数  
	 * @return: int    返回类型
	 */
	public int addMsgComment(MessageCommentAddModel  MsgCommentAddModel) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: delMsgComment  
	 * @Description: 删除评论消息
	 * @param commentCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean delMsgComment(String commentCode) throws BusinessException;
}
