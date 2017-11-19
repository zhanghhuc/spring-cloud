package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.GetVoteInfoEntity;
import com.zssq.dao.pojo.VoteComment;
import com.zssq.dao.pojo.VoteCommentReply;
import com.zssq.dao.pojo.VoteDetailModel;
import com.zssq.dao.pojo.VoteInfo;
import com.zssq.dao.pojo.VoteJoinAuth;
import com.zssq.dao.pojo.VoteOptions;
import com.zssq.dao.pojo.VoteReview;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageInfo;

/**
 * 投票管理端service接口
 * @author POWER
 *
 */
public interface IVoteManageService {

	
	/**
	 * 发起投票
	 * @param voteInfo 投票对象
	 * @param options 投票选项对象
	 * @param joinAuth 权限对象
	 * @return 投票code
	 */
	String insertVoteInfo(VoteInfo voteInfo, List<VoteOptions> options, VoteJoinAuth joinAuth)throws BusinessException;

	/**
	 * 获取管理员发起的投票列表
	 * @param info 投票对象
	 * @param page 分页对象
	 * @return
	 * @throws BusinessException
	 */
	PageInfo selectVoteList(VoteInfo info, PageInfo page)throws BusinessException;

	/**
	 * 查询投票信息
	 * @param info 投票对象
	 * @return
	 * @throws BusinessException
	 */
	VoteInfo selectVoteInfo(VoteInfo info)throws BusinessException;

	/**
	 * 审核投票
	 * @param review 审核实体
	 * @return
	 * @throws BusinessException
	 */
	String insertVoteReview(VoteReview review)throws BusinessException;
	
	
	/**
	 * @Function getVoteCommentFixedList
	 * @Description 获取投票评论列表 定位
	 * @param voteComment     评论对象
	 * @return PageInfo       结果集
	 * @throws BusinessException
	 */
	PageInfo getVoteCommentFixedList(VoteComment voteComment) throws BusinessException;

	/**
	 * @Function getVoteReplyFixedList
	 * @Description 获取投票评论回复列表 定位
	 * @param voteCommentReply  评论回复对象
	 * @return PageInfo         结果集
	 * @throws BusinessException
	 */
	PageInfo getVoteReplyFixedList(VoteCommentReply voteCommentReply)throws BusinessException;

	/**
	 * @Function updateVoteInfoStatus
	 * @Description 终止/提前结束投票  
	 * @param voteInfo  投票信息对象
	 * @return boolean : true  : 成功
	 *                   false : 失败
	 * @throws BusinessException
	 */
	boolean updateVoteInfoStatus(VoteInfo voteInfo)throws BusinessException;

	/**
	 * @Function updateVoteInfo
	 * @Description  更新投票信息
	 * @param voteInfo      投票信息对象
	 * @param options       选项对象
	 * @param joinAuth      参与范围对象
	 * @return voteInfoCode 更新对象Code
	 * @throws BusinessException
	 */
	String updateVoteInfo(VoteInfo voteInfo, List<VoteOptions> options, VoteJoinAuth joinAuth)throws BusinessException;

	/**
	 * @Function selectVoteInfoDetail
	 * @Description 查询投票详情 
	 * @param entity 
	 * @return VoteDetailModel
	 * @throws BusinessException
	 */
	VoteDetailModel selectVoteInfoDetail(GetVoteInfoEntity entity)throws BusinessException;
	/**
	 * 提交/撤销  投票审核
	 * @Function updateSubmitOrNotExamine
	 * @Description 
	 * @param tenantCode   租户标识
	 * @param orgCode      组织机构编码
	 * @param voteInfoCode 投票信息CODE
	 * @param operateType  操作标识：1-提交审核；2-撤销审核
	 * @throws BusinessException
	 */
	void updateSubmitOrNotExamine(String tenantCode, String orgCode, String voteInfoCode, String operateType) throws BusinessException;
	/**
	 * 查询投票参与范围信息
	 * @Function selectRangeName
	 * @Description 
	 * @param voteInfoCode 投票信息CODE
	 * @return
	 * @throws BusinessException
	 */
	VoteJoinAuth selectVoteJoinAuth(String voteInfoCode) throws BusinessException;

	/**
	 * 内容监控-查询投票列表
	 * @param tenantCode
	 * @param orgCode
	 * @param page
	 * @return
	 * @throws BusinessException
	 */
	PageInfo selectVoteListForMonitor(String tenantCode, String orgCode, PageInfo page)throws BusinessException;

}
