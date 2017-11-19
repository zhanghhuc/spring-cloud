package com.zssq.team.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.config.AuthConfig;
import com.zssq.constants.AuthConstants;
import com.zssq.constants.MblogConstants;
import com.zssq.constants.TeamConstants;
import com.zssq.constants.VoteConstants;
import com.zssq.dao.model.TeamBlogModel;
import com.zssq.dao.model.TeamCommentModel;
import com.zssq.dao.model.TeamReplyModel;
import com.zssq.dao.pojo.CoinAccount;
import com.zssq.dao.pojo.ForumCommentReply;
import com.zssq.dao.pojo.ForumTopics;
import com.zssq.dao.pojo.ForumTopicsFollow;
import com.zssq.dao.pojo.HonorAwardRecord;
import com.zssq.dao.pojo.HonorComment;
import com.zssq.dao.pojo.HonorCommentReply;
import com.zssq.dao.pojo.IntegralAccount;
import com.zssq.dao.pojo.IntegralAndCoin;
import com.zssq.dao.pojo.MblogComment;
import com.zssq.dao.pojo.MblogModel;
import com.zssq.dao.pojo.MblogReply;
import com.zssq.dao.pojo.MblogResource;
import com.zssq.dao.pojo.MyCount;
import com.zssq.dao.pojo.RankEntity;
import com.zssq.dao.pojo.StatisticVisitDetail;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamFans;
import com.zssq.dao.pojo.TeamFriend;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.dao.pojo.TeamMember;
import com.zssq.dao.pojo.TeamMessage;
import com.zssq.dao.pojo.TeamMessageReply;
import com.zssq.dao.pojo.UserLevelConfig;
import com.zssq.dao.pojo.VoteComment;
import com.zssq.dao.pojo.VoteCommentReply;
import com.zssq.dao.pojo.VoteInfo;
import com.zssq.dao.pojo.VoteOptions;
import com.zssq.exceptions.BusinessException;
import com.zssq.fastdfs.config.FastDfsConf;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.BlogThirdService;
import com.zssq.service.HonorThirdService;
import com.zssq.service.ICoinAccountService;
import com.zssq.service.IExpAccountService;
import com.zssq.service.IForumThirdService;
import com.zssq.service.IIntegralAccountService;
import com.zssq.service.IStatisticService;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.ITeamMemberService;
import com.zssq.service.IUserRelationService;
import com.zssq.service.IVoteThirdService;
import com.zssq.service.MblogThridService;
import com.zssq.team.utils.FileUtil;
import com.zssq.team.vo.TeamArchivesVo;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;
import com.zssq.utils.file.FileHandler;
import com.zssq.utils.zip.ZipUtil;
import com.zssq.vo.BlogThirdVO;

@Controller
@RequestMapping("/teamRecord")
public class TeamRecordController {

	private Logger log = Logger.getLogger(this.getClass());

	/** 班组基本信息service */
	@Autowired
	private ITeamInfoService teamInfoService;

	/** 班组成员信息service */
	@Autowired
	private ITeamMemberService teamMemberService;

	/** 组织机构service */
	@Autowired
	private ISysOrgService sysOrgService;

	/** 用户service */
	@Autowired
	private ISysUserService sysUserService;

	/** 积分service */
	@Autowired
	private IIntegralAccountService integralAccountService;

	/** 金币service */
	@Autowired
	private ICoinAccountService coinAccountService;

	/** fastDfs配置 */
	@Autowired
	private FastDfsConf fastDfsConf;

	/** 经验服务 */
	@Autowired
	private IExpAccountService expAccountService;

	/** 好友关系service */
	@Autowired
	private IUserRelationService userRelationService;

	/** 获取配置的默认头像 */
	@Autowired
	private AuthConfig authConfig;

	/** 调用荣誉service */
	@Autowired
	private HonorThirdService honorThirdService;

	/** 获取访问量service */
	@Autowired
	private IStatisticService statisticService;
	
	/** 投票第三方 */
	@Autowired
	private IVoteThirdService voteThirdService;
	
	/** 论坛第三方 */
	@Autowired
	private IForumThirdService forumThirdService;
	
	/** 微博第三方 */
	@Autowired
	private MblogThridService mblogThridService;
	
	/** 博客第三方 */
	@Autowired
	private BlogThirdService blogThridService;

