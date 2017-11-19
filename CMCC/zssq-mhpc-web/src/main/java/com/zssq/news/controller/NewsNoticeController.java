package com.zssq.news.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.NewsNoticeConstants;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsNoticeContent;
import com.zssq.news.model.NoticeQuery;
import com.zssq.news.newsnoticevo.NoticeBaseVo;
import com.zssq.news.newsvo.NewsNoticeListVO;
import com.zssq.news.service.NewsNoticeContentService;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysUserService;
import com.zssq.utils.PageBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: NewsNoticeController
 * @Description: 公告Controller
 * @author SharlaCheung
 * @date 2017年4月13日
 *
 */
@Controller
@RequestMapping("/noticepc")
public class NewsNoticeController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private NewsNoticeContentService newsNoticeContentService;

	@Autowired
	private ISysUserService sysUserService;
	
	/**
	 * 
	 * @Title: getNotices
	 * @Description: 公告列表查询
	 * @param newsNoticeListVO
	 * @return    参数  
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException 
	 */
	@RequestMapping("/getNotices")
	@ResponseBody
	public ResultJSON getNotices(@RequireValid NewsNoticeListVO newsNoticeListVO) throws BusinessException{
		NoticeQuery noticeQuery=new NoticeQuery();
		ResultJSON resJson = null ;
		JSONObject json = null ;
		JSONArray jsonArray=new JSONArray();
		JSONObject body = new JSONObject();
		try {
			noticeQuery.setUserCode(newsNoticeListVO.getUserCode());
			noticeQuery.setPageNo(newsNoticeListVO.getPageNo());
			noticeQuery.setPageSize(newsNoticeListVO.getPageSize());
			noticeQuery.setLimitStart(noticeQuery.getPageNo()*noticeQuery.getPageSize());
			noticeQuery.setNoticeStatus(NewsNoticeConstants.NOTICE_STATUS_PUBLISHED);
			noticeQuery.setIsDelete(NewsNoticeConstants.NOTICE_NO_DELETE);
			noticeQuery.setOrgCode(newsNoticeListVO.getOrgCode());
			//查询条件
			PageBean pageBean=newsNoticeContentService.getNoticesPc(noticeQuery);
			//返回值封装
			List<NewsNoticeContent> list=pageBean.getRecordList();
			//根据用户code列表获取用户集合
			if(list.size()>0){
				List<String> usercodes = new ArrayList<>();
				for (NewsNoticeContent newsNoticeContent : list) {
					usercodes.add(newsNoticeContent.getNoticeOperatorCode());
				}
				List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
				Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
				for(int i=0;i<list.size();i++){
					json=new JSONObject();
					NewsNoticeContent temp=list.get(i);
					json.put("noticeCode", temp.getNoticeCode());
					json.put("noticeTitle",temp.getNoticeTitle());
					json.put("noticeContentText",temp.getNoticeContentText());
					json.put("noticeContentHtml",temp.getNoticeContentHtml());
					json.put("noticeStatus", temp.getNoticeStatus());
					json.put("noticeOperatorCode", temp.getNoticeOperatorCode());
					SysUserInfo userInfo = userMap.get(temp.getNoticeOperatorCode());
					json.put("noticeOperatorName",userInfo == null ? "" : userInfo.getUserName());
					json.put("isTop", temp.getIsTop());
					json.put("noticeSort", temp.getNoticeSort());
					json.put("createTime", temp.getCreateTime());
					jsonArray.add(json) ;
				}
			}
			resJson=new ResultJSON("COMMON_200");
			body.put("totalCount", pageBean.getTotalCount());
			body.put("list", jsonArray);
			resJson.setBody(body);
			log.info("NewsNoticeController.page:获取公告列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("NOTICE_14102", "获取公告列表");
		}
		return resJson;
	}

	/**
	 *
	 * @Title: getNoticeDetail
	 * @Description: 公告明细
	 * @param noticeBaseVo
	 * @return    参数
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException
	 */
	@RequestMapping("/getNoticeDetail")
	@ResponseBody
	public ResultJSON getNoticeDetail(@RequireValid NoticeBaseVo noticeBaseVo) throws BusinessException{
		NoticeQuery noticeQuery=new NoticeQuery();
		ResultJSON resJson = null ;
		JSONObject json=new JSONObject();
		try {
			noticeQuery.setNoticeCode(noticeBaseVo.getNoticeCode());//只查询未被隐藏的新闻
			noticeQuery.setIsDelete(NewsNoticeConstants.NOTICE_NO_DELETE);
			noticeQuery.setIsHidden(NewsNoticeConstants.NOTICE_NO_HIDDEN);
			//查询条件
			NewsNoticeContent noticeDetail=newsNoticeContentService.getNoticeDetail(noticeQuery);
			//返回值封装
			if(null != noticeDetail){
				SysUserInfo sysUser = sysUserService.selectByCode(noticeDetail.getNoticeOperatorCode());
				json.put("noticeCode", noticeDetail.getNoticeCode());
				json.put("noticeTitle",noticeDetail.getNoticeTitle());
				json.put("noticeContentText",noticeDetail.getNoticeContentText());
				json.put("noticeContentHtml",noticeDetail.getNoticeContentHtml());
				json.put("noticeStatus", noticeDetail.getNoticeStatus());
				json.put("noticeOperatorCode", noticeDetail.getNoticeOperatorCode());
				json.put("noticeOperatorName", sysUser==null?"":sysUser.getUserName());
				json.put("isTop", noticeDetail.getIsTop());
				json.put("noticeSort", noticeDetail.getNoticeSort());
			}
			resJson=new ResultJSON("COMMON_200");
			resJson.setBody(json);

			log.info("NewsNoticeController.getNoticeDetail:获取公告明细成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("NEWS_14002", "获取公告明细");
		}
		return resJson;
	}


	private Map<String, SysUserInfo> generateUserInfoMap(List<SysUserInfo> userInfos) {
		Map<String, SysUserInfo> users = new HashMap<String, SysUserInfo>();
		for (SysUserInfo sysUserInfo : userInfos) {
            if(null !=sysUserInfo){
                users.put(sysUserInfo.getUserCode(), sysUserInfo);
            }
		}
		return users;
	}
}