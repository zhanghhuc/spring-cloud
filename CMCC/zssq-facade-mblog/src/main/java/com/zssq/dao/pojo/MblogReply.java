package com.zssq.dao.pojo;

/**
 * 
    * @ClassName: MblogReply  
    * @Description: 微博回复对象  
    * @author Mr.B  
    * @date 2017年3月15日  
    *
 */
public class MblogReply extends BaseEntity{
    /**  
    * @Fields serialVersionUID
    */  
	private static final long serialVersionUID = 1L;

    private String mblogReplyCode = "";

    private String orgCode = "";

    private String userCode = "";

    private String commentCode = "";

    private String replyedUserCode = "";

    private String replyedOrgCode = "";

    private Byte isDelete = 0;

    private Byte isShield = 0;

    private Integer reportNum = 0;

    private Integer praiseNum = 0;

    private String mblogCode = "";

    private String tenantCode = "";

    private String content = "";

    private String shieldUserCode = "";
    
    private Long shieldTime = 0L;
    
    private Byte replyType = 1;
    
    /*  =============  外部关联数据  =================== */
    /** 我是否点赞 **/
    private Byte mePraise = 0;
    /*  =============  外部关联数据  =================== */
    
    
    
    public String getShieldUserCode() {
		return shieldUserCode;
	}
	public Byte getReplyType() {
		return replyType;
	}
	public void setReplyType(Byte replyType) {
		this.replyType = replyType;
	}
	public void setShieldUserCode(String shieldUserCode) {
		this.shieldUserCode = shieldUserCode;
	}
	public Long getShieldTime() {
		return shieldTime;
	}
	public void setShieldTime(Long shieldTime) {
		this.shieldTime = shieldTime;
	}
	
    public String getMblogReplyCode() {
        return mblogReplyCode;
    }

    public Byte getMePraise() {
		return mePraise;
	}

	public void setMePraise(Byte mePraise) {
		this.mePraise = mePraise;
	}

	public void setMblogReplyCode(String mblogReplyCode) {
        this.mblogReplyCode = mblogReplyCode == null ? "" : mblogReplyCode.trim();
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

    public String getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(String commentCode) {
        this.commentCode = commentCode == null ? "" : commentCode.trim();
    }

    public String getReplyedUserCode() {
        return replyedUserCode;
    }

    public void setReplyedUserCode(String replyedUserCode) {
        this.replyedUserCode = replyedUserCode == null ? "" : replyedUserCode.trim();
    }

    public String getReplyedOrgCode() {
        return replyedOrgCode;
    }

    public void setReplyedOrgCode(String replyedOrgCode) {
        this.replyedOrgCode = replyedOrgCode == null ? "" : replyedOrgCode.trim();
    }

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public Byte getIsShield() {
		return isShield;
	}

	public void setIsShield(Byte isShield) {
		this.isShield = isShield;
	}

	public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content.trim();
    }
}