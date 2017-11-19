package com.zssq.dao.pojo;

import java.util.List;

/**
 * 
    * @ClassName: MblogComment  
    * @Description: 微博评论对象  
    * @author Mr.B  
    * @date 2017年3月15日  
    *
 */
public class MblogComment extends BaseEntity{
    /**  
    * @Fields serialVersionUID
    */  
	private static final long serialVersionUID = 1L;

    private String mblogCommentCode = "";

    private String orgCode = "";

    private String userCode = "";

    private Byte isDelete = 0;

    private Byte isShield = 0;

    private Integer reportNum = 0;

    private Integer praiseNum = 0;

    private String mblogCode = ""; 

    private String tenantCode = "";

    private Integer replyNum = 0;

    private String content = "";

    private String shieldUserCode = "";
    
    private Long shieldTime = 0L;
    
    /*  =============  外部关联数据  =================== */
    /** 我是否点赞 **/
    private Byte mePraise = 0;
    /** 评论的回复集合 **/
    private List<MblogReply> replyList = null;
    /*  =============  外部关联数据  =================== */
    
    
    public String getShieldUserCode() {
		return shieldUserCode;
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
    public String getMblogCommentCode() {
        return mblogCommentCode;
    }

    public Byte getMePraise() {
		return mePraise;
	}

	public void setMePraise(Byte mePraise) {
		this.mePraise = mePraise;
	}

	public void setMblogCommentCode(String mblogCommentCode) {
        this.mblogCommentCode = mblogCommentCode == null ? "" : mblogCommentCode.trim();
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

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content.trim();
    }
	public List<MblogReply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<MblogReply> replyList) {
		this.replyList = replyList;
	}
    
    
    
}