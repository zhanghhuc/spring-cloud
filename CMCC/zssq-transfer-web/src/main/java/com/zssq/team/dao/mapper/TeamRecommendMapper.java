package com.zssq.team.dao.mapper;

import java.util.List;

import com.zssq.team.pojo.TeamRecommend;

public interface TeamRecommendMapper {
	
	int batchInsert(List<TeamRecommend> record);
	
	int deleteByElectCode(String code);
}