package com.zssq.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.RelationSubjectInfoByZKZMapper;
import com.zssq.dao.pojo.RelationSubjectInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.RelationSubjectService;
import com.zssq.vo.RelationSubjectVO;

/**
 * 
 * @ClassName: RelationSubjectServiceImpl  
 * @Description: 内容信息  
 * @author ZKZ  
 * @date 2017年4月22日  
 *
 */
@Service("relationSubjectService")
public class RelationSubjectServiceImpl implements RelationSubjectService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RelationSubjectInfoByZKZMapper relationSubjectInfoByZKZMapper;

	/**
	 * 获取内容基本信息
	 */
	@Override
	public RelationSubjectInfo getSubjectBaseInfo(RelationSubjectVO relationSubjectVO) throws BusinessException {
		// 返回值
		RelationSubjectInfo relationSubjectInfo = null;
		
		try {
			// 参数校验
			if (relationSubjectVO == null) {
				log.error("RelationSubjectServiceImpl.getSubjectBaseInfo：relationSubjectVO为空");
				throw BusinessException.build("COMMON_402", "relationSubjectVO");
			}
			
			// 获取参数
			String subjectCode = relationSubjectVO.getSubjectCode(); // 内容编号
			
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationSubjectServiceImpl.getSubjectBaseInfo：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			
			// 查询
			relationSubjectInfo = relationSubjectInfoByZKZMapper.getSubjectBaseInfo(subjectCode);
		} catch (BusinessException e) {
			throw e;
		}
		
		return relationSubjectInfo;
	}

}
