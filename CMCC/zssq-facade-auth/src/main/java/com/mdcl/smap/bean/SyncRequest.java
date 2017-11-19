package com.mdcl.smap.bean;


public class SyncRequest {
	
	String appid = "";
	String password = "";
	String usersucceed = "";
	String userfailed = "";
	String orgsucceed = "";
	String orgfailed = "";
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsersucceed() {
		return usersucceed;
	}
	public void setUsersucceed(String usersucceed) {
		this.usersucceed = usersucceed;
	}
	public String getUserfailed() {
		return userfailed;
	}
	public void setUserfailed(String userfailed) {
		this.userfailed = userfailed;
	}
	public String getOrgsucceed() {
		return orgsucceed;
	}
	public void setOrgsucceed(String orgsucceed) {
		this.orgsucceed = orgsucceed;
	}
	public String getOrgfailed() {
		return orgfailed;
	}
	public void setOrgfailed(String orgfailed) {
		this.orgfailed = orgfailed;
	}

}
