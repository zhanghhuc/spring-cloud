package com.zssq.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zssq.blog.dao.mapper.BlogReplyMapper;

/**
 * 
 * @ClassName: BlogReplyService  
 * @Description: 评论回复数据迁移Service  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Service("blogReplyService")
public class BlogReplyService {
	
	@Autowired
	private BlogReplyMapper blogReplyMapper;
	
	/**
	 * 
	 * @Title: insertSourceReply  
	 * @Description: 导入原回复数据
	 * @return: void    返回类型
	 */
	@Transactional
	public void insertSourceReply() {
		blogReplyMapper.insertSourceReply();
	}

}
