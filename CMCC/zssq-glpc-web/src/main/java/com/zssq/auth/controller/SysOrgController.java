package com.zssq.auth.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.auth.vo.IsEnableVo;
import com.zssq.auth.vo.OrgLinkVo;
import com.zssq.auth.vo.OrgTreeVo;
import com.zssq.auth.vo.SmapOrgAddVo;
import com.zssq.auth.vo.StaticTreeVo;
import com.zssq.auth.vo.SubOrgVo;
import com.zssq.constants.AuthConstants;
import com.zssq.constants.CreditConstants;
import com.zssq.dao.pojo.CoinAccount;
import com.zssq.dao.pojo.ExpAccount;
import com.zssq.dao.pojo.IntegralAccount;
import com.zssq.dao.pojo.OrgLinkList;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ICoinAccountService;
import com.zssq.service.IExpAccountService;
import com.zssq.service.IIntegralAccountService;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

/**
 * 提供对"组织机构信息"进行操作的接口，使用SpringMVC映射请求Url
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
@Controller  
@RequestMapping("/sysOrg")
public class SysOrgController {

	/** 日志记录器 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/** 组织信息业务组件 */
	@Autowired
	private ISysOrgService sysOrgService;

	/** 用户信息业务组件 */
	@Autowired
	private ISysUserService sysUserService;
	
	/** 积分账户业务组件 */
	@Autowired
	private IIntegralAccountService integralAccountService;
	
	/** 金币账户业务组件 */
	@Autowired	
	private ICoinAccountService coinAccountService;
	
	/** 经验值账户业务组件 */
	@Autowired		
	private IExpAccountService expAccountService;

	/**
	 * 获取指定机构的详细信息
	 * 
	 * @param request
	 * 			Http 请求参数，必须参数包含：orgCode
	 * @return 组织机构详细信息
	 * @throws BusinessException
	 */
	@RequestMapping("/info")  
	@ResponseBody
	public ResultJSON getByCode(HttpServletRequest request) throws BusinessException {
		
		Message message = null;
		Map<String, String> resultBody = new HashMap<String, String>();
		try {
			SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(request.getParameter("orgCode"));
			
			if(sysOrgInfo != null) {
				resultBody.put("id", StringTools.formatToString(sysOrgInfo.getId()));
				resultBody.put("orgCode", StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
				resultBody.put("parentCode", StringTools.formatToString(sysOrgInfo.getParentCode()));
				resultBody.put("orgName", StringTools.formatToString(sysOrgInfo.getSysOrgName()));
				resultBody.put("orgFullname", StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
				resultBody.put("orgType", StringTools.formatToString(sysOrgInfo.getSysOrgType()));
				resultBody.put("orgOrder", StringTools.formatToString(sysOrgInfo.getSysOrgOrder()));
				resultBody.put("isEnable", StringTools.formatToString(sysOrgInfo.getIsEnable()));
				message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			} else {
				message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			}
			
			ResultJSON rj = new ResultJSON();
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取指定机构的详细信息"));
	        rj.setBody(resultBody);
			
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.getByCode 获取指定机构信息出错", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取指定机构的详细信息"));
		}
	}
	
	
	/**
	 * 获取指定机构的详细信息（多条）
	 * 
	 * @param request
	 * 			Http 请求参数，必须参数包含：orgCode
	 * @return 组织机构详细信息
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/infos")  
	public ResultJSON getByCodes(HttpServletRequest request) throws BusinessException {
		
		Message m = null;
		try {
			List<String> codeList = Arrays.asList(request.getParameter("orgCodes").split(","));
			List<SysOrgInfo> orgList = sysOrgService.selectByCodes(codeList);
			
			JSONObject body = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < orgList.size(); i++) {
				JSONObject orgInfo = new JSONObject();
				orgInfo.put("id", StringTools.formatToString(orgList.get(i).getId()));
				orgInfo.put("orgCode", StringTools.formatToString(orgList.get(i).getSysOrgCode()));
				orgInfo.put("parentCode", StringTools.formatToString(orgList.get(i).getParentCode()));
				orgInfo.put("orgName", StringTools.formatToString(orgList.get(i).getSysOrgName()));
				orgInfo.put("orgFullName", StringTools.formatToString(orgList.get(i).getSysOrgFullname()));
				orgInfo.put("orgType", StringTools.formatToString(orgList.get(i).getSysOrgType()));
				orgInfo.put("orgOrder", StringTools.formatToString(orgList.get(i).getSysOrgOrder()));
				orgInfo.put("isEnable", StringTools.formatToString(orgList.get(i).getIsEnable()));
				jsonArray.add(orgInfo);
			}
			body.put("dataList", jsonArray);
			ResultJSON rj = new ResultJSON();
			log.info("SysOrgController.linkList:获取组织级联下拉");
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			rj.setReturnCode(m.getCode());
	        rj.setReturnTip(String.format(m.getTip(), "获取指定机构的详细信息"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			log.error("SysOrgController.getByCodes 获取指定机构信息出错", e);
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(), "获取多个机构的详细信息"));
		}
	}
	
	/**
	 * 级联向上查询行政组织信息
	 * 
	 * @param request
	 * 			Http 请求参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/cascadeUpManOrg") 
	public ResultJSON cascadeUpManOrg(HttpServletRequest request) throws BusinessException{
		
		Message m = null;
		JSONObject body = new JSONObject();
		try {			
			List<SysOrgInfo> manOrgInfos = sysOrgService.selectCascadeUpManOrg(request.getParameter("manOrgCode"));						
			for(SysOrgInfo manOrgInfo : manOrgInfos) {
				body.put(manOrgInfo.getSysOrgType(), manOrgInfo.getSysOrgCode());
			}
			
			ResultJSON rj = new ResultJSON();
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
        	
			rj.setReturnCode(m.getCode());
	        rj.setReturnTip(String.format(m.getTip(), "级联向上查询行政组织信息"));
	        rj.setBody(body);
	        
	        return rj;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"级联向上查询行政组织信息"));
		}
	}
	 
	/**
	 * 组织树，根据当前用户所属的行政组织，动态构建根节点及子孙节点
	 * 
	 * @param param
	 * 			封装构建组织树所需的参数，必须参数：userCode
	 * @return 通用返回参数
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/tree")
	public ResultJSON tree(@RequireValid OrgTreeVo param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	// 获取用户信息
        	SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());        	
        	// 获取用户所属的行政组织，作为根节点
        	SysOrgInfo topOrg = userInfo.getManageOrgInfo();
        	
        	// 获取 parentCode 等于根节点的组织，做为二级节点
        	SysOrgInfo record = new SysOrgInfo();
        	record.setParentCode(topOrg.getSysOrgCode());
        	// 
        	record.setIsEnable(AuthConstants.BOOLEAN_TRUE);
        	List<SysOrgInfo> orgList = sysOrgService.selectByRecord(record);
        	
        	JSONObject body = new JSONObject();        	
    	
        	// 封装根节点信息
        	body.put("orgCode", StringTools.formatToString(topOrg.getSysOrgCode()));
        	body.put("orgName", StringTools.formatToString(topOrg.getSysOrgName()));
        	body.put("orgFullName", StringTools.formatToString(topOrg.getSysOrgFullname()));
        	body.put("orgType", StringTools.formatToString(topOrg.getSysOrgType()));  
        	
        	// 判断根节点是否有下级节点，返回标识属性，用于控制界面中底层节点前的符号控制
        	if(CollectionUtils.isEmpty(orgList)) {
        		body.put("isParent", "false");
        	} else {
        		body.put("isParent", "true");
        	}

        	JSONArray jsonArray = new JSONArray();
        	// 封装根节点的下级节点信息
        	jsonArray = new JSONArray();
        	for (int i = 0; i < orgList.size(); i++) {
        		JSONObject orgJson = new JSONObject();
        		
            	SysOrgInfo searchParam = new SysOrgInfo();
            	searchParam.setParentCode(orgList.get(i).getSysOrgCode());
            	List<SysOrgInfo> subOrgList = sysOrgService.selectByRecord(searchParam);
            	if(CollectionUtils.isEmpty(subOrgList)) {
            		orgJson.put("isParent", "false");
            	} else {
            		orgJson.put("isParent", "true");
            	}            	
        		        		
        		orgJson.put("orgCode",StringTools.formatToString(orgList.get(i).getSysOrgCode()));
        		orgJson.put("parentCode",StringTools.formatToString(orgList.get(i).getParentCode()));
        		orgJson.put("orgName",StringTools.formatToString(orgList.get(i).getSysOrgName()));
        		orgJson.put("orgFullName",StringTools.formatToString(orgList.get(i).getSysOrgFullname()));
        		orgJson.put("orgType",StringTools.formatToString(orgList.get(i).getSysOrgType()));
        		jsonArray.add(orgJson);
			}
        	body.put("orgList", jsonArray);
        	
            log.info("SysOrgController.tree:获取组织机构树");
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"获取组织机构树"));
            rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取组织机构树"));
		}
        return rj;
	}
		
	/**
	 * 组织树，全量组织，根节点为集团
	 * 
	 * @param param
	 * 			封装构建组织树所需的参数
	 * @return 通用返回参数
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/topTree")
	public ResultJSON topTree(@RequireValid StaticTreeVo staticTreeVo) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	JSONObject body = new JSONObject();
        	JSONArray jsonArray = new JSONArray();
        	        	
        	SysOrgInfo record = new SysOrgInfo();
        	// 查询根节点时，上级组织编码设置为“0”
        	record.setParentCode("0");
        	// 租户编码，用以隔离查询不同租户的数据
        	record.setSaasTenantCode(staticTreeVo.getSaasTenantCode());
        	record.setIsEnable(AuthConstants.BOOLEAN_TRUE);
        	
        	SysOrgInfo topOrg = null;
        	// 查询静态组织树根节点
        	List<SysOrgInfo> orgInfos = sysOrgService.selectByRecord(record);        	
        	if(CollectionUtils.isNotEmpty(orgInfos)) {
        		topOrg = orgInfos.get(0);
            	body.put("topOrgCode", StringTools.formatToString(topOrg.getSysOrgCode()));
            	body.put("topOrgName", StringTools.formatToString(topOrg.getSysOrgName()));
            	body.put("topOrgFullName", StringTools.formatToString(topOrg.getSysOrgFullname()));
            	body.put("topOrgType", StringTools.formatToString(topOrg.getSysOrgType()));
            	body.put("isEnable", StringTools.formatToString(topOrg.getIsEnable()));
            	// 查询静态组织树直属于根节点的下级节点
            	record.setParentCode(topOrg.getSysOrgCode());
            	
            	List<SysOrgInfo> orgList = sysOrgService.selectByRecord(record);
            	
            	if(CollectionUtils.isEmpty(orgList)) {
            		body.put("isParent", "false");
            	} else {
            		body.put("isParent", "true");
            	}            	            	
            	
            	for (int i = 0; i < orgList.size(); i++) {
            		JSONObject orgJson = new JSONObject();
            		
                	SysOrgInfo searchParam = new SysOrgInfo();
                	searchParam.setParentCode(orgList.get(i).getSysOrgCode());
                	List<SysOrgInfo> subOrgList = sysOrgService.selectByRecord(searchParam);
                	if(CollectionUtils.isEmpty(subOrgList)) {
                		orgJson.put("isParent", "false");
                	} else {
                		orgJson.put("isParent", "true");
                	}            		
            		
            		orgJson.put("orgCode",StringTools.formatToString(orgList.get(i).getSysOrgCode()));
            		orgJson.put("parentCode",StringTools.formatToString(orgList.get(i).getParentCode()));
            		orgJson.put("orgName",StringTools.formatToString(orgList.get(i).getSysOrgName()));
            		orgJson.put("orgFullName",StringTools.formatToString(orgList.get(i).getSysOrgFullname()));
            		orgJson.put("orgType",StringTools.formatToString(orgList.get(i).getSysOrgType()));
            		orgJson.put("isEnable",StringTools.formatToString(orgList.get(i).getIsEnable()));
            		jsonArray.add(orgJson);
    			}        		
        	}
        	body.put("orgList",jsonArray);
        	
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"获取静态组织树"));
            rj.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取静态组织树"));
		}
        return rj;
	}
	
	
	/**
	 * @Function topTreeClean
	 * @Description 用于数据清洗,加载集团与省
	 * @param staticTreeVo
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/topTreeClean")
	public ResultJSON topTreeClean(@RequireValid StaticTreeVo staticTreeVo) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	JSONObject body = new JSONObject();
        	JSONArray jsonArray = new JSONArray();
        	        	
        	SysOrgInfo record = new SysOrgInfo();
        	// 查询根节点时，上级组织编码设置为“0”
        	record.setParentCode("0");
        	// 租户编码，用以隔离查询不同租户的数据
        	record.setSaasTenantCode(staticTreeVo.getSaasTenantCode());
        	
        	SysOrgInfo topOrg = null;
        	// 查询静态组织树根节点
        	List<SysOrgInfo> orgInfos = sysOrgService.selectByRecord(record);        	
        	if(CollectionUtils.isNotEmpty(orgInfos)) {
        		topOrg = orgInfos.get(0);
            	body.put("topOrgCode", StringTools.formatToString(topOrg.getSysOrgCode()));
            	body.put("topOrgName", StringTools.formatToString(topOrg.getSysOrgName()));
            	body.put("topOrgFullName", StringTools.formatToString(topOrg.getSysOrgFullname()));
            	body.put("topOrgType", StringTools.formatToString(topOrg.getSysOrgType()));
            	if(topOrg.getIsEnable().equals(Byte.valueOf("1"))){//删除禁用都表示,禁用
            		body.put("isEnable", StringTools.formatToString(topOrg.getIsEnable()));
            	}else{
            		body.put("isEnable", "0");
            	}
            	// 查询静态组织树直属于根节点的下级节点
            	record.setParentCode(topOrg.getSysOrgCode());
            	
            	List<SysOrgInfo> orgList = sysOrgService.selectByRecord(record);
            	
            	if(CollectionUtils.isEmpty(orgList)) {
            		body.put("isParent", "false");
            	} else {
            		body.put("isParent", "true");
            	}            	            	
            	
            	for (int i = 0; i < orgList.size(); i++) {
            		if(orgList.get(i).getIsEnable().equals(Byte.valueOf("2"))){//删除状态,不展示
            			continue;
            		}
            		
            		JSONObject orgJson = new JSONObject();
            		
                	SysOrgInfo searchParam = new SysOrgInfo();
                	searchParam.setParentCode(orgList.get(i).getSysOrgCode());
                	List<SysOrgInfo> subOrgList = sysOrgService.selectByRecord(searchParam);
                	if(CollectionUtils.isEmpty(subOrgList)) {
                		orgJson.put("isParent", "false");
                	} else {
                		orgJson.put("isParent", "true");
                	}            		
            		
            		orgJson.put("orgCode",StringTools.formatToString(orgList.get(i).getSysOrgCode()));
            		orgJson.put("parentCode",StringTools.formatToString(orgList.get(i).getParentCode()));
            		orgJson.put("orgName",StringTools.formatToString(orgList.get(i).getSysOrgName()));
            		orgJson.put("orgFullName",StringTools.formatToString(orgList.get(i).getSysOrgFullname()));
            		orgJson.put("orgType",StringTools.formatToString(orgList.get(i).getSysOrgType()));
            		orgJson.put("isEnable",StringTools.formatToString(orgList.get(i).getIsEnable()));
            		jsonArray.add(orgJson);
    			}        		
        	}
        	body.put("orgList",jsonArray);
        	
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"获取静态组织树"));
            rj.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取静态组织树"));
		}
        return rj;
	}
	
	/**
	 * 组织联动
	 * 
	 * @param param
	 * 			封装请求参数，必须参数：userCode
	 * @return 行政组织信息（三级）
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/linkList")
	public ResultJSON linkList(@RequireValid OrgLinkVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	SysOrgInfo record = new SysOrgInfo();
	    	SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
	    	if(userInfo != null){
	    		String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();//行政orgCode
	    		String orgType = userInfo.getManageOrgInfo().getSysOrgType();//行政org类型
	    		record.setSysOrgCode(orgCode);
	    		record.setSysOrgType(orgType);
	    	}else{
	    		m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10004);
				throw new BusinessException(m.getCode(), String.format(m.getTip(),"查询集团/省/市级联下拉","用户code无效"));
	    	}
	    	OrgLinkList orgLinkList = sysOrgService.getOrgLinkList(record);
	    	//集团等级
			SysOrgInfo topOrg = orgLinkList.getTopOrg();
			JSONObject body = new JSONObject();
			body.put("topOrgCode", topOrg.getSysOrgCode());
			body.put("topOrgName", topOrg.getSysOrgName());
			body.put("topOrgFullName", topOrg.getSysOrgFullname());
	    	//省
	    	JSONArray proArray = new JSONArray();
			List<SysOrgInfo> proList = orgLinkList.getProList();
	    	for (int i = 0; i < proList.size(); i++) {
	    		JSONObject orgJson = new JSONObject();
	    		orgJson.put("orgCode", StringTools.formatToString(proList.get(i).getSysOrgCode()));
	    		orgJson.put("orgFullName", StringTools.formatToString(proList.get(i).getSysOrgFullname()));
	    		proArray.add(orgJson);
			}
	    	body.put("proList", proArray);
	    	//市
	    	JSONArray cityArray = new JSONArray();
			List<SysOrgInfo> cityList = orgLinkList.getCityList();
	    	for (int i = 0; i < cityList.size(); i++) {
	    		JSONObject orgJson = new JSONObject();
	    		orgJson.put("orgCode", StringTools.formatToString(cityList.get(i).getSysOrgCode()));
	    		orgJson.put("orgFullName", StringTools.formatToString(cityList.get(i).getSysOrgFullname()));
	    		cityArray.add(orgJson);
			}
	    	body.put("cityList", cityArray);
	    	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
	        rj.setReturnCode(m.getCode());
	        rj.setReturnTip(String.format(m.getTip(),"查询集团/省/市级联下拉"));
	        rj.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("查询集团/省/市级联下拉", e);
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"查询集团/省/市级联下拉"));
		}
	    return rj;
	}

	
	/**
	 * @Function teamLinkList
	 * @Description 编辑班组时,根据班组所属org,返回相应的列表
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/team/linkList")
	public ResultJSON teamLinkList(@RequireValid SubOrgVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	SysOrgInfo orgInfo = sysOrgService.selectByCode(param.getOrgCode());
	    	JSONObject body = new JSONObject();
	    	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"获取组织机构列表"));
	    	if(orgInfo == null || orgInfo.getSysOrgType().equals(AuthConstants.ORG_TYPE_A)){
		        body.put("proList", new JSONArray());
		        body.put("cityList", new JSONArray());
	    	}else if(orgInfo.getSysOrgType().equals(AuthConstants.ORG_TYPE_B)){
	    		SysOrgInfo record = new SysOrgInfo();
	    		record.setParentCode(orgInfo.getParentCode());
	    		record.setSysOrgType(AuthConstants.ORG_TYPE_B);
	    		record.setIsEnable(AuthConstants.BOOLEAN_TRUE);
	    		List<SysOrgInfo> proList = sysOrgService.selectByRecord(record);
	    		JSONArray proArray = new JSONArray();
	    		for (int i = 0; i < proList.size(); i++) {
					JSONObject pro = new JSONObject();
					pro.put("orgCode", StringTools.formatToString(proList.get(i).getSysOrgCode()));
					pro.put("orgFullName", StringTools.formatToString(proList.get(i).getSysOrgFullname()));
					if(orgInfo.getSysOrgCode().equals(proList.get(i).getSysOrgCode())){
						pro.put("isSel", AuthConstants.BOOLEAN_1);
					}else{
						pro.put("isSel", AuthConstants.BOOLEAN_0);
					}
					proArray.add(pro);
				}
	    		body.put("proList", proArray);
		        body.put("cityList", new JSONArray());
	    	}else if(orgInfo.getSysOrgType().equals(AuthConstants.ORG_TYPE_C)){
	    		SysOrgInfo record = new SysOrgInfo();
	    		record.setParentCode(orgInfo.getParentCode());
	    		record.setSysOrgType(AuthConstants.ORG_TYPE_C);
	    		record.setIsEnable(AuthConstants.BOOLEAN_TRUE);
	    		List<SysOrgInfo> cityList = sysOrgService.selectByRecord(record);
	    		JSONArray cityArray = new JSONArray();
	    		for (int i = 0; i < cityList.size(); i++) {
					JSONObject pro = new JSONObject();
					pro.put("orgCode", StringTools.formatToString(cityList.get(i).getSysOrgCode()));
					pro.put("orgFullName", StringTools.formatToString(cityList.get(i).getSysOrgFullname()));
					if(orgInfo.getSysOrgCode().equals(cityList.get(i).getSysOrgCode())){
						pro.put("isSel", AuthConstants.BOOLEAN_1);
					}else{
						pro.put("isSel", AuthConstants.BOOLEAN_0);
					}
					cityArray.add(pro);
				}
	    		body.put("cityList", cityArray);
	    		
	    		SysOrgInfo parentOrg = sysOrgService.selectByCode(orgInfo.getParentCode());
	    		record.setParentCode(parentOrg.getParentCode());
	    		record.setSysOrgType(AuthConstants.ORG_TYPE_B);
	    		record.setIsEnable(AuthConstants.BOOLEAN_TRUE);
	    		List<SysOrgInfo> proList = sysOrgService.selectByRecord(record);
	    		JSONArray proArray = new JSONArray();
	    		for (int i = 0; i < proList.size(); i++) {
					JSONObject pro = new JSONObject();
					pro.put("orgCode", StringTools.formatToString(proList.get(i).getSysOrgCode()));
					pro.put("orgFullName", StringTools.formatToString(proList.get(i).getSysOrgFullname()));
					if(parentOrg.getSysOrgCode().equals(proList.get(i).getSysOrgCode())){
						pro.put("isSel", AuthConstants.BOOLEAN_1);
					}else{
						pro.put("isSel", AuthConstants.BOOLEAN_0);
					}
					proArray.add(pro);
				}
	    		body.put("proList", proArray);
	    	}else{
		        body.put("proList", new JSONArray());
		        body.put("cityList", new JSONArray());
	    	}
	    	rj.setBody(body);
	        return rj;
	    } catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取组织机构列表"));
		}
	}
	
	/**
	 * 获取指定组织的下级行政组织
	 * 
	 * @param param
	 * 			封装参数，必须参数：orgCode
	 * @return 通用返回参数
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/subManList")
	public ResultJSON subManList(@RequireValid SubOrgVo param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	List<SysOrgInfo> orgList = sysOrgService.selectSubManOrg(param.getOrgCode());
        	
        	JSONArray jsonArray = new JSONArray();
        	JSONObject body = new JSONObject();
        	for (int i = 0; i < orgList.size(); i++) {
        		JSONObject orgJson = new JSONObject();
        		orgJson.put("orgCode",StringTools.formatToString(orgList.get(i).getSysOrgCode()));
        		orgJson.put("parentCode",StringTools.formatToString(orgList.get(i).getParentCode()));
        		orgJson.put("orgName",StringTools.formatToString(orgList.get(i).getSysOrgName()));
        		orgJson.put("orgFullName",StringTools.formatToString(orgList.get(i).getSysOrgFullname()));
        		orgJson.put("orgType",StringTools.formatToString(orgList.get(i).getSysOrgType()));
        		jsonArray.add(orgJson);
			}
        	body.put("dataList",jsonArray);
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"获取指定组织的下级行政组织"));
            rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取指定组织的下级行政组织"));
		}
        return rj;
	}

	
	/**
	 * 获取指定组织的下级节点
	 * 
	 * @param param
	 * 			封装请求参数，必须参数：orgCode
	 * @return 组织信息
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/subList")
	public ResultJSON subList(@RequireValid SubOrgVo param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	SysOrgInfo record = new SysOrgInfo();
        	record.setParentCode(param.getOrgCode());
        	record.setIsEnable(AuthConstants.BOOLEAN_TRUE);
        	List<SysOrgInfo> orgList = sysOrgService.selectByRecord(record);
        	JSONArray jsonArray = new JSONArray();
        	JSONObject body = new JSONObject();
        	for (int i = 0; i < orgList.size(); i++) {
        		JSONObject orgJson = new JSONObject();
        		
            	SysOrgInfo searchParam = new SysOrgInfo();
            	searchParam.setParentCode(orgList.get(i).getSysOrgCode());
            	List<SysOrgInfo> subOrgList = sysOrgService.selectByRecord(searchParam);
            	if(CollectionUtils.isEmpty(subOrgList)) {
            		orgJson.put("isParent", "false");
            	} else {
            		orgJson.put("isParent", "true");
            	}          		
        		
        		orgJson.put("isEnable",StringTools.formatToString(orgList.get(i).getIsEnable()));
        		orgJson.put("orgCode",StringTools.formatToString(orgList.get(i).getSysOrgCode()));
        		orgJson.put("parentCode",StringTools.formatToString(orgList.get(i).getParentCode()));
        		orgJson.put("orgName",StringTools.formatToString(orgList.get(i).getSysOrgName()));
        		orgJson.put("orgFullName",StringTools.formatToString(orgList.get(i).getSysOrgFullname()));
        		orgJson.put("orgType",StringTools.formatToString(orgList.get(i).getSysOrgType()));
        		jsonArray.add(orgJson);
			}
        	body.put("dataList",jsonArray);
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"获取指定组织的下级组织"));
            rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取指定组织的下级组织"));
		}
        return rj;
	}
	
	
	
	/**
	 * @Function subListClean
	 * @Description 获取下级组织,用于数据清洗
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/subListClean")
	public ResultJSON subListClean(@RequireValid SubOrgVo param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	SysOrgInfo record = new SysOrgInfo();
        	record.setParentCode(param.getOrgCode());
        	List<SysOrgInfo> orgList = sysOrgService.selectByRecord(record);
        	JSONArray jsonArray = new JSONArray();
        	JSONObject body = new JSONObject();
        	for (int i = 0; i < orgList.size(); i++) {
        		JSONObject orgJson = new JSONObject();
        		if(Byte.valueOf("2").equals(orgList.get(i).getIsEnable())){//删除,不返回
        			continue;
        		}
        		
            	SysOrgInfo searchParam = new SysOrgInfo();
            	searchParam.setParentCode(orgList.get(i).getSysOrgCode());
            	List<SysOrgInfo> subOrgList = sysOrgService.selectByRecord(searchParam);
            	if(CollectionUtils.isEmpty(subOrgList)) {
            		orgJson.put("isParent", "false");
            	} else {
            		orgJson.put("isParent", "true");
            	}          		
        		
        		orgJson.put("isEnable",StringTools.formatToString(orgList.get(i).getIsEnable()));
        		orgJson.put("orgCode",StringTools.formatToString(orgList.get(i).getSysOrgCode()));
        		orgJson.put("parentCode",StringTools.formatToString(orgList.get(i).getParentCode()));
        		orgJson.put("orgName",StringTools.formatToString(orgList.get(i).getSysOrgName()));
        		orgJson.put("orgFullName",StringTools.formatToString(orgList.get(i).getSysOrgFullname()));
        		orgJson.put("orgType",StringTools.formatToString(orgList.get(i).getSysOrgType()));
        		jsonArray.add(orgJson);
			}
        	body.put("dataList",jsonArray);
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"获取指定组织的下级组织"));
            rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取指定组织的下级组织"));
		}
        return rj;
	}

	
	/**
	 * 组织数据清洗：添加下级组织
	 * 
	 * @param param
	 * 			封装请求参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/addSubOrg")
	public ResultJSON addSubOrg(@RequireValid SmapOrgAddVo param) throws BusinessException{  
            	
        Message m = null;
        ResultJSON rj = new ResultJSON();
        JSONObject body = new JSONObject();
        try {
        	// 如果待添加的组织标识为“行政组织”，判断其上级是否为“行政组织”
        	if(StringUtils.equals(AuthConstants.BOOLEAN_1, param.getIsManager())) {
        		SysOrgInfo orgInfoIsMan = sysOrgService.selectByCode(param.getParentOrgCode());
        		if(StringUtils.isBlank(orgInfoIsMan.getSysOrgType())) {
        			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10006);
        			rj.setReturnCode(m.getCode());
        			rj.setReturnTip(m.getTip());
        			rj.setBody(body);
        			return rj;        		
        		}
        	}
        	
        	// 判断待添加的组织是否已存在
        	SysOrgInfo orgInfoBySmap = sysOrgService.selectBySrcCode(param.getSmapOrgCode());
        	if(orgInfoBySmap != null) {//存在  更新  parentCode/manOrgCode/orgType
        		sysOrgService.updateOrg(param.getSmapOrgCode(), param.getParentOrgCode(), param.getIsManager());
        	}else{//新增
        		// 组织数据清洗：新增组织,返回新增的组织信息
        		SysOrgInfo newSysOrgInfo = sysOrgService.addSubOrg(param.getSmapOrgCode(), param.getParentOrgCode(), param.getIsManager());
        		// 获取新增的组织编码
        		String newSysOrgCode  = newSysOrgInfo.getSysOrgCode();
        		
        		if(StringUtils.isNotBlank(newSysOrgInfo.getSysOrgType())) {
        			// 当新增组织为行政组织时，为其创建积分账户
        			IntegralAccount integralAccount = new IntegralAccount();
        			integralAccount.setAccountCode(newSysOrgCode);
        			integralAccount.setAccountType(CreditConstants.TYPE_CORPORATION);
        			integralAccount.setOrgCode(newSysOrgCode);
        			integralAccount.setIntegralBalance(Integer.valueOf(0));
        			integralAccount.setCreateTime(DateUtils.nowTimeMillis());
        			integralAccount.setModifyTime(DateUtils.nowTimeMillis());
        			integralAccount.setSaasTenantCode(AuthConstants.TENANT_CODE);
        			
        			integralAccountService.insert(integralAccount);
        		}
        		
        		SysUserInfo searchParam = new SysUserInfo(); 
        		searchParam.setOrgCode(newSysOrgCode);
        		List<SysUserInfo> userInfos = sysUserService.selectByRecord(searchParam);
        		
        		// 存放用户编码，用于批量设置员工为“普通员工”
        		StringBuffer userCodes = new StringBuffer();
        		
        		for(SysUserInfo userInfo : userInfos) {
        			// 为用户创建积分账户
        			IntegralAccount integralAccount = new IntegralAccount();
        			integralAccount.setAccountCode(userInfo.getUserCode());
        			integralAccount.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
        			integralAccount.setOrgCode(newSysOrgInfo.getManOrgCode());
        			integralAccount.setIntegralBalance(Integer.valueOf(0));
        			integralAccount.setCreateTime(DateUtils.nowTimeMillis());
        			integralAccount.setModifyTime(DateUtils.nowTimeMillis());
        			integralAccount.setSaasTenantCode(AuthConstants.TENANT_CODE);        		
        			integralAccountService.insert(integralAccount);
        			
        			// 为用户创建金币账户
        			CoinAccount coinAccount = new CoinAccount();
        			coinAccount.setAccountCode(userInfo.getUserCode());
        			coinAccount.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
        			coinAccount.setOrgCode(newSysOrgInfo.getManOrgCode());
        			coinAccount.setCoinBalance(Integer.valueOf(0));
        			coinAccount.setCreateTime(DateUtils.nowTimeMillis());
        			coinAccount.setModifyTime(DateUtils.nowTimeMillis());
        			coinAccount.setSaasTenantCode(AuthConstants.TENANT_CODE);           		        		
        			coinAccountService.insert(coinAccount);
        			
        			// 为用户创建经验值账户
        			ExpAccount expAccount = new ExpAccount();
        			expAccount.setAccountCode(userInfo.getUserCode());
        			expAccount.setCurrentExp(Integer.valueOf(0));
        			expAccount.setCurrentLevel(Integer.valueOf(1));
        			expAccount.setCreateTime(DateUtils.nowTimeMillis());
        			expAccount.setModifyTime(DateUtils.nowTimeMillis());
        			expAccount.setOrgCode(newSysOrgInfo.getManOrgCode());        		
        			expAccount.setSaasTenantCode(AuthConstants.TENANT_CODE);        		
        			expAccountService.insert(expAccount); 
        			
        			userCodes.append(userInfo.getUserCode()).append(",");
        		}
        		
        		// 批量设置员工为“普通员工”
        		if(StringUtils.isNotBlank(userCodes.toString())) {
        			sysUserService.setIsLeader(StringUtils.removeEnd(userCodes.toString(), ","), AuthConstants.BOOLEAN_0);
        		}
        	}
        	  
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"组织数据清洗：添加下级组织"));
            rj.setBody(body);
		} catch (Exception e) {
			log.error("组织数据清洗：添加下级组织出错", e);
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"组织数据清洗：添加下级组织"));
		}
        return rj;
	}
	
	/**
	 * 组织数据清洗：删除组织
	 * 
	 * @param param
	 * 			封装请求参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/delOrg")
	public ResultJSON delOrg(HttpServletRequest request) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        JSONObject body = new JSONObject();
        try {
        	// 如果待删除的组织存在上级组织，则不允许删除
        	SysOrgInfo param = new SysOrgInfo();
        	param.setParentCode(request.getParameter("orgCode"));
        	List<String> list = sysOrgService.selectOrgCodes(param);
        	
        	if(CollectionUtils.isNotEmpty(list)) {
        		m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10007);
        		rj.setReturnCode(m.getCode());
                rj.setReturnTip(m.getTip());
                rj.setBody(body);
                return rj;   
        	}
        	
        	// 删除组织     逻辑删除  is_enable=2   	
        	sysOrgService.deleteByCode(request.getParameter("orgCode"));

        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"组织数据清洗：删除组织"));
            rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取指定组织的下级组织"));
		}
        return rj;
	}
	
	/**
	 * @Function topTreeFilter
	 * @Description 获取1层与2层非行政结构树
	 * @param staticTreeVo
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/topTreeFilter")
	public ResultJSON topTreeFilter(@RequireValid StaticTreeVo staticTreeVo) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	JSONObject body = new JSONObject();
        	JSONArray jsonArray = new JSONArray();
        	        	
        	SysOrgInfo record = new SysOrgInfo();
        	// 查询根节点时，上级组织编码设置为“0”
        	record.setParentCode("0");
        	// 租户编码，用以隔离查询不同租户的数据
        	record.setSaasTenantCode(staticTreeVo.getSaasTenantCode());
        	        	
        	SysOrgInfo topOrg = null;
        	record.setIsEnable(AuthConstants.BOOLEAN_TRUE);
        	// 查询静态组织树根节点
        	List<SysOrgInfo> orgInfos = sysOrgService.selectByRecord(record);        	
        	if(CollectionUtils.isNotEmpty(orgInfos)) {
        		topOrg = orgInfos.get(0);
            	body.put("orgCode", StringTools.formatToString(topOrg.getSysOrgCode()));
            	body.put("orgName", StringTools.formatToString(topOrg.getSysOrgName()));
            	body.put("orgFullName", StringTools.formatToString(topOrg.getSysOrgFullname()));
            	body.put("orgType", StringTools.formatToString(topOrg.getSysOrgType()));
            	
            	// 查询静态组织树直属于根节点的下级节点
            	record.setParentCode(topOrg.getSysOrgCode());
            	List<SysOrgInfo> orgList = sysOrgService.selectByRecord(record);            	
            	for (int i = 0; i < orgList.size(); i++) {
            		JSONObject orgJson = new JSONObject();
            		SysOrgInfo info = orgList.get(i);
            		if(info.getSysOrgType() != null){
            			continue;
            		}
            		orgJson.put("orgCode",StringTools.formatToString(orgList.get(i).getSysOrgCode()));
            		orgJson.put("parentCode",StringTools.formatToString(orgList.get(i).getParentCode()));
            		orgJson.put("orgName",StringTools.formatToString(orgList.get(i).getSysOrgName()));
            		orgJson.put("orgFullName",StringTools.formatToString(orgList.get(i).getSysOrgFullname()));
            		orgJson.put("orgType",StringTools.formatToString(orgList.get(i).getSysOrgType()));
            		jsonArray.add(orgJson);
    			}        		
        	}
        	body.put("orgList",jsonArray);
        	
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"获取静态组织树"));
            rj.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取静态组织树"));
		}
        return rj;
	}

	/**
	 * @Function isEnable
	 * @Description 组织机构启禁用,目前只针对行政组织
	 * @param isEnableVo
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/setIsEnable")
	public ResultJSON setIsEnable(@RequireValid IsEnableVo param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	JSONObject body = new JSONObject();
        	
//        	SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
//        	String tenantCode = userInfo.getTenantCode();
        	
        	sysOrgService.setIsEnable(param.getOrgCode(), Byte.parseByte(param.getIsEnable()), param.getSaasTenantCode());
        	
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
            rj.setReturnCode(m.getCode());
            if(param.getIsEnable().equals("1")){
            	rj.setReturnTip(String.format(m.getTip(),"组织机构启用"));
            }else{
            	rj.setReturnTip(String.format(m.getTip(),"组织机构禁用"));
            }
            rj.setBody(body);
		} catch(BusinessException e){
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"组织机构启禁用"));
		}
        return rj;
	}
}
