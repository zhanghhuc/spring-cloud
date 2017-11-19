package com.zssq.news.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsInfoContent;
import com.zssq.news.model.NewsModel;
import com.zssq.news.model.NewsQuery;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: NewsInfoContentService  
 * @Description: 新闻管理service  
 * @author ZKZ  
 * @date 2017年7月26日  
 *
 */
public interface NewsInfoContentService {
	
	/**
	 * 新闻列表接口
	 * @param newsModel
	 * @return
	 * @throws BusinessException
	 */
	public PageBean getNewsInfos(NewsQuery newsModel) throws BusinessException;

	/**
	 * 新闻新增接口
	 * @param newsModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean addNewsInfo(NewsModel newsModel) throws BusinessException ;

	/**
	 * 新闻修改接口
	 * @param newsModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateNewsInfo(NewsModel newsModel) throws BusinessException ;

	/**
	 * 新闻删除接口
	 * @param newsModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean deleteNewsInfo(NewsModel newsModel) throws BusinessException ;

	/**
	 * 新闻置顶
	 * @param newsModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateNewsInfosUp(NewsModel newsModel) throws BusinessException ;
	
	/**
	 * 新闻审核接口
	 * @param newsModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean addNewsInfoHistory(NewsModel newsModel) throws BusinessException ;
	
	/**
	 * 新闻上移或者下移接口
	 * @param newsModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateNewsInfoSort(NewsModel newsModel) throws BusinessException ;
	
	/**
	 * 新闻隐藏或者取消隐藏
	 * @param newsModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateNewsInfosHidden(NewsModel newsModel);

	/**
	 * 新闻列表接口--PC端
	 * @param  newsModel
	 * @return
	 * @throws BusinessException
	 */
	public PageBean getNewsInfosPc( NewsQuery newsModel) throws BusinessException;


	/**
	 * 新闻详情接口--PC端
	 * @param  newsModel
	 * @return
	 * @throws BusinessException
	 */
	public NewsInfoContent getNewsInfoDetail(NewsQuery newsModel) throws BusinessException;

	/**
	 * 新闻详情接口--PC端
	 * @param  newsModel
	 * @return
	 * @throws BusinessException
	 */
	public NewsInfoContent getNewsInfoDetailPC( NewsQuery newsModel) throws BusinessException;

	/**
	 * 
	 * @Title: getUserCollectNewsPc  
	 * @Description: 查询收藏新闻列表
	 * @param newsQuery
	 * @return: PageBean    返回类型
	 */
	public PageBean getUserCollectNewsPc(NewsQuery newsQuery);
}
