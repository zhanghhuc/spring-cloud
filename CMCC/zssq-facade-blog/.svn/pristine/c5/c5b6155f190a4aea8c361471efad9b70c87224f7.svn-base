package com.zssq.service;

import com.zssq.dao.pojo.BlogCollect;
import com.zssq.dao.pojo.BlogForward;
import com.zssq.dao.pojo.BlogLike;
import com.zssq.dao.pojo.BlogRead;
import com.zssq.dao.pojo.BlogShare;
import com.zssq.exceptions.BusinessException;
import com.zssq.vo.BlogOperateVO;

/**
 * 
 * @ClassName: BlogOperateService  
 * @Description: 博客操作
 * @author ZKZ  
 * @date 2017年3月25日  
 *
 */
public interface BlogOperateService {
	
	/**
	 * 
	 * @Title: saveLike  
	 * @Description: 点赞
	 * @param blogLike 点赞信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveLike(BlogLike blogLike) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteLike  
	 * @Description: 取消点赞
	 * @param blogOperateVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteLike(BlogOperateVO blogOperateVO) throws BusinessException;

	/**
	 * 
	 * @Title: saveCollect  
	 * @Description: 收藏
	 * @param blogCollect 收藏信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveCollect(BlogCollect blogCollect) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteCollect  
	 * @Description: 取消收藏
	 * @param blogOperateVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteCollect(BlogOperateVO blogOperateVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveShare  
	 * @Description: 保存分享信息
	 * @param blogShare 分享信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveShare(BlogShare blogShare) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteShare  
	 * @Description: 删除分享信息
	 * @param blogOperateVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteShare(BlogOperateVO blogOperateVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveForward  
	 * @Description: 保存转发信息
	 * @param blogForward 转发信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveForward(BlogForward blogForward) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveRead  
	 * @Description: 保存浏览信息
	 * @param blogRead 浏览信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveRead(BlogRead blogRead) throws BusinessException;
	
}
