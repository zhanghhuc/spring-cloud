package com.zssq.news.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.NewsNoticeConstants;
import com.zssq.dao.pojo.MessageIntegral;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.news.dao.pojo.NewsNoticeContent;
import com.zssq.news.dao.pojo.NewsNoticeHistory;
import com.zssq.news.model.NoticeHistoryQuery;
import com.zssq.news.model.NoticeModel;
import com.zssq.news.model.NoticeQuery;
import com.zssq.news.noticevo.NewsNoticeAuditVo;
import com.zssq.news.noticevo.NewsNoticeContentVo;
import com.zssq.news.noticevo.NewsNoticeDeleteVO;
import com.zssq.news.noticevo.NewsNoticeHandlerVO;
import com.zssq.news.noticevo.NewsNoticeHiddenVo;
import com.zssq.news.noticevo.NewsNoticeListVO;
import com.zssq.news.noticevo.NewsNoticeMoveVo;
import com.zssq.news.noticevo.NewsNoticeRepealVo;
import com.zssq.news.noticevo.NewsNoticeTopVo;
import com.zssq.news.service.NewsNoticeContentService;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysUserService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: NewsNoticeController
 * @Description: 公告Controller
 * @author SharlaCheung
 * @date 2017年4月13日
 *
 */
@Controller
@RequestMapping("/notice")
public class NewsNoticeController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private NewsNoticeContentService newsNoticeContentService;
//	@Autowired
//	private NewsInfoHistoryService newsInfoHistoryService;
	@Autowired
	private ISysUserService sysUserService;
	@SuppressWarnings("rawtypes")
	@Autowired
	protected KafkaProducerTemplate producerTeplate;
	
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
			SysUserInfo sysUser = sysUserService.selectByCode(newsNoticeListVO.getUserCode());
			//查询条件
			noticeQuery.setUserCode(newsNoticeListVO.getUserCode());
			noticeQuery.setPageNo(Integer.valueOf(newsNoticeListVO.getPageNo()));
			noticeQuery.setPageSize(Integer.valueOf(newsNoticeListVO.getPageSize()));
			noticeQuery.setLimitStart(noticeQuery.getPageNo()*noticeQuery.getPageSize());
			noticeQuery.setOrgCode(sysUser.getManageOrgInfo().getManOrgCode());
			noticeQuery.setIsDelete(NewsNoticeConstants.NOTICE_NO_DELETE);
			noticeQuery.setSelfFlag(Integer.valueOf(newsNoticeListVO.getSelfFlag()));
			if(null != newsNoticeListVO.getNoticeStatus() && !"".equals(newsNoticeListVO.getNoticeStatus())){
				noticeQuery.setNoticeStatus(Integer.valueOf(newsNoticeListVO.getNoticeStatus()));
			}
			if(null != newsNoticeListVO.getStartTime() && !"".equals(newsNoticeListVO.getStartTime())){
				noticeQuery.setStartTime(Long.valueOf(newsNoticeListVO.getStartTime()));
			}
			if(null != newsNoticeListVO.getEndTime() && !"".equals(newsNoticeListVO.getEndTime())){
				noticeQuery.setEndTime(Long.valueOf(newsNoticeListVO.getEndTime()));
			}
			if(null != newsNoticeListVO.getNoticeTitle() && !"".equals(newsNoticeListVO.getNoticeTitle())){
				noticeQuery.setNoticeTitle(newsNoticeListVO.getNoticeTitle());
			}
			PageBean pageBean=newsNoticeContentService.getNotices(noticeQuery);
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
					json.put("noticeOperatorName", userInfo == null ? "" : userInfo.getUserName());
					json.put("noticeCreatorCode", temp.getNoticeCreatorCode());
					json.put("isTop", temp.getIsTop());
					json.put("noticeSort", temp.getNoticeSort());
					json.put("isHidden",temp.getIsHidden());
					json.put("modifyTime",temp.getModifyTime());

					jsonArray.add(json) ;
				}
			}
			resJson=new ResultJSON("COMMON_200");
			body.put("totalCount", pageBean.getTotalCount());
			body.put("topTotal", pageBean.getNumPerPage());
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
	 * @Title: addNoticeInfo
	 * @Description: 添加公告
	 * @param newsNoticeContentVo
	 * @return    参数
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException
	 */
	@RequestMapping("/addNoticeInfo")
	@ResponseBody
	public ResultJSON addNoticeInfo(@RequireValid NewsNoticeContentVo newsNoticeContentVo) throws BusinessException{
		NoticeModel noticeModel = null ;
		ResultJSON resJson = null ;
		JSONObject body = new JSONObject();
		try {
			//获取用户信息
			SysUserInfo sysUserInfo = sysUserService.selectByCode(newsNoticeContentVo.getUserCode()) ;
			noticeModel = convertVoToModel(newsNoticeContentVo,sysUserInfo,newsNoticeContentVo.getUserCode());
			noticeModel.setNoticeCode(UUIDHelper.getUUID());
			//查询条件
			boolean flag = newsNoticeContentService.addNoticeInfo(noticeModel);
			if(flag){
				resJson=new ResultJSON("COMMON_200");
				resJson.setBody(body);
			}
			log.info("NewsNoticeController.addNoticeInfo:公告添加成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("NOTICE_14102", "公告添加");
		}
		return resJson;
	}

	/**
	 *
	 * @Title: updateNotice
	 * @Description: 公告修改
	 * @param newsNoticeContentVo
	 * @return    参数
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException
	 */
	@RequestMapping("/updateNotice")
	@ResponseBody
	public ResultJSON updateNotice(@RequireValid NewsNoticeContentVo newsNoticeContentVo) throws BusinessException{
		NoticeModel noticeModel = null ;
		ResultJSON resJson = null ;
		JSONObject body = new JSONObject();
		try {
			//获取用户信息
			SysUserInfo sysUserInfo = sysUserService.selectByCode(newsNoticeContentVo.getUserCode()) ;
			noticeModel = convertVoToModel(newsNoticeContentVo,sysUserInfo,null);
			//查询条件
			boolean flag = newsNoticeContentService.updateNotice(noticeModel);
			if(flag){
				resJson=new ResultJSON("COMMON_200");
				resJson.setBody(body);
			}
			log.info("NewsNoticeController.updateNotice:公告修改成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("NOTICE_14102", "公告修改");
		}
		return resJson;
	}

	/**
	 *
	 * @Title: deleteNotice
	 * @Description: 公告删除
	 * @param newsNoticeDeleteVO
	 * @return    参数
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException
	 */
	@RequestMapping("/deleteNotice")
	@ResponseBody
	public ResultJSON deleteNotice(@RequireValid NewsNoticeDeleteVO newsNoticeDeleteVO) throws BusinessException{
		NoticeModel  noticeModel = null ;
		ResultJSON resJson = null ;
		JSONObject body = new JSONObject();
		try {
			String userCode = newsNoticeDeleteVO.getUserCode() ;
			//获取用户信息
			SysUserInfo sysUserInfo = sysUserService.selectByCode(userCode) ;
			noticeModel = new NoticeModel() ;
			noticeModel.setNoticeOperatorCode(userCode);
			noticeModel.setNoticeCode(newsNoticeDeleteVO.getNoticeCode());
			noticeModel.setNoticeOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
			noticeModel.setTenantCode(sysUserInfo.getTenantCode());
			noticeModel.setIsDelete(Integer.valueOf(newsNoticeDeleteVO.getIsDelete()));
			//查询条件
			boolean flag = newsNoticeContentService.deleteNotice(noticeModel);
			if(flag){
				resJson=new ResultJSON("COMMON_200");
				resJson.setBody(body);
			}
			log.info("NewsNoticeController.deleteNotice:公告删除成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("NOTICE_14102", "新闻删除");
		}
		return resJson;
	}

	/**
	 *
	 * @Title: getNoticeHistorys
 	 * @param newsNoticeHandlerVO
	 * @return    参数
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException
	 */
	@RequestMapping("/getNoticeHistorys")
	@ResponseBody
	public ResultJSON getNoticeHistorys(@RequireValid NewsNoticeHandlerVO newsNoticeHandlerVO) throws BusinessException{
		NoticeHistoryQuery noticeHistoryQuery=new NoticeHistoryQuery();
		ResultJSON resJson = null ;
		JSONObject json = null ;
		JSONArray jsonArray=new JSONArray();
		JSONObject body = new JSONObject();
		try {
			noticeHistoryQuery.setUserCode(newsNoticeHandlerVO.getUserCode());
			noticeHistoryQuery.setNoticeCode(newsNoticeHandlerVO.getNoticeCode());
			//查询条件
			List<NewsNoticeHistory> list=newsNoticeContentService.getNoticeHistorys(noticeHistoryQuery);
			//根据用户code列表获取用户集合
			if(list.size()>0){
				List<String> usercodes = new ArrayList<>();
				for (NewsNoticeHistory newsNoticeHistory : list) {
					usercodes.add(newsNoticeHistory.getNoticeOperatorCode());
				}
				List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
				Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
				for(int i=0 ; i<list.size() ; i++){
					json=new JSONObject();
					NewsNoticeHistory temp=list.get(i);
					json.put("noticeCode", temp.getNoticeCode());
					json.put("createTime", temp.getCreateTime());
					json.put("noticeOperatorCode", temp.getNoticeOperatorCode());
					SysUserInfo userInfo = userMap.get(temp.getNoticeOperatorCode());
					json.put("noticeOperatorName", userInfo == null ? "" : userInfo.getUserName());
					json.put("noticeStatus", temp.getNoticeStatus());
					json.put("noticeRemark", temp.getNoticeRemark());
					jsonArray.add(json) ;
				}
			}
			resJson=new ResultJSON("COMMON_200");
			body.put("list", jsonArray);
			resJson.setBody(body);
			log.info("NewsNoticeController.getNoticeHistorys:获取公告历程列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("NOTICE_14102", "获取公告历程列表");
		}
		return resJson;
	}

	/**
	 *
	 * @Title: addNoticeInfoHistory
	 * @Description: 公告审核
	 * @param newsNoticeAuditVo
	 * @return    参数
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException
	 */
	@RequestMapping("/addNoticeInfoHistory")
	@ResponseBody
	public ResultJSON addNoticeInfoHistory(@RequireValid NewsNoticeAuditVo newsNoticeAuditVo) throws BusinessException{
		NoticeModel noticeHistoryModel = null ;
		ResultJSON resJson = null ;
		JSONObject body = new JSONObject();
		try {
			//获取用户信息
			SysUserInfo sysUserInfo = sysUserService.selectByCode(newsNoticeAuditVo.getUserCode()) ;
			noticeHistoryModel = new NoticeModel() ;
			noticeHistoryModel.setUserCode(newsNoticeAuditVo.getUserCode());
			noticeHistoryModel.setNoticeCode(newsNoticeAuditVo.getNoticeCode());
			noticeHistoryModel.setNoticeStatus(Integer.valueOf(newsNoticeAuditVo.getNoticeStatus()));
			noticeHistoryModel.setNoticeRemark(newsNoticeAuditVo.getNoticeRemark());
			noticeHistoryModel.setNoticeOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
			noticeHistoryModel.setTenantCode(sysUserInfo.getTenantCode());
			//查询条件
			boolean flag = newsNoticeContentService.addNoticeInfoHistory(noticeHistoryModel);
			if(flag){
				SysUserInfo author = sysUserService.selectByCode(newsNoticeAuditVo.getNoticeCreatorCode());
				sendCreditMsg(author.getOrgCode(),author.getManageOrgInfo().getManOrgCode(),(byte)3,CreditConstants.COMMAND_NOTICE_CITYPUBLISH);
				resJson=new ResultJSON("COMMON_200");
				resJson.setBody(body);
			}
			log.info("NewsNoticeController.addNewsInfoHistory:公告审核操作成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("NOTICE_14102", "公告审核操作");
		}
		return resJson;
	}

	/**
	 *
	 * @Title: updateNoticeMove
	 * @Description: 公告上移或者下移
	 * @param newsNoticeMoveVo
	 * @return    参数
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException
	 */
	@RequestMapping("/updateNoticeMove")
	@ResponseBody
	public ResultJSON updateNoticeMove(@RequireValid NewsNoticeMoveVo newsNoticeMoveVo) throws BusinessException{
		NoticeModel noticeModel = null ;
		ResultJSON resJson = null ;
		JSONObject body = new JSONObject();
		try {
			//获取用户信息
			SysUserInfo sysUserInfo = sysUserService.selectByCode(newsNoticeMoveVo.getUserCode()) ;
			noticeModel = new NoticeModel();
			noticeModel.setNoticeCode(newsNoticeMoveVo.getNoticeCode());
			noticeModel.setUserCode(newsNoticeMoveVo.getUserCode());
			noticeModel.setNoticeOperatorCode(newsNoticeMoveVo.getUserCode());
			noticeModel.setMoveType(Integer.valueOf(newsNoticeMoveVo.getMoveType()));
			noticeModel.setNoticeOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
			noticeModel.setTenantCode(sysUserInfo.getTenantCode());
			noticeModel.setNoticeSort(Integer.valueOf(newsNoticeMoveVo.getNoticeSort()));
			if(null != newsNoticeMoveVo.getNoticeTitle() && !"".equals(newsNoticeMoveVo.getNoticeTitle())){
				noticeModel.setNoticeTitle(newsNoticeMoveVo.getNoticeTitle());
			}
			if(null != newsNoticeMoveVo.getNoticeStatus()&& !"".equals(newsNoticeMoveVo.getNoticeStatus())){
				noticeModel.setNoticeStatus(Integer.valueOf(newsNoticeMoveVo.getNoticeStatus()));
			}
			if(null != newsNoticeMoveVo.getStartTime() && !"".equals(newsNoticeMoveVo.getStartTime())){
				noticeModel.setStartTime(Long.valueOf(newsNoticeMoveVo.getStartTime()));
			}
			if(null != newsNoticeMoveVo.getEndTime() && !"".equals(newsNoticeMoveVo.getEndTime())){
				noticeModel.setEndTime(Long.valueOf(newsNoticeMoveVo.getEndTime()));
			}

			//查询条件
			boolean flag = newsNoticeContentService.updateNoticeMove(noticeModel);
			if(flag){
				resJson=new ResultJSON("COMMON_200");
				resJson.setBody(body);
			}
			log.info("NewsNoticeController.updateNoticeMove:公告移动成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("NOTICE_14102", "公告移动");
		}
		return resJson;
	}

	/**
	 *
	 * @Title: updateNoticeHidden
	 * @Description: 公告隐藏或取消隐藏
	 * @param newsNoticeHiddenVo
	 * @return    参数
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException
	 */
	@RequestMapping("/updateNoticeHidden")
	@ResponseBody
	public ResultJSON updateNoticeHidden(@RequireValid NewsNoticeHiddenVo newsNoticeHiddenVo) throws BusinessException{
		NoticeModel noticeModel = null ;
		ResultJSON resJson = null ;
		JSONObject body = new JSONObject();
		String hiddenStatus = "隐藏";
		try {
			if(null != newsNoticeHiddenVo.getIsHidden() && Integer.valueOf(newsNoticeHiddenVo.getIsHidden()) ==0 ){
				hiddenStatus="取消隐藏" ;
			}
			//获取用户信息
			SysUserInfo sysUserInfo = sysUserService.selectByCode(newsNoticeHiddenVo.getUserCode()) ;
			noticeModel = new NoticeModel();
			noticeModel.setNoticeCode(newsNoticeHiddenVo.getNoticeCode());
			noticeModel.setUserCode(newsNoticeHiddenVo.getUserCode());
			noticeModel.setNoticeOperatorCode(newsNoticeHiddenVo.getUserCode());
			noticeModel.setIsHidden(Integer.valueOf(newsNoticeHiddenVo.getIsHidden()));
			noticeModel.setNoticeOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
			noticeModel.setTenantCode(sysUserInfo.getTenantCode());
			//查询条件
			boolean flag = newsNoticeContentService.updateNewsNoticeHidden(noticeModel);
			if(flag){
				resJson=new ResultJSON("COMMON_200");
				resJson.setBody(body);
			}
			log.info("NewsNoticeController.updateNoticeHidden:公告"+hiddenStatus+"成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("NOTICE_14102", "公告"+hiddenStatus);
		}
		return resJson;
	}

	/**
	 *
	 * @Title: updateNewsNoticeUp
	 * @Description: 公告置顶
	 * @param newsNoticeTopVo
	 * @return    参数
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException
	 */
	@RequestMapping("/updateNewsNoticeUp")
	@ResponseBody
	public ResultJSON updateNewsNoticeUp(@RequireValid NewsNoticeTopVo newsNoticeTopVo) throws BusinessException{
		NoticeModel noticeModel = null ;
		ResultJSON resJson = null ;
		JSONObject body = new JSONObject();
		String topStatus = "置顶";
		try {
			if( null != newsNoticeTopVo.getIsTop() &&Integer.valueOf(newsNoticeTopVo.getIsTop()) ==0 ){
				topStatus="取消置顶" ;
			}
			//获取用户信息
			SysUserInfo sysUserInfo = sysUserService.selectByCode(newsNoticeTopVo.getUserCode()) ;
			noticeModel = new NoticeModel();
			noticeModel.setNoticeCode(newsNoticeTopVo.getNoticeCode());
			noticeModel.setUserCode(newsNoticeTopVo.getUserCode());
			noticeModel.setNoticeOperatorCode(newsNoticeTopVo.getUserCode());
			noticeModel.setIsTop(Integer.valueOf(newsNoticeTopVo.getIsTop()));
			noticeModel.setNoticeOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
			noticeModel.setTenantCode(sysUserInfo.getTenantCode());
			//查询条件
			boolean flag = newsNoticeContentService.updateNewsNoticeUp(noticeModel);
			if(flag){
				resJson=new ResultJSON("COMMON_200");
				resJson.setBody(body);
			}
			log.info("NewsNoticeController.updateNewsNoticeUp:公告"+topStatus+"成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("NOTICE_14102", "公告"+topStatus);
		}
		return resJson;
	}

	/**
	 *
	 * @Title: updateNewsNoticeRepeal
	 * @Description: 公告撤销、提交审核
	 * @param newsNoticeRepealVo
	 * @return    参数
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException
	 */
	@RequestMapping("/updateNewsNoticeRepeal")
	@ResponseBody
	public ResultJSON updateNewsNoticeRepeal(@RequireValid NewsNoticeRepealVo newsNoticeRepealVo) throws BusinessException{
		NoticeModel noticeModel = null ;
		ResultJSON resJson = null ;
		JSONObject body = new JSONObject();
		try {
			//获取用户信息
			SysUserInfo sysUserInfo = sysUserService.selectByCode(newsNoticeRepealVo.getUserCode()) ;
			noticeModel = new NoticeModel();
			noticeModel.setNoticeCode(newsNoticeRepealVo.getNoticeCode());
			noticeModel.setUserCode(newsNoticeRepealVo.getUserCode());
			noticeModel.setNoticeStatus(Integer.valueOf(newsNoticeRepealVo.getNoticeStatus()));
			noticeModel.setNoticeOperatorCode(newsNoticeRepealVo.getUserCode());
			noticeModel.setNoticeOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
			noticeModel.setTenantCode(sysUserInfo.getTenantCode());
			//查询条件
			boolean flag = newsNoticeContentService.updateNewsNoticeRepeal(noticeModel);
			if(flag){
				resJson=new ResultJSON("COMMON_200");
				resJson.setBody(body);
			}
			log.info("NewsNoticeController.updateNewsNoticeRepeal:公告撤销成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("NOTICE_14102", "公告撤销");
		}
		return resJson;
	}

	private NoticeModel convertVoToModel(NewsNoticeContentVo newsNoticeContentVo,SysUserInfo sysUserInfo,String createUserCode) {
		NoticeModel noticeModel = new NoticeModel() ;
		if(null !=newsNoticeContentVo.getNoticeCode() && !"".equals(newsNoticeContentVo.getNoticeCode())){
			noticeModel.setNoticeCode(newsNoticeContentVo.getNoticeCode());
		}
		noticeModel.setUserCode(newsNoticeContentVo.getUserCode());
		if(null != createUserCode) {
			noticeModel.setNoticeCreatorCode(createUserCode);
		}else{
			noticeModel.setNoticeCreatorCode(newsNoticeContentVo.getNoticeCreatorCode());
		}
		noticeModel.setNoticeOperatorCode(newsNoticeContentVo.getUserCode());
		noticeModel.setNoticeTitle(newsNoticeContentVo.getNoticeTitle());
		noticeModel.setNoticeContentHtml(newsNoticeContentVo.getNoticeContentHtml());
		noticeModel.setNoticeContentText(newsNoticeContentVo.getNoticeContentText());
		noticeModel.setNoticeStatus(Integer.valueOf(newsNoticeContentVo.getNoticeStatus()));
		noticeModel.setNoticeRemark(newsNoticeContentVo.getNoticeRemark());
		noticeModel.setNoticeOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
		noticeModel.setNoticeFileUrl(newsNoticeContentVo.getNoticeFileUrl());
		noticeModel.setTenantCode(sysUserInfo.getTenantCode());
		return noticeModel ;
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


	/**
	 *
	 * @Title: sendCreditMsg
	 * @Description: 	发送积分信息
	 * @param accountCode	账号CODE
	 * @param orgCode 		组织CODE
	 * @param accountType	账号类型
	 * @param actionCode		动作类型
	 * @return void    返回类型
	 */
	@SuppressWarnings("unchecked")
	protected void sendCreditMsg(String accountCode,String orgCode,Byte accountType,String actionCode){
		MessageIntegral msg = new MessageIntegral();
		msg.setAccountCode(accountCode);
		msg.setActionCode(actionCode);
		msg.setAccountType(accountType);
		msg.setManageOrgCode(orgCode);
		producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(msg));
	}


}