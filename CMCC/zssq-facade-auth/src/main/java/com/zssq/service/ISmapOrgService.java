package com.zssq.service;

import java.util.List;

import com.mdcl.smap.bean.OrgInfo;
import com.zssq.exceptions.BusinessException;

public interface ISmapOrgService {

	/**
	 * 根据parentOrgCode查询组织信息；传入 00000000000000000000 时，表示查询树形结构的顶级节点
	 * 
	 * @param parentOrgCode
	 * 			上级组织编码
	 * @return 下级组织信息集合
	 */	
	List<OrgInfo> selectByParentOrgCode(String parentOrgCode) throws BusinessException;
}
