package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.SysOrgLevel;

public interface SysOrgLevelMapper {

	int deleteByPrimaryKey(Long id);
	
	/**
	 * 新增组织级别
	 * 
	 * @param sysOrgLevel
	 * 			组织级别详细信息
	 * @return 成功时返回新增记录数，失败时返回0
	 */
    int insert(SysOrgLevel sysOrgLevel);

    /**
     * 根据组织级别Code查询详细信息
     * 
     * @param orgTypeCode
     * 			组织级别Code
     * @return 组织级别详细信息
     */
    SysOrgLevel selectByCode(String levelCode);
    
    /**
     * 根据租户Code查询组织级别集合
     * 
     * @param saasTenantCode
     * 			租户Code
     * @return 组织级别集合
     */
    List<SysOrgLevel> selectByTenantCode(String saasTenantCode);

    int updateByPrimaryKey(SysOrgLevel record);

}
