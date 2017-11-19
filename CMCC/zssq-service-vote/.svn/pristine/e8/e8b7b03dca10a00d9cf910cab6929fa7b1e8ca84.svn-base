package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.VoteCommentReply;
import com.zssq.dao.pojo.VoteCommentReplyExample;

public interface VoteCommentReplyMapper {
    int countByExample(VoteCommentReplyExample example);

    int deleteByExample(VoteCommentReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteCommentReply record);

    int insertSelective(VoteCommentReply record);

    List<VoteCommentReply> selectByExampleWithBLOBs(VoteCommentReplyExample example);

    List<VoteCommentReply> selectByExample(VoteCommentReplyExample example);

    VoteCommentReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteCommentReply record, @Param("example") VoteCommentReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") VoteCommentReply record, @Param("example") VoteCommentReplyExample example);

    int updateByExample(@Param("record") VoteCommentReply record, @Param("example") VoteCommentReplyExample example);

    int updateByPrimaryKeySelective(VoteCommentReply record);

    int updateByPrimaryKeyWithBLOBs(VoteCommentReply record);

    int updateByPrimaryKey(VoteCommentReply record);

    /**
     * @Function getCommentReplyList
     * @Description  查询评论回复列表
     * @param paramMap  过滤条件
     * @return  List    评论回复列表
     */
	List<VoteCommentReply> getCommentReplyList(Map<String, Object> paramMap);

	/**
	 * @Function getCommentReplySurplusCount
	 * @Description 查询加载更多 剩余记录数
	 * @param paramMap      过滤条件
	 * @return surplusCount 剩余记录数
	 */
	int getCommentReplySurplusCount(Map<String, Object> paramMap);

	/**
	 * @Function deleteAllReplyByComment
	 * @Description 删除/恢复所有的回复
	 * @param paramMap  过滤条件
	 * @return
	 */
	int updateIsDelete(Map<String, Object> paramMap);

	/**
	 * @Function updatePraiseCountByReply
	 * @Description 点赞/取消点赞评论回复时 更新点赞数
	 * @param paramMap
	 * @return
	 */
	int updatePraiseCountByReply(Map<String, Object> paramMap);
	
	/**
	 * @Function getCommentReplyListForLocation
	 * @Description 查询评论回复列表 --定位
	 * @param paramMap   过滤条件
	 * @return List      评论回复列表
	 */
	List<VoteCommentReply> getCommentReplyListForLocation(Map<String, Object> paramMap);

	/**
	 * @Function getCommentReplySurplusCountForLocation
	 * @Description 查询加载更多 剩余记录数 定位
	 * @param paramMap      过滤条件
	 * @return surplusCount 剩余记录数
	 */
	int getCommentReplySurplusCountForLocation(Map<String, Object> paramMap);

	/**
	 * 获取评论的所有回复
	 * @param code
	 * @return
	 */
	List<VoteCommentReply> selectVoteReply(String code,String commentCode);
	
}