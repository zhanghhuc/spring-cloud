package com.zssq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.UserMsgSubscribeMapper;
import com.zssq.dao.pojo.UserMsgSubscribe;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageSubscribeAddModel;
import com.zssq.model.MessageSubscribeModel;
import com.zssq.service.MessageSubscribeService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.RedisUtil;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: MessageSubscribeServiceImpl  
 * @Description: 订阅通知  
 * @author YDB  
 * @date 2017年3月31日  
 *
 */
@Service("messageSubscribeService")
public class MessageSubscribeServiceImpl implements MessageSubscribeService{

	@Autowired
	private UserMsgSubscribeMapper userMsgSubscribeMapper; 
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 
	 * @Title: addMessageSubscribe   
	 * @Description: 添加订阅通知
	 * @param subModel
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean addMessageSubscribe(MessageSubscribeAddModel subModel) throws BusinessException{
		
		UserMsgSubscribe record=new UserMsgSubscribe();
		record.setCreatTime(DateUtils.getTime());
		record.setIsDelete((byte)0);
		record.setOrgCode(subModel.getOrgCode());
	//	record.setOrgLevel((byte)Integer.parseInt(subModel.getOrgLevel()));
		record.setSubCode(UUIDHelper.getUUID());
		record.setSubType((byte)subModel.getSubType());
		record.setSubUserCode(subModel.getSubUserCode());
		record.setTenantCode(subModel.getTenantCode());
		record.setUserCode(subModel.getUserCode());
		int state=userMsgSubscribeMapper.insert(record);
		
		return state==1?true:false;
	}

	/**
	 * 
	 * @Title: delMessageSubscribe  
	 * @Description: 删除订阅消息 
	 * @param subCode 
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean delMessageSubscribe(String subCode) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

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
	@Override
	public PageBean getMsgSubscribesList(MessageSubscribeModel subModel) throws BusinessException {
		redisUtil.delMsgNumber(subModel.getUserCode(),6);
		
		PageBean pageBean=new PageBean();
		subModel.setPageNo(subModel.getPageNo()*subModel.getPageSize());
		
		int total=userMsgSubscribeMapper.selectCount(subModel);
		List<UserMsgSubscribe> list=userMsgSubscribeMapper.selectPage(subModel);
		pageBean.setTotalCount(total);
		pageBean.setRecordList(list);
		
		return pageBean;
	}

	
	
	
	
}
