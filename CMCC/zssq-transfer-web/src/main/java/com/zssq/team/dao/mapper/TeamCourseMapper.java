package com.zssq.team.dao.mapper;

import java.util.List;

import com.zssq.team.pojo.TeamCourse;

public interface TeamCourseMapper {
	
	int batchInsert(List<TeamCourse> record);
	
	int deleteByElectCode(String code);
}