package com.zssq.team.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.team.dao.mapper.TeamCourseMapper;
import com.zssq.team.dao.mapper.TeamElectMapper;
import com.zssq.team.dao.mapper.TeamInfoMapper;
import com.zssq.team.dao.mapper.TeamRecommendMapper;
import com.zssq.team.pojo.TeamCourse;
import com.zssq.team.pojo.TeamElect;
import com.zssq.team.pojo.TeamRecommend;

@Service("honorTeamService")
public class HonorTeamServiceImpl implements IHonorTeamService {

	@Autowired
	private TeamCourseMapper teamCourseMapper;
	
	@Autowired
	private TeamElectMapper teamElectMapper;
	
	@Autowired
	private TeamRecommendMapper teamRecommendMapper;
	
	@Autowired
	private TeamInfoMapper teamInfoMapper;
	
	@Override
	public void honorTeamInsert() {
		
		String electCode = "20170101";
		long electTime = new Date().getTime()/1000*1000;
		
		List<String> orgList = teamInfoMapper.selectAOrg();
		String orgCode = "";
		if(orgList.size() > 0){
			orgCode = orgList.get(0);
		}
		
		teamCourseMapper.deleteByElectCode(electCode);
		teamElectMapper.deleteByCode(electCode);
		teamRecommendMapper.deleteByElectCode(electCode);
		
		List<String> honorTeamCodes = teamInfoMapper.selectHonotTeam();
		
		TeamElect teamElect = new TeamElect();
		teamElect.setTeamElectCode(electCode);
		teamElect.setElectDetail("");
		teamElect.setElectStartTime(electTime);
		teamElect.setElectEndTime(electTime);
		teamElect.setElectTitle("");
		teamElect.setIsLatest(Byte.valueOf("1"));
		teamElect.setUserCode("");//开始评选人
		teamElect.setOrgCode(orgCode);
		teamElectMapper.insertSelective(teamElect);
		
		List<TeamCourse> teamCourseList = new ArrayList<TeamCourse>();
		List<TeamRecommend> teamRecommendList = new ArrayList<TeamRecommend>();
		for (int i = 0; i < honorTeamCodes.size(); i++) {
			TeamCourse teamCourse = new TeamCourse();
			teamCourse.setTeamCourseCode(UUIDHelper.getUUID());
			teamCourse.setTeamElectCode(electCode);
			teamCourse.setTeamCode(honorTeamCodes.get(i));
			teamCourse.setCurrentRecommendReason("");
			teamCourse.setCurrentOrgCode(orgCode);
			teamCourse.setCurrentOrgType("A");
			teamCourse.setSuperOrgCode("");
			teamCourse.setIsRecommend(Byte.valueOf("1"));
			teamCourse.setUserCode("");//推荐人
			teamCourse.setCreateTime(electTime);
			teamCourseList.add(teamCourse);
			
			TeamRecommend teamRecommend = new TeamRecommend();
			teamRecommend.setTeamRecommendCode(UUIDHelper.getUUID());
			teamRecommend.setTeamElectCode(electCode);
			teamRecommend.setTeamCode(honorTeamCodes.get(i));
			teamRecommend.setRecommendReason("");
			teamRecommend.setIsExcellent(Byte.valueOf("1"));
			teamRecommend.setUserCode("");
			teamRecommend.setElectTime(electTime);
			teamRecommend.setIsHonor(Byte.valueOf("1"));
			teamRecommend.setOrgCode(orgCode);
			teamRecommendList.add(teamRecommend);
		}
		
		teamCourseMapper.batchInsert(teamCourseList);
		
		teamRecommendMapper.batchInsert(teamRecommendList);
		
		
	}

}
