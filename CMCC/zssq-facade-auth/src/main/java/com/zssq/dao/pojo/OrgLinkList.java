package com.zssq.dao.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 组织机构关联关系实体
 * 
 * @since JDK 1.7
 * @author 赵翊
 */
public class OrgLinkList implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 集团公司信息 */
	private SysOrgInfo topOrg;

	/** 省级公司信息 */
	private List<SysOrgInfo> proList;

	/** 市级公司信息 */
	private List<SysOrgInfo> cityList;

	/** 部门信息 */
	private List<SysOrgInfo> deptList;

	public SysOrgInfo getTopOrg() {
		return topOrg;
	}

	public void setTopOrg(SysOrgInfo topOrg) {
		this.topOrg = topOrg;
	}

	public List<SysOrgInfo> getProList() {
		return proList;
	}

	public void setProList(List<SysOrgInfo> proList) {
		this.proList = proList;
	}

	public List<SysOrgInfo> getCityList() {
		return cityList;
	}

	public void setCityList(List<SysOrgInfo> cityList) {
		this.cityList = cityList;
	}

	public List<SysOrgInfo> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<SysOrgInfo> deptList) {
		this.deptList = deptList;
	}

}
