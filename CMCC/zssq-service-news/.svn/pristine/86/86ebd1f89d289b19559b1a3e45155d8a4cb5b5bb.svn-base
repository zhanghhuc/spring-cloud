package com.zssq.service.impl;

import com.zssq.biz.NewsInfoHistoryBiz;
import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsInfoHistory;
import com.zssq.news.model.NewsHistoryQuery;
import com.zssq.news.service.NewsInfoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsInfoHistoryServiceImpl implements NewsInfoHistoryService {

    @Autowired
    private NewsInfoHistoryBiz newsInfoHistoryBiz;

    @Override
    public List<NewsInfoHistory> getNewsInfoHistorys(NewsHistoryQuery newsHistoryQuery) throws BusinessException {
        return newsInfoHistoryBiz.getNewsInfoHistorys(newsHistoryQuery);
    }
}
