package com.zssq.team.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.zssq.constants.MsgClassConstants;
import com.zssq.constants.StatisticConstants;
import com.zssq.constants.TeamConstants;
import com.zssq.dao.pojo.CoinAccount;
import com.zssq.dao.pojo.HonorAwardRecord;
import com.zssq.dao.pojo.IntegralAccount;
import com.zssq.dao.pojo.IntegralAndCoin;
import com.zssq.dao.pojo.MyCount;
import com.zssq.dao.pojo.RankEntity;
import com.zssq.dao.pojo.StatisticCommon;
import com.zssq.dao.pojo.StatisticVisitDetail;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamFans;
import com.zssq.dao.pojo.TeamFriend;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.dao.pojo.TeamMember;
import com.zssq.dao.pojo.TeamMessage;
import com.zssq.dao.pojo.TeamMessageReply;
import com.zssq.dao.pojo.UserBoardRemind;
import com.zssq.dao.pojo.UserLevelConfig;
import com.zssq.dao.pojo.UserTeamState;
import com.zssq.dao.vo.MessageGPSVo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.HonorThirdService;
import com.zssq.service.ICoinAccountService;
import com.zssq.service.IExpAccountService;
import com.zssq.service.IIntegralAccountService;
import com.zssq.service.IStatisticService;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.ITeamMemberService;
import com.zssq.service.IUserRelationService;
import com.zssq.service.MessagerBoradService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.team.vo.AllByOrgVo;
import com.zssq.team.vo.ConcernsTeamVo;
import com.zssq.team.vo.DelVisitorVo;
import com.zssq.team.vo.FansAddVo;
import com.zssq.team.vo.FansDelVo;
import com.zssq.team.vo.FansVo;
import com.zssq.team.vo.FriendTeamAddVo;
import com.zssq.team.vo.FriendTeamDelVo;
import com.zssq.team.vo.FriendTeamListVo;
import com.zssq.team.vo.HeadInfoVo;
import com.zssq.team.vo.HeadVo;
import com.zssq.team.vo.IntroVo;
import com.zssq.team.vo.ListByOrgVo;
import com.zssq.team.vo.MessageAddVo;
import com.zssq.team.vo.MessageDelVo;
import com.zssq.team.vo.MessageListVo;
import com.zssq.team.vo.ReplyAddVo;
import com.zssq.team.vo.ReplyDelVo;
import com.zssq.team.vo.ReplyListVo;
import com.zssq.team.vo.SearchVo;
import com.zssq.team.vo.TeamMemDevoteVo;
import com.zssq.team.vo.TeamMemVo;
import com.zssq.team.vo.TeamVisitorVo;
import com.zssq.team.vo.UnfollowVo;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;


