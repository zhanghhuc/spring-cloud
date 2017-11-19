package com.zssq.dao.mapper;

import com.zssq.news.dao.pojo.NewsPraise;
import com.zssq.news.model.NewsPraiseModel;

public interface NewsPraiseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsPraise record);

    int insertSelective(NewsPraise record);

    NewsPraise selectByPraiseCode(String praiseCode);

    NewsPraise selectPraiseByPraiseUser(String userCode,String newsCode);

    int updateByPrimaryKeySelective(NewsPraise record);

    int updateByPrimaryKey(NewsPraise record);

    int deleteNewsInfoGood(NewsPraiseModel newsPraiseModel);
}