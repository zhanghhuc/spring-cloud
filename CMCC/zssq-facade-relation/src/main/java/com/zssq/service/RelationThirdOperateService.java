package com.zssq.service;

import com.zssq.dao.pojo.RelationCollect;
import com.zssq.dao.pojo.RelationLike;
import com.zssq.dao.pojo.RelationShare;
import com.zssq.exceptions.BusinessException;
import com.zssq.vo.RelationOperateVO;

/**
 * 
 * @ClassName: RelationThirdOperateService  
 * @Description: 操作  
 * @author ZKZ  
 * @date 2017年3月17日  
 *
 */
public interface RelationThirdOperateService {

	/**
	 * 
	 * @Title: saveLike  
	 * @Description: 点赞
	 * @param relationLike 点赞信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveLike(RelationLike relationLike) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteLike  
	 * @Description: 取消点赞
	 * @param relationOperateVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteLike(RelationOperateVO relationOperateVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveCollect  
	 * @Description: 收藏
	 * @param relationCollect 收藏信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveCollect(RelationCollect relationCollect) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteCollect  
	 * @Description: 取消收藏
	 * @param relationOperateVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteCollect(RelationOperateVO relationOperateVO) throws BusinessException;
	/**
	 * 
	 * @Title: saveShare  
	 * @Description: 分享
	 * @param relationShare
	 * @return
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveShare(RelationShare relationShare) throws BusinessException;
}
