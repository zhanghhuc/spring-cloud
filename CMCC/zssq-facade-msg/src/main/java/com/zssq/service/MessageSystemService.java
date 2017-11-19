package com.zssq.service;

import com.zssq.dao.pojo.UserMsgSys;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageAddSystemModel;
import com.zssq.model.MessageSysUserMsgModel;
import com.zssq.model.MessageSystemModel;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: MessageSystemService  
 * @Description: 系统消息  
 * @author YDB  
 * @date 2017年4月5日  
 *
 */
public interface MessageSystemService {
	
	/**
	 * 
	 * @Title: addMessageSystem  
	 * @Description: 添加消息
	 * @param sysModel
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean addMessageSystem(MessageAddSystemModel sysModel) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: getSystemMsgList  
	 * @Description: 获取消息列表
	 * @param sysModel
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getSystemMsgList(MessageSystemModel sysModel) throws BusinessException;
	
	/**
	 * 
	 * @Title: delMessageSystem  
	 * @Description: 删除系统消息
	 * @param msgCode
	 * @param userCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean delMessageSystem(String msgCode,String userCode) throws BusinessException;
	
	
	
	
	
	/**
	 * 
	 * @Title: getPcSystemMsg  
	 * @Description: PC用户端获取系统消息
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean updateAndGetPcSystemMsgList(MessageSysUserMsgModel sysMsg) throws BusinessException;
	

	
	/**
	 * 
	 * @Title: delUserMsg  
	 * @Description: 删除用户系统消息
	 * @param userCode
	 * @param msgCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteUserMsg(String userCode,String msgCode) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: getMsgSysInfo  
	 * @Description: 获取系统消息详细信息
	 * @param msgCode
	 * @return    参数  
	 * @return: UserMsgSys    返回类型
	 */
	public  UserMsgSys getMsgSysInfo(String msgCode) throws BusinessException;
}