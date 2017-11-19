package com.zssq.biz;

import com.zssq.dao.mapper.NewsNoticeHistoryMapper;
import com.zssq.news.dao.pojo.NewsNoticeHistory;
import com.zssq.news.model.NoticeHistoryQuery;
import com.zssq.news.model.NoticeModel;
import com.zssq.shiro.mysecurity.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017-04-05.
 */
@Service
public class NewsNoticeHistoryBiz {

    @Autowired
    private NewsNoticeHistoryMapper newsNoticeHistoryMapper;

    public List<NewsNoticeHistory> getNoticeHistorys(NoticeHistoryQuery noticeHistoryQuery) {
        return newsNoticeHistoryMapper.getNoticeHistorys(noticeHistoryQuery);
    }

    public int addNoticeHistory(NoticeModel noticeModel) {
        long currentTiem = System.currentTimeMillis() ;
        NewsNoticeHistory newsNoticeHistory = generateNoticeHistory(noticeModel,currentTiem,currentTiem) ;
        return newsNoticeHistoryMapper.insertSelective(newsNoticeHistory);
    }

    private NewsNoticeHistory generateNoticeHistory(NoticeModel noticeModel,long crateTime,long modifyTime) {
        NewsNoticeHistory newsNoticeHistory = new NewsNoticeHistory() ;
        newsNoticeHistory.setNoticeHistoryCode(UUIDHelper.getUUID());
        newsNoticeHistory.setNoticeCode(noticeModel.getNoticeCode());
        newsNoticeHistory.setNoticeOperatorCode(noticeModel.getUserCode());
        newsNoticeHistory.setOrgCode(noticeModel.getNoticeOperatorOrg());
        newsNoticeHistory.setNoticeStatus(noticeModel.getNoticeStatus());
        newsNoticeHistory.setOrgLevel(noticeModel.getNoticeOperatorLevel());
        newsNoticeHistory.setCreateTime(crateTime);
        newsNoticeHistory.setModifyTime(modifyTime);
        if(null != noticeModel.getNoticeRemark() && !"".equals(noticeModel.getNoticeRemark())){
            newsNoticeHistory.setNoticeRemark(noticeModel.getNoticeRemark());
        }
        return newsNoticeHistory ;
    }
}
