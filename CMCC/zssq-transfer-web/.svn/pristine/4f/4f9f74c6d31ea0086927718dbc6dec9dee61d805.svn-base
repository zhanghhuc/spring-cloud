package com.zssq.blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zssq.blog.dao.mapper.BlogDraftMapper;
import com.zssq.blog.pojo.BlogDraft;
import com.zssq.blog.pojo.DraftTemp;
import com.zssq.blog.pojo.SourceDraftModel;
import com.zssq.constants.BlogConstants;

/**
 * 
 * @ClassName: BlogDraftService  
 * @Description: 草稿数据迁移Service  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Service("blogDraftService")
public class BlogDraftService {
	
	@Autowired
	private BlogDraftMapper blogDraftMapper;
	
	/**
	 * 
	 * @Title: createTempTable  
	 * @Description: 创建草稿临时表
	 * @return: void    返回类型
	 */
	@Transactional
	public void createTempTable() {
		// 查看是否存在临时表
		String tempTable = blogDraftMapper.isTempTableExists();
		if (tempTable != null) {
			return;
		}
		
		// 新建临时表
		blogDraftMapper.createTempTable();
	}
	
	/**
	 * 
	 * @Title: getSourceDraftCount  
	 * @Description: 查询原草稿表中数据总量
	 * @return: int    返回类型
	 */
	public int getSourceDraftCount() {
		return blogDraftMapper.getSourceDraftCount();
	}

	/**
	 * 
	 * @Title: getSourceDraftList  
	 * @Description: 获取原草稿数据
	 * @param pageNo
	 * @param pageSize
	 * @return: List<SourceDraftModel>    返回类型
	 */
	public List<SourceDraftModel> getSourceDraftList(int pageNo, int pageSize, Byte draftDepend) {
		if (BlogConstants.BLOG_DRAFT_DEPEND_USER.equals(draftDepend)) {
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			
			// 查询个人草稿列表
			return blogDraftMapper.getSourceUserDraftList(paramMap);
		} else {
			// 查询班组草稿列表
			return blogDraftMapper.getSourceTeamDraftList();
		}
	}

	/**
	 * 
	 * @Title: insertBlogList  
	 * @Description: 导入原草稿数据
	 * @param blogDraftList
	 * @param draftTempList    参数  
	 * @return: void    返回类型
	 */
	@Transactional
	public void insertDraftList(List<BlogDraft> blogDraftList, List<DraftTemp> draftTempList) {
		// 插入草稿列表
		blogDraftMapper.insertDraftList(blogDraftList);
		
		// 插入草稿临时列表
		blogDraftMapper.insertDraftTempList(draftTempList);
	}

	/**
	 * 
	 * @Title: insertSourceBlogContent  
	 * @Description: 导入原草稿内容数据 
	 * @return: void    返回类型
	 */
	@Transactional
	public void insertSourceDraftContent() {
		blogDraftMapper.insertSourceDraftContent();
	}
	
}
