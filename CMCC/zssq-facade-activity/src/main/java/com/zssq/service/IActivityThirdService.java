package com.zssq.service;

import com.zssq.exceptions.BusinessException;

/**
 * 活动模块通用业务（评论，回复，点赞，收藏）
 * @ClassName IActivityCommonService
 * @Description 
 * @author liurong
 * @date 2017年4月21日 下午2:09:54
 * @version 1.0
 * @since JDK 1.7
 */
public interface IActivityThirdService {
	
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
	 * 删除/恢复 活动
	 * @Function deleteOrNotActivity
	 * @Description 
	 * @param tenantCode   租户标识
	 * @param opt          操作标识：DELETE(删除);RECOVERY(恢复)
	 * @param activityCode 活动CODE
	 * @throws BusinessException
	 */
	int deleteOrNotActivity(String tenantCode, Operate opt, String activityCode) throws BusinessException;
	/**
	 * 删除/恢复评论
	 * @Function deleteOrNotComment
	 * @Description 
	 * @param tenantCode   租户标识
	 * @param opt         操作标识：DELETE(删除);RECOVERY(恢复)
	 * @param commentCode  评论CODE
	 * @throws BusinessException
	 */
	int deleteOrNotComment(String tenantCode, Operate opt, String commentCode) throws BusinessException;
	/**
	 * 删除/恢复回复
	 * @Function deleteOrNotReply
	 * @Description 
	 * @param tenantCode  租户标识
	 * @param opt         操作标识：DELETE(删除);RECOVERY(恢复)
	 * @param replyCode   回复CODE
	 * @throws BusinessException
	 */
	int deleteOrNotReply(String tenantCode, Operate opt, String replyCode) throws BusinessException;
	/**
	 * 减少  count个分享数
	 * @Function updateDecreaseShareNumByCode
	 * @Description 
	 * @param code      投票主表CODE
	 * @param count     变量
	 * @throws BusinessException
	 */
	public void updateDecreaseShareNumByCode(String code, int count) throws BusinessException;
}
