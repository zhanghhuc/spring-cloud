package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.BlogClass;

/**
 * 
 * @ClassName: BlogClassMapper  
 * @Description: 博客分类  
 * @author ZKZ  
 * @date 2017年3月22日  
 *
 */
public interface BlogClassMapper {

	/**
	 * 
	 * @Title: listAll  
	 * @Description: 获取分类列表 - 不分页
	 * @param paramMap
	 * @return: List<BlogClass>    返回类型
	 */
	List<BlogClass> listAll(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: getBlogNum  
	 * @Description: 获取分类下博文数量
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int getBlogNum(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: getClassNum  
	 * @Description: 查询拥有的分类个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int getClassNum(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: insert  
	 * @Description: 保存分类信息
	 * @param blogClass
	 * @return: int    返回类型
	 */
	int insert(BlogClass blogClass);

	/**
	 * 
	 * @Title: delete  
	 * @Description: 删除分类
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int delete(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: updateData  
	 * @Description: 修改数量
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int updateData(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: getDefaultClassCode  
	 * @Description: 查询默认分类编号
	 * @param paramMap
	 * @return: String    返回类型
	 */
	String getDefaultClassCode(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: updateBlogNum  
	 * @Description: 修改分类下博客数量
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int updateBlogNum(Map<String, Object> paramMap);
	

}