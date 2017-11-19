package com.zssq.credit.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.credit.service.CreditService;
import com.zssq.pojo.ResultJSON;

@RequestMapping("/creditMigrate")
@Controller
public class CreditController {

	@Resource
	CreditService creditService;
	
	/**
	 * 
	 * @Title: userStaList  
	 * @Description: 查询源库个人积分、金币账户列表
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/userStaList")
	@ResponseBody
	public ResultJSON userStaList(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		body.put("dataList", JSONArray.toJSONString(creditService.getGoldUserStaList()));
		rj.setBody(body);
		return rj;
	}
	
	/**
	 * 
	 * @Title: teamStaList  
	 * @Description: 查询源库班组积分、金币账户列表
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/teamStaList")
	@ResponseBody
	public ResultJSON teamStaList(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		body.put("dataList", JSONArray.toJSONString(creditService.getGoldTeamStaList()));
		rj.setBody(body);
		return rj;
	}
	
	/**
	 * 
	 * @Title: integralAccountList  
	 * @Description: 查询积分账户列表
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/integralAccountList")
	@ResponseBody
	public ResultJSON integralAccountList(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		body.put("dataList", JSONArray.toJSONString(creditService.getIntegralAccountList()));
		rj.setBody(body);
		return rj;
	}
	
	/**
	 * 
	 * @Title: coinAccountList  
	 * @Description: 查询金币账户列表
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/coinAccountList")
	@ResponseBody
	public ResultJSON coinAccountList(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		body.put("dataList", JSONArray.toJSONString(creditService.getCoinAccountList()));
		rj.setBody(body);
		return rj;
	}
	
	/**
	 * 
	 * @Title: insertIntegralAccountListWithUser  
	 * @Description: 导入个人积分账户
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/insertIntegralAccountListWithUser")
	@ResponseBody
	public ResultJSON insertIntegralAccountListWithUser(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		rj.setBody(body);
		creditService.iinsertIntegralWithUser();
		return rj;
	}
	
	/**
	 * 
	 * @Title: insertIntegralAccountListWithTeam  
	 * @Description: 导入班组积分账户
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/insertIntegralAccountListWithTeam")
	@ResponseBody
	public ResultJSON insertIntegralAccountListWithTeam(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		rj.setBody(body);
		creditService.iinsertIntegralWithTeam();
		return rj;
	}
	
	/**
	 * 
	 * @Title: insertCoinAccountListWithUser  
	 * @Description: 导入个人金币账户
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/insertCoinAccountListWithUser")
	@ResponseBody
	public ResultJSON insertCoinAccountListWithUser(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		rj.setBody(body);
		creditService.iinsertCoinWithUser();
		return rj;
	}
	
	/**
	 * 
	 * @Title: insertCoinAccountListWithTeam  
	 * @Description: 导入班组金币账户
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/insertCoinAccountListWithTeam")
	@ResponseBody
	public ResultJSON insertCoinAccountListWithTeam(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		rj.setBody(body);
		creditService.iinsertCoinWithTeam();
		return rj;
	}
	
	/**
	 * 
	 * @Title: insertIntegralAccountDetail  
	 * @Description: 从已导入历史数据的积分账户表中查询数据并导入到积分明细表中
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/insertIntegralAccountDetail")
	@ResponseBody
	public ResultJSON insertIntegralAccountDetail(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		rj.setBody(body);
		creditService.insertIntegralDetail();
		return rj;
	}
	
	/**
	 * 
	 * @Title: insertCoinAccountDetail  
	 * @Description: 从已导入历史数据的金币账户表中查询数据并导入到金币明细表中
	 * @param @return    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/insertCoinAccountDetail")
	@ResponseBody
	public ResultJSON insertCoinAccountDetail(){
		ResultJSON rj = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		rj.setBody(body);
		creditService.insertCoinDetail();
		return rj;
	}
	
	
	
}
