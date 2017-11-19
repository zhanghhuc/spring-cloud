package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.RelationSubjectInfo;

public interface RelationSubjectInfoBySryMapper {
    
    /**
     * 
     * @Title: insert  
     * @Description: 保存内容信息 use
     * @param record
     * @return    参数  
     * @return: int    返回类型
     */
    int insert(RelationSubjectInfo record);
    /**
     * 
     * @Title: update  
     * @Description: 更新内容
     * @param paramMap
     * @return    参数  
     * @return: int    返回类型
     */
	int update(Map<String, Object> paramMap);
	/**
	 * 
	 * @Title: delete  
	 * @Description: 标记删除 内容
	 * @param paramMap
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int delete(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: deleteSource  
	 * @Description: 标记 删除 来源
	 * @param paramMap
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int deleteSource(Map<String, Object> paramMap);
	/**
	 * 
	 * @Title: updateShieldStatus  
	 * @Description: 屏蔽/恢复内容
	 * @param paramMap
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int updateShieldStatus(Map<String, Object> paramMap);
	/**
	 * 
	 * @Title: updateShieldStatusSource  
	 * @Description: 屏蔽/恢复 来源为 subjectCode 的
	 * @param paramMap    参数  
	 * @return: void    返回类型
	 */
	void updateShieldStatusSource(Map<String, Object> paramMap);

}