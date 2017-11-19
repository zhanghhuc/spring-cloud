package com.zssq.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zssq.auth.service.OrgTransferServiceI;

@Controller  
@RequestMapping("/orgTransfer")
public class OrgTransferController {

	@Autowired
	private OrgTransferServiceI orgTransferService;
	
	@RequestMapping("/transferBase")
	@ResponseBody
	public String transferBase() {
		
		orgTransferService.transferBase();
		return "success";
	}
	
	@RequestMapping("/processPraentCodeAndOrgType")
	@ResponseBody
	public String processPraentCodeAndOrgType() {
		
		orgTransferService.processPraentCodeAndOrgType();
		return "success";
	}
	
	@RequestMapping("/processManOrgCode")
	@ResponseBody
	public String processManOrgCode() {
		
		orgTransferService.processManOrgCode();
		return "success";
	}		
}
