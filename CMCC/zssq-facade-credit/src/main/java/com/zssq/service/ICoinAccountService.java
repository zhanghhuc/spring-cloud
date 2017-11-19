package com.zssq.service;

import com.zssq.dao.pojo.CoinAccount;
import com.zssq.exceptions.BusinessException;

public interface ICoinAccountService {
	
	/**
	 * 
	 * @Title: selectByAccountCode  
	 * @Description: 根据账户编号获取账户信息
	 * @param @param accountCode 账户编号
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return CoinAccount    金币账户实体  
	 * @throws
	 */
	public CoinAccount selectByAccountCode(String accountCode) throws BusinessException;
	
	/**
	 * 
	 * @Title: insert  
	 * @Description: 添加金币账户
	 * @param @param coinAccount 金币账户实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return boolean    true-成功，false-失败  
	 * @throws
	 */
	public boolean insert(CoinAccount coinAccount) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateCoinAccount  
	 * @Description: 修改金币账户
	 * @param @param coinAccount 金币账户实体
	 * @param @throws BusinessException    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public boolean updateByCode(CoinAccount coinAccount) throws BusinessException;

}
