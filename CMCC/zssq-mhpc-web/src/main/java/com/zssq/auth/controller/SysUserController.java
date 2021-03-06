package com.zssq.auth.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.auth.vo.DeputyUserVo;
import com.zssq.auth.vo.GetUserByUserNameVo;
import com.zssq.auth.vo.GetUserListByOrgCodeVo;
import com.zssq.auth.vo.UserHeadVo;
import com.zssq.auth.vo.UserIntroVo;
import com.zssq.auth.vo.UserManageIsLeaderVo;
import com.zssq.auth.vo.out.GetUserByUserNameOutVo;
import com.zssq.config.AuthConfig;
import com.zssq.constants.AuthConstants;
import com.zssq.dao.pojo.CoinAccount;
import com.zssq.dao.pojo.ExpAccount;
import com.zssq.dao.pojo.IntegralAccount;
import com.zssq.dao.pojo.SysDeputyInfo;
import com.zssq.dao.pojo.SysMenuInfo;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.UserInfoByName;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.HonorThirdService;
import com.zssq.service.ICoinAccountService;
import com.zssq.service.IExpAccountService;
import com.zssq.service.IIntegralAccountService;
import com.zssq.service.ISysDeputyService;
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
	
	/** 荣誉服务第三方 */
	@Autowired
	private HonorThirdService honorThirdService;
	
	/** 积分服务 */
	@Autowired
	private IIntegralAccountService integralAccountService;
	
	/** 金币服务 */
	@Autowired
	private ICoinAccountService coinAccountService;
	
	/** 经验服务 */
	@Autowired
	private IExpAccountService expAccountService;
	
	/** 代发服务 */
	@Autowired
	private ISysDeputyService sysDeputyService;
	
	/** 授权disconf */
	@Autowired
	private AuthConfig authConfig;
	
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
	 * 获取用户详细信息，适用于当前登陆用户跳转到别人的空间首页时使用
	 * 
	 * @param request
	 * 			Http 请求参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/searchOtherUserInfo")  
	public ResultJSON searchOtherUserInfo(HttpServletRequest request) throws BusinessException {
		
		Message message = null;
		if(StringTools.isEmpty(request.getParameter("otherUserCode"))){
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10004);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取用户详细信息","otherUserCode 为空"));
		}
		ResultJSON rj = new ResultJSON();
		try {			
			SysUserInfo userInfo = sysUserService.selectByCode(request.getParameter("otherUserCode"));	
			
			JSONObject body = new JSONObject();
			if(userInfo != null) {
				body = getResultUserInfo(userInfo);
				message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			} else {
				message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			}
			
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取用户详细信息"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.searchOtherUserInfo 获取用户详细信息", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取用户详细信息"));
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
	 * 获取系统菜单信息
	 * 
	 * @param request
	 * 			Http 请求参数，必须参数包含：userCode
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/menus")  
	public ResultJSON getSysMenus(HttpServletRequest request) throws BusinessException {
		
		ResultJSON rj = new ResultJSON();
		Message message = null;
		try {
			List<SysMenuInfo> menus = sysUserService.selectMenus(request.getParameter("userCode"));
			
			JSONObject body = new JSONObject();
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
	 * 设置用户权限信息
	 * 
	 * @param request
	 * 			Http 请求参数，必须参数包含：userCode
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/authorize")  
	public ResultJSON forUserAuthorize(HttpServletRequest request) throws BusinessException {
		
		ResultJSON rj = new ResultJSON();
		Message message = null;
		try {
			sysUserService.addUserMenuRelation(request.getParameter("userCode"), request.getParameter("menuCodes"));
			
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
				userInfo.put("userName", StringTools.formatToString(userList.get(i).getUserName()));
				SysOrgInfo orgInfo = sysOrgService.selectByCode(userList.get(i).getOrgCode());
				if(orgInfo != null){
					userInfo.put("orgName", StringTools.formatToString(orgInfo.getSysOrgName()));
				}else{
					userInfo.put("orgName", "");
				}
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
				body.put("introduce", StringTools.formatToString(userInfo.getIntroduce()));		// 用户简介
				body.put("head", StringTools.formatToString(userInfo.getHeadPortrait()));		// 头像
				body.put("tenantCode", StringTools.formatToString(userInfo.getTenantCode()));	// 租户编号
				body.put("orgCode", StringTools.formatToString(userInfo.getOrgCode()));			// 员工所属组织机构编号
				
				//获取代发标识
				SysDeputyInfo record = new SysDeputyInfo();
				record.setDeputyUserCode(userInfo.getUserCode());
				List<SysDeputyInfo> deputyList = sysDeputyService.selectByRecord(record);
				String roleCode = StringTools.formatToString(userInfo.getRoleCode());
				String isDeputy = "";
				if(deputyList.size() == 0){//无代发领导
					if(StringUtils.equals(AuthConstants.USER_ROLE_0, roleCode)) {//不是领导
						isDeputy = "0";
					} else if(StringUtils.equals(AuthConstants.USER_ROLE_1, roleCode)) {//领导
						isDeputy = "2";
					} else {//不是领导
						isDeputy = "0";
					}
				}else{//有代发领导
					isDeputy = "1";
				}
				body.put("isDeputy", isDeputy);
				
				body.put("honor", StringTools.formatToString(honorThirdService.getHonorCount(userInfo.getUserCode(), AuthConstants.TENANT_CODE)));//荣誉个数
				ExpAccount expAccount = expAccountService.selectByAccountCode(userInfo.getUserCode());
				body.put("level", StringTools.formatToString(expAccount.getCurrentLevel()));//等级
				body.put("exp", StringTools.formatToString(expAccount.getCurrentExp()));//经验
				IntegralAccount integralAccount = integralAccountService.selectByAccountCode(userInfo.getUserCode());
				if(integralAccount != null){
					body.put("score", StringTools.formatToString(integralAccount.getIntegralBalance()));//积分
				}else{
					body.put("score", "0");//积分
				}
				CoinAccount coinAccount = coinAccountService.selectByAccountCode(userInfo.getUserCode());
				if(coinAccount != null){
					body.put("gold", StringTools.formatToString(coinAccount.getCoinBalance()));//金币
				}else{
					body.put("gold", "0");//金币
				}
				
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
				manOrgJson.put("srcOrgCode", StringTools.formatToString(manOrgInfo.getSrcCode()));			// 员工所属原始组织机构编号
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
	
	/**
	 * @Function updateHead
	 * @Description 更新用户头像
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/updateHead")
	public ResultJSON updateHead(@RequireValid UserHeadVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	boolean isSuccess = sysUserService.updateHead(param.getUserCode(), param.getHead());
	    	if(isSuccess){
	    		m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
	    	}else{
	    		m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"更新用户头像"));
	    	rj.setBody(new JSONObject());
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			log.error("更新用户头像异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"更新用户头像"));
		}
	    return rj;
	}
	
	/**
	 * @Function updateIntro
	 * @Description 更新用户简介
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/updateIntro")
	public ResultJSON updateIntro(@RequireValid UserIntroVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	String inrto = param.getIntro();
	    	boolean isSuccess = sysUserService.updateIntro(param.getUserCode(), StringTools.formatToString(inrto));
	    	if(isSuccess){
	    		m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
	    	}else{
	    		m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"更新用户简介"));
	    	rj.setBody(new JSONObject());
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			log.error("更新用户简介异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"更新用户简介"));
		}
	    return rj;
	}
	/**
	 * 
	 * @Function getUserListByOrgCode
	 * @Description 通过组织编码查找员工（门户）
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/getUserByOrgCode")
	public ResultJSON getUserListByOrgCode(@RequireValid GetUserListByOrgCodeVo param) throws BusinessException{
		Message message = null;
		try {
			ResultJSON result = new ResultJSON();
			String orgCode = param.getOrgCode();
			List<SysUserInfo> list = sysUserService.getUserListByOrgCode(orgCode);
			String orgName = sysOrgService.selectByCode(orgCode).getSysOrgName();
			JSONObject body = new JSONObject();
			JSONArray jsonArray = new JSONArray();	
			for(SysUserInfo info : list){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("userName", info.getUserName());
				jsonObject.put("userCode", info.getUserCode());
				jsonObject.put("headPortrait", info.getHeadPortrait());
				jsonObject.put("orgName", orgName);
				jsonArray.add(jsonObject);
			}
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			body.put("totalCount", list.size());
			body.put("list", jsonArray);
			result.setReturnCode(message.getCode());
			result.setReturnTip(String.format(message.getTip(), "mh组织编码查找员工"));
			result.setBody(body);
			return result;
			
		} catch (Exception e) {
			log.error("SysOrgController.getUserListByOrgCode 组织编码查找员工出错", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException("获取指定用户的详细信息", e);
		}
	
		
	}
	/**
	 * 
	 * @Function getUserByUserName
	 * @Description 通过员工姓名模糊查询员工信息
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/getUserByUserName")
	public ResultJSON getUserByUserName(@RequireValid GetUserByUserNameVo param) throws BusinessException{
		Message message = null;
		try {
			ResultJSON result = new ResultJSON();
			String userName = param.getUserName();
			PageParam page = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
			PageBean pageBean = sysUserService.selectUserByUserName(userName,page);
			List<UserInfoByName> userInfoList = pageBean.getRecordList();
			JSONObject body = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for(UserInfoByName info : userInfoList){
				GetUserByUserNameOutVo out = new GetUserByUserNameOutVo();
				out.setUserCode(info.getUserCode());
				out.setUserName(info.getUserName());
				out.setOrgFullName(StringTools.formatToString(info.getFullName()));
				out.setIntroduce(StringTools.formatToString(info.getIntroduce()));
				if(StringTools.isEmpty(info.getHeadPortrait())){
					out.setHeadPortrait(authConfig.getUserHead());
				}else{
					out.setHeadPortrait(StringTools.formatToString(info.getHeadPortrait()));
				}
				jsonArray.add(out);
			}
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			body.put("totalCount", pageBean.getTotalCount());
			body.put("list", jsonArray);
			result.setReturnCode(message.getCode());
			result.setReturnTip(String.format(message.getTip(), "根据用户名获取用户信息"));
			result.setBody(body);
			return result;
			
		} catch (Exception e) {
			log.error("SysOrgController.getUserListByOrgCode 根据用户名获取用户信息出错", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException("获取指定用户的详细信息", e);
		}
	}
}
