package com.zssq.relation.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.pojo.ResultJSON;
import com.zssq.relation.service.IRelationService;

/**
 * 
 * @ClassName: TransferRecommandController  
 * @Description: 导入推荐数据  
 * @author sry  
 * @date 2017年6月21日  
 *
 */
@Controller  
@RequestMapping("/relationRecommend")
public class TransferRecommendController {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IRelationService relationService;
	/**
	 * @Function insertMblog
	 * @Description  导入微博推荐
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/importMblog")  
    public ResultJSON insertMblog(int pageNo,int pageSize){
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
					 DB2SubCount = relationService.recMblogCount();
					if (DB2SubCount <= 0) {
						throw new Exception();
					}
					
					// 总页数
					int totalPage = DB2SubCount/pageSize + 1;
					
					for (int i = pageNo; i < totalPage; i++) {
						importedDataCount +=relationService.importMblogRecommend(pageNo, pageSize);
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
	
	
	*//**
	 * @Function insertBlog
	 * @Description 导入博客推荐
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *//*
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
			 DB2SubCount = relationService.recBlogCount();
			if (DB2SubCount <= 0) {
				throw new Exception();
			}
			
			// 总页数
			int totalPage = DB2SubCount/pageSize + 1;
			
			for (int i = pageNo; i < totalPage; i++) {
				importedDataCount += relationService.importBlogRecommend(pageNo, pageSize);
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
	*/
	/*********************************new 导入数据****************************************/
	@ResponseBody
	@RequestMapping(value = "/batchImportRecommend")  
	public ResultJSON batchImportRecommend(){
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		// 开始时间
		long beginTime = System.currentTimeMillis(); 
		log.info("开始时间：" + beginTime);
		try {
			// 导入原评论数据
			log.info("导入推荐数据中...");
			relationService.batchImportRecommend();
			// 结束时间
			long endTime = System.currentTimeMillis(); 
			log.info("结束时间：" + endTime);
			
			// 用时
			long useTime = endTime - beginTime;
			log.info("使用时间：" + useTime);
			// 返回值
			body.put("message", "将推荐数据导入成功，共用时:" + useTime);
			result.setBody(body);
		}catch(Exception e) {
			result = new ResultJSON("COMMON_400");
			result.setBody(body);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @Title: batchImportBlog  
	 * @Description: 导入博客关系内容
	 * @return    参数  
	 * @return: String    返回类型
	 */
	@ResponseBody
	@RequestMapping(value = "/batchImportBlog")  
	public String batchImportBlog(){
		//博客自己导入
		//relationService.batchImportBlog();
		return "success";
	}
	/**
	 * 
	 * @Title: batchImportMblog  
	 * @Description: 导入微博 关系内容
	 * @return    参数  
	 * @return: String    返回类型
	 */
	@ResponseBody
	@RequestMapping(value = "/batchImportMblog")  
	public ResultJSON batchImportMblog(){
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		// 开始时间
		long beginTime = System.currentTimeMillis(); 
		log.info("开始时间：" + beginTime);
		try {
			// 导入原评论数据
			log.info("导入推荐微博到管理内容信息数据中...");
			relationService.batchImportMblog();
			// 结束时间
			long endTime = System.currentTimeMillis(); 
			log.info("结束时间：" + endTime);
			
			// 用时
			long useTime = endTime - beginTime;
			log.info("使用时间：" + useTime);
			// 返回值
			body.put("message", "将推荐微博到管理内容信息导入成功，共用时:" + useTime);
			result.setBody(body);
		}catch(Exception e) {
			result = new ResultJSON("COMMON_400");
			body.put("message", "将推荐微博到管理内容信息导入失败");
			result.setBody(body);
			e.printStackTrace();
		}
		return result;
	}
}
