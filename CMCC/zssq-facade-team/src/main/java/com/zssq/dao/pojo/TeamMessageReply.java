package com.zssq.dao.pojo;

import java.io.Serializable;

public class TeamMessageReply implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Long id;

	/** 回复唯一标识 */
    private String replyCode;

    /** 留言唯一标识 */
    private String messageCode;

    /** 回复人 */
    private String userCode;
    
    /** 被回复人 */
    private String replyUserCode;

    /** 回复内容 */
    private String replyMessaage;

    /** 创建时间 */
    private Long createTime;
    
    /** 是否删除 */
    private Byte isDelete;
    
    /** 读取下一页留言的条数 */
    private Integer pageCount;

    public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode == null ? null : replyCode.trim();
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode == null ? null : messageCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getReplyMessaage() {
        return replyMessaage;
    }

    public void setReplyMessaage(String replyMessaage) {
        this.replyMessaage = replyMessaage == null ? null : replyMessaage.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public String getReplyUserCode() {
		return replyUserCode;
	}

	public void setReplyUserCode(String replyUserCode) {
		this.replyUserCode = replyUserCode;
	}
}