package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.news.model.NewsModel;
import com.zssq.news.model.NewsQuery;

import com.zssq.news.dao.pojo.NewsInfoContent;

public interface NewsInfoContentMapper {
    Integer selectCount(NewsQuery example);

    int deleteByNewsCode(NewsModel newsModel);

    int insert(NewsInfoContent record);

    List<NewsInfoContent> selectByQuery(NewsQuery example);

    int updateNews(NewsInfoContent record);

    NewsInfoContent selectByNewsCode(String newsCode);

    NewsInfoContent selectByNewsSort(NewsQuery newsQuery);

    List<NewsInfoContent> selectByQueryPc(NewsQuery newsModel);

    NewsInfoContent getNewsInfoDetail(NewsQuery newsModel);

    int updateNewsHidden(NewsInfoContent newsInfoContent);

    Integer selectMinSort();

    int selectCollectNewsCount(NewsQuery newsQuery);

    List<NewsInfoContent> selectUserCollectNews(NewsQuery newsQuery);

    NewsInfoContent selectByArchivedCodes(String newsCode);

    int updateArchiveNews(Map<String, Object> maps);

    int updateNewsCommentCount(Map<String, Object> maps);

}