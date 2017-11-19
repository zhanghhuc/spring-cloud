package com.zssq.relation.pojo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: BlogInfo  
 * @Description: 博客  
 * @author ZKZ  
 * @date 2017年4月5日  
 *
 */
public class BlogInfo implements RowMapper<BlogInfo>, Serializable {
        
	private static final long serialVersionUID = 1L;

	private Long id = 0L;
    private String blogCode = "";
    private String tenantCode = "";
    private String orgCode = "";
    private Long createTime = 0L;
    private Long modifyTime = 0L;
    private String remark = "";
    private String dynamicCode = "";
    private String userCode = "";
    private Byte blogAgentFlag = 0;
    private String agentUserCode = "";
    private Byte blogSource = 0;
    private String sourceBlogCode = "";
    private String sourceUserCode = "";
    private Byte sourceBlogDepend = 0;
    private String sourceTeamCode = "";
    private Byte blogDepend = 0;
    private String teamCode = "";
    private String blogTitle = "";
    private String blogDigest = "";
    private String classCode = "";
    private String blogTags = "";
    private Long blogPlanPublishTime = 0L;
    private Long blogPublishTime = 0L;
    private String blogLogo = "";
    private Byte blogIsDelete = 0;
    private Byte blogIsShield = 0;
    private String blogTips = "";


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogCode() {
        return blogCode;
    }

    public void setBlogCode(String blogCode) {
        this.blogCode = blogCode == null ? null : blogCode.trim();
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode == null ? null : dynamicCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Byte getBlogAgentFlag() {
        return blogAgentFlag;
    }

    public void setBlogAgentFlag(Byte blogAgentFlag) {
        this.blogAgentFlag = blogAgentFlag;
    }

    public String getAgentUserCode() {
        return agentUserCode;
    }

    public void setAgentUserCode(String agentUserCode) {
        this.agentUserCode = agentUserCode == null ? null : agentUserCode.trim();
    }

    public Byte getBlogSource() {
        return blogSource;
    }

    public void setBlogSource(Byte blogSource) {
        this.blogSource = blogSource;
    }

    public String getSourceBlogCode() {
        return sourceBlogCode;
    }

    public void setSourceBlogCode(String sourceBlogCode) {
        this.sourceBlogCode = sourceBlogCode == null ? null : sourceBlogCode.trim();
    }

    public String getSourceUserCode() {
        return sourceUserCode;
    }

    public void setSourceUserCode(String sourceUserCode) {
        this.sourceUserCode = sourceUserCode == null ? null : sourceUserCode.trim();
    }

	public Byte getSourceBlogDepend() {
		return sourceBlogDepend;
	}

	public void setSourceBlogDepend(Byte sourceBlogDepend) {
		this.sourceBlogDepend = sourceBlogDepend;
	}

	public String getSourceTeamCode() {
		return sourceTeamCode;
	}

	public void setSourceTeamCode(String sourceTeamCode) {
		this.sourceTeamCode = sourceTeamCode;
	}

	public Byte getBlogDepend() {
        return blogDepend;
    }

    public void setBlogDepend(Byte blogDepend) {
        this.blogDepend = blogDepend;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle == null ? null : blogTitle.trim();
    }

    public String getBlogDigest() {
        return blogDigest;
    }

    public void setBlogDigest(String blogDigest) {
        this.blogDigest = blogDigest == null ? null : blogDigest.trim();
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public String getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(String blogTags) {
        this.blogTags = blogTags == null ? null : blogTags.trim();
    }

    public Long getBlogPlanPublishTime() {
        return blogPlanPublishTime;
    }

    public void setBlogPlanPublishTime(Long blogPlanPublishTime) {
        this.blogPlanPublishTime = blogPlanPublishTime;
    }

    public Long getBlogPublishTime() {
        return blogPublishTime;
    }

    public void setBlogPublishTime(Long blogPublishTime) {
        this.blogPublishTime = blogPublishTime;
    }

    public String getBlogLogo() {
        return blogLogo;
    }

    public void setBlogLogo(String blogLogo) {
        this.blogLogo = blogLogo == null ? null : blogLogo.trim();
    }

    public Byte getBlogIsDelete() {
        return blogIsDelete;
    }

    public void setBlogIsDelete(Byte blogIsDelete) {
        this.blogIsDelete = blogIsDelete;
    }

    public Byte getBlogIsShield() {
        return blogIsShield;
    }

    public void setBlogIsShield(Byte blogIsShield) {
        this.blogIsShield = blogIsShield;
    }

    public String getBlogTips() {
        return blogTips;
    }

    public void setBlogTips(String blogTips) {
        this.blogTips = blogTips == null ? null : blogTips.trim();
    }


	@Override
	public BlogInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		BlogInfo blogInfo = new BlogInfo();
		blogInfo.setCreateTime(rs.getLong("create_time"));
		blogInfo.setDynamicCode(rs.getString("dynamic_code"));
		blogInfo.setBlogIsDelete(rs.getByte("blog_is_delete"));
		blogInfo.setBlogIsShield(rs.getByte("blog_is_shield"));
		blogInfo.setBlogCode(rs.getString("blog_code"));
		blogInfo.setBlogDepend(rs.getByte("blog_depend"));
		blogInfo.setBlogSource(rs.getByte("blog_source"));
		blogInfo.setBlogTips(rs.getString("blog_tips"));
		blogInfo.setBlogTitle(rs.getString("blog_title"));
		blogInfo.setBlogDigest(rs.getString("blog_digest"));
		
		blogInfo.setSourceBlogCode(rs.getString("source_blog_code"));
		blogInfo.setSourceUserCode(rs.getString("source_user_code"));
		blogInfo.setSourceBlogDepend(rs.getByte("source_blog_depend"));
		blogInfo.setSourceTeamCode(rs.getString("source_team_code"));
		
		blogInfo.setModifyTime(rs.getLong("modify_time"));
		blogInfo.setOrgCode(rs.getString("org_code"));
		blogInfo.setBlogPublishTime(rs.getLong("blog_publish_time"));
		blogInfo.setTeamCode(rs.getString("team_code"));
		blogInfo.setTenantCode(rs.getString("tenant_code"));
		blogInfo.setUserCode(rs.getString("user_code"));
		return blogInfo;
	}
    
    
    
}