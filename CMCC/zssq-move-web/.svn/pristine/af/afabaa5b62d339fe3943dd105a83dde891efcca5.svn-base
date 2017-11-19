package com.zssq.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.blog.service.BlogClassService;
import com.zssq.blog.service.BlogThirdService;
import com.zssq.blog.vo.DB2BlogCatalog;
import com.zssq.blog.vo.MySQLBlogClass;
import com.zssq.blog.vo.TeamInfo;
import com.zssq.blog.vo.UserInfo;
import com.zssq.constants.BlogConstants;
import com.zssq.pojo.ResultJSON;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.StringTools;

/**
 * 
 * @ClassName: BlogClassController  
 * @Description: 博客分类数据迁移Controller  
 * @author ZKZ  
 * @date 2017年5月19日  
 *
 */
@Controller
@RequestMapping("blogClassController")
public class BlogClassController {
	
	@Autowired
	private BlogClassService blogClassService;
	@Autowired
	private BlogThirdService blogThirdService;
	
	@RequestMapping(value = "/transferClassData")  
	@ResponseBody
	public ResultJSON transferClassData() {
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		
		try {
			// 创建临时表
			boolean createFlag = createTempTable();
			if (!createFlag) {
				body.put("message", "新建临时表失败");
				throw new Exception();
			}
			
			// 处理数据
			boolean arrangeFlag = arrangeClassData();
			if (!arrangeFlag) {
				body.put("message", "数据是处理失败");
				throw new Exception();
			}
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			result.setBody(body);
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void transferClassDataMain() throws Exception {
		// 创建临时表
		boolean createFlag = createTempTable();
		if (!createFlag) {
			System.err.println(this.getClass() + "新建临时表失败");
			throw new Exception();
		}
		
		// 处理数据
		boolean arrangeFlag = arrangeClassData();
		if (!arrangeFlag) {
			System.err.println(this.getClass() + "数据是处理失败");
			throw new Exception();
		}
	}
	
	/**
	 * 
	 * @Title: createTempTable  
	 * @Description: 创建临时表
	 * @return: boolean    返回类型
	 */
	private boolean createTempTable() {
		// 返回值
		boolean result = true;
		
		try {
			// 新建临时表
			blogClassService.createMySQLBlogClassTemp();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: arrangeClassData  
	 * @Description: 处理数据
	 * @return: boolean    返回类型
	 */
	private boolean arrangeClassData() {
		// 返回值
		boolean result = true;
		
		try {
			// 查询DB2库中的博客分类数据
//			List<DB2BlogCatalog> DB2ClassList = blogClassService.getDB2ClassList();
			List<DB2BlogCatalog> DB2ClassList = blogClassService.getSourceMySQLClassList();
			
			// 返回值校验
			if (DB2ClassList == null || DB2ClassList.isEmpty()) {
				return false;
			}
			
			// 共用信息
			long time = new Date().getTime();
			
			// 查询所有人员
			List<UserInfo> userInfoList = blogThirdService.getUserInfoList();
			if (userInfoList == null) {
				return false;
			}
			
			// 查询所有班组
			List<TeamInfo> teamInfoList = blogThirdService.getTeamInfoList();
			if (teamInfoList == null) {
				return false;
			}
			
			// 循环所有人员，新建分类
			boolean insertUserClassFlag = true;
			for (int i = 0; i < userInfoList.size(); i++) {
				UserInfo userInfo = userInfoList.get(i);
				List<MySQLBlogClass> mySQLClassList = new ArrayList<MySQLBlogClass>();
				// 默认分类
				MySQLBlogClass defaultMySQLBlogClass = new MySQLBlogClass();
				defaultMySQLBlogClass.setClassCode(UUIDHelper.getUUID());
				defaultMySQLBlogClass.setTenantCode(StringTools.formatToString(userInfo.getTenantCode()));
				defaultMySQLBlogClass.setOrgCode(StringTools.formatToString(userInfo.getOrgCode()));
				defaultMySQLBlogClass.setCreateTime(time);
				defaultMySQLBlogClass.setModifyTime(time);
				defaultMySQLBlogClass.setRemark(BlogConstants.BLOG_CLASS_DEFAULT_FLAG);
				defaultMySQLBlogClass.setClassName(BlogConstants.BLOG_CLASS_DEFAULT);
				defaultMySQLBlogClass.setUserCode(StringTools.formatToString(userInfo.getUserCode()));
				defaultMySQLBlogClass.setClassDepend(BlogConstants.BLOG_CLASS_DEPEND_USER);
				defaultMySQLBlogClass.setClassBlogNum(0);
				mySQLClassList.add(defaultMySQLBlogClass);
				
				// 原有分类
				for (DB2BlogCatalog db2BlogCatalog : DB2ClassList) {
					MySQLBlogClass mySQLBlogClass = new MySQLBlogClass();
					mySQLBlogClass.setClassCode(UUIDHelper.getUUID());
					mySQLBlogClass.setTenantCode(StringTools.formatToString(userInfo.getTenantCode()));
					mySQLBlogClass.setOrgCode(StringTools.formatToString(userInfo.getOrgCode()));
					mySQLBlogClass.setCreateTime(db2BlogCatalog.getCreateTime().getTime());
					mySQLBlogClass.setModifyTime(db2BlogCatalog.getCreateTime().getTime());
					mySQLBlogClass.setClassName(db2BlogCatalog.getParentsCataName() + " - " + db2BlogCatalog.getCataName());
					mySQLBlogClass.setUserCode(StringTools.formatToString(userInfo.getUserCode()));
					mySQLBlogClass.setClassDepend(BlogConstants.BLOG_CLASS_DEPEND_USER);
					mySQLBlogClass.setClassBlogNum(0);
					mySQLBlogClass.setOldId(db2BlogCatalog.getCataId());
					mySQLClassList.add(mySQLBlogClass);
				}
				
				// 插入
				insertUserClassFlag = blogClassService.insertMySQLClassList(mySQLClassList);
				if (!insertUserClassFlag) {
					return false;
				}
			}
			
			// 循环所有班组，新建分类
			boolean insertTeamClassFlag = true;
			for (int i = 0; i < teamInfoList.size(); i++) {
				TeamInfo teamInfo = teamInfoList.get(i);
				List<MySQLBlogClass> mySQLClassList = new ArrayList<MySQLBlogClass>();
				// 默认分类
				MySQLBlogClass defaultMySQLBlogClass = new MySQLBlogClass();
				defaultMySQLBlogClass.setClassCode(UUIDHelper.getUUID());
				defaultMySQLBlogClass.setTenantCode(StringTools.formatToString(teamInfo.getTenantCode()));
				defaultMySQLBlogClass.setOrgCode(StringTools.formatToString(teamInfo.getOrgCode()));
				defaultMySQLBlogClass.setCreateTime(time);
				defaultMySQLBlogClass.setModifyTime(time);
				defaultMySQLBlogClass.setRemark(BlogConstants.BLOG_CLASS_DEFAULT_FLAG);
				defaultMySQLBlogClass.setClassName(BlogConstants.BLOG_CLASS_DEFAULT);
				defaultMySQLBlogClass.setClassDepend(BlogConstants.BLOG_CLASS_DEPEND_TEAM);
				defaultMySQLBlogClass.setTeamCode(StringTools.formatToString(teamInfo.getTeamCode()));
				defaultMySQLBlogClass.setClassBlogNum(0);
				mySQLClassList.add(defaultMySQLBlogClass);
				
				// 原有分类
				for (DB2BlogCatalog db2BlogCatalog : DB2ClassList) {
					MySQLBlogClass mySQLBlogClass = new MySQLBlogClass();
					mySQLBlogClass.setClassCode(UUIDHelper.getUUID());
					mySQLBlogClass.setTenantCode(StringTools.formatToString(teamInfo.getTenantCode()));
					mySQLBlogClass.setOrgCode(StringTools.formatToString(teamInfo.getOrgCode()));
					mySQLBlogClass.setCreateTime(db2BlogCatalog.getCreateTime().getTime());
					mySQLBlogClass.setModifyTime(db2BlogCatalog.getCreateTime().getTime());
					mySQLBlogClass.setClassName(db2BlogCatalog.getParentsCataName() + " - " + db2BlogCatalog.getCataName());
					mySQLBlogClass.setClassDepend(BlogConstants.BLOG_CLASS_DEPEND_TEAM);
					mySQLBlogClass.setTeamCode(StringTools.formatToString(teamInfo.getTeamCode()));
					mySQLBlogClass.setClassBlogNum(0);
					mySQLBlogClass.setOldId(db2BlogCatalog.getCataId());
					mySQLClassList.add(mySQLBlogClass);
				}
				
				// 插入
				insertTeamClassFlag = blogClassService.insertMySQLClassList(mySQLClassList);
				if (!insertTeamClassFlag) {
					return false;
				}
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}

}
