package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityJoinAdjunct;
import com.zssq.dao.pojo.ActivityJoinAdjunctExample;

public interface ActivityJoinAdjunctMapper {
    int countByExample(ActivityJoinAdjunctExample example);

    int deleteByExample(ActivityJoinAdjunctExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityJoinAdjunct record);

    int insertSelective(ActivityJoinAdjunct record);

    List<ActivityJoinAdjunct> selectByExample(ActivityJoinAdjunctExample example);

    ActivityJoinAdjunct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityJoinAdjunct record, @Param("example") ActivityJoinAdjunctExample example);

    int updateByExample(@Param("record") ActivityJoinAdjunct record, @Param("example") ActivityJoinAdjunctExample example);

    int updateByPrimaryKeySelective(ActivityJoinAdjunct record);

    int updateByPrimaryKey(ActivityJoinAdjunct record);
}