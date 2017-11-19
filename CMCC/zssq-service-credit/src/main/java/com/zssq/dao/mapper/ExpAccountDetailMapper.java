package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.ExpAccountDetail;

/**
 * 
 * @ClassName: ExpAccountDetailMapper  
 * @Description: 操作经验值明细表  exp_account_detail  
 * @author power  
 * @date 2017年4月28日  
 *
 */
public interface ExpAccountDetailMapper {

	/**
	 * 
	 * @Title: insert  
	 * @Description: 新增经验值明细
	 * @param @param expAccountDetail 经验值明细实体
	 * @param @return    参数  
	 * @return int    操作结果  
	 * @throws
	 */
	int insert(ExpAccountDetail expAccountDetail);

	/**
	 * 
	 * @Title: selectBalance  
	 * @Description: 查询当前经验值余额
	 * @param @param expAccountDetail 经验值账户实体
	 * @param @return    参数  
	 * @return Integer    当前经验值余额  
	 * @throws
	 */
	Integer selectBalance(ExpAccountDetail expAccountDetail);

	/**
	 * 
	 * @Title: selectTodayCount  
	 * @Description: 查询今日已奖励次数
	 * @param @param map 封装start, end, accountCode
	 * @param @return    参数  
	 * @return Integer    今日已奖励次数
	 * @throws
	 */
	Integer selectTodayCount(Map<String, Object> map);

	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 分页查询经验值明细列表
	 * @param @param record 经验值明细查询实体
	 * @param @return    参数  
	 * @return List<ExpAccountDetail>    经验值明细列表
	 * @throws
	 */
	List<ExpAccountDetail> selectPage(ExpAccountDetail record);

	/**
	 * 
	 * @Title: selectPageCount  
	 * @Description: 查询经验值明细列表总记录数
	 * @param @param record 经验值明细查询实体
	 * @param @return    参数  
	 * @return int    总记录数  
	 * @throws
	 */
	int selectPageCount(ExpAccountDetail record);

}
