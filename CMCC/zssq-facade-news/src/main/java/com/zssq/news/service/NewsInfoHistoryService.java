package com.zssq.news.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsInfoHistory;
import com.zssq.news.model.NewsHistoryQuery;

import java.util.List;

public interface NewsInfoHistoryService {
	/**
	 * 新闻历程列表接口
	 * @param newsHistoryQuery
	 * @return
	 * @throws BusinessException
	 */
	public List<NewsInfoHistory> getNewsInfoHistorys(NewsHistoryQuery newsHistoryQuery) throws BusinessException;

}
