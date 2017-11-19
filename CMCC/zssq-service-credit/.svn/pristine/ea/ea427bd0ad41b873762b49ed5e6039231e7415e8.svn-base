package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.IntegralAction;

/**
 * 
 * @ClassName: IntegralActionMapper  
 * @Description: 操作积分行为表  integral_action  
 * @author power  
 * @date 2017年4月28日  
 *
 */
public interface IntegralActionMapper {
	
	/**
	 * 
	 * @Title: selectByActionCode  
	 * @Description: 根据积分行为编号查询积分行为信息
	 * @param @param actionCode 积分行为编号
	 * @param @return    参数  
	 * @return IntegralAction    积分行为实体  
	 * @throws
	 */
	IntegralAction selectByActionCode(String actionCode);
	
	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 分页查询积分行为列表
	 * @param @param record 积分行为查询实体
	 * @param @return    参数  
	 * @return List<IntegralAction>    积分行为列表  
	 * @throws
	 */
	List<IntegralAction> selectPage(IntegralAction record);
	
	/**
	 * 
	 * @Title: selectPageCount  
	 * @Description: 查询积分行为列表的总记录数
	 * @param @param record 积分行为查询实体
	 * @param @return    参数  
	 * @return int    总记录数
	 * @throws
	 */
	int selectPageCount(IntegralAction record);
	
	/**
	 * 
	 * @Title: updateByActionCode  
	 * @Description: 修改积分行为
	 * @param @param record 积分行为实体
	 * @param @return    参数  
	 * @return int    操作结果  
	 * @throws
	 */
	int updateByActionCode(IntegralAction record);
	
	/**
	 * 
	 * @Title: selectAll  
	 * @Description: 根据积分行为类型查询积分行为列表
	 * @param @param actionType 积分行为类型
	 * @param @return    参数  
	 * @return List<IntegralAction>    积分行为列表 
	 * @throws
	 */
	List<IntegralAction> selectAll(Byte actionType);

	/**
	 * 
	 * @Title: selectByIntegralPlus  
	 * @Description: 根据积分行为类型，查询积分值为正值的积分行为列表
	 * @param @param actionType 积分行为类型
	 * @param @return    参数  
	 * @return List<IntegralAction>    积分行为列表  
	 * @throws
	 */
	List<IntegralAction> selectByIntegralPlus(Byte actionType);
	
	/**
	 * 
	 * @Title: selectByIntegralMinus  
	 * @Description: 根据积分行为类型，查询积分值为负值和零的积分行为列表
	 * @param @param actionType 积分行为类型
	 * @param @return    参数  
	 * @return List<IntegralAction>    积分行为列表  
	 * @throws
	 */
	List<IntegralAction> selectByIntegralMinus(Byte actionType);
}
