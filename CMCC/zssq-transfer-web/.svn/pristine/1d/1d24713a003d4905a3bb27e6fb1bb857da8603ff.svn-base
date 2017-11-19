package com.zssq.forum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.forum.service.ITransferForumService;

@Controller
@RequestMapping("/forum")
public class TransferForumController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ITransferForumService transferForumService;
	
	@RequestMapping("plate")
	@ResponseBody
	public JSONObject transForumPlate(HttpServletRequest req, HttpServletResponse res){
		JSONObject json = new JSONObject();
		try {
			int startNum = 0;
			int endNum = 3500;
			transferForumService.transfer(false, startNum, endNum);
			json.put("status", "success");
			json.put("msg", "所有班组论坛数据迁移成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("迁移失败", e);
			json.put("status", "fail");
			json.put("msg", "班组论坛数据迁移出错：" + e);
		}
		return json;
		
	}
	
	

}
