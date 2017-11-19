package com.zssq.dao.mapper;

import com.zssq.news.dao.pojo.NewsCollect;
import com.zssq.news.model.NewsCollectModel;

public interface NewsCollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsCollect record);

    int insertSelective(NewsCollect record);

    NewsCollect selectByPrimaryKey(Long id);

    NewsCollect selectByCollectUser(String userCode,String newsCode);

    int updateByPrimaryKeySelective(NewsCollect record);

    int updateByPrimaryKey(NewsCollect record);

    int deleteNewsCollect(NewsCollectModel newsCollectModel);
}