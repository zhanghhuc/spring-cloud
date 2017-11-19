package com.zssq.news.dao.mapper;

import com.zssq.news.po.SysOrgInfo;
import com.zssq.news.po.SysOrgInfoExample;

import java.util.List;

public interface NewsSysOrgInfoMapper {
    int countByExample(SysOrgInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysOrgInfo record);

    int insertSelective(SysOrgInfo record);

    List<SysOrgInfo> selectByExample(SysOrgInfoExample example);

    SysOrgInfo selectByPrimaryKey(Long id);

    SysOrgInfo selectByCode(String orgCode);

    int updateByPrimaryKeySelective(SysOrgInfo record);

    int updateByPrimaryKey(SysOrgInfo record);
}