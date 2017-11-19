package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RelationDynamicTeamRel;
import com.zssq.dao.pojo.RelationQuality;
import com.zssq.dao.pojo.RelationRecommend;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.RelationDynamicVO;

/**
 * 
 * @ClassName: RelationDynamicService  
 * @Description: 动态
 * @author ZKZ  
 * @date 2017年3月16日  
 *
 */
public interface RelationDynamicService {
	
	/**
	 * 
	 * @Title: getUserSpaceDynamicList  
	 * @Description: 查询个人空间动态
	 * @param pageParam
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getUserSpaceDynamicList(PageParam pageParam, RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getUserDynamicList  
	 * @Description: 查询个人主页动态
	 * @param pageParam
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getUserDynamicList(PageParam pageParam, RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getTeamDynamicList  
	 * @Description: 查询班组动态
	 * @param pageParam
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getTeamDynamicList(PageParam pageParam, RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getTeamUserDynamicList  
	 * @Description: 查询班组成员动态
	 * @param pageParam
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getTeamUserDynamicList(PageParam pageParam, RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getPortalUserDynamicList  
	 * @Description: 查询门户成员动态列表
	 * @param pageParam
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getPortalUserDynamicList(PageParam pageParam, RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getPortalTeamDynamicList  
	 * @Description: 查询门户班组动态列表
	 * @param pageParam
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getPortalTeamDynamicList(PageParam pageParam, RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getPortalTopTeamDynamicList  
	 * @Description: 查询门户1号班组动态列表
	 * @param pageParam
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getPortalTopTeamDynamicList(PageParam pageParam, RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getPortalExTeamDynamicList  
	 * @Description: 查询门户百强班组动态列表
	 * @param pageParam
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getPortalExTeamDynamicList(PageParam pageParam, RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getDynamicTeamRel  
	 * @Description: 查询动态班组关系信息
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: RelationDynamicTeamRel    返回类型
	 */
	public RelationDynamicTeamRel getDynamicTeamRel(RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveTeamFrontDynamic  
	 * @Description: 班组首页展示动态
	 * @param relationDynamicTeamRel 动态班组关系信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveTeamFrontDynamic(RelationDynamicTeamRel relationDynamicTeamRel) throws BusinessException;
	
	/**
	 * 
	 * @Title: removeTeamFrontDynamic  
	 * @Description: 班组取消首页展示动态
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean removeTeamFrontDynamic(RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveTeamQulity  
	 * @Description: 班组置精
	 * @param relationDynamicTeamRel 动态班组关系信息
	 * @param relationQuality 精华信息
	 * @param relationDynamic 动态信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveTeamQulity(RelationDynamicTeamRel relationDynamicTeamRel, RelationQuality relationQuality,
			RelationDynamic relationDynamic) throws BusinessException;
	
	/**
	 * 
	 * @Title: removeTeamQulity  
	 * @Description: 班组取消置精
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean removeTeamQulity(RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveTeamRec  
	 * @Description: 班组推荐
	 * @param relationDynamicVO
	 * @param relationRecommend 推荐信息
	 * @param relationDynamic 动态信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveTeamRec(RelationDynamicVO relationDynamicVO, RelationRecommend relationRecommend,
			RelationDynamic relationDynamic) throws BusinessException;

	/**
	 * 
	 * @Title: getDynamicTeamCodes  
	 * @Description: 查询动态被哪些关注的班组首页展示
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: List<String>    返回类型
	 */
	public List<String> getDynamicTeamCodes(RelationDynamicVO relationDynamicVO) throws BusinessException;

}
