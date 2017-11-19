package com.zssq.team.pojo;

import java.util.List;

import com.zssq.pojo.BasePage;

public class TeamMember extends BasePage {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	/** 唯一标识 */
    private String teamMemberCode;
    
    /** 唯一标识集合 */
    private List<String> teamMemberCodes;

    /** 班组编码 */
    private String teamCode;

    /** 用户编码 */
    private String userCode;

    /** 留言唯一标识 */
    private Byte isLeader;

    /** 是否是班组长 */
    private String orgCode;

    /** 创建时间 */    
    private Long createTime;
    
    /** 班组成员ID */  
    private Integer teamMemberId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamMemberCode() {
        return teamMemberCode;
    }

    public void setTeamMemberCode(String teamMemberCode) {
        this.teamMemberCode = teamMemberCode == null ? null : teamMemberCode.trim();
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Byte getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Byte isLeader) {
        this.isLeader = isLeader;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public List<String> getTeamMemberCodes() {
		return teamMemberCodes;
	}

	public void setTeamMemberCodes(List<String> teamMemberCodes) {
		this.teamMemberCodes = teamMemberCodes;
	}

	public Integer getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(Integer teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

}