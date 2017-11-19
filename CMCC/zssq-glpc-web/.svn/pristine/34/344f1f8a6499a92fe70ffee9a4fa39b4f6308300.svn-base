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
import com.zssq.constants.AuthConstants;
import com.zssq.constants.BlogConstants;
import com.zssq.constants.MblogConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RelationQuality;
import com.zssq.dao.pojo.RelationRecommend;
import com.zssq.dao.pojo.RelationSubjectInfo;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.model.RelationRecModel;
import com.zssq.pojo.ResultJSON;
import com.zssq.qo.sync.MblogEssenceVo;
import com.zssq.relation.vo.GetRecListVO;
import com.zssq.relation.vo.IgnoreRecVO;
import com.zssq.relation.vo.RecSubjectVO;
import com.zssq.relation.vo.RemoveQualityTopVO;
import com.zssq.relation.vo.RemoveQualityVO;
import com.zssq.relation.vo.SetQualityTopVO;
import com.zssq.relation.vo.SetQualityVO;
import com.zssq.relation.vo.UpdateQualitySeqVO;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.RelationQualityService;
import com.zssq.service.RelationRecService;
import com.zssq.service.RelationSubjectService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.BlogThirdDataVO;
import com.zssq.vo.RelationDataVO;
import com.zssq.vo.RelationQualityVO;
import com.zssq.vo.RelationRecVO;
import com.zssq.vo.RelationSubjectVO;

/**
 * @ClassName RelationRecommendByGlController
 * @Description 集中对门户推荐|置精相关进行管理
 * @author LXW
 * @date 2017年4月11日 下午6:24:12
 * @version 1.0
 * @since JDK 1.7
 */
