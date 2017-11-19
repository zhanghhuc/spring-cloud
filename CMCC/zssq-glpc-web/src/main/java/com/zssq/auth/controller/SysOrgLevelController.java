package com.zssq.auth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.auth.vo.OrgLevelAddVo;
import com.zssq.constants.AuthConstants;
import com.zssq.dao.pojo.SysOrgLevel;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysOrgLevelService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

/**
 * 提供对“组织级别”信息操作的接口
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
@Controller  
@RequestMapping("/orgLevel")
public class SysOrgLevelController {

	/** 日志记录器 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/** 组织级别信息业务组件 */
	@Autowired
	private ISysOrgLevelService sysOrgLevelService;
	
	/**
	 * 获取租户的“组织级别”信息
	 * 
	 * @param request
	 * 			Http 请求参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/listAll")  
	public ResultJSON searchAllOrgLevel(HttpServletRequest request) throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		try {
			List<SysOrgLevel> orgLevels = sysOrgLevelService.selectByTenantCode(AuthConstants.TENANT_CODE);
			
			JSONArray jsonArry = new JSONArray();
			for(SysOrgLevel orgLevel : orgLevels) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("levelCode", StringTools.formatToString(orgLevel.getLevelCode()));
				jsonObj.put("levelName", StringTools.formatToString(orgLevel.getLevelName()));
				jsonObj.put("levelValue", StringTools.formatToString(orgLevel.getLevel()));
				jsonArry.add(jsonObj);
			}
			
			JSONObject body = new JSONObject();
			body.put("dataList", jsonArry);
			
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取组织级别信息"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取组织级别信息"));
		}
	}
	
	/**
	 * 新增“组织级别”
	 * 
	 * @param request
	 * 			Http 请求参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/add")  
	public ResultJSON addLevel(@RequireValid OrgLevelAddVo param) throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		try {
			SysOrgLevel orgLevel = new SysOrgLevel();
			PropertyUtils.copyProperties(orgLevel, param);
			
			orgLevel.setLevelCode(UUIDHelper.getUUID());
			orgLevel.setSaasTenantCode(AuthConstants.TENANT_CODE);
			
			sysOrgLevelService.insert(orgLevel);
						
			JSONObject body = new JSONObject();			
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "新增组织级别"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "新增组织级别"));
		}
	}
}
