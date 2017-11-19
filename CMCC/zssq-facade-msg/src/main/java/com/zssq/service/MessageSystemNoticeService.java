package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.UserMsgNotice;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageNoticeListModel;
import com.zssq.utils.PageBean;

public interface MessageSystemNoticeService {

	
	/**
	 * 
	 * @Title: getNoticeList  
	 * @Description: 获取系统通知
	 * @param mdel
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	
	public PageBean  getNoticeList(MessageNoticeListModel mdel) throws BusinessException;
	
	/**
	 * 
	 * @Title: getNoticeInfo  
	 * @Description: 获取系统消息
	 * @param msgCode
	 * @return    参数  
	 * @return: UserMsgNotice    返回类型
	 */
	
	public UserMsgNotice getNoticeInfo(String msgCode) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: delNotice  
	 * @Description: 删除消息通知
	 * @param msgCode
	 * @return
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean delNotice(String msgCode)throws BusinessException;
	
	
	
	
	/**
	 * 
	 * @Title: saveNoticeList  
	 * @Description: 批量添加系统消息
	 * @param list    参数  
	 * @return: void    返回类型
	 */
	public void saveNoticeList(List<UserMsgNotice> list);
	
}
