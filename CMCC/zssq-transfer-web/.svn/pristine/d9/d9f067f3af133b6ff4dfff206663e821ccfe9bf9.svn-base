package com.zssq.auth.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.auth.pojo.SysOrgInfo;

public interface SysOrgInfoMapper {

	public Integer selectSysOrgCount();

	public List<SysOrgInfo> selectSysOrgBatch(Map<String, Integer> param);
	
	public SysOrgInfo selectByOrgCode(String orgCode);
	
	public List<SysOrgInfo> selectBySrcCode(String srcCode);
	
	public void insert(List<SysOrgInfo> sysOrgs);
	
	public int updateParentCodeAndLevel(SysOrgInfo sysOrgInfo);
	
	public int updateProvinceParentCode(String parentCode);
	
	public int updateManOrgCode(SysOrgInfo sysOrgInfo);
}
