package com.zssq.relation.pojo;

/**
 * 
    * @ClassName: MblogResource  
    * @Description: 微博资源对象  
    * @author Mr.B  
    * @date 2017年3月15日  
    *
 */
public class MblogResource{

    private String mblogRecourceCode = "";

    private String orgCode = "";

    private String mblogCode = "";

    private Byte resType = 0;

    private String tenantCode = "";

    private String content = "";

    public String getMblogRecourceCode() {
        return mblogRecourceCode;
    }

    public void setMblogRecourceCode(String mblogRecourceCode) {
        this.mblogRecourceCode = mblogRecourceCode == null ? "" : mblogRecourceCode.trim();
    }


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? "" : orgCode.trim();
    }

    

    public String getMblogCode() {
        return mblogCode;
    }

    public void setMblogCode(String mblogCode) {
        this.mblogCode = mblogCode == null ? "" : mblogCode.trim();
    }

	public Byte getResType() {
		return resType;
	}

	public void setResType(Byte resType) {
		this.resType = resType;
	}

	public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? "" : tenantCode.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content.trim();
    }
}