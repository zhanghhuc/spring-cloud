package com.zssq.dao.pojo;

/**
 * 
    * @ClassName: MblogAt  
    * @Description: 微博At对象  
    * @author Mr.B  
    * @date 2017年3月15日  
    *
 */
public class MblogAt extends BaseEntity{

    /**  
    * @Fields serialVersionUID :
    */  
	private static final long serialVersionUID = 1L;

	private String mblogAtCode = "";

    private String orgCode = "";

    private String userCode = "";

    private Byte type = 0;

    private Byte atSite = 0;

    private String mblogCode = "";

    private String commentCode = "";

    private String tenantCode = "";

    private String replyCode = "";
    
    

    public String getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}

	public String getMblogAtCode() {
        return mblogAtCode;
    }

    public void setMblogAtCode(String mblogAtCode) {
        this.mblogAtCode = mblogAtCode == null ? "" : mblogAtCode.trim();
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getAtSite() {
        return atSite;
    }

    public void setAtSite(Byte atSite) {
        this.atSite = atSite;
    }

    public String getMblogCode() {
        return mblogCode;
    }

    public void setMblogCode(String mblogCode) {
        this.mblogCode = mblogCode == null ? "" : mblogCode.trim();
    }

    public String getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(String commentCode) {
        this.commentCode = commentCode == null ? "" : commentCode.trim();
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? "" : tenantCode.trim();
    }
}