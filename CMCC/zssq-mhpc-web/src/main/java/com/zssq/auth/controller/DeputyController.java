package com.zssq.auth.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.auth.vo.LeadersVo;
import com.zssq.config.AuthConfig;
import com.zssq.constants.AuthConstants;
import com.zssq.dao.pojo.MyCount;
import com.zssq.dao.pojo.SysDeputyInfo;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysDeputyService;
import com.zssq.service.ISysUserService;
import com.zssq.service.IUserRelationService;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

@Controller  
@RequestMapping("/deputy")
public class DeputyController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ISysDeputyService sysDeputyService;
	
	@Autowired
	private ISysUserService sysUserService;
	
	/** 好友关系service */
	@Autowired
	private IUserRelationService userRelationService;
	
	/** 授权disconf */
	@Autowired
	private AuthConfig authConfig;
	
	/**
	 * @Function leaders
	 * @Description 门户代发领导
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/leaders")  
	public ResultJSON leaders(@RequireValid LeadersVo param) throws BusinessException {
		Message message = null;
		ResultJSON rj = new ResultJSON();
		try {
			SysDeputyInfo record =new SysDeputyInfo();
			if("RY".equals(param.getDeputyApp())){
				record.setDeputyAppCode(AuthConstants.DEPUTY_RY);
			}else if("WB".equals(param.getDeputyApp())){
				record.setDeputyAppCode(AuthConstants.DEPUTY_WB);
			}else if("BK".equals(param.getDeputyApp())){
				record.setDeputyAppCode(AuthConstants.DEPUTY_BK);
			}
			record.setDeputyUserCode(param.getUserCode());
			List<SysDeputyInfo> deputyInfos = sysDeputyService.selectByRecord(record);
			JSONObject body = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < deputyInfos.size(); i++) {
				JSONObject deputyInfo = new JSONObject();
				deputyInfo.put("userCode", StringTools.formatToString(deputyInfos.get(i).getDeputyLeaderCode()));
				SysUserInfo leader = sysUserService.selectByCode(deputyInfos.get(i).getDeputyLeaderCode());
				if(leader != null){
					deputyInfo.put("userName", StringTools.formatToString(leader.getUserName()));
					if(leader.getHeadPortrait() != null){
						deputyInfo.put("head", StringTools.formatToString(leader.getHeadPortrait()));
					}else{
						deputyInfo.put("head", StringTools.formatToString(authConfig.getUserHead()));
					}
					deputyInfo.put("intro", StringTools.formatToString(leader.getIntroduce()));
					SysOrgInfo orgInfo = leader.getOrgInfo();
					if(orgInfo != null){
						deputyInfo.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));
					}else{
						deputyInfo.put("orgName", "");
					}
					MyCount myCount = userRelationService.selectMyCount(deputyInfos.get(i).getDeputyLeaderCode());
					deputyInfo.put("concernsCount", StringTools.formatToString(myCount.getConcernsCount()));//关注数量
					deputyInfo.put("friendsCount", StringTools.formatToString(myCount.getFriendsCount()));//好友数量
					deputyInfo.put("fansCount", StringTools.formatToString(myCount.getFansCount()));//粉丝数量
				}else{
					deputyInfo.put("userName", "");
					deputyInfo.put("intro", "");
					deputyInfo.put("orgName", "");
					deputyInfo.put("concernsCount", 0);//关注数量
					deputyInfo.put("friendsCount",0);//好友数量
					deputyInfo.put("fansCount", 0);
				}
				jsonArray.add(deputyInfo);
			}
			body.put("dataList", jsonArray);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取代发详细信息"));
	        rj.setBody(body);
			return rj;
		} catch (Exception e) {
			logger.error("获取代发详细信息", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取代发详细信息"));
		}
	}
	
	
}
