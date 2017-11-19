package com.zssq.dao.mapper;

import com.zssq.dao.pojo.SysAdminInfo;

public interface SaasTenantMapper {

	/**
	 * 根据管理员帐号获取详细信息
	 * 
	 * @param sysAdminInfo
	 * 			管理员信息实体
	 * @return 管理员详细信息
	 */
	public SysAdminInfo selectByAccount(SysAdminInfo sysAdminInfo);
	
	/**
	 * @Function insertSelective
	 * @Description 插入
	 * @param record
	 * @return
	 */
	public int insertSelective(SysAdminInfo record);
	
	
	/**
	 * @Function updatePassword
	 * @Description 根据条件更新
	 * @param record
	 * @return
	 */
	public int updatePassword(SysAdminInfo record);
	
	/**
	 * @Function setAdminState
	 * @Description 超管启禁用
	 * @param record
	 * @return
	 */
	public int setAdminState(SysAdminInfo record);
}
