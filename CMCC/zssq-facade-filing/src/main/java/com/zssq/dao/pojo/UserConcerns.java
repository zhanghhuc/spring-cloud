package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

public class UserConcerns extends BasePage{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String concernsCode;

    private String concernsUserCode;

    private String isConcernsUserCode;

    private Long createTime;

    private Long modifyTime;

    private String orgCode;

    private Byte orgLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcernsCode() {
        return concernsCode;
    }

    public void setConcernsCode(String concernsCode) {
        this.concernsCode = concernsCode == null ? null : concernsCode.trim();
    }

    public String getConcernsUserCode() {
        return concernsUserCode;
    }

    public void setConcernsUserCode(String concernsUserCode) {
        this.concernsUserCode = concernsUserCode == null ? null : concernsUserCode.trim();
    }

    public String getIsConcernsUserCode() {
        return isConcernsUserCode;
    }

    public void setIsConcernsUserCode(String isConcernsUserCode) {
        this.isConcernsUserCode = isConcernsUserCode == null ? null : isConcernsUserCode.trim();
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
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Byte getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Byte orgLevel) {
        this.orgLevel = orgLevel;
    }
}