package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RelationQuality;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.RelationQualityModel;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.RelationQualityVO;
import com.zssq.vo.RelationRecVO;

/**
 * 
 * @ClassName: RelationQualityService  
 * @Description: 精华  
 * @author ZKZ  
 * @date 2017年3月16日  
 *
 */
public interface RelationQualityService {
	
	/**
	 * 
	 * @Title: getUserQualityPageBean  
	 * @Description: 查询个人精华列表
	 * @param pageParam
	 * @param relationQualityVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getUserQualityPageBean(PageParam pageParam, RelationQualityVO relationQualityVO) throws BusinessException;

	/**
	 * 
	 * @Title: getTeamQualityList  
	 * @Description: 查询班组精华列表
	 * @param pageParam 分页参数
	 * @param relationQualityVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getTeamQualityList(PageParam pageParam, RelationQualityVO relationQualityVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getPortalQualityList  
	 * @Description: 查询门户精华列表
	 * @param pageParam 分页参数
	 * @param relationQualityVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getPortalQualityList(PageParam pageParam, RelationQualityVO relationQualityVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getPortalFrontQualityList  
	 * @Description: 查询门户首页展示精华列表
	 * @param relationQualityVO
	 * @throws BusinessException    参数  
	 * @return: List<RelationQualityModel>    返回类型
	 */
	public List<RelationQualityModel> getPortalFrontQualityList(RelationQualityVO relationQualityVO) throws BusinessException;
	
	/**
	 * @Function isQuality
	 * @Description 判断是否已被置精
	 * @param quality
	 * @return
	 */
	/*public boolean getQualityStatus(RelationQualityVO relationQualityVO) throws BusinessException;*/	
	
	/**
	 * 
	 * @Title: getQualityInfo  
	 * @Description: 获取精华信息
	 * @param relationQualityVO
	 * @throws BusinessException    参数  
	 * @return: RelationQuality    返回类型
	 */
	public RelationQuality getQualityInfo(RelationQualityVO relationQualityVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveQuality  
	 * @Description: 置精（门户）
	 * @param relationRecVO
	 * @param relationQuality
	 * @param relationDynamic
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveQuality(RelationRecVO relationRecVO, RelationQuality relationQuality,
			RelationDynamic relationDynamic) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteQuality  
	 * @Description: 删除精华信息
	 * @param relationQualityVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteQuality(RelationQualityVO relationQualityVO) throws BusinessException;

	/**
	 * 
	 * @Title: updateQualityTop  
	 * @Description: 修改精华置顶状态
	 * @param relationQualityVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateQualityTop(RelationQualityVO relationQualityVO) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: updateQualitySeq  
	 * @Description: 精华排序
	 * @param relationQualityVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateQualitySeq(RelationQualityVO relationQualityVO) throws BusinessException;
	
}
