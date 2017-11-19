package com.zssq.news.dao.mapper;


import com.zssq.news.po.SysUserInfo;
import com.zssq.news.po.SysUserInfoExample;

import java.util.List;

public interface NewsSysUserInfoMapper {
    int countByExample(SysUserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserInfo record);

    int insertSelective(SysUserInfo record);

    List<SysUserInfo> selectByExample(SysUserInfoExample example);

    SysUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserInfo record);

    int updateByPrimaryKey(SysUserInfo record);
}