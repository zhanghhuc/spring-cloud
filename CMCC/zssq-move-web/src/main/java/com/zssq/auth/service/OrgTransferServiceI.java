package com.zssq.auth.service;

import java.util.List;
import java.util.Map;

public interface OrgTransferServiceI {
	
	/**
	 * 组织数据迁移：基础迁移
	 */
	public void executeTransferBase();
	
	/**
	 * 组织数据迁移：设置“上级组织编码”及“组织级别”
	 */
	public void processPraentCodeAndOrgType();
	
	/**
	 * 组织数据迁移：设置“行政组织编码”
	 */	
	public void processManOrgCode();
	
	/**
	 * 根据组织编码查询组织信息
	 * 
	 * @param sysOrgCode
	 * 			组织编码
	 * @return 组织详细信息，键值为字段名称
	 */
	public Map<String, Object> searchSysOrg(String sysOrgCode);
	
	/**
	 * 根据组织编码获取行政组织信息
	 * 
	 * @param sysOrgCode
	 * 			组织编码
	 * @return 行政组织信息
	 */
	public Map<String, Object> searchManSysOrg(String sysOrgCode);
	
	/**
	 * 以 orgId 为筛选条件，从 kc_org 表中获取单条记录
	 * 
	 * @param orgId
	 * 			源始组织ID，在表中唯一
	 * @return 源组织详细信息
	 */
	public Map<String, Object> searchKcOrgOne(int orgId);
	
	/**
	 * 统计 kc_org 表记录数
	 * 
	 * @return kc_org 表记录数
	 */
	public Integer searchKcOrgCount();	
	
	/**
	 * 从 kc_org 表中获取指定行号的记录
	 * 
	 * @param startRowNum
	 * 			开始行号
	 * @param endRowNum
	 * 			结束行号
	 * @return 源组织信息集合
	 */
	public List<Map<String, Object>> searchKcOrgPage(int startRowNum, int endRowNum);
}
