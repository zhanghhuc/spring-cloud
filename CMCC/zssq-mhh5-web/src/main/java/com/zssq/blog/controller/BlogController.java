package com.zssq.blog.controller;

import java.util.ArrayList;
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
import com.zssq.blog.vo.CollectBlogVO;
import com.zssq.blog.vo.DelTeamBlogVO;
import com.zssq.blog.vo.DelUserBlogVO;
import com.zssq.blog.vo.ForwardUserBlogVO;
import com.zssq.blog.vo.GetBlogInfoVO;
import com.zssq.blog.vo.GetCollectBlogListVO;
import com.zssq.blog.vo.GetSubBlogListVO;
import com.zssq.blog.vo.GetSubStatusVO;
import com.zssq.blog.vo.GetUserBlogListVO;
import com.zssq.blog.vo.LikeBlogVO;
import com.zssq.blog.vo.PublishTeamBlogVO;
import com.zssq.blog.vo.PublishUserBlogVO;
import com.zssq.blog.vo.ShareBlogVO;
import com.zssq.blog.vo.SubBlogVO;
import com.zssq.constants.BlogConstants;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.FilingConstants;
import com.zssq.constants.MsgClassConstants;
import com.zssq.constants.MsgTopicConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.constants.SolrCoreConstants;
import com.zssq.constants.StatisticConstants;
import com.zssq.constants.TeamConstants;
import com.zssq.dao.model.BlogModel;
import com.zssq.dao.pojo.BlogCollect;
import com.zssq.dao.pojo.BlogContent;
import com.zssq.dao.pojo.BlogInfo;
import com.zssq.dao.pojo.BlogLike;
import com.zssq.dao.pojo.BlogShare;
import com.zssq.dao.pojo.BlogSubscribe;
import com.zssq.dao.pojo.MessageIntegral;
import com.zssq.dao.pojo.RelationCollect;
import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RelationDynamicTeamRel;
import com.zssq.dao.pojo.RelationLike;
import com.zssq.dao.pojo.RelationQuality;
import com.zssq.dao.pojo.RelationSubjectInfo;
import com.zssq.dao.pojo.StatisticCommon;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.dao.pojo.UserState;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.model.MessagePraiseAddModel;
import com.zssq.model.MessageSubscribeAddModel;
import com.zssq.pojo.ResultJSON;
import com.zssq.search.service.SolrQueryService;
import com.zssq.service.BlogClassService;
import com.zssq.service.BlogOperateService;
import com.zssq.service.BlogService;
import com.zssq.service.BlogSubService;
import com.zssq.service.IStatisticService;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamElectService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.ITeamMemberService;
import com.zssq.service.IUserRelationService;
import com.zssq.service.RelationThirdDynamicService;
import com.zssq.service.RelationThirdOperateService;
import com.zssq.service.RelationThirdSubjectService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.BlogClassVO;
import com.zssq.vo.BlogOperateVO;
import com.zssq.vo.BlogSubVO;
import com.zssq.vo.BlogThirdDataVO;
import com.zssq.vo.BlogVO;
import com.zssq.vo.RelationDataVO;
import com.zssq.vo.RelationDynamicVO;
import com.zssq.vo.RelationOperateVO;

/**
 * 
 * @ClassName: BlogController  
 * @Description: 博客  
 * @author guoyang  
 * @date 2017年5月16日  
 *
 */
