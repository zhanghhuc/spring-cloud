package com.zssq.activity.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.activity.proc.DynamicActivityProcedure;
import com.zssq.activity.vo.GetAwardeeListVo;
import com.zssq.activity.vo.GetCommentFixedListVo;
import com.zssq.activity.vo.GetPortalActivityListVo;
import com.zssq.activity.vo.GetReplyFixedListVo;
import com.zssq.activity.vo.JoinActivityVo;
import com.zssq.activity.vo.QueryActivityDetailVo;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.ActivityConstants;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.VoteConstants;
import com.zssq.dao.pojo.ActivityComment;
import com.zssq.dao.pojo.ActivityCommentReply;
import com.zssq.dao.pojo.ActivityInfo;
import com.zssq.dao.pojo.ActivityInfoWithBLOBs;
import com.zssq.dao.pojo.ActivityJoin;
import com.zssq.dao.pojo.ActivityJoinAdjunct;
import com.zssq.dao.pojo.ActivityPrize;
import com.zssq.dao.pojo.ActivityResource;
import com.zssq.dao.pojo.ActivityWinningRecord;
import com.zssq.dao.pojo.OrgLinkList;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IActivityManageService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageInfo;
import com.zssq.utils.StringTools;
import com.zssq.vote.controller.BaseController;

/**
 * 
 * @ClassName: ActivityTwoController  
 * @Description: 活动门户端controller
 * @author liuzhijie  
 * @date 2017年4月21日  
 *
 */
