package com.zssq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.UserMsgNoticeMapper;
import com.zssq.dao.pojo.UserMsgNotice;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageNoticeListModel;
import com.zssq.service.MessageSystemNoticeService;
import com.zssq.util.RedisUtil;
import com.zssq.utils.PageBean;

@Service("messageSystemNoticeSerice")
public class MessageSystemNoticeSericeImpl implements MessageSystemNoticeService{

	
	@Autowired
	private UserMsgNoticeMapper userMsgNoticeMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 
	 * @Title: getNoticeList  
	 * @Description: 获取系统通知
	 * @param mdel
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	
	@Override
	public PageBean getNoticeList(MessageNoticeListModel mdel) throws BusinessException{
		redisUtil.delMsgNumber(mdel.getUserCode(),8);
		
		PageBean pageBean=new PageBean();
		mdel.setPageNo(mdel.getPageNo()*mdel.getPageSize());
		
		int total=userMsgNoticeMapper.selectCount(mdel);
		List<UserMsgNotice> list=userMsgNoticeMapper.selectPage(mdel);
		pageBean.setTotalCount(total);
		pageBean.setRecordList(list);
		
		return pageBean;
	}


	
	/**
	 * 
	 * @Title: getNoticeInfo  
	 * @Description: 获取系统消息
	 * @param msgCode
	 * @return    参数  
	 * @return: UserMsgNotice    返回类型
	 */
	
	@Override
	public UserMsgNotice getNoticeInfo(String msgCode) throws BusinessException {
		
		return userMsgNoticeMapper.getNoticeInfo(msgCode);	
	}



	/**
	 * 
	 * @Title: delNotice  
	 * @Description: 删除消息通知
	 * @param msgCode
	 * @return
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	
	@Override
	public boolean delNotice(String msgCode) throws BusinessException {
		int i=userMsgNoticeMapper.delNotice(msgCode);
		return i==1;
	}

	
	
	/**
	 * 
	 * @Title: saveNoticeList  
	 * @Description: 批量添加系统消息
	 * @param list    参数  
	 * @return: void    返回类型
	 */
	
	@Override
	public void saveNoticeList(List<UserMsgNotice> list) {
		
		if(list==null||list.size()==0){
			return;
		}
		//添加消息数量
		for (int i = 0; i < list.size(); i++) {
			redisUtil.addNumber(list.get(i).getUserCode(),8,list.size());
		}
		
		userMsgNoticeMapper.saveNoticeList(list);

	}
	

}