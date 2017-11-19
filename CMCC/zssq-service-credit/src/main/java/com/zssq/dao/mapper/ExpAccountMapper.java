package com.zssq.dao.mapper;

import com.zssq.dao.pojo.ExpAccount;
import com.zssq.dao.pojo.UserLevelConfig;

/**
 * 
 * @ClassName: ExpAccountMapper  
 * @Description: 操作经验值账户表  exp_account  
 * @author power  
 * @date 2017年4月28日  
 *
 */
public interface ExpAccountMapper {
	
	/**
	 * 
	 * @Title: selectByAccountCode  
	 * @Description: 根据账户编号查询经验值账户
	 * @param @param accountCode 账户编号
	 * @param @return    参数  
	 * @return ExpAccount   经验值账户实体  
	 * @throws
	 */
	ExpAccount selectByAccountCode(String accountCode);
	
	/**
	 * 
	 * @Title: insert  
	 * @Description: 新增经验值账户
	 * @param @param expAccount 经验值账户实体
	 * @param @return    参数  
	 * @return int    操作结果  
	 * @throws
	 */
	int insert(ExpAccount expAccount);

	/**
	 * 
	 * @Title: updateByAccountCode  
	 * @Description: 修改经验值账户
	 * @param @param expAccount 经验值账户实体
	 * @param @return    参数  
	 * @return int    操作结果  
	 * @throws
	 */
	int updateByAccountCode(ExpAccount expAccount);

	/**
	 * 
	 * @Title: selectMultiByCode  
	 * @Description: 关联经验值账户表与经验值等级配置表，查询用户等级、经验值等信息
	 * @param @param accountCode 账号编号
	 * @param @return    参数  
	 * @return UserLevelConfig    经验值等级配置实体 
	 * @throws
	 */
	UserLevelConfig selectMultiByCode(String accountCode);

}