/**
 * @ClassName TeamController
 * @Description 门户班组controller
 * @author JiPengChun
 * @date 2017年5月15日 上午10:42:58
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/team")
public class TeamController {

	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 班组成员service
	 */
	@Autowired
	private ITeamMemberService teamMemberService;
	
	/**
	 * 组织机构service
	 */
	@Autowired
	private ISysOrgService sysOrgService;
	
	/**
	 * 用户service
	 */
	@Autowired
	private ISysUserService sysUserService;
	
	/**
	 * 班组基本信息service
	 */
	@Autowired
	private ITeamInfoService teamInfoService;
	
	/**
	 * 好友关系service
	 */
	@Autowired
	private IUserRelationService userRelationService;
	
	/**
	 * 获取访问量service
	 */
	@Autowired
	private IStatisticService statisticService;
	
	/**
	 * 调用荣誉service
	 */
	@Autowired
	private HonorThirdService honorThirdService;
	
	/**
	 * 积分service
	 */
	@Autowired
	private IIntegralAccountService integralAccountService;
	
	/**
	 * 金币service
	 */
	@Autowired
	private ICoinAccountService coinAccountService;
	
	/**
	 * 经验等级service
	 */
	@Autowired
	private IExpAccountService expAccountService;
	
	/**
	 * 留言板service
	 */
	@Autowired
	private MessagerBoradService messagerBoradService;
	
	/** 获取配置的默认头像 */
	@Autowired
	private AuthConfig authConfig;
	
	/**
	 * @Function myTeam
	 * @Description 顶栏我的班组
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/myTeam")
	public ResultJSON myTeam(HttpServletRequest request) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	List<TeamInfo> teamList = teamInfoService.getMyTeamList(request.getParameter("userCode"));
	    	JSONObject body = new JSONObject();
	    	JSONArray jsonArray = new JSONArray();
	    	String teamIcon = authConfig.getTeamIcon();
	    	for (int i = 0; i < teamList.size(); i++) {
	    		SysOrgInfo orginfo = sysOrgService.selectByCode(teamList.get(i).getOrgCode());
	    		if(orginfo == null || !orginfo.getIsEnable().equals(AuthConstants.BOOLEAN_TRUE)){
	    			continue;
	    		}
	    		JSONObject teamJson = new JSONObject();
	    		teamJson.put("teamCode", StringTools.formatToString(teamList.get(i).getTeamCode()));//班组code
	    		teamJson.put("teamName", StringTools.formatToString(teamList.get(i).getTeamName()));//班组名称
	    		if(StringTools.isEmpty(teamList.get(i).getTeamIcon())){
	    			teamJson.put("teamIcon", teamIcon);//班组头像
	    		}else{
	    			teamJson.put("teamIcon", StringTools.formatToString(teamList.get(i).getTeamIcon()));//班组头像
	    		}
	    		jsonArray.add(teamJson);
			}
	    	body.put("dataList", jsonArray);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"获取我的班组"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取我的班组异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取我的班组"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function headInfo
	 * @Description 班组头信息
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/headInfo")
	public ResultJSON headInfo(@RequireValid HeadInfoVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	TeamInfo teamInfo = teamInfoService.selectHeadInfo(param.getUserCode(), param.getTeamCode());
	    	if(teamInfo == null){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取班组头信息","班组code无效"));
	    	}
	    	JSONObject body = new JSONObject();
	    	String teamIcon = authConfig.getTeamIcon();
	    	if(StringTools.isEmpty(teamInfo.getTeamIcon())){
	    		body.put("teamIcon", teamIcon);//班组头像
    		}else{
    			body.put("teamIcon", StringTools.formatToString(teamInfo.getTeamIcon()));//班组头像
    		}
	    	body.put("teamCode", StringTools.formatToString(teamInfo.getTeamCode()));//班组编码
	    	body.put("teamName", StringTools.formatToString(teamInfo.getTeamName()));//班组名称
	    	CoinAccount coinAccount = coinAccountService.selectByAccountCode(teamInfo.getTeamCode());
			if(coinAccount != null){
				body.put("gold", StringTools.formatToString(coinAccount.getCoinBalance()));//金币
			}else{
				body.put("gold", "0");//金币
			}
	    	IntegralAccount integralAccount = integralAccountService.selectByAccountCode(teamInfo.getTeamCode());
			if(integralAccount != null){
				body.put("score", StringTools.formatToString(integralAccount.getIntegralBalance()));//积分
			}else{
				body.put("score", "0");//积分
			}
	    	body.put("honor", StringTools.formatToString(honorThirdService.getHonorCount(teamInfo.getTeamCode(), AuthConstants.TENANT_CODE)));//荣誉数量
	    	SysOrgInfo orgInfo = sysOrgService.selectByCode(teamInfo.getOrgCode());
	    	body.put("orgCode", StringTools.formatToString(teamInfo.getOrgCode()));//组织机构编码
	    	if(orgInfo != null){
	    		body.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));//组织机构名称
	    	}else{
	    		body.put("orgName", "");
	    	}
	    	body.put("intro", StringTools.formatToString(teamInfo.getTeamIntro()));//班组简介
	    	if(StringTools.isEmpty(teamInfo.getIsLeader())){//空   这个人没有班组  即不是班组长
	    		body.put("isLeader", TeamConstants.BOOLEAN_0);
	    	}else{
	    		body.put("isLeader", StringTools.formatToString(teamInfo.getIsLeader()));//当前登录人是否是班组长
	    	}
	    	if(StringTools.isEmpty(teamInfo.getIsLeader())){
	    		body.put("isMyTeam", TeamConstants.BOOLEAN_0);
	    	}else{
	    		body.put("isMyTeam", TeamConstants.BOOLEAN_1);
	    	}
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"获取班组头信息"));
	    	rj.setBody(body);
		} catch (BusinessException e){
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取班组头信息异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取班组头信息"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function updateIntro
	 * @Description 更新班组简介
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/updateIntro")
	public ResultJSON updateIntro(@RequireValid IntroVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	boolean isSuccess = teamInfoService.updateTeamIntro(param.getTeamCode(), param.getIntro());
	    	JSONObject body = new JSONObject();
	    	if(isSuccess){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"更新班组简介"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("更新班组简介异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"更新班组简介"));
		}
	    return rj;
	}
	
	/**
	 * @Function updateHead
	 * @Description 更新班组头像
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/updateHead")
	public ResultJSON updateHead(@RequireValid HeadVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	boolean isSuccess = teamInfoService.updateTeamHead(param.getTeamCode(), param.getHead());
	    	JSONObject body = new JSONObject();
	    	if(isSuccess){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"更新班组头像"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("更新班组头像异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"更新班组头像"));
		}
	    return rj;
	}
	
	/**
	 * @Function addFans
	 * @Description 新增班组粉丝
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/addFans")
	public ResultJSON addFans(@RequireValid FansAddVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	boolean isSuccess = teamInfoService.addFans(param.getTeamCode(), param.getUserCode());
	    	JSONObject body = new JSONObject();
	    	if(isSuccess){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    		TeamInfo teamInfo = teamInfoService.selectByCode(param.getTeamCode());
	    		// 统计操作
				StatisticCommon statisticCommon = new StatisticCommon();
				statisticCommon.setOrgCode(teamInfo.getOrgCode());
				statisticCommon.setTeamCode(teamInfo.getTeamCode());
				statisticCommon.setTeamName(teamInfo.getTeamName());
				statisticCommon.setFaces(StatisticConstants.FACES);
				statisticService.addRecord(statisticCommon);
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"关注班组"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("关注班组异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"关注班组"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function isFans
	 * @Description 是否关注该班组
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/isFans")
	public ResultJSON isFans(@RequireValid FansAddVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	boolean isSuccess = teamInfoService.isFans(param.getTeamCode(), param.getUserCode());
	    	JSONObject body = new JSONObject();
	    	if(isSuccess){
	    		body.put("isFans", TeamConstants.BOOLEAN_1);
	    	}else{
	    		body.put("isFans", TeamConstants.BOOLEAN_0);
	    	}
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"获取关注状态"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取粉丝状态异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取关注状态"));
		}
	    return rj;
	}
	
	/**
	 * @Function delFans
	 * @Description 移除粉丝
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/delFans")
	public ResultJSON delFans(@RequireValid FansDelVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	String teamCode = teamInfoService.delFans(param.getTeamFansCode());
	    	JSONObject body = new JSONObject();
	    	if(!"".equals(teamCode)){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
//	    		TeamInfo teamInfo = teamInfoService.selectByCode(teamCode);
//	    		if(teamInfo != null){
//	    			// 统计操作
//	    			StatisticCommon statisticCommon = new StatisticCommon();
//	    			statisticCommon.setOrgCode(teamInfo.getOrgCode());
//	    			statisticCommon.setTeamCode(teamInfo.getTeamCode());
//	    			statisticCommon.setTeamName(teamInfo.getTeamName());
//	    			statisticCommon.setFaces(StatisticConstants.FACES);
//	    			statisticService.addRecord(statisticCommon);
//	    		}
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"操作"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("移除班组粉丝异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"操作"));
		}
	    return rj;
	}
	
	/**
	 * @Function unfollow
	 * @Description 取消关注
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/unfollow")
	public ResultJSON unfollow(@RequireValid UnfollowVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	String teamCode = teamInfoService.unfollow(param.getUserCode(), param.getTeamCode());
	    	JSONObject body = new JSONObject();
	    	if(!"".equals(teamCode)){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
//	    		TeamInfo teamInfo = teamInfoService.selectByCode(teamCode);
//	    		if(teamInfo != null){
//	    			// 统计操作
//	    			StatisticCommon statisticCommon = new StatisticCommon();
//	    			statisticCommon.setOrgCode(teamInfo.getOrgCode());
//	    			statisticCommon.setTeamCode(teamInfo.getTeamCode());
//	    			statisticCommon.setTeamName(teamInfo.getTeamName());
//	    			statisticCommon.setFaces(StatisticConstants.FACES);
//	    			statisticService.addRecord(statisticCommon);
//	    		}
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"取消关注"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("取消关注异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"取消关注"));
		}
	    return rj;
	}
	
	
	
	/**
	 * @Function fansList
	 * @Description 班组粉丝列表
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/fansList")
	public ResultJSON fansList(@RequireValid FansVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	//查询当前登录人关注列表
	    	List<String> concernsList = userRelationService.selectConcerns(param.getUserCode());
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
        	PageBean pageBean = teamInfoService.selectFans(pageParam,param.getTeamCode());
        	JSONObject body = new JSONObject();
        	List<TeamFans> fans = pageBean.getRecordList();
        	JSONArray jsonArray = new JSONArray();
	    	for (int i = 0; i < fans.size(); i++) {
	    		JSONObject info = new JSONObject();
	    		TeamFans teamFans = fans.get(i);
	    		info.put("teamFansCode", StringTools.formatToString(teamFans.getTeamFansCode()));//唯一标识
	    		info.put("userCode", StringTools.formatToString(teamFans.getUserCode()));//用户code
	    		SysUserInfo userInfo = sysUserService.selectByCode(teamFans.getUserCode());//查询用户
	    		info.put("isConcern", TeamConstants.BOOLEAN_0);//用户code
	    		for (int j = 0; j < concernsList.size(); j++) {
					if(concernsList.get(j).equals(teamFans.getUserCode())){//我关注了这个人
						info.put("isConcern", TeamConstants.BOOLEAN_1);
						concernsList.remove(j);
						break;
					}
				}
	    		if(userInfo != null){
	    			info.put("userName", StringTools.formatToString(userInfo.getUserName()));//粉丝名称
	    			info.put("head", StringTools.formatToString(userInfo.getHeadPortrait()));//头像
	    			SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(userInfo.getOrgCode());
	    			if(sysOrgInfo != null){
	    				info.put("orgName", StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));//组织机构名称
	    			}else{
	    				info.put("orgName", "");
	    			}
	    			MyCount myCount = userRelationService.selectMyCount(teamFans.getUserCode());
	    			info.put("concernsCount", StringTools.formatToString(myCount.getConcernsCount()));//关注数量
	    			info.put("friendsCount", StringTools.formatToString(myCount.getFriendsCount()));//好友数量
	    			info.put("fansCount", StringTools.formatToString(myCount.getFansCount()));//粉丝数量
	    		}else{
	    			info.put("userName", "");
	    			info.put("head", "");
	    			info.put("orgName", "");
	    			info.put("concernsCount", "");
	    			info.put("friendsCount", "");
	    			info.put("fansCount", "");
	    		}
	    		jsonArray.add(info);
			}
	    	body.put("totalCount", pageBean.getTotalCount());
	    	body.put("dataList", jsonArray);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"获取班组粉丝列表"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取班组粉丝列表异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取班组粉丝列表"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function concerns
	 * @Description 我关注的班组
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/concerns")
	public ResultJSON concerns(@RequireValid ConcernsTeamVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
        	PageBean pageBean = teamInfoService.selectConcernsTeam(pageParam,param.getOtherCode());
        	SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
        	JSONObject body = new JSONObject();
        	List<TeamFans> fans = pageBean.getRecordList();
        	JSONArray jsonArray = new JSONArray();
        	String teamIcon = authConfig.getTeamIcon();
	    	for (int i = 0; i < fans.size(); i++) {
	    		JSONObject info = new JSONObject();
	    		TeamFans teamFans = fans.get(i);
	    		info.put("teamFansCode", StringTools.formatToString(teamFans.getTeamFansCode()));//唯一标识
	    		info.put("teamCode", StringTools.formatToString(teamFans.getTeamCode()));//班组code
	    		info.put("teamName", StringTools.formatToString(teamFans.getTeamName()));//班组名称
		    	if(StringTools.isEmpty(teamFans.getTeamIcon())){
		    		info.put("teamIcon", teamIcon);//班组头像
	    		}else{
	    			info.put("teamIcon", StringTools.formatToString(teamFans.getTeamIcon()));//班组头像
	    		}
	    		info.put("teamIntro", StringTools.formatToString(teamFans.getTeamIntro()));//班组简介
	    		info.put("teamOrgCode", StringTools.formatToString(teamFans.getTeamOrgCode()));//班组所属组织机构code
	    		SysOrgInfo orgInfo = sysOrgService.selectByCode(teamFans.getTeamOrgCode());
	    		if(orgInfo != null){
	    			info.put("teamOrgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));//班组所属组织机构
	    		}else{
	    			info.put("teamOrgName", "");//班组所属组织机构
	    		}
	    		JSONArray memArray = new JSONArray();
    			List<String> userCodes = teamFans.getTeamMember();
    			List<SysUserInfo> userInfos = sysUserService.selectByCodes(userCodes);//查询用户
    			for (int j = 0; j < userInfos.size(); j++) {
    				JSONObject userJson = new JSONObject();
    				userJson.put("head", StringTools.formatToString(userInfos.get(j).getHeadPortrait()));
    				userJson.put("userName", StringTools.formatToString(userInfos.get(j).getUserName()));
    				userJson.put("userCode", StringTools.formatToString(userInfos.get(j).getUserCode()));
    				memArray.add(userJson);
    			}
	    		info.put("memList", memArray);
	    		JSONArray honorArray = new JSONArray();
	    		if(userInfo != null){
	    			List<HonorAwardRecord> honorList = honorThirdService.getHonorListForHonoree(teamFans.getTeamCode(), userInfo.getTenantCode());
	    			for (int j = 0; j < honorList.size() && j < 5; j++) {
						JSONObject honorInfo = new JSONObject();
						honorInfo.put("honorCode", StringTools.formatToString(honorList.get(j).getHonorAwardRecordCode()));
						honorInfo.put("honorIcon", StringTools.formatToString(honorList.get(j).getHonorUrl()));
						honorInfo.put("honorName", StringTools.formatToString(honorList.get(j).getHonorName()));
						honorArray.add(honorInfo);
					}
	    		}
	    		
	    		info.put("honorList", honorArray);
	    		jsonArray.add(info);
			}
	    	body.put("totalCount", pageBean.getTotalCount());
	    	body.put("dataList", jsonArray);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"获取我关注的班组"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取我关注的班组异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取我关注的班组"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function memberList
	 * @Description 班组成员列表
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/member/list")
	public ResultJSON memberList(@RequireValid TeamMemVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
			TeamMember record = new TeamMember();
			record.setTeamCode(param.getTeamCode());
			PageBean pageBean = teamMemberService.getTeamMemList(pageParam, record);
			
        	JSONObject body = new JSONObject();
        	List<TeamMember> memberList = pageBean.getRecordList();
        	JSONArray jsonArray = new JSONArray();
	    	for (int i = 0; i < memberList.size(); i++) {
	    		JSONObject info = new JSONObject();
	    		TeamMember member = memberList.get(i);
	    		SysUserInfo userInfo = sysUserService.selectByCode(member.getUserCode());
	    		if(userInfo != null){
	    			info.put("userCode", StringTools.formatToString(userInfo.getUserCode()));//用户code
	    			info.put("head", StringTools.formatToString(userInfo.getHeadPortrait()));//头像
	    			info.put("userName", StringTools.formatToString(userInfo.getUserName()));//用户名
	    			UserLevelConfig userLevelConfig = expAccountService.selectMultiByCode(member.getUserCode());
	    			if(userLevelConfig != null){
	    				info.put("level", StringTools.formatToString(userLevelConfig.getLevelNo()));//等级
	    				info.put("title", StringTools.formatToString(userLevelConfig.getLevelTitle()));//称号
	    			}else{
	    				info.put("level", "");
	    				info.put("title", "");
	    			}
//	    			int honor = honorThirdService.getHonorCount(userInfo.getUserCode(), AuthConstants.TENANT_CODE);
//	    			info.put("honor", honor);
	    			SysOrgInfo orgInfo = sysOrgService.selectByCode(userInfo.getOrgCode());
	    			if(orgInfo != null){
	    				info.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));//组织机构名称
	    			}else{
	    				info.put("orgName", "");
	    			}
	    			info.put("isLeader", StringTools.formatToString(member.getIsLeader()));//是否班组长
	    		}
	    		jsonArray.add(info);
			}
	    	body.put("totalCount", pageBean.getTotalCount());
	    	body.put("dataList", jsonArray);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"获取班组成员列表"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取班组成员列表异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取班组成员列表"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function addFriendTeam
	 * @Description 添加友好班组
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/friends/add")
	public ResultJSON addFriendTeam(@RequireValid FriendTeamAddVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	boolean isSuccess = teamInfoService.addFriendTeam(param.getUserCode(), param.getTeamCode());
	    	JSONObject body = new JSONObject();
	    	if(isSuccess){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"添加友好班组"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("添加友好班组异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"添加友好班组"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function userState
	 * @Description 我与其他班组的关系  是否关注  我的班组是否是该班组的友好班组
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/userState")
	public ResultJSON userState(@RequireValid FriendTeamAddVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	UserTeamState userState = teamInfoService.userState(param.getUserCode(), param.getTeamCode());
	    	JSONObject body = new JSONObject();
	    	if(userState != null){
	    		body.put("isConcern", StringTools.formatToString(userState.getIsConcern()));//我是否关注了这个班组
	    		body.put("isFriendTeam", StringTools.formatToString(userState.getIsFriendTeam()));//我为班组长的班组是否和该班组为友好班组
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"我与班组状态"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("我与班组状态异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"我与班组状态"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function delFriendTeam
	 * @Description 移除友好班组
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/friends/del")
	public ResultJSON delFriendTeam(@RequireValid FriendTeamDelVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	boolean isSuccess = teamInfoService.delFriendTeam(param.getTeamFriendCode());
	    	JSONObject body = new JSONObject();
	    	if(isSuccess){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"移除友好班组"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("移除友好班组异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"移除友好班组"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function friendsTeamList
	 * @Description 获取友好班组列表
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/friends/list")
	public ResultJSON friendsTeamList(@RequireValid FriendTeamListVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
			PageBean pageBean = teamInfoService.selectFriendTeam(pageParam, param.getTeamCode());
        	JSONObject body = new JSONObject();
        	List<TeamFriend> friendList = pageBean.getRecordList();
        	JSONArray jsonArray = new JSONArray();
        	String teamIcon = authConfig.getTeamIcon();
	    	for (int i = 0; i < friendList.size(); i++) {
	    		JSONObject info = new JSONObject();
	    		info.put("code",  StringTools.formatToString(friendList.get(i).getTeamFriendCode()));//唯一标识
	    		info.put("teamCode",  StringTools.formatToString(friendList.get(i).getFriendTeamCode()));//班组code
//	    		info.put("head", StringTools.formatToString(friendList.get(i).getTeamIcon()));//班组头像
		    	if(StringTools.isEmpty(friendList.get(i).getTeamIcon())){
		    		info.put("head", teamIcon);//班组头像
	    		}else{
	    			info.put("head", StringTools.formatToString(friendList.get(i).getTeamIcon()));//班组头像
	    		}
	    		info.put("name", StringTools.formatToString(friendList.get(i).getTeamName()));//班组名称
	    		int honor = honorThirdService.getHonorCount(friendList.get(i).getFriendTeamCode(), AuthConstants.TENANT_CODE);
	    		info.put("honor", honor);//班组荣誉数量
	    		info.put("createTime", StringTools.formatToString(friendList.get(i).getCreateTime()));//班组创建时间
	    		SysOrgInfo orgInfo = sysOrgService.selectByCode(friendList.get(i).getOrgCode());
    			if(orgInfo != null){
    				info.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));//班组所属组织机构名称
    			}else{
    				info.put("orgName", "");
    			}
    			info.put("intro",StringTools.formatToString(friendList.get(i).getTeamIntro()));//班组简介
	    		jsonArray.add(info);
			}
	    	body.put("totalCount", pageBean.getTotalCount());
	    	body.put("dataList", jsonArray);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"获取友好班组列表"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取友好班组列表异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取友好班组列表"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function messagesAdd
	 * @Description 班组留言
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/messages/add")
	public ResultJSON messagesAdd(@RequireValid MessageAddVo param) throws BusinessException{  
		Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	TeamMessage teamMessage = teamInfoService.addTeamMessage(param.getTeamCode(), param.getUserCode(), param.getMessage());
	    	JSONObject body = new JSONObject();
	    	if(teamMessage != null){
	    		body.put("teamMessageCode",  StringTools.formatToString(teamMessage.getTeamMessageCode()));//留言唯一标识
	    		body.put("mesDel",TeamConstants.BOOLEAN_1);//当前人是否可以删除该留言
	    		SysUserInfo userInfo = sysUserService.selectByCode(teamMessage.getUserCode());
	    		body.put("head",StringTools.formatToString(userInfo.getHeadPortrait()));//留言人头像
	    		body.put("userCode",StringTools.formatToString(userInfo.getUserCode()));//留言人code
	    		body.put("userName",StringTools.formatToString(userInfo.getUserName()));//留言人名称
    			SysOrgInfo orgInfo = sysOrgService.selectByCode(userInfo.getOrgCode());
    			if(orgInfo != null){
    				body.put("orgName",StringTools.formatToString(orgInfo.getSysOrgFullname()));//留言人组织机构
    			}else{
    				body.put("orgName","");
    			}
    			body.put("replyList", new JSONArray());//回复列表
    			body.put("message", StringTools.formatToString(teamMessage.getContent()));//留言内容
    			body.put("createTime", StringTools.formatToString(teamMessage.getCreateTime()));//留言时间
	    		
    			TeamInfo teamInfo = teamInfoService.selectByCode(teamMessage.getTeamCode());
	    		// 统计操作
				StatisticCommon statisticCommon = new StatisticCommon();
				statisticCommon.setOrgCode(teamInfo.getOrgCode());
				statisticCommon.setTeamCode(teamInfo.getTeamCode());
				statisticCommon.setTeamName(teamInfo.getTeamName());
				statisticCommon.setMessage(StatisticConstants.MESSAGE);
				statisticService.addRecord(statisticCommon);
    			
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"班组留言"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("班组留言异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"班组留言"));
		}
	    return rj;
	}
	
	/**
	 * @Function replyAdd
	 * @Description 班组长回复
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/reply/add")
	public ResultJSON replyAdd(@RequireValid ReplyAddVo param) throws BusinessException{  
		Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	TeamMessageReply teamMessageReply = teamInfoService.addMesReply(param.getMesCode(), param.getUserCode(),param.getReplyUserCode(), param.getReply());
	    	JSONObject body = new JSONObject();
	    	if(teamMessageReply != null){
	    		//组装数据返回
	    		JSONObject reply = new JSONObject();
	    		reply.put("replyId", StringTools.formatToString(teamMessageReply.getId()));//回复标识
				reply.put("replyCode", StringTools.formatToString(teamMessageReply.getReplyCode()));//回复标识
				reply.put("replyMessage", StringTools.formatToString(teamMessageReply.getReplyMessaage()));//回复内容
				SysUserInfo user = sysUserService.selectByCode(teamMessageReply.getUserCode());
				reply.put("replyUserCode", StringTools.formatToString(teamMessageReply.getUserCode()));//回复人code
				if(user != null){
					reply.put("replyUserHead", StringTools.formatToString(user.getHeadPortrait()));//回复人头像
					reply.put("replyUserName", StringTools.formatToString(user.getUserName()));//回复人
					reply.put("replyOrgName", StringTools.formatToString(user.getManageOrgInfo().getSysOrgFullname()));//回复人组织
				}else{
					reply.put("replyUserHead", "");
					reply.put("replyUserName", "");
					reply.put("replyOrgName", "");//回复人
				}
				user = sysUserService.selectByCode(teamMessageReply.getReplyUserCode());
				reply.put("receUserCode", StringTools.formatToString(teamMessageReply.getReplyUserCode()));//被回复人
				if(user != null){
					reply.put("receUserName", StringTools.formatToString(user.getUserName()));//被回复人
					reply.put("receOrgName", StringTools.formatToString(user.getManageOrgInfo().getSysOrgFullname()));//被回复人组织
				}else{
					reply.put("receUserName","");//被回复人
					reply.put("receOrgName", "");//被回复人组织
				}
				reply.put("replyDel",TeamConstants.BOOLEAN_1);//当前登录人是否可以删除该回复
				reply.put("replyTime", StringTools.formatToString(teamMessageReply.getCreateTime()));//回复时间
				body.put("reply", reply);
				
				//调用留言板接口
    			UserBoardRemind board = new UserBoardRemind();
    			board.setMsgCode(UUIDHelper.getUUID());
    			board.setOriginalContent(param.getOriginalContent());
    			board.setMsgContent(param.getReply());
    			board.setIsDelete(0);
    			board.setUserCode(param.getReplyUserCode());//收信息的人
    			board.setReceiveUserCode(param.getReplyUserCode());//收信息的人
    			board.setSendUserCode(param.getUserCode());//发信息的人
    			if(user != null){
    				board.setOrgCode(user.getManageOrgInfo().getSysOrgCode());//收信息人的org
    				board.setTenantCode(user.getTenantCode());//收信息的人租户code
    			}else{
    				board.setOrgCode("");//收信息人的org
    				board.setTenantCode("");//收信息的人租户code
    			}
    			board.setMsgType(MsgClassConstants.BOARD_MSG_TEAM);
    			board.setCreateTime(DateUtils.getFormatDateLong());
    			board.setTeamCode(param.getTeamCode());
	    		
	    		messagerBoradService.addBzBoard(board);
    		}
    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"班组长回复"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("班组长回复异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"班组长回复"));
		}
	    return rj;
	}
	
	/**
	 * @Function messagesDel
	 * @Description 删除班组留言
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/messages/del")
	public ResultJSON messagesDel(@RequireValid MessageDelVo param) throws BusinessException{  
		Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	TeamMessage teamMessage = teamInfoService.delTeamMessage(param.getMessageCode());
	    	JSONObject body = new JSONObject();
	    	if(teamMessage != null){
//	    		TeamInfo teamInfo = teamInfoService.selectByCode(teamMessage.getTeamCode());
//	    		// 统计操作
//				StatisticCommon statisticCommon = new StatisticCommon();
//				statisticCommon.setOrgCode(teamInfo.getOrgCode());
//				statisticCommon.setTeamCode(teamInfo.getTeamCode());
//				statisticCommon.setTeamName(teamInfo.getTeamName());
//				statisticCommon.setMessage(StatisticConstants.MESSAGE);
//				statisticService.addRecord(statisticCommon);
	    		
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"删除班组留言"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("删除班组留言异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"删除班组留言"));
		}
	    return rj;
	}
	
	/**
	 * @Function replyDel
	 * @Description 班组长回复删除
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/reply/del")
	public ResultJSON replyDel(@RequireValid ReplyDelVo param) throws BusinessException{  
		Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	boolean isSuccess = teamInfoService.delMesReply(param.getReplyCode());
	    	JSONObject body = new JSONObject();
	    	if(isSuccess){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"班组长回复删除"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("班组长回复删除异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"班组长回复删除"));
		}
	    return rj;
	}
	
	/**
	 * @Function messagesList
	 * @Description 班组留言列表
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/messages/list")
	public ResultJSON messagesList(@RequireValid MessageListVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    String userCode = param.getUserCode();
	    try {
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
			PageBean pageBean = teamInfoService.selectTeamMeassage(pageParam, param.getTeamCode());
        	JSONObject body = new JSONObject();
        	List<TeamMessage> messageList = pageBean.getRecordList();
        	JSONArray jsonArray = new JSONArray();
        	boolean isLeader = false;
	    	for (int i = 0; i < messageList.size(); i++) {
	    		JSONObject info = new JSONObject();
	    		info.put("teamMessageCode",  StringTools.formatToString(messageList.get(i).getTeamMessageCode()));//留言唯一标识
	    		SysUserInfo userInfo = sysUserService.selectByCode(messageList.get(i).getUserCode());
	    		if(userInfo != null){
	    			if(i == 0){//校验当前登录人是不是班组长,是班组长可以删除任何留言
	    				for (int j = 0; j < messageList.get(i).getLeaders().size(); j++) {
	    					if(userCode.equals(messageList.get(i).getLeaders().get(j))){
	    						isLeader = true;
	    						break;
	    					}
	    				}
	    			}
	    			if(!isLeader){//不是组长 校验是不是本人 本人只能删除自己在的留言
	    				if(userCode.equals(userInfo.getUserCode())){
	    					info.put("mesDel",TeamConstants.BOOLEAN_1);
	    				}else{
	    					info.put("mesDel",TeamConstants.BOOLEAN_0);
	    				}
	    			}else{//组长
	    				info.put("mesDel",TeamConstants.BOOLEAN_1);//删除当前留言标识
	    			}
	    			info.put("head",StringTools.formatToString(userInfo.getHeadPortrait()));//头像
	    			info.put("userCode",StringTools.formatToString(userInfo.getUserCode()));//用户code
	    			info.put("userName",StringTools.formatToString(userInfo.getUserName()));//用户名
	    			SysOrgInfo orgInfo = sysOrgService.selectByCode(userInfo.getOrgCode());
	    			if(orgInfo != null){
	    				info.put("orgName",StringTools.formatToString(orgInfo.getSysOrgFullname()));//组织机构名称
	    			}else{
	    				info.put("orgName","");
	    			}
	    			JSONArray replyArray = new JSONArray();
	    			List<TeamMessageReply> replyList = messageList.get(i).getTeamMessageReply();
	    			info.put("replyCount", replyList.size());//回复总条数
	    			if(replyList.size()>0){//循环留言
	    				int replyCount = Integer.parseInt(param.getReplyCount());
	    				for (int j = 0; j < replyList.size() && j < replyCount; j++) {
	    					JSONObject reply = new JSONObject();
	    					reply.put("replyId", StringTools.formatToString(replyList.get(j).getId()));//回复Id
	    					reply.put("replyCode", StringTools.formatToString(replyList.get(j).getReplyCode()));//回复标识
	    					reply.put("replyMessage", StringTools.formatToString(replyList.get(j).getReplyMessaage()));//回复内容
	    					userInfo = sysUserService.selectByCode(replyList.get(j).getUserCode());
	    					reply.put("replyUserCode", StringTools.formatToString(replyList.get(j).getUserCode()));//回复人code
	    					if(userInfo != null){
	    						reply.put("replyUserHead", StringTools.formatToString(userInfo.getHeadPortrait()));//回复人头像
	    						reply.put("replyUserName", StringTools.formatToString(userInfo.getUserName()));//回复人
	    						reply.put("replyOrgName", StringTools.formatToString(userInfo.getManageOrgInfo().getSysOrgFullname()));//回复人组织
	    					}else{
	    						reply.put("replyUserHead", "");
	    						reply.put("replyUserName", "");
	    						reply.put("replyOrgName", "");//回复人
	    					}
	    					reply.put("receUserCode", StringTools.formatToString(replyList.get(j).getReplyUserCode()));//被回复人code
	    					userInfo = sysUserService.selectByCode(replyList.get(j).getReplyUserCode());
	    					if(userInfo != null){
	    						reply.put("receUserName", StringTools.formatToString(userInfo.getUserName()));//被回复人
	    						reply.put("receOrgName", StringTools.formatToString(userInfo.getManageOrgInfo().getSysOrgFullname()));//被回复人组织
	    					}else{
	    						reply.put("receUserName", "");//被回复人
	    						reply.put("receOrgName", "");//被回复人组织
	    					}
	    					
	    					if(userCode.equals(replyList.get(j).getUserCode())){
	    						reply.put("replyDel",TeamConstants.BOOLEAN_1);//当前登录人是否可以删除该回复
	    					}else{
	    						reply.put("replyDel",TeamConstants.BOOLEAN_0);
	    					}
	    					reply.put("replyTime", StringTools.formatToString(replyList.get(j).getCreateTime()));//回复时间
	    					replyArray.add(reply);
						}
	    			}
	    			info.put("replyList", replyArray);
	    			info.put("message", StringTools.formatToString(messageList.get(i).getContent()));
	    			info.put("createTime", StringTools.formatToString(messageList.get(i).getCreateTime()));
	    		}
	    		jsonArray.add(info);
			}
	    	body.put("totalCount", pageBean.getTotalCount());
	    	body.put("dataList", jsonArray);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"获取班组留言列表"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("班组留言列表异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取班组留言列表"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function replyList
	 * @Description 留言回复加载更多
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/reply/list")
	public ResultJSON replyList(@RequireValid ReplyListVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    String userCode = param.getUserCode();
	    try {
	    	long id = Long.parseLong(param.getLastId());
	    	int pageCount = Integer.parseInt(param.getCount());
			List<TeamMessageReply> replyList = teamInfoService.selectReply(id, pageCount, param.getMesCode());
        	JSONObject body = new JSONObject();
        	JSONArray jsonArray = new JSONArray();
        	for (int j = 0; j < replyList.size(); j++) {
				JSONObject reply = new JSONObject();
				reply.put("replyId", StringTools.formatToString(replyList.get(j).getId()));//回复标识
				reply.put("replyCode", StringTools.formatToString(replyList.get(j).getReplyCode()));//回复标识
				reply.put("replyMessage", StringTools.formatToString(replyList.get(j).getReplyMessaage()));//回复内容
				SysUserInfo userInfo = sysUserService.selectByCode(replyList.get(j).getUserCode());
				reply.put("replyUserCode", StringTools.formatToString(replyList.get(j).getUserCode()));//回复人code
				if(userInfo != null){
					reply.put("replyUserHead", StringTools.formatToString(userInfo.getHeadPortrait()));//回复人头像
					reply.put("replyUserName", StringTools.formatToString(userInfo.getUserName()));//回复人
					reply.put("replyOrgName", StringTools.formatToString(userInfo.getManageOrgInfo().getSysOrgFullname()));//回复人组织
				}else{
					reply.put("replyUserHead", "");
					reply.put("replyUserName", "");
					reply.put("replyOrgName", "");//回复人
				}
				userInfo = sysUserService.selectByCode(replyList.get(j).getReplyUserCode());
				reply.put("receUserCode", StringTools.formatToString(replyList.get(j).getReplyUserCode()));//被回复人code
				if(userInfo != null){
					reply.put("receUserName", StringTools.formatToString(userInfo.getUserName()));//被回复人
					reply.put("receOrgName", StringTools.formatToString(userInfo.getManageOrgInfo().getSysOrgFullname()));//被回复人组织
				}else{
					reply.put("receUserName", "");//被回复人
					reply.put("receOrgName", "");//被回复人组织
				}
				if(userCode.equals(replyList.get(j).getUserCode())){
					reply.put("replyDel",TeamConstants.BOOLEAN_1);//当前登录人是否可以删除该回复
				}else{
					reply.put("replyDel",TeamConstants.BOOLEAN_0);
				}
				reply.put("replyTime", StringTools.formatToString(replyList.get(j).getCreateTime()));//回复时间
				jsonArray.add(reply);
			}
	    	body.put("dataList", jsonArray);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"班组留言回复加载更多"));
	    	rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("班组留言回复加载更多异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"班组留言回复加载更多"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function teamVisitors
	 * @Description 班组访客
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/visitor/list")
	public ResultJSON visitorList(@RequireValid TeamVisitorVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
			StatisticVisitDetail record = new StatisticVisitDetail();
			record.setTeamCode(param.getTeamCode());
			PageBean pageBean = statisticService.getVisit(pageParam, record);//调用访客接口
			JSONObject body = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			List<StatisticVisitDetail> visitorList = pageBean.getRecordList();
			for (int i = 0; i < visitorList.size(); i++) {
				JSONObject info = new JSONObject();
				SysUserInfo userInfo = sysUserService.selectByCode(visitorList.get(i).getVisitCode());
				info.put("id", StringTools.formatToString(visitorList.get(i).getId()));//唯一标识
				info.put("head", StringTools.formatToString(userInfo.getHeadPortrait()));//头像
				info.put("userName", StringTools.formatToString(userInfo.getUserName()));//用户名
				info.put("userCode", StringTools.formatToString(userInfo.getUserCode()));//用户code
				info.put("visitTime", StringTools.formatToString(visitorList.get(i).getVisitTime()));//访问时间
				jsonArray.add(info);
			}
			record.setStartTime(DateUtils.getCurDayStart());
			record.setEndTime(DateUtils.getCurDayEnd());
			int dayCount = statisticService.getTodayVisit(record);//得到今日访问量
			body.put("dayCount", dayCount);
	    	body.put("totalCount", pageBean.getNumPerPage());
	    	body.put("totalViewCount", pageBean.getTotalCount());
	    	body.put("dataList", jsonArray);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"获取班组访客列表"));
	    	rj.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("班组访客列表异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取班组访客列表"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function memberDevote
	 * @Description 班组成员贡献排行
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/memberDevote")
	public ResultJSON memberDevote(@RequireValid TeamMemDevoteVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
			List<TeamMember> teamMembers = teamMemberService.selectByTeamCode(param.getTeamCode());
			List<String> userCodes = new ArrayList<String>();
			for (int i = 0; i < teamMembers.size(); i++) {
				userCodes.add(teamMembers.get(i).getUserCode());
			}
			if(userCodes.size() == 0){
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
		    	rj.setReturnCode(m.getCode());
		    	rj.setReturnTip(String.format(m.getTip(),"班组成员贡献排行","该班组无班组成员"));
		    	rj.setBody(new JSONObject());
		    	return rj;
			}
			RankEntity record = new RankEntity();
			record.setList(userCodes);
			PageBean pageBean = integralAccountService.selectPageByRank(pageParam, record);
			JSONObject body = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			List<IntegralAndCoin> recordList = pageBean.getRecordList();
			for (int i = 0; i < recordList.size(); i++) {
				JSONObject info = new JSONObject();
				info.put("rank", StringTools.formatToString(recordList.get(i).getRownum()));//排名
				SysUserInfo userInfo = sysUserService.selectByCode(recordList.get(i).getAccountCode());
				if(userInfo != null){
					info.put("userCode", StringTools.formatToString(userInfo.getUserCode()));
					info.put("head", StringTools.formatToString(userInfo.getHeadPortrait()));//头像
					info.put("userName", StringTools.formatToString(userInfo.getUserName()));//用户名
					info.put("score", StringTools.formatToString(recordList.get(i).getIntegralBalance()));//积分
					info.put("gold", StringTools.formatToString(recordList.get(i).getCoinBalance()));//金币
				}else{
					info.put("userCode", "");
					info.put("head", "");
					info.put("userName", "");
					info.put("score", "");
					info.put("gold", "");
				}
				jsonArray.add(info);
			}
			body.put("dataList", jsonArray);
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"获取班组成员贡献排行"));
	    	rj.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("班组成员贡献排行异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"班组成员贡献排行"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function delVisitor
	 * @Description 删除班组访客
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/visitor/del")
	public ResultJSON delVisitor(@RequireValid DelVisitorVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	StatisticVisitDetail record = new StatisticVisitDetail();
	    	record.setVisitCode(param.getVisitCode());
	    	record.setTeamCode(param.getTeamCode());
	    	int count = statisticService.deleteVisit(record);
	    	if(count >= 1){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    		rj.setReturnCode(m.getCode());
	    		rj.setReturnTip(String.format(m.getTip(),"删除班组访客"));
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    		rj.setReturnCode(m.getCode());
	    		rj.setReturnTip(String.format(m.getTip(),"删除班组访客"));
	    	}
	    	rj.setBody(new JSONObject());
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("删除班组访客异常", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"删除班组访客"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function listByOrg
	 * @Description 根据orgCode返回班组列表,属于这个orgCode的班组列表
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/list/byOrg")
	public ResultJSON listByOrg(@RequireValid ListByOrgVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
	    	PageBean pageBean = teamInfoService.selectByOrgCode(pageParam,param.getOrgCode());
	    	JSONObject body = new JSONObject();
	    	JSONArray array= new JSONArray();
	    	String teamIcon = authConfig.getTeamIcon();
	    	List<TeamInfo> teamInfoList = pageBean.getRecordList();
	    	for (int i = 0; i < teamInfoList.size(); i++) {
	    		JSONObject teamInfo = new JSONObject();
	    		teamInfo.put("teamCode", StringTools.formatToString(teamInfoList.get(i).getTeamCode()));//班组code
	    		if(StringTools.isEmpty(teamInfoList.get(i).getTeamIcon())){
	    			teamInfo.put("teamIcon", teamIcon);//班组头像
	    		}else{
	    			teamInfo.put("teamIcon", StringTools.formatToString(teamInfoList.get(i).getTeamIcon()));//班组头像
	    		}
	    		teamInfo.put("teamName", StringTools.formatToString(teamInfoList.get(i).getTeamName()));//班组名称
	    		SysOrgInfo orgInfo = sysOrgService.selectByCode(teamInfoList.get(i).getOrgCode());
	    		if(orgInfo != null){
	    			teamInfo.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));//所属组织机构
	    		}else{
	    			teamInfo.put("orgName", "");
	    		}
	    		IntegralAccount integralAccount = integralAccountService.selectByAccountCode(teamInfoList.get(i).getTeamCode());
	    		if(integralAccount != null){
	    			teamInfo.put("score", StringTools.formatToString(integralAccount.getIntegralBalance()));
	    		}else{
	    			teamInfo.put("score", "0");//积分
	    		}
	    		teamInfo.put("count", StringTools.formatToString(teamInfoList.get(i).getSumCount()));//班组人数
	    		teamInfo.put("intro", StringTools.formatToString(teamInfoList.get(i).getTeamIntro()));//班组简介
	    		
	    		array.add(teamInfo);
			}
	    	body.put("dataList", array);
	    	body.put("totalCount", pageBean.getTotalCount());
	    	rj.setBody(body);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
    		rj.setReturnCode(m.getCode());
    		rj.setReturnTip(String.format(m.getTip(),"获取班组信息"));
	    } catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取班组信息", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取班组信息"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function allByOrg
	 * @Description 不带分页
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/all/byOrg")
	public ResultJSON allByOrg(@RequireValid AllByOrgVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	PageBean pageBean = teamInfoService.selectByOrgCode(null,param.getOrgCode());
	    	JSONObject body = new JSONObject();
	    	JSONArray array= new JSONArray();
	    	List<TeamInfo> teamInfoList = pageBean.getRecordList();
	    	SysOrgInfo orgInfo = sysOrgService.selectByCode(param.getOrgCode());
	    	String teamIcon = authConfig.getTeamIcon();//默认头像
	    	for (int i = 0; i < teamInfoList.size(); i++) {
	    		JSONObject teamInfo = new JSONObject();
	    		teamInfo.put("teamCode", StringTools.formatToString(teamInfoList.get(i).getTeamCode()));//班组code
	    		if(StringTools.isEmpty(teamInfoList.get(i).getTeamIcon())){
	    			teamInfo.put("teamIcon", teamIcon);//班组头像
	    		}else{
	    			teamInfo.put("teamIcon", StringTools.formatToString(teamInfoList.get(i).getTeamIcon()));//班组头像
	    		}
	    		teamInfo.put("teamName", StringTools.formatToString(teamInfoList.get(i).getTeamName()));//班组名称
	    		if(orgInfo != null){
	    			teamInfo.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));//所属组织机构
	    		}else{
	    			teamInfo.put("orgName", "");
	    		}
	    		
	    		array.add(teamInfo);
			}
	    	body.put("dataList", array);
	    	body.put("totalCount", pageBean.getTotalCount());
	    	rj.setBody(body);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
    		rj.setReturnCode(m.getCode());
    		rj.setReturnTip(String.format(m.getTip(),"获取班组信息"));
	    } catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取班组信息", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取班组信息"));
		}
	    return rj;
	}
	
	
	/**
	 * @Function search
	 * @Description 班组搜索
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/search")
	public ResultJSON search(@RequireValid SearchVo param) throws BusinessException{  
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
	    	PageBean pageBean = teamInfoService.selectByName(pageParam,param.getTeamName());
	    	JSONObject body = new JSONObject();
	    	JSONArray array= new JSONArray();
	    	List<TeamInfo> teamInfoList = pageBean.getRecordList();
	    	String teamIcon = authConfig.getTeamIcon();//默认头像
	    	for (int i = 0; i < teamInfoList.size(); i++) {
	    		JSONObject teamInfo = new JSONObject();
	    		teamInfo.put("teamCode", StringTools.formatToString(teamInfoList.get(i).getTeamCode()));//班组code
		    	if(StringTools.isEmpty(teamInfoList.get(i).getTeamIcon())){
		    		teamInfo.put("teamIcon", teamIcon);//班组头像
	    		}else{
	    			teamInfo.put("teamIcon", StringTools.formatToString(teamInfoList.get(i).getTeamIcon()));//班组头像
	    		}
	    		teamInfo.put("teamName", StringTools.formatToString(teamInfoList.get(i).getTeamName()));//班组名称
	    		teamInfo.put("teamIntro", StringTools.formatToString(teamInfoList.get(i).getTeamIntro()));//班组名称
	    		SysOrgInfo orgInfo = sysOrgService.selectByCode(teamInfoList.get(i).getOrgCode());
	    		if(orgInfo != null){
	    			teamInfo.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));//所属组织机构
	    		}else{
	    			teamInfo.put("orgName", "");
	    		}
	    		
	    		array.add(teamInfo);
			}
	    	body.put("dataList", array);
	    	body.put("totalCount", pageBean.getTotalCount());
	    	rj.setBody(body);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
    		rj.setReturnCode(m.getCode());
    		rj.setReturnTip(String.format(m.getTip(),"获取班组信息"));
	    } catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取班组信息", e);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取班组信息"));
		}
	    return rj;
	}
	
	/**
	 * 查询班组留言墙信息，该接口将指定的留言放在结果集中的第一位
	 * 
	 * @param param
	 * @return 班组留言墙信息集合
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/searchTeamMessageGPS")
	public ResultJSON searchTeamMessageGPS(@RequireValid MessageListVo param) throws BusinessException{  
	    
		Message m = null;		
	    ResultJSON rj = new ResultJSON();
	    JSONObject body = new JSONObject();
	    
	    String clientUserCode = param.getUserCode();
	    try {
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()), Integer.parseInt(param.getPageSize()));
			
			MessageGPSVo messageGPSVo = new MessageGPSVo();
			messageGPSVo.setTeamCode(param.getTeamCode());
			messageGPSVo.setMessageCode(param.getMessageCode());
			messageGPSVo.setReplyCode(param.getReplyCode());
			
			// 查询班组留言信息，并将需要定位的留言和留言回复放在结果集的第一行
			PageBean pageBean = teamInfoService.searchTeamMessageGPS(pageParam, messageGPSVo);        	
        	List<TeamMessage> messageList = pageBean.getRecordList();
        	
        	// 判断当前登录人是否为班组长
        	List<String> teamLeaderCodes = teamMemberService.selectTeamLeaders(param.getTeamCode());        	
        	boolean isLeader = checkUserIsTeamLeader(clientUserCode, teamLeaderCodes);        	
        	
        	JSONArray messageArray = new JSONArray();
        		    	
        	for (int i = 0; i < messageList.size(); i++) {
        		TeamMessage teamMessage = messageList.get(i);        		
	    		JSONObject messageObj = new JSONObject();	    		    		
	    		
	    		SysUserInfo messageUserInfo = sysUserService.selectByCode(teamMessage.getUserCode());	    		
	    		if(messageUserInfo != null){
	    			// 设置留言信息结果集
	    			setMessageProperty(teamMessage, messageObj, messageUserInfo, clientUserCode, isLeader);
	    				    			
	    			List<TeamMessageReply> replyList = teamMessage.getTeamMessageReply();
	    			// 留言回复记录数统计
	    			messageObj.put("replyCount", replyList.size());		
	    			
	    			JSONArray replyArray = new JSONArray();	    			
	    			if(replyList.size()>0){	    				
	    				// 用以控制结果集中留言的回复记录数
	    				int replyCount = Integer.parseInt(param.getReplyCount());	
	    				for (int j = 0; j < replyList.size() && j < replyCount; j++) {
	    					// 设置留言回复结果集
	    					replyArray.add(setMessageReplyProperty(replyList.get(j), clientUserCode));
						}
	    			}
	    			messageObj.put("replyList", replyArray);
	    		}
	    		messageArray.add(messageObj);
			}
	    	
        	body.put("totalCount", pageBean.getTotalCount());
	    	body.put("dataList", messageArray);
	    	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"查询班组留言墙信息（举报定位）"));
	    	rj.setBody(body);
		} catch (Exception e) {
			String errMsg = "查询班组留言墙信息（举报定位）出错";
			log.error(errMsg, e);
			
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);			
			throw new BusinessException(m.getCode(), String.format(m.getTip(), errMsg));
		}
	    return rj;
	}
	
	/**
	 * 判断指定用户是否为班组长
	 * 
	 * @param userCode
	 * 			待校验的用户编码
	 * @param teamLeaderCodes
	 * 			班组长code集合
	 * @return true, 是班组长；false, 不是班组长
	 */
	private boolean checkUserIsTeamLeader(String userCode, List<String> teamLeaderCodes) {
		
		if(teamLeaderCodes.contains(userCode)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 设置班组留言结果集属性
	 * 
	 * @param teamMessage
	 * 			班组留言信息
	 * @param messageObj
	 * 			班组留言结果集对象
	 * @param messageUserInfo
	 * 			留言人信息
	 * @param clientUserCode
	 * 			登录人code
	 * @param isLeader
	 * 			登录人是否为班组长
	 */
	private void setMessageProperty(TeamMessage teamMessage, JSONObject messageObj, 
			SysUserInfo messageUserInfo, String clientUserCode, boolean isLeader) {
				
		if(!isLeader){
			// 不是班组长，校验登陆人是否为当前留言的留言人，用以控制本人只能删除自己的留言
			if(clientUserCode.equals(messageUserInfo.getUserCode())){
				messageObj.put("mesDel",TeamConstants.BOOLEAN_1);
			}else{
				messageObj.put("mesDel",TeamConstants.BOOLEAN_0);
			}
		}else{
			// mesDel 标识，用以控制当前登陆人是否可删除留言；1，可删除；0，不可删除
			messageObj.put("mesDel",TeamConstants.BOOLEAN_1);
		}
		
		messageObj.put("head",StringTools.formatToString(messageUserInfo.getHeadPortrait()));				// 留言人头像
		messageObj.put("userCode",StringTools.formatToString(messageUserInfo.getUserCode()));				// 留言人code
		messageObj.put("userName",StringTools.formatToString(messageUserInfo.getUserName()));				// 留言人姓名
		messageObj.put("teamMessageCode",  StringTools.formatToString(teamMessage.getTeamMessageCode()));	// 留言code	    			
		messageObj.put("message", StringTools.formatToString(teamMessage.getContent()));					// 留言内容
		messageObj.put("createTime", StringTools.formatToString(teamMessage.getCreateTime()));	    		// 留言时间	
		
		SysOrgInfo orgInfo = messageUserInfo.getOrgInfo();
		if(orgInfo != null){
			messageObj.put("orgName",StringTools.formatToString(orgInfo.getSysOrgFullname()));				// 留言人所属组织
		}else{
			messageObj.put("orgName","");
		}
	}
	
	/**
	 * 设置班组留言回复结果集属性
	 * 
	 * @param messageReply
	 * 			班组留言回复信息
	 * @param clientUserCode
	 * 			登录用户code
	 * @return 班组留言回复结果集对象
	 * @throws Exception
	 */
	private JSONObject setMessageReplyProperty(TeamMessageReply messageReply, String clientUserCode) throws Exception {
		
		try {
			JSONObject replyResult = new JSONObject();
			
			replyResult.put("replyId", StringTools.formatToString(messageReply.getId()));					// 回复id
			replyResult.put("replyCode", StringTools.formatToString(messageReply.getReplyCode()));			// 回复code
			replyResult.put("replyMessage", StringTools.formatToString(messageReply.getReplyMessaage()));	// 回复内容	    					
			replyResult.put("replyUserCode", StringTools.formatToString(messageReply.getUserCode()));		// 回复人code
			replyResult.put("replyTime", StringTools.formatToString(messageReply.getCreateTime()));			// 回复时间
			replyResult.put("receUserCode", StringTools.formatToString(messageReply.getReplyUserCode()));	// 被回复人code 
			
			SysUserInfo messageUserInfo = sysUserService.selectByCode(messageReply.getUserCode());
			if(messageUserInfo != null){
				replyResult.put("replyUserHead", StringTools.formatToString(messageUserInfo.getHeadPortrait()));	// 回复人头像
				replyResult.put("replyUserName", StringTools.formatToString(messageUserInfo.getUserName()));		// 回复人姓名
				replyResult.put("replyOrgName", 
						StringTools.formatToString(messageUserInfo.getManageOrgInfo().getSysOrgFullname()));		// 回复人所属组织
			}else{
				replyResult.put("replyUserHead", "");
				replyResult.put("replyUserName", "");
				replyResult.put("replyOrgName", "");
			}
				    					
			messageUserInfo = sysUserService.selectByCode(messageReply.getReplyUserCode());
			if(messageUserInfo != null){
				replyResult.put("receUserName", StringTools.formatToString(messageUserInfo.getUserName()));		// 被回复人姓名
				replyResult.put("receOrgName", 
						StringTools.formatToString(messageUserInfo.getManageOrgInfo().getSysOrgFullname()));	// 被回复人所属组织
			}else{
				replyResult.put("receUserName", "");
				replyResult.put("receOrgName", "");
			}
			
			// replyDel 标识用以控制当前回复是否可以被登录员工删除
			if(clientUserCode.equals(messageReply.getUserCode())){
				replyResult.put("replyDel",TeamConstants.BOOLEAN_1);
			}else{
				replyResult.put("replyDel",TeamConstants.BOOLEAN_0);
			}
			return replyResult;
		} catch (Exception e) {
			throw new Exception("设置班组留言回复结果集属性出错", e);
		}	    					
	}
}
