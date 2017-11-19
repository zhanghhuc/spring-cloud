package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.StatisticPortal;

public interface StatisticPortalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StatisticPortal record);

    int insertSelective(StatisticPortal record);

    StatisticPortal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StatisticPortal record);

    int updateByPrimaryKey(StatisticPortal record);
    
    List<StatisticPortal> selectPage(StatisticPortal record);
    
    List<StatisticPortal> selectPageCount(StatisticPortal record);
}