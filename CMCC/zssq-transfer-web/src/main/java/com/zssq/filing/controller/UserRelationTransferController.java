package com.zssq.filing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zssq.filing.service.UserRelationService;

@Controller
@RequestMapping("/userRelationTransfer")
public class UserRelationTransferController {
	
	@Autowired
	private UserRelationService userRelationService; 
	
	/**
	 * 
	 * @Function transferUserFriendGroup
	 * @Description 迁移好友分组
	 * @return
	 */
	@RequestMapping("/transferUserFriendGroup")
	@ResponseBody
	public String transferUserFriendGroup(){
		
		boolean isSuccess = userRelationService.transferUserFriendGroup();
		if(isSuccess){
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 
	 * @Function transferUserFriends
	 * @Description 迁移用户好友
	 * @return
	 */
	@RequestMapping("/transferUserFriends")
	@ResponseBody
	public String transferUserFriends(){
		boolean isSuccess = userRelationService.transferUserFriends();
		if(isSuccess){
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 
	 * @Function transferUserConcerns
	 * @Description 迁移用户关注
	 * @return
	 */
	@RequestMapping("/transferUserConcerns")
	@ResponseBody
	public String transferUserConcerns(){
		boolean isSuccess = userRelationService.transferUserConcerns();
		if(isSuccess){
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 
	 * @Function transferSenstiveWord
	 * @Description 迁移敏感词
	 * @return
	 */
	@RequestMapping("/transferSenstiveWord")
	@ResponseBody
	public String transferSenstiveWord(){
		boolean isSuccess = userRelationService.transferSenstiveWord();
		if(isSuccess){
			return "success";
		}
		return "fail";
	}
}
