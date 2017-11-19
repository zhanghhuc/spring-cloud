package com.zssq.news.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.news.model.NewsCollectModel;
import com.zssq.news.model.NewsTransmitModel;

/**
 *
 */
public interface NewsCollectService {


	/**
	 * 新闻收藏接口
	 * @param newsCollectModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean addNewsInfoCollect(NewsCollectModel newsCollectModel) throws BusinessException ;




	/**
	 * 新闻转发接口
	 * @param newsTransmitModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean addNewsInfoTransmit(NewsTransmitModel newsTransmitModel) throws BusinessException ;



}
