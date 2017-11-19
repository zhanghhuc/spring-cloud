package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.RelationDynamicTeamRel;

/**
 * 
 * @ClassName: RelationDynamicTeamRelMapper  
 * @Description: 动态班组关系  
 * @author ZKZ  
 * @date 2017年4月17日  
 *
 */
public interface RelationDynamicTeamRelMapper {

    /**
     * 
     * @Title: insert  
     * @Description: 保存动态班组关系信息
     * @param relationDynamicTeamRel
     * @return: int    返回类型
     */
    int insert(RelationDynamicTeamRel relationDynamicTeamRel);
    
    /**
     * 
     * @Title: setTeamExc  
     * @Description: 班组设为百强班组
     * @param execMap
     * @return: int    返回类型
     */
    int setTeamExc(Map<String, Object> execMap);

    /**
     * 
     * @Title: removeTeamExc  
     * @Description: 班组取消百强班组
     * @param notExecMap
     * @return: int    返回类型
     */
	int removeTeamExc(Map<String, Object> notExecMap);
	
	/**
	 * 
	 * @Title: updateTeamNoOne  
	 * @Description: 班组设为一号班组
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int updateTeamNoOne(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: updateTeamStatus  
	 * @Description: 解散班组
	 * @param paramMap    参数  
	 * @return: int    返回类型
	 */
    int updateTeamStatus(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: getDynamicTeamRel  
     * @Description: 查询是否存在动态班组关系信息
     * @param paramMap
     * @return: RelationDynamicTeamRel    返回类型
     */
    RelationDynamicTeamRel getDynamicTeamRel(Map<String, Object> paramMap);

    /**
     * 
     * @Title: updateTeamFrontStatus  
     * @Description: 更改首页展示状态
     * @param paramMap
     * @return: int    返回类型
     */
	int updateTeamFrontStatus(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: updateTeamQulityStatus  
	 * @Description: 更改置精状态
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int updateTeamQulityStatus(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: updateTeamRecStatus  
	 * @Description: 更改推荐状态
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int updateTeamRecStatus(Map<String, Object> paramMap);

}