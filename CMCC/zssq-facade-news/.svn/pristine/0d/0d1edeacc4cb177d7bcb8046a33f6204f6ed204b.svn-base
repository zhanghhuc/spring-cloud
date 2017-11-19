package com.zssq.news.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsReply;
import com.zssq.news.model.NewsReplyModel;
import com.zssq.news.model.NewsReplyPraiseModel;
import com.zssq.news.model.NewsReplyQuery;
import com.zssq.utils.PageBean;

/**
 *
 */
public interface NewsReplyService {

	/**
	 * 新闻评论回复列表接口
	 * @param newsReplyQuery
	 * @return
	 * @throws BusinessException
	 */
	public PageBean getNewsReplys(NewsReplyQuery newsReplyQuery) throws BusinessException;


	PageBean getNewsReplesPC(NewsReplyQuery newsReplyQuery) throws BusinessException;

	NewsReply addNewsReplyPC(NewsReplyModel newsReplyModel) throws BusinessException;

	NewsReply getNewsReplyDetail(NewsReplyQuery newsReplyQuery) throws BusinessException;

	boolean deleteNewsReply(NewsReplyModel newsReplyModel) throws BusinessException;

	public boolean updateNewsReplyGood(NewsReplyPraiseModel newsReplyPraiseModel) throws BusinessException ;


}
