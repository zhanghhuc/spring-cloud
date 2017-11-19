package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityJoin;
import com.zssq.dao.pojo.ActivityJoinExample;

public interface ActivityJoinMapper {
    int countByExample(ActivityJoinExample example);

    int deleteByExample(ActivityJoinExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityJoin record);

    int insertSelective(ActivityJoin record);

    List<ActivityJoin> selectByExample(ActivityJoinExample example);

    ActivityJoin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityJoin record, @Param("example") ActivityJoinExample example);

    int updateByExample(@Param("record") ActivityJoin record, @Param("example") ActivityJoinExample example);

    int updateByPrimaryKeySelective(ActivityJoin record);

    int updateByPrimaryKey(ActivityJoin record);
    
    List<ActivityJoin> getActivityJoinList(Map<String, Object> paramMap);
}