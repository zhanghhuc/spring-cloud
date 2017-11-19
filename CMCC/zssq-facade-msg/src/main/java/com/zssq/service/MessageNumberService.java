package com.zssq.service;

import com.alibaba.fastjson.JSONObject;
import com.zssq.exceptions.BusinessException;

/**
 * 
 * @ClassName: MessageNumberService  
 * @Description: 消息数量  
 * @author YDB  
 * @date 2017年4月11日  
 *
 */
public interface MessageNumberService {
	
	
	/**
	 * 
	 * @Title: getMsgNumber  
	 * @Description: 获取用户消息数量列表
	 * @param userCode
	 * @return    参数  
	 * @return: JSONObject    返回类型
	 */
	public JSONObject getMsgNumber(String userCode)  throws BusinessException;
	
	
	/**
	 * 
	 * @Title: MsgDelNumber  
	 * @Description: 删除用户消息数量  
	 * @return: void    返回类型
	 */
	public void MsgDelNumber(String userCode,int type)  throws BusinessException;
	
	
	
	
}
