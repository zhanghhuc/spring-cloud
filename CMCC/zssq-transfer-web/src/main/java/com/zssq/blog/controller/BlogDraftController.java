package com.zssq.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.blog.pojo.BlogDraft;
import com.zssq.blog.pojo.DraftTemp;
import com.zssq.blog.pojo.SourceDraftModel;
import com.zssq.blog.service.BlogDraftService;
import com.zssq.constants.BlogConstants;
import com.zssq.pojo.ResultJSON;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.StringTools;

/**
 * 
 * @ClassName: BlogDraftController  
 * @Description: 草稿数据迁移Controller  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Controller
@RequestMapping("blogDraftController")
public class BlogDraftController {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BlogDraftService blogDraftService;
	
	/**
	 * 
	 * @Title: transferUserDraftData  
	 * @Description: 迁移个人草稿数据
	 * @param pageNo
	 * @param pageSize    参数  
	 * @return: void    返回类型
	 */
	@RequestMapping(value = "/transferUserDraftData")  
	@ResponseBody
	public ResultJSON transferUserDraftData(int pageNo, int pageSize) {
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		
		// 开始时间
		long beginTime = System.currentTimeMillis(); 
		log.info("开始时间：" + beginTime);
		
		try {
			// 参数校验
			if (pageNo <= 0) {
				pageNo = 0;
			}
			if (pageSize <= 0) {
				pageSize = 1000;
			}
			
			// 创建临时表
			log.info("开始创建草稿临时表...");
			boolean createFlag = createTempTable();
			if (!createFlag) {
				body.put("message", "新建草稿临时表失败");
				log.error("新建草稿临时表失败");
				throw new Exception();
			}
			log.info("创建草稿临时表成功");
			
			// 查询原草稿表中数据总量
			log.info("开始查询源数据库中草稿数据总量...");
			int sourceDraftCount = blogDraftService.getSourceDraftCount();
			if (sourceDraftCount <= 0) {
				body.put("message", "查询源数据库中草稿数据总量时出错，查询出的个数为：" + sourceDraftCount);
				log.error("查询源数据库中草稿数据总量时出错，查询出的个数为：" + sourceDraftCount);
				throw new Exception();
			}
			log.info("查询源数据库中草稿数据总量成功");
			
			// 总页数
			int totalPage = sourceDraftCount/pageSize + 1;
			
			// 导入原个人草稿数据
			boolean insertFlag = true;
			for (int i = pageNo; i < totalPage; i++) {
				log.info("开始导入原个人草稿第" + pageNo + "页...");
				insertFlag = insertSourceDraft(pageNo, pageSize, BlogConstants.BLOG_DRAFT_DEPEND_USER);
				if (!insertFlag) {
					body.put("message", "导入原个人草稿时失败");
					log.error("导入原个人草稿时失败，pageNo=" + pageNo);
					throw new Exception();
				}
				log.info("导入原个人草稿第" + pageNo + "页成功");
				pageNo++;
			}
			
			// 结束时间
			long endTime = System.currentTimeMillis(); 
			log.info("结束时间：" + endTime);
			
			// 用时
			long useTime = endTime - beginTime;
			log.info("使用时间：" + useTime);
			
			// 返回值
			body.put("message", "导入个人草稿成功，共用时:" + useTime);
			result.setBody(body);
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			body.put("pageNo", pageNo);
			result.setBody(body);
			long errorTime = System.currentTimeMillis(); 
			log.info("失败时间：" + errorTime);
			long useTime = errorTime - beginTime;
			log.info("使用时间：" + useTime);
			body.put("useTime", "共用时:" + useTime);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: transferTeamDraftData  
	 * @Description: 迁移班组草稿数据
	 * @return: void    返回类型
	 */
	@RequestMapping(value = "/transferTeamDraftData")  
	@ResponseBody
	public ResultJSON transferTeamDraftData() {
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		
		try {
			// 开始时间
			long beginTime = System.currentTimeMillis(); 
			log.info("开始时间：" + beginTime);
			
			// 创建临时表
			log.info("开始创建草稿临时表...");
			boolean createFlag = createTempTable();
			if (!createFlag) {
				body.put("message", "新建草稿临时表失败");
				log.error("新建草稿临时表失败");
				throw new Exception();
			}
			log.info("创建草稿临时表成功");

			// 导入原班组草稿数据
			log.info("开始导入原班组草稿...");
			boolean insertFlag = insertSourceDraft(0, 0, BlogConstants.BLOG_DRAFT_DEPEND_TEAM);
			if (!insertFlag) {
				body.put("message", "导入原班组草稿时失败");
				throw new Exception();
			}
			
			// 结束时间
			long endTime = System.currentTimeMillis(); 
			log.info("结束时间：" + endTime);
			
			// 用时
			long useTime = endTime - beginTime;
			log.info("使用时间：" + useTime);
			
			// 返回值
			body.put("message", "导入班组草稿成功，共用时:" + useTime);
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
	 * @Title: transferDraftContent  
	 * @Description: 迁移草稿内容数据
	 * @return: void    返回类型
	 */
	@RequestMapping(value = "/transferDraftContent")  
	@ResponseBody
	public ResultJSON transferDraftContent() {
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		
		try {
			// 开始时间
			long beginTime = System.currentTimeMillis(); 
			log.info("开始时间：" + beginTime);

			// 导入原草稿内容数据
			log.info("开始导入原草稿内容...");
			boolean	insertFlag = insertSourceDraftContent();
			if (!insertFlag) {
				body.put("message", "导入原草稿内容时失败");
				log.error("导入原草稿内容时失败");
				throw new Exception();
			}
			
			// 结束时间
			long endTime = System.currentTimeMillis(); 
			log.info("结束时间：" + endTime);
			
			// 用时
			long useTime = endTime - beginTime;
			log.info("使用时间：" + useTime);
			
			// 返回值
			body.put("message", "导入草稿内容成功，共用时:" + useTime);
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
	 * @Title: createTempTable  
	 * @Description: 创建临时表
	 * @return: boolean    返回类型
	 */
	private boolean createTempTable() {
		// 返回值
		boolean result = true;
		
		try {
			// 新建临时表
			blogDraftService.createTempTable();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: insertSourceDraft  
	 * @Description: 导入原草稿数据
	 * @param pageNo
	 * @param pageSize
	 * @param draftDepend
	 * @return: boolean    返回类型
	 */
	private boolean insertSourceDraft(int pageNo, int pageSize, Byte draftDepend) {
		// 返回值
		boolean result = true;
		
		try {
			// 获取原草稿数据
			List<SourceDraftModel> sourceDraftModelList = blogDraftService.getSourceDraftList(pageNo, pageSize, draftDepend);
			
			// 校验返回值
			if (sourceDraftModelList == null) {
				return false;
			}
			if (sourceDraftModelList.isEmpty()) {
				return true;
			}
			
			// 整理数据
			List<BlogDraft> blogDraftList = new ArrayList<BlogDraft>(); // 草稿列表
			List<DraftTemp> draftTempList = new ArrayList<DraftTemp>(); // 草稿临时表列表
			for (SourceDraftModel sourceDraftModel : sourceDraftModelList) {
				if (sourceDraftModel == null) {
					continue;
				}
				
				// 共用
				String draftCode = UUIDHelper.getUUID(); // 草稿编号
				
				// 判断数据是否需要导入
				if (StringUtils.isBlank(sourceDraftModel.getUserCode())) {
					continue;
				}
				if (BlogConstants.BLOG_DRAFT_DEPEND_TEAM.equals(draftDepend) && StringUtils.isBlank(sourceDraftModel.getTeamCode())) {
					continue;
				}
				
				// 草稿
				BlogDraft blogDraft = new BlogDraft();
				blogDraft.setDraftCode(draftCode);
				blogDraft.setTenantCode(StringTools.formatToString(sourceDraftModel.getTenantCode()));
				blogDraft.setOrgCode(StringTools.formatToString(sourceDraftModel.getOrgCode()));
				blogDraft.setCreateTime(sourceDraftModel.getCreateTime() == null ? 0L : sourceDraftModel.getCreateTime().getTime());
				blogDraft.setModifyTime(sourceDraftModel.getUpdatetime() == null ? 0L : sourceDraftModel.getUpdatetime().getTime());
				blogDraft.setUserCode(StringTools.formatToString(sourceDraftModel.getUserCode()));
				if (BlogConstants.BLOG_DRAFT_DEPEND_TEAM.equals(draftDepend)) {
					blogDraft.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_TEAM);
					blogDraft.setTeamCode(StringTools.formatToString(sourceDraftModel.getTeamCode()));
				} else {
					blogDraft.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_USER);
				}
				blogDraft.setDraftTitle(StringTools.formatToString(sourceDraftModel.getTitle()));
				blogDraft.setClassCode(StringTools.formatToString(sourceDraftModel.getClassCode()));
				blogDraft.setDraftPlanPublishTime(0L);
				
				blogDraftList.add(blogDraft);
				
				// 临时表
				DraftTemp draftTemp = new DraftTemp();
				draftTemp.setOldId(sourceDraftModel.getBlogId());
				draftTemp.setDraftCode(draftCode);
				draftTemp.setTenantCode(StringTools.formatToString(sourceDraftModel.getTenantCode()));
				draftTemp.setOrgCode(StringTools.formatToString(sourceDraftModel.getOrgCode()));
				draftTemp.setCreateTime(sourceDraftModel.getCreateTime() == null ? 0L : sourceDraftModel.getCreateTime().getTime());
				draftTemp.setModifyTime(sourceDraftModel.getUpdatetime() == null ? 0L : sourceDraftModel.getUpdatetime().getTime());
				
				draftTempList.add(draftTemp);
			}
			
			// 插入
			if (!blogDraftList.isEmpty()) {
				blogDraftService.insertDraftList(blogDraftList, draftTempList);
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 
	 * @Title: insertSourceDraftContent  
	 * @Description: 导入原草稿内容数据
	 * @return: boolean    返回类型
	 */
	private boolean insertSourceDraftContent() {
		// 返回值
		boolean result = true;
		
		try {
			// 导入原草稿内容数据
			blogDraftService.insertSourceDraftContent();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
}
