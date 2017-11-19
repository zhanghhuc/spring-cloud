package com.zssq.blog.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.blog.pojo.BlogData;
import com.zssq.blog.pojo.BlogInfo;
import com.zssq.blog.pojo.BlogTemp;
import com.zssq.blog.pojo.SourceBlogModel;

/**
 * 
 * @ClassName: BlogMapper  
 * @Description: 博客
 * @author ZKZ  
 * @date 2017年6月20日  
 *
 */
public interface BlogMapper {
	
	/**
	 * 
	 * @Title: isIndexExists  
	 * @Description: 查看Index_1索引是否存在
	 * @return: String    返回类型
	 */
	String isIndexExists();
	
	/**
	 * 
	 * @Title: deleteIndex  
	 * @Description: 删除索引
	 * @return: void    返回类型
	 */
	void deleteIndex();

	/**
	 * 
	 * @Title: dropTempTable  
	 * @Description: 删除临时表
	 * @return: void    返回类型
	 */
	void dropTempTable();
	
	/**
	 * 
	 * @Title: isTempTableExists  
	 * @Description: 查看临时表是否存在
	 * @return: String    返回类型
	 */
	String isTempTableExists();

	/**
	 * 
	 * @Title: createTempTable  
	 * @Description: 新建临时表
	 * @return: void    返回类型
	 */
	void createTempTable();

	/**
	 * 
	 * @Title: createIndex  
	 * @Description: 创建索引
	 * @return: void    返回类型
	 */
	void createIndex();

	/**
	 * 
	 * @Title: getSourceBlogCount  
	 * @Description: 查询原博客表中数据总量
	 * @return: int    返回类型
	 */
	int getSourceBlogCount();

	/**
	 * 
	 * @Title: getSourceUserBlogList  
	 * @Description: 获取原个人博客数据
	 * @param paramMap
	 * @return: List<SourceBlogModel>    返回类型
	 */
	List<SourceBlogModel> getSourceUserBlogList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: getSourceTeamBlogList  
	 * @Description: 获取原班组博客数据
	 * @param paramMap
	 * @return: List<SourceBlogModel>    返回类型
	 */
	List<SourceBlogModel> getSourceTeamBlogList(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: insertBlogList  
	 * @Description: 插入博客列表
	 * @param blogInfoList
	 * @return: int    返回类型
	 */
	int insertBlogList(List<BlogInfo> blogInfoList);
	
	/**
	 * 
	 * @Title: insertBlogDataList  
	 * @Description: 插入博客数据列表
	 * @param blogDataList
	 * @return: int    返回类型
	 */
	int insertBlogDataList(List<BlogData> blogDataList);

	/**
	 * 
	 * @Title: insertBlogTempList  
	 * @Description: 插入博客临时列表
	 * @param blogTempList
	 * @return: int    返回类型
	 */
	int insertBlogTempList(List<BlogTemp> blogTempList);

	/**
	 * 
	 * @Title: insertSourceBlogContent  
	 * @Description: 插入博客内容数据 
	 * @return: int    返回类型
	 */
	int insertSourceBlogContent();
	
}