package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.IntegralAccountDetail;

/**
 * 
 * @ClassName: IntegralAccountDetailMapper  
 * @Description: 操作积分明细表  integral_account_detail  
 * @author power  
 * @date 2017年4月28日  
 *
 */
public interface IntegralAccountDetailMapper {
	
	/**
	 * 
	 * @Title: insert  
	 * @Description: 新增积分明细
	 * @param @param integralAccountDetail 积分明细实体
	 * @param @return    参数  
	 * @return int    操作结果  
	 * @throws
	 */
	int insert(IntegralAccountDetail integralAccountDetail);
	
	/**
	 * 
	 * @Title: selectBalance  
	 * @Description: 查询当前积分余额
	 * @param @param integralAccountDetail 积分明细实体
	 * @param @return    参数  
	 * @return Integer    积分余额  
	 * @throws
	 */
	Integer selectBalance(IntegralAccountDetail integralAccountDetail);
	
	/**
	 * 
	 * @Title: selectTodayCount  
	 * @Description: 查询今日已奖励次数
	 * @param @param map 封装start, end, accountCode, actionCode
	 * @param @return    参数  
	 * @return Integer    今日已奖励次数  
	 * @throws
	 */
	int selectTodayCount(Map<String,Object> map);
	
	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 分页查询积分明细列表
	 * @param @param record 积分明细查询实体
	 * @param @return    参数  
	 * @return List<IntegralAccountDetail>    积分明细列表
	 * @throws
	 */
	List<IntegralAccountDetail> selectPage(IntegralAccountDetail record);
	
	/**
	 * 
	 * @Title: selectPageCount  
	 * @Description: 查询积分明细列表的总记录数
	 * @param @param record 积分明细查询实体
	 * @param @return    参数  
	 * @return int    总记录数  
	 * @throws
	 */
	int selectPageCount(IntegralAccountDetail record);
	
	/**
	 * 
	 * @Title: selectPortraitCount  
	 * @Description: 查询用户上传头像所获得奖励的次数
	 * @param @param map 封装actionCode，accountCode
	 * @param @return    参数  
	 * @return int    返回类型  
	 * @throws
	 */
	int selectPortraitCount(Map<String,Object> map);
	
}