@RequestMapping("/blog")
@Controller
public class BlogController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogOperateService blogOperateService;
	@Autowired
	private BlogSubService blogSubService;
	@Autowired
	private RelationThirdDynamicService relationThirdDynamicService;
	@Autowired
	private RelationThirdOperateService relationThirdOperateService;
	@Autowired
	private RelationThirdSubjectService relationThirdSubjectService;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private ITeamInfoService iTeamInfoService;
	@Autowired
	private ITeamMemberService iTeamMemberService;
	@Autowired
	private ITeamElectService iTeamElectService;
	@Autowired
	private ISysOrgService iSysOrgService;
	@Autowired
	private IUserRelationService iUserRelationService;
	@Autowired
	private IStatisticService iStatisticService;
	@Autowired
	private BlogClassService blogClassService;
    @Autowired
    private SolrQueryService solrQueryService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	protected KafkaProducerTemplate producerTeplate;
	
	/**
	 * 
	 * @Title: getUserBlogList  
	 * @Description: 查询个人博客列表
	 * @param getUserBlogListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getUserBlogList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getUserBlogList(@RequireValid GetUserBlogListVO getUserBlogListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.getUserBlogList：查询个人博客列表");
			
			// 获取参数
			String pageSize = getUserBlogListVO.getPageSize(); // 每页条数
			String pageNo = getUserBlogListVO.getPageNo(); // 页码
			String queryTime = getUserBlogListVO.getQueryTime(); // 首次查询时间
			String blogUserCode = getUserBlogListVO.getBlogUserCode(); // 博客发布人编号
			String userCode = getUserBlogListVO.getUserCode(); // 当前登录用户编号
			
			// 人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(blogUserCode);
			if (sysUserInfo == null) {
				log.error("BlogController.getUserBlogList：获取不到人员信息userCode=" + blogUserCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			BlogVO blogVO = new BlogVO();
			blogVO.setUserCode(blogUserCode); // 博客发布人编号
			blogVO.setBlogDepend(BlogConstants.BLOG_DEPEND_USER); // 从属关系
			blogVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			blogVO.setLoginUserCode(userCode); // 当前登录用户编号
			
			// 查询
			PageBean pageBean = blogService.getBlogList(pageParam, blogVO);
			if (pageBean == null) {
				log.error("BlogController.getUserBlogList：查询失败");
				throw BusinessException.build("BLOG_13002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					BlogModel blogModel = (BlogModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("blogCode", StringTools.formatToString(blogModel.getBlogCode()));
					jo.put("dynamicCode", StringTools.formatToString(blogModel.getDynamicCode()));
					jo.put("blogSource", StringTools.formatToString(blogModel.getBlogSource()));
					
					// 转发时来源信息
					if (BlogConstants.BLOG_SOURCE_FORWARD.equals(blogModel.getBlogSource())) {
						// 获取来源发布人信息
						SysUserInfo sourceUserInfo = iSysUserService.selectByCode(blogModel.getSourceUserCode());
						jo.put("sourceBlogCode", StringTools.formatToString(blogModel.getSourceBlogCode()));
						jo.put("sourceUserCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserCode()));
						jo.put("sourceUserName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getUserName()));
						jo.put("sourceBlogDepend", StringTools.formatToString(blogModel.getSourceBlogDepend()));
						if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogModel.getSourceBlogDepend())) {
							// 获取班组信息
							TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(blogModel.getSourceTeamCode());
							jo.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamCode()));
							jo.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
							// 获取组织信息
							SysOrgInfo sysOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo == null ? "" : sourceTeamInfo.getOrgCode());
							jo.put("sourceOrgCode", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
							jo.put("sourceOrgName", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
						} else {
							jo.put("sourceTeamCode", "");
							jo.put("sourceTeamName", "");
							jo.put("sourceOrgName", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgCode()));
							jo.put("sourceOrgCode", sourceUserInfo == null ? "" : StringTools.formatToString(sourceUserInfo.getManageOrgInfo().getSysOrgFullname()));
						}
					} else {
						jo.put("sourceBlogCode", "");
						jo.put("sourceUserCode", "");
						jo.put("sourceUserName", "");
						jo.put("sourceBlogDepend", "");
						jo.put("sourceTeamCode", "");
						jo.put("sourceTeamName", "");
						jo.put("sourceOrgCode", "");
						jo.put("sourceOrgName", "");
					}
					
					jo.put("blogTitle", StringTools.formatToString(blogModel.getBlogTitle()));
					jo.put("blogDigest", StringTools.formatToString(blogModel.getBlogDigest()));
					jo.put("blogLogo", StringTools.formatToString(blogModel.getBlogLogo()));
					jo.put("blogPublishTime", StringTools.formatToString(blogModel.getBlogPublishTime()));
					jo.put("teamQualityNum", StringTools.formatToString(blogModel.getTeamQualityNum()));
					jo.put("groupQualityNum", StringTools.formatToString(blogModel.getGroupQualityNum()));
					jo.put("provinceQualityNum", StringTools.formatToString(blogModel.getProvinceQualityNum()));
					jo.put("cityQualityNum", StringTools.formatToString(blogModel.getCityQualityNum()));
					jo.put("readNum", StringTools.formatToString(blogModel.getReadNum()));
					jo.put("likeNum", StringTools.formatToString(blogModel.getLikeNum()));
					jo.put("collectNum", StringTools.formatToString(blogModel.getCollectNum()));
					jo.put("commentNum", StringTools.formatToString(blogModel.getCommentNum()));
					jo.put("forwardNum", StringTools.formatToString(blogModel.getForwardNum()));
					jo.put("isLike", StringTools.formatToString(blogModel.getIsLike()));
					jo.put("isCollect", StringTools.formatToString(blogModel.getIsCollect()));
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("userCode", StringTools.formatToString(sysUserInfo.getUserCode()));
			body.put("userName", StringTools.formatToString(sysUserInfo.getUserName()));
			body.put("userPhotoUrl", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
			body.put("orgCode", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
			body.put("orgName", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
			body.put("total", pageBean.getTotalCount());
			body.put("blogList", ja);
			result.setBody(body);
			
			log.info("BlogController.getUserBlogList：查询个人博客列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.getUserBlogList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getBlogInfo  
	 * @Description: 查询博客详情
	 * @param getBlogInfoVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getBlogInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getBlogInfo(@RequireValid GetBlogInfoVO getBlogInfoVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.getBlogInfo：查询博客详情");
			
			// 获取参数
			String blogCode = getBlogInfoVO.getBlogCode(); // 博客编号
			String userCode = getBlogInfoVO.getUserCode(); // 人员编号
			
			// 拼接参数
			BlogVO blogVO = new BlogVO();
			blogVO.setLoginUserCode(userCode); // 当前登录用户编号
			blogVO.setBlogCode(blogCode); // 人员编号
			
			// 查询
			BlogModel blogModel = blogService.getBlogInfo(blogVO);
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			if (blogModel != null) {
				body.put("blogCode", StringTools.formatToString(blogModel.getBlogCode()));
				body.put("dynamicCode", StringTools.formatToString(blogModel.getDynamicCode()));
				
				// 获取发布人信息
				SysUserInfo sysUserInfo = iSysUserService.selectByCode(blogModel.getUserCode());
				body.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
				body.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
				body.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
				body.put("blogDepend", StringTools.formatToString(blogModel.getBlogDepend()));
				if (BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(blogModel.getBlogDepend())) {
					// 获取班组信息
					TeamInfo teamInfo = iTeamInfoService.selectByCode(blogModel.getTeamCode());
					body.put("teamCode", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamCode()));
					body.put("teamName", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamName()));
					// 获取组织信息
					SysOrgInfo sysOrgInfo = iSysOrgService.selectByCode(teamInfo == null ? "" : teamInfo.getOrgCode());
					body.put("orgCode", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
					body.put("orgName", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
				} else {
					body.put("teamCode", "");
					body.put("teamName", "");
					body.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
					body.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
				}
				
				body.put("blogSource", StringTools.formatToString(blogModel.getBlogSource()));
				
				// 转发时来源信息
				if (BlogConstants.BLOG_SOURCE_FORWARD.equals(blogModel.getBlogSource())) {
					// 获取来源发布人信息
					SysUserInfo sourceSysUserInfo = iSysUserService.selectByCode(blogModel.getSourceUserCode());
					body.put("sourceUserCode", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getUserCode()));
					body.put("sourceUserName", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getUserName()));
					body.put("sourceBlogDepend", StringTools.formatToString(blogModel.getSourceBlogDepend()));
					if (blogModel.getSourceBlogDepend() != null && blogModel.getSourceBlogDepend().equals(BlogConstants.BLOG_DEPEND_TEAM)) {
						// 获取班组信息
						TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(blogModel.getSourceTeamCode());
						body.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getUserCode()));
						body.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
						SysOrgInfo sourceSysOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo == null ? "" : sourceTeamInfo.getOrgCode());
						body.put("sourceOrgCode", sourceSysOrgInfo == null ? "" : StringTools.formatToString(sourceSysOrgInfo.getSysOrgCode()));
						body.put("sourceOrgName", sourceSysOrgInfo == null ? "" : StringTools.formatToString(sourceSysOrgInfo.getSysOrgFullname()));
					} else {
						body.put("sourceTeamCode", "");
						body.put("sourceTeamName", "");
						body.put("sourceOrgName", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getManageOrgInfo().getSysOrgCode()));
						body.put("sourceOrgCode", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getManageOrgInfo().getSysOrgFullname()));
					}
				} else {
					body.put("sourceUserCode", "");
					body.put("sourceUserName", "");
					body.put("sourceBlogDepend", "");
					body.put("sourceTeamCode", "");
					body.put("sourceTeamName", "");
					body.put("sourceOrgCode", "");
					body.put("sourceOrgName", "");
				}
				
				body.put("blogTitle", StringTools.formatToString(blogModel.getBlogTitle()));
				body.put("blogTags", StringTools.formatToString(blogModel.getBlogTags()));
				body.put("className", StringTools.formatToString(blogModel.getClassName()));
				body.put("contentInfo", StringTools.formatToString(blogModel.getContentInfo()));
				body.put("blogPublishTime", StringTools.formatToString(blogModel.getBlogPublishTime()));
				body.put("readNum", StringTools.formatToString(blogModel.getReadNum()));
				body.put("likeNum", StringTools.formatToString(blogModel.getLikeNum()));
				body.put("collectNum", StringTools.formatToString(blogModel.getCollectNum()));
				body.put("commentNum", StringTools.formatToString(blogModel.getCommentNum()));
				body.put("forwardNum", StringTools.formatToString(blogModel.getForwardNum()));
				body.put("shareNum", StringTools.formatToString(blogModel.getShareNum()));
				body.put("isLike", StringTools.formatToString(blogModel.getIsLike()));
				body.put("isCollect", StringTools.formatToString(blogModel.getIsCollect()));
			}
			result.setBody(body);
			
			// 当前时间
			long time = new Date().getTime();
			
			// 修改博客浏览量
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			blogThirdDataVO.setSubjectCode(blogCode);
			blogThirdDataVO.setUpdateNumber(1);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_READ);
			blogThirdDataVO.setModifyTime(time);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 修改关系服务博客浏览量
			RelationDataVO relationDataVO = new RelationDataVO();
			relationDataVO.setSubjectCode(blogCode);
			relationDataVO.setUpdateNumber(1);
			relationDataVO.setModifyTime(time);
			relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_READ);
			producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
			
			log.info("BlogController.getBlogInfo：查询博客详情成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.getBlogInfo", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: likeBlog  
	 * @Description: 点赞/取消点赞博客
	 * @param likeBlogVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="likeBlog",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON likeBlog(@RequireValid LikeBlogVO likeBlogVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.likeBlog：点赞/取消点赞博客");
			
			// 获取参数
			String blogCode = likeBlogVO.getBlogCode(); // 博客编号
			String actionClass = likeBlogVO.getActionClass(); // 操作类型
			String userCode = likeBlogVO.getUserCode(); // 人员编号
			
			// 查询博客信息
			BlogVO blogVO = new BlogVO();
			blogVO.setBlogCode(blogCode); // 博客编号
			BlogInfo blogInfo = blogService.getBlogMiniInfo(blogVO);
			if (blogInfo == null || BlogConstants.BLOG_YES.equals(blogInfo.getBlogIsDelete())
					 || BlogConstants.BLOG_YES.equals(blogInfo.getBlogIsShield())) {
				log.error("BlogController.likeBlog：获取不到博客信息blogCode=" + blogCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 查询人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogController.likeBlog：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			
			// 判断是点赞还是取消点赞
			if (BlogConstants.BLOG_ACTION_YES.toString().equals(actionClass)) {
				/* 点赞 */
				// 判断黑名单关系
				if (BlogConstants.BLOG_DEPEND_USER.equals(blogInfo.getBlogDepend())) {
					UserState userState = iUserRelationService.selectUserState(blogInfo.getUserCode(), userCode);
					if (userState == null || userState.getIsBlack() == null) {
						log.error("BlogController.likeBlog：获取不到人员关系信息");
						throw BusinessException.build("COMMON_400");
					}
					if (FilingConstants.BOOLEAN_TRUE.equals(userState.getIsBlack())) {
						log.error("BlogController.likeBlog：操作权限不足");
						throw BusinessException.build("COMMON_403");
					}
				}
				
				// 博客点赞信息
				BlogLike blogLike = new BlogLike();
				blogLike.setLikeCode(UUIDHelper.getUUID());
				blogLike.setTenantCode(sysUserInfo.getTenantCode());
				blogLike.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				blogLike.setCreateTime(time);
				blogLike.setModifyTime(time);
				blogLike.setBlogCode(blogCode);
				blogLike.setSubjectCode(blogCode);
				blogLike.setSubjectClass(BlogConstants.BLOG_LIKE_SUBJECT_BLOG);
				blogLike.setUserCode(userCode);
				
				// 内容点赞信息
				RelationLike relationLike = new RelationLike();
				relationLike.setLikeCode(UUIDHelper.getUUID());
				relationLike.setTenantCode(sysUserInfo.getTenantCode());
				relationLike.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				relationLike.setCreateTime(time);
				relationLike.setModifyTime(time);
				relationLike.setSubjectCode(blogCode);
				relationLike.setSubjectClass(RelationConstants.RELATION_LIKE_BLOG);
				relationLike.setUserCode(userCode);
				
				// 保存点赞信息
				boolean saveFlag = blogOperateService.saveLike(blogLike);
				if (!saveFlag) {
					log.error("BlogController.likeBlog：操作失败");
					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 关系服务保存点赞信息
				saveFlag = relationThirdOperateService.saveLike(relationLike);
				if (!saveFlag) {
					log.error("BlogController.likeBlog：操作失败");
//					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 更新博客点赞量
				BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
				blogThirdDataVO.setSubjectCode(blogCode);
				blogThirdDataVO.setUpdateNumber(1);
				blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_LIKE);
				blogThirdDataVO.setModifyTime(time);
				producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
				
				// 更新关系服务中点赞量
				RelationDataVO relationDataVO = new RelationDataVO();
				relationDataVO.setSubjectCode(blogCode);
				relationDataVO.setUpdateNumber(1);
				relationDataVO.setModifyTime(time);
				relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_LIKE);
				producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
				
				// 发布消息
				MessagePraiseAddModel messagePraiseAddModel = new MessagePraiseAddModel();
				messagePraiseAddModel.setUserCode(blogInfo.getUserCode());
				messagePraiseAddModel.setPraiseUserCode(userCode);
				messagePraiseAddModel.setPraiseType(MsgClassConstants.PRAISE_BOLG);
				messagePraiseAddModel.setOriginalCode(blogCode);
				messagePraiseAddModel.setContent(blogInfo.getBlogTitle());
				messagePraiseAddModel.setTenantCode(sysUserInfo.getTenantCode());
				messagePraiseAddModel.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(MsgTopicConstants.TOPIC_PRAISE, JSONObject.toJSONString(messagePraiseAddModel));
				
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_ADMIRE_PUBLISH);
				MessageIntegral.setAccountCode(userCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			} else if (BlogConstants.BLOG_ACTION_NO.toString().equals(actionClass)) {
				/* 取消点赞 */
				// 拼接参数
				BlogOperateVO blogOperateVO = new BlogOperateVO();
				blogOperateVO.setSubjectCode(blogCode); // 内容编号
				blogOperateVO.setSubjectClass(BlogConstants.BLOG_LIKE_SUBJECT_BLOG); // 内容类型
				blogOperateVO.setUserCode(userCode); // 人员编号
				
				// 拼接关系参数
				RelationOperateVO relationOperateVO = new RelationOperateVO();
				relationOperateVO.setSubjectCode(blogCode); // 内容编号
				relationOperateVO.setUserCode(userCode); // 人员编号
				
				// 取消点赞
				boolean deleteFlag = blogOperateService.deleteLike(blogOperateVO);
				if (!deleteFlag) {
					log.error("BlogController.likeBlog：操作失败");
					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 关系服务取消点赞信息
				deleteFlag = relationThirdOperateService.deleteLike(relationOperateVO);
				if (!deleteFlag) {
					log.error("BlogController.likeBlog：操作失败");
//					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 更新博客点赞量
				BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
				blogThirdDataVO.setSubjectCode(blogCode);
				blogThirdDataVO.setUpdateNumber(-1);
				blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_LIKE);
				blogThirdDataVO.setModifyTime(time);
				producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
				
				// 更新关系服务中点赞量
				RelationDataVO relationDataVO = new RelationDataVO();
				relationDataVO.setSubjectCode(blogCode);
				relationDataVO.setUpdateNumber(-1);
				relationDataVO.setModifyTime(time);
				relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_LIKE);
				producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
				
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_ADMIRE_DEL);
				MessageIntegral.setAccountCode(userCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			} else {
				log.error("BlogController.likeBlog：actionClass错误");
				throw BusinessException.build("COMMON_402", "actionClass");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogController.likeBlog：点赞/取消点赞博客成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.likeBlog", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: collectBlog  
	 * @Description: 收藏/取消收藏
	 * @param collectBlogVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="collectBlog",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON collectBlog(@RequireValid CollectBlogVO collectBlogVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.collectBlog：收藏/取消收藏博客");
			
			// 获取参数
			String blogCode = collectBlogVO.getBlogCode(); // 博客编号
			String actionClass = collectBlogVO.getActionClass(); // 操作类型
			String userCode = collectBlogVO.getUserCode(); // 人员编号
			
			// 查询博客信息
			BlogVO blogVO = new BlogVO();
			blogVO.setBlogCode(blogCode); // 博客编号
			BlogInfo blogInfo = blogService.getBlogMiniInfo(blogVO);
			if (blogInfo == null || BlogConstants.BLOG_YES.equals(blogInfo.getBlogIsDelete())
					 || BlogConstants.BLOG_YES.equals(blogInfo.getBlogIsShield())) {
				log.error("BlogController.collectBlog：获取不到博客信息blogCode=" + blogCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 查询人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogController.collectBlog：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			
			// 判断是收藏还是取消收藏
			if (BlogConstants.BLOG_ACTION_YES.toString().equals(actionClass)) {
				/* 收藏 */
				// 判断黑名单关系
				if (BlogConstants.BLOG_DEPEND_USER.equals(blogInfo.getBlogDepend())) {
					UserState userState = iUserRelationService.selectUserState(blogInfo.getUserCode(), userCode);
					if (userState == null || userState.getIsBlack() == null) {
						log.error("BlogController.collectBlog：获取不到人员关系信息");
						throw BusinessException.build("COMMON_400");
					}
					if (FilingConstants.BOOLEAN_TRUE.equals(userState.getIsBlack())) {
						log.error("BlogController.collectBlog：操作权限不足");
						throw BusinessException.build("COMMON_403");
					}
				}
				
				// 收藏信息
				BlogCollect blogCollect = new BlogCollect();
				blogCollect.setCollectCode(UUIDHelper.getUUID());
				blogCollect.setTenantCode(sysUserInfo.getTenantCode());
				blogCollect.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				blogCollect.setCreateTime(time);
				blogCollect.setModifyTime(time);
				blogCollect.setBlogCode(blogCode);
				blogCollect.setUserCode(userCode);
				
				// 关系服务收藏信息
				RelationCollect relationCollect = new RelationCollect();
				relationCollect.setCollectCode(UUIDHelper.getUUID());
				relationCollect.setTenantCode(sysUserInfo.getTenantCode());
				relationCollect.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				relationCollect.setCreateTime(time);
				relationCollect.setModifyTime(time);
				relationCollect.setSubjectCode(blogCode);
				relationCollect.setSubjectClass(RelationConstants.RELATION_COLLECT_BLOG);
				relationCollect.setUserCode(userCode);
				
				// 保存收藏信息
				boolean saveFlag = blogOperateService.saveCollect(blogCollect);
				if (!saveFlag) {
					log.error("BlogController.collectBlog：操作失败");
					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 保存关系服务中收藏信息
				saveFlag = relationThirdOperateService.saveCollect(relationCollect);
				if (!saveFlag) {
					log.error("BlogController.collectBlog：操作失败");
//					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 更新博客收藏量
				BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
				blogThirdDataVO.setSubjectCode(blogCode);
				blogThirdDataVO.setUpdateNumber(1);
				blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_COLLECT);
				blogThirdDataVO.setModifyTime(time);
				producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
				
				// 更新关系服务中收藏量
				RelationDataVO relationDataVO = new RelationDataVO();
				relationDataVO.setSubjectCode(blogCode);
				relationDataVO.setUpdateNumber(1);
				relationDataVO.setModifyTime(time);
				relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_COLLECT);
				producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
				
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_CONTENT_COLLECT);
				MessageIntegral.setAccountCode(userCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			} else if (BlogConstants.BLOG_ACTION_NO.toString().equals(actionClass)) {
				/* 取消收藏 */
				// 拼接参数
				BlogOperateVO blogOperateVO = new BlogOperateVO();
				blogOperateVO.setBlogCode(blogCode); // 博客编号
				blogOperateVO.setUserCode(userCode); // 人员编号
				
				// 拼接关系参数
				RelationOperateVO relationOperateVO = new RelationOperateVO();
				relationOperateVO.setSubjectCode(blogCode); // 内容编号
				relationOperateVO.setUserCode(userCode); // 人员编号
				
				// 取消收藏
				boolean deleteFlag = blogOperateService.deleteCollect(blogOperateVO);
				if (!deleteFlag) {
					log.error("BlogController.collectBlog：操作失败");
					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 关系服务取消点赞信息
				deleteFlag = relationThirdOperateService.deleteCollect(relationOperateVO);
				if (!deleteFlag) {
					log.error("BlogController.collectBlog：操作失败");
//					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 更新博客收藏量
				BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
				blogThirdDataVO.setSubjectCode(blogCode);
				blogThirdDataVO.setUpdateNumber(-1);
				blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_COLLECT);
				blogThirdDataVO.setModifyTime(time);
				producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
				
				// 更新关系服务中收藏量
				RelationDataVO relationDataVO = new RelationDataVO();
				relationDataVO.setSubjectCode(blogCode);
				relationDataVO.setUpdateNumber(-1);
				relationDataVO.setModifyTime(time);
				relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_COLLECT);
				producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
				
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_CONTENT_COLLECTCANCEL);
				MessageIntegral.setAccountCode(userCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			} else {
				log.error("BlogController.collectBlog：actionClass错误");
				throw BusinessException.build("COMMON_402", "actionClass");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogController.collectBlog：收藏/取消收藏博客成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.collectBlog", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: subBlog  
	 * @Description: 订阅/取消订阅博客
	 * @param subBlogVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="subBlog",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON subBlog(@RequireValid SubBlogVO subBlogVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.subBlog：订阅/取消订阅博客");
			
			// 获取参数
			String actionClass = subBlogVO.getActionClass(); // 操作类型
			String userCode = subBlogVO.getUserCode(); // 人员编号
			String subClass = subBlogVO.getSubClass(); // 订阅类型
			String subUserCode = subBlogVO.getSubUserCode(); // 被订阅人编号
			String teamCode = subBlogVO.getTeamCode(); // 班组编号
			
			// 参数校验
			if (BlogConstants.BLOG_SUB_USER.toString().equals(subClass) && StringUtils.isBlank(subUserCode)) {
				log.error("BlogController.subBlog：subClass错误");
				throw BusinessException.build("COMMON_402", "subClass");
			} else if (BlogConstants.BLOG_SUB_TEAM.toString().equals(subClass) && StringUtils.isBlank(teamCode)) {
				log.error("BlogController.subBlog：subClass错误");
				throw BusinessException.build("COMMON_402", "subClass");
			}
			
			// 查询人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogController.subBlog：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			
			// 判断是订阅还是取消订阅
			if (BlogConstants.BLOG_ACTION_YES.toString().equals(actionClass)) {
				/* 订阅 */
				// 订阅信息
				BlogSubscribe blogSubscribe = new BlogSubscribe();
				blogSubscribe.setSubCode(UUIDHelper.getUUID());
				blogSubscribe.setTenantCode(sysUserInfo.getTenantCode());
				blogSubscribe.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				blogSubscribe.setCreateTime(time);
				blogSubscribe.setModifyTime(time);
				blogSubscribe.setUserCode(userCode);
				
				// 判断订阅类型
				if (BlogConstants.BLOG_SUB_USER.toString().equals(subClass)) {
					// 判断黑名单关系
					UserState userState = iUserRelationService.selectUserState(subUserCode, userCode);
					if (userState == null || userState.getIsBlack() == null) {
						log.error("BlogController.subBlog：获取不到人员关系信息");
						throw BusinessException.build("COMMON_400");
					}
					if (FilingConstants.BOOLEAN_TRUE.equals(userState.getIsBlack())) {
						log.error("BlogController.subBlog：操作权限不足");
						throw BusinessException.build("COMMON_403");
					}
					
					// 赋值
					blogSubscribe.setSubClass(BlogConstants.BLOG_SUB_USER);
					blogSubscribe.setSubUserCode(subUserCode);
					
				} else if (BlogConstants.BLOG_SUB_TEAM.toString().equals(subClass)) {
					// 赋值
					blogSubscribe.setSubClass(BlogConstants.BLOG_SUB_TEAM);
					blogSubscribe.setTeamCode(teamCode);
				}
				
				// 保存订阅信息
				boolean saveFlag = blogSubService.saveSubscribe(blogSubscribe);
				if (!saveFlag) {
					log.error("BlogController.subBlogVO：操作失败");
					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 只有订阅个人时需要发消息
				if (BlogConstants.BLOG_SUB_USER.toString().equals(subClass)) {
					// 发布消息
					MessageSubscribeAddModel messageSubscribeAddModel = new MessageSubscribeAddModel();
					messageSubscribeAddModel.setUserCode(subUserCode);
					messageSubscribeAddModel.setSubUserCode(userCode);
					messageSubscribeAddModel.setSubType(MsgClassConstants.SUBSCRIBE_BLOG);
					messageSubscribeAddModel.setTenantCode(sysUserInfo.getTenantCode());
					messageSubscribeAddModel.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
					producerTeplate.send(MsgTopicConstants.TOPIC_SUBSCRIBE, JSONObject.toJSONString(messageSubscribeAddModel));
				}
				
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_BLOG_SUBSCRIBE);
				MessageIntegral.setAccountCode(userCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			} else if (BlogConstants.BLOG_ACTION_NO.toString().equals(actionClass)) {
				/* 取消订阅 */
				// 拼接参数
				BlogSubVO blogSubVO = new BlogSubVO();
				blogSubVO.setUserCode(userCode); // 人员编号
				blogSubVO.setSubClass(Byte.valueOf(subClass)); // 订阅类型
				blogSubVO.setSubUserCode(subUserCode); // 被订阅人编号 
				blogSubVO.setTeamCode(teamCode); // 班组编号
				
				// 取消订阅
				boolean deleteFlag = blogSubService.deleteSubscribe(blogSubVO);
				if (!deleteFlag) {
					log.error("BlogController.subBlogVO：操作失败");
					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_BLOG_UNSUBSCRIBE);
				MessageIntegral.setAccountCode(userCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			} else {
				log.error("BlogController.subBlog：actionClass错误");
				throw BusinessException.build("COMMON_402", "actionClass");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogController.subBlog：订阅/取消订阅博客成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.subBlogVO", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @Title: shareBlog  
	 * @Description: 分享博客
	 * @param shareBlogVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="shareBlog",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON shareBlog(@RequireValid ShareBlogVO shareBlogVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.shareBlog：分享博客");
			
			// 获取参数
			String userCode = shareBlogVO.getUserCode(); // 人员编号
			String blogCode = shareBlogVO.getBlogCode(); // 博客编号
			
			// 查询博客信息
			BlogVO blogVO = new BlogVO();
			blogVO.setBlogCode(blogCode); // 博客编号
			BlogInfo blogInfo = blogService.getBlogMiniInfo(blogVO);
			if (blogInfo == null || BlogConstants.BLOG_YES.equals(blogInfo.getBlogIsDelete())
					 || BlogConstants.BLOG_YES.equals(blogInfo.getBlogIsShield())) {
				log.error("BlogController.shareBlog：获取不到博客信息blogCode=" + blogCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 查询人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogController.shareBlog：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 判断黑名单关系
			UserState userState = iUserRelationService.selectUserState(blogInfo.getUserCode(), userCode);
			if (userState == null || userState.getIsBlack() == null) {
				log.error("BlogController.shareBlog：获取不到人员关系信息");
				throw BusinessException.build("COMMON_400");
			}
			if (FilingConstants.BOOLEAN_TRUE.equals(userState.getIsBlack())) {
				log.error("BlogController.shareBlog：操作权限不足");
				throw BusinessException.build("COMMON_403");
			}
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			
			// 分享信息
			BlogShare blogShare = new BlogShare();
			blogShare.setShareCode(UUIDHelper.getUUID());
			blogShare.setTenantCode(sysUserInfo.getTenantCode());
			blogShare.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			blogShare.setCreateTime(time);
			blogShare.setModifyTime(time);
			blogShare.setBlogCode(blogCode);
			blogShare.setUserCode(userCode);
			
			// 动态信息
			RelationDynamic relationDynamic = new RelationDynamic();
			relationDynamic.setDynamicCode(UUIDHelper.getUUID());
			relationDynamic.setTenantCode(sysUserInfo.getTenantCode());
			relationDynamic.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationDynamic.setCreateTime(time);
			relationDynamic.setModifyTime(time);
			relationDynamic.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SHARE_BLOG);
			relationDynamic.setDynamicDepend(RelationConstants.RELATION_DYNAMIC_DEPEND_USER);
			relationDynamic.setUserCode(userCode);
			relationDynamic.setOperateTime(time);
			relationDynamic.setSubjectCode(blogCode);
			relationDynamic.setDynamicIsDelete(RelationConstants.RELATION_NO);
			relationDynamic.setDynamicIsShield(RelationConstants.RELATION_NO);
			relationDynamic.setIsSubjectShow(RelationConstants.RELATION_YES);
			relationDynamic.setIsSubjectDataShow(RelationConstants.RELATION_NO);
			
			// 保存分享信息
			boolean saveShareFlag = blogOperateService.saveShare(blogShare);
			if (!saveShareFlag) {
				log.error("BlogController.shareBlog：保存分享信息失败");
				throw BusinessException.build("BLOG_13002", "操作");
			}
			
			// 保存动态信息
			boolean saveDynamicFlag = relationThirdDynamicService.saveDynamic(relationDynamic, null, null, null);
			if (!saveDynamicFlag) {
				log.error("BlogController.shareBlog：保存动态信息失败");
//				throw BusinessException.build("BLOG_13002", "操作");
			}
			
			// 修改博客分享量
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			blogThirdDataVO.setSubjectCode(blogCode);
			blogThirdDataVO.setUpdateNumber(1);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_SHARE);
			blogThirdDataVO.setModifyTime(time);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 修改关系服务中博客分享量
			RelationDataVO relationDataVO = new RelationDataVO();
			relationDataVO.setSubjectCode(blogCode);
			relationDataVO.setUpdateNumber(1);
			relationDataVO.setModifyTime(time);
			relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_SHARE);
			producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
			
			// 积分操作
			MessageIntegral MessageIntegral = new MessageIntegral();
			MessageIntegral.setActionCode(CreditConstants.COMMAND_CONTENT_SHARE);
			MessageIntegral.setAccountCode(userCode);
			MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
			MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogController.shareBlog：分享博客成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.shareBlog", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: delUserBlog  
	 * @Description: 删除个人博客
	 * @param delUserBlogVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="delUserBlog",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON delUserBlog(@RequireValid DelUserBlogVO delUserBlogVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.delUserBlog：删除个人博客");
			
			// 获取参数
			String blogCode = delUserBlogVO.getBlogCode(); // 博客编号
			String dynamicCode = delUserBlogVO.getDynamicCode(); // 动态编号
			String userCode = delUserBlogVO.getUserCode(); // 人员编号
			
			// 判断该博客是否为精华
			RelationQuality relationQuality = new RelationQuality();
			relationQuality.setSubjectClass(RelationConstants.RELATION_QUALITY_SUBJECT_BLOG);
			relationQuality.setSubjectCode(blogCode);
			boolean qualityStatus = relationThirdSubjectService.getQualityStatus(relationQuality);
			if (qualityStatus) {
				log.error("BlogController.delUserBlog：已置精博客不能被删除blogCode=" + blogCode);
				throw BusinessException.build("BLOG_13012");
			}
			
			// 查询博客信息
			BlogVO blogVO = new BlogVO();
			blogVO.setBlogCode(blogCode); // 博客编号
			BlogInfo blogInfo = blogService.getBlogMiniInfo(blogVO);
			if (blogInfo == null || BlogConstants.BLOG_YES.equals(blogInfo.getBlogIsDelete())) {
				log.error("BlogController.delUserBlog：获取不到博客信息blogCode=" + blogCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 查询个人信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogController.delUserBlog：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			
			// 拼接参数
			blogVO.setUserCode(userCode); // 人员编号
			blogVO.setModifyTime(time); // 修改时间
			
			// 删除
			boolean deleteBlogFlag = blogService.deleteUserBlog(blogVO);
			if (!deleteBlogFlag) {
				log.error("BlogController.delUserBlog：删除博客失败");
				throw BusinessException.build("BLOG_13002", "删除");
			}
			
			// 删除动态
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setDynamicCode(dynamicCode);
			if (BlogConstants.BLOG_SOURCE_FORWARD.equals(blogInfo.getBlogSource())) {
				relationDynamicVO.setDynamicClass(RelationConstants.RELATION_DYNAMIC_FORWARD_BLOG);
			} else if (BlogConstants.BLOG_SOURCE_ORIGINAL.equals(blogInfo.getBlogSource())) {
				relationDynamicVO.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SEND_BLOG);
			}
			relationDynamicVO.setSubjectClass(RelationConstants.RELATION_SUBJECT_BLOG);
			relationDynamicVO.setUserCode(userCode);
			relationDynamicVO.setModifyTime(time);
			relationDynamicVO.setSubjectCode(blogCode);
			boolean deleteDynamicFlag = relationThirdDynamicService.deleteDynamic(relationDynamicVO);
			if (!deleteDynamicFlag) {
				log.error("BlogController.delUserBlog：删除动态失败");
//				throw BusinessException.build("BLOG_13002", "删除");
			}
			
			// 积分操作
			MessageIntegral MessageIntegral = new MessageIntegral();
			MessageIntegral.setActionCode(CreditConstants.COMMAND_BLOG_DEL);
			MessageIntegral.setAccountCode(userCode);
			MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
			MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			
			// 修改博客分类下博客数量
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			blogThirdDataVO.setSubjectCode(blogInfo.getClassCode());
			blogThirdDataVO.setUpdateNumber(-1);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_CLASS_BLOG);
			blogThirdDataVO.setModifyTime(time);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogController.delUserBlog：删除个人博客成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.delUserBlog", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: delTeamBlog  
	 * @Description: 删除班组博客
	 * @param delTeamBlogVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="delTeamBlog",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON delTeamBlog(@RequireValid DelTeamBlogVO delTeamBlogVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.delTeamBlog：删除班组博客");
			
			// 获取参数
			String blogCode = delTeamBlogVO.getBlogCode(); // 博客编号
			String dynamicCode = delTeamBlogVO.getDynamicCode(); // 动态编号
			String teamCode = delTeamBlogVO.getTeamCode(); // 班组编号
			String userCode = delTeamBlogVO.getUserCode(); // 人员编号
			
			// 权限校验
			boolean isTeamLeader = iTeamMemberService.isTeamLeader(teamCode, userCode);
			if (!isTeamLeader) {
				log.error("BlogController.delTeamBlog：操作权限不足");
				throw BusinessException.build("COMMON_403");
			}
			
			// 判断该博客是否为精华
			RelationQuality relationQuality = new RelationQuality();
			relationQuality.setSubjectClass(RelationConstants.RELATION_QUALITY_SUBJECT_BLOG);
			relationQuality.setSubjectCode(blogCode);
			boolean qualityStatus = relationThirdSubjectService.getQualityStatus(relationQuality);
			if (qualityStatus) {
				log.error("BlogController.delTeamBlog：已置精博客不能被删除blogCode=" + blogCode);
				throw BusinessException.build("BLOG_13012");
			}
			
			// 查询博客信息
			BlogVO blogVO = new BlogVO();
			blogVO.setBlogCode(blogCode); // 博客编号
			BlogInfo blogInfo = blogService.getBlogMiniInfo(blogVO);
			if (blogInfo == null || BlogConstants.BLOG_YES.equals(blogInfo.getBlogIsDelete())) {
				log.error("BlogController.delTeamBlog：获取不到博客信息blogCode=" + blogCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 查询班组信息
			TeamInfo teamInfo = iTeamInfoService.selectByCode(teamCode);
			if (teamInfo == null) {
				log.error("BlogController.delUserBlog：获取不到班组信息teamInfo=" + teamInfo);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			
			// 拼接参数
			blogVO.setUserCode(userCode); // 人员编号
			blogVO.setTeamCode(teamCode); // 班组编号
			blogVO.setModifyTime(time); // 修改时间
			
			// 修改
			boolean deleteFlag = blogService.deleteTeamBlog(blogVO);
			if (!deleteFlag) {
				log.error("BlogController.delTeamBlog：删除失败");
				throw BusinessException.build("BLOG_13002", "删除");
			}
			
			// 删除动态
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setDynamicCode(dynamicCode);
			if (BlogConstants.BLOG_SOURCE_FORWARD.equals(blogInfo.getBlogSource())) {
				relationDynamicVO.setDynamicClass(RelationConstants.RELATION_DYNAMIC_FORWARD_BLOG);
			} else if (BlogConstants.BLOG_SOURCE_ORIGINAL.equals(blogInfo.getBlogSource())) {
				relationDynamicVO.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SEND_BLOG);
			}
			relationDynamicVO.setSubjectClass(RelationConstants.RELATION_SUBJECT_BLOG);
			relationDynamicVO.setUserCode(userCode);
			relationDynamicVO.setModifyTime(time);
			relationDynamicVO.setSubjectCode(blogCode);
			boolean deleteDynamicFlag = relationThirdDynamicService.deleteDynamic(relationDynamicVO);
			if (!deleteDynamicFlag) {
				log.error("BlogController.delUserBlog：删除动态失败");
//				throw BusinessException.build("BLOG_13002", "删除");
			}
			
			// 积分操作
			MessageIntegral MessageIntegral = new MessageIntegral();
			MessageIntegral.setActionCode(CreditConstants.COMMAND_BLOG_TEAMDEL);
			MessageIntegral.setAccountCode(teamCode);
			MessageIntegral.setAccountType(CreditConstants.TYPE_TEAM);
			MessageIntegral.setManageOrgCode(teamInfo.getOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			
			// 修改博客分类下博客数量
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			blogThirdDataVO.setSubjectCode(blogInfo.getClassCode());
			blogThirdDataVO.setUpdateNumber(-1);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_CLASS_BLOG);
			blogThirdDataVO.setModifyTime(time);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogController.delTeamBlog：删除班组博客成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.delTeamBlog", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: publishUserBlog  
	 * @Description: 发布个人博客
	 * @param publishUserBlogVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="publishUserBlog",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON publishUserBlog(@RequireValid PublishUserBlogVO publishUserBlogVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.publishUserBlog：发布个人博客");
			
			// 获取参数
			String blogTitle = publishUserBlogVO.getBlogTitle(); // 标题
			String blogDigest = publishUserBlogVO.getBlogDigest(); // 摘要
			String contentInfo = publishUserBlogVO.getContentInfo(); // 正文内容
			String blogLogo = publishUserBlogVO.getBlogLogo(); // 图片链接
			String userCode = publishUserBlogVO.getUserCode(); // 人员编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogController.publishUserBlog：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 标题敏感词过滤
            JSONArray jsonArray = solrQueryService.CheckSentence(sysUserInfo.getManageOrgInfo().getSysOrgCode(), blogTitle,
                    SolrCoreConstants.SENSITIVE_WORD_CORE);
            if( jsonArray.size() > 0 ){
            	result = new ResultJSON("COMMON_999");
    			JSONObject body = new JSONObject();
                body.put("totalCount", jsonArray.size());
                body.put("isPass", false);
                body.put("list", jsonArray);
    			result.setBody(body);
                return result ;
            }
            
            // 摘要敏感词过滤
            jsonArray = solrQueryService.CheckSentence(sysUserInfo.getManageOrgInfo().getSysOrgCode(), blogDigest,
            		SolrCoreConstants.SENSITIVE_WORD_CORE);
            if( jsonArray.size() > 0 ){
            	result = new ResultJSON("COMMON_999");
            	JSONObject body = new JSONObject();
            	body.put("totalCount", jsonArray.size());
            	body.put("isPass", false);
            	body.put("list", jsonArray);
            	result.setBody(body);
            	return result ;
            }
            
            // 正文敏感词过滤
            jsonArray = solrQueryService.CheckSentence(sysUserInfo.getManageOrgInfo().getSysOrgCode(), contentInfo,
            		SolrCoreConstants.SENSITIVE_WORD_CORE);
            if( jsonArray.size() > 0 ){
            	result = new ResultJSON("COMMON_999");
            	JSONObject body = new JSONObject();
            	body.put("totalCount", jsonArray.size());
            	body.put("isPass", false);
            	body.put("list", jsonArray);
            	result.setBody(body);
            	return result ;
            }
			
			// 共用信息
			String dynamicCode = UUIDHelper.getUUID(); // 动态编号
			String blogCode = UUIDHelper.getUUID(); // 博客编号
			long time = new Date().getTime(); // 当前时间
			
			// 查询默认分类编号
			BlogClassVO blogClassVO = new BlogClassVO();
			blogClassVO.setClassDepend(BlogConstants.BLOG_DEPEND_USER);
			blogClassVO.setTenantCode(sysUserInfo.getTenantCode());
			blogClassVO.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			blogClassVO.setUserCode(userCode);
			String classCode = blogClassService.getDefaultClassCode(blogClassVO);
			
			// 博客信息
			BlogInfo blogInfo = new BlogInfo();
			blogInfo.setBlogCode(blogCode);
			blogInfo.setTenantCode(sysUserInfo.getTenantCode());
			blogInfo.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			blogInfo.setCreateTime(time);
			blogInfo.setModifyTime(time);
			blogInfo.setDynamicCode(dynamicCode);
			blogInfo.setUserCode(userCode);
			blogInfo.setBlogAgentFlag(BlogConstants.BLOG_NO);
			blogInfo.setBlogSource(BlogConstants.BLOG_SOURCE_ORIGINAL);
			blogInfo.setBlogDepend(BlogConstants.BLOG_DEPEND_USER);
			blogInfo.setBlogTitle(blogTitle);
			blogInfo.setBlogDigest(blogDigest);
			blogInfo.setBlogLogo(blogLogo);
			blogInfo.setBlogIsDelete(BlogConstants.BLOG_NO);
			blogInfo.setBlogIsShield(BlogConstants.BLOG_NO);
			blogInfo.setBlogPublishTime(time);
			blogInfo.setBlogPlanPublishTime(0L);
			blogInfo.setClassCode(classCode);
			
			// 博客正文信息
			BlogContent blogContent = new BlogContent();
			blogContent.setContentCode(UUIDHelper.getUUID());
			blogContent.setTenantCode(sysUserInfo.getTenantCode());
			blogContent.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			blogContent.setCreateTime(time);
			blogContent.setModifyTime(time);
			blogContent.setBlogCode(blogCode);
			blogContent.setContentInfo(contentInfo);
			
			// 保存博客
			boolean saveBlogFlag = blogService.saveBlogForH5(blogInfo, blogContent);
			if (!saveBlogFlag) {
				log.error("BlogController.publishUserBlog：保存博客失败");
				throw BusinessException.build("BLOG_13002", "保存");
			}
			
			// 动态信息
			RelationDynamic relationDynamic = new RelationDynamic();
			relationDynamic.setDynamicCode(dynamicCode);
			relationDynamic.setTenantCode(sysUserInfo.getTenantCode());
			relationDynamic.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationDynamic.setCreateTime(time);
			relationDynamic.setModifyTime(time);
			relationDynamic.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SEND_BLOG);
			relationDynamic.setDynamicDepend(RelationConstants.RELATION_DYNAMIC_DEPEND_USER);
			relationDynamic.setUserCode(userCode);
			relationDynamic.setOperateTime(time);
			relationDynamic.setSubjectCode(blogCode);
			relationDynamic.setDynamicIsDelete(RelationConstants.RELATION_NO);
			relationDynamic.setDynamicIsShield(RelationConstants.RELATION_NO);
			relationDynamic.setIsSubjectShow(RelationConstants.RELATION_YES);
			relationDynamic.setIsSubjectDataShow(RelationConstants.RELATION_YES);
			
			// 内容信息
			RelationSubjectInfo relationSubjectInfo = new RelationSubjectInfo();
			relationSubjectInfo.setSubjectCode(blogCode);
			relationSubjectInfo.setTenantCode(sysUserInfo.getTenantCode());
			relationSubjectInfo.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationSubjectInfo.setCreateTime(time);
			relationSubjectInfo.setModifyTime(time);
			relationSubjectInfo.setSubjectClass(RelationConstants.RELATION_SUBJECT_BLOG);
			relationSubjectInfo.setSubjectDepend(RelationConstants.RELATION_SUBJECT_DEPEND_USER);
			relationSubjectInfo.setUserCode(userCode);
			relationSubjectInfo.setSubjectPublishTime(time);
			relationSubjectInfo.setSubjectTitle(blogTitle);
			relationSubjectInfo.setSubjectDigest(blogDigest);
			relationSubjectInfo.setSubjectUrl(blogLogo);
			relationSubjectInfo.setSubjectSource(RelationConstants.RELATION_SUBJECT_SOURCE_ORIGINAL);
			relationSubjectInfo.setSubjectIsDelete(RelationConstants.RELATION_NO);
			relationSubjectInfo.setSubjectIsShield(RelationConstants.RELATION_NO);
			
			// 保存动态信息
			boolean saveDynamicFlag = relationThirdDynamicService.saveDynamic(relationDynamic, null, relationSubjectInfo, null);
			if (!saveDynamicFlag) {
				log.error("BlogController.publishUserBlog：保存动态失败");
//				throw BusinessException.build("BLOG_13002", "保存");
			}
			
			// 统计操作
			StatisticCommon statisticCommon = new StatisticCommon();
			statisticCommon.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			statisticCommon.setPeopleCode(userCode);
			statisticCommon.setPeopleName(sysUserInfo.getUserName());
			statisticCommon.setBlog(StatisticConstants.BLOG);
			iStatisticService.addRecord(statisticCommon);
			
			// 积分操作
			MessageIntegral MessageIntegral = new MessageIntegral();
			MessageIntegral.setActionCode(CreditConstants.COMMAND_BLOG_PUBLISH);
			MessageIntegral.setAccountCode(userCode);
			MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
			MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			
			// 修改博客分类下博客数量
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			blogThirdDataVO.setSubjectCode(classCode);
			blogThirdDataVO.setUpdateNumber(1);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_CLASS_BLOG);
			blogThirdDataVO.setModifyTime(time);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("blogCode", blogCode);
			result.setBody(body);
			
			log.info("BlogController.publishUserBlog：发布个人博客成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.publishUserBlog", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: publishTeamBlog  
	 * @Description: 发布班组博客
	 * @param publishTeamBlogVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="publishTeamBlog",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON publishTeamBlog(@RequireValid PublishTeamBlogVO publishTeamBlogVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.publishTeamBlog：发布班组博客");
			
			// 获取参数
			String teamCode = publishTeamBlogVO.getTeamCode(); // 班组编号
			String blogTitle = publishTeamBlogVO.getBlogTitle(); // 标题
			String blogDigest = publishTeamBlogVO.getBlogDigest(); // 摘要
			String contentInfo = publishTeamBlogVO.getContentInfo(); // 正文内容
			String blogLogo = publishTeamBlogVO.getBlogLogo(); // 图片链接
			String userCode = publishTeamBlogVO.getUserCode(); // 人员编号
			
			// 判断权限
			boolean isTeamLeader = iTeamMemberService.isTeamLeader(teamCode, userCode);
			if (!isTeamLeader) {
				log.error("BlogController.publishTeamBlog：操作权限不足");
				throw BusinessException.build("COMMON_403");
			}
			
			// 获取班组信息
			TeamInfo teamInfo = iTeamInfoService.selectByCode(teamCode);
			if (teamInfo == null) {
				log.error("BlogController.publishTeamBlog：获取不到班组信息teamCode=" + teamCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 标题敏感词过滤
            JSONArray jsonArray = solrQueryService.CheckSentence(teamInfo.getOrgCode(), blogTitle,
                    SolrCoreConstants.SENSITIVE_WORD_CORE);
            if( jsonArray.size() > 0 ){
            	result = new ResultJSON("COMMON_999");
    			JSONObject body = new JSONObject();
                body.put("totalCount", jsonArray.size());
                body.put("isPass", false);
                body.put("list", jsonArray);
    			result.setBody(body);
                return result ;
            }
            
            // 摘要敏感词过滤
            jsonArray = solrQueryService.CheckSentence(teamInfo.getOrgCode(), blogDigest,
            		SolrCoreConstants.SENSITIVE_WORD_CORE);
            if( jsonArray.size() > 0 ){
            	result = new ResultJSON("COMMON_999");
            	JSONObject body = new JSONObject();
            	body.put("totalCount", jsonArray.size());
            	body.put("isPass", false);
            	body.put("list", jsonArray);
            	result.setBody(body);
            	return result ;
            }
            
            // 正文敏感词过滤
            jsonArray = solrQueryService.CheckSentence(teamInfo.getOrgCode(), contentInfo,
            		SolrCoreConstants.SENSITIVE_WORD_CORE);
            if( jsonArray.size() > 0 ){
            	result = new ResultJSON("COMMON_999");
            	JSONObject body = new JSONObject();
            	body.put("totalCount", jsonArray.size());
            	body.put("isPass", false);
            	body.put("list", jsonArray);
            	result.setBody(body);
            	return result ;
            }
			
			// 共用信息
			String dynamicCode = UUIDHelper.getUUID(); // 动态编号
			String blogCode = UUIDHelper.getUUID(); // 博客编号
			long time = new Date().getTime(); // 当前时间
			
			// 查询默认分类编号
			BlogClassVO blogClassVO = new BlogClassVO();
			blogClassVO.setClassDepend(BlogConstants.BLOG_DEPEND_TEAM);
			blogClassVO.setTenantCode(teamInfo.getTenantCode());
			blogClassVO.setOrgCode(teamInfo.getOrgCode());
			blogClassVO.setTeamCode(teamCode);
			blogClassVO.setUserCode(userCode);
			String classCode = blogClassService.getDefaultClassCode(blogClassVO);
			
			// 博客信息
			BlogInfo blogInfo = new BlogInfo();
			blogInfo.setBlogCode(blogCode);
			blogInfo.setTenantCode(teamInfo.getTenantCode());
			blogInfo.setOrgCode(teamInfo.getOrgCode());
			blogInfo.setCreateTime(time);
			blogInfo.setModifyTime(time);
			blogInfo.setDynamicCode(dynamicCode);
			blogInfo.setUserCode(userCode);
			blogInfo.setBlogAgentFlag(BlogConstants.BLOG_NO);
			blogInfo.setBlogSource(BlogConstants.BLOG_SOURCE_ORIGINAL);
			blogInfo.setBlogDepend(BlogConstants.BLOG_DEPEND_TEAM);
			blogInfo.setTeamCode(teamCode);
			blogInfo.setBlogTitle(blogTitle);
			blogInfo.setBlogDigest(blogDigest);
			blogInfo.setBlogLogo(blogLogo);
			blogInfo.setBlogIsDelete(BlogConstants.BLOG_NO);
			blogInfo.setBlogIsShield(BlogConstants.BLOG_NO);
			blogInfo.setBlogPublishTime(time);
			blogInfo.setBlogPlanPublishTime(0L);
			blogInfo.setClassCode(classCode);
			
			// 博客正文信息
			BlogContent blogContent = new BlogContent();
			blogContent.setContentCode(UUIDHelper.getUUID());
			blogContent.setTenantCode(teamInfo.getTenantCode());
			blogContent.setOrgCode(teamInfo.getOrgCode());
			blogContent.setCreateTime(time);
			blogContent.setModifyTime(time);
			blogContent.setBlogCode(blogCode);
			blogContent.setContentInfo(contentInfo);
			
			// 保存博客
			boolean saveBlogFlag = blogService.saveBlogForH5(blogInfo, blogContent);
			if (!saveBlogFlag) {
				log.error("BlogController.publishTeamBlog：保存失败");
				throw BusinessException.build("BLOG_13002", "保存");
			}
			
			// 动态信息
			RelationDynamic relationDynamic = new RelationDynamic();
			relationDynamic.setDynamicCode(dynamicCode);
			relationDynamic.setTenantCode(teamInfo.getTenantCode());
			relationDynamic.setOrgCode(teamInfo.getOrgCode());
			relationDynamic.setCreateTime(time);
			relationDynamic.setModifyTime(time);
			relationDynamic.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SEND_BLOG);
			relationDynamic.setDynamicDepend(RelationConstants.RELATION_DYNAMIC_DEPEND_TEAM);
			relationDynamic.setTeamCode(teamCode);
			relationDynamic.setUserCode(userCode);
			relationDynamic.setOperateTime(time);
			relationDynamic.setSubjectCode(blogCode);
			relationDynamic.setDynamicIsDelete(RelationConstants.RELATION_NO);
			relationDynamic.setDynamicIsShield(RelationConstants.RELATION_NO);
			relationDynamic.setIsSubjectShow(RelationConstants.RELATION_YES);
			relationDynamic.setIsSubjectDataShow(RelationConstants.RELATION_YES);
			
			// 动态班组关系
			RelationDynamicTeamRel relationDynamicTeamRel = new RelationDynamicTeamRel();
			relationDynamicTeamRel.setRelCode(UUIDHelper.getUUID());
			relationDynamicTeamRel.setTenantCode(teamInfo.getTenantCode());
			relationDynamicTeamRel.setOrgCode(teamInfo.getOrgCode());
			relationDynamicTeamRel.setCreateTime(time);
			relationDynamicTeamRel.setModifyTime(time);
			relationDynamicTeamRel.setDynamicCode(dynamicCode);
			relationDynamicTeamRel.setTeamCode(teamCode);
			boolean isExcelent = iTeamElectService.isExcelent(teamCode);
			relationDynamicTeamRel.setTeamIsExcellent(isExcelent ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
			relationDynamicTeamRel.setTeamIsNoOne(TeamConstants.TEAM_TYPE_1.equals(teamInfo.getTeamType()) ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
			relationDynamicTeamRel.setTeamIsDissolve(TeamConstants.BOOLEAN_TRUE.equals(teamInfo.getIsDissolve()) ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
			relationDynamicTeamRel.setRelIsHomeShow(RelationConstants.RELATION_YES);
			relationDynamicTeamRel.setRelIsQuality(RelationConstants.RELATION_NO);
			relationDynamicTeamRel.setRelIsRecommend(RelationConstants.RELATION_NO);
			
			// 内容信息
			RelationSubjectInfo relationSubjectInfo = new RelationSubjectInfo();
			relationSubjectInfo.setSubjectCode(blogCode);
			relationSubjectInfo.setTenantCode(teamInfo.getTenantCode());
			relationSubjectInfo.setOrgCode(teamInfo.getOrgCode());
			relationSubjectInfo.setCreateTime(time);
			relationSubjectInfo.setModifyTime(time);
			relationSubjectInfo.setSubjectClass(RelationConstants.RELATION_SUBJECT_BLOG);
			relationSubjectInfo.setSubjectDepend(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM);
			relationSubjectInfo.setTeamCode(teamCode);
			relationSubjectInfo.setUserCode(userCode);
			relationSubjectInfo.setSubjectPublishTime(time);
			relationSubjectInfo.setSubjectTitle(blogTitle);
			relationSubjectInfo.setSubjectDigest(blogDigest);
			relationSubjectInfo.setSubjectUrl(blogLogo);
			relationSubjectInfo.setSubjectSource(RelationConstants.RELATION_SUBJECT_SOURCE_ORIGINAL);
			relationSubjectInfo.setSubjectIsDelete(RelationConstants.RELATION_NO);
			relationSubjectInfo.setSubjectIsShield(RelationConstants.RELATION_NO);
			
			// 保存动态信息
			boolean saveDynamicFlag = relationThirdDynamicService.saveDynamic(relationDynamic, relationDynamicTeamRel, relationSubjectInfo, null);
			if (!saveDynamicFlag) {
				log.error("BlogController.publishTeamBlog：保存动态失败");
//				throw BusinessException.build("BLOG_13002", "保存");
			}
			
			// 统计操作
			StatisticCommon statisticCommon = new StatisticCommon();
			statisticCommon.setOrgCode(teamInfo.getOrgCode());
			statisticCommon.setTeamCode(teamCode);
			statisticCommon.setTeamName(teamInfo.getTeamName());
			statisticCommon.setBlog(StatisticConstants.BLOG);
			iStatisticService.addRecord(statisticCommon);
			
			// 积分操作
			MessageIntegral MessageIntegral = new MessageIntegral();
			MessageIntegral.setActionCode(CreditConstants.COMMAND_BLOG_TEAMPUBLISH);
			MessageIntegral.setAccountCode(teamCode);
			MessageIntegral.setAccountType(CreditConstants.TYPE_TEAM);
			MessageIntegral.setManageOrgCode(teamInfo.getOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			
			// 修改博客分类下博客数量
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			blogThirdDataVO.setSubjectCode(classCode);
			blogThirdDataVO.setUpdateNumber(1);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_CLASS_BLOG);
			blogThirdDataVO.setModifyTime(time);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("blogCode", blogCode);
			result.setBody(body);
			
			log.info("BlogController.publishTeamBlog：发布班组博客成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.publishTeamBlog", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: forwardUserBlog  
	 * @Description: 转发博客
	 * @param forwardUserBlogVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="forwardUserBlog",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON forwardUserBlog(@RequireValid ForwardUserBlogVO forwardUserBlogVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.forwardUserBlog：转发博客");
			
			// 获取参数
			String sourceBlogCode = forwardUserBlogVO.getBlogCode(); // 原博客编号
			String userCode = forwardUserBlogVO.getUserCode(); // 人员编号
			
			// 查询原博客信息
			BlogVO blogVO = new BlogVO();
			blogVO.setBlogCode(sourceBlogCode); // 原博客编号
			BlogInfo sourceBlogInfo = blogService.getBlogMiniInfo(blogVO);
			if (sourceBlogInfo == null || BlogConstants.BLOG_YES.equals(sourceBlogInfo.getBlogIsDelete())
					 || BlogConstants.BLOG_YES.equals(sourceBlogInfo.getBlogIsShield())) {
				log.error("BlogController.forwardUserBlog：获取不到博客信息blogCode=" + sourceBlogCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 转发的博客不能再次被转发
			if (BlogConstants.BLOG_SOURCE_FORWARD.equals(sourceBlogInfo.getBlogSource())) {
				log.error("BlogController.forwardUserBlog：转发的博客不能再次被转发");
				throw BusinessException.build("BLOG_13013");
			}
			
			// 判断黑名单
			if (BlogConstants.BLOG_DEPEND_USER.equals(sourceBlogInfo.getBlogDepend())) {
				UserState userState = iUserRelationService.selectUserState(sourceBlogInfo.getUserCode(), userCode);
				if (userState == null || userState.getIsBlack() == null) {
					log.error("BlogController.forwardUserBlog：获取不到人员关系信息");
					throw BusinessException.build("COMMON_400");
				}
				if (FilingConstants.BOOLEAN_TRUE.equals(userState.getIsBlack())) {
					log.error("BlogController.forwardUserBlog：操作权限不足");
					throw BusinessException.build("COMMON_403");
				}
			}
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogController.forwardUserBlog：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用信息
			String blogCode = UUIDHelper.getUUID(); // 博客编号
			String dynamicCode = UUIDHelper.getUUID(); // 动态编号
			long time = new Date().getTime(); // 当前时间
			
			// 查询默认分类编号
			BlogClassVO blogClassVO = new BlogClassVO();
			blogClassVO.setClassDepend(BlogConstants.BLOG_DEPEND_USER);
			blogClassVO.setTenantCode(sysUserInfo.getTenantCode());
			blogClassVO.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			blogClassVO.setUserCode(userCode);
			String classCode = blogClassService.getDefaultClassCode(blogClassVO);
			
			// 博客信息
			BlogInfo blogInfo = new BlogInfo();
			blogInfo.setBlogCode(blogCode);
			blogInfo.setTenantCode(sysUserInfo.getTenantCode());
			blogInfo.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			blogInfo.setCreateTime(time);
			blogInfo.setModifyTime(time);
			blogInfo.setDynamicCode(dynamicCode);
			blogInfo.setUserCode(userCode);
			blogInfo.setBlogAgentFlag(BlogConstants.BLOG_NO);
			blogInfo.setBlogSource(BlogConstants.BLOG_SOURCE_FORWARD);
			blogInfo.setSourceBlogCode(sourceBlogCode);
			blogInfo.setSourceBlogDepend(sourceBlogInfo.getBlogDepend());
			blogInfo.setSourceTeamCode(sourceBlogInfo.getTeamCode());
			blogInfo.setBlogDepend(BlogConstants.BLOG_DEPEND_USER);
			blogInfo.setBlogTitle(sourceBlogInfo.getBlogTitle());
			blogInfo.setBlogDigest(sourceBlogInfo.getBlogDigest());
			blogInfo.setBlogPlanPublishTime(0L);
			blogInfo.setBlogPublishTime(time);
			blogInfo.setBlogIsDelete(BlogConstants.BLOG_NO);
			blogInfo.setBlogIsShield(BlogConstants.BLOG_NO);
			blogInfo.setClassCode(classCode);
			
			// 动态信息
			RelationDynamic relationDynamic = new RelationDynamic();
			relationDynamic.setDynamicCode(dynamicCode);
			relationDynamic.setTenantCode(sysUserInfo.getTenantCode());
			relationDynamic.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationDynamic.setCreateTime(time);
			relationDynamic.setModifyTime(time);
			relationDynamic.setDynamicClass(RelationConstants.RELATION_DYNAMIC_FORWARD_BLOG);
			relationDynamic.setDynamicDepend(RelationConstants.RELATION_DYNAMIC_DEPEND_USER);
			relationDynamic.setUserCode(userCode);
			relationDynamic.setOperateTime(time);
			relationDynamic.setSubjectCode(blogCode);
			relationDynamic.setDynamicIsDelete(RelationConstants.RELATION_NO);
			relationDynamic.setDynamicIsShield(RelationConstants.RELATION_NO);
			relationDynamic.setIsSubjectShow(RelationConstants.RELATION_YES);
			relationDynamic.setIsSubjectDataShow(RelationConstants.RELATION_YES);
			
			// 内容信息
			RelationSubjectInfo relationSubjectInfo = new RelationSubjectInfo();
			relationSubjectInfo.setSubjectCode(blogCode);
			relationSubjectInfo.setTenantCode(sysUserInfo.getTenantCode());
			relationSubjectInfo.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			relationSubjectInfo.setCreateTime(time);
			relationSubjectInfo.setModifyTime(time);
			relationSubjectInfo.setSubjectClass(RelationConstants.RELATION_SUBJECT_BLOG);
			relationSubjectInfo.setSubjectDepend(RelationConstants.RELATION_SUBJECT_DEPEND_USER);
			relationSubjectInfo.setUserCode(userCode);
			relationSubjectInfo.setSubjectPublishTime(time);
			relationSubjectInfo.setSubjectTitle(sourceBlogInfo.getBlogTitle());
			relationSubjectInfo.setSubjectDigest(sourceBlogInfo.getBlogDigest());
			relationSubjectInfo.setSubjectUrl(sourceBlogInfo.getBlogLogo());
			relationSubjectInfo.setSubjectSource(RelationConstants.RELATION_SUBJECT_SOURCE_FORWARD);
			relationSubjectInfo.setSourceCode(sourceBlogCode);
			if (BlogConstants.BLOG_DEPEND_USER.equals(sourceBlogInfo.getBlogDepend())) {
				relationSubjectInfo.setSourceDepend(RelationConstants.RELATION_SUBJECT_DEPEND_USER);
			} else if (BlogConstants.BLOG_DEPEND_TEAM.equals(sourceBlogInfo.getBlogDepend())) {
				relationSubjectInfo.setSourceDepend(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM);
				relationSubjectInfo.setSourceTeamCode(sourceBlogInfo.getTeamCode());
			}
			relationSubjectInfo.setSourceUserCode(sourceBlogInfo.getUserCode());
			relationSubjectInfo.setSourcePublishTime(sourceBlogInfo.getBlogPublishTime());
			relationSubjectInfo.setSourceIsDelete(RelationConstants.RELATION_NO);
			relationSubjectInfo.setSourceIsShield(RelationConstants.RELATION_NO);
			relationSubjectInfo.setSubjectIsDelete(RelationConstants.RELATION_NO);
			relationSubjectInfo.setSubjectIsShield(RelationConstants.RELATION_NO);
			
			// 保存博客
			boolean saveBlogFlag = blogService.saveForwardBlog(blogInfo);
			if (!saveBlogFlag) {
				log.error("BlogController.forwardUserBlog：保存博客失败");
				throw BusinessException.build("BLOG_13002", "保存");
			}
			
			// 保存动态信息
			boolean saveDynamic = relationThirdDynamicService.saveDynamic(relationDynamic, null, relationSubjectInfo, null);
			if (!saveDynamic) {
				log.error("BlogController.forwardUserBlog：保存动态失败");
//				throw BusinessException.build("BLOG_13002", "保存");
			}
			
			// 积分操作
			MessageIntegral MessageIntegral = new MessageIntegral();
			MessageIntegral.setActionCode(CreditConstants.COMMAND_CONTENT_RELAY);
			MessageIntegral.setAccountCode(userCode);
			MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
			MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			
			// 修改博客分类下博客数量
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			blogThirdDataVO.setSubjectCode(classCode);
			blogThirdDataVO.setUpdateNumber(1);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_CLASS_BLOG);
			blogThirdDataVO.setModifyTime(time);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 修改博客转发量
			blogThirdDataVO.setSubjectCode(blogCode);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_FORWARD);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 修改关系服务中博客转发量
			RelationDataVO relationDataVO = new RelationDataVO();
			relationDataVO.setSubjectCode(blogCode);
			relationDataVO.setUpdateNumber(1);
			relationDataVO.setModifyTime(time);
			relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_FORWARD);
			producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
			
			// 发布消息
			JSONObject atMessageJO = new JSONObject();
			List<JSONObject> messageList = new ArrayList<JSONObject>();
			JSONObject messageJO = new JSONObject();
			messageJO.put("userCode", sourceBlogInfo.getUserCode());
			messageJO.put("atUserCode", userCode);
			messageJO.put("atType", MsgClassConstants.AT_BLOG_FORWARD);
			messageJO.put("originalCode", sourceBlogCode);
			messageJO.put("tenantCode", sourceBlogInfo.getTenantCode());
			messageJO.put("content", "");
			messageJO.put("orgCode", sourceBlogInfo.getOrgCode());
			messageList.add(messageJO);
			atMessageJO.put("userList", messageList);
			producerTeplate.send(MsgTopicConstants.TOPIC_AT, JSONObject.toJSONString(atMessageJO));
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("blogCode", blogCode);
			result.setBody(body);
			
			log.info("BlogController.forwardUserBlog：转发博客成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.forwardUserBlog", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getSubStatus  
	 * @Description: 查询订阅状态
	 * @param getSubStatusVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getSubStatus",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getSubStatus(@RequireValid GetSubStatusVO getSubStatusVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.getSubStatus：查询订阅状态");
			
			// 获取参数
			String userCode = getSubStatusVO.getUserCode(); // 人员编号
			String subClass = getSubStatusVO.getSubClass(); // 订阅类型
			String subUserCode = getSubStatusVO.getSubUserCode(); // 被订阅人编号
			String teamCode = getSubStatusVO.getTeamCode(); // 被订阅班组编号
			
			// 参数校验
			if (BlogConstants.BLOG_SUB_USER.toString().equals(subClass) && StringUtils.isBlank(subUserCode)) {
				log.error("BlogController.getSubStatus：subClass错误");
				throw BusinessException.build("COMMON_402", "subClass");
			} else if (BlogConstants.BLOG_SUB_TEAM.toString().equals(subClass) && StringUtils.isBlank(teamCode)) {
				log.error("BlogController.getSubStatus：subClass错误");
				throw BusinessException.build("COMMON_402", "subClass");
			}
			
			// 拼接参数
			BlogSubVO blogSubVO = new BlogSubVO();
			blogSubVO.setSubClass(Byte.valueOf(subClass));
			blogSubVO.setSubUserCode(subUserCode);
			blogSubVO.setTeamCode(teamCode);
			blogSubVO.setUserCode(userCode);
			boolean subStatus = blogSubService.getSubStatus(blogSubVO);
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			if (subStatus) {
				body.put("result", "true");
			} else {
				body.put("result", "false");
			}
			result.setBody(body);
			
			log.info("BlogController.getSubStatus：查询订阅状态成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.getSubStatus", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getCollectBlogList  
	 * @Description: 查询收藏博客列表
	 * @param getCollectBlogListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getCollectBlogList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getCollectBlogList(@RequireValid GetCollectBlogListVO getCollectBlogListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.getCollectBlogList：查询收藏博客列表");
			
			// 获取参数
			String pageSize = getCollectBlogListVO.getPageSize(); // 每页条数
			String pageNo = getCollectBlogListVO.getPageNo(); // 页码
			String queryTime = getCollectBlogListVO.getQueryTime(); // 首次查询时间
			String collectUserCode = getCollectBlogListVO.getCollectUserCode(); // 收藏人编号
			String userCode = getCollectBlogListVO.getUserCode(); // 当前登录用户编号
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			BlogVO blogVO = new BlogVO();
			blogVO.setUserCode(collectUserCode); // 收藏人编号
			blogVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			blogVO.setLoginUserCode(userCode); // 当前登录用户编号
			
			// 查询
			PageBean pageBean = blogService.getCollectBlogList(pageParam, blogVO);
			if (pageBean == null) {
				log.error("BlogController.getCollectBlogList：查询失败");
				throw BusinessException.build("BLOG_13002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					BlogModel blogModel = (BlogModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("blogCode", StringTools.formatToString(blogModel.getBlogCode()));
					jo.put("dynamicCode", StringTools.formatToString(blogModel.getDynamicCode()));
					
					// 获取发布人信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(blogModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jo.put("blogDepend", StringTools.formatToString(blogModel.getBlogDepend()));
					if (BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(blogModel.getBlogDepend())) {
						// 获取班组信息
						TeamInfo teamInfo = iTeamInfoService.selectByCode(blogModel.getTeamCode());
						jo.put("teamCode", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamCode()));
						jo.put("teamName", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamName()));
						// 获取组织信息
						SysOrgInfo sysOrgInfo = iSysOrgService.selectByCode(teamInfo == null ? "" : teamInfo.getOrgCode());
						jo.put("orgCode", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
						jo.put("orgName", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("teamCode", "");
						jo.put("teamName", "");
						jo.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
						jo.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
					}
					
					jo.put("blogSource", StringTools.formatToString(blogModel.getBlogSource()));
					
					// 转发时来源信息
					if (BlogConstants.BLOG_SOURCE_FORWARD.equals(blogModel.getBlogSource())) {
						// 获取来源发布人信息
						SysUserInfo sourceSysUserInfo = iSysUserService.selectByCode(blogModel.getSourceUserCode());
						jo.put("sourceBlogCode", StringTools.formatToString(blogModel.getSourceBlogCode()));
						jo.put("sourceUserCode", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getUserCode()));
						jo.put("sourceUserName", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getUserName()));
						jo.put("sourceBlogDepend", StringTools.formatToString(blogModel.getSourceBlogDepend()));
						if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogModel.getSourceBlogDepend())) {
							// 获取班组信息
							TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(blogModel.getSourceTeamCode());
							jo.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamCode()));
							jo.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
							// 获取组织信息
							SysOrgInfo sourceSysOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo == null ? "" : sourceTeamInfo.getOrgCode());
							jo.put("sourceOrgCode", sourceSysOrgInfo == null ? "" : StringTools.formatToString(sourceSysOrgInfo.getSysOrgCode()));
							jo.put("sourceOrgName", sourceSysOrgInfo == null ? "" : StringTools.formatToString(sourceSysOrgInfo.getSysOrgFullname()));
						} else {
							jo.put("sourceTeamCode", "");
							jo.put("sourceTeamName", "");
							jo.put("sourceOrgCode", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getManageOrgInfo().getSysOrgCode()));
							jo.put("sourceOrgName", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getManageOrgInfo().getSysOrgFullname()));
						}
					} else {
						jo.put("sourceBlogCode", "");
						jo.put("sourceUserCode", "");
						jo.put("sourceUserName", "");
						jo.put("sourceBlogDepend", "");
						jo.put("sourceTeamCode", "");
						jo.put("sourceTeamName", "");
						jo.put("sourceOrgCode", "");
						jo.put("sourceOrgName", "");
					}
					
					jo.put("blogTitle", StringTools.formatToString(blogModel.getBlogTitle()));
					jo.put("blogDigest", StringTools.formatToString(blogModel.getBlogDigest()));
					jo.put("blogLogo", StringTools.formatToString(blogModel.getBlogLogo()));
					jo.put("blogPublishTime", StringTools.formatToString(blogModel.getBlogPublishTime()));
					jo.put("operateTime", StringTools.formatToString(blogModel.getOperateTime()));
					jo.put("teamQualityNum", StringTools.formatToString(blogModel.getTeamQualityNum()));
					jo.put("groupQualityNum", StringTools.formatToString(blogModel.getGroupQualityNum()));
					jo.put("provinceQualityNum", StringTools.formatToString(blogModel.getProvinceQualityNum()));
					jo.put("cityQualityNum", StringTools.formatToString(blogModel.getCityQualityNum()));
					jo.put("readNum", StringTools.formatToString(blogModel.getReadNum()));
					jo.put("likeNum", StringTools.formatToString(blogModel.getLikeNum()));
					jo.put("collectNum", StringTools.formatToString(blogModel.getCollectNum()));
					jo.put("commentNum", StringTools.formatToString(blogModel.getCommentNum()));
					jo.put("forwardNum", StringTools.formatToString(blogModel.getForwardNum()));
					jo.put("isLike", StringTools.formatToString(blogModel.getIsLike()));
					jo.put("isCollect", StringTools.formatToString(blogModel.getIsCollect()));
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("blogList", ja);
			result.setBody(body);
			
			log.info("BlogController.getCollectBlogList：查询收藏博客列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.getCollectBlogList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getSubBlogList  
	 * @Description: 查询订阅博客列表
	 * @param getSubBlogListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getSubBlogList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getSubBlogList(@RequireValid GetSubBlogListVO getSubBlogListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogController.getSubBlogList：查询订阅博客列表");
			
			// 获取参数
			String pageSize = getSubBlogListVO.getPageSize(); // 每页条数
			String pageNo = getSubBlogListVO.getPageNo(); // 页码
			String queryTime = getSubBlogListVO.getQueryTime(); // 首次查询时间
			String subUserCode = getSubBlogListVO.getSubUserCode(); // 订阅人编号
			String userCode = getSubBlogListVO.getUserCode(); // 当前登录用户编号
			
			// 拼接参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			BlogVO blogVO = new BlogVO();
			blogVO.setUserCode(subUserCode); // 订阅人编号
			blogVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			blogVO.setLoginUserCode(userCode); // 当前登录用户编号
			
			// 查询
			PageBean pageBean = blogService.getSubBlogList(pageParam, blogVO);
			if (pageBean == null) {
				log.error("BlogController.getSubBlogList：查询失败");
				throw BusinessException.build("BLOG_13002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				int recordSize = recordList.size();
				for (int i = 0; i < recordSize; i++) {
					BlogModel blogModel = (BlogModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("blogCode", StringTools.formatToString(blogModel.getBlogCode()));
					
					// 获取发布人信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(blogModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jo.put("blogDepend", StringTools.formatToString(blogModel.getBlogDepend()));
					if (BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(blogModel.getBlogDepend())) {
						// 获取班组信息
						TeamInfo teamInfo = iTeamInfoService.selectByCode(blogModel.getTeamCode());
						jo.put("teamCode", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamCode()));
						jo.put("teamName", teamInfo == null ? "" : StringTools.formatToString(teamInfo.getTeamName()));
						// 获取组织信息
						SysOrgInfo sysOrgInfo = iSysOrgService.selectByCode(teamInfo == null ? "" : teamInfo.getOrgCode());
						jo.put("orgCode", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgCode()));
						jo.put("orgName", sysOrgInfo == null ? "" : StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
					} else {
						jo.put("teamCode", "");
						jo.put("teamName", "");
						jo.put("orgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
						jo.put("orgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
					}
					
					jo.put("blogSource", StringTools.formatToString(blogModel.getBlogSource()));
					
					// 转发时来源信息
					if (BlogConstants.BLOG_SOURCE_FORWARD.equals(blogModel.getBlogSource())) {
						// 获取来源发布人信息
						SysUserInfo sourceSysUserInfo = iSysUserService.selectByCode(blogModel.getSourceUserCode());
						jo.put("sourceBlogCode", StringTools.formatToString(blogModel.getSourceBlogCode()));
						jo.put("sourceUserCode", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getUserCode()));
						jo.put("sourceUserName", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getUserName()));
						jo.put("sourceBlogDepend", StringTools.formatToString(blogModel.getSourceBlogDepend()));
						if (blogModel.getSourceBlogDepend() != null && blogModel.getSourceBlogDepend().equals(BlogConstants.BLOG_DEPEND_TEAM)) {
							// 获取班组信息
							TeamInfo sourceTeamInfo = iTeamInfoService.selectByCode(blogModel.getSourceTeamCode());
							jo.put("sourceTeamCode", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamCode()));
							jo.put("sourceTeamName", sourceTeamInfo == null ? "" : StringTools.formatToString(sourceTeamInfo.getTeamName()));
							// 获取组织信息
							SysOrgInfo sourceSysOrgInfo = iSysOrgService.selectByCode(sourceTeamInfo == null ? "" : sourceTeamInfo.getOrgCode());
							jo.put("sourceOrgCode", sourceSysOrgInfo == null ? "" : StringTools.formatToString(sourceSysOrgInfo.getSysOrgCode()));
							jo.put("sourceOrgName", sourceSysOrgInfo == null ? "" : StringTools.formatToString(sourceSysOrgInfo.getSysOrgFullname()));
						} else {
							jo.put("sourceTeamCode", "");
							jo.put("sourceTeamName", "");
							jo.put("sourceOrgName", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getManageOrgInfo().getSysOrgCode()));
							jo.put("sourceOrgCode", sourceSysUserInfo == null ? "" : StringTools.formatToString(sourceSysUserInfo.getManageOrgInfo().getSysOrgFullname()));
						}
					} else {
						jo.put("sourceBlogCode", "");
						jo.put("sourceUserCode", "");
						jo.put("sourceUserName", "");
						jo.put("sourceBlogDepend", "");
						jo.put("sourceTeamCode", "");
						jo.put("sourceTeamName", "");
						jo.put("sourceOrgCode", "");
						jo.put("sourceOrgName", "");
					}
					
					jo.put("blogTitle", StringTools.formatToString(blogModel.getBlogTitle()));
					jo.put("blogDigest", StringTools.formatToString(blogModel.getBlogDigest()));
					jo.put("blogLogo", StringTools.formatToString(blogModel.getBlogLogo()));
					jo.put("blogPublishTime", StringTools.formatToString(blogModel.getBlogPublishTime()));
					jo.put("teamQualityNum", StringTools.formatToString(blogModel.getTeamQualityNum()));
					jo.put("groupQualityNum", StringTools.formatToString(blogModel.getGroupQualityNum()));
					jo.put("provinceQualityNum", StringTools.formatToString(blogModel.getProvinceQualityNum()));
					jo.put("cityQualityNum", StringTools.formatToString(blogModel.getCityQualityNum()));
					jo.put("readNum", StringTools.formatToString(blogModel.getReadNum()));
					jo.put("likeNum", StringTools.formatToString(blogModel.getLikeNum()));
					jo.put("collectNum", StringTools.formatToString(blogModel.getCollectNum()));
					jo.put("commentNum", StringTools.formatToString(blogModel.getCommentNum()));
					jo.put("forwardNum", StringTools.formatToString(blogModel.getForwardNum()));
					jo.put("isLike", StringTools.formatToString(blogModel.getIsLike()));
					jo.put("isCollect", StringTools.formatToString(blogModel.getIsCollect()));
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("blogList", ja);
			result.setBody(body);
			
			log.info("BlogController.getSubBlogList：查询订阅博客列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogController.getSubBlogList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}

}