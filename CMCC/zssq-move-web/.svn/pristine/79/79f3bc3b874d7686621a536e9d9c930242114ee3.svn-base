package com.zssq.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.blog.service.BlogClassService;
import com.zssq.blog.service.BlogDraftService;
import com.zssq.blog.service.BlogThirdService;
import com.zssq.blog.vo.DB2Blog;
import com.zssq.blog.vo.MySQLBlogDraft;
import com.zssq.blog.vo.MySQLBlogDraftContent;
import com.zssq.blog.vo.TeamInfo;
import com.zssq.blog.vo.UserInfo;
import com.zssq.constants.BlogConstants;
import com.zssq.pojo.ResultJSON;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.EmojiConvertUtil;
import com.zssq.utils.StringTools;

/**
 * 
 * @ClassName: BlogDraftController  
 * @Description: 博客草稿数据迁移Controller  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Controller
@RequestMapping("blogDraftController")
public class BlogDraftController {

	@Autowired
	private BlogDraftService blogDraftService;
	@Autowired
	private BlogClassService blogClassService;
	@Autowired
	private BlogThirdService blogThirdService;
	
	/**
	 * 
	 * @Title: transferDraftData  
	 * @Description: 迁移博客数据
	 * @param pageNo
	 * @param pageSize    参数  
	 * @return: void    返回类型
	 */
	@RequestMapping(value = "/transferDraftData")  
	@ResponseBody
	public ResultJSON transferDraftData(int pageNo, int pageSize) {
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
			
			// 查询DB2库中草稿数据总量
//			int DB2DraftCount = blogDraftService.getDB2DraftCount();
			int DB2DraftCount = blogDraftService.getSourceMySQLDraftCount();
			if (DB2DraftCount <= 0) {
				body.put("message", "查询源数据库中草稿数据总量时出错，查询出的个数为：" + DB2DraftCount);
				throw new Exception();
			}
			
			// 总页数
			int totalPage = DB2DraftCount/pageSize + 1;
			
			// 循环处理数据
			boolean arrangeFlag = true;
			for (int i = pageNo; i < totalPage; i++) {
				arrangeFlag = arrangeDraftData(pageNo, pageSize);
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
	
	public void transferDraftDataMain(int pageNo, int pageSize) throws Exception {
		// 参数校验
		if (pageNo <= 0) {
			pageNo = 0;
		}
		if (pageSize <= 0) {
			pageSize = 100;
		}
		
		// 查询DB2库中草稿数据总量
//		int DB2DraftCount = blogDraftService.getDB2DraftCount();
		int DB2DraftCount = blogDraftService.getSourceMySQLDraftCount();
		if (DB2DraftCount <= 0) {
			System.err.println(this.getClass() + "查询源数据库中草稿数据总量时出错，查询出的个数为：" + DB2DraftCount);
			throw new Exception();
		}
		
		// 总页数
		int totalPage = DB2DraftCount/pageSize + 1;
		System.out.println(this.getClass() + "草稿数据共" + totalPage + "页");
		
		// 循环处理数据
		boolean arrangeFlag = true;
		for (int i = pageNo; i < totalPage; i++) {
			arrangeFlag = arrangeDraftData(pageNo, pageSize);
			if (!arrangeFlag) {
				System.err.println(this.getClass() + "处理数据时失败");
				throw new Exception();
			}
			System.out.println(this.getClass() + "草稿数据第" + pageNo + "页处理成功");
			pageNo++;
		}
	}
	
	/**
	 * 
	 * @Title: arrangeDraftData  
	 * @Description: 整理草稿数据
	 * @param pageStart
	 * @param pageSize    参数  
	 * @return: boolean    返回类型
	 */
	private boolean arrangeDraftData(int pageNo, int pageSize) {
		// 返回值
		boolean result = true;
		
		try {
			// 获取DB2库中的草稿数据
//			List<DB2Blog> DB2BlogList = blogDraftService.getDB2Draftist(pageNo, pageSize);
			List<DB2Blog> DB2BlogList = blogDraftService.getSourceMySQLDraftist(pageNo, pageSize);
			
			// 校验返回值
			if (DB2BlogList == null || DB2BlogList.isEmpty()) {
				return false;
			}
			
			// 将DB2中的数据整理为MySQL中的数据
			List<MySQLBlogDraft> mySQLDraftList = new ArrayList<MySQLBlogDraft>(); // 草稿
			List<MySQLBlogDraftContent> mySQLDraftContentList = new ArrayList<MySQLBlogDraftContent>(); // 草稿内容
			for (DB2Blog db2Blog : DB2BlogList) {
				// 共用
				String draftCode = UUIDHelper.getUUID(); // 草稿编号
				String tenantCode = null; // 租户编号
				String orgCode = null; // 组织编号
				String teamCode = null; // 班组编号
				String userCode = null; // 人员编号
				
				// 获取共用信息
				if (db2Blog.getTeamId() != null && db2Blog.getTeamId() != 0) {
					TeamInfo teamInfo = blogThirdService.getTeamInfo(db2Blog.getTeamId());
					tenantCode = teamInfo == null ? "" : teamInfo.getTenantCode();
					orgCode = teamInfo == null ? "" : teamInfo.getOrgCode();
					teamCode = teamInfo == null ? "" : teamInfo.getTeamCode();
				} else {
					UserInfo userInfo = blogThirdService.getUserInfo(db2Blog.getUserId());
					tenantCode = userInfo == null ? "" : userInfo.getTenantCode();
					orgCode = userInfo == null ? "" : userInfo.getOrgCode();
					userCode = userInfo == null ? "" : userInfo.getUserCode();
				}
				
				// 草稿
				MySQLBlogDraft mySQLBlogDraft = new MySQLBlogDraft();
				mySQLBlogDraft.setDraftCode(draftCode);
				mySQLBlogDraft.setTenantCode(StringTools.formatToString(tenantCode));
				mySQLBlogDraft.setOrgCode(StringTools.formatToString(orgCode));
				mySQLBlogDraft.setCreateTime(db2Blog.getCreateTime() == null ? 0L : db2Blog.getCreateTime().getTime());
				mySQLBlogDraft.setModifyTime(db2Blog.getUpdatetime() == null ? 0L : db2Blog.getUpdatetime().getTime());
				
				// 获取用户code
				if (userCode == null) {
					userCode = blogThirdService.getUserCode(db2Blog.getUserId());
				}
				mySQLBlogDraft.setUserCode(StringTools.formatToString(userCode));
				
				// 从属关系
				if (db2Blog.getTeamId() != null && db2Blog.getTeamId() != 0) {
					mySQLBlogDraft.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_TEAM);
					mySQLBlogDraft.setTeamCode(StringTools.formatToString(teamCode));
				} else {
					mySQLBlogDraft.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_USER);
				}
				
				mySQLBlogDraft.setDraftDigest(StringTools.formatToString(db2Blog.getDigest()));
				mySQLBlogDraft.setDraftTitle(StringTools.formatToString(db2Blog.getTitle()));
				
				// 获取分类编号
				if (db2Blog.getCataLevelTwoId() != null) {
					String classCode = blogClassService.getClassCode(mySQLBlogDraft.getDraftDepend(), mySQLBlogDraft.getUserCode(), mySQLBlogDraft.getTeamCode(), db2Blog.getCataLevelTwoId());
					mySQLBlogDraft.setClassCode(StringTools.formatToString(classCode));
				} else {
					mySQLBlogDraft.setClassCode("");
				}
				
				mySQLBlogDraft.setDraftPlanPublishTime(0L);
				mySQLDraftList.add(mySQLBlogDraft);
				
				// 草稿内容
				MySQLBlogDraftContent mySQLBlogDraftContent = new MySQLBlogDraftContent();
				mySQLBlogDraftContent.setDraftContentCode(UUIDHelper.getUUID());
				mySQLBlogDraftContent.setTenantCode(StringTools.formatToString(tenantCode));
				mySQLBlogDraftContent.setOrgCode(StringTools.formatToString(orgCode));
				mySQLBlogDraftContent.setCreateTime(db2Blog.getCreateTime() == null ? 0L : db2Blog.getCreateTime().getTime());
				mySQLBlogDraftContent.setModifyTime(db2Blog.getUpdatetime() == null ? 0L : db2Blog.getUpdatetime().getTime());
				mySQLBlogDraftContent.setDraftCode(draftCode);
				mySQLBlogDraftContent.setDraftContentInfo(EmojiConvertUtil.emojiConvert(StringTools.formatToString(db2Blog.getContent())));
				mySQLDraftContentList.add(mySQLBlogDraftContent);
			}
			
			// 插入草稿数据到MySQL库中
			boolean insertFlag = blogDraftService.insertMySQLDraftList(mySQLDraftList, mySQLDraftContentList);
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
