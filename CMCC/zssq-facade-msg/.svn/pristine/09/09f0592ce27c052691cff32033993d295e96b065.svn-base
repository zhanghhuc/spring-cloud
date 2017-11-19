package com.zssq.service;

import com.zssq.dao.pojo.UserMsgPraise;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessagePraiseAddModel;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 
 * @ClassName: MessagePraiseService  
 * @Description: 消息-用户点赞模块 
 * @author YDB  
 * @date 2017年3月20日  
 *
 */
public interface MessagePraiseService {

	/**
	 * @Title: getPraiseList  
	 * @Description: 消息点赞查询
	 * @param userCode
	 * @param pageParam
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getPraiseList(UserMsgPraise userMsgPraise,PageParam pageParam) throws BusinessException ;
	
	
	
	/**
	 * 
	 * @Title: addMessagePraise  
	 * @Description: 添加消息点赞
	 * @param messagePraise
	 * @return    参数  
	 * @return: int   1成功，0失败 返回类型
	 */
	public boolean addMessagePraise(MessagePraiseAddModel messagePraise) throws BusinessException;
	
	
	
	
	/**
	 * 
	 * @Title: delMessagePraise  
	 * @Description: 删除点赞消息
	 * @param praiseCode 
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean delMessagePraise(String praiseCode) throws BusinessException;
	
	
}
