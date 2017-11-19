package com.zssq.vote.dao.mapper;

import com.zssq.vote.pojo.CountResult;
import com.zssq.vote.pojo.VoteComment;
import com.zssq.vote.pojo.VoteCommentExample;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteCommentMapper {
    int countByExample(VoteCommentExample example);

    int deleteByExample(VoteCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteComment record);

    int insertSelective(VoteComment record);

    List<VoteComment> selectByExampleWithBLOBs(VoteCommentExample example);

    List<VoteComment> selectByExample(VoteCommentExample example);

    VoteComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteComment record, @Param("example") VoteCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") VoteComment record, @Param("example") VoteCommentExample example);

    int updateByExample(@Param("record") VoteComment record, @Param("example") VoteCommentExample example);

    int updateByPrimaryKeySelective(VoteComment record);

    int updateByPrimaryKeyWithBLOBs(VoteComment record);

    int updateByPrimaryKey(VoteComment record);

	void batchInsert(ArrayList<VoteComment> commentList);

	List<CountResult> countComment();
	
}