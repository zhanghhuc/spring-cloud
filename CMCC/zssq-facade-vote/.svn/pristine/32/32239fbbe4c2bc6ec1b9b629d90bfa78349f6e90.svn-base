package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.GetMyJoinVoteListEntity;
import com.zssq.dao.pojo.GetVoteInfoEntity;
import com.zssq.dao.pojo.JoinVoteEntity;
import com.zssq.dao.pojo.VoteCollection;
import com.zssq.dao.pojo.VoteComment;
import com.zssq.dao.pojo.VoteCommentReply;
import com.zssq.dao.pojo.VoteDetailModel;
import com.zssq.dao.pojo.VoteInfo;
import com.zssq.dao.pojo.VoteJoinAuth;
import com.zssq.dao.pojo.VoteOptions;
import com.zssq.dao.pojo.VotePraise;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageInfo;

/**
 * 投票接口服务
 * @ClassName IVoteService
 * @Description 
 * @author liurong
 * @date 2017年3月30日 上午10:57:19
 * @version 1.0
 * @since JDK 1.7
 */
public interface IVoteService {

	/**
	 * 发起投票
	 * @param voteInfo 投票对象
	 * @param options 投票选项对象
	 * @param joinAuth 权限对象
	 * @return 投票code
	 */
	String insertVoteInfo(VoteInfo voteInfo, List<VoteOptions> options, VoteJoinAuth joinAuth)throws BusinessException;

	/**
	 * 参与投票
	 * @param entity 参数实体
	 * @return
	 * @throws BusinessException
	 */
	String insertJoinVote(JoinVoteEntity entity) throws BusinessException;

	/**
	 * 获取我/班组/门户发起的投票列表
	 * @param info投票实体
	 * @param page分页实体
	 * @return
	 * @throws BusinessException
	 */
	PageInfo selectPublishVoteList(VoteInfo info, PageInfo page)throws BusinessException;

	/**
	 * 获取我参与的投票列表
	 * @param entity 查询参数实体
	 * @return
	 * @throws BusinessException
	 */
	PageInfo getMyJoinVoteList(GetMyJoinVoteListEntity entity)throws BusinessException;

	/**
	 * 门户页面获取投票列表
	 * @Function getGatewayVoteList
	 * @Description 
	 * @param tenantCode      租户编码
	 * @param sponsorOrgCode  门户组织机构编码
	 * @param userCode        查询的用户（判断是否点赞，是否收藏）
	 * @param pageInfo
	 * @return
	 * @throws BusinessException
	 */
	PageInfo getGatewayVoteList(String tenantCode, String sponsorOrgCode, String userCode, Integer perPage) throws BusinessException;

	/**
	 *  查看投票详情
	 * @param entity 参数实体
	 * @return
	 * @throws BusinessException
	 */
	VoteDetailModel selectVoteInfoDetail(GetVoteInfoEntity entity)throws BusinessException;
	/**
	 * 逻辑删除投票主信息
	 * @Function deleteVoteInfo
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param voteInfoCode 投票主表CODE
	 * @return
	 * @throws BusinessException
	 */
	int deleteVoteInfo(String tenantCode, String voteInfoCode) throws BusinessException;
	
	
	/**
	 * @Function updateCollectionStatus
	 * @Description 收藏/取消收藏
	 * @param voteCollection 投票收藏表实体
	 * @return collectionNum 最新收藏数
	 * @throws BusinessException
	 */
	Integer updateCollectionStatus(VoteCollection voteCollection) throws BusinessException;

	/**
	 * @Function updateVoteInfoStatus
	 * @Description 提前结束投票
	 * @param voteInfo 投票信息实体
	 * @return 是否更新成功 ：
	 *         true  成功 
	 *         false 失败
	 * @throws BusinessException
	 */
	boolean updateVoteInfoStatus(VoteInfo voteInfo)throws BusinessException;

	/**
	 * @Function addComment
	 * @Description 发表评论
	 * @param voteComment 	投票评论对象
	 * @return commentCode  评论code
	 * @throws BusinessException
	 */
	String addComment(VoteComment voteComment)throws BusinessException;

