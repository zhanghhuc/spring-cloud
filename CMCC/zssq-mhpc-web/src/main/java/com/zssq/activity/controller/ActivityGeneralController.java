package com.zssq.activity.controller;

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
import com.zssq.activity.vo.CollectingOrNotVo;
import com.zssq.activity.vo.CommentingVo;
import com.zssq.activity.vo.DeletingMyInfoVo;
import com.zssq.activity.vo.GetActivityCommentListVo;
import com.zssq.activity.vo.GetActivityReplyListVo;
import com.zssq.activity.vo.PraisingOrNotVo;
import com.zssq.activity.vo.QueryMyCollectionListVo;
import com.zssq.activity.vo.ReplyingVo;
import com.zssq.activity.vo.SharingActivityVo;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.ActivityConstants;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.constants.VoteConstants;
import com.zssq.dao.pojo.ActivityCollection;
import com.zssq.dao.pojo.ActivityComment;
import com.zssq.dao.pojo.ActivityCommentReply;
import com.zssq.dao.pojo.ActivityInfoWithBLOBs;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IActivityManageService;
import com.zssq.utils.PageInfo;
import com.zssq.utils.StringTools;
import com.zssq.vote.controller.BaseController;
import com.zssq.vote.thread.UpdateRelationDataThread;

/**
 * 活动模块通用接口（评论，回复，点赞，收藏）
 * 
 * @ClassName ActivityGeneralController
 * @Description 
 * @author liurong
 * @date 2017年4月21日 下午2:47:02
 * @version 1.0
 * @since JDK 1.7
 */
