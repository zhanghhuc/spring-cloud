package com.zssq.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mdcl.smap.bean.OrgInfo;
import com.zssq.constants.AuthConstants;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISmapOrgService;
import com.zssq.service.ISmapService;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

/**
 * 提供对 SMAP 同步到的原始组织信息进行操作的接口，使用SpringMVC映射请求Url
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
@Controller  
@RequestMapping("/smapOrg")
public class SmapOrgController {

	/** 日志记录器 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/** SMAP 同步组织信息操作接口 */
	@Autowired
	private ISmapOrgService smapOrgService;
	
	/**
	 * 获取 SMAP 组织树节点，需传入上级节点编码
	 * 
	 * @param request
	 * 			Http 请求参数
	 * @return SMAP 组织树顶级节点
	 * @throws BusinessException
	 */
	@RequestMapping("/treeNode")  
	@ResponseBody
	public ResultJSON treeNode(HttpServletRequest request) throws BusinessException {
		
		Message message = null;
		Map<String, Object> resultBody = new HashMap<String, Object>();
		try {
			List<OrgInfo> orgInfos = smapOrgService.selectByParentOrgCode(request.getParameter("parentOrgCode"));
			
			if(CollectionUtils.isNotEmpty(orgInfos)) {
				JSONArray array = new JSONArray();
				for(OrgInfo orgInfo : orgInfos) {
					JSONObject object = new JSONObject();
					object.put("orgCode", StringTools.formatToString(orgInfo.getO()));			// 组织编码
					object.put("orgName", StringTools.formatToString(orgInfo.getInitials()));	// 组织名称（非显示名）
					array.add(object);
				}
				resultBody.put("dataList", array);
			}
			
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			ResultJSON rj = new ResultJSON();
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取 SMAP 组织树节点"));
	        rj.setBody(resultBody);
			
			return rj;
		} catch (Exception e) {
			log.error("SmapOrgController.treeNode 获取 SMAP 组织树节点出错", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取 SMAP 组织树节点"));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	// TODO 以下为测试代码
	@Autowired
	private ISmapService smapService;
	
	@RequestMapping("/syncOrg")  
	@ResponseBody
	public ResultJSON syncOrg(HttpServletRequest request) {
		
		try {
			smapService.syncOrgs();
		} catch (BusinessException e) {
			log.error("测试同步组织信息出错", e);
		}
		
		ResultJSON rj = new ResultJSON();
		rj.setReturnCode("200");
        rj.setReturnTip("测试同步组织信息成功");
        rj.setBody(new JSONObject());
		return rj;
	}
	
	@RequestMapping("/syncUser")  
	@ResponseBody
	public ResultJSON syncUser(HttpServletRequest request) {
		
		try {
			smapService.syncUsers();
		} catch (BusinessException e) {
			log.error("测试同步员工信息出错", e);
		}
		
		ResultJSON rj = new ResultJSON();
		rj.setReturnCode("200");
        rj.setReturnTip("测试同步员工信息成功");
        rj.setBody(new JSONObject());
		return rj;
	}
}
