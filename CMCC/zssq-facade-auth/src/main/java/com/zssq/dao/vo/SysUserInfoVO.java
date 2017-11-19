package com.zssq.dao.vo;

import java.io.Serializable;

import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;

/**
 * 员工信息封装，除员工自身信息外，封装其增值信息，如所属的三级组织信息
 * 
 * @since JDK 1.7
 * @author 赵翊
 */
public class SysUserInfoVO implements Serializable {

	private static final long serialVersionUID = -6159923594156955298L;

	/** 员工信息实体映射 */
	private SysUserInfo sysUserInfo;

	/** 员工所属组织机构 */
	private SysOrgInfo orgInfo;

	/** 员工所属市级组织机构 */
	private SysOrgInfo cityOrgInfo;

	/** 员工所属省级组织机构 */
	private SysOrgInfo provinceOrgInfo;

	public SysUserInfo getSysUserInfo() {
		return sysUserInfo;
	}

	public void setSysUserInfo(SysUserInfo sysUserInfo) {
		this.sysUserInfo = sysUserInfo;
	}

	public SysOrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(SysOrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public SysOrgInfo getCityOrgInfo() {
		return cityOrgInfo;
	}

	public void setCityOrgInfo(SysOrgInfo cityOrgInfo) {
		this.cityOrgInfo = cityOrgInfo;
	}

	public SysOrgInfo getProvinceOrgInfo() {
		return provinceOrgInfo;
	}

	public void setProvinceOrgInfo(SysOrgInfo provinceOrgInfo) {
		this.provinceOrgInfo = provinceOrgInfo;
	}
}
