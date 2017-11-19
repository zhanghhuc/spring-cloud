package com.zssq.vote.dao.mapper;

import com.zssq.vote.pojo.TransVoteComment;
import com.zssq.vote.pojo.TransVoteCommentExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TransVoteCommentMapper {
    int countByExample(TransVoteCommentExample example);

    int deleteByExample(TransVoteCommentExample example);

    int deleteByPrimaryKey(Long commentId);

    int insert(TransVoteComment record);

    int insertSelective(TransVoteComment record);

    List<TransVoteComment> selectByExample(TransVoteCommentExample example);

    TransVoteComment selectByPrimaryKey(Long commentId);

    int updateByExampleSelective(@Param("record") TransVoteComment record, @Param("example") TransVoteCommentExample example);

    int updateByExample(@Param("record") TransVoteComment record, @Param("example") TransVoteCommentExample example);

    int updateByPrimaryKeySelective(TransVoteComment record);

    int updateByPrimaryKey(TransVoteComment record);
    
    
    List<Map<String, Object>> selectComment(int voteId);

	List<Map<String, Object>> selectReply(int cid);
}