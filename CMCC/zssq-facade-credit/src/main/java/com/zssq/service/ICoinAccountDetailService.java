package com.zssq.service;

import com.zssq.dao.pojo.CoinAccountDetail;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 
 * @ClassName: ICoinAccountDetailService  
 * @Description: 金币明细相关的服务  
 * @author power  
 * @date 2017年4月19日  
 *
 */
public interface ICoinAccountDetailService {
	
	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 根据账户编号、按照创建时间倒序分页查询金币明细
	 * @param @param pageParam 分页参数
	 * @param @param record 金币明细查询实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return PageBean    分页查询结果实体  
	 * @throws
	 */
	public PageBean selectPage(PageParam pageParam,CoinAccountDetail record) throws BusinessException;
	
	/**
	 * 
	 * @Title: insert  
	 * @Description: 添加金币明细
	 * @param @param coinAccountDetail 金币明细实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return boolean    true-成功，false-失败 
	 * @throws
	 */
	public boolean insert(CoinAccountDetail coinAccountDetail) throws BusinessException;
	
	/**
	 * 
	 * @Title: selectBalance  
	 * @Description: 查询用户金币明细的当前余额
	 * @param @param coinAccountDetail 金币明细实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return Integer    当前金币余额  
	 * @throws
	 */
	public Integer selectBalance(CoinAccountDetail coinAccountDetail) throws BusinessException;

}
