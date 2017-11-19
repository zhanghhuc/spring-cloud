package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.SensitiveWordInfo;

public interface SensitiveWordMapper {
	
	int insertSelective(SensitiveWordInfo sensitiveWordInfo);
	
	int deleteBySensitiveCode(String sensitiveCode);
	
	List<SensitiveWordInfo> selectPage(SensitiveWordInfo record);
	
	int selectPageCount(SensitiveWordInfo record);
	
	int batchInsert(List<SensitiveWordInfo> sensitiveWordInfos);
	
}
