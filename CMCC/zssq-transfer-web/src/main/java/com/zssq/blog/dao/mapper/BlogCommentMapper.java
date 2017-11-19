package com.zssq.blog.dao.mapper;

/**
 * 
 * @ClassName: BlogCommentMapper  
 * @Description: 评论
 * @author ZKZ  
 * @date 2017年6月20日  
 *
 */
public interface BlogCommentMapper {
	
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
	 * @Title: createIndex  
	 * @Description: 创建索引
	 * @return: void    返回类型
	 */
	void createIndex();
	
	/**
	 * 
	 * @Title: insertSourceComment  
	 * @Description: 插入评论数据
	 * @return: int    返回类型
	 */
	int insertSourceComment();
	
}