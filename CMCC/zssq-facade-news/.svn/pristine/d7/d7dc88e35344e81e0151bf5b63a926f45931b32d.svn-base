package com.zssq.news.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsInfoContent;
import com.zssq.news.model.NewsArchiveModel;
import com.zssq.news.model.NewsArchiveQuery;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: NewsArchiveService  
 * @Description: 新闻归档操作service  
 * @author ZKZ  
 * @date 2017年7月24日  
 *
 */
public interface NewsArchiveService {
	
	/**
	 * 
	 * @Title: getNewsArchives  
	 * @Description: 查询已归档新闻列表
	 * @param newsArchiveQuery
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getNewsArchives(NewsArchiveQuery newsArchiveQuery) throws BusinessException;

	/**
	 * 
	 * @Title: addNewsArchives  
	 * @Description: 修改新闻状态
	 * @param newsArchiveModel
	 * @throws BusinessException    参数  
	 * @return: int    返回类型
	 */
	public int addNewsArchives(NewsArchiveModel newsArchiveModel) throws BusinessException ;

	/**
	 * 
	 * @Title: generateNewsData  
	 * @Description:获取新闻详情
	 * @param newsCode
	 * @return    参数  
	 * @return: NewsInfoContent    返回类型
	 */
	public NewsInfoContent generateNewsData(String newsCode);
}
