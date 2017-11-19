package com.zssq.news.model;

import java.io.Serializable;

/**
 * @author SharlaCheung
 * @ClassName: NewsInfoInformModel
 * @Description: 新闻举报增删改参数类
 * @date 2017年04月02日
 */
public class NewsInfoInformModel implements Serializable {

    private static final long serialVersionUID = -1606166264275829882L;

    private String newsCode;//新闻编码

    private String infoCreatorCode;//新闻发布人Code

    private Integer informType;//举报类型

    private String informPeopleCode;//举报人Code

    private Integer orgLevel;//举报人机构级别

    private String orgCode;//举报人组织机构编码

    private String processorUserCode;//新闻处理人Code

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String tenantCode;//租户Code

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getInfoCreatorCode() {
        return infoCreatorCode;
    }

    public void setInfoCreatorCode(String infoCreatorCode) {
        this.infoCreatorCode = infoCreatorCode;
    }

    public Integer getInformType() {
        return informType;
    }

    public void setInformType(Integer informType) {
        this.informType = informType;
    }

    public String getInformPeopleCode() {
        return informPeopleCode;
    }

    public void setInformPeopleCode(String informPeopleCode) {
        this.informPeopleCode = informPeopleCode;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getProcessorUserCode() {
        return processorUserCode;
    }

    public void setProcessorUserCode(String processorUserCode) {
        this.processorUserCode = processorUserCode;
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

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }
}
