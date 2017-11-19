package com.zssq.auth.vo;

public class MapOrgRelationSearchVo {

	/** 门户类型 */
//	@NotBlank(message = "{empty.message}")
	private String portalType;
	
	/** 地图插件：省名称 */
	private String provinceName;

	public String getPortalType() {
		return portalType;
	}

	public void setPortalType(String portalType) {
		this.portalType = portalType;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
