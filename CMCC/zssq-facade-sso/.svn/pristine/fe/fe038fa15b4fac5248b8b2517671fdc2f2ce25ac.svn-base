package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.SSO;
import com.zssq.exceptions.BusinessException;

public interface ISSOService {
	
	/**
	 * 登录认证/延时登录
	 * @param uid 员工账户
	 * @param uidPassword 员工密码
	 * @param app 应用类型
	 * @return
	 */
	public String certification(String uid,String uidPassword,String app) throws BusinessException;  
	
	/**
	 * 登录签退
	 * @param uid 员工账户
	 * @param app 应用类型
	 * @param ticket 票据
	 * @return
	 */
//	public boolean logout(String uid,String app,String ticket) throws BusinessException;  
	
	/**
	 * 获取用户登录登出详情，用于计算在线时长积分
	 * @return
	 * @throws BusinessException
	 */
//	public List<SSO> updateInfo() throws BusinessException; 
	
	/**
	 * 移动定时认证
	 * @param uid 员工账户
	 * @param app 应用类型
	 * @param uidPassword 员工密码
	 * @param key redis的key
	 * @return
	 * @throws BusinessException
	 */
//	public boolean updateCertification(String uid,String app,String uidPassword,String key) throws BusinessException; 
}
