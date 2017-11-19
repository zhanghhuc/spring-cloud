package com.zssq.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.SysOrgLevelMapper;
import com.zssq.dao.pojo.SysOrgLevel;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.ISysOrgLevelService;

@Service("sysOrgLevelService")
public class SysOrgLevelServiceImpl implements ISysOrgLevelService {

	/** 日志记录操作 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/** 组织级别信息持久化接口 */
	@Autowired
	private SysOrgLevelMapper sysOrgLevelMapper;
	
	@Override
	public SysOrgLevel selectByCode(String levelCode) throws BusinessException {

		try {
			return sysOrgLevelMapper.selectByCode(levelCode);
		} catch (Exception e) {
			log.error("根据组织级别Code查询详细信息出错", e);
			throw new BusinessException("根据组织级别Code查询详细信息出错", e); 
		}		
	}

	@Override
	public List<SysOrgLevel> selectByTenantCode(String saasTenantCode) throws BusinessException {

		try {
			return sysOrgLevelMapper.selectByTenantCode(saasTenantCode);
		} catch (Exception e) {
			log.error("根据租户Code查询组织级别集合出错", e);
			throw new BusinessException("根据租户Code查询组织级别集合出错", e); 
		}		
	}

	@Override
	public int insert(SysOrgLevel sysOrgLevel) throws BusinessException {

		try {
			return sysOrgLevelMapper.insert(sysOrgLevel);
		} catch (Exception e) {
			log.error("新增组织级别出错", e);
			throw new BusinessException("新增组织级别出错", e); 
		}		
	}

}