	/**
	 * @Function addTeamArchives
	 * @Description 班组归档
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/addTeamArchives")
	public ResultJSON addTeamArchives(@RequireValid TeamArchivesVo param, HttpServletRequest request, HttpServletResponse response) throws BusinessException {
		Message m = null;
		ResultJSON rj = new ResultJSON();
		String teamCode = param.getTeamCode();
		File file = null;
		String archivesFile = "";
		try {
			TeamInfo teamInfo = teamInfoService.selectByCode(param.getTeamCode());
			if (teamInfo == null) {//班组不存在,直接返回
				rj.setBody(new JSONObject());
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
				rj.setReturnCode(m.getCode());
				rj.setReturnTip("班组编码无效");
				return rj;
			}
			if (teamInfo.getIsRecord().equals(TeamConstants.ARCHIVING)) {// 归档中...
				rj.setBody(new JSONObject());
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
				rj.setReturnCode(m.getCode());
				rj.setReturnTip( "班组正在归档...");
				return rj;
			} else if (teamInfo.getIsRecord().equals(TeamConstants.BOOLEAN_TRUE)) {// 已归档....
				rj.setBody(new JSONObject());
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
				rj.setReturnCode(m.getCode());
				rj.setReturnTip("班组已归档");
				return rj;
			} else {// 变更标志,归档中,标志2
				teamInfoService.teamArchiving(teamCode, TeamConstants.ARCHIVING);
			}
			// 路径样例: \\opt\\archive\\team\\template\\";
			String indexFile = fastDfsConf.getTeamTemplate();//初始化模板路径
			// 路径样例:\\opt\\archive\\team\\archiveFile\\";
			archivesFile = fastDfsConf.getTeamArchive();// 资源文件(中转)路径
			String teamFile = archivesFile + teamCode + "\\";//指定文件夹  班组编码
			String teamZip = archivesFile + teamCode + ".zip";//压缩包路径
			file = new File(teamZip);
			if (file.exists()) {// 存在删除压缩包
				file.delete();
			}
			file = new File(teamFile);
			if (file.exists()) {// 存在删除班组文件夹
				FileUtil.deleteDir(file);
			}
			FileUtil.copyFolder(indexFile, teamFile);//复制模板
			String dataPath = teamFile + "data\\";//数据.js存放目录
			
			// 1.班组信息归档
			String teamInfoData = this.teamInfoData(teamInfo);
			FileHandler.createFile(dataPath, "teamInfoData", ".js", teamInfoData);

			// 2.班组成员信息归档
			String teamMemberData = this.teamMemberData(teamCode);
			FileHandler.createFile(dataPath, "teamMemberData", ".js", teamMemberData);

			// 3.班组粉丝
			String teamFansData = this.teamFansData(teamCode);
			FileHandler.createFile(dataPath, "teamFansData", ".js", teamFansData);

			// 4.友好班组
			String teamFriendsData = this.teamFriendsData(teamCode);
			FileHandler.createFile(dataPath, "teamFriendsData", ".js", teamFriendsData);

			// 5.班组访客
			String teamVisitorsData = this.teamVisitorsData(teamCode);
			FileHandler.createFile(dataPath, "teamVisitorsData", ".js", teamVisitorsData);

			// 6.班组留言
			String teamMessagesData = this.teamMessagesData(teamCode);
			FileHandler.createFile(dataPath, "teamMessagesData", ".js", teamMessagesData);

			// 7.班组成员贡献排行榜
			String memberDevoteData = this.memberDevoteData(teamCode);
			FileHandler.createFile(dataPath, "memberDevoteData", ".js", memberDevoteData);

			// 8.班组微博
			Map<String,String> mBlogMap = this.teamMBlogData(teamCode);
			FileHandler.createFile(dataPath, "mBlogArray", ".js", mBlogMap.get("mBlogArray"));
			FileHandler.createFile(dataPath, "mBlogFollowBody", ".js", mBlogMap.get("mBlogFollowBody"));
			
			// 9.班组博客
			Map<String,String> blogMap = this.teamBlogData(teamCode);
			FileHandler.createFile(dataPath, "blogArray", ".js", blogMap.get("blogArray"));
			FileHandler.createFile(dataPath, "blogFollowBody", ".js", blogMap.get("blogFollowBody"));
			
			// 10.班组投票
			Map<String,String> voteMap = this.teamVoteData(teamCode);
			FileHandler.createFile(dataPath, "teamVoteData", ".js", voteMap.get("voteArray"));
			FileHandler.createFile(dataPath, "voteFollowData", ".js", voteMap.get("voteFollowBody"));
			
			// 11.班组论坛
			Map<String,String> forumMap = this.teamForumData(teamCode);
			FileHandler.createFile(dataPath, "teamForumData", ".js", forumMap.get("forumArray"));
			FileHandler.createFile(dataPath, "forumFollowData", ".js", forumMap.get("forumFollowBody"));
			
			// 12.班组荣誉
			Map<String,String> honorMap = this.teamHonorData(teamCode);
			FileHandler.createFile(dataPath, "teamHonorData", ".js", honorMap.get("honorArray"));
			FileHandler.createFile(dataPath, "honorFollowData", ".js", honorMap.get("honorFollowBody"));
			
			// 打包，上传到文件服务器
			ZipUtil.zip(teamFile ,archivesFile , teamCode + ".zip");
			String result = upload(archivesFile + teamCode + ".zip", request, response);
			String url = urlAnalysis(result);

			// 删除历史文件夹及压缩包
			FileUtil.deleteDir(file);//删除班组文件夹
			FileHandler.delFile(archivesFile, teamCode, ".zip");//删除班组压缩包
			
			// 变更数据库
			teamInfoService.teamArchive(param.getTeamCode(), url, param.getUserCode());// 变更数据库
			
			rj = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
//			body.put("url", url);
			rj.setBody(body);
			log.info("班组归档成功");
		} catch (Exception e) {
			//删除文件
			if(file != null){
				FileUtil.deleteDir(file);//删除班组文件夹
				FileHandler.delFile(archivesFile, teamCode, ".zip");//删除班组压缩包
			}
			// 归档失败,归档标志2-->0
			teamInfoService.teamArchiving(teamCode, TeamConstants.BOOLEAN_FALSE);
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("班组归档", e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(), "班组归档"));
		}
		return rj;
	}

	/**
	 * @Function teamInfoDate
	 * @Description 班组归档
	 * @param teamInfo
	 *            班组信息对象
	 * @param filePath
	 *            班组头像存放路径
	 * @return
	 * @throws Exception
	 */
	private String teamInfoData(TeamInfo teamInfo) throws Exception {
		try {
			// 拼接班组信息
			JSONObject teamInfoJson = new JSONObject();
			if(StringTools.isEmpty(teamInfo.getTeamIcon())){
				teamInfoJson.put("teamIcon", authConfig.getTeamIcon());// 头像
			}else{
				teamInfoJson.put("teamIcon", StringTools.formatToString(teamInfo.getTeamIcon()));// 头像
			}
			teamInfoJson.put("teamName", StringTools.formatToString(teamInfo.getTeamName()));// 名称
			if (teamInfo.getTeamType().equals(TeamConstants.TEAM_TYPE_1)) {
				teamInfoJson.put("teamType", TeamConstants.TEAM_TYPE_NAME_1);// 类型
			} else {
				teamInfoJson.put("teamType", TeamConstants.TEAM_TYPE_NAME_0);
			}
			CoinAccount coinAccount = coinAccountService.selectByAccountCode(teamInfo.getTeamCode());
			if (coinAccount != null) {
				teamInfoJson.put("gold", StringTools.formatToString(coinAccount.getCoinBalance()));// 金币
			} else {
				teamInfoJson.put("gold", "0");// 金币
			}
			IntegralAccount integralAccount = integralAccountService.selectByAccountCode(teamInfo.getTeamCode());
			if (integralAccount != null) {
				teamInfoJson.put("score", StringTools.formatToString(integralAccount.getIntegralBalance()));// 积分
			} else {
				teamInfoJson.put("score", "0");// 积分
			}
			SysOrgInfo orgInfo = sysOrgService.selectByCode(teamInfo.getOrgCode());
			if (orgInfo != null) {
				teamInfoJson.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));// 组织机构名称
			} else {
				teamInfoJson.put("orgName", "");
			}
			teamInfoJson.put("intro", StringTools.formatToString(teamInfo.getTeamIntro()));// 班组简介
			String teamInfoData = "var data = " + teamInfoJson.toString();
			return teamInfoData;
		} catch (Exception e) {
			log.error("班组归档", e);
			throw e;
		}
	}

	/**
	 * @Function teamMemberDate
	 * @Description 班组成员归档
	 * @param teamCode
	 *            班组编码
	 * @param filePath
	 *            班组成员头像存放路径
	 * @return
	 * @throws Exception
	 */
	private String teamMemberData(String teamCode) throws Exception {
		try {
			TeamMember memberRecord = new TeamMember();
			memberRecord.setTeamCode(teamCode);
			PageBean memberPageBean = teamMemberService.getTeamMemList(null, memberRecord);
			List<TeamMember> teamMemberList = memberPageBean.getRecordList();
			JSONArray memberArray = new JSONArray();
			for (int i = 0; i < teamMemberList.size(); i++) {
				JSONObject memberJson = new JSONObject();
				TeamMember teamMember = teamMemberList.get(i);
				SysUserInfo userInfo = sysUserService.selectByCode(teamMember.getUserCode());
				if (userInfo != null) {
					if(StringTools.isEmpty(userInfo.getHeadPortrait())){
						memberJson.put("userHead", authConfig.getUserHead());// 头像
					}else{
						memberJson.put("userHead", StringTools.formatToString(userInfo.getHeadPortrait()));// 头像
					}
					memberJson.put("userName", userInfo.getUserName());
					SysOrgInfo orgInfo = userInfo.getManageOrgInfo();
					if (orgInfo != null) {
						memberJson.put("orgName", orgInfo.getSysOrgFullname());
					} else {
						memberJson.put("orgName", "");
					}
					UserLevelConfig userLevelConfig = expAccountService.selectMultiByCode(teamMember.getUserCode());
					if (userLevelConfig != null) {
						memberJson.put("level", StringTools.formatToString(userLevelConfig.getLevelNo()));// 等级
						memberJson.put("title", StringTools.formatToString(userLevelConfig.getLevelTitle()));// 称号
					} else {
						memberJson.put("level", "1");
						memberJson.put("title", "儒生");
					}
				} else {
					memberJson.put("userHead", "");
					memberJson.put("userName", "");
					memberJson.put("orgName", "");
					memberJson.put("level", "");
					memberJson.put("title", "");
				}
				if (teamMember.getIsLeader().equals(TeamConstants.BOOLEAN_1)) {
					memberJson.put("isLeader", TeamConstants.IS_LEADER_TITLE_1);
				} else {
					memberJson.put("isLeader", TeamConstants.IS_LEADER_TITLE_0);
				}
				memberArray.add(memberJson);
			}
			String teamMemberData = "var data = " + memberArray.toString();
			return teamMemberData;
		} catch (Exception e) {
			log.error("班组归档", e);
			throw e;
		}
	}

	/**
	 * @Function teamFansData
	 * @Description 班组粉丝归档
	 * @param teamCode
	 *            班组编码
	 * @param filePath
	 *            粉丝头像存放路径
	 * @return
	 * @throws Exception
	 */
	private String teamFansData(String teamCode) throws Exception {
		try {
			PageBean pageBean = teamInfoService.selectFans(null, teamCode);
			List<TeamFans> fans = pageBean.getRecordList();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < fans.size(); i++) {
				JSONObject info = new JSONObject();
				TeamFans teamFans = fans.get(i);
				SysUserInfo userInfo = sysUserService.selectByCode(teamFans.getUserCode());// 查询用户
				if (userInfo != null) {
					info.put("userName", StringTools.formatToString(userInfo.getUserName()));// 粉丝名称
					if(StringTools.isEmpty(userInfo.getHeadPortrait())){
						info.put("userHead", authConfig.getUserHead());// 头像
					}else{
						info.put("userHead", StringTools.formatToString(userInfo.getHeadPortrait()));// 头像
					}
					SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(userInfo.getOrgCode());
					if (sysOrgInfo != null) {
						info.put("orgName", StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));// 组织机构名称
					} else {
						info.put("orgName", "");
					}
					MyCount myCount = userRelationService.selectMyCount(teamFans.getUserCode());
					info.put("concernsCount", StringTools.formatToString(myCount.getConcernsCount()));// 关注数量
					info.put("friendsCount", StringTools.formatToString(myCount.getFriendsCount()));// 好友数量
					info.put("fansCount", StringTools.formatToString(myCount.getFansCount()));// 粉丝数量
				} else {
					info.put("userName", "");
					info.put("head", "");
					info.put("orgName", "");
					info.put("concernsCount", "");
					info.put("friendsCount", "");
					info.put("fansCount", "");
				}
				jsonArray.add(info);
			}
			String teamFansData = "var data = " + jsonArray.toString();
			return teamFansData;
		} catch (Exception e) {
			log.error("班组粉丝归档", e);
			throw e;
		}
	}

	/**
	 * @Function teamFriendsData
	 * @Description 友好班组归档
	 * @param teamCode
	 *            班组编码
	 * @param filePath
	 *            班组头像存放路径
	 * @return
	 * @throws Exception
	 */
	private String teamFriendsData(String teamCode) throws Exception {
		try {
			PageBean pageBean = teamInfoService.selectFriendTeam(null, teamCode);
			List<TeamFriend> friendList = pageBean.getRecordList();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < friendList.size(); i++) {
				JSONObject info = new JSONObject();
				info.put("teamCode", StringTools.formatToString(friendList.get(i).getFriendTeamCode()));// 班组code
				if(StringTools.isEmpty(friendList.get(i).getTeamIcon())){
					info.put("teamIcon", authConfig.getTeamIcon());// 头像
				}else{
					info.put("teamIcon", StringTools.formatToString(friendList.get(i).getTeamIcon()));// 头像
				}
				info.put("name", StringTools.formatToString(friendList.get(i).getTeamName()));// 班组名称
				int honor = honorThirdService.getHonorCount(friendList.get(i).getFriendTeamCode(), AuthConstants.TENANT_CODE);
				info.put("honor", honor);// 班组荣誉数量
				Long createTime = friendList.get(i).getCreateTime();
				if (createTime != null) {
					info.put("createTime", DateUtils.getDateString(createTime, "yyyy年MM月dd日"));// 班组创建时间
				} else {
					info.put("createTime", "");
				}
				SysOrgInfo orgInfo = sysOrgService.selectByCode(friendList.get(i).getOrgCode());
				if (orgInfo != null) {
					info.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));// 班组所属组织机构名称
				} else {
					info.put("orgName", "");
				}
				info.put("intro", StringTools.formatToString(friendList.get(i).getTeamIntro()));// 班组简介
				jsonArray.add(info);
			}
			String teamFriendsData = "var data = " + jsonArray.toString();
			return teamFriendsData;
		} catch (Exception e) {
			log.error("友好班组归档", e);
			throw e;
		}
	}

	/**
	 * @Function teamVisitorsData
	 * @Description 班组访客归档
	 * @param teamCode
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private String teamVisitorsData(String teamCode) throws Exception {
		try {
			StatisticVisitDetail record = new StatisticVisitDetail();
			record.setTeamCode(teamCode);
			PageBean pageBean = statisticService.getVisit(null, record);// 调用访客接口
			JSONArray jsonArray = new JSONArray();
			List<StatisticVisitDetail> visitorList = pageBean.getRecordList();
			for (int i = 0; i < visitorList.size(); i++) {
				JSONObject info = new JSONObject();
				SysUserInfo userInfo = sysUserService.selectByCode(visitorList.get(i).getVisitCode());
				if(userInfo != null){
					if(StringTools.isEmpty(userInfo.getHeadPortrait())){
						info.put("head", authConfig.getUserHead());// 头像
					}else{
						info.put("head", StringTools.formatToString(userInfo.getHeadPortrait()));// 头像
					}
					info.put("userName", StringTools.formatToString(userInfo.getUserName()));// 用户名
					info.put("userCode", StringTools.formatToString(userInfo.getUserCode()));// 用户code
					info.put("visitTime", DateUtils.getDateString(visitorList.get(i).getVisitTime(), "yyyy-MM-dd HH:mm"));// 访问时间
					jsonArray.add(info);
				}
			}
			String teamVisitorsData = "var data = " + jsonArray.toString();
			return teamVisitorsData;
		} catch (Exception e) {
			log.error("班组访客归档", e);
			throw e;
		}
	}

	/**
	 * @Function teamMessagesData
	 * @Description 班组留言归档
	 * @param teamCode
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private String teamMessagesData(String teamCode) throws Exception {
		try {
			int size = 50;
			List<TeamMessage> messageList = new ArrayList<TeamMessage>();
			int pageNo = 0;
			while(true){
				PageParam pageParam = new PageParam(pageNo, size);
				PageBean pageBean = teamInfoService.selectTeamMeassage(pageParam, teamCode);
				if(pageBean.getRecordList().size() == 0){
					break;
				}else{
					List<TeamMessage> messageOne = pageBean.getRecordList();
					messageList.addAll(messageOne);
					pageNo++;
				}
			}
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < messageList.size(); i++) {
				JSONObject info = new JSONObject();
				SysUserInfo userInfo = sysUserService.selectByCode(messageList.get(i).getUserCode());
				if (userInfo != null) {
					if(StringTools.isEmpty(userInfo.getHeadPortrait())){
						info.put("head", authConfig.getUserHead());// 回复人头像
					}else{
						info.put("head", StringTools.formatToString(userInfo.getHeadPortrait()));// 回复人头像
					}
					info.put("userName", StringTools.formatToString(userInfo.getUserName()));// 用户名
					SysOrgInfo orgInfo = sysOrgService.selectByCode(userInfo.getOrgCode());
					if (orgInfo != null) {
						info.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));// 组织机构名称
					} else {
						info.put("orgName", "");
					}
					JSONArray replyArray = new JSONArray();
					List<TeamMessageReply> replyList = messageList.get(i).getTeamMessageReply();
					info.put("replyCount", replyList.size());// 回复总条数
					if (replyList.size() > 0) {// 循环留言
						for (int j = 0; j < replyList.size(); j++) {
							JSONObject reply = new JSONObject();
							reply.put("replyMessage", StringTools.formatToString(replyList.get(j).getReplyMessaage()));// 回复内容
							userInfo = sysUserService.selectByCode(replyList.get(j).getUserCode());
							if (userInfo != null) {
								if(StringTools.isEmpty(userInfo.getHeadPortrait())){
									reply.put("replyUserHead", authConfig.getUserHead());// 回复人头像
								}else{
									reply.put("replyUserHead", StringTools.formatToString(userInfo.getHeadPortrait()));// 回复人头像
								}
								reply.put("replyUserName", StringTools.formatToString(userInfo.getUserName()));// 回复人
								reply.put("replyOrgName",StringTools.formatToString(userInfo.getManageOrgInfo().getSysOrgFullname()));// 回复人组织
							} else {
								reply.put("replyUserHead", "");
								reply.put("replyUserName", "");
								reply.put("replyOrgName", "");// 回复人
							}
							userInfo = sysUserService.selectByCode(replyList.get(j).getReplyUserCode());
							if (userInfo != null) {
								reply.put("receUserName", StringTools.formatToString(userInfo.getUserName()));// 被回复人
								reply.put("receOrgName", StringTools.formatToString(userInfo.getManageOrgInfo().getSysOrgFullname()));// 被回复人组织
							} else {
								reply.put("receUserName", "");// 被回复人
								reply.put("receOrgName", "");// 被回复人组织
							}
							if(replyList.get(j).getCreateTime() != null){
								reply.put("replyTime", DateUtils.getDateString(replyList.get(j).getCreateTime(), "yyyy-MM-dd HH:mm"));// 回复时间
							}else{
								reply.put("replyTime", "");
							}
							replyArray.add(reply);
						}
					}
					info.put("replyList", replyArray);
					info.put("message", StringTools.formatToString(messageList.get(i).getContent()));
					Long createTime = messageList.get(i).getCreateTime();
					if (createTime != null) {
						info.put("createTime", DateUtils.getDateString(createTime, "yyyy-MM-dd HH:mm"));
					} else {
						info.put("createTime", "");
					}
				}
				jsonArray.add(info);
			}
			String teamMessagesData = "var data = " + jsonArray.toString();
			return teamMessagesData;
		} catch (Exception e) {
			log.error("班组留言归档", e);
			throw e;
		}
	}

	/**
	 * @Function memberDevoteData
	 * @Description 班组成员贡献归档
	 * @param teamCode
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private String memberDevoteData(String teamCode) throws Exception {
		try {
			List<TeamMember> teamMembers = teamMemberService.selectByTeamCode(teamCode);
			List<String> userCodes = new ArrayList<String>();
			for (int i = 0; i < teamMembers.size(); i++) {
				userCodes.add(teamMembers.get(i).getUserCode());
			}
			if (userCodes.size() == 0) {
				return "";
			}
			RankEntity record = new RankEntity();
			record.setList(userCodes);
			PageBean pageBean = integralAccountService.selectPageByRank(null, record);
			JSONArray jsonArray = new JSONArray();
			List<IntegralAndCoin> recordList = pageBean.getRecordList();
			for (int i = 0; i < recordList.size(); i++) {
				JSONObject info = new JSONObject();
				info.put("rank", StringTools.formatToString(recordList.get(i).getRownum()));// 排名
				SysUserInfo userInfo = sysUserService.selectByCode(recordList.get(i).getAccountCode());
				if (userInfo != null) {
					info.put("userCode", StringTools.formatToString(userInfo.getUserCode()));
					if(StringTools.isEmpty(userInfo.getHeadPortrait())){
						info.put("head", authConfig.getUserHead());// 头像
					}else{
						info.put("head", StringTools.formatToString(userInfo.getHeadPortrait()));// 头像
					}
					info.put("userName", StringTools.formatToString(userInfo.getUserName()));// 用户名
					info.put("score", StringTools.formatToString(recordList.get(i).getIntegralBalance()));// 积分
					info.put("gold", StringTools.formatToString(recordList.get(i).getCoinBalance()));// 金币
				} else {
					info.put("userCode", "");
					info.put("head", "");
					info.put("userName", "");
					info.put("score", "");
					info.put("gold", "");
				}
				jsonArray.add(info);
			}
			String memberDevoteData = "var data = " + jsonArray.toString();
			return memberDevoteData;
		} catch (Exception e) {
			log.error("班组成员贡献归档", e);
			throw e;
		}
	}
	
	private Map<String,String> teamMBlogData(String teamCode) throws Exception {
		try {
			// 获取数据
			int start = 0;
			int size = 30;
			List<MblogModel> mBlogList = new ArrayList<MblogModel>();
			JSONArray mBlogArray = new JSONArray();
			JSONObject mBlogFollowBody = new JSONObject();
			while(true){
				mBlogList = mblogThridService.getTeamMblogList(teamCode, start, size);
				if(mBlogList.size() == 0){
					break;
				}else{
					start = start + size;
					this.teamMBlogData(mBlogList, mBlogArray, mBlogFollowBody);
					mBlogList.clear();
				}
			}
			Map<String,String> map = new HashMap<String,String>();
			map.put("mBlogArray", "var data = " + mBlogArray.toString());
			map.put("mBlogFollowBody", "var data = " + mBlogFollowBody.toString());
			return map;
		} catch (Exception e) {
			log.error("班组微博归档", e);
			throw e;
		}
	}
	
	private void teamMBlogData(List<MblogModel> mBlogList,JSONArray mBlogArray,JSONObject mBlogFollowBody) throws BusinessException{
		for (int i = 0; i < mBlogList.size(); i++) {
			//微博
			JSONObject mBlogJson = new JSONObject();
			MblogModel mBolg = mBlogList.get(i);
			SysUserInfo sponsor = sysUserService.selectByCode(mBolg.getUserCode());
			if(sponsor != null){
				mBlogJson.put("userName", StringTools.formatToString(sponsor.getUserName()));
				mBlogJson.put("userHead", StringTools.formatToString(sponsor.getHeadPortrait()));
				SysOrgInfo orgInfo = sponsor.getManageOrgInfo();
				if(orgInfo != null){
					mBlogJson.put("orgName", StringTools.formatToString(orgInfo.getSysOrgName()));
				}else{
					mBlogJson.put("orgName", "");
				}
			}else{
				mBlogJson.put("userName", "");
				mBlogJson.put("userHead", "");
				mBlogJson.put("orgName", "");
			}
			mBlogJson.put("createTime", DateUtils.getDateString(mBolg.getCreateTime(), "yyyy-MM-dd HH:mm"));
			JSONObject resJson = new JSONObject();
			resJson.put("img", "");
			resJson.put("audio", "");
			resJson.put("video", "");
			resJson.put("summary", "");
			resJson.put("text", "");
			resJson.put("topic", "");
			String img = "";
			for (int j = 0; j < mBolg.getResList().size(); j++) {
				MblogResource res = mBolg.getResList().get(j);
				if(MblogConstants.MBLOG_RESOURCE_IMG == res.getResType().byteValue()){//图片
					img += res.getContent()+",";
				}else if(MblogConstants.MBLOG_RESOURCE_AUDIO == res.getResType().byteValue()){//音频
					resJson.put("audio", res.getContent());
				}else if(MblogConstants.MBLOG_RESOURCE_VIDEO == res.getResType().byteValue()){//视频
					resJson.put("video", res.getContent());
				}else if(MblogConstants.MBLOG_RESOURCE_SUMMARY == res.getResType().byteValue()){
					resJson.put("summary", res.getContent());
				}else if(MblogConstants.MBLOG_RESOURCE_TEXT == res.getResType().byteValue()){
					resJson.put("text", res.getContent());
				}else if(MblogConstants.MBLOG_RESOURCE_TOPIC == res.getResType().byteValue()){
					resJson.put("topic", res.getContent());
				}
			}
			if(img.length()>0){
				resJson.put("img", img.substring(0, img.length()-1));
			}
			mBlogJson.put("res", resJson);
			mBlogJson.put("praiseNum", StringTools.formatToString(mBolg.getPraiseNum()));
			mBlogJson.put("commentNum", StringTools.formatToString(mBolg.getCommentNum()));
			mBlogJson.put("forwardNum", StringTools.formatToString(mBolg.getForwardNum()));
			mBlogJson.put("collectNum", StringTools.formatToString(mBolg.getCollectNum()));
			mBlogJson.put("mblogCode", StringTools.formatToString(mBolg.getMblogCode()));
			mBlogArray.add(mBlogJson);
			//回复及评论
			JSONArray followArray = new JSONArray();
			for (int j = 0; j < mBolg.getCommentList().size(); j++) {
				MblogComment mblogComment = mBolg.getCommentList().get(j);
				JSONObject mblogCommentJson = new JSONObject();
				SysUserInfo commenter = sysUserService.selectByCode(mBolg.getUserCode());
				if(commenter != null){
					mblogCommentJson.put("userName", StringTools.formatToString(commenter.getUserName()));
					mblogCommentJson.put("userHead", StringTools.formatToString(commenter.getHeadPortrait()));
					if(commenter.getManageOrgInfo() != null){
						mblogCommentJson.put("orgName", StringTools.formatToString(commenter.getManageOrgInfo().getSysOrgName()));
					}else{
						mblogCommentJson.put("orgName", "");
					}
				}else{
					mblogCommentJson.put("userName", "");
					mblogCommentJson.put("userHead", "");
					mblogCommentJson.put("orgName", "");
				}
				if(mblogComment.getCreateTime() != null){
					mblogCommentJson.put("createTime", DateUtils.getDateString(mblogComment.getCreateTime(), "yyyy-MM-dd HH:mm"));
				}else{
					mblogCommentJson.put("createTime", "");
				}
				mblogCommentJson.put("content", StringTools.formatToString(mblogComment.getContent()));
				mblogCommentJson.put("praiseNum", StringTools.formatToString(mblogComment.getPraiseNum()));
				
				JSONArray followReplyArray = new JSONArray();
				for (int k = 0; k < mblogComment.getReplyList().size(); k++) {
					JSONObject commentReplyJson = new JSONObject();
					MblogReply mblogCommentReply = mblogComment.getReplyList().get(k);
					SysUserInfo user = sysUserService.selectByCode(mblogCommentReply.getUserCode());
					SysUserInfo replyedUser = sysUserService.selectByCode(mblogCommentReply.getReplyedUserCode());
					if(user != null){
						commentReplyJson.put("userName", StringTools.formatToString(user.getUserName()));
						commentReplyJson.put("userHead", StringTools.formatToString(user.getHeadPortrait()));
						if(user.getManageOrgInfo() != null){
							commentReplyJson.put("orgName", StringTools.formatToString(user.getManageOrgInfo().getSysOrgName()));
						}else{
							commentReplyJson.put("orgName", "");
						}
					}else{
						commentReplyJson.put("userName", "");
						commentReplyJson.put("userHead", "");
						commentReplyJson.put("orgName", "");
					}
					if(replyedUser != null){
						commentReplyJson.put("replyedName", StringTools.formatToString(replyedUser.getUserName()));
						if(replyedUser.getManageOrgInfo() != null){
							commentReplyJson.put("replyedOrg", StringTools.formatToString(replyedUser.getManageOrgInfo().getSysOrgName()));
						}else{
							commentReplyJson.put("replyedOrg", "");
						}
					}else{
						commentReplyJson.put("replyedName", "");
						commentReplyJson.put("replyedOrg", "");
					}
					if(mblogCommentReply.getCreateTime() != null){
						commentReplyJson.put("createTime", DateUtils.getDateString(mblogCommentReply.getCreateTime(), "yyyy-MM-dd HH:mm"));
					}else{
						commentReplyJson.put("createTime", "");
					}
					commentReplyJson.put("praiseNum", StringTools.formatToString(mblogCommentReply.getPraiseNum()));
					commentReplyJson.put("content", StringTools.formatToString(mblogCommentReply.getContent()));
					followReplyArray.add(commentReplyJson);
				}
				mblogCommentJson.put("replyList", followReplyArray);
				followArray.add(mblogCommentJson);
			}
			mBlogFollowBody.put(mBolg.getMblogCode(), followArray);
		}
	}
	
	private Map<String,String> teamBlogData(String teamCode) throws Exception {
		try {
			int size = 30;
			List<TeamBlogModel> blogList = new ArrayList<TeamBlogModel>();
			BlogThirdVO blogThirdVO = new BlogThirdVO();
			blogThirdVO.setTeamCode(teamCode);
			JSONArray blogArray = new JSONArray();
			JSONObject blogFollowBody = new JSONObject();
			int pageNo = 0;
			while(true){
				PageParam pageParam = new PageParam(pageNo, size);
				blogList = blogThridService.getTeamBlogList(pageParam, blogThirdVO);
				if(blogList.size() == 0){
					break;
				}else{
					this.teamBlogData(blogList, blogArray, blogFollowBody);
					blogList.clear();
					pageNo++;
				}
			}
			Map<String,String> map = new HashMap<String,String>();
			map.put("blogArray", "var data = " + blogArray.toString());
			map.put("blogFollowBody", "var data = " + blogFollowBody.toString());
			return map;
		} catch (Exception e) {
			log.error("班组博客归档", e);
			throw e;
		}
	}
	
	private void teamBlogData(List<TeamBlogModel> blogList, JSONArray blogArray, JSONObject blogFollowBody) throws BusinessException{
		for (int i = 0; i < blogList.size(); i++) {
			JSONObject blogJson = new JSONObject();
			TeamBlogModel blog = blogList.get(i);
			SysUserInfo sponsor = sysUserService.selectByCode(blog.getUserCode());
			if(sponsor != null){
				blogJson.put("userName", StringTools.formatToString(sponsor.getUserName()));
				blogJson.put("userhead", StringTools.formatToString(sponsor.getHeadPortrait()));
				SysOrgInfo orgInfo = sponsor.getManageOrgInfo();
				if(orgInfo != null){
					blogJson.put("orgName", StringTools.formatToString(orgInfo.getSysOrgName()));
				}else{
					blogJson.put("orgName", "");
				}
			}else{
				blogJson.put("userName", "");
				blogJson.put("userhead", "");
				blogJson.put("orgName", "");
			}
			if(blog.getBlogPublishTime() != null){
				blogJson.put("publishTime", DateUtils.getDateString(blog.getBlogPublishTime(), "yyyy-MM-dd HH:mm"));
			}else{
				blogJson.put("publishTime", "");
			}
			blogJson.put("title", StringTools.formatToString(blog.getBlogTitle()));
			blogJson.put("content", StringTools.formatToString(blog.getContentInfo()));
			blogJson.put("collectNum", StringTools.formatToString(blog.getCollectNum()));//收藏
			blogJson.put("readNum", StringTools.formatToString(blog.getReadNum()));//浏览
			blogJson.put("commentNum", StringTools.formatToString(blog.getCommentNum()));//评论
			blogJson.put("likeNum", StringTools.formatToString(blog.getLikeNum()));//点赞
			blogJson.put("blogCode", StringTools.formatToString(blog.getBlogCode()));
			blogArray.add(blogJson);
			
			JSONArray followArray = new JSONArray();
			for (int j = 0; j < blog.getCommentList().size(); j++) {
				TeamCommentModel blogComment = blog.getCommentList().get(j);
				JSONObject blogCommentJson = new JSONObject();
				SysUserInfo commenter = sysUserService.selectByCode(blogComment.getUserCode());
				if(commenter != null){
					blogCommentJson.put("userName", StringTools.formatToString(commenter.getUserName()));
					blogCommentJson.put("userHead", StringTools.formatToString(commenter.getHeadPortrait()));
					if(commenter.getManageOrgInfo() != null){
						blogCommentJson.put("orgName", StringTools.formatToString(commenter.getManageOrgInfo().getSysOrgName()));
					}else{
						blogCommentJson.put("orgName", "");
					}
				}else{
					blogCommentJson.put("userName", "");
					blogCommentJson.put("userHead", "");
					blogCommentJson.put("orgName", "");
				}
				if(blogComment.getCreateTime() != null){
					blogCommentJson.put("createTime", DateUtils.getDateString(blogComment.getCreateTime(), "yyyy-MM-dd HH:mm"));
				}else{
					blogCommentJson.put("createTime", "");
				}
				blogCommentJson.put("content", StringTools.formatToString(blogComment.getCommentContent()));
				blogCommentJson.put("likeNum", StringTools.formatToString(blogComment.getCommentLikeNum()));
				
				JSONArray followReplyArray = new JSONArray();
				for (int k = 0; k < blogComment.getReplyList().size(); k++) {
					JSONObject commentReplyJson = new JSONObject();
					TeamReplyModel blogCommentReply = blogComment.getReplyList().get(k);
					SysUserInfo replier = sysUserService.selectByCode(blogCommentReply.getUserCode());
					SysUserInfo questioner = sysUserService.selectByCode(blogCommentReply.getToReplyUserCode());
					if(replier != null){
						commentReplyJson.put("replierName", StringTools.formatToString(replier.getUserName()));
						commentReplyJson.put("replierHead", StringTools.formatToString(replier.getHeadPortrait()));
						if(replier.getManageOrgInfo() != null){
							commentReplyJson.put("replierOrg", StringTools.formatToString(replier.getManageOrgInfo().getSysOrgName()));
						}else{
							commentReplyJson.put("replierOrg", "");
						}
					}else{
						commentReplyJson.put("replierName", "");
						commentReplyJson.put("replierOrg", "");
					}
					if(questioner != null){
						commentReplyJson.put("questionerName", StringTools.formatToString(questioner.getUserName()));
						commentReplyJson.put("questionerHead", StringTools.formatToString(questioner.getHeadPortrait()));
						if(questioner.getManageOrgInfo() != null){
							commentReplyJson.put("questionerOrg", StringTools.formatToString(questioner.getManageOrgInfo().getSysOrgName()));
						}else{
							commentReplyJson.put("questionerOrg", "");
						}
					}else{
						commentReplyJson.put("questionerName", "");
						commentReplyJson.put("questionerOrg", "");
					}
					
					if(blogCommentReply.getCreateTime() != null){
						commentReplyJson.put("createTime", DateUtils.getDateString(blogCommentReply.getCreateTime(), "yyyy-MM-dd HH:mm"));
					}else{
						commentReplyJson.put("createTime", "");
					}
					commentReplyJson.put("likeNum", StringTools.formatToString(blogCommentReply.getReplyLikeNum()));
					commentReplyJson.put("content", StringTools.formatToString(blogCommentReply.getReplyContent()));
					followReplyArray.add(commentReplyJson);
				}
				blogCommentJson.put("replyList", followReplyArray);
				followArray.add(blogCommentJson);
			}
			blogFollowBody.put(blog.getBlogCode(), followArray);
		}
	}
	
	private Map<String,String> teamVoteData(String teamCode) throws Exception {
		try {
			int start = 0;
			int size = 30;
			List<VoteInfo> voteList = new ArrayList<VoteInfo>();
			JSONArray voteArray = new JSONArray();//论坛数组
			JSONObject voteFollowBody = new JSONObject();//论坛回帖及回复数据
			while(true){
				voteList = voteThirdService.sumupTeamVote(teamCode, start, size);
				if(voteList.size() == 0){
					break;
				}else{
					this.teamVoteData(voteList, voteArray, voteFollowBody);
					voteList.clear();
					start = start + size;
				}
			}
			Map<String,String> map = new HashMap<String,String>();
			map.put("voteArray", "var data = " + voteArray.toString());
			map.put("voteFollowBody", "var data = " + voteFollowBody.toString());
			return map;
		} catch (Exception e) {
			log.error("班组投票归档", e);
			throw e;
		}
	}
	
	private void teamVoteData(List<VoteInfo> voteList, JSONArray voteArray, JSONObject voteFollowBody) throws BusinessException{
		for (int i = 0; i < voteList.size(); i++) {
			JSONObject voteJson = new JSONObject();
			VoteInfo vote = voteList.get(i);
			voteJson.put("title", StringTools.formatToString(vote.getTitle()));
			SysUserInfo sponsor = sysUserService.selectByCode(vote.getSponsorCode());//发布人
			if(sponsor != null){
				voteJson.put("userName", StringTools.formatToString(sponsor.getUserName()));
				SysOrgInfo orgInfo = sponsor.getManageOrgInfo();
				if(orgInfo != null){
					voteJson.put("orgName", StringTools.formatToString(orgInfo.getSysOrgName()));
				}else{
					voteJson.put("orgName", "");
				}
			}else{
				voteJson.put("userName", "");
				voteJson.put("orgName", "");
			}
			if(vote.getStartTime() != null){//开始时间
				voteJson.put("startTime", DateUtils.getDateString(vote.getStartTime(), "yyyy-MM-dd HH:mm"));
			}else{
				voteJson.put("startTime", "");
			}
			if(vote.getEndTime() != null){//结束时间
				voteJson.put("endTime", DateUtils.getDateString(vote.getEndTime(), "yyyy-MM-dd HH:mm"));
			}else{
				voteJson.put("endTime", "");
			}
			if(vote.getCreateTime() != null){//创建时间
				voteJson.put("createTime", DateUtils.getDateString(vote.getCreateTime(), "yyyy-MM-dd HH:mm"));
			}else{
				voteJson.put("createTime", "");
			}
			voteJson.put("voteExplain", StringTools.formatToString(vote.getVoteExplain()));
			JSONArray optionArray = new JSONArray();
			List<VoteOptions> voteOption = vote.getOptions();
			for (int j = 0; j < voteOption.size(); j++) {
				VoteOptions option = voteOption.get(j);
				JSONObject optionBody = new JSONObject();
				optionBody.put("orderNumber", StringTools.formatToString(option.getOrderNumber()));
				optionBody.put("content", StringTools.formatToString(option.getContent()));
				optionBody.put("selectedNum", StringTools.formatToString(option.getSelectedNum()));
				optionBody.put("percent", StringTools.formatToString(option.getPercent()));//是否加%
				optionArray.add(optionBody);
			}
			voteJson.put("options", optionArray);
			if(VoteConstants.STATUS_1.equals(vote.getVoteStatus())){
				voteJson.put("voteStatus", "草稿");
			}else if(VoteConstants.STATUS_2.equals(vote.getVoteStatus())){
				voteJson.put("voteStatus", "审批中");
			}else if(VoteConstants.STATUS_3.equals(vote.getVoteStatus())){
				voteJson.put("voteStatus", "已驳回");
			}else if(VoteConstants.STATUS_4.equals(vote.getVoteStatus())){
				voteJson.put("voteStatus", "进行中");
			}else if(VoteConstants.STATUS_5.equals(vote.getVoteStatus())){
				voteJson.put("voteStatus", "结束");
			}
			voteJson.put("shareNum", StringTools.formatToString(vote.getShareNum()));
			voteJson.put("joinNum", StringTools.formatToString(vote.getJoinNum()));
			voteJson.put("praiseNum", StringTools.formatToString(vote.getPraiseNum()));
			voteJson.put("collectionNum", StringTools.formatToString(vote.getCollectionNum()));
			voteJson.put("code", StringTools.formatToString(vote.getCode()));
			voteArray.add(voteJson);//投票数据添加
			
			JSONArray followArray = new JSONArray();//回复加评论数组
			for (int j = 0; j < vote.getComments().size(); j++) {//跟帖与回复处理
				VoteComment voteComment = vote.getComments().get(j);//跟帖实体
				JSONObject voteCommentJson = new JSONObject();//跟帖json
				SysUserInfo commenter = sysUserService.selectByCode(voteComment.getCommenterCode());
				if(commenter != null){
					voteCommentJson.put("userName", StringTools.formatToString(commenter.getUserName()));
					voteCommentJson.put("userHead", StringTools.formatToString(commenter.getHeadPortrait()));
				}else{
					voteCommentJson.put("userName", "");
					voteCommentJson.put("userHead", "");
				}
				if(voteComment.getCreateTime() != null){
					voteCommentJson.put("createTime", DateUtils.getDateString(voteComment.getCreateTime(), "yyyy-MM-dd HH:mm"));
				}else{
					voteCommentJson.put("createTime", "");
				}
				voteCommentJson.put("followContent", StringTools.formatToString(voteComment.getContent()));
				voteCommentJson.put("praiseCount", StringTools.formatToString(voteComment.getPraiseCount()));
				
				JSONArray followReplyArray = new JSONArray();
				for (int k = 0; k < voteComment.getReplys().size(); k++) {//遍历回复
					JSONObject commentReplyJson = new JSONObject();
					VoteCommentReply voteCommentReply = voteComment.getReplys().get(k);
					SysUserInfo replier = sysUserService.selectByCode(voteCommentReply.getReplierCode());
					SysUserInfo questioner = sysUserService.selectByCode(voteCommentReply.getQuestionerCode());
					if(replier != null){
						commentReplyJson.put("replierName", StringTools.formatToString(replier.getUserName()));
						commentReplyJson.put("replierHead", StringTools.formatToString(replier.getHeadPortrait()));
						if(replier.getManageOrgInfo() != null){
							commentReplyJson.put("replierOrg", StringTools.formatToString(replier.getManageOrgInfo().getSysOrgName()));
						}else{
							commentReplyJson.put("replierOrg", "");
						}
					}else{
						commentReplyJson.put("replierName", "");
						commentReplyJson.put("replierOrg", "");
					}
					if(questioner != null){
						commentReplyJson.put("questionerName", StringTools.formatToString(questioner.getUserName()));
						commentReplyJson.put("questionerHead", StringTools.formatToString(questioner.getHeadPortrait()));
						if(questioner.getManageOrgInfo() != null){
							commentReplyJson.put("questionerOrg", StringTools.formatToString(questioner.getManageOrgInfo().getSysOrgName()));
						}else{
							commentReplyJson.put("questionerOrg", "");
						}
					}else{
						commentReplyJson.put("questionerName", "");
						commentReplyJson.put("questionerOrg", "");
					}
					
					if(voteCommentReply.getCreateTime() != null){
						commentReplyJson.put("createTime", DateUtils.getDateString(voteCommentReply.getCreateTime(), "yyyy-MM-dd HH:mm"));
					}else{
						commentReplyJson.put("createTime", "");
					}
					commentReplyJson.put("praiseCount", StringTools.formatToString(voteCommentReply.getPraiseCount()));
					commentReplyJson.put("content", StringTools.formatToString(voteCommentReply.getContent()));
					followReplyArray.add(commentReplyJson);
				}
				voteCommentJson.put("replyList", followReplyArray);
				followArray.add(voteCommentJson);
			}
			voteFollowBody.put(vote.getCode(), followArray);
		}
	}
	
	private Map<String,String> teamForumData(String teamCode) throws Exception {
		try {
			int start = 0;
			int size = 30;
			List<ForumTopics> forumList = new ArrayList<ForumTopics>();
			JSONArray forumArray = new JSONArray();//论坛数组
			JSONObject forumFollowBody = new JSONObject();//论坛回帖及回复数据
			while(true){//取数据,直到取不到退出循环
				forumList =forumThirdService.sumupTeamForum(teamCode, start, size);
				if(forumList.size() == 0){
					break;
				}else{
					this.teamForumData(forumList, forumArray, forumFollowBody);
					forumList.clear();
					start = start + size;
				}
			}
			Map<String,String> map = new HashMap<String,String>();
			map.put("forumArray", "var data = " + forumArray.toString());
			map.put("forumFollowBody", "var data = " + forumFollowBody.toString());
			return map;
		} catch (Exception e) {
			log.error("班组论坛归档", e);
			throw e;
		}
	}
	
	private void teamForumData(List<ForumTopics> forumList,JSONArray forumArray,JSONObject forumFollowBody) throws Exception {
		for (int i = 0; i < forumList.size(); i++) {
			JSONObject forumJson = new JSONObject();
			ForumTopics forum = forumList.get(i);
			SysUserInfo postMan = sysUserService.selectByCode(forum.getPostmanCode());//发布人
			if(postMan != null){
				forumJson.put("userName", StringTools.formatToString(postMan.getUserName()));
				forumJson.put("userhead", StringTools.formatToString(postMan.getHeadPortrait()));
				SysOrgInfo orgInfo = postMan.getManageOrgInfo();
				if(orgInfo != null){
					forumJson.put("orgName", StringTools.formatToString(orgInfo.getSysOrgName()));
				}else{
					forumJson.put("orgName", "");
				}
			}else{
				forumJson.put("userName", "");
				forumJson.put("userhead", "");
				forumJson.put("orgName", "");
			}
			forumJson.put("subject", StringTools.formatToString(forum.getSubject()));//主题
			forumJson.put("isTop", StringTools.formatToString(forum.getIsTop()));//置顶
			forumJson.put("isBest", StringTools.formatToString(forum.getIsBest()));//加精
			forumJson.put("viewCount", StringTools.formatToString(forum.getViewCount()));//浏览数
			forumJson.put("replyCount", StringTools.formatToString(forum.getReplyCount()));//回复数
			if(forum.getLastReplyTime() != null){//回复时间
				forumJson.put("lastReplyTime", DateUtils.getDateString(forum.getLastReplyTime(), "yyyy-MM-dd HH:mm"));
			}else{
				forumJson.put("lastReplyTime", "");
			}
			if(forum.getCreateTime() != null){//创建时间
				forumJson.put("createTime", DateUtils.getDateString(forum.getCreateTime(), "yyyy-MM-dd HH:mm"));
			}else{
				forumJson.put("createTime", "");
			}
			forumJson.put("topicsCode", forum.getForumTopicsCode());
			forumArray.add(forumJson);//论坛数据添加
			
			JSONArray followArray = new JSONArray();//回复加评论数组
			for (int j = 0; j < forum.getFollowList().size(); j++) {//跟帖与回复处理
				ForumTopicsFollow forumFollow = forum.getFollowList().get(j);//跟帖实体
				JSONObject forumFollowJson = new JSONObject();//跟帖json
				String followCode = forumFollow.getFollowerCode();//跟帖人code
				if(followCode != null && followCode.equals(forum.getPostmanCode())){//发布人即楼主
					forumFollowJson.put("isLeader", "楼主");
				}else{
					forumFollowJson.put("isLeader", "");
				}
				SysUserInfo followMan = sysUserService.selectByCode(forumFollow.getFollowerCode());
				if(followMan != null){
					forumFollowJson.put("userName", StringTools.formatToString(followMan.getUserName()));
					forumFollowJson.put("userHead", StringTools.formatToString(followMan.getHeadPortrait()));
					UserLevelConfig userLevelConfig = expAccountService.selectMultiByCode(followMan.getUserCode());
					if (userLevelConfig != null) {
						forumFollowJson.put("level", StringTools.formatToString(userLevelConfig.getLevelNo()));// 等级
						forumFollowJson.put("title", StringTools.formatToString(userLevelConfig.getLevelTitle()));// 称号
					} else {
						forumFollowJson.put("level", "1");
						forumFollowJson.put("title", "儒生");
					}
				}else{
					forumFollowJson.put("userName", "");
					forumFollowJson.put("userHead", "");
					forumFollowJson.put("level", "1");
					forumFollowJson.put("title", "儒生");
				}
				if(forumFollow.getCreateTime() != null){
					forumJson.put("createTime", DateUtils.getDateString(forumFollow.getCreateTime(), "yyyy-MM-dd HH:mm"));
				}else{
					forumJson.put("createTime", "");
				}
				if(TeamConstants.BOOLEAN_TRUE.equals(forumFollow.getIsHide())){//隐藏标识
					forumFollowJson.put("followContent", "");
					forumFollowJson.put("replyList", new JSONArray());
					followArray.add(forumFollowJson);
					continue;
				}
				forumFollowJson.put("followContent", StringTools.formatToString(forumFollow.getFollowContent()));
				JSONArray followReplyArray = new JSONArray();
				for (int k = 0; k < forumFollow.getCommentReplyList().size(); k++) {//遍历回复
					JSONObject followReplyJson = new JSONObject();
					ForumCommentReply forumCommentReply = forumFollow.getCommentReplyList().get(k);
					SysUserInfo replier = sysUserService.selectByCode(forumCommentReply.getReplierCode());
					SysUserInfo questioner = sysUserService.selectByCode(forumCommentReply.getQuestionerCode());
					if(replier != null){
						followReplyJson.put("replierName", StringTools.formatToString(replier.getUserName()));
						followReplyJson.put("replierHead", StringTools.formatToString(replier.getHeadPortrait()));
						if(replier.getManageOrgInfo() != null){
							followReplyJson.put("replierOrg", StringTools.formatToString(replier.getManageOrgInfo().getSysOrgName()));
						}else{
							followReplyJson.put("replierOrg", "");
						}
					}else{
						followReplyJson.put("replierName", "");
						followReplyJson.put("replierOrg", "");
					}
					if(questioner != null){
						followReplyJson.put("questionerName", StringTools.formatToString(questioner.getUserName()));
						if(questioner.getManageOrgInfo() != null){
							followReplyJson.put("questionerOrg", StringTools.formatToString(questioner.getManageOrgInfo().getSysOrgName()));
						}else{
							followReplyJson.put("questionerOrg", "");
						}
					}else{
						followReplyJson.put("questionerName", "");
						followReplyJson.put("questionerOrg", "");
					}
					
					if(forumCommentReply.getCreateTime() != null){
						followReplyJson.put("createTime", DateUtils.getDateString(forumCommentReply.getCreateTime(), "yyyy-MM-dd HH:mm"));
					}else{
						followReplyJson.put("createTime", "");
					}
					followReplyJson.put("content", StringTools.formatToString(forumCommentReply.getContent()));
					followReplyArray.add(followReplyJson);
				}
				forumFollowJson.put("replyList", followReplyArray);
				followArray.add(forumFollowJson);
			}
			forumFollowBody.put(forum.getForumTopicsCode(), followArray);
		}
	}
	
	private Map<String,String> teamHonorData(String teamCode) throws Exception {
		try {
			int start = 0;
			int size = 30;
			List<HonorAwardRecord> honorList = new ArrayList<HonorAwardRecord>();
			JSONArray honorArray = new JSONArray();//数组
			JSONObject honorFollowBody = new JSONObject();//回帖及回复数据
			while(true){//取数据,直到取不到退出循环
				honorList =honorThirdService.sumupTeamHonor(teamCode, start, size);
				if(honorList.size() == 0){
					break;
				}else{
					this.teamHonorData(honorList, honorArray, honorFollowBody);
					honorList.clear();
					start = start + size;
				}
			}
			Map<String,String> map = new HashMap<String,String>();
			map.put("honorArray", "var data = " + honorArray.toString());
			map.put("honorFollowBody", "var data = " + honorFollowBody.toString());
			return map;
			
		} catch (Exception e) {
			log.error("班组荣誉归档", e);
			throw e;
		}
	}
	
	private void teamHonorData(List<HonorAwardRecord> honorList,JSONArray honorArray,JSONObject honorFollowBody) throws Exception {
		for (int i = 0; i < honorList.size(); i++) {
			JSONObject honorJson = new JSONObject();
			HonorAwardRecord honor = honorList.get(i);
			honorJson.put("honorCode", StringTools.formatToString(honor.getHonorAwardRecordCode()));
			honorJson.put("honorUrl", StringTools.formatToString(honor.getHonorUrl()));
			honorJson.put("honorName", StringTools.formatToString(honor.getHonorName()));
			if(honor.getCreateTime() != null){
				honorJson.put("createTime", DateUtils.getDateString(honor.getCreateTime(), "yyyy-MM-dd HH:mm"));
			}else{
				honorJson.put("createTime", "");
			}
			SysUserInfo awarder = sysUserService.selectByCode(honor.getAwarderCode());
			if(awarder != null){
				honorJson.put("userName", StringTools.formatToString(awarder.getUserName()));
				SysOrgInfo orgInfo = awarder.getManageOrgInfo();
				if(orgInfo != null){
					honorJson.put("orgName", StringTools.formatToString(orgInfo.getSysOrgName()));
				}else{
					honorJson.put("orgName", "");
				}
			}else{
				honorJson.put("userName", "");
				honorJson.put("orgName", "");
			}
			honorJson.put("awardReason", StringTools.formatToString(honor.getAwardReason()));
			honorJson.put("praiseCount", StringTools.formatToString(honor.getPraiseCount()));
			honorArray.add(honorJson);
			
			JSONArray followArray = new JSONArray();//回复加评论数组
			for (int j = 0; j < honor.getCommentList().size(); j++) {//跟帖与回复处理
				HonorComment honorFollow = honor.getCommentList().get(j);//跟帖实体
				JSONObject honorFollowJson = new JSONObject();//跟帖json
				String commenterCode = honorFollow.getCommenterCode();
				SysUserInfo commenter = sysUserService.selectByCode(commenterCode);
				if(commenter != null){
					honorFollowJson.put("userName", StringTools.formatToString(commenter.getUserName()));
					honorFollowJson.put("userHead", StringTools.formatToString(commenter.getHeadPortrait()));
					if(commenter.getManageOrgInfo() != null){
						honorFollowJson.put("orgName", StringTools.formatToString(commenter.getManageOrgInfo().getSysOrgName()));
					}else{
						honorFollowJson.put("orgName", "");
					}
				}else{
					honorFollowJson.put("userName", "");
					honorFollowJson.put("userHead", "");
					honorFollowJson.put("orgName", "");
				}
				honorFollowJson.put("content", StringTools.formatToString(honorFollow.getContent()));
				if(honorFollow.getCreateTime() != null){
					honorFollowJson.put("createTime", DateUtils.getDateString(honorFollow.getCreateTime(), "yyyy-MM-dd HH:mm"));
				}else{
					honorFollowJson.put("createTime", "");
				}
				honorFollowJson.put("praiseCount", StringTools.formatToString(honorFollow.getPraiseCount()));
				
				JSONArray followReplyArray = new JSONArray();
				for (int k = 0; k < honorFollow.getReplyList().size(); k++) {//遍历回复
					JSONObject followReplyJson = new JSONObject();
					HonorCommentReply honorCommentReply = honorFollow.getReplyList().get(k);
					SysUserInfo replier = sysUserService.selectByCode(honorCommentReply.getReplierCode());
					SysUserInfo questioner = sysUserService.selectByCode(honorCommentReply.getQuestionerCode());
					if(replier != null){
						followReplyJson.put("replierName", StringTools.formatToString(replier.getUserName()));
						followReplyJson.put("replierHead", StringTools.formatToString(replier.getHeadPortrait()));
						if(replier.getManageOrgInfo() != null){
							followReplyJson.put("replierOrg", StringTools.formatToString(replier.getManageOrgInfo().getSysOrgName()));
						}else{
							followReplyJson.put("replierOrg", "");
						}
					}else{
						followReplyJson.put("replierName", "");
						followReplyJson.put("replierOrg", "");
					}
					if(questioner != null){
						followReplyJson.put("questionerName", StringTools.formatToString(questioner.getUserName()));
						if(questioner.getManageOrgInfo() != null){
							followReplyJson.put("questionerOrg", StringTools.formatToString(questioner.getManageOrgInfo().getSysOrgName()));
						}else{
							followReplyJson.put("questionerOrg", "");
						}
					}else{
						followReplyJson.put("questionerName", "");
						followReplyJson.put("questionerOrg", "");
					}
					
					if(honorCommentReply.getCreateTime() != null){
						followReplyJson.put("createTime", DateUtils.getDateString(honorCommentReply.getCreateTime(), "yyyy-MM-dd HH:mm"));
					}else{
						followReplyJson.put("createTime", "");
					}
					followReplyJson.put("content", StringTools.formatToString(honorCommentReply.getContent()));
					followReplyArray.add(followReplyJson);
				}
				honorFollowJson.put("replyList", followReplyArray);
				followArray.add(honorFollowJson);
			}
			honorFollowBody.put(honor.getHonorAwardRecordCode(), followArray);
		}
	}

	/**
	 * @Function upload
	 * @Description 上传zip
	 * @param zipPath
	 *            zip所在路径,/usr/local/test.zip
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private String upload(String zipPath, HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {

		String resultStr = "";
		// 创建可关闭的httpClient
		CloseableHttpClient httpclient = HttpClients.createDefault();
		File targetFile = new File(zipPath);
		try {

			// 创建post传输方法
			HttpPost post = new HttpPost(fastDfsConf.getFastUrl());

			// 创建浏览器传输模式
			MultipartEntityBuilder builder = MultipartEntityBuilder.create()
					.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			// 设置模式编码
			builder.setCharset(CharsetUtils.get("UTF-8"));

			// 把文件放到流对象FileBody中
			FileBody fileBody = new FileBody(targetFile);
			// 将文件信息放到模式中
			builder.addPart("multipartFile" + System.currentTimeMillis(), fileBody);

			HttpEntity reqEntity = builder.build();
			post.setEntity(reqEntity);
			// 发送请求，并获取响应
			CloseableHttpResponse res = httpclient.execute(post);
			try {
				if (org.apache.http.HttpStatus.SC_OK == res.getStatusLine().getStatusCode()) {
					HttpEntity entitys = res.getEntity();
					if (null != entitys) {
						resultStr += EntityUtils.toString(entitys, Charset.forName("UTF-8"));
					}
					EntityUtils.consume(entitys);
				}
			} finally {
				// 关闭响应
				res.close();
			}
		} finally {
			// 关闭http连接
			httpclient.close();
			if (null != targetFile) {
				targetFile.delete();
			}
		}
		return resultStr;
	}

	/**
	 * @Function urlAnalysis
	 * @Description 解析json得到url
	 * @param jsonUrl
	 * @return
	 * @throws BusinessException
	 */
	private String urlAnalysis(String jsonUrl) throws BusinessException {
		JSONObject jsonObject = JSONObject.parseObject(jsonUrl);
		Object object = jsonObject.get("body");
		JSONObject body = JSONObject.parseObject(object.toString());

		Object url = body.get("url");
		return url.toString();
	}

	/**
	 * @Function downloadResource
	 * @Description 下载静态资源
	 * @param url
	 *            静态资源url,如group1/M00/00/00/rBB9c1lV5KeAHygkAACJ2kkwOF4545.jpg
	 * @param path
	 *            资源存放目录,如/usr/local/common/
	 * @throws Exception
	 */
