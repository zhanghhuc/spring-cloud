package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.ActivityComment;
import com.zssq.dao.pojo.ActivityCommentReply;
import com.zssq.dao.pojo.ActivityJoin;
import com.zssq.dao.pojo.ActivityPrize;
import com.zssq.dao.pojo.ActivityWinningRecord;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageInfo;

public interface IActivityModelThreeService {
	
	/**
	 * @Function updateIsWinning
	 * @Description 设为/撤销获奖作品
	 * @param activityJoin
	 * @throws BusinessException
	 */
	public void updateIsWinning(ActivityJoin activityJoin)throws BusinessException;
	
	/**
	 * @Function getPrizeList
	 * @Description 查询活动奖项列表
	 * @param activityPrize
	 * @return List<ActivityPrize>
	 * @throws BusinessException
	 */
	List<ActivityPrize> getPrizeList(ActivityPrize activityPrize)throws BusinessException;
	
	/**
	 * @Function addPrizeForWorks
	 * @Description 为获奖作品设定奖项
	 * @param activityWinningRecord
	 * @throws BusinessException
	 */
	void addPrizeForWorks(ActivityWinningRecord activityWinningRecord)throws BusinessException;
	
	/**
	 * @Function getActivityCommentFixedList
	 * @Description 查询活动评论列表（定位） 
	 * @param activityComment
	 * @return PageInfo
	 * @throws BusinessException
	 */
	PageInfo getActivityCommentFixedList(ActivityComment activityComment)throws BusinessException;
	
	/**
	 * @Function getActivityeReplyFixedList
	 * @Description 查询评论的回复列表（定位）
	 * @param activityCommentReply
	 * @return PageInfo
	 * @throws BusinessException
	 */
	PageInfo getActivityeReplyFixedList(ActivityCommentReply activityCommentReply)throws BusinessException;
	
	/**
	 * @Function addAwardPirzeForWinWork
	 * @Description 颁发奖励
	 * @param activityWinningRecord
	 * @return List<ActivityPrize>
	 * @throws BusinessException
	 */
	//List<ActivityPrize> addAwardPirzeForWinWork(ActivityWinningRecord activityWinningRecord)throws BusinessException;
	/**
	 * 批量更新非参与奖获奖记录的奖品发放状态为1-是
	 * @Function updateAwardStatusWithBatch
	 * @Description 
	 * @param tenantCode    租户编码
	 * @param activityCode  活动CODE
	 * @param joinCodes     参与CODE集合
	 * @throws BusinessException
	 */
	void updateAwardStatusWithBatch(String tenantCode, String activityCode, List<String> joinCodes) throws BusinessException;
	/**
	 * 获取活动获奖记录列表
	 * @Function getWinningRecord
	 * @Description 
	 * @param tenantCode   租户编码
	 * @param activityCode 活动CODE
	 * @param joinCodes    参与CODE集合
	 * @return
	 * @throws BusinessException
	 */
	List<ActivityWinningRecord> getWinningRecordList(String tenantCode, String activityCode, List<String> joinCodes) throws BusinessException;
}
