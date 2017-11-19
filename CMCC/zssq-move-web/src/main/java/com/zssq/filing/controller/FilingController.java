package com.zssq.filing.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.filing.service.FilingService;
import com.zssq.pojo.ResultJSON;

@RequestMapping("/filingMigrate")
@Controller
public class FilingController {

	@Resource
	FilingService filingService;
	
	/**
	 * 
	 * @Title: kcFriendGroup  
	 * @Description: 查询源库kc_friend_group，获取好友分组列表
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/kcFriendGroup")
	@ResponseBody
	public ResultJSON kcFriendGroup(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		body.put("dataList", JSONArray.toJSONString(filingService.getKcFriendGroup()));
		rj.setBody(body);
		return rj;
	}
	
	/**
	 * 
	 * @Title: getKcFriendGrpUsrRel2  
	 * @Description: 查询源库kc_friend_grp_usr_rel，获取关注列表
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/usrRelWithConcerns")
	@ResponseBody
	public ResultJSON getKcFriendGrpUsrRelWithConcerns(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		body.put("dataList", JSONArray.toJSONString(filingService.getKcFriendGrpUsrRelWithConcerns()));
		rj.setBody(body);
		return rj;
	}
	
	/**
	 * 
	 * @Title: getKcFriendGrpUsrRel2  
	 * @Description: 查询源库kc_friend_grp_usr_rel，获取好友列表
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/usrRelWithFriend")
	@ResponseBody
	public ResultJSON getKcFriendGrpUsrRelWithFriend(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		body.put("dataList", JSONArray.toJSONString(filingService.getKcFriendGrpUsrRelWithFriend()));
		rj.setBody(body);
		return rj;
	}
	
	/**
	 * 
	 * @Title: userFriendGroup  
	 * @Description: 导入好友分组列表
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/userFriendGroup")
	@ResponseBody
	public ResultJSON userFriendGroup(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		rj.setBody(body);
		filingService.iinsertUserFriendGroup();
		return rj;
	}
	
	/**
	 * 
	 * @Title: userConcerns  
	 * @Description: 导入关注列表
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/userConcerns")
	@ResponseBody
	public ResultJSON userConcerns(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		rj.setBody(body);
		filingService.iinsertUserConcerns();
		return rj;
	}
	
	/**
	 * 
	 * @Title: userFriend  
	 * @Description: 导入好友列表
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/userFriend")
	@ResponseBody
	public ResultJSON userFriend(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		rj.setBody(body);
		filingService.iinsertUserFriend();
		return rj;
	}
	
}
