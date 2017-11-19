package com.zssq.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageATModel;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: MessageATService  
 * @Description: 消息-@我  
 * @author YDB  
 * @date 2017年3月21日  
 *
 */
public interface MessageATService {

	




	/**
	 * 
	 * @Title: getMyAtList  
	 * @Description: @我的查询
	 * @param messageAT
	 * @return    参数  
	 * @return: List<UserMsgAT>  返回类型
	 */
	public PageBean getMyAtList(MessageATModel messageAT) throws BusinessException ;
	
	
	/**
	 * 
	 * @Title: addMessageAT  
	 * @Description: 添加AT消息
	 * @param msgModel
	 * @return    参数  
	 * @return: int  1成功，0失败 返回类型
	 */
	//public boolean addMessageAT(MessageAtAddModel msgModel) throws BusinessException;
	
	
	
	
	/**
	 * 
	 * @Title: delMessageAT  
	 * @Description: 删除at
	 * @param atCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean delMessageAT(String atCode) throws BusinessException;
	
}