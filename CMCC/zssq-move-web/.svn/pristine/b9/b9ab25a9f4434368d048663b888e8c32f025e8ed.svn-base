package com.zssq.blog.controller;

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
 * @Description: 迁移之后的数据整理Controller  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Controller
@RequestMapping("blogDataController")
public class BlogDataController {

	@Autowired
	private BlogDataService blogDataService;
	
	/**
	 * 
	 * @Title: updateBlogData  
	 * @Description: 处理数据
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "/updateBlogData")  
	@ResponseBody
	public ResultJSON updateBlogData(int pageSize) {
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		
		try {
			// 参数校验
			if (pageSize <= 0) {
				pageSize = 100;
			}
			
			// 查询分类表中id的最大值
			int maxBlogClassId = blogDataService.getMySQLBlogClassMaxId();
			// 总页数
			int blogClassTotalPage = maxBlogClassId/pageSize + 1;
			// 循环处理数据
			for (int i = 0; i < blogClassTotalPage; i++) {
				// 更新分类下博客数
				blogDataService.updateClassBlogNum(i, pageSize);
			}
			
			// 查询博客数据表中id的最大值
			int maxBlogDataId = blogDataService.getMySQLBlogDataMaxId();
			// 总页数
			int blogDataTotalPage = maxBlogDataId/pageSize + 1;
			// 循环处理数据
			for (int i = 0; i < blogDataTotalPage; i++) {
				// 更新分类下博客数
				blogDataService.updateBlogCommentNum(i, pageSize);
			}
			
			// 查询评论表中id的最大值
			int maxBlogCommentId = blogDataService.getMySQLBlogCommentMaxId();
			// 总页数
			int blogCommentTotalPage = maxBlogCommentId/pageSize + 1;
			// 循环处理数据
			for (int i = 0; i < blogCommentTotalPage; i++) {
				// 更新评论下回复数
				blogDataService.updateCommentReplyNum(i, pageSize);
			}
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			result.setBody(body);
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public void updateBlogDataMain() {
		// 更新分类下博客数
//		blogDataService.updateClassBlogNum();
		
		// 更新博客评论数
//		blogDataService.updateBlogCommentNum();
		
		// 更新评论回复数
//		blogDataService.updateCommentReplyNum();
	}
	
}
