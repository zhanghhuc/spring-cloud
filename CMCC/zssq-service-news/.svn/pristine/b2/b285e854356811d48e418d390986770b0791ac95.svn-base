package com.zssq.dao.mapper;

import com.zssq.news.dao.pojo.NewsArchive;
import com.zssq.news.model.NewsArchiveQuery;

import java.util.List;

/**
 * 
 * @ClassName: NewsArchiveMapper  
 * @Description: 新闻归档mapper  
 * @author ZKZ  
 * @date 2017年7月24日  
 *
 */
public interface NewsArchiveMapper {
	
	/**
	 * 
	 * @Title: insertSelective  
	 * @Description: 插入
	 * @param record
	 * @return: int    返回类型
	 */
	int insertSelective(NewsArchive record);
	
	/**
	 * 
	 * @Title: selectCount  
	 * @Description: 查询个数
	 * @param newsArchiveQuery
	 * @return: Integer    返回类型
	 */
	Integer selectCount(NewsArchiveQuery newsArchiveQuery);
	
	/**
	 * 
	 * @Title: selectList  
	 * @Description: 查询列表
	 * @param newsArchiveQuery
	 * @return: List<NewsArchive>    返回类型
	 */
	List<NewsArchive> selectList(NewsArchiveQuery newsArchiveQuery);
	
    int deleteByPrimaryKey(Long id);

    int insert(NewsArchive record);

    NewsArchive selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsArchive record);

    int updateByPrimaryKey(NewsArchive record);
	
}