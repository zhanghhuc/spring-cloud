package com.zssq.qo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
    * @ClassName: PublishWeiboQO  
    * @Description: 发表微博信息  
    * @author Mr.B  
    * @date 2017年3月23日  
    *
 */
public class PublishWeiboQO implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 微博CODE **/
	private String mblogCode = "";
	/** 发布人 **/
	private String userCode = "";
	/** 组别CODE **/
	private String teamCode = "";
	/** 组织CODE **/
	private String orgCode = "";
	/** 是否班组 **/
	private Byte isTeam = 0;
	/** 内容 **/
	private String content = "";
	/** 标题 **/
	private String title = "";
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
    /** 动态CODE **/
	private String dynamicCode = "";
	/** 时间戳 **/
	private Long timeSign;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getTimeSign() {
		return timeSign;
	}
	public void setTimeSign(Long timeSign) {
		this.timeSign = timeSign;
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
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public List<String> getImgs() {
		return imgs;
	}
	public void setImgs(List<String> imgs) {
		if(null != imgs && !imgs.isEmpty()){
			this.imgs = imgs;
		}
	}
	public List<String> getAudios() {
		return audios;
	}
	public void setAudios(List<String> audios) {
		if(null != audios && !audios.isEmpty()){
			this.audios = audios;
		}
	}
	public List<String> getVideos() {
		return videos;
	}
	public void setVideos(List<String> videos) {
		if(null != videos && !videos.isEmpty()){
			this.videos = videos;
		}
	}
	public List<String> getTopicCodes() {
		return topicCodes;
	}
	public void setTopicCodes(List<String> topicCodes) {
		if(null != topicCodes && !topicCodes.isEmpty()){
			this.topicCodes = topicCodes;
		}
	}
	public List<UserInfoQO> getAtUserCodes() {
		return atUserCodes;
	}
	public void setAtUserCodes(List<UserInfoQO> atUserCodes) {
		if(null != atUserCodes && !atUserCodes.isEmpty()){
			this.atUserCodes = atUserCodes;
		}
	}
	public String getTenatCode() {
		return tenatCode;
	}
	public void setTenatCode(String tenatCode) {
		this.tenatCode = tenatCode;
	}
	
	

}
