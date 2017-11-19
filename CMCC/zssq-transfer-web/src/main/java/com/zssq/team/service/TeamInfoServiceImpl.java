package com.zssq.team.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.team.dao.mapper.TeamInfoMapper;
import com.zssq.team.pojo.TeamInfo;

@Service("teamInfoservice")
public class TeamInfoServiceImpl implements ITeamInfoService {

	@Autowired
	private TeamInfoMapper teamInfoMapper;
	
	
	@Override
	public void teamInfoInsert() {
		
		teamInfoMapper.deleteTeamInfo();
		
		List<TeamInfo> teamList = teamInfoMapper.selectData();
		
		List<TeamInfo> insertList = new ArrayList<TeamInfo>();
		for (int i = 0; i < teamList.size(); i++) {
			teamList.get(i).setTenantCode("856966d06b96418fab2e430d71a1e84e");
			insertList.add(teamList.get(i));
			if((i+1)%200==0){
				teamInfoMapper.batchInsert(insertList);
				insertList.clear();
			}
		}
		if(insertList.size()>0){
			teamInfoMapper.batchInsert(insertList);
		}
		//变更头像(null)
		teamInfoMapper.updateTeamIcon();
	}

}
