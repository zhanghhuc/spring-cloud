package com.zssq.blog.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.blog.pojo.SourceClassModel;

/**
 * 
 * @ClassName: BlogClassMapper  
 * @Description: 博客分类  
 * @author ZKZ  
 * @date 2017年6月20日  
 *
 */
public interface BlogClassMapper {
	
	/**
	 * 
	 * @Title: isColumnExists  
	 * @Description: 查看old_id字段是否存在
	 * @return: String    返回类型
	 */
	String isColumnExists();
	
	/**
	 * 
	 * @Title: addColumn  
	 * @Description: 添加字段
	 * @return: void    返回类型
	 */
	void addColumn();
	
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
	 * @Title: createIndex  
	 * @Description: 创建索引
	 * @return: void    返回类型
	 */
	void createIndex();

	/**
	 * 
	 * @Title: getSourceClassList  
	 * @Description: 查询原表中分类数据
	 * @return: List<SourceClassModel>    返回类型
	 */
	List<SourceClassModel> getSourceClassList();

	/**
	 * 
	 * @Title: getBlogClassCount  
	 * @Description: 查询分类表中是否已经有数据
	 * @return: int    返回类型
	 */
	int getBlogClassCount();

	/**
	 * 
	 * @Title: insertUserClass  
	 * @Description: 插入人员分类数据
	 * @param paramMap    参数  
	 * @return: void    返回类型
	 */
	void insertUserClass(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: insertTeamClass  
	 * @Description: 插入班组分类数据
	 * @param blogClass    参数  
	 * @return: paramMap    返回类型
	 */
	void insertTeamClass(Map<String, Object> paramMap);

}