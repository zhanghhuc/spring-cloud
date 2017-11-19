package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.RelationSubjectInfo;

/**
 * 
 * @ClassName: RelationSubjectInfoByZKZMapper  
 * @Description: 内容信息  
 * @author ZKZ  
 * @date 2017年4月17日  
 *
 */
public interface RelationSubjectInfoByZKZMapper {

	/**
	 * 
	 * @Title: getSubjectBaseInfo  
	 * @Description: 查询内容基本信息
	 * @param subjectCode
	 * @return: RelationSubjectInfo    返回类型
	 */
	RelationSubjectInfo getSubjectBaseInfo(String subjectCode);
	
    /**
     * 
     * @Title: insert  
     * @Description: 保存内容信息
     * @param relationSubjectInfo
     * @return: int    返回类型
     */
    int insert(RelationSubjectInfo relationSubjectInfo);

    /**
     * 
     * @Title: deleteSubject  
     * @Description: 删除内容
     * @param subjectMap
     * @return: int    返回类型
     */
	int deleteSubject(Map<String, Object> subjectMap);
	
	/**
	 * 
	 * @Title: batchDeleteSubject  
	 * @Description: 批量删除内容
	 * @param subjectMap
	 * @return: int    返回类型
	 */
	int batchDeleteSubject(Map<String, Object> subjectMap);
	
	/**
	 * 
	 * @Title: deleteRepo  
	 * @Description: 删除知识库动态
	 * @param subjectMap
	 * @return: int    返回类型
	 */
	int deleteRepo(Map<String, Object> subjectMap);
	
	/**
	 * 
	 * @Title: deleteSource  
	 * @Description: 删除来源
	 * @param subjectMap
	 * @return: int    返回类型
	 */
	int deleteSource(Map<String, Object> subjectMap);

	/**
	 * 
	 * @Title: shield  
	 * @Description: 屏蔽/恢复内容
	 * @param subjectMap    参数  
	 * @return: int    返回类型
	 */
	int shieldSubject(Map<String, Object> subjectMap);
	
	/**
	 * 
	 * @Title: shieldSource  
	 * @Description: 屏蔽/恢复来源
	 * @param subjectMap
	 * @return: int    返回类型
	 */
	int shieldSource(Map<String, Object> subjectMap);

}