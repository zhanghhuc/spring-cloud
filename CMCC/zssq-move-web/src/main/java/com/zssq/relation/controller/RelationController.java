package com.zssq.relation.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.pojo.ResultJSON;
import com.zssq.relation.service.RelationService;

import net.sf.json.JSONArray;

@Controller
public class RelationController {
	
	@Resource
	private RelationService relationService;
	
	/**
	 * @Function getRecList
	 * @Description 查询推荐列表
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/getRecList")  
	@ResponseBody
    public ResultJSON getRecList(HttpServletRequest req,HttpServletResponse res){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		body.put("recList",JSONArray.fromObject(relationService.getRecList()));
		result.setBody(body);
		return result;
	}
	

	
	/**
	 * @Function insertMblog
	 * @Description  导入微博推荐
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/importMblog")  
    public ResultJSON insertMblog(Integer pageNo, Integer pageSize){
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		long importedDataCount=0;
		int DB2SubCount=0;
		
		try {
			// 参数校验
			if (pageNo <= 0) {
				pageNo = 0;
			}
			if (pageSize <= 0) {
				pageSize = 100;
			}
			
			// 查询DB2库中订阅数据总量
			 DB2SubCount = relationService.mblogCount();
/*			if (DB2SubCount <= 0) {
				throw new Exception();
			}*/
			
			// 总页数
			int totalPage = DB2SubCount/pageSize + 1;
			
