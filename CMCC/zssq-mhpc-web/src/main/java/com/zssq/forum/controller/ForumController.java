package com.zssq.forum.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.ForumConstants;
import com.zssq.dao.pojo.CollectionResultEntity;
import com.zssq.dao.pojo.ExpAccount;
import com.zssq.dao.pojo.ForumCollection;
import com.zssq.dao.pojo.ForumCommentReply;
import com.zssq.dao.pojo.ForumPlates;
import com.zssq.dao.pojo.ForumPlatesMember;
import com.zssq.dao.pojo.ForumTopics;
import com.zssq.dao.pojo.ForumTopicsFollow;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.forum.thread.StatisticsForumThread;
import com.zssq.forum.vo.ChangeTopicStatusVo;
import com.zssq.forum.vo.DeletingFollowInfoVo;
import com.zssq.forum.vo.EmpowerVo;
import com.zssq.forum.vo.FixedFollowTopicVo;
import com.zssq.forum.vo.FixedReplyVo;
import com.zssq.forum.vo.GetCollectionListVo;
import com.zssq.forum.vo.GetTopicDetailVo;
import com.zssq.forum.vo.GetTopicFollowListVo;
import com.zssq.forum.vo.GetTopicFollowReplyListVo;
import com.zssq.forum.vo.GetTopicListVo;
import com.zssq.forum.vo.OpearteCollectionVo;
import com.zssq.forum.vo.QueryEnpowermentVo;
import com.zssq.forum.vo.SendCommentVo;
import com.zssq.forum.vo.SendReplyVo;
import com.zssq.forum.vo.SendTopicFollowVo;
import com.zssq.forum.vo.SendTopicVo;
import com.zssq.forum.vo.ShieldingFollowInfoVo;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IExpAccountService;
import com.zssq.service.IForumAuthService;
import com.zssq.service.IForumManageService;
import com.zssq.service.IForumService;
import com.zssq.service.ITeamInfoService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageInfo;
import com.zssq.utils.StringTools;
import com.zssq.vote.controller.BaseController;

/**
 * 论坛模块Controller
 * @ClassName ForumController
 * @Description 
 * @author liurong
 * @date 2017年3月17日 下午5:48:02
 * @version 1.0
 * @since JDK 1.7
 */
