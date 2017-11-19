package com.zssq.team.vo;

import java.util.Date;

public class KcTeamUser {
    private Integer id;
    
    private String memberCode;

    private String teamCode;

    private String userCode;

    private Date joinDate;

    private Integer teamPosition;

    public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getTeamPosition() {
        return teamPosition;
    }

    public void setTeamPosition(Integer teamPosition) {
        this.teamPosition = teamPosition;
    }
}