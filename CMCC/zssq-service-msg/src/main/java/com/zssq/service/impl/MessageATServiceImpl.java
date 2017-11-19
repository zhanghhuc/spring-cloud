package com.zssq.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.UserMsgATMapper;
import com.zssq.dao.pojo.UserMsgAT;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageATModel;
import com.zssq.service.MessageATService;
import com.zssq.util.RedisUtil;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: MessageATServiceImpl  
 * @Description: 消息-@我  
 * @author YDB  
 * @date 2017年3月21日  
 *
 */
@Service("messageATService")
public class MessageATServiceImpl implements MessageATService ,Serializable{

	    
	/**  
	 * @Fields serialVersionUID : TODO
	 */  
	private static final long serialVersionUID = 1L;

	@Autowired
	private  UserMsgATMapper userMsgATMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	/**
	 * 
	 * @Title: getMyAtList  
	 * @Description: @我的查询 
	 * @param messageAT
	 * @return    参数  
	 * @return: List<UserMsgAT>  返回类型
	 */
	@Override
	public PageBean getMyAtList(MessageATModel messageAT)  throws BusinessException {
		
		//删除消息数量
		redisUtil.delMsgNumber(messageAT.getUserCode(),1);
		
		PageBean pageBean=new PageBean();
		UserMsgAT userMsgAT=new UserMsgAT();
		//查询条件
		userMsgAT.setLimitStart(messageAT.getPageNo()*messageAT.getPageSize());
		userMsgAT.setLimitEnd(messageAT.getPageSize());
		userMsgAT.setUserCode(messageAT.getUserCode());
		//查询
		List<UserMsgAT> list=userMsgATMapper.selectPage(userMsgAT);
		int total=userMsgATMapper.selectCount(userMsgAT);
		//返回值
		pageBean.setTotalCount(total);
		pageBean.setRecordList(list);
		
		return pageBean;
	}


	/**
	 * 
	 * @Title: delMessageAT  
	 * @Description: 删除at
	 * @param atCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean delMessageAT(String atCode) throws BusinessException  {
		int state=userMsgATMapper.delMessageAT(atCode);
		return state==0?false:true;
	}

	
	
}