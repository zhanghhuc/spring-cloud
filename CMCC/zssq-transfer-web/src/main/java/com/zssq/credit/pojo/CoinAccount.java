package com.zssq.credit.pojo;

public class CoinAccount {
    private Long id;

    private String accountCode;

    private Byte accountType;

    private Integer coinBalance;

    private String coinBalanceSalt;

    private Long createTime;

    private Long modifyTime;

    private String orgCode;

    private String saasTenantCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode == null ? null : accountCode.trim();
    }

    public Byte getAccountType() {
        return accountType;
    }

    public void setAccountType(Byte accountType) {
        this.accountType = accountType;
    }

    public Integer getCoinBalance() {
        return coinBalance;
    }

    public void setCoinBalance(Integer coinBalance) {
        this.coinBalance = coinBalance;
    }

    public String getCoinBalanceSalt() {
        return coinBalanceSalt;
    }

    public void setCoinBalanceSalt(String coinBalanceSalt) {
        this.coinBalanceSalt = coinBalanceSalt == null ? null : coinBalanceSalt.trim();
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

    public String getSaasTenantCode() {
        return saasTenantCode;
    }

    public void setSaasTenantCode(String saasTenantCode) {
        this.saasTenantCode = saasTenantCode == null ? null : saasTenantCode.trim();
    }
}