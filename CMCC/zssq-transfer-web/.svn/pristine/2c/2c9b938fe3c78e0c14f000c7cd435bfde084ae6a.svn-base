package com.zssq.relation.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.zssq.relation.pojo.RelationSubjectInfo;

public interface RelationSubjectInfoMapper {
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
     * @Title: getSubjectBaseInfo  
     * @Description: 查询博客信息
     * @param getmBlogCode
     * @return    参数  
     * @return: RelationSubjectInfo    返回类型
     */
	RelationSubjectInfo getSubjectBaseInfo(@Param("subjectCode")String subjectCode);

	void insertBlog();
	void insertMBlog();

}