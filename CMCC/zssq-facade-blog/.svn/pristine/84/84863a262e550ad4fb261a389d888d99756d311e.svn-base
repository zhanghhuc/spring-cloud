package com.zssq.service;

import java.util.List;

import com.zssq.dao.model.BlogModel;
import com.zssq.dao.pojo.BlogContent;
import com.zssq.dao.pojo.BlogInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.BlogVO;

/**
 * 
 * @ClassName: BlogService  
 * @Description: 博客  
 * @author ZKZ  
 * @date 2017年3月20日  
 *
 */
public interface BlogService {
	
	/**
	 * 
	 * @Title: getBlogList  
	 * @Description: 查询博客列表
	 * @param pageParam 分页参数
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getAgentBlogList  
	 * @Description: 查询代发博客列表
	 * @param pageParam 分页参数
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getAgentBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getQualityBlogList  
	 * @Description: 查询精品博客列表
	 * @param pageParam 分页参数
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getQualityBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getCollectBlogList  
	 * @Description: 查询收藏博客列表
	 * @param pageParam 分页参数
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getCollectBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getSubBlogList  
	 * @Description: 查询订阅博客列表
	 * @param pageParam 分页参数
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	 public PageBean getSubBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getPlanBlogList  
	 * @Description: 查询定时发布博客列表
	 * @param pageParam 分页参数
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getPlanBlogList(PageParam pageParam, BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getPlanBlogAllList  
	 * @Description: 查询定时发布博客列表
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: List<BlogInfo>    返回类型
	 */
	public List<BlogInfo> getPlanBlogAllList(BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getBlogMiniInfo  
	 * @Description: 获取博客基本信息
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: BlogInfo    返回类型
	 */
	public BlogInfo getBlogMiniInfo(BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getBlogBaseInfo  
	 * @Description: 查询博客内容
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: BlogModel    返回类型
	 */
	public BlogModel getBlogBaseInfo(BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getBlogInfo  
	 * @Description: 查询博客详情
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: BlogModel    返回类型
	 */
	public BlogModel getBlogInfo(BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteUserBlog  
	 * @Description: 删除个人博客
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteUserBlog(BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteBlogByAgent  
	 * @Description: 删除代发博客
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteBlogByAgent(BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteTeamBlog  
	 * @Description: 删除班组博客
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteTeamBlog(BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveBlog  
	 * @Description: 保存博客信息
	 * @param blogInfo 博客信息
	 * @param blogContent 博客正文信息
	 * @param draftCode 草稿编号
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveBlog(BlogInfo blogInfo, BlogContent blogContent, String draftCode) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateBlog  
	 * @Description: 更新博客信息
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateBlog(BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveForwardBlog  
	 * @Description: 保存转发信息
	 * @param blogInfo 博客信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveForwardBlog(BlogInfo blogInfo) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateBlogClass  
	 * @Description: 修改博客分类
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateBlogClass(BlogVO blogVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateBlogPublishTime  
	 * @Description: 立即发布定时发布博客
	 * @param blogVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateBlogPublishTime(BlogVO blogVO) throws BusinessException;
	/**
	 * 
	 * @Title: getBlogListByGL  
	 * @Description: 管理 获取博客列表
	 * @param pageParam
	 * @param blogVO
	 * @return    参数  
	 * @return: PageBean    返回类型
	 * @throws BusinessException 
	 */
	public PageBean getBlogListByGL(PageParam pageParam, BlogVO blogVO) throws BusinessException;

	boolean saveBlogForH5(BlogInfo blogInfo, BlogContent blogContent) throws BusinessException;

}
