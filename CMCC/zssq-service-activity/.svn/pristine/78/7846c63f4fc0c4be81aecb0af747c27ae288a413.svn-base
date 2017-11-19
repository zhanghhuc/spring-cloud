package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityCommentReply;
import com.zssq.dao.pojo.ActivityCommentReplyExample;

public interface ActivityCommentReplyMapper {
    int countByExample(ActivityCommentReplyExample example);

    int deleteByExample(ActivityCommentReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityCommentReply record);

    int insertSelective(ActivityCommentReply record);

    List<ActivityCommentReply> selectByExampleWithBLOBs(ActivityCommentReplyExample example);

    List<ActivityCommentReply> selectByExample(ActivityCommentReplyExample example);

    ActivityCommentReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityCommentReply record, @Param("example") ActivityCommentReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") ActivityCommentReply record, @Param("example") ActivityCommentReplyExample example);

    int updateByExample(@Param("record") ActivityCommentReply record, @Param("example") ActivityCommentReplyExample example);

    int updateByPrimaryKeySelective(ActivityCommentReply record);

    int updateByPrimaryKeyWithBLOBs(ActivityCommentReply record);

    int updateByPrimaryKey(ActivityCommentReply record);

    /**
     * @Function getCommentReplyListForLocation
     * @Description 查询评论回复列表 定位
     * @param paramMap
     * @return
     */
	List<ActivityCommentReply> getCommentReplyListForLocation(Map<String, Object> paramMap);
	/**
	 * 查询评论的回复列表(加载更多方式)
	 * @Function getCommentReplyList
	 * @Description 
	 * @param paramMap
	 * @return
	 */
	List<ActivityCommentReply> getCommentReplyList(Map<String, Object> paramMap);
	/**
	 * 查询加载更多 剩余记录数
	 * @Function getCommentReplySurplusCount
	 * @Description 
	 * @param paramMap
	 * @return
	 */
	int getCommentReplySurplusCount(Map<String, Object> paramMap);
	/**
	 * @Function getCommentReplySurplusCountForLocation
	 * @Description 查询评论回复 剩余条数
	 * @param paramMap
	 * @return
	 */
	int getCommentReplySurplusCountForLocation(Map<String, Object> paramMap);
	/**
	 * 更新回复的点赞数
	 * @Function updatePraiseNum
	 * @Description 
	 * @param paramMap
	 * @return
	 */
	int updatePraiseNum(Map<String, Object> paramMap);
	/**
	 * 查询回复的点赞数
	 * @Function queryPraiseNum
	 * @Description 
	 * @param replyCode 回复CODE
	 * @return
	 */
	int queryPraiseNum(String replyCode);
}