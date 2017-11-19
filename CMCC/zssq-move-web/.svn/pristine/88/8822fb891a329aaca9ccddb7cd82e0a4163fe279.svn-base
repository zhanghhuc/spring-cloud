package com.zssq.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName: BlogMainController  
 * @Description: 主流程Controller  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Controller
@RequestMapping("blogMainController")
public class BlogMainController {
	
	/**
	 * 
	 * @Title: transferData  
	 * @Description: 处理数据
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transferData")  
	public void transferData() {
		try {
			// 导入订阅数据
			BlogSubscribeController blogSubscribeController = new BlogSubscribeController();
			blogSubscribeController.transferSubDataMain(0, 1000);
			
			// 导入分类数据
			BlogClassController blogClassController = new BlogClassController();
			blogClassController.transferClassDataMain();
			
			// 导入博客数据
			BlogController blogController = new BlogController();
			blogController.transferBlogDataMain(0, 1000);
			
			// 导入草稿数据
			BlogDraftController blogDraftController = new BlogDraftController();
			blogDraftController.transferDraftDataMain(0, 1000);
			
			// 导入评论数据
			BlogCommentController blogCommentController = new BlogCommentController();
			blogCommentController.transferCommentDataMain(0, 1000);
			
			// 导入评论回复数据
			BlogReplyController blogReplyController = new BlogReplyController();
			blogReplyController.transferReplyDataMain(0, 1000);
			
			// 数据量修改
			BlogDataController blogDataController = new BlogDataController();
			blogDataController.updateBlogDataMain();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
