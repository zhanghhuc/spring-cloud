package com.zssq.news.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.news.model.NewsInfoInformModel;

/**
 *
 */
public interface NewsInfoInformService {


	/**
	 * 新闻收藏接口
	 * @param newsInfoInformModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean addNewsInfoReportThird(NewsInfoInformModel newsInfoInformModel) throws BusinessException ;



}
