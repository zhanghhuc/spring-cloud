package com.zssq.dao.mapper;

import com.zssq.news.dao.pojo.NewsNoticeHistory;
import com.zssq.news.model.NoticeHistoryQuery;

import java.util.List;

public interface NewsNoticeHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsNoticeHistory record);

    int insertSelective(NewsNoticeHistory record);

    NewsNoticeHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsNoticeHistory record);

    int updateByPrimaryKey(NewsNoticeHistory record);

    List<NewsNoticeHistory> getNoticeHistorys(NoticeHistoryQuery noticeHistoryQuery);
}