package com.zssq.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.biz.MessagePraiseBiz;
import com.zssq.dao.mapper.UserMsgPraiseMapper;
import com.zssq.dao.pojo.UserMsgPraise;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessagePraiseAddModel;
import com.zssq.service.MessagePraiseService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.RedisUtil;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

@Service("messagePraiseService")
public class MessagePraiseServiceImpl implements MessagePraiseService  {

	@Autowired
	private MessagePraiseBiz messagePraiseBiz;	
	@Autowired
	private UserMsgPraiseMapper userMsgPraiseMapper;
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * @Title: getPraiseList  
	 * @Description: 消息点赞查询
	 * @param userCode
	 * @param pageParam
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	@Override
	public PageBean getPraiseList(UserMsgPraise userMsgPraise, PageParam pageParam) throws BusinessException  {
		redisUtil.delMsgNumber(userMsgPraise.getUserCode(), 3);
		
		return messagePraiseBiz.getPraiseList(userMsgPraise, pageParam);
	}
	
	
	
	/**
	 * 
	 * @Title: addMessagePraise  
	 * @Description: 添加消息点赞
	 * @param messagePraise
	 * @return    参数  
	 * @return: int   1成功，0失败 返回类型
	 */
	@Override
	public boolean addMessagePraise(MessagePraiseAddModel messagePraise) throws BusinessException {
		
		UserMsgPraise UserMsgPraise=new UserMsgPraise();
		UserMsgPraise.setIsDelete((byte)0);
		UserMsgPraise.setUserCode(messagePraise.getUserCode());
		UserMsgPraise.setPraiseUserCode(messagePraise.getPraiseUserCode());
		UserMsgPraise.setPraiseType((byte)messagePraise.getPraiseType().intValue());
		UserMsgPraise.setCreateTime(DateUtils.getTime());
		UserMsgPraise.setOrgCode(messagePraise.getOrgCode());
		UserMsgPraise.setTenantCode(messagePraise.getTenantCode());
	//	UserMsgPraise.setOrgLevel((byte)messagePraise.getOrgLeve().intValue());
		UserMsgPraise.setContent(messagePraise.getContent());
		UserMsgPraise.setPraiseCode(UUIDHelper.getUUID());
		UserMsgPraise.setOriginalCode(messagePraise.getOriginalCode());
		int state=userMsgPraiseMapper.insert(UserMsgPraise);
		
		return state==1?true:false;
	}



	/**
	 * 
	 * @Title: delMessagePraise  
	 * @Description: 删除点赞消息
	 * @param praiseCode 
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean delMessagePraise(String praiseCode) throws BusinessException {
		UserMsgPraise userMsgPraise=new UserMsgPraise();
		
		userMsgPraise.setPraiseCode(praiseCode);
		userMsgPraise.setIsDelete((byte)1);
		int state=userMsgPraiseMapper.delPraise(userMsgPraise);
		
		return state==0?false:true;
	}



	
	
	
	
	

}
