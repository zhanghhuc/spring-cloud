package com.zssq.service;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.RelationHot;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.RelationPortalFrontHotModel;
import com.zssq.model.RelationPortalHotModel;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.RelationHotVO;

/**
 * 
 * @ClassName: RelationHotService  
 * @Description: 热点  
 * @author ZKZ  
 * @date 2017年3月16日  
 *
 */
public interface RelationHotService {
	
	/**
	 * 
	 * @Title: getGLPortalHotList  
	 * @Description: 查询门户热点列表-管理端
	 * @param pageParam 分页参数
	 * @param relationHotVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getGLPortalHotList(PageParam pageParam, RelationHotVO relationHotVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getMHPortalHotList  
	 * @Description: 查询门户展示中热点列表-门户端
	 * @param relationHotVO
	 * @throws BusinessException    参数  
	 * @return: List<RelationHotModel>    返回类型
	 */
	public List<RelationPortalHotModel> getMHPortalHotList(RelationHotVO relationHotVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getMHPortalFrontHotList  
	 * @Description: 查询门户首页展示热点列表-门户端
	 * @param relationHotVO
	 * @throws BusinessException    参数  
	 * @return: List<Map<String,Object>>    返回类型
	 */
	public List<RelationPortalFrontHotModel> getMHPortalFrontHotList(RelationHotVO relationHotVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getTeamHotList  
	 * @Description: 查询班组热点列表
	 * @param pageParam 分页参数
	 * @param relationHotVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getTeamHotList(PageParam pageParam, RelationHotVO relationHotVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateHotStatus  
	 * @Description: 隐藏/显示班组热点
	 * @param relationHotVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateTeamHotStatus(RelationHotVO relationHotVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: updatePortalHotStatus  
	 * @Description: 隐藏/显示门户热点
	 * @param relationHotVO
	 * @return
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updatePortalHotStatusByGL(RelationHotVO relationHotVO) throws BusinessException;
	
	/***********************热点定时任务*******************************/
	/**
	 * 
	 * @Title: deleteBeforeSevenDayHotData  
	 * @Description: 删除7天前热点数据
	 * @param beforeSevenDayTime    参数  
	 * @return: void    返回类型
	 */
	public boolean deleteBeforeSevenDayHotData(Long beforeSevenDayTime);
	/**
	 * 
	 * @Title: selectCountByJob  
	 * @Description: 获取共几条记录
	 * @param map
	 * @return    参数  
	 * @return: int    返回类型
	 */
	public int selectCountByJob(Map<String, Object> map);
	
	/**
	 * 
	 * @Title: selectHotByJob  
	 * @Description: 批量获取 热度
	 * @param map
	 * @return    参数  
	 * @return: List<RelationHot>    返回类型
	 */
	public List<RelationHot> selectHotByJob(Map<String, Object> map);
	/**
	 * 
	 * @Title: updateHotWeight  
	 * @Description: 批量修改热度
	 * @param rhList
	 * @return    参数  
	 * @return: int    返回类型
	 */
	public int updateHotWeight(List<RelationHot> rhList);
	
}
