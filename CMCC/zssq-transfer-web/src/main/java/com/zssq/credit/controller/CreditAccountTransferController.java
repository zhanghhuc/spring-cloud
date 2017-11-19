package com.zssq.credit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zssq.credit.service.CreditTeamTransferService;
import com.zssq.credit.service.CreditUserTransferService;

/**
 * 
 * @ClassName: CreditAccountTransferController  
 * @Description: 导入个人和班组的积分、金币账户  
 * @author CaiZhaohui  
 * @date 2017年6月20日  
 *
 */
@Controller
@RequestMapping("/creditAccountTransfer")
public class CreditAccountTransferController {
	
	/** 导入个人积分、金币账户的服务 */
	@Autowired
	CreditUserTransferService creditUserTransferService;
	
	/** 导入班组积分、金币账户的服务 */
	@Autowired
	CreditTeamTransferService creditTeamTransferService;
	
	/**
	 * 
	 * @Title: accountForUser  
	 * @Description: 导入个人的积分、金币账户
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	@RequestMapping("/user")
	@ResponseBody
	public String accountForUser(){
		creditUserTransferService.userAccount();
		return "success";
	}
	
	/**
	 * 
	 * @Title: accountForTeam  
	 * @Description: 导入班组的积分、金币账户
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	@RequestMapping("/team")
	@ResponseBody
	public String accountForTeam(){
		creditTeamTransferService.teamAccount();
		return "success";
	}
	
}
