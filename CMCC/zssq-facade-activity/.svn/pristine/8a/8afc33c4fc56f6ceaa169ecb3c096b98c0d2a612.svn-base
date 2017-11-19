package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.ActivityJoinAuth;
import com.zssq.exceptions.BusinessException;
/**
 * 活动权限接口服务
 * @ClassName IActivityAuthService
 * @Description 
 * @author liurong
 * @date 2017年4月25日 下午8:11:41
 * @version 1.0
 * @since JDK 1.7
 */
public interface IActivityAuthService {
	
	/**
	 * 新增活动权限信息
	 * @Function insertAuthInfo
	 * @Description 
	 * @param record
	 * @throws BusinessException
	 */
	public void insertAuthInfo(ActivityJoinAuth record) throws BusinessException;
	/**
	 * 更新活动权限信息(先delete再insert)
	 * @Function updateAuthInfo
	 * @Description 
	 * @param record
	 * @throws BusinessException
	 */
	public void updateAuthInfo(ActivityJoinAuth record) throws BusinessException;
	/**
	 * 判断用户是否有参与活动的权限
	 * @Function judgeAuthority
	 * @Description 
	 * @param activityCode  活动主表信息CODE
	 * @param userGCode     参与用户所在集团CODE
	 * @param userPCode     参与用户所在省CODE，没有则传""
	 * @param userCCode     参与用户所在市CODE，没有则传""
	 * @param userTCode     参与用户所在班组CODE，没有则传空对象
	 * @return true：有权限  
	 * 		   false：无权限
	 * @throws BusinessException
	 */
	public boolean judgeAuthority(String activityCode, String userGCode, String userPCode, String userCCode,
			List<String> userTCodes) throws BusinessException;

}
