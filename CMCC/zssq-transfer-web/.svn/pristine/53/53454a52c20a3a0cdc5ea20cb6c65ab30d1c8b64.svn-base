package com.zssq.team.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.team.dao.mapper.TeamMemberMapper;
import com.zssq.team.pojo.TeamMember;

@Service("teamMemberSerivce")
public class TeamMemberSerivceImpl implements ITeamMemberService {

	@Autowired
	private TeamMemberMapper teamMemberMapper;
	
	@Override
	public void teamMemberInsert() {
		
		teamMemberMapper.delete();
		
		List<TeamMember> teamMemberList = teamMemberMapper.selectData();
		
		List<TeamMember> insertList = new ArrayList<TeamMember>();
		for (int i = 0; i < teamMemberList.size(); i++) {
			teamMemberList.get(i).setTeamMemberCode(UUIDHelper.getUUID());
			insertList.add(teamMemberList.get(i));
			if((i+1)%400==0){
				teamMemberMapper.batchInsert(insertList);
				insertList.clear();
			}
		}
		if(insertList.size()>0)
			teamMemberMapper.batchInsert(insertList);
	}

}
