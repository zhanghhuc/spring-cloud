package com.zssq.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: RelationDynamicModel
 * @Description: 动态详情参数
 * @author ZKZ
 * @date 2017年3月17日
 *
 */
public class RelationDynamicModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dynamicCode; // 动态编号
	private Byte dynamicClass; // 动态类型
	private Byte dynamicDepend; // 动态从属关系
	private String teamCode; // 动态所属班组编号
	private String userCode; // 动态所属人员编号
	private String operateTeamCode; // 操作班组编号
	private String operateOrgCode; // 操作组织编号
	private Long operateTime; // 操作时间
	private String subjectCode; // 内容编号
	private String subjectName; // 内容名称（荣誉）
	private String subjectUrlHonor; // 内容链接（荣誉）
	private Byte isSubjectShow; // 是否显示内容信息
	private Byte subjectClass; // 内容类型
	private Byte subjectDepend; // 内容从属关系
	private String subjectTeamCode; // 内容所属班组编号
	private String subjectUserCode; // 内容所属人员编号
	private Long subjectPublishTime; // 内容发布时间
	private String diskOrgCode; // 网盘所属组织
	private String subjectTitle; // 内容标题
	private String subjectDigest; // 内容摘要
	private String subjectUrl; // 内容链接
	private Byte subjectSource; // 内容来源
	private String sourceCode; // 内容来源编号
	private Byte sourceDepend; // 内容来源从属关系
	private String sourceTeamCode; // 内容来源所属班组编号
	private String sourceUserCode; // 内容来源人员编号
	private Long sourcePublishTime; // 内容来源发布时间
	private Byte sourceIsDelete; // 内容来源是否删除
	private Byte sourceIsShield; // 内容来源是否屏蔽
	private String contentTips; // 转发心得
	private Byte subjectIsDelete; // 内容是否删除
	private Byte subjectIsShield; // 内容是否屏蔽
	private Byte subjectStatus; // 内容状态
	private Long subjectBeginTime; // 内容开始时间
	private Long subjectEndTime; // 内容结束时间
	private Integer teamQualityNum; // 班组置精次数
	private Integer groupQualityNum; // 集团置精次数
	private Integer provinceQualityNum; // 省置精次数
	private Integer cityQualityNum; // 市置精次数
	private Integer joinNum; // 参与量
	private Integer readNum; // 浏览量
	private Integer likeNum; // 点赞量
	private Integer collectNum; // 收藏量
	private Integer forwardNum; // 转发量
	private Integer commentNum; // 评论量
	private Integer shareNum; // 分享量
	private Integer relIsHomeShow; // 首页展示标识
	private Integer relIsQuality; // 精华标识
	private Integer relIsRecommend; // 推荐标识
	private Integer isLike; // 是否点赞
	private Integer isCollect; // 是否收藏
	private Integer teamFrontShow; // 是否被首页展示
	private Integer teamQuality; // 是否被置精
	private List<RelationSubjectResourceModel> resourceList; // 资源列表

	
	public String getDynamicCode() {
		return dynamicCode;
	}

	public void setDynamicCode(String dynamicCode) {
		this.dynamicCode = dynamicCode;
	}

	public Byte getDynamicClass() {
		return dynamicClass;
	}

	public void setDynamicClass(Byte dynamicClass) {
		this.dynamicClass = dynamicClass;
	}

	public Byte getDynamicDepend() {
		return dynamicDepend;
	}

	public void setDynamicDepend(Byte dynamicDepend) {
		this.dynamicDepend = dynamicDepend;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getOperateTeamCode() {
		return operateTeamCode;
	}

	public void setOperateTeamCode(String operateTeamCode) {
		this.operateTeamCode = operateTeamCode;
	}

	public String getOperateOrgCode() {
		return operateOrgCode;
	}

	public void setOperateOrgCode(String operateOrgCode) {
		this.operateOrgCode = operateOrgCode;
	}

	public Long getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectUrlHonor() {
		return subjectUrlHonor;
	}

	public void setSubjectUrlHonor(String subjectUrlHonor) {
		this.subjectUrlHonor = subjectUrlHonor;
	}

	public Byte getIsSubjectShow() {
		return isSubjectShow;
	}

	public void setIsSubjectShow(Byte isSubjectShow) {
		this.isSubjectShow = isSubjectShow;
	}

	public Byte getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(Byte subjectClass) {
		this.subjectClass = subjectClass;
	}

	public Byte getSubjectDepend() {
		return subjectDepend;
	}

	public void setSubjectDepend(Byte subjectDepend) {
		this.subjectDepend = subjectDepend;
	}

	public String getSubjectTeamCode() {
		return subjectTeamCode;
	}

	public void setSubjectTeamCode(String subjectTeamCode) {
		this.subjectTeamCode = subjectTeamCode;
	}

	public String getSubjectUserCode() {
		return subjectUserCode;
	}

	public void setSubjectUserCode(String subjectUserCode) {
		this.subjectUserCode = subjectUserCode;
	}

	public Long getSubjectPublishTime() {
		return subjectPublishTime;
	}

	public void setSubjectPublishTime(Long subjectPublishTime) {
		this.subjectPublishTime = subjectPublishTime;
	}

	public String getDiskOrgCode() {
		return diskOrgCode;
	}

	public void setDiskOrgCode(String diskOrgCode) {
		this.diskOrgCode = diskOrgCode;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public String getSubjectDigest() {
		return subjectDigest;
	}

	public void setSubjectDigest(String subjectDigest) {
		this.subjectDigest = subjectDigest;
	}

	public String getSubjectUrl() {
		return subjectUrl;
	}

	public void setSubjectUrl(String subjectUrl) {
		this.subjectUrl = subjectUrl;
	}

	public Byte getSubjectSource() {
		return subjectSource;
	}

	public void setSubjectSource(Byte subjectSource) {
		this.subjectSource = subjectSource;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public Byte getSourceDepend() {
		return sourceDepend;
	}

	public void setSourceDepend(Byte sourceDepend) {
		this.sourceDepend = sourceDepend;
	}

	public String getSourceTeamCode() {
		return sourceTeamCode;
	}

	public void setSourceTeamCode(String sourceTeamCode) {
		this.sourceTeamCode = sourceTeamCode;
	}

	public String getSourceUserCode() {
		return sourceUserCode;
	}

	public void setSourceUserCode(String sourceUserCode) {
		this.sourceUserCode = sourceUserCode;
	}

	public Long getSourcePublishTime() {
		return sourcePublishTime;
	}

	public void setSourcePublishTime(Long sourcePublishTime) {
		this.sourcePublishTime = sourcePublishTime;
	}

	public Byte getSourceIsDelete() {
		return sourceIsDelete;
	}

	public void setSourceIsDelete(Byte sourceIsDelete) {
		this.sourceIsDelete = sourceIsDelete;
	}

	public Byte getSourceIsShield() {
		return sourceIsShield;
	}

	public void setSourceIsShield(Byte sourceIsShield) {
		this.sourceIsShield = sourceIsShield;
	}

	public String getContentTips() {
		return contentTips;
	}

	public void setContentTips(String contentTips) {
		this.contentTips = contentTips;
	}

	public Byte getSubjectIsDelete() {
		return subjectIsDelete;
	}

	public void setSubjectIsDelete(Byte subjectIsDelete) {
		this.subjectIsDelete = subjectIsDelete;
	}

	public Byte getSubjectIsShield() {
		return subjectIsShield;
	}

	public void setSubjectIsShield(Byte subjectIsShield) {
		this.subjectIsShield = subjectIsShield;
	}

	public Byte getSubjectStatus() {
		return subjectStatus;
	}

	public void setSubjectStatus(Byte subjectStatus) {
		this.subjectStatus = subjectStatus;
	}

	public Long getSubjectBeginTime() {
		return subjectBeginTime;
	}

	public void setSubjectBeginTime(Long subjectBeginTime) {
		this.subjectBeginTime = subjectBeginTime;
	}

	public Long getSubjectEndTime() {
		return subjectEndTime;
	}

	public void setSubjectEndTime(Long subjectEndTime) {
		this.subjectEndTime = subjectEndTime;
	}

	public Integer getTeamQualityNum() {
		return teamQualityNum;
	}

	public void setTeamQualityNum(Integer teamQualityNum) {
		this.teamQualityNum = teamQualityNum;
	}

	public Integer getGroupQualityNum() {
		return groupQualityNum;
	}

	public void setGroupQualityNum(Integer groupQualityNum) {
		this.groupQualityNum = groupQualityNum;
	}

	public Integer getProvinceQualityNum() {
		return provinceQualityNum;
	}

	public void setProvinceQualityNum(Integer provinceQualityNum) {
		this.provinceQualityNum = provinceQualityNum;
	}

	public Integer getCityQualityNum() {
		return cityQualityNum;
	}

	public void setCityQualityNum(Integer cityQualityNum) {
		this.cityQualityNum = cityQualityNum;
	}

	public Integer getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Integer getForwardNum() {
		return forwardNum;
	}

	public void setForwardNum(Integer forwardNum) {
		this.forwardNum = forwardNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}

	public Integer getRelIsHomeShow() {
		return relIsHomeShow;
	}

	public void setRelIsHomeShow(Integer relIsHomeShow) {
		this.relIsHomeShow = relIsHomeShow;
	}

	public Integer getRelIsQuality() {
		return relIsQuality;
	}

	public void setRelIsQuality(Integer relIsQuality) {
		this.relIsQuality = relIsQuality;
	}

	public Integer getRelIsRecommend() {
		return relIsRecommend;
	}

	public void setRelIsRecommend(Integer relIsRecommend) {
		this.relIsRecommend = relIsRecommend;
	}

	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}

	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public Integer getTeamFrontShow() {
		return teamFrontShow;
	}

	public void setTeamFrontShow(Integer teamFrontShow) {
		this.teamFrontShow = teamFrontShow;
	}

	public Integer getTeamQuality() {
		return teamQuality;
	}

	public void setTeamQuality(Integer teamQuality) {
		this.teamQuality = teamQuality;
	}

	public List<RelationSubjectResourceModel> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<RelationSubjectResourceModel> resourceList) {
		this.resourceList = resourceList;
	}

}
