package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.RelationHot;
import com.zssq.model.RelationHotListModel;
import com.zssq.model.RelationPortalFrontHotModel;
import com.zssq.model.RelationPortalHotModel;
import com.zssq.model.RelationTeamHideHotModel;

public interface RelationHotMapper {
	/**
	 * 
	 * @Title: insert  
	 * @Description: 插入热点
	 * @param record
	 * @return    参数  
	 * @return: int    返回类型
	 */
    int insert(RelationHot record);
    
    /**
	 * 
	 * @Title: getPortalAllHotCount  
	 * @Description: 查询门户所有热点个数---管理端
	 * @param paramsMap
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int selectAllHotCountByGL(Map<String,Object> paramsMap);
	
	/**
	 * 
	 * @Title: getPortalAllHotCount  
	 * @Description: 查询门户展示热点个数---管理端
	 * @param paramsMap
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int selectShowCountByGL(Map<String,Object> paramsMap);
	
	/**
	 * 
	 * @Title: getPortalAllHotCount  
	 * @Description: 查询门户隐藏热点个数---管理端
	 * @param paramsMap
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int selectHideCountByGL(Map<String,Object> paramsMap); 
	
	/**
	 * 
	 * @Title: getPortalAllHotCount  
	 * @Description: 查询门户全部热点列表---管理端
	 * @param paramsMap
	 * @return    参数  
	 * @return: int    返回类型
	 */
	List<RelationHotListModel> getAllHotListByGL(Map<String,Object> paramsMap); 
	
	/**
	 * 
	 * @Title: getPortalAllHotCount  
	 * @Description: 查询门户展示热点列表---管理端
	 * @param paramsMap
	 * @return    参数  
	 * @return: int    返回类型
	 */
	List<RelationHotListModel> getShowHotListByGL(Map<String,Object> paramsMap); 
	
	/**
	 * 
	 * @Title: getPortalAllHotCount  
	 * @Description: 查询门户隐藏热点列表---管理端
	 * @param paramsMap
	 * @return    参数  
	 * @return: int    返回类型
	 */
	List<RelationHotListModel> getHideHotListByGL(Map<String,Object> paramsMap);
	
	/**********************************门户 start**************************************/
	/**
	 * 
	 * @Title: getTeamShowHotCountByMH  
	 * @Description: 获取班组展示数量
	 * @param map
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int getTeamShowHotCountByMH(Map<String, Object> map);
	/**
	 * 
	 * @Title: listTeamShowHotByMH  
	 * @Description:  获取班组展示列表
	 * @param map
	 * @return    参数  
	 * @return: List<RelationTeamHideHotModel>    返回类型
	 */
	List<RelationTeamHideHotModel> listTeamShowHotByMH(Map<String, Object> map);
	/**
	 * 
	 * @Title: getTeamHideHotCountByMH  
	 * @Description:  获取班组隐藏数量
	 * @param map
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int getTeamHideHotCountByMH(Map<String, Object> map);
	/**
	 * 
	 * @Title: listTeamHideHotByMH  
	 * @Description:  获取班组隐藏列表
	 * @param map
	 * @return    参数  
	 * @return: List<RelationTeamHideHotModel>    返回类型
	 */
	List<RelationTeamHideHotModel> listTeamHideHotByMH(Map<String, Object> map);
	/**
	 * 
	 * @Title: getPortalHotList  
	 * @Description: 查询门户展示中热点列表
	 * @param map
	 * @return    参数  
	 * @return: List<RelationPortalHotModel>    返回类型
	 */
	List<RelationPortalHotModel> getPortalHotList(Map<String, Object> map);
	/**
	 * 
	 * @Title: getPortalFrontHotList  
	 * @Description: 查询门户首页展示热点列表
	 * @param map
	 * @return    参数  
	 * @return: List<RelationPortalFrontHotModel>    返回类型
	 */
	List<RelationPortalFrontHotModel> getPortalFrontHotList(Map<String, Object> map);
	/**
	 * 
	 * @Title: getHotByCode  
	 * @Description: 通过热点code 获取热点对象
	 * @param map
	 * @return    参数  
	 * @return: RelationHot    返回类型
	 */
	RelationHot getHotByCode(Map<String, Object> map);
	
	/**********************************门户 end**************************************/
	/**
	 * 
	 * @Title: deleteHotDataSevenDay  
	 * @Description: 删除 7天前热点
	 * @param beforeSevenDayTime
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int deleteHotDataSevenDay(Long beforeSevenDayTime);

	int selectCountByJob(Map<String, Object> map);

	List<RelationHot> selectHotByJob(Map<String, Object> map);

	int updateHotWeight(List<RelationHot> rhList);

	

}