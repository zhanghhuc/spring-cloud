package com.zssq.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.blog.service.BlogCommentService;
import com.zssq.blog.service.BlogReplyService;
import com.zssq.blog.service.BlogService;
import com.zssq.blog.service.BlogThirdService;
import com.zssq.blog.vo.DB2BlogReply;
import com.zssq.blog.vo.MySQLBlogReply;
import com.zssq.blog.vo.UserInfo;
import com.zssq.constants.BlogConstants;
import com.zssq.pojo.ResultJSON;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.EmojiConvertUtil;
import com.zssq.utils.StringTools;

/**
 * 
 * @ClassName: BlogReplyController  
 * @Description: 评论回复数据迁移Controller  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Controller
@RequestMapping("blogReplyController")
public class BlogReplyController {

	@Autowired
	private BlogReplyService blogReplyService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogCommentService blogCommentService;
	@Autowired
	private BlogThirdService blogThirdService;
	
	/**
	 * 
	 * @Title: transferReplyData  
	 * @Description: 迁移评论回复数据
	 * @param pageNo
	 * @param pageSize    参数  
	 * @return: void    返回类型
	 */
	@RequestMapping(value = "/transferReplyData")  
	@ResponseBody
	public ResultJSON transferReplyData(int pageNo, int pageSize) {
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		
		try {
			// 参数校验
			if (pageNo <= 0) {
				pageNo = 0;
			}
			if (pageSize <= 0) {
				pageSize = 100;
			}
			
			// 查询DB2库中评论回复数据总量
//			int DB2ReplyCount = blogReplyService.getDB2ReplyCount();
			int DB2ReplyCount = blogReplyService.getSourceMySQLReplyCount();
			if (DB2ReplyCount <= 0) {
				body.put("message", "查询源数据库中评论回复数据总量时出错，查询出的个数为：" + DB2ReplyCount);
				throw new Exception();
			}
			
			// 总页数
			int totalPage = DB2ReplyCount/pageSize + 1;
			
			// 循环处理数据
			boolean arrangeFlag = true;
			for (int i = pageNo; i < totalPage; i++) {
				arrangeFlag = arrangeReplyData(pageNo, pageSize);
				if (!arrangeFlag) {
					body.put("message", "处理数据时失败");
					throw new Exception();
				}
				pageNo++;
			}
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			body.put("pageNo", pageNo);
			result.setBody(body);
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void transferReplyDataMain(int pageNo, int pageSize) throws Exception {
		// 参数校验
		if (pageNo <= 0) {
			pageNo = 0;
		}
		if (pageSize <= 0) {
			pageSize = 100;
		}
		
		// 查询DB2库中评论回复数据总量
//		int DB2ReplyCount = blogReplyService.getDB2ReplyCount();
		int DB2ReplyCount = blogReplyService.getSourceMySQLReplyCount();
		if (DB2ReplyCount <= 0) {
			System.err.println(this.getClass() + "查询源数据库中评论回复数据总量时出错，查询出的个数为：" + DB2ReplyCount);
			throw new Exception();
		}
		
		// 总页数
		int totalPage = DB2ReplyCount/pageSize + 1;
		System.out.println(this.getClass() + "评论回复数据共" + totalPage + "页");
		
		// 循环处理数据
		boolean arrangeFlag = true;
		for (int i = pageNo; i < totalPage; i++) {
			arrangeFlag = arrangeReplyData(pageNo, pageSize);
			if (!arrangeFlag) {
				System.err.println(this.getClass() + "处理数据时失败");
				throw new Exception();
			}
			System.out.println(this.getClass() + "评论回复数据第" + pageNo + "页处理成功");
			pageNo++;
		}
	}
	
	/**
	 * 
	 * @Title: arrangeReplyData  
	 * @Description: 整理评论回复数据
	 * @param pageStart
	 * @param pageSize    参数  
	 * @return: boolean    返回类型
	 */
	private boolean arrangeReplyData(int pageNo, int pageSize) {
		// 返回值
		boolean result = true;
		
		try {
			// 获取DB2库中的评论回复数据
//			List<DB2BlogReply> DB2ReplyList = blogReplyService.getDB2ReplyList(pageNo, pageSize);
			List<DB2BlogReply> DB2ReplyList = blogReplyService.getSourceMySQLReplyList(pageNo, pageSize);
			
			// 校验返回值
			if (DB2ReplyList == null || DB2ReplyList.isEmpty()) {
				return false;
			}
			
			// 将DB2中的数据整理为MySQL中的数据
			List<MySQLBlogReply> mySQLReplyList = new ArrayList<MySQLBlogReply>();
			for (DB2BlogReply db2BlogReply : DB2ReplyList) {
				MySQLBlogReply mySQLBlogReply = new MySQLBlogReply();
				mySQLBlogReply.setReplyCode(UUIDHelper.getUUID());
				
				// 获取回复人信息
				UserInfo userInfo = blogThirdService.getUserInfo(db2BlogReply.getUserId());
				mySQLBlogReply.setTenantCode(userInfo == null ? "" : StringTools.formatToString(userInfo.getTenantCode()));
				mySQLBlogReply.setOrgCode(userInfo == null ? "" : StringTools.formatToString(userInfo.getOrgCode()));
				mySQLBlogReply.setUserCode(userInfo == null ? "" : StringTools.formatToString(userInfo.getUserCode()));
				mySQLBlogReply.setCreateTime(db2BlogReply.getCreateTime() == null ? 0L : db2BlogReply.getCreateTime().getTime());
				mySQLBlogReply.setModifyTime(db2BlogReply.getUpdateTime() == null ? 0L : db2BlogReply.getUpdateTime().getTime());
				
				// 获取博客编号
				if (db2BlogReply.getBlogId() != null) {
					String blogCode = blogService.getBlogCode(db2BlogReply.getBlogId());
					mySQLBlogReply.setBlogCode(StringTools.formatToString(blogCode));
				} else {
					mySQLBlogReply.setBlogCode("");
				}
				
				// 获取评论编号
				if (db2BlogReply.getParentsId() != null) {
					String commentCode = blogCommentService.getCommentCode(db2BlogReply.getParentsId());
					mySQLBlogReply.setCommentCode(StringTools.formatToString(commentCode));
				} else {
					mySQLBlogReply.setCommentCode("");
				}
				
				if (db2BlogReply.getFlagReply() != null && db2BlogReply.getFlagReply() == 2) {
					// 获取被回复人信息
					String userCode = blogThirdService.getUserCode(db2BlogReply.getToReplyUserId());
					mySQLBlogReply.setToReplyUserCode(StringTools.formatToString(userCode));
				}
				mySQLBlogReply.setReplyContent(EmojiConvertUtil.emojiConvert(StringTools.formatToString(db2BlogReply.getReplyContent())));
				mySQLBlogReply.setReplyIsDelete(BlogConstants.BLOG_NO);
				mySQLBlogReply.setReplytIsShield(BlogConstants.BLOG_NO);
				mySQLBlogReply.setReplyLikeNum(0);
				mySQLReplyList.add(mySQLBlogReply);
			}
			
			// 插入评论回复数据到MySQL库中
			boolean insertFlag = blogReplyService.insertMySQLReplyList(mySQLReplyList);
			if (!insertFlag) {
				result = false;
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
}
