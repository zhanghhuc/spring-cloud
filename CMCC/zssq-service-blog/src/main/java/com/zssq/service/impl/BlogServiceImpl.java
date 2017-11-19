package com.zssq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.BlogConstants;
import com.zssq.dao.mapper.BlogContentMapper;
import com.zssq.dao.mapper.BlogDataMapper;
import com.zssq.dao.mapper.BlogDraftContentMapper;
import com.zssq.dao.mapper.BlogDraftMapper;
import com.zssq.dao.mapper.BlogInfoMapper;
import com.zssq.dao.model.BlogModel;
import com.zssq.dao.pojo.BlogContent;
import com.zssq.dao.pojo.BlogData;
import com.zssq.dao.pojo.BlogInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.BlogService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.BlogVO;

/**
 * 
 * @ClassName: BlogServiceImpl  
 * @Description: 博客  
 * @author ZKZ  
 * @date 2017年4月1日  
 *
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BlogInfoMapper blogInfoMapper;
	@Autowired
	private BlogContentMapper blogContentMapper;
//	@Autowired
//	private BlogAttachMapper blogAttachMapper;
	@Autowired
	private BlogDataMapper blogDataMapper;
	@Autowired
	private BlogDraftMapper blogDraftMapper;
	@Autowired
	private BlogDraftContentMapper blogDraftContentMapper;
//	@Autowired
//	private BlogDraftAttachMapper blogDraftAttachMapper;

	/**
	 * 查询博客列表
	 */
	@Override
	public PageBean getBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException {
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null) {
				log.error("BlogServiceImpl.getBlogList：pageParam为空");
				throw BusinessException.build("COMMON_402", "pageParam");
			}
			if (blogVO == null) {
				log.error("BlogServiceImpl.getBlogList：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			Byte blogDepend = blogVO.getBlogDepend(); // 从属关系
			String userCode = blogVO.getUserCode(); // 人员编号
			String teamCode = blogVO.getTeamCode(); // 班组编号
			String classCode = blogVO.getClassCode(); // 分类编号
			Long queryTime = blogVO.getQueryTime(); // 首次查询时间
			String loginUserCode = blogVO.getLoginUserCode(); // 当前登录用户编号
			
			// 参数校验
			if (pageSize == null) {
				log.error("BlogServiceImpl.getBlogList：pageSize为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if (pageNo == null) {
				log.error("BlogServiceImpl.getBlogList：pageNo为空");
				throw BusinessException.build("COMMON_402", "pageNo");
			}
			if (queryTime == null || queryTime == 0) {
				log.error("BlogServiceImpl.getBlogList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if (StringUtils.isBlank(loginUserCode)) {
				log.error("BlogServiceImpl.getBlogList：loginUserCode为空");
				throw BusinessException.build("COMMON_402", "loginUserCode");
			}
			if (blogDepend == null || (!BlogConstants.BLOG_DEPEND_USER.equals(blogDepend)
					&& !BlogConstants.BLOG_DEPEND_TEAM.equals(blogDepend))) {
				log.error("BlogServiceImpl.getBlogList：博客从属关系错误");
				throw BusinessException.build("COMMON_402", "blogDepend");
			} else if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogServiceImpl.getBlogList：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			} else if (BlogConstants.BLOG_DEPEND_USER.equals(blogDepend) && StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.getBlogList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("queryTime", queryTime); // 首次查询时间
			paramMap.put("blogDepend", blogDepend); // 从属关系
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("teamCode", teamCode); // 班组编号
			paramMap.put("classCode", classCode); // 分类编号
			paramMap.put("loginUserCode", loginUserCode); // 当前登录用户编号
			
			// 查询列表总数
			int count = blogInfoMapper.selectCount(paramMap);
			
			// 查询列表内容
			List<BlogModel> blogList = blogInfoMapper.list(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, blogList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}
	
	/**
	 * 查询代发博客列表
	 */
	@Override
	public PageBean getAgentBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException {
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null) {
				log.error("BlogServiceImpl.getAgentBlogList：pageParam为空");
				throw BusinessException.build("COMMON_402", "pageParam");
			}
			if (blogVO == null) {
				log.error("BlogServiceImpl.getAgentBlogList：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			String userCode = blogVO.getUserCode(); // 人员编号
			String agentUserCode = blogVO.getAgentUserCode(); // 代发人员编号
			Long queryTime = blogVO.getQueryTime(); // 首次查询时间
			String loginUserCode = blogVO.getLoginUserCode(); // 当前登录用户编号
			
			// 参数校验
			if (pageSize == null) {
				log.error("BlogServiceImpl.getAgentBlogList：pageSize为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if (pageNo == null) {
				log.error("BlogServiceImpl.getAgentBlogList：pageNo为空");
				throw BusinessException.build("COMMON_402", "pageNo");
			}
			if (queryTime == null || queryTime == 0) {
				log.error("BlogServiceImpl.getAgentBlogList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.getAgentBlogList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (StringUtils.isBlank(loginUserCode)) {
				log.error("BlogServiceImpl.getAgentBlogList：loginUserCode为空");
				throw BusinessException.build("COMMON_402", "loginUserCode");
			}
			if (StringUtils.isBlank(agentUserCode)) {
				log.error("BlogServiceImpl.getAgentBlogList：agentUserCode为空");
				throw BusinessException.build("COMMON_402", "agentUserCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("queryTime", queryTime); // 首次查询时间
			paramMap.put("blogDepend", BlogConstants.BLOG_DEPEND_USER); // 从属关系
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("blogAgentFlag", BlogConstants.BLOG_YES); // 代发标识
			paramMap.put("agentUserCode", agentUserCode); // 代发人员编号
			paramMap.put("loginUserCode", loginUserCode); // 当前登录用户编号
			
			// 查询列表总数
			int count = blogInfoMapper.selectCount(paramMap);
			
			// 查询列表内容
			List<BlogModel> blogList = blogInfoMapper.list(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, blogList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}

	/**
	 * 查询精品博客列表
	 */
	@Override
	public PageBean getQualityBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException {
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null) {
				log.error("BlogServiceImpl.getQualityBlogList：pageParam为空");
				throw BusinessException.build("COMMON_402", "pageParam");
			}
			if (blogVO == null) {
				log.error("BlogServiceImpl.getQualityBlogList：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			String userCode = blogVO.getUserCode(); // 人员编号
			Long queryTime = blogVO.getQueryTime(); // 首次查询时间
			String loginUserCode = blogVO.getLoginUserCode(); // 当前登录用户编号
			
			// 参数校验
			if (pageSize == null) {
				log.error("BlogServiceImpl.getQualityBlogList：分页信息为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if (pageNo == null) {
				log.error("BlogServiceImpl.getQualityBlogList：分页信息为空");
				throw BusinessException.build("COMMON_402", "pageNo");
			}
			if (queryTime == null || queryTime == 0) {
				log.error("BlogServiceImpl.getQualityBlogList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.getQualityBlogList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (StringUtils.isBlank(loginUserCode)) {
				log.error("BlogServiceImpl.getQualityBlogList：loginUserCode为空");
				throw BusinessException.build("COMMON_402", "loginUserCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("queryTime", queryTime); // 首次查询时间
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("loginUserCode", loginUserCode); // 当前登录用户编号
			
			// 查询列表总数
			int count = blogInfoMapper.selectQualityCount(paramMap);
			
			// 查询列表内容
			List<BlogModel> blogList = blogInfoMapper.qualityList(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, blogList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}

	/**
	 * 查询收藏博客列表
	 */
	@Override
	public PageBean getCollectBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException {
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null) {
				log.error("BlogServiceImpl.getCollectBlogList：pageParam为空");
				throw BusinessException.build("COMMON_402", "pageParam");
			}
			if (blogVO == null) {
				log.error("BlogServiceImpl.getCollectBlogList：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			String userCode = blogVO.getUserCode(); // 人员编号
			Long queryTime = blogVO.getQueryTime(); // 首次查询时间
			String loginUserCode = blogVO.getLoginUserCode(); // 当前登录用户编号
			
			// 参数校验
			if (pageSize == null) {
				log.error("BlogServiceImpl.getCollectBlogList：pageSize为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if (pageNo == null) {
				log.error("BlogServiceImpl.getCollectBlogList：pageNo为空");
				throw BusinessException.build("COMMON_402", "pageNo");
			}
			if (queryTime == null || queryTime == 0) {
				log.error("BlogServiceImpl.getCollectBlogList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.getCollectBlogList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (StringUtils.isBlank(loginUserCode)) {
				log.error("BlogServiceImpl.getCollectBlogList：loginUserCode为空");
				throw BusinessException.build("COMMON_402", "loginUserCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("queryTime", queryTime); // 首次查询时间
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("loginUserCode", loginUserCode); // 当前登录用户编号
			
			// 查询列表总数
			int count = blogInfoMapper.selectCollectCount(paramMap);
			
			// 查询列表内容
			List<BlogModel> blogList = blogInfoMapper.collectList(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, blogList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}

	/**
	 * 查询订阅博客列表
	 */
	@Override
	public PageBean getSubBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException {
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null) {
				log.error("BlogServiceImpl.getSubBlogList：pageParam为空");
				throw BusinessException.build("COMMON_402", "pageParam");
			}
			if (blogVO == null) {
				log.error("BlogServiceImpl.getSubBlogList：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			String userCode = blogVO.getUserCode(); // 人员编号
			Long queryTime = blogVO.getQueryTime(); // 首次查询时间
			String loginUserCode = blogVO.getLoginUserCode(); // 当前登录用户编号
			
			// 参数校验
			if (pageSize == null) {
				log.error("BlogServiceImpl.getSubBlogList：pageSize为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if (pageNo == null) {
				log.error("BlogServiceImpl.getSubBlogList：pageNo为空");
				throw BusinessException.build("COMMON_402", "pageNo");
			}
			if (queryTime == null || queryTime == 0) {
				log.error("BlogServiceImpl.getSubBlogList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.getSubBlogList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (StringUtils.isBlank(loginUserCode)) {
				log.error("BlogServiceImpl.getSubBlogList：loginUserCode为空");
				throw BusinessException.build("COMMON_402", "loginUserCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("queryTime", queryTime); // 首次查询时间
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("loginUserCode", loginUserCode); // 当前登录用户编号
			
			// 查询列表总数
			int count = blogInfoMapper.selectSubCount(paramMap);
			
			// 查询列表内容
			List<BlogModel> blogList = blogInfoMapper.subList(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, blogList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}
	
	/**
	 * 查询定时发布博客列表
	 */
	@Override
	public PageBean getPlanBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException {
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null) {
				log.error("BlogServiceImpl.getPlanBlogList：pageParam为空");
				throw BusinessException.build("COMMON_402", "pageParam");
			}
			if (blogVO == null) {
				log.error("BlogServiceImpl.getPlanBlogList：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			Long queryTime = blogVO.getQueryTime(); // 首次查询时间
			Byte blogDepend = blogVO.getBlogDepend(); // 从属关系
			String userCode = blogVO.getUserCode(); // 人员编号
			String teamCode = blogVO.getTeamCode(); // 班组编号
			
			// 参数校验
			if (pageSize == null) {
				log.error("BlogServiceImpl.getPlanBlogList：pageSize为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if (pageNo == null) {
				log.error("BlogServiceImpl.getPlanBlogList：pageNo为空");
				throw BusinessException.build("COMMON_402", "pageNo");
			}
			if (queryTime == null || queryTime == 0) {
				log.error("BlogServiceImpl.getPlanBlogList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.getPlanBlogList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (blogDepend == null || (!BlogConstants.BLOG_DEPEND_USER.equals(blogDepend)
					&& !BlogConstants.BLOG_DEPEND_TEAM.equals(blogDepend))) {
				log.error("BlogServiceImpl.getPlanBlogList：博客从属关系错误");
				throw BusinessException.build("COMMON_402", "blogDepend");
			} else if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogServiceImpl.getPlanBlogList：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("queryTime", queryTime); // 首次查询时间
			paramMap.put("blogDepend", blogDepend); // 从属关系
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("teamCode", teamCode); // 班组编号
			
			// 查询个数
			int count = blogInfoMapper.selectPlanCount(paramMap);
			
			// 查询列表内容
			List<BlogInfo> blogList = blogInfoMapper.planList(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, blogList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}
	
	/**
	 * 查询定时发布博客列表
	 */
	public List<BlogInfo> getPlanBlogAllList(BlogVO blogVO) throws BusinessException {
		// 返回值
		List<BlogInfo> blogInfoList = null;
		
		try {
			// 参数校验
			if (blogVO == null) {
				log.error("BlogServiceImpl.getPlanBlogAllList：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			Long queryTime = blogVO.getQueryTime(); // 查询时间
			
			// 参数校验
			if (queryTime == null || queryTime == 0) {
				log.error("BlogServiceImpl.getPlanBlogAllList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("queryTime", queryTime); // 查询时间
			
			// 查询
			blogInfoList = blogInfoMapper.planAllList(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		
		return blogInfoList;
	}
	
	/**
	 * 查询博客基本信息
	 */
	@Override
	public BlogInfo getBlogMiniInfo(BlogVO blogVO) throws BusinessException {
		// 返回值
		BlogInfo blogInfo = null;
		
		try {
			// 参数校验
			if (blogVO == null) {
				log.error("BlogServiceImpl.getBlogMiniInfo：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			String blogCode = blogVO.getBlogCode(); // 博客编号
			
			// 参数校验
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogServiceImpl.getBlogMiniInfo：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("blogCode", blogCode); // 博客编号
			
			// 查询
			blogInfo = blogInfoMapper.selectMiniInfo(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		
		return blogInfo;
	}

	/**
	 * 查询博客内容
	 */
	@Override
	public BlogModel getBlogBaseInfo(BlogVO blogVO) throws BusinessException {
		// 返回值
		BlogModel blogModel = null;
		
		try {
			// 参数校验
			if (blogVO == null) {
				log.error("BlogServiceImpl.getBlogBaseInfo：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			String blogCode = blogVO.getBlogCode(); // 博客编号
			String userCode = blogVO.getUserCode(); // 人员编号
			
			// 参数校验
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogServiceImpl.getBlogBaseInfo：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.getBlogBaseInfo：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("blogCode", blogCode); // 博客编号
			paramMap.put("userCode", userCode); // 人员编号
			
			// 查询
			blogModel = blogInfoMapper.selectBaseInfo(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		
		return blogModel;
	}
	
	/**
	 * 查询博客详情
	 */
	@Override
	public BlogModel getBlogInfo(BlogVO blogVO) throws BusinessException {
		// 返回值
		BlogModel blogModel = null;
		
		try {
			// 参数校验
			if (blogVO == null) {
				log.error("BlogServiceImpl.getBlogInfo：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			String blogCode = blogVO.getBlogCode(); // 博客编号
			String userCode = blogVO.getLoginUserCode(); // 人员编号
			
			// 参数校验
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogServiceImpl.getBlogInfo：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.getBlogInfo：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("blogCode", blogCode); // 博客编号
			paramMap.put("loginUserCode", userCode); // 人员编号
			
			// 查询
			blogModel = blogInfoMapper.select(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		
		return blogModel;
	}

	/**
	 * 删除个人博客
	 */
	@Override
	public boolean deleteUserBlog(BlogVO blogVO) throws BusinessException {
		try {
			// 参数校验
			if (blogVO == null) {
				log.error("BlogServiceImpl.deleteUserBlog：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			String blogCode = blogVO.getBlogCode(); // 博客编号
			String userCode = blogVO.getUserCode(); // 人员编号
			
			// 参数校验
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogServiceImpl.deleteUserBlog：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.deleteUserBlog：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("blogCode", blogCode); // 博客编号
			paramMap.put("blogDepend", BlogConstants.BLOG_DEPEND_USER); // 从属关系
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("modifyTime", blogVO.getModifyTime()); // 修改时间
			
			// 修改
			int deleteNum = blogInfoMapper.delete(paramMap);
			if (deleteNum != 1) {
				log.error("BlogServiceImpl.deleteUserBlog：删除" + deleteNum + "条博客信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 删除代发博客
	 */
	@Override
	public boolean deleteBlogByAgent(BlogVO blogVO) throws BusinessException {
		try {
			// 参数校验
			if (blogVO == null) {
				log.error("BlogServiceImpl.deleteBlogByAgent：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			String blogCode = blogVO.getBlogCode(); // 博客编号
			String userCode = blogVO.getUserCode(); // 人员编号
			String agentUserCode = blogVO.getAgentUserCode(); // 人员编号
			
			// 参数校验
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogServiceImpl.deleteBlogByAgent：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.deleteBlogByAgent：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (StringUtils.isBlank(agentUserCode)) {
				log.error("BlogServiceImpl.deleteBlogByAgent：agentUserCode为空");
				throw BusinessException.build("COMMON_402", "agentUserCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("blogCode", blogCode); // 博客编号
			paramMap.put("blogDepend", BlogConstants.BLOG_DEPEND_USER); // 从属关系
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("blogAgentFlag", BlogConstants.BLOG_YES); // 代发标识
			paramMap.put("agentUserCode", agentUserCode); // 代发人员编号
			paramMap.put("modifyTime", blogVO.getModifyTime()); // 修改时间
			
			// 修改
			int deleteNum = blogInfoMapper.delete(paramMap);
			if (deleteNum != 1) {
				log.error("BlogServiceImpl.deleteBlogByAgent：删除" + deleteNum + "条博客信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 删除班组博客
	 */
	@Override
	public boolean deleteTeamBlog(BlogVO blogVO) throws BusinessException {
		try {
			// 参数校验
			if (blogVO == null) {
				log.error("BlogServiceImpl.deleteTeamBlog：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			String blogCode = blogVO.getBlogCode(); // 博客编号
			String userCode = blogVO.getUserCode(); // 人员编号
			String teamCode = blogVO.getTeamCode(); // 班组编号
			
			// 参数校验
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogServiceImpl.deleteTeamBlog：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.deleteTeamBlog：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (StringUtils.isBlank(teamCode)) {
				log.error("BlogServiceImpl.deleteTeamBlog：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("blogCode", blogCode); // 博客编号
			paramMap.put("blogDepend", BlogConstants.BLOG_DEPEND_TEAM); // 从属关系
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("teamCode", teamCode); // 班组编号
			paramMap.put("modifyTime", blogVO.getModifyTime()); // 修改时间
			
			// 修改
			int deleteNum = blogInfoMapper.delete(paramMap);
			if (deleteNum != 1) {
				log.error("BlogServiceImpl.deleteTeamBlog：删除" + deleteNum + "条博客信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 保存博客信息
	 */
	@Override
	public boolean saveBlog(BlogInfo blogInfo, BlogContent blogContent, String draftCode)
			throws BusinessException {
		try {
			// 参数校验
			if (blogInfo == null) {
				log.error("BlogServiceImpl.saveBlog：blogInfo为空");
				throw BusinessException.build("COMMON_402", "blogInfo");
			}
			if (blogContent == null) {
				log.error("BlogServiceImpl.saveBlog：blogContent为空");
				throw BusinessException.build("COMMON_402", "blogContent");
			}
			
			// 获取参数
			Long blogPlanPublishTime = blogInfo.getBlogPlanPublishTime(); // 定时发布时间
			Long createTime = blogInfo.getCreateTime(); // 创建时间
			
			// 参数校验
			if (blogPlanPublishTime != null && blogPlanPublishTime != 0) {
				if (blogPlanPublishTime < createTime) {
					log.error("BlogServiceImpl.saveBlog：定时发布时间不能小于当前时间");
					throw BusinessException.build("BLOG_13007");
				}
				if (!StringUtils.isBlank(draftCode)) {
					log.error("BlogServiceImpl.saveBlog：定时发布博客没有草稿");
					throw BusinessException.build("COMMON_402", "draftCode");
				}
			}
			
			// 插入博客信息
			int insertBlogNum = blogInfoMapper.insert(blogInfo);
			if (insertBlogNum != 1) {
				log.error("BlogServiceImpl.saveBlog：插入" + insertBlogNum + "条博客信息 ");
				return false;
			}
			
			// 插入博客正文信息
			int insertContentNum = blogContentMapper.insert(blogContent);
			if (insertContentNum != 1) {
				log.error("BlogServiceImpl.saveBlog：插入" + insertContentNum + "条博客正文信息 ");
				throw BusinessException.build("BLOG_13002", "保存");
			}
			
			// 插入博客数据信息
			BlogData blogData = new BlogData();
			blogData.setDataCode(UUIDHelper.getUUID());
			blogData.setTenantCode(blogInfo.getTenantCode());
			blogData.setOrgCode(blogInfo.getOrgCode());
			blogData.setCreateTime(blogInfo.getCreateTime());
			blogData.setModifyTime(blogInfo.getModifyTime());
			blogData.setBlogCode(blogInfo.getBlogCode());
			blogData.setTeamQualityNum(0);
			blogData.setGroupQualityNum(0);
			blogData.setProvinceQualityNum(0);
			blogData.setCityQualityNum(0);
			blogData.setReadNum(0);
			blogData.setLikeNum(0);
			blogData.setCollectNum(0);
			blogData.setForwardNum(0);
			blogData.setCommentNum(0);
			blogData.setShareNum(0);
			int insertDataNum = blogDataMapper.insert(blogData);
			if (insertBlogNum != 1) {
				log.error("BlogServiceImpl.saveBlog：插入" + insertDataNum + "条博客数据信息 ");
				throw BusinessException.build("BLOG_13002", "保存");
			}
			
			// 删除草稿信息
			if (!StringUtils.isBlank(draftCode)) {
				// 拼接参数
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("draftCode", draftCode); // 草稿编号
				paramMap.put("draftDepend", blogInfo.getBlogDepend()); // 从属关系
				paramMap.put("userCode", blogInfo.getUserCode()); // 人员编号
				paramMap.put("teamCode", blogInfo.getTeamCode()); // 班组编号
				
				// 删除草稿
				int deleteDraftNum = blogDraftMapper.delete(paramMap);
				if (deleteDraftNum != 1) {
					log.error("BlogServiceImpl.saveBlog：删除" + deleteDraftNum + "条博客草稿信息");
					throw BusinessException.build("BLOG_13002", "保存");
				}
				
				// 删除草稿正文
				blogDraftContentMapper.delete(draftCode);
				
				// 删除草稿附件
//				blogDraftAttachMapper.delete(draftCode);
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 更新博客信息
	 */
	@Override
	public boolean updateBlog(BlogVO blogVO) throws BusinessException {
		try {
			// 参数校验
			if (blogVO == null) {
				log.error("BlogServiceImpl.updateBlog：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			String blogCode = blogVO.getBlogCode(); // 博客编号
			String userCode = blogVO.getUserCode(); // 人员编号
			Long blogPlanPublishTime = blogVO.getBlogPlanPublishTime(); // 定时发布时间
			Long modifyTime = blogVO.getModifyTime(); // 修改时间
			String teamCode = blogVO.getTeamCode(); // 班组编号
			Byte blogDepend = blogVO.getBlogDepend(); // 从属关系
			
			// 参数校验
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogServiceImpl.updateBlog：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.updateBlog：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (modifyTime == null || modifyTime == 0) {
				log.error("BlogServiceImpl.updateBlog：modifyTime为空");
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			if (blogDepend == null || (!BlogConstants.BLOG_DEPEND_USER.equals(blogDepend)
					&& !BlogConstants.BLOG_DEPEND_TEAM.equals(blogDepend))) {
				log.error("BlogServiceImpl.updateBlog：博客从属关系错误");
				throw BusinessException.build("COMMON_402", "blogDepend");
			} else if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogServiceImpl.updateBlog：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 查询博客是否为定时发布博客
			Long planPublishTime = blogInfoMapper.getPlanPublishTime(blogCode);
			// 如果为定时发布，则判断定时发布时间
			if (planPublishTime != null && planPublishTime != 0) {
				// 如果未设置定时发布时间，则不修改
				if (blogPlanPublishTime == 0) {
					blogPlanPublishTime = planPublishTime;
				} else if (planPublishTime < modifyTime && blogPlanPublishTime != 0) {
					// 判断该博客是否已经发布
					log.error("BlogServiceImpl.updateBlog：已发布博客不能修改定时发布时间");
					throw BusinessException.build("BLOG_13006");
				} else if (blogPlanPublishTime != 0 && blogPlanPublishTime < modifyTime) {
					// 定时发布时间不能小于当前时间
					log.error("BlogServiceImpl.updateBlog：定时发布时间不能小于当前时间");
					throw BusinessException.build("BLOG_13007");
				}
			} else {
				// 如果不是定时发布博客，则不能修改定时发布时间
				if (blogPlanPublishTime != 0) {
					log.error("BlogServiceImpl.updateBlog：该博客不是定时发布博客");
					throw BusinessException.build("BLOG_13008");
				}
			}
			
			// 修改博客信息
			Map<String, Object> blogMap = new HashMap<String, Object>();
			blogMap.put("blogCode", blogCode); // 博客编号
			blogMap.put("userCode", userCode); // 人员编号
			blogMap.put("teamCode", teamCode); // 班组编号
			blogMap.put("blogDepend", blogDepend); // 从属关系
			blogMap.put("classCode", blogVO.getClassCode()); // 分类编号
			blogMap.put("blogTags", blogVO.getBlogTags()); // 标签
			blogMap.put("blogTitle", blogVO.getBlogTitle()); // 标题
			blogMap.put("blogLogo", blogVO.getBlogLogo()); // 图片链接
			blogMap.put("blogDigest", blogVO.getBlogDigest()); // 摘要
			blogMap.put("blogPlanPublishTime", blogPlanPublishTime); // 定时发布时间
			blogMap.put("modifyTime", modifyTime); // 修改时间
			int updateBlogNum = blogInfoMapper.update(blogMap);
			if (updateBlogNum != 1) {
				log.error("BlogServiceImpl.updateBlog：修改" + updateBlogNum + "条博客信息 ");
				return false;
			}
			
			// 修改博客正文信息
			Map<String, Object> contentMap = new HashMap<String, Object>();
			contentMap.put("blogCode", blogCode); // 博客编号
			contentMap.put("contentInfo", blogVO.getContentInfo()); // 正文信息
			contentMap.put("modifyTime", modifyTime); // 修改时间
			int updateContentNum = blogContentMapper.update(contentMap);
			if (updateContentNum != 1) {
				log.error("BlogServiceImpl.updateBlog：修改" + updateContentNum + "条博客正文信息 ");
				throw BusinessException.build("BLOG_13002", "保存");
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 保存转发信息
	 */
	@Override
	public boolean saveForwardBlog(BlogInfo blogInfo) throws BusinessException {
		try {
			// 参数校验
			if (blogInfo == null) {
				log.error("BlogServiceImpl.saveForwardBlog：blogInfo为空");
				throw BusinessException.build("COMMON_402", "blogInfo");
			}
			
			// 获取参数
			String sourceBlogCode = blogInfo.getSourceBlogCode(); // 原博客编号
			String tenantCode = blogInfo.getTenantCode(); // 租户编号
			String orgCode = blogInfo.getOrgCode(); // 组织编号
			Long createTime = blogInfo.getCreateTime(); // 创建时间
			Long modifyTime = blogInfo.getModifyTime(); // 修改时间
			String remark = blogInfo.getRemark(); // 备注
			String blogCode = blogInfo.getBlogCode(); // 博客编号
			
			// 参数校验
			if (StringUtils.isBlank(sourceBlogCode)) {
				log.error("BlogServiceImpl.saveForwardBlog：sourceBlogCode为空");
				throw BusinessException.build("COMMON_402", "sourceBlogCode");
			}
			
			// 插入博客信息
			int insertBlogNum = blogInfoMapper.insert(blogInfo);
			if (insertBlogNum != 1) {
				log.error("BlogServiceImpl.saveForwardBlog：插入" + insertBlogNum + "条博客信息 ");
				return false;
			}
			
			// 插入博客正文信息
			Map<String, Object> contentMap = new HashMap<String, Object>();
			contentMap.put("contentCode", UUIDHelper.getUUID()); // 正文编号
			contentMap.put("tenantCode", tenantCode); // 租户编号
			contentMap.put("orgCode", orgCode); // 组织编号
			contentMap.put("createTime", createTime); // 创建时间
			contentMap.put("modifyTime", modifyTime); // 修改时间
			contentMap.put("remark", remark); // 备注
			contentMap.put("blogCode", blogCode); // 博客编号
			contentMap.put("sourceBlogCode", sourceBlogCode); // 原博客编号
			int insertContentNum = blogContentMapper.copyInsert(contentMap);
			if (insertContentNum != 1) {
				log.error("BlogServiceImpl.saveForwardBlog：插入" + insertContentNum + "条博客正文信息 ");
				throw BusinessException.build("BLOG_13002", "保存");
			}
			/*
			// 查询原博客附件列表
			Map<String, Object> attachMap = new HashMap<String, Object>();
			contentMap.put("blogCode", sourceBlogCode); // 博客编号
			List<BlogAttach> attachList = blogAttachMapper.listAll(attachMap);
			if (attachList != null && !attachList.isEmpty()) {
				// 新博客附件列表
				List<BlogAttach> blogAttachList = new ArrayList<BlogAttach>();
				for (BlogAttach attach : attachList) {
					attach.setAttachCode(UUIDHelper.getUUID());
					attach.setTenantCode(tenantCode);
					attach.setOrgCode(orgCode);
					attach.setCreateTime(createTime);
					attach.setModifyTime(modifyTime);
					attach.setRemark(remark);
					attach.setBlogCode(blogCode);
					attach.setAttachDownNum(0);
					blogAttachList.add(attach);
				}
				
				// 插入博客附件信息
				int insertAttachNum = blogAttachMapper.batchInsert(blogAttachList);
				if (insertAttachNum != blogAttachList.size()) {
					log.error("BlogServiceImpl.saveForwardBlog：插入" + insertAttachNum + "条博客附件信息，应插入"
							+ blogAttachList.size() + "条");
					throw BusinessException.build("BLOG_13002", "保存");
				}
			}*/
			
			// 插入博客数据信息
			BlogData blogData = new BlogData();
			blogData.setDataCode(UUIDHelper.getUUID());
			blogData.setTenantCode(tenantCode);
			blogData.setOrgCode(orgCode);
			blogData.setCreateTime(createTime);
			blogData.setModifyTime(modifyTime);
			blogData.setBlogCode(blogCode);
			blogData.setTeamQualityNum(0);
			blogData.setGroupQualityNum(0);
			blogData.setProvinceQualityNum(0);
			blogData.setCityQualityNum(0);
			blogData.setReadNum(0);
			blogData.setLikeNum(0);
			blogData.setCollectNum(0);
			blogData.setForwardNum(0);
			blogData.setCommentNum(0);
			blogData.setShareNum(0);
			int insertDataNum = blogDataMapper.insert(blogData);
			if (insertBlogNum != 1) {
				log.error("BlogServiceImpl.saveForwardBlog：插入" + insertDataNum + "条博客数据信息 ");
				throw BusinessException.build("BLOG_13002", "保存");
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 修改博客分类
	 */
	@Override
	public boolean updateBlogClass(BlogVO blogVO) throws BusinessException {
		try {
			// 参数校验
			if (blogVO == null) {
				log.error("BlogServiceImpl.updateBlogClass：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			String blogCode = blogVO.getBlogCode(); // 博客编号
			String classCode = blogVO.getClassCode(); // 分类编号
			String userCode = blogVO.getUserCode(); // 人员编号
			String teamCode = blogVO.getTeamCode(); // 班组编号
			Byte blogDepend = blogVO.getBlogDepend(); // 从属关系
			
			// 参数校验
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogServiceImpl.updateBlogClass：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if (StringUtils.isBlank(classCode)) {
				log.error("BlogServiceImpl.updateBlogClass：classCode为空");
				throw BusinessException.build("COMMON_402", "classCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.updateBlogClass：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (blogDepend == null || (!BlogConstants.BLOG_DEPEND_USER.equals(blogDepend)
					&& !BlogConstants.BLOG_DEPEND_TEAM.equals(blogDepend))) {
				log.error("BlogServiceImpl.updateBlogClass：博客从属关系错误");
				throw BusinessException.build("COMMON_402", "blogDepend");
			} else if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogServiceImpl.updateBlogClass：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("blogCode", blogCode); // 博客编号
			paramMap.put("classCode", classCode); // 分类编号
			paramMap.put("blogDepend", blogDepend); // 从属关系
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("teamCode", teamCode); // 班组编号
			paramMap.put("modifyTime", blogVO.getModifyTime()); // 修改时间
			
			// 修改
			int updateNum = blogInfoMapper.updateClass(paramMap);
			if (updateNum != 1) {
				log.error("BlogServiceImpl.updateBlogClass：修改" + updateNum + "条博客信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 立即发布定时发布博客
	 */
	@Override
	public boolean updateBlogPublishTime(BlogVO blogVO) throws BusinessException {
		try {
			// 参数校验
			if (blogVO == null) {
				log.error("BlogServiceImpl.updateBlogPublishTime：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			String blogCode = blogVO.getBlogCode(); // 博客编号
			Byte blogDepend = blogVO.getBlogDepend(); // 从属关系
			String userCode = blogVO.getUserCode(); // 人员编号
			String teamCode = blogVO.getTeamCode(); // 班组编号
			Long modifyTime = blogVO.getModifyTime(); // 修改时间
			
			// 参数校验
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogServiceImpl.updateBlogPublishTime：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogServiceImpl.updateBlogPublishTime：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (modifyTime == null || modifyTime == 0) {
				log.error("BlogServiceImpl.updateBlogPublishTime：modifyTime为空");
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			if (blogDepend == null || (!BlogConstants.BLOG_DEPEND_USER.equals(blogDepend)
					&& !BlogConstants.BLOG_DEPEND_TEAM.equals(blogDepend))) {
				log.error("BlogServiceImpl.updateBlogPublishTime：博客从属关系错误");
				throw BusinessException.build("COMMON_402", "blogDepend");
			} else if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogServiceImpl.updateBlogPublishTime：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 查询博客是否为定时发布博客
			Long planPublishTime = blogInfoMapper.getPlanPublishTime(blogCode);
			if (planPublishTime == null || planPublishTime == 0) {
				log.error("BlogServiceImpl.updateBlogPublishTime：该博客不是定时发布博客");
				throw BusinessException.build("BLOG_13008");
			}
			
			// 判断该博客是否已经发布
			if (planPublishTime < modifyTime) {
				log.error("BlogServiceImpl.updateBlogPublishTime：该博客已经发布");
				throw BusinessException.build("BLOG_13009");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("blogCode", blogCode); // 博客编号
			paramMap.put("blogDepend", blogDepend); // 从属关系
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("teamCode", teamCode); // 班组编号
			paramMap.put("blogPlanPublishTime", blogVO.getModifyTime()); // 定时发布时间
			paramMap.put("modifyTime", blogVO.getModifyTime()); // 修改时间
			paramMap.put("blogPublishTime", blogVO.getModifyTime()); // 发布时间
			
			// 修改
			int updateNum = blogInfoMapper.updatePublishTime(paramMap);
			if (updateNum != 1) {
				log.error("BlogServiceImpl.updateBlogPublishTime：修改" + updateNum + "条博客信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 后台查询博客列表
	 */
	@Override
	public PageBean getBlogListByGL(PageParam pageParam, BlogVO blogVO) throws BusinessException {
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null) {
				log.error("BlogServiceImpl.getBlogListByGL：pageParam为空");
				throw BusinessException.build("COMMON_402", "pageParam");
			}
			if (blogVO == null) {
				log.error("BlogServiceImpl.getBlogListByGL：blogVO为空");
				throw BusinessException.build("COMMON_402", "blogVO");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			Long queryTime = blogVO.getQueryTime(); // 首次查询时间
			Byte blogIsShield = blogVO.getBlogIsShield();//是否屏蔽：0.否；1.是；
			String orgCode = blogVO.getOrgCode();//当前用户所在的组织机构
			// 参数校验
			if (pageSize == null) {
				log.error("BlogServiceImpl.getBlogListByGL：pageSize为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if (pageNo == null) {
				log.error("BlogServiceImpl.getBlogListByGL：pageNo为空");
				throw BusinessException.build("COMMON_402", "pageNo");
			}
			if (queryTime == null || queryTime == 0) {
				log.error("BlogServiceImpl.getBlogListByGL：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if (orgCode == null) {
				log.error("BlogServiceImpl.getBlogListByGL：orgCode为空");
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			if (blogIsShield == null||(blogIsShield!=BlogConstants.BLOG_NO&&blogIsShield!=BlogConstants.BLOG_YES)) {
				log.error("BlogServiceImpl.getBlogListByGL：blogIsShield参数错误："+blogIsShield);
				throw BusinessException.build("COMMON_402", "blogIsShield");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("queryTime", queryTime); // 首次查询时间
			paramMap.put("blogIsShield", blogIsShield); // 是否屏蔽
			paramMap.put("orgCode", orgCode);
			// 查询列表总数
			int count = blogInfoMapper.selectCountByGL(paramMap);
			// 如果分类编号不为空，则修改分类下博客数量
			
			// 查询列表内容
			List<BlogInfo> blogList = blogInfoMapper.listByGL(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, blogList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}
	
	
	
	
	
	/**
	 * 保存博客信息H5
	 */
	@Override
	public boolean saveBlogForH5(BlogInfo blogInfo, BlogContent blogContent)
			throws BusinessException {
		try {
			// 参数校验
			if (blogInfo == null) {
				log.error("BlogServiceImpl.saveBlog：blogInfo为空");
				throw BusinessException.build("COMMON_402", "blogInfo");
			}
			if (blogContent == null) {
				log.error("BlogServiceImpl.saveBlog：blogContent为空");
				throw BusinessException.build("COMMON_402", "blogContent");
			}
			
			// 获取参数
//			Long createTime = blogInfo.getCreateTime(); // 创建时间
			
			
			// 插入博客信息
			int insertBlogNum = blogInfoMapper.insert(blogInfo);
			if (insertBlogNum != 1) {
				log.error("BlogServiceImpl.saveBlog：插入" + insertBlogNum + "条博客信息 ");
				return false;
			}
			
			// 插入博客正文信息
			int insertContentNum = blogContentMapper.insert(blogContent);
			if (insertContentNum != 1) {
				log.error("BlogServiceImpl.saveBlog：插入" + insertContentNum + "条博客正文信息 ");
				throw BusinessException.build("BLOG_13002", "保存");
			}
			
			// 插入博客数据信息
			BlogData blogData = new BlogData();
			blogData.setDataCode(UUIDHelper.getUUID());
			blogData.setTenantCode(blogInfo.getTenantCode());
			blogData.setOrgCode(blogInfo.getOrgCode());
			blogData.setCreateTime(blogInfo.getCreateTime());
			blogData.setModifyTime(blogInfo.getModifyTime());
			blogData.setBlogCode(blogInfo.getBlogCode());
			blogData.setTeamQualityNum(0);
			blogData.setGroupQualityNum(0);
			blogData.setProvinceQualityNum(0);
			blogData.setCityQualityNum(0);
			blogData.setReadNum(0);
			blogData.setLikeNum(0);
			blogData.setCollectNum(0);
			blogData.setForwardNum(0);
			blogData.setCommentNum(0);
			blogData.setShareNum(0);
			int insertDataNum = blogDataMapper.insert(blogData);
			if (insertBlogNum != 1) {
				log.error("BlogServiceImpl.saveBlog：插入" + insertDataNum + "条博客数据信息 ");
				throw BusinessException.build("BLOG_13002", "保存");
			}
			
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

}
