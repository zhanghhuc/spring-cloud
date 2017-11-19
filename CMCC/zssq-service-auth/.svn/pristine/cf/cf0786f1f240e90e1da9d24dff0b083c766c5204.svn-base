package com.zssq.dao.mapper;

import java.util.List;

import com.mdcl.smap.bean.OrgInfo;

public interface SmapOrgMapper {

	/**
	 * 根据orgCode查询组织信息
	 * 
	 * @param orgCode
	 * 			组织编码
	 * @return 组织详细信息
	 */
	OrgInfo selectByOrgCode(String orgCode);
	
	/**
	 * 根据parentOrgCode查询组织信息；传入 00000000000000000000 时，表示查询树形结构的顶级节点
	 * 
	 * @param parentOrgCode
	 * 			上级组织编码
	 * @return 下级组织信息集合
	 */	
	List<OrgInfo> selectByParentOrgCode(String parentOrgCode);
	
	/**
	 * 新增组织信息
	 * 
	 * @param orgInfo
	 * 			组织信息实体
	 * @return 成功时返回新增条数，失败时返回0
	 */
	int insert(OrgInfo orgInfo);
	
	/**
	 * 新增 SMAP 同步组织信息，保存至静态表，该表记录同步到的每一条组织信息，不做任何处理
	 * 
	 * @param orgInfo
	 * 			组织信息实体
	 * @return 成功时返回新增条数，失败时返回0
	 */	
	int insertStatic(OrgInfo orgInfo);
	
	/**
	 * 更新组织信息
	 * 
	 * @param orgInfo
	 * 			组织信息实体
	 * @return 成功时返回更新条数，失败时返回0
	 */	
	int updateByOrgCode(OrgInfo orgInfo);
	
	
	
	
	
	
	
	
	/* ========================= 以下代码为测试代码 ========================= */
	
	List<OrgInfo> selectStaticAll();
}
