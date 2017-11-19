package com.zssq.blog.controller;

import java.util.Date;
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
import com.zssq.blog.vo.CreateCommentVO;
import com.zssq.blog.vo.CreateReplyVO;
import com.zssq.blog.vo.DelCommentVO;
import com.zssq.blog.vo.DelReplyVO;
import com.zssq.blog.vo.GetCommentListVO;
import com.zssq.blog.vo.GetReplyListVO;
import com.zssq.blog.vo.GetReportCommentListVO;
import com.zssq.blog.vo.GetReportReplyListVO;
import com.zssq.blog.vo.LikeCommentVO;
import com.zssq.blog.vo.LikeReplyVO;
import com.zssq.constants.BlogConstants;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.FilingConstants;
import com.zssq.constants.MsgClassConstants;
import com.zssq.constants.MsgTopicConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.constants.SolrCoreConstants;
import com.zssq.dao.model.BlogCommentModel;
import com.zssq.dao.model.BlogReplyModel;
import com.zssq.dao.pojo.BlogComment;
import com.zssq.dao.pojo.BlogInfo;
import com.zssq.dao.pojo.BlogLike;
import com.zssq.dao.pojo.BlogReply;
import com.zssq.dao.pojo.MessageIntegral;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.UserState;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.model.MessageCommentAddModel;
import com.zssq.model.MessagePraiseAddModel;
import com.zssq.pojo.ResultJSON;
import com.zssq.search.service.SolrQueryService;
import com.zssq.service.BlogCommentService;
import com.zssq.service.BlogOperateService;
import com.zssq.service.BlogService;
import com.zssq.service.ISysUserService;
import com.zssq.service.IUserRelationService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.BlogCommentVO;
import com.zssq.vo.BlogOperateVO;
import com.zssq.vo.BlogThirdDataVO;
import com.zssq.vo.BlogVO;
import com.zssq.vo.RelationDataVO;

/**
 * 
 * @ClassName: BlogCommentController  
 * @Description: 博客评论  
 * @author ZKZ  
 * @date 2017年3月21日  
 *
 */
