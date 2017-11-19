package com.zssq.team.pojo;

public class TeamRecommend {

	private Long id;

	/** 唯一标识 */
    private String teamRecommendCode;

    /** 评选编码 */
    private String teamElectCode;

    /** 班组编码 */
    private String teamCode;

    /** 推荐原因 */
    private String recommendReason;

    /** 是否百强 */
    private Byte isExcellent;

    /** 百强班组确认人 */
    private String userCode;

    /** 评选时间 */
    private Long electTime;

    /** 是否颁发荣誉 */
    private Byte isHonor;

    /** 组织CODE */
    private String orgCode;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamRecommendCode() {
        return teamRecommendCode;
    }

    public void setTeamRecommendCode(String teamRecommendCode) {
        this.teamRecommendCode = teamRecommendCode == null ? null : teamRecommendCode.trim();
    }

    public String getTeamElectCode() {
        return teamElectCode;
    }

    public void setTeamElectCode(String teamElectCode) {
        this.teamElectCode = teamElectCode == null ? null : teamElectCode.trim();
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    public String getRecommendReason() {
        return recommendReason;
    }

    public void setRecommendReason(String recommendReason) {
        this.recommendReason = recommendReason == null ? null : recommendReason.trim();
    }

    public Byte getIsExcellent() {
        return isExcellent;
    }

    public void setIsExcellent(Byte isExcellent) {
        this.isExcellent = isExcellent;
    }

    public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getElectTime() {
        return electTime;
    }

    public void setElectTime(Long electTime) {
        this.electTime = electTime;
    }

    public Byte getIsHonor() {
        return isHonor;
    }

    public void setIsHonor(Byte isHonor) {
        this.isHonor = isHonor;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

}