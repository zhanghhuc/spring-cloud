package com.zssq.vote.dao.mapper;

import com.zssq.vote.pojo.VoteOptions;
import com.zssq.vote.pojo.VoteOptionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteOptionsMapper {
    int countByExample(VoteOptionsExample example);

    int deleteByExample(VoteOptionsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteOptions record);

    int insertSelective(VoteOptions record);

    List<VoteOptions> selectByExample(VoteOptionsExample example);

    VoteOptions selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteOptions record, @Param("example") VoteOptionsExample example);

    int updateByExample(@Param("record") VoteOptions record, @Param("example") VoteOptionsExample example);

    int updateByPrimaryKeySelective(VoteOptions record);

    int updateByPrimaryKey(VoteOptions record);
}