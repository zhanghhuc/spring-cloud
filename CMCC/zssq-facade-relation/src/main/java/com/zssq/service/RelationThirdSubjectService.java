package com.zssq.service;

import com.zssq.dao.pojo.RelationQuality;
import com.zssq.dao.pojo.RelationSubjectInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.vo.RelationSubjectVO;

/**
 * 
 * @ClassName: RelationSubjectInfoService  
 * @Description: 内容信息  
 * @author ZKZ  
 * @date 2017年3月18日  
 *
 */
public interface RelationThirdSubjectService {
	
	/**
	 * 
	 * @Title: saveSubject  
	 * @Description: 保存内容信息
	 * @param relationSubjectInfo 内容信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveSubject(RelationSubjectInfo relationSubjectInfo) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateSubject
	 * @Description: 更新内容信息，可更新字段：标题、摘要、图片链接、状态
	 * @param relationSubjectVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateSubject(RelationSubjectVO relationSubjectVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteSubject  
	 * @Description: 删除内容
	 * @param relationSubjectVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteSubject(RelationSubjectVO relationSubjectVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateNewsDeleteStatus  
	 * @Description: 修改新闻删除状态
	 * @param relationSubjectVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateNewsDeleteStatus(RelationSubjectVO relationSubjectVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateShieldStatus  
	 * @Description: 屏蔽/恢复内容
	 * @param relationSubjectVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateShieldStatus(RelationSubjectVO relationSubjectVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getQualityStatus  
	 * @Description: 查询是否置精
	 * @param relationQuality
	 * @return    参数  
	 * @return: boolean    返回类型
	 * @throws BusinessException 
	 */
	boolean getQualityStatus(RelationQuality relationQuality) throws BusinessException;
}
