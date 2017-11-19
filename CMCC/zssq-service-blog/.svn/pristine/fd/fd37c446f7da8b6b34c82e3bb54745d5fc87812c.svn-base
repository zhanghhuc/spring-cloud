package com.zssq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.BlogCommentMapper;
import com.zssq.dao.mapper.BlogReplyMapper;
import com.zssq.dao.model.BlogCommentModel;
import com.zssq.dao.model.BlogReplyModel;
import com.zssq.dao.pojo.BlogComment;
import com.zssq.dao.pojo.BlogReply;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.BlogCommentService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.BlogCommentVO;

/**
 * 
 * @ClassName: BlogCommentServiceImpl  
 * @Description: 博客评论  
 * @author ZKZ  
 * @date 2017年3月24日  
 *
 */
@Service("blogCommentService")
public class BlogCommentServiceImpl implements BlogCommentService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BlogCommentMapper blogCommentMapper;
	@Autowired
	private BlogReplyMapper blogReplyMapper;

	/**
	 * 查询评论列表
	 */
	@Override
	public PageBean getCommentList(PageParam pageParam, BlogCommentVO blogCommentVO) throws BusinessException {
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null) {
				log.error("BlogCommentServiceImpl.getCommentList：pageParam为空");
				throw BusinessException.build("COMMON_402", "pageParam");
			}
			if (blogCommentVO == null) {
				log.error("BlogCommentServiceImpl.getCommentList：blogCommentVO为空");
				throw BusinessException.build("COMMON_402", "blogCommentVO");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			Long queryTime = blogCommentVO.getQueryTime(); // 首次查询时间
			String blogCode = blogCommentVO.getBlogCode(); // 博客编号
			String userCode = blogCommentVO.getUserCode(); // 人员编号
			
			// 参数校验
			if (pageSize == null) {
				log.error("BlogCommentServiceImpl.getCommentList：pageSize为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if (pageNo == null) {
				log.error("BlogCommentServiceImpl.getCommentList：pageNo为空");
				throw BusinessException.build("COMMON_402", "pageNo");
			}
			if (queryTime == null || queryTime == 0) {
				log.error("BlogCommentServiceImpl.getCommentList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogCommentServiceImpl.getCommentList：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogCommentServiceImpl.getCommentList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("queryTime", queryTime); // 首次查询时间
			paramMap.put("blogCode", blogCode); // 博客编号
			paramMap.put("userCode", userCode); // 人员编号
			
			// 查询列表总数
			int count = blogCommentMapper.selectCount(paramMap);
			
			// 查询列表内容
			List<BlogCommentModel> blogCommentList = blogCommentMapper.list(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, blogCommentList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}

	/**
	 * 查询回复列表
	 */
	@Override
	public PageBean getReplyList(PageParam pageParam, BlogCommentVO blogCommentVO) throws BusinessException {
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null) {
				log.error("BlogCommentServiceImpl.getReplyList：pageParam为空");
				throw BusinessException.build("COMMON_402", "pageParam");
			}
			if (blogCommentVO == null) {
				log.error("BlogCommentServiceImpl.getReplyList：blogCommentVO为空");
				throw BusinessException.build("COMMON_402", "blogCommentVO");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			Long queryTime = blogCommentVO.getQueryTime(); // 首次查询时间
			String commentCode = blogCommentVO.getCommentCode(); // 评论编号
			String userCode = blogCommentVO.getUserCode(); // 人员编号
			
			// 参数校验
			if (pageSize == null) {
				log.error("BlogCommentServiceImpl.getReplyList：pageSize为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if (pageNo == null) {
				log.error("BlogCommentServiceImpl.getReplyList：pageNo为空");
				throw BusinessException.build("COMMON_402", "pageNo");
			}
			if (queryTime == null || queryTime == 0) {
				log.error("BlogCommentServiceImpl.getReplyList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if (StringUtils.isBlank(commentCode)) {
				log.error("BlogCommentServiceImpl.getReplyList：commentCode为空");
				throw BusinessException.build("COMMON_402", "commentCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogCommentServiceImpl.getReplyList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("queryTime", queryTime); // 首次查询时间
			paramMap.put("commentCode", commentCode); // 评论编号
			paramMap.put("userCode", userCode); // 人员编号
			
			// 查询列表总数
			int count = blogReplyMapper.selectCount(paramMap);
			
			// 查询列表内容
			List<BlogReplyModel> blogReplyList = blogReplyMapper.list(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, blogReplyList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}
	
	/**
	 * 获取评论详情
	 */
	@Override
	public BlogCommentModel getCommentInfo(BlogCommentVO blogCommentVO) throws BusinessException {
		// 返回值
		BlogCommentModel blogCommentModel = null;
		
		try {
			// 参数校验
			if (blogCommentVO == null) {
				log.error("BlogCommentServiceImpl.getCommentInfo：blogCommentVO为空");
				throw BusinessException.build("COMMON_402", "blogCommentVO");
			}
			
			// 获取参数
			String commentCode = blogCommentVO.getCommentCode(); // 评论编号
			
			// 参数校验
			if (StringUtils.isBlank(commentCode)) {
				log.error("BlogCommentServiceImpl.getCommentInfo：commentCode为空");
				throw BusinessException.build("COMMON_402", "commentCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("commentCode", commentCode); // 评论编号
			
			// 查询
			blogCommentModel = blogCommentMapper.select(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		
		return blogCommentModel;
	}
	
	/**
	 * 获取评论详情（所有）
	 */
	@Override
	public BlogCommentModel getCommentAllInfo(BlogCommentVO blogCommentVO) throws BusinessException {
		// 返回值
		BlogCommentModel blogCommentModel = null;
		
		try {
			// 参数校验
			if (blogCommentVO == null) {
				log.error("BlogCommentServiceImpl.getCommentAllInfo：blogCommentVO为空");
				throw BusinessException.build("COMMON_402", "blogCommentVO");
			}
			
			// 获取参数
			String commentCode = blogCommentVO.getCommentCode(); // 评论编号
			String userCode = blogCommentVO.getUserCode(); // 用户编号
			
			// 参数校验
			if (StringUtils.isBlank(commentCode)) {
				log.error("BlogCommentServiceImpl.getCommentAllInfo：commentCode为空");
				throw BusinessException.build("COMMON_402", "commentCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogCommentServiceImpl.getCommentAllInfo：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("commentCode", commentCode); // 评论编号
			paramMap.put("userCode", userCode); // 用户编号
			
			// 查询
			blogCommentModel = blogCommentMapper.getCommentAllInfo(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		
		return blogCommentModel;
	}
	
	/**
	 * 获取回复详情
	 */
	@Override
	public BlogReplyModel getReplyInfo(BlogCommentVO blogCommentVO) throws BusinessException {
		// 返回值
		BlogReplyModel blogReplyModel = null;
		
		try {
			// 参数校验
			if (blogCommentVO == null) {
				log.error("BlogCommentServiceImpl.getReplyInfo：blogCommentVO为空");
				throw BusinessException.build("COMMON_402", "blogCommentVO");
			}
			
			// 获取参数
			String replyCode = blogCommentVO.getReplyCode(); // 评论编号
			
			// 参数校验
			if (StringUtils.isBlank(replyCode)) {
				log.error("BlogCommentServiceImpl.getReplyInfo：replyCode为空");
				throw BusinessException.build("COMMON_402", "replyCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("replyCode", replyCode); // 评论编号
			
			// 查询
			blogReplyModel = blogReplyMapper.select(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		
		return blogReplyModel;
	}

	/**
	 * 获取回复详情（所有）
	 */
	@Override
	public BlogReplyModel getReplyAllInfo(BlogCommentVO blogCommentVO) throws BusinessException {
		// 返回值
		BlogReplyModel blogReplyModel = null;
		
		try {
			// 参数校验
			if (blogCommentVO == null) {
				log.error("BlogCommentServiceImpl.getReplyAllInfo：blogCommentVO为空");
				throw BusinessException.build("COMMON_402", "blogCommentVO");
			}
			
			// 获取参数
			String replyCode = blogCommentVO.getReplyCode(); // 回复编号
			String userCode = blogCommentVO.getUserCode(); // 人员编号
			
			// 参数校验
			if (StringUtils.isBlank(replyCode)) {
				log.error("BlogCommentServiceImpl.getReplyAllInfo：replyCode为空");
				throw BusinessException.build("COMMON_402", "replyCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogCommentServiceImpl.getReplyAllInfo：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("replyCode", replyCode); // 回复编号
			paramMap.put("userCode", userCode); // 人员编号
			
			// 查询
			blogReplyModel = blogReplyMapper.getReplyAllInfo(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		
		return blogReplyModel;
	}

	/**
	 * 保存评论信息
	 */
	@Override
	public boolean saveComment(BlogComment blogComment) throws BusinessException {
		try {
			// 参数校验
			if (blogComment == null) {
				log.error("BlogCommentServiceImpl.saveComment：blogComment为空");
				throw BusinessException.build("COMMON_402", "blogComment");
			}
			
			// 插入
			int insertNum = blogCommentMapper.insert(blogComment);
			if (insertNum != 1) {
				log.error("BlogCommentServiceImpl.saveComment：插入" + insertNum + "条评论信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 保存回复信息
	 */
	@Override
	public boolean saveReply(BlogReply blogReply) throws BusinessException {
		try {
			// 参数校验
			if (blogReply == null) {
				log.error("BlogCommentServiceImpl.saveReply：blogReply为空");
				throw BusinessException.build("COMMON_402", "blogReply");
			}
			
			// 插入
			int insertNum = blogReplyMapper.insert(blogReply);
			if (insertNum != 1) {
				log.error("BlogCommentServiceImpl.saveReply：插入" + insertNum + "条回复信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 删除评论
	 */
	@Override
	public boolean updateCommentDeleteStatus(BlogCommentVO blogCommentVO) throws BusinessException {
		try {
			// 参数校验
			if (blogCommentVO == null) {
				log.error("BlogCommentServiceImpl.updateCommentDeleteStatus：blogCommentVO为空");
				throw BusinessException.build("COMMON_402", "blogCommentVO");
			}
			
			// 获取参数
			String commentCode = blogCommentVO.getCommentCode(); // 评论编号
			String userCode = blogCommentVO.getUserCode(); // 人员编号
			
			// 参数校验
			if (StringUtils.isBlank(commentCode)) {
				log.error("BlogCommentServiceImpl.updateCommentDeleteStatus：commentCode为空");
				throw BusinessException.build("COMMON_402", "commentCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogCommentServiceImpl.updateCommentDeleteStatus：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("commentCode", commentCode); // 评论编号
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("modifyTime", blogCommentVO.getModifyTime()); // 修改时间
			
			// 删除评论
			int deleteCommentNum = blogCommentMapper.delete(paramMap);
			if (deleteCommentNum != 1) {
				log.error("BlogDraftServiceImpl.updateCommentDeleteStatus：删除" + deleteCommentNum + "条评论信息");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 删除回复
	 */
	@Override
	public boolean updateReplyDeleteStatus(BlogCommentVO blogCommentVO) throws BusinessException {
		try {
			// 参数校验
			if (blogCommentVO == null) {
				log.error("BlogCommentServiceImpl.updateReplyDeleteStatus：blogCommentVO为空");
				throw BusinessException.build("COMMON_402", "blogCommentVO");
			}
			
			// 获取参数
			String replyCode = blogCommentVO.getReplyCode(); // 回复编号
			String userCode = blogCommentVO.getUserCode(); // 人员编号
			
			// 参数校验
			if (StringUtils.isBlank(replyCode)) {
				log.error("BlogCommentServiceImpl.updateReplyDeleteStatus：replyCode为空");
				throw BusinessException.build("COMMON_402", "replyCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogCommentServiceImpl.updateReplyDeleteStatus：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("replyCode", replyCode); // 回复编号
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("modifyTime", blogCommentVO.getModifyTime()); // 修改时间
			
			// 删除评论
			int deleteReplyNum = blogReplyMapper.delete(paramMap);
			if (deleteReplyNum != 1) {
				log.error("BlogDraftServiceImpl.updateReplyDeleteStatus：删除" + deleteReplyNum + "条回复信息");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 定位评论
	 */
	@Override
	public PageBean getReportCommentList(BlogCommentVO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			if(StringTools.isEmpty(qo.getBlogCode())){
				log.error("BlogCommentServiceImpl.getReportCommentList：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if(StringTools.isEmpty(qo.getCommentCode())){
				log.error("BlogCommentServiceImpl.getReportCommentList：commentCode为空");
				throw BusinessException.build("COMMON_402", "commentCode");
			}
			if(qo.getQueryTime()==null){
				log.error("BlogCommentServiceImpl.getReportReplyList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if(qo.getPageSize()==null||qo.getPageSize()<1){
				log.error("BlogCommentServiceImpl.getReportReplyList：queryTime为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			// 获取评论列表
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("queryTime", qo.getQueryTime());
			paramsMap.put("limitCount", qo.getPageSize());
			paramsMap.put("commentCode", qo.getCommentCode());
			paramsMap.put("blogCode", qo.getBlogCode());
			//当前记录所在的 页码
			int pageNo = blogCommentMapper.queryCommentCurrentPageNum(paramsMap);
			// 查询数据
			paramsMap.put("limitStart", pageNo*qo.getPageSize()); // 查询开始条数
			
			// 查询列表总数
			int count = blogCommentMapper.selectCount(paramsMap);
			
			// 查询列表内容
			List<BlogCommentModel> blogCommentList = blogCommentMapper.list(paramsMap);
			// 返回值
			pageBean = new PageBean(pageNo, qo.getPageSize(), count, blogCommentList);
		}catch(Exception e){
			throw new BusinessException(e);
		}
		return pageBean;
	}
	
	/**
	 * 定位回复
	 */
	@Override
	public PageBean getReportReplyList(BlogCommentVO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			if(StringTools.isEmpty(qo.getBlogCode())){
				log.error("BlogCommentServiceImpl.getReportReplyList：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if(StringTools.isEmpty(qo.getCommentCode())){
				log.error("BlogCommentServiceImpl.getReportReplyList：commentCode为空");
				throw BusinessException.build("COMMON_402", "commentCode");
			}
			if(qo.getQueryTime()==null){
				log.error("BlogCommentServiceImpl.getReportReplyList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if(qo.getPageSize()==null||qo.getPageSize()<1){
				log.error("BlogCommentServiceImpl.getReportReplyList：pageSize为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if(StringTools.isEmpty(qo.getReplyCode())){
				log.error("BlogCommentServiceImpl.getReportReplyList：replyCode为空");
				throw BusinessException.build("COMMON_402", "replyCode");
			}
			// 获取评论列表
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("queryTime", qo.getQueryTime());
			paramsMap.put("limitCount", qo.getPageSize());
			paramsMap.put("commentCode", qo.getCommentCode());
			paramsMap.put("blogCode", qo.getBlogCode());
			paramsMap.put("replyCode", qo.getReplyCode());
			//当前记录所在的 页码
			int pageNo = blogReplyMapper.queryReplyCurrentPageNum(paramsMap);
			// 查询数据
			paramsMap.put("limitStart", pageNo*qo.getPageSize()); // 查询开始条数
			
			// 查询列表总数
			int count = blogReplyMapper.selectCount(paramsMap);
			
			// 查询列表内容
			List<BlogReplyModel> blogCommentList = blogReplyMapper.list(paramsMap);
			// 返回值
			pageBean = new PageBean(pageNo, qo.getPageSize(), count, blogCommentList);
		}catch(Exception e){
			throw new BusinessException(e);
		}
		return pageBean;
	}

}
