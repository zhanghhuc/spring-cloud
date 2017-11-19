package com.zssq.service;

import com.zssq.dao.pojo.IntegralAccountDetail;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 
 * @ClassName: IIntegralAccountDetailService  
 * @Description: 积分明细相关的服务  
 * @author power  
 * @date 2017年4月12日  
 *
 */
public interface IIntegralAccountDetailService {

	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 根据积分账户编号、按照创建时间倒序分页查询积分明细
	 * @param @param pageParam 分页参数
	 * @param @param record 积分明细查询实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return PageBean    分页查询结果实体
	 * @throws
	 */
	public PageBean selectPage(PageParam pageParam,IntegralAccountDetail record) throws BusinessException;
	
	/**
	 * 
	 * @Title: insert  
	 * @Description: 添加积分明细
	 * @param @param IntegralAccountDetail 积分明细实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return boolean    true-成功，false-失败  
	 * @throws
	 */
	public boolean insert(IntegralAccountDetail integralAccountDetail) throws BusinessException;
	
}
