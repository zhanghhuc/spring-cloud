package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.GetMyJoinVoteListEntity;
import com.zssq.dao.pojo.VoteJoin;
import com.zssq.dao.pojo.VoteJoinExample;

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

    /**
     * 获取我参与的投票列表
     * @param entity 查询参数实体
     * @return
     */
	List<VoteJoin> selectMyJoinVoteList(@Param("record") GetMyJoinVoteListEntity entity);
}