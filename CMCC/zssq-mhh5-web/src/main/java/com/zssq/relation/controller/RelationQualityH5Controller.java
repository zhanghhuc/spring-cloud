package com.zssq.relation.controller;

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
import com.zssq.constants.RelationConstants;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.RelationQualityModel;
import com.zssq.model.RelationSubjectResourceModel;
import com.zssq.pojo.ResultJSON;
import com.zssq.relation.vo.GetPortalQualityListVO;
import com.zssq.relation.vo.GetTeamQualityListVO;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.RelationQualityService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.RelationQualityVO;

/**
 * @ClassName RelationQualityH5Controller
 * @Description 精华列表
 * @author LXW
 * @date 2017年5月25日 下午3:15:56
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/relation")
public class RelationQualityH5Controller {
private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RelationQualityService relationQualityService;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private ITeamInfoService iTeamInfoService;
	@Autowired
	private ISysOrgService iSysOrgService;
	
	/**
	 * 
	 * @Title: getTeamQualityList  
	 * @Description: 获取班组精华列表
	 * @param getTeamQualityListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getTeamQualityList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getTeamQualityList(@RequireValid GetTeamQualityListVO getTeamQualityListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("RelationQualityH5Controller.getTeamQualityList：获取班组精华列表");
			
			// 获取参数
			String pageSize = getTeamQualityListVO.getPageSize(); // 每页条数
			String pageNo = getTeamQualityListVO.getPageNo(); // 页码
			String queryTime = getTeamQualityListVO.getQueryTime(); // 首次查询时间
			String userCode = getTeamQualityListVO.getUserCode(); // 人员编号
			String teamCode = getTeamQualityListVO.getTeamCode(); // 班组编号
			
			// 获取班组信息
			TeamInfo teamInfo = iTeamInfoService.selectByCode(teamCode);
			if (teamInfo == null) {
				log.error("RelationQualityH5Controller.getTeamQualityList：获取不到班组信息teamCode=" + teamCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RelationQualityVO relationQualityVO =new RelationQualityVO();
			relationQualityVO.setTeamCode(teamCode);
			relationQualityVO.setUserCode(userCode);
			relationQualityVO.setQueryTime(Long.valueOf(queryTime));
			
			//查询
			PageBean pageBean = relationQualityService.getTeamQualityList(pageParam, relationQualityVO);
			if (pageBean == null) {
				log.error("RelationQualityH5Controller.getTeamQualityList：查询失败");
				throw BusinessException.build("BLOG_13002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (null != recordList && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					RelationQualityModel relationQualityModel = (RelationQualityModel) recordList.get(i);
					
					JSONObject jo = new JSONObject();
					jo.put("qualityCode", StringTools.formatToString(relationQualityModel.getQualityCode()));//精华编号
					jo.put("dynamicCode", StringTools.formatToString(relationQualityModel.getDynamicCode()));//动态编号
					jo.put("subjectCode", StringTools.formatToString(relationQualityModel.getSubjectCode()));//内容编号
					jo.put("subjectClass", StringTools.formatToString(relationQualityModel.getSubjectClass()));//内容类型：1.微博；2.博客；
					
					//获取人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(relationQualityModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));//内容发布人编号
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));//内容发布人名称
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));//内容发布人头像链接
					jo.put("subjectDepend", StringTools.formatToString(relationQualityModel.getSubjectDepend()));//从属关系
					if(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationQualityModel.getSubjectDepend())){
						// 获取内容所属组织信息
						SysOrgInfo sysOrgInfo = iSysOrgService.selectByCode(teamInfo == null ? "" : teamInfo.getOrgCode());
						jo.put("orgCode", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
						jo.put("orgName", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
					}else{
						jo.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));//内容所属组织编号
						jo.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));//内容所属组织名称
					}
					
					jo.put("subjectPublishTime", StringTools.formatToString(relationQualityModel.getSubjectPublishTime()));//内容发布时间
					jo.put("subjectTitle", StringTools.formatToString(relationQualityModel.getSubjectTitle()));//内容标题
					jo.put("subjectDigest", StringTools.formatToString(relationQualityModel.getSubjectDigest()));//内容摘要
					jo.put("subjectUrl", StringTools.formatToString(relationQualityModel.getSubjectUrl()));//内容图片链接
					jo.put("teamQualityNum", StringTools.formatToString(relationQualityModel.getTeamQualityNum()));//班组置精次数
					jo.put("groupQualityNum", StringTools.formatToString(relationQualityModel.getGroupQualityNum()));//集团级别置精次数
					jo.put("provinceQualityNum", StringTools.formatToString(relationQualityModel.getProvinceQualityNum()));//省级别置精次数
					jo.put("cityQualityNum", StringTools.formatToString(relationQualityModel.getCityQualityNum()));//市级别置精次数
					jo.put("readNum", StringTools.formatToString(relationQualityModel.getReadNum()));//浏览量
					jo.put("likeNum", StringTools.formatToString(relationQualityModel.getLikeNum()));//点赞量
					jo.put("collectNum", StringTools.formatToString(relationQualityModel.getCollectNum()));//收藏量
					jo.put("forwardNum", StringTools.formatToString(relationQualityModel.getForwardNum()));//转发量
					jo.put("commentNum", StringTools.formatToString(relationQualityModel.getCommentNum()));//评论量
					jo.put("shareNum", StringTools.formatToString(relationQualityModel.getShareNum()));//分享量
					jo.put("isLike", StringTools.formatToString(relationQualityModel.getQualityIsLike()));//是否点赞：0.否；1.是
					jo.put("isCollect", StringTools.formatToString(relationQualityModel.getQualityIsCollect()));//是否收藏：0.否；1.是
					jo.put("teamFrontShow", relationQualityModel.getTeamFrontShow() == null ? RelationConstants.RELATION_NO.toString() : StringTools.formatToString(relationQualityModel.getTeamFrontShow()));//动态编号

					//获取资源集合
					JSONArray resJa = new JSONArray();
					List<RelationSubjectResourceModel> resourceList = relationQualityModel.getResourceList();
					for (RelationSubjectResourceModel relationSubjectResourceModel : resourceList) {
						JSONObject resJo = new JSONObject();
						resJo.put("resCode", StringTools.formatToString(relationSubjectResourceModel.getSubjectResCode()));//资源编号
						resJo.put("resUrl", StringTools.formatToString(relationSubjectResourceModel.getResUrl()));//资源链接
						resJo.put("resClass", StringTools.formatToString(relationSubjectResourceModel.getResClass()));//资源类型：1.图片；2.音频；3.视频；
						resJa.add(resJo);
					}
					jo.put("resList", resJa);
					
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("teamCode", teamInfo.getTeamCode());
			body.put("teamName", teamInfo.getTeamName());
			
			body.put("total", pageBean.getTotalCount());
			body.put("qualityList", ja);
			result.setBody(body);
			
			log.info("RelationQualityH5Controller.getTeamQualityList：获取班组精华列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationQualityH5Controller.getTeamQualityList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getPortalQualityList  
	 * @Description: 查询门户精华列表
	 * @param getPortalQualityListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getPortalQualityList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getPortalQualityList(@RequireValid GetPortalQualityListVO getPortalQualityListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		try {
			log.info("RelationQualityH5Controller.getPortalQualityList：获取门户精华列表");
			
			// 获取参数
			String pageSize = getPortalQualityListVO.getPageSize(); // 每页条数
			String pageNo = getPortalQualityListVO.getPageNo(); // 页码
			String queryTime = getPortalQualityListVO.getQueryTime(); // 首次查询时间
			String orgCode = getPortalQualityListVO.getOrgCode(); // 组织机构编码
			String userCode = getPortalQualityListVO.getUserCode(); // 人员编号
			Byte subjectClass = null;
			if(!StringUtils.isBlank(getPortalQualityListVO.getSubjectClass())){
				subjectClass= Byte.valueOf(getPortalQualityListVO.getSubjectClass());
			}
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RelationQualityVO relationQualityVO =new RelationQualityVO();
			relationQualityVO.setOrgCode(orgCode);
			relationQualityVO.setUserCode(userCode);
			relationQualityVO.setSubjectClass(subjectClass);
			relationQualityVO.setQueryTime(Long.valueOf(queryTime));
			
			//查询
			PageBean pageBean = relationQualityService.getPortalQualityList(pageParam, relationQualityVO);
			if (pageBean == null) {
				log.error("RelationQualityH5Controller.getPortalQualityList：查询失败");
				throw BusinessException.build("RELATION_19002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (null != recordList && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					RelationQualityModel relationQualityModel = (RelationQualityModel) recordList.get(i);
					
					JSONObject jo = new JSONObject();
					jo.put("qualityCode", StringTools.formatToString(relationQualityModel.getQualityCode()));//精华编号
					jo.put("subjectCode", StringTools.formatToString(relationQualityModel.getSubjectCode()));//内容编号
					jo.put("subjectClass", StringTools.formatToString(relationQualityModel.getSubjectClass()));//内容类型：1.微博；2.博客；
					
					//获取人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(relationQualityModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));//内容发布人编号
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));//内容发布人名称
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));//内容发布人头像链接
					jo.put("subjectDepend", StringTools.formatToString(relationQualityModel.getSubjectDepend()));//从属关系
					if(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM.equals(relationQualityModel.getSubjectDepend())){
						// 获取内容所属班组信息
						TeamInfo teamInfo = iTeamInfoService.selectByCode(relationQualityModel.getTeamCode());
						jo.put("teamCode", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamCode()));
						jo.put("teamName", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamName()));
						// 获取内容所属组织信息
						SysOrgInfo sysOrgInfo = iSysOrgService.selectByCode(teamInfo == null ? "" : teamInfo.getOrgCode());
						jo.put("orgCode", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
						jo.put("orgName", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
					}else{
						jo.put("teamCode", "");
						jo.put("teamName", "");
						jo.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));//内容所属组织编号
						jo.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));//内容所属组织名称
					}
					
					jo.put("subjectPublishTime", StringTools.formatToString(relationQualityModel.getSubjectPublishTime()));//内容发布时间
					jo.put("subjectTitle", StringTools.formatToString(relationQualityModel.getSubjectTitle()));//内容标题
					jo.put("subjectDigest", StringTools.formatToString(relationQualityModel.getSubjectDigest()));//内容摘要
					jo.put("subjectUrl", StringTools.formatToString(relationQualityModel.getSubjectUrl()));//内容图片链接
					jo.put("teamQualityNum", StringTools.formatToString(relationQualityModel.getTeamQualityNum()));//班组置精次数
					jo.put("groupQualityNum", StringTools.formatToString(relationQualityModel.getGroupQualityNum()));//集团级别置精次数
					jo.put("provinceQualityNum", StringTools.formatToString(relationQualityModel.getProvinceQualityNum()));//省级别置精次数
					jo.put("cityQualityNum", StringTools.formatToString(relationQualityModel.getCityQualityNum()));//市级别置精次数
					jo.put("readNum", StringTools.formatToString(relationQualityModel.getReadNum()));//浏览量
					jo.put("likeNum", StringTools.formatToString(relationQualityModel.getLikeNum()));//点赞量
					jo.put("collectNum", StringTools.formatToString(relationQualityModel.getCollectNum()));//收藏量
					jo.put("forwardNum", StringTools.formatToString(relationQualityModel.getForwardNum()));//转发量
					jo.put("commentNum", StringTools.formatToString(relationQualityModel.getCommentNum()));//评论量
					jo.put("shareNum", StringTools.formatToString(relationQualityModel.getShareNum()));//分享量
					jo.put("qualityIsLike", StringTools.formatToString(relationQualityModel.getQualityIsLike()));//是否点赞：0.否；1.是
					jo.put("qualityIsCollect", StringTools.formatToString(relationQualityModel.getQualityIsCollect()));//是否收藏：0.否；1.是

					//获取资源集合
					JSONArray resJa = new JSONArray();
					List<RelationSubjectResourceModel> resourceList = relationQualityModel.getResourceList();
					for (RelationSubjectResourceModel relationSubjectResourceModel : resourceList) {
						JSONObject resJo = new JSONObject();
						resJo.put("resCode", StringTools.formatToString(relationSubjectResourceModel.getSubjectResCode()));//资源编号
						resJo.put("resUrl", StringTools.formatToString(relationSubjectResourceModel.getResUrl()));//资源链接
						resJo.put("resClass", StringTools.formatToString(relationSubjectResourceModel.getResClass()));//资源类型：1.图片；2.音频；3.视频；
						resJa.add(resJo);
					}
					jo.put("resList", resJa);
					
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("qualityList", ja);
			result.setBody(body);
			
			log.info("RelationQualityH5Controller.getPortalQualityList：获取门户精华列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RelationQualityH5Controller.getPortalQualityList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
}
