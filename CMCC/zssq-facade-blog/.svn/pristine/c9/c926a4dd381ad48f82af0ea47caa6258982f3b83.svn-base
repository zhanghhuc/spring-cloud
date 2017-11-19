package com.zssq.service;

import com.zssq.dao.model.BlogDraftModel;
import com.zssq.dao.pojo.BlogDraft;
import com.zssq.dao.pojo.BlogDraftContent;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.BlogDraftVO;

/**
 * 
 * @ClassName: BlogDraftService  
 * @Description: 博客草稿  
 * @author ZKZ  
 * @date 2017年3月20日  
 *
 */
public interface BlogDraftService {
	
	/**
	 * 
	 * @Title: getDraftList  
	 * @Description: 获取草稿列表
	 * @param pageParam 分页参数
	 * @param blogDraftVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getDraftList(PageParam pageParam, BlogDraftVO blogDraftVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getDraftInfo  
	 * @Description: 获取草稿详情
	 * @param blogDraftVO
	 * @throws BusinessException    参数  
	 * @return: BlogDraftModel    返回类型
	 */
	public BlogDraftModel getDraftInfo(BlogDraftVO blogDraftVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveDraft  
	 * @Description: 保存草稿信息
	 * @param blogDraft 草稿信息
	 * @param blogDraftContent 草稿正文信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveDraft(BlogDraft blogDraft, BlogDraftContent blogDraftContent) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateDraft  
	 * @Description: 修改草稿信息
	 * @param blogDraftVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateDraft(BlogDraftVO blogDraftVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteDraft  
	 * @Description: 删除草稿
	 * @param blogDraftVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteDraft(BlogDraftVO blogDraftVO) throws BusinessException;

}
