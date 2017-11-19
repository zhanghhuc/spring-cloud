package com.zssq.biz;

import com.zssq.dao.mapper.NewsInfoHistoryMapper;
import com.zssq.news.dao.pojo.NewsInfoHistory;
import com.zssq.news.model.NewsHistoryQuery;
import com.zssq.news.model.NewsModel;
import com.zssq.shiro.mysecurity.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017-04-05.
 */
@Service
public class NewsInfoHistoryBiz {

    @Autowired
    private NewsInfoHistoryMapper newsInfoHistoryMapper;


    public List<NewsInfoHistory> getNewsInfoHistorys(NewsHistoryQuery newsHistoryQuery) {
        return newsInfoHistoryMapper.selectList(newsHistoryQuery);
    }

    public int addNewsHistory(NewsModel newsModel) {
        long currentTime = System.currentTimeMillis();
        NewsInfoHistory record = generateNewsInfoHistory(newsModel, currentTime, currentTime);
        int number = newsInfoHistoryMapper.insertSelective(record);
        return number;
    }

    private NewsInfoHistory generateNewsInfoHistory(NewsModel newsModel, long createTime, long modifyTime) {
        NewsInfoHistory newsInfoHistory = new NewsInfoHistory();
        newsInfoHistory.setNewsCode(newsModel.getNewsCode());
        newsInfoHistory.setInfoHistoryCode(UUIDHelper.getUUID());
        newsInfoHistory.setInfoOperatorCode(newsModel.getUserCode());
        newsInfoHistory.setOrgCode(newsModel.getInfoOperatorOrg());
        newsInfoHistory.setTenantCode(newsModel.getTenantCode());
        newsInfoHistory.setInfoStatus(newsModel.getInfoStatus());
        if (createTime > 0) {
            newsInfoHistory.setCreatTime(createTime);
        }
        newsInfoHistory.setModifyTime(modifyTime);
        if (null != newsModel.getInfoRemark() && !"".equals(newsModel.getInfoRemark())) {
            newsInfoHistory.setInfoRemark(newsModel.getInfoRemark());
        }
        return newsInfoHistory;
    }
}
