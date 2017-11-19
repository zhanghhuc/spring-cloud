package com.zssq.news.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsInfoComment;
import com.zssq.news.model.NewsCommentModel;
import com.zssq.news.model.NewsCommentPraiseModel;
import com.zssq.news.model.NewsCommentQuery;
import com.zssq.news.model.NewsPraiseModel;
import com.zssq.utils.PageBean;

/**
 *
 */
public interface NewsCommentService {

	/**
	 * 新闻评论新增接口
	 * @param newsCommentModel
	 * @return
	 * @throws BusinessException
	 */
	public NewsInfoComment addNewsComment(NewsCommentModel newsCommentModel) throws BusinessException ;

	/**
	 * 根据ID获取新闻评论
	 * @param newsCommentQuery
	 * @return
	 * @throws BusinessException
	 */
	NewsInfoComment getNewsCommentByQuery(NewsCommentQuery newsCommentQuery) throws BusinessException ;

	/**
	 * 新闻评论列表接口
	 * @param newsCommentQuery
	 * @return
	 * @throws BusinessException
	 */
	public PageBean getNewsIComments(NewsCommentQuery newsCommentQuery) throws BusinessException;

	/**
	 * 新闻评论和回复列表接口
	 * @param newsCommentQuery
	 * @return
	 * @throws BusinessException
	 */
	public PageBean getNewsComments(NewsCommentQuery newsCommentQuery) throws BusinessException;


	/**
	 * 新闻点赞接口
	 * @param newsPraiseModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateNewsInfoGood(NewsPraiseModel newsPraiseModel) throws BusinessException ;


	/**
	 * 新闻评论点赞接口
	 * @param newsCommentPraiseModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateNewsInfoCommentGood(NewsCommentPraiseModel newsCommentPraiseModel) throws BusinessException ;


    boolean deleteNewsComment(NewsCommentModel newsCommentModel)  throws BusinessException;
}
