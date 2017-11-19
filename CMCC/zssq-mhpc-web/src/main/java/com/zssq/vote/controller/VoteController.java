package com.zssq.vote.controller;

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
import com.zssq.annotation.RequireValid;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.ForumConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.constants.VoteConstants;
import com.zssq.dao.pojo.GetMyJoinVoteListEntity;
import com.zssq.dao.pojo.GetVoteInfoEntity;
import com.zssq.dao.pojo.JoinVoteEntity;
import com.zssq.dao.pojo.OrgLinkList;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.dao.pojo.UserState;
import com.zssq.dao.pojo.VoteCollection;
import com.zssq.dao.pojo.VoteComment;
import com.zssq.dao.pojo.VoteCommentReply;
import com.zssq.dao.pojo.VoteDetailModel;
import com.zssq.dao.pojo.VoteInfo;
import com.zssq.dao.pojo.VoteJoin;
import com.zssq.dao.pojo.VoteJoinAuth;
import com.zssq.dao.pojo.VoteOptions;
import com.zssq.dao.pojo.VotePraise;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysOrgService;
import com.zssq.service.IUserRelationService;
import com.zssq.service.IVoteAuthService;
import com.zssq.service.IVoteManageService;
import com.zssq.service.IVoteService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageInfo;
import com.zssq.utils.StringTools;
import com.zssq.vote.proc.DynamicVoteProcedure;
import com.zssq.vote.thread.SendCommentOrReplyMsgThread;
import com.zssq.vote.thread.SendPraiseMsgThread;
import com.zssq.vote.thread.StatisticsVoteThread;
import com.zssq.vote.vo.AdvanceFinishVoteVo;
import com.zssq.vote.vo.CollectingOrNotVo;
import com.zssq.vote.vo.CommentingVo;
import com.zssq.vote.vo.DeletingVo;
import com.zssq.vote.vo.GetGatewayVoteListVo;
import com.zssq.vote.vo.GetJoinVoteListVo;
import com.zssq.vote.vo.GetPublishVoteListVo;
import com.zssq.vote.vo.GetVoteCommentFixedListVo;
import com.zssq.vote.vo.GetVoteCommentListVo;
import com.zssq.vote.vo.GetVoteInfoVo;
import com.zssq.vote.vo.GetVoteReplyFixedListVo;
import com.zssq.vote.vo.GetVoteReplyListVo;
import com.zssq.vote.vo.JoinVoteVo;
import com.zssq.vote.vo.Option;
import com.zssq.vote.vo.PraisingOrNotVo;
import com.zssq.vote.vo.QueryMyCollectionListVo;
import com.zssq.vote.vo.ReplyingVo;
import com.zssq.vote.vo.SendVoteVo;
import com.zssq.vote.vo.SharingVoteVo;

/**
 * @ClassName VoteController
 * @Description 门户投票服务Controller
 * @author LiuYunLong
 * @date 2017年3月30日 下午2:02:32
 * @version 1.0
 * @since JDK 1.7
 */
