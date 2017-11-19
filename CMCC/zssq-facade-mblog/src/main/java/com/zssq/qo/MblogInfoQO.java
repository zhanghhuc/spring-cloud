package com.zssq.qo;

import java.io.Serializable;
import java.util.List;

/**
 * 
    * @ClassName: MblogInfoQO  
    * @Description: 微博查询类  
    * @author Mr.B  
    * @date 2017年3月16日  
    *
 */
public class MblogInfoQO implements Serializable{

	private static final long serialVersionUID = 1L;
	  
	/** 我的用户CODE **/
	private String myUserCode = null;
	/** 用户CODE列表 **/
	private List<String> userCodes;
	/** 每页条数 **/
	private Integer pageSize = null;
	/** 页码 **/
	private Integer pageNo = null;
	/** 分割数据 **/
	private Long lineTime;
	/** 微博CODE **/
	private String mblogCode = null;
	/** 是否点赞 **/
	private Byte isPraise = 0;
	/** 收藏收藏 **/
	private Byte isCollect = 0;
	/** 是否订阅 **/
	private Byte isSubscribe = 0;
	/** 被订阅用户CODE **/
	private String subScribeUserCode;
	/** 被订阅的班组CODE **/
	private String subTeamCode = null;
	/** 订阅分类： 1：个人，2：班组 **/
	private Byte subType = 0;
	/** 微博列表 **/
	private List<String> mblogCodes;
	/** 微博类型 1:个人，2：班组**/
	private Byte depend = null;
	/** 其他人CODE **/
	private String otherUserCode = null;
	/** 班组CODE **/
	private String teamCode = null;
	/** 组织CODE **/
	private String orgCode = "";
	
	/** 组织CODE **/
	private String subOrgCode = "";
	
	/** 租户 **/
	private String tenantCode = "";
	
	/** 是否屏蔽 **/
	private Byte isShield = 0;
	
	private String leaderUserCode;
	
	
	public String getLeaderUserCode() {
		return leaderUserCode;
	}
	public void setLeaderUserCode(String leaderUserCode) {
		this.leaderUserCode = leaderUserCode;
	}
	public Byte getIsShield() {
		return isShield;
	}
	public void setIsShield(Byte isShield) {
		this.isShield = isShield;
	}
	public String getSubOrgCode() {
		return subOrgCode;
	}
	public void setSubOrgCode(String subOrgCode) {
		this.subOrgCode = subOrgCode;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getOtherUserCode() {
		return otherUserCode;
	}
	public void setOtherUserCode(String otherUserCode) {
		this.otherUserCode = otherUserCode;
	}
	public Byte getDepend() {
		return depend;
	}
	public void setDepend(Byte depend) {
		this.depend = depend;
	}
	public List<String> getMblogCodes() {
		return mblogCodes;
	}
	public void setMblogCodes(List<String> mblogCodes) {
		this.mblogCodes = mblogCodes;
	}
	public String getSubScribeUserCode() {
		return subScribeUserCode;
	}
	public void setSubScribeUserCode(String subScribeUserCode) {
		this.subScribeUserCode = subScribeUserCode;
	}
	public String getSubTeamCode() {
		return subTeamCode;
	}
	public void setSubTeamCode(String subTeamCode) {
		this.subTeamCode = subTeamCode;
	}
	public Byte getSubType() {
		return subType;
	}
	public void setSubType(Byte subType) {
		this.subType = subType;
	}
	public Byte getIsPraise() {
		return isPraise;
	}
	public void setIsPraise(Byte isPraise) {
		this.isPraise = isPraise;
	}
	public Byte getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Byte isCollect) {
		this.isCollect = isCollect;
	}
	public Byte getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(Byte isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	public String getMblogCode() {
		return mblogCode;
	}
	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}
	public Integer getLimitStart() {
		if(null != pageNo && null != pageSize && pageSize > 0){
			return pageNo*pageSize;
		}
		return null;
	}
	public Integer getLimitSize() {
		if(null != pageSize && pageSize > 0){
			return pageSize;
		}
		return null;
		
	}
	
	public Long getLineTime() {
		return lineTime;
	}
	public void setLineTime(Long lineTime) {
		this.lineTime = lineTime;
	}
	public String getMyUserCode() {
		return myUserCode;
	}
	public void setMyUserCode(String myUserCode) {
		this.myUserCode = myUserCode;
	}
	public List<String> getUserCodes() {
		return userCodes;
	}
	public void setUserCodes(List<String> userCodes) {
		this.userCodes = userCodes;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	
}
