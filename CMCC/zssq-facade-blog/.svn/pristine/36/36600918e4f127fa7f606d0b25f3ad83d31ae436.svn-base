package com.zssq.service;

import com.zssq.dao.pojo.BlogSubscribe;
import com.zssq.exceptions.BusinessException;
import com.zssq.vo.BlogSubVO;

/**
 * 
 * @ClassName: BlogSubService  
 * @Description: 订阅  
 * @author ZKZ  
 * @date 2017年3月25日  
 *
 */
public interface BlogSubService {
	
	/**
	 * 
	 * @Title: getSubStatus  
	 * @Description: 查询订阅状态
	 * @param blogSubVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean getSubStatus(BlogSubVO blogSubVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveSubscribe  
	 * @Description: 保存订阅信息
	 * @param blogSubscribe 订阅信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveSubscribe(BlogSubscribe blogSubscribe) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteSubscribe  
	 * @Description: 取消订阅
	 * @param blogSubVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteSubscribe(BlogSubVO blogSubVO) throws BusinessException;

}