@RequestMapping("/activity")
@Controller
public class ActivityGeneralController extends BaseController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IActivityManageService activityManageService;
	@Autowired
	private DynamicActivityProcedure dap;
	/**
	 * 获取评论列表
	 * @Function getActivityCommentList
	 * @Description 
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getActivityCommentList", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getActivityCommentList(@RequireValid GetActivityCommentListVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			//入参
			ActivityComment comment = new ActivityComment();
			comment.setTenantCode(tenantCode);
			comment.setOrgCode(orgCode);
			comment.setActivityCode(inVo.getActivityCode());
			comment.setPageSize(Integer.parseInt(inVo.getPageSize()));
			if (StringTools.isNotEmpty(inVo.getId())) {
				comment.setId(Long.valueOf(inVo.getId()));
			}
			comment.setAdmirerCode(inVo.getUserCode());  //点赞人
			
			PageInfo pageInfo = activityManageService.getCommentList(comment);
			List<ActivityComment> list = pageInfo.getList();
			JSONArray jsonArray = new JSONArray();
			for (ActivityComment vi : list) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", StringTools.formatToString(vi.getId()));
				jsonObject.put("commentCode", vi.getCode());
				jsonObject.put("activityCode", vi.getActivityCode());
				jsonObject.put("commenterCode", vi.getCommenterCode());
				SysUserInfo commenterInfo = getUserInfo(vi.getCommenterCode());
				if(commenterInfo != null){
					jsonObject.put("commenterName", commenterInfo.getUserName());
					jsonObject.put("commenterUrl", commenterInfo.getHeadPortrait());
					// 评论人所属组织
					jsonObject.put("commenterOrg", commenterInfo.getManageOrgInfo().getSysOrgName());
				}else{
					jsonObject.put("commenterName", "");
					jsonObject.put("commenterUrl", "");
					jsonObject.put("commenterOrg", "");
				}
				jsonObject.put("content", vi.getContent());
				jsonObject.put("createTime", StringTools.formatToString(vi.getCreateTime()));
				jsonObject.put("replyCount", StringTools.formatToString(vi.getReplyCount()));
				jsonObject.put("praiseCount", StringTools.formatToString(vi.getPraiseCount()));
				if (null != vi.getAdmirerCode()) {
					jsonObject.put("isPraise", StringTools.formatToString(ActivityConstants.YES));
				} else {
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
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityGeneralController.getActivityCommentList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 查询活动评论的回复列表
	 * @Function getActivityReplyList
	 * @Description 
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getActivityReplyList", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getActivityReplyList(@RequireValid GetActivityReplyListVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			// 入参
			ActivityCommentReply reply = new ActivityCommentReply();
			reply.setTenantCode(tenantCode);
			reply.setOrgCode(orgCode);
			reply.setActivityCode(inVo.getActivityCode());
			reply.setCommentCode(inVo.getCommentCode());
			reply.setPageSize(Integer.parseInt(inVo.getPageSize()));
			if (StringTools.isNotEmpty(inVo.getId())) {
				reply.setId(Long.valueOf(inVo.getId()));
			}
			reply.setAdmirerCode(inVo.getUserCode()); // 点赞人
			
			PageInfo pageInfo = activityManageService.getCommentReplyList(reply);
			List<ActivityCommentReply> list = pageInfo.getList();
			JSONArray jsonArray = new JSONArray();
			for (ActivityCommentReply vcr : list) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", StringTools.formatToString(vcr.getId()));
				jsonObject.put("replyCode", vcr.getCode());
				jsonObject.put("commentCode", vcr.getCommentCode());
				jsonObject.put("activityCode", vcr.getActivityCode());
				jsonObject.put("replierCode", vcr.getReplierCode());
				jsonObject.put("questionerCode", vcr.getQuestionerCode());
				// 根据 replierCode 查询 replierName replierUrl replierOrg
				List<SysUserInfo> users = getUserInfos(vcr.getReplierCode(), vcr.getQuestionerCode());
				for (SysUserInfo sysUserInfo : users) {
					if (vcr.getReplierCode().equals(sysUserInfo.getUserCode())) {
						jsonObject.put("replierName", StringTools.formatToString(sysUserInfo.getUserName()));
						jsonObject.put("replierUrl", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
						jsonObject.put("replierOrg", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgName()));
					}
					if (vcr.getQuestionerCode().equals(sysUserInfo.getUserCode())) {
						jsonObject.put("questionerName", StringTools.formatToString(sysUserInfo.getUserName()));
						jsonObject.put("questionerUrl", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
						jsonObject.put("questionerOrg", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgName()));
					}
				}
				if (!jsonObject.containsKey("replierName")) {
					jsonObject.put("replierName", "");
				}
				if (!jsonObject.containsKey("replierUrl")) {
					jsonObject.put("replierUrl", "");
				}
				if (!jsonObject.containsKey("replierOrg")) {
					jsonObject.put("replierOrg", "");
				}
				if (!jsonObject.containsKey("questionerName")) {
					jsonObject.put("questionerName", "");
				}
				if (!jsonObject.containsKey("questionerUrl")) {
					jsonObject.put("questionerUrl", "");
				}
				if (!jsonObject.containsKey("questionerOrg")) {
					jsonObject.put("questionerOrg", "");
				}
				
				jsonObject.put("content", vcr.getContent());
				jsonObject.put("createTime", StringTools.formatToString(vcr.getCreateTime()));
				jsonObject.put("praiseCount", StringTools.formatToString(vcr.getPraiseCount()));
				if (null != vcr.getAdmirerCode()) {
					jsonObject.put("isPraise", StringTools.formatToString(ActivityConstants.YES));
				} else {
					jsonObject.put("isPraise", StringTools.formatToString(ActivityConstants.NO));
				}
				jsonArray.add(jsonObject);
			}

			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("replyList", jsonArray);
			jsonObject.put("surplusCount", StringTools.formatToString(pageInfo.getTotalItem()));
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityGeneralController.getActivityReplyList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 发表评论
	 * @Function commenting
	 * @Description 
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/commenting", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON commenting(@RequireValid CommentingVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			// 入参
			ActivityComment comment = new ActivityComment();
			comment.setTenantCode(tenantCode);
			comment.setOrgCode(orgCode);
			comment.setCommenterCode(inVo.getUserCode()); // 评论人Code
			comment.setContent(inVo.getContent());
			comment.setActivityCode(inVo.getActivityCode());
			
			ActivityComment aComment = activityManageService.addComment(comment);
			
			/**发送"成功发表一次评论"行为信息*/
			noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_PUBLISH, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			
			//更新评论数(动态)
			new Thread(new UpdateRelationDataThread(inVo.getActivityCode(), RelationConstants.NUM_ONE, RelationConstants.RELATION_THIRD_DATANUM_TYPE_COMMENT)).start();
			
			// 暂时不发送评论消息提醒/
			
			
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", StringTools.formatToString(aComment.getId()));
			jsonObject.put("commentCode", aComment.getCode());
			jsonObject.put("activityCode", aComment.getActivityCode());
			jsonObject.put("commenterCode", aComment.getCommenterCode());
			SysUserInfo commenterInfo = getUserInfo(inVo.getUserCode());
			if(commenterInfo != null){
				jsonObject.put("commenterName", commenterInfo.getUserName());
				jsonObject.put("commenterUrl", commenterInfo.getHeadPortrait());
				// 评论人所属组织
				jsonObject.put("commenterOrg", commenterInfo.getManageOrgInfo().getSysOrgName());
			}else{
				jsonObject.put("commenterName", "");
				jsonObject.put("commenterUrl", "");
				jsonObject.put("commenterOrg", "");
			}
			jsonObject.put("content", aComment.getContent());
			jsonObject.put("createTime", StringTools.formatToString(aComment.getCreateTime()));
			jsonObject.put("replyCount", StringTools.formatToString(aComment.getReplyCount()));
			jsonObject.put("praiseCount", StringTools.formatToString(aComment.getPraiseCount()));
		    jsonObject.put("isPraise", StringTools.formatToString(ActivityConstants.NO));
			
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityGeneralController.commenting", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 发表回复
	 * @Function replying
	 * @Description 
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/replying", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON replying(@RequireValid ReplyingVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			// 入参
			ActivityCommentReply reply = new ActivityCommentReply();
			reply.setTenantCode(tenantCode);
			reply.setOrgCode(orgCode);
			reply.setActivityCode(inVo.getActivityCode());
			reply.setCommentCode(inVo.getCommentCode());
			reply.setReplierCode(inVo.getUserCode());// 回复人Code
			reply.setContent(inVo.getContent());
			reply.setQuestionerCode(inVo.getQuestionerCode()); // 被回复人
			
			ActivityCommentReply vcr = activityManageService.addReply(reply);
			
			/**发送"成功回复一次评论"行为信息*/
			noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_REPLY, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			
			// 暂时不发送回复消息提醒
			
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("id", StringTools.formatToString(vcr.getId()));
			jsonObject.put("replyCode", vcr.getCode());
			jsonObject.put("commentCode", vcr.getCommentCode());
			jsonObject.put("activityCode", vcr.getActivityCode());
			jsonObject.put("replierCode", vcr.getReplierCode());
			jsonObject.put("questionerCode", vcr.getQuestionerCode());
			// 根据 replierCode 查询 replierName replierUrl replierOrg
			List<SysUserInfo> users = getUserInfos(vcr.getReplierCode(), vcr.getQuestionerCode());
			for (SysUserInfo sysUserInfo : users) {
				if (vcr.getReplierCode().equals(sysUserInfo.getUserCode())) {
					jsonObject.put("replierName", StringTools.formatToString(sysUserInfo.getUserName()));
					jsonObject.put("replierUrl", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jsonObject.put("replierOrg", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgName()));
				}
				if (vcr.getQuestionerCode().equals(sysUserInfo.getUserCode())) {
					jsonObject.put("questionerName", StringTools.formatToString(sysUserInfo.getUserName()));
					jsonObject.put("questionerUrl", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jsonObject.put("questionerOrg", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgName()));
				}
			}
			if (!jsonObject.containsKey("replierName")) {
				jsonObject.put("replierName", "");
			}
			if (!jsonObject.containsKey("replierUrl")) {
				jsonObject.put("replierUrl", "");
			}
			if (!jsonObject.containsKey("replierOrg")) {
				jsonObject.put("replierOrg", "");
			}
			if (!jsonObject.containsKey("questionerName")) {
				jsonObject.put("questionerName", "");
			}
			if (!jsonObject.containsKey("questionerUrl")) {
				jsonObject.put("questionerUrl", "");
			}
			if (!jsonObject.containsKey("questionerOrg")) {
				jsonObject.put("questionerOrg", "");
			}
			
			jsonObject.put("content", vcr.getContent());
			jsonObject.put("createTime", StringTools.formatToString(vcr.getCreateTime()));
			jsonObject.put("praiseCount", StringTools.formatToString(vcr.getPraiseCount()));
			jsonObject.put("isPraise", StringTools.formatToString(ActivityConstants.NO));
			
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityGeneralController.replying", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 收藏/取消收藏活动
	 * @Function collectingOrNot
	 * @Description 
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/collectingOrNot", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON collectingOrNot(@RequireValid CollectingOrNotVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			// 入参
			ActivityCollection collection = new ActivityCollection();
			collection.setTenantCode(tenantCode);
			collection.setOrgCode(orgCode);
			collection.setActivityCode(inVo.getActivityCode());
			collection.setCollectorCode(inVo.getUserCode()); // 收藏人Code
			
			int collectionNum = 0;
			// 0-取消收藏
			if (ActivityConstants.OPERATE_0.toString().equals(inVo.getOperating())) {
				collectionNum = activityManageService.deleteCollection(collection);
				
				/**发送"取消收藏"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_CONTENT_COLLECT, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
				//取消收藏数（动态）
				new Thread(new UpdateRelationDataThread(inVo.getActivityCode(), RelationConstants.NUM_BELOW_ONE, RelationConstants.RELATION_THIRD_DATANUM_TYPE_COLLECT)).start();
			
			}
			// 1-收藏
			if (ActivityConstants.OPERATE_1.toString().equals(inVo.getOperating())) {
				collectionNum = activityManageService.insertCollection(collection);
				
				/**发送"收藏"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_CONTENT_COLLECTCANCEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
				//更新收藏数（动态）
				new Thread(new UpdateRelationDataThread(inVo.getActivityCode(), RelationConstants.NUM_ONE, RelationConstants.RELATION_THIRD_DATANUM_TYPE_COLLECT)).start();
			}
			
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("collectionNum", StringTools.formatToString(collectionNum));
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityGeneralController.collectingOrNot", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function praisingOrNot
	 * @Description 点赞/取消点赞活动、评论、回复
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/praisingOrNot", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON praisingOrNot(@RequireValid PraisingOrNotVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			String admirerCode = inVo.getUserCode();
			String infoCode = inVo.getInfoCode();
			String infoType = inVo.getInfoType();
			
			int newPraiseNum = 0;
			// 取消操作
			if (ActivityConstants.OPERATE_0.toString().equals(inVo.getOperating())) {
				if (ActivityConstants.INFO_TYPE_1.toString().equals(infoType)) {
					// 取消点赞活动
					newPraiseNum = activityManageService.deletePraiseOfActivity(admirerCode, infoCode);
					//取消点赞活动时更改动态点赞数
					new Thread(new UpdateRelationDataThread(inVo.getInfoCode(),RelationConstants.NUM_BELOW_ONE, RelationConstants.RELATION_THIRD_DATANUM_TYPE_LIKE)).start();
					
				}
				if (ActivityConstants.INFO_TYPE_2.toString().equals(infoType)) {
					// 取消点赞评论
					newPraiseNum = activityManageService.deletePraiseOfComment(admirerCode, infoCode);
				}
				if (ActivityConstants.INFO_TYPE_3.toString().equals(infoType)) {
					// 取消点赞回复
					newPraiseNum = activityManageService.deletePraiseOfReply(admirerCode, infoCode);
				}
				/**发送"取消点赞"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_ADMIRE_DEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			}
			// 点赞操作
			if (ActivityConstants.OPERATE_1.toString().equals(inVo.getOperating())) {
				if (ActivityConstants.INFO_TYPE_1.toString().equals(infoType)) {
					// 点赞活动
					newPraiseNum = activityManageService.insertPraiseOfActivity(tenantCode, orgCode, admirerCode, infoCode);
					//点赞活动时更改动态点赞数
					new Thread(new UpdateRelationDataThread(inVo.getInfoCode(), RelationConstants.NUM_ONE, RelationConstants.RELATION_THIRD_DATANUM_TYPE_LIKE)).start();
				}
				if (ActivityConstants.INFO_TYPE_2.toString().equals(infoType)) {
					// 点赞评论
					newPraiseNum = activityManageService.insertPraiseOfComment(tenantCode, orgCode, admirerCode, infoCode);
				}
				if (ActivityConstants.INFO_TYPE_3.toString().equals(infoType)) {
					// 点赞回复
					newPraiseNum = activityManageService.insertPraiseOfReply(tenantCode, orgCode, admirerCode, infoCode);
				}
				/**发送"点赞"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_ADMIRE_PUBLISH, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			}
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("praiseNum", StringTools.formatToString(newPraiseNum));
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityGeneralController.praisingOrNot", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 删除我的评论/回复
	 * @Function deletingMyInfo
	 * @Description 
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/deletingMyInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON deletingMyInfo(@RequireValid DeletingMyInfoVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			// 删除评论
			if (ActivityConstants.INFO_TYPE_2.toString().equals(inVo.getInfoType())) {
				activityManageService.deleteCommentByCode(tenantCode, inVo.getUserCode(), inVo.getInfoCode());
				
				/**发送"删除一条我的评论"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_DEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
				
				//更新评论数（动态）
				new Thread(new UpdateRelationDataThread(inVo.getInfoCode(), RelationConstants.NUM_BELOW_ONE, RelationConstants.RELATION_THIRD_DATANUM_TYPE_COMMENT)).start();
			}
			// 删除回复
			if (ActivityConstants.INFO_TYPE_3.toString().equals(inVo.getInfoType())) {
				activityManageService.deleteReplyByCode(tenantCode, inVo.getUserCode(), inVo.getInfoCode());
				
				/**发送"删除一条我的回复"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_REPLYDEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			}
			
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityGeneralController.deletingMyInfo", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 分享活动
	 * @Function sharingActivity
	 * @Description 
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/sharingActivity", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON sharingActivity(@RequireValid SharingActivityVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();

			dap.publishDynamicOfShare(tenantCode, orgCode, inVo.getUserCode(), inVo.getActivityCode());

			// 活动主表中的分享数+1
			activityManageService.updateIncreaseShareNumByCode(inVo.getActivityCode(), 1);
			//增加分享数 （动态）
			new Thread(new UpdateRelationDataThread(inVo.getActivityCode(), RelationConstants.NUM_ONE, RelationConstants.RELATION_THIRD_DATANUM_TYPE_SHARE)).start();
			
			// 设置返回结果集
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityGeneralController.sharingActivity", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	 * @Title: queryMyCollectionList
	 * @Description: 查询我的收藏列表
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/queryMyCollectionList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON queryMyCollectionList(@RequireValid QueryMyCollectionListVo vo) throws BusinessException {
		try {
			SysUserInfo user = getUserInfo(vo.getUserCode());
			PageInfo page;
			if(StringTools.isNotEmpty(vo.getId())){//加载更多时的最后一条记录id
				page = new PageInfo(new Integer(vo.getId()), Integer.parseInt(vo.getPageSize()));
			} else {
				page = new PageInfo(1, Integer.parseInt(vo.getPageSize()));
			}
			List<ActivityInfoWithBLOBs> list = activityManageService.getActivityCollectionList(vo.getUserCode(),
					user.getTenantCode(), page);
			
			//组织返回数据
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonAry = new JSONArray();
			if(list.size() > 0){
				for(ActivityInfoWithBLOBs h : list){
					JSONObject o = new JSONObject();
					o.put("id", StringTools.formatToString(h.getCollectionId()));
					o.put("code", StringTools.formatToString(h.getCode()));
					o.put("activityNo", StringTools.formatToString(h.getActivityNo()));
					o.put("activityType", StringTools.formatToString(h.getActivityType()));
					o.put("activityName", StringTools.formatToString(h.getActivityName()));
					o.put("introduce", StringTools.formatToString(h.getIntroduce()));
					o.put("activityStatus", StringTools.formatToString(h.getActivityStatus()));
					o.put("preheatTime", StringTools.formatToString(h.getPreheatTime()));
					o.put("startTime", StringTools.formatToString(h.getStartTime()));
					o.put("endTime", StringTools.formatToString(h.getEndTime()));
					o.put("createTime", StringTools.formatToString(h.getCollectionTime()));
					o.put("createUserCode", StringTools.formatToString(h.getCreateUserCode()));
					o.put("joinNum", StringTools.formatToString(h.getJoinNum()));
					o.put("shareNum", StringTools.formatToString(h.getShareNum()));
					o.put("commentNum", StringTools.formatToString(h.getCommentNum()));
					o.put("praiseNum", StringTools.formatToString(h.getPraiseNum()));
					o.put("collectionNum", StringTools.formatToString(h.getCollectionNum()));
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
			log.error("ActivityGeneralController.queryMyCollectionList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
}
