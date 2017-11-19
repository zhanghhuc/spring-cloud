package com.zssq.qo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
    * @ClassName: MblogInfoAddQO  
    * @Description:  微博信息添加查询类
    * @author Mr.B  
    * @date 2017年3月15日  
    *
 */
public class MblogInfoAddQO implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 微博CODE **/
	private String mblogCode = "";
	/** 发布人 **/
	private String userCode = "";
	/** 代发人组织CODE **/
	private String agentOrgCode = "";
	/** 代发人CODE **/
	private String agentUserCode = "";
	/** 组别CODE **/
	private String teamCode = "";
	/** 组织CODE **/
	private String orgCode = "";
	/** 是否班组 **/
	private Byte isTeam = 0;
	/** 内容 **/
	private String content = "";
	/** 摘要 **/
	private String summary = "";
	/** 图片资源 **/
	private List<String> imgs = new ArrayList<String>();
	/** 音频资源 **/
	private List<String> audios = new ArrayList<String>();
	/** 视频资源 **/
	private List<String> videos = new ArrayList<String>();
	/** 话题资源 **/
	private List<String> topicCodes = new ArrayList<String>();
	/** at用户列表 **/
	private List<UserInfoQO> atUserCodes = new ArrayList<UserInfoQO>();
	/** 租户 **/
	private String tenatCode = "";
	/** 微博类型 **/
	private Byte mblogSource = 1;
	/** 被转发微博来源CODE **/
	private String sourceMblogCode = "";
	/** 被转发微博来源用户CODE **/
	private String sourceUserCode = "";
    /** 动态CODE **/
	private String dynamicCode = "";
	/** 被转发微博CODE **/
	private String forMblogCode = "";  
	/** 被转发微博发布人CODE **/
	private String forUserCode = ""; 
	/** 时间戳 **/
	private Long timeSign;
	
	
	public Long getTimeSign() {
		return timeSign;
	}
	public void setTimeSign(Long timeSign) {
		this.timeSign = timeSign;
	}
	public String getForMblogCode() {
		return forMblogCode;
	}
	public void setForMblogCode(String forMblogCode) {
		this.forMblogCode = forMblogCode;
	}
	public String getForUserCode() {
		return forUserCode;
	}
	public void setForUserCode(String forUserCode) {
		this.forUserCode = forUserCode;
	}
	public String getDynamicCode() {
		return dynamicCode;
	}
	public void setDynamicCode(String dynamicCode) {
		this.dynamicCode = dynamicCode;
	}
	public String getMblogCode() {
		return mblogCode;
	}
	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}
	public String getSourceMblogCode() {
		return sourceMblogCode;
	}
	public void setSourceMblogCode(String sourceMblogCode) {
		this.sourceMblogCode = sourceMblogCode;
	}
	public String getSourceUserCode() {
		return sourceUserCode;
	}
	public void setSourceUserCode(String sourceUserCode) {
		this.sourceUserCode = sourceUserCode;
	}
	public Byte getMblogSource() {
		return mblogSource;
	}
	public void setMblogSource(Byte mblogSource) {
		this.mblogSource = mblogSource;
	}
	public String getTenatCode() {
		return tenatCode;
	}
	public void setTenatCode(String tenatCode) {
		this.tenatCode = tenatCode;
	}
	public String getAgentOrgCode() {
		return agentOrgCode;
	}
	public void setAgentOrgCode(String agentOrgCode) {
		this.agentOrgCode = agentOrgCode;
	}
	public String getAgentUserCode() {
		return agentUserCode;
	}
	public void setAgentUserCode(String agentUserCode) {
		this.agentUserCode = agentUserCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public Byte getIsTeam() {
		return isTeam;
	}
	public void setIsTeam(Byte isTeam) {
		this.isTeam = isTeam;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getImgs() {
		return imgs;
	}
	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
	public List<String> getAudios() {
		return audios;
	}
	public void setAudios(List<String> audios) {
		this.audios = audios;
	}
	public List<String> getVideos() {
		return videos;
	}
	public void setVideos(List<String> videos) {
		this.videos = videos;
	}
	public List<String> getTopicCodes() {
		return topicCodes;
	}
	public void setTopicCodes(List<String> topicCodes) {
		this.topicCodes = topicCodes;
	}
	public List<UserInfoQO> getAtUserCodes() {
		return atUserCodes;
	}
	public void setAtUserCodes(List<UserInfoQO> atUserCodes) {
		this.atUserCodes = atUserCodes;
	}
	
	
}
