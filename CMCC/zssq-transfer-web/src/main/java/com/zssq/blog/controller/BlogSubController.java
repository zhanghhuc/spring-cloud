package com.zssq.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.blog.pojo.BlogSub;
import com.zssq.blog.pojo.SourceSubModel;
import com.zssq.blog.service.BlogSubService;
import com.zssq.constants.BlogConstants;
import com.zssq.pojo.ResultJSON;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.StringTools;

/**
 * 
 * @ClassName: BlogSubController  
 * @Description: 博客订阅数据迁移Controller  
 * @author ZKZ  
 * @date 2017年5月18日  
 *
 */
@Controller
@RequestMapping("blogSubController")
public class BlogSubController {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BlogSubService blogSubService;
	
	/**
	 * 
	 * @Title: transferSubData  
	 * @Description: 迁移订阅数据
	 * @param pageNo
	 * @param pageSize    参数  
	 * @return: void    返回类型
	 */
	@RequestMapping(value = "/transferSubData")  
	@ResponseBody
	public ResultJSON transferSubData() {
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		
		try {
			// 开始时间
			long beginTime = System.currentTimeMillis(); 
			log.info("开始时间：" + beginTime);
			
			// 删除订阅表中的数据
			log.info("开始删除订阅表中数据...");
			boolean deleteFlag = deleteSubData();
			if (!deleteFlag) {
				body.put("message", "删除订阅表中数据时失败");
				log.error("删除订阅表中数据时失败");
				throw new Exception();
			}
			log.info("删除订阅表中数据成功");
			
			// 导入原数据
			log.info("开始原订阅数据...");
			boolean insertFlag = insertSourceSub();
			if (!insertFlag) {
				body.put("message", "导入原订阅数据时失败");
				log.error("导入原订阅数据时失败");
				throw new Exception();
			}
			
			// 结束时间
			long endTime = System.currentTimeMillis(); 
			log.info("结束时间：" + endTime);
			
			// 用时
			long useTime = endTime - beginTime;
			log.info("使用时间：" + useTime);
			
			// 返回值
			body.put("message", "导入订阅成功，共用时:" + useTime);
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
	 * @Title: deleteSubData  
	 * @Description: 删除订阅表中的数据
	 * @return: boolean    返回类型
	 */
	private boolean deleteSubData() {
		// 返回值
		boolean result = true;
		
		try {
			// 删除
			blogSubService.deleteSubData();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: insertSourceSub  
	 * @Description: 导入原数据
	 * @return: boolean    返回类型
	 */
	private boolean insertSourceSub() {
		// 返回值
		boolean result = true;
		
		try {
			// 查询原订阅数据
			List<SourceSubModel> sourceSubModelList = blogSubService.getSourceSubList();
			if (sourceSubModelList == null) {
				return false;
			}
			
			// 循环处理数据
			List<BlogSub> blogSubList = new ArrayList<BlogSub>();
			for (SourceSubModel sourceSubModel : sourceSubModelList) {
				BlogSub blogSub = new BlogSub();
				blogSub.setSubCode(UUIDHelper.getUUID());
				blogSub.setTenantCode(StringTools.formatToString(sourceSubModel.getTenantCode()));
				blogSub.setOrgCode(StringTools.formatToString(sourceSubModel.getOrgCode()));
				blogSub.setUserCode(StringTools.formatToString(sourceSubModel.getSubscribeUser()));
				blogSub.setCreateTime(sourceSubModel.getCreateTime() == null ? 0L : sourceSubModel.getCreateTime().getTime());
				blogSub.setModifyTime(sourceSubModel.getCreateTime() == null ? 0L : sourceSubModel.getCreateTime().getTime());
				blogSub.setSubClass(BlogConstants.BLOG_SUB_USER);
				blogSub.setSubUserCode(StringTools.formatToString(sourceSubModel.getPublishUser()));
				blogSubList.add(blogSub);
			}
			
			// 插入订阅数据到MySQL库中
			if (blogSubList != null && !blogSubList.isEmpty()) {
				blogSubService.insertSourceSub(blogSubList);
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
}
