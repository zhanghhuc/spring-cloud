package com.zssq.news.controller;

import com.alibaba.fastjson.JSONObject;
import com.zssq.news.service.NoticeService;
import com.zssq.news.vo.NewsNoticeDB2;
import com.zssq.news.vo.NewsNoticeMySql;
import com.zssq.pojo.ResultJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: NoticeController
 * @Description: 公告数据迁移Controller
 * @author SharlaCheung
 * @date 2017年5月22日
 *
 */
@Controller
@RequestMapping("noticeController")
public class NoticeController {
//
//	@Autowired
//	private NoticeService noticeService;
//
//	/**
//	 *
//	 * @Title: transferNoticeData
//	 * @Description: 迁移公告数据
//	 * @param pageNo
//	 * @param pageSize    参数
//	 * @return: void    返回类型
//	 */
//	@RequestMapping(value = "/transferNoticeData")
//	@ResponseBody
//	public ResultJSON transferNoticeata(int pageNo, int pageSize) {
//		// 返回值
//		ResultJSON result = new ResultJSON("COMMON_200");
//		JSONObject body = new JSONObject();
//
//		try {
//			// 参数校验
//			if (pageNo <= 0) {
//				pageNo = 0;
//			}
//			if (pageSize <= 0) {
//				pageSize = 100;
//			}
//
//			// 查询DB2库中订阅数据总量
//			int DB2SubCount = noticeService.getDB2NewsCount();
//			if (DB2SubCount <= 0) {
//				body.put("message", "查询DB2库中公告数据总量时出错，查询出的个数为：" + DB2SubCount);
//				throw new Exception();
//			}
//
//			// 总页数
//			int totalPage = (DB2SubCount+pageSize-1)/pageSize;
//
//			// 循环处理数据
//			boolean arrangeFlag = true;
//			for (int i = pageNo; i < totalPage; i++) {
//				arrangeFlag = arrangeSubData(pageNo, pageSize);
//				if (!arrangeFlag) {
//					body.put("message", "处理数据时失败");
//					throw new Exception();
//				}
//				pageNo++;
//			}
//		} catch (Exception e) {
//			result = new ResultJSON("COMMON_400");
//			body.put("pageNo", pageNo);
//			result.setBody(body);
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	/**
//	 *
//	 * @Title: arrangeSubData
//	 * @Description: 整理订阅数据
//	 * @param pageNo
//	 * @param pageSize    参数
//	 * @return: boolean    返回类型
//	 */
//	private boolean arrangeSubData(int pageNo, int pageSize) {
//		// 返回值
//		boolean result = true;
//
//		try {
//			// 获取DB2库中的订阅数据
//			List<NewsNoticeDB2> DB2NewsList = noticeService.getDB2SubList(pageNo, pageSize);
//
//			// 校验返回值
//			if (DB2NewsList == null || DB2NewsList.isEmpty()) {
//				return false;
//			}
//
//			// 将DB2中的数据整理为MySQL中的数据
//			List<NewsNoticeMySql> mySQLSubList = new ArrayList<NewsNoticeMySql>();
//			for (NewsNoticeDB2 newsNoticeDB2 : DB2NewsList) {
//				NewsNoticeMySql newsNoticeMySql = new NewsNoticeMySql();
//				newsNoticeMySql.setNoticeCode(newsNoticeDB2.getNoticeCode());
//				newsNoticeMySql.setNoticeTitle(newsNoticeDB2.getNoticeTitle());
//				newsNoticeMySql.setNoticeContentText(newsNoticeDB2.getNoticeContentHtml());
//				newsNoticeMySql.setNoticeContentHtml(newsNoticeDB2.getNoticeContentHtml());
//				newsNoticeMySql.setNoticeFileUrl("");
//				newsNoticeMySql.setNoticeStatus(4);
//				newsNoticeMySql.setNoticeCreatorCode(newsNoticeDB2.getNoticeCreatorCode());
//				newsNoticeMySql.setNoticeOperatorCode(newsNoticeDB2.getNoticeOperatorCode());
//				newsNoticeMySql.setCreateTime(newsNoticeDB2.getCreateTime().getTime());
//				newsNoticeMySql.setModifyTime(newsNoticeDB2.getModifyTime().getTime());
//				// TODO: 2017-05-22
////				newsNoticeMySql.setOrgCode();
//				newsNoticeMySql.setIsTop(0);
//				newsNoticeMySql.setIsDelete(0);
//				newsNoticeMySql.setNoticeSort(999);
//				newsNoticeMySql.setIsHidden(0);
//
//				mySQLSubList.add(newsNoticeMySql);
//			}
//
//			// 插入订阅数据到MySQL库中
//			boolean insertFlag = noticeService.insertMySQLSubList(mySQLSubList);
//			if (!insertFlag) {
//				result = false;
//			}
//		} catch (Exception e) {
//			result = false;
//			e.printStackTrace();
//		}
//
//		return result;
//	}
	
}
