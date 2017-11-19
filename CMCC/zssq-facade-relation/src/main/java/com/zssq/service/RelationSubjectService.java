package com.zssq.service;

import com.zssq.dao.pojo.RelationSubjectInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.vo.RelationSubjectVO;

/**
 * 
 * @ClassName: RelationSubjectService  
 * @Description: 内容信息
 * @author ZKZ  
 * @date 2017年4月22日  
 *
 */
public interface RelationSubjectService {

	/**
	 * 
	 * @Title: getSubjectBaseInfo  
	 * @Description: 获取内容基本信息
	 * @param relationSubjectVO
	 * @return: RelationSubjectInfo    返回类型
	 * @throws BusinessException 
	 */
	public RelationSubjectInfo getSubjectBaseInfo(RelationSubjectVO relationSubjectVO) throws BusinessException;
	
}
