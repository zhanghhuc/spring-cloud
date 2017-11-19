package com.zssq.dao.pojo;

import java.util.List;

import com.zssq.pojo.BasePage;

public class TeamCourse extends BasePage {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	/** 唯一标识 */
    private String teamCourseCode;

    /** 班组评选编码 */
    private String teamElectCode;

    /** 班组编码 */
    private String teamCode;

    /** 推荐原因 */
    private String currentRecommendReason;

    /** 当前推荐组织机构编码 */
    private String currentOrgCode;
    
    /** 当前推荐组织机构类型 */
    private String currentOrgType;
    
    /** 上级组织机构编码 */
    private String superOrgCode;
    
    /** 班组所属组织机构编码 */
    private String teamOrgCode;
    
    /** 是否推荐到上级 */
    private Byte isRecommend;

    /** 推荐人编码 */
    private String userCode;

    /** 创建时间 */
    private Long createTime;
    
    /** 班组名称 */
    private String teamName;
    
    /** 班组类型 */
    private Byte teamType;
    
    /** 开始时间 */
    private Long startDate;
    
    /** 结束时间 */
    private Long endDate;
    
    /** 班组长集合 */
    private List<String> leaders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamCourseCode() {
        return teamCourseCode;
    }

    public void setTeamCourseCode(String teamCourseCode) {
        this.teamCourseCode = teamCourseCode == null ? null : teamCourseCode.trim();
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

    public String getCurrentRecommendReason() {
        return currentRecommendReason;
    }

    public void setCurrentRecommendReason(String currentRecommendReason) {
        this.currentRecommendReason = currentRecommendReason == null ? null : currentRecommendReason.trim();
    }

    public String getCurrentOrgCode() {
        return currentOrgCode;
    }

    public void setCurrentOrgCode(String currentOrgCode) {
        this.currentOrgCode = currentOrgCode == null ? null : currentOrgCode.trim();
    }

    public Byte getIsRecommend() {
        return isRecommend;
    }

    public String getCurrentOrgType() {
		return currentOrgType;
	}

	public void setCurrentOrgType(String currentOrgType) {
		this.currentOrgType = currentOrgType;
	}

	public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Byte getTeamType() {
		return teamType;
	}

	public void setTeamType(Byte teamType) {
		this.teamType = teamType;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public List<String> getLeaders() {
		return leaders;
	}

	public void setLeaders(List<String> leaders) {
		this.leaders = leaders;
	}

	public String getSuperOrgCode() {
		return superOrgCode;
	}

	public void setSuperOrgCode(String superOrgCode) {
		this.superOrgCode = superOrgCode;
	}

	public String getTeamOrgCode() {
		return teamOrgCode;
	}

	public void setTeamOrgCode(String teamOrgCode) {
		this.teamOrgCode = teamOrgCode;
	}

}