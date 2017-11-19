package com.zssq.dao.pojo;

/**
 * 
    * @ClassName: MblogCollect  
    * @Description: 微博收藏对象  
    * @author Mr.B  
    * @date 2017年3月15日  
    *
 */
public class MblogCollect extends BaseEntity{
    /**  
    * @Fields serialVersionUID
    */  
	private static final long serialVersionUID = 1L;
	
    private String mblogCollectCode = "";

    private String orgCode = "";

    private String userCode = "";

    private String mblogCode = "";

    private String tenantCode = "";

    public String getMblogCollectCode() {
        return mblogCollectCode;
    }

    public void setMblogCollectCode(String mblogCollectCode) {
        this.mblogCollectCode = mblogCollectCode == null ? "" : mblogCollectCode.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? "" : orgCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? "" : userCode.trim();
    }

    public String getMblogCode() {
        return mblogCode;
    }

    public void setMblogCode(String mblogCode) {
        this.mblogCode = mblogCode == null ? "" : mblogCode.trim();
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? "" : tenantCode.trim();
    }
}