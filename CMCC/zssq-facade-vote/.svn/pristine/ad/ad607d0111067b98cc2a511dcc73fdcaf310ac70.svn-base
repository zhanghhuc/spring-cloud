package com.zssq.dao.pojo;

import java.util.ArrayList;
import java.util.List;

public class VoteJoinAuthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public VoteJoinAuthExample() {
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

        public Criteria andVoteInfoCodeIsNull() {
            addCriterion("vote_info_code is null");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeIsNotNull() {
            addCriterion("vote_info_code is not null");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeEqualTo(String value) {
            addCriterion("vote_info_code =", value, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeNotEqualTo(String value) {
            addCriterion("vote_info_code <>", value, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeGreaterThan(String value) {
            addCriterion("vote_info_code >", value, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeGreaterThanOrEqualTo(String value) {
            addCriterion("vote_info_code >=", value, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeLessThan(String value) {
            addCriterion("vote_info_code <", value, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeLessThanOrEqualTo(String value) {
            addCriterion("vote_info_code <=", value, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeLike(String value) {
            addCriterion("vote_info_code like", value, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeNotLike(String value) {
            addCriterion("vote_info_code not like", value, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeIn(List<String> values) {
            addCriterion("vote_info_code in", values, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeNotIn(List<String> values) {
            addCriterion("vote_info_code not in", values, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeBetween(String value1, String value2) {
            addCriterion("vote_info_code between", value1, value2, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andVoteInfoCodeNotBetween(String value1, String value2) {
            addCriterion("vote_info_code not between", value1, value2, "voteInfoCode");
            return (Criteria) this;
        }

        public Criteria andRangeTypeIsNull() {
            addCriterion("range_type is null");
            return (Criteria) this;
        }

        public Criteria andRangeTypeIsNotNull() {
            addCriterion("range_type is not null");
            return (Criteria) this;
        }

        public Criteria andRangeTypeEqualTo(Byte value) {
            addCriterion("range_type =", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeNotEqualTo(Byte value) {
            addCriterion("range_type <>", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeGreaterThan(Byte value) {
            addCriterion("range_type >", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("range_type >=", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeLessThan(Byte value) {
            addCriterion("range_type <", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeLessThanOrEqualTo(Byte value) {
            addCriterion("range_type <=", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeIn(List<Byte> values) {
            addCriterion("range_type in", values, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeNotIn(List<Byte> values) {
            addCriterion("range_type not in", values, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeBetween(Byte value1, Byte value2) {
            addCriterion("range_type between", value1, value2, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("range_type not between", value1, value2, "rangeType");
            return (Criteria) this;
        }

        public Criteria andIsCascadeIsNull() {
            addCriterion("is_cascade is null");
            return (Criteria) this;
        }

        public Criteria andIsCascadeIsNotNull() {
            addCriterion("is_cascade is not null");
            return (Criteria) this;
        }

        public Criteria andIsCascadeEqualTo(Byte value) {
            addCriterion("is_cascade =", value, "isCascade");
            return (Criteria) this;
        }

        public Criteria andIsCascadeNotEqualTo(Byte value) {
            addCriterion("is_cascade <>", value, "isCascade");
            return (Criteria) this;
        }

        public Criteria andIsCascadeGreaterThan(Byte value) {
            addCriterion("is_cascade >", value, "isCascade");
            return (Criteria) this;
        }

        public Criteria andIsCascadeGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_cascade >=", value, "isCascade");
            return (Criteria) this;
        }

        public Criteria andIsCascadeLessThan(Byte value) {
            addCriterion("is_cascade <", value, "isCascade");
            return (Criteria) this;
        }

        public Criteria andIsCascadeLessThanOrEqualTo(Byte value) {
            addCriterion("is_cascade <=", value, "isCascade");
            return (Criteria) this;
        }

        public Criteria andIsCascadeIn(List<Byte> values) {
            addCriterion("is_cascade in", values, "isCascade");
            return (Criteria) this;
        }

        public Criteria andIsCascadeNotIn(List<Byte> values) {
            addCriterion("is_cascade not in", values, "isCascade");
            return (Criteria) this;
        }

        public Criteria andIsCascadeBetween(Byte value1, Byte value2) {
            addCriterion("is_cascade between", value1, value2, "isCascade");
            return (Criteria) this;
        }

        public Criteria andIsCascadeNotBetween(Byte value1, Byte value2) {
            addCriterion("is_cascade not between", value1, value2, "isCascade");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeIsNull() {
            addCriterion("g_range_code is null");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeIsNotNull() {
            addCriterion("g_range_code is not null");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeEqualTo(String value) {
            addCriterion("g_range_code =", value, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeNotEqualTo(String value) {
            addCriterion("g_range_code <>", value, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeGreaterThan(String value) {
            addCriterion("g_range_code >", value, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("g_range_code >=", value, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeLessThan(String value) {
            addCriterion("g_range_code <", value, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeLessThanOrEqualTo(String value) {
            addCriterion("g_range_code <=", value, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeLike(String value) {
            addCriterion("g_range_code like", value, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeNotLike(String value) {
            addCriterion("g_range_code not like", value, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeIn(List<String> values) {
            addCriterion("g_range_code in", values, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeNotIn(List<String> values) {
            addCriterion("g_range_code not in", values, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeBetween(String value1, String value2) {
            addCriterion("g_range_code between", value1, value2, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeCodeNotBetween(String value1, String value2) {
            addCriterion("g_range_code not between", value1, value2, "gRangeCode");
            return (Criteria) this;
        }

        public Criteria andGRangeNameIsNull() {
            addCriterion("g_range_name is null");
            return (Criteria) this;
        }

        public Criteria andGRangeNameIsNotNull() {
            addCriterion("g_range_name is not null");
            return (Criteria) this;
        }

        public Criteria andGRangeNameEqualTo(String value) {
            addCriterion("g_range_name =", value, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andGRangeNameNotEqualTo(String value) {
            addCriterion("g_range_name <>", value, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andGRangeNameGreaterThan(String value) {
            addCriterion("g_range_name >", value, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andGRangeNameGreaterThanOrEqualTo(String value) {
            addCriterion("g_range_name >=", value, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andGRangeNameLessThan(String value) {
            addCriterion("g_range_name <", value, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andGRangeNameLessThanOrEqualTo(String value) {
            addCriterion("g_range_name <=", value, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andGRangeNameLike(String value) {
            addCriterion("g_range_name like", value, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andGRangeNameNotLike(String value) {
            addCriterion("g_range_name not like", value, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andGRangeNameIn(List<String> values) {
            addCriterion("g_range_name in", values, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andGRangeNameNotIn(List<String> values) {
            addCriterion("g_range_name not in", values, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andGRangeNameBetween(String value1, String value2) {
            addCriterion("g_range_name between", value1, value2, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andGRangeNameNotBetween(String value1, String value2) {
            addCriterion("g_range_name not between", value1, value2, "gRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeIsNull() {
            addCriterion("p_range_code is null");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeIsNotNull() {
            addCriterion("p_range_code is not null");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeEqualTo(String value) {
            addCriterion("p_range_code =", value, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeNotEqualTo(String value) {
            addCriterion("p_range_code <>", value, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeGreaterThan(String value) {
            addCriterion("p_range_code >", value, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("p_range_code >=", value, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeLessThan(String value) {
            addCriterion("p_range_code <", value, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeLessThanOrEqualTo(String value) {
            addCriterion("p_range_code <=", value, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeLike(String value) {
            addCriterion("p_range_code like", value, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeNotLike(String value) {
            addCriterion("p_range_code not like", value, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeIn(List<String> values) {
            addCriterion("p_range_code in", values, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeNotIn(List<String> values) {
            addCriterion("p_range_code not in", values, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeBetween(String value1, String value2) {
            addCriterion("p_range_code between", value1, value2, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeCodeNotBetween(String value1, String value2) {
            addCriterion("p_range_code not between", value1, value2, "pRangeCode");
            return (Criteria) this;
        }

        public Criteria andPRangeNameIsNull() {
            addCriterion("p_range_name is null");
            return (Criteria) this;
        }

        public Criteria andPRangeNameIsNotNull() {
            addCriterion("p_range_name is not null");
            return (Criteria) this;
        }

        public Criteria andPRangeNameEqualTo(String value) {
            addCriterion("p_range_name =", value, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeNameNotEqualTo(String value) {
            addCriterion("p_range_name <>", value, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeNameGreaterThan(String value) {
            addCriterion("p_range_name >", value, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeNameGreaterThanOrEqualTo(String value) {
            addCriterion("p_range_name >=", value, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeNameLessThan(String value) {
            addCriterion("p_range_name <", value, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeNameLessThanOrEqualTo(String value) {
            addCriterion("p_range_name <=", value, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeNameLike(String value) {
            addCriterion("p_range_name like", value, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeNameNotLike(String value) {
            addCriterion("p_range_name not like", value, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeNameIn(List<String> values) {
            addCriterion("p_range_name in", values, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeNameNotIn(List<String> values) {
            addCriterion("p_range_name not in", values, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeNameBetween(String value1, String value2) {
            addCriterion("p_range_name between", value1, value2, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andPRangeNameNotBetween(String value1, String value2) {
            addCriterion("p_range_name not between", value1, value2, "pRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeIsNull() {
            addCriterion("c_range_code is null");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeIsNotNull() {
            addCriterion("c_range_code is not null");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeEqualTo(String value) {
            addCriterion("c_range_code =", value, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeNotEqualTo(String value) {
            addCriterion("c_range_code <>", value, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeGreaterThan(String value) {
            addCriterion("c_range_code >", value, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("c_range_code >=", value, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeLessThan(String value) {
            addCriterion("c_range_code <", value, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeLessThanOrEqualTo(String value) {
            addCriterion("c_range_code <=", value, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeLike(String value) {
            addCriterion("c_range_code like", value, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeNotLike(String value) {
            addCriterion("c_range_code not like", value, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeIn(List<String> values) {
            addCriterion("c_range_code in", values, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeNotIn(List<String> values) {
            addCriterion("c_range_code not in", values, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeBetween(String value1, String value2) {
            addCriterion("c_range_code between", value1, value2, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeCodeNotBetween(String value1, String value2) {
            addCriterion("c_range_code not between", value1, value2, "cRangeCode");
            return (Criteria) this;
        }

        public Criteria andCRangeNameIsNull() {
            addCriterion("c_range_name is null");
            return (Criteria) this;
        }

        public Criteria andCRangeNameIsNotNull() {
            addCriterion("c_range_name is not null");
            return (Criteria) this;
        }

        public Criteria andCRangeNameEqualTo(String value) {
            addCriterion("c_range_name =", value, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeNameNotEqualTo(String value) {
            addCriterion("c_range_name <>", value, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeNameGreaterThan(String value) {
            addCriterion("c_range_name >", value, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeNameGreaterThanOrEqualTo(String value) {
            addCriterion("c_range_name >=", value, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeNameLessThan(String value) {
            addCriterion("c_range_name <", value, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeNameLessThanOrEqualTo(String value) {
            addCriterion("c_range_name <=", value, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeNameLike(String value) {
            addCriterion("c_range_name like", value, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeNameNotLike(String value) {
            addCriterion("c_range_name not like", value, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeNameIn(List<String> values) {
            addCriterion("c_range_name in", values, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeNameNotIn(List<String> values) {
            addCriterion("c_range_name not in", values, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeNameBetween(String value1, String value2) {
            addCriterion("c_range_name between", value1, value2, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andCRangeNameNotBetween(String value1, String value2) {
            addCriterion("c_range_name not between", value1, value2, "cRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeIsNull() {
            addCriterion("t_range_code is null");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeIsNotNull() {
            addCriterion("t_range_code is not null");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeEqualTo(String value) {
            addCriterion("t_range_code =", value, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeNotEqualTo(String value) {
            addCriterion("t_range_code <>", value, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeGreaterThan(String value) {
            addCriterion("t_range_code >", value, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("t_range_code >=", value, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeLessThan(String value) {
            addCriterion("t_range_code <", value, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeLessThanOrEqualTo(String value) {
            addCriterion("t_range_code <=", value, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeLike(String value) {
            addCriterion("t_range_code like", value, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeNotLike(String value) {
            addCriterion("t_range_code not like", value, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeIn(List<String> values) {
            addCriterion("t_range_code in", values, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeNotIn(List<String> values) {
            addCriterion("t_range_code not in", values, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeBetween(String value1, String value2) {
            addCriterion("t_range_code between", value1, value2, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeCodeNotBetween(String value1, String value2) {
            addCriterion("t_range_code not between", value1, value2, "tRangeCode");
            return (Criteria) this;
        }

        public Criteria andTRangeNameIsNull() {
            addCriterion("t_range_name is null");
            return (Criteria) this;
        }

        public Criteria andTRangeNameIsNotNull() {
            addCriterion("t_range_name is not null");
            return (Criteria) this;
        }

        public Criteria andTRangeNameEqualTo(String value) {
            addCriterion("t_range_name =", value, "tRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeNameNotEqualTo(String value) {
            addCriterion("t_range_name <>", value, "tRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeNameGreaterThan(String value) {
            addCriterion("t_range_name >", value, "tRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeNameGreaterThanOrEqualTo(String value) {
            addCriterion("t_range_name >=", value, "tRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeNameLessThan(String value) {
            addCriterion("t_range_name <", value, "tRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeNameLessThanOrEqualTo(String value) {
            addCriterion("t_range_name <=", value, "tRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeNameLike(String value) {
            addCriterion("t_range_name like", value, "tRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeNameNotLike(String value) {
            addCriterion("t_range_name not like", value, "tRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeNameIn(List<String> values) {
            addCriterion("t_range_name in", values, "tRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeNameNotIn(List<String> values) {
            addCriterion("t_range_name not in", values, "tRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeNameBetween(String value1, String value2) {
            addCriterion("t_range_name between", value1, value2, "tRangeName");
            return (Criteria) this;
        }

        public Criteria andTRangeNameNotBetween(String value1, String value2) {
            addCriterion("t_range_name not between", value1, value2, "tRangeName");
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