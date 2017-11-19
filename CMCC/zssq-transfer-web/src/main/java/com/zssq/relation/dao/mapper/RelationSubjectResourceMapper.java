package com.zssq.relation.dao.mapper;

import java.util.List;

import com.zssq.relation.pojo.RelationSubjectResource;
/**
 * 
 * @ClassName: RelationSubjectResourceMapper  
 * @Description: 内容资源  
 * @author sry  
 * @date 2017年6月22日  
 *
 */
public interface RelationSubjectResourceMapper {

	/**
     * 
     * @Title: batchInsert  
     * @Description: 批量插入内容资源信息
     * @param relationSubjectResourceList
     * @return: int    返回类型
     */
	int batchInsert(List<RelationSubjectResource> relationSubjectResourceList);

	void batchInsertResource();
}