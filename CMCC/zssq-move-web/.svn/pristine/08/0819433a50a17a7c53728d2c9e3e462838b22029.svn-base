package com.zssq.news.controller;

import com.alibaba.fastjson.JSONObject;
import com.zssq.news.service.NewsUploadService;
import com.zssq.news.vo.NewsInfoUploadDB2;
import com.zssq.news.vo.NewsInfoUploadMySql;
import com.zssq.pojo.ResultJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: NewsUploadController
 * @Description: 新闻上传数据迁移Controller
 * @author SharlaCheung
 * @date 2017年5月22日
 *
 */
@Controller
@RequestMapping("newsUploadController")
public class NewsUploadController {

	@Autowired
	private NewsUploadService newsUploadService;
	
	/**
	 * 
	 * @Title: transferNewsData
	 * @Description: 迁移新闻上传数据
	 * @param pageNo
	 * @param pageSize    参数  
	 * @return: void    返回类型
	 */
	@RequestMapping(value = "/transferNewsData")
	@ResponseBody
	public ResultJSON transferNewsData(int pageNo, int pageSize) {
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
			
			// 查询DB2库中订阅数据总量
			int DB2SubCount = newsUploadService.getDB2UploadCount();
			if (DB2SubCount <= 0) {
				body.put("message", "查询DB2库中新闻上传数据总量时出错，查询出的个数为：" + DB2SubCount);
				throw new Exception();
			}
			
			// 总页数
			int totalPage = (DB2SubCount+pageSize-1)/pageSize;
			
			// 循环处理数据
			boolean arrangeFlag = true;
			for (int i = pageNo; i < totalPage; i++) {
				arrangeFlag = arrangeSubData(pageNo, pageSize);
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
	
	/**
	 * 
	 * @Title: arrangeSubData  
	 * @Description: 整理订阅数据
	 * @param pageNo
	 * @param pageSize    参数  
	 * @return: boolean    返回类型
	 */
	private boolean arrangeSubData(int pageNo, int pageSize) {
		// 返回值
		boolean result = true;
		
		try {
			// 获取DB2库中的订阅数据
			List<NewsInfoUploadDB2> DB2NewsList = newsUploadService.getDB2SubList(pageNo, pageSize);
			
			// 校验返回值
			if (DB2NewsList == null || DB2NewsList.isEmpty()) {
				return false;
			}
			
			// 将DB2中的数据整理为MySQL中的数据
			List<NewsInfoUploadMySql> mySQLSubList = new ArrayList<NewsInfoUploadMySql>();
			for (NewsInfoUploadDB2 newsInfoUploadDB2 : DB2NewsList) {
				NewsInfoUploadMySql newsInfoUploadMySql = new NewsInfoUploadMySql();

				newsInfoUploadMySql.setInfoUploadCode(newsInfoUploadDB2.getInfoUploadCode());
				// TODO: 2017-05-23
//				newsInfoUploadMySql.setNewsCode(newsInfoUploadDB2.getNewsCode());
				newsInfoUploadMySql.setInfoType(newsInfoUploadDB2.getInfoType());

				newsInfoUploadMySql.setInfoPath(newsInfoUploadDB2.getInfoPath());
				newsInfoUploadMySql.setInfoOrgName(newsInfoUploadDB2.getInfoOrgName());
				newsInfoUploadMySql.setInfoNewName(newsInfoUploadDB2.getInfoNewName());
				newsInfoUploadMySql.setInfoOrgExt(newsInfoUploadDB2.getInfoOrgExt());
				newsInfoUploadMySql.setInfoNewExt(newsInfoUploadDB2.getInfoNewExt());
				newsInfoUploadMySql.setInfoUploadTime(newsInfoUploadDB2.getInfoUploadTime().getTime());
				newsInfoUploadMySql.setCreateTime(newsInfoUploadDB2.getInfoUploadTime().getTime());
				newsInfoUploadMySql.setModifyTime(newsInfoUploadDB2.getModifyTime().getTime());
				// TODO: 2017-05-22
//				newsInfoUploadMySql.setOrgCode();
				mySQLSubList.add(newsInfoUploadMySql);
			}
			
			// 插入订阅数据到MySQL库中
			boolean insertFlag = newsUploadService.insertMySQLSubList(mySQLSubList);
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
