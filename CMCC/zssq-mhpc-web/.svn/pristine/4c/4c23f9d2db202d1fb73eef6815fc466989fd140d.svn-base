package com.zssq.relation.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.BlogConstants;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.MblogConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.constants.TeamConstants;
import com.zssq.dao.pojo.MessageIntegral;
import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RelationDynamicTeamRel;
import com.zssq.dao.pojo.RelationQuality;
import com.zssq.dao.pojo.RelationRecommend;
import com.zssq.dao.pojo.RelationSubjectInfo;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.model.RelationDynamicModel;
import com.zssq.model.RelationSubjectResourceModel;
import com.zssq.pojo.ResultJSON;
import com.zssq.qo.sync.MblogEssenceVo;
import com.zssq.relation.vo.GetPortalExTeamDynamicListVO;
import com.zssq.relation.vo.GetPortalTeamDynamicListVO;
import com.zssq.relation.vo.GetPortalTopTeamDynamicListVO;
import com.zssq.relation.vo.GetPortalUserDynamicListVO;
import com.zssq.relation.vo.GetTeamDynamicListVO;
import com.zssq.relation.vo.GetTeamUserDynamicListVO;
import com.zssq.relation.vo.GetUserDynamicListVO;
import com.zssq.relation.vo.GetUserSpaceDynamicListVO;
import com.zssq.relation.vo.RecByTeamVO;
import com.zssq.relation.vo.SetTeamQualityVO;
import com.zssq.relation.vo.ShowFrontTeamUserDynamicVO;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamElectService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.ITeamMemberService;
import com.zssq.service.IUserRelationService;
import com.zssq.service.RelationDynamicService;
import com.zssq.service.RelationSubjectService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.BlogThirdDataVO;
import com.zssq.vo.RelationDataVO;
import com.zssq.vo.RelationDynamicVO;
import com.zssq.vo.RelationSubjectVO;

/**
 * 
 * @ClassName: RelationDynamicController  
 * @Description: 动态  
 * @author ZKZ  
 * @date 2017年4月20日  
 *
 */
