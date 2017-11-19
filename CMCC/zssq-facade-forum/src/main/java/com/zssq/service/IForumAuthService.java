package com.zssq.service;

import com.zssq.dao.pojo.ForumPlates;
import com.zssq.dao.pojo.ForumPlatesMember;
import com.zssq.exceptions.BusinessException;
/**
 * 论坛权限接口
 * @ClassName IForumAuthService
 * @Description 
 * @author liurong
 * @date 2017年4月7日 上午9:40:47
 * @version 1.0
 * @since JDK 1.7
 */
public interface IForumAuthService {
	
	/**
	 * 查询userCode是否为版主，platesCode方式
	 * @Function queryUserIsTeamLeaderByPlatesCode
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param platesCode 论坛版块CODE
	 * @param userCode 用户CODE
	 * @return true  是版主
	 * 		   false 不是版主
	 * @throws BusinessException
	 */
	public boolean queryUserIsTeamLeaderByPlatesCode(String tenantCode, String platesCode, String userCode) throws BusinessException;
	/**
	 * 查询userCode是否为版主，belongCode方式
	 * @Function queryUserIsTeamLeaderByBelongCode
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param belongCode 论坛所属CODE
	 * @param userCode   用户CODE
	 * @return true  是版主
	 * 		   false 不是版主
	 * @throws BusinessException
	 */
	public boolean queryUserIsTeamLeaderByBelongCode(String tenantCode, String belongCode, String userCode) throws BusinessException;
	/**
	 * 查询userCode是否具有发帖的权限
	 * @Function queryUserCanbeSendTopic
	 * @Description 
	 * @param userCode
	 * @return true  有发帖权限
	 * 		   false 没有发帖权限
	 * @throws BusinessException
	 */
	public boolean queryUserCanbeSendTopic(String tenantCode, String belongCode, String userCode) throws BusinessException;
	/**
	 * 查询userCode的论坛操作权限
	 * @Function queryUserCanbeReplyTopic
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param platesCode 论坛版块CODE
	 * @param userCode 用户CODE
	 * @return
	 * @throws BusinessException
	 */
	public ForumPlatesMember queryUserForumAuthority(String tenantCode, String platesCode, String userCode) throws BusinessException;
	/**
	 * @Function updateForumPlateEmpowerment
	 * @Description 设置论坛权限
	 * @param forumPlates
	 * @throws BusinessException
	 */
	public void updateForumPlateEmpowerment(ForumPlates forumPlates)throws BusinessException;
	
	/**
	 * @Function selectForumPlateEmpowerment
	 * @Description 查询论坛版块信息
	 * @param forumPlates
	 * @return ForumPlates
	 * @throws BusinessException
	 */
	public ForumPlates selectForumPlate(ForumPlates forumPlates)throws BusinessException;
	
	
}
