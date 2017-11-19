package com.zssq.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zssq.blog.dao.mapper.BlogDataMapper;

/**
 * 
 * @ClassName: BlogDataService  
 * @Description: 数据迁移过程数量修改Service  
 * @author ZKZ  
 * @date 2017年5月23日  
 *
 */
@Service("blogDataService")
public class BlogDataService {
	
	@Autowired
	private BlogDataMapper blogDataMapper;
	
	/**
	 * 
	 * @Title: updateData  
	 * @Description: 修改数据量
	 * @return: void    返回类型
	 */
	@Transactional
	public void updateData() {
		// 修改分类下博客数量
		blogDataMapper.updateClassBlogNum();
		
		// 修改博客评论量
		blogDataMapper.updateBlogCommentNum();
		
		// 修改评论回复量
		blogDataMapper.updateCommentReplyNum();
	}

}
