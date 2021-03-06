package com.zssq.dao.pojo;

import java.util.List;

import com.zssq.pojo.BasePage;

/**
 * 用户信息实体，使用该对象在缓存中维护用户基本信息及其它附属信息
 * 
 * @since JDK 1.7
 * @author 赵翊
 */
public class SysUserInfo extends BasePage {

	private static final long serialVersionUID = 7261976065597520257L;

	private Long id;

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
	
	/** 角色名称,普通员工或领导 */
	private String roleName;
	
	/** 可操作的接口资源列表 */
	private List<String> permissions;

	/** 租户编号 */
	private String tenantCode;
	
	/** 所属组织信息 */
	private SysOrgInfo orgInfo;
	
	/** 所属行政组织编号 */
	private String manOrgCode;
	
	/** 行政组织信息 */
	private SysOrgInfo manageOrgInfo;
	
	/** 修改时间 */
	private Long modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
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

	public SysOrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(SysOrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public SysOrgInfo getManageOrgInfo() {
		return manageOrgInfo;
	}

	public void setManageOrgInfo(SysOrgInfo manageOrgInfo) {
		this.manageOrgInfo = manageOrgInfo;
	}

	public String getManOrgCode() {
		return manOrgCode;
	}

	public void setManOrgCode(String manOrgCode) {
		this.manOrgCode = manOrgCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

}