package com.zssq.service;

import java.util.List;

import com.mdcl.smap.bean.UserInfo;
import com.zssq.exceptions.BusinessException;

/**
 * SMAP 同步业务组件，用以同步用户及组织机构数据
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
public interface ISmapService {

	/**
	 * SMAP 同步组织信息
	 * 
	 * @throws BusinessException
	 */
	public void syncOrgs() throws BusinessException;
	
	/**
	 * SMAP 同步用户信息
	 * 
	 * @throws BusinessException
	 */	
	public List<UserInfo> syncUsers() throws BusinessException;
}
