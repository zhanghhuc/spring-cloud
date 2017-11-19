package com.zssq.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zssq.auth.service.UserTransferServiceI;

@Controller  
@RequestMapping("/userTransfer")
public class UserTransferController {

	@Autowired
	private UserTransferServiceI userTransferService;
	
	@RequestMapping("/executeTransfer")
	@ResponseBody
	public String executeTransfer() {
		
		userTransferService.executeTransfer();
		return "success";
	}	
}
