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
import com.zssq.constants.ForumConstants;
import com.zssq.dao.pojo.ForumCommentReply;
import com.zssq.dao.pojo.ForumPlatesMember;
import com.zssq.dao.pojo.ForumTopics;
import com.zssq.dao.pojo.ForumTopicsFollow;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.forum.vo.FixedFollowTopicVo;
import com.zssq.forum.vo.FixedReplyVo;
import com.zssq.forum.vo.GetForumListForMonitorVo;
import com.zssq.forum.vo.GetTopicDetailVo;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IForumAuthService;
import com.zssq.service.IForumManageService;
import com.zssq.service.IForumService;
import com.zssq.utils.PageInfo;
import com.zssq.utils.StringTools;
import com.zssq.vote.controller.BaseController;

@Controller
@RequestMapping("/bbs")
public class ForumControllerByGl extends BaseController {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IForumManageService forumManageService;
	@Autowired
	private IForumAuthService forumAuthService;
	@Autowired
	private IForumService forumService;

	/**
	 * 查询主帖详情
	 * 
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

			// 查询主贴信息
			ForumTopics topic = forumManageService.selectTopicInfo(tenantCode, vo.getTopicCode(), orgCode,
					vo.getUserCode());
			if (topic.getForumTopicsCode() != null) {
				body.put("topicCode", StringTools.formatToString(topic.getForumTopicsCode()));
				body.put("topicContent", StringTools.formatToString(topic.getContent()));
				body.put("createTime", StringTools.formatToString(topic.getCreateTime()));

				// 查询userCode的论坛权限信息
				ForumPlatesMember authority = forumAuthService.queryUserForumAuthority(topic.getTenantCode(),
						topic.getForumPlatesCode(), vo.getUserCode());
				body.put("canbeDelete", StringTools.formatToString(authority.getCanbeDelete()));
				body.put("canbeTop", StringTools.formatToString(authority.getCanbeTop()));
				body.put("canbeBest", StringTools.formatToString(authority.getCanbeBest()));
				body.put("canbeReply", StringTools.formatToString(authority.getIsReply()));

				body.put("isTop", StringTools.formatToString(topic.getIsTop()));
				body.put("isBest", StringTools.formatToString(topic.getIsBest()));

				// 查询收藏表，确定该用户是否收藏该帖子
				if (forumManageService.isCollectionTopic(tenantCode, vo.getUserCode(), topic.getForumTopicsCode())) {
					body.put("isCollection", StringTools.formatToString(ForumConstants.YES));
				} else {
					body.put("isCollection", StringTools.formatToString(ForumConstants.NO));
				}

				// 从缓存中 查询发帖人信息
				body.put("floorHostCode", topic.getPostmanCode());
				// TODO 查询用户名称、头像URL、等级信息
				SysUserInfo floorHost = getUserInfo(topic.getPostmanCode());
				if(floorHost != null){
					body.put("floorHostName", StringTools.formatToString(floorHost.getUserName()));
					body.put("floorHostPhoto", StringTools.formatToString(floorHost.getHeadPortrait()));
				}else{
					body.put("floorHostName", "");
					body.put("floorHostPhoto", "");
				}
				body.put("floorHostGrade", "");
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
				// TODO 等级信息
				json.put("followReplierGrade", "");
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
	
	
	
	/**
	 * @Function getVoteListForMonitor
	 * @Description 内容监控-查询班组列表
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getForumListForMonitor", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getVoteListForMonitor(@RequireValid GetForumListForMonitorVo inVo) throws BusinessException{
		
		try {
			// 根据userCode 查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			//设置分页数据
			PageInfo page = new PageInfo(Integer.parseInt(inVo.getPageNo()) + 1, Integer.parseInt(inVo.getPageSize()));
			
			page = forumManageService.selectForumListForMonitor(tenantCode,orgCode,page);
			
			JSONArray array = new JSONArray();
			List<ForumTopics> list = page.getList();
			for (ForumTopics ft : list) {
				JSONObject item = new JSONObject();
				item.put("forumTopicsCode", ft.getForumTopicsCode());
				item.put("postorCode", ft.getPostmanCode());
				item.put("teamCode", ft.getBelongCode());
				item.put("topicTitle", ft.getSubject());
				SysUserInfo person = getUserInfo(ft.getPostmanCode());
				if(person != null){
					item.put("postorName", StringTools.formatToString(person.getUserName()));
					item.put("postsorCompany", StringTools.formatToString(person.getManageOrgInfo().getSysOrgName()));
				}else{
					item.put("postorName", "");
					item.put("postsorCompany", "");
				}
				item.put("createTime", StringTools.formatToString(ft.getCreateTime()));
				array.add(item);
			}
			
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("contentList", array);
			jsonObject.put("totalCount", StringTools.formatToString(page.getTotalItem()));
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumControllerByGl.getForumListForMonitor", e);
			throw BusinessException.build("COMMON_400");
		}
	}
}
