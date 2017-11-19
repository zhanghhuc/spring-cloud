package com.zssq.vote.dao.mapper;

import com.zssq.vote.pojo.TransVoteOptions;
import com.zssq.vote.pojo.TransVoteOptionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransVoteOptionsMapper {
    int countByExample(TransVoteOptionsExample example);

    int deleteByExample(TransVoteOptionsExample example);

    int deleteByPrimaryKey(Integer optionsId);

    int insert(TransVoteOptions record);

    int insertSelective(TransVoteOptions record);

    List<TransVoteOptions> selectByExample(TransVoteOptionsExample example);

    TransVoteOptions selectByPrimaryKey(Integer optionsId);

    int updateByExampleSelective(@Param("record") TransVoteOptions record, @Param("example") TransVoteOptionsExample example);

    int updateByExample(@Param("record") TransVoteOptions record, @Param("example") TransVoteOptionsExample example);

    int updateByPrimaryKeySelective(TransVoteOptions record);

    int updateByPrimaryKey(TransVoteOptions record);
}