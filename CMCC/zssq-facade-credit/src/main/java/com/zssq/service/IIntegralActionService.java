package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.IntegralAction;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 
 * @ClassName: IIntegralActionService  
 * @Description: 积分行为相关的服务  
 * @author power  
 * @date 2017年4月11日  
 *
 */
public interface IIntegralActionService {

	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 根据积分行为类型分页查询积分行为表integral_action
	 * @param @param pageParam 分页参数
	 * @param @param record 积分行为实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return PageBean    分页查询结果实体  
	 * @throws
	 */
	public PageBean selectPage(PageParam pageParam,IntegralAction record) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateByActionCode  
	 * @Description: 根据积分行为编号修改积分行为表integral_action
	 * @param @param record 积分行为实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return boolean    true-成功，false-失败  
	 * @throws
	 */
	public boolean updateByActionCode(IntegralAction record) throws BusinessException;
	
	/**
	 * 
	 * @Title: selectByActionCode  
	 * @Description: 根据积分行为编号获取积分行为
	 * @param @param actionCode 积分行为编号
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return IntegralAction    积分行为实体
	 * @throws
	 */
	public IntegralAction selectByActionCode(String actionCode) throws BusinessException;
	
	/**
	 * 
	 * @Title: selectAll  
	 * @Description: 根据积分行为类型查询积分行为列表
	 * @param @param actionType 积分行为类型
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return List<IntegralAction>    积分行为列表
	 * @throws
	 */
	public List<IntegralAction> selectAll(Byte actionType) throws BusinessException;
	
	/**
	 * 
	 * @Title: selectByIntegralPlus  
	 * @Description: 根据积分行为类型，查询积分值为正值的积分行为列表
	 * @param @param actionType 积分行为类型
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return List<IntegralAction>    积分账户列表
	 * @throws
	 */
	public List<IntegralAction> selectByIntegralPlus(Byte actionType) throws BusinessException;
	
	/**
	 * 
	 * @Title: selectByIntegralMinus  
	 * @Description: 根据积分行为类型，查询积分值为负值和零的积分行为列表
	 * @param @param actionType 积分行为类型
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return List<IntegralAction>    返回类型  
	 * @throws
	 */
	public List<IntegralAction> selectByIntegralMinus(Byte actionType) throws BusinessException;
	
}