@RequestMapping("/relation/")
@Controller
public class RelationRecommendByGlController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RelationRecService relationRecService;
	@Autowired
	private RelationQualityService relationQualityService;
	@Autowired
	private RelationSubjectService relationSubjectService;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private ITeamInfoService iTeamInfoService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private KafkaProducerTemplate producerTemplate;
	
	/**
	 * 
	 * @Title: getRecList  
	 * @Description: 查询推荐列表
	 * @param getRecListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "getRecList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getRecList(@RequireValid GetRecListVO getRecListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationRecommendByGlController.getRecList：查询推荐列表");
			
			// 获取参数
			String pageSize = getRecListVO.getPageSize(); // 每页条数
			String pageNo = getRecListVO.getPageNo(); // 页码
			String recStatus = getRecListVO.getRecStatus(); // 推荐状态
			String subjectTitle = getRecListVO.getSubjectTitle(); // 标题
			String subjectClass = getRecListVO.getSubjectClass(); // 应用类型
			String beginTime = getRecListVO.getBeginTime(); // 推荐开始时间
			String endTime = getRecListVO.getEndTime(); // 推荐结束时间
			String queryTime = getRecListVO.getQueryTime(); // 首次查询时间
			String userCode = getRecListVO.getUserCode(); // 人员编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("RelationRecommendByGlController.getRecList：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RelationRecVO relationRecVO = new RelationRecVO();
			relationRecVO.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationRecVO.setQueryTime(Long.valueOf(queryTime));
			if (!StringUtils.isBlank(recStatus)) {
				relationRecVO.setRecStatus(Byte.valueOf(recStatus));
			}
			if (!StringUtils.isBlank(subjectTitle)) {
				relationRecVO.setSubjectTitle(subjectTitle);
			}
			if (!StringUtils.isBlank(subjectClass)) {
				relationRecVO.setSubjectClass(Byte.valueOf(subjectClass));
			}
			if (!StringUtils.isBlank(beginTime)) {
				relationRecVO.setBeginTime(Long.valueOf(beginTime));
			}
			if (!StringUtils.isBlank(endTime)) {
				relationRecVO.setEndTime(Long.valueOf(endTime));
			}

			// 查询
			PageBean pageBean = relationRecService.getPortalRecList(pageParam, relationRecVO);
			if (pageBean == null) {
				log.error("RelationRecommendByGlController.getRecList：查询失败");
				throw BusinessException.build("RELATION_19002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			int topCount = 0; // 精华置顶个数
			if (null != recordList && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					RelationRecModel relationRecModel = (RelationRecModel) recordList.get(i);
					
					// 置顶个数修改
					if (!StringUtils.isBlank(recStatus)
							&& (RelationConstants.RELATION_REC_STATUS_SHOW.equals(Byte.valueOf(recStatus))
									|| RelationConstants.RELATION_YES.equals(relationRecModel.getQualityIsTop()))) {
						topCount++;
					}

					JSONObject jo = new JSONObject();
					jo.put("recCode", StringTools.formatToString(relationRecModel.getRecCode()));// 推荐编号
					jo.put("subjectCode", StringTools.formatToString(relationRecModel.getSubjectCode()));// 内容编号
					jo.put("subjectClass", StringTools.formatToString(relationRecModel.getSubjectClass()));// 内容类型：1.微博；2.博客；
					jo.put("subjectDepend", StringTools.formatToString(relationRecModel.getSubjectDepend()));// 内容从属关系
					jo.put("teamCode", StringTools.formatToString(relationRecModel.getTeamCode()));// 班组编号
					jo.put("subjectTitle", StringTools.formatToString(relationRecModel.getSubjectTitle()));// 内容标题
					
					// 获取发布人信息
					SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationRecModel.getUserCode());
					jo.put("userCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserCode()));// 内容发布人
					jo.put("userName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getUserName()));// 内容发布人名称
					jo.put("orgCode", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgCode()));// 内容发布人所属公司编号
					jo.put("orgName", subjectUserInfo == null ? "" : StringTools.formatToString(subjectUserInfo.getManageOrgInfo().getSysOrgFullname()));// 内容发布人所属公司名称

					jo.put("subjectPublishTime", StringTools.formatToString(relationRecModel.getSubjectPublishTime()));// 内容发布时间
					jo.put("createTime", StringTools.formatToString(relationRecModel.getCreateTime()));// 推荐时间
					
					// 获取推荐人信息
					String recUserName = "";
					if (!StringUtils.isBlank(relationRecModel.getRecUserCode())) {
						// 获取所有推荐人信息
						String[] recUserCodes = relationRecModel.getRecUserCode().split(",");
						for (int j = 0; j < recUserCodes.length; j++) {
							SysUserInfo recUserInfo = iSysUserService.selectByCode(recUserCodes[j]);
							if (recUserInfo != null && !StringUtils.isBlank(recUserInfo.getUserName())) {
								if (j != 0) {
									recUserName += ",";
								}
								recUserName += recUserInfo.getUserName();
							}
						}
					}
					jo.put("recUserCode", relationRecModel.getRecUserCode());
					jo.put("recUserName", recUserName);
					
					jo.put("modifyTime", StringTools.formatToString(relationRecModel.getModifyTime()));// 操作时间
					jo.put("qualityIsTop", StringTools.formatToString(relationRecModel.getQualityIsTop()));// 是否置顶：0.否；1.是
					jo.put("recQualityTime", StringTools.formatToString(relationRecModel.getRecQualityTime()).equals("0") ? "" : StringTools.formatToString(relationRecModel.getRecQualityTime()));// 置精时间
					jo.put("qualitySeqNum", StringTools.formatToString(relationRecModel.getQualitySeqNum()));// 排序
					jo.put("qualityCode", StringTools.formatToString(relationRecModel.getQualityCode()));// 精华编号
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("topTotal", topCount);
			body.put("total", pageBean.getTotalCount());
			body.put("recList", ja);
			result.setBody(body);
			
			log.info("RelationRecommendByGlController.getRecList：查询门户推荐列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationRecommendByGlController.getRecList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}

	/**
	 * 
	 * @Title: recSubject  
	 * @Description: 推荐
	 * @param recSubjectVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "recSubject",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON recSubject(@RequireValid RecSubjectVO recSubjectVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationRecommendByGlController.recSubject：门户推荐");

			// 获取参数
			String subjectClass= recSubjectVO.getSubjectClass();// 推荐内容类型：1.微博；2.博客
			String subjectCode = recSubjectVO.getSubjectCode();// 推荐内容编号
			String userCode = recSubjectVO.getUserCode();// 当前用户
			
			// 查询内容信息
			RelationSubjectVO relationSubjectVO = new RelationSubjectVO();
			relationSubjectVO.setSubjectCode(subjectCode); // 内容编号
			RelationSubjectInfo relationSubjectInfo = relationSubjectService.getSubjectBaseInfo(relationSubjectVO);
			if (relationSubjectInfo == null) {
				log.error("RelationRecommendByGlController.recSubject：获取不到内容信息subjectCode=" + subjectCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("RelationRecommendByGlController.recSubject：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 获取行政组织信息
			SysOrgInfo sysOrgInfo = sysUserInfo.getManageOrgInfo();
			if (AuthConstants.ORG_TYPE_A.equals(sysOrgInfo.getSysOrgType())) {
				log.error("RelationRecommendByGlController.recSubject：集团用户不能推荐内容");
				throw BusinessException.build("RELATION_19205");
			}
			String orgCode = sysOrgInfo.getSysOrgCode(); // 本级行政组织code
			String parentOrgCode = sysOrgInfo.getParentCode(); // 上级行政组织code
			
			// 验证是否已被推荐
			RelationRecVO relationRecVO = new RelationRecVO();
			relationRecVO.setSubjectCode(subjectCode);
			relationRecVO.setOrgCode(orgCode);
			relationRecVO.setRecToOrgCode(parentOrgCode);
			boolean recStatus = relationRecService.getRecStatus(relationRecVO);
			if (recStatus) {
				log.error("RelationRecommendByGlController.recSubject：该内容已被推荐");
				throw BusinessException.build("RELATION_19402");
			}
			
			// 共用信息
			long time = new Date().getTime();
			
			// 推荐信息
			RelationRecommend relationRecommend = new RelationRecommend();
			relationRecommend.setRecCode(UUIDHelper.getUUID());
			relationRecommend.setTenantCode(sysUserInfo.getTenantCode());
			relationRecommend.setCreateTime(time);
			relationRecommend.setModifyTime(time);
			relationRecommend.setSubjectCode(subjectCode);
			relationRecommend.setSubjectClass(Byte.valueOf(subjectClass));
			relationRecommend.setRecSource(RelationConstants.RELATION_RECOMMEND_SOURCE_PORTAL);
			relationRecommend.setRecUserCode(userCode);
			relationRecommend.setRecOrgCode(orgCode);
			relationRecommend.setRecToOrgCode(parentOrgCode);
			relationRecommend.setRecStatus(RelationConstants.RELATION_REC_STATUS_UNTREATED);
			relationRecommend.setRecQualityTime(0L);
			
			// 动态信息
			RelationDynamic relationDynamic = new RelationDynamic();
			relationDynamic.setDynamicCode(UUIDHelper.getUUID());
			// 获取组织信息
			if (RelationConstants.RELATION_SUBJECT_DEPEND_USER.equals(relationSubjectInfo.getSubjectDepend())) {
				// 获取内容发布人信息
				SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationSubjectInfo.getUserCode());
				if (subjectUserInfo == null) {
					log.error("RelationRecommendByGlController.recSubject：获取不到内容发布人信息userCode=" + relationSubjectInfo.getUserCode());
					throw BusinessException.build("COMMON_400");
				}
				
				relationDynamic.setTenantCode(subjectUserInfo.getTenantCode());
				relationDynamic.setOrgCode(subjectUserInfo.getManageOrgInfo().getSysOrgCode());
			} else {
				// 获取内容所属班组信息
				TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(relationSubjectInfo.getTeamCode());
				if (subjectTeamInfo == null) {
					log.error("RelationDynamicController.recByTeam：获取不到内容所属班组信息teamCode=" + relationSubjectInfo.getTeamCode());
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
			relationDynamic.setOperateOrgCode(orgCode);
			relationDynamic.setOperateTime(time);
			relationDynamic.setSubjectCode(subjectCode);
			relationDynamic.setDynamicIsDelete(RelationConstants.RELATION_NO);
			relationDynamic.setDynamicIsShield(RelationConstants.RELATION_NO);
			relationDynamic.setIsSubjectShow(RelationConstants.RELATION_YES);
			relationDynamic.setIsSubjectDataShow(RelationConstants.RELATION_NO);
			
			// 保存
			boolean saveFlag = relationRecService.saveRec(relationRecommend, relationDynamic);
			if (!saveFlag) {
				log.error("RelationRecommendByGlController.recSubject：操作失败");
				throw BusinessException.build("RELATION_19002", "操作");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("RelationRecommendByGlController.recSubject：推荐成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationRecommendByGlController.recSubject", e);
			throw BusinessException.build("COMMON_400");
		}

		return result;
	}

	/**
	 * 
	 * @Title: setQuality  
	 * @Description:  置精
	 * @param setQualityVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "setQuality",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON setQuality(@RequireValid SetQualityVO setQualityVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationRecommendByGlController.setQuality：置精");

			// 获取参数
			String recCode = setQualityVO.getRecCode(); // 推荐编号
			String subjectCode = setQualityVO.getSubjectCode(); // 内容编号
			String subjectClass = setQualityVO.getSubjectClass(); // 内容编号
			String userCode = setQualityVO.getUserCode(); // 当前用户
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("RelationRecommendByGlController.setQuality：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 查询内容信息
			RelationSubjectVO relationSubjectVO = new RelationSubjectVO();
			relationSubjectVO.setSubjectCode(subjectCode); // 内容编号
			RelationSubjectInfo relationSubjectInfo = relationSubjectService.getSubjectBaseInfo(relationSubjectVO);
			if (relationSubjectInfo == null) {
				log.error("RelationRecommendByGlController.setQuality：获取不到内容信息subjectCode=" + subjectCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 验证是否已被置精
			RelationRecVO relationRecVO = new RelationRecVO();
			relationRecVO.setSubjectCode(subjectCode);
			relationRecVO.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationRecVO.setRecToOrgCode(sysUserInfo.getManageOrgInfo().getParentCode());
			relationRecVO.setRecStatus(RelationConstants.RELATION_REC_STATUS_SHOW);
			boolean qualityFlag = relationRecService.getRecStatus(relationRecVO);
			if (qualityFlag) {
				log.error("RelationRecommendByGlController.setQuality：该内容已被置精");
				throw BusinessException.build("RELATION_19403");
			}
			
			// 共用信息
			long time = new Date().getTime();
			
			// 精华信息
			RelationQuality relationQuality = new RelationQuality();
			relationQuality.setQualityCode(UUIDHelper.getUUID());
			relationQuality.setTenantCode(sysUserInfo.getTenantCode());
			relationQuality.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationQuality.setCreateTime(time);
			relationQuality.setModifyTime(time);
			relationQuality.setSubjectCode(subjectCode);
			relationQuality.setSubjectClass(Byte.valueOf(subjectClass));
			relationQuality.setQualityDepend(RelationConstants.RELATION_QUALITY_DEPEND_GROUP);
			relationQuality.setQualityIsTop(RelationConstants.RELATION_NO);
			relationQuality.setQualityTopTime(0L);
			
			// 动态信息
			RelationDynamic relationDynamic = new RelationDynamic();
			relationDynamic.setDynamicCode(UUIDHelper.getUUID());
			// 获取组织信息
			if (RelationConstants.RELATION_SUBJECT_DEPEND_USER.equals(relationSubjectInfo.getSubjectDepend())) {
				// 获取内容发布人信息
				SysUserInfo subjectUserInfo = iSysUserService.selectByCode(relationSubjectInfo.getUserCode());
				if (subjectUserInfo == null) {
					log.error("RelationRecommendByGlController.setQuality：获取不到内容发布人信息userCode=" + relationSubjectInfo.getUserCode());
					throw BusinessException.build("COMMON_400");
				}
				
				relationDynamic.setTenantCode(subjectUserInfo.getTenantCode());
				relationDynamic.setOrgCode(subjectUserInfo.getManageOrgInfo().getSysOrgCode());
			} else {
				// 获取内容所属班组信息
				TeamInfo subjectTeamInfo = iTeamInfoService.selectByCode(relationSubjectInfo.getTeamCode());
				if (subjectTeamInfo == null) {
					log.error("RelationRecommendByGlController.setQuality：获取不到内容所属班组信息teamCode=" + relationSubjectInfo.getTeamCode());
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
			relationDynamic.setOperateOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationDynamic.setOperateTime(time);
			relationDynamic.setSubjectCode(subjectCode);
			relationDynamic.setDynamicIsDelete(RelationConstants.RELATION_NO);
			relationDynamic.setDynamicIsShield(RelationConstants.RELATION_NO);
			relationDynamic.setIsSubjectShow(RelationConstants.RELATION_YES);
			relationDynamic.setIsSubjectDataShow(RelationConstants.RELATION_NO);
			
			// 其他参数
			relationRecVO.setRecCode(recCode);
			
			// 操作
			boolean saveFlag = relationQualityService.saveQuality(relationRecVO, relationQuality, relationDynamic);
			if (!saveFlag) {
				log.error("RelationRecommendByGlController.setQuality：操作失败");
				throw BusinessException.build("RELATION_19002", "操作");
			}
			
			// 修改置精次数
			RelationDataVO relationDataVO = new RelationDataVO();
			MblogEssenceVo mblogEssenceVo = new MblogEssenceVo();
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			
			// 判断组织级别
			switch (sysUserInfo.getManageOrgInfo().getSysOrgType()) {
				case AuthConstants.ORG_TYPE_A:
					relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_GROUP);
					mblogEssenceVo.setScope(MblogConstants.MBLOG_ESSENCE_SCOPE_GROUP);
					blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_QUALITY_GROUP);
					break;
				case AuthConstants.ORG_TYPE_B:
					relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_PROVINCE);
					mblogEssenceVo.setScope(MblogConstants.MBLOG_ESSENCE_SCOPE_PROVINCE);
					blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_QUALITY_PROVINCE);
					break;
				case AuthConstants.ORG_TYPE_C:
					relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_CITY);
					mblogEssenceVo.setScope(MblogConstants.MBLOG_ESSENCE_SCOPE_CITY);
					blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_QUALITY_CITY);
					break;
				default:
					break;
			}
			
			// 修改关系服务内容表置精次数
			relationDataVO.setModifyTime(time);
			relationDataVO.setSubjectCode(subjectCode);
			relationDataVO.setUpdateNumber(RelationConstants.NUM_ONE);
			producerTemplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
			
			// 判断内容是微博还是博客
			if (RelationConstants.RELATION_QUALITY_SUBJECT_BLOG.equals(subjectClass)) {
				blogThirdDataVO.setModifyTime(time);
				blogThirdDataVO.setSubjectCode(subjectCode);
				blogThirdDataVO.setUpdateNumber(1);
				producerTemplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			} else {
				mblogEssenceVo.setUserCode(userCode);
				mblogEssenceVo.setMblogCode(subjectCode);
				mblogEssenceVo.setIsEssence(MblogConstants.MBLOG_YES);
				producerTemplate.send(MblogConstants.MBLOG_KAFKA_TOPIC_ESSENCE, JSONObject.toJSONString(mblogEssenceVo));
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("RelationRecommendByGlController.setQuality：置精成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationRecommendByGlController.setQuality", e);
			throw BusinessException.build("COMMON_400");
		}

		return result;
	}

	/**
	 * @Function removeQuality
	 * @Description 取消置精
	 * @param recSubjectVO
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "removeQuality",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON removeQuality(@RequireValid RemoveQualityVO removeQualityVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationRecommendByGlController.removeQuality：取消置精");

			// 获取参数
			String recCode = removeQualityVO.getRecCode(); // 推荐编号
			String qualityCode = removeQualityVO.getQualityCode(); // 精华编号
			String userCode = removeQualityVO.getUserCode(); // 人员编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("RelationRecommendByGlController.removeQuality：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 拼接参数
			RelationQualityVO relationQualityVO = new RelationQualityVO();
			relationQualityVO.setQualityCode(qualityCode);
			
			// 获取精华信息
			RelationQuality relationQuality = relationQualityService.getQualityInfo(relationQualityVO);
			if (relationQuality == null) {
				log.error("RelationRecommendByGlController.removeQuality：该内容未被置精");
				throw BusinessException.build("RELATION_19404");
			}
			
			// 共用信息
			long time = new Date().getTime(); 
			
			// 拼接参数
			relationQualityVO.setRecCode(recCode);
			relationQualityVO.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationQualityVO.setModifyTime(time);
			
			// 取消置精
			boolean deleteFlag = relationQualityService.deleteQuality(relationQualityVO);
			if (!deleteFlag) {
				log.error("RelationRecommendByGlController.removeQuality：操作失败");
				throw BusinessException.build("RELATION_19002", "操作");
			}
			
			// 修改置精次数
			RelationDataVO relationDataVO = new RelationDataVO();
			MblogEssenceVo mblogEssenceVo = new MblogEssenceVo();
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			
			// 判断组织级别
			switch (sysUserInfo.getManageOrgInfo().getSysOrgType()) {
				case AuthConstants.ORG_TYPE_A:
					relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_GROUP);
					mblogEssenceVo.setScope(MblogConstants.MBLOG_ESSENCE_SCOPE_GROUP);
					blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_QUALITY_GROUP);
					break;
				case AuthConstants.ORG_TYPE_B:
					relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_PROVINCE);
					mblogEssenceVo.setScope(MblogConstants.MBLOG_ESSENCE_SCOPE_PROVINCE);
					blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_QUALITY_PROVINCE);
					break;
				case AuthConstants.ORG_TYPE_C:
					relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_CITY);
					mblogEssenceVo.setScope(MblogConstants.MBLOG_ESSENCE_SCOPE_CITY);
					blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_QUALITY_CITY);
					break;
				default:
					break;
			}
			
			// 修改关系服务内容表置精次数
			relationDataVO.setModifyTime(time);
			relationDataVO.setSubjectCode(relationQuality.getSubjectCode());
			relationDataVO.setUpdateNumber(RelationConstants.NUM_BELOW_ONE);
			producerTemplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
			
			// 判断内容是微博还是博客
			if (RelationConstants.RELATION_QUALITY_SUBJECT_BLOG.equals(relationQuality.getSubjectClass())) {
				blogThirdDataVO.setModifyTime(time);
				blogThirdDataVO.setSubjectCode(relationQuality.getSubjectCode());
				blogThirdDataVO.setUpdateNumber(-1);
				producerTemplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			} else {
				mblogEssenceVo.setUserCode(userCode);
				mblogEssenceVo.setMblogCode(relationQuality.getSubjectCode());
				mblogEssenceVo.setIsEssence(MblogConstants.MBLOG_NO);
				producerTemplate.send(MblogConstants.MBLOG_KAFKA_TOPIC_ESSENCE, JSONObject.toJSONString(mblogEssenceVo));
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("RelationRecommendByGlController.removeQuality：取消置精成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationRecommendByGlController.removeQuality", e);
			throw BusinessException.build("COMMON_400");
		}

		return result;
	}

	/**
	 * 
	 * @Title: ignoreRec  
	 * @Description: 忽略推荐
	 * @param ignoreRecVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "ignoreRec",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON ignoreRec(@RequireValid IgnoreRecVO ignoreRecVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		try {
			log.info("RelationRecommendByGlController.ignoreRec：忽略推荐");

			// 获取参数
			String recCode = ignoreRecVO.getRecCode();// 推荐编号
			String userCode = ignoreRecVO.getUserCode(); // 人员编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("RelationRecommendByGlController.ignoreRec：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 拼接参数
			RelationRecVO relationRecVO = new RelationRecVO();
			relationRecVO.setRecCode(recCode);
			relationRecVO.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationRecVO.setModifyTime(new Date().getTime());
			
			// 忽略推荐
			boolean updateFlag = relationRecService.updateRecStatus(relationRecVO);
			if (!updateFlag) {
				log.error("RelationRecommendByGlController.ignoreRec：操作失败");
				throw BusinessException.build("RELATION_19002", "操作");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("RelationRecommendByGlController.ignoreRec：忽略推荐成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationRecommendByGlController.ignoreRec", e);
			throw BusinessException.build("COMMON_400");
		}

		return result;
	}

	/**
	 * 
	 * @Title: setQualityTop  
	 * @Description: 精华置顶
	 * @param setQualityTopVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "setQualityTop",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON setQualityTop(@RequireValid SetQualityTopVO setQualityTopVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationRecommendByGlController.setQualityTop：精华置顶");

			// 获取参数
			String qualityCode = setQualityTopVO.getQualityCode();// 精华编号
			String userCode = setQualityTopVO.getUserCode(); // 人员编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("RelationRecommendByGlController.setQualityTop：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 拼接参数
			RelationQualityVO relationQualityVO = new RelationQualityVO();
			relationQualityVO.setQualityCode(qualityCode);
			relationQualityVO.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationQualityVO.setActionClass(RelationConstants.RELATION_ACTION_YES);
			relationQualityVO.setModifyTime(new Date().getTime());
			
			// 置顶
			boolean updateFlag = relationQualityService.updateQualityTop(relationQualityVO);
			if (!updateFlag) {
				log.error("RelationRecommendByGlController.setQualityTop：操作失败");
				throw BusinessException.build("RELATION_19002", "操作");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("RelationRecommendByGlController.setQualityTop：精华置顶成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationRecommendByGlController.setQualityTop", e);
			throw BusinessException.build("COMMON_400");
		}
		return result;

	}

	/**
	 * 
	 * @Title: removeQualityTop  
	 * @Description: 精华取消置顶
	 * @param removeQualityTopVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "removeQualityTop",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON removeQualityTop(@RequireValid RemoveQualityTopVO removeQualityTopVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		try {
			log.info("RelationRecommendByGlController.removeQualityTop：精华取消置顶");

			// 获取参数
			String qualityCode = removeQualityTopVO.getQualityCode();// 精华编号
			String userCode = removeQualityTopVO.getUserCode(); // 人员编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("RelationRecommendByGlController.removeQualityTop：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 拼接参数
			RelationQualityVO relationQualityVO = new RelationQualityVO();
			relationQualityVO.setQualityCode(qualityCode);
			relationQualityVO.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationQualityVO.setActionClass(RelationConstants.RELATION_ACTION_NO);
			relationQualityVO.setModifyTime(new Date().getTime());
			
			// 取消置顶
			boolean updateFlag = relationQualityService.updateQualityTop(relationQualityVO);
			if (!updateFlag) {
				log.error("RelationRecommendByGlController.removeQualityTop：操作失败");
				throw BusinessException.build("RELATION_19002", "操作");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("RelationRecommendByGlController.removeQualityTop：精华取消置顶成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationRecommendByGlController.removeQualityTop", e);
			throw BusinessException.build("COMMON_400");
		}

		return result;
	}

	/**
	 * 
	 * @Title: updateQualitySeq  
	 * @Description: 修改精华排序
	 * @param updateQualitySeqVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "updateQualitySeq",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateQualitySeq(@RequireValid UpdateQualitySeqVO updateQualitySeqVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		try {
			log.info("RelationRecommendByGlController.updateQualitySeq：修改精华排序");

			// 获取参数
			String qualityCode = updateQualitySeqVO.getQualityCode(); // 精华编号
			String qualitySeqNum = updateQualitySeqVO.getQualitySeqNum(); // 排序值
			String actionClass = updateQualitySeqVO.getActionClass(); // 操作类型
			String subjectTitle = updateQualitySeqVO.getSubjectTitle(); // 标题
			String subjectClass = updateQualitySeqVO.getSubjectClass(); // 应用类型
			String beginTime = updateQualitySeqVO.getBeginTime(); // 推荐开始时间
			String endTime = updateQualitySeqVO.getEndTime(); // 推荐结束时间
			String queryTime = updateQualitySeqVO.getQueryTime(); // 首次查询时间
			String userCode = updateQualitySeqVO.getUserCode(); // 人员编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("RelationRecommendByGlController.updateQualitySeq：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 拼接参数
			RelationQualityVO relationQualityVO = new RelationQualityVO();
			relationQualityVO.setQualityCode(qualityCode);
			relationQualityVO.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationQualityVO.setQualitySeqNum(Integer.valueOf(qualitySeqNum));
			relationQualityVO.setActionClass(Byte.valueOf(actionClass));
			relationQualityVO.setQueryTime(Long.valueOf(queryTime));
			relationQualityVO.setModifyTime(new Date().getTime());
			if (!StringUtils.isBlank(subjectTitle)) {
				relationQualityVO.setSubjectTitle(subjectTitle);
			}
			if (!StringUtils.isBlank(subjectClass)) {
				relationQualityVO.setSubjectClass(Byte.valueOf(subjectClass));
			}
			if (!StringUtils.isBlank(beginTime)) {
				relationQualityVO.setBeginTime(Long.valueOf(beginTime));
			}
			if (!StringUtils.isBlank(endTime)) {
				relationQualityVO.setEndTime(Long.valueOf(endTime));
			}
			
			// 修改排序
			boolean updateFlag = relationQualityService.updateQualitySeq(relationQualityVO);
			if (!updateFlag) {
				log.error("RelationRecommendByGlController.updateQualitySeq：操作失败");
				throw BusinessException.build("RELATION_19002", "操作");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("RelationRecommendByGlController.updateQualitySeq：修改精华排序成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationRecommendByGlController.updateQualitySeq", e);
			throw BusinessException.build("COMMON_400");
		}

		return result;
	}
	
}