@RequestMapping("/bbs")
@Controller
public class ForumController extends BaseController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IForumService forumService;
	@Autowired
	private IForumAuthService forumAuthService;
	@Autowired
	private IExpAccountService expAccountService;
	@Autowired
	private IForumManageService forumManageService;
	@Autowired
	private ITeamInfoService teamService;
	/**
	 * 发表主帖
	 * @Function sendTopic
	 * @Description 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/send/topic", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON sendTopic(@RequireValid SendTopicVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			// 校验是否具有发帖权限
			boolean flag = forumAuthService.queryUserCanbeSendTopic(tenantCode, vo.getBelongCode(), vo.getUserCode());
			if (!flag) {
				// 没有发帖权限
				throw BusinessException.build("FORUM_16002");
			}
			
			/**发送行为操作*/
			boolean isTeamLeader = forumAuthService.queryUserIsTeamLeaderByBelongCode(tenantCode, vo.getBelongCode(), vo.getUserCode());
			if (isTeamLeader) {
				noticeAction(orgCode, CreditConstants.COMMAND_POST_TEAMPUBLISH, vo.getBelongCode(), CreditConstants.TYPE_TEAM);
			} else {
				noticeAction(orgCode, CreditConstants.COMMAND_POST_PUBLISH, vo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			}
			// 发送统计数据
			Thread thread = new Thread(new StatisticsForumThread(orgCode,
					userInfo.getUserCode(), userInfo.getUserName(),vo.getBelongCode()));
			thread.start();
			
			ForumTopics topic = new ForumTopics();
			topic.setTenantCode(tenantCode);
			topic.setOrgCode(orgCode);
			topic.setPostmanCode(vo.getUserCode());// 发帖人CODE
			topic.setSubject(vo.getSubject());// 标题
			topic.setContent(vo.getContent());// 内容
			topic.setBelongCode(vo.getBelongCode());// 论坛所属CODE（这里传入班组CODE即可）
			
			String topicCode = forumService.insertTopic(topic);
			
			JSONObject body = new JSONObject();
			body.put("topicCode", topicCode);
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumController.sendTopic", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 回复主帖（发表跟帖）
	 * @Function sendTopicFollow
	 * @Description 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/send/followTopic", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON sendTopicFollow(@RequireValid SendTopicFollowVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			//获取主贴对象
//			ForumTopics topic = forumService.selectTopicInfo(tenantCode, vo.getTopicCode());
//			if (topic.getForumTopicsCode() == null) {
//				throw BusinessException.build("FORUM_16000", "主帖");
//			}
			// 判断是否具有回复权限
//			ForumPlatesMember authority = forumAuthService.queryUserForumAuthority(tenantCode, topic.getForumPlatesCode(), vo.getUserCode());
//			if (authority.getIsReply() == ForumConstants.NO) {
//				// 没有回复权限
//				throw BusinessException.build("FORUM_16002");
//			}
			
			// 组织数据
			ForumTopicsFollow follow = new ForumTopicsFollow();
			follow.setTenantCode(tenantCode);
			follow.setOrgCode(orgCode);
			follow.setTopicCode(vo.getTopicCode());// 主帖CODE
			follow.setFollowerCode(vo.getUserCode());// 跟帖发表人CODE
			follow.setFollowContent(vo.getReplyContent());// 跟帖内容
			
			String followTopicCode = forumService.insertTopicFollow(follow);
			
			// 组织返回数据
			JSONObject body = new JSONObject();
			body.put("followTopicCode", followTopicCode);
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumController.sendTopicFollow", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 回复跟帖（发表评论）
	 * @Function sendComment
	 * @Description 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/send/comment", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON sendComment(@RequireValid SendCommentVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();

			// 获取主贴对象
			ForumTopics topic = forumService.selectTopicInfo(tenantCode, vo.getTopicCode());
			if (topic.getForumTopicsCode() == null) {
				throw BusinessException.build("FORUM_16000", "主帖");
			}
			// 判断是否具有回复权限
			ForumPlatesMember authority = forumAuthService.queryUserForumAuthority(tenantCode, topic.getForumPlatesCode(), vo.getUserCode());
			if (authority.getIsReply() == ForumConstants.NO) {
				// 没有回复权限
				throw BusinessException.build("FORUM_16002");
			}
			// 组织新增数据
			ForumCommentReply comment = new ForumCommentReply();
			comment.setTenantCode(tenantCode);
			comment.setOrgCode(orgCode);
			comment.setTopicCode(vo.getTopicCode());
			comment.setTopicFollowCode(vo.getFollowTopicCode());
			comment.setQuestionerCode(vo.getQuestionerCode());
			comment.setContent(vo.getReplyContent());
			comment.setReplierCode(vo.getUserCode());
			
			String commentCode = forumService.addComment(comment);
			
			/**发送"成功发表一次评论"行为*/
			noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_PUBLISH , vo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			
			JSONObject body = new JSONObject();
			body.put("commentCode", commentCode);
			
			ResultJSON resultJSON = new ResultJSON("COMMON_200");
			resultJSON.setBody(body);
			return resultJSON;
		}  catch (BusinessException e) {
			throw e;
		}  catch (Exception e) {
			log.error("ForumController.sendComment", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 回复评论（发表回复）
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/send/reply", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON sendReply(@RequireValid SendReplyVo vo) throws BusinessException {
		try {	
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			// 获取主贴对象
			ForumTopics topic = forumService.selectTopicInfo(tenantCode, vo.getTopicCode());
			if (topic.getForumTopicsCode() == null) {
				throw BusinessException.build("FORUM_16000", "主帖");
			}
			// 判断是否具有回复权限
			ForumPlatesMember authority = forumAuthService.queryUserForumAuthority(tenantCode, topic.getForumPlatesCode(), vo.getUserCode());
			if (authority.getIsReply() == ForumConstants.NO) {
				// 没有回复权限
				throw BusinessException.build("FORUM_16002");
			}
			
			ForumCommentReply reply = new ForumCommentReply();
			reply.setTenantCode(tenantCode);
			reply.setOrgCode(orgCode);
			reply.setTopicCode(vo.getTopicCode());
			reply.setTopicFollowCode(vo.getFollowTopicCode());
			reply.setCommentCode(vo.getCommentCode());
			reply.setQuestionerCode(vo.getQuestionerCode());
			reply.setContent(vo.getReplyContent());
			reply.setReplierCode(vo.getUserCode());
			
			String replyCode = forumService.addReply(reply);
			
			/**发送"成功回复一次评论"行为*/
			noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_REPLY , vo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
	
			JSONObject body = new JSONObject();
			body.put("replyCode", replyCode);
			
			ResultJSON resultJSON = new ResultJSON("COMMON_200");
			resultJSON.setBody(body);
			return resultJSON;
		}  catch (BusinessException e) {
			throw e;
		}  catch (Exception e) {
			log.error("ForumController.sendReply", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 查询主帖列表
	 * @Function getTopicList
	 * @Description 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/topics/list", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getTopicList(@RequireValid GetTopicListVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			
			/**查询此belongCode是否存在论坛版块信息*/
			ForumPlates forumPlates = forumService.queyrPlateByBelongCode(vo.getBelongCode(), tenantCode);
			if (forumPlates == null) {
				// 当前论坛信息不存在
				throw BusinessException.build("FORUM_16000", "论坛信息");
			}
			// 论坛版块CODE
			String platesCode = forumPlates.getForumPlatesCode();
			
			ForumTopics topic = new ForumTopics();
			topic.setTenantCode(tenantCode);//租户标识
			topic.setForumPlatesCode(platesCode);
			
			PageInfo pageInfo = new PageInfo(Integer.parseInt(vo.getPageNo()) + 1, Integer.parseInt(vo.getPageSize()));
			
			// 查询主帖列表数据
			pageInfo = forumService.getTopicsList(topic, pageInfo);
			List<ForumTopics> list = pageInfo.getList();
			JSONArray array = new JSONArray();
			SysUserInfo postmanInfo = null;
			for (ForumTopics forumTopics : list) {
				JSONObject json = new JSONObject();
				json.put("topicCode", forumTopics.getForumTopicsCode());
				json.put("postmanCode", forumTopics.getPostmanCode());
				// 查询用户名称和头像URL
				postmanInfo = getUserInfo(forumTopics.getPostmanCode());
				json.put("postmanName", StringTools.formatToString(postmanInfo.getUserName()));
				json.put("postmanHeadUrl", StringTools.formatToString(postmanInfo.getHeadPortrait()));
				json.put("postmanPosition", StringTools.formatToString(postmanInfo.getManageOrgInfo().getSysOrgName()));
				
				json.put("createTime", StringTools.formatToString(forumTopics.getCreateTime()));
				json.put("subject", forumTopics.getSubject());
				json.put("isTop", StringTools.formatToString(forumTopics.getIsTop()));
				json.put("isBest", StringTools.formatToString(forumTopics.getIsBest()));
				json.put("viewCount", StringTools.formatToString(forumTopics.getViewCount()));
				json.put("replyCount", StringTools.formatToString(forumTopics.getReplyCount()));
				json.put("lastReplyTime", StringTools.formatToString(forumTopics.getLastReplyTime()));
				json.put("teamCode", vo.getBelongCode());
				array.add(json);
			}
			
			// 查询当前userCode是否具有发帖权限
			boolean canbeSendTopic = forumAuthService.queryUserCanbeSendTopic(tenantCode, vo.getBelongCode(), vo.getUserCode());
			String isSend = "";
			if (canbeSendTopic) {
				isSend = String.valueOf(ForumConstants.YES);
			} else {
				isSend = String.valueOf(ForumConstants.NO);
			}
			// 统计该论坛版块今日发帖数
			int todayCount = forumService.countDailyTopic(tenantCode, platesCode);
			
			JSONObject body = new JSONObject();
			body.put("totalCount", StringTools.formatToString(pageInfo.getTotalItem()));
			body.put("todayCount", StringTools.formatToString(todayCount));
			body.put("isSend", isSend);
			body.put("topicsList", array);
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumController.getTopicList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 查询跟帖列表
	 * @Function getFollowTopicList
	 * @Description 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/followTopic/list", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getFollowTopicList(@RequireValid GetTopicFollowListVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			
			// 获取主贴对象
			ForumTopics topic = forumService.selectTopicInfo(tenantCode, vo.getTopicCode());
			if (topic.getForumTopicsCode() == null) {
				throw BusinessException.build("FORUM_16000", "主帖");
			}
			
			PageInfo pageInfo = new PageInfo(Integer.parseInt(vo.getPageNo()) + 1, Integer.parseInt(vo.getPageSize()));
			
			ForumTopicsFollow follow = new ForumTopicsFollow();
			follow.setTenantCode(tenantCode);
			follow.setTopicCode(vo.getTopicCode());
			follow.setFollowerCode(vo.getFloorHostCode());
			
			pageInfo = forumService.getTopicFollowList(follow, pageInfo);
			List<ForumTopicsFollow> list = pageInfo.getList();
			
			JSONArray array = new JSONArray();
			if (list.size() > 0) {
				ForumPlatesMember member = forumAuthService.queryUserForumAuthority(tenantCode, topic.getForumPlatesCode(), vo.getUserCode());
				
				SysUserInfo followReplier = null;
				for (ForumTopicsFollow forumTopicsFollow : list) {
					JSONObject json = new JSONObject();
					// 查询名称、头像
					json.put("followReplierCode", forumTopicsFollow.getFollowerCode());
					followReplier = getUserInfo(forumTopicsFollow.getFollowerCode());
					if (followReplier != null) {
						json.put("followReplierName", StringTools.formatToString(followReplier.getUserName()));
						json.put("followReplierPhoto", StringTools.formatToString(followReplier.getHeadPortrait()));
					} else {
						json.put("followReplierName", "");
						json.put("followReplierPhoto", "");
					}
					// 查询等级信息
					ExpAccount expAccount = expAccountService.selectByAccountCode(forumTopicsFollow.getFollowerCode());
					json.put("followReplierGrade", StringTools.formatToString(expAccount.getCurrentLevel()));
					
					if (ForumConstants.NO.equals(forumTopicsFollow.getIsHide())) {
						json.put("followTopicCode", forumTopicsFollow.getForumTopicsFollowCode());
						json.put("followReplyCount", StringTools.formatToString(forumTopicsFollow.getFollowCount()));
						json.put("isHide", StringTools.formatToString(forumTopicsFollow.getIsHide()));
						json.put("content", forumTopicsFollow.getFollowContent());
						json.put("createTime", StringTools.formatToString(forumTopicsFollow.getCreateTime()));
						json.put("canbeDelete", StringTools.formatToString(member.getCanbeDelete()));// 是否具有删除权限：0-否  1-是
						json.put("canbeShield", StringTools.formatToString(member.getCanbeShield()));// 是否具有屏蔽权限：0-否  1-是
					} else {
						json.put("followTopicCode", "");
						json.put("followReplyCount", "");
						json.put("isHide", StringTools.formatToString(forumTopicsFollow.getIsHide()));
						json.put("content", "");
						json.put("createTime", "");
						json.put("canbeDelete", "");// 是否具有删除权限：0-否 1-是
						json.put("canbeShield", "");// 是否具有屏蔽权限：0-否 1-是
					}
					array.add(json);
				}
			}
			
			JSONObject body = new JSONObject();
			body.put("totalCount", StringTools.formatToString(pageInfo.getTotalItem()));
			body.put("followTopicsList", array);
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumController.getFollowTopicList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 查询跟帖的评论及回复列表
	 * @Function getFollowTopicReplyList
	 * @Description 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/followTopic/reply/list", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getFollowTopicReplyList(@RequireValid GetTopicFollowReplyListVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			
			// 获取主贴对象
			ForumTopics topic = forumService.selectTopicInfo(tenantCode, vo.getTopicCode());
			if (topic.getForumTopicsCode() == null) {
				throw BusinessException.build("FORUM_16000", "主帖");
			}
			
			PageInfo pageInfo = new PageInfo(Integer.parseInt(vo.getPageNo()) + 1, Integer.parseInt(vo.getPageSize()));
			
			ForumCommentReply record = new ForumCommentReply();
			record.setTenantCode(tenantCode);
			record.setTopicCode(vo.getTopicCode());
			record.setTopicFollowCode(vo.getFollowTopicCode());
			
			pageInfo = forumService.getTopicFollowReplyList(record, pageInfo);
			
			List<ForumCommentReply> list = pageInfo.getList();
			JSONArray array = new JSONArray();
			if (list.size() > 0) {
				ForumPlatesMember member = forumAuthService.queryUserForumAuthority(tenantCode, topic.getForumPlatesCode(), vo.getUserCode());
				
				for (ForumCommentReply reply : list) {
					JSONObject json = new JSONObject();
					json.put("replierCode", reply.getReplierCode());
					json.put("questionerCode", reply.getQuestionerCode());
					
					List<SysUserInfo> userInfolist = getUserInfos(reply.getReplierCode(), reply.getQuestionerCode());
					for (SysUserInfo sysUserInfo : userInfolist) {
						if (reply.getReplierCode().equals(sysUserInfo.getUserCode())) {
							// 回复人用户名，头像URL
							json.put("replierName", StringTools.formatToString(sysUserInfo.getUserName()));
							json.put("replierPhoto", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
						}
						if (reply.getQuestionerCode().equals(sysUserInfo.getUserCode())) {
							// 被回复人用户名
							json.put("questionerName", StringTools.formatToString(sysUserInfo.getUserName()));
						}
					}
					if (!json.containsKey("replierName")) {
						json.put("replierName", "");
					}
					if (!json.containsKey("replierPhoto")) {
						json.put("replierPhoto", "");
					}
					if (!json.containsKey("questionerName")) {
						json.put("questionerName", "");
					}
					
					if (ForumConstants.NO.equals(reply.getIsHide())) {
						json.put("replyCode", reply.getForumCommentReplyCode());
						json.put("content", reply.getContent());
						json.put("createTime", StringTools.formatToString(reply.getCreateTime()));
						json.put("topicCode", reply.getTopicCode());
						json.put("followTopicCode", reply.getTopicFollowCode());
						json.put("commentCode", StringTools.formatToString(reply.getCommentCode()));
						json.put("isHide", StringTools.formatToString(reply.getIsHide()));
						json.put("canbeDelete", StringTools.formatToString(member.getCanbeDelete()));// 是否具有删除权限：0-否  1-是
						json.put("canbeShield", StringTools.formatToString(member.getCanbeShield()));// 是否具有屏蔽权限：0-否  1-是
					} else {
						json.put("replyCode", "");
						json.put("content", "");
						json.put("createTime", "");
						json.put("topicCode", "");
						json.put("followTopicCode", "");
						json.put("commentCode", "");
						json.put("isHide", StringTools.formatToString(reply.getIsHide()));
						json.put("canbeDelete", "");// 是否具有删除权限：0-否  1-是
						json.put("canbeShield", "");// 是否具有屏蔽权限：0-否  1-是
					}
					array.add(json);
				}
			}
			JSONObject body = new JSONObject();
			body.put("totalCount", StringTools.formatToString(pageInfo.getTotalItem()));
			body.put("repliesList", array);
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumController.getFollowTopicReplyList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 查询主帖详情
	 * @Function getTopicDetail
	 * @Description 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/topic/detail", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getTopicDetail(@RequireValid GetTopicDetailVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			JSONObject body = new JSONObject();
			
			//查询主贴信息
			ForumTopics topic = forumService.selectTopicInfo(tenantCode, vo.getTopicCode(), orgCode, vo.getUserCode());
			if (topic.getForumTopicsCode() != null) {
				body.put("topicCode", StringTools.formatToString(topic.getForumTopicsCode()));
				body.put("subject", StringTools.formatToString(topic.getSubject()));
				body.put("topicContent", StringTools.formatToString(topic.getContent()));
				body.put("createTime", StringTools.formatToString(topic.getCreateTime()));
				
				// 查询userCode的论坛权限信息
				ForumPlatesMember authority = forumAuthService.queryUserForumAuthority(topic.getTenantCode(), topic.getForumPlatesCode(), vo.getUserCode());
				body.put("canbeDelete", StringTools.formatToString(authority.getCanbeDelete()));
				body.put("canbeTop", StringTools.formatToString(authority.getCanbeTop()));
				body.put("canbeBest", StringTools.formatToString(authority.getCanbeBest()));
				body.put("canbeReply", StringTools.formatToString(authority.getIsReply()));
				
				body.put("isTop", StringTools.formatToString(topic.getIsTop()));
				body.put("isBest", StringTools.formatToString(topic.getIsBest()));
				
				//查询收藏表，确定该用户是否收藏该帖子
				if (forumService.isCollectionTopic(tenantCode, vo.getUserCode(), topic.getForumTopicsCode())) {
					body.put("isCollection", StringTools.formatToString(ForumConstants.YES));
				} else {
					body.put("isCollection", StringTools.formatToString(ForumConstants.NO));
				}
				
				//从缓存中  查询发帖人信息  
				body.put("floorHostCode", topic.getPostmanCode());
				// 查询用户名称、头像URL
				SysUserInfo floorHost = getUserInfo(topic.getPostmanCode());
				if (floorHost != null) {
					body.put("floorHostName", StringTools.formatToString(floorHost.getUserName()));
					body.put("floorHostPhoto", StringTools.formatToString(floorHost.getHeadPortrait()));
				} else {
					body.put("floorHostName", "");
					body.put("floorHostPhoto", "");
				}
				// 查询等级信息
				ExpAccount expAccount = expAccountService.selectByAccountCode(topic.getPostmanCode());
				body.put("floorHostGrade", StringTools.formatToString(expAccount.getCurrentLevel()));
			}
			
			// 更改主贴浏览数
			forumService.addTopicViewer(vo.getTopicCode(), vo.getUserCode(), orgCode, tenantCode);
			
			ResultJSON resultJSON = new ResultJSON("COMMON_200");
			resultJSON.setBody(body);
			return resultJSON;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumController.getTopicDetail", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 修改主帖状态
	 * @Function changeTopicStatus
	 * @Description 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/topic/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON changeTopicStatus(@RequireValid ChangeTopicStatusVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			
			/** 1. 权限校验 */
			//获取主贴对象
			ForumTopics topic = forumService.selectTopicInfo(tenantCode, vo.getTopicCode());
			if (topic.getForumTopicsCode() == null) {
				throw BusinessException.build("FORUM_16000", "主帖");
			}
			
			//判断是否是班组长
			boolean isTeamLeader = forumAuthService.queryUserIsTeamLeaderByPlatesCode(tenantCode, topic.getForumPlatesCode(), vo.getUserCode());
			if (isTeamLeader) {
				ForumTopics t = new ForumTopics();
				t.setTenantCode(tenantCode);
				t.setForumTopicsCode(vo.getTopicCode());
				
				/** 2. 修改状态  */
				if (ForumConstants.OPERATION_TOP.equalsIgnoreCase(vo.getOperating())) {
					// 置顶
					t.setIsTop(ForumConstants.YES);
				}
				if (ForumConstants.OPERATION_DELETE_TOP.equalsIgnoreCase(vo.getOperating())) {
					// 取消置顶
					t.setIsTop(ForumConstants.NO);
				}
				if (ForumConstants.OPERATION_BEST.equalsIgnoreCase(vo.getOperating())) {
					// 置精
					t.setIsBest(ForumConstants.YES);
				}
				if (ForumConstants.OPERATION_DELETE_BEST.equalsIgnoreCase(vo.getOperating())) {
					// 取消置精
					t.setIsBest(ForumConstants.NO);
				}
				if (ForumConstants.OPERATION_DELETE.equalsIgnoreCase(vo.getOperating())) {
					// 删除帖子
					t.setIsDelete(ForumConstants.YES);
					
				}
				
				forumService.updateTopic(t);
			} else {
				// 没有操作权限
				throw BusinessException.build("COMMON_403");
			}
			if (ForumConstants.OPERATION_DELETE.equalsIgnoreCase(vo.getOperating())) {
				/**发送"版主（班组长）删除一条帖子（主帖）"行为信息*/
				ForumPlates record = new ForumPlates();
				record.setTenantCode(tenantCode);
				record.setForumPlatesCode(topic.getForumPlatesCode());
				record = forumAuthService.selectForumPlate(record);
				noticeAction(topic.getOrgCode(), CreditConstants.COMMAND_POST_TEAMDEL, record.getBelongCode(), CreditConstants.TYPE_TEAM);
			}
			
			ResultJSON resultJSON = new ResultJSON("COMMON_200");
			resultJSON.setBody(new JSONObject());
			
			return resultJSON;
		}  catch (BusinessException e) {
			throw e;
		}  catch (Exception e) {
			log.error("ForumController.changeTopicStatus ", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 收藏or取消收藏主帖
	 * @Function opearteCollection
	 * @Description 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/topic/collection", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON opearteCollection(@RequireValid OpearteCollectionVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			ForumCollection collection = new ForumCollection();
			collection.setMemberCode(vo.getUserCode());
			collection.setTopicCode(vo.getTopicCode());
			collection.setTenantCode(tenantCode);
			collection.setOrgCode(orgCode);
			
			if (ForumConstants.OPERATION_COLLECT.equals(Byte.parseByte(vo.getOperating()))) {
				// 收藏帖子
				collection.setBelongCode(vo.getBelongCode());
				forumService.addCollection(collection);
				
				/**发送收藏行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_CONTENT_COLLECT , vo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			} else if (ForumConstants.OPERATION_DELETE_COLLECT.equals(Byte.parseByte(vo.getOperating()))) {
				// 取消收藏
				forumService.deleteCollection(collection);
				
				/**发送取消收藏行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_CONTENT_COLLECTCANCEL , vo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			}
			
			ResultJSON resultJSON = new ResultJSON("COMMON_200");
			resultJSON.setBody(new JSONObject());
			
			return resultJSON;
		} catch (BusinessException e) {
			log.error("ForumController.opearteCollection", e);
			throw e;
		} catch (Exception e) {
			log.error("ForumController.opearteCollection ", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 加载更多方式获取收藏列表
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/myCollection/list", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getCollectionList(@RequireValid GetCollectionListVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();

			//获取收藏列表
			PageBean bean = forumService.selectCollectionList(vo.getId(),vo.getPageSize(),vo.getUserCode(),tenantCode);
			
			//组织返回数据
			List<CollectionResultEntity> collectionList = bean.getRecordList();
			JSONArray array = new JSONArray();
			
			SysUserInfo postmanInfo = null;
			if (collectionList.size() > 0) {
				for (CollectionResultEntity c : collectionList) {
					JSONObject item = new JSONObject();
					item.put("id", c.getId());
					item.put("collectionCode", c.getCollectionCode());
					item.put("postmanCode", c.gettPostManCode());
					
					postmanInfo = getUserInfo(c.gettPostManCode());
					item.put("postmanName", StringTools.formatToString(postmanInfo.getUserName()));
					item.put("postmanUrl", StringTools.formatToString(postmanInfo.getHeadPortrait()));
					item.put("org", StringTools.formatToString(postmanInfo.getManageOrgInfo().getSysOrgName()));
					// 班组CODE
					String teamCode = c.getBelongCode();
					item.put("belongCode", StringTools.formatToString(teamCode));
					TeamInfo teamInfo = teamService.selectByCode(teamCode);
					if (teamInfo != null) {
						item.put("teamName", teamInfo.getTeamName());
					} else {
						item.put("teamName", "");
					}
					item.put("createTime", StringTools.formatToString(c.getcCreateTime()));
					item.put("topicCode", c.getcTopicCode());
					item.put("subject", c.getSubject());
					item.put("publishTime", StringTools.formatToString(c.gettCreateTime()));
					
					array.add(item);
				}
			}
			JSONObject body = new JSONObject();
			body.put("collectionList", array);
			ResultJSON resultJSON = new ResultJSON("COMMON_200");
			resultJSON.setBody(body);
			
			return resultJSON;
		}  catch (BusinessException e) {
			log.error("ForumController.getCollectionList",e);
			throw e;
		}  catch (Exception e) {
			log.error("ForumController.getCollectionList ",e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function empower
	 * @Description 设置某一论坛权限
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/empower",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON empower(@RequireValid EmpowerVo inVo) throws BusinessException {
		try{
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			
			//判断是否是班组长
			boolean isTeamLeader = forumAuthService.queryUserIsTeamLeaderByPlatesCode(tenantCode, inVo.getForumCode(), inVo.getUserCode());
			if (!isTeamLeader) {
				// 没有操作权限
				throw BusinessException.build("FORUM_16002");
			}
			
			ForumPlates forumPlates = new ForumPlates();
			forumPlates.setAllReply(Byte.valueOf(inVo.getIsReply()));
			forumPlates.setAllSend(Byte.valueOf(inVo.getIsSend()));
			forumPlates.setForumPlatesCode(inVo.getForumCode());
			forumPlates.setTenantCode(tenantCode);
			
			forumAuthService.updateForumPlateEmpowerment(forumPlates);
			
			ResultJSON resultJSON = new ResultJSON("COMMON_200");
			resultJSON.setBody(new JSONObject());
			return resultJSON;
		}  catch (BusinessException e) {
			throw e;
		}  catch (Exception e) {
			log.error("ForumController.empower ", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function queryEmpowerment
	 * @Description 查询某一论坛权限
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/empowerment/query",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON queryEmpowerment(@RequireValid QueryEnpowermentVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			ForumPlates forumPlates = new ForumPlates();
			forumPlates.setBelongCode(inVo.getBelongCode());
			forumPlates.setTenantCode(tenantCode);
			forumPlates.setOrgCode(orgCode);
			ForumPlates forumInfo = forumAuthService.selectForumPlate(forumPlates);
			JSONObject jsonObject = new JSONObject();
			if (null != forumInfo.getForumPlatesCode()) {
				jsonObject.put("forumCode", forumInfo.getForumPlatesCode());
				jsonObject.put("belongCode", forumInfo.getBelongCode());
				jsonObject.put("isSend", StringTools.formatToString(forumInfo.getAllSend()));
				jsonObject.put("isReply", StringTools.formatToString(forumInfo.getAllReply()));
			}
			ResultJSON resultJSON = new ResultJSON("COMMON_200");
			resultJSON.setBody(jsonObject);
			return resultJSON;
		}  catch (BusinessException e) {
			throw e;
		}  catch (Exception e) {
			log.error("ForumController.queryEmpowerment ", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 删除跟帖/评论及回复
	 * @Function deletingFollowInfo
	 * @Description 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/deletingFollowInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON deletingFollowInfo(@RequireValid DeletingFollowInfoVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			
			boolean flag = forumAuthService.queryUserIsTeamLeaderByBelongCode(tenantCode, vo.getBelongCode(), vo.getUserCode());
			if (!flag) {
				// 权限不足
				throw BusinessException.build("FORUM_16002");
			}
			
			if (ForumConstants.TOBE_DS_INFOTYPE_1.equals(vo.getInfoType())) {
				forumService.deleteFollowTopic(tenantCode, vo.getInfoCode());
			}
			if (ForumConstants.TOBE_DS_INFOTYPE_2.equals(vo.getInfoType())) {
				ForumCommentReply fcr = forumService.deleteCommentAndReply(tenantCode, vo.getInfoCode());
				
				
				/**发送删除一条我的评论行为信息*/
				noticeAction(fcr.getOrgCode(), CreditConstants.COMMAND_COMMENT_DEL, fcr.getReplierCode(), CreditConstants.TYPE_INDIVIDUAL);
			}
			if (ForumConstants.TOBE_DS_INFOTYPE_3.equals(vo.getInfoType())) {
				ForumCommentReply fcr = forumService.deleteCommentAndReply(tenantCode, vo.getInfoCode());
				
				/**发送删除一条我的回复行为信息*/
				noticeAction(fcr.getOrgCode(), CreditConstants.COMMAND_COMMENT_REPLYDEL, fcr.getReplierCode(), CreditConstants.TYPE_INDIVIDUAL);
			}
			
			
			JSONObject body = new JSONObject();
			ResultJSON resultJSON = new ResultJSON("COMMON_200");
			resultJSON.setBody(body);
			
			return resultJSON;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumController.deletingFollowInfo ", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 屏蔽跟帖/评论及回复
	 * @Function shieldingFollowInfo
	 * @Description 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/shieldingFollowInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON shieldingFollowInfo(@RequireValid ShieldingFollowInfoVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();

			boolean flag = forumAuthService.queryUserIsTeamLeaderByBelongCode(tenantCode, vo.getBelongCode(), vo.getUserCode());
			if (!flag) {
				// 权限不足
				throw BusinessException.build("FORUM_16002");
			}
			
			if (ForumConstants.TOBE_DS_INFOTYPE_1.equals(vo.getInfoType())) {
				forumService.shieldFollowTopic(tenantCode, vo.getInfoCode());
			}
			if (ForumConstants.TOBE_DS_INFOTYPE_2.equals(vo.getInfoType())) {
				forumService.shieldCommentAndReply(tenantCode, vo.getInfoCode());
			}
			
			JSONObject body = new JSONObject();
			ResultJSON resultJSON = new ResultJSON("COMMON_200");
			resultJSON.setBody(body);
			
			return resultJSON;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumController.shieldingFollowInfo ", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/****** 暂时舍弃 *****/
	//@RequestMapping(value="/myCollection/list",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getCollectionListByNext(@RequireValid GetCollectionListVo vo) throws BusinessException{
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			
			//
			String pageNo = "0";
			
			//构造分页对象
			PageInfo pageInfo = new PageInfo(Integer.parseInt(pageNo) + 1, Integer.parseInt(vo.getPageSize()));
			
			//获取收藏列表
			PageBean bean = forumService.selectCollectionList(pageInfo,vo.getUserCode(),tenantCode);
			
			//组织返回数据
			List<CollectionResultEntity> collectionList = bean.getRecordList();
			JSONArray array = new JSONArray();
			if (collectionList.size() > 0) {
				for (CollectionResultEntity c : collectionList) {
					JSONObject item = new JSONObject();
					item.put("collectionCode", c.getCollectionCode());
					item.put("postmanCode", c.gettPostManCode());
					//TODO
					/*item.put("postmanName", c.gettPostManCode());
					item.put("postmanUrl", c.gettPostManCode());
					item.put("teamName", c.gettPostManCode());
					item.put("org", c.gettPostManCode());*/
					item.put("createTime", StringTools.formatToString(c.getcCreateTime()));
					item.put("topicCode", c.getcTopicCode());
					item.put("content", c.getContent());
					item.put("publishTime", StringTools.formatToString(c.gettCreateTime()));
					
					array.add(item);
				}
			}
			JSONObject body = new JSONObject();
			body.put("totalCount", StringTools.formatToString(bean.getTotalCount()));
			body.put("collectionList", array);
			ResultJSON resultJSON = new ResultJSON("COMMON_200");
			resultJSON.setBody(body);
			
			return resultJSON;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumController.getCollectionList ", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 查询跟帖列表（定位）
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/followTopic/list/fixed", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON fixedFollowTopic(@RequireValid FixedFollowTopicVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();

			ForumTopicsFollow follow = new ForumTopicsFollow();
			follow.setTenantCode(tenantCode);
			follow.setTopicCode(vo.getTopicCode());
			follow.setForumTopicsFollowCode(vo.getFollowTopicCode());
			follow.setPageSize(Integer.parseInt(vo.getPageSize()));

			// 获取被举报跟帖所在页数据
			PageInfo pageInfo = forumManageService.getComplaintFollowTopic(follow);
			List<ForumTopicsFollow> list = pageInfo.getList();

			JSONArray array = new JSONArray();
			for (ForumTopicsFollow forumTopicsFollow : list) {
				JSONObject json = new JSONObject();
				json.put("followReplierCode", forumTopicsFollow.getFollowerCode());
				SysUserInfo followRelier = getUserInfo(forumTopicsFollow.getFollowerCode());
				if(followRelier != null){
					json.put("followReplierName", followRelier.getUserName());
					json.put("followReplierPhoto", followRelier.getHeadPortrait());
				}else{
					json.put("followReplierName", "");
					json.put("followReplierPhoto", "");
				}
				// 查询等级信息
				ExpAccount expAccount = expAccountService.selectByAccountCode(forumTopicsFollow.getFollowerCode());
				json.put("followReplierGrade", StringTools.formatToString(expAccount.getCurrentLevel()));
				if (ForumConstants.NO.equals(forumTopicsFollow.getIsHide())) {
					json.put("followTopicCode", forumTopicsFollow.getForumTopicsFollowCode());
					json.put("followReplyCount", StringTools.formatToString(forumTopicsFollow.getFollowCount()));
					json.put("isShow", StringTools.formatToString(forumTopicsFollow.getIsHide()));
					json.put("content", forumTopicsFollow.getFollowContent());
					json.put("createTime", StringTools.formatToString(forumTopicsFollow.getCreateTime()));
				} else {
					json.put("followTopicCode", "");
					json.put("followReplyCount", "");
					json.put("isShow", "");
					json.put("content", "");
					json.put("createTime", "");
				}
				array.add(json);
			}

			JSONObject body = new JSONObject();
			body.put("totalCount", StringTools.formatToString(pageInfo.getTotalItem()));
			body.put("pageNo", StringTools.formatToString(pageInfo.getToPage() - 1));
			body.put("followTopicsList", array);

			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumController.fixedFollowTopic", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	/**
	 * 查询跟帖的评论及回复列表（定位）
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/followTopic/reply/list/fixed", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON fixedReply(@RequireValid FixedReplyVo vo) throws BusinessException {
		try {
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();

			ForumCommentReply reply = new ForumCommentReply();
			reply.setTenantCode(tenantCode);
			reply.setTopicCode(vo.getTopicCode());
			reply.setTopicFollowCode(vo.getFollowTopicCode());
			reply.setForumCommentReplyCode(vo.getReplyCode());
			reply.setPageSize(Integer.parseInt(vo.getPageSize()));

			// 获取被举报评论或回复所在页数据
			PageInfo pageInfo = forumManageService.getComplaintReply(reply);

			List<ForumCommentReply> list = pageInfo.getList();
			JSONArray array = new JSONArray();
			if (list.size() > 0) {
				for (ForumCommentReply r : list) {
					JSONObject json = new JSONObject();
					json.put("replyCode", r.getForumCommentReplyCode());
					json.put("content", r.getContent());
					json.put("createTime", StringTools.formatToString(r.getCreateTime()));
					json.put("topicCode", r.getTopicCode());
					json.put("followTopicCode", r.getTopicFollowCode());
					json.put("commentCode", StringTools.formatToString(r.getCommentCode()));
					json.put("replierCode", r.getReplierCode());
					json.put("questionerCode", r.getQuestionerCode());
					List<SysUserInfo> users = getUserInfos(r.getReplierCode(), r.getQuestionerCode());
					for (SysUserInfo sysUserInfo : users) {
						if (sysUserInfo.getUserCode().equals(r.getReplierCode())) {
							// 回复人用户名，头像URL
							json.put("replierName", sysUserInfo.getUserName());
							json.put("replierPhoto", sysUserInfo.getHeadPortrait());
						}
						if (sysUserInfo.getUserCode().equals(r.getQuestionerCode())) {
							// 被回复人用户名
							json.put("questionerName", sysUserInfo.getUserName());
						}
					}
					if (!json.containsKey("replierName")) {
						json.put("replierName", "");
					}
					if (!json.containsKey("replierPhoto")) {
						json.put("replierPhoto", "");
					}
					if (!json.containsKey("questionerName")) {
						json.put("questionerName", "");
					}
					json.put("isShow", StringTools.formatToString(r.getIsHide()));

					array.add(json);
				}
			}
			JSONObject body = new JSONObject();
			body.put("totalCount", StringTools.formatToString(pageInfo.getTotalItem()));
			body.put("pageNo", StringTools.formatToString(pageInfo.getToPage() - 1));
			body.put("repliesList", array);

			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumController.fixedReply", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
	
}
