package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.VoteInfo;
import com.zssq.exceptions.BusinessException;

/**
 * 投票模块提供给其他模块的接口
 * @ClassName IVoteThirdService
 * @Description 
 * @author liurong
 * @date 2017年4月7日 下午6:35:04
 * @version 1.0
 * @since JDK 1.7
 */
public interface IVoteThirdService {
	public enum Operate {
		// 删除
		DELETE, 
		// 恢复
		RECOVERY
	}
	/**
	 * 删除/恢复信息
	 * @Function deleteOrNot
	 * @Description 
	 * @param tenantCode  租户标识
	 * @param subjectCode 信息CODE
	 * @param subjectType 信息类型：1-应用；2-应用的评论；3-评论的回复
	 * @param isDelete    是否删除：0-恢复；1-删除
	 * @return
	 * @throws BusinessException
	 */
	public boolean deleteOrNot(String tenantCode, String subjectCode, Byte subjectType, Byte isDelete) throws BusinessException;
	/**
	 * 删除/恢复投票信息
	 * @Function deleteOrNotVote
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param opt        操作标识：DELETE(删除);RECOVERY(恢复)
	 * @param code       投票信息CODE
	 * @return
	 * @throws BusinessException
	 */
	public int deleteOrNotVote(String tenantCode, Operate opt, String code) throws BusinessException;
	/**
	 * 删除/恢复投票的评论信息
	 * @Function deleteOrNotComment
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param opt        操作标识：DELETE(删除);RECOVERY(恢复)
	 * @param code       评论信息CODE
	 * @return
	 * @throws BusinessException
	 */
	public int deleteOrNotComment(String tenantCode, Operate opt, String code) throws BusinessException;
	/**
	 * 删除/恢复投票的回复信息
	 * @Function deleteOrNotReply
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param opt        操作标识：DELETE(删除);RECOVERY(恢复)
	 * @param code       回复信息CODE
	 * @return
	 * @throws BusinessException
	 */
	public int deleteOrNotReply(String tenantCode, Operate opt, String code) throws BusinessException;
	/**
	 * 减少  count个分享数
	 * @Function updateDecreaseShareNumByCode
	 * @Description 
	 * @param code      投票主表CODE
	 * @param count     变量
	 * @throws BusinessException
	 */
	public void updateDecreaseShareNumByCode(String code, int count) throws BusinessException;
	
	/**
	 * 班组投票归档
	 * @param teamCode 班组code
	 * @param startNum 起始行号
	 * @param endNum 结束行号
	 * @return
	 * @throws BusinessException
	 */
	public List<VoteInfo> sumupTeamVote(String teamCode,Integer startNum,Integer endNum)throws BusinessException;

}
