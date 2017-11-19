package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.RelationQuality;
import com.zssq.model.RelationQualityModel;

/**
 * 
 * @ClassName: RelationQualityMapper  
 * @Description: 精华  
 * @author ZKZ  
 * @date 2017年4月19日  
 *
 */
public interface RelationQualityMapper {
	
	/**
	 * 
	 * @Title: selectUserQualityCount  
	 * @Description: 查询个人精华列表个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectUserQualityCount(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectUserQualityList  
	 * @Description: 查询个人精华列表
	 * @param paramMap
	 * @return: List<RelationQualityModel>    返回类型
	 */
	List<RelationQualityModel> selectUserQualityList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectTeamQualityCount  
	 * @Description: 查询班组精华列表个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectTeamQualityCount(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectTeamQualityList  
	 * @Description: 查询班组精华列表
	 * @param paramMap
	 * @return: List<RelationQualityModel>    返回类型
	 */
	List<RelationQualityModel> selectTeamQualityList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectPortalQualityCount  
	 * @Description: 查询门户精华列表个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectPortalQualityCount(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectPortalQualityList  
	 * @Description: 查询门户精华列表
	 * @param paramMap
	 * @return: List<RelationQualityModel>    返回类型
	 */
	List<RelationQualityModel> selectPortalQualityList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectPortalFrontQualityList  
	 * @Description: 查询门户首页展示精华列表
	 * @param paramMap
	 * @return: List<RelationQualityModel>    返回类型
	 */
	List<RelationQualityModel> selectPortalFrontQualityList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: getQualityStatus  
	 * @Description: 判断是否已被置精
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int getQualityStatus(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: getQualityInfo  
	 * @Description: 获取精华信息
	 * @param paramMap
	 * @return: RelationQuality    返回类型
	 */
	RelationQuality getQualityInfo(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: insert  
	 * @Description: 保存精华信息
	 * @param relationQuality
	 * @return: int    返回类型
	 */
    int insert(RelationQuality relationQuality);

    /**
     * 
     * @Title: deleteTeamQuality  
     * @Description: 删除班组精华信息
     * @param qualityMap
     * @return: int    返回类型
     */
	int deleteTeamQuality(Map<String, Object> qualityMap);

	/**
	 * 
	 * @Title: deletePortalQuality  
	 * @Description: 删除门户精华信息
	 * @param qualityMap
	 * @return: int    返回类型
	 */
	int deletePortalQuality(Map<String, Object> qualityMap);

	/**
	 * 
	 * @Title: updateMaxSortNum  
	 * @Description: 修改精华排序为最大值
	 * @param qualityMap
	 * @return: int    返回类型
	 */
	int updateMaxSortNum(Map<String, Object> qualityMap);

	/**
	 * 
	 * @Title: updateQualityTop  
	 * @Description: 精华置顶/取消置顶
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int updateQualityTop(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: getAffectedQuality  
	 * @Description: 获取排序受影响精华信息
	 * @param paramMap
	 * @return: RelationQuality    返回类型
	 */
	RelationQuality getAffectedQuality(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: updateQualitySeq  
	 * @Description: 修改排序值
	 * @param originalMap
	 * @return: int    返回类型
	 */
	int updateQualitySeq(Map<String, Object> originalMap);

}