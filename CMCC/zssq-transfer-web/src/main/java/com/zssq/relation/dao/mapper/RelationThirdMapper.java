package com.zssq.relation.dao.mapper;

import java.util.List;

import com.zssq.relation.pojo.RelationSubjectData;
import com.zssq.relation.pojo.RelationSubjectInfo;

/**
 * 
 * @ClassName: RelationSubjectMapper  
 * @Description: 关系内容数据
 * @author ZKZ  
 * @date 2017年6月20日  
 *
 */
public interface RelationThirdMapper {
	
	/**
	 * 
	 * @param subjectInfoList 
	 * @Title: insertSubjectInfoList  
	 * @Description: 插入内容信息
	 * @return: int    返回类型
	 */
	int insertSubjectInfoList(List<RelationSubjectInfo> subjectInfoList);

	/**
	 * 
	 * @param subjectDataList 
	 * @Title: insertSubjectDataList  
	 * @Description: 插入内容数据信息
	 * @return: int    返回类型
	 */
	int insertSubjectDataList(List<RelationSubjectData> subjectDataList);

	/**
	 * 
	 * @Title: insertSubjectInfoBlog  
	 * @Description: 将博客信息迁移到关系内容信息中
	 * @return: int    返回类型
	 */
	int insertSubjectInfoBlog();
	
	/**
	 * 
	 * @Title: insertSubjectDataBlog  
	 * @Description: 将博客数据迁移到关系内容数据中
	 * @return: int    返回类型
	 */
	int insertSubjectDataBlog();

	/**
	 * 
	 * @Title: insertSubjectInfoNews  
	 * @Description: 将新闻信息迁移到关系内容信息中
	 * @return: int    返回类型
	 */
	int insertSubjectInfoNews();

	/**
	 * 
	 * @Title: insertSubjectDataNews  
	 * @Description: 将新闻信息迁移到关系内容信息中
	 * @return: int    返回类型
	 */
	int insertSubjectDataNews();

	/**
	 * 
	 * @Title: insertSubjectInfoDisk  
	 * @Description: 将网盘信息迁移到关系内容信息中
	 * @return: int    返回类型
	 */
	int insertSubjectInfoDisk();

	/**
	 * 
	 * @Title: insertSubjectDataDisk  
	 * @Description: 将网盘信息迁移到关系内容信息中
	 * @return: int    返回类型
	 */
	int insertSubjectDataDisk();
	
}