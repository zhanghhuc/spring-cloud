package com.zssq.dao.mapper;

import com.zssq.news.dao.pojo.NewsCommentPraise;
import com.zssq.news.model.NewsCommentPraiseModel;

public interface NewsCommentPraiseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsCommentPraise record);

    int insertSelective(NewsCommentPraise record);

    NewsCommentPraise selectByPrimaryKey(Long id);

    NewsCommentPraise selectByPraiseUser(String userCode, String commentCode);

    int updateByPrimaryKeySelective(NewsCommentPraise record);

    int updateByPrimaryKey(NewsCommentPraise record);

    int deleteNewsCommentGood(NewsCommentPraiseModel newsCommentPraiseModel);
}