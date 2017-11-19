package com.zssq.blog.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.blog.pojo.BlogDraft;
import com.zssq.blog.pojo.DraftTemp;
import com.zssq.blog.pojo.SourceDraftModel;

/**
 * 
 * @ClassName: BlogDraftMapper  
 * @Description: 草稿
 * @author ZKZ  
 * @date 2017年6月20日  
 *
 */
public interface BlogDraftMapper {

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
	 * @Title: getSourceDraftCount  
	 * @Description: 查询原草稿表中数据总量
	 * @return: int    返回类型
	 */
	int getSourceDraftCount();

	/**
	 * 
	 * @Title: getSourceDraftList  
	 * @Description: 获取原草稿数据
	 * @param paramMap
	 * @return: List<SourceDraftModel>    返回类型
	 */
	List<SourceDraftModel> getSourceUserDraftList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: getSourceTeamDraftList  
	 * @Description: 获取原草稿数据
	 * @return: List<SourceDraftModel>    返回类型
	 */
	List<SourceDraftModel> getSourceTeamDraftList();

	/**
	 * 
	 * @Title: insertDraftList  
	 * @Description: 插入草稿列表
	 * @param blogDraftList
	 * @return: int    返回类型
	 */
	int insertDraftList(List<BlogDraft> blogDraftList);

	/**
	 * 
	 * @Title: insertDraftTempList  
	 * @Description: 插入草稿临时列表
	 * @param draftTempList
	 * @return: int    返回类型
	 */
	int insertDraftTempList(List<DraftTemp> draftTempList);

	/**
	 * 
	 * @Title: insertSourceDraftContent  
	 * @Description: 插入草稿内容数据 
	 * @return: int    返回类型
	 */
	int insertSourceDraftContent();
	
}