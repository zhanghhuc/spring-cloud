package com.zssq.auth.pojo;

public class SysUserInfo {

	private Long id;
	
	private Integer userid;

	/** 编号 */
	private String userCode;
	
	/** 姓名 */
	private String userName;
	
	/** 性别 */
	private Byte userSex;

	/** 电话 */
	private String userOfficePhone;
	
	/** 所属组织编号 */
	private String orgCode;
	
	/** 状态 */
	private Byte userStatus;
	
	/** 昵称 */
	private String nickName;
	
	/** 头像 */
	private String headPortrait;
	
	/** 个人简介 */
	private String introduce;
	
	/** 角色标识，用于标识普通员工或领导 */
	private String roleCode;

	/** 租户编号 */
	private String tenantCode;
	
	/** 所属行政组织编号 */
	private String manOrgCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode == null ? null : userCode.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public Byte getUserSex() {
		return userSex;
	}

	public void setUserSex(Byte userSex) {
		this.userSex = userSex;
	}

	public String getUserOfficePhone() {
		return userOfficePhone;
	}

	public void setUserOfficePhone(String userOfficePhone) {
		this.userOfficePhone = userOfficePhone == null ? null : userOfficePhone.trim();
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode == null ? null : orgCode.trim();
	}

	public Byte getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Byte userStatus) {
		this.userStatus = userStatus;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getManOrgCode() {
		return manOrgCode;
	}

	public void setManOrgCode(String manOrgCode) {
		this.manOrgCode = manOrgCode;
	}	
}
