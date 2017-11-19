package com.zssq.service;

import java.util.List;

import com.zssq.dao.model.BlogModel;
import com.zssq.dao.model.TeamBlogModel;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.RssReturnPo;
import com.zssq.utils.PageParam;
import com.zssq.vo.BlogThirdVO;

/**
 * 
 * @ClassName: BlogThirdService  
 * @Description: 博客  
 * @author ZKZ  
 * @date 2017年3月20日  
 *
 */
public interface BlogThirdService {
	
	/**
	 * 
	 * @Title: updateShieldStatus  
	 * @Description: 屏蔽/恢复博客
	 * @param blogThirdVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateShieldStatus(BlogThirdVO blogThirdVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getRssBlogList  
	 * @Description: 获取订阅博客列表
	 * @param blogThirdVO
	 * @throws BusinessException    参数  
	 * @return: List<RssReturnPo>    返回类型
	 */
	public List<RssReturnPo> getRssBlogList(BlogThirdVO blogThirdVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getBlogTitalAndDateByCode  
	 * @Description: 根据博客编号列表获取博客基本信息和数据信息
	 * @param blogCodeList
	 * @throws BusinessException    参数  
	 * @return: List<BlogModel>    返回类型
	 */
	public List<BlogModel> getBlogTitleAndDataByCode(List<String> blogCodeList) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteSubscribe  
	 * @Description: 取消订阅
	 * @param blogThirdVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteSubscribe(BlogThirdVO blogThirdVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getTeamBlogList  
	 * @Description: 班组归档时获取博客列表
	 * @param pageParam
	 * @param blogThirdVO
	 * @throws BusinessException    参数  
	 * @return: List<TeamBlogModel>    返回类型
	 */
	public List<TeamBlogModel> getTeamBlogList(PageParam pageParam, BlogThirdVO blogThirdVO) throws BusinessException;

}
