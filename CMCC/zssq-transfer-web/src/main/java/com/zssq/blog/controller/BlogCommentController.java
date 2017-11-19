package com.zssq.blog.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.blog.service.BlogCommentService;
import com.zssq.pojo.ResultJSON;

/**
 * 
 * @ClassName: BlogCommentController  
 * @Description: 评论数据迁移Controller  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Controller
@RequestMapping("blogCommentController")
public class BlogCommentController {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BlogCommentService blogCommentService;
	
	/**
	 * 
	 * @Title: transferCommentData  
	 * @Description: 迁移评论数据
	 * @return: void    返回类型
	 */
	@RequestMapping(value = "/transferCommentData")  
	@ResponseBody
	public ResultJSON transferCommentData() {
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		
		// 开始时间
		long beginTime = System.currentTimeMillis(); 
		log.info("开始时间：" + beginTime);
		
		try {
			// 删除索引
			log.info("开始删除索引...");
			boolean deleteIndexFlag = deleteIndex();
			if (!deleteIndexFlag) {
				body.put("message", "删除索引时失败");
				log.error("删除索引时失败");
				throw new Exception();
			}
			log.info("删除索引成功...");
			
			// 往评论表中添加字段
			log.info("开始在blog_comment表中添加old_id字段...");
			boolean addColumnFlag = addColumn();
			if (!addColumnFlag) {
				body.put("message", "添加old_id字段时失败");
				throw new Exception();
			}
			log.info("在blog_comment表中添加old_id字段成功");
			
			// 导入原评论数据
			log.info("开始导入原评论数据...");
			boolean insertFlag = insertSourceComment();
			if (!insertFlag) {
				body.put("message", "导入原评论数据时失败");
				throw new Exception();
			}
			log.info("导入原评论数据成功");
			
			// 创建索引
			log.info("开始创建索引...");
			boolean createIndexFlag = createIndex();
			if (!createIndexFlag) {
				body.put("message", "创建索引时失败");
				log.error("创建索引时失败");
				throw new Exception();
			}
			log.info("创建索引成功");
			
			// 结束时间
			long endTime = System.currentTimeMillis(); 
			log.info("结束时间：" + endTime);
			
			// 用时
			long useTime = endTime - beginTime;
			log.info("使用时间：" + useTime);
			
			// 返回值
			body.put("message", "导入评论成功，共用时:" + useTime);
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
	 * @Title: deleteIndex  
	 * @Description: 删除索引
	 * @return: boolean    返回类型
	 */
	private boolean deleteIndex() {
		// 返回值
		boolean result = true;
		
		try {
			// 删除索引
			blogCommentService.deleteIndex();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: addColumn  
	 * @Description: 往评论表中添加字段
	 * @return: boolean    返回类型
	 */
	private boolean addColumn() {
		// 返回值
		boolean result = true;
		
		try {
			// 往评论表中添加字段
			blogCommentService.addColumn();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: createIndex  
	 * @Description: 创建索引
	 * @return: boolean    返回类型
	 */
	private boolean createIndex() {
		// 返回值
		boolean result = true;
		
		try {
			// 创建索引
			blogCommentService.createIndex();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: insertSourceComment  
	 * @Description: 导入原评论数据
	 * @return: boolean    返回类型
	 */
	private boolean insertSourceComment() {
		// 返回值
		boolean result = true;
		
		try {
			// 导入原评论数据
			blogCommentService.insertSourceComment();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
}
