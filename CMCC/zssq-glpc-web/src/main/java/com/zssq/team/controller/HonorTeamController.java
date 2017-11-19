package com.zssq.team.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.AuthConstants;
import com.zssq.constants.MsgClassConstants;
import com.zssq.constants.MsgTopicConstants;
import com.zssq.constants.TeamConstants;
import com.zssq.dao.pojo.HonorTeam;
import com.zssq.dao.pojo.MyManageTeam;
import com.zssq.dao.pojo.RecommondTeam;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamCourse;
import com.zssq.dao.pojo.TeamElect;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.dao.pojo.UserMsgNotice;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamCourseService;
import com.zssq.service.ITeamElectService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.RelationThirdDynamicService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.team.vo.ElectListVo;
import com.zssq.team.vo.HonorTeamAddVo;
import com.zssq.team.vo.HonorTeamDelVo;
import com.zssq.team.vo.HonorTeamHisVo;
import com.zssq.team.vo.HonorTeamNew;
import com.zssq.team.vo.MyManageTeamVo;
import com.zssq.team.vo.ReceiveTeamVo;
import com.zssq.team.vo.TeamCourseVo;
import com.zssq.team.vo.TeamElectVo;
import com.zssq.team.vo.ToSuperiorVo;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;
import com.zssq.vo.RelationDynamicVO;

