package com.zssq.activity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.activity.vo.AwardPirzeForWinWorksVo;
import com.zssq.activity.vo.GetCommentFixedListVo;
import com.zssq.activity.vo.GetPrizeListVo;
import com.zssq.activity.vo.GetReplyFixedListVo;
import com.zssq.activity.vo.SettingPrizeForWorksVo;
import com.zssq.activity.vo.SettingWinForWorksVo;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.ActivityConstants;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.VoteConstants;
import com.zssq.dao.pojo.ActivityComment;
import com.zssq.dao.pojo.ActivityCommentReply;
import com.zssq.dao.pojo.ActivityJoin;
import com.zssq.dao.pojo.ActivityPrize;
import com.zssq.dao.pojo.ActivityWinningRecord;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IActivityManageService;
import com.zssq.utils.PageInfo;
import com.zssq.utils.StringTools;
import com.zssq.vote.controller.BaseController;

/**
 * @ClassName ActivityModelThreeController
 * @Description 活动模块管理端Controller
 * @author LiuYunLong
 * @date 2017年4月19日 下午3:41:00
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/activity")
public class ActivityModelThreeController extends BaseController{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IActivityManageService activityManageService;
	
	
	/**
	 * @Function settingWinForWorks
	 * @Description 设为/撤销获奖作品
	 * @param inVo 
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "settingWinForWorks",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON settingWinForWorks(@RequireValid SettingWinForWorksVo inVo)throws BusinessException{
		
		try{
			// 根据userCode信息查询tenant_code(租户标识)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			ActivityJoin activityJoin = new ActivityJoin();
			activityJoin.setTenantCode(tenantCode);
			activityJoin.setActivityCode(inVo.getActivityCode());
			activityJoin.setCode(inVo.getJoinCode());
			if(ActivityConstants.WINNING_TYPE_1.equals(inVo.getOperateType())){
				activityJoin.setIsWinning(ActivityConstants.YES);
			}
			if(ActivityConstants.WINNING_TYPE_2.equals(inVo.getOperateType())){
				activityJoin.setIsWinning(ActivityConstants.NO);
			}
			activityManageService.updateIsWinning(activityJoin);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("ActivityControllerByGl.settingWinForWorks",e);
			throw BusinessException.build("COMMON_400");
		}
		
	}
	
	
	/**
	 * @Function getPrizeList
	 * @Description 查询活动奖项列表
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "getPrizeList",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getPrizeList(@RequireValid GetPrizeListVo inVo)throws BusinessException{
		
		try{
			// 根据userCode信息查询tenant_code(租户标识)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			ActivityPrize activityPrize = new ActivityPrize();
			activityPrize.setActivityCode(inVo.getActivityCode());
			activityPrize.setTenantCode(tenantCode);
			List<ActivityPrize> prizeList = activityManageService.getPrizeList(activityPrize);
			
			JSONArray jsonArray = new JSONArray();
			if(null != prizeList && prizeList.size() > 0){
				for (ActivityPrize ap : prizeList) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("prizeCode", ap.getCode());
					jsonObject.put("activityCode", ap.getActivityCode());
					jsonObject.put("isJoinPrize", StringTools.formatToString(ap.getIsJoinPrize()));
					jsonObject.put("prizeName", StringTools.formatToString(ap.getPrizeName()));
					jsonObject.put("sequenceNo", StringTools.formatToString(ap.getSequenceNo()));
					jsonArray.add(jsonObject);
				}
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("prizeList", jsonArray);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jsonObject);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("ActivityModelThreeController.getPrizeList",e);
			throw BusinessException.build("COMMON_400");
		}
		
	}
	
	/**
	 * @Function settingPrizeForWorks
	 * @Description 为获奖作品设定奖项
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "settingPrizeForWorks",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON settingPrizeForWorks(@RequireValid SettingPrizeForWorksVo inVo)throws BusinessException{
		
		try{
			// 根据userCode信息查询tenant_code(租户标识)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			ActivityWinningRecord activityWinningRecord = new ActivityWinningRecord();
			activityWinningRecord.setTenantCode(tenantCode);
			activityWinningRecord.setOrgCode(orgCode);
			activityWinningRecord.setActivityCode(inVo.getActivityCode());
			activityWinningRecord.setActivityJoinCode(inVo.getJoinCode());
			activityWinningRecord.setJoinUserCode(inVo.getJoinUserCode());
			activityWinningRecord.setActivityPrizeCode(inVo.getPrizeCode());
			
			activityManageService.addPrizeForWorks(activityWinningRecord);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("ActivityModelThreeController.settingPrizeForWorks",e);
			throw BusinessException.build("COMMON_400");
		}
		
	}
	

	/**
	 * @Function awardPirzeForWinWorks
	 * @Description 颁发奖励
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "awardPirzeForWinWorks",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON awardPirzeForWinWorks(@RequireValid AwardPirzeForWinWorksVo inVo)throws BusinessException{
		
		try{
			// 根据userCode信息查询tenant_code(租户标识) orgCode 
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			/**1.批量更新奖品发放状态为1-是*/
			String[] strs = inVo.getJoinCode().split("\\|");
			List<String> joinCodes = new ArrayList<String>();
			for (String string : strs) {
				if (StringTools.isNotEmpty(string)) {
					joinCodes.add(string);
				}
			}
			if (joinCodes.size() > 0) {
				activityManageService.updateAwardStatusWithBatch(tenantCode, inVo.getActivityCode(), joinCodes);
				/**2.查询非参与奖奖品列表*/
				Map<String, ActivityPrize> prizeMap = new HashMap<String, ActivityPrize>();
				
				ActivityPrize activityPrize = new ActivityPrize();
				activityPrize.setTenantCode(tenantCode);
				activityPrize.setActivityCode(inVo.getActivityCode());
				activityPrize.setIsJoinPrize(ActivityConstants.NO);   //是否为参与奖   0:否(默认) 1:是
				List<ActivityPrize> prizeList = activityManageService.getPrizeList(activityPrize);
				if (prizeList.size() > 0) {
					for (ActivityPrize prize : prizeList) {
						prizeMap.put(prize.getCode(), prize);
					}
					
					/**3.查询获奖人列表*/
					List<ActivityWinningRecord> winningList = activityManageService.getWinningRecordList(tenantCode, inVo.getActivityCode(), joinCodes);
					for (ActivityWinningRecord record : winningList) {
						String prizeCode = record.getActivityPrizeCode();
						if (prizeMap.containsKey(prizeCode)) {
							ActivityPrize prize = prizeMap.get(prizeCode);
							// 判断是否发放金币
							if (ActivityConstants.YES == prize.getIsRewardGold()) {
								SysUserInfo joinUserInfo = getUserInfo(record.getJoinUserCode());
								increaseCoinAccount(record.getJoinUserCode(),
										joinUserInfo.getManageOrgInfo().getSysOrgCode(), prize.getRewardGoldNum());
							}
							// 判断是否颁发荣誉
							if (ActivityConstants.YES == prize.getIsAwardHonor()) {
								//1、颁发荣誉 + 发动态
								prize.setAccepterCode(record.getJoinUserCode());
								awardHonorForActivity(prize, inVo.getUserCode());
								// 发送行为信息
								noticeAction(orgCode, CreditConstants.COMMAND_HONOR_ATTAIN, prize.getAccepterCode(), CreditConstants.TYPE_INDIVIDUAL);
							}
						}
					}
				}
			}
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityModelThreeController.awardPirzeForWinWorks", e);
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
						jsonObject.put("commenterOrg", commenterInfo.getManageOrgInfo().getSysOrgName());
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
						jsonObject.put("replierOrg", replierInfo.getManageOrgInfo().getSysOrgName());
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
						jsonObject.put("questionerOrg", questionerInfo.getManageOrgInfo().getSysOrgName());
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
