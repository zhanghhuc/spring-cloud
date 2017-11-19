package com.zssq.auth.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.auth.pojo.KcOrg;

public interface KcOrgMapper {

	public Integer selectKcOrgCount();
	
	public List<KcOrg> selectKcOrgBatch(Map<String, Integer> param);
}
