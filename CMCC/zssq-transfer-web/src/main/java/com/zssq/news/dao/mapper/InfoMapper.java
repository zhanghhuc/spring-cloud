package com.zssq.news.dao.mapper;


import com.zssq.news.po.Info;
import com.zssq.news.po.InfoExample;

import java.util.List;

public interface InfoMapper {
    int countByExample(InfoExample example);

    int insert(Info record);

    int insertSelective(Info record);

    List<Info> selectByExampleWithBLOBs(InfoExample example);

    List<Info> selectByExample(InfoExample example);
}