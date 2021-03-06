package com.zssq.team.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.AuthConstants;
import com.zssq.constants.TeamConstants;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamMember;
import com.zssq.dao.pojo.TeamMemberVo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IForumManageService;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamMemberService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.team.vo.TeamMemberAddVo;
import com.zssq.team.vo.TeamMemberAllAddVo;
import com.zssq.team.vo.TeamMemberDelVo;
import com.zssq.team.vo.TeamMemberLeaderVo;
import com.zssq.team.vo.TeamMemberSearchVo;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

/**
 * @ClassName TeamMemberController
 * @Description 班组成员
 * @author JiPengChun
 * @date 2017年3月17日 上午10:14:02
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/teamMember")
public class TeamMemberController {

	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 班组成员service
	 */
	@Autowired
	private ITeamMemberService teamMemberService;
	
	/**
	 * 用户service
	 */
	@Autowired
	private ISysUserService sysUserService;
	
	/**
	 * 组织机构service
	 */
	@Autowired
	private ISysOrgService sysOrgService;
	
	/**
	 * 论坛service
	 */
	@Autowired
	private IForumManageService forumManageService;
	
	/**
	 * @Function addTeamMember
	 * @Description 新增班组成员
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/add")
	public ResultJSON addTeamMember(@RequireValid TeamMemberAddVo param) throws BusinessException{  
	    
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	List<String> userCodes = Arrays.asList(param.getUserCodes().split(","));
	    	List<TeamMember> memList = new ArrayList<TeamMember>(userCodes.size());
	    	for (int i = 0; i < userCodes.size(); i++) {
	    		TeamMember record = new TeamMember();
	    		record.setTeamMemberCode(UUIDHelper.getUUID());
	    		record.setTeamCode(param.getTeamCode());
	    		record.setUserCode(userCodes.get(i));
	    		record.setIsLeader(Byte.parseByte(param.getIsLeader()));
	    		record.setCreateTime(DateUtils.getFormatDateLong());
	    		memList.add(record);
			}
	    	boolean isSuccess = teamMemberService.batchAdd(memList);
	    	JSONObject body = new JSONObject();
	    	if(isSuccess){
	    		//调用论坛服务
	    		SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
	    		for (int i = 0; i < memList.size(); i++) {
	    			SysUserInfo meminfo = sysUserService.selectByCode(memList.get(i).getUserCode());
	    			forumManageService.addOneForumMember(AuthConstants.TENANT_CODE, userInfo.getManageOrgInfo().getSysOrgCode(),param.getTeamCode(), memList.get(i).getUserCode(), meminfo.getUserName(), memList.get(i).getIsLeader());
				}
	    		log.info("TeamMemberController.addTeamMember:新增班组成员成功");
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
	    		rj.setReturnCode(m.getCode());
	    		if((TeamConstants.IS_LEADER_0).equals(param.getIsLeader())){
	    			rj.setReturnTip(String.format(m.getTip(),"新增班组组员"));
	    		}else{
	    			rj.setReturnTip(String.format(m.getTip(),"新增班组组长"));
	    		}
	    		rj.setBody(new JSONObject());
	    	}else{
	    		log.info("TeamMemberController.addTeamMember:新增班组成员失败");
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
	    		rj.setReturnCode(m.getCode());
	    		if((TeamConstants.IS_LEADER_0).equals(param.getIsLeader())){
	    			rj.setReturnTip(String.format(m.getTip(),"新增班组组员"));
	    		}else{
	    			rj.setReturnTip(String.format(m.getTip(),"新增班组组长"));
	    		}
	    		rj.setBody(body);
	    	}
		} catch (Exception e) {
			log.error(e);
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			if((TeamConstants.IS_LEADER_0).equals(param.getIsLeader())){
				throw new BusinessException(m.getCode(),String.format(m.getTip(),"新增班组组员"));
			}else{
				throw new BusinessException(m.getCode(),String.format(m.getTip(),"新增班组组长"));
			}
		}
	    return rj;
	}
	
	
	/**
	 * @Function addTeamMemberAll
	 * @Description 加班组成员(组长和组员)
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/addAll")
	public ResultJSON addTeamMemberAll(@RequireValid TeamMemberAllAddVo param) throws BusinessException{  
	    
	    Message m = null;
	    ResultJSON rj = new ResultJSON();
	    try {
	    	List<TeamMemberVo> teamMembers = JSONArray.parseArray(param.getTeamMember(), TeamMemberVo.class);
	    	Set<String> userSet = new HashSet<String>();
	    	for (int i = 0; i < teamMembers.size(); i++) {
	    		userSet.add(teamMembers.get(i).getUserCode());
			}
	    	if(userSet.size() != teamMembers.size()){
	    		log.info("TeamMemberController.addTeamMember:新增班组成员成功,存在重复用户");
	    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
	    		rj.setReturnCode(m.getCode());
	    		rj.setReturnTip(String.format(m.getTip(),"新增班组成员","存在重复用户"));
	    		rj.setBody(new JSONObject());
	    		return rj;
	    	}
	    	teamMembers = teamMemberService.batchAddAll(teamMembers,param.getTeamCode());
    		//调用论坛服务
    		SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
    		for (int i = 0; i < teamMembers.size(); i++) {
    			try {
    				SysUserInfo meminfo = sysUserService.selectByCode(teamMembers.get(i).getUserCode());
    				forumManageService.addOneForumMember(AuthConstants.TENANT_CODE, userInfo.getManageOrgInfo().getSysOrgCode(),param.getTeamCode(), teamMembers.get(i).getUserCode(),meminfo.getUserName(), teamMembers.get(i).getIsLeader());
    			} catch (Exception e) {
    				log.error("新增班组成员调用论坛服务异常捕获...",e);
    			}
			}
    		log.info("TeamMemberController.addTeamMember:新增班组成员成功");
    		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
    		rj.setReturnCode(m.getCode());
    		rj.setReturnTip(String.format(m.getTip(),"新增班组成员"));
    		rj.setBody(new JSONObject());
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"新增班组成员"));
		}
	    return rj;
	}

	/**
	 * @Function getTeamMemberList
	 * @Description 获取班组成员列表
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/list")
    public ResultJSON getTeamMemberList(@RequireValid TeamMemberSearchVo param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	PageParam pageParam = null;
        	TeamMember record = new TeamMember();
        	record.setTeamCode(param.getTeamCode());
        	PageBean pageBean = teamMemberService.getTeamMemList(pageParam, record);
        	JSONArray jsonArray = new JSONArray();
			List<TeamMember> teamMemList = pageBean.getRecordList();
        	for (int i = 0; i < teamMemList.size(); i++) {
        		JSONObject teamMemJson = new JSONObject();
        		teamMemJson.put("teamMemberCode", StringTools.formatToString(teamMemList.get(i).getTeamMemberCode()));//唯一标识
        		teamMemJson.put("userCode", StringTools.formatToString(teamMemList.get(i).getUserCode()));//用户code
        		SysUserInfo userInfo = sysUserService.selectByCode(teamMemList.get(i).getUserCode());
        		if(userInfo != null){
        			teamMemJson.put("userName", StringTools.formatToString(userInfo.getUserName()));//用户名
        			teamMemJson.put("phone", StringTools.formatToString(userInfo.getUserOfficePhone()));//用户名
        			SysOrgInfo orgInfo = sysOrgService.selectByCode(userInfo.getOrgCode());
        			if(orgInfo != null){
        				teamMemJson.put("orgName", StringTools.formatToString(orgInfo.getSysOrgFullname()));//组织机构名称
        			}else{
        				teamMemJson.put("orgName", "");
        			}
        		}else{
        			teamMemJson.put("userName", "");//用户名
        			teamMemJson.put("phone", "");//用户名
    				teamMemJson.put("orgName", "");
        		}
        		teamMemJson.put("isLeader", StringTools.formatToString(teamMemList.get(i).getIsLeader()));//是否是班组长
        		teamMemJson.put("createTime", StringTools.formatToString(teamMemList.get(i).getCreateTime()));//加入时间
        		jsonArray.add(teamMemJson);
			}
        	JSONObject body = new JSONObject();
        	body.put("totalCount", pageBean.getTotalCount());
        	body.put("dataList", jsonArray);
        	m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(String.format(m.getTip(),"查询班组成员"));
            rj.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			log.error("查询班组成员异常", e);
			throw new BusinessException(m.getCode(), String.format(m.getTip(),"查询班组成员"));
		}
        return rj;
	}
	
	/**
	 * @Function setIsLeader
	 * @Description 设置组长/组员
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/setIsLeader") 
	public ResultJSON setIsLeader(@RequireValid TeamMemberLeaderVo param) throws BusinessException{
		Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	TeamMember record = new TeamMember();
        	List<String> teamMemberCodes = Arrays.asList(param.getTeamMemberCodes().split(","));
        	record.setTeamMemberCodes(teamMemberCodes);
        	record.setIsLeader(Byte.parseByte(param.getIsLeader()));
        	boolean isSuccess = teamMemberService.updateByRecord(record);
        	if(isSuccess){
        		List<TeamMember> teamMembers = teamMemberService.selectByCodes(teamMemberCodes);
        		SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
        		//调用论坛服务
        		for (int i = 0; i < teamMembers.size(); i++) {
        			forumManageService.updateModerator(AuthConstants.TENANT_CODE, userInfo.getManageOrgInfo().getSysOrgCode(), teamMembers.get(i).getTeamCode(), teamMembers.get(i).getUserCode(), Byte.parseByte(param.getIsLeader()));
				}
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
        		rj.setReturnCode(m.getCode());
        		if((TeamConstants.IS_LEADER_0).equals(param.getIsLeader())){
        			rj.setReturnTip(String.format(m.getTip(),"设置组员"));
        		}else{
        			rj.setReturnTip(String.format(m.getTip(),"设置组长"));
        		}
        		rj.setBody(new JSONObject());
        	}else{
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
        		rj.setReturnCode(m.getCode());
        		if((TeamConstants.IS_LEADER_0).equals(param.getIsLeader())){
        			rj.setReturnTip(String.format(m.getTip(),"设置组员"));
        		}else{
        			rj.setReturnTip(String.format(m.getTip(),"设置组长"));
        		}
        		rj.setBody(new JSONObject());
        	}
		} catch (Exception e) {
			log.error("设置组员/组长异常", e);
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			if((TeamConstants.IS_LEADER_0).equals(param.getIsLeader())){
    			throw new BusinessException(m.getCode(), String.format(m.getTip(),"设置组员"));
    		}else{
    			throw new BusinessException(m.getCode(), String.format(m.getTip(),"设置组长"));
    		}
		}
        return rj;
	}
	
	/**
	 * @Function delTeamMember
	 * @Description 移除班组成员
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping("/del") 
	public ResultJSON delTeamMember(@RequireValid TeamMemberDelVo param) throws BusinessException{
		Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	TeamMember teamMember = teamMemberService.selectByCode(param.getTeamMemberCode());
        	boolean isSuccess = teamMemberService.delTeamMember(param.getTeamMemberCode());
        	if(isSuccess){
        		SysUserInfo userInfo = sysUserService.selectByCode(param.getUserCode());
        		try {
        			//调用论坛服务
        			forumManageService.deleteOneForumMember(AuthConstants.TENANT_CODE, userInfo.getManageOrgInfo().getSysOrgCode(), teamMember.getTeamCode(), teamMember.getUserCode());
				} catch (Exception e) {
					log.error("移除班组成员调用论坛服务异常捕获", e);
				}
        		log.info("TeamMemberController.delTeamMember:移除班组成员成功");
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_200);
        	}else{
        		log.info("TeamMemberController.delTeamMember:移除班组成员失败");
        		m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
        	}
        	rj.setReturnCode(m.getCode());
        	rj.setReturnTip(String.format(m.getTip(),"移除班组成员"));
        	rj.setBody(new JSONObject());
        	
		} catch (Exception e) {
			log.error("移除班组成员异常", e);
			m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15002);
			throw new BusinessException(m.getCode(),String.format(m.getTip(),"移除班组成员"));
		}
        return rj;
	}
	
	
}
