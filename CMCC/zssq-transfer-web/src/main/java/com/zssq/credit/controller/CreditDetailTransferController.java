package com.zssq.credit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zssq.credit.service.CreditDetailTransferService;

/**
 * 
 * @ClassName: CreditDetailTransferController  
 * @Description: 导入积分和金币明细
 * @author CaiZhaohui  
 * @date 2017年6月20日  
 *
 */
@Controller
@RequestMapping("/creditDetailTransfer")
public class CreditDetailTransferController {
	
	/** 导入积分、金币明细的服务 */
	@Autowired
	CreditDetailTransferService creditDetailTransferService;

	
	@RequestMapping("/integral")
	@ResponseBody
	public String integralDetail(){
		// 导入积分明细
		creditDetailTransferService.integralDetail();
		return "success";
	}
	
	@RequestMapping("/coin")
	@ResponseBody
	public String coinDetail(){
		// 导入金币明细
		creditDetailTransferService.coinDetail();
		return "success";
	}
	
}
