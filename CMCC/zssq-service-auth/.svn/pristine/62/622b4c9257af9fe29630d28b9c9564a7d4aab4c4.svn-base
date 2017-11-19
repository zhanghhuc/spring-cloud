package com.zssq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdcl.smap.bean.OrgInfo;
import com.zssq.dao.mapper.SmapOrgMapper;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.ISmapOrgService;

@Service("smapOrgService")
public class SmapOrgServiceImpl implements ISmapOrgService {

	/** SMAP 组织信息同步持久化接口 */
	@Autowired
	private SmapOrgMapper smapOrgMapper;
	
	@Override
	public List<OrgInfo> selectByParentOrgCode(String parentOrgCode) throws BusinessException {
		
		try {
			return smapOrgMapper.selectByParentOrgCode(parentOrgCode);
		} catch (Exception e) {
			throw new BusinessException("根据parentOrgCode查询组织信息出错", e);
		}
	}

}
