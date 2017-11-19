package com.zssq.relation.dao.mapper;

import java.util.List;

import com.zssq.relation.pojo.RelationRecommend;
/**
 * 
 * @ClassName: RelationRecommendMapper  
 * @Description: 推荐  
 * @author sry  
 * @date 2017年6月22日  
 *
 */
public interface RelationRecommendMapper {
	
	
	void insertBanZuBlog();
	void insertshiBlog();
	void insertshengBlog();
	void insertBanZuMBlog();
	void insertshiMBlog();
	void insertshengMBlog();
}