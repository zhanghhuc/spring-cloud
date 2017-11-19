package com.zssq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.MessageNumberService;
import com.zssq.util.RedisUtil;


@Service("messageNumberService")
public class MessageNumberServiceImpl implements MessageNumberService{
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 
	 * @Title: getMsgNumber  
	 * @Description: 获取用户消息数量列表
	 * @param userCode
	 * @return    参数  
	 * @return: JSONObject    返回类型
	 */
	@Override
	public JSONObject getMsgNumber(String userCode) throws BusinessException {
		return redisUtil.getUserNumber(userCode);
	}

	/**
	 * 
	 * @Title: MsgDelNumber  
	 * @Description: 删除用户消息数量  
	 * @return: void    返回类型
	 */
	@Override
	public void MsgDelNumber(String userCode, int type)  throws BusinessException {
		redisUtil.delMsgNumber(userCode, type);
	}
	
	
}