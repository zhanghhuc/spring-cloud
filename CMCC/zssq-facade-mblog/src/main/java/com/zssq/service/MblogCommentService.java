package com.zssq.service;

import com.zssq.dao.pojo.MblogComment;
import com.zssq.exceptions.BusinessException;
import com.zssq.qo.CommentInfoQO;
import com.zssq.utils.PageBean;
/**
 * 
    * @ClassName: MblogCommentService  
    * @Description: 微博评论信息操作   
    * @author Mr.B  
    * @date 2017年3月16日  
    *
 */
public interface MblogCommentService {

	/**
	 * 
	    * @Title: addCommentInfo  
	    * @Description: 添加评论
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	public boolean addCommentInfo(CommentInfoQO qo)throws BusinessException;
	
	/**
	 * 
	    * @Title: queryCommentList  
	    * @Description: 获取评论列表
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	public PageBean queryCommentList(CommentInfoQO qo)throws BusinessException;
	
	
	/**
	 * 
	    * @Title: addOrDelComPraise  
	    * @Description: 点赞/取消点赞
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	public boolean addOrDelComPraise(CommentInfoQO qo)throws BusinessException;
	
	/**
	 * 
	    * @Title: deleteCommentInfo  
	    * @Description: 删除评论
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	public boolean deleteCommentInfo(CommentInfoQO qo)throws BusinessException;

	/**
	 * 
	    * @Title: gotoCommentList  
	    * @Description: 定位评论列表
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	public PageBean gotoCommentList(CommentInfoQO qo)throws BusinessException;

	/**
	 * 
	    * @Title: gotoCommentListByPage  
	    * @Description: 定位评论列表可以分页进行的
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	public PageBean gotoCommentListByPage(CommentInfoQO qo)throws BusinessException;

	/**
	 * 
	    * @Title: getCommentInfo  
	    * @Description: 获取评论信息
	    * @param commentCode	评论CODE
	    * @throws BusinessException
		* @return MblogComment    返回类型
	 */
	public MblogComment getCommentInfo(String commentCode) throws BusinessException ;
}
