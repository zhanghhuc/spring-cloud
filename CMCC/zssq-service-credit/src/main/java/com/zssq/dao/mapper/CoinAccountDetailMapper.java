package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.CoinAccountDetail;

/**
 * 
 * @ClassName: CoinAccountDetailMapper  
 * @Description: 操作金币明细表  coin_account_detail
 * @author power  
 * @date 2017年4月28日  
 *
 */
public interface CoinAccountDetailMapper {
	
	/**
	 * 
	 * @Title: insert  
	 * @Description: 新增金币明细
	 * @param @param coinAccountDetail 金币明细实体
	 * @param @return   参数    
	 * @return int    操作结果  
	 * @throws
	 */
	int insert(CoinAccountDetail coinAccountDetail);
	
	/**
	 * 
	 * @Title: selectBalance  
	 * @Description: 查询当前金币余额
	 * @param @param coinAccountDetail 金币明细实体
	 * @param @return    参数  
	 * @return Integer    当前金币余额  
	 * @throws
	 */
	Integer selectBalance(CoinAccountDetail coinAccountDetail);
	
	/**
	 * 
	 * @Title: selectTodayCount  
	 * @Description: 查询今日已奖励次数
	 * @param @param map 封装start, end, accountCode
	 * @param @return    参数  
	 * @return Integer    今日已奖励次数  
	 * @throws
	 */
	Integer selectTodayCount(Map<String,Object> map);
	
	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 分页查询金币明细列表
	 * @param @param record 金币明细查询实体
	 * @param @return    参数  
	 * @return List<CoinAccountDetail>    金币明细列表  
	 * @throws
	 */
	List<CoinAccountDetail> selectPage(CoinAccountDetail record);
	
	/**
	 * 
	 * @Title: selectPageCount  
	 * @Description: 查询金币明细列表的总记录数
	 * @param @param record 金币明细查询实体
	 * @param @return    参数  
	 * @return int    总记录数 
	 * @throws
	 */
	int selectPageCount(CoinAccountDetail record);

}
