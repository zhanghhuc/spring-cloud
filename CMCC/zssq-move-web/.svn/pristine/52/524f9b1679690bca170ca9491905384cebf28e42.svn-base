package com.zssq.news.controller;

import com.alibaba.fastjson.JSONObject;
import com.zssq.news.service.NewsService;
import com.zssq.news.vo.NewsInfoVoDB2;
import com.zssq.news.vo.NewsInfoVoMysql;
import com.zssq.pojo.ResultJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: NewsController
 * @Description: 新闻数据迁移Controller
 * @author SharlaCheung
 * @date 2017年5月22日
 *
 */
@Controller
@RequestMapping("newsController")
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	/**
	 * 
	 * @Title: transferNewsData
	 * @Description: 迁移新闻数据
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
			int DB2SubCount = newsService.getDB2NewsCount();
			if (DB2SubCount <= 0) {
				body.put("message", "查询DB2库中新闻数据总量时出错，查询出的个数为：" + DB2SubCount);
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
			List<NewsInfoVoDB2> DB2NewsList = newsService.getDB2SubList(pageNo, pageSize);
			
			// 校验返回值
			if (DB2NewsList == null || DB2NewsList.isEmpty()) {
				return false;
			}
			
			// 将DB2中的数据整理为MySQL中的数据
			List<NewsInfoVoMysql> mySQLSubList = new ArrayList<NewsInfoVoMysql>();
			for (NewsInfoVoDB2 newsInfoVoDB2 : DB2NewsList) {
				NewsInfoVoMysql newsInfoVoMysql = new NewsInfoVoMysql();
				newsInfoVoMysql.setNewsCode(newsInfoVoDB2.getNewsCode());
				newsInfoVoMysql.setInfoTitle(newsInfoVoDB2.getInfoTitle());
//				if(null != newsInfoVoDB2.getInfoContentHtml() &&!"".equals(newsInfoVoDB2.getInfoContentHtml())){
//					int length = newsInfoVoDB2.getInfoContentHtml().length() ;
//					if(length<200){
//						newsInfoVoMysql.setInfoContentText(newsInfoVoDB2.getInfoContentHtml());
//					}else{
//						newsInfoVoMysql.setInfoContentText(newsInfoVoDB2.getInfoContentHtml().substring(0,200)+"...");
//					}
//					newsInfoVoMysql.setInfoContentHtml(newsInfoVoDB2.getInfoContentHtml());
//				}
				newsInfoVoMysql.setInfoContentText(newsInfoVoDB2.getInfoContentHtml());
				newsInfoVoMysql.setFileUrl(newsInfoVoDB2.getFileUrl());
				newsInfoVoMysql.setInfoStatus(newsInfoVoDB2.getInfoStatus());
				newsInfoVoMysql.setInfoCreatorCode(newsInfoVoDB2.getInfoCreatorCode());
				newsInfoVoMysql.setInfoOperatorCode(newsInfoVoDB2.getInfoOperatorCode());
				newsInfoVoMysql.setCreateTime(newsInfoVoDB2.getCreateTime().getTime());
				newsInfoVoMysql.setModifyTime(newsInfoVoDB2.getModifyTime().getTime());
				// TODO: 2017-05-22
//				newsInfoVoMysql.setOrgCode();
				newsInfoVoMysql.setIsTop(0);
				newsInfoVoMysql.setIsDelete(newsInfoVoDB2.getIsDelete());
				newsInfoVoMysql.setInfoSort(999);
				newsInfoVoMysql.setIsShield(newsInfoVoDB2.getIsDelete());
				newsInfoVoMysql.setInfoGoodCount(0);
				newsInfoVoMysql.setInfoCommentCount(0);
				newsInfoVoMysql.setCollectNumber(0);
				newsInfoVoMysql.setTransmitNumber(0);

				mySQLSubList.add(newsInfoVoMysql);
			}
			
			// 插入订阅数据到MySQL库中
			boolean insertFlag = newsService.insertMySQLSubList(mySQLSubList);
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