	/**
	 * @Function addReply
	 * @Description 发表回复
	 * @param voteCommentReply 投票评论回复对象
	 * @return replyCode       回复Code
	 * @throws BusinessException
	 */
	String addReply(VoteCommentReply voteCommentReply)throws BusinessException;

	/**
	 * @Function getVoteCommentList
	 * @Description 查询投票评论列表
	 * @param voteComment    投票评论对象
	 * @return PageInfo      封装结果集
	 * @throws BusinessException
	 */
	PageInfo getVoteCommentList(VoteComment voteComment)throws BusinessException;

	/**
	 * @Function getVoteCommentReplyList
	 * @Description 查询投票评论回复列表
	 * @param voteCommentReply  投票评论回复对象
	 * @return pageInfo         封装结果集
	 * @throws BusinessException
	 */
	PageInfo getVoteCommentReplyList(VoteCommentReply voteCommentReply)throws BusinessException;

	/**
	 * @Function deleteVoteComment
	 * @Description 删除评论
	 * @param infoCode   评论Code
	 * @param voteInfoCode   投票Code
	 * @param tenantCode 租户Code
	 * @throws BusinessException
	 */
	int deleteVoteComment(String infoCode, String voteInfoCode, String tenantCode)throws BusinessException;

	/**
	 * @Function deleteVoteCommentReply
	 * @Description 删除评论回复
	 * @param infoCode    评论回复Code
	 * @param userCode    用户Code
	 * @param tenantCode  租户Code
	 * @throws BusinessException
	 */
	void deleteVoteCommentReply(String infoCode, String userCode, String tenantCode)throws BusinessException;

	/**
	 * @Function getVoteInfoByCode
	 * @Description 根据投票信息Code查询投票详情
	 * @param code         投票信息Code
	 * @param tenantCode   租户Code
	 * @return VoteInfo    投票详情
	 * @throws BusinessException
	 */
	VoteInfo getVoteInfoByCode(String code,String tenantCode)throws BusinessException;

	/**
	 * 删除投票
	 * @Function deleteVote
	 * @Description 
	 * @param tenantCode   租户标识
	 * @param voteInfoCode 投票CODE
	 * @throws BusinessException
	 */
	void deleteVote(String tenantCode, String voteInfoCode)throws BusinessException;

	/**
	 * @Function updatePraiseStatus
	 * @Description 点赞/取消点赞投票、评论、回复
	 * @param votePraise   投票点赞对象
	 * @return praiseNum   最新点赞数
	 * @throws BusinessException
	 */
	Integer updatePraiseStatus(VotePraise votePraise)throws BusinessException;
	/**
	 * 根据CODE获取评论的详情
	 * @Function getCommentByCode
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param code       唯一键CODE
	 * @return
	 * @throws BusinessException
	 */
	VoteComment getCommentByCode(String code,String tenantCode) throws BusinessException;
	/**
	 * 根据CODE获取回复的详情
	 * @Function getCommentReplyByCode
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param code       唯一键CODE
	 * @return
	 * @throws BusinessException
	 */
	VoteCommentReply getCommentReplyByCode(String tenantCode, String code) throws BusinessException;
	/**
	 * 增加  count个分享数
	 * @Function updateIncreaseShareNumByCode
	 * @Description 
	 * @param code      投票主表CODE
	 * @param count     变量
	 * @throws BusinessException
	 */
	void updateIncreaseShareNumByCode(String code, int count) throws BusinessException;

	/**
	 * 查询评论详情
	 * @param commentCode 评论code
	 * @return
	 * @throws BusinessException
	 */
	VoteComment selectCommentInfo(String commentCode)throws BusinessException;

	/**
	 * 查询回复详情
	 * @param replyCode 回复code
	 * @return
	 * @throws BusinessException
	 */
	VoteCommentReply selectReply(String replyCode)throws BusinessException;

	/**
	 * @Function getMyCollectionList
	 * @Description 查询我的收藏列表
	 * @param voteCollection
	 * @return
	 * @throws BusinessException
	 */
	List<VoteInfo> getMyCollectionList(VoteCollection voteCollection)throws BusinessException;
	
}
