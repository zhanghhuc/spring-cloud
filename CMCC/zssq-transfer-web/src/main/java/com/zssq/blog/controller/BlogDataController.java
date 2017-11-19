package com.zssq.blog.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.blog.service.BlogDataService;
import com.zssq.pojo.ResultJSON;

/**
 * 
 * @ClassName: BlogDataController  
 * @Description: 数据迁移过程数量修改Controller  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Controller
@RequestMapping("blogDataController")
public class BlogDataController {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BlogDataService blogDataService;
	
	/**
	 * 
	 * @Title: transferBlogData  
	 * @Description: 修改数据量
	 * @return: void    返回类型
	 */
	@RequestMapping(value = "/transferBlogData")  
	@ResponseBody
	public ResultJSON transferBlogData() {
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		
		// 开始时间
		long beginTime = System.currentTimeMillis(); 
		log.info("开始时间：" + beginTime);
		
		try {
			// 修改数据量
			log.info("开始修改数据量...");
			boolean updateFlag = updateData();
			if (!updateFlag) {
				body.put("message", "修改数据量时失败");
				throw new Exception();
			}
			
			// 结束时间
			long endTime = System.currentTimeMillis(); 
			log.info("结束时间：" + endTime);
			
			// 用时
			long useTime = endTime - beginTime;
			log.info("使用时间：" + useTime);
			
			// 返回值
			body.put("message", "数据量修改成功，共用时:" + useTime);
			result.setBody(body);
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			result.setBody(body);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: updateData  
	 * @Description: 修改数据量
	 * @return: boolean    返回类型
	 */
	private boolean updateData() {
		// 返回值
		boolean result = true;
		
		try {
			// 修改数据量
			blogDataService.updateData();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
}
