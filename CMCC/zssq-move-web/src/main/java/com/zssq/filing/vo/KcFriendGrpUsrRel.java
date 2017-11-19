package com.zssq.filing.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: KcFriendGrpUsrRel  
 * @Description: 好友关系  
 * @author CaiZhaohui  
 * @date 2017年5月22日  
 *
 */
public class KcFriendGrpUsrRel implements RowMapper<KcFriendGrpUsrRel>,Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 用户编号 */
	private String userCode;
	
	/** 好友编号 */
	private String friendCode;
	
	/** 被关注编号 */
	private String concernedCode;
	
	/** 组编号 */
	private String groupCode;

	/** 非空id */
    private Integer id;

    /** 组id */
    private Integer groupId;

    /** 用户id */
    private Integer userId;

    /** 好友id */
    private Integer friendId;

    /** 状态 */
    private String status;

    /** 创建时间 */
    private Date createDate;
    
    public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getFriendCode() {
		return friendCode;
	}

	public void setFriendCode(String friendCode) {
		this.friendCode = friendCode;
	}

	public String getConcernedCode() {
		return concernedCode;
	}

	public void setConcernedCode(String concernedCode) {
		this.concernedCode = concernedCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	@Override
	public KcFriendGrpUsrRel mapRow(ResultSet rs, int rowNum) throws SQLException {
		KcFriendGrpUsrRel kcFriendGrpUsrRel = new KcFriendGrpUsrRel();
		kcFriendGrpUsrRel.setId(rs.getInt("ID"));
		kcFriendGrpUsrRel.setGroupId(rs.getInt("GROUP_ID"));
		kcFriendGrpUsrRel.setUserId(rs.getInt("USER_ID"));
		kcFriendGrpUsrRel.setFriendId(rs.getInt("FRIEND_ID"));
		kcFriendGrpUsrRel.setStatus(rs.getString("STATUS"));
		kcFriendGrpUsrRel.setCreateDate(rs.getDate("CREATE_DATE"));
		return kcFriendGrpUsrRel;
	}
}