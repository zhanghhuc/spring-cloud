package com.zssq.dao.mapper;

import com.zssq.news.dao.pojo.NewsCommentPraise;
import com.zssq.news.dao.pojo.NewsReplyPraise;
import com.zssq.news.model.NewsReplyPraiseModel;

public interface NewsReplyPraiseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsReplyPraise record);

    int insertSelective(NewsReplyPraise record);

    NewsReplyPraise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsReplyPraise record);

    int updateByPrimaryKey(NewsReplyPraise record);

    int deleteNewsReplyGood(NewsReplyPraiseModel newsReplyPraiseModel);

    NewsCommentPraise selectByPraiseUser(String userCode, String replyCode);
}