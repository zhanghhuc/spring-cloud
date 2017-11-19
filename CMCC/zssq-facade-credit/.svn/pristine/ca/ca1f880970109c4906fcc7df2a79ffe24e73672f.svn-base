package com.zssq.service;

import com.zssq.dao.pojo.ExpAccount;
import com.zssq.dao.pojo.UserLevelConfig;
import com.zssq.exceptions.BusinessException;

public interface IExpAccountService {

	/**
	 * 
	 * @Title: selectByAccountCode  
	 * @Description: 根据账户编号获取账户信息
	 * @param @param accountCode 账户编号
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return ExpAccount    经验值账户实体  
	 * @throws
	 */
	public ExpAccount selectByAccountCode(String accountCode) throws BusinessException;

	/**
	 * 
	 * @Title: insert  
	 * @Description: 添加经验值账户
	 * @param @param expAccount 经验值账户实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return boolean    true-成功，false-失败  
	 * @throws
	 */
	public boolean insert(ExpAccount expAccount) throws BusinessException;

	/**
	 * 
	 * @Title: updateByCode  
	 * @Description: 修改经验值账户
	 * @param @param expAccount 经验值账户实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return boolean    true-成功，false-失败  
	 * @throws
	 */
	public boolean updateByCode(ExpAccount expAccount) throws BusinessException;

	/**
	 * 
	 * @Title: selectMultiByCode  
	 * @Description: 根据账户编号获取用户的等级、头衔
	 * @param @param accountCode 账户编号
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return UserLevelConfig    用户经验值与等级等信息的实体  
	 * @throws
	 */
	public UserLevelConfig selectMultiByCode(String accountCode) throws BusinessException;

}
