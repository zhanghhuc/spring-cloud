package com.zssq.vote.dao.mapper;

import com.zssq.vote.pojo.TransVoteUser;
import com.zssq.vote.pojo.TransVoteUserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TransVoteUserMapper {
    int countByExample(TransVoteUserExample example);

    int deleteByExample(TransVoteUserExample example);

    int insert(TransVoteUser record);

    int insertSelective(TransVoteUser record);

    List<TransVoteUser> selectByExample(TransVoteUserExample example);

    int updateByExampleSelective(@Param("record") TransVoteUser record, @Param("example") TransVoteUserExample example);

    int updateByExample(@Param("record") TransVoteUser record, @Param("example") TransVoteUserExample example);
    
    /**
     * 查询参与记录列表
     * @Function queryVoteUserByUnion
     * @Description 
     * @param voteId
     * @return
     */
    List<Map<String, Object>> queryVoteUserByUnion(Integer voteId);
    
}