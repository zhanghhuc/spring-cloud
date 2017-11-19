package com.zssq.dao.pojo;

import java.util.List;

import com.zssq.pojo.BasePage;

public class TeamMessage extends BasePage {
    
	private static final long serialVersionUID = 1L;

	private Long id;

	/** 留言唯一标识 */
    private String teamMessageCode;

    /** 班组编码 */
    private String teamCode;

    /** 内容 */
    private String content;

    /** 留言时间 */
    private Long createTime;

    /** 留言人 */
    private String userCode;

    /** 是否删除 */
    private Byte isDelete;
    
    /** 该班组的班组长 */
    private List<String> leaders;
    
    /** 回复集合 */
    private List<TeamMessageReply> teamMessageReply;
    
    /** 班组编码集合(入参) */
    private List<String> teamCodes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamMessageCode() {
        return teamMessageCode;
    }

    public void setTeamMessageCode(String teamMessageCode) {
        this.teamMessageCode = teamMessageCode == null ? null : teamMessageCode.trim();
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

	public List<TeamMessageReply> getTeamMessageReply() {
		return teamMessageReply;
	}

	public void setTeamMessageReply(List<TeamMessageReply> teamMessageReply) {
		this.teamMessageReply = teamMessageReply;
	}

	public List<String> getLeaders() {
		return leaders;
	}

	public void setLeaders(List<String> leaders) {
		this.leaders = leaders;
	}

	public List<String> getTeamCodes() {
		return teamCodes;
	}

	public void setTeamCodes(List<String> teamCodes) {
		this.teamCodes = teamCodes;
	}
}