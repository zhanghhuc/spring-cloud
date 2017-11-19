package com.zssq.service;

import com.zssq.dao.pojo.ExpAccountDetail;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 
 * @ClassName: IExpAccountDetailService  
 * @Description: 经验值明细相关的服务  
 * @author power  
 * @date 2017年4月19日  
 *
 */
public interface IExpAccountDetailService {

	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 根据账户编号、按照创建时间倒序分页查询经验值明细
	 * @param @param pageParam 分页参数
	 * @param @param record 经验值明细查询实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return PageBean    分页查询结果实体  
	 * @throws
	 */
	public PageBean selectPage(PageParam pageParam,ExpAccountDetail record) throws BusinessException;
	
	/**
	 * 
	 * @Title: insert  
	 * @Description: 添加经验值明细
	 * @param @param expAccountDetail 经验值明细实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return boolean    true-成功，false-失败
	 * @throws
	 */
	public boolean insert(ExpAccountDetail expAccountDetail) throws BusinessException;
	
}