@RequestMapping("/relation")
@Controller
public class RelationDynamicController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RelationDynamicService relationDynamicService;
	@Autowired
	private RelationSubjectService relationSubjectService;
	@Autowired
	private IUserRelationService iUserRelationService;
	@Autowired
	private ITeamInfoService iTeamInfoService;
	@Autowired
	private ITeamElectService iTeamElectService;
	@Autowired
	private ITeamMemberService iTeamMemberService;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private ISysOrgService iSysOrgService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	protected KafkaProducerTemplate producerTeplate;
	
	
	/**
	 * 
	 * @Title: getUserSpaceDynamicList  
	 * @Description: 查询个人空间动态列表
	 * @param getUserSpaceDynamicListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getUserSpaceDynamicList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getUserSpaceDynamicList(@RequireValid GetUserSpaceDynamicListVO getUserSpaceDynamicListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationDynamicController.getUserSpaceDynamicList：查询个人空间动态列表");
			
			// 获取参数
			String pageSize = getUserSpaceDynamicListVO.getPageSize(); // 每页条数
			String pageNo = getUserSpaceDynamicListVO.getPageNo(); // 页码
			String queryTime = getUserSpaceDynamicListVO.getQueryTime(); // 首次查询时间
			String userCode = getUserSpaceDynamicListVO.getUserCode(); // 人员编号
			
			// 获取关注的人列表
			List<String> userCodeList = iUserRelationService.selectConcernsAndFriends(userCode);
			
			// 获取关注的班组列表
			List<String> teamCodeList = iTeamInfoService.selectConcernsTeam(userCode);
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setUserCode(userCode); // 人员编号
			relationDynamicVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			relationDynamicVO.setUserCodeList(userCodeList); // 人员列表
			relationDynamicVO.setTeamCodeList(teamCodeList); // 班组列表
			
			// 查询
			PageBean pageBean = relationDynamicService.getUserSpaceDynamicList(pageParam, relationDynamicVO);
			if (pageBean == null) {
				log.error("RelationDynamicController.getUserSpaceDynamicList：查询失败");
				throw BusinessException.build("RELATION_19002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					RelationDynamicModel relationDynamicModel = (RelationDynamicModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("dynamicCode", StringTools.formatToString(relationDynamicModel.getDynamicCode()));
					jo.put("dynamicClass", StringTools.formatToString(relationDynamicModel.getDynamicClass()));
					
					// 获取动态所属人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(relationDynamicModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jo.put("dynamicDepend", StringTools.formatToString(relationDynamicModel.getDynamicDepend()));
					if (RelationConstants.RELATION_DYNAMIC_DEPEND_TEAM.equals(relationDynamicModel.getDynamicDepend())) {
						// 获取动态所属班组信息
						TeamInfo teamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getTeamCode());
						jo.put("teamCode", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamCode()));
						jo.put("teamName", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamName()));
						// 获取动态所属门户信息
						SysOrgInfo sysOrgInfo = iSysOrgService.selectByCode(teamInfo == null ? "" : teamInfo.getOrgCode());
						jo.put("orgCode", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
						jo.put("orgName", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("teamCode", "");
						jo.put("teamName", "");
						jo.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
						jo.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
					}

					// 操作信息
					if (!StringUtils.isBlank(relationDynamicModel.getOperateTeamCode())) {
						// 查询操作班组信息
						TeamInfo operateTeamCode = iTeamInfoService.selectByCode(relationDynamicModel.getOperateTeamCode());
						jo.put("operateTeamCode", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamCode()));
						jo.put("operateTeamName", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamName()));
					} else {
						jo.put("operateTeamCode", "");
						jo.put("operateTeamName", "");
					}
					if (!StringUtils.isBlank(relationDynamicModel.getOperateOrgCode())) {
						// 查询操作组织信息
						SysOrgInfo operateOrgInfo = iSysOrgService.selectByCode(relationDynamicModel.getOperateOrgCode());
						jo.put("operateOrgCode", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgCode()));
						jo.put("operateOrgName", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("operateOrgCode", "");
						jo.put("operateOrgName", "");
					}
					
					jo.put("operateTime", StringTools.formatToString(relationDynamicModel.getOperateTime()));
					jo.put("subjectCode", StringTools.formatToString(relationDynamicModel.getSubjectCode()));
					jo.put("subjectName", StringTools.formatToString(relationDynamicModel.getSubjectName()));
					jo.put("subjectUrlHonor", StringTools.formatToString(relationDynamicModel.getSubjectUrlHonor()));
					jo.put("subjectClass", StringTools.formatToString(relationDynamicModel.getSubjectClass()));
					
					// 显示内容信息
					if (RelationConstants.RELATION_YES.equals(relationDynamicModel.getIsSubjectShow())) {
						// 获取内容发布人信息
						SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSubjectUserCode());
						jo.put("subjectUserCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserCode()));
						jo.put("subjectUserName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserName()));
						jo.put("subjectUserPhotoUrl", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getHeadPortrait()));
						jo.put("subjectDepend", StringTools.formatToString(relationDynamicModel.getSubjectDepend()));
						if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSubjectDepend())) {
							// 获取内容所属班组信息
							TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSubjectTeamCode());
							jo.put("subjectTeamCode", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamCode()));
							jo.put("subjectTeamName", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamName()));
							SysOrgInfo subjectOrgCode = iSysOrgService.selectByCode(subjectTeamInfo == null ? "" : subjectTeamInfo.getOrgCode());
							jo.put("subjectOrgCode", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgCode()));
							jo.put("subjectOrgName", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgFullname()));
						} else {
							jo.put("subjectTeamCode", "");
							jo.put("subjectTeamName", "");
							jo.put("subjectOrgCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgCode()));
							jo.put("subjectOrgName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgFullname()));
						}
						
						jo.put("subjectPublishTime", StringTools.formatToString(relationDynamicModel.getSubjectPublishTime()));
						jo.put("subjectTitle", StringTools.formatToString(relationDynamicModel.getSubjectTitle()));
						jo.put("subjectDigest", StringTools.formatToString(relationDynamicModel.getSubjectDigest()));
						jo.put("subjectUrl", StringTools.formatToString(relationDynamicModel.getSubjectUrl()));
						jo.put("subjectSource", StringTools.formatToString(relationDynamicModel.getSubjectSource()));
						jo.put("sourceCode", StringTools.formatToString(relationDynamicModel.getSourceCode()));
						
						// 内容来源信息
						if (RelationConstants.RELATION_SUBJECT_SOURCE_FORWARD.equals(relationDynamicModel.getSubjectSource())) {
							// 转发微博被删除或者被屏蔽不需要查
							if (RelationConstants.RELATION_DYNAMIC_FORWARD_MBLOG.equals(relationDynamicModel.getDynamicClass())
									&& (RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsShield())
											|| RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsDelete()))) {
								jo.put("sourceUserCode", "");
								jo.put("sourceUserName", "");
								jo.put("sourceUserPhotoUrl", "");
								jo.put("sourceDepend", "");
								jo.put("sourceTeamCode", "");
								jo.put("sourceTeamName", "");
								jo.put("sourceOrgCode", "");
								jo.put("sourceOrgName", "");
								jo.put("sourcePublishTime", "");
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							} else {
								// 获取来源人员信息
								SysUserInfo sourceUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSourceUserCode());
								jo.put("sourceUserCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserCode()));
								jo.put("sourceUserName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserName()));
								jo.put("sourceUserPhotoUrl", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getHeadPortrait()));
								jo.put("sourceDepend", StringTools.formatToString(relationDynamicModel.getSourceDepend()));
								if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSourceDepend())) {
									// 获取来源班组信息
									TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSourceTeamCode());
									jo.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamCode()));
									jo.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
									// 获取来源组织信息
									SysOrgInfo sourceOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo == null ? "" : sourceTeamInfo.getOrgCode());
									jo.put("sourceOrgCode", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgCode()));
									jo.put("sourceOrgName", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgFullname()));
								} else {
									jo.put("sourceTeamCode", "");
									jo.put("sourceTeamName", "");
									jo.put("sourceOrgCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgCode()));
									jo.put("sourceOrgName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgFullname()));
								}
								
								jo.put("sourcePublishTime", StringTools.formatToString(relationDynamicModel.getSourcePublishTime()));
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							}
						} else {
							jo.put("sourceUserCode", "");
							jo.put("sourceUserName", "");
							jo.put("sourceUserPhotoUrl", "");
							jo.put("sourceDepend", "");
							jo.put("sourceTeamCode", "");
							jo.put("sourceTeamName", "");
							jo.put("sourceOrgCode", "");
							jo.put("sourceOrgName", "");
							jo.put("sourcePublishTime", "");
							jo.put("sourceIsShield", "");
							jo.put("sourceIsDelete", "");
						}
						
						jo.put("contentTips", StringTools.formatToString(relationDynamicModel.getContentTips()));
						jo.put("subjectBeginTime", StringTools.formatToString(relationDynamicModel.getSubjectBeginTime()));
						jo.put("subjectEndTime", StringTools.formatToString(relationDynamicModel.getSubjectEndTime()));
						jo.put("teamQualityNum", StringTools.formatToString(relationDynamicModel.getTeamQualityNum()));
						jo.put("groupQualityNum", StringTools.formatToString(relationDynamicModel.getGroupQualityNum()));
						jo.put("provinceQualityNum", StringTools.formatToString(relationDynamicModel.getProvinceQualityNum()));
						jo.put("cityQualityNum", StringTools.formatToString(relationDynamicModel.getCityQualityNum()));
						jo.put("joinNum", StringTools.formatToString(relationDynamicModel.getJoinNum()));
						jo.put("readNum", StringTools.formatToString(relationDynamicModel.getReadNum()));
						jo.put("likeNum", StringTools.formatToString(relationDynamicModel.getLikeNum()));
						jo.put("collectNum", StringTools.formatToString(relationDynamicModel.getCollectNum()));
						jo.put("forwardNum", StringTools.formatToString(relationDynamicModel.getForwardNum()));
						jo.put("commentNum", StringTools.formatToString(relationDynamicModel.getCommentNum()));
						jo.put("shareNum", StringTools.formatToString(relationDynamicModel.getShareNum()));
					} else {
						jo.put("subjectUserCode", "");
						jo.put("subjectUserName", "");
						jo.put("subjectUserPhotoUrl", "");
						jo.put("subjectDepend", "");
						jo.put("subjectTeamCode", "");
						jo.put("subjectOrgCode", "");
						jo.put("subjectOrgName", "");
						jo.put("subjectPublishTime", "");
						jo.put("subjectTitle", "");
						jo.put("subjectDigest", "");
						jo.put("subjectUrl", "");
						jo.put("subjectSource", "");
						jo.put("sourceCode", "");
						jo.put("sourceUserCode", "");
						jo.put("sourceUserName", "");
						jo.put("sourceUserPhotoUrl", "");
						jo.put("sourceDepend", "");
						jo.put("sourceTeamCode", "");
						jo.put("sourceTeamName", "");
						jo.put("sourceOrgCode", "");
						jo.put("sourceOrgName", "");
						jo.put("sourcePublishTime", "");
						jo.put("sourceIsShield", "");
						jo.put("sourceIsDelete", "");
						jo.put("contentTips", "");
						jo.put("subjectBeginTime", "");
						jo.put("subjectEndTime", "");
						jo.put("teamQualityNum", "");
						jo.put("groupQualityNum", "");
						jo.put("provinceQualityNum", "");
						jo.put("cityQualityNum", "");
						jo.put("joinNum", "");
						jo.put("readNum", "");
						jo.put("likeNum", "");
						jo.put("collectNum", "");
						jo.put("forwardNum", "");
						jo.put("commentNum", "");
						jo.put("shareNum", "");
					}
					
					// 网盘所属组织
					if (RelationConstants.RELATION_DYNAMIC_OD.equals(relationDynamicModel.getDynamicClass())) {
						jo.put("diskOrgCode", StringTools.formatToString(relationDynamicModel.getDiskOrgCode()));
					} else {
						jo.put("diskOrgCode", "");
					}
					
					jo.put("isLike", StringTools.formatToString(relationDynamicModel.getIsLike()));
					jo.put("isCollect", StringTools.formatToString(relationDynamicModel.getIsCollect()));
					
					// 内容资源
					List<RelationSubjectResourceModel> resourceList = relationDynamicModel.getResourceList();
					JSONArray resList = new JSONArray();
					if (resourceList != null && !resourceList.isEmpty()) {
						for (RelationSubjectResourceModel resource : resourceList) {
							JSONObject res = new JSONObject();
							res.put("resCode", StringTools.formatToString(resource.getSubjectResCode()));
							res.put("resUrl", StringTools.formatToString(resource.getResUrl()));
							res.put("resClass", StringTools.formatToString(resource.getResClass()));
							resList.add(res);
						}
					}
					jo.put("resList", resList);
					
					// 添加
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("dynamicList", ja);
			result.setBody(body);
			
			log.info("RelationDynamicController.getUserSpaceDynamicList：查询个人空间动态列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationDynamicController.getUserSpaceDynamicList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getUserDynamicList  
	 * @Description: 查询个人主页动态列表
	 * @param getUserDynamicListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getUserDynamicList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getUserDynamicList(@RequireValid GetUserDynamicListVO getUserDynamicListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationDynamicController.getUserDynamicList：查询个人主页动态列表");
			
			// 获取参数
			String pageSize = getUserDynamicListVO.getPageSize(); // 每页条数
			String pageNo = getUserDynamicListVO.getPageNo(); // 页码
			String queryTime = getUserDynamicListVO.getQueryTime(); // 首次查询时间
			String userCode = getUserDynamicListVO.getUserCode(); // 人员编号
			String dynamicUserCode = getUserDynamicListVO.getDynamicUserCode(); // 所属人编号
			
			// 获取动态所属人员信息
			SysUserInfo dynamicUserInfo = iSysUserService.selectByCode(dynamicUserCode);
			if (dynamicUserInfo == null) {
				log.error("RelationDynamicController.getUserDynamicList：获取不到人员信息userCode=" + dynamicUserCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setDynamicUserCode(dynamicUserCode); // 所属人员编号
			relationDynamicVO.setUserCode(userCode); // 人员编号
			relationDynamicVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			
			// 查询
			PageBean pageBean = relationDynamicService.getUserDynamicList(pageParam, relationDynamicVO);
			if (pageBean == null) {
				log.error("RelationDynamicController.getUserDynamicList：查询失败");
				throw BusinessException.build("RELATION_19002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					RelationDynamicModel relationDynamicModel = (RelationDynamicModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("dynamicCode", StringTools.formatToString(relationDynamicModel.getDynamicCode()));
					jo.put("dynamicClass", StringTools.formatToString(relationDynamicModel.getDynamicClass()));
					jo.put("dynamicDepend", StringTools.formatToString(relationDynamicModel.getDynamicDepend()));

					// 操作信息
					if (!StringUtils.isBlank(relationDynamicModel.getOperateTeamCode())) {
						// 查询操作班组信息
						TeamInfo operateTeamCode = iTeamInfoService.selectByCode(relationDynamicModel.getOperateTeamCode());
						jo.put("operateTeamCode", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamCode()));
						jo.put("operateTeamName", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamName()));
					} else {
						jo.put("operateTeamCode", "");
						jo.put("operateTeamName", "");
					}
					if (!StringUtils.isBlank(relationDynamicModel.getOperateOrgCode())) {
						// 查询操作组织信息
						SysOrgInfo operateOrgInfo = iSysOrgService.selectByCode(relationDynamicModel.getOperateOrgCode());
						jo.put("operateOrgCode", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgCode()));
						jo.put("operateOrgName", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("operateOrgCode", "");
						jo.put("operateOrgName", "");
					}
					
					jo.put("operateTime", StringTools.formatToString(relationDynamicModel.getOperateTime()));
					jo.put("subjectCode", StringTools.formatToString(relationDynamicModel.getSubjectCode()));
					jo.put("subjectName", StringTools.formatToString(relationDynamicModel.getSubjectName()));
					jo.put("subjectUrlHonor", StringTools.formatToString(relationDynamicModel.getSubjectUrlHonor()));
					jo.put("subjectClass", StringTools.formatToString(relationDynamicModel.getSubjectClass()));
					
					// 显示内容信息
					if (RelationConstants.RELATION_YES.equals(relationDynamicModel.getIsSubjectShow())) {
						// 获取内容发布人信息
						SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSubjectUserCode());
						jo.put("subjectUserCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserCode()));
						jo.put("subjectUserName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserName()));
						jo.put("subjectUserPhotoUrl", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getHeadPortrait()));
						jo.put("subjectDepend", StringTools.formatToString(relationDynamicModel.getSubjectDepend()));
						if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSubjectDepend())) {
							// 获取内容所属班组信息
							TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSubjectTeamCode());
							jo.put("subjectTeamCode", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamCode()));
							jo.put("subjectTeamName", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamName()));
							SysOrgInfo subjectOrgCode = iSysOrgService.selectByCode(subjectTeamInfo == null ? "" : subjectTeamInfo.getOrgCode());
							jo.put("subjectOrgCode", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgCode()));
							jo.put("subjectOrgName", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgFullname()));
						} else {
							jo.put("subjectTeamCode", "");
							jo.put("subjectTeamName", "");
							jo.put("subjectOrgCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgCode()));
							jo.put("subjectOrgName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgFullname()));
						}
						
						jo.put("subjectPublishTime", StringTools.formatToString(relationDynamicModel.getSubjectPublishTime()));
						jo.put("subjectTitle", StringTools.formatToString(relationDynamicModel.getSubjectTitle()));
						jo.put("subjectDigest", StringTools.formatToString(relationDynamicModel.getSubjectDigest()));
						jo.put("subjectUrl", StringTools.formatToString(relationDynamicModel.getSubjectUrl()));
						jo.put("subjectSource", StringTools.formatToString(relationDynamicModel.getSubjectSource()));
						jo.put("sourceCode", StringTools.formatToString(relationDynamicModel.getSourceCode()));
						
						// 内容来源信息
						if (RelationConstants.RELATION_SUBJECT_SOURCE_FORWARD.equals(relationDynamicModel.getSubjectSource())) {
							// 转发微博被删除或者被屏蔽不需要查
							if (RelationConstants.RELATION_DYNAMIC_FORWARD_MBLOG.equals(relationDynamicModel.getDynamicClass())
									&& (RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsShield())
											|| RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsDelete()))) {
								jo.put("sourceUserCode", "");
								jo.put("sourceUserName", "");
								jo.put("sourceUserPhotoUrl", "");
								jo.put("sourceDepend", "");
								jo.put("sourceTeamCode", "");
								jo.put("sourceTeamName", "");
								jo.put("sourceOrgCode", "");
								jo.put("sourceOrgName", "");
								jo.put("sourcePublishTime", "");
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							} else {
								// 获取来源人员信息
								SysUserInfo sourceUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSourceUserCode());
								jo.put("sourceUserCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserCode()));
								jo.put("sourceUserName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserName()));
								jo.put("sourceUserPhotoUrl", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getHeadPortrait()));
								jo.put("sourceDepend", StringTools.formatToString(relationDynamicModel.getSourceDepend()));
								if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSourceDepend())) {
									// 获取来源班组信息
									TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSourceTeamCode());
									jo.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamCode()));
									jo.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
									// 获取来源组织信息
									SysOrgInfo sourceOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo.getOrgCode());
									jo.put("sourceOrgCode", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgCode()));
									jo.put("sourceOrgName", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgFullname()));
								} else {
									jo.put("sourceTeamCode", "");
									jo.put("sourceTeamName", "");
									jo.put("sourceOrgCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgCode()));
									jo.put("sourceOrgName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgFullname()));
								}
								
								jo.put("sourcePublishTime", StringTools.formatToString(relationDynamicModel.getSourcePublishTime()));
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							}
						} else {
							jo.put("sourceUserCode", "");
							jo.put("sourceUserName", "");
							jo.put("sourceUserPhotoUrl", "");
							jo.put("sourceDepend", "");
							jo.put("sourceTeamCode", "");
							jo.put("sourceTeamName", "");
							jo.put("sourceOrgCode", "");
							jo.put("sourceOrgName", "");
							jo.put("sourcePublishTime", "");
							jo.put("sourceIsShield", "");
							jo.put("sourceIsDelete", "");
						}
						
						jo.put("contentTips", StringTools.formatToString(relationDynamicModel.getContentTips()));
						jo.put("subjectBeginTime", StringTools.formatToString(relationDynamicModel.getSubjectBeginTime()));
						jo.put("subjectEndTime", StringTools.formatToString(relationDynamicModel.getSubjectEndTime()));
						jo.put("teamQualityNum", StringTools.formatToString(relationDynamicModel.getTeamQualityNum()));
						jo.put("groupQualityNum", StringTools.formatToString(relationDynamicModel.getGroupQualityNum()));
						jo.put("provinceQualityNum", StringTools.formatToString(relationDynamicModel.getProvinceQualityNum()));
						jo.put("cityQualityNum", StringTools.formatToString(relationDynamicModel.getCityQualityNum()));
						jo.put("joinNum", StringTools.formatToString(relationDynamicModel.getJoinNum()));
						jo.put("readNum", StringTools.formatToString(relationDynamicModel.getReadNum()));
						jo.put("likeNum", StringTools.formatToString(relationDynamicModel.getLikeNum()));
						jo.put("collectNum", StringTools.formatToString(relationDynamicModel.getCollectNum()));
						jo.put("forwardNum", StringTools.formatToString(relationDynamicModel.getForwardNum()));
						jo.put("commentNum", StringTools.formatToString(relationDynamicModel.getCommentNum()));
						jo.put("shareNum", StringTools.formatToString(relationDynamicModel.getShareNum()));
					} else {
						jo.put("subjectUserCode", "");
						jo.put("subjectUserName", "");
						jo.put("subjectUserPhotoUrl", "");
						jo.put("subjectDepend", "");
						jo.put("subjectTeamCode", "");
						jo.put("subjectOrgCode", "");
						jo.put("subjectOrgName", "");
						jo.put("subjectPublishTime", "");
						jo.put("subjectTitle", "");
						jo.put("subjectDigest", "");
						jo.put("subjectUrl", "");
						jo.put("subjectSource", "");
						jo.put("sourceCode", "");
						jo.put("sourceUserCode", "");
						jo.put("sourceUserName", "");
						jo.put("sourceUserPhotoUrl", "");
						jo.put("sourceDepend", "");
						jo.put("sourceTeamCode", "");
						jo.put("sourceTeamName", "");
						jo.put("sourceOrgCode", "");
						jo.put("sourceOrgName", "");
						jo.put("sourcePublishTime", "");
						jo.put("sourceIsShield", "");
						jo.put("sourceIsDelete", "");
						jo.put("contentTips", "");
						jo.put("subjectBeginTime", "");
						jo.put("subjectEndTime", "");
						jo.put("teamQualityNum", "");
						jo.put("groupQualityNum", "");
						jo.put("provinceQualityNum", "");
						jo.put("cityQualityNum", "");
						jo.put("joinNum", "");
						jo.put("readNum", "");
						jo.put("likeNum", "");
						jo.put("collectNum", "");
						jo.put("forwardNum", "");
						jo.put("commentNum", "");
						jo.put("shareNum", "");
					}
					
					// 网盘所属组织
					if (RelationConstants.RELATION_DYNAMIC_OD.equals(relationDynamicModel.getDynamicClass())) {
						jo.put("diskOrgCode", StringTools.formatToString(relationDynamicModel.getDiskOrgCode()));
					} else {
						jo.put("diskOrgCode", "");
					}
					
					jo.put("isLike", StringTools.formatToString(relationDynamicModel.getIsLike()));
					jo.put("isCollect", StringTools.formatToString(relationDynamicModel.getIsCollect()));
					
					// 内容资源
					List<RelationSubjectResourceModel> resourceList = relationDynamicModel.getResourceList();
					JSONArray resList = new JSONArray();
					if (resourceList != null && !resourceList.isEmpty()) {
						for (RelationSubjectResourceModel resource : resourceList) {
							JSONObject res = new JSONObject();
							res.put("resCode", StringTools.formatToString(resource.getSubjectResCode()));
							res.put("resUrl", StringTools.formatToString(resource.getResUrl()));
							res.put("resClass", StringTools.formatToString(resource.getResClass()));
							resList.add(res);
						}
					}
					jo.put("resList", resList);
					
					// 添加
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("userCode", StringTools.formatToString(dynamicUserInfo.getUserCode()));
			body.put("userName", StringTools.formatToString(dynamicUserInfo.getUserName()));
			body.put("userPhotoUrl", StringTools.formatToString(dynamicUserInfo.getHeadPortrait()));
			body.put("orgCode", StringTools.formatToString(dynamicUserInfo.getManageOrgInfo().getSysOrgCode()));
			body.put("orgName", StringTools.formatToString(dynamicUserInfo.getManageOrgInfo().getSysOrgFullname()));
			body.put("total", pageBean.getTotalCount());
			body.put("dynamicList", ja);
			result.setBody(body);
			
			log.info("RelationDynamicController.getUserDynamicList：查询个人主页动态列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationDynamicController.getUserDynamicList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getTeamDynamicList  
	 * @Description: 查询班组动态列表
	 * @param getTeamDynamicListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getTeamDynamicList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getTeamDynamicList(@RequireValid GetTeamDynamicListVO getTeamDynamicListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationDynamicController.getTeamDynamicList：查询班组动态列表");
			
			// 获取参数
			String pageSize = getTeamDynamicListVO.getPageSize(); // 每页条数
			String pageNo = getTeamDynamicListVO.getPageNo(); // 页码
			String queryTime = getTeamDynamicListVO.getQueryTime(); // 首次查询时间
			String userCode = getTeamDynamicListVO.getUserCode(); // 人员编号
			String teamCode = getTeamDynamicListVO.getTeamCode(); // 班组编号
			
			// 获取班组信息
			TeamInfo teamInfo = iTeamInfoService.selectByCode(teamCode);
			if (teamInfo == null) {
				log.error("RelationDynamicController.getTeamDynamicList：获取不到班组信息teamCode=" + teamCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setUserCode(userCode); // 人员编号
			relationDynamicVO.setTeamCode(teamCode); // 班组编号
			relationDynamicVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			
			// 查询
			PageBean pageBean = relationDynamicService.getTeamDynamicList(pageParam, relationDynamicVO);
			if (pageBean == null) {
				log.error("RelationDynamicController.getTeamDynamicList：查询失败");
				throw BusinessException.build("RELATION_19002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					RelationDynamicModel relationDynamicModel = (RelationDynamicModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("dynamicCode", StringTools.formatToString(relationDynamicModel.getDynamicCode()));
					jo.put("dynamicClass", StringTools.formatToString(relationDynamicModel.getDynamicClass()));
					
					// 获取动态所属人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(relationDynamicModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jo.put("dynamicDepend", StringTools.formatToString(relationDynamicModel.getDynamicDepend()));
					if (RelationConstants.RELATION_DYNAMIC_DEPEND_TEAM.equals(relationDynamicModel.getDynamicDepend())) {
						// 获取动态所属门户信息
						SysOrgInfo sysOrgInfo = iSysOrgService.selectByCode(teamInfo.getOrgCode());
						jo.put("orgCode", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
						jo.put("orgName", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
						jo.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
					}

					// 操作信息
					if (!StringUtils.isBlank(relationDynamicModel.getOperateTeamCode())) {
						// 查询操作班组信息
						TeamInfo operateTeamCode = iTeamInfoService.selectByCode(relationDynamicModel.getOperateTeamCode());
						jo.put("operateTeamCode", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamCode()));
						jo.put("operateTeamName", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamName()));
					} else {
						jo.put("operateTeamCode", "");
						jo.put("operateTeamName", "");
					}
					if (!StringUtils.isBlank(relationDynamicModel.getOperateOrgCode())) {
						// 查询操作组织信息
						SysOrgInfo operateOrgInfo = iSysOrgService.selectByCode(relationDynamicModel.getOperateOrgCode());
						jo.put("operateOrgCode", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgCode()));
						jo.put("operateOrgName", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("operateOrgCode", "");
						jo.put("operateOrgName", "");
					}
					
					jo.put("operateTime", StringTools.formatToString(relationDynamicModel.getOperateTime()));
					jo.put("subjectCode", StringTools.formatToString(relationDynamicModel.getSubjectCode()));
					jo.put("subjectName", StringTools.formatToString(relationDynamicModel.getSubjectName()));
					jo.put("subjectUrlHonor", StringTools.formatToString(relationDynamicModel.getSubjectUrlHonor()));
					jo.put("subjectClass", StringTools.formatToString(relationDynamicModel.getSubjectClass()));
					
					// 显示内容信息
					if (RelationConstants.RELATION_YES.equals(relationDynamicModel.getIsSubjectShow())) {
						// 获取内容发布人信息
						SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSubjectUserCode());
						jo.put("subjectUserCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserCode()));
						jo.put("subjectUserName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserName()));
						jo.put("subjectUserPhotoUrl", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getHeadPortrait()));
						jo.put("subjectDepend", StringTools.formatToString(relationDynamicModel.getSubjectDepend()));
						if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSubjectDepend())) {
							// 获取内容所属班组信息
							TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSubjectTeamCode());
							jo.put("subjectTeamCode", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamCode()));
							jo.put("subjectTeamName", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamName()));
							SysOrgInfo subjectOrgCode = iSysOrgService.selectByCode(subjectTeamInfo == null ? "" : subjectTeamInfo.getOrgCode());
							jo.put("subjectOrgCode", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgCode()));
							jo.put("subjectOrgName", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgFullname()));
						} else {
							jo.put("subjectTeamCode", "");
							jo.put("subjectTeamName", "");
							jo.put("subjectOrgCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgCode()));
							jo.put("subjectOrgName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgFullname()));
						}
						
						jo.put("subjectPublishTime", StringTools.formatToString(relationDynamicModel.getSubjectPublishTime()));
						jo.put("subjectTitle", StringTools.formatToString(relationDynamicModel.getSubjectTitle()));
						jo.put("subjectDigest", StringTools.formatToString(relationDynamicModel.getSubjectDigest()));
						jo.put("subjectUrl", StringTools.formatToString(relationDynamicModel.getSubjectUrl()));
						jo.put("subjectSource", StringTools.formatToString(relationDynamicModel.getSubjectSource()));
						jo.put("sourceCode", StringTools.formatToString(relationDynamicModel.getSourceCode()));
						
						// 内容来源信息
						if (RelationConstants.RELATION_SUBJECT_SOURCE_FORWARD.equals(relationDynamicModel.getSubjectSource())) {
							// 转发微博被删除或者被屏蔽不需要查
							if (RelationConstants.RELATION_DYNAMIC_FORWARD_MBLOG.equals(relationDynamicModel.getDynamicClass())
									&& (RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsShield())
											|| RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsDelete()))) {
								jo.put("sourceUserCode", "");
								jo.put("sourceUserName", "");
								jo.put("sourceUserPhotoUrl", "");
								jo.put("sourceDepend", "");
								jo.put("sourceTeamCode", "");
								jo.put("sourceTeamName", "");
								jo.put("sourceOrgCode", "");
								jo.put("sourceOrgName", "");
								jo.put("sourcePublishTime", "");
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							} else {
								// 获取来源人员信息
								SysUserInfo sourceUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSourceUserCode());
								jo.put("sourceUserCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserCode()));
								jo.put("sourceUserName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserName()));
								jo.put("sourceUserPhotoUrl", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getHeadPortrait()));
								jo.put("sourceDepend", StringTools.formatToString(relationDynamicModel.getSourceDepend()));
								if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSourceDepend())) {
									// 获取来源班组信息
									TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSourceTeamCode());
									jo.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamCode()));
									jo.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
									// 获取来源组织信息
									SysOrgInfo sourceOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo == null ? "" : sourceTeamInfo.getOrgCode());
									jo.put("sourceOrgCode", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgCode()));
									jo.put("sourceOrgName", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgFullname()));
								} else {
									jo.put("sourceTeamCode", "");
									jo.put("sourceTeamName", "");
									jo.put("sourceOrgCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgCode()));
									jo.put("sourceOrgName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgFullname()));
								}
								
								jo.put("sourcePublishTime", StringTools.formatToString(relationDynamicModel.getSourcePublishTime()));
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							}
						} else {
							jo.put("sourceUserCode", "");
							jo.put("sourceUserName", "");
							jo.put("sourceUserPhotoUrl", "");
							jo.put("sourceDepend", "");
							jo.put("sourceTeamCode", "");
							jo.put("sourceTeamName", "");
							jo.put("sourceOrgCode", "");
							jo.put("sourceOrgName", "");
							jo.put("sourcePublishTime", "");
							jo.put("sourceIsShield", "");
							jo.put("sourceIsDelete", "");
						}
						
						jo.put("contentTips", StringTools.formatToString(relationDynamicModel.getContentTips()));
						jo.put("subjectBeginTime", StringTools.formatToString(relationDynamicModel.getSubjectBeginTime()));
						jo.put("subjectEndTime", StringTools.formatToString(relationDynamicModel.getSubjectEndTime()));
						jo.put("teamQualityNum", StringTools.formatToString(relationDynamicModel.getTeamQualityNum()));
						jo.put("groupQualityNum", StringTools.formatToString(relationDynamicModel.getGroupQualityNum()));
						jo.put("provinceQualityNum", StringTools.formatToString(relationDynamicModel.getProvinceQualityNum()));
						jo.put("cityQualityNum", StringTools.formatToString(relationDynamicModel.getCityQualityNum()));
						jo.put("joinNum", StringTools.formatToString(relationDynamicModel.getJoinNum()));
						jo.put("readNum", StringTools.formatToString(relationDynamicModel.getReadNum()));
						jo.put("likeNum", StringTools.formatToString(relationDynamicModel.getLikeNum()));
						jo.put("collectNum", StringTools.formatToString(relationDynamicModel.getCollectNum()));
						jo.put("forwardNum", StringTools.formatToString(relationDynamicModel.getForwardNum()));
						jo.put("commentNum", StringTools.formatToString(relationDynamicModel.getCommentNum()));
						jo.put("shareNum", StringTools.formatToString(relationDynamicModel.getShareNum()));
					} else {
						jo.put("subjectUserCode", "");
						jo.put("subjectUserName", "");
						jo.put("subjectUserPhotoUrl", "");
						jo.put("subjectDepend", "");
						jo.put("subjectTeamCode", "");
						jo.put("subjectOrgCode", "");
						jo.put("subjectOrgName", "");
						jo.put("subjectPublishTime", "");
						jo.put("subjectTitle", "");
						jo.put("subjectDigest", "");
						jo.put("subjectUrl", "");
						jo.put("subjectSource", "");
						jo.put("sourceCode", "");
						jo.put("sourceUserCode", "");
						jo.put("sourceUserName", "");
						jo.put("sourceUserPhotoUrl", "");
						jo.put("sourceDepend", "");
						jo.put("sourceTeamCode", "");
						jo.put("sourceTeamName", "");
						jo.put("sourceOrgCode", "");
						jo.put("sourceOrgName", "");
						jo.put("sourcePublishTime", "");
						jo.put("sourceIsShield", "");
						jo.put("sourceIsDelete", "");
						jo.put("contentTips", "");
						jo.put("subjectBeginTime", "");
						jo.put("subjectEndTime", "");
						jo.put("teamQualityNum", "");
						jo.put("groupQualityNum", "");
						jo.put("provinceQualityNum", "");
						jo.put("cityQualityNum", "");
						jo.put("joinNum", "");
						jo.put("readNum", "");
						jo.put("likeNum", "");
						jo.put("collectNum", "");
						jo.put("forwardNum", "");
						jo.put("commentNum", "");
						jo.put("shareNum", "");
					}
					
					// 网盘所属组织
					if (RelationConstants.RELATION_DYNAMIC_OD.equals(relationDynamicModel.getDynamicClass())) {
						jo.put("diskOrgCode", StringTools.formatToString(relationDynamicModel.getDiskOrgCode()));
					} else {
						jo.put("diskOrgCode", "");
					}
					
					jo.put("isLike", StringTools.formatToString(relationDynamicModel.getIsLike()));
					jo.put("isCollect", StringTools.formatToString(relationDynamicModel.getIsCollect()));
					jo.put("teamQuality", StringTools.formatToString(relationDynamicModel.getTeamQuality()));
					
					// 内容资源
					List<RelationSubjectResourceModel> resourceList = relationDynamicModel.getResourceList();
					JSONArray resList = new JSONArray();
					if (resourceList != null && !resourceList.isEmpty()) {
						for (RelationSubjectResourceModel resource : resourceList) {
							JSONObject res = new JSONObject();
							res.put("resCode", StringTools.formatToString(resource.getSubjectResCode()));
							res.put("resUrl", StringTools.formatToString(resource.getResUrl()));
							res.put("resClass", StringTools.formatToString(resource.getResClass()));
							resList.add(res);
						}
					}
					jo.put("resList", resList);
					
					// 添加
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("teamCode", StringTools.formatToString(teamInfo.getTeamCode()));
			body.put("teamName", StringTools.formatToString(teamInfo.getTeamName()));
			body.put("teamPhotoUrl", StringTools.formatToString(teamInfo.getTeamIcon()));
			body.put("total", pageBean.getTotalCount());
			body.put("dynamicList", ja);
			result.setBody(body);
			
			log.info("RelationDynamicController.getTeamDynamicList：查询班组动态列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationDynamicController.getTeamDynamicList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getTeamUserDynamicList  
	 * @Description: 查询班组成员动态列表
	 * @param getTeamUserDynamicListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getTeamUserDynamicList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getTeamUserDynamicList(@RequireValid GetTeamUserDynamicListVO getTeamUserDynamicListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationDynamicController.getTeamUserDynamicList：查询班组成员动态列表");
			
			// 获取参数
			String pageSize = getTeamUserDynamicListVO.getPageSize(); // 每页条数
			String pageNo = getTeamUserDynamicListVO.getPageNo(); // 页码
			String queryTime = getTeamUserDynamicListVO.getQueryTime(); // 首次查询时间
			String userCode = getTeamUserDynamicListVO.getUserCode(); // 人员编号
			String teamCode = getTeamUserDynamicListVO.getTeamCode(); // 班组编号
			
			// 获取班组人员列表
			List<String> userCodeList = iTeamMemberService.selectUserCodeByTeamCode(teamCode);
			
			// 如果班组没有人，直接返回
			if (userCodeList == null || userCodeList.isEmpty()) {
				result = new ResultJSON("COMMON_200");
				JSONObject body = new JSONObject();
				body.put("total", 0);
				body.put("dynamicList", new JSONArray());
				result.setBody(body);
				
				log.info("RelationDynamicController.getTeamUserDynamicList：查询班组成员动态列表成功");
				return result;
			}
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setUserCode(userCode); // 人员编号
			relationDynamicVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			relationDynamicVO.setUserCodeList(userCodeList); // 人员列表
			relationDynamicVO.setTeamCode(teamCode); // 班组编号
			
			// 查询
			PageBean pageBean = relationDynamicService.getTeamUserDynamicList(pageParam, relationDynamicVO);
			if (pageBean == null) {
				log.error("RelationDynamicController.getTeamUserDynamicList：查询失败");
				throw BusinessException.build("RELATION_19002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					RelationDynamicModel relationDynamicModel = (RelationDynamicModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("dynamicCode", StringTools.formatToString(relationDynamicModel.getDynamicCode()));
					jo.put("dynamicClass", StringTools.formatToString(relationDynamicModel.getDynamicClass()));
					jo.put("dynamicDepend", StringTools.formatToString(relationDynamicModel.getDynamicDepend()));
					
					// 获取动态所属人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(relationDynamicModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jo.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
					jo.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));

					// 操作信息
					if (!StringUtils.isBlank(relationDynamicModel.getOperateTeamCode())) {
						// 查询操作班组信息
						TeamInfo operateTeamCode = iTeamInfoService.selectByCode(relationDynamicModel.getOperateTeamCode());
						jo.put("operateTeamCode", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamCode()));
						jo.put("operateTeamName", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamName()));
					} else {
						jo.put("operateTeamCode", "");
						jo.put("operateTeamName", "");
					}
					if (!StringUtils.isBlank(relationDynamicModel.getOperateOrgCode())) {
						// 查询操作组织信息
						SysOrgInfo operateOrgInfo = iSysOrgService.selectByCode(relationDynamicModel.getOperateOrgCode());
						jo.put("operateOrgCode", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgCode()));
						jo.put("operateOrgName", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("operateOrgCode", "");
						jo.put("operateOrgName", "");
					}
					
					jo.put("operateTime", StringTools.formatToString(relationDynamicModel.getOperateTime()));
					jo.put("subjectCode", StringTools.formatToString(relationDynamicModel.getSubjectCode()));
					jo.put("subjectName", StringTools.formatToString(relationDynamicModel.getSubjectName()));
					jo.put("subjectUrlHonor", StringTools.formatToString(relationDynamicModel.getSubjectUrlHonor()));
					jo.put("subjectClass", StringTools.formatToString(relationDynamicModel.getSubjectClass()));
					
					// 显示内容信息
					if (RelationConstants.RELATION_YES.equals(relationDynamicModel.getIsSubjectShow())) {
						// 获取内容发布人信息
						SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSubjectUserCode());
						jo.put("subjectUserCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserCode()));
						jo.put("subjectUserName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserName()));
						jo.put("subjectUserPhotoUrl", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getHeadPortrait()));
						jo.put("subjectDepend", StringTools.formatToString(relationDynamicModel.getSubjectDepend()));
						if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSubjectDepend())) {
							// 获取内容所属班组信息
							TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSubjectTeamCode());
							jo.put("subjectTeamCode", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamCode()));
							jo.put("subjectTeamName", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamName()));
							SysOrgInfo subjectOrgCode = iSysOrgService.selectByCode(subjectTeamInfo == null ? "" : subjectTeamInfo.getOrgCode());
							jo.put("subjectOrgCode", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgCode()));
							jo.put("subjectOrgName", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgFullname()));
						} else {
							jo.put("subjectTeamCode", "");
							jo.put("subjectTeamName", "");
							jo.put("subjectOrgCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgCode()));
							jo.put("subjectOrgName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgFullname()));
						}
						
						jo.put("subjectPublishTime", StringTools.formatToString(relationDynamicModel.getSubjectPublishTime()));
						jo.put("subjectTitle", StringTools.formatToString(relationDynamicModel.getSubjectTitle()));
						jo.put("subjectDigest", StringTools.formatToString(relationDynamicModel.getSubjectDigest()));
						jo.put("subjectUrl", StringTools.formatToString(relationDynamicModel.getSubjectUrl()));
						jo.put("subjectSource", StringTools.formatToString(relationDynamicModel.getSubjectSource()));
						jo.put("sourceCode", StringTools.formatToString(relationDynamicModel.getSourceCode()));
						
						// 内容来源信息
						if (RelationConstants.RELATION_SUBJECT_SOURCE_FORWARD.equals(relationDynamicModel.getSubjectSource())) {
							// 转发微博被删除或者被屏蔽不需要查
							if (RelationConstants.RELATION_DYNAMIC_FORWARD_MBLOG.equals(relationDynamicModel.getDynamicClass())
									&& (RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsShield())
											|| RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsDelete()))) {
								jo.put("sourceUserCode", "");
								jo.put("sourceUserName", "");
								jo.put("sourceUserPhotoUrl", "");
								jo.put("sourceDepend", "");
								jo.put("sourceTeamCode", "");
								jo.put("sourceTeamName", "");
								jo.put("sourceOrgCode", "");
								jo.put("sourceOrgName", "");
								jo.put("sourcePublishTime", "");
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							} else {
								// 获取来源人员信息
								SysUserInfo sourceUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSourceUserCode());
								jo.put("sourceUserCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserCode()));
								jo.put("sourceUserName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserName()));
								jo.put("sourceUserPhotoUrl", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getHeadPortrait()));
								jo.put("sourceDepend", StringTools.formatToString(relationDynamicModel.getSourceDepend()));
								if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSourceDepend())) {
									// 获取来源班组信息
									TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSourceTeamCode());
									jo.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamCode()));
									jo.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
									// 获取来源组织信息
									SysOrgInfo sourceOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo == null ? "" : sourceTeamInfo.getOrgCode());
									jo.put("sourceOrgCode", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgCode()));
									jo.put("sourceOrgName", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgFullname()));
								} else {
									jo.put("sourceTeamCode", "");
									jo.put("sourceTeamName", "");
									jo.put("sourceOrgCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgCode()));
									jo.put("sourceOrgName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgFullname()));
								}
								
								jo.put("sourcePublishTime", StringTools.formatToString(relationDynamicModel.getSourcePublishTime()));
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							}
						} else {
							jo.put("sourceUserCode", "");
							jo.put("sourceUserName", "");
							jo.put("sourceUserPhotoUrl", "");
							jo.put("sourceDepend", "");
							jo.put("sourceTeamCode", "");
							jo.put("sourceTeamName", "");
							jo.put("sourceOrgCode", "");
							jo.put("sourceOrgName", "");
							jo.put("sourcePublishTime", "");
							jo.put("sourceIsShield", "");
							jo.put("sourceIsDelete", "");
						}
						
						jo.put("contentTips", StringTools.formatToString(relationDynamicModel.getContentTips()));
						jo.put("subjectBeginTime", StringTools.formatToString(relationDynamicModel.getSubjectBeginTime()));
						jo.put("subjectEndTime", StringTools.formatToString(relationDynamicModel.getSubjectEndTime()));
						jo.put("teamQualityNum", StringTools.formatToString(relationDynamicModel.getTeamQualityNum()));
						jo.put("groupQualityNum", StringTools.formatToString(relationDynamicModel.getGroupQualityNum()));
						jo.put("provinceQualityNum", StringTools.formatToString(relationDynamicModel.getProvinceQualityNum()));
						jo.put("cityQualityNum", StringTools.formatToString(relationDynamicModel.getCityQualityNum()));
						jo.put("joinNum", StringTools.formatToString(relationDynamicModel.getJoinNum()));
						jo.put("readNum", StringTools.formatToString(relationDynamicModel.getReadNum()));
						jo.put("likeNum", StringTools.formatToString(relationDynamicModel.getLikeNum()));
						jo.put("collectNum", StringTools.formatToString(relationDynamicModel.getCollectNum()));
						jo.put("forwardNum", StringTools.formatToString(relationDynamicModel.getForwardNum()));
						jo.put("commentNum", StringTools.formatToString(relationDynamicModel.getCommentNum()));
						jo.put("shareNum", StringTools.formatToString(relationDynamicModel.getShareNum()));
					} else {
						jo.put("subjectUserCode", "");
						jo.put("subjectUserName", "");
						jo.put("subjectUserPhotoUrl", "");
						jo.put("subjectDepend", "");
						jo.put("subjectTeamCode", "");
						jo.put("subjectOrgCode", "");
						jo.put("subjectOrgName", "");
						jo.put("subjectPublishTime", "");
						jo.put("subjectTitle", "");
						jo.put("subjectDigest", "");
						jo.put("subjectUrl", "");
						jo.put("subjectSource", "");
						jo.put("sourceCode", "");
						jo.put("sourceUserCode", "");
						jo.put("sourceUserName", "");
						jo.put("sourceUserPhotoUrl", "");
						jo.put("sourceDepend", "");
						jo.put("sourceTeamCode", "");
						jo.put("sourceTeamName", "");
						jo.put("sourceOrgCode", "");
						jo.put("sourceOrgName", "");
						jo.put("sourcePublishTime", "");
						jo.put("sourceIsShield", "");
						jo.put("sourceIsDelete", "");
						jo.put("contentTips", "");
						jo.put("subjectBeginTime", "");
						jo.put("subjectEndTime", "");
						jo.put("teamQualityNum", "");
						jo.put("groupQualityNum", "");
						jo.put("provinceQualityNum", "");
						jo.put("cityQualityNum", "");
						jo.put("joinNum", "");
						jo.put("readNum", "");
						jo.put("likeNum", "");
						jo.put("collectNum", "");
						jo.put("forwardNum", "");
						jo.put("commentNum", "");
						jo.put("shareNum", "");
					}
					
					// 网盘所属组织
					if (RelationConstants.RELATION_DYNAMIC_OD.equals(relationDynamicModel.getDynamicClass())) {
						jo.put("diskOrgCode", StringTools.formatToString(relationDynamicModel.getDiskOrgCode()));
					} else {
						jo.put("diskOrgCode", "");
					}
					
					jo.put("isLike", StringTools.formatToString(relationDynamicModel.getIsLike()));
					jo.put("isCollect", StringTools.formatToString(relationDynamicModel.getIsCollect()));
					jo.put("teamFrontShow", relationDynamicModel.getTeamFrontShow() == null ? RelationConstants.RELATION_NO.toString() : StringTools.formatToString(relationDynamicModel.getTeamFrontShow()));
					jo.put("teamQuality", StringTools.formatToString(relationDynamicModel.getTeamQuality()));
					
					// 内容资源
					List<RelationSubjectResourceModel> resourceList = relationDynamicModel.getResourceList();
					JSONArray resList = new JSONArray();
					if (resourceList != null && !resourceList.isEmpty()) {
						for (RelationSubjectResourceModel resource : resourceList) {
							JSONObject res = new JSONObject();
							res.put("resCode", StringTools.formatToString(resource.getSubjectResCode()));
							res.put("resUrl", StringTools.formatToString(resource.getResUrl()));
							res.put("resClass", StringTools.formatToString(resource.getResClass()));
							resList.add(res);
						}
					}
					jo.put("resList", resList);
					
					// 添加
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("dynamicList", ja);
			result.setBody(body);
			
			log.info("RelationDynamicController.getTeamUserDynamicList：查询班组成员动态列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationDynamicController.getTeamUserDynamicList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getPortalUserDynamicList  
	 * @Description: 查询门户成员动态列表
	 * @param getPortalUserDynamicListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getPortalUserDynamicList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getPortalUserDynamicList(@RequireValid GetPortalUserDynamicListVO getPortalUserDynamicListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationDynamicController.getPortalUserDynamicList：查询门户成员动态列表");
			
			// 获取参数
			String pageSize = getPortalUserDynamicListVO.getPageSize(); // 每页条数
			String pageNo = getPortalUserDynamicListVO.getPageNo(); // 页码
			String queryTime = getPortalUserDynamicListVO.getQueryTime(); // 首次查询时间
			String userCode = getPortalUserDynamicListVO.getUserCode(); // 人员编号
			String orgCode = getPortalUserDynamicListVO.getOrgCode(); // 门户编号
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setUserCode(userCode); // 人员编号
			relationDynamicVO.setOrgCode(orgCode); // 门户编号
			relationDynamicVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			
			// 查询
			PageBean pageBean = relationDynamicService.getPortalUserDynamicList(pageParam, relationDynamicVO);
			if (pageBean == null) {
				log.error("RelationDynamicController.getPortalUserDynamicList：查询失败");
				throw BusinessException.build("RELATION_19002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					RelationDynamicModel relationDynamicModel = (RelationDynamicModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("dynamicCode", StringTools.formatToString(relationDynamicModel.getDynamicCode()));
					jo.put("dynamicClass", StringTools.formatToString(relationDynamicModel.getDynamicClass()));
					jo.put("dynamicDepend", StringTools.formatToString(relationDynamicModel.getDynamicDepend()));
					
					// 获取动态所属人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(relationDynamicModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jo.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
					jo.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
					
					// 操作信息
					if (!StringUtils.isBlank(relationDynamicModel.getOperateTeamCode())) {
						// 查询操作班组信息
						TeamInfo operateTeamCode = iTeamInfoService.selectByCode(relationDynamicModel.getOperateTeamCode());
						jo.put("operateTeamCode", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamCode()));
						jo.put("operateTeamName", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamName()));
					} else {
						jo.put("operateTeamCode", "");
						jo.put("operateTeamName", "");
					}
					if (!StringUtils.isBlank(relationDynamicModel.getOperateOrgCode())) {
						// 查询操作组织信息
						SysOrgInfo operateOrgInfo = iSysOrgService.selectByCode(relationDynamicModel.getOperateOrgCode());
						jo.put("operateOrgCode", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgCode()));
						jo.put("operateOrgName", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("operateOrgCode", "");
						jo.put("operateOrgName", "");
					}
					
					jo.put("operateTime", StringTools.formatToString(relationDynamicModel.getOperateTime()));
					jo.put("subjectCode", StringTools.formatToString(relationDynamicModel.getSubjectCode()));
					jo.put("subjectName", StringTools.formatToString(relationDynamicModel.getSubjectName()));
					jo.put("subjectUrlHonor", StringTools.formatToString(relationDynamicModel.getSubjectUrlHonor()));
					jo.put("subjectClass", StringTools.formatToString(relationDynamicModel.getSubjectClass()));
					
					// 显示内容信息
					if (RelationConstants.RELATION_YES.equals(relationDynamicModel.getIsSubjectShow())) {
						// 获取内容发布人信息
						SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSubjectUserCode());
						jo.put("subjectUserCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserCode()));
						jo.put("subjectUserName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserName()));
						jo.put("subjectUserPhotoUrl", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getHeadPortrait()));
						jo.put("subjectDepend", StringTools.formatToString(relationDynamicModel.getSubjectDepend()));
						if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSubjectDepend())) {
							// 获取内容所属班组信息
							TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSubjectTeamCode());
							jo.put("subjectTeamCode", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamCode()));
							jo.put("subjectTeamName", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamName()));
							SysOrgInfo subjectOrgCode = iSysOrgService.selectByCode(subjectTeamInfo == null ? "" : subjectTeamInfo.getOrgCode());
							jo.put("subjectOrgCode", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgCode()));
							jo.put("subjectOrgName", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgFullname()));
						} else {
							jo.put("subjectTeamCode", "");
							jo.put("subjectTeamName", "");
							jo.put("subjectOrgCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgCode()));
							jo.put("subjectOrgName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgFullname()));
						}
						
						jo.put("subjectPublishTime", StringTools.formatToString(relationDynamicModel.getSubjectPublishTime()));
						jo.put("subjectTitle", StringTools.formatToString(relationDynamicModel.getSubjectTitle()));
						jo.put("subjectDigest", StringTools.formatToString(relationDynamicModel.getSubjectDigest()));
						jo.put("subjectUrl", StringTools.formatToString(relationDynamicModel.getSubjectUrl()));
						jo.put("subjectSource", StringTools.formatToString(relationDynamicModel.getSubjectSource()));
						jo.put("sourceCode", StringTools.formatToString(relationDynamicModel.getSourceCode()));
						
						// 内容来源信息
						if (RelationConstants.RELATION_SUBJECT_SOURCE_FORWARD.equals(relationDynamicModel.getSubjectSource())) {
							// 转发微博被删除或者被屏蔽不需要查
							if (RelationConstants.RELATION_DYNAMIC_FORWARD_MBLOG.equals(relationDynamicModel.getDynamicClass())
									&& (RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsShield())
											|| RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsDelete()))) {
								jo.put("sourceUserCode", "");
								jo.put("sourceUserName", "");
								jo.put("sourceUserPhotoUrl", "");
								jo.put("sourceDepend", "");
								jo.put("sourceTeamCode", "");
								jo.put("sourceTeamName", "");
								jo.put("sourceOrgCode", "");
								jo.put("sourceOrgName", "");
								jo.put("sourcePublishTime", "");
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							} else {
								// 获取来源人员信息
								SysUserInfo sourceUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSourceUserCode());
								jo.put("sourceUserCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserCode()));
								jo.put("sourceUserName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserName()));
								jo.put("sourceUserPhotoUrl", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getHeadPortrait()));
								jo.put("sourceDepend", StringTools.formatToString(relationDynamicModel.getSourceDepend()));
								if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSourceDepend())) {
									// 获取来源班组信息
									TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSourceTeamCode());
									jo.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamCode()));
									jo.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
									// 获取来源组织信息
									SysOrgInfo sourceOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo == null ? "" : sourceTeamInfo.getOrgCode());
									jo.put("sourceOrgCode", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgCode()));
									jo.put("sourceOrgName", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgFullname()));
								} else {
									jo.put("sourceTeamCode", "");
									jo.put("sourceTeamName", "");
									jo.put("sourceOrgCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgCode()));
									jo.put("sourceOrgName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgFullname()));
								}
								
								jo.put("sourcePublishTime", StringTools.formatToString(relationDynamicModel.getSourcePublishTime()));
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							}
						} else {
							jo.put("sourceUserCode", "");
							jo.put("sourceUserName", "");
							jo.put("sourceUserPhotoUrl", "");
							jo.put("sourceDepend", "");
							jo.put("sourceTeamCode", "");
							jo.put("sourceTeamName", "");
							jo.put("sourceOrgCode", "");
							jo.put("sourceOrgName", "");
							jo.put("sourcePublishTime", "");
							jo.put("sourceIsShield", "");
							jo.put("sourceIsDelete", "");
						}
						
						jo.put("contentTips", StringTools.formatToString(relationDynamicModel.getContentTips()));
						jo.put("subjectBeginTime", StringTools.formatToString(relationDynamicModel.getSubjectBeginTime()));
						jo.put("subjectEndTime", StringTools.formatToString(relationDynamicModel.getSubjectEndTime()));
						jo.put("teamQualityNum", StringTools.formatToString(relationDynamicModel.getTeamQualityNum()));
						jo.put("groupQualityNum", StringTools.formatToString(relationDynamicModel.getGroupQualityNum()));
						jo.put("provinceQualityNum", StringTools.formatToString(relationDynamicModel.getProvinceQualityNum()));
						jo.put("cityQualityNum", StringTools.formatToString(relationDynamicModel.getCityQualityNum()));
						jo.put("joinNum", StringTools.formatToString(relationDynamicModel.getJoinNum()));
						jo.put("readNum", StringTools.formatToString(relationDynamicModel.getReadNum()));
						jo.put("likeNum", StringTools.formatToString(relationDynamicModel.getLikeNum()));
						jo.put("collectNum", StringTools.formatToString(relationDynamicModel.getCollectNum()));
						jo.put("forwardNum", StringTools.formatToString(relationDynamicModel.getForwardNum()));
						jo.put("commentNum", StringTools.formatToString(relationDynamicModel.getCommentNum()));
						jo.put("shareNum", StringTools.formatToString(relationDynamicModel.getShareNum()));
					} else {
						jo.put("subjectUserCode", "");
						jo.put("subjectUserName", "");
						jo.put("subjectUserPhotoUrl", "");
						jo.put("subjectDepend", "");
						jo.put("subjectTeamCode", "");
						jo.put("subjectOrgCode", "");
						jo.put("subjectOrgName", "");
						jo.put("subjectPublishTime", "");
						jo.put("subjectTitle", "");
						jo.put("subjectDigest", "");
						jo.put("subjectUrl", "");
						jo.put("subjectSource", "");
						jo.put("sourceCode", "");
						jo.put("sourceUserCode", "");
						jo.put("sourceUserName", "");
						jo.put("sourceUserPhotoUrl", "");
						jo.put("sourceDepend", "");
						jo.put("sourceTeamCode", "");
						jo.put("sourceTeamName", "");
						jo.put("sourceOrgCode", "");
						jo.put("sourceOrgName", "");
						jo.put("sourcePublishTime", "");
						jo.put("sourceIsShield", "");
						jo.put("sourceIsDelete", "");
						jo.put("contentTips", "");
						jo.put("subjectBeginTime", "");
						jo.put("subjectEndTime", "");
						jo.put("teamQualityNum", "");
						jo.put("groupQualityNum", "");
						jo.put("provinceQualityNum", "");
						jo.put("cityQualityNum", "");
						jo.put("joinNum", "");
						jo.put("readNum", "");
						jo.put("likeNum", "");
						jo.put("collectNum", "");
						jo.put("forwardNum", "");
						jo.put("commentNum", "");
						jo.put("shareNum", "");
					}
					
					// 网盘所属组织
					if (RelationConstants.RELATION_DYNAMIC_OD.equals(relationDynamicModel.getDynamicClass())) {
						jo.put("diskOrgCode", StringTools.formatToString(relationDynamicModel.getDiskOrgCode()));
					} else {
						jo.put("diskOrgCode", "");
					}
					
					jo.put("isLike", StringTools.formatToString(relationDynamicModel.getIsLike()));
					jo.put("isCollect", StringTools.formatToString(relationDynamicModel.getIsCollect()));
					
					// 内容资源
					List<RelationSubjectResourceModel> resourceList = relationDynamicModel.getResourceList();
					JSONArray resList = new JSONArray();
					if (resourceList != null && !resourceList.isEmpty()) {
						for (RelationSubjectResourceModel resource : resourceList) {
							JSONObject res = new JSONObject();
							res.put("resCode", StringTools.formatToString(resource.getSubjectResCode()));
							res.put("resUrl", StringTools.formatToString(resource.getResUrl()));
							res.put("resClass", StringTools.formatToString(resource.getResClass()));
							resList.add(res);
						}
					}
					jo.put("resList", resList);
					
					// 添加
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("dynamicList", ja);
			result.setBody(body);
			
			log.info("RelationDynamicController.getPortalUserDynamicList：查询门户成员动态列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationDynamicController.getPortalUserDynamicList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getPortalTeamDynamicList  
	 * @Description: 查询门户班组动态列表
	 * @param getPortalTeamDynamicListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getPortalTeamDynamicList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getPortalTeamDynamicList(@RequireValid GetPortalTeamDynamicListVO getPortalTeamDynamicListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationDynamicController.getPortalTeamDynamicList：查询门户班组动态列表");
			
			// 获取参数
			String pageSize = getPortalTeamDynamicListVO.getPageSize(); // 每页条数
			String pageNo = getPortalTeamDynamicListVO.getPageNo(); // 页码
			String queryTime = getPortalTeamDynamicListVO.getQueryTime(); // 首次查询时间
			String userCode = getPortalTeamDynamicListVO.getUserCode(); // 人员编号
			String orgCode = getPortalTeamDynamicListVO.getOrgCode(); // 门户编号
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setUserCode(userCode); // 人员编号
			relationDynamicVO.setOrgCode(orgCode); // 门户编号
			relationDynamicVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			
			// 查询
			PageBean pageBean = relationDynamicService.getPortalTeamDynamicList(pageParam, relationDynamicVO);
			if (pageBean == null) {
				log.error("RelationDynamicController.getPortalTeamDynamicList：查询失败");
				throw BusinessException.build("RELATION_19002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					RelationDynamicModel relationDynamicModel = (RelationDynamicModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("dynamicCode", StringTools.formatToString(relationDynamicModel.getDynamicCode()));
					jo.put("dynamicClass", StringTools.formatToString(relationDynamicModel.getDynamicClass()));
					
					// 获取动态所属人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(relationDynamicModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jo.put("dynamicDepend", StringTools.formatToString(relationDynamicModel.getDynamicDepend()));
					if (RelationConstants.RELATION_DYNAMIC_DEPEND_TEAM.equals(relationDynamicModel.getDynamicDepend())) {
						// 获取动态所属班组信息
						TeamInfo teamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getTeamCode());
						jo.put("teamCode", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamCode()));
						jo.put("teamName", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamName()));
						jo.put("teamPhotoUrl",  teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamIcon()));
						// 获取动态所属门户信息
						SysOrgInfo sysOrgInfo = iSysOrgService.selectByCode(teamInfo == null ? "" : teamInfo.getOrgCode());
						jo.put("orgCode", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
						jo.put("orgName", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("teamCode", "");
						jo.put("teamName", "");
						jo.put("teamPhotoUrl", "");
						jo.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
						jo.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
					}

					// 操作信息
					if (!StringUtils.isBlank(relationDynamicModel.getOperateTeamCode())) {
						// 查询操作班组信息
						TeamInfo operateTeamCode = iTeamInfoService.selectByCode(relationDynamicModel.getOperateTeamCode());
						jo.put("operateTeamCode", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamCode()));
						jo.put("operateTeamName", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamName()));
					} else {
						jo.put("operateTeamCode", "");
						jo.put("operateTeamName", "");
					}
					if (!StringUtils.isBlank(relationDynamicModel.getOperateOrgCode())) {
						// 查询操作组织信息
						SysOrgInfo operateOrgInfo = iSysOrgService.selectByCode(relationDynamicModel.getOperateOrgCode());
						jo.put("operateOrgCode", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgCode()));
						jo.put("operateOrgName", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("operateOrgCode", "");
						jo.put("operateOrgName", "");
					}
					
					jo.put("operateTime", StringTools.formatToString(relationDynamicModel.getOperateTime()));
					jo.put("subjectCode", StringTools.formatToString(relationDynamicModel.getSubjectCode()));
					jo.put("subjectName", StringTools.formatToString(relationDynamicModel.getSubjectName()));
					jo.put("subjectUrlHonor", StringTools.formatToString(relationDynamicModel.getSubjectUrlHonor()));
					jo.put("subjectClass", StringTools.formatToString(relationDynamicModel.getSubjectClass()));
					
					// 显示内容信息
					if (RelationConstants.RELATION_YES.equals(relationDynamicModel.getIsSubjectShow())) {
						// 获取内容发布人信息
						SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSubjectUserCode());
						jo.put("subjectUserCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserCode()));
						jo.put("subjectUserName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserName()));
						jo.put("subjectUserPhotoUrl", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getHeadPortrait()));
						jo.put("subjectDepend", StringTools.formatToString(relationDynamicModel.getSubjectDepend()));
						if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSubjectDepend())) {
							// 获取内容所属班组信息
							TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSubjectTeamCode());
							jo.put("subjectTeamCode", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamCode()));
							jo.put("subjectTeamName", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamName()));
							SysOrgInfo subjectOrgCode = iSysOrgService.selectByCode(subjectTeamInfo == null ? "" : subjectTeamInfo.getOrgCode());
							jo.put("subjectOrgCode", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgCode()));
							jo.put("subjectOrgName", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgFullname()));
						} else {
							jo.put("subjectTeamCode", "");
							jo.put("subjectTeamName", "");
							jo.put("subjectOrgCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgCode()));
							jo.put("subjectOrgName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgFullname()));
						}
						
						jo.put("subjectPublishTime", StringTools.formatToString(relationDynamicModel.getSubjectPublishTime()));
						jo.put("subjectTitle", StringTools.formatToString(relationDynamicModel.getSubjectTitle()));
						jo.put("subjectDigest", StringTools.formatToString(relationDynamicModel.getSubjectDigest()));
						jo.put("subjectUrl", StringTools.formatToString(relationDynamicModel.getSubjectUrl()));
						jo.put("subjectSource", StringTools.formatToString(relationDynamicModel.getSubjectSource()));
						jo.put("sourceCode", StringTools.formatToString(relationDynamicModel.getSourceCode()));
						
						// 内容来源信息
						if (RelationConstants.RELATION_SUBJECT_SOURCE_FORWARD.equals(relationDynamicModel.getSubjectSource())) {
							// 转发微博被删除或者被屏蔽不需要查
							if (RelationConstants.RELATION_DYNAMIC_FORWARD_MBLOG.equals(relationDynamicModel.getDynamicClass())
									&& (RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsShield())
											|| RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsDelete()))) {
								jo.put("sourceUserCode", "");
								jo.put("sourceUserName", "");
								jo.put("sourceUserPhotoUrl", "");
								jo.put("sourceDepend", "");
								jo.put("sourceTeamCode", "");
								jo.put("sourceTeamName", "");
								jo.put("sourceOrgCode", "");
								jo.put("sourceOrgName", "");
								jo.put("sourcePublishTime", "");
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							} else {
								// 获取来源人员信息
								SysUserInfo sourceUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSourceUserCode());
								jo.put("sourceUserCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserCode()));
								jo.put("sourceUserName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserName()));
								jo.put("sourceUserPhotoUrl", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getHeadPortrait()));
								jo.put("sourceDepend", StringTools.formatToString(relationDynamicModel.getSourceDepend()));
								if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSourceDepend())) {
									// 获取来源班组信息
									TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSourceTeamCode());
									jo.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamCode()));
									jo.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
									// 获取来源组织信息
									SysOrgInfo sourceOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo == null ? "" : sourceTeamInfo.getOrgCode());
									jo.put("sourceOrgCode", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgCode()));
									jo.put("sourceOrgName", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgFullname()));
								} else {
									jo.put("sourceTeamCode", "");
									jo.put("sourceTeamName", "");
									jo.put("sourceOrgCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgCode()));
									jo.put("sourceOrgName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgFullname()));
								}
								
								jo.put("sourcePublishTime", StringTools.formatToString(relationDynamicModel.getSourcePublishTime()));
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							}
						} else {
							jo.put("sourceUserCode", "");
							jo.put("sourceUserName", "");
							jo.put("sourceUserPhotoUrl", "");
							jo.put("sourceDepend", "");
							jo.put("sourceTeamCode", "");
							jo.put("sourceTeamName", "");
							jo.put("sourceOrgCode", "");
							jo.put("sourceOrgName", "");
							jo.put("sourcePublishTime", "");
							jo.put("sourceIsShield", "");
							jo.put("sourceIsDelete", "");
						}
						
						jo.put("contentTips", StringTools.formatToString(relationDynamicModel.getContentTips()));
						jo.put("subjectBeginTime", StringTools.formatToString(relationDynamicModel.getSubjectBeginTime()));
						jo.put("subjectEndTime", StringTools.formatToString(relationDynamicModel.getSubjectEndTime()));
						jo.put("teamQualityNum", StringTools.formatToString(relationDynamicModel.getTeamQualityNum()));
						jo.put("groupQualityNum", StringTools.formatToString(relationDynamicModel.getGroupQualityNum()));
						jo.put("provinceQualityNum", StringTools.formatToString(relationDynamicModel.getProvinceQualityNum()));
						jo.put("cityQualityNum", StringTools.formatToString(relationDynamicModel.getCityQualityNum()));
						jo.put("joinNum", StringTools.formatToString(relationDynamicModel.getJoinNum()));
						jo.put("readNum", StringTools.formatToString(relationDynamicModel.getReadNum()));
						jo.put("likeNum", StringTools.formatToString(relationDynamicModel.getLikeNum()));
						jo.put("collectNum", StringTools.formatToString(relationDynamicModel.getCollectNum()));
						jo.put("forwardNum", StringTools.formatToString(relationDynamicModel.getForwardNum()));
						jo.put("commentNum", StringTools.formatToString(relationDynamicModel.getCommentNum()));
						jo.put("shareNum", StringTools.formatToString(relationDynamicModel.getShareNum()));
					} else {
						jo.put("subjectUserCode", "");
						jo.put("subjectUserName", "");
						jo.put("subjectUserPhotoUrl", "");
						jo.put("subjectDepend", "");
						jo.put("subjectTeamCode", "");
						jo.put("subjectOrgCode", "");
						jo.put("subjectOrgName", "");
						jo.put("subjectPublishTime", "");
						jo.put("subjectTitle", "");
						jo.put("subjectDigest", "");
						jo.put("subjectUrl", "");
						jo.put("subjectSource", "");
						jo.put("sourceCode", "");
						jo.put("sourceUserCode", "");
						jo.put("sourceUserName", "");
						jo.put("sourceUserPhotoUrl", "");
						jo.put("sourceDepend", "");
						jo.put("sourceTeamCode", "");
						jo.put("sourceTeamName", "");
						jo.put("sourceOrgCode", "");
						jo.put("sourceOrgName", "");
						jo.put("sourcePublishTime", "");
						jo.put("sourceIsShield", "");
						jo.put("sourceIsDelete", "");
						jo.put("contentTips", "");
						jo.put("subjectBeginTime", "");
						jo.put("subjectEndTime", "");
						jo.put("teamQualityNum", "");
						jo.put("groupQualityNum", "");
						jo.put("provinceQualityNum", "");
						jo.put("cityQualityNum", "");
						jo.put("joinNum", "");
						jo.put("readNum", "");
						jo.put("likeNum", "");
						jo.put("collectNum", "");
						jo.put("forwardNum", "");
						jo.put("commentNum", "");
						jo.put("shareNum", "");
					}
					
					// 网盘所属组织
					if (RelationConstants.RELATION_DYNAMIC_OD.equals(relationDynamicModel.getDynamicClass())) {
						jo.put("diskOrgCode", StringTools.formatToString(relationDynamicModel.getDiskOrgCode()));
					} else {
						jo.put("diskOrgCode", "");
					}
					
					jo.put("isLike", StringTools.formatToString(relationDynamicModel.getIsLike()));
					jo.put("isCollect", StringTools.formatToString(relationDynamicModel.getIsCollect()));
					
					// 内容资源
					List<RelationSubjectResourceModel> resourceList = relationDynamicModel.getResourceList();
					JSONArray resList = new JSONArray();
					if (resourceList != null && !resourceList.isEmpty()) {
						for (RelationSubjectResourceModel resource : resourceList) {
							JSONObject res = new JSONObject();
							res.put("resCode", StringTools.formatToString(resource.getSubjectResCode()));
							res.put("resUrl", StringTools.formatToString(resource.getResUrl()));
							res.put("resClass", StringTools.formatToString(resource.getResClass()));
							resList.add(res);
						}
					}
					jo.put("resList", resList);
					
					// 添加
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("dynamicList", ja);
			result.setBody(body);
			
			log.info("RelationDynamicController.getPortalTeamDynamicList：查询门户班组动态列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationDynamicController.getPortalTeamDynamicList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getPortalTopTeamDynamicList  
	 * @Description: 查询门户1号班组动态列表
	 * @param getPortalTopTeamDynamicListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getPortalTopTeamDynamicList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getPortalTopTeamDynamicList(@RequireValid GetPortalTopTeamDynamicListVO getPortalTopTeamDynamicListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationDynamicController.getPortalTopTeamDynamicList：查询门户1号班组动态列表");
			
			// 获取参数
			String pageSize = getPortalTopTeamDynamicListVO.getPageSize(); // 每页条数
			String pageNo = getPortalTopTeamDynamicListVO.getPageNo(); // 页码
			String queryTime = getPortalTopTeamDynamicListVO.getQueryTime(); // 首次查询时间
			String userCode = getPortalTopTeamDynamicListVO.getUserCode(); // 人员编号
			String orgCode = getPortalTopTeamDynamicListVO.getOrgCode(); // 门户编号
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setUserCode(userCode); // 人员编号
			relationDynamicVO.setOrgCode(orgCode); // 门户编号
			relationDynamicVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			
			// 查询
			PageBean pageBean = relationDynamicService.getPortalTopTeamDynamicList(pageParam, relationDynamicVO);
			if (pageBean == null) {
				log.error("RelationDynamicController.getPortalTopTeamDynamicList：查询失败");
				throw BusinessException.build("RELATION_19002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					RelationDynamicModel relationDynamicModel = (RelationDynamicModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("dynamicCode", StringTools.formatToString(relationDynamicModel.getDynamicCode()));
					jo.put("dynamicClass", StringTools.formatToString(relationDynamicModel.getDynamicClass()));
					
					// 获取动态所属人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(relationDynamicModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jo.put("dynamicDepend", StringTools.formatToString(relationDynamicModel.getDynamicDepend()));
					if (RelationConstants.RELATION_DYNAMIC_DEPEND_TEAM.equals(relationDynamicModel.getDynamicDepend())) {
						// 获取动态所属班组信息
						TeamInfo teamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getTeamCode());
						jo.put("teamCode", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamCode()));
						jo.put("teamName", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamName()));
						jo.put("teamPhotoUrl",  teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamIcon()));
						// 获取动态所属门户信息
						SysOrgInfo sysOrgInfo = iSysOrgService.selectByCode(teamInfo == null ? "" : teamInfo.getOrgCode());
						jo.put("orgCode", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
						jo.put("orgName", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("teamCode", "");
						jo.put("teamName", "");
						jo.put("teamPhotoUrl", "");
						jo.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
						jo.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
					}
					
					// 操作信息
					if (!StringUtils.isBlank(relationDynamicModel.getOperateTeamCode())) {
						// 查询操作班组信息
						TeamInfo operateTeamCode = iTeamInfoService.selectByCode(relationDynamicModel.getOperateTeamCode());
						jo.put("operateTeamCode", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamCode()));
						jo.put("operateTeamName", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamName()));
					} else {
						jo.put("operateTeamCode", "");
						jo.put("operateTeamName", "");
					}
					if (!StringUtils.isBlank(relationDynamicModel.getOperateOrgCode())) {
						// 查询操作组织信息
						SysOrgInfo operateOrgInfo = iSysOrgService.selectByCode(relationDynamicModel.getOperateOrgCode());
						jo.put("operateOrgCode", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgCode()));
						jo.put("operateOrgName", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("operateOrgCode", "");
						jo.put("operateOrgName", "");
					}
					
					jo.put("operateTime", StringTools.formatToString(relationDynamicModel.getOperateTime()));
					jo.put("subjectCode", StringTools.formatToString(relationDynamicModel.getSubjectCode()));
					jo.put("subjectName", StringTools.formatToString(relationDynamicModel.getSubjectName()));
					jo.put("subjectUrlHonor", StringTools.formatToString(relationDynamicModel.getSubjectUrlHonor()));
					jo.put("subjectClass", StringTools.formatToString(relationDynamicModel.getSubjectClass()));
					
					// 显示内容信息
					if (RelationConstants.RELATION_YES.equals(relationDynamicModel.getIsSubjectShow())) {
						// 获取内容发布人信息
						SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSubjectUserCode());
						jo.put("subjectUserCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserCode()));
						jo.put("subjectUserName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserName()));
						jo.put("subjectUserPhotoUrl", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getHeadPortrait()));
						jo.put("subjectDepend", StringTools.formatToString(relationDynamicModel.getSubjectDepend()));
						if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSubjectDepend())) {
							// 获取内容所属班组信息
							TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSubjectTeamCode());
							jo.put("subjectTeamCode", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamCode()));
							jo.put("subjectTeamName", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamName()));
							SysOrgInfo subjectOrgCode = iSysOrgService.selectByCode(subjectTeamInfo == null ? "" : subjectTeamInfo.getOrgCode());
							jo.put("subjectOrgCode", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgCode()));
							jo.put("subjectOrgName", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgFullname()));
						} else {
							jo.put("subjectTeamCode", "");
							jo.put("subjectTeamName", "");
							jo.put("subjectOrgCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgCode()));
							jo.put("subjectOrgName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgFullname()));
						}
						
						jo.put("subjectPublishTime", StringTools.formatToString(relationDynamicModel.getSubjectPublishTime()));
						jo.put("subjectTitle", StringTools.formatToString(relationDynamicModel.getSubjectTitle()));
						jo.put("subjectDigest", StringTools.formatToString(relationDynamicModel.getSubjectDigest()));
						jo.put("subjectUrl", StringTools.formatToString(relationDynamicModel.getSubjectUrl()));
						jo.put("subjectSource", StringTools.formatToString(relationDynamicModel.getSubjectSource()));
						jo.put("sourceCode", StringTools.formatToString(relationDynamicModel.getSourceCode()));
						
						// 内容来源信息
						if (RelationConstants.RELATION_SUBJECT_SOURCE_FORWARD.equals(relationDynamicModel.getSubjectSource())) {
							// 转发微博被删除或者被屏蔽不需要查
							if (RelationConstants.RELATION_DYNAMIC_FORWARD_MBLOG.equals(relationDynamicModel.getDynamicClass())
									&& (RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsShield())
											|| RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsDelete()))) {
								jo.put("sourceUserCode", "");
								jo.put("sourceUserName", "");
								jo.put("sourceUserPhotoUrl", "");
								jo.put("sourceDepend", "");
								jo.put("sourceTeamCode", "");
								jo.put("sourceTeamName", "");
								jo.put("sourceOrgCode", "");
								jo.put("sourceOrgName", "");
								jo.put("sourcePublishTime", "");
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							} else {
								// 获取来源人员信息
								SysUserInfo sourceUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSourceUserCode());
								jo.put("sourceUserCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserCode()));
								jo.put("sourceUserName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserName()));
								jo.put("sourceUserPhotoUrl", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getHeadPortrait()));
								jo.put("sourceDepend", StringTools.formatToString(relationDynamicModel.getSourceDepend()));
								if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSourceDepend())) {
									// 获取来源班组信息
									TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSourceTeamCode());
									jo.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamCode()));
									jo.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
									// 获取来源组织信息
									SysOrgInfo sourceOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo == null ? "" : sourceTeamInfo.getOrgCode());
									jo.put("sourceOrgCode", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgCode()));
									jo.put("sourceOrgName", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgFullname()));
								} else {
									jo.put("sourceTeamCode", "");
									jo.put("sourceTeamName", "");
									jo.put("sourceOrgCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgCode()));
									jo.put("sourceOrgName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgFullname()));
								}
								
								jo.put("sourcePublishTime", StringTools.formatToString(relationDynamicModel.getSourcePublishTime()));
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							}
						} else {
							jo.put("sourceUserCode", "");
							jo.put("sourceUserName", "");
							jo.put("sourceUserPhotoUrl", "");
							jo.put("sourceDepend", "");
							jo.put("sourceTeamCode", "");
							jo.put("sourceTeamName", "");
							jo.put("sourceOrgCode", "");
							jo.put("sourceOrgName", "");
							jo.put("sourcePublishTime", "");
							jo.put("sourceIsShield", "");
							jo.put("sourceIsDelete", "");
						}
						
						jo.put("contentTips", StringTools.formatToString(relationDynamicModel.getContentTips()));
						jo.put("subjectBeginTime", StringTools.formatToString(relationDynamicModel.getSubjectBeginTime()));
						jo.put("subjectEndTime", StringTools.formatToString(relationDynamicModel.getSubjectEndTime()));
						jo.put("teamQualityNum", StringTools.formatToString(relationDynamicModel.getTeamQualityNum()));
						jo.put("groupQualityNum", StringTools.formatToString(relationDynamicModel.getGroupQualityNum()));
						jo.put("provinceQualityNum", StringTools.formatToString(relationDynamicModel.getProvinceQualityNum()));
						jo.put("cityQualityNum", StringTools.formatToString(relationDynamicModel.getCityQualityNum()));
						jo.put("joinNum", StringTools.formatToString(relationDynamicModel.getJoinNum()));
						jo.put("readNum", StringTools.formatToString(relationDynamicModel.getReadNum()));
						jo.put("likeNum", StringTools.formatToString(relationDynamicModel.getLikeNum()));
						jo.put("collectNum", StringTools.formatToString(relationDynamicModel.getCollectNum()));
						jo.put("forwardNum", StringTools.formatToString(relationDynamicModel.getForwardNum()));
						jo.put("commentNum", StringTools.formatToString(relationDynamicModel.getCommentNum()));
						jo.put("shareNum", StringTools.formatToString(relationDynamicModel.getShareNum()));
					} else {
						jo.put("subjectUserCode", "");
						jo.put("subjectUserName", "");
						jo.put("subjectUserPhotoUrl", "");
						jo.put("subjectDepend", "");
						jo.put("subjectTeamCode", "");
						jo.put("subjectOrgCode", "");
						jo.put("subjectOrgName", "");
						jo.put("subjectPublishTime", "");
						jo.put("subjectTitle", "");
						jo.put("subjectDigest", "");
						jo.put("subjectUrl", "");
						jo.put("subjectSource", "");
						jo.put("sourceCode", "");
						jo.put("sourceUserCode", "");
						jo.put("sourceUserName", "");
						jo.put("sourceUserPhotoUrl", "");
						jo.put("sourceDepend", "");
						jo.put("sourceTeamCode", "");
						jo.put("sourceTeamName", "");
						jo.put("sourceOrgCode", "");
						jo.put("sourceOrgName", "");
						jo.put("sourcePublishTime", "");
						jo.put("sourceIsShield", "");
						jo.put("sourceIsDelete", "");
						jo.put("contentTips", "");
						jo.put("subjectBeginTime", "");
						jo.put("subjectEndTime", "");
						jo.put("teamQualityNum", "");
						jo.put("groupQualityNum", "");
						jo.put("provinceQualityNum", "");
						jo.put("cityQualityNum", "");
						jo.put("joinNum", "");
						jo.put("readNum", "");
						jo.put("likeNum", "");
						jo.put("collectNum", "");
						jo.put("forwardNum", "");
						jo.put("commentNum", "");
						jo.put("shareNum", "");
					}
					
					// 网盘所属组织
					if (RelationConstants.RELATION_DYNAMIC_OD.equals(relationDynamicModel.getDynamicClass())) {
						jo.put("diskOrgCode", StringTools.formatToString(relationDynamicModel.getDiskOrgCode()));
					} else {
						jo.put("diskOrgCode", "");
					}
					
					jo.put("isLike", StringTools.formatToString(relationDynamicModel.getIsLike()));
					jo.put("isCollect", StringTools.formatToString(relationDynamicModel.getIsCollect()));
					
					// 内容资源
					List<RelationSubjectResourceModel> resourceList = relationDynamicModel.getResourceList();
					JSONArray resList = new JSONArray();
					if (resourceList != null && !resourceList.isEmpty()) {
						for (RelationSubjectResourceModel resource : resourceList) {
							JSONObject res = new JSONObject();
							res.put("resCode", StringTools.formatToString(resource.getSubjectResCode()));
							res.put("resUrl", StringTools.formatToString(resource.getResUrl()));
							res.put("resClass", StringTools.formatToString(resource.getResClass()));
							resList.add(res);
						}
					}
					jo.put("resList", resList);
					
					// 添加
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("dynamicList", ja);
			result.setBody(body);
			
			log.info("RelationDynamicController.getPortalTopTeamDynamicList：查询门户1号班组动态列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationDynamicController.getPortalTopTeamDynamicList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getPortalExTeamDynamicList  
	 * @Description: 查询门户百强班组动态列表
	 * @param getPortalExTeamDynamicListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getPortalExTeamDynamicList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getPortalExTeamDynamicList(@RequireValid GetPortalExTeamDynamicListVO getPortalExTeamDynamicListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationDynamicController.getPortalTopTeamDynamicList：查询门户百强班组动态列表");
			
			// 获取参数
			String pageSize = getPortalExTeamDynamicListVO.getPageSize(); // 每页条数
			String pageNo = getPortalExTeamDynamicListVO.getPageNo(); // 页码
			String queryTime = getPortalExTeamDynamicListVO.getQueryTime(); // 首次查询时间
			String userCode = getPortalExTeamDynamicListVO.getUserCode(); // 人员编号
			String orgCode = getPortalExTeamDynamicListVO.getOrgCode(); // 门户编号
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setUserCode(userCode); // 人员编号
			relationDynamicVO.setOrgCode(orgCode); // 门户编号
			relationDynamicVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			
			// 查询
			PageBean pageBean = relationDynamicService.getPortalExTeamDynamicList(pageParam, relationDynamicVO);
			if (pageBean == null) {
				log.error("RelationDynamicController.getPortalTopTeamDynamicList：查询失败");
				throw BusinessException.build("RELATION_19002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					RelationDynamicModel relationDynamicModel = (RelationDynamicModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("dynamicCode", StringTools.formatToString(relationDynamicModel.getDynamicCode()));
					jo.put("dynamicClass", StringTools.formatToString(relationDynamicModel.getDynamicClass()));
					
					// 获取动态所属人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(relationDynamicModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jo.put("dynamicDepend", StringTools.formatToString(relationDynamicModel.getDynamicDepend()));
					if (RelationConstants.RELATION_DYNAMIC_DEPEND_TEAM.equals(relationDynamicModel.getDynamicDepend())) {
						// 获取动态所属班组信息
						TeamInfo teamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getTeamCode());
						jo.put("teamCode", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamCode()));
						jo.put("teamName", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamName()));
						jo.put("teamPhotoUrl",  teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamIcon()));
						// 获取动态所属门户信息
						SysOrgInfo sysOrgInfo = iSysOrgService.selectByCode(teamInfo == null ? "" : teamInfo.getOrgCode());
						jo.put("orgCode", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
						jo.put("orgName", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("teamCode", "");
						jo.put("teamName", "");
						jo.put("teamPhotoUrl", "");
						jo.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
						jo.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
					}
					
					// 操作信息
					if (!StringUtils.isBlank(relationDynamicModel.getOperateTeamCode())) {
						// 查询操作班组信息
						TeamInfo operateTeamCode = iTeamInfoService.selectByCode(relationDynamicModel.getOperateTeamCode());
						jo.put("operateTeamCode", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamCode()));
						jo.put("operateTeamName", operateTeamCode == null ? "" : StringTools.formatToString(operateTeamCode.getTeamName()));
					} else {
						jo.put("operateTeamCode", "");
						jo.put("operateTeamName", "");
					}
					if (!StringUtils.isBlank(relationDynamicModel.getOperateOrgCode())) {
						// 查询操作组织信息
						SysOrgInfo operateOrgInfo = iSysOrgService.selectByCode(relationDynamicModel.getOperateOrgCode());
						jo.put("operateOrgCode", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgCode()));
						jo.put("operateOrgName", operateOrgInfo == null ? "" : StringTools.formatToString(operateOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("operateOrgCode", "");
						jo.put("operateOrgName", "");
					}
					
					jo.put("operateTime", StringTools.formatToString(relationDynamicModel.getOperateTime()));
					jo.put("subjectCode", StringTools.formatToString(relationDynamicModel.getSubjectCode()));
					jo.put("subjectName", StringTools.formatToString(relationDynamicModel.getSubjectName()));
					jo.put("subjectUrlHonor", StringTools.formatToString(relationDynamicModel.getSubjectUrlHonor()));
					jo.put("subjectClass", StringTools.formatToString(relationDynamicModel.getSubjectClass()));
					
					// 显示内容信息
					if (RelationConstants.RELATION_YES.equals(relationDynamicModel.getIsSubjectShow())) {
						// 获取内容发布人信息
						SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSubjectUserCode());
						jo.put("subjectUserCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserCode()));
						jo.put("subjectUserName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserName()));
						jo.put("subjectUserPhotoUrl", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getHeadPortrait()));
						jo.put("subjectDepend", StringTools.formatToString(relationDynamicModel.getSubjectDepend()));
						if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSubjectDepend())) {
							// 获取内容所属班组信息
							TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSubjectTeamCode());
							jo.put("subjectTeamCode", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamCode()));
							jo.put("subjectTeamName", subjectTeamInfo == null ? "" : StringTools.formatToString(subjectTeamInfo.getTeamName()));
							SysOrgInfo subjectOrgCode = iSysOrgService.selectByCode(subjectTeamInfo == null ? "" : subjectTeamInfo.getOrgCode());
							jo.put("subjectOrgCode", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgCode()));
							jo.put("subjectOrgName", subjectOrgCode == null ? "" : StringTools.formatToString(subjectOrgCode.getSysOrgFullname()));
						} else {
							jo.put("subjectTeamCode", "");
							jo.put("subjectTeamName", "");
							jo.put("subjectOrgCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgCode()));
							jo.put("subjectOrgName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgFullname()));
						}
						
						jo.put("subjectPublishTime", StringTools.formatToString(relationDynamicModel.getSubjectPublishTime()));
						jo.put("subjectTitle", StringTools.formatToString(relationDynamicModel.getSubjectTitle()));
						jo.put("subjectDigest", StringTools.formatToString(relationDynamicModel.getSubjectDigest()));
						jo.put("subjectUrl", StringTools.formatToString(relationDynamicModel.getSubjectUrl()));
						jo.put("subjectSource", StringTools.formatToString(relationDynamicModel.getSubjectSource()));
						jo.put("sourceCode", StringTools.formatToString(relationDynamicModel.getSourceCode()));
						
						// 内容来源信息
						if (RelationConstants.RELATION_SUBJECT_SOURCE_FORWARD.equals(relationDynamicModel.getSubjectSource())) {
							// 转发微博被删除或者被屏蔽不需要查
							if (RelationConstants.RELATION_DYNAMIC_FORWARD_MBLOG.equals(relationDynamicModel.getDynamicClass())
									&& (RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsShield())
											|| RelationConstants.RELATION_YES.equals(relationDynamicModel.getSourceIsDelete()))) {
								jo.put("sourceUserCode", "");
								jo.put("sourceUserName", "");
								jo.put("sourceUserPhotoUrl", "");
								jo.put("sourceDepend", "");
								jo.put("sourceTeamCode", "");
								jo.put("sourceTeamName", "");
								jo.put("sourceOrgCode", "");
								jo.put("sourceOrgName", "");
								jo.put("sourcePublishTime", "");
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							} else {
								// 获取来源人员信息
								SysUserInfo sourceUserInfo = iSysUserService.selectByCode(relationDynamicModel.getSourceUserCode());
								jo.put("sourceUserCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserCode()));
								jo.put("sourceUserName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserName()));
								jo.put("sourceUserPhotoUrl", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getHeadPortrait()));
								jo.put("sourceDepend", StringTools.formatToString(relationDynamicModel.getSourceDepend()));
								if (RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationDynamicModel.getSourceDepend())) {
									// 获取来源班组信息
									TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(relationDynamicModel.getSourceTeamCode());
									jo.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamCode()));
									jo.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
									// 获取来源组织信息
									SysOrgInfo sourceOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo == null ? "" : sourceTeamInfo.getOrgCode());
									jo.put("sourceOrgCode", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgCode()));
									jo.put("sourceOrgName", sourceOrgInfo == null ? "" : StringTools.formatToString(sourceOrgInfo.getSysOrgFullname()));
								} else {
									jo.put("sourceTeamCode", "");
									jo.put("sourceTeamName", "");
									jo.put("sourceOrgCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgCode()));
									jo.put("sourceOrgName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgFullname()));
								}
								
								jo.put("sourcePublishTime", StringTools.formatToString(relationDynamicModel.getSourcePublishTime()));
								jo.put("sourceIsShield", StringTools.formatToString(relationDynamicModel.getSourceIsShield()));
								jo.put("sourceIsDelete", StringTools.formatToString(relationDynamicModel.getSourceIsDelete()));
							}
						} else {
							jo.put("sourceUserCode", "");
							jo.put("sourceUserName", "");
							jo.put("sourceUserPhotoUrl", "");
							jo.put("sourceDepend", "");
							jo.put("sourceTeamCode", "");
							jo.put("sourceTeamName", "");
							jo.put("sourceOrgCode", "");
							jo.put("sourceOrgName", "");
							jo.put("sourcePublishTime", "");
							jo.put("sourceIsShield", "");
							jo.put("sourceIsDelete", "");
						}
						
						jo.put("contentTips", StringTools.formatToString(relationDynamicModel.getContentTips()));
						jo.put("subjectBeginTime", StringTools.formatToString(relationDynamicModel.getSubjectBeginTime()));
						jo.put("subjectEndTime", StringTools.formatToString(relationDynamicModel.getSubjectEndTime()));
						jo.put("teamQualityNum", StringTools.formatToString(relationDynamicModel.getTeamQualityNum()));
						jo.put("groupQualityNum", StringTools.formatToString(relationDynamicModel.getGroupQualityNum()));
						jo.put("provinceQualityNum", StringTools.formatToString(relationDynamicModel.getProvinceQualityNum()));
						jo.put("cityQualityNum", StringTools.formatToString(relationDynamicModel.getCityQualityNum()));
						jo.put("joinNum", StringTools.formatToString(relationDynamicModel.getJoinNum()));
						jo.put("readNum", StringTools.formatToString(relationDynamicModel.getReadNum()));
						jo.put("likeNum", StringTools.formatToString(relationDynamicModel.getLikeNum()));
						jo.put("collectNum", StringTools.formatToString(relationDynamicModel.getCollectNum()));
						jo.put("forwardNum", StringTools.formatToString(relationDynamicModel.getForwardNum()));
						jo.put("commentNum", StringTools.formatToString(relationDynamicModel.getCommentNum()));
						jo.put("shareNum", StringTools.formatToString(relationDynamicModel.getShareNum()));
					} else {
						jo.put("subjectUserCode", "");
						jo.put("subjectUserName", "");
						jo.put("subjectUserPhotoUrl", "");
						jo.put("subjectDepend", "");
						jo.put("subjectTeamCode", "");
						jo.put("subjectOrgCode", "");
						jo.put("subjectOrgName", "");
						jo.put("subjectPublishTime", "");
						jo.put("subjectTitle", "");
						jo.put("subjectDigest", "");
						jo.put("subjectUrl", "");
						jo.put("subjectSource", "");
						jo.put("sourceCode", "");
						jo.put("sourceUserCode", "");
						jo.put("sourceUserName", "");
						jo.put("sourceUserPhotoUrl", "");
						jo.put("sourceDepend", "");
						jo.put("sourceTeamCode", "");
						jo.put("sourceTeamName", "");
						jo.put("sourceOrgCode", "");
						jo.put("sourceOrgName", "");
						jo.put("sourcePublishTime", "");
						jo.put("sourceIsShield", "");
						jo.put("sourceIsDelete", "");
						jo.put("contentTips", "");
						jo.put("subjectBeginTime", "");
						jo.put("subjectEndTime", "");
						jo.put("teamQualityNum", "");
						jo.put("groupQualityNum", "");
						jo.put("provinceQualityNum", "");
						jo.put("cityQualityNum", "");
						jo.put("joinNum", "");
						jo.put("readNum", "");
						jo.put("likeNum", "");
						jo.put("collectNum", "");
						jo.put("forwardNum", "");
						jo.put("commentNum", "");
						jo.put("shareNum", "");
					}
					
					// 网盘所属组织
					if (RelationConstants.RELATION_DYNAMIC_OD.equals(relationDynamicModel.getDynamicClass())) {
						jo.put("diskOrgCode", StringTools.formatToString(relationDynamicModel.getDiskOrgCode()));
					} else {
						jo.put("diskOrgCode", "");
					}
					
					jo.put("isLike", StringTools.formatToString(relationDynamicModel.getIsLike()));
					jo.put("isCollect", StringTools.formatToString(relationDynamicModel.getIsCollect()));
					
					// 内容资源
					List<RelationSubjectResourceModel> resourceList = relationDynamicModel.getResourceList();
					JSONArray resList = new JSONArray();
					if (resourceList != null && !resourceList.isEmpty()) {
						for (RelationSubjectResourceModel resource : resourceList) {
							JSONObject res = new JSONObject();
							res.put("resCode", StringTools.formatToString(resource.getSubjectResCode()));
							res.put("resUrl", StringTools.formatToString(resource.getResUrl()));
							res.put("resClass", StringTools.formatToString(resource.getResClass()));
							resList.add(res);
						}
					}
					jo.put("resList", resList);
					
					// 添加
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("dynamicList", ja);
			result.setBody(body);
			
			log.info("RelationDynamicController.getPortalTopTeamDynamicList：查询门户百强班组动态列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationDynamicController.getPortalTopTeamDynamicList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: showFrontTeamUserDynamic  
	 * @Description: 班组首页展示/取消首页展示动态
	 * @param showFrontTeamUserDynamicVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="showFrontTeamUserDynamic",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON showFrontTeamUserDynamic(@RequireValid ShowFrontTeamUserDynamicVO showFrontTeamUserDynamicVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationDynamicController.showFrontTeamUserDynamic：班组首页展示/取消首页展示动态");
			
			// 获取参数
			String dynamicCode = showFrontTeamUserDynamicVO.getDynamicCode(); // 动态编号
			String teamCode = showFrontTeamUserDynamicVO.getTeamCode(); // 班组编号
			String userCode = showFrontTeamUserDynamicVO.getUserCode(); // 人员编号
			String actionClass = showFrontTeamUserDynamicVO.getActionClass(); // 操作类型
			
			// 权限校验
			boolean isTeamLeader = iTeamMemberService.isTeamLeader(teamCode, userCode);
			if (!isTeamLeader) {
				log.error("RelationDynamicController.showFrontTeamUserDynamic：操作权限不足");
				throw BusinessException.build("COMMON_403");
			}
			
			// 共用参数
			long time = new Date().getTime();

			// 拼接参数
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setTeamCode(teamCode); // 班组编号
			relationDynamicVO.setDynamicCode(dynamicCode); // 动态编号
			
			// 查询动态班组关系
			RelationDynamicTeamRel relationDynamicTeamRel = relationDynamicService.getDynamicTeamRel(relationDynamicVO);
			
			// 获取班组信息
			TeamInfo teamInfo = iTeamInfoService.selectByCode(teamCode);
			if (teamInfo == null) {
				log.error("RelationDynamicController.showFrontTeamUserDynamic：获取不到班组信息teamCode=" + teamCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 判断操作状态
			if (RelationConstants.RELATION_ACTION_YES.equals(Byte.valueOf(actionClass))) {
				/* 首页展示 */
				
				// 判断新增还是修改动态班组关系内容
				if (relationDynamicTeamRel == null) {
					// 动态班组关系
					relationDynamicTeamRel = new RelationDynamicTeamRel();
					relationDynamicTeamRel.setTenantCode(teamInfo.getTenantCode());
					relationDynamicTeamRel.setOrgCode(teamInfo.getOrgCode());
					relationDynamicTeamRel.setCreateTime(time);
					relationDynamicTeamRel.setModifyTime(time);
					relationDynamicTeamRel.setDynamicCode(dynamicCode);
					relationDynamicTeamRel.setTeamCode(teamCode);
					// 获取班组是不是百强班组
					boolean isExcelent = iTeamElectService.isExcelent(teamCode);
					relationDynamicTeamRel.setTeamIsExcellent(isExcelent ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setTeamIsNoOne(TeamConstants.TEAM_TYPE_1.equals(teamInfo.getTeamType()) ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setTeamIsDissolve(TeamConstants.BOOLEAN_TRUE.equals(teamInfo.getIsDissolve()) ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setRelIsHomeShow(RelationConstants.RELATION_YES);
					relationDynamicTeamRel.setRelIsQuality(RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setRelIsRecommend(RelationConstants.RELATION_NO);
				} else {
					// 判断是否已经首页展示
					if (RelationConstants.RELATION_YES.equals(relationDynamicTeamRel.getRelIsHomeShow())) {
						log.error("RelationDynamicController.showFrontTeamUserDynamic：该内容已被首页展示");
						throw BusinessException.build("RELATION_19400");
					}
					
					// 拼接参数
					relationDynamicTeamRel.setModifyTime(time);
				}
				
				// 保存
				boolean saveFlag = relationDynamicService.saveTeamFrontDynamic(relationDynamicTeamRel);
				if (!saveFlag) {
					log.error("RelationDynamicController.showFrontTeamUserDynamic：操作失败");
					throw BusinessException.build("RELATION_19002", "操作");
				}
				
				// 加积分
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_DYNAMIC_TEAMMEMADD);
				MessageIntegral.setAccountCode(teamCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_TEAM);
				MessageIntegral.setManageOrgCode(teamInfo.getOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			} else if (RelationConstants.RELATION_ACTION_NO.equals(Byte.valueOf(actionClass))) {
				/* 取消首页展示 */
				// 判断是否未首页展示
				if (relationDynamicTeamRel == null || RelationConstants.RELATION_NO.equals(relationDynamicTeamRel.getRelIsHomeShow())) {
					log.error("RelationDynamicController.showFrontTeamUserDynamic：该内容未被首页展示");
					throw BusinessException.build("RELATION_19401");
				}
				
				// 拼接参数
				relationDynamicVO.setModifyTime(time);
				// 修改
				boolean removeFlag = relationDynamicService.removeTeamFrontDynamic(relationDynamicVO);
				if (!removeFlag) {
					log.error("RelationDynamicController.showFrontTeamUserDynamic：操作失败");
					throw BusinessException.build("RELATION_19002", "操作");
				}
				
				// 减积分
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_DYNAMIC_TEAMMEMDEL);
				MessageIntegral.setAccountCode(teamCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_TEAM);
				MessageIntegral.setManageOrgCode(teamInfo.getOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("RelationDynamicController.showFrontTeamUserDynamic：班组首页展示/取消首页展示动态成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationDynamicController.showFrontTeamUserDynamic", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: recByTeam  
	 * @Description: 班组推荐
	 * @param recByTeamVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="recByTeam",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON recByTeam(@RequireValid RecByTeamVO recByTeamVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationDynamicController.recByTeam：班组推荐");
			
			// 获取参数
			String dynamicCode = recByTeamVO.getDynamicCode(); // 动态编号
			String teamCode = recByTeamVO.getTeamCode(); // 班组编号
			String subjectCode = recByTeamVO.getSubjectCode(); // 内容编号
			String userCode = recByTeamVO.getUserCode(); // 人员编号
			
			// 权限校验
			boolean isTeamLeader = iTeamMemberService.isTeamLeader(teamCode, userCode);
			if (!isTeamLeader) {
				log.error("RelationDynamicController.recByTeam：操作权限不足");
				throw BusinessException.build("COMMON_403");
			}
			
			// 查询动态班组关系
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setTeamCode(teamCode); // 班组编号
			relationDynamicVO.setDynamicCode(dynamicCode); // 动态编号
			RelationDynamicTeamRel relationDynamicTeamRel = relationDynamicService.getDynamicTeamRel(relationDynamicVO);
			
			// 判断是否已被班组置精
			if (relationDynamicTeamRel == null || RelationConstants.RELATION_NO.equals(relationDynamicTeamRel.getRelIsQuality())) {
				log.error("RelationDynamicController.recByTeam：该内容未被班组置精，不能推荐");
				throw BusinessException.build("RELATION_19406");
			}
			
			// 判断是否已被推荐
			if (RelationConstants.RELATION_YES.equals(relationDynamicTeamRel.getRelIsRecommend())) {
				log.error("RelationDynamicController.recByTeam：该内容已被推荐");
				throw BusinessException.build("RELATION_19402");
			}
			
			// 共用参数
			long time = new Date().getTime();
			
			// 获取班组信息
			TeamInfo teamInfo = iTeamInfoService.selectByCode(teamCode);
			if (teamInfo == null) {
				log.error("RelationDynamicController.recByTeam：获取不到班组信息teamCode=" + teamCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 查询内容信息
			RelationSubjectVO relationSubjectVO = new RelationSubjectVO();
			relationSubjectVO.setSubjectCode(subjectCode); // 内容编号
			RelationSubjectInfo relationSubjectInfo = relationSubjectService.getSubjectBaseInfo(relationSubjectVO);
			if (relationSubjectInfo == null) {
				log.error("RelationDynamicController.recByTeam：获取不到内容信息subjectCode=" + subjectCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 推荐信息
			RelationRecommend relationRecommend = new RelationRecommend();
			relationRecommend.setRecCode(UUIDHelper.getUUID());
			relationRecommend.setTenantCode(teamInfo.getTenantCode());
			relationRecommend.setCreateTime(time);
			relationRecommend.setModifyTime(time);
			relationRecommend.setSubjectCode(subjectCode);
			relationRecommend.setSubjectClass(relationSubjectInfo.getSubjectClass());
			relationRecommend.setRecSource(RelationConstants.RELATION_RECOMMEND_SOURCE_TEAM);
			relationRecommend.setTeamCode(teamCode);
			relationRecommend.setRecUserCode(userCode);
			relationRecommend.setRecOrgCode(teamInfo.getOrgCode());
			relationRecommend.setRecToOrgCode(teamInfo.getOrgCode());
			relationRecommend.setRecStatus(RelationConstants.RELATION_REC_STATUS_UNTREATED);
			
			// 动态信息
			RelationDynamic relationDynamic = new RelationDynamic();
			relationDynamic.setDynamicCode(UUIDHelper.getUUID());
			// 获取组织信息
			if (RelationConstants.RELATION_SUBJECT_DEPEND_USER.equals(relationSubjectInfo.getSubjectDepend())) {
				// 获取内容发布人信息
				SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationSubjectInfo.getUserCode());
				if (subjectUserInfo == null) {
					log.error("RelationDynamicController.recByTeam：获取不到内容发布人信息userCode=" + relationSubjectInfo.getUserCode());
					throw BusinessException.build("COMMON_400");
				}
				
				relationDynamic.setTenantCode(subjectUserInfo.getTenantCode());
				relationDynamic.setOrgCode(subjectUserInfo.getManageOrgInfo().getSysOrgCode());
			} else {
				// 获取内容所属班组信息
				TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(teamCode);
				if (subjectTeamInfo == null) {
					log.error("RelationDynamicController.recByTeam：获取不到内容所属班组信息teamCode=" + teamCode);
					throw BusinessException.build("COMMON_400");
				}
				
				relationDynamic.setTenantCode(subjectTeamInfo.getTenantCode());
				relationDynamic.setOrgCode(subjectTeamInfo.getOrgCode());
			}
			relationDynamic.setCreateTime(time);
			relationDynamic.setModifyTime(time);
			relationDynamic.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SUBJECT_REC);
			relationDynamic.setDynamicDepend(relationSubjectInfo.getSubjectDepend());
			relationDynamic.setUserCode(relationSubjectInfo.getUserCode());
			relationDynamic.setTeamCode(relationSubjectInfo.getTeamCode());
			relationDynamic.setOperateTeamCode(teamCode);
			relationDynamic.setOperateTime(time);
			relationDynamic.setSubjectCode(subjectCode);
			relationDynamic.setDynamicIsDelete(RelationConstants.RELATION_NO);
			relationDynamic.setDynamicIsShield(RelationConstants.RELATION_NO);
			relationDynamic.setIsSubjectShow(RelationConstants.RELATION_YES);
			relationDynamic.setIsSubjectDataShow(RelationConstants.RELATION_NO);
			
			// 拼接参数
			relationDynamicVO.setModifyTime(time);
			
			// 保存
			boolean saveFlag = relationDynamicService.saveTeamRec(relationDynamicVO, relationRecommend, relationDynamic);
			if (!saveFlag) {
				log.error("RelationDynamicController.recByTeam：操作失败");
				throw BusinessException.build("RELATION_19002", "操作");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("RelationDynamicController.recByTeam：班组推荐成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationDynamicController.recByTeam", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: setTeamQuality  
	 * @Description: 班组置精/取消置精
	 * @param setTeamQualityVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="setTeamQuality",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON setTeamQuality(@RequireValid SetTeamQualityVO setTeamQualityVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationDynamicController.setTeamQuality：班组置精/取消置精");
			
			// 获取参数
			String dynamicCode = setTeamQualityVO.getDynamicCode(); // 动态编号
			String subjectCode = setTeamQualityVO.getSubjectCode(); // 内容编号
			String teamCode = setTeamQualityVO.getTeamCode(); // 班组编号
			String userCode = setTeamQualityVO.getUserCode(); // 人员编号
			String actionClass = setTeamQualityVO.getActionClass(); // 操作类型
			
			// 权限校验
			boolean isTeamLeader = iTeamMemberService.isTeamLeader(teamCode, userCode);
			if (!isTeamLeader) {
				log.error("RelationDynamicController.setTeamQuality：操作权限不足");
				throw BusinessException.build("COMMON_403");
			}
			
			// 共用参数
			long time = new Date().getTime();
			
			// 查询内容信息
			RelationSubjectVO relationSubjectVO = new RelationSubjectVO();
			relationSubjectVO.setSubjectCode(subjectCode); // 内容编号
			RelationSubjectInfo relationSubjectInfo = relationSubjectService.getSubjectBaseInfo(relationSubjectVO);
			if (relationSubjectInfo == null) {
				log.error("RelationDynamicController.setTeamQuality：获取不到内容信息subjectCode=" + subjectCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 判断内容是否被删除
			if (RelationConstants.RELATION_YES.equals(relationSubjectInfo.getSubjectIsDelete())
					|| RelationConstants.RELATION_YES.equals(relationSubjectInfo.getSubjectIsShield())) {				
				log.error("RelationDynamicController.setTeamQuality：原内容已被删除，不能进行操作");
				throw BusinessException.build("RELATION_19405");
			}
			
			// 查询动态班组关系
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setTeamCode(teamCode); // 班组编号
			relationDynamicVO.setDynamicCode(dynamicCode); // 动态编号
			RelationDynamicTeamRel relationDynamicTeamRel = relationDynamicService.getDynamicTeamRel(relationDynamicVO);
			
			// 判断操作状态
			if (RelationConstants.RELATION_ACTION_YES.equals(Byte.valueOf(actionClass))) {
				/* 置精 */
				// 判断是否是原创的微博或者博客
				if (RelationConstants.RELATION_SUBJECT_SOURCE_FORWARD.equals(relationSubjectInfo.getSubjectSource())
						|| (!RelationConstants.RELATION_SUBJECT_MBLOG.equals(relationSubjectInfo.getSubjectClass())
								&& !RelationConstants.RELATION_SUBJECT_BLOG
										.equals(relationSubjectInfo.getSubjectClass()))) {
					log.error("RelationDynamicController.setTeamQuality：只有原创微博和博客可以置精");
					throw BusinessException.build("RELATION_19407");
				}
				
				// 判断置精状态
				if (relationDynamicTeamRel != null && RelationConstants.RELATION_YES.equals(relationDynamicTeamRel.getRelIsQuality())) {
					log.error("RelationDynamicController.setTeamQuality：该内容已被置精");
					throw BusinessException.build("RELATION_19403");
				}
				
				// 获取班组信息
				TeamInfo teamInfo = iTeamInfoService.selectByCode(teamCode);
				if (teamInfo == null) {
					log.error("RelationDynamicController.setTeamQuality：获取不到班组信息teamCode=" + teamCode);
					throw BusinessException.build("COMMON_400");
				}
				
				// 精华信息
				RelationQuality relationQuality = new RelationQuality();
				relationQuality.setQualityCode(UUIDHelper.getUUID());
				relationQuality.setTenantCode(teamInfo.getTenantCode());
				relationQuality.setOrgCode(teamInfo.getOrgCode());
				relationQuality.setTeamCode(teamCode);
				relationQuality.setCreateTime(time);
				relationQuality.setModifyTime(time);
				relationQuality.setSubjectCode(subjectCode);
				relationQuality.setSubjectClass(relationSubjectInfo.getSubjectClass());
				relationQuality.setQualityDepend(RelationConstants.RELATION_QUALITY_DEPEND_TEAM);
				relationQuality.setQualityIsTop(RelationConstants.RELATION_NO);
				
				// 动态信息
				RelationDynamic relationDynamic = new RelationDynamic();
				relationDynamic.setDynamicCode(UUIDHelper.getUUID());
				// 获取组织信息
				if (RelationConstants.RELATION_SUBJECT_DEPEND_USER.equals(relationSubjectInfo.getSubjectDepend())) {
					// 获取内容发布人信息
					SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationSubjectInfo.getUserCode());
					if (subjectUserInfo == null) {
						log.error("RelationDynamicController.setTeamQuality：获取不到内容发布人信息userCode=" + relationSubjectInfo.getUserCode());
						throw BusinessException.build("COMMON_400");
					}
					
					relationDynamic.setTenantCode(subjectUserInfo.getTenantCode());
					relationDynamic.setOrgCode(subjectUserInfo.getManageOrgInfo().getSysOrgCode());
				} else {
					// 获取内容所属班组信息
					TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(teamCode);
					if (subjectTeamInfo == null) {
						log.error("RelationDynamicController.setTeamQuality：获取不到内容所属班组信息teamCode=" + teamCode);
						throw BusinessException.build("COMMON_400");
					}
					
					relationDynamic.setTenantCode(subjectTeamInfo.getTenantCode());
					relationDynamic.setOrgCode(subjectTeamInfo.getOrgCode());
				}
				relationDynamic.setCreateTime(time);
				relationDynamic.setModifyTime(time);
				relationDynamic.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SUBJECT_QUALITY);
				relationDynamic.setDynamicDepend(relationSubjectInfo.getSubjectDepend());
				relationDynamic.setUserCode(relationSubjectInfo.getUserCode());
				relationDynamic.setTeamCode(relationSubjectInfo.getTeamCode());
				relationDynamic.setOperateTeamCode(teamCode);
				relationDynamic.setOperateTime(time);
				relationDynamic.setSubjectCode(subjectCode);
				relationDynamic.setDynamicIsDelete(RelationConstants.RELATION_NO);
				relationDynamic.setDynamicIsShield(RelationConstants.RELATION_NO);
				relationDynamic.setIsSubjectShow(RelationConstants.RELATION_YES);
				relationDynamic.setIsSubjectDataShow(RelationConstants.RELATION_NO);
				
				// 判断新增还是修改动态班组关系内容
				if (relationDynamicTeamRel == null) {
					// 动态班组关系
					relationDynamicTeamRel = new RelationDynamicTeamRel();
					relationDynamicTeamRel.setTenantCode(teamInfo.getTenantCode());
					relationDynamicTeamRel.setOrgCode(teamInfo.getOrgCode());
					relationDynamicTeamRel.setCreateTime(time);
					relationDynamicTeamRel.setModifyTime(time);
					relationDynamicTeamRel.setDynamicCode(dynamicCode);
					relationDynamicTeamRel.setTeamCode(teamCode);
					// 获取班组是不是百强班组
					boolean isExcelent = iTeamElectService.isExcelent(teamCode);
					relationDynamicTeamRel.setTeamIsExcellent(isExcelent ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setTeamIsNoOne(TeamConstants.TEAM_TYPE_1.equals(teamInfo.getTeamType()) ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setTeamIsDissolve(TeamConstants.BOOLEAN_TRUE.equals(teamInfo.getIsDissolve()) ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setRelIsHomeShow(RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setRelIsQuality(RelationConstants.RELATION_YES);
					relationDynamicTeamRel.setRelIsRecommend(RelationConstants.RELATION_NO);
				} else {
					// 拼接参数
					relationDynamicTeamRel.setModifyTime(time);
				}
				
				// 保存
				boolean saveFlag = relationDynamicService.saveTeamQulity(relationDynamicTeamRel, relationQuality, relationDynamic);
				if (!saveFlag) {
					log.error("RelationDynamicController.setTeamQuality：操作失败");
					throw BusinessException.build("RELATION_19002", "操作");
				}
				
				// 更新内容班组置精次数
				RelationDataVO relationDataVO = new RelationDataVO();
				relationDataVO.setSubjectCode(subjectCode);
				relationDataVO.setUpdateNumber(1);
				relationDataVO.setModifyTime(time);
				relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_TEAM);
				producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
				
				// 判断内容为博客还是微博
				if (RelationConstants.RELATION_SUBJECT_MBLOG.equals(relationSubjectInfo.getSubjectClass())) {
					/* 微博 */
					MblogEssenceVo mblogEssenceVo = new MblogEssenceVo();
					mblogEssenceVo.setUserCode(userCode);
					mblogEssenceVo.setMblogCode(subjectCode);
					mblogEssenceVo.setScope(MblogConstants.MBLOG_ESSENCE_SCOPE_TEAM);
					mblogEssenceVo.setIsEssence(MblogConstants.MBLOG_YES);
					producerTeplate.send(MblogConstants.MBLOG_KAFKA_TOPIC_ESSENCE, JSONObject.toJSONString(mblogEssenceVo));
				} else if (RelationConstants.RELATION_SUBJECT_BLOG.equals(relationSubjectInfo.getSubjectClass())) {
					/* 博客 */
					BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
					blogThirdDataVO.setSubjectCode(subjectCode);
					blogThirdDataVO.setUpdateNumber(1);
					blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_QUALITY_TEAM);
					blogThirdDataVO.setModifyTime(time);
					producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
				}
			} else if (RelationConstants.RELATION_ACTION_NO.equals(Byte.valueOf(actionClass))) {
				/* 取消置精 */
				// 判断置精状态
				if (relationDynamicTeamRel == null || RelationConstants.RELATION_NO.equals(relationDynamicTeamRel.getRelIsQuality())) {
					log.error("RelationDynamicController.setTeamQuality：该内容未被置精");
					throw BusinessException.build("RELATION_19404");
				}
				
				// 拼接参数
				relationDynamicVO.setSubjectCode(subjectCode);
				relationDynamicVO.setSubjectClass(relationSubjectInfo.getSubjectClass());
				relationDynamicVO.setModifyTime(time);
				
				// 修改
				boolean removeFlag = relationDynamicService.removeTeamQulity(relationDynamicVO);
				if (!removeFlag) {
					log.error("RelationDynamicController.setTeamQuality：操作失败");
					throw BusinessException.build("RELATION_19002", "操作");
				}
				
				// 更新内容班组置精次数
				RelationDataVO relationDataVO = new RelationDataVO();
				relationDataVO.setSubjectCode(subjectCode);
				relationDataVO.setUpdateNumber(-1);
				relationDataVO.setModifyTime(time);
				relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_TEAM);
				producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
				
				// 判断内容为博客还是微博
				if (RelationConstants.RELATION_SUBJECT_MBLOG.equals(relationSubjectInfo.getSubjectClass())) {
					/* 微博 */
					MblogEssenceVo mblogEssenceVo = new MblogEssenceVo();
					mblogEssenceVo.setUserCode(userCode);
					mblogEssenceVo.setMblogCode(subjectCode);
					mblogEssenceVo.setScope(MblogConstants.MBLOG_ESSENCE_SCOPE_TEAM);
					mblogEssenceVo.setIsEssence(MblogConstants.MBLOG_NO);
					producerTeplate.send(MblogConstants. MBLOG_KAFKA_TOPIC_ESSENCE, JSONObject.toJSONString(mblogEssenceVo));
				} else if (RelationConstants.RELATION_SUBJECT_BLOG.equals(relationSubjectInfo.getSubjectClass())) {
					/* 博客 */
					BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
					blogThirdDataVO.setSubjectCode(subjectCode);
					blogThirdDataVO.setUpdateNumber(-1);
					blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_QUALITY_TEAM);
					blogThirdDataVO.setModifyTime(time);
					producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("RelationDynamicController.setTeamQuality：班组置精/取消置精成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationDynamicController.showFrontTeamUserDynamic", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}

}
