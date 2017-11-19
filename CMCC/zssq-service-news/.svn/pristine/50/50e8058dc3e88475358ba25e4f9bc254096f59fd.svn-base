package com.zssq.service.impl;

import com.zssq.biz.NewsInfoInformBiz;
import com.zssq.exceptions.BusinessException;
import com.zssq.news.model.NewsInfoInformModel;
import com.zssq.news.service.NewsInfoInformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsInfoInformServiceImpl implements NewsInfoInformService {

    @Autowired
    private NewsInfoInformBiz newsInfoInformBiz;


    @Override
    public boolean addNewsInfoReportThird(NewsInfoInformModel newsInfoInformModel) throws BusinessException {
        return newsInfoInformBiz.addNewsInfoReport(newsInfoInformModel);
    }
}
