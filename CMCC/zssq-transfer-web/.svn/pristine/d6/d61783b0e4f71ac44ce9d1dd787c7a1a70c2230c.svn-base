package com.zssq.vote.dao.mapper;

import com.zssq.vote.pojo.VoteJoinAuth;
import com.zssq.vote.pojo.VoteJoinAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteJoinAuthMapper {
    int countByExample(VoteJoinAuthExample example);

    int deleteByExample(VoteJoinAuthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteJoinAuth record);

    int insertSelective(VoteJoinAuth record);

    List<VoteJoinAuth> selectByExample(VoteJoinAuthExample example);

    VoteJoinAuth selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteJoinAuth record, @Param("example") VoteJoinAuthExample example);

    int updateByExample(@Param("record") VoteJoinAuth record, @Param("example") VoteJoinAuthExample example);

    int updateByPrimaryKeySelective(VoteJoinAuth record);

    int updateByPrimaryKey(VoteJoinAuth record);

	void batchInsert(List<VoteJoinAuth> authList);
	
}