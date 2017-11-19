package com.zssq.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessagePrivateInfoModel;
import com.zssq.model.MessagePrivateLetterModel;
import com.zssq.model.SendMsgPrivateLetterModel;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: MessagePriateLetter  
 * @Description: 私信接口  
 * @author YDB  
 * @date 2017年3月29日  
 *
 */
public interface MessagePrivateLetterService {
	
	/**
	 * 
	 * @Title: sendPrivateLetter  
	 * @Description: 发送私信
	 * @param userCode 
	 * @param ReceiveUserCode 接收人userCode
	 * @param content
	 * @return    参数  
	 * @return: MsgPrivateLetter    返回类型
	 */
	
	public boolean sendPrivateLetter(SendMsgPrivateLetterModel sendModel) throws BusinessException ;
	
	
	/**
	 * 
	 * @Title: getPrivateLetterMainList  
	 * @Description: 获取私信主列表
	 * @param letter
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getPrivateLetterMainList(MessagePrivateLetterModel letter) throws BusinessException ;
	
	
	
	
	/**
	 * 
	 * @Title: getPrivateLetterInfoList  
	 * @Description: 获取私信详细列表
	 * @param letter
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getPrivateLetterInfoList(MessagePrivateInfoModel letter) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: delPrivateLetter  
	 * @Description: 删除私信
	 * @param userCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean delPrivateLetter(String userCodem,String letterUserCode) throws BusinessException;
	
	
}