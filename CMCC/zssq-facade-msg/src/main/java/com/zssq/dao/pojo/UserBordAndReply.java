package com.zssq.dao.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: UserBordAndReply  
 * @Description: 留言查询  
 * @author YDB  
 * @date 2017年4月22日  
 *
 */
public class UserBordAndReply implements Serializable{
	
  	private Long id;

    private String messageBoardCode;

    private String userCode;

    private String messageBoardUserCode;

    private Byte isRead;

    private Long createTime;

    private Long modifyTime;

    private String orgCode;

    private String tenantCode;

    private Byte isDelete;

    private Byte isDeleteAll;

    private String bzName;

    private String content;

	private List<Map<String, Object>> replyList;

	private Integer replyCount;
	
	private String orgName;
	
	private String bzCode;
	
	private String userHead;
	
	private String boardUserHead;
	
	private String boardOrgName;
	
	//回复人名称
	private String boardUserName;
	//当前人名称
	private String userName;
	
    private Byte type;
	
	
	
	
	

	public String getBoardOrgName() {
		return boardOrgName;
	}

	public void setBoardOrgName(String boardOrgName) {
		this.boardOrgName = boardOrgName;
	}

	public String getUserHead() {
		return userHead;
	}

	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}

	
	public String getBoardUserHead() {
		return boardUserHead;
	}

	public void setBoardUserHead(String boardUserHead) {
		this.boardUserHead = boardUserHead;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getBzCode() {
		return bzCode;
	}

	public void setBzCode(String bzCode) {
		this.bzCode = bzCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getBoardUserName() {
		return boardUserName;
	}

	public void setBoardUserName(String boardUserName) {
		this.boardUserName = boardUserName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessageBoardCode() {
		return messageBoardCode;
	}

	public void setMessageBoardCode(String messageBoardCode) {
		this.messageBoardCode = messageBoardCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMessageBoardUserCode() {
		return messageBoardUserCode;
	}

	public void setMessageBoardUserCode(String messageBoardUserCode) {
		this.messageBoardUserCode = messageBoardUserCode;
	}

	public Byte getIsRead() {
		return isRead;
	}

	public void setIsRead(Byte isRead) {
		this.isRead = isRead;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public Byte getIsDeleteAll() {
		return isDeleteAll;
	}

	public void setIsDeleteAll(Byte isDeleteAll) {
		this.isDeleteAll = isDeleteAll;
	}

	public String getBzName() {
		return bzName;
	}

	public void setBzName(String bzName) {
		this.bzName = bzName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Map<String, Object>> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Map<String, Object>> replyList) {
		this.replyList = replyList;
	}
	 
	
}