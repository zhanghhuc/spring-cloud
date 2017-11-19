package com.zssq.relation.pojo;

public class TeamMember {
    private Long id;

    private String teamMemberCode;

    private String teamCode;

    private String userCode;

    private Byte isLeader;

    private Long createTime;

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(Integer teamMemberId) {
        this.teamMemberId = teamMemberId;
    }
}