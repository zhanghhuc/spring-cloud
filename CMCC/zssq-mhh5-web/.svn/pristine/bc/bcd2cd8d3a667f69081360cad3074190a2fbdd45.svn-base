package com.zssq.auth.controller;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.auth.vo.MapInfoVo;
import com.zssq.auth.vo.MapOrgRelationSearchVo;
import com.zssq.auth.vo.SrcOrgVo;
import com.zssq.constants.AuthConstants;
import com.zssq.dao.pojo.MapOrgRelation;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IMapOrgRelationService;
import com.zssq.service.ISysOrgService;
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

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ISysOrgService sysOrgService;
	
	/** 门户地图插件与组织对应关系处理业务组件 */
	@Autowired
	private IMapOrgRelationService mapOrgRelationService;


	/**
	 * 
	 * @Function getPrimaryOrgList
	 * @Description 获取集团和省级组织机构列表
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/getPrimaryOrgList")
	public ResultJSON getPrimaryOrgList() throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	JSONArray jsonArray = new JSONArray();
        	JSONObject body = new JSONObject();
        	
        	//查询集团的组织信息并封装入结果集
        	SysOrgInfo record = new SysOrgInfo();
        	record.setSysOrgType(AuthConstants.ORG_TYPE_A);
        	List<SysOrgInfo> levelAOrgList = sysOrgService.selectByRecord(record);
        	for (SysOrgInfo sysOrgInfo : levelAOrgList) {
				JSONObject item = new JSONObject();
				item.put("orgCode", StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
				item.put("orgName", StringTools.formatToString(sysOrgInfo.getSysOrgName()));
				item.put("level", StringTools.formatToString(AuthConstants.ORG_TYPE_A));
				jsonArray.add(item);
			}
        	
        	//查询省级组织的信息并封装入结果集
        	record.setSysOrgType(AuthConstants.ORG_TYPE_B);
        	List<SysOrgInfo> levelBOrgList = sysOrgService.selectByRecord(record);
        	for (SysOrgInfo sysOrgInfo : levelBOrgList) {
				JSONObject item = new JSONObject();
				item.put("orgCode", StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
				item.put("orgName", StringTools.formatToString(sysOrgInfo.getSysOrgName()));
				item.put("level", StringTools.formatToString(AuthConstants.ORG_TYPE_B));
				jsonArray.add(item);
			}
        	
        	//出参
        	body.put("primaryOrgList", jsonArray);
            log.info("SysOrgController.getPrimaryOrgList:获取集团和省级组织机构列表");
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"获取集团和省级组织机构列表"));
            rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取集团和省级组织机构列表"));
		}
        return rj;
	}
	
	/**
	 * 根据原始code查询
	 * 
	 * @param param
	 * 			封装请求参数，必须参数：srcCode
	 * @return 组织信息
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/selectBySrcCode") 
	public ResultJSON selectBySrcCode(@RequireValid SrcOrgVo param) throws BusinessException{
		
		Message m = null;
		JSONObject body = new JSONObject();
		try {			
			SysOrgInfo orgInfo = sysOrgService.selectBySrcCode(param.getSrcCode());						
			
			body.put("isEnable", StringTools.formatToString(orgInfo.getIsEnable()));
			body.put("orgCode", StringTools.formatToString(orgInfo.getManOrgCode()));
			
			ResultJSON rj = new ResultJSON();
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
        	
			rj.setReturnCode(m.getCode());
	        rj.setReturnTip(String.format(m.getTip(), "根据原始code查询"));
	        rj.setBody(body);
	        
	        return rj;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"根据原始code查询"));
		}
	}
	
	
	/**
	 * 查询门户地图插件与组织对应关系数据
	 * 
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/selectMapInfo") 	
	public ResultJSON selectMapInfo(@RequireValid MapInfoVo param) throws BusinessException {

		Message m = null;
		JSONObject body = new JSONObject();
		try {
			MapOrgRelation mapOrgRelation = mapOrgRelationService.selectByCode(param.getOrgCode());
			
			if(mapOrgRelation != null){
				body.put("name", mapOrgRelation.getMapName());
				body.put("province", mapOrgRelation.getMapProvince());
				body.put("mhType", mapOrgRelation.getPortalType());
				body.put("letter", mapOrgRelation.getMapLetter());
				body.put("staticCode", mapOrgRelation.getOrgCode());
				body.put("value", mapOrgRelation.getMapValue());
			}
			
			ResultJSON rj = new ResultJSON();
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
        	
			rj.setReturnCode(m.getCode());
	        rj.setReturnTip(String.format(m.getTip(), "查询门户地图插件与组织对应关系数据(info)"));
	        rj.setBody(body);
	        
	        return rj;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"查询门户地图插件与组织对应关系数据(info)"));
		}		
	}
	
	/**
	 * 查询所有门户地图插件与组织对应关系数据
	 * 
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/searchAllRelationData") 	
	public ResultJSON searchAllRelationData(@RequireValid MapOrgRelationSearchVo searchVo) throws BusinessException {

		Message m = null;
		JSONObject body = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			MapOrgRelation searchParam = new MapOrgRelation();
			searchParam.setPortalType(searchVo.getPortalType());
			searchParam.setMapProvince(searchVo.getProvinceName());

			List<MapOrgRelation> mapOrgRelations = mapOrgRelationService.searchAllRelationData(searchParam);					
			
			if(CollectionUtils.isNotEmpty(mapOrgRelations)) {
				for(MapOrgRelation mapOrgRelation : mapOrgRelations) {
					JSONObject jsonObj = new JSONObject();
					
					jsonObj.put("name", mapOrgRelation.getMapName());
					jsonObj.put("province", mapOrgRelation.getMapProvince());
					jsonObj.put("mhType", mapOrgRelation.getPortalType());
					jsonObj.put("letter", mapOrgRelation.getMapLetter());
					jsonObj.put("staticCode", mapOrgRelation.getOrgCode());
					jsonObj.put("value", mapOrgRelation.getMapValue());
					
					jsonArray.add(jsonObj);					
				}
			}
			body.put("dataList", jsonArray);
			
			ResultJSON rj = new ResultJSON();
        	m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
        	
			rj.setReturnCode(m.getCode());
	        rj.setReturnTip(String.format(m.getTip(), "查询门户地图插件与组织对应关系数据"));
	        rj.setBody(body);
	        
	        return rj;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"查询门户地图插件与组织对应关系数据"));
		}		
	}
}