/**
 * @ClassName TeamHonorController
 * @Description 百强班组
 * @author JiPengChun
 * @date 2017年3月17日 下午5:35:01
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/honorTeam")
public class HonorTeamController {

	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 班组评选service
	 */
	@Autowired
	private ITeamElectService teamElectService;
	
	/**
	 * 班组基本信息service
	 */
	@Autowired
	private ITeamInfoService teamInfoService;
	
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
	 * 班组推荐历程service
	 */
	@Autowired
	private ITeamCourseService teamCourseService;
	
	/**
	 * 动态关系service
	 */
	@Autowired
	private RelationThirdDynamicService relationThirdDynamicService;
	
	/**
	 * Kafka
	 */
	@SuppressWarnings("rawtypes")
	@Autowired
	private KafkaProducerTemplate producerTemplate;
	
	
	/**
	 * @Function startElect
	 * @Description 开始评选
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/startElect") 
	public ResultJSON startElect(@RequireValid TeamElectVo param) throws BusinessException{
		Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	TeamElect record = new TeamElect();
        	record.setTeamElectCode(teamElectService.getElectCode());
        	record.setElectTitle(param.getElectTitle());
        	record.setElectDetail(param.getElectDetail());
        	record.setIsLatest(TeamConstants.ELECT_TYPE_2);//正在进行的评选标志  2
        	record.setElectStartTime(DateUtils.stringToLong(param.getElectStartTime(), "yyyy-MM-dd"));
        	record.setElectEndTime(DateUtils.stringToLong(param.getElectEndTime(), "yyyy-MM-dd") + TeamConstants.DAY_TIME);
        	record.setUserCode(param.getUserCode());
        	boolean isSuccess = teamElectService.startElect(record);
        	if(isSuccess){
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
        		//拿到有评选菜单权限的人,给这些人发通知
        		List<SysUserInfo> userList = sysUserService.selectUserByMenuOrg(null, TeamConstants.MENU_ELECTMANAGE);
        		List<UserMsgNotice> list = new ArrayList<UserMsgNotice>();
        		SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
        		String orgName = "";
        		if(userInfo != null){
        			orgName = userInfo.getManageOrgInfo().getSysOrgFullname();
        		}
        		for (int i = 0; i < userList.size(); i++) {
        			UserMsgNotice notice = new UserMsgNotice();
    				notice.setNoticeCode(UUIDHelper.getUUID());
    				notice.setTitle(TeamConstants.TEAM_PUSH_ELECT_TITLE);
    				notice.setCreateTime(DateUtils.getFormatDateLong());
    				notice.setIsDelete((int)TeamConstants.BOOLEAN_FALSE);
    				notice.setUserCode(userList.get(i).getUserCode());
    				notice.setOrgCode(userList.get(i).getManOrgCode());
    				notice.setTenantCode(AuthConstants.TENANT_CODE);
    				notice.setType(MsgClassConstants.NOTICE_MSG_TYPE);
    				notice.setCreatUserCode(param.getUserCode());
    				String time = DateUtils.getDateString(DateUtils.getFormatDateLong(), "yyyy年MM月dd日 HH:mm:ss");
    				notice.setContent(String.format(TeamConstants.TEAM_PUSH_ELECT, time ,orgName));
    				list.add(notice);
				}
        		//往kafka推消息
        		producerTemplate.send(MsgTopicConstants.TOPIC_NOTICE, JSON.toJSONString(list));
        	}else{
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
        	}
        	rj.setReturnCode(m.getCode());
        	rj.setReturnTip(String.format(m.getTip(),"开始评选设置"));
        	rj.setBody(new JSONObject());
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("开始评选设置异常",e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"开始评选设置"));
		}
        return rj;
	}
	
	/**
	 * @Function closeElect
	 * @Description 完成评选
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/closeElect") 
	public ResultJSON closeElect(HttpServletRequest request) throws BusinessException{
		Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	boolean isSuccess = teamElectService.closeElect();
	    	if(isSuccess){
	    		//调用关系服务
	    		List<String> teamCodes = teamElectService.selectLastHonorTeam();
	    		RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
	    		relationDynamicVO.setModifyTime(DateUtils.getFormatDateLong());
	    		relationDynamicVO.setTeamCodeList(teamCodes);
	    		relationThirdDynamicService.updateTeamExc(relationDynamicVO);
	    		//发通知    查询本期百强班组,给百强班组所在的行政组织并且有菜单权限的人发信息
	    		TeamElect teamElect = teamElectService.getLastTeamElect();
	    		if(teamElect != null){
	    			String electCode = teamElect.getTeamElectCode();
	    			//拿到班组集合
	    			List<HonorTeam> honorTeamList = teamCourseService.selectLastHonorTeam(electCode);
	    			List<String> orgList = new ArrayList<String>();
	    			for (int i = 0; i < honorTeamList.size(); i++) {
	    				orgList.add(honorTeamList.get(i).getOrgCode());
					}
	    			if(orgList.size()>0){
	    				//拿到通知人员集合
	    				List<SysUserInfo> userList = sysUserService.selectUserByMenuOrgs(orgList, TeamConstants.MENU_ELECTMANAGE);
	    				//构建通知对象
	    				List<UserMsgNotice> list = new ArrayList<UserMsgNotice>();
	    				for (int i = 0; i < userList.size(); i++) {
	    					UserMsgNotice notice = new UserMsgNotice();
	        				notice.setNoticeCode(UUIDHelper.getUUID());
	        				notice.setTitle(TeamConstants.TEAM_PUSH_HONOR_TITLE);
	        				notice.setCreateTime(DateUtils.getFormatDateLong());
	        				notice.setIsDelete((int)TeamConstants.BOOLEAN_FALSE);
	        				notice.setUserCode(userList.get(i).getUserCode());
	        				notice.setOrgCode(userList.get(i).getManOrgCode());
	        				notice.setTenantCode(AuthConstants.TENANT_CODE);
	        				notice.setType(MsgClassConstants.NOTICE_MSG_TYPE);
	        				notice.setCreatUserCode(request.getParameter("userCode"));
	        				for (int j = 0; j < honorTeamList.size(); j++) {
	        					if(honorTeamList.get(j).getOrgCode().equals(userList.get(i).getManOrgCode())){
	        						String time = DateUtils.getDateString(DateUtils.getFormatDateLong(), "yyyy年MM月dd日 HH:mm:ss");
	        						String teamName = honorTeamList.get(j).getTeamName();
	        						notice.setContent(String.format(TeamConstants.TEAM_PUSH_HONOR,teamName,time));
	        						list.add(notice);
	        					}
	        				}
	    				}
	    				producerTemplate.send(MsgTopicConstants.TOPIC_NOTICE, JSON.toJSONString(list));
	    			}
	    		}
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    	}else{
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    	}
	    	rj.setReturnCode(m.getCode());
	    	rj.setReturnTip(String.format(m.getTip(),"完成评选设置"));
	    	rj.setBody(new JSONObject());
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("完成评选设置异常", e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"完成评选设置"));
		}
	    return rj;
	}
	
	/**
	 * @Function myManageTeam
	 * @Description 我管理的班组
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/myManageTeam")
	public ResultJSON myManageTeam(@RequireValid MyManageTeamVo param) throws BusinessException{
		Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
	    	SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
	    	if(userInfo == null){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(), String.format(m.getTip(),"我管理的班组查询","用户Code无效"));
	    	}else if(StringTools.isEmpty(userInfo.getOrgCode())){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(), String.format(m.getTip(),"我管理的班组查询","用户无对应的组织"));
	    	}
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			MyManageTeam record = new MyManageTeam();
			TeamElect teamElect = teamElectService.getNowTeamElect();
			if(teamElect != null){
				record.setTeamElectCode(teamElect.getTeamElectCode());
			}else{
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15003);
				JSONObject body = new JSONObject();
				body.put("totalCount", 0);
				body.put("dataList", new JSONArray());
				rj.setReturnCode(m.getCode());
				rj.setReturnTip(String.format(m.getTip(),"获取我管理的班组","无正在进行的评选"));
				rj.setBody(body);
				return rj;
			}
			record.setOrgCode(orgCode);
			if(StringTools.isNotEmpty(param.getTeamName())){//班组名称
				record.setTeamName(param.getTeamName());
			}
			if(StringTools.isNotEmpty(param.getTeamType())){//班组类型
				record.setTeamType(Byte.parseByte(param.getTeamType()));
			}
			if(!StringTools.isEmpty(param.getStartDate())){
	    		Long startTime = DateUtils.stringToLong(param.getStartDate(), "yyyy-MM-dd");
	    		record.setStartDate(startTime);
	    	}
	    	if(!StringTools.isEmpty(param.getEndDate())){
	    		Long endTime = DateUtils.stringToLong(param.getEndDate(), "yyyy-MM-dd");
	    		record.setEndDate(endTime + TeamConstants.DAY_TIME);
	    	}
//			if(StringTools.isNotEmpty(param.getStartDate())){//开始日期
//				record.setStartDate(Long.parseLong(param.getStartDate()));
//			}
//			if(StringTools.isNotEmpty(param.getEndDate())){//结束日期
//				record.setEndDate(Long.parseLong(param.getEndDate()) + TeamConstants.DAY_TIME);
//			}
			if(StringTools.isNotEmpty(param.getLeaderName())){//班组长
				SysUserInfo leaderInfo = new SysUserInfo();
				leaderInfo.setUserName(param.getLeaderName());
				List<SysUserInfo> userList = sysUserService.selectByRecord(leaderInfo);
				List<String> leaders = new ArrayList<String>();
				for (int i = 0; i < userList.size(); i++) {
					leaders.add(userList.get(i).getUserCode());
				}
				if(leaders.size() > 0){
					record.setLeaders(leaders);
				}else{
					m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
					rj.setReturnCode(m.getCode());
					rj.setReturnTip(String.format(m.getTip(),"班组查询"));
					JSONObject body = new JSONObject();
					body.put("totalCount", 0);
					body.put("dataList", new JSONArray());
					rj.setBody(body);
					return rj;
				}
			}
			PageBean pageBean = teamInfoService.getMyManageTeam(pageParam, record);
			JSONArray jsonArray = new JSONArray();
			List<MyManageTeam> teamList = pageBean.getRecordList();
			for (int i = 0; i < teamList.size(); i++) {
				JSONObject teamInfoJson = new JSONObject();
				teamInfoJson.put("teamCode", StringTools.formatToString(teamList.get(i).getTeamCode()));//班组code
				teamInfoJson.put("teamName", StringTools.formatToString(teamList.get(i).getTeamName()));//班组名称
				teamInfoJson.put("teamType", StringTools.formatToString(teamList.get(i).getTeamType()));//班组类型
				teamInfoJson.put("orgCode", StringTools.formatToString(teamList.get(i).getOrgCode()));//组织机构code
				SysOrgInfo org = sysOrgService.selectByCode(teamList.get(i).getOrgCode());
				if(org != null){
					teamInfoJson.put("orgName", StringTools.formatToString(org.getSysOrgFullname()));//组织机构名称
				}else{
					teamInfoJson.put("orgName", "");
				}
				teamInfoJson.put("sumCount", StringTools.formatToString(teamList.get(i).getSumCount()));//班组人数
				if(teamList.get(i).getLeaderCodes() != null){
					List<String> leaderList = Arrays.asList(teamList.get(i).getLeaderCodes().split(","));
					String leaders = "";
					for (int j = 0; j < leaderList.size(); j++) {
						SysUserInfo user = sysUserService.selectByCode(leaderList.get(j));
						if(user != null){
	    					leaders += user.getUserName() + ",";
	    				}
	    				if(j == leaderList.size() - 1 && leaders.length() > 1 ){
	    					leaders = leaders.substring(0, leaders.length()-1);
	    				}
					}
					teamInfoJson.put("leaders", StringTools.formatToString(leaders));
				}else{
					teamInfoJson.put("leaders", "");//班组长  们
				}
				if(teamList.get(i).getModifyTime() == null){
					teamInfoJson.put("modifyTime","");//修改时间,每推荐上级/设为百强  修改
				}else{
					teamInfoJson.put("modifyTime", StringTools.formatToString(teamList.get(i).getModifyTime()));
				}
				if(teamList.get(i).getIsRecommend() != null){
					teamInfoJson.put("isRecommend",TeamConstants.BOOLEAN_1);//推荐上级按键  是否可操作
				}else{
					teamInfoJson.put("isRecommend",TeamConstants.BOOLEAN_0);
				}
				jsonArray.add(teamInfoJson);
			}
			JSONObject body = new JSONObject();
			body.put("totalCount", pageBean.getTotalCount());
			body.put("dataList", jsonArray);
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
			rj.setReturnCode(m.getCode());
			rj.setReturnTip(String.format(m.getTip(),"获取我管理的班组"));
			rj.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取我管理的班组异常", e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取我管理的班组"));
		}
	    return rj;
	}

	/**
	 * @Function toSuperior
	 * @Description 推荐上级
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/toSuperior") 
	public ResultJSON toSuperior(@RequireValid ToSuperiorVo param) throws BusinessException{
		Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	TeamCourse record = new TeamCourse();
        	TeamElect teamElect = teamElectService.getNowTeamElect();
			if(teamElect != null){
				record.setTeamElectCode(teamElect.getTeamElectCode());
			}else{
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(), String.format(m.getTip(),"推荐上级","无正在进行的评选"));
			}
			SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
			record.setCurrentOrgCode(userInfo.getManageOrgInfo().getSysOrgCode());
			if(StringTools.isNotEmpty(userInfo.getManageOrgInfo().getSysOrgCode())){
				SysOrgInfo orgInfo = sysOrgService.selectManageOrgByCode(userInfo.getOrgCode());
				if(orgInfo != null){
					record.setCurrentOrgType(orgInfo.getSysOrgType());
					record.setSuperOrgCode(orgInfo.getParentCode());
				}
			}
			record.setTeamCourseCode(UUIDHelper.getUUID());
			record.setTeamCode(param.getTeamCode());
			record.setCurrentRecommendReason(param.getReason());
			record.setCreateTime(DateUtils.getFormatDateLong());
			record.setIsRecommend(TeamConstants.BOOLEAN_TRUE);
			record.setUserCode(param.getUserCode());
			boolean isSuccess = teamCourseService.addTeamCourse(record);
        	if(isSuccess){
        		//当前推荐人的行政组织级别
        		SysOrgInfo manOrg = userInfo.getManageOrgInfo();
        		String orgType = manOrg.getSysOrgType();
        		if(AuthConstants.ORG_TYPE_C.equals(orgType)){//市推荐到省给省发
        			List<SysUserInfo> userList = sysUserService.selectUserByMenuOrg(manOrg.getParentCode(), TeamConstants.MENU_ELECTMANAGE);
        			//给省级发通知
        			List<UserMsgNotice> list = new ArrayList<UserMsgNotice>();
        			TeamInfo teamInfo = teamInfoService.selectByCode(param.getTeamCode());
        			for (int i = 0; i < userList.size(); i++) {
        				UserMsgNotice notice = new UserMsgNotice();
        				notice.setNoticeCode(UUIDHelper.getUUID());
        				notice.setTitle(TeamConstants.TEAM_PUSH_UP_TITLE);
        				notice.setCreateTime(DateUtils.getFormatDateLong());
        				notice.setIsDelete((int)TeamConstants.BOOLEAN_FALSE);
        				String time = DateUtils.getDateString(DateUtils.getFormatDateLong(), "yyyy年MM月dd日 HH:mm:ss");
        				String orgName = manOrg.getSysOrgFullname();
        				String teamName = teamInfo.getTeamName();
        				notice.setContent(String.format(TeamConstants.TEAM_PUSH_UP, time,orgName,teamName));
        				notice.setUserCode(userList.get(i).getUserCode());
        				notice.setOrgCode(userList.get(i).getManOrgCode());
        				notice.setTenantCode(AuthConstants.TENANT_CODE);
        				notice.setType(MsgClassConstants.NOTICE_MSG_TYPE);
        				notice.setCreatUserCode(param.getUserCode());
        				list.add(notice);
					}
        			producerTemplate.send(MsgTopicConstants.TOPIC_NOTICE, JSON.toJSONString(list));
        		}else if(AuthConstants.ORG_TYPE_B.equals(orgType)){//省推荐到集团给集团发   再给市发
        			List<SysUserInfo> userUpList = sysUserService.selectUserByMenuOrg(manOrg.getParentCode(), TeamConstants.MENU_ELECTMANAGE);
        			//给集团发通知
        			List<UserMsgNotice> list = new ArrayList<UserMsgNotice>();
        			TeamInfo teamInfo = teamInfoService.selectByCode(param.getTeamCode());
        			for (int i = 0; i < userUpList.size(); i++) {
        				UserMsgNotice notice = new UserMsgNotice();
        				notice.setNoticeCode(UUIDHelper.getUUID());
        				notice.setTitle(TeamConstants.TEAM_PUSH_UP_TITLE);
        				notice.setCreateTime(DateUtils.getFormatDateLong());
        				notice.setIsDelete((int)TeamConstants.BOOLEAN_FALSE);
        				String time = DateUtils.getDateString(DateUtils.getFormatDateLong(), "yyyy年MM月dd日 HH:mm:ss");
        				String orgName = manOrg.getSysOrgFullname();
        				String teamName = teamInfo.getTeamName();
        				notice.setContent(String.format(TeamConstants.TEAM_PUSH_UP, time,orgName,teamName));
        				notice.setUserCode(userUpList.get(i).getUserCode());
        				notice.setOrgCode(userUpList.get(i).getManOrgCode());
        				notice.setTenantCode(AuthConstants.TENANT_CODE);
        				notice.setType(MsgClassConstants.NOTICE_MSG_TYPE);
        				notice.setCreatUserCode(param.getUserCode());
        				list.add(notice);
					}
        			producerTemplate.send(MsgTopicConstants.TOPIC_NOTICE, JSON.toJSONString(list));
        			list.clear();
        			//查出推荐组织
        			TeamCourse teamCourse = new TeamCourse();
        			teamCourse.setTeamElectCode(teamElect.getTeamElectCode());
        			teamCourse.setTeamCode(param.getTeamCode());
        			teamCourse.setSuperOrgCode(manOrg.getSysOrgCode());
        			List<TeamCourse> courseList = teamCourseService.selectTeamCourseByRecord(teamCourse);
        			if(courseList.size()>0){
        				List<SysUserInfo> userDownList = sysUserService.selectUserByMenuOrg(courseList.get(0).getCurrentOrgCode(), TeamConstants.MENU_ELECTMANAGE);
        				//如果有,给推荐市发通知
        				for (int i = 0; i < userDownList.size(); i++) {
            				UserMsgNotice notice = new UserMsgNotice();
            				notice.setNoticeCode(UUIDHelper.getUUID());
            				notice.setTitle(TeamConstants.TEAM_PUSH_DOWN_TITLE);
            				notice.setCreateTime(DateUtils.getFormatDateLong());
            				notice.setIsDelete((int)TeamConstants.BOOLEAN_FALSE);
            				String time = DateUtils.getDateString(DateUtils.getFormatDateLong(), "yyyy年MM月dd日 HH:mm:ss");
            				String orgName = manOrg.getSysOrgFullname();
            				String teamName = teamInfo.getTeamName();
            				notice.setContent(String.format(TeamConstants.TEAM_PUSH_DOWN,teamName,time,orgName));
            				notice.setUserCode(userDownList.get(i).getUserCode());
            				notice.setOrgCode(userDownList.get(i).getManOrgCode());
            				notice.setTenantCode(AuthConstants.TENANT_CODE);
            				notice.setType(MsgClassConstants.NOTICE_MSG_TYPE);
            				notice.setCreatUserCode(param.getUserCode());
            				list.add(notice);
						}
        				producerTemplate.send(MsgTopicConstants.TOPIC_NOTICE, JSON.toJSONString(list));
        			}
        		}
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
        	}else{
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
        	}
        	rj.setReturnCode(m.getCode());
        	rj.setReturnTip(String.format(m.getTip(),"推荐上级"));
        	rj.setBody(new JSONObject());
		} catch (Exception e) {
			log.error("推荐上级异常", e);
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"推荐上级"));
		}
        return rj;
	}
	
	
	/**
	 * @Function recommondTeamPro
	 * @Description 我推荐的班组/百强班组
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/recommondTeam")
	public ResultJSON recommondTeam(@RequireValid MyManageTeamVo param) throws BusinessException{
		Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
	    	SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
	    	if(userInfo == null){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(), String.format(m.getTip(),"班组查询","用户Code无效"));
	    	}else if(StringTools.isEmpty(userInfo.getOrgCode())){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(), String.format(m.getTip(),"班组查询","用户无对应的组织"));
	    	}
			TeamCourse record = new TeamCourse();
			record.setCurrentOrgCode(userInfo.getManageOrgInfo().getSysOrgCode());
			TeamElect teamElect = teamElectService.getNowTeamElect();
			String electCode = null;
			if(teamElect != null){
				electCode = teamElect.getTeamElectCode();
				record.setTeamElectCode(electCode);
			}else{
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15003);
				JSONObject body = new JSONObject();
				body.put("totalCount", 0);
				body.put("dataList", new JSONArray());
				body.put("isUnderway", TeamConstants.BOOLEAN_1);
				rj.setReturnCode(m.getCode());
				rj.setReturnTip(String.format(m.getTip(),"获取班组","无正在进行的评选"));
				rj.setBody(body);
				return rj;
			}
			if(StringTools.isNotEmpty(param.getTeamName())){//班组名称
				record.setTeamName(param.getTeamName());
			}
			if(StringTools.isNotEmpty(param.getOrgCode())){
	    		record.setTeamOrgCode(param.getOrgCode());
	    	}
			if(StringTools.isNotEmpty(param.getTeamType())){//班组类型
				record.setTeamType(Byte.parseByte(param.getTeamType()));
			}
			if(!StringTools.isEmpty(param.getStartDate())){
	    		Long startTime = DateUtils.stringToLong(param.getStartDate(), "yyyy-MM-dd");
	    		record.setStartDate(startTime);
	    	}
	    	if(!StringTools.isEmpty(param.getEndDate())){
	    		Long endTime = DateUtils.stringToLong(param.getEndDate(), "yyyy-MM-dd");
	    		record.setEndDate(endTime + TeamConstants.DAY_TIME);
	    	}
			if(StringTools.isNotEmpty(param.getLeaderName())){//班组长
				SysUserInfo leaderInfo = new SysUserInfo();
				leaderInfo.setUserName(param.getLeaderName());
				List<SysUserInfo> userList = sysUserService.selectByRecord(leaderInfo);
				List<String> leaders = new ArrayList<String>();
				for (int i = 0; i < userList.size(); i++) {
					leaders.add(userList.get(i).getUserCode());
				}
				if(leaders.size() > 0){
					record.setLeaders(leaders);
				}else{
					m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
					JSONObject body = new JSONObject();
					body.put("totalCount", 0);
					body.put("dataList", new JSONArray());
					rj.setReturnCode(m.getCode());
					rj.setReturnTip(String.format(m.getTip(),"班组查询"));
					rj.setBody(body);
					return rj;
				}
			}
			record.setIsRecommend(TeamConstants.BOOLEAN_TRUE);
			PageBean pageBean = teamInfoService.getMyRecommondList(pageParam, record);
			JSONArray jsonArray = new JSONArray();
			List<RecommondTeam> teamList = pageBean.getRecordList();
			for (int i = 0; i < teamList.size(); i++) {
				JSONObject teamInfoJson = new JSONObject();
				teamInfoJson.put("teamCode", StringTools.formatToString(teamList.get(i).getTeamCode()));//班组code
				teamInfoJson.put("teamName", StringTools.formatToString(teamList.get(i).getTeamName()));//班组名称
				teamInfoJson.put("teamType", StringTools.formatToString(teamList.get(i).getTeamType()));//班组类型
				teamInfoJson.put("orgCode", StringTools.formatToString(teamList.get(i).getOrgCode()));//组织机构code
				SysOrgInfo org = sysOrgService.selectByCode(teamList.get(i).getOrgCode());
				if(org != null){
					teamInfoJson.put("orgName", StringTools.formatToString(org.getSysOrgFullname()));//组织机构名称
				}else{
					teamInfoJson.put("orgName", "");
				}
				teamInfoJson.put("sumCount", StringTools.formatToString(teamList.get(i).getSumCount()));//人数
				if(teamList.get(i).getLeaderCodes() != null){
					List<String> leaderList = Arrays.asList(teamList.get(i).getLeaderCodes().split(","));
					String leaders = "";
					for (int j = 0; j < leaderList.size(); j++) {
						SysUserInfo user = sysUserService.selectByCode(leaderList.get(j));
						if(user != null){
	    					leaders += user.getUserName() + ",";
	    				}
	    				if(j == leaderList.size() - 1 && leaders.length() > 1 ){
	    					leaders = leaders.substring(0, leaders.length()-1);
	    				}
					}
					teamInfoJson.put("leaders", StringTools.formatToString(leaders));
				}else{
					teamInfoJson.put("leaders", "");//班组长们
				}
				teamInfoJson.put("modifyTime", StringTools.formatToString(teamList.get(i).getModifyTime()));
				teamInfoJson.put("reason",StringTools.formatToString(teamList.get(i).getCurrentRecommendReason()));//推荐原因
				jsonArray.add(teamInfoJson);
			}
			JSONObject body = new JSONObject();
			body.put("totalCount", pageBean.getTotalCount());
			body.put("dataList", jsonArray);
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
			rj.setReturnCode(m.getCode());
			rj.setReturnTip(String.format(m.getTip(),"班组查询"));
			rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取我推荐的班组异常",e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"班组查询"));
		}
	    return rj;
	}

	/**
	 * @Function receiveTeamGroup
	 * @Description 我收到的推荐
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/receiveTeam")
	public ResultJSON receiveTeam(@RequireValid ReceiveTeamVo param) throws BusinessException{
		Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
	    	SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
	    	if(userInfo == null || userInfo.getManageOrgInfo() == null){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取我收到的推荐","用户code无效"));
	    	}
	    	TeamElect teamElect = teamElectService.getNowTeamElect();
			String electCode = null;
			if(teamElect != null){
				electCode = teamElect.getTeamElectCode();
			}else{
				//没有正在进行的评选，直接返回
				JSONObject body = new JSONObject();
				body.put("totalCount", 0);
				body.put("dataList", new JSONArray());
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15003);
				rj.setReturnCode(m.getCode());
				rj.setReturnTip(String.format(m.getTip(),"获取我收到的推荐","无正在进行的评选"));
				rj.setBody(body);
				return rj;
			}
	    	TeamCourse record = new TeamCourse();
	    	record.setSuperOrgCode(userInfo.getManageOrgInfo().getSysOrgCode());
	    	record.setTeamElectCode(electCode);
	    	if(StringTools.isNotEmpty(param.getTeamName())){
	    		record.setTeamName(param.getTeamName());
	    	}
	    	if(StringTools.isNotEmpty(param.getOrgCode())){
	    		record.setTeamOrgCode(param.getOrgCode());
	    	}
	    	if(StringTools.isNotEmpty(param.getTeamName())){//班组名称
				record.setTeamName(param.getTeamName());
			}
			if(StringTools.isNotEmpty(param.getTeamType())){//班组类型
				record.setTeamType(Byte.parseByte(param.getTeamType()));
			}
			if(!StringTools.isEmpty(param.getStartDate())){
	    		Long startTime = DateUtils.stringToLong(param.getStartDate(), "yyyy-MM-dd");
	    		record.setStartDate(startTime);
	    	}
	    	if(!StringTools.isEmpty(param.getEndDate())){
	    		Long endTime = DateUtils.stringToLong(param.getEndDate(), "yyyy-MM-dd");
	    		record.setEndDate(endTime + TeamConstants.DAY_TIME);
	    	}
//			if(StringTools.isNotEmpty(param.getStartDate())){//开始日期
//				record.setStartDate(Long.parseLong(param.getStartDate()));
//			}
//			if(StringTools.isNotEmpty(param.getEndDate())){//结束日期
//				record.setEndDate(Long.parseLong(param.getEndDate()) + TeamConstants.DAY_TIME);
//			}
			if(StringTools.isNotEmpty(param.getLeaderName())){//班组长
				SysUserInfo leaderInfo = new SysUserInfo();
				leaderInfo.setUserName(param.getLeaderName());
				List<SysUserInfo> userList = sysUserService.selectByRecord(leaderInfo);
				List<String> leaders = new ArrayList<String>();
				for (int i = 0; i < userList.size(); i++) {
					leaders.add(userList.get(i).getUserCode());
				}
				if(leaders.size() > 0){
					record.setLeaders(leaders);
				}else{
					m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
					rj.setReturnCode(m.getCode());
					rj.setReturnTip(String.format(m.getTip(),"班组查询"));
					JSONObject body = new JSONObject();
					body.put("totalCount", 0);
					body.put("dataList", new JSONArray());
					rj.setBody(body);
					return rj;
				}
			}
			PageBean pageBean = teamInfoService.getMyRecommondList(pageParam, record);
			JSONArray jsonArray = new JSONArray();
			List<RecommondTeam> teamList = pageBean.getRecordList();
			for (int i = 0; i < teamList.size(); i++) {
				JSONObject teamInfoJson = new JSONObject();
				teamInfoJson.put("teamCode", StringTools.formatToString(teamList.get(i).getTeamCode()));
				teamInfoJson.put("teamName", StringTools.formatToString(teamList.get(i).getTeamName()));
				teamInfoJson.put("teamType", StringTools.formatToString(teamList.get(i).getTeamType()));
				teamInfoJson.put("orgCode", StringTools.formatToString(teamList.get(i).getOrgCode()));
				SysOrgInfo org = sysOrgService.selectByCode(teamList.get(i).getOrgCode());
				if(org != null){
					teamInfoJson.put("orgName", StringTools.formatToString(org.getSysOrgFullname()));//组织机构名称
				}else{
					teamInfoJson.put("orgName", "");
				}
				teamInfoJson.put("sumCount", StringTools.formatToString(teamList.get(i).getSumCount()));
				if(teamList.get(i).getLeaderCodes() != null){
					List<String> leaderList = Arrays.asList(teamList.get(i).getLeaderCodes().split(","));
					String leaders = "";
					for (int j = 0; j < leaderList.size(); j++) {
						SysUserInfo user = sysUserService.selectByCode(leaderList.get(j));
						if(user != null){
	    					leaders += user.getUserName() + ",";
	    				}
	    				if(j == leaderList.size() - 1 && leaders.length() > 1 ){
	    					leaders = leaders.substring(0, leaders.length()-1);
	    				}
					}
					teamInfoJson.put("leaders", StringTools.formatToString(leaders));
				}else{
					teamInfoJson.put("leaders", "");
				}
				teamInfoJson.put("modifyTime", StringTools.formatToString(teamList.get(i).getModifyTime()));
				if(teamList.get(i).getIsRecommend() !=  null){
					teamInfoJson.put("isRecommend",TeamConstants.BOOLEAN_1);
				}else{
					teamInfoJson.put("isRecommend",TeamConstants.BOOLEAN_0);//操作按键  是否可用
				}
				teamInfoJson.put("reason",StringTools.formatToString(teamList.get(i).getCurrentRecommendReason()));
				jsonArray.add(teamInfoJson);
			}
			JSONObject body = new JSONObject();
			body.put("totalCount", pageBean.getTotalCount());
			body.put("dataList", jsonArray);
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
			rj.setReturnCode(m.getCode());
			rj.setReturnTip(String.format(m.getTip(),"获取我收到的推荐"));
			rj.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取我收到的推荐异常", e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取我收到的推荐"));
		}
	    return rj;
	}

	/**
	 * @Function toSuperior
	 * @Description 评为百强班组
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/addHonorTeam") 
	public ResultJSON addHonorTeam(@RequireValid HonorTeamAddVo param) throws BusinessException{
		Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	TeamElect teamElect = teamElectService.getNowTeamElect();
			String electCode = null;
			if(teamElect != null){
				electCode = teamElect.getTeamElectCode();
			}else{
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(), String.format(m.getTip(),"评为百强班组","无正在进行的评选"));
			}
			SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
	    	if(userInfo == null){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(), String.format(m.getTip(),"评为百强班组","用户Code无效"));
	    	}else if(StringTools.isEmpty(userInfo.getOrgCode())){
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(), String.format(m.getTip(),"评为百强班组","用户无对应的组织"));
	    	}
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
        	boolean isSuccess = teamCourseService.addHonorTeam(param.getTeamCode(),electCode,param.getUserCode(),orgCode);
        	if(isSuccess){
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
        	}else{
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
        	}
        	rj.setReturnCode(m.getCode());
        	rj.setReturnTip(String.format(m.getTip(),"评为百强班组"));
        	rj.setBody(new JSONObject());
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("评为百强班组异常", e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"评为百强班组"));
		}
        return rj;
	}
	
	/**
	 * @Function delHonorTeam
	 * @Description 移除百强班组
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/delHonorTeam") 
	public ResultJSON delHonorTeam(@RequireValid HonorTeamDelVo param) throws BusinessException{
		Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	TeamElect teamElect = teamElectService.getNowTeamElect();
			String electCode = null;
			if(teamElect != null){
				electCode = teamElect.getTeamElectCode();
			}else{
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(), String.format(m.getTip(),"移除百强班组","无正在进行的评选"));
			}
        	boolean isSuccess = teamCourseService.delHonorTeam(param.getTeamCode(), electCode);
        	if(isSuccess){
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
        	}else{
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
        	}
        	rj.setReturnCode(m.getCode());
        	rj.setReturnTip(String.format(m.getTip(),"移除百强班组"));
        	rj.setBody(new JSONObject());
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("移除百强班组异常", e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"移除百强班组"));
		}
        return rj;
	}
	
	/**
	 * @Function honorTeamHis
	 * @Description 获取历史百强班组
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/honorTeamHis")
    public ResultJSON honorTeamHis(@RequireValid HonorTeamHisVo param) throws BusinessException{
		Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
        	TeamCourse record = new TeamCourse();
        	if(StringTools.isNotEmpty(param.getTeamElectCode())){
        		record.setTeamElectCode(param.getTeamElectCode());
        	}
        	record.setCurrentOrgType(AuthConstants.ORG_TYPE_A);
        	record.setIsRecommend(TeamConstants.BOOLEAN_TRUE);
        	if(StringTools.isNotEmpty(param.getTeamName())){//班组名称
				record.setTeamName(param.getTeamName());
			}
			if(StringTools.isNotEmpty(param.getTeamType())){//班组类型
				record.setTeamType(Byte.parseByte(param.getTeamType()));
			}
			if(!StringTools.isEmpty(param.getStartDate())){
	    		Long startTime = DateUtils.stringToLong(param.getStartDate(), "yyyy-MM-dd");
	    		record.setStartDate(startTime);
	    	}
	    	if(!StringTools.isEmpty(param.getEndDate())){
	    		Long endTime = DateUtils.stringToLong(param.getEndDate(), "yyyy-MM-dd");
	    		record.setEndDate(endTime + TeamConstants.DAY_TIME);
	    	}
//			if(StringTools.isNotEmpty(param.getStartDate())){//开始日期
//				record.setStartDate(Long.parseLong(param.getStartDate()));
//			}
//			if(StringTools.isNotEmpty(param.getEndDate())){//结束日期
//				record.setEndDate(Long.parseLong(param.getEndDate()) + TeamConstants.DAY_TIME);
//			}
			if(StringTools.isNotEmpty(param.getLeaderName())){//班组长
				SysUserInfo leaderInfo = new SysUserInfo();
				leaderInfo.setUserName(param.getLeaderName());
				List<SysUserInfo> userList = sysUserService.selectByRecord(leaderInfo);
				List<String> leaders = new ArrayList<String>();
				for (int i = 0; i < userList.size(); i++) {
					leaders.add(userList.get(i).getUserCode());
				}
				if(leaders.size() > 0){
					record.setLeaders(leaders);
				}else{
					m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
					rj.setReturnCode(m.getCode());
					rj.setReturnTip(String.format(m.getTip(),"班组查询"));
					JSONObject body = new JSONObject();
					body.put("totalCount", 0);
					body.put("dataList", new JSONArray());
					rj.setBody(body);
					return rj;
				}
			}
        	PageBean pageBean = teamCourseService.selectHonorTeamHis(pageParam,record);
        	JSONArray jsonArray = new JSONArray();
			List<HonorTeam> teamList = pageBean.getRecordList();
        	for (int i = 0; i < teamList.size(); i++) {
        		JSONObject teamInfoJson = new JSONObject();
        		teamInfoJson.put("teamCode", StringTools.formatToString(teamList.get(i).getTeamCode()));
        		teamInfoJson.put("teamName", StringTools.formatToString(teamList.get(i).getTeamName()));
        		teamInfoJson.put("teamType", StringTools.formatToString(teamList.get(i).getTeamType()));
        		teamInfoJson.put("orgCode", StringTools.formatToString(teamList.get(i).getOrgCode()));
        		SysOrgInfo org = sysOrgService.selectByCode(teamList.get(i).getOrgCode());
        		if(org != null){
					teamInfoJson.put("orgName", StringTools.formatToString(org.getSysOrgFullname()));//组织机构名称
				}else{
					teamInfoJson.put("orgName", "");
				}
        		teamInfoJson.put("sumCount", StringTools.formatToString(teamList.get(i).getSumCount()));
        		if(teamList.get(i).getLeaderCodes() != null){
        			List<String> leaderList = Arrays.asList(teamList.get(i).getLeaderCodes().split(","));
        			String leaders = "";
        			for (int j = 0; j < leaderList.size(); j++) {
        				SysUserInfo user = sysUserService.selectByCode(leaderList.get(j));
        				if(user != null){
	    					leaders += user.getUserName() + ",";
	    				}
	    				if(j == leaderList.size() - 1 && leaders.length() > 1 ){
	    					leaders = leaders.substring(0, leaders.length()-1);
	    				}
        			}
        			teamInfoJson.put("leaders", StringTools.formatToString(leaders));
        		}else{
        			teamInfoJson.put("leaders", "");
        		}
        		teamInfoJson.put("modifyTime", StringTools.formatToString(teamList.get(i).getModifyTime()));
        		teamInfoJson.put("reason", StringTools.formatToString(teamList.get(i).getCurrentRecommendReason()));
        		jsonArray.add(teamInfoJson);
			}
        	JSONObject body = new JSONObject();
        	body.put("totalCount", pageBean.getTotalCount());
        	body.put("dataList", jsonArray);
        	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"查询历史百强班组"));
            rj.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("查询历史百强班组异常", e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"查询历史百强班组"));
		}
        return rj;
	}
	
	/**
	 * @Function courseList
	 * @Description 评选历程
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/course/list")
    public ResultJSON courseList(@RequireValid TeamCourseVo param) throws BusinessException{
		Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	JSONObject body = new JSONObject();
        	TeamCourse record = new TeamCourse();
        	record.setTeamCode(param.getTeamCode());
        	if(StringTools.isNotEmpty(param.getElectCode())){
        		record.setTeamElectCode(param.getElectCode());
        	}else{
        		TeamElect teamElect = teamElectService.getNowTeamElect();
        		if(teamElect != null){
        			record.setTeamElectCode(teamElect.getTeamElectCode());
        		}else{
        			body.put("dataList", new JSONArray());
        			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
        			rj.setReturnCode(m.getCode());
        			rj.setReturnTip(String.format(m.getTip(),"查询班组历程"));
        			rj.setBody(body);
        			return rj;
        		}
        	}
        	List<TeamCourse> teamList = teamCourseService.selectTeamCourseByRecord(record);
        	JSONArray jsonArray = new JSONArray();
        	for (int i = 0; i < teamList.size(); i++) {
        		JSONObject teamInfoJson = new JSONObject();
        		teamInfoJson.put("createTime", StringTools.formatToString(teamList.get(i).getCreateTime()));
        		SysUserInfo userInfo = sysUserService.selectByCode(teamList.get(i).getUserCode());
        		if(userInfo != null){
        			teamInfoJson.put("userName", StringTools.formatToString(userInfo.getUserName()));
        		}else{
        			teamInfoJson.put("userName", "");
        		}
        		teamInfoJson.put("type", StringTools.formatToString(teamList.get(i).getCurrentOrgType()));
        		jsonArray.add(teamInfoJson);
			}
        	body.put("dataList", jsonArray);
        	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"查询班组历程"));
            rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("查询班组历程异常", e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"查询班组历程"));
		}
        return rj;
	}

	
	/**
	 * @Function electList
	 * @Description 评选列表
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/elect/list")
    public ResultJSON electList(@RequireValid ElectListVo param) throws BusinessException{
		Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
        	TeamElect record = new TeamElect();
        	if(StringTools.isNotEmpty(param.getElectCode())){
        		record.setTeamElectCode(param.getElectCode());
        	}
        	if(StringTools.isNotEmpty(param.getTitle())){
        		record.setElectTitle(param.getTitle());
        	}
        	if(StringTools.isNotEmpty(param.getPublishUser())){
        		SysUserInfo userInfo = new SysUserInfo();
        		userInfo.setUserName(param.getPublishUser());
        		List<SysUserInfo> userList = sysUserService.selectByRecord(userInfo);
        		List<String> userCodes = new ArrayList<String>();
        		for (int i = 0; i < userList.size(); i++) {
        			userCodes.add(userList.get(i).getUserCode());
				}
        		if(userCodes.size() > 0){
        			record.setUserCodes(userCodes);
        		}else{
        			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
                    rj.setReturnCode(m.getCode());
                    rj.setReturnTip(String.format(m.getTip(),"查询班组评选"));
                    JSONObject body = new JSONObject();
					body.put("totalCount", 0);
					body.put("dataList", new JSONArray());
					rj.setBody(body);
                    return rj;
        		}
        	}
//        	if(StringTools.isNotEmpty(param.getStartDate())){
//        		record.setElectStartTime(Long.parseLong(param.getStartDate()));
//        	}
//        	if(StringTools.isNotEmpty(param.getEndDate())){
//        		record.setElectEndTime(Long.parseLong(param.getEndDate()) + TeamConstants.DAY_TIME);
//        	}
        	if(!StringTools.isEmpty(param.getStartDate())){
	    		Long startTime = DateUtils.stringToLong(param.getStartDate(), "yyyy-MM-dd");
	    		record.setElectStartTime(startTime);
	    	}
	    	if(!StringTools.isEmpty(param.getEndDate())){
	    		Long endTime = DateUtils.stringToLong(param.getEndDate(), "yyyy-MM-dd");
	    		record.setElectEndTime(endTime + TeamConstants.DAY_TIME);
	    	}
        	PageBean pageBean = teamElectService.selectTeamElectList(record,pageParam);
        	List<TeamElect> electList = pageBean.getRecordList();
        	JSONArray jsonArray = new JSONArray();
        	for (int i = 0; i < electList.size(); i++) {
        		JSONObject electJson = new JSONObject();
        		electJson.put("electCode", StringTools.formatToString(electList.get(i).getTeamElectCode()));
        		electJson.put("electTitle", StringTools.formatToString(electList.get(i).getElectTitle()));
        		electJson.put("electDetail", StringTools.formatToString(electList.get(i).getElectDetail()));
        		electJson.put("electDate", DateUtils.longToString(electList.get(i).getElectStartTime(), "yyyy-MM-dd"));
        		SysUserInfo userInfo = sysUserService.selectByCode(electList.get(i).getUserCode());
        		if(userInfo != null){
        			electJson.put("userName", StringTools.formatToString(userInfo.getUserName()));
        		}else{
        			electJson.put("userName","");
        		}
        		jsonArray.add(electJson);
			}
        	JSONObject body = new JSONObject();
        	body.put("dataList", jsonArray);
        	body.put("totalCount", pageBean.getTotalCount());
        	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"查询班组评选"));
            rj.setBody(body);
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("查询班组评选异常", e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"查询班组评选"));
		}
        return rj;
	}
	
	
	/**
	 * @Function awardHonor
	 * @Description 获取百强班组(颁发荣誉)
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/awardTeam/list")
	public ResultJSON awardTeam(@RequireValid HonorTeamNew param) throws BusinessException{
		Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
			TeamElect teamElect = teamElectService.getLastTeamElect();
			String electCode = null;
			if(teamElect != null){
				electCode = teamElect.getTeamElectCode();
			}else{
				JSONObject body = new JSONObject();
				body.put("dataList", new JSONArray());
				m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				rj.setReturnCode(m.getCode());
				rj.setReturnTip(String.format(m.getTip(),"获取百强班组(颁发荣誉)","当前评选未完成"));
				rj.setBody(body);
				return rj;
			}
			PageBean pageBean = teamInfoService.selectHonorTeam(pageParam, electCode);
			JSONArray jsonArray = new JSONArray();
			List<HonorTeam> teamList = pageBean.getRecordList();
			for (int i = 0; i < teamList.size(); i++) {
				JSONObject teamInfoJson = new JSONObject();
				teamInfoJson.put("teamCode", StringTools.formatToString(teamList.get(i).getTeamCode()));
				teamInfoJson.put("teamName", StringTools.formatToString(teamList.get(i).getTeamName()));
				teamInfoJson.put("teamType", StringTools.formatToString(teamList.get(i).getTeamType()));
				teamInfoJson.put("orgCode", StringTools.formatToString(teamList.get(i).getOrgCode()));
				SysOrgInfo org = sysOrgService.selectByCode(teamList.get(i).getOrgCode());
				if(org != null){
					teamInfoJson.put("orgName", StringTools.formatToString(org.getSysOrgFullname()));
				}else{
					teamInfoJson.put("orgName", "");
				}
				teamInfoJson.put("sumCount", StringTools.formatToString(teamList.get(i).getSumCount()));
				if(teamList.get(i).getLeaderCodes() != null){
					List<String> leaderList = Arrays.asList(teamList.get(i).getLeaderCodes().split(","));
					String leaders = "";
					for (int j = 0; j < leaderList.size(); j++) {
						SysUserInfo user = sysUserService.selectByCode(leaderList.get(j));
						if(user != null){
	    					leaders += user.getUserName() + ",";
	    				}
	    				if(j == leaderList.size() - 1 && leaders.length() > 1 ){
	    					leaders = leaders.substring(0, leaders.length()-1);
	    				}
					}
					teamInfoJson.put("leaders", StringTools.formatToString(leaders));
				}else{
					teamInfoJson.put("leaders", "");
				}
				teamInfoJson.put("isHonor", StringTools.formatToString(teamList.get(i).getIsHonor()));
				teamInfoJson.put("modifyTime", StringTools.formatToString(teamList.get(i).getElectTime()));
				jsonArray.add(teamInfoJson);
			}
			JSONObject body = new JSONObject();
			body.put("totalCount", pageBean.getTotalCount());
			body.put("dataList", jsonArray);
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
			rj.setReturnCode(m.getCode());
			rj.setReturnTip(String.format(m.getTip(),"获取百强班组(颁发荣誉)"));
			rj.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("获取百强班组(颁发荣誉)异常", e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"获取百强班组(颁发荣誉)"));
		}
	    return rj;
	}
	
	
}
