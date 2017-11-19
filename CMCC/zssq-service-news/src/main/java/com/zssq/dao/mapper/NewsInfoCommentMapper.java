package com.zssq.dao.mapper;

import com.zssq.news.dao.pojo.NewsInfoComment;
import com.zssq.news.model.NewsCommentModel;
import com.zssq.news.model.NewsCommentQuery;

import java.util.List;

public interface NewsInfoCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsInfoComment record);

    int insertSelective(NewsInfoComment record);

    NewsInfoComment selectByCommentCode(String commentCode);

    int updateByPrimaryKeySelective(NewsInfoComment record);

    int updateByPrimaryKey(NewsInfoComment record);

    Integer selectCount(NewsCommentQuery newsCommentQuery);

    List<NewsInfoComment> selectList(NewsCommentQuery newsCommentQuery);

    int modifyNewsCommentShield(NewsCommentModel newsCommentModel);

    List<NewsInfoComment> selectComments(NewsCommentQuery newsCommentQuery);

    List<NewsCommentModel> selectCommentsAndReply(NewsCommentQuery newsCommentQuery);

    boolean deleteNewsComment(NewsCommentModel newsCommentModel);

    NewsInfoComment getNewsCommentByQuery(NewsCommentQuery newsCommentQuery);
}