package com.zssq.news.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.news.model.NewsRssQuery;
import com.zssq.news.model.third.ShieldModel;
import com.zssq.pojo.RssReturnPo;

import java.util.List;

public interface NewsThridService {

	/**
	 * 新闻屏蔽、取消屏蔽
	 * @param shieldModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean modifyShield(ShieldModel shieldModel) throws BusinessException ;


	/**
	 * 新闻屏蔽、取消屏蔽
	 * @param shieldModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean modifyNewsShield(ShieldModel shieldModel) throws BusinessException ;

	/**
	 * 新闻评论屏蔽、取消屏蔽
	 * @param shieldModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean modifyNewsCommentShield(ShieldModel shieldModel) throws BusinessException ;
	/**
	 * 新闻评论回复屏蔽、取消屏蔽
	 * @param shieldModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean modifyReplyShield(ShieldModel shieldModel);


	/**
	 * 新闻RSS列表接口
	 * @param newsRssQuery
	 * @return
	 * @throws BusinessException
	 */
	public List<RssReturnPo> getNewsInfosRss(NewsRssQuery newsRssQuery) throws BusinessException;


}
