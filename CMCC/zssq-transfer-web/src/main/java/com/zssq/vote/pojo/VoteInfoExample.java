package com.zssq.vote.pojo;

import java.util.ArrayList;
import java.util.List;

public class VoteInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public VoteInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andIsDisableIsNull() {
            addCriterion("is_disable is null");
            return (Criteria) this;
        }

        public Criteria andIsDisableIsNotNull() {
            addCriterion("is_disable is not null");
            return (Criteria) this;
        }

        public Criteria andIsDisableEqualTo(Byte value) {
            addCriterion("is_disable =", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableNotEqualTo(Byte value) {
            addCriterion("is_disable <>", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableGreaterThan(Byte value) {
            addCriterion("is_disable >", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_disable >=", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableLessThan(Byte value) {
            addCriterion("is_disable <", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableLessThanOrEqualTo(Byte value) {
            addCriterion("is_disable <=", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableIn(List<Byte> values) {
            addCriterion("is_disable in", values, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableNotIn(List<Byte> values) {
            addCriterion("is_disable not in", values, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableBetween(Byte value1, Byte value2) {
            addCriterion("is_disable between", value1, value2, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableNotBetween(Byte value1, Byte value2) {
            addCriterion("is_disable not between", value1, value2, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Byte value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Byte value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Byte value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Byte value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Byte value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Byte> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Byte> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Byte value1, Byte value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Byte value1, Byte value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsHideIsNull() {
            addCriterion("is_hide is null");
            return (Criteria) this;
        }

        public Criteria andIsHideIsNotNull() {
            addCriterion("is_hide is not null");
            return (Criteria) this;
        }

        public Criteria andIsHideEqualTo(Byte value) {
            addCriterion("is_hide =", value, "isHide");
            return (Criteria) this;
        }

        public Criteria andIsHideNotEqualTo(Byte value) {
            addCriterion("is_hide <>", value, "isHide");
            return (Criteria) this;
        }

        public Criteria andIsHideGreaterThan(Byte value) {
            addCriterion("is_hide >", value, "isHide");
            return (Criteria) this;
        }

        public Criteria andIsHideGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_hide >=", value, "isHide");
            return (Criteria) this;
        }

        public Criteria andIsHideLessThan(Byte value) {
            addCriterion("is_hide <", value, "isHide");
            return (Criteria) this;
        }

        public Criteria andIsHideLessThanOrEqualTo(Byte value) {
            addCriterion("is_hide <=", value, "isHide");
            return (Criteria) this;
        }

        public Criteria andIsHideIn(List<Byte> values) {
            addCriterion("is_hide in", values, "isHide");
            return (Criteria) this;
        }

        public Criteria andIsHideNotIn(List<Byte> values) {
            addCriterion("is_hide not in", values, "isHide");
            return (Criteria) this;
        }

        public Criteria andIsHideBetween(Byte value1, Byte value2) {
            addCriterion("is_hide between", value1, value2, "isHide");
            return (Criteria) this;
        }

        public Criteria andIsHideNotBetween(Byte value1, Byte value2) {
            addCriterion("is_hide not between", value1, value2, "isHide");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIsNull() {
            addCriterion("tenant_code is null");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIsNotNull() {
            addCriterion("tenant_code is not null");
            return (Criteria) this;
        }

        public Criteria andTenantCodeEqualTo(String value) {
            addCriterion("tenant_code =", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotEqualTo(String value) {
            addCriterion("tenant_code <>", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeGreaterThan(String value) {
            addCriterion("tenant_code >", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_code >=", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLessThan(String value) {
            addCriterion("tenant_code <", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLessThanOrEqualTo(String value) {
            addCriterion("tenant_code <=", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLike(String value) {
            addCriterion("tenant_code like", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotLike(String value) {
            addCriterion("tenant_code not like", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIn(List<String> values) {
            addCriterion("tenant_code in", values, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotIn(List<String> values) {
            addCriterion("tenant_code not in", values, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeBetween(String value1, String value2) {
            addCriterion("tenant_code between", value1, value2, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotBetween(String value1, String value2) {
            addCriterion("tenant_code not between", value1, value2, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNull() {
            addCriterion("org_code is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("org_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("org_code =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("org_code <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("org_code >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("org_code >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("org_code <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("org_code <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("org_code like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("org_code not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("org_code in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("org_code not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("org_code between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("org_code not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIsNull() {
            addCriterion("org_level is null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIsNotNull() {
            addCriterion("org_level is not null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelEqualTo(Byte value) {
            addCriterion("org_level =", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotEqualTo(Byte value) {
            addCriterion("org_level <>", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelGreaterThan(Byte value) {
            addCriterion("org_level >", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelGreaterThanOrEqualTo(Byte value) {
            addCriterion("org_level >=", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLessThan(Byte value) {
            addCriterion("org_level <", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLessThanOrEqualTo(Byte value) {
            addCriterion("org_level <=", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIn(List<Byte> values) {
            addCriterion("org_level in", values, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotIn(List<Byte> values) {
            addCriterion("org_level not in", values, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelBetween(Byte value1, Byte value2) {
            addCriterion("org_level between", value1, value2, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotBetween(Byte value1, Byte value2) {
            addCriterion("org_level not between", value1, value2, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Long value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Long value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Long value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Long value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Long value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Long> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Long> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Long value1, Long value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Long value1, Long value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeIsNull() {
            addCriterion("dynamic_code is null");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeIsNotNull() {
            addCriterion("dynamic_code is not null");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeEqualTo(String value) {
            addCriterion("dynamic_code =", value, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeNotEqualTo(String value) {
            addCriterion("dynamic_code <>", value, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeGreaterThan(String value) {
            addCriterion("dynamic_code >", value, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeGreaterThanOrEqualTo(String value) {
            addCriterion("dynamic_code >=", value, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeLessThan(String value) {
            addCriterion("dynamic_code <", value, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeLessThanOrEqualTo(String value) {
            addCriterion("dynamic_code <=", value, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeLike(String value) {
            addCriterion("dynamic_code like", value, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeNotLike(String value) {
            addCriterion("dynamic_code not like", value, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeIn(List<String> values) {
            addCriterion("dynamic_code in", values, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeNotIn(List<String> values) {
            addCriterion("dynamic_code not in", values, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeBetween(String value1, String value2) {
            addCriterion("dynamic_code between", value1, value2, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andDynamicCodeNotBetween(String value1, String value2) {
            addCriterion("dynamic_code not between", value1, value2, "dynamicCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeIsNull() {
            addCriterion("sponsor_code is null");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeIsNotNull() {
            addCriterion("sponsor_code is not null");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeEqualTo(String value) {
            addCriterion("sponsor_code =", value, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeNotEqualTo(String value) {
            addCriterion("sponsor_code <>", value, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeGreaterThan(String value) {
            addCriterion("sponsor_code >", value, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sponsor_code >=", value, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeLessThan(String value) {
            addCriterion("sponsor_code <", value, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeLessThanOrEqualTo(String value) {
            addCriterion("sponsor_code <=", value, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeLike(String value) {
            addCriterion("sponsor_code like", value, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeNotLike(String value) {
            addCriterion("sponsor_code not like", value, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeIn(List<String> values) {
            addCriterion("sponsor_code in", values, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeNotIn(List<String> values) {
            addCriterion("sponsor_code not in", values, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeBetween(String value1, String value2) {
            addCriterion("sponsor_code between", value1, value2, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorCodeNotBetween(String value1, String value2) {
            addCriterion("sponsor_code not between", value1, value2, "sponsorCode");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeIsNull() {
            addCriterion("sponsor_type is null");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeIsNotNull() {
            addCriterion("sponsor_type is not null");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeEqualTo(String value) {
            addCriterion("sponsor_type =", value, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeNotEqualTo(String value) {
            addCriterion("sponsor_type <>", value, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeGreaterThan(String value) {
            addCriterion("sponsor_type >", value, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sponsor_type >=", value, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeLessThan(String value) {
            addCriterion("sponsor_type <", value, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeLessThanOrEqualTo(String value) {
            addCriterion("sponsor_type <=", value, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeLike(String value) {
            addCriterion("sponsor_type like", value, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeNotLike(String value) {
            addCriterion("sponsor_type not like", value, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeIn(List<String> values) {
            addCriterion("sponsor_type in", values, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeNotIn(List<String> values) {
            addCriterion("sponsor_type not in", values, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeBetween(String value1, String value2) {
            addCriterion("sponsor_type between", value1, value2, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorTypeNotBetween(String value1, String value2) {
            addCriterion("sponsor_type not between", value1, value2, "sponsorType");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeIsNull() {
            addCriterion("sponsor_org_code is null");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeIsNotNull() {
            addCriterion("sponsor_org_code is not null");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeEqualTo(String value) {
            addCriterion("sponsor_org_code =", value, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeNotEqualTo(String value) {
            addCriterion("sponsor_org_code <>", value, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeGreaterThan(String value) {
            addCriterion("sponsor_org_code >", value, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sponsor_org_code >=", value, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeLessThan(String value) {
            addCriterion("sponsor_org_code <", value, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("sponsor_org_code <=", value, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeLike(String value) {
            addCriterion("sponsor_org_code like", value, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeNotLike(String value) {
            addCriterion("sponsor_org_code not like", value, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeIn(List<String> values) {
            addCriterion("sponsor_org_code in", values, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeNotIn(List<String> values) {
            addCriterion("sponsor_org_code not in", values, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeBetween(String value1, String value2) {
            addCriterion("sponsor_org_code between", value1, value2, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andSponsorOrgCodeNotBetween(String value1, String value2) {
            addCriterion("sponsor_org_code not between", value1, value2, "sponsorOrgCode");
            return (Criteria) this;
        }

        public Criteria andVoteStatusIsNull() {
            addCriterion("vote_status is null");
            return (Criteria) this;
        }

        public Criteria andVoteStatusIsNotNull() {
            addCriterion("vote_status is not null");
            return (Criteria) this;
        }

        public Criteria andVoteStatusEqualTo(Byte value) {
            addCriterion("vote_status =", value, "voteStatus");
            return (Criteria) this;
        }

        public Criteria andVoteStatusNotEqualTo(Byte value) {
            addCriterion("vote_status <>", value, "voteStatus");
            return (Criteria) this;
        }

        public Criteria andVoteStatusGreaterThan(Byte value) {
            addCriterion("vote_status >", value, "voteStatus");
            return (Criteria) this;
        }

        public Criteria andVoteStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("vote_status >=", value, "voteStatus");
            return (Criteria) this;
        }

        public Criteria andVoteStatusLessThan(Byte value) {
            addCriterion("vote_status <", value, "voteStatus");
            return (Criteria) this;
        }

        public Criteria andVoteStatusLessThanOrEqualTo(Byte value) {
            addCriterion("vote_status <=", value, "voteStatus");
            return (Criteria) this;
        }

        public Criteria andVoteStatusIn(List<Byte> values) {
            addCriterion("vote_status in", values, "voteStatus");
            return (Criteria) this;
        }

        public Criteria andVoteStatusNotIn(List<Byte> values) {
            addCriterion("vote_status not in", values, "voteStatus");
            return (Criteria) this;
        }

        public Criteria andVoteStatusBetween(Byte value1, Byte value2) {
            addCriterion("vote_status between", value1, value2, "voteStatus");
            return (Criteria) this;
        }

        public Criteria andVoteStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("vote_status not between", value1, value2, "voteStatus");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionIsNull() {
            addCriterion("is_multi_option is null");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionIsNotNull() {
            addCriterion("is_multi_option is not null");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionEqualTo(Byte value) {
            addCriterion("is_multi_option =", value, "isMultiOption");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionNotEqualTo(Byte value) {
            addCriterion("is_multi_option <>", value, "isMultiOption");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionGreaterThan(Byte value) {
            addCriterion("is_multi_option >", value, "isMultiOption");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_multi_option >=", value, "isMultiOption");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionLessThan(Byte value) {
            addCriterion("is_multi_option <", value, "isMultiOption");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionLessThanOrEqualTo(Byte value) {
            addCriterion("is_multi_option <=", value, "isMultiOption");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionIn(List<Byte> values) {
            addCriterion("is_multi_option in", values, "isMultiOption");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionNotIn(List<Byte> values) {
            addCriterion("is_multi_option not in", values, "isMultiOption");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionBetween(Byte value1, Byte value2) {
            addCriterion("is_multi_option between", value1, value2, "isMultiOption");
            return (Criteria) this;
        }

        public Criteria andIsMultiOptionNotBetween(Byte value1, Byte value2) {
            addCriterion("is_multi_option not between", value1, value2, "isMultiOption");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentIsNull() {
            addCriterion("is_enable_comment is null");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentIsNotNull() {
            addCriterion("is_enable_comment is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentEqualTo(Byte value) {
            addCriterion("is_enable_comment =", value, "isEnableComment");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentNotEqualTo(Byte value) {
            addCriterion("is_enable_comment <>", value, "isEnableComment");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentGreaterThan(Byte value) {
            addCriterion("is_enable_comment >", value, "isEnableComment");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_enable_comment >=", value, "isEnableComment");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentLessThan(Byte value) {
            addCriterion("is_enable_comment <", value, "isEnableComment");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentLessThanOrEqualTo(Byte value) {
            addCriterion("is_enable_comment <=", value, "isEnableComment");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentIn(List<Byte> values) {
            addCriterion("is_enable_comment in", values, "isEnableComment");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentNotIn(List<Byte> values) {
            addCriterion("is_enable_comment not in", values, "isEnableComment");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentBetween(Byte value1, Byte value2) {
            addCriterion("is_enable_comment between", value1, value2, "isEnableComment");
            return (Criteria) this;
        }

        public Criteria andIsEnableCommentNotBetween(Byte value1, Byte value2) {
            addCriterion("is_enable_comment not between", value1, value2, "isEnableComment");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Long value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Long value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Long value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Long value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Long value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Long> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Long> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Long value1, Long value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Long value1, Long value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Long value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Long value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Long value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Long value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Long value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Long> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Long> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Long value1, Long value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Long value1, Long value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIsNull() {
            addCriterion("collection_num is null");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIsNotNull() {
            addCriterion("collection_num is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionNumEqualTo(Integer value) {
            addCriterion("collection_num =", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotEqualTo(Integer value) {
            addCriterion("collection_num <>", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumGreaterThan(Integer value) {
            addCriterion("collection_num >", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("collection_num >=", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumLessThan(Integer value) {
            addCriterion("collection_num <", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumLessThanOrEqualTo(Integer value) {
            addCriterion("collection_num <=", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIn(List<Integer> values) {
            addCriterion("collection_num in", values, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotIn(List<Integer> values) {
            addCriterion("collection_num not in", values, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumBetween(Integer value1, Integer value2) {
            addCriterion("collection_num between", value1, value2, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotBetween(Integer value1, Integer value2) {
            addCriterion("collection_num not between", value1, value2, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andShareNumIsNull() {
            addCriterion("share_num is null");
            return (Criteria) this;
        }

        public Criteria andShareNumIsNotNull() {
            addCriterion("share_num is not null");
            return (Criteria) this;
        }

        public Criteria andShareNumEqualTo(Integer value) {
            addCriterion("share_num =", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumNotEqualTo(Integer value) {
            addCriterion("share_num <>", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumGreaterThan(Integer value) {
            addCriterion("share_num >", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("share_num >=", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumLessThan(Integer value) {
            addCriterion("share_num <", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumLessThanOrEqualTo(Integer value) {
            addCriterion("share_num <=", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumIn(List<Integer> values) {
            addCriterion("share_num in", values, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumNotIn(List<Integer> values) {
            addCriterion("share_num not in", values, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumBetween(Integer value1, Integer value2) {
            addCriterion("share_num between", value1, value2, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumNotBetween(Integer value1, Integer value2) {
            addCriterion("share_num not between", value1, value2, "shareNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumIsNull() {
            addCriterion("comment_num is null");
            return (Criteria) this;
        }

        public Criteria andCommentNumIsNotNull() {
            addCriterion("comment_num is not null");
            return (Criteria) this;
        }

        public Criteria andCommentNumEqualTo(Integer value) {
            addCriterion("comment_num =", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotEqualTo(Integer value) {
            addCriterion("comment_num <>", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumGreaterThan(Integer value) {
            addCriterion("comment_num >", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_num >=", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumLessThan(Integer value) {
            addCriterion("comment_num <", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumLessThanOrEqualTo(Integer value) {
            addCriterion("comment_num <=", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumIn(List<Integer> values) {
            addCriterion("comment_num in", values, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotIn(List<Integer> values) {
            addCriterion("comment_num not in", values, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumBetween(Integer value1, Integer value2) {
            addCriterion("comment_num between", value1, value2, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_num not between", value1, value2, "commentNum");
            return (Criteria) this;
        }

        public Criteria andPraiseNumIsNull() {
            addCriterion("praise_num is null");
            return (Criteria) this;
        }

        public Criteria andPraiseNumIsNotNull() {
            addCriterion("praise_num is not null");
            return (Criteria) this;
        }

        public Criteria andPraiseNumEqualTo(Integer value) {
            addCriterion("praise_num =", value, "praiseNum");
            return (Criteria) this;
        }

        public Criteria andPraiseNumNotEqualTo(Integer value) {
            addCriterion("praise_num <>", value, "praiseNum");
            return (Criteria) this;
        }

        public Criteria andPraiseNumGreaterThan(Integer value) {
            addCriterion("praise_num >", value, "praiseNum");
            return (Criteria) this;
        }

        public Criteria andPraiseNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("praise_num >=", value, "praiseNum");
            return (Criteria) this;
        }

        public Criteria andPraiseNumLessThan(Integer value) {
            addCriterion("praise_num <", value, "praiseNum");
            return (Criteria) this;
        }

        public Criteria andPraiseNumLessThanOrEqualTo(Integer value) {
            addCriterion("praise_num <=", value, "praiseNum");
            return (Criteria) this;
        }

        public Criteria andPraiseNumIn(List<Integer> values) {
            addCriterion("praise_num in", values, "praiseNum");
            return (Criteria) this;
        }

        public Criteria andPraiseNumNotIn(List<Integer> values) {
            addCriterion("praise_num not in", values, "praiseNum");
            return (Criteria) this;
        }

        public Criteria andPraiseNumBetween(Integer value1, Integer value2) {
            addCriterion("praise_num between", value1, value2, "praiseNum");
            return (Criteria) this;
        }

        public Criteria andPraiseNumNotBetween(Integer value1, Integer value2) {
            addCriterion("praise_num not between", value1, value2, "praiseNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumIsNull() {
            addCriterion("join_num is null");
            return (Criteria) this;
        }

        public Criteria andJoinNumIsNotNull() {
            addCriterion("join_num is not null");
            return (Criteria) this;
        }

        public Criteria andJoinNumEqualTo(Integer value) {
            addCriterion("join_num =", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumNotEqualTo(Integer value) {
            addCriterion("join_num <>", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumGreaterThan(Integer value) {
            addCriterion("join_num >", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("join_num >=", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumLessThan(Integer value) {
            addCriterion("join_num <", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumLessThanOrEqualTo(Integer value) {
            addCriterion("join_num <=", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumIn(List<Integer> values) {
            addCriterion("join_num in", values, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumNotIn(List<Integer> values) {
            addCriterion("join_num not in", values, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumBetween(Integer value1, Integer value2) {
            addCriterion("join_num between", value1, value2, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumNotBetween(Integer value1, Integer value2) {
            addCriterion("join_num not between", value1, value2, "joinNum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}