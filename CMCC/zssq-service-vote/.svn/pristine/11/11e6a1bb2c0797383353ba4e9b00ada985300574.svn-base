package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.VoteInfo;
import com.zssq.dao.pojo.VoteInfoExample;

public interface VoteInfoMapper {
    int countByExample(VoteInfoExample example);

    int deleteByExample(VoteInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteInfo record);

    int insertSelective(VoteInfo record);

    List<VoteInfo> selectByExampleWithBLOBs(VoteInfoExample example);

    List<VoteInfo> selectByExample(VoteInfoExample example);

    VoteInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteInfo record, @Param("example") VoteInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") VoteInfo record, @Param("example") VoteInfoExample example);

    int updateByExample(@Param("record") VoteInfo record, @Param("example") VoteInfoExample example);

    int updateByPrimaryKeySelective(VoteInfo record);

    int updateByPrimaryKeyWithBLOBs(VoteInfo record);

    int updateByPrimaryKey(VoteInfo record);
    
	/**
	 * 投票主表参与人数+1
	 * @Function updateJoinNum
	 * @Description 
	 * @param map
	 * @return
	 */
	int updateJoinNum(Map<String, Object> map);

    /**
     * 获取我/班组/门户发起的投票列表
     *  外连接收藏表和点赞表
     * @param map
     * @return
     */
	List<VoteInfo> selectPublishVoteList(Map<String, Object> map);

	/**
	 * 查询投票权限信息
	 * 查询投票主表和权限表
	 * @param map
	 * @return
	 */
	List<VoteInfo> selectVoteInfoAndAuth(Map<String, Object> map);

	/**
	 * 查询投票详细信息
	 * @Function selectVoteDetail
	 * @Description 
	 * @param tenantCode   租户标识
	 * @param userCode     用户CODE
	 * @param voteInfoCode 投票CODE
	 * @return
	 */
	VoteInfo selectVoteDetail(String tenantCode, String userCode, String voteInfoCode);
	
	 /**
     * @Function updateCollectionNum
     * @Description 更新收藏数
     * @param paramMap        过滤条件
     * @return collectionNum  收藏数
     */
	int updateCollectionNum(Map<String, Object> paramMap);

	/**
	 * @Function updateCommentNum
	 * @Description 更新评论数
	 * @param paramMap       过滤条件
	 * @return commentNum    评论数
	 */
	int updateCommentNum(Map<String, Object> paramMap);
	/**
	 * 增加/减少  分享数
	 * @Function updateShareNum
	 * @Description 
	 * @param paramMap
	 * @return
	 */
	int updateShareNum(Map<String, Object> paramMap);

	/**
	 * @Function updatePraiseCountByVI
	 * @Description 更新点赞/取消点赞投票
	 * @param paramMap
	 */
	int updatePraiseCountByVI(Map<String, Object> paramMap);

	/**
	 * @Function updatePraiseCountByComment
	 * @Description 更新点赞/取消点赞评论时 投票主信息表中点赞数
	 * @param paramMap
	 */
	void updatePraiseCountByComment(Map<String, Object> paramMap);

	/**
	 * @Function updatePraiseCountByComment
	 * @Description 更新点赞/取消点赞评论回复时 投票主信息表中点赞数
	 * @param paramMap
	 */
	void updatePraiseCountByReply(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: updateVoteStatusList
	 * @Description: 批量更新投票信息状态
	 * @param paramMap
	 */
	void updateVoteStatusList(Map<String, Object> paramMap);

	/**
	 * 查询投票详情列表--班组归档
	 * @param map
	 * @return
	 */
	List<VoteInfo> selectVoteDetailList(Map<String, Object> map);
	
}