@RequestMapping("/activity")
@Controller
public class ActivityTwoController extends BaseController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IActivityManageService activityManageService;
	@Autowired
	private DynamicActivityProcedure dap;
	
	/**
	 * 
	 * @Title: getPortalActivityList  
	 * @Description: 获取门户页面活动列表
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getPortalActivityList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getPortalActivityList(@RequireValid GetPortalActivityListVo vo) throws BusinessException{
		try {
			ActivityInfo activity = new ActivityInfo();
			activity.setOrgCode(vo.getProtalCode());
			List<ActivityInfo> list = activityManageService.getPortalActivityList(activity, vo.getPageSize());
			//组织返回数据
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonAry = new JSONArray();
			if(list.size() > 0){
				for(ActivityInfo h : list){
					JSONObject o = new JSONObject();
					o.put("activityId", StringTools.formatToString(h.getId()));
					o.put("activityCode", StringTools.formatToString(h.getCode()));
					o.put("portalCode", StringTools.formatToString(h.getOrgCode()));
					o.put("activityName", StringTools.formatToString(h.getActivityName()));
					o.put("activityType", StringTools.formatToString(h.getActivityType()));
					o.put("activityStatus", StringTools.formatToString(h.getActivityStatus()));
					o.put("mainUrl", StringTools.formatToString(h.getMainUrl()));
					o.put("preheatUrl", StringTools.formatToString(h.getPreheatUrl()));
					jsonAry.add(o);
				}
			}
			jsonObj.put("activityList", jsonAry);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jsonObj);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityTwoController.getPortalActivityList", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	/**
	 * 
	 * @Title: queryActivityDetail  
	 * @Description: 查询活动详情
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/queryActivityDetail",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON queryActivityDetail(@RequireValid QueryActivityDetailVo vo) throws BusinessException{
		try {
			//用户信息
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			ActivityInfoWithBLOBs activity = activityManageService.queryActivityDetail(vo.getActivityCode(),
					vo.getActivityType(),vo.getUserCode(),userInfo.getTenantCode());
			//组织返回数据
			JSONObject jsonObj = new JSONObject();
			
			JSONArray jsonAryPrize = new JSONArray();
			JSONArray jsonAryResource = new JSONArray();
			
			List<ActivityPrize> prizeList = activity.getPrizes();
			List<ActivityResource> resourceList = activity.getResources();
			if(prizeList.size() > 0){//奖项列表
				for(ActivityPrize h : prizeList){
					JSONObject o = new JSONObject();
					o.put("prizeName", StringTools.formatToString(h.getPrizeName()));
					o.put("isJoinPrize", StringTools.formatToString(h.getIsJoinPrize()));
					o.put("sequenceNo", StringTools.formatToString(h.getSequenceNo()));
					o.put("prizeIntro", StringTools.formatToString(h.getIntroduce()));
					o.put("rule", StringTools.formatToString(h.getRule()));
					o.put("isRewardGold", StringTools.formatToString(h.getIsRewardGold()));
					o.put("rewardGoldNum", StringTools.formatToString(h.getRewardGoldNum()));
					o.put("isAwardHonor", StringTools.formatToString(h.getIsAwardHonor()));
					o.put("awardHonorCode", StringTools.formatToString(h.getAwardHonorCode()));
					o.put("awardHonorName", StringTools.formatToString(h.getAwardHonorName()));
					o.put("awardHonorUrl", StringTools.formatToString(h.getAwardHonorUrl()));
					jsonAryPrize.add(o);
				}
			}
			if(resourceList.size() > 0){//资源列表
				for(ActivityResource h : resourceList){
					JSONObject o = new JSONObject();
					o.put("type", StringTools.formatToString(h.getType()));
					o.put("url", StringTools.formatToString(h.getUrl()));
					o.put("sequenceNo", StringTools.formatToString(h.getSequenceNo()));
					jsonAryResource.add(o);
				}
			}
			jsonObj.put("prizeList", jsonAryPrize);
			jsonObj.put("resourceList", jsonAryResource);
			
			jsonObj.put("activityId", activity.getId());
			jsonObj.put("activityCode", activity.getCode());
			jsonObj.put("activityName", activity.getActivityName());
			jsonObj.put("activityStatus", activity.getActivityStatus());
			jsonObj.put("introduce", activity.getIntroduce());
			jsonObj.put("activityRule", activity.getActivityRule());
			jsonObj.put("joinNum", activity.getJoinNum());
			jsonObj.put("shareNum", activity.getShareNum());
			jsonObj.put("commentNum", activity.getCommentNum());
			jsonObj.put("praiseNum", activity.getPraiseNum());
			jsonObj.put("collectionNum", activity.getCollectionNum());
			if (StringTools.isNotEmpty(activity.getCollectionCode())) {
				jsonObj.put("isCollection", StringTools.formatToString(VoteConstants.YES));
			} else {
				jsonObj.put("isCollection", StringTools.formatToString(VoteConstants.NO));
			}
			if (StringTools.isNotEmpty(activity.getPraiseCode())) {
				jsonObj.put("isPraise", StringTools.formatToString(VoteConstants.YES));
			} else {
				jsonObj.put("isPraise", StringTools.formatToString(VoteConstants.NO));
			}
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jsonObj);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityTwoController.queryActivityDetail", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	 * @Title: getActivityList  
	 * @Description: 查询活动列表
	 * @param vo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getActivityList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getActivityList(@RequireValid GetPortalActivityListVo vo) throws BusinessException{
		try {
			
			//用户信息
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			//组织参数
			ActivityInfo activity = new ActivityInfo();
			activity.setOrgCode(vo.getProtalCode());
			activity.setCreateUserCode(vo.getUserCode());
			activity.setTenantCode(userInfo.getTenantCode());
			PageInfo page;
			if(StringTools.isEmpty(vo.getId())){//加载更多时的最后一条记录id
				page = new PageInfo(1, Integer.parseInt(vo.getPageSize()));
			} else {
				page = new PageInfo(new Integer(vo.getId()), Integer.parseInt(vo.getPageSize()));
			}
			List<ActivityInfo> list = activityManageService.getActivityList(activity, page);
			//组织返回数据
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonAry = new JSONArray();
			if(list.size() > 0){
				for(ActivityInfo h : list){
					JSONObject o = new JSONObject();
					o.put("id", StringTools.formatToString(h.getId()));
					o.put("code", StringTools.formatToString(h.getCode()));
					o.put("activityNo", StringTools.formatToString(h.getActivityNo()));
					o.put("activityType", StringTools.formatToString(h.getActivityType()));
					o.put("activityName", StringTools.formatToString(h.getActivityName()));
					o.put("activityStatus", StringTools.formatToString(h.getActivityStatus()));
					o.put("mainUrl", StringTools.formatToString(h.getMainUrl()));
					o.put("preheatUrl", StringTools.formatToString(h.getPreheatUrl()));
					o.put("preheatTime", StringTools.formatToString(h.getPreheatTime()));
					o.put("startTime", StringTools.formatToString(h.getStartTime()));
					o.put("endTime", StringTools.formatToString(h.getEndTime()));
					o.put("createTime", StringTools.formatToString(h.getCreateTime()));
					o.put("joinNum", StringTools.formatToString(h.getJoinNum()));
					o.put("shareNum", StringTools.formatToString(h.getShareNum()));
					o.put("commentNum", StringTools.formatToString(h.getCommentNum()));
					o.put("praiseNum", StringTools.formatToString(h.getPraiseNum()));
					o.put("collectionNum", StringTools.formatToString(h.getCollectionNum()));
					if (StringTools.isNotEmpty(h.getCollectionCode())) {
						o.put("isCollection", StringTools.formatToString(VoteConstants.YES));
					} else {
						o.put("isCollection", StringTools.formatToString(VoteConstants.NO));
					}
					if (StringTools.isNotEmpty(h.getPraiseCode())) {
						o.put("isPraise", StringTools.formatToString(VoteConstants.YES));
					} else {
						o.put("isPraise", StringTools.formatToString(VoteConstants.NO));
					}
					jsonAry.add(o);
				}
			}
			jsonObj.put("activityList", jsonAry);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jsonObj);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityTwoController.getActivityList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	 * @Title: joinActivity  
	 * @Description: 参加活动
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/joinActivity",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON joinActivity(@RequireValid JoinActivityVo vo) throws BusinessException{
		try {
			ActivityJoin join = new ActivityJoin();
			ActivityJoinAdjunct adjunct = new ActivityJoinAdjunct();
			//用户信息
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			// 获取用户所在的组织机构树信息
			String orgType = userInfo.getManageOrgInfo().getSysOrgType();
			OrgLinkList orgLinkList = getOrgTreeByAnyOrgCode(orgType, orgCode);
			String userGCode = orgLinkList.getTopOrg().getSysOrgCode();
			String userPCode = "";
			String userCCode = "";
			if (orgLinkList.getProList() != null && orgLinkList.getProList().size() == 1) {
				SysOrgInfo province = orgLinkList.getProList().get(0);
				userPCode = province.getSysOrgCode();
				userPCode = userPCode == null ? "" : userPCode;
			}
			if (orgLinkList.getCityList() != null && orgLinkList.getCityList().size() == 1) {
				SysOrgInfo city = orgLinkList.getCityList().get(0);
				userCCode = city.getSysOrgCode();
				userCCode = userCCode == null ? "" : userCCode;
			}
			// 获取用户所在的班组CODE列表
			List<String> userTCodes = new ArrayList<String>();
			
			boolean flag = activityManageService.judgeAuthority(vo.getActivityCode(), userGCode, userPCode, userCCode, userTCodes);
			if(!flag){
				// 无权参与此活动
				throw BusinessException.build("COMMON_403");
			}
			//参与记录对象参数
			join.setCode(UUIDHelper.getUUID());
			join.setActivityCode(vo.getActivityCode());
			join.setJoinUserCode(vo.getUserCode());
			join.setTenantCode(tenantCode);
			join.setOrgCode(orgCode);
			//作品参数
			adjunct.setFileName(vo.getFileName());
			adjunct.setFileUrl(vo.getFileUrl());
			adjunct.setFileType(Byte.valueOf(vo.getFileType()));
			activityManageService.insertJoinActivity(adjunct, join);
			//发放参与奖
			giveJoinPrize(join, userInfo, vo.getActivityCode());
			
			/**发送"参与一次活动"行为信息*/
			noticeAction(orgCode, CreditConstants.COMMAND_ACTIVITY_JOIN, vo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			
			//参与活动动态
			dap.publishDynamicOfJoin(tenantCode, join.getOrgCode(), vo.getUserCode(), vo.getActivityCode());
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityTwoController.joinActivity", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	 * @Title: giveJoinPrize
	 * @Description: 发送参与奖
	 * @param userInfo
	 * @param activityCode
	 * @throws Exception 
	 */
	private void giveJoinPrize(ActivityJoin join, SysUserInfo userInfo, String activityCode) throws Exception{
		//活动信息
		ActivityInfo activity = activityManageService.getActivityInfoByCode(userInfo.getTenantCode(), activityCode);
		//活动奖项列表
		ActivityPrize prize = new ActivityPrize();
		prize.setActivityCode(activityCode);
		prize.setTenantCode(userInfo.getTenantCode());
		prize = activityManageService.getJoinPrize(prize);
		//判断是否有参与奖
		if(null != prize){
			Long now = DateUtils.getFormatDateLong();
			ActivityWinningRecord win = new ActivityWinningRecord();
			win.setCode(UUIDHelper.getUUID());
			win.setTenantCode(userInfo.getTenantCode());
			win.setOrgCode(userInfo.getManageOrgInfo().getSysOrgCode());
			win.setCreateTime(now);
			win.setModifyTime(now);
			win.setActivityCode(activityCode);
			win.setJoinUserCode(userInfo.getUserCode());
			win.setActivityJoinCode(join.getCode());
			win.setActivityPrizeCode(prize.getCode());
			win.setPrizeName(prize.getPrizeName());
			win.setAwardStatus(ActivityConstants.NO);
			win.setIsJoinPrize(ActivityConstants.YES);
			int count = activityManageService.insertWinningRecord(win);
			if(count > 0){//插入成功
				if(ActivityConstants.YES.equals(prize.getIsAwardHonor())){//颁发荣誉
					prize.setAccepterCode(userInfo.getUserCode());//活动参与者
					prize.setActivityName(activity.getActivityName());
					prize.setOrgCode(userInfo.getManageOrgInfo().getSysOrgCode());
					awardHonorForActivity(prize, activity.getCreateUserCode());
				}
				if(ActivityConstants.YES.equals(prize.getIsRewardGold())){//奖励金币
					prize.setAccepterCode(userInfo.getUserCode());//活动参与者
					prize.setActivityName(activity.getActivityName());
					prize.setOrgCode(userInfo.getManageOrgInfo().getSysOrgCode());
					increaseCoinAccount(userInfo.getUserCode(), userInfo.getManageOrgInfo().getSysOrgCode(), prize.getRewardGoldNum());
				}
				ActivityWinningRecord winning = new ActivityWinningRecord();
				winning.setCode(win.getCode());
				winning.setTenantCode(win.getTenantCode());
				winning.setAwardStatus(ActivityConstants.YES);//修改奖品已经发放
				winning.setModifyTime(DateUtils.getFormatDateLong());
				activityManageService.updateWinningRecord(winning);
			}
		}
	}
	
	/**
	 * 
	 * @Title: getAwardeeList  
	 * @Description: 查询获奖人列表
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getAwardeeList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getAwardeeList(@RequireValid GetAwardeeListVo vo) throws BusinessException{
		try {
			List<ActivityWinningRecord> list = activityManageService.getAwardeeList(vo.getActivityCode());
			
			//组织返回数据
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonAry = new JSONArray();
			if(list.size() > 0){
				for(ActivityWinningRecord h : list){
					JSONObject o = new JSONObject();
					o.put("id", StringTools.formatToString(h.getId()));
					o.put("code", StringTools.formatToString(h.getCode()));
					o.put("activityCode", StringTools.formatToString(h.getActivityCode()));
					o.put("awardeeCode", StringTools.formatToString(h.getJoinUserCode()));
					o.put("awardeeOrgCode", StringTools.formatToString(h.getOrgCode()));
					SysUserInfo userInfo = getUserInfo(h.getJoinUserCode());
					if(userInfo != null){
						o.put("awardeeName", StringTools.formatToString(userInfo.getUserName()));
						o.put("awardeeHeadUrl", StringTools.formatToString(userInfo.getHeadPortrait()));
						o.put("awardeeOrgName", StringTools.formatToString(userInfo.getManageOrgInfo().getSysOrgName()));
					}else{
						o.put("awardeeName", "");
						o.put("awardeeHeadUrl", "");
						o.put("awardeeOrgName", "");
					}
					o.put("winningWorkName", StringTools.formatToString(h.getPrizeWorkName()));
					o.put("prizeCode", StringTools.formatToString(h.getActivityPrizeCode()));
					o.put("prizeName", StringTools.formatToString(h.getPrizeName()));
					jsonAry.add(o);
				}
			}
			jsonObj.put("awardeeList", jsonAry);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jsonObj);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityTwoController.getAwardeeList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function getCommentFixedList
	 * @Description 查询活动评论列表（定位）
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "getCommentFixedList",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getCommentFixedList(@RequireValid GetCommentFixedListVo inVo) throws BusinessException{
		try{
			ActivityComment activityComment = new ActivityComment();
			activityComment.setActivityCode(inVo.getActivityCode());
			activityComment.setCode(inVo.getCommentCode());
			activityComment.setPageSize(Integer.valueOf(inVo.getPageSize()));
			if(StringTools.isNotEmpty(inVo.getId())){
				activityComment.setId(Long.valueOf(inVo.getId()));
			}
			//点赞人
			activityComment.setAdmirerCode(inVo.getUserCode());
			//根据userCode 查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			activityComment.setTenantCode(tenantCode);
			activityComment.setOrgCode(orgCode);
			
			PageInfo pageInfo = activityManageService.getActivityCommentFixedList(activityComment);
			List<ActivityComment> list = pageInfo.getList();
			JSONArray jsonArray = new JSONArray();
				for (ActivityComment ac : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", StringTools.formatToString(ac.getId()));
					jsonObject.put("activityCode", ac.getActivityCode());
					jsonObject.put("commentCode", ac.getCode());
					jsonObject.put("commenterCode",ac.getCommenterCode());
					//根据 commenterCode  查询 commenterName commenterUrl commenterOrg
					SysUserInfo commenterInfo = getUserInfo(ac.getCommenterCode());
					if(commenterInfo != null){
						jsonObject.put("commenterName", commenterInfo.getUserName());
						jsonObject.put("commenterUrl",StringTools.formatToString(commenterInfo.getHeadPortrait()));
						jsonObject.put("commenterOrg", commenterInfo.getManageOrgInfo().getSysOrgCode());
					}else{
						jsonObject.put("commenterName", "");
						jsonObject.put("commenterUrl","");
						jsonObject.put("commenterOrg", "");
					}
					jsonObject.put("content", StringTools.formatToString(ac.getContent()));
					jsonObject.put("createTime", StringTools.formatToString(ac.getCreateTime()));
					jsonObject.put("replyCount", StringTools.formatToString(ac.getReplyCount()));
					jsonObject.put("praiseCount", StringTools.formatToString(ac.getPraiseCount()));
					if(null != ac.getAdmirerCode()){
						jsonObject.put("isPraise", StringTools.formatToString(ActivityConstants.YES));
					}else{
						jsonObject.put("isPraise", StringTools.formatToString(ActivityConstants.NO));
					}
					jsonArray.add(jsonObject);
				}
			
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("commentList", jsonArray);
			jsonObject.put("totalCount", StringTools.formatToString(pageInfo.getTotalItem()));
			result.setBody(jsonObject);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("ActivityModelThreeController.getCommentFixedList", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}
	
	
	/**
	 * @Function getReplyFixedList
	 * @Description 查询评论的回复列表（定位）
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "getReplyFixedList", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getReplyFixedList(@RequireValid GetReplyFixedListVo inVo) throws BusinessException{
		
		try{
			ActivityCommentReply activityCommentReply = new ActivityCommentReply();
			activityCommentReply.setActivityCode(inVo.getActivityCode());
			activityCommentReply.setCommentCode(inVo.getCommentCode());
			activityCommentReply.setCode(inVo.getReplyCode());
			activityCommentReply.setPageSize(Integer.valueOf(inVo.getPageSize()));
			if(StringTools.isNotEmpty(inVo.getId())){
				activityCommentReply.setId(Long.valueOf(inVo.getId()));
			}
			//点赞人
			activityCommentReply.setAdmirerCode(inVo.getUserCode());
			// 根据userCode 查询tenant_code(租户标识)、org_code(组织机构编码)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			activityCommentReply.setTenantCode(tenantCode);
			activityCommentReply.setOrgCode(orgCode);
			
			PageInfo pageInfo = activityManageService.getActivityeReplyFixedList(activityCommentReply);
			List<ActivityCommentReply> list = pageInfo.getList();
			JSONArray jsonArray = new JSONArray();
				for (ActivityCommentReply acr : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", StringTools.formatToString(acr.getId()));
					jsonObject.put("activityCode", acr.getActivityCode());
					jsonObject.put("commentCode", acr.getCommentCode());
					jsonObject.put("replyCode", acr.getCode());
					jsonObject.put("replierCode", acr.getReplierCode());
					// 根据 replierCode  查询 replierName replierUrl replierOrg
					SysUserInfo replierInfo = getUserInfo(acr.getReplierCode());
					if(replierInfo != null){
						jsonObject.put("replierName", replierInfo.getUserName());
						jsonObject.put("replierUrl",StringTools.formatToString(replierInfo.getHeadPortrait()));
						jsonObject.put("replierOrg", replierInfo.getManageOrgInfo().getSysOrgCode());
					}else{
						jsonObject.put("replierName", "");
						jsonObject.put("replierUrl", "");
						jsonObject.put("replierOrg", "");
					}
					jsonObject.put("questionerCode", acr.getQuestionerCode());
					// 根据 questionerCode  查询 questionerName questionerUrl questionerOrg
					SysUserInfo questionerInfo = getUserInfo(acr.getQuestionerCode());
					if(questionerInfo != null ){
						jsonObject.put("questionerName", questionerInfo.getUserName());
						jsonObject.put("questionerUrl",StringTools.formatToString(questionerInfo.getHeadPortrait()));
						jsonObject.put("questionerOrg", questionerInfo.getManageOrgInfo().getSysOrgCode());
					}else{
						jsonObject.put("questionerName", "");
						jsonObject.put("questionerUrl", "");
						jsonObject.put("questionerOrg", "");
					}
					jsonObject.put("content", StringTools.formatToString(acr.getContent()));
					jsonObject.put("createTime", StringTools.formatToString(acr.getCreateTime()));
					jsonObject.put("praiseCount", StringTools.formatToString(acr.getPraiseCount()));
					if(null != acr.getAdmirerCode()){
						jsonObject.put("isPraise", StringTools.formatToString(VoteConstants.YES));
					}else{
						jsonObject.put("isPraise", StringTools.formatToString(VoteConstants.NO));
					}
					jsonArray.add(jsonObject);
				}
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("replyList", jsonArray);
			jsonObject.put("surplusCount", StringTools.formatToString(pageInfo.getTotalItem()));
			result.setBody(jsonObject);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("ActivityModelThreeController.getReplyFixedList", e);
			throw BusinessException.build("COMMON_400");
		} 
		
	}
}
