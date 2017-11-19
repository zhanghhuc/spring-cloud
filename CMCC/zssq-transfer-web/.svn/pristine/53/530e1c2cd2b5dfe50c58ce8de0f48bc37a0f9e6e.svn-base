package com.zssq.vote.dao.mapper;

import com.zssq.vote.pojo.CountResult;
import com.zssq.vote.pojo.VoteJoin;
import com.zssq.vote.pojo.VoteJoinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteJoinMapper {
    int countByExample(VoteJoinExample example);

    int deleteByExample(VoteJoinExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteJoin record);

    int insertSelective(VoteJoin record);

    List<VoteJoin> selectByExample(VoteJoinExample example);

    VoteJoin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteJoin record, @Param("example") VoteJoinExample example);

    int updateByExample(@Param("record") VoteJoin record, @Param("example") VoteJoinExample example);

    int updateByPrimaryKeySelective(VoteJoin record);

    int updateByPrimaryKey(VoteJoin record);

	List<CountResult> countJoin();
}