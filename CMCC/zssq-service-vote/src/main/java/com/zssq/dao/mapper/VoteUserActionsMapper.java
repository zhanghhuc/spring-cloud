package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.VoteUserActions;
import com.zssq.dao.pojo.VoteUserActionsExample;

public interface VoteUserActionsMapper {
    int countByExample(VoteUserActionsExample example);

    int deleteByExample(VoteUserActionsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteUserActions record);

    int insertSelective(VoteUserActions record);

    List<VoteUserActions> selectByExample(VoteUserActionsExample example);

    VoteUserActions selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteUserActions record, @Param("example") VoteUserActionsExample example);

    int updateByExample(@Param("record") VoteUserActions record, @Param("example") VoteUserActionsExample example);

    int updateByPrimaryKeySelective(VoteUserActions record);

    int updateByPrimaryKey(VoteUserActions record);
}