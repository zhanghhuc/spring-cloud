package com.zssq.dao.mapper;

import com.zssq.news.dao.pojo.NewsReply;
import com.zssq.news.model.NewsReplyModel;
import com.zssq.news.model.NewsReplyQuery;

import java.util.List;

public interface NewsReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsReply record);

    int insertSelective(NewsReply record);

    NewsReply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsReply record);

    int updateByPrimaryKeyWithBLOBs(NewsReply record);

    int updateByPrimaryKey(NewsReply record);

    int modifyNewsReplyShield(NewsReplyModel newsReplyModel);

    Integer selectCount(NewsReplyQuery newsReplyQuery);

    List<NewsReply> selectList(NewsReplyQuery newsReplyQuery);

    List<NewsReply> selectReplyList(NewsReplyQuery newsReplyQuery);

    boolean deleteNewsReply(NewsReplyModel newsReplyModel);

    NewsReply selectByReplyCode(String newsReplyCode);

}