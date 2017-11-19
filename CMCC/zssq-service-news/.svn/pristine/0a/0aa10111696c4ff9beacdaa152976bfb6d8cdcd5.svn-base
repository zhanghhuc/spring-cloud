package com.zssq.dao.mapper;

import com.zssq.news.dao.pojo.NewsNoticeContent;
import com.zssq.news.model.NoticeModel;
import com.zssq.news.model.NoticeQuery;

import java.util.List;

public interface NewsNoticeContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsNoticeContent record);

    int insertSelective(NewsNoticeContent record);

    NewsNoticeContent selectByPrimaryKey(Long id);

    int updateByCodeSelective(NewsNoticeContent record);

    int updateByCode(NewsNoticeContent record);

    Integer selectCount(NoticeQuery noticeQuery);

    List<NewsNoticeContent> selectByQuery(NoticeQuery example);

    int deleteByCode(NoticeModel noticeModel);

    NewsNoticeContent selectByCode(String noticeCode);

    NewsNoticeContent selectByNoticeSort(NoticeQuery noticeQuery);

    NewsNoticeContent getNoticeDetail(NoticeQuery noticeQuery);

    int updateNewsNoticeHidden(NewsNoticeContent newsNoticeContent);

    Integer selectMinSort();
}