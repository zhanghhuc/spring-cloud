package com.zssq.search.po;


import com.zssq.solr.annotation.SolrAnnotation;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * Created by admin on 2017-03-24.
 */
public class ZssqResultVo implements Serializable {
	/** id 对应各实体id,由于会有冲突,不作主键使用 */
//    @Field
    @SolrAnnotation(value = "nid")
    private Integer id;
	/** categoryCode对应各实体code,solr中的主键 */
    @Field
    @SolrAnnotation(value = "categoryCode")
    private String categoryCode;

	/** categoryTitle 对应各实体标题*/
    @Field
    @SolrAnnotation(value = "categoryTitle")
    private String categoryTitle;

	/** contentDigest 对应各实体中的内容摘要 */
    @Field
    @SolrAnnotation(value = "contentDigest")
    private String contentDigest;

	/** contentText 对应各实体中的内容 */
	@Field
	@SolrAnnotation(value = "contentText")
	private String contentText;

	/** status 对应各实体中的状态 */
	@Field
	@SolrAnnotation(value = "status")
	private Integer status;


	/** sourceBelongsTo 对应各实体中的归属 1:个人,2:班组 */
	@Field
	@SolrAnnotation(value = "sourceBelongsTo")
	private Integer sourceBelongsTo;

	/** rootCode 对应各实体中的根节点，如评论回复根节点为主体(微博、博客code) */
	@Field
	@SolrAnnotation(value = "rootCode")
	private String rootCode;

	/** parentCode 对应各实体中的父节点，如评论回复父节点为主体的回复 */
	@Field
	@SolrAnnotation(value = "parentCode")
	private String parentCode;

	/** creatorCode 创建人 */
	@Field
	@SolrAnnotation(value = "creatorCode")
	private String creatorCode;

	/** modifyCode 修改人 */
	@Field
	@SolrAnnotation(value = "modifyCode")
	private String modifyCode;

	/** tenantCode 租户编码 */
	@Field
	@SolrAnnotation(value = "tenantCode")
	private String tenantCode;

	/** isDelete 是否删除 */
	@Field
	@SolrAnnotation(value = "isDelete")
	private Integer isDelete;

	/** isHidden 是否隐藏 */
	@Field
	@SolrAnnotation(value = "isHidden")
	private Integer isHidden;

	/** planTime 计划发布时间(仅微博有定时发布任务) */
	@Field
	@SolrAnnotation(value = "planTime")
	private Long planTime;

	/** createTime 创建时间 */
	@Field
    @SolrAnnotation(value = "createTime")
    private Long createTime;

	/** modifyTime 修改时间 */
    @Field
    @SolrAnnotation(value = "modifyTime")
    private Long modifyTime;

	/** remark 修改时间 */
    @Field
    @SolrAnnotation(value = "remark")
    private String remark;

	/**
	 * moduleType 类别
	 * 1、微博；2、博客；3、新闻；4、投票；5、活动；6、帖子；7、网盘；8、知识库
	 */
    @Field
    @SolrAnnotation(value = "moduleType")
    private Integer moduleType;

	/**
	 * categoryType 类型
	 * 1、主体(module_type中所有类型) 2、评论  3、评论的回复  4、转发
	 */
    @Field
    @SolrAnnotation(value = "categoryType")
    private Integer categoryType;


	/**
	 * orgCode 用户所属机构编码
	 */
    @Field
    @SolrAnnotation(value = "orgCode")
    private String orgCode;

	private String  highlightingFiled ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getContentDigest() {
		return contentDigest;
	}

	public void setContentDigest(String contentDigest) {
		this.contentDigest = contentDigest;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRootCode() {
		return rootCode;
	}

	public void setRootCode(String rootCode) {
		this.rootCode = rootCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCreatorCode() {
		return creatorCode;
	}

	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}

	public String getModifyCode() {
		return modifyCode;
	}

	public void setModifyCode(String modifyCode) {
		this.modifyCode = modifyCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
	}

	public Long getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Long planTime) {
		this.planTime = planTime;
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
		this.remark = remark;
	}

	public Integer getModuleType() {
		return moduleType;
	}

	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getSourceBelongsTo() {
		return sourceBelongsTo;
	}

	public void setSourceBelongsTo(Integer sourceBelongsTo) {
		this.sourceBelongsTo = sourceBelongsTo;
	}

	public String getHighlightingFiled() {
		return highlightingFiled;
	}

	public void setHighlightingFiled(String highlightingFiled) {
		this.highlightingFiled = highlightingFiled;
	}
}
