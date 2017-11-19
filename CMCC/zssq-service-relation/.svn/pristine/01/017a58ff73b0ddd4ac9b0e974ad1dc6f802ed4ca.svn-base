package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.RelationRecommend;
import com.zssq.model.RelationQualityModel;

/**
 * 
 * @ClassName: RelationRecommendMapper  
 * @Description: 推荐  
 * @author ZKZ  
 * @date 2017年4月20日  
 *
 */
public interface RelationRecommendMapper {

	/**
	 * 
	 * @Title: selectPortalRecCount  
	 * @Description: 查询推荐列表个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectPortalRecCount(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectPortalRecList  
	 * @Description: 查询推荐列表
	 * @param paramMap
	 * @return: List<RelationQualityModel>    返回类型
	 */
	List<RelationQualityModel> selectPortalRecList(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: getSameRec  
	 * @Description: 获取已被推荐到相同门户的相同内容信息
	 * @param paramMap
	 * @return: RelationRecommend    返回类型
	 */
	RelationRecommend getSameRec(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: insert  
	 * @Description: 插入
	 * @param relationRecommend
	 * @return: int    返回类型
	 */
    int insert(RelationRecommend relationRecommend);
    
    /**
	 * 
	 * @Title: getRecStatus  
	 * @Description: 查询是否被推荐过
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int getRecStatus(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: updateStatus  
	 * @Description: 修改推荐状态
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int updateStatus(Map<String, Object> paramMap);

}