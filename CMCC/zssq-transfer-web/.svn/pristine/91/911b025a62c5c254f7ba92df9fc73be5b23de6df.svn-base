package com.zssq.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zssq.blog.dao.mapper.BlogSubMapper;
import com.zssq.blog.pojo.BlogSub;
import com.zssq.blog.pojo.SourceSubModel;

/**
 * 
 * @ClassName: BlogSubService  
 * @Description: 博客订阅数据迁移Service  
 * @author ZKZ  
 * @date 2017年5月18日  
 *
 */
@Service("blogSubService")
public class BlogSubService {
	
	@Autowired
	private BlogSubMapper blogSubMapper;

	/**
	 * 
	 * @Title: deleteSubData  
	 * @Description: 删除订阅表中的数据
	 * @return: void    返回类型
	 */
	@Transactional
	public void deleteSubData() {
		blogSubMapper.deleteSubData();
	}

	/**
	 * 
	 * @Title: getSourceSubList  
	 * @Description: 查询原订阅数据
	 * @return: List<SourceSubModel>    返回类型
	 */
	public List<SourceSubModel> getSourceSubList() {
		return blogSubMapper.getSourceSubList();
	}

	/**
	 * 
	 * @Title: insertSourceSub  
	 * @Description: 导入原数据
	 * @param blogSubList    参数  
	 * @return: void    返回类型
	 */
	@Transactional
	public void insertSourceSub(List<BlogSub> blogSubList) {
		blogSubMapper.insertSourceSub(blogSubList);
	}
	
}
