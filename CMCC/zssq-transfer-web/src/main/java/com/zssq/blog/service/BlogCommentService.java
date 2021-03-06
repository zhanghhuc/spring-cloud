package com.zssq.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zssq.blog.dao.mapper.BlogCommentMapper;

/**
 * 
 * @ClassName: BlogCommentService  
 * @Description: 评论数据迁移Service  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Service("blogCommentService")
public class BlogCommentService {
	
	@Autowired
	private BlogCommentMapper blogCommentMapper;
	
	/**
	 * 
	 * @Title: deleteIndex  
	 * @Description: 删除索引
	 * @return: void    返回类型
	 */
	@Transactional
	public void deleteIndex() {
		// 查看Index_1索引是否存在
		String indexName = blogCommentMapper.isIndexExists();
		if (indexName == null) {
			return;
		}
		
		// 删除索引
		blogCommentMapper.deleteIndex();
	}

	/**
	 * 
	 * @Title: addColumn  
	 * @Description: 往评论表中添加字段
	 * @return: void    返回类型
	 */
	@Transactional
	public void addColumn() {
		// 查看old_id字段是否存在
		String tableName = blogCommentMapper.isColumnExists();
		if (tableName != null) {
			return;
		}
		
		// 添加字段
		blogCommentMapper.addColumn();
	}

	/**
	 * 
	 * @Title: createIndex  
	 * @Description: 创建索引
	 * @return: void    返回类型
	 */
	public void createIndex() {
		blogCommentMapper.createIndex();
	}
	
	/**
	 * 
	 * @Title: insertSourceComment  
	 * @Description: 导入原评论数据
	 * @return: void    返回类型
	 */
	@Transactional
	public void insertSourceComment() {
		blogCommentMapper.insertSourceComment();
	}

}
