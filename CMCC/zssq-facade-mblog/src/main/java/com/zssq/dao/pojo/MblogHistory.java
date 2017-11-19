package com.zssq.dao.pojo;

/**
 * 
    * @ClassName: MblogHistory  
    * @Description: 微博历史对象  
    * @author Mr.B  
    * @date 2017年3月15日  
    *
 */
public class MblogHistory extends BaseEntity{
    /**  
    * @Fields serialVersionUID
    */  
	private static final long serialVersionUID = 1L;

    private String mblogHistoryCode = "";

    private String orgCode = "";

    private String mblogCode = "";

    private String userCode = "";

    private String action = "";

    private String tenantCode = "";

    private Byte actionType = 0;
    
    
    public Byte getActionType() {
		return actionType;
	}

	public void setActionType(Byte actionType) {
		this.actionType = actionType;
	}

	public String getMblogHistoryCode() {
        return mblogHistoryCode;
    }

    public void setMblogHistoryCode(String mblogHistoryCode) {
        this.mblogHistoryCode = mblogHistoryCode == null ? "" : mblogHistoryCode.trim();
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? "" : userCode.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? "" : action.trim();
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? "" : tenantCode.trim();
    }
}