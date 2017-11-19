package com.zssq.dao.pojo;

import java.util.ArrayList;
import java.util.List;

public class ActivityPrizeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ActivityPrizeExample() {
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
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
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

        public Criteria andActivityCodeIsNull() {
            addCriterion("activity_code is null");
            return (Criteria) this;
        }

        public Criteria andActivityCodeIsNotNull() {
            addCriterion("activity_code is not null");
            return (Criteria) this;
        }

        public Criteria andActivityCodeEqualTo(String value) {
            addCriterion("activity_code =", value, "activityCode");
            return (Criteria) this;
        }

        public Criteria andActivityCodeNotEqualTo(String value) {
            addCriterion("activity_code <>", value, "activityCode");
            return (Criteria) this;
        }

        public Criteria andActivityCodeGreaterThan(String value) {
            addCriterion("activity_code >", value, "activityCode");
            return (Criteria) this;
        }

        public Criteria andActivityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("activity_code >=", value, "activityCode");
            return (Criteria) this;
        }

        public Criteria andActivityCodeLessThan(String value) {
            addCriterion("activity_code <", value, "activityCode");
            return (Criteria) this;
        }

        public Criteria andActivityCodeLessThanOrEqualTo(String value) {
            addCriterion("activity_code <=", value, "activityCode");
            return (Criteria) this;
        }

        public Criteria andActivityCodeLike(String value) {
            addCriterion("activity_code like", value, "activityCode");
            return (Criteria) this;
        }

        public Criteria andActivityCodeNotLike(String value) {
            addCriterion("activity_code not like", value, "activityCode");
            return (Criteria) this;
        }

        public Criteria andActivityCodeIn(List<String> values) {
            addCriterion("activity_code in", values, "activityCode");
            return (Criteria) this;
        }

        public Criteria andActivityCodeNotIn(List<String> values) {
            addCriterion("activity_code not in", values, "activityCode");
            return (Criteria) this;
        }

        public Criteria andActivityCodeBetween(String value1, String value2) {
            addCriterion("activity_code between", value1, value2, "activityCode");
            return (Criteria) this;
        }

        public Criteria andActivityCodeNotBetween(String value1, String value2) {
            addCriterion("activity_code not between", value1, value2, "activityCode");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeIsNull() {
            addCriterion("is_join_prize is null");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeIsNotNull() {
            addCriterion("is_join_prize is not null");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeEqualTo(Byte value) {
            addCriterion("is_join_prize =", value, "isJoinPrize");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeNotEqualTo(Byte value) {
            addCriterion("is_join_prize <>", value, "isJoinPrize");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeGreaterThan(Byte value) {
            addCriterion("is_join_prize >", value, "isJoinPrize");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_join_prize >=", value, "isJoinPrize");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeLessThan(Byte value) {
            addCriterion("is_join_prize <", value, "isJoinPrize");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeLessThanOrEqualTo(Byte value) {
            addCriterion("is_join_prize <=", value, "isJoinPrize");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeIn(List<Byte> values) {
            addCriterion("is_join_prize in", values, "isJoinPrize");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeNotIn(List<Byte> values) {
            addCriterion("is_join_prize not in", values, "isJoinPrize");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeBetween(Byte value1, Byte value2) {
            addCriterion("is_join_prize between", value1, value2, "isJoinPrize");
            return (Criteria) this;
        }

        public Criteria andIsJoinPrizeNotBetween(Byte value1, Byte value2) {
            addCriterion("is_join_prize not between", value1, value2, "isJoinPrize");
            return (Criteria) this;
        }

        public Criteria andSequenceNoIsNull() {
            addCriterion("sequence_no is null");
            return (Criteria) this;
        }

        public Criteria andSequenceNoIsNotNull() {
            addCriterion("sequence_no is not null");
            return (Criteria) this;
        }

        public Criteria andSequenceNoEqualTo(Integer value) {
            addCriterion("sequence_no =", value, "sequenceNo");
            return (Criteria) this;
        }

        public Criteria andSequenceNoNotEqualTo(Integer value) {
            addCriterion("sequence_no <>", value, "sequenceNo");
            return (Criteria) this;
        }

        public Criteria andSequenceNoGreaterThan(Integer value) {
            addCriterion("sequence_no >", value, "sequenceNo");
            return (Criteria) this;
        }

        public Criteria andSequenceNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("sequence_no >=", value, "sequenceNo");
            return (Criteria) this;
        }

        public Criteria andSequenceNoLessThan(Integer value) {
            addCriterion("sequence_no <", value, "sequenceNo");
            return (Criteria) this;
        }

        public Criteria andSequenceNoLessThanOrEqualTo(Integer value) {
            addCriterion("sequence_no <=", value, "sequenceNo");
            return (Criteria) this;
        }

        public Criteria andSequenceNoIn(List<Integer> values) {
            addCriterion("sequence_no in", values, "sequenceNo");
            return (Criteria) this;
        }

        public Criteria andSequenceNoNotIn(List<Integer> values) {
            addCriterion("sequence_no not in", values, "sequenceNo");
            return (Criteria) this;
        }

        public Criteria andSequenceNoBetween(Integer value1, Integer value2) {
            addCriterion("sequence_no between", value1, value2, "sequenceNo");
            return (Criteria) this;
        }

        public Criteria andSequenceNoNotBetween(Integer value1, Integer value2) {
            addCriterion("sequence_no not between", value1, value2, "sequenceNo");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIsNull() {
            addCriterion("prize_name is null");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIsNotNull() {
            addCriterion("prize_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeNameEqualTo(String value) {
            addCriterion("prize_name =", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotEqualTo(String value) {
            addCriterion("prize_name <>", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameGreaterThan(String value) {
            addCriterion("prize_name >", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameGreaterThanOrEqualTo(String value) {
            addCriterion("prize_name >=", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLessThan(String value) {
            addCriterion("prize_name <", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLessThanOrEqualTo(String value) {
            addCriterion("prize_name <=", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLike(String value) {
            addCriterion("prize_name like", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotLike(String value) {
            addCriterion("prize_name not like", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIn(List<String> values) {
            addCriterion("prize_name in", values, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotIn(List<String> values) {
            addCriterion("prize_name not in", values, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameBetween(String value1, String value2) {
            addCriterion("prize_name between", value1, value2, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotBetween(String value1, String value2) {
            addCriterion("prize_name not between", value1, value2, "prizeName");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNull() {
            addCriterion("introduce is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("introduce is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("introduce =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("introduce <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("introduce >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("introduce >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("introduce <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("introduce <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("introduce like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("introduce not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("introduce in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("introduce not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("introduce between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("introduce not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldIsNull() {
            addCriterion("is_reward_gold is null");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldIsNotNull() {
            addCriterion("is_reward_gold is not null");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldEqualTo(Byte value) {
            addCriterion("is_reward_gold =", value, "isRewardGold");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldNotEqualTo(Byte value) {
            addCriterion("is_reward_gold <>", value, "isRewardGold");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldGreaterThan(Byte value) {
            addCriterion("is_reward_gold >", value, "isRewardGold");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_reward_gold >=", value, "isRewardGold");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldLessThan(Byte value) {
            addCriterion("is_reward_gold <", value, "isRewardGold");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldLessThanOrEqualTo(Byte value) {
            addCriterion("is_reward_gold <=", value, "isRewardGold");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldIn(List<Byte> values) {
            addCriterion("is_reward_gold in", values, "isRewardGold");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldNotIn(List<Byte> values) {
            addCriterion("is_reward_gold not in", values, "isRewardGold");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldBetween(Byte value1, Byte value2) {
            addCriterion("is_reward_gold between", value1, value2, "isRewardGold");
            return (Criteria) this;
        }

        public Criteria andIsRewardGoldNotBetween(Byte value1, Byte value2) {
            addCriterion("is_reward_gold not between", value1, value2, "isRewardGold");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumIsNull() {
            addCriterion("reward_gold_num is null");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumIsNotNull() {
            addCriterion("reward_gold_num is not null");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumEqualTo(Integer value) {
            addCriterion("reward_gold_num =", value, "rewardGoldNum");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumNotEqualTo(Integer value) {
            addCriterion("reward_gold_num <>", value, "rewardGoldNum");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumGreaterThan(Integer value) {
            addCriterion("reward_gold_num >", value, "rewardGoldNum");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("reward_gold_num >=", value, "rewardGoldNum");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumLessThan(Integer value) {
            addCriterion("reward_gold_num <", value, "rewardGoldNum");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumLessThanOrEqualTo(Integer value) {
            addCriterion("reward_gold_num <=", value, "rewardGoldNum");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumIn(List<Integer> values) {
            addCriterion("reward_gold_num in", values, "rewardGoldNum");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumNotIn(List<Integer> values) {
            addCriterion("reward_gold_num not in", values, "rewardGoldNum");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumBetween(Integer value1, Integer value2) {
            addCriterion("reward_gold_num between", value1, value2, "rewardGoldNum");
            return (Criteria) this;
        }

        public Criteria andRewardGoldNumNotBetween(Integer value1, Integer value2) {
            addCriterion("reward_gold_num not between", value1, value2, "rewardGoldNum");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorIsNull() {
            addCriterion("is_award_honor is null");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorIsNotNull() {
            addCriterion("is_award_honor is not null");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorEqualTo(Byte value) {
            addCriterion("is_award_honor =", value, "isAwardHonor");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorNotEqualTo(Byte value) {
            addCriterion("is_award_honor <>", value, "isAwardHonor");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorGreaterThan(Byte value) {
            addCriterion("is_award_honor >", value, "isAwardHonor");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_award_honor >=", value, "isAwardHonor");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorLessThan(Byte value) {
            addCriterion("is_award_honor <", value, "isAwardHonor");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorLessThanOrEqualTo(Byte value) {
            addCriterion("is_award_honor <=", value, "isAwardHonor");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorIn(List<Byte> values) {
            addCriterion("is_award_honor in", values, "isAwardHonor");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorNotIn(List<Byte> values) {
            addCriterion("is_award_honor not in", values, "isAwardHonor");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorBetween(Byte value1, Byte value2) {
            addCriterion("is_award_honor between", value1, value2, "isAwardHonor");
            return (Criteria) this;
        }

        public Criteria andIsAwardHonorNotBetween(Byte value1, Byte value2) {
            addCriterion("is_award_honor not between", value1, value2, "isAwardHonor");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeIsNull() {
            addCriterion("award_honor_code is null");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeIsNotNull() {
            addCriterion("award_honor_code is not null");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeEqualTo(String value) {
            addCriterion("award_honor_code =", value, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeNotEqualTo(String value) {
            addCriterion("award_honor_code <>", value, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeGreaterThan(String value) {
            addCriterion("award_honor_code >", value, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("award_honor_code >=", value, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeLessThan(String value) {
            addCriterion("award_honor_code <", value, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeLessThanOrEqualTo(String value) {
            addCriterion("award_honor_code <=", value, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeLike(String value) {
            addCriterion("award_honor_code like", value, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeNotLike(String value) {
            addCriterion("award_honor_code not like", value, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeIn(List<String> values) {
            addCriterion("award_honor_code in", values, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeNotIn(List<String> values) {
            addCriterion("award_honor_code not in", values, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeBetween(String value1, String value2) {
            addCriterion("award_honor_code between", value1, value2, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorCodeNotBetween(String value1, String value2) {
            addCriterion("award_honor_code not between", value1, value2, "awardHonorCode");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameIsNull() {
            addCriterion("award_honor_name is null");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameIsNotNull() {
            addCriterion("award_honor_name is not null");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameEqualTo(String value) {
            addCriterion("award_honor_name =", value, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameNotEqualTo(String value) {
            addCriterion("award_honor_name <>", value, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameGreaterThan(String value) {
            addCriterion("award_honor_name >", value, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameGreaterThanOrEqualTo(String value) {
            addCriterion("award_honor_name >=", value, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameLessThan(String value) {
            addCriterion("award_honor_name <", value, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameLessThanOrEqualTo(String value) {
            addCriterion("award_honor_name <=", value, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameLike(String value) {
            addCriterion("award_honor_name like", value, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameNotLike(String value) {
            addCriterion("award_honor_name not like", value, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameIn(List<String> values) {
            addCriterion("award_honor_name in", values, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameNotIn(List<String> values) {
            addCriterion("award_honor_name not in", values, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameBetween(String value1, String value2) {
            addCriterion("award_honor_name between", value1, value2, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorNameNotBetween(String value1, String value2) {
            addCriterion("award_honor_name not between", value1, value2, "awardHonorName");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlIsNull() {
            addCriterion("award_honor_url is null");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlIsNotNull() {
            addCriterion("award_honor_url is not null");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlEqualTo(String value) {
            addCriterion("award_honor_url =", value, "awardHonorUrl");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlNotEqualTo(String value) {
            addCriterion("award_honor_url <>", value, "awardHonorUrl");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlGreaterThan(String value) {
            addCriterion("award_honor_url >", value, "awardHonorUrl");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlGreaterThanOrEqualTo(String value) {
            addCriterion("award_honor_url >=", value, "awardHonorUrl");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlLessThan(String value) {
            addCriterion("award_honor_url <", value, "awardHonorUrl");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlLessThanOrEqualTo(String value) {
            addCriterion("award_honor_url <=", value, "awardHonorUrl");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlLike(String value) {
            addCriterion("award_honor_url like", value, "awardHonorUrl");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlNotLike(String value) {
            addCriterion("award_honor_url not like", value, "awardHonorUrl");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlIn(List<String> values) {
            addCriterion("award_honor_url in", values, "awardHonorUrl");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlNotIn(List<String> values) {
            addCriterion("award_honor_url not in", values, "awardHonorUrl");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlBetween(String value1, String value2) {
            addCriterion("award_honor_url between", value1, value2, "awardHonorUrl");
            return (Criteria) this;
        }

        public Criteria andAwardHonorUrlNotBetween(String value1, String value2) {
            addCriterion("award_honor_url not between", value1, value2, "awardHonorUrl");
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