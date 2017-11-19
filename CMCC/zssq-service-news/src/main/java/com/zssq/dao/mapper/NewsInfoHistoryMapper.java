package com.zssq.dao.mapper;

import com.zssq.news.dao.pojo.NewsInfoHistory;
import com.zssq.news.model.NewsHistoryQuery;

import java.util.List;

public interface NewsInfoHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsInfoHistory record);

    int insertSelective(NewsInfoHistory record);

    NewsInfoHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsInfoHistory record);

    int updateByPrimaryKey(NewsInfoHistory record);

    List<NewsInfoHistory> selectList(NewsHistoryQuery newsHistoryQuery);
}