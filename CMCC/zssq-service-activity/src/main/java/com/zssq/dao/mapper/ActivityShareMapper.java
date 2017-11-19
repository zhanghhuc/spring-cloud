package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityShare;
import com.zssq.dao.pojo.ActivityShareExample;

public interface ActivityShareMapper {
    int countByExample(ActivityShareExample example);

    int deleteByExample(ActivityShareExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityShare record);

    int insertSelective(ActivityShare record);

    List<ActivityShare> selectByExample(ActivityShareExample example);

    ActivityShare selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityShare record, @Param("example") ActivityShareExample example);

    int updateByExample(@Param("record") ActivityShare record, @Param("example") ActivityShareExample example);

    int updateByPrimaryKeySelective(ActivityShare record);

    int updateByPrimaryKey(ActivityShare record);
}