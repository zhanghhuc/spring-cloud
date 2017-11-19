package com.zssq.relation.service;

import java.util.List;

import com.zssq.relation.pojo.RelationSubjectData;
import com.zssq.relation.pojo.RelationSubjectInfo;

/**
 * 
 * @ClassName: IRelationThirdService  
 * @Description: 关系内容数据迁移Service（第三方调用）  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
public interface IRelationThirdService {
	
	/**
	 * 
	 * @Title: insertSubjectAll  
	 * @Description: 导入关系内容信息和关系内容数据
	 * @param subjectInfoList
	 * @param subjectDataList    参数  
	 * @return: void    返回类型
	 */
	public void insertSubjectAll(List<RelationSubjectInfo> subjectInfoList, List<RelationSubjectData> subjectDataList);
	
	/**
	 * 
	 * @Title: insertSubjectInfo  
	 * @Description: 导入关系内容信息
	 * @param subjectInfoList    参数  
	 * @return: void    返回类型
	 */
	public void insertSubjectInfo(List<RelationSubjectInfo> subjectInfoList);
	
	/**
	 * 
	 * @Title: insertSubjectData  
	 * @Description: 导入关系内容数据
	 * @param subjectDataList    参数  
	 * @return: void    返回类型
	 */
	public void insertSubjectData(List<RelationSubjectData> subjectDataList);

	/**
	 * 
	 * @Title: insertRelationBlogData  
	 * @Description: 将博客信息迁移到关系内容信息中
	 * @return: void    返回类型
	 */
	public void insertRelationBlogData();

	/**
	 * 
	 * @Title: insertRelationNewsData  
	 * @Description: 将新闻信息迁移到关系内容信息中
	 * @return: void    返回类型
	 */
	public void insertRelationNewsData();

	/**
	 * 
	 * @Title: insertRelationDiskData  
	 * @Description: 将网盘信息迁移到关系内容信息中
	 * @return: void    返回类型
	 */
	public void insertRelationDiskData();

}
