package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.SysOrgLevel;
import com.zssq.exceptions.BusinessException;

public interface ISysOrgLevelService {

    /**
     * 根据组织级别Code查询详细信息
     * 
     * @param levelCode
     * 			组织级别Code
     * @return 组织级别详细信息
     */
    SysOrgLevel selectByCode(String levelCode) throws BusinessException;

    /**
     * 根据租户Code查询组织级别集合
     * 
     * @param saasTenantCode
     * 			租户Code
     * @return 组织级别集合
     */
    public List<SysOrgLevel> selectByTenantCode(String saasTenantCode) throws BusinessException;
    
	/**
	 * 新增组织级别
	 * 
	 * @param record
	 * 			组织级别详细信息
	 * @return 成功时返回新增记录数，失败时返回0
	 */
    int insert(SysOrgLevel sysOrgLevel) throws BusinessException;

}
