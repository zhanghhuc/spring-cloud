package com.zssq.dao.mapper;

import java.util.Map;

public interface RelationHotHideMapper {
	
	/**
	 * 
	 * @Title: selectByMap  
	 * @Description: 查询隐藏数量
	 * @param map
	 * @return    参数  
	 * @return: int   返回类型
	 */
	int selectCountByMap(Map<String, Object> map);
	/**
	 * 
	 * @Title: getTeamHideHotCount  
	 * @Description: 通过teamCode,获取班组隐藏热点个数
	 * @param paramsMap
	 * @return    参数  
	 * @return: int    返回类型
	 *//*
    int getTeamHideHotCount(Map<String, Object> paramsMap);
    
    *//**
     * 
     * @Title: getTeamHideHotList  
     * @Description: 通过teamCode,获取班组隐藏热点列表
     * @param paramsMap
     * @return    参数  
     * @return: List<RelationTeamHideHotModel>    返回类型
     *//*
    List<RelationTeamHideHotModel> getTeamHideHotList(Map<String, Object> paramsMap);*/
	/**
	 * 
	 * @Title: insertHideHot  
	 * @Description: 插入隐藏
	 * @param map
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int insertHideHot(Map<String, Object> map);
	/**
	 * 
	 * @Title: deleteHideHot  
	 * @Description: 删除隐藏记录
	 * @param map
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int deleteHideHot(Map<String, Object> map);
	
	/**
	 * 
	 * @Title: deleteHideHotByHotSevenDay  
	 * @Description: 删除7天前 隐藏记录
	 * @param time
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int deleteHideHotByHotSevenDay(Long time);
	/**
	 * 
	 * @Title: deleteHideHotSevenDay  
	 * @Description: 删除7天前 隐藏记录
	 * @param time
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int deleteHideHotSevenDay(Long time);
}