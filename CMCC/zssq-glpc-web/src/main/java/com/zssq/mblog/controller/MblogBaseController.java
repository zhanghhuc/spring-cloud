package com.zssq.mblog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.UserState;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.IUserRelationService;
import com.zssq.service.MblogCommentService;
import com.zssq.service.MblogInfoService;
import com.zssq.service.MblogReplyService;
import com.zssq.utils.StringTools;


@Component
public class MblogBaseController {
	// 引入评论Service
	@Autowired
	protected MblogCommentService mblogCommentService;
	// 引入微博信息Service
	@Autowired
	protected MblogInfoService mblogInfoService;
	// 引入回复Service
	@Autowired
	protected MblogReplyService mblogReplyService;
	// 引入用户Service
	@Autowired
	protected ISysUserService sysUserService;
	// 引入组织Service
	@Autowired
	protected ISysOrgService sysOrgService;
	// 引入人员关系Service
	@Autowired
	protected IUserRelationService userRelationService;
	
	/**
	 * 
	    * @Title: checkIsBlack  
	    * @Description: 	判断俩人是否是黑名单关系
	    * @param userCode
	    * @param otherCode
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	protected boolean checkIsBlack(String userCode,String otherCode) throws BusinessException{
		UserState userState = userRelationService.selectUserState(userCode, otherCode);
		if(null != userState){
			return userState.getIsBlack() > 0;
		}
		return false;
	}
	
	/**
	 * 
	    * @Title: getMyFrAndCo  
	    * @Description: 	获取我的好友以及我的关注
	    * @param userCode	我的用户CODE
	    * @throws BusinessException
		* @return List<String>    返回类型
	 */
	protected List<String> getMyFrAndCo(String userCode) throws BusinessException{
		List<String> userCodes = new ArrayList<String>();
		// 获取我的关注
		userCodes.addAll(userRelationService.selectConcerns(userCode));
		// 获取我的好友
		userCodes.addAll(userRelationService.selectFriends(userCode));
		return userCodes;
	}
	
	/**
	 * 
	    * @Title: getUserInfoList  
	    * @Description: 获取用户列表
	    * @param userCodes
	    * @throws BusinessException
		* @return List<SysUserInfo>    返回类型
	 */
	protected List<SysUserInfo> getUserInfoList(List<String> userCodes) throws BusinessException{
		List<SysUserInfo> userList = new ArrayList<SysUserInfo>();
		userList = sysUserService.selectByCodes(userCodes);
		if(null != userList){
			return userList;
		}
		return new ArrayList<SysUserInfo>();
	}
	
	/**
	 * 
	    * @Title: getUserInfo  
	    * @Description: 获取用户信息
	    * @param userCode
	    * @throws BusinessException
		* @return SysUserInfo    返回类型
	 */
	protected SysUserInfo getUserInfo(String userCode) throws BusinessException{
		// 获取用户信息
		if(StringTools.isNotEmpty(userCode)){
			return sysUserService.selectByCode(userCode);
		}
		return null;
	}
	
	/**
	 * 
	    * @Title: getOrgInfo  
	    * @Description: 获取组织信息
	    * @param orgCode
	    * @throws BusinessException
		* @return SysUserInfo    返回类型
	 */
	protected SysOrgInfo getOrgInfo(String orgCode) throws BusinessException{
		// 获取用户信息
		if(StringTools.isNotEmpty(orgCode)){
			return sysOrgService.selectByCode(orgCode);
		}
		return null;
	}
	
	/**
	 * 
	    * @Title: getOrgName  
	    * @Description: 获取组织名字
	    * @param orgCode
	    * @throws BusinessException
		* @return String    返回类型
	 */
	protected String getOrgName(String orgCode) throws BusinessException{
		// 获取用户信息
		if(StringTools.isNotEmpty(orgCode)){
			SysOrgInfo org = sysOrgService.selectByCode(orgCode);
			if(null != org){
				return org.getSysOrgName();
			}
			return "";
		}
		return "";
	}
}
