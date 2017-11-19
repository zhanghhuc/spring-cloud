package com.zssq.blog.controller;

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
import com.zssq.blog.vo.DelTeamDraftVO;
import com.zssq.blog.vo.DelUserDraftVO;
import com.zssq.blog.vo.GetTeamDraftInfoVO;
import com.zssq.blog.vo.GetTeamDraftListVO;
import com.zssq.blog.vo.GetUserDraftInfoVO;
import com.zssq.blog.vo.GetUserDraftListVO;
import com.zssq.blog.vo.SaveTeamDraftVO;
import com.zssq.blog.vo.SaveUserDraftVO;
import com.zssq.constants.BlogConstants;
import com.zssq.dao.model.BlogDraftModel;
import com.zssq.dao.pojo.BlogDraft;
import com.zssq.dao.pojo.BlogDraftContent;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.BlogDraftService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.ITeamMemberService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.BlogDraftVO;

/**
 * 
 * @ClassName: BlogDraftController  
 * @Description: 博客草稿  
 * @author ZKZ  
 * @date 2017年3月21日  
 *
 */
@RequestMapping("/blog")
@Controller
public class BlogDraftController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BlogDraftService blogDraftService;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private ITeamInfoService iTeamInfoService;
	@Autowired
	private ITeamMemberService iTeamMemberService;
	
	/**
	 * 
	 * @Title: getUserDraftList  
	 * @Description: 查询个人草稿列表
	 * @param getUserDraftListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getUserDraftList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getUserDraftList(@RequireValid GetUserDraftListVO getUserDraftListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogDraftController.getUserDraftList：查询个人草稿列表");
			
			// 获取参数
			String pageSize = getUserDraftListVO.getPageSize(); // 每页条数
			String pageNo = getUserDraftListVO.getPageNo(); // 页码
			String queryTime = getUserDraftListVO.getQueryTime(); // 首次查询时间
			String userCode = getUserDraftListVO.getUserCode(); // 人员编号
			
			// 查询参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			BlogDraftVO blogDraftVO = new BlogDraftVO();
			blogDraftVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			blogDraftVO.setUserCode(userCode); // 人员编号
			blogDraftVO.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_USER); // 从属关系
			
			// 查询草稿列表
			PageBean pageBean = blogDraftService.getDraftList(pageParam, blogDraftVO);
			if (pageBean == null) {
				log.error("BlogDraftController.getUserDraftList：查询失败");
				throw BusinessException.build("BLOG_13002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				for (int i = 0; i < recordList.size(); i++) {
					BlogDraft blogDraft = (BlogDraft) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("draftCode", StringTools.formatToString(blogDraft.getDraftCode()));
					jo.put("draftTitle", StringTools.formatToString(blogDraft.getDraftTitle()));
					jo.put("modifyTime", StringTools.formatToString(blogDraft.getModifyTime()));
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("draftList", ja);
			result.setBody(body);
			
			log.info("BlogDraftController.getUserDraftList：查询个人草稿列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogDraftController.getUserDraftList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getTeamDraftList  
	 * @Description: 查询班组草稿列表
	 * @param getTeamDraftListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getTeamDraftList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getTeamDraftList(@RequireValid GetTeamDraftListVO getTeamDraftListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogDraftController.getTeamDraftList：查询班组草稿列表");
			
			// 获取参数
			String pageSize = getTeamDraftListVO.getPageSize(); // 每页条数
			String pageNo = getTeamDraftListVO.getPageNo(); // 页码
			String queryTime = getTeamDraftListVO.getQueryTime(); // 首次查询时间
			String userCode = getTeamDraftListVO.getUserCode(); // 人员编号
			String teamCode = getTeamDraftListVO.getTeamCode(); // 班组编号
			
			// 权限校验
			boolean isTeamLeader = iTeamMemberService.isTeamLeader(teamCode, userCode);
			if (!isTeamLeader) {
				log.error("BlogDraftController.getTeamDraftList：操作权限不足");
				throw BusinessException.build("COMMON_403");
			}
			
			// 查询参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			BlogDraftVO blogDraftVO = new BlogDraftVO();
			blogDraftVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			blogDraftVO.setUserCode(userCode); // 人员编号
			blogDraftVO.setTeamCode(teamCode); // 班组编号
			blogDraftVO.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_TEAM); // 从属关系
			
			// 查询草稿列表
			PageBean pageBean = blogDraftService.getDraftList(pageParam, blogDraftVO);
			if (pageBean == null) {
				log.error("BlogDraftController.getTeamDraftList：查询失败");
				throw BusinessException.build("BLOG_13002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				for (int i = 0; i < recordList.size(); i++) {
					BlogDraft blogDraft = (BlogDraft) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("draftCode", StringTools.formatToString(blogDraft.getDraftCode()));
					jo.put("draftTitle", StringTools.formatToString(blogDraft.getDraftTitle()));
					jo.put("modifyTime", StringTools.formatToString(blogDraft.getModifyTime()));
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("draftList", ja);
			result.setBody(body);
			
			log.info("BlogDraftController.getTeamDraftList：查询班组草稿列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogDraftController.getTeamDraftList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getUserDraftInfo  
	 * @Description: 查询个人草稿详情
	 * @param getUserDraftInfoVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getUserDraftInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getUserDraftInfo(@RequireValid GetUserDraftInfoVO getUserDraftInfoVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogDraftController.getUserDraftInfo：查询个人草稿详情");
			
			// 获取参数
			String draftCode = getUserDraftInfoVO.getDraftCode(); // 草稿编号
			String userCode = getUserDraftInfoVO.getUserCode(); // 人员编号
			
			// 查询参数
			BlogDraftVO blogDraftVO = new BlogDraftVO();
			blogDraftVO.setUserCode(userCode); // 人员编号
			blogDraftVO.setDraftCode(draftCode); // 草稿编号
			blogDraftVO.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_USER); // 从属关系
			
			// 查询草稿详情
			BlogDraftModel blogDraftModel = blogDraftService.getDraftInfo(blogDraftVO);
			if (blogDraftModel == null) {
				result = new ResultJSON("COMMON_200");
				JSONObject body = new JSONObject();
				result.setBody(body);
				
				log.info("BlogDraftController.getUserDraftInfo：查询个人草稿详情成功");
				return result;
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("draftCode", StringTools.formatToString(blogDraftModel.getDraftCode()));
			body.put("draftTitle", StringTools.formatToString(blogDraftModel.getDraftTitle()));
			body.put("draftTags", StringTools.formatToString(blogDraftModel.getDraftTags()));
			body.put("classCode", StringTools.formatToString(blogDraftModel.getClassCode()));
			body.put("draftContentInfo", StringTools.formatToString(blogDraftModel.getDraftContentInfo()));
			body.put("draftPlanPublishTime", StringTools.formatToString(blogDraftModel.getDraftPlanPublishTime()));
			result.setBody(body);
			
			log.info("BlogDraftController.getUserDraftInfo：查询个人草稿详情成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogDraftController.getUserDraftInfo", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getTeamDraftInfo  
	 * @Description: 查询班组草稿详情
	 * @param getTeamDraftInfoVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getTeamDraftInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getTeamDraftInfo(@RequireValid GetTeamDraftInfoVO getTeamDraftInfoVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogDraftController.getTeamDraftInfo：查询班组草稿详情");
			
			// 获取参数
			String draftCode = getTeamDraftInfoVO.getDraftCode(); // 草稿编号
			String userCode = getTeamDraftInfoVO.getUserCode(); // 人员编号
			String teamCode = getTeamDraftInfoVO.getTeamCode(); // 班组编号
			
			// 权限校验
			boolean isTeamLeader = iTeamMemberService.isTeamLeader(teamCode, userCode);
			if (!isTeamLeader) {
				log.error("BlogDraftController.getTeamDraftInfo：操作权限不足");
				throw BusinessException.build("COMMON_403");
			}
			
			// 查询参数
			BlogDraftVO blogDraftVO = new BlogDraftVO();
			blogDraftVO.setUserCode(userCode); // 人员编号
			blogDraftVO.setDraftCode(draftCode); // 草稿编号
			blogDraftVO.setTeamCode(teamCode); // 班组编号
			blogDraftVO.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_TEAM); // 从属关系
			
			// 查询草稿详情
			BlogDraftModel blogDraftModel = blogDraftService.getDraftInfo(blogDraftVO);
			if (blogDraftModel == null) {
				result = new ResultJSON("COMMON_200");
				JSONObject body = new JSONObject();
				result.setBody(body);
				
				log.info("BlogDraftController.getUserDraftInfo：查询班组草稿详情成功");
				return result;
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("draftCode", StringTools.formatToString(blogDraftModel.getDraftCode()));
			body.put("draftTitle", StringTools.formatToString(blogDraftModel.getDraftTitle()));
			body.put("draftTags", StringTools.formatToString(blogDraftModel.getDraftTags()));
			body.put("classCode", StringTools.formatToString(blogDraftModel.getClassCode()));
			body.put("draftContentInfo", StringTools.formatToString(blogDraftModel.getDraftContentInfo()));
			body.put("draftPlanPublishTime", StringTools.formatToString(blogDraftModel.getDraftPlanPublishTime()));
			result.setBody(body);
			
			log.info("BlogDraftController.getTeamDraftInfo：查询班组草稿详情成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogDraftController.getTeamDraftInfo", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: saveUserDraft  
	 * @Description: 保存个人草稿信息
	 * @param saveUserDraftVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="saveUserDraft",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON saveUserDraft(@RequireValid SaveUserDraftVO saveUserDraftVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogDraftController.saveUserDraft：保存个人草稿信息");
			
			// 获取参数信息
			String userCode = saveUserDraftVO.getUserCode(); // 人员信息
			String draftCode = saveUserDraftVO.getDraftCode(); // 草稿编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogDraftController.saveUserDraft：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			
			// 判断是新增还是修改
			boolean saveFlag = true;
			if (StringUtils.isBlank(draftCode)) {
				// 草稿编号
				draftCode = UUIDHelper.getUUID();
				
				// 草稿信息
				BlogDraft blogDraft = new BlogDraft();
				blogDraft.setDraftCode(draftCode); // 草稿编号
				blogDraft.setTenantCode(sysUserInfo.getTenantCode()); // 租户编号
				blogDraft.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode()); // 组织机构编号
				blogDraft.setCreateTime(time); // 创建时间
				blogDraft.setModifyTime(time); // 修改时间
				blogDraft.setUserCode(userCode); // 发布人编号
				blogDraft.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_USER); // 从属关系
				blogDraft.setDraftTitle(saveUserDraftVO.getDraftTitle()); // 标题
				blogDraft.setClassCode(saveUserDraftVO.getClassCode()); // 分类编号
				blogDraft.setDraftTags(saveUserDraftVO.getDraftTags());; // 标签
				if (StringUtils.isBlank(saveUserDraftVO.getDraftPlanPublishTime()) || "0".equals(saveUserDraftVO.getDraftPlanPublishTime())) {
					blogDraft.setDraftPlanPublishTime(0L);
				} else {
					blogDraft.setDraftPlanPublishTime(Long.valueOf(saveUserDraftVO.getDraftPlanPublishTime())); // 定时发布时间
				}
				
				// 草稿正文信息
				BlogDraftContent blogDraftContent = new BlogDraftContent();
				blogDraftContent.setDraftContentCode(UUIDHelper.getUUID()); // 内容编号
				blogDraftContent.setTenantCode(sysUserInfo.getTenantCode()); // 租户编号
				blogDraftContent.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode()); // 组织机构编号
				blogDraftContent.setCreateTime(time); // 创建时间
				blogDraftContent.setModifyTime(time); // 修改时间
				blogDraftContent.setDraftCode(draftCode); // 草稿编号
				blogDraftContent.setDraftContentInfo(saveUserDraftVO.getDraftContentInfo()); // 正文内容
				
				// 保存信息
				saveFlag = blogDraftService.saveDraft(blogDraft, blogDraftContent);
			} else {
				// 查询参数
				BlogDraftVO blogDraftVO = new BlogDraftVO();
				blogDraftVO.setDraftCode(draftCode); // 草稿编号
				blogDraftVO.setUserCode(userCode); // 发布人编号
				blogDraftVO.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_USER); // 从属关系
				blogDraftVO.setDraftTitle(saveUserDraftVO.getDraftTitle()); // 标题
				blogDraftVO.setClassCode(saveUserDraftVO.getClassCode()); // 分类编号
				blogDraftVO.setDraftTags(saveUserDraftVO.getDraftTags());; // 标签
				if (StringUtils.isBlank(saveUserDraftVO.getDraftPlanPublishTime()) || "0".equals(saveUserDraftVO.getDraftPlanPublishTime())) {
					blogDraftVO.setDraftPlanPublishTime(0L);
				} else {
					blogDraftVO.setDraftPlanPublishTime(Long.valueOf(saveUserDraftVO.getDraftPlanPublishTime())); // 定时发布时间
				}
				blogDraftVO.setDraftContentInfo(saveUserDraftVO.getDraftContentInfo()); // 正文内容
				blogDraftVO.setModifyTime(time); // 修改时间
				
				// 修改信息
				saveFlag = blogDraftService.updateDraft(blogDraftVO);
			}
			
			// 判断保存结果
			if (!saveFlag) {
				log.error("BlogDraftController.saveUserDraft：保存失败");
				throw BusinessException.build("BLOG_13002", "保存");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("draftCode", draftCode);
			result.setBody(body);
			
			log.info("BlogDraftController.saveUserDraft：保存个人草稿信息成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogDraftController.saveUserDraft", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: saveTeamDraft  
	 * @Description: 保存班组草稿信息
	 * @param saveTeamDraftVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="saveTeamDraft",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON saveTeamDraft(@RequireValid SaveTeamDraftVO saveTeamDraftVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogDraftController.saveTeamDraft：保存班组草稿信息");
			
			// 获取参数信息
			String userCode = saveTeamDraftVO.getUserCode(); // 人员编号
			String teamCode = saveTeamDraftVO.getTeamCode(); // 班组编号
			String draftCode = saveTeamDraftVO.getDraftCode(); // 草稿编号
			
			// 权限校验
			boolean isTeamLeader = iTeamMemberService.isTeamLeader(teamCode, userCode);
			if (!isTeamLeader) {
				log.error("BlogDraftController.saveTeamDraft：操作权限不足");
				throw BusinessException.build("COMMON_403");
			}
			
			// 获取班组信息
			TeamInfo teamInfo = iTeamInfoService.selectByCode(teamCode);
			if (teamInfo == null) {
				log.error("BlogDraftController.saveTeamDraft：获取不到班组信息teamCode=" + teamCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用字段
			long time = new Date().getTime(); // 当前时间
			
			// 判断是新增还是修改
			boolean saveFlag = true;
			if (StringUtils.isBlank(draftCode)) {
				// 草稿编号
				draftCode = UUIDHelper.getUUID();
				
				// 草稿信息
				BlogDraft blogDraft = new BlogDraft();
				blogDraft.setDraftCode(draftCode); // 草稿编号
				blogDraft.setTenantCode(teamInfo.getTenantCode()); // 租户编号
				blogDraft.setOrgCode(teamInfo.getOrgCode()); // 组织机构编号
				blogDraft.setCreateTime(time); // 创建时间
				blogDraft.setModifyTime(time); // 修改时间
				blogDraft.setUserCode(userCode); // 发布人编号
				blogDraft.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_TEAM); // 从属关系
				blogDraft.setTeamCode(saveTeamDraftVO.getTeamCode()); // 班组编号
				blogDraft.setDraftTitle(saveTeamDraftVO.getDraftTitle()); // 标题
				blogDraft.setClassCode(saveTeamDraftVO.getClassCode()); // 分类编号
				blogDraft.setDraftTags(saveTeamDraftVO.getDraftTags());; // 标签
				if (StringUtils.isBlank(saveTeamDraftVO.getDraftPlanPublishTime()) || "0".equals(saveTeamDraftVO.getDraftPlanPublishTime())) {
					blogDraft.setDraftPlanPublishTime(0L);
				} else {
					blogDraft.setDraftPlanPublishTime(Long.valueOf(saveTeamDraftVO.getDraftPlanPublishTime())); // 定时发布时间
				}
				
				// 草稿正文信息
				BlogDraftContent blogDraftContent = new BlogDraftContent();
				blogDraftContent.setDraftContentCode(UUIDHelper.getUUID()); // 内容编号
				blogDraftContent.setTenantCode(teamInfo.getTenantCode()); // 租户编号
				blogDraftContent.setOrgCode(teamInfo.getOrgCode()); // 组织机构编号
				blogDraftContent.setCreateTime(time); // 创建时间
				blogDraftContent.setModifyTime(time); // 修改时间
				blogDraftContent.setDraftCode(draftCode); // 草稿编号
				blogDraftContent.setDraftContentInfo(saveTeamDraftVO.getDraftContentInfo()); // 正文内容
				
				// 保存信息
				saveFlag = blogDraftService.saveDraft(blogDraft, blogDraftContent);
			} else {
				// 查询参数
				BlogDraftVO blogDraftVO = new BlogDraftVO();
				blogDraftVO.setDraftCode(draftCode); // 草稿编号
				blogDraftVO.setUserCode(userCode); // 发布人编号
				blogDraftVO.setTeamCode(teamCode); // 发布人编号
				blogDraftVO.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_TEAM); // 从属关系
				blogDraftVO.setDraftTitle(saveTeamDraftVO.getDraftTitle()); // 标题
				blogDraftVO.setClassCode(saveTeamDraftVO.getClassCode()); // 分类编号
				blogDraftVO.setDraftTags(saveTeamDraftVO.getDraftTags());; // 标签
				if (StringUtils.isBlank(saveTeamDraftVO.getDraftPlanPublishTime()) || "0".equals(saveTeamDraftVO.getDraftPlanPublishTime())) {
					blogDraftVO.setDraftPlanPublishTime(0L);
				} else {
					blogDraftVO.setDraftPlanPublishTime(Long.valueOf(saveTeamDraftVO.getDraftPlanPublishTime())); // 定时发布时间
				}
				blogDraftVO.setDraftContentInfo(saveTeamDraftVO.getDraftContentInfo()); // 正文内容
				blogDraftVO.setModifyTime(time); // 修改时间
				
				// 修改信息
				saveFlag = blogDraftService.updateDraft(blogDraftVO);
			}
			
			// 判断保存结果
			if (!saveFlag) {
				log.error("BlogDraftController.saveTeamDraft：保存失败");
				throw BusinessException.build("BLOG_13002", "保存");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("draftCode", draftCode);
			result.setBody(body);
			
			log.info("BlogDraftController.saveTeamDraft：保存班组草稿信息成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogDraftController.saveTeamDraft", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: delUserDraft  
	 * @Description: 删除个人草稿
	 * @param delUserDraftVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="delUserDraft",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON delUserDraft(@RequireValid DelUserDraftVO delUserDraftVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogDraftController.delUserDraft：删除个人草稿");
			
			// 查询参数
			BlogDraftVO blogDraftVO = new BlogDraftVO();
			blogDraftVO.setDraftCode(delUserDraftVO.getDraftCode()); // 草稿编号
			blogDraftVO.setUserCode(delUserDraftVO.getUserCode()); // 人员编号
			blogDraftVO.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_USER); // 从属关系
			
			// 删除
			boolean deleteFlag = blogDraftService.deleteDraft(blogDraftVO);
			if (!deleteFlag) {
				log.error("BlogDraftController.delUserDraft：删除失败");
				throw BusinessException.build("BLOG_13002", "删除");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogDraftController.delUserDraft：删除个人草稿成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogDraftController.delUserDraft", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: delTeamDraft  
	 * @Description: 删除班组草稿
	 * @param delTeamDraftVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="delTeamDraft",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON delTeamDraft(@RequireValid DelTeamDraftVO delTeamDraftVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogDraftController.delTeamDraft：删除班组草稿");
			
			// 获取参数
			String userCode = delTeamDraftVO.getUserCode(); // 人员编号
			String teamCode = delTeamDraftVO.getTeamCode(); // 班组编号
			
			// 权限校验
			boolean isTeamLeader = iTeamMemberService.isTeamLeader(teamCode, userCode);
			if (!isTeamLeader) {
				log.error("BlogDraftController.delTeamDraft：操作权限不足");
				throw BusinessException.build("COMMON_403");
			}
			
			// 查询参数
			BlogDraftVO blogDraftVO = new BlogDraftVO();
			blogDraftVO.setDraftCode(delTeamDraftVO.getDraftCode()); // 草稿编号
			blogDraftVO.setUserCode(userCode); // 人员编号
			blogDraftVO.setTeamCode(teamCode); // 班组编号
			blogDraftVO.setDraftDepend(BlogConstants.BLOG_DRAFT_DEPEND_TEAM); // 从属关系
			
			// 删除
			boolean deleteFlag = blogDraftService.deleteDraft(blogDraftVO);
			if (!deleteFlag) {
				log.error("BlogDraftController.delTeamDraft：删除失败");
				throw BusinessException.build("BLOG_13002", "删除");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogDraftController.delTeamDraft：删除班组草稿成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogDraftController.delTeamDraft", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}

}
