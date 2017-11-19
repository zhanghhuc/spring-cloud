package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

public class HonorTeam extends BasePage {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	/** 班组编码 */
    private String teamCode;

    /** 班组名称 */
    private String teamName;

    /** 班组简介 */
    private String teamIntro;

    /** 班组头像 */
    private String teamIcon;

    /** 班组类型 0普通班组 1 1号班组 */
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

    /** 班组所属组织机构 */
    private String orgCode;

    /** 组内人数 */
    private Integer sumCount=0;
    
    /** 班组长编码集合,分隔 */
    private String leaderCodes;
    
    /** 推荐原因 */
    private String currentRecommendReason;
    
    /** 评选时间 */
    private Long electTime;
    
    /** 修改时间 */
    private Long modifyTime;
    
    /** 是否颁发荣誉 */
    private Byte isHonor;
    
    /** 租户标识 */
    private String tenantCode;
    
	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

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
		this.teamCode = teamCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamIntro() {
		return teamIntro;
	}

	public void setTeamIntro(String teamIntro) {
		this.teamIntro = teamIntro;
	}

	public String getTeamIcon() {
		return teamIcon;
	}

	public void setTeamIcon(String teamIcon) {
		this.teamIcon = teamIcon;
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
		this.recordFileUrl = recordFileUrl;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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

	public String getCurrentRecommendReason() {
		return currentRecommendReason;
	}

	public void setCurrentRecommendReason(String currentRecommendReason) {
		this.currentRecommendReason = currentRecommendReason;
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

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
    
}
