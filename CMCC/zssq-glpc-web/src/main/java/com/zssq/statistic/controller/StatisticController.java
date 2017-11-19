package com.zssq.statistic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zssq.dao.pojo.*;
import com.zssq.news.dao.pojo.NewsInfoContent;
import com.zssq.service.ISysOrgService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IStatisticService;
import com.zssq.statistic.vo.StatisticVO;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller  
@RequestMapping("/statistic")
public class StatisticController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IStatisticService statisticService;

	@Autowired
	private ISysOrgService sysOrgService;

	@RequestMapping("/getTeamInfos")
	@ResponseBody
    public ResultJSON getTeamPage(HttpServletRequest request,HttpServletResponse response,@RequireValid StatisticVO param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	PageParam pageParam = new PageParam(Integer.valueOf(param.getPageNo()),Integer.valueOf(param.getPageSize()));
        	StatisticCommon record = new StatisticCommon();
			if(null != param.getOrgCode() && !"".equals(param.getOrgCode())){
				record.setOrgCode(param.getOrgCode());
			}
			if(null != param.getTeamCode() && !"".equals(param.getTeamCode())){
				record.setTeamCode(param.getTeamCode());
			}
			if(null != param.getTeamName() && !"".equals(param.getTeamName())){
				record.setTeamName(param.getTeamName());
			}

        	if(param.getStartTime() != null){
        		record.setStartTime(Long.valueOf(param.getStartTime()));
        	}
        	if(param.getEndTime() != null){
        		record.setEndTime(Long.valueOf(param.getEndTime()));
        	}
        	
        	PageBean pageBean = statisticService.getTeam(pageParam, record);
        	
        	JSONArray arrayJSON = new JSONArray();
        	for (int i = 0; i < pageBean.getRecordList().size(); i++) {
        		JSONObject objectJSON = new JSONObject();
        		StatisticCommon sc = (StatisticCommon) pageBean.getRecordList().get(i);
        		objectJSON.put("orgCode", sc.getOrgCode());
        		objectJSON.put("teamCode", sc.getTeamCode());
        		objectJSON.put("teamName", sc.getTeamName());
        		objectJSON.put("microblog", sc.getMicroblog());
        		objectJSON.put("blog", sc.getBlog());
        		objectJSON.put("post", sc.getPost());
        		objectJSON.put("vote", sc.getVote());
        		objectJSON.put("message", sc.getMessage());
        		objectJSON.put("faces", sc.getFaces());
        		objectJSON.put("networkDisk", sc.getNetworkDisk());
        		objectJSON.put("repository", sc.getRepository());
        		objectJSON.put("visitCount", sc.getVisitCount());
        		
				arrayJSON.add(objectJSON);
			}
        	
        	JSONObject jo = new JSONObject();
        	jo.put("totalCount", String.valueOf(pageBean.getTotalCount()));
        	jo.put("list", JSONObject.toJSON(arrayJSON));
             
            log.info("StatisticController.page:获取班组信息成功"); //根据需要打印
            
            
        	m = PropertiesUtil.getMessage("COMMON_200");
            
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(m.getTip());
            rj.setBody(jo);
        	
        	
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw BusinessException.build("STATISTIC_22000", "获取班组信息");
		}
        
        return rj;
	}
	
	@RequestMapping("/getPeopleInfos")
	@ResponseBody
    public ResultJSON getPeopleInfos(HttpServletRequest request,HttpServletResponse response,@RequireValid StatisticVO param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	
        	PageParam pageParam = new PageParam(Integer.valueOf(param.getPageNo()),Integer.valueOf(param.getPageSize()));
        	StatisticCommon record = new StatisticCommon();
        	if(null != param.getOrgCode() && !"".equals(param.getOrgCode())){
				record.setOrgCode(param.getOrgCode());
			}
        	if(null != param.getPeopleCode() && !"".equals(param.getPeopleCode())){
				record.setPeopleCode(param.getPeopleCode());
			}
        	if(null != param.getPeopleName() && !"".equals(param.getPeopleName())){
				record.setPeopleName(param.getPeopleName());
			}
        	if(param.getStartTime() != null){
        		record.setStartTime(Long.valueOf(param.getStartTime()));
        	}
        	if(param.getEndTime() != null){
        		record.setEndTime(Long.valueOf(param.getEndTime()));
        	}
        	
        	PageBean pageBean = statisticService.getPeople(pageParam, record);
        	
        	JSONArray arrayJSON = new JSONArray();
        	for (int i = 0; i < pageBean.getRecordList().size(); i++) {
        		JSONObject objectJSON = new JSONObject();
        		StatisticCommon sc = (StatisticCommon) pageBean.getRecordList().get(i);
        		objectJSON.put("orgCode", sc.getOrgCode());
        		objectJSON.put("peopleCode", sc.getPeopleCode());
        		objectJSON.put("peopleName", sc.getPeopleName());
        		objectJSON.put("microblog", sc.getMicroblog());
        		objectJSON.put("blog", sc.getBlog());
        		objectJSON.put("post", sc.getPost());
        		objectJSON.put("vote", sc.getVote());
        		objectJSON.put("message", sc.getMessage());
        		objectJSON.put("faces", sc.getFaces());
        		objectJSON.put("networkDisk", sc.getNetworkDisk());
        		objectJSON.put("repository", sc.getRepository());
        		objectJSON.put("visitCount", sc.getVisitCount());
        		
				arrayJSON.add(objectJSON);
			}
        	
        	JSONObject jo = new JSONObject();
        	jo.put("totalCount", String.valueOf(pageBean.getTotalCount()));
        	jo.put("list", JSONObject.toJSON(arrayJSON));
             
            log.info("StatisticController.page:获取个人信息成功"); //根据需要打印
            
            
        	m = PropertiesUtil.getMessage("COMMON_200");
            
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(m.getTip());
            rj.setBody(jo);
        	
        	
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw BusinessException.build("STATISTIC_22000", "获取个人信息");
		}
        
        return rj;
	}
	
	@RequestMapping("/getAppsInfos")  
	@ResponseBody
    public ResultJSON getAppsInfos(HttpServletRequest request,HttpServletResponse response,@RequireValid StatisticVO param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	
        	
        	StatisticCommon record = new StatisticCommon();
			if(null != param.getOrgCode() && !"".equals(param.getOrgCode())){
				record.setOrgCode(param.getOrgCode());
			}
        	if(param.getStartTime() != null){
        		record.setStartTime(Long.valueOf(param.getStartTime()));
        	}
        	if(param.getEndTime() != null){
        		record.setEndTime(Long.valueOf(param.getEndTime()));
        	}
        	
        	StatisticApp sa = statisticService.getApp(record);
        	
    		JSONObject objectJSON = new JSONObject();
    		objectJSON.put("orgCode", sa.getOrgCode());
    		objectJSON.put("news", sa.getNews());
    		objectJSON.put("microblog", sa.getMicroblog());
    		objectJSON.put("blog", sa.getBlog());
    		objectJSON.put("activity", sa.getActivity());
    		objectJSON.put("vote", sa.getVote());
    		objectJSON.put("networkDisk", sa.getNetworkDisk());
    		objectJSON.put("repository", sa.getRepository());
        		
        	JSONObject jo = new JSONObject();
        	jo.put("obj", JSONObject.toJSON(objectJSON));
             
            log.info("StatisticController.getAppsInfos:获取应用成功"); //根据需要打印
            
            
        	m = PropertiesUtil.getMessage("COMMON_200");
            
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(m.getTip());
            rj.setBody(jo);
        	
        	
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			
			throw BusinessException.build("STATISTIC_22000", "获取应用");
		}
        
        return rj;
	}
	
	@RequestMapping("/getPortalInfos")  
	@ResponseBody
    public ResultJSON getPortalInfos(HttpServletRequest request,HttpServletResponse response,@RequireValid StatisticVO param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	PageParam pageParam = new PageParam(Integer.valueOf(param.getPageNo()),Integer.valueOf(param.getPageSize()));
        	StatisticPortal record = new StatisticPortal();

        	if(param.getOrgCode() != null && !"".equals(param.getOrgCode())){
				record.setOrgCode(param.getOrgCode());
        	}
        	if(param.getStartTime() != null  && !"".equals(param.getStartTime())){
        		record.setStartTime(Long.valueOf(param.getStartTime()));
        	}
        	if(param.getEndTime() != null && !"".equals(param.getEndTime())){
        		record.setEndTime(Long.valueOf(param.getEndTime()));
        	}
        	
        	PageBean pageBean = statisticService.getPortal(pageParam, record);
			List<String> orgCodes = new ArrayList<>();
			List<StatisticPortal> statisticPortals = pageBean.getRecordList() ;
			for (StatisticPortal statisticPortal :  statisticPortals) {
				orgCodes.add(statisticPortal.getOrgCode()) ;
			}
			List<SysOrgInfo> sysOrgInfos = sysOrgService.selectByCodes(orgCodes) ;
			Map<String, SysOrgInfo> orgMap = generateOrgInfoMap(sysOrgInfos);

        	JSONArray arrayJSON = new JSONArray();

        	for (int i = 0; i < statisticPortals.size(); i++) {
        		JSONObject objectJSON = new JSONObject();
        		StatisticPortal sp = statisticPortals.get(i);
        		objectJSON.put("orgCode", sp.getOrgCode());
				SysOrgInfo sysOrgInfo = orgMap.get(sp.getOrgCode()) ;
				objectJSON.put("orgName", sysOrgInfo==null?"":sysOrgInfo.getSysOrgName());
        		objectJSON.put("homePage", sp.getHomePage());
        		objectJSON.put("news", sp.getNews());
        		objectJSON.put("activity", sp.getActivity());
        		objectJSON.put("marrow", sp.getMarrow());
        		objectJSON.put("hotspot", sp.getHotspot());
        		objectJSON.put("dynamic", sp.getDynamic());
        		objectJSON.put("top", sp.getTop());
        		objectJSON.put("detail", sp.getDetail());
        		objectJSON.put("visitCount", sp.getVisitCount());
        		
				arrayJSON.add(objectJSON);
			}
        	
        	JSONObject jo = new JSONObject();
        	jo.put("totalCount", String.valueOf(pageBean.getTotalCount()));
        	jo.put("list", JSONObject.toJSON(arrayJSON));
             
            log.info("StatisticController.page:获取门户信息成功"); //根据需要打印
            
            
        	m = PropertiesUtil.getMessage("COMMON_200");
            
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(m.getTip());
            rj.setBody(jo);
        	
        	
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw BusinessException.build("STATISTIC_22000", "获取门户信息");
		}
        
        return rj;
	}


	private Map<String,SysOrgInfo> generateOrgInfoMap(List<SysOrgInfo> sysOrgInfos) {
		Map<String, SysOrgInfo> orgInfos = new HashMap<String, SysOrgInfo>();
		for (SysOrgInfo sysOrgInfo : sysOrgInfos) {
			if(null != sysOrgInfo){
				orgInfos.put(sysOrgInfo.getManOrgCode(), sysOrgInfo);
			}
		}
		return orgInfos;
	}
}