@RequestMapping("/vote")
@Controller
public class VoteController extends BaseController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IVoteService voteService;
	@Autowired
	private IVoteAuthService voteAuthService;
	@Autowired
	private DynamicVoteProcedure dvp;
	@Autowired
	private ISysOrgService sysOrgService;
	@Autowired
	private IUserRelationService userRelationService;
	@Autowired
	private IVoteManageService voteManageService;
	
	/**
	 * 发起投票
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/launch", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON sendVote(@RequireValid SendVoteVo vo) throws BusinessException {
		try {
			if (!VoteConstants.SPONSOR_TYPE_PERSON.equals(vo.getSponsorType())) {
				// 如果是班组或管理员发起的投票，则发起机构CODE一定不能为空
				if (StringTools.isEmpty(vo.getSponsorOrgCode())) {
					throw BusinessException.build("COMMON_402", "sponsorOrgCode");
				}
			}
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			// 发布的动态编号
			String dynamicCode = "";
			long curTime = DateUtils.getFormatDateLong();
			/** 设置投票对象参数 */
			VoteInfo voteInfo = new VoteInfo();
			voteInfo.setCode(UUIDHelper.getUUID());
			voteInfo.setTenantCode(tenantCode);
			voteInfo.setOrgCode(orgCode);
			voteInfo.setCreateTime(curTime);
			voteInfo.setModifyTime(curTime);
			voteInfo.setVoteStatus(VoteConstants.STATUS_4);
			voteInfo.setSponsorCode(vo.getUserCode());
			voteInfo.setSponsorType(vo.getSponsorType());
			
			if (VoteConstants.SPONSOR_TYPE_CLASS.equals(vo.getSponsorType())) {
				// 班组或管理员发起，插入发起机构CODE
				voteInfo.setSponsorOrgCode(vo.getSponsorOrgCode());
			}
			if (VoteConstants.SPONSOR_TYPE_PERSON.equals(vo.getSponsorType())
					|| VoteConstants.SPONSOR_TYPE_CLASS.equals(vo.getSponsorType())) {
				// 个人或班组发起，插入发布动态的编号
				dynamicCode = UUIDHelper.getUUID();
				voteInfo.setDynamicCode(dynamicCode);
			}
			
			
			voteInfo.setTitle(vo.getTitle());
			if (StringTools.isNotEmpty(vo.getExplain())) {
				voteInfo.setVoteExplain(vo.getExplain());
			}
			voteInfo.setIsMultiOption(Byte.parseByte(vo.getIsMulti()));
			voteInfo.setIsEnableComment(Byte.parseByte(vo.getIsEnableComment()));
			voteInfo.setStartTime(curTime);
			voteInfo.setEndTime(DateUtils.getDateFromString(vo.getEndTime()+" 23:59:59", "yyyy-MM-dd HH:mm:ss").getTime());
			
			/** 设置权限对象参数 */
			VoteJoinAuth joinAuth = new VoteJoinAuth();
			joinAuth.setTenantCode(tenantCode);
			joinAuth.setOrgCode(orgCode);
			joinAuth.setIsCascade(Byte.parseByte(vo.getIsCascade()));// 是否范围级联:0-否  1-是
			joinAuth.setRangeType(Byte.parseByte(vo.getJoinRange()));// 参与范围:1-集团；2-省；3-市；4-班组
			String gRangeCode = "";
			String gRangeName = "";
			if (VoteConstants.RANGE_TYPE_CLASS.equals(Byte.parseByte(vo.getJoinRange()))) {
				// 如果参与范围是班组
				// 根据班组CODE查询出班组信息
				TeamInfo ti = getTeamInfoByCode(vo.getRangeCode());
				joinAuth.settRangeCode(vo.getRangeCode());// 参与范围班组CODE
				joinAuth.settRangeName(ti.getTeamName());// 参与方班组名称
			} else {
				OrgLinkList orgLinkList = getOrgTreeByAnyOrgCode(vo.getJoinRange(), vo.getRangeCode());
				gRangeCode = orgLinkList.getTopOrg().getSysOrgCode();
				gRangeName = orgLinkList.getTopOrg().getSysOrgName();
				if (VoteConstants.RANGE_TYPE_PRO.equals(Byte.parseByte(vo.getJoinRange()))
						|| VoteConstants.RANGE_TYPE_CITY.equals(Byte.parseByte(vo.getJoinRange()))) {
					// 如果是省范围，则新增省CODE和省名称
					SysOrgInfo province = orgLinkList.getProList().get(0);
					joinAuth.setpRangeCode(province.getSysOrgCode());// 参与范围省CODE
					joinAuth.setpRangeName(province.getSysOrgName());// 参与方省名
					if (VoteConstants.RANGE_TYPE_CITY.equals(Byte.parseByte(vo.getJoinRange()))) {
						// 如果是市范围，再新增市CODE和市名称
						SysOrgInfo city = orgLinkList.getCityList().get(0);
						joinAuth.setcRangeCode(city.getSysOrgCode());// 参与范围市CODE
						joinAuth.setcRangeName(city.getSysOrgName());// 参与方市名称
					}
				}
				joinAuth.setgRangeCode(gRangeCode);// 参与范围集团CODE
				joinAuth.setgRangeName(gRangeName);// 参与方集团名称
			}
			
			
			// 设置投票选项对象参数
			List<VoteOptions> options = new ArrayList<VoteOptions>();
			List<Option> list = vo.getOption();
			for (Option o : list) {
				VoteOptions v = new VoteOptions();
				v.setTenantCode(tenantCode);
				v.setOrgCode(orgCode);
				v.setOrderNumber(Integer.parseInt(o.getOrderNum()));
				v.setContent(o.getContent());
				
				options.add(v);
			}
			
			//插入数据
			String voteInfoCode = voteService.insertVoteInfo(voteInfo, options, joinAuth);
			
			/**发送"班组长在班组空间成功发布一次投票"行为信息*/
			Byte isDissolve = null;// 班组是否解散
			if (VoteConstants.SPONSOR_TYPE_CLASS.equals(vo.getSponsorType())) {
				TeamInfo teamInfo = getTeamInfoByCode(vo.getSponsorOrgCode());
				// 班组发起
				noticeAction(teamInfo.getOrgCode(), CreditConstants.COMMAND_VOTE_TEAMPUBLISH, teamInfo.getTeamCode(), CreditConstants.TYPE_TEAM);
				
				// 发送统计数据
				Thread thread = new Thread(new StatisticsVoteThread(ForumConstants.STATISTICS_OBJECT_TYPE_2, teamInfo.getOrgCode(),
						teamInfo.getTeamCode(), teamInfo.getTeamName()));
				thread.start();
				
				isDissolve = teamInfo.getIsDissolve();
			}
			/**发送"成功发布一条投票"行为信息*/
			if (VoteConstants.SPONSOR_TYPE_PERSON.equals(vo.getSponsorType())) {
				// 个人发起
				noticeAction(orgCode, CreditConstants.COMMAND_VOTE_PUBLISH, vo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
				
				// 发送统计数据
				Thread thread = new Thread(new StatisticsVoteThread(ForumConstants.STATISTICS_OBJECT_TYPE_1, orgCode,
						userInfo.getUserCode(), userInfo.getUserName()));
				thread.start();
			}
			
			try {
				// 如果是个人或者班组发表的投票，则要插入动态信息
				if (VoteConstants.SPONSOR_TYPE_PERSON.equals(vo.getSponsorType())
						|| VoteConstants.SPONSOR_TYPE_CLASS.equals(vo.getSponsorType())) {
					dvp.publishDynamicOfSendVote(voteInfo, isDissolve);
				}
			} catch (Exception e) {
				// 动态发布失败，则进行数据回滚
				log.info("动态发布失败，进行投票数据回滚操作...");
				voteService.deleteVoteInfo(tenantCode, voteInfoCode);
				log.error("动态发布失败，异常信息：", e);
				throw BusinessException.build("COMMON_400");
			}
			
			JSONObject body = new JSONObject();
			body.put("voteInfoCode", voteInfoCode);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.sendVote", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 参与投票
	 * @param vo
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/joinVoting", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON joinVote(@RequireValid JoinVoteVo vo) throws BusinessException {
		try {
			List<Integer> orders = new ArrayList<Integer>();
			try {
				String[] selectedStr = vo.getOptionsCode().split("\\|");
				for (String str : selectedStr) {
					orders.add(Integer.parseInt(str));
				}
			} catch (Exception e) {
				log.error("optionsCode参数非法：" + vo.getOptionsCode());
				throw BusinessException.build("COMMON_402", "optionsCode");
			}
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			//黑名单用户不能参与个人投票
			if(VoteConstants.YES.equals(isBlack(vo.getVoteInfoCode(), vo.getUserCode(), tenantCode))){
				throw BusinessException.build("VOTE_18012");
			}
			
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
			List<String> userTCodes = getUserCodeTeamCodes(vo.getUserCode());
			/**1.判断用户是否有权限参与此投票*/
			boolean authFlag = voteAuthService.judgeVoteAuthority(vo.getVoteInfoCode(), userGCode, userPCode, userCCode, userTCodes);
			if (!authFlag) {
				// 无权参与此投票
				throw BusinessException.build("COMMON_403");
			}
			/**2.调用参与投票service*/
			//组织参与投票参数对象
			JoinVoteEntity entity = new JoinVoteEntity();
			entity.setVoteInfoCode(vo.getVoteInfoCode());
			entity.setUserCode(vo.getUserCode());
			entity.setOptionsCode(vo.getOptionsCode());
			entity.setOptionsCodeList(orders);
			entity.setTenantCode(tenantCode);
			entity.setOrgCode(orgCode);
			
			//插入数据
			String joinCode = voteService.insertJoinVote(entity);
			
			/**发送"参与一次投票"行为信息*/
			noticeAction(orgCode, CreditConstants.COMMAND_VOTE_JOIN, vo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			/**发送"参与投票"动态*/
			dvp.publishDynamicOfJoinVote(tenantCode, orgCode, vo.getUserCode(), vo.getVoteInfoCode());
			
			JSONObject body = new JSONObject();
			body.put("joinCode", joinCode);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.joinVote", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 获取我/班组/门户发起的投票列表
	 * @param vo
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/getPublishedVoteList", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getPublishVoteList(@RequireValid GetPublishVoteListVo vo) throws BusinessException {
		try {
			if (VoteConstants.SPONSOR_TYPE_PERSON.equals(vo.getSponsorType())) {
				// 如果发起类型是1-个人，则发起人CODE不能为空
				if (StringTools.isEmpty(vo.getSponsorCode())) {
					throw BusinessException.build("COMMON_402", "sponsorCode");
				}
			} else {
				// 如果发起类型是2-班组 或  3-门户管理员，则发起机构CODE不能为空
				if (StringTools.isEmpty(vo.getSponsorOrgCode())) {
					throw BusinessException.build("COMMON_402", "sponsorOrgCode");
				}
			}
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			
			//组织查询对象
			VoteInfo info = new VoteInfo();
			info.setTenantCode(tenantCode);
			info.setSponsorType(vo.getSponsorType());
			info.setSponsorCode(vo.getSponsorCode());
			info.setSponsorOrgCode(vo.getSponsorOrgCode());
			if (StringTools.isNotEmpty(vo.getVoteStatus())) {
				info.setVoteStatus(Byte.parseByte(vo.getVoteStatus()));
			}
			info.setUserCode(vo.getUserCode());
			
			//构造分页数据
			Integer id = 0;
			if (StringTools.isNotEmpty(vo.getId())) {
				id = Integer.parseInt(vo.getId());
			}
			PageInfo page = new PageInfo(id, Integer.parseInt(vo.getPageSize()));
			
			//查询数据库
			PageInfo pageInfo = voteService.selectPublishVoteList(info, page);
			
			//组织返回数据
			List<VoteInfo> list = pageInfo.getList();
			JSONArray array = new JSONArray();
			if (list.size() > 0) {
				for (VoteInfo v : list) {
					JSONObject item = new JSONObject();
					item.put("id", StringTools.formatToString(v.getId()));
					item.put("code", v.getCode());
					item.put("sponsorCode", v.getSponsorCode());
					item.put("sponsorType", v.getSponsorType());
					SysUserInfo person = getUserInfo(v.getSponsorCode());
					if (person != null) {
						item.put("sponsorName", person.getUserName());
						item.put("sponsorHeadUrl", StringTools.formatToString(person.getHeadPortrait()));
					} else {
						item.put("sponsorName", "");
						item.put("sponsorHeadUrl", "");
					}
					String orgCode = "";
					if (VoteConstants.SPONSOR_TYPE_CLASS.equals(v.getSponsorType())) {
						// 班组所属机构code
						TeamInfo team = getTeamInfoByCode(v.getSponsorOrgCode());
						if (team != null) {
							orgCode = team.getOrgCode();
						}
					} else if (VoteConstants.SPONSOR_TYPE_MANAGER.equals(v.getSponsorType())) {
						orgCode = v.getSponsorOrgCode();
					}
					if (StringTools.isNotEmpty(orgCode)) {
						SysOrgInfo org = getOrgInfoByCode(orgCode);
						if (org != null) {
							item.put("sponsorOrgName", StringTools.formatToString(org.getSysOrgName()));
						} else {
							item.put("sponsorOrgName", "");
						}
					} else {
						item.put("sponsorOrgName", "");
					}
					item.put("sponsorOrgCode", StringTools.formatToString(v.getSponsorOrgCode()));
					
					//投票状态
					item.put("voteStatus", StringTools.formatToString(v.getVoteStatus()));
					item.put("title", v.getTitle());
					item.put("startTime", StringTools.formatToString(v.getStartTime()));
					item.put("endTime", StringTools.formatToString(v.getEndTime()));
					item.put("createTime", StringTools.formatToString(v.getCreateTime()));
					item.put("collectionNum", StringTools.formatToString(v.getCollectionNum()));
					item.put("shareNum", StringTools.formatToString(v.getShareNum()));
					item.put("commentNum", StringTools.formatToString(v.getCommentNum()));
					item.put("praiseNum", StringTools.formatToString(v.getPraiseNum()));
					item.put("joinNum", StringTools.formatToString(v.getJoinNum()));
					if (StringTools.isNotEmpty(v.getCollectionCode())) {
						item.put("isCollection", StringTools.formatToString(VoteConstants.YES));
					} else {
						item.put("isCollection", StringTools.formatToString(VoteConstants.NO));
					}
					if (StringTools.isNotEmpty(v.getPraiseCode())) {
						item.put("isPraise", StringTools.formatToString(VoteConstants.YES));
					} else {
						item.put("isPraise", StringTools.formatToString(VoteConstants.NO));
					}
					
					array.add(item);
				}
			}
			
			JSONObject body = new JSONObject();
			body.put("votesList", array);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.getPublishVoteList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 获取我/他人参与的投票列表
	 * @param vo
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/getJoinVoteList", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getJoinVoteList(@RequireValid GetJoinVoteListVo vo) throws BusinessException {
		
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			
			//组织查询参数实体
			GetMyJoinVoteListEntity entity = new GetMyJoinVoteListEntity();
			if(StringTools.isNotEmpty(vo.getVoteStatus())){
				entity.setVoteStatus(vo.getVoteStatus());
			}
			Integer id = 0;
			if (StringTools.isNotEmpty(vo.getId())) {
				id = Integer.parseInt(vo.getId());
			}
			entity.setId(id);
			entity.setPageSize(Integer.parseInt(vo.getPageSize()));
			entity.setTenantCode(tenantCode);
			entity.setUserCode(vo.getJoinUserCode());
			//查询数据库
			PageInfo page = voteService.getMyJoinVoteList(entity);		
			
			//组织返回数据
			List<VoteJoin> list = page.getList();
			JSONArray array = new JSONArray();
			if (list.size() > 0) {
				for (VoteJoin join : list) {
					VoteInfo v = join.getVoteInfo();
					JSONObject item = new JSONObject();
					item.put("id", StringTools.formatToString(v.getId()));
					item.put("code", v.getCode());
					
					item.put("sponsorCode", v.getSponsorCode());
					item.put("sponsorType", v.getSponsorType());
					SysUserInfo person = getUserInfo(v.getSponsorCode());
					if (person != null) {
						item.put("sponsorName", person.getUserName());
						item.put("sponsorHeadUrl", StringTools.formatToString(person.getHeadPortrait()));
					} else {
						item.put("sponsorName", "");
						item.put("sponsorHeadUrl", "");
					}
					if (VoteConstants.SPONSOR_TYPE_CLASS.equals(v.getSponsorType())) {
						// 班组名称
						TeamInfo team = getTeamInfoByCode(v.getSponsorOrgCode());
						if (team != null) {
							item.put("sponsorOrgName", team.getTeamName());
						}
					} else if (VoteConstants.SPONSOR_TYPE_MANAGER.equals(v.getSponsorType())) {
						// 门户名称
						SysOrgInfo org = getOrgInfoByCode(v.getSponsorOrgCode());
						if (org != null) {
							item.put("sponsorOrgName", org.getSysOrgName());
						}
					} else {
						item.put("sponsorOrgName", "");
					}
					item.put("sponsorOrgCode", StringTools.formatToString(v.getSponsorOrgCode()));
					
					item.put("voteStatus", StringTools.formatToString(v.getVoteStatus()));
					item.put("title", v.getTitle());
					item.put("startTime", StringTools.formatToString(v.getStartTime()));
					item.put("endTime", StringTools.formatToString(v.getEndTime()));
					item.put("joinTime", StringTools.formatToString(join.getCreateTime()));
					item.put("collectionNum", StringTools.formatToString(v.getCollectionNum()));
					item.put("shareNum", StringTools.formatToString(v.getShareNum()));
					item.put("commentNum", StringTools.formatToString(v.getCommentNum()));
					item.put("praiseNum", StringTools.formatToString(v.getPraiseNum()));
					item.put("joinNum", StringTools.formatToString(v.getJoinNum()));
					if (StringTools.isNotEmpty(v.getCollectionCode())) {
						item.put("isCollection", StringTools.formatToString(VoteConstants.YES));
					} else {
						item.put("isCollection", StringTools.formatToString(VoteConstants.NO));
					}
					if (StringTools.isNotEmpty(v.getPraiseCode())) {
						item.put("isPraise", StringTools.formatToString(VoteConstants.YES));
					} else {
						item.put("isPraise", StringTools.formatToString(VoteConstants.NO));
					}
					
					array.add(item);
				}
			}
			JSONObject body = new JSONObject();
			body.put("votesList", array);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.getJoinVoteList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 门户页面获取投票列表
	 * @param vo
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/getVoteListInGateway", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getGatewayVoteList(@RequireValid GetGatewayVoteListVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			
			// 默认从第0页开始
//			Integer pageNo = 0;
//			PageInfo pageInfo = new PageInfo(pageNo + 1, Integer.parseInt(vo.getPageSize()));
			
			PageInfo page = voteService.getGatewayVoteList(tenantCode, vo.getGatewayCode(), vo.getUserCode(), Integer.parseInt(vo.getPageSize()));
			
			List<VoteInfo> list = page.getList();
			JSONArray array = new JSONArray();
			if (list.size() > 0) {
				for (VoteInfo v : list) {
					JSONObject item = new JSONObject();
					item.put("id", StringTools.formatToString(v.getId()));
					item.put("code", v.getCode());
					item.put("gatewayCode", v.getSponsorOrgCode());
					SysOrgInfo org = sysOrgService.selectByCode(v.getSponsorOrgCode());
					if (org != null) {
						item.put("gatewayName", org.getSysOrgName());
					} else {
						item.put("gatewayName", "");
					}
					item.put("collectionNum", StringTools.formatToString(v.getCollectionNum()));
					item.put("shareNum", StringTools.formatToString(v.getShareNum()));
					item.put("commentNum", StringTools.formatToString(v.getCommentNum()));
					item.put("praiseNum", StringTools.formatToString(v.getPraiseNum()));
					item.put("joinNum", StringTools.formatToString(v.getJoinNum()));
					item.put("title", v.getTitle());
					item.put("startTime", StringTools.formatToString(v.getStartTime()));
					item.put("endTime", StringTools.formatToString(v.getEndTime()));
					item.put("createTime", StringTools.formatToString(v.getCreateTime()));
					item.put("voteStatus", StringTools.formatToString(v.getVoteStatus()));
					if (StringTools.isNotEmpty(v.getCollectionCode())) {
						item.put("isCollection", StringTools.formatToString(VoteConstants.YES));
					} else {
						item.put("isCollection", StringTools.formatToString(VoteConstants.NO));
					}
					if (StringTools.isNotEmpty(v.getPraiseCode())) {
						item.put("isPraise", StringTools.formatToString(VoteConstants.YES));
					} else {
						item.put("isPraise", StringTools.formatToString(VoteConstants.NO));
					}

					array.add(item);
				}
			}
			
			JSONObject body = new JSONObject();
			body.put("votesList", array);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.getGatewayVoteList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 查看投票详情
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getVoteInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getVoteInfo(@RequireValid GetVoteInfoVo vo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			String tenantCode = userInfo.getTenantCode();

			// 组织查询参数实体
			GetVoteInfoEntity entity = new GetVoteInfoEntity();
			entity.setVoteInfoCode(vo.getVoteInfoCode());
			entity.setTenantCode(tenantCode);
			entity.setUserCode(vo.getUserCode());
			// 查询数据库
			VoteDetailModel model = voteService.selectVoteInfoDetail(entity);
			
			VoteInfo info = model.getVoteInfo();
			if (info == null) {
				info = new VoteInfo();
			}
			
			//组织返回数据
			JSONObject body = new JSONObject();
			body.put("voteInfoCode", StringTools.formatToString(info.getCode()));
			body.put("sponsorCode", StringTools.formatToString(info.getSponsorCode()));
			// 查询发起人用户名和所属公司
			SysUserInfo sponsorInfo = getUserInfo(info.getSponsorCode());
			if (sponsorInfo.getOrgInfo() == null) {
				body.put("sponsorName", "");
				body.put("sponsorCompany", "");
			} else {
				body.put("sponsorName", StringTools.formatToString(sponsorInfo.getUserName()));
				body.put("sponsorCompany", StringTools.formatToString(sponsorInfo.getManageOrgInfo().getSysOrgName()));
			}
			
			body.put("startTime", StringTools.formatToString(info.getStartTime()));
			body.put("endTime", StringTools.formatToString(info.getEndTime()));
			body.put("createTime", StringTools.formatToString(info.getCreateTime()));
			body.put("title", StringTools.formatToString(info.getTitle()));
			body.put("explain", StringTools.formatToString(info.getVoteExplain()));
			body.put("collectionNum", StringTools.formatToString(info.getCollectionNum()));
			body.put("shareNum", StringTools.formatToString(info.getShareNum()));
			body.put("praiseNum", StringTools.formatToString(info.getPraiseNum()));
			body.put("joinNum", StringTools.formatToString(info.getJoinNum()));
			if (StringTools.isNotEmpty(info.getCollectionCode())) {
				body.put("isCollection", StringTools.formatToString(VoteConstants.YES));
			} else {
				body.put("isCollection", StringTools.formatToString(VoteConstants.NO));
			}
			if (StringTools.isNotEmpty(info.getPraiseCode())) {
				body.put("isPraise", StringTools.formatToString(VoteConstants.YES));
			} else {
				body.put("isPraise", StringTools.formatToString(VoteConstants.NO));
			}
			body.put("isMulti", StringTools.formatToString(info.getIsMultiOption()));
			body.put("isEnableComment", StringTools.formatToString(info.getIsEnableComment()));
			// 是否能提前结束0-否  1-是（判断条件：发起人CODE = userCode）
			if (vo.getUserCode().equals(info.getSponsorCode())) {
				// 如果该投票已经结束，则无提前结束说法
				if (info.getEndTime() < DateUtils.getFormatDateLong()) {
					body.put("canbeFinish", StringTools.formatToString(VoteConstants.NO));
				} else {
					body.put("canbeFinish", StringTools.formatToString(VoteConstants.YES));
				}
			} else {
				body.put("canbeFinish", StringTools.formatToString(VoteConstants.NO));
			}
			body.put("voteStatus", StringTools.formatToString(info.getVoteStatus()));
			
			VoteJoin voteJoin = model.getVoteJoin();
			// 参与状态：1-已参与  2-未参与（判断条件：voteJoin != null）
			String[] selected = {};
			if (voteJoin == null) {
				body.put("joinStatus", VoteConstants.JOIN_STATUS_2);
				body.put("joinTime", "");
			} else {
				body.put("joinStatus", VoteConstants.JOIN_STATUS_1);
				body.put("joinTime", StringTools.formatToString(voteJoin.getCreateTime()));
				selected = voteJoin.getSelectedNumber().split(VoteConstants.SPLIT_REGX);
			}
			// 选项总选择人数
			int totalSelectedNum = model.getTotalSelectedNum();
			// 组织选项列表数据
			List<VoteOptions> oList = model.getOptions();
			JSONArray array = new JSONArray();
			// userCode是否选择了此选项，默认0-否
			if (oList != null) {
				for (VoteOptions voteOptions : oList) {
					String isSelected = String.valueOf(VoteConstants.NO);
					JSONObject optionJSON = new JSONObject();
					optionJSON.put("optionCode", voteOptions.getCode());
					optionJSON.put("orderNum", StringTools.formatToString(voteOptions.getOrderNumber()));
					optionJSON.put("content", voteOptions.getContent());
					optionJSON.put("selectedNum", StringTools.formatToString(voteOptions.getSelectedNum()));
					optionJSON.put("percent", divide(voteOptions.getSelectedNum(), totalSelectedNum, 6) + "");
					for (int i = 0; i < selected.length; i++) {
						if (selected[i].equals(voteOptions.getOrderNumber()+"")) {
							isSelected = String.valueOf(VoteConstants.YES);
							break;
						}
					}
					optionJSON.put("isSelected", isSelected);
					
					array.add(optionJSON);
				}
			}
			body.put("optionsList", array);
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.getVoteInfo", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
	/**
	 * @Function collectingOrNot
	 * @Description 收藏/取消收藏
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "collectingOrNot", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON collectingOrNot(@RequireValid CollectingOrNotVo inVo) throws BusinessException {
		try{
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			//黑名单用户不能收藏个人投票
			if(VoteConstants.COLLECTION_TYPE_1.equals(inVo.getOperating()) && 
					VoteConstants.YES.equals(isBlack(inVo.getVoteInfoCode(), inVo.getUserCode(), tenantCode))){
				throw BusinessException.build("VOTE_18012");
			}
			
			//入参
			VoteCollection voteCollection = new VoteCollection();
			voteCollection.setTenantCode(tenantCode);
			voteCollection.setOrgCode(orgCode);
			voteCollection.setVoteInfoCode(inVo.getVoteInfoCode()); 
			voteCollection.setCollectorCode(inVo.getUserCode()); //收藏人Code
			voteCollection.setOperating(inVo.getOperating());
			
			Integer collectionNum = voteService.updateCollectionStatus(voteCollection);
			
			/**发送"收藏 "行为信息*/
			if (VoteConstants.COLLECTION_TYPE_1.equals(inVo.getOperating())) {
				//加积分操作
				noticeAction(orgCode, CreditConstants.COMMAND_CONTENT_COLLECT, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
				//更新收藏数（动态）
				dvp.saveVoteCollection(tenantCode, orgCode, "", inVo.getVoteInfoCode(), RelationConstants.RELATION_COLLECT_VOTE, inVo.getUserCode());	
			}
			/**发送"取消收藏 "行为信息*/
			if (VoteConstants.COLLECTION_TYPE_0.equals(inVo.getOperating())) {
				//减积分操作
				noticeAction(orgCode, CreditConstants.COMMAND_CONTENT_COLLECTCANCEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
				//更新收藏数（动态）
				dvp.deleteVoteCollection(inVo.getVoteInfoCode(), inVo.getUserCode());
				
			}
			
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("collectionNum", StringTools.formatToString(collectionNum));
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.collectingOrNot", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
	/**
	 * @Function praisingOrNot
	 * @Description 点赞/取消点赞投票、评论、回复
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "praisingOrNot", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON praisingOrNot(@RequireValid PraisingOrNotVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			//黑名单用户不能点赞个人投票
			String voteCode = voteCode(inVo.getInfoType(), inVo.getInfoCode(), tenantCode);
			if(VoteConstants.OPERAT_TYPE_1.equals(inVo.getOperating()) &&
					VoteConstants.YES.equals(isBlack(voteCode, inVo.getUserCode(), tenantCode))){
				throw BusinessException.build("VOTE_18012");
			}
			//入参
			VotePraise votePraise = new VotePraise();
			votePraise.setTenantCode(tenantCode);
			votePraise.setOrgCode(orgCode);
			votePraise.setAdmirerCode(inVo.getUserCode());  //点赞人
			votePraise.setInfoCode(inVo.getInfoCode());
			votePraise.setInfoType(Byte.valueOf(inVo.getInfoType()));
			votePraise.setOperating(inVo.getOperating());
			
			Integer praiseNum = voteService.updatePraiseStatus(votePraise);
			
			/** 如果是点赞操作，则异步发送消息通知提醒 and 发送"点赞"行为信息*/
			if (VoteConstants.OPERAT_TYPE_1.equals(inVo.getOperating())) {
				Thread thread = new Thread(new SendPraiseMsgThread(tenantCode, inVo.getInfoCode(), new Byte(inVo.getInfoType()), inVo.getUserCode()));
				thread.start();
				
				/**发送"点赞"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_ADMIRE_PUBLISH, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
				
				//点赞投票时更改动态点赞数
				if (inVo.getInfoType().equals(VoteConstants.INFO_TYPE_1)) {
					dvp.saveVoteLike(tenantCode, orgCode, "", inVo.getInfoCode(), RelationConstants.RELATION_LIKE_VOTE,
							inVo.getUserCode());
				}
			}
			/** 发送"取消点赞 "行为信息*/
			if (VoteConstants.OPERAT_TYPE_0.equals(inVo.getOperating())) {
				/**发送"取消点赞"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_ADMIRE_DEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
				
				// 取消点赞投票时更改动态点赞数
				if (inVo.getInfoType().equals(VoteConstants.INFO_TYPE_1)) {
					dvp.deleteVoteLike(inVo.getInfoCode(), inVo.getUserCode());
				}
			}
			
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("praiseNum", StringTools.formatToString(praiseNum));
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.praisingOrNot", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function getVoteCommentList
	 * @Description 查询投票评论列表
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "getVoteCommentList", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getVoteCommentList(@RequireValid GetVoteCommentListVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			//入参
			VoteComment voteComment = new VoteComment();
			voteComment.setTenantCode(tenantCode);
			voteComment.setOrgCode(orgCode);
			voteComment.setVoteInfoCode(inVo.getVoteInfoCode());
			voteComment.setPageSize(Integer.parseInt(inVo.getPageSize()));
			if (StringTools.isNotEmpty(inVo.getId())) {
				voteComment.setId(Long.valueOf(inVo.getId()));
			}
			voteComment.setAdmirerCode(inVo.getUserCode());  //点赞人
			
			PageInfo pageInfo = voteService.getVoteCommentList(voteComment);
			List<VoteComment> list = pageInfo.getList();
			JSONArray jsonArray = new JSONArray();
			for (VoteComment vi : list) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", StringTools.formatToString(vi.getId()));
				jsonObject.put("commentCode", vi.getCode());
				jsonObject.put("voteInfoCode", vi.getVoteInfoCode());
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
					// 评论人所属组织
					jsonObject.put("commenterOrg", "");
				}
				jsonObject.put("content", vi.getContent());
				jsonObject.put("createTime", StringTools.formatToString(vi.getCreateTime()));
				jsonObject.put("replyCount", StringTools.formatToString(vi.getReplyCount()));
				jsonObject.put("praiseCount", StringTools.formatToString(vi.getPraiseCount()));
				if (null != vi.getAdmirerCode()) {
					jsonObject.put("isPraise", StringTools.formatToString(VoteConstants.YES));
				} else {
					jsonObject.put("isPraise", StringTools.formatToString(VoteConstants.NO));
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
			log.error("VoteController.getVoteCommentList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function getVoteReplyList
	 * @Description 查询评论回复列表
	 * @param inVo 
	 * @return 
	 * @throws BusinessException
	 */
	@RequestMapping(value = "getVoteReplyList", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getVoteReplyList(@RequireValid GetVoteReplyListVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			// 入参
			VoteCommentReply voteCommentReply = new VoteCommentReply();
			voteCommentReply.setTenantCode(tenantCode);
			voteCommentReply.setOrgCode(orgCode);
			voteCommentReply.setVoteInfoCode(inVo.getVoteInfoCode());
			voteCommentReply.setCommentCode(inVo.getCommentCode());
			voteCommentReply.setPageSize(Integer.parseInt(inVo.getPageSize()));
			if (StringTools.isNotEmpty(inVo.getId())) {
				voteCommentReply.setId(Long.valueOf(inVo.getId()));
			}
			voteCommentReply.setAdmirerCode(inVo.getUserCode()); // 点赞人

			PageInfo pageInfo = voteService.getVoteCommentReplyList(voteCommentReply);
			List<VoteCommentReply> list = pageInfo.getList();
			JSONArray jsonArray = new JSONArray();
			for (VoteCommentReply vcr : list) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", StringTools.formatToString(vcr.getId()));
				jsonObject.put("replyCode", vcr.getCode());
				jsonObject.put("commentCode", vcr.getCommentCode());
				jsonObject.put("voteInfoCode", vcr.getVoteInfoCode());
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
					jsonObject.put("isPraise", StringTools.formatToString(VoteConstants.YES));
				} else {
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
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.getVoteReplyList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function commenting
	 * @Description 发表评论
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "commenting", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON commenting(@RequireValid CommentingVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			//黑名单用户不能评论个人投票
			if(VoteConstants.YES.equals(isBlack(inVo.getVoteInfoCode(), inVo.getUserCode(), tenantCode))){
				throw BusinessException.build("VOTE_18012");
			}
			// 入参
			VoteComment voteComment = new VoteComment();
			voteComment.setTenantCode(tenantCode);
			voteComment.setOrgCode(orgCode);
			voteComment.setCommenterCode(inVo.getUserCode());// 评论人Code
			voteComment.setContent(inVo.getContent());
			voteComment.setVoteInfoCode(inVo.getVoteInfoCode()); 
			String commentCode = voteService.addComment(voteComment);
			
			/**发送"成功发表一次评论"行为信息*/
			noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_PUBLISH, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			
			// 发表评论发送消息 ，只有个人发起的才发这个消息
			Thread thread = new Thread(new SendCommentOrReplyMsgThread(inVo.getVoteInfoCode(),voteComment,VoteConstants.INFO_TYPE_2,commentCode));
			thread.start();
			
			//更新评论数(动态)
			dvp.increaseCommentNum(inVo.getVoteInfoCode());
			
			VoteComment comment = voteService.selectCommentInfo(commentCode);
			JSONObject body = new JSONObject();
			body.put("id", StringTools.formatToString(comment.getId()));
			body.put("commentCode", StringTools.formatToString(comment.getCode()));
			body.put("voteInfoCode", StringTools.formatToString(comment.getVoteInfoCode()));
			body.put("commenterCode", StringTools.formatToString(comment.getCommenterCode()));
			body.put("commenterName", userInfo.getUserName());
			body.put("commenterUrl", StringTools.formatToString(userInfo.getHeadPortrait()));
			body.put("commenterOrg", StringTools.formatToString(userInfo.getManageOrgInfo().getSysOrgName()));
			body.put("content", StringTools.formatToString(comment.getContent()));
			body.put("createTime", StringTools.formatToString(comment.getCreateTime()));
			body.put("commentCount", "0");
			body.put("praiseCount", "0");
			body.put("isPraise", "0");
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.commenting", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function replying
	 * @Description 发表回复
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "replying", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON replying(@RequireValid ReplyingVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			//黑名单用户不能回复个人投票
			if(VoteConstants.YES.equals(isBlack(inVo.getVoteInfoCode(), inVo.getUserCode(), tenantCode))){
				throw BusinessException.build("VOTE_18012");
			}
			// 入参
			VoteCommentReply voteCommentReply = new VoteCommentReply();
			voteCommentReply.setTenantCode(tenantCode);
			voteCommentReply.setOrgCode(orgCode);
			voteCommentReply.setVoteInfoCode(inVo.getVoteInfoCode());
			voteCommentReply.setCommentCode(inVo.getCommentCode());
			voteCommentReply.setReplierCode(inVo.getUserCode());// 回复人Code
			voteCommentReply.setContent(inVo.getContent());
			voteCommentReply.setQuestionerCode(inVo.getQuestionerCode()); // 被回复人
			String replyCode = voteService.addReply(voteCommentReply);
			
			/**发送"成功回复一次评论"行为信息*/
			noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_REPLY, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			
			// 发表回复发送消息 
			Thread thread = new Thread(new SendCommentOrReplyMsgThread(inVo.getVoteInfoCode(),voteCommentReply,VoteConstants.INFO_TYPE_3,replyCode));
			thread.start();
			
			VoteCommentReply reply = voteService.selectReply(replyCode);
			
			JSONObject body = new JSONObject();
			body.put("id", StringTools.formatToString(reply.getId()));
			body.put("replyCode", StringTools.formatToString(reply.getCode()));
			body.put("commentCode", StringTools.formatToString(reply.getCommentCode()));
			body.put("voteInfoCode", StringTools.formatToString(reply.getVoteInfoCode()));
			body.put("replierCode", StringTools.formatToString(reply.getReplierCode()));
			body.put("replierName", StringTools.formatToString(userInfo.getUserName()));
			body.put("replierUrl", StringTools.formatToString(userInfo.getHeadPortrait()));
			body.put("replierOrg", StringTools.formatToString(userInfo.getManageOrgInfo().getSysOrgName()));
			body.put("questionerCode", StringTools.formatToString(reply.getQuestionerCode()));
			SysUserInfo person = getUserInfo(reply.getQuestionerCode());
			if(person != null){
				body.put("questionerName", StringTools.formatToString(person.getUserName()));
				body.put("questionerUrl", StringTools.formatToString(person.getHeadPortrait()));
				body.put("questionerOrg", StringTools.formatToString(person.getManageOrgInfo().getSysOrgName()));
			}else{
				body.put("questionerName", "");
				body.put("questionerUrl", "");
				body.put("questionerOrg", "");
			}
			body.put("content", StringTools.formatToString(reply.getContent()));
			body.put("createTime", StringTools.formatToString(reply.getCreateTime()));
			body.put("praiseCount", "0");
			body.put("isPraise", "0");
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.replying", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	/**
	 * @Function advanceFinishVote
	 * @Description 提前结束投票
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "advanceFinishVote", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON advanceFinishVote(@RequireValid AdvanceFinishVoteVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			// 入参
			long curTime = DateUtils.getFormatDateLong();
			VoteInfo voteInfo = new VoteInfo();
			voteInfo.setTenantCode(tenantCode);
			voteInfo.setOrgCode(orgCode);
			voteInfo.setCode(inVo.getVoteInfoCode());
			voteInfo.setUserCode(inVo.getUserCode());
			voteInfo.setEndTime(curTime);

			voteService.updateVoteInfoStatus(voteInfo);
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.advanceFinishVote", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function deleting
	 * @Description 删除投票/评论/回复
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "deletingMyInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON deleting(@RequireValid DeletingVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();

			// 1、是否是删除投票
			if (VoteConstants.INFO_TYPE_1.equals(inVo.getInfoType())) {
				// 获取投票详情
				VoteInfo voteInfo = voteService.getVoteInfoByCode(inVo.getInfoCode(), tenantCode);
				
				if (voteInfo != null) {
					String type = voteInfo.getSponsorType();
					// 个人发起
					if (VoteConstants.SPONSOR_TYPE_PERSON.equals(type)) {
						// 只有是本人发起的投票才有删除操作权限
						if (inVo.getUserCode().equals(voteInfo.getSponsorCode())) {
							voteService.deleteVote(tenantCode, inVo.getInfoCode());
							
							/**发送"删除一次我发布的投票"行为信息*/
							noticeAction(orgCode, CreditConstants.COMMAND_VOTE_DEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
						} else {
							// 操作权限不足
							throw BusinessException.build("COMMON_403");
						}
					}
					// 班组发起
					if (VoteConstants.SPONSOR_TYPE_CLASS.equals(type)) {
						List<String> list = selectTeamLeaderCodesByTeamCode(voteInfo.getSponsorOrgCode());
						// 获得班组CODE--sponsorOrgCode的班组长列表信息，判断userCode是否存在其中
						// 存在则具有删除权限，否则没有
						boolean flag = list.contains(inVo.getUserCode());
						if (flag) {
							voteService.deleteVote(tenantCode, inVo.getInfoCode());
							
							/**发送"班组长删除一次在班组空间发布的投票"行为信息*/
							TeamInfo teamInfo = getTeamInfoByCode(voteInfo.getSponsorOrgCode());
							noticeAction(teamInfo.getOrgCode(), CreditConstants.COMMAND_VOTE_TEAMPDEL, teamInfo.getTeamCode(), CreditConstants.TYPE_TEAM);
							
						} else {
							// 操作权限不足
							throw BusinessException.build("COMMON_403");
						}
					}
				} else {
					throw BusinessException.build("VOTE_18002", "投票信息");
				}
				/**删除相关动态信息*/
				dvp.deleteDynamicOfVote(voteInfo.getDynamicCode(), voteInfo.getCode(), voteInfo.getSponsorCode());
			}

			// 2、是否是删除评论
			if (VoteConstants.INFO_TYPE_2.equals(inVo.getInfoType())) {
				VoteComment vc = voteService.selectCommentInfo(inVo.getInfoCode());
				// 评论信息不存在
				if (StringTools.isEmpty(vc.getVoteInfoCode())) {
					throw BusinessException.build("VOTE_18002", "评论信息");
				}
				if (!vc.getCommenterCode().equals(inVo.getUserCode())) {
					// 您不是此评论或回复的发布人，无权操作。
					throw BusinessException.build("COMMON_403");
				}
				int refCount = voteService.deleteVoteComment(inVo.getInfoCode(), vc.getVoteInfoCode(), tenantCode);
				if (refCount > 0) {
					/**发送"删除一条我的评论"行为信息*/
					noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_DEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
					
					//更新评论数（动态）
					dvp.decreaseCommentNum(vc.getVoteInfoCode());
				}
			}

			// 3、是否是删除回复
			if (VoteConstants.INFO_TYPE_3.equals(inVo.getInfoType())) {
				voteService.deleteVoteCommentReply(inVo.getInfoCode(), inVo.getUserCode(), tenantCode);
				
				/**发送"删除一条我的回复"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_REPLYDEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			}

			// 设置返回结果集
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.deleting", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 分享投票
	 * @Function sharingVote
	 * @Description 
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "sharingVote", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON sharingVote(@RequireValid SharingVoteVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			//黑名单用户不能分享个人投票
			if(VoteConstants.YES.equals(isBlack(inVo.getVoteInfoCode(), inVo.getUserCode(), tenantCode))){
				throw BusinessException.build("VOTE_18012");
			}
						
			dvp.publishDynamicOfShareVote(tenantCode, orgCode, inVo.getUserCode(), inVo.getVoteInfoCode());
			
			//投票主表中的分享数+1
			voteService.updateIncreaseShareNumByCode(inVo.getVoteInfoCode(), 1);
			//增加分享数 （动态）
			//new Thread(new UpdateRelationDataThread(inVo.getVoteInfoCode(), RelationConstants.NUM_ONE, RelationConstants.RELATION_THIRD_DATANUM_TYPE_SHARE)).start();
			
			// 设置返回结果集
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.sharingVote", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
	/**
	 * @Function queryMyCollectionList
	 * @Description 查询我的收藏列表
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "queryMyCollectionList",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON queryMyCollectionList(@RequireValid QueryMyCollectionListVo inVo ) throws BusinessException {
		
		try{
			VoteCollection voteCollection = new VoteCollection();
			if(StringTools.isNotEmpty(inVo.getId())){
				voteCollection.setId(Long.parseLong(inVo.getId()));
			}
			voteCollection.setPageSize(Integer.parseInt(inVo.getPageSize()));
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			voteCollection.setCollectorCode(inVo.getUserCode());
			voteCollection.setTenantCode(tenantCode);
			
			List<VoteInfo> collectionList = voteService.getMyCollectionList(voteCollection);
			JSONArray jsonArray = new JSONArray();
			if(null != collectionList && collectionList.size() > 0){
				for (VoteInfo v : collectionList) {
					JSONObject item = new JSONObject();
					item.put("id", StringTools.formatToString(v.getId()));
					item.put("code", v.getCode());
					item.put("sponsorCode", v.getSponsorCode());
					item.put("sponsorType", v.getSponsorType());
					SysUserInfo person = getUserInfo(v.getSponsorCode());
					if (person != null) {
						item.put("sponsorName", person.getUserName()== null ? "":person.getUserName());
					} else {
						item.put("sponsorName", "");
					}
					if (VoteConstants.SPONSOR_TYPE_CLASS.equals(v.getSponsorType())) {
						// 班组名称
						TeamInfo team = getTeamInfoByCode(v.getSponsorOrgCode());
						if (team != null) {
							item.put("sponsorOrgName", team.getTeamName());
						}
					} else if (VoteConstants.SPONSOR_TYPE_MANAGER.equals(v.getSponsorType())) {
						// 门户名称
						SysOrgInfo org = getOrgInfoByCode(v.getSponsorOrgCode());
						if (org != null) {
							item.put("sponsorOrgName", org.getSysOrgName());
						}
					} else {
						item.put("sponsorOrgName", "");
					}
					item.put("sponsorOrgCode", StringTools.formatToString(v.getSponsorOrgCode()));
					
					//投票状态
					item.put("voteStatus", StringTools.formatToString(v.getVoteStatus()));
					item.put("title", v.getTitle());
					item.put("startTime", StringTools.formatToString(v.getStartTime()));
					item.put("endTime", StringTools.formatToString(v.getEndTime()));
					item.put("createTime", StringTools.formatToString(v.getCreateTime()));
					item.put("collectionNum", StringTools.formatToString(v.getCollectionNum()));
					item.put("shareNum", StringTools.formatToString(v.getShareNum()));
					item.put("commentNum", StringTools.formatToString(v.getCommentNum()));
					item.put("praiseNum", StringTools.formatToString(v.getPraiseNum()));
					item.put("joinNum", StringTools.formatToString(v.getJoinNum()));
					item.put("collectorTime", StringTools.formatToString(v.getCollectorTime()));
					if (StringTools.isNotEmpty(v.getPraiseCode())) {
						item.put("isPraise", StringTools.formatToString(VoteConstants.YES));
					} else {
						item.put("isPraise", StringTools.formatToString(VoteConstants.NO));
					}
					jsonArray.add(item);
				}
			}
			// 设置返回结果集
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("votesList", jsonArray);
			result.setBody(jsonObject);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("VoteController.queryMyCollectionList", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}
	
	/**
	 * @Function getVoteCommentFixedList
	 * @Description 获取投票评论列表 定位
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "getVoteCommentFixedList", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getVoteCommentFixedList(@RequireValid GetVoteCommentFixedListVo inVo) throws BusinessException {
		try {
			// 根据userCode 查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			//入参
			VoteComment voteComment = new VoteComment();
			voteComment.setTenantCode(tenantCode);
			voteComment.setOrgCode(orgCode);
			voteComment.setVoteInfoCode(inVo.getVoteInfoCode());
			voteComment.setCode(inVo.getCommentCode());
			voteComment.setPageSize(Integer.valueOf(inVo.getPageSize()));
			if (StringTools.isNotEmpty(inVo.getId())) {
				voteComment.setId(Long.valueOf(inVo.getId()));
			}
			voteComment.setAdmirerCode(inVo.getUserCode());  //点赞人
			
			PageInfo pageInfo = voteManageService.getVoteCommentFixedList(voteComment);
			List<VoteComment> list = pageInfo.getList();
			JSONArray jsonArray = new JSONArray();
			for (VoteComment vi : list) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", StringTools.formatToString(vi.getId()));
				jsonObject.put("commentCode", vi.getCode());
				jsonObject.put("voteInfoCode", vi.getVoteInfoCode());
				jsonObject.put("commenterCode", vi.getCommenterCode());
				// 根据 commenterCode 查询 commenterName commenterUrl commenterOrg
				SysUserInfo commenterInfo = getUserInfo(vi.getCommenterCode());
				if (commenterInfo != null) {
					jsonObject.put("commenterName", commenterInfo.getUserName());
					jsonObject.put("commenterUrl", StringTools.formatToString(commenterInfo.getHeadPortrait()));
					jsonObject.put("commenterOrg", commenterInfo.getManageOrgInfo().getSysOrgName());
				} else {
					jsonObject.put("commenterName", "");
					jsonObject.put("commenterUrl", "");
					jsonObject.put("commenterOrg", "");
				}
				jsonObject.put("content", vi.getContent());
				jsonObject.put("createTime", StringTools.formatToString(vi.getCreateTime()));
				jsonObject.put("replyCount", StringTools.formatToString(vi.getReplyCount()));
				jsonObject.put("praiseCount", StringTools.formatToString(vi.getPraiseCount()));
				if (null != vi.getAdmirerCode()) {
					jsonObject.put("isPraise", StringTools.formatToString(VoteConstants.YES));
				} else {
					jsonObject.put("isPraise", StringTools.formatToString(VoteConstants.NO));
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
			log.error("VoteController.getVoteCommentFixedList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function getVoteReplyList
	 * @Description 获取投票评论回复列表 定位
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "getVoteReplyFixedList", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getVoteReplyFixedList(@RequireValid GetVoteReplyFixedListVo inVo) throws BusinessException {
		try {
			// 根据userCode 查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			// 入参
			VoteCommentReply voteCommentReply = new VoteCommentReply();
			voteCommentReply.setTenantCode(tenantCode);
			voteCommentReply.setOrgCode(orgCode);
			voteCommentReply.setVoteInfoCode(inVo.getVoteInfoCode());
			voteCommentReply.setCommentCode(inVo.getCommentCode());
			voteCommentReply.setCode(inVo.getReplyCode());
			voteCommentReply.setPageSize(Integer.valueOf(inVo.getPageSize()));
			if (StringTools.isNotEmpty(inVo.getId())) {
				voteCommentReply.setId(Long.valueOf(inVo.getId()));
			}
			// 点赞人
			voteCommentReply.setAdmirerCode(inVo.getUserCode());
			
			
			PageInfo pageInfo = voteManageService.getVoteReplyFixedList(voteCommentReply);
			List<VoteCommentReply> list = pageInfo.getList();
			JSONArray jsonArray = new JSONArray();
			for (VoteCommentReply vcr : list) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", StringTools.formatToString(vcr.getId()));
				jsonObject.put("replyCode", vcr.getCode());
				jsonObject.put("commentCode", vcr.getCommentCode());
				jsonObject.put("voteInfoCode", vcr.getVoteInfoCode());
				jsonObject.put("replierCode", vcr.getReplierCode());
				// 根据 replierCode 查询 replierName replierUrl replierOrg
				SysUserInfo replierInfo = getUserInfo(vcr.getReplierCode());
				if(replierInfo != null){
					jsonObject.put("replierName", replierInfo.getUserName());
					jsonObject.put("replierUrl", StringTools.formatToString(replierInfo.getHeadPortrait()));
					jsonObject.put("replierOrg", StringTools.formatToString(replierInfo.getManageOrgInfo().getSysOrgName()));
				}else{
					jsonObject.put("replierName", "");
					jsonObject.put("replierUrl", "");
					jsonObject.put("replierOrg", "");
				}
				jsonObject.put("questionerCode", vcr.getQuestionerCode());
				// 根据 questionerCode 查询 questionerName questionerUrl questionerOrg
				SysUserInfo questionerInfo = getUserInfo(vcr.getQuestionerCode());
				if(questionerInfo != null){
					jsonObject.put("questionerName", questionerInfo.getUserName());
					jsonObject.put("questionerUrl", StringTools.formatToString(questionerInfo.getHeadPortrait()));
					jsonObject.put("questionerOrg", StringTools.formatToString(questionerInfo.getManageOrgInfo().getSysOrgName()));
				}else{
					jsonObject.put("questionerName", "");
					jsonObject.put("questionerUrl", "");
					jsonObject.put("questionerOrg", "");
				}
				jsonObject.put("content", vcr.getContent());
				jsonObject.put("createTime", StringTools.formatToString(vcr.getCreateTime()));
				jsonObject.put("praiseCount", StringTools.formatToString(vcr.getPraiseCount()));
				if (null != vcr.getAdmirerCode()) {
					jsonObject.put("isPraise", StringTools.formatToString(VoteConstants.YES));
				} else {
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
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteController.getVoteReplyList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	 * @Title: isBlack
	 * @Description: 投票是个人投票情况下，参与者是否是创建者的黑名单
	 * @param voteCode
	 * @param userCode
	 * @return 是黑名单返回 是    不是个人投票或不是黑名单返回 否
	 */
	private Byte isBlack(String voteCode, String userCode, String tenantCode) throws Exception{
		VoteInfo vote = voteService.getVoteInfoByCode(voteCode, tenantCode);
		if(vote == null){
			throw BusinessException.build("VOTE_18002","投票信息");
		}
		if(VoteConstants.SPONSOR_TYPE_PERSON.equals(vote.getSponsorType())){
			UserState userState = userRelationService.selectUserState(vote.getSponsorCode(), userCode);
			if(VoteConstants.YES.equals(userState.getIsBlack())){
				return VoteConstants.YES;
			}
		}
		return VoteConstants.NO;
	}
	
	/**
	 * 
	 * @Title: voteCode
	 * @Description: 根据信息类型查询投票code
	 * @param infoType
	 * @param infoCode
	 * @param tenantCode
	 * @return 投票code
	 * @throws Exception
	 */
	private String voteCode(String infoType ,String infoCode, String tenantCode) throws Exception{
		if(VoteConstants.INFO_TYPE_2.equals(infoType)){
			VoteComment comment = voteService.getCommentByCode(infoCode, tenantCode);
			return comment.getVoteInfoCode();
		}
		if(VoteConstants.INFO_TYPE_3.equals(infoType)){
			VoteCommentReply reply = voteService.getCommentReplyByCode(tenantCode, infoCode);
			return reply.getVoteInfoCode();
		}
		return infoCode;
	}
	
}
