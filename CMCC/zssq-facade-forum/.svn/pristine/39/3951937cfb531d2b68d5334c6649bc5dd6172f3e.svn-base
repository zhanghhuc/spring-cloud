package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.ForumTopics;
import com.zssq.exceptions.BusinessException;

/**
 * 论坛模块提供给其他模块的接口
 * @ClassName IForumThirdService
 * @Description 
 * @author liurong
 * @date 2017年4月11日15:37:59
 * @version 1.0
 * @since JDK 1.7
 */
public interface IForumThirdService {
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
	 * 删除/恢复主帖信息
	 * @Function deleteOrNotTopic
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param opt        操作标识：DELETE(删除);RECOVERY(恢复)
	 * @param code       主帖信息CODE
	 * @return
	 * @throws BusinessException
	 */
	public int deleteOrNotTopic(String tenantCode, Operate opt, String code) throws BusinessException;
	/**
	 * 删除/恢复跟帖信息
	 * @Function deleteOrNotFollow
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param opt        操作标识：DELETE(删除);RECOVERY(恢复)
	 * @param code       跟帖信息CODE
	 * @return
	 * @throws BusinessException
	 */
	public int deleteOrNotFollow(String tenantCode, Operate opt, String code) throws BusinessException;
	/**
	 * 删除/恢复跟帖的评论或回复信息
	 * @Function deleteOrNotCommentReply
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param opt        操作标识：DELETE(删除);RECOVERY(恢复)
	 * @param code       评论或回复信息CODE
	 * @return
	 * @throws BusinessException
	 */
	public int deleteOrNotCommentReply(String tenantCode, Operate opt, String code) throws BusinessException;
	/**
	 * 班组归档-论坛接口
	 * @Function sumupTeamForum
	 * @Description 
	 * @param teamCode 班组code
	 * @param startNum 
	 * @param endNum
	 * @return
	 * @throws BusinessException
	 */
	public List<ForumTopics> sumupTeamForum(String teamCode, int startNum, int endNum) throws BusinessException;

}
