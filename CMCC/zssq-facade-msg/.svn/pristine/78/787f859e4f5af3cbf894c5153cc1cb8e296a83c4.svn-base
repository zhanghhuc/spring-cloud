package com.zssq.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageSubscribeAddModel;
import com.zssq.model.MessageSubscribeModel;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: MessageSubscribeService  
 * @Description: 消息订阅  
 * @author YDB  
 * @date 2017年3月28日  
 *
 */
public interface MessageSubscribeService {

	
	/**
	 * 
	 * @Title: addMessageSubscribe   
	 * @Description: 添加订阅通知
	 * @param subModel
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean addMessageSubscribe(MessageSubscribeAddModel subModel) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: delMessageSubscribe  
	 * @Description: 删除订阅消息 
	 * @param subCode 
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean delMessageSubscribe(String subCode) throws BusinessException;
	
	
	
	
	/**
	 * 
	 * @Title: getMsgSubscribesList  
	 * @Description: 查询订阅通知列表
	 * @param userCode
	 * @param type
	 * @param pageSize
	 * @param pageNo
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getMsgSubscribesList(MessageSubscribeModel subModel) throws BusinessException;
	
}
