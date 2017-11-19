package com.zssq.auth.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zssq.auth.service.UserTransferServiceI;

@Controller
public class UserTransferController {

	/** 员工数据迁移业务组件 */
	@Resource
	private UserTransferServiceI userTransferService;
	
	/**
	 * 员工数据迁移
	 * 
	 * @return
	 */
	@RequestMapping("/executeTransferUser") 
	@ResponseBody	
	public String executeTransferUser() {
		
		userTransferService.executeTransferUser();
		return "success";
	}
}