			for (int i = pageNo; i < totalPage; i++) {
				importedDataCount +=relationService.mBlogRecommend(pageNo, pageSize);
				pageNo++;
			}
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			body.put("pageNo", pageNo);
			body.put("oldDataTotalCount", DB2SubCount);
			body.put("importedDataCount", importedDataCount);
			result.setBody(body);
			e.printStackTrace();
		}
		body.put("oldDataTotalCount", DB2SubCount);
		body.put("importedDataCount", importedDataCount);
		result.setBody(body);
		return result;
	}
	
	
	/**
	 * @Function insertBlog
	 * @Description 导入博客推荐
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/importBlog")  
    public ResultJSON insertBlog(Integer pageNo, Integer pageSize){
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		long importedDataCount=0;
		int DB2SubCount =0;
		
		try {
			// 参数校验
			if (pageNo <= 0) {
				pageNo = 0;
			}
			if (pageSize <= 0) {
				pageSize = 100;
			}
			
			// 查询DB2库中订阅数据总量
			 DB2SubCount = relationService.blogCount();
/*			if (DB2SubCount <= 0) {
				throw new Exception();
			}*/
			
			// 总页数
			int totalPage = DB2SubCount/pageSize + 1;
			
			for (int i = pageNo; i < totalPage; i++) {
				importedDataCount += relationService.blogRecommend(pageNo, pageSize);
				pageNo++;
			}
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			body.put("pageNo", pageNo);
			body.put("oldDataTotalCount", DB2SubCount);
			body.put("importedDataCount", importedDataCount);
			result.setBody(body);
			e.printStackTrace();
		}
		body.put("oldDataTotalCount", DB2SubCount);
		body.put("importedDataCount", importedDataCount);
		result.setBody(body);
		return result;
	}
	
	
	/**
	 * @Function importMblogSubjectInfo
	 * @Description 导入微博推荐相关的内容信息 表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody			  
	@RequestMapping(value = "/importMblogSubjectInfo")  
    public ResultJSON importMblogSubjectInfo(Integer pageNo, Integer pageSize){
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		long importedDataCount=0;
		int DB2SubCount =0;
		
		try {
			// 参数校验
			if (pageNo <= 0) {
				pageNo = 0;
			}
			if (pageSize <= 0) {
				pageSize = 100;
			}
			
			// 查询DB2库中订阅数据总量
			 DB2SubCount = relationService.mBlogSubjectInfoCount();
/*			if (DB2SubCount <= 0) {
				throw new Exception();
			}
*/			
			// 总页数
			int totalPage = DB2SubCount/pageSize + 1;
			
			for (int i = pageNo; i < totalPage; i++) {
				importedDataCount += relationService.mBlogSubjectInfo(pageNo, pageSize);
				pageNo++;
			}
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			body.put("pageNo", pageNo);
			body.put("oldDataTotalCount", DB2SubCount);
			body.put("importedDataCount", importedDataCount);
			result.setBody(body);
			e.printStackTrace();
		}
		body.put("oldDataTotalCount", DB2SubCount);
		body.put("importedDataCount", importedDataCount);
		result.setBody(body);
		return result;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/importBlogSubjectInfo")  
    public ResultJSON importBlogSubjectInfo(Integer pageNo, Integer pageSize){
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		long importedDataCount=0;
		int DB2SubCount =0;
		
		try {
			// 参数校验
			if (pageNo <= 0) {
				pageNo = 0;
			}
			if (pageSize <= 0) {
				pageSize = 100;
			}
			
			// 查询DB2库中订阅数据总量
			 DB2SubCount = relationService.blogSubjectInfoCount();
/*			if (DB2SubCount <= 0) {
				throw new Exception();
			}*/
			
			// 总页数
			int totalPage = DB2SubCount/pageSize + 1;
			
			for (int i = pageNo; i < totalPage; i++) {
				importedDataCount += relationService.blogSubjectInfo(pageNo, pageSize);
				pageNo++;
			}
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			body.put("pageNo", pageNo);
			body.put("oldDataTotalCount", DB2SubCount);
			body.put("importedDataCount", importedDataCount);
			result.setBody(body);
			e.printStackTrace();
		}
		body.put("oldDataTotalCount", DB2SubCount);
		body.put("importedDataCount", importedDataCount);
		result.setBody(body);
		return result;
	}
	
	
	
	
	/**
	 * @Function importMBlogSubjectData
	 * @Description 导入博客推荐相关内容信息表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/importMBlogSubjectData")  
    public ResultJSON importMBlogSubjectData(Integer pageNo, Integer pageSize){
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		long importedDataCount=0;
		int DB2SubCount =0;
		
		try {
			// 参数校验
			if (pageNo <= 0) {
				pageNo = 0;
			}
			if (pageSize <= 0) {
				pageSize = 100;
			}
			
			// 查询DB2库中订阅数据总量
			 DB2SubCount = relationService.mBlogSubjectDataCount();
/*			if (DB2SubCount <= 0) {
				throw new Exception();
			}*/
			
			// 总页数
			int totalPage = DB2SubCount/pageSize + 1;
			
			for (int i = pageNo; i < totalPage; i++) {
			    importedDataCount += relationService.mBlogSubjectData(pageNo, pageSize);
				pageNo++;
			}
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			body.put("pageNo", pageNo);
			body.put("oldDataTotalCount", DB2SubCount);
			body.put("importedDataCount", importedDataCount);
			result.setBody(body);
			e.printStackTrace();
		}
		body.put("oldDataTotalCount", DB2SubCount);
		body.put("importedDataCount", importedDataCount);
		result.setBody(body);
		return result;
	}
	
	
	
	/**
	 * @Function importBlogSubjectData
	 * @Description 导入博客推荐相关的内容信息表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/importBlogSubjectData")  
    public ResultJSON importBlogSubjectData(Integer pageNo, Integer pageSize){
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		long importedDataCount=0;
		int DB2SubCount =0;
		
		try {
			// 参数校验
			if (pageNo <= 0) {
				pageNo = 0;
			}
			if (pageSize <= 0) {
				pageSize = 100;
			}
			
			// 查询DB2库中订阅数据总量
			 DB2SubCount = relationService.blogSubjectDataCount();
	/*		if (DB2SubCount <= 0) {
				throw new Exception();
			}*/
			
			// 总页数
			int totalPage = DB2SubCount/pageSize + 1;
			
			for (int i = pageNo; i < totalPage; i++) {
				importedDataCount += relationService.blogSubjectData(pageNo, pageSize);
				pageNo++;
			}
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			body.put("pageNo", pageNo);
			body.put("oldDataTotalCount", DB2SubCount);
			body.put("importedDataCount", importedDataCount);
			result.setBody(body);
			e.printStackTrace();
		}
		body.put("oldDataTotalCount", DB2SubCount);
		body.put("importedDataCount", importedDataCount);
		result.setBody(body);
		return result;
	}
	
	
	

}
