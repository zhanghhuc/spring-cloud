package com.zssq.relation.controller;

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
import com.zssq.constants.RelationConstants;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.RelationPortalHotModel;
import com.zssq.model.RelationTeamHideHotModel;
import com.zssq.model.ResourceModel;
import com.zssq.pojo.ResultJSON;
import com.zssq.relation.vo.GetPortalHotListVO;
import com.zssq.relation.vo.GetTeamShowHotListVO;
import com.zssq.relation.vo.UpdateTeamHotStatusVO;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.ITeamMemberService;
import com.zssq.service.RelationHotService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.RelationHotVO;

/**
 * @ClassName RelationHotH5Controller
 * @Description H5热点
 * @author LXW
 * @date 2017年5月25日 下午3:08:07
 * @version 1.0
 * @since JDK 1.7
 */
@Controller  
@RequestMapping("/relation")
public class RelationHotH5Controller {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RelationHotService relationHotService;
	@Autowired
	private ITeamInfoService teamInfoService;
	@Autowired
	private ITeamMemberService teamMemberService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysOrgService sysOrgService;
	
	
	/**
	 * 
	 * @Title: getTeamShowHotList  
	 * @Description: 查询班组展示中热点列表
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getTeamShowHotList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getTeamShowHotList(@RequireValid GetTeamShowHotListVO param) throws BusinessException {
		//返回值
		ResultJSON resultJSON = null;
		
		try {
			//方法进入
			log.info("RelationHotH5Controller.getTeamShowHotList:查询班组展示中热点列表");
			
			//获取参数
			String teamCode = StringTools.formatToString(param.getTeamCode());	//班组编号
			String pageSize = param.getPageSize();	//每页条数
			String pageNo = param.getPageNo();		//页码,从0开始
			String queryTime = param.getQueryTime();
			String userCode = StringTools.formatToString(param.getUserCode());
			//未使用数据
			String teamName = "";	//班组名称
			String teamOrgCode = "";	//班组组织编号
			String teamOrgName = "";	//班组组织名称
			TeamInfo teamInfo = teamInfoService.selectByCode(teamCode);
			if(teamInfo!=null){
				teamName = teamInfo.getTeamName();
				SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(teamInfo.getOrgCode());
				if(sysOrgInfo!=null){
					teamOrgCode = sysOrgInfo.getSysOrgCode();
					teamOrgName = sysOrgInfo.getSysOrgFullname();
				}
			}
			List<String> teamUserCodes = teamMemberService.selectUserCodeByTeamCode(teamCode);
			//封装数据
			RelationHotVO relationHotVO = new RelationHotVO();
			relationHotVO.setTeamCode(StringTools.formatToString(teamCode));
			relationHotVO.setHotStatus(RelationConstants.RELATION_HOT_STATUS_SHOW);	//service层用于判断展示的是显示热点还是隐藏热点
			relationHotVO.setQueryTime(StringTools.safeToLong(queryTime));
			relationHotVO.setUserCodeList(teamUserCodes);
			relationHotVO.setUserCode(userCode);
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			PageBean pageBean = relationHotService.getTeamHotList(pageParam, relationHotVO);
			if (pageBean == null) {
				log.error("RelationHotH5Controller.getTeamShowHotList：查询班组展示中热点列表");
				throw BusinessException.build("RELATION_19002", "查询班组展示中热点列表");
			}
			List<RelationTeamHideHotModel> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			for (RelationTeamHideHotModel model : recordList) {
				JSONObject jo = new JSONObject();
				jo.put("hotCode", StringTools.formatToString(model.getHotCode()));
				jo.put("subjectCode", StringTools.formatToString(model.getSubjectCode()));
				jo.put("subjectClass", StringTools.formatToString(model.getSubjectClass()));
				jo.put("userCode", StringTools.formatToString(model.getUserCode()));
				String userName ="";
				String userPhotoUrl = "";
				String orgCode = "";
				String orgName = "";
					
				if(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(model.getSubjectDepend())){
					orgCode = teamOrgCode;
					orgName = teamOrgName;
					//班组
//					TeamInfo tempTeamInfo = teamInfoService.selectByCode(StringTools.formatToString(model.getTeamCode()));
//					if(tempTeamInfo!=null){
//						SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(tempTeamInfo.getOrgCode());
//						if(sysOrgInfo!=null){
//							orgCode = tempTeamInfo.getOrgCode();
//							orgName = sysOrgInfo.getSysOrgFullname();
//						}
//					}
				}else{
					SysUserInfo tempUser = sysUserService.selectByCode(StringTools.formatToString(model.getUserCode()));
					if(tempUser!=null){
						userName = tempUser.getUserName();
						userPhotoUrl = tempUser.getHeadPortrait();
						orgCode = tempUser.getManageOrgInfo().getSysOrgCode();
						orgName = tempUser.getManageOrgInfo().getSysOrgFullname();
					}
					
				}
				jo.put("userName", StringTools.formatToString(userName));
				jo.put("userPhotoUrl", StringTools.formatToString(userPhotoUrl));
				jo.put("subjectDepend", StringTools.formatToString(model.getSubjectDepend()));
				jo.put("orgCode", StringTools.formatToString(orgCode));
				jo.put("orgName", StringTools.formatToString(orgName));
				jo.put("subjectPublishTime", StringTools.formatToString(model.getSubjectPublishTime()));
				jo.put("subjectTitle", StringTools.formatToString(model.getSubjectTitle()));
				jo.put("subjectDigest", StringTools.formatToString(model.getSubjectDigest()));
				jo.put("subjectUrl", StringTools.formatToString(model.getSubjectUrl()));
				jo.put("subjectBeginTime", StringTools.formatToString(model.getSubjectBeginTime()));
				jo.put("subjectEndTime", StringTools.formatToString(model.getSubjectEndTime()));
				jo.put("subjectSource", StringTools.formatToString(model.getSubjectSource()));
				jo.put("sourceCode", StringTools.formatToString(model.getSourceCode()));
				jo.put("sourceUserCode", StringTools.formatToString(model.getSourceUserCode()));
				jo.put("sourceDepend", StringTools.formatToString(model.getSourceDepend()));
				
				String sourceTeamName = "";
				String sourceUserName = "";
				String sourceUserPhotoUrl = "";
				String sourceOrgCode = "";
				String sourceOrgName = "";
				String sourceTeamCode = StringTools.formatToString(model.getSourceTeamCode());
					
				if(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(model.getSourceDepend())){
					//班组
					TeamInfo tempTeamInfo = teamInfoService.selectByCode(sourceTeamCode);
					if(tempTeamInfo!=null){
						sourceTeamName = tempTeamInfo.getTeamName();
						SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(tempTeamInfo.getOrgCode());
						if(sysOrgInfo!=null){
							sourceOrgCode = tempTeamInfo.getOrgCode();
							sourceOrgName = sysOrgInfo.getSysOrgFullname();
						}
					}
				}else if (RelationConstants.RELATION_SUBJECT_DEPEND_USER.equals(model.getSourceDepend())) {
					SysUserInfo tempUser = sysUserService.selectByCode(StringTools.formatToString(model.getSourceUserCode()));
					if(tempUser!=null){
						sourceUserName = tempUser.getUserName();
						sourceUserPhotoUrl = tempUser.getHeadPortrait();
						sourceOrgCode = tempUser.getManageOrgInfo().getSysOrgCode();
						sourceOrgName = tempUser.getManageOrgInfo().getSysOrgFullname();
					}
				}
				jo.put("sourceTeamCode", StringTools.formatToString(sourceTeamCode));
				jo.put("sourceUserName", StringTools.formatToString(sourceUserName));
				jo.put("sourceUserPhotoUrl", StringTools.formatToString(sourceUserPhotoUrl));
				jo.put("sourceTeamName", StringTools.formatToString(sourceTeamName));
				jo.put("sourceOrgCode", StringTools.formatToString(sourceOrgCode));
				jo.put("sourceOrgName", StringTools.formatToString(sourceOrgName));
				jo.put("sourcePublishTime", StringTools.formatToString(model.getSourcePublishTime()));
				jo.put("sourceIsDelete", StringTools.formatToString(model.getSourceIsDelete()));
				jo.put("sourceIsShield", StringTools.formatToString(model.getSourceIsShield()));
				jo.put("contentTips", StringTools.formatToString(model.getContentTips()));
				jo.put("teamQualityNum", StringTools.formatToString(model.getTeamQualityNum()));
				jo.put("groupQualityNum", StringTools.formatToString(model.getGroupQualityNum()));
				jo.put("provinceQualityNum", StringTools.formatToString(model.getProvinceQualityNum()));
				jo.put("cityQualityNum", StringTools.formatToString(model.getCityQualityNum()));
				jo.put("joinNum", StringTools.formatToString(model.getJoinNum()));
				jo.put("readNum", StringTools.formatToString(model.getReadNum()));
				jo.put("likeNum", StringTools.formatToString(model.getLikeNum()));
				jo.put("collectNum", StringTools.formatToString(model.getCollectNum()));
				jo.put("forwardNum", StringTools.formatToString(model.getForwardNum()));
				jo.put("commentNum", StringTools.formatToString(model.getCommentNum()));
				jo.put("shareNum", StringTools.formatToString(model.getShareNum()));
				jo.put("isLike", StringTools.formatToString(model.getHotIsLike()));
				jo.put("isCollect", StringTools.formatToString(model.getHotIsCollect()));
				
				jo.put("dynamicCode", StringTools.formatToString(model.getDynamicCode()));
				
				jo.put("teamFrontShow", StringTools.formatToString(model.getRelIsHomeShow()!=null?model.getRelIsHomeShow():RelationConstants.RELATION_NO));
				jo.put("teamQuality", StringTools.formatToString(model.getRelIsQuality()!=null?model.getRelIsQuality():RelationConstants.RELATION_NO));
				JSONArray jResList = new JSONArray();
				for(ResourceModel rm:model.getResList()){
					JSONObject jr = new JSONObject();
					jr.put("resCode", StringTools.formatToString(rm.getResCode()));
					jr.put("resUrl", StringTools.formatToString(rm.getResUrl()));
					jr.put("resClass", StringTools.formatToString(rm.getResClass()));
					jResList.add(jr);
				}
				jo.put("resList", jResList);
				ja.add(jo);
			}
			JSONObject body = new JSONObject();
			body.put("teamCode", teamCode);
			body.put("teamName", teamName);
			body.put("total", pageBean.getTotalCount());
			body.put("hotList", ja);
			resultJSON = new ResultJSON("COMMON_200", "查询班组展示中热点列表");
			resultJSON.setBody(body);
			
			//方法出去
			log.info("RelationHotH5Controller.getTeamShowHotList:查询班组展示中热点列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationHotH5Controller.getTeamShowHotList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
	}
	
	
	
	/**
	 * 
	 * @Title: getPortalHotList  
	 * @Description: 查询门户展示中热点列表
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getPortalHotList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getPortalHotList(@RequireValid GetPortalHotListVO param) throws BusinessException {
		
		//返回值
		ResultJSON resultJSON = null;
		
		try {
			//方法进入
			log.info("RelationHotH5Controller.getPortalHotList:查询门户展示中热点列表");
			
			//获取参数
			String qOrgCode = StringTools.formatToString(param.getOrgCode());	//门户编号
			String pageSize = StringTools.formatToString(param.getPageSize());	//显示条数
			String pageNo = StringTools.formatToString(param.getPageNo());
			String userCode = StringTools.formatToString(param.getUserCode());	//用户编号
			Long queryTime = Long.valueOf(param.getQueryTime());//首次查询时间
			//封装参数
			RelationHotVO relationHotVO = new RelationHotVO();
			relationHotVO.setOrgCode(qOrgCode);
			relationHotVO.setPageSize(Integer.valueOf(pageSize));
			relationHotVO.setPageNo(Integer.valueOf(pageNo));
			relationHotVO.setUserCode(userCode);
			relationHotVO.setQueryTime(queryTime);
			//查询
			List<RelationPortalHotModel> list = relationHotService.getMHPortalHotList(relationHotVO);
			if (list == null) {
				log.error("RelationHotH5Controller.getPortalHotList:查询门户展示中热点列表失败");
				throw BusinessException.build("RELATION_19002", "查询门户展示中热点列表");
			}
			//拼装参数
			JSONArray ja = new JSONArray();
			for (RelationPortalHotModel model : list) {
				JSONObject jo = new JSONObject();
				jo.put("hotCode", StringTools.formatToString(model.getHotCode()));
				jo.put("subjectCode", StringTools.formatToString(model.getSubjectCode()));
				jo.put("subjectClass", StringTools.formatToString(model.getSubjectClass()));
				jo.put("userCode", StringTools.formatToString(model.getUserCode()));
				String userName ="";
				String userPhotoUrl = "";
				String orgCode = "";
				String orgName = "";
				String teamName = "";
				if(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(model.getSubjectDepend())){
					//班组
					TeamInfo tempTeamInfo = teamInfoService.selectByCode(StringTools.formatToString(model.getTeamCode()));
					if(tempTeamInfo!=null){
						SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(tempTeamInfo.getOrgCode());
						teamName = tempTeamInfo.getTeamName();
						if(sysOrgInfo!=null){
							orgCode = tempTeamInfo.getOrgCode();
							orgName = sysOrgInfo.getSysOrgFullname();
						}
					}
				}else{
					SysUserInfo tempUser = sysUserService.selectByCode(StringTools.formatToString(model.getUserCode()));
					if(tempUser!=null){
						userName = tempUser.getUserName();
						userPhotoUrl = tempUser.getHeadPortrait();
						orgCode = tempUser.getManageOrgInfo().getSysOrgCode();
						orgName = tempUser.getManageOrgInfo().getSysOrgFullname();
					}
					
				}
				jo.put("userName", StringTools.formatToString(userName));
				jo.put("userPhotoUrl", StringTools.formatToString(userPhotoUrl));
				jo.put("subjectDepend", StringTools.formatToString(model.getSubjectDepend()));
				jo.put("orgCode", StringTools.formatToString(orgCode));
				jo.put("orgName", StringTools.formatToString(orgName));
				jo.put("teamCode", StringTools.formatToString(model.getTeamCode()));
				jo.put("teamName", teamName);
				jo.put("subjectPublishTime", StringTools.formatToString(model.getSubjectPublishTime()));
				jo.put("subjectTitle", StringTools.formatToString(model.getSubjectTitle()));
				jo.put("subjectDigest", StringTools.formatToString(model.getSubjectDigest()));
				jo.put("subjectUrl", StringTools.formatToString(model.getSubjectUrl()));
				jo.put("subjectBeginTime", StringTools.formatToString(model.getSubjectBeginTime()));
				jo.put("subjectEndTime", StringTools.formatToString(model.getSubjectEndTime()));
				jo.put("subjectSource", StringTools.formatToString(model.getSubjectSource()));
				jo.put("subjectStatus", StringTools.formatToString(model.getSubjectStatus()));
				jo.put("sourceCode", StringTools.formatToString(model.getSourceCode()));
				jo.put("sourceUserCode", StringTools.formatToString(model.getSourceUserCode()));
				jo.put("sourceDepend", StringTools.formatToString(model.getSourceDepend()));
				
				String sourceTeamName = "";
				String sourceUserName = "";
				String sourceUserPhotoUrl = "";
				String sourceOrgCode = "";
				String sourceOrgName = "";
				String sourceTeamCode = StringTools.formatToString(model.getSourceTeamCode());
					
				if(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(model.getSourceDepend())){
					//班组
					TeamInfo tempTeamInfo = teamInfoService.selectByCode(sourceTeamCode);
					if(tempTeamInfo!=null){
						sourceTeamName = tempTeamInfo.getTeamName();
						SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(tempTeamInfo.getOrgCode());
						if(sysOrgInfo!=null){
							sourceOrgCode = tempTeamInfo.getOrgCode();
							sourceOrgName = sysOrgInfo.getSysOrgFullname();
						}
					}
				}else if (RelationConstants.RELATION_SUBJECT_DEPEND_USER.equals(model.getSourceDepend())) {
					//个人
					SysUserInfo tempUser = sysUserService.selectByCode(StringTools.formatToString(model.getSourceUserCode()));
					if(tempUser!=null){
						sourceUserName = tempUser.getUserName();
						sourceUserPhotoUrl = tempUser.getHeadPortrait();
						sourceOrgCode = tempUser.getManageOrgInfo().getSysOrgCode();
						sourceOrgName = tempUser.getManageOrgInfo().getSysOrgFullname();
					}
				}
				jo.put("sourceTeamCode", StringTools.formatToString(sourceTeamCode));
				jo.put("sourceUserName", StringTools.formatToString(sourceUserName));
				jo.put("sourceUserPhotoUrl", StringTools.formatToString(sourceUserPhotoUrl));
				jo.put("sourceTeamName", StringTools.formatToString(sourceTeamName));
				jo.put("sourceOrgCode", StringTools.formatToString(sourceOrgCode));
				jo.put("sourceOrgName", StringTools.formatToString(sourceOrgName));
				jo.put("sourcePublishTime", StringTools.formatToString(model.getSourcePublishTime()));
				jo.put("sourceIsDelete", StringTools.formatToString(model.getSourceIsDelete()));
				jo.put("sourceIsShield", StringTools.formatToString(model.getSourceIsShield()));
				jo.put("contentTips", StringTools.formatToString(model.getContentTips()));
				jo.put("teamQualityNum", StringTools.formatToString(model.getTeamQualityNum()));
				jo.put("groupQualityNum", StringTools.formatToString(model.getGroupQualityNum()));
				jo.put("provinceQualityNum", StringTools.formatToString(model.getProvinceQualityNum()));
				jo.put("cityQualityNum", StringTools.formatToString(model.getCityQualityNum()));
				jo.put("joinNum", StringTools.formatToString(model.getJoinNum()));
				jo.put("readNum", StringTools.formatToString(model.getReadNum()));
				jo.put("likeNum", StringTools.formatToString(model.getLikeNum()));
				jo.put("collectNum", StringTools.formatToString(model.getCollectNum()));
				jo.put("forwardNum", StringTools.formatToString(model.getForwardNum()));
				jo.put("commentNum", StringTools.formatToString(model.getCommentNum()));
				jo.put("shareNum", StringTools.formatToString(model.getShareNum()));
				jo.put("hotIsLike", StringTools.formatToString(model.getHotIsLike()));
				jo.put("hotIsCollect", StringTools.formatToString(model.getHotIsCollect()));
				JSONArray jResList = new JSONArray();
				for(ResourceModel rm:model.getResList()){
					JSONObject jr = new JSONObject();
					jr.put("resCode", StringTools.formatToString(rm.getResCode()));
					jr.put("resUrl", StringTools.formatToString(rm.getResUrl()));
					jr.put("resClass", StringTools.formatToString(rm.getResClass()));
					jResList.add(jr);
				}
				jo.put("resList", jResList);
				ja.add(jo);
			}
			JSONObject body = new JSONObject();
			body.put("hotList", ja);
			resultJSON = new ResultJSON("COMMON_200", "查询门户展示中热点列表成功");
			resultJSON.setBody(body);
			
			//方法出来
			log.info("RelationHotH5Controller.getPortalHotList:查询门户展示中热点列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationHotH5Controller.getPortalHotList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
		
	}
	
	
	
	/**
	 * 
	 * @Title: updateTeamHotStatus  
	 * @Description: 隐藏班组热点
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@ResponseBody
	@RequestMapping(value="updateTeamHotStatus",method=RequestMethod.POST)
	public ResultJSON updateTeamHotStatus(@RequireValid UpdateTeamHotStatusVO param) throws BusinessException {

		//返回值
		ResultJSON resultJSON = null;
		try {
			log.info("RelationHotH5Controller.updateTeamHotStatus:隐藏/显示班组热点");
			
			//获取参数
			String hotCode = StringTools.formatToString(param.getHotCode());			//热点编号
			String actionClass = StringTools.formatToString(1);	//操作类型：1.隐藏；2.显示
			String teamCode = StringTools.formatToString(param.getTeamCode());			//班组编号
			SysUserInfo sysUserInfo = sysUserService.selectByCode(StringTools.formatToString(param.getUserCode()));
			if(sysUserInfo==null || sysUserInfo.getManageOrgInfo()==null||StringTools.isEmpty(sysUserInfo.getManageOrgInfo().getSysOrgCode())){
				log.error("RelationHotH5Controller.updateTeamHotStatus：用户或组织没有获取到userCode:"+param.getUserCode());
				throw BusinessException.build("BLOG_13002", "隐藏/显示班组热点");
			}
			String tenantCode = sysUserInfo.getTenantCode();
			String orgCode = sysUserInfo.getManageOrgInfo().getSysOrgCode();
			String userCode = StringTools.formatToString(param.getUserCode());	
			//封装参数数据
			RelationHotVO relationHotVO = new RelationHotVO();
			relationHotVO.setHotCode(hotCode);
			relationHotVO.setActionClass(Byte.valueOf(actionClass));
			relationHotVO.setTeamCode(teamCode);
			relationHotVO.setHotHideDepend(Byte.valueOf(RelationConstants.RELATION_HOT_HIDE_TEAM));
			relationHotVO.setOrgCode(orgCode);
			relationHotVO.setTenantCode(tenantCode);
			relationHotVO.setUserCode(userCode);
			boolean updateFlag = relationHotService.updateTeamHotStatus(relationHotVO);
			if (!updateFlag) {
				log.error("RelationHotH5Controller.updateHotStatus：隐藏/显示热点失败");
				throw BusinessException.build("RELATION_19002", "隐藏/显示热点");
			}else{
				resultJSON = new ResultJSON("COMMON_200", "隐藏/显示热点操作");
				resultJSON.setBody(new JSONObject());
				log.info("RelationHotH5Controller.updateTeamHotStatus:隐藏/显示班组热点成功");
			}
			
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationHotH5Controller.updateTeamHotStatus", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
	}
	
}
