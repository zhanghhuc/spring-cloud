package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityComment;
import com.zssq.dao.pojo.ActivityCommentExample;

public interface ActivityCommentMapper {
    int countByExample(ActivityCommentExample example);

    int deleteByExample(ActivityCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityComment record);

    int insertSelective(ActivityComment record);

    List<ActivityComment> selectByExampleWithBLOBs(ActivityCommentExample example);

    List<ActivityComment> selectByExample(ActivityCommentExample example);

    ActivityComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityComment record, @Param("example") ActivityCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") ActivityComment record, @Param("example") ActivityCommentExample example);

    int updateByExample(@Param("record") ActivityComment record, @Param("example") ActivityCommentExample example);

    int updateByPrimaryKeySelective(ActivityComment record);

    int updateByPrimaryKeyWithBLOBs(ActivityComment record);

    int updateByPrimaryKey(ActivityComment record);

    /**
     * @Function getCommentListForLocation
     * @Description 查询活动评论列表
     * @param paramMap
     * @return List
     */
	List<ActivityComment> getCommentListForLocation(Map<String, Object> paramMap);
	/**
	 * 查询评论列表（加载更多方式）
	 * @Function getCommentList
	 * @Description 
	 * @param paramMap
	 * @return
	 */
	List<ActivityComment> getCommentList(Map<String, Object> paramMap);
	/**
	 * 增加/减少 评论表中的回复数
	 * @Function updateReplyNum
	 * @Description 
	 * @param paramMap
	 * @return
	 */
	int updateReplyNum(Map<String, Object> paramMap);
	/**
	 * 增加/减少 评论表中的点赞数
	 * @Function updatePraiseNum
	 * @Description 
	 * @param paramMap
	 * @return
	 */
	int updatePraiseNum(Map<String, Object> paramMap);
	/**
	 * 查询评论点赞数
	 * @Function queryPraiseNum
	 * @Description 
	 * @param commentCode 评论CODE
	 * @return
	 */
	int queryPraiseNum(String commentCode);
}