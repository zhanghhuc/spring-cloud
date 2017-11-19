package com.zssq.dao.pojo;

import java.util.List;

import com.zssq.pojo.BasePage;

public class TeamInfo extends BasePage {

	private static final long serialVersionUID = 1L;

	private Long id;

	/** 班组code */
    private String teamCode;
    
    /** 班组名称 */
    private String teamName;

    /** 班组简介 */
    private String teamIntro;

    /** 班组头像 */
    private String teamIcon;

    /** 班组类型 1 1号 0普通 */
    private Byte teamType;

    /** 创建时间 */
    private Long createTime;

    /** 解散时间 */
    private Long dissolveTime;

    /** 是否解散 */
    private Byte isDissolve;

    /** 是否归档 */
    private Byte isRecord;

    /** 归档地址 */
    private String recordFileUrl;

    /** 归档人 */
    private String userCode;

    /** 班组所属org */
    private String orgCode;
    
    /** 组织机构集合 */
    private List<String> orgCodes;
    
    /** 组内人数(默认为0) */
    private Integer sumCount = 0;
    
    /** 组长们,分隔 */
    private String leaderCodes;
    
    /** 组长列表 */
    private List<String> leaders;
    
    /** 当前登录人是否是组长 */
    private String isLeader;
    
    /** 开始日期 */
    private Long startDate;
    
    /** 结束日期 */
    private Long endDate;
    
    /** 租户code */
    private String tenantCode;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

	public List<String> getLeaders() {
		return leaders;
	}

	public void setLeaders(List<String> leaders) {
		this.leaders = leaders;
	}

	public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getTeamIntro() {
        return teamIntro;
    }

    public void setTeamIntro(String teamIntro) {
        this.teamIntro = teamIntro == null ? null : teamIntro.trim();
    }

    public String getTeamIcon() {
        return teamIcon;
    }

    public void setTeamIcon(String teamIcon) {
        this.teamIcon = teamIcon == null ? null : teamIcon.trim();
    }

    public Byte getTeamType() {
        return teamType;
    }

    public void setTeamType(Byte teamType) {
        this.teamType = teamType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getDissolveTime() {
        return dissolveTime;
    }

    public void setDissolveTime(Long dissolveTime) {
        this.dissolveTime = dissolveTime;
    }

    public Byte getIsDissolve() {
        return isDissolve;
    }

    public void setIsDissolve(Byte isDissolve) {
        this.isDissolve = isDissolve;
    }

    public Byte getIsRecord() {
        return isRecord;
    }

    public void setIsRecord(Byte isRecord) {
        this.isRecord = isRecord;
    }

    public String getRecordFileUrl() {
        return recordFileUrl;
    }

    public void setRecordFileUrl(String recordFileUrl) {
        this.recordFileUrl = recordFileUrl == null ? null : recordFileUrl.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

	public Integer getSumCount() {
		return sumCount;
	}

	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}

	public String getLeaderCodes() {
		return leaderCodes;
	}

	public void setLeaderCodes(String leaderCodes) {
		this.leaderCodes = leaderCodes;
	}

	public String getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
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

	public List<String> getOrgCodes() {
		return orgCodes;
	}

	public void setOrgCodes(List<String> orgCodes) {
		this.orgCodes = orgCodes;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

}