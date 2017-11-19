package com.zssq.service;

import com.zssq.dao.pojo.ActivityComment;
import com.zssq.dao.pojo.ActivityInfo;
import com.zssq.exceptions.BusinessException;

/**
 * 活动模块接口列表
 * @ClassName IActivityService
 * @Description 
 * @author liurong
 * @date 2017年4月17日 下午7:25:52
 * @version 1.0
 * @since JDK 1.7
 */
public interface IActivityManageService
		extends IActivityGeneralService, IActivityModelThreeService, IActivityModelOneService, IActivityModelTwoService, IActivityAuthService {
	/**
	 * 通过CODE查询活动信息
	 * @Function getActivityInfoByCode
	 * @Description 
	 * @param tenantCode 租户编码
	 * @param code       活动CODE
	 * @return
	 * @throws BusinessException
	 */
	public ActivityInfo getActivityInfoByCode(String tenantCode, String code) throws BusinessException;
	/**
	 * 通过CODE查询活动评论详情
	 * @Function getCommentInfoByCode
	 * @Description 
	 * @param tenantCode 租户编码
	 * @param code       评论CODE
	 * @return
	 * @throws BusinessException
	 */
	public ActivityComment getCommentInfoByCode(String tenantCode, String code) throws BusinessException;
}