@RequestMapping("/blog")
@Controller
public class BlogCommentController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BlogCommentService blogCommentService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogOperateService blogOperateService;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private IUserRelationService iUserRelationService;
    @Autowired
    private SolrQueryService solrQueryService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	protected KafkaProducerTemplate producerTeplate;
	
	/**
	 * 
	 * @Title: getCommentList  
	 * @Description: 查询评论列表
	 * @param getCommentListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getCommentList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getCommentList(@RequireValid GetCommentListVO getCommentListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogCommentController.getCommentList：查询评论列表");
			
			// 获取参数
			String pageSize = getCommentListVO.getPageSize(); // 每页条数
			String pageNo = getCommentListVO.getPageNo(); // 页码
			String queryTime = getCommentListVO.getQueryTime(); // 首次查询时间
			String blogCode = getCommentListVO.getBlogCode(); // 博客编号
			String userCode = getCommentListVO.getUserCode(); // 人员编号
			
			// 查询参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			BlogCommentVO blogCommentVO = new BlogCommentVO();
			blogCommentVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			blogCommentVO.setBlogCode(blogCode); // 博客编号
			blogCommentVO.setUserCode(userCode); // 人员编号
			
			// 查询评论列表
			PageBean pageBean = blogCommentService.getCommentList(pageParam, blogCommentVO);
			if (pageBean == null) {
				log.error("BlogCommentController.getCommentList：查询失败");
				throw BusinessException.build("BLOG_13002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				for (int i = 0; i < recordList.size(); i++) {
					BlogCommentModel blogCommentModel = (BlogCommentModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("commentCode", StringTools.formatToString(blogCommentModel.getCommentCode()));
					
					// 获取人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(blogCommentModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jo.put("userOrgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
					jo.put("userOrgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
					
					jo.put("commentContent", StringTools.formatToString(blogCommentModel.getCommentContent()));
					jo.put("createTime", StringTools.formatToString(blogCommentModel.getCreateTime()));
					jo.put("commentLikeNum", StringTools.formatToString(blogCommentModel.getCommentLikeNum()));
					jo.put("commentReplyNum", StringTools.formatToString(blogCommentModel.getCommentReplyNum()));
					jo.put("isLike", StringTools.formatToString(blogCommentModel.getIsLike()));
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("commentList", ja);
			result.setBody(body);
			
			log.info("BlogCommentController.getCommentList：查询评论列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogCommentController.getCommentList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getReplyList  
	 * @Description: 查询回复列表
	 * @param getReplyListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getReplyList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getReplyList(@RequireValid GetReplyListVO getReplyListVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogCommentController.getReplyList：查询回复列表");
			
			// 获取参数
			String pageSize = getReplyListVO.getPageSize(); // 每页条数
			String pageNo = getReplyListVO.getPageNo(); // 页码
			String queryTime = getReplyListVO.getQueryTime(); // 首次查询时间
			String commentCode = getReplyListVO.getCommentCode(); // 评论编号
			String userCode = getReplyListVO.getUserCode(); // 人员编号
			
			// 查询参数
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			BlogCommentVO blogCommentVO = new BlogCommentVO();
			blogCommentVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			blogCommentVO.setCommentCode(commentCode); // 评论编号
			blogCommentVO.setUserCode(userCode); // 人员编号
			
			// 查询评论列表
			PageBean pageBean = blogCommentService.getReplyList(pageParam, blogCommentVO);
			if (pageBean == null) {
				log.error("BlogCommentController.getReplyList：查询失败");
				throw BusinessException.build("BLOG_13002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				for (int i = 0; i < recordList.size(); i++) {
					BlogReplyModel blogReplyModel = (BlogReplyModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("replyCode", StringTools.formatToString(blogReplyModel.getReplyCode()));
					
					// 获取回复人信息
					SysUserInfo replyUserInfo = iSysUserService.selectByCode(blogReplyModel.getUserCode());
					jo.put("replyUserCode", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getUserCode()));
					jo.put("replyUserName", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getUserName()));
					jo.put("replyUserPhotoUrl", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getHeadPortrait()));
					jo.put("replyUserOrgCode", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getManageOrgInfo().getSysOrgCode()));
					jo.put("replyUserOrgName", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getManageOrgInfo().getSysOrgFullname()));
					
					// 获取被回复人信息
					SysUserInfo toReplyUserInfo = iSysUserService.selectByCode(blogReplyModel.getToReplyUserCode());
					jo.put("toReplyUserCode", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getUserCode()));
					jo.put("toReplyUserName", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getUserName()));
					jo.put("toReplyUserPhotoUrl", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getHeadPortrait()));
					jo.put("toReplyUserOrgCode", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getManageOrgInfo().getSysOrgCode()));
					jo.put("toReplyUserOrgName", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getManageOrgInfo().getSysOrgFullname()));
					
					jo.put("replyContent", StringTools.formatToString(blogReplyModel.getReplyContent()));
					jo.put("replyLikeNum", StringTools.formatToString(blogReplyModel.getReplyLikeNum()));
					jo.put("createTime", StringTools.formatToString(blogReplyModel.getCreateTime()));
					jo.put("isLike", StringTools.formatToString(blogReplyModel.getIsLike()));
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("replyList", ja);
			result.setBody(body);
			
			log.info("BlogCommentController.getReplyList：查询回复列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogCommentController.getReplyList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: createComment  
	 * @Description: 发表评论
	 * @param createCommentVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="createComment",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON createComment(@RequireValid CreateCommentVO createCommentVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogCommentController.createComment：发表评论");
			
			// 获取参数
			String blogCode = createCommentVO.getBlogCode(); // 博客编号
			String userCode = createCommentVO.getUserCode(); // 人员编号
			String commentContent = createCommentVO.getCommentContent(); // 评论内容
			
			// 获取博客信息
			BlogVO blogVO = new BlogVO();
			blogVO.setBlogCode(blogCode); // 博客编号
			BlogInfo blogInfo = blogService.getBlogMiniInfo(blogVO);
			if (blogInfo == null || BlogConstants.BLOG_YES.equals(blogInfo.getBlogIsDelete())
					|| BlogConstants.BLOG_YES.equals(blogInfo.getBlogIsShield())) {
				log.error("BlogClassController.createComment：获取不到博客信息blogCode=" + blogCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 判断黑名单
			if (BlogConstants.BLOG_DEPEND_USER.equals(blogInfo.getBlogDepend())) {
				UserState userState = iUserRelationService.selectUserState(blogInfo.getUserCode(), userCode);
				if (userState == null || userState.getIsBlack() == null) {
					log.error("BlogClassController.createComment：获取不到人员关系信息");
					throw BusinessException.build("COMMON_400");
				}
				if (FilingConstants.BOOLEAN_TRUE.equals(userState.getIsBlack())) {
					log.error("BlogClassController.createComment：操作权限不足");
					throw BusinessException.build("COMMON_403");
				}
			}
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogClassController.createComment：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 敏感词过滤
            JSONArray jsonArray = solrQueryService.CheckSentence(sysUserInfo.getManageOrgInfo().getSysOrgCode(), commentContent,
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
			long time = new Date().getTime(); // 当前时间
			String commentCode = UUIDHelper.getUUID(); // 评论编号
			
			// 评论信息
			BlogComment blogComment = new BlogComment();
			blogComment.setCommentCode(commentCode);
			blogComment.setTenantCode(sysUserInfo.getTenantCode());
			blogComment.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			blogComment.setCreateTime(time);
			blogComment.setModifyTime(time);
			blogComment.setBlogCode(blogCode);
			blogComment.setUserCode(userCode);
			blogComment.setCommentContent(commentContent);
			blogComment.setCommentIsDelete(BlogConstants.BLOG_NO);
			blogComment.setCommentIsShield(BlogConstants.BLOG_NO);
			blogComment.setCommentLikeNum(0);
			blogComment.setCommentReplyNum(0);
			
			// 保存信息
			boolean saveFlag = blogCommentService.saveComment(blogComment);
			if (!saveFlag) {
				log.error("BlogCommentController.createComment：发布失败");
				throw BusinessException.build("BLOG_13002", "发布");
			}
			
			// 更新博客评论量
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			blogThirdDataVO.setSubjectCode(blogCode);
			blogThirdDataVO.setUpdateNumber(1);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_COMMENT);
			blogThirdDataVO.setModifyTime(time);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 更新关系内容评论量
			RelationDataVO relationDataVO = new RelationDataVO();
			relationDataVO.setSubjectCode(blogCode);
			relationDataVO.setUpdateNumber(1);
			relationDataVO.setModifyTime(time);
			relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_COMMENT);
			producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
			
			// 发布消息
			MessageCommentAddModel messageCommentAddModel = new MessageCommentAddModel();
			messageCommentAddModel.setUserCode(blogInfo.getUserCode());
			messageCommentAddModel.setCommentUserCode(userCode);
			messageCommentAddModel.setType(MsgClassConstants.COMMENT_BOLG_COMMENT);
			messageCommentAddModel.setOriginalContent(blogInfo.getBlogTitle());
			messageCommentAddModel.setOriginalCode(blogCode);
			messageCommentAddModel.setCommentCode(commentCode);
			messageCommentAddModel.setCommentContent(commentContent);
			messageCommentAddModel.setTenantCode(sysUserInfo.getTenantCode());
			messageCommentAddModel.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			producerTeplate.send(MsgTopicConstants.TOPIC_COMMENT, JSONObject.toJSONString(messageCommentAddModel));
			
			// 积分操作
			MessageIntegral MessageIntegral = new MessageIntegral();
			MessageIntegral.setActionCode(CreditConstants.COMMAND_COMMENT_PUBLISH);
			MessageIntegral.setAccountCode(userCode);
			MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
			MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("commentCode", commentCode);
			body.put("userCode", userCode);
			body.put("createTime", time);
			body.put("commentContent", commentContent);
			body.put("userName",StringTools.formatToString(sysUserInfo.getUserName()));
			body.put("userPhotoUrl", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
			body.put("userOrgCode", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
			body.put("userOrgName", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
			result.setBody(body);
			
			log.info("BlogCommentController.createComment：发表评论成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogCommentController.createComment", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: createReply  
	 * @Description: 发表回复
	 * @param createReplyVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="createReply",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON createReply(@RequireValid CreateReplyVO createReplyVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogCommentController.createReply：发表回复");
			
			// 获取参数
			String subjectCode = createReplyVO.getSubjectCode(); // 被回复内容编号
			String subjectClass = createReplyVO.getSubjectClass(); // 被回复内容类型
			String userCode = createReplyVO.getUserCode(); // 人员编号
			String replyContent = createReplyVO.getReplyContent(); // 回复内容
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			String replyCode = UUIDHelper.getUUID(); // 回复编号
			String toReplyUserCode = ""; // 被回复人编号
			String toReplyContent = ""; // 被回复内容
			String blogCode = ""; // 博客编号
			String commentCode = ""; // 评论编号
			
			// 判断被回复类型
			if (BlogConstants.BLOG_REPLY_COMMENT.toString().equals(subjectClass)) {
				// 获取评论信息
				BlogCommentVO blogCommentVO = new BlogCommentVO();
				blogCommentVO.setCommentCode(subjectCode); // 评论编号
				BlogCommentModel blogCommentModel = blogCommentService.getCommentInfo(blogCommentVO);
				if (blogCommentModel == null) {
					log.error("BlogClassController.createReply：获取不到评论信息commentCode=" + subjectCode);
					throw BusinessException.build("COMMON_400");
				}
				
				// 赋值
				toReplyUserCode = blogCommentModel.getUserCode(); // 被回复人编号
				blogCode = blogCommentModel.getBlogCode(); // 博客编号
				commentCode = blogCommentModel.getCommentCode(); // 评论编号
				toReplyContent = blogCommentModel.getCommentContent(); // 被回复内容
			} else if (BlogConstants.BLOG_REPLY_REPLY.toString().equals(subjectClass)) {
				// 获取回复信息
				BlogCommentVO blogCommentVO = new BlogCommentVO();
				blogCommentVO.setReplyCode(subjectCode); // 回复编号
				BlogReplyModel blogReplyModel = blogCommentService.getReplyInfo(blogCommentVO);
				if (blogReplyModel == null) {
					log.error("BlogClassController.createReply：获取不到回复信息replyCode=" + subjectCode);
					throw BusinessException.build("COMMON_400");
				}
				
				// 赋值
				toReplyUserCode = blogReplyModel.getUserCode(); // 被回复人编号
				blogCode = blogReplyModel.getBlogCode(); // 博客编号
				commentCode = blogReplyModel.getCommentCode(); // 评论编号
				toReplyContent = blogReplyModel.getReplyContent(); // 被回复内容
			}
			
			// 判断黑名单关系
			UserState userState = iUserRelationService.selectUserState(toReplyUserCode, userCode);
			if (userState == null || userState.getIsBlack() == null) {
				log.error("BlogClassController.createReply：获取不到人员关系信息");
				throw BusinessException.build("COMMON_400");
			}
			if (FilingConstants.BOOLEAN_TRUE.equals(userState.getIsBlack())) {
				log.error("BlogClassController.createReply：操作权限不足");
				throw BusinessException.build("COMMON_403");
			}
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogClassController.createReply：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 敏感词过滤
            JSONArray jsonArray = solrQueryService.CheckSentence(sysUserInfo.getManageOrgInfo().getSysOrgCode(), replyContent,
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
			
			// 回复信息
			BlogReply blogReply = new BlogReply();
			blogReply.setReplyCode(UUIDHelper.getUUID());
			blogReply.setCommentCode(replyCode);
			blogReply.setTenantCode(sysUserInfo.getTenantCode());
			blogReply.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			blogReply.setCreateTime(time);
			blogReply.setModifyTime(time);
			blogReply.setBlogCode(blogCode);
			blogReply.setCommentCode(commentCode);
			blogReply.setUserCode(userCode);
			blogReply.setToReplyUserCode(toReplyUserCode);
			blogReply.setReplyContent(replyContent);
			blogReply.setReplyIsDelete(BlogConstants.BLOG_NO);
			blogReply.setReplytIsShield(BlogConstants.BLOG_NO);
			blogReply.setReplyLikeNum(0);
			
			// 保存信息
			boolean saveFlag = blogCommentService.saveReply(blogReply);
			if (!saveFlag) {
				log.error("BlogCommentController.createReply：发布失败");
				throw BusinessException.build("BLOG_13002", "发布");
			}
			
			// 更新评论回复量
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			blogThirdDataVO.setSubjectCode(commentCode);
			blogThirdDataVO.setUpdateNumber(1);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_COMMENT_REPLY);
			blogThirdDataVO.setModifyTime(time);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 发布消息
			MessageCommentAddModel messageCommentAddModel = new MessageCommentAddModel();
			messageCommentAddModel.setUserCode(toReplyUserCode);
			messageCommentAddModel.setCommentUserCode(userCode);
			messageCommentAddModel.setType(MsgClassConstants.COMMENT_BOLG_REPLY);
			messageCommentAddModel.setOriginalContent(toReplyContent);
			messageCommentAddModel.setOriginalCode(blogCode);
			messageCommentAddModel.setCommentCode(replyCode);
			messageCommentAddModel.setCommentContent(replyContent);
			messageCommentAddModel.setTenantCode(sysUserInfo.getTenantCode());
			messageCommentAddModel.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			producerTeplate.send(MsgTopicConstants.TOPIC_COMMENT, JSONObject.toJSONString(messageCommentAddModel));
			
			// 积分操作
			MessageIntegral MessageIntegral = new MessageIntegral();
			MessageIntegral.setActionCode(CreditConstants.COMMAND_COMMENT_REPLY);
			MessageIntegral.setAccountCode(userCode);
			MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
			MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("replyCode", replyCode);
			body.put("replyUserCode", userCode);
			body.put("createTime", time);
			body.put("replyContent", replyContent);
			body.put("replyUserName", StringTools.formatToString(sysUserInfo.getUserName()));
			body.put("replyUserPhotoUrl", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
			body.put("replyUserOrgCode", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
			body.put("replyUserOrgName", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
			result.setBody(body);

			// 获取被回复人信息
			SysUserInfo toReplyUserInfo = iSysUserService.selectByCode(toReplyUserCode);
			body.put("toReplyCode", toReplyUserCode);
			body.put("toReplyUserName", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getUserName()));
			body.put("toReplyUserPhotoUrl", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getHeadPortrait()));
			body.put("toReplyUserOrgCode", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getManageOrgInfo().getSysOrgCode()));
			body.put("toReplyUserOrgName", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getManageOrgInfo().getSysOrgFullname()));
			result.setBody(body);
			
			log.info("BlogCommentController.createReply：发表回复成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogCommentController.createReply", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: delComment  
	 * @Description: 删除评论
	 * @param delCommentVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="delComment",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON delComment(@RequireValid DelCommentVO delCommentVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogCommentController.delComment：删除评论");
			
			// 获取参数
			String blogCode = delCommentVO.getBlogCode(); // 博客编号
			String commentCode = delCommentVO.getCommentCode(); // 评论编号
			String userCode = delCommentVO.getUserCode(); // 人员编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogClassController.delComment：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			
			// 查询参数
			BlogCommentVO blogCommentVO = new BlogCommentVO();
			blogCommentVO.setCommentCode(commentCode); // 评论编号
			blogCommentVO.setUserCode(userCode); // 人员编号
			blogCommentVO.setModifyTime(time); // 修改时间
			
			// 删除
			boolean deleteFlag = blogCommentService.updateCommentDeleteStatus(blogCommentVO);
			if (!deleteFlag) {
				log.error("BlogCommentController.delComment：删除失败");
				throw BusinessException.build("BLOG_13002", "删除");
			}
			
			// 更新博客评论量
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			blogThirdDataVO.setSubjectCode(blogCode);
			blogThirdDataVO.setUpdateNumber(-1);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_BLOG_COMMENT);
			blogThirdDataVO.setModifyTime(time);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 更新关系内容评论量
			RelationDataVO relationDataVO = new RelationDataVO();
			relationDataVO.setSubjectCode(blogCode);
			relationDataVO.setUpdateNumber(-1);
			relationDataVO.setModifyTime(time);
			relationDataVO.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_COMMENT);
			producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
			
			// 积分操作
			MessageIntegral MessageIntegral = new MessageIntegral();
			MessageIntegral.setActionCode(CreditConstants.COMMAND_COMMENT_DEL);
			MessageIntegral.setAccountCode(userCode);
			MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
			MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogCommentController.delComment：删除评论成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogCommentController.delComment", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: delReply  
	 * @Description: 删除回复
	 * @param delReplyVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="delReply",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON delReply(@RequireValid DelReplyVO delReplyVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogCommentController.delReply：删除回复");
			
			// 获取参数
			String commentCode = delReplyVO.getCommentCode(); // 评论编号
			String replyCode = delReplyVO.getReplyCode(); // 回复编号
			String userCode = delReplyVO.getUserCode(); // 人员编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogClassController.delReply：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			
			// 查询参数
			BlogCommentVO blogCommentVO = new BlogCommentVO();
			blogCommentVO.setReplyCode(replyCode);; // 回复编号
			blogCommentVO.setUserCode(userCode); // 人员编号
			blogCommentVO.setModifyTime(time); // 修改时间
			
			// 删除
			boolean deleteFlag = blogCommentService.updateReplyDeleteStatus(blogCommentVO);
			if (!deleteFlag) {
				log.error("BlogCommentController.delReply：删除失败");
				throw BusinessException.build("BLOG_13002", "删除");
			}
			
			// 更新评论回复量
			BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
			blogThirdDataVO.setSubjectCode(commentCode);
			blogThirdDataVO.setUpdateNumber(-1);
			blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_COMMENT_REPLY);
			blogThirdDataVO.setModifyTime(time);
			producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			
			// 积分操作
			MessageIntegral MessageIntegral = new MessageIntegral();
			MessageIntegral.setActionCode(CreditConstants.COMMAND_COMMENT_REPLYDEL);
			MessageIntegral.setAccountCode(userCode);
			MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
			MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogCommentController.delReply：删除回复成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogCommentController.delReply", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: likeComment  
	 * @Description: 点赞/取消点赞评论
	 * @param likeCommentVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="likeComment",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON likeComment(@RequireValid LikeCommentVO likeCommentVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogCommentController.likeComment：点赞/取消点赞评论");
			
			// 获取参数
			String commentCode = likeCommentVO.getCommentCode(); // 评论编号
			String actionClass = likeCommentVO.getActionClass(); // 操作类型
			String userCode = likeCommentVO.getUserCode(); // 人员编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogClassController.likeComment：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			
			// 判断是点赞还是取消点赞
			if (BlogConstants.BLOG_ACTION_YES.toString().equals(actionClass)) {
				/* 点赞 */
				// 获取评论信息
				BlogCommentVO blogCommentVO = new BlogCommentVO();
				blogCommentVO.setCommentCode(commentCode); // 评论编号
				BlogCommentModel blogCommentModel = blogCommentService.getCommentInfo(blogCommentVO);
				if (blogCommentModel == null) {
					log.error("BlogClassController.likeComment：获取不到评论信息commentCode=" + commentCode);
					throw BusinessException.build("COMMON_400");
				}
				
				// 判断黑名单
				UserState userState = iUserRelationService.selectUserState(blogCommentModel.getUserCode(), userCode);
				if (userState == null || userState.getIsBlack() == null) {
					log.error("BlogClassController.likeComment：获取不到人员关系信息");
					throw BusinessException.build("COMMON_400");
				}
				if (FilingConstants.BOOLEAN_TRUE.equals(userState.getIsBlack())) {
					log.error("BlogClassController.likeComment：操作权限不足");
					throw BusinessException.build("COMMON_403");
				}
				
				// 点赞信息
				BlogLike blogLike = new BlogLike();
				blogLike.setLikeCode(UUIDHelper.getUUID());
				blogLike.setTenantCode(sysUserInfo.getTenantCode());
				blogLike.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				blogLike.setCreateTime(time);
				blogLike.setModifyTime(time);
				blogLike.setBlogCode(blogCommentModel.getBlogCode());
				blogLike.setSubjectCode(commentCode);
				blogLike.setSubjectClass(BlogConstants.BLOG_LIKE_SUBJECT_COMMENT);
				blogLike.setUserCode(userCode);
				
				// 保存点赞信息
				boolean saveFlag = blogOperateService.saveLike(blogLike);
				if (!saveFlag) {
					log.error("BlogCommentController.likeComment：操作失败");
					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 更新评论点赞量
				BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
				blogThirdDataVO.setSubjectCode(commentCode);
				blogThirdDataVO.setUpdateNumber(1);
				blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_COMMENT_LIKE);
				blogThirdDataVO.setModifyTime(time);
				producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
				
				// 发布消息
				MessagePraiseAddModel messagePraiseAddModel = new MessagePraiseAddModel();
				messagePraiseAddModel.setUserCode(blogCommentModel.getUserCode());
				messagePraiseAddModel.setPraiseUserCode(userCode);
				messagePraiseAddModel.setPraiseType(MsgClassConstants.PRAISE_BOLG_COMMENT);
				messagePraiseAddModel.setOriginalCode(blogCommentModel.getBlogCode());
				messagePraiseAddModel.setContent(blogCommentModel.getCommentContent());
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
				// 查询参数
				BlogOperateVO blogOperateVO = new BlogOperateVO();
				blogOperateVO.setSubjectClass(BlogConstants.BLOG_LIKE_SUBJECT_COMMENT); // 点赞类型
				blogOperateVO.setSubjectCode(commentCode); // 评论编号
				blogOperateVO.setUserCode(userCode); // 人员编号
				
				// 取消点赞
				boolean deleteFlag = blogOperateService.deleteLike(blogOperateVO);
				if (!deleteFlag) {
					log.error("BlogCommentController.likeComment：操作失败");
					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 更新评论点赞量
				BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
				blogThirdDataVO.setSubjectCode(commentCode);
				blogThirdDataVO.setUpdateNumber(-1);
				blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_COMMENT_LIKE);
				blogThirdDataVO.setModifyTime(time);
				producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
				
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_ADMIRE_DEL);
				MessageIntegral.setAccountCode(userCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			} else {
				log.error("BlogController.likeComment：参数错误");
				throw BusinessException.build("COMMON_402", "actionClass");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogCommentController.likeComment：点赞/取消点赞评论成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogCommentController.likeComment", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: likeReply  
	 * @Description: 点赞/取消点赞回复
	 * @param likeReplyVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="likeReply",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON likeReply(@RequireValid LikeReplyVO likeReplyVO) throws BusinessException {
		// 返回值
		ResultJSON result = null;
		
		try {
			log.info("BlogCommentController.likeReply：点赞/取消点赞回复");
			
			// 获取参数
			String replyCode = likeReplyVO.getReplyCode(); // 回复编号
			String actionClass = likeReplyVO.getActionClass(); // 操作类型
			String userCode = likeReplyVO.getUserCode(); // 人员编号
			
			// 获取人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("BlogClassController.likeReply：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			
			// 共用信息
			long time = new Date().getTime(); // 当前时间
			
			// 判断是点赞还是取消点赞
			if (BlogConstants.BLOG_ACTION_YES.toString().equals(actionClass)) {
				/* 点赞 */
				// 获取回复信息
				BlogCommentVO blogCommentVO = new BlogCommentVO();
				blogCommentVO.setReplyCode(replyCode); // 回复编号
				BlogReplyModel blogReplyModel = blogCommentService.getReplyInfo(blogCommentVO);
				if (blogReplyModel == null) {
					log.error("BlogClassController.likeReply：获取不到回复信息replyCode=" + replyCode);
					throw BusinessException.build("COMMON_400");
				}
				
				// 判断黑名单
				UserState userState = iUserRelationService.selectUserState(blogReplyModel.getUserCode(), userCode);
				if (userState == null || userState.getIsBlack() == null) {
					log.error("BlogClassController.likeReply：获取不到人员关系信息");
					throw BusinessException.build("COMMON_400");
				}
				if (FilingConstants.BOOLEAN_TRUE.equals(userState.getIsBlack())) {
					log.error("BlogClassController.likeReply：操作权限不足");
					throw BusinessException.build("COMMON_403");
				}
				
				// 点赞信息
				BlogLike blogLike = new BlogLike();
				blogLike.setLikeCode(UUIDHelper.getUUID());
				blogLike.setTenantCode(sysUserInfo.getTenantCode());
				blogLike.setOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				blogLike.setCreateTime(time);
				blogLike.setModifyTime(time);
				blogLike.setBlogCode(blogReplyModel.getBlogCode());
				blogLike.setSubjectCode(replyCode);
				blogLike.setSubjectClass(BlogConstants.BLOG_LIKE_SUBJECT_REPLY);
				blogLike.setUserCode(userCode);
				
				// 保存点赞信息
				boolean saveFlag = blogOperateService.saveLike(blogLike);
				if (!saveFlag) {
					log.error("BlogCommentController.likeReply：操作失败");
					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 更新回复点赞量
				BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
				blogThirdDataVO.setSubjectCode(replyCode);
				blogThirdDataVO.setUpdateNumber(1);
				blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_REPLY_LIKE);
				blogThirdDataVO.setModifyTime(time);
				producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
				
				// 发布消息
				MessagePraiseAddModel messagePraiseAddModel = new MessagePraiseAddModel();
				messagePraiseAddModel.setUserCode(blogReplyModel.getUserCode());
				messagePraiseAddModel.setPraiseUserCode(userCode);
				messagePraiseAddModel.setPraiseType(MsgClassConstants.PRAISE_BOLG_REPLY);
				messagePraiseAddModel.setOriginalCode(blogReplyModel.getBlogCode());
				messagePraiseAddModel.setContent(blogReplyModel.getReplyContent());
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
				// 查询参数
				BlogOperateVO blogOperateVO = new BlogOperateVO();
				blogOperateVO.setSubjectClass(BlogConstants.BLOG_LIKE_SUBJECT_REPLY); // 点赞类型
				blogOperateVO.setSubjectCode(replyCode); // 回复编号
				blogOperateVO.setUserCode(userCode); // 人员编号
				
				// 取消点赞
				boolean deleteFlag = blogOperateService.deleteLike(blogOperateVO);
				if (!deleteFlag) {
					log.error("BlogCommentController.likeReply：操作失败");
					throw BusinessException.build("BLOG_13002", "操作");
				}
				
				// 更新回复点赞量
				BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
				blogThirdDataVO.setSubjectCode(replyCode);
				blogThirdDataVO.setUpdateNumber(-1);
				blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_REPLY_LIKE);
				blogThirdDataVO.setModifyTime(time);
				producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
				
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_ADMIRE_PUBLISH);
				MessageIntegral.setAccountCode(userCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			} else {
				log.error("BlogController.likeReply：参数错误");
				throw BusinessException.build("COMMON_402", "actionClass");
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			result.setBody(body);
			
			log.info("BlogCommentController.likeReply：点赞/取消点赞回复成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogCommentController.likeReply", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getReportCommentList  
	 * @Description: 定位被举报的评论
	 * @param getReportCommentListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getReportCommentList",method = RequestMethod.POST)  
	@ResponseBody
	public ResultJSON getReportCommentList(@RequireValid GetReportCommentListVO getReportCommentListVO) throws BusinessException{  
		// 返回值
		ResultJSON result = null;
		JSONObject body = new JSONObject();
		
		try {
			log.info("BlogCommentController.getReportCommentList：定位被举报的评论");
			
			// 获取参数
			String pageSize = getReportCommentListVO.getPageSize(); // 每页条数
			String pageNo = getReportCommentListVO.getPageNo(); // 页码
			String queryTime = getReportCommentListVO.getQueryTime(); // 首次查询时间
			String blogCode = getReportCommentListVO.getBlogCode(); // 博客编号
			String commentCode = getReportCommentListVO.getCommentCode(); // 评论编号
			String userCode = getReportCommentListVO.getUserCode(); // 人员编号

			// 查询条件
			BlogCommentVO blogCommentVO = new BlogCommentVO();
			
			// 查询被举报的评论详情
			if (Integer.valueOf(pageNo) == 0) {
				blogCommentVO.setCommentCode(commentCode); // 评论编号
				blogCommentVO.setUserCode(userCode); // 人员编号
				
				// 查询
				BlogCommentModel blogComment = blogCommentService.getCommentAllInfo(blogCommentVO);
				if (blogComment == null) {
					log.error("BlogCommentController.getReportCommentList：查询评论详情失败");
					throw BusinessException.build("BLOG_13002", "查询");
				}
				
				body.put("reportCommentCode", blogComment.getCommentCode());
				body.put("reportUserCode", StringTools.formatToString(blogComment.getUserCode()));
				// 获取人员信息
				SysUserInfo sysUserInfo = iSysUserService.selectByCode(blogComment.getUserCode());
				body.put("reportUserName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
				body.put("reportUserPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
				body.put("reportUserOrgCode", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
				body.put("reportUserOrgName", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
				body.put("reportCommentContent", StringTools.formatToString(blogComment.getCommentContent()));
				body.put("reportCreateTime", StringTools.formatToString(blogComment.getCreateTime()));
				body.put("reportCommentLikeNum", StringTools.formatToString(blogComment.getCommentLikeNum()));
				body.put("reportCommentReplyNum", StringTools.formatToString(blogComment.getCommentReplyNum()));
				body.put("reportIsLike", StringTools.formatToString(blogComment.getIsLike()));
			}
			
			// 查询评论列表
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			blogCommentVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			blogCommentVO.setBlogCode(blogCode); // 博客编号
			blogCommentVO.setUserCode(userCode); // 人员编号
			PageBean pageBean = blogCommentService.getCommentList(pageParam, blogCommentVO);
			if (pageBean == null) {
				log.error("BlogCommentController.getReportCommentList：查询评论列表失败");
				throw BusinessException.build("BLOG_13002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				for (int i = 0; i < recordList.size(); i++) {
					BlogCommentModel blogCommentModel = (BlogCommentModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("commentCode", StringTools.formatToString(blogCommentModel.getCommentCode()));
					
					// 获取人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(blogCommentModel.getUserCode());
					jo.put("userCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserCode()));
					jo.put("userName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getUserName()));
					jo.put("userPhotoUrl", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getHeadPortrait()));
					jo.put("userOrgCode", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgCode()));
					jo.put("userOrgName", sysUserInfo == null ? "" : StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgFullname()));
					
					jo.put("commentContent", StringTools.formatToString(blogCommentModel.getCommentContent()));
					jo.put("createTime", StringTools.formatToString(blogCommentModel.getCreateTime()));
					jo.put("commentLikeNum", StringTools.formatToString(blogCommentModel.getCommentLikeNum()));
					jo.put("commentReplyNum", StringTools.formatToString(blogCommentModel.getCommentReplyNum()));
					jo.put("isLike", StringTools.formatToString(blogCommentModel.getIsLike()));
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			body.put("total", pageBean.getTotalCount());
			body.put("commentList", ja);
			result.setBody(body);
			
			log.info("BlogCommentController.getReportCommentList：定位被举报的评论成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogCommentController.getReportCommentList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getReportReplyList
	 * @Description: 定位博客评论回复
	 * @param getReportReplyListVO
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getReportReplyList",method = RequestMethod.POST)  
	@ResponseBody
	public ResultJSON getReportReplyList(@RequireValid GetReportReplyListVO getReportReplyListVO) throws BusinessException{  
		// 返回值
		ResultJSON result = null;
		JSONObject body = new JSONObject();
		
		try {
			log.info("BlogCommentController.getReportReplyList：定位博客评论回复列表");
			
			// 获取参数
			String pageSize = getReportReplyListVO.getPageSize(); // 每页条数
			String pageNo = getReportReplyListVO.getPageNo(); // 页码
			String queryTime = getReportReplyListVO.getQueryTime(); // 首次查询时间
			String commentCode = getReportReplyListVO.getCommentCode(); // 评论编号
			String replyCode = getReportReplyListVO.getReplyCode(); // 回复编号
			String userCode = getReportReplyListVO.getUserCode(); // 人员编号
			
			// 查询条件
			BlogCommentVO blogCommentVO = new BlogCommentVO();
			
			// 查询被举报的回复详情
			if (Integer.valueOf(pageNo) == 0) {
				blogCommentVO.setReplyCode(replyCode); // 回复编号
				blogCommentVO.setUserCode(userCode); // 人员编号

				// 查询
				BlogReplyModel blogReply = blogCommentService.getReplyAllInfo(blogCommentVO);
				if (blogReply == null) {
					log.error("BlogCommentController.getReportReplyList：查询回复详情失败");
					throw BusinessException.build("BLOG_13002", "查询");
				}
				
				body.put("reportReplyCode", StringTools.formatToString(blogReply.getReplyCode()));
				// 获取回复人信息
				SysUserInfo replyUserInfo = iSysUserService.selectByCode(blogReply.getUserCode());
				body.put("reportReplyUserCode", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getUserCode()));
				body.put("reportReplyUserName", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getUserName()));
				body.put("reportReplyUserPhotoUrl", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getHeadPortrait()));
				body.put("reportReplyUserOrgCode", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getManageOrgInfo().getSysOrgCode()));
				body.put("reportReplyUserOrgName", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getManageOrgInfo().getSysOrgFullname()));
				// 获取被回复人信息
				SysUserInfo toReplyUserInfo = iSysUserService.selectByCode(blogReply.getToReplyUserCode());
				body.put("reportToReplyUserCode", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getUserCode()));
				body.put("reportToReplyUserName", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getUserName()));
				body.put("reportToReplyUserPhotoUrl", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getHeadPortrait()));
				body.put("reportToReplyUserOrgCode", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getManageOrgInfo().getSysOrgCode()));
				body.put("reportToReplyUserOrgName", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getManageOrgInfo().getSysOrgFullname()));
				body.put("reportReplyContent", StringTools.formatToString(blogReply.getReplyContent()));
				body.put("reportReplyLikeNum", StringTools.formatToString(blogReply.getReplyLikeNum()));
				body.put("reportCreateTime", StringTools.formatToString(blogReply.getCreateTime()));
				body.put("reportIsLike", StringTools.formatToString(blogReply.getIsLike()));
			}
			
			// 查询回复列表
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			blogCommentVO.setQueryTime(Long.valueOf(queryTime)); // 首次查询时间
			blogCommentVO.setCommentCode(commentCode); // 评论编号
			blogCommentVO.setUserCode(userCode); // 人员编号
			PageBean pageBean = blogCommentService.getReplyList(pageParam, blogCommentVO);
			if (pageBean == null) {
				log.error("BlogCommentController.getReportReplyList：查询回复列表失败");
				throw BusinessException.build("BLOG_13002", "查询");
			}
			
			// 获取数据
			List<Object> recordList = pageBean.getRecordList();
			JSONArray ja = new JSONArray();
			if (recordList != null && !recordList.isEmpty()) {
				for (int i = 0; i < recordList.size(); i++) {
					BlogReplyModel blogReplyModel = (BlogReplyModel) recordList.get(i);
					JSONObject jo = new JSONObject();
					jo.put("replyCode", StringTools.formatToString(blogReplyModel.getReplyCode()));
					
					// 获取回复人信息
					SysUserInfo replyUserInfo = iSysUserService.selectByCode(blogReplyModel.getUserCode());
					jo.put("replyUserCode", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getUserCode()));
					jo.put("replyUserName", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getUserName()));
					jo.put("replyUserPhotoUrl", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getHeadPortrait()));
					jo.put("replyUserOrgCode", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getManageOrgInfo().getSysOrgCode()));
					jo.put("replyUserOrgName", replyUserInfo == null ? "" : StringTools.formatToString(replyUserInfo.getManageOrgInfo().getSysOrgFullname()));
					
					// 获取被回复人信息
					SysUserInfo toReplyUserInfo = iSysUserService.selectByCode(blogReplyModel.getToReplyUserCode());
					jo.put("toReplyUserCode", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getUserCode()));
					jo.put("toReplyUserName", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getUserName()));
					jo.put("toReplyUserPhotoUrl", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getHeadPortrait()));
					jo.put("toReplyUserOrgCode", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getManageOrgInfo().getSysOrgCode()));
					jo.put("toReplyUserOrgName", toReplyUserInfo == null ? "" : StringTools.formatToString(toReplyUserInfo.getManageOrgInfo().getSysOrgFullname()));
					
					jo.put("replyContent", StringTools.formatToString(blogReplyModel.getReplyContent()));
					jo.put("replyLikeNum", StringTools.formatToString(blogReplyModel.getReplyLikeNum()));
					jo.put("createTime", StringTools.formatToString(blogReplyModel.getCreateTime()));
					jo.put("isLike", StringTools.formatToString(blogReplyModel.getIsLike()));
					ja.add(jo);
				}
			}
			
			// 返回值赋值
			result = new ResultJSON("COMMON_200");
			body.put("total", pageBean.getTotalCount());
			body.put("replyList", ja);
			result.setBody(body);
			
			log.info("BlogCommentController.getReportReplyList：定位博客评论回复成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("BlogCommentController.getReportReplyList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return result;	
	}
	
}
