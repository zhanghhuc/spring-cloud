package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityPraise;
import com.zssq.dao.pojo.ActivityPraiseExample;

public interface ActivityPraiseMapper {
    int countByExample(ActivityPraiseExample example);

    int deleteByExample(ActivityPraiseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityPraise record);

    int insertSelective(ActivityPraise record);

    List<ActivityPraise> selectByExample(ActivityPraiseExample example);

    ActivityPraise selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityPraise record, @Param("example") ActivityPraiseExample example);

    int updateByExample(@Param("record") ActivityPraise record, @Param("example") ActivityPraiseExample example);

    int updateByPrimaryKeySelective(ActivityPraise record);

    int updateByPrimaryKey(ActivityPraise record);
}