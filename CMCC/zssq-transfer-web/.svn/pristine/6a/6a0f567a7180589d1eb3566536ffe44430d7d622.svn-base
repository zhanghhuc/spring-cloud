package com.zssq.relation.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.pojo.ResultJSON;
import com.zssq.relation.service.IRelationThirdService;

/**
 * 
 * @ClassName: RelationBlogController  
 * @Description: 将博客信息迁移到关系内容信息中  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Controller
@RequestMapping("relationBlogController")
public class RelationBlogController {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IRelationThirdService relationThirdService;
	
	/**
	 * 
	 * @Title: transferBlogRelationData  
	 * @Description: 将博客信息迁移到关系内容信息中
	 * @return: void    返回类型
	 */
	@RequestMapping(value = "/transferRelationBlogData")  
	@ResponseBody
	public ResultJSON transferRelationBlogData() {
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		
		// 开始时间
		long beginTime = System.currentTimeMillis(); 
		log.info("开始时间：" + beginTime);
		
		try {
			// 导入原评论数据
			log.info("开始将博客信息迁移到关系内容信息中...");
			boolean insertFlag = insertRelationSubject();
			if (!insertFlag) {
				body.put("message", "将博客信息迁移到关系内容信息中时失败");
				throw new Exception();
			}
			
			// 结束时间
			long endTime = System.currentTimeMillis(); 
			log.info("结束时间：" + endTime);
			
			// 用时
			long useTime = endTime - beginTime;
			log.info("使用时间：" + useTime);
			
			// 返回值
			body.put("message", "将博客信息迁移到关系内容信息中成功，共用时:" + useTime);
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
	 * @Title: insertRelationSubject
	 * @Description: 将博客信息迁移到关系内容信息中
	 * @return: boolean    返回类型
	 */
	private boolean insertRelationSubject() {
		// 返回值
		boolean result = true;
		
		try {
			relationThirdService.insertRelationBlogData();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
}
