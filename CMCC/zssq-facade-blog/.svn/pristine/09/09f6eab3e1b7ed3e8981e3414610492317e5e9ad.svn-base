package com.zssq.service;

import com.zssq.dao.model.BlogCommentModel;
import com.zssq.dao.model.BlogReplyModel;
import com.zssq.dao.pojo.BlogComment;
import com.zssq.dao.pojo.BlogReply;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.BlogCommentVO;

/**
 * 
 * @ClassName: BlogCommentService  
 * @Description: 博客评论  
 * @author ZKZ  
 * @date 2017年3月20日  
 *
 */
public interface BlogCommentService {
	
	/**
	 * 
	 * @Title: getCommentList  
	 * @Description: 查询评论列表
	 * @param pageParam 分页参数
	 * @param blogCommentVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getCommentList(PageParam pageParam, BlogCommentVO blogCommentVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getReplyList  
	 * @Description: 查询回复列表
	 * @param pageParam 分页参数
	 * @param blogCommentVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getReplyList(PageParam pageParam, BlogCommentVO blogCommentVO) throws BusinessException;

	/**
	 * 
	 * @Title: getCommentInfo  
	 * @Description: 获取评论详情
	 * @param blogCommentVO
	 * @throws BusinessException    参数  
	 * @return: BlogCommentModel    返回类型
	 */
	public BlogCommentModel getCommentInfo(BlogCommentVO blogCommentVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getCommentAllInfo  
	 * @Description: 获取评论详情（所有）
	 * @param blogCommentVO
	 * @throws BusinessException    参数  
	 * @return: BlogCommentModel    返回类型
	 */
	public BlogCommentModel getCommentAllInfo(BlogCommentVO blogCommentVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getReplyInfo  
	 * @Description: 获取回复详情
	 * @param blogCommentVO
	 * @throws BusinessException    参数  
	 * @return: BlogReplyModel    返回类型
	 */
	public BlogReplyModel getReplyInfo(BlogCommentVO blogCommentVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getReplyAllInfo  
	 * @Description: 获取回复详情（所有）
	 * @param blogCommentVO
	 * @throws BusinessException    参数  
	 * @return: BlogReplyModel    返回类型
	 */
	public BlogReplyModel getReplyAllInfo(BlogCommentVO blogCommentVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveComment  
	 * @Description: 保存评论信息
	 * @param blogComment 评论信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveComment(BlogComment blogComment) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveReply  
	 * @Description: 保存回复信息
	 * @param blogReply 回复信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveReply(BlogReply blogReply) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateCommentDeleteStatus  
	 * @Description: 删除评论
	 * @param blogCommentVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateCommentDeleteStatus(BlogCommentVO blogCommentVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateReplyDeleteStatus  
	 * @Description: 删除回复
	 * @param blogCommentVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateReplyDeleteStatus(BlogCommentVO blogCommentVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getReportCommentList  
	 * @Description: 定位评论
	 * @param qo
	 * @return    参数  
	 * @return: PageBean    返回类型
	 * @throws BusinessException 
	 */
	public PageBean getReportCommentList(BlogCommentVO qo) throws BusinessException;
	
	/**
	 * 
	 * @Title: getReportReplyList  
	 * @Description: 定位评论的回复
	 * @param qo
	 * @return    参数  
	 * @return: PageBean    返回类型
	 * @throws BusinessException 
	 */
	public PageBean getReportReplyList(BlogCommentVO qo) throws BusinessException;
	
}
