package com.zssq.auth.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.auth.vo.AdminManageSearchVo;
import com.zssq.auth.vo.DeputyUserVo;
import com.zssq.auth.vo.GetUserListByOrgCodeVo;
import com.zssq.auth.vo.UserManageIsLeaderVo;
import com.zssq.auth.vo.UserManageSearchVo;
import com.zssq.auth.vo.UserMenuAddVo;
import com.zssq.auth.vo.UserMenuVo;
import com.zssq.auth.vo.UserStatusVo;
import com.zssq.auth.vo.UserTeamAddVo;
import com.zssq.constants.AuthConstants;
import com.zssq.constants.TeamConstants;
import com.zssq.dao.pojo.SysMenuInfo;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

/**
 * 提供对“用户信息”进行操作的接口，使用SpringMVC映射请求Url
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
@Controller  
@RequestMapping("/sysUser")
public class SysUserController {

	private Logger log = Logger.getLogger(this.getClass());
	
	/** 用户信息业务组件 */
	@Autowired
	private ISysUserService sysUserService;
	
	/** 组织机构信息业务组件 */
	@Autowired
	private ISysOrgService sysOrgService;
	
//	@Autowired
//	private ITeamMemberService teamMemberService;
	
	/**
	 * 刷新指定用户缓存
	 * 
	 * @param request
	 * 			Http 请求参数，必须参数包含：userCode
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/refresh/cache")  
	public ResultJSON refreshCache(HttpServletRequest request) throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		try {
			sysUserService.refreshUserCache(request.getParameter("userCode"));
			
			JSONObject body = new JSONObject();
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "刷新指定用户的缓存"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "刷新指定用户的缓存"));
		}
	}
	
	/**
	 * 获取指定用户的详细信息
	 * 
	 * @param request
	 * 			Http 请求参数，必须参数包含：userCode
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/info")  
	public ResultJSON getByCode(HttpServletRequest request) throws BusinessException {
		
		Message message = null;
		if(StringTools.isEmpty(request.getParameter("userCode"))){
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10004);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取指定用户的详细信息","userCode为空"));
		}
		ResultJSON rj = new ResultJSON();
		try {			
			SysUserInfo userInfo = sysUserService.selectByCode(request.getParameter("userCode"));	
			
			JSONObject body = new JSONObject();
			if(userInfo != null) {
				body = getResultUserInfo(userInfo);
				message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			} else {
				message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			}
			
//			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取指定用户的详细信息"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.getByCode 获取指定用户信息出错", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取指定用户的详细信息"));
		}
	}
	
	/**
	 * 获取指定多个用户的详细信息
	 * 
	 * @param request
	 * 			Http 请求参数，必须参数包含：userCodes
	 * @return	通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/infos")  
	public ResultJSON getByCodes(HttpServletRequest request) throws BusinessException {
		
		Message message = null;
		if(StringTools.isEmpty(request.getParameter("userCodes"))){
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10004);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取指定用户的详细信息","userCodes为空"));
		}
		ResultJSON rj = new ResultJSON();
		try {
			List<String> userCodes = Arrays.asList(request.getParameter("userCodes").split(","));
			List<SysUserInfo> userList = sysUserService.selectByCodes(userCodes);
			
			JSONObject body = new JSONObject();
			JSONArray jsonArray = new JSONArray();			
			for(SysUserInfo userInfo : userList) {
				body = this.getResultUserInfo(userInfo);
				jsonArray.add(body);
			}
			
			body.put("dataList", jsonArray);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取用户的详细信息集合"));
	        rj.setBody(body);
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.getByCodes 获取用户的详细信息集合出错", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取用户的详细信息集合"));
		}
	}
	
	/**
	 * 获取系统菜单信息，超管系统，为员工设置权限界面数据
	 * 
	 * @param request
	 * 			Http 请求参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/menus")  
	public ResultJSON getSysMenus(@RequireValid UserMenuVo param) throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		JSONObject body = new JSONObject();
		
		try {
			// 查询系统中所有菜单信息，并标识出已为指定用户授权的菜单
			List<SysMenuInfo> menus = sysUserService.selectMenus(param.getCode());			
			// 查询待设置权限的用户信息
			SysUserInfo userInfo = sysUserService.selectByCode(param.getCode());
			// 用户所属行政组织信息
			SysOrgInfo manOrgInfo = userInfo.getManageOrgInfo();
			
			// 设置待授权用户所属的行政组织名称，用于前台界面展示
			if(manOrgInfo != null){
				body.put("orgName", StringTools.formatToString(manOrgInfo.getSysOrgName()));
			}else{
				body.put("orgName", "");
			}			
			body.put("dataList", menus);			
									
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取菜单信息"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.getByCode 获取菜单信息出错", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取菜单信息"));
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/userMenus")  
	public ResultJSON getUserMenus(HttpServletRequest request,HttpServletResponse response) throws BusinessException {
		
		ResultJSON rj = new ResultJSON();
		Message message = null;
		try {
			List<SysMenuInfo> menus = sysUserService.selectUserMenus(request.getParameter("userCode"));
			
			JSONObject body = new JSONObject();
			body.put("dataList", menus);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取用户菜单列表"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.userMenus 获取用户菜单列表出错", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取用户菜单列表"));
		}
	}
	
	/**
	 * @Function getManageList
	 * @Description 员工管理查询界面(超级管理员)
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/adminManage/list")  
	public ResultJSON getAdminManageList(@RequireValid AdminManageSearchVo param) throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		try {
        	PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
        	SysUserInfo record = new SysUserInfo();
        	boolean orgStatus = true;
        	if(StringTools.isNotEmpty(param.getUserCodeSearch())){
        		record.setUserCode(param.getUserCodeSearch());
        		orgStatus = false;
        	}
        	if(StringTools.isNotEmpty(param.getUserName())){
        		record.setUserName(param.getUserName());
        		orgStatus = false;
        	}
        	if(StringTools.isNotEmpty(param.getPhone())){
        		record.setUserOfficePhone(param.getPhone());
        		orgStatus = false;
        	}
        	if(orgStatus){
        		record.setOrgCode(param.getOrgCode());
        	}
        	
        	PageBean pageBean = sysUserService.selectAdminManagePage(pageParam, record);
        	JSONObject body = new JSONObject();
        	JSONArray jsonArray = new JSONArray();
			List<SysUserInfo> userList = pageBean.getRecordList();
			for (int i = 0; i < userList.size(); i++) {
				JSONObject userJson = new JSONObject();
				userJson.put("userName", StringTools.formatToString(userList.get(i).getUserName()));
				userJson.put("userCode", StringTools.formatToString(userList.get(i).getUserCode()));
				userJson.put("phone", StringTools.formatToString(userList.get(i).getUserOfficePhone()));
				if(AuthConstants.USER_ROLE_1.equals(userList.get(i).getRoleCode())){
					userJson.put("isLeader", "1");
				}else{
					userJson.put("isLeader", "0");
				}
				SysOrgInfo orgInfo = sysOrgService.selectByCode(userList.get(i).getOrgCode());
				if(orgInfo != null){
					userJson.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));
				}else{
					userJson.put("orgName", "");
				}
				userJson.put("userStatus", StringTools.formatToString(userList.get(i).getUserStatus()));
				jsonArray.add(userJson);
			}
			body.put("totalCount", pageBean.getTotalCount());
			body.put("dataList",jsonArray);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取员工管理查询页面"));
	        rj.setBody(body);
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.getManageList 获取员工管理查询页面", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取员工管理查询页面"));
		}
	}
	
	/**
	 * @Function getUserManageList
	 * @Description 员工管理查询界面(普通员工)
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/userManage/list")  
	public ResultJSON getUserManageList(@RequireValid UserManageSearchVo param) throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		try {
        	PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
        	SysUserInfo record = new SysUserInfo();
        	if(StringTools.isNotEmpty(param.getUserCodeSearch())){
        		record.setUserCode(param.getUserCodeSearch());
        	}
        	if(StringTools.isNotEmpty(param.getUserName())){
        		record.setUserName(param.getUserName());
        	}
        	if(StringTools.isNotEmpty(param.getPhone())){
        		record.setUserOfficePhone(param.getPhone());
        	}
        	SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
        	if(userInfo != null){
        		record.setOrgCode(userInfo.getManageOrgInfo().getSysOrgCode());//按个人行政组织查询
        	}else{
        		message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10004);
    			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取员工管理查询页面","用户code无效"));
        	}
        	
        	PageBean pageBean = sysUserService.selectUserManagePage(pageParam, record);
        	JSONObject body = new JSONObject();
        	JSONArray jsonArray = new JSONArray();
			List<SysUserInfo> userList = pageBean.getRecordList();
			for (int i = 0; i < userList.size(); i++) {
				JSONObject userJson = new JSONObject();
				userJson.put("userName", StringTools.formatToString(userList.get(i).getUserName()));
				userJson.put("userCode", StringTools.formatToString(userList.get(i).getUserCode()));
				userJson.put("phone", StringTools.formatToString(userList.get(i).getUserOfficePhone()));
				if(AuthConstants.USER_ROLE_1.equals(userList.get(i).getRoleCode())){
					userJson.put("isLeader", "1");
				}else{
					userJson.put("isLeader", "0");
				}
				SysOrgInfo orgInfo = sysOrgService.selectByCode(userList.get(i).getOrgCode());
				if(orgInfo != null){
					userJson.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));
				}else{
					userJson.put("orgName", "");
				}
				userJson.put("userStatus", StringTools.formatToString(userList.get(i).getUserStatus()));
				jsonArray.add(userJson);
			}
			body.put("totalCount", pageBean.getTotalCount());
			body.put("dataList",jsonArray);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取员工管理查询页面"));
	        rj.setBody(body);
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.getManageList 获取员工管理查询页面", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取员工管理查询页面"));
		}
	}

	/**
	 * 设置用户权限信息
	 * 
	 * @param request
	 * 			Http 请求参数，必须参数包含：userCode
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/authorize")  
	public ResultJSON forUserAuthorize(@RequireValid UserMenuAddVo param) throws BusinessException {
		
		ResultJSON rj = new ResultJSON();
		Message message = null;
		try {
			sysUserService.addUserMenuRelation(param.getCode(), param.getMenuCodes());
			
			JSONObject body = new JSONObject();
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "设置用户权限信息"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.getByCode 设置用户权限信息出错", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "设置用户权限信息"));
		}
	}
	
	
	/**
	 * @Function setIsLeader
	 * @Description 设置普通员工/公司领导
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/manage/setIsLeader")  
	public ResultJSON setIsLeader(@RequireValid UserManageIsLeaderVo param) throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		try {
			boolean isSuccess = sysUserService.setIsLeader(param.getUserCodes(), param.getIsLeader());
			if(isSuccess){
				message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			}else{
				message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			}
			rj.setReturnCode(message.getCode());
			if(param.getIsLeader().equals(AuthConstants.BOOLEAN_1)){
				rj.setReturnTip(String.format(message.getTip(), "设置领导"));
			}else{
				rj.setReturnTip(String.format(message.getTip(), "设置普通员工"));
			}
	        rj.setBody(new JSONObject());
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.getManageList 设置领导/普通员工", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "设置领导/普通员工"));
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/manage/setUserStatus")  
	public ResultJSON setUserStatus(@RequireValid UserStatusVo param) throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		try {
			boolean isSuccess = sysUserService.setIsLeader(param.getUserCodes(), param.getUserStatus());
			if(isSuccess){
				message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			}else{
				message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			}
			rj.setReturnCode(message.getCode());
			if(param.getUserStatus().equals(AuthConstants.BOOLEAN_1)){
				rj.setReturnTip(String.format(message.getTip(), "启用"));
			}else{
				rj.setReturnTip(String.format(message.getTip(), "禁用"));
			}
	        rj.setBody(new JSONObject());
			return rj;
		} catch (Exception e) {
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "用户启禁用"));
		}
	}

	/**
	 * @Function getDeputyList
	 * @Description 获取代发领导/员工
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/deputy/list")  
	public ResultJSON getDeputyList(@RequireValid DeputyUserVo param) throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		try {
        	List<SysUserInfo> userList = sysUserService.selectDeputyUser(param.getOrgCode(), param.getIsLeader() ,param.getUserName());
        	JSONObject body = new JSONObject();
        	JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < userList.size(); i++) {
				JSONObject userInfo = new JSONObject();
				userInfo.put("userCode", StringTools.formatToString(userList.get(i).getUserCode()));
				userInfo.put("userName", StringTools.formatToString(userList.get(i).getUserName()));
//				SysOrgInfo orgInfo = sysOrgService.selectByCode(userList.get(i).getOrgCode());
//				if(orgInfo != null){
//					userInfo.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));
//				}else{
//					userInfo.put("orgName", "");
//				}
				jsonArray.add(userInfo);
			}
			body.put("dataList",jsonArray);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取代发领导/员工成功"));
	        rj.setBody(body);
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.getDeputyList 获取代发领导/员工成功", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取代发领导/员工成功"));
		}
	}
	
	
	/**
	 * @Function getUserListByOrg
	 * @Description 班组成员添加查询
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/listByOrg")
	public ResultJSON getUserListByOrg(@RequireValid UserTeamAddVo param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	SysUserInfo record = new SysUserInfo();
        	record.setOrgCode(param.getOrgCode());
//        	if(!StringTools.isEmpty(param.getUserName())){
//        		record.setUserName(param.getUserName());
//        	}
//        	if(!StringTools.isEmpty(param.getUserCodeSearch())){
//        		record.setUserCode(param.getUserCodeSearch());
//        	}
//        	if(!StringTools.isEmpty(param.getPhone())){
//        		record.setUserOfficePhone(param.getPhone());
//        	}
        	List<SysUserInfo> userList = sysUserService.selectByRecord(record);
//        	List<String> teamLeaders = teamMemberService.selectTeamLeaders(param.getTeamCode());
        	JSONArray jsonArray = new JSONArray();
        	for (int i = 0; i < userList.size(); i++) {
        		JSONObject userJson = new JSONObject();
        		userJson.put("userName", StringTools.formatToString(userList.get(i).getUserName()));
        		userJson.put("userCode", StringTools.formatToString(userList.get(i).getUserCode()));
        		userJson.put("phone", StringTools.formatToString(userList.get(i).getUserOfficePhone()));
        		SysOrgInfo orgInfo = sysOrgService.selectByCode(userList.get(i).getOrgCode());
        		if(orgInfo == null){
        			userJson.put("orgName", "");
        		}else{
        			userJson.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));
        		}
//        		userJson.put("isLeader", "0");
//        		for (int j = 0; j < teamLeaders.size(); j++) {
//					if(userList.get(i).getUserCode().equals(teamLeaders.get(j))){
//						userJson.put("isLeader", "1");
//						break;
//					}
//				}
        		jsonArray.add(userJson);
			}
        	JSONObject body = new JSONObject();
        	body.put("dataList", jsonArray);
            log.info("SysUserController.getUserListByOrg:获取班组添加成员列表");
        	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"查询班组添加成员列表"));
            rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"查询班组添加成员列表"));
		}
        return rj;
	}
	
	/**
	 * 封装用户信息返回对象
	 * 
	 * @param userInfo
	 * 			用户信息
	 * @return 用户信息
	 */
	private JSONObject getResultUserInfo(SysUserInfo userInfo) throws BusinessException {
		
		try {			
			JSONObject body = new JSONObject();
			if(userInfo != null) {
				body.put("userCode", StringTools.formatToString(userInfo.getUserCode()));		// 用户uid
				body.put("userName", StringTools.formatToString(userInfo.getUserName()));		// 用户姓名
				body.put("userSex", StringTools.formatToString(userInfo.getUserSex()));			// 性别
				body.put("phone", StringTools.formatToString(userInfo.getUserOfficePhone()));	// 电话
				body.put("userStatus", StringTools.formatToString(userInfo.getUserStatus()));	// 状态
				body.put("nickName", StringTools.formatToString(userInfo.getNickName()));		// 昵称
				body.put("head", StringTools.formatToString(userInfo.getHeadPortrait()));		// 头像
				body.put("tenantCode", StringTools.formatToString(userInfo.getTenantCode()));	// 租户编号
				body.put("orgCode", StringTools.formatToString(userInfo.getOrgCode()));			// 员工所属组织机构编号
				
				// 所属组织信息				
//				SysOrgInfo orgInfo = sysOrgService.selectByCode(userInfo.getOrgCode());	
				SysOrgInfo orgInfo = userInfo.getOrgInfo();
				JSONObject orgJson = new JSONObject();				
				orgJson.put("orgCode", StringTools.formatToString(orgInfo.getSysOrgCode()));		// 组织编号
				orgJson.put("parentCode", StringTools.formatToString(orgInfo.getParentCode()));		// 上级编号
				orgJson.put("orgName", StringTools.formatToString(orgInfo.getSysOrgName()));		// 组织名称
				orgJson.put("orgFullName", StringTools.formatToString(orgInfo.getSysOrgFullname()));// 组织全名	
				orgJson.put("orgType", StringTools.formatToString(orgInfo.getSysOrgType()));		// 组织类型
				body.put("orgInfo", orgJson);
				
				// 行政组织信息
//				SysOrgInfo manOrgInfo = sysOrgService.selectManageOrgByCode(userInfo.getOrgCode());
				SysOrgInfo manOrgInfo = userInfo.getManageOrgInfo();
				JSONObject manOrgJson = new JSONObject();
				manOrgJson.put("orgCode", StringTools.formatToString(manOrgInfo.getSysOrgCode()));			// 组织编号
				manOrgJson.put("parentCode", StringTools.formatToString(manOrgInfo.getParentCode()));		// 上级编号
				manOrgJson.put("orgName", StringTools.formatToString(manOrgInfo.getSysOrgName()));			// 组织名称
				manOrgJson.put("orgFullName", StringTools.formatToString(manOrgInfo.getSysOrgFullname()));	// 组织全名	
				manOrgJson.put("orgType", StringTools.formatToString(manOrgInfo.getSysOrgType()));			// 组织类型
				body.put("manOrgInfo", manOrgJson);
			}			
			return body;
		} catch (Exception e) {
			log.error("SysOrgController.getResultUserInfo 封装用户信息返回对象出错", e);
			throw new BusinessException("获取指定用户的详细信息", e);
		}
	}
	@ResponseBody
	@RequestMapping("/getUserByOrgCode")
	public ResultJSON getUserListByOrgCode(@RequireValid GetUserListByOrgCodeVo param) throws BusinessException{
		Message message = null;
		try {
			ResultJSON result = new ResultJSON();
			String orgCode = param.getOrgCode();
			List<SysUserInfo> list = sysUserService.getUserListByOrgCode(orgCode);
			JSONObject body = new JSONObject();
			JSONArray jsonArray = new JSONArray();	
			for(SysUserInfo info : list){
				jsonArray.add(info);
			}
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			body.put("totalCount", list.size());
			body.put("list", jsonArray);
			result.setReturnCode(message.getCode());
			result.setReturnTip(String.format(message.getTip(), "组织编码查找员工"));
			result.setBody(body);
			return result;
			
		} catch (Exception e) {
			log.error("SysOrgController.getUserListByOrgCode 组织编码查找员工出错", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException("获取指定用户的详细信息", e);
		}
		
		
		
		
		
	}
	
	
}
