package com.zssq.service.impl;


import com.zssq.biz.NewsCollectBiz;
import com.zssq.biz.NewsTransmitBiz;
import com.zssq.exceptions.BusinessException;
import com.zssq.news.model.NewsCollectModel;
import com.zssq.news.model.NewsTransmitModel;
import com.zssq.news.service.NewsCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsCollectServiceImpl implements NewsCollectService {

    @Autowired
    private NewsCollectBiz newsCollectBiz;//新闻评论逻辑层

    @Autowired
    private NewsTransmitBiz newsTransmitBiz;//新闻评论逻辑层


    @Override
    public boolean addNewsInfoCollect(NewsCollectModel newsCollectModel) throws BusinessException {
        return newsCollectBiz.addNewsInfoCollect(newsCollectModel);
    }

    @Override
    public boolean addNewsInfoTransmit(NewsTransmitModel newsTransmitModel) throws BusinessException {
        return newsTransmitBiz.addNewsInfoTransmit(newsTransmitModel);
    }
}