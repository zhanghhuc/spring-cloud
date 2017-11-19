package com.zssq.team.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.pojo.ResultJSON;
import com.zssq.team.service.TeamService;

@Controller
@RequestMapping(value = "/team")  
public class TeamController {
	
	@Resource
	private TeamService teamService;
	
	/**
	 * @Function insert
	 * @Description 班组信息
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insert/info")  
	@ResponseBody
    public ResultJSON insert(HttpServletRequest req,HttpServletResponse res){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		long start = new Date().getTime();
		teamService.teamInfoInsert();
		long end = new Date().getTime();
		System.out.println("x=============================>"+(end - start));
		result.setBody(body);
		return result;
	}
	
	
	/**
	 * @Function insertMember
	 * @Description 班组成员
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insert/member")  
	@ResponseBody
    public ResultJSON insertMember(HttpServletRequest req,HttpServletResponse res){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		long start = new Date().getTime();
		teamService.teamMemberInsert();
		long end = new Date().getTime();
		System.out.println("x=============================>"+(end - start));
		result.setBody(body);
		return result;
	}
	
	/**
	 * @Function insertHonor
	 * @Description 百强班组
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insert/honor")  
	@ResponseBody
    public ResultJSON insertHonor(HttpServletRequest req,HttpServletResponse res){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		long start = new Date().getTime();
		teamService.honorTeamInsert();
		long end = new Date().getTime();
		System.out.println("x=============================>"+(end - start));
		result.setBody(body);
		return result;
	}
	
	
}