//	private String downloadResource(String uri, String path) throws Exception {
//		OutputStream os = null;
//		InputStream is = null;
//		try {
//			String teamIconName = "";
//			if (StringTools.isEmpty(uri)) {
//				return teamIconName;
//			}
//			List<String> headUri = Arrays.asList(uri.split("/"));
//			// 查找文件是否已经下载
//			teamIconName = headUri.get(headUri.size() - 1);
//			File file = new File(path + teamIconName);
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			// 下载
//			String downloadUrl = fastDfsConf.getDownloadUrl();
//			URL url = new java.net.URL(downloadUrl + uri);
//			URLConnection conn = url.openConnection();
//			if(conn.getContentType().equals("text/html")){//内容为文本,放弃下载
//				return "";
//			}
//			is = conn.getInputStream();
//			os = new FileOutputStream(file);
//			int bytesRead = 0;
//			byte[] buffer = new byte[8192];
//			while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
//				os.write(buffer, 0, bytesRead);
//			}
//			os.close();
//			is.close();
//			return teamIconName;
//		} catch (Exception e) {
//			log.error("下载静态资源异常", e);
//			throw e;
//		} finally {
//			if (os != null) {
//				try {
//					os.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (is != null) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	

//	public static void main(String[] args) throws Exception {
//		JSONObject resJson = new JSONObject();
//		resJson.put("img", "");
//		resJson.put("audio", "");
//		resJson.put("video", "");
//		resJson.put("summary", "");
//		resJson.put("text", "");
//		resJson.put("topic", "");
//		String img = "";
//		List<MblogResource> s = new ArrayList<MblogResource>();
//		for (int i = 0; i < 5; i++) {
//			MblogResource m= new MblogResource();
//			m.setResType(MblogConstants.MBLOG_RESOURCE_IMG);
//			m.setContent(i+"");
//			s.add(m);
//		}
//		
//		for (int j = 0; j < s.size(); j++) {
//			MblogResource res = s.get(j);
//			if(MblogConstants.MBLOG_RESOURCE_IMG == res.getResType().byteValue()){//图片
//				img += res.getContent()+",";
//			}else if(MblogConstants.MBLOG_RESOURCE_AUDIO == res.getResType().byteValue()){//音频
//				resJson.put("audio", res.getContent());
//			}else if(MblogConstants.MBLOG_RESOURCE_VIDEO == res.getResType().byteValue()){//视频
//				resJson.put("video", res.getContent());
//			}else if(MblogConstants.MBLOG_RESOURCE_SUMMARY == res.getResType().byteValue()){
//				resJson.put("summary", res.getContent());
//			}else if(MblogConstants.MBLOG_RESOURCE_TEXT == res.getResType().byteValue()){
//				resJson.put("text", res.getContent());
//			}else if(MblogConstants.MBLOG_RESOURCE_TOPIC == res.getResType().byteValue()){
//				resJson.put("topic", res.getContent());
//			}
//		}
//		if(img.length()>0){
//			resJson.put("img", img.substring(0, img.length()-1));
//		}
//		System.out.println(resJson);
//	}

}
