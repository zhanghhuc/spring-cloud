package com.zssq.blog.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: MySQLBlogData  
 * @Description: 博客数据  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
public class MySQLBlogData implements Serializable {
        
	private static final long serialVersionUID = 1L;

	private Long id = 0L;
    private String dataCode = "";
    private String tenantCode = "";
    private String orgCode = "";
    private Long createTime = 0L;
    private Long modifyTime = 0L;
    private String remark = "";
    private String blogCode = "";
    private Integer teamQualityNum = 0;
    private Integer groupQualityNum = 0;
    private Integer provinceQualityNum = 0;
    private Integer cityQualityNum = 0;
    private Integer readNum = 0;
    private Integer likeNum = 0;
    private Integer collectNum = 0;
    private Integer forwardNum = 0;
    private Integer commentNum = 0;
    private Integer shareNum = 0;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode == null ? null : dataCode.trim();
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

    public String getBlogCode() {
        return blogCode;
    }

    public void setBlogCode(String blogCode) {
        this.blogCode = blogCode == null ? null : blogCode.trim();
    }

    public Integer getTeamQualityNum() {
        return teamQualityNum;
    }

    public void setTeamQualityNum(Integer teamQualityNum) {
        this.teamQualityNum = teamQualityNum;
    }

    public Integer getGroupQualityNum() {
        return groupQualityNum;
    }

    public void setGroupQualityNum(Integer groupQualityNum) {
        this.groupQualityNum = groupQualityNum;
    }

    public Integer getProvinceQualityNum() {
        return provinceQualityNum;
    }

    public void setProvinceQualityNum(Integer provinceQualityNum) {
        this.provinceQualityNum = provinceQualityNum;
    }

    public Integer getCityQualityNum() {
        return cityQualityNum;
    }

    public void setCityQualityNum(Integer cityQualityNum) {
        this.cityQualityNum = cityQualityNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(Integer forwardNum) {
        this.forwardNum = forwardNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }
}