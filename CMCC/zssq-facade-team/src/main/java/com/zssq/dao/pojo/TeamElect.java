package com.zssq.dao.pojo;

import java.util.List;

import com.zssq.pojo.BasePage;

public class TeamElect extends BasePage {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	/** 唯一标识 */
    private String teamElectCode;

    /** 评选标题 */
    private String electTitle;

    /** 评选内容 */
    private String electDetail;

    /** 开始时间 */
    private Long electStartTime;

    /** 结束时间 */
    private Long electEndTime;

    /** 是否是最新评选 */
    private Byte isLatest;

    /** 组织机构编码 */
    private String orgCode;

    /** 创建人 */
    private String userCode;
    
    /** 开始日期 */
//    private Long startDate;
    
    /** 结束日期 */
//    private Long endDate;
    
    /** 用户集合 */
    private List<String> userCodes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamElectCode() {
        return teamElectCode;
    }

    public void setTeamElectCode(String teamElectCode) {
        this.teamElectCode = teamElectCode == null ? null : teamElectCode.trim();
    }

    public String getElectTitle() {
        return electTitle;
    }

    public void setElectTitle(String electTitle) {
        this.electTitle = electTitle == null ? null : electTitle.trim();
    }

    public String getElectDetail() {
        return electDetail;
    }

    public void setElectDetail(String electDetail) {
        this.electDetail = electDetail == null ? null : electDetail.trim();
    }

    public Long getElectStartTime() {
		return electStartTime;
	}

	public void setElectStartTime(Long electStartTime) {
		this.electStartTime = electStartTime;
	}

	public Long getElectEndTime() {
		return electEndTime;
	}

	public void setElectEndTime(Long electEndTime) {
		this.electEndTime = electEndTime;
	}

	public Byte getIsLatest() {
        return isLatest;
    }

    public void setIsLatest(Byte isLatest) {
        this.isLatest = isLatest;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

//	public Long getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(Long startDate) {
//		this.startDate = startDate;
//	}
//
//	public Long getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(Long endDate) {
//		this.endDate = endDate;
//	}

	public List<String> getUserCodes() {
		return userCodes;
	}

	public void setUserCodes(List<String> userCodes) {
		this.userCodes = userCodes;
	}
    
}