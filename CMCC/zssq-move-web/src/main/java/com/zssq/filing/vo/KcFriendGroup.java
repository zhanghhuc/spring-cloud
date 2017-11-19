package com.zssq.filing.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: KcFriendGroup  
 * @Description: 好友分组
 * @author CaiZhaohui  
 * @date 2017年5月22日  
 *
 */
public class KcFriendGroup implements RowMapper<KcFriendGroup>,Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 用户编号 */
	private String userCode;

	/** 非空id */
    private Integer id;

    /** 组名称 */
    private String groupName;

    /** 创建时间 */
    private Date createDate;

    /** 创建用户 */
    private String createUser;

    /** 修改时间 */
    private Date modifyDate;

    /** 是否默认 */
    private String isDefault;

    public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

	@Override
	public KcFriendGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
		KcFriendGroup kcFriendGroup = new KcFriendGroup();
		kcFriendGroup.setId(rs.getInt("ID"));
		kcFriendGroup.setGroupName(rs.getString("GROUP_NAME"));
		kcFriendGroup.setCreateDate(rs.getDate("CREATE_DATE"));
		kcFriendGroup.setCreateUser(rs.getString("CREATE_USER"));
		kcFriendGroup.setModifyDate(rs.getDate("MODIFY_DATE"));
		kcFriendGroup.setIsDefault(rs.getString("IS_DEFAULT"));
		return kcFriendGroup;
	}
}