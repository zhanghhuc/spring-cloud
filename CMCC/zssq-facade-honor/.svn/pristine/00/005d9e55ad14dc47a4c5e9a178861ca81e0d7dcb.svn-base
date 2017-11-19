package com.zssq.dao.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HonorAwardRecordExample implements Serializable {
	
	private static final long serialVersionUID = -3845605570651328766L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HonorAwardRecordExample() {
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

        public Criteria andHonorAwardRecordCodeIsNull() {
            addCriterion("honor_award_record_code is null");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeIsNotNull() {
            addCriterion("honor_award_record_code is not null");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeEqualTo(String value) {
            addCriterion("honor_award_record_code =", value, "honorAwardRecordCode");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeNotEqualTo(String value) {
            addCriterion("honor_award_record_code <>", value, "honorAwardRecordCode");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeGreaterThan(String value) {
            addCriterion("honor_award_record_code >", value, "honorAwardRecordCode");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeGreaterThanOrEqualTo(String value) {
            addCriterion("honor_award_record_code >=", value, "honorAwardRecordCode");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeLessThan(String value) {
            addCriterion("honor_award_record_code <", value, "honorAwardRecordCode");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeLessThanOrEqualTo(String value) {
            addCriterion("honor_award_record_code <=", value, "honorAwardRecordCode");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeLike(String value) {
            addCriterion("honor_award_record_code like", value, "honorAwardRecordCode");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeNotLike(String value) {
            addCriterion("honor_award_record_code not like", value, "honorAwardRecordCode");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeIn(List<String> values) {
            addCriterion("honor_award_record_code in", values, "honorAwardRecordCode");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeNotIn(List<String> values) {
            addCriterion("honor_award_record_code not in", values, "honorAwardRecordCode");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeBetween(String value1, String value2) {
            addCriterion("honor_award_record_code between", value1, value2, "honorAwardRecordCode");
            return (Criteria) this;
        }

        public Criteria andHonorAwardRecordCodeNotBetween(String value1, String value2) {
            addCriterion("honor_award_record_code not between", value1, value2, "honorAwardRecordCode");
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

        public Criteria andHonorCodeIsNull() {
            addCriterion("honor_code is null");
            return (Criteria) this;
        }

        public Criteria andHonorCodeIsNotNull() {
            addCriterion("honor_code is not null");
            return (Criteria) this;
        }

        public Criteria andHonorCodeEqualTo(String value) {
            addCriterion("honor_code =", value, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorCodeNotEqualTo(String value) {
            addCriterion("honor_code <>", value, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorCodeGreaterThan(String value) {
            addCriterion("honor_code >", value, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("honor_code >=", value, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorCodeLessThan(String value) {
            addCriterion("honor_code <", value, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorCodeLessThanOrEqualTo(String value) {
            addCriterion("honor_code <=", value, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorCodeLike(String value) {
            addCriterion("honor_code like", value, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorCodeNotLike(String value) {
            addCriterion("honor_code not like", value, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorCodeIn(List<String> values) {
            addCriterion("honor_code in", values, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorCodeNotIn(List<String> values) {
            addCriterion("honor_code not in", values, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorCodeBetween(String value1, String value2) {
            addCriterion("honor_code between", value1, value2, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorCodeNotBetween(String value1, String value2) {
            addCriterion("honor_code not between", value1, value2, "honorCode");
            return (Criteria) this;
        }

        public Criteria andHonorNameIsNull() {
            addCriterion("honor_name is null");
            return (Criteria) this;
        }

        public Criteria andHonorNameIsNotNull() {
            addCriterion("honor_name is not null");
            return (Criteria) this;
        }

        public Criteria andHonorNameEqualTo(String value) {
            addCriterion("honor_name =", value, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorNameNotEqualTo(String value) {
            addCriterion("honor_name <>", value, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorNameGreaterThan(String value) {
            addCriterion("honor_name >", value, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorNameGreaterThanOrEqualTo(String value) {
            addCriterion("honor_name >=", value, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorNameLessThan(String value) {
            addCriterion("honor_name <", value, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorNameLessThanOrEqualTo(String value) {
            addCriterion("honor_name <=", value, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorNameLike(String value) {
            addCriterion("honor_name like", value, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorNameNotLike(String value) {
            addCriterion("honor_name not like", value, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorNameIn(List<String> values) {
            addCriterion("honor_name in", values, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorNameNotIn(List<String> values) {
            addCriterion("honor_name not in", values, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorNameBetween(String value1, String value2) {
            addCriterion("honor_name between", value1, value2, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorNameNotBetween(String value1, String value2) {
            addCriterion("honor_name not between", value1, value2, "honorName");
            return (Criteria) this;
        }

        public Criteria andHonorUrlIsNull() {
            addCriterion("honor_url is null");
            return (Criteria) this;
        }

        public Criteria andHonorUrlIsNotNull() {
            addCriterion("honor_url is not null");
            return (Criteria) this;
        }

        public Criteria andHonorUrlEqualTo(String value) {
            addCriterion("honor_url =", value, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andHonorUrlNotEqualTo(String value) {
            addCriterion("honor_url <>", value, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andHonorUrlGreaterThan(String value) {
            addCriterion("honor_url >", value, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andHonorUrlGreaterThanOrEqualTo(String value) {
            addCriterion("honor_url >=", value, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andHonorUrlLessThan(String value) {
            addCriterion("honor_url <", value, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andHonorUrlLessThanOrEqualTo(String value) {
            addCriterion("honor_url <=", value, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andHonorUrlLike(String value) {
            addCriterion("honor_url like", value, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andHonorUrlNotLike(String value) {
            addCriterion("honor_url not like", value, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andHonorUrlIn(List<String> values) {
            addCriterion("honor_url in", values, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andHonorUrlNotIn(List<String> values) {
            addCriterion("honor_url not in", values, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andHonorUrlBetween(String value1, String value2) {
            addCriterion("honor_url between", value1, value2, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andHonorUrlNotBetween(String value1, String value2) {
            addCriterion("honor_url not between", value1, value2, "honorUrl");
            return (Criteria) this;
        }

        public Criteria andAgentCodeIsNull() {
            addCriterion("agent_code is null");
            return (Criteria) this;
        }

        public Criteria andAgentCodeIsNotNull() {
            addCriterion("agent_code is not null");
            return (Criteria) this;
        }

        public Criteria andAgentCodeEqualTo(String value) {
            addCriterion("agent_code =", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeNotEqualTo(String value) {
            addCriterion("agent_code <>", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeGreaterThan(String value) {
            addCriterion("agent_code >", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeGreaterThanOrEqualTo(String value) {
            addCriterion("agent_code >=", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeLessThan(String value) {
            addCriterion("agent_code <", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeLessThanOrEqualTo(String value) {
            addCriterion("agent_code <=", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeLike(String value) {
            addCriterion("agent_code like", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeNotLike(String value) {
            addCriterion("agent_code not like", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeIn(List<String> values) {
            addCriterion("agent_code in", values, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeNotIn(List<String> values) {
            addCriterion("agent_code not in", values, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeBetween(String value1, String value2) {
            addCriterion("agent_code between", value1, value2, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeNotBetween(String value1, String value2) {
            addCriterion("agent_code not between", value1, value2, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeIsNull() {
            addCriterion("awarder_code is null");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeIsNotNull() {
            addCriterion("awarder_code is not null");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeEqualTo(String value) {
            addCriterion("awarder_code =", value, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeNotEqualTo(String value) {
            addCriterion("awarder_code <>", value, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeGreaterThan(String value) {
            addCriterion("awarder_code >", value, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("awarder_code >=", value, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeLessThan(String value) {
            addCriterion("awarder_code <", value, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeLessThanOrEqualTo(String value) {
            addCriterion("awarder_code <=", value, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeLike(String value) {
            addCriterion("awarder_code like", value, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeNotLike(String value) {
            addCriterion("awarder_code not like", value, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeIn(List<String> values) {
            addCriterion("awarder_code in", values, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeNotIn(List<String> values) {
            addCriterion("awarder_code not in", values, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeBetween(String value1, String value2) {
            addCriterion("awarder_code between", value1, value2, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderCodeNotBetween(String value1, String value2) {
            addCriterion("awarder_code not between", value1, value2, "awarderCode");
            return (Criteria) this;
        }

        public Criteria andAwarderNameIsNull() {
            addCriterion("awarder_name is null");
            return (Criteria) this;
        }

        public Criteria andAwarderNameIsNotNull() {
            addCriterion("awarder_name is not null");
            return (Criteria) this;
        }

        public Criteria andAwarderNameEqualTo(String value) {
            addCriterion("awarder_name =", value, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderNameNotEqualTo(String value) {
            addCriterion("awarder_name <>", value, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderNameGreaterThan(String value) {
            addCriterion("awarder_name >", value, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderNameGreaterThanOrEqualTo(String value) {
            addCriterion("awarder_name >=", value, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderNameLessThan(String value) {
            addCriterion("awarder_name <", value, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderNameLessThanOrEqualTo(String value) {
            addCriterion("awarder_name <=", value, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderNameLike(String value) {
            addCriterion("awarder_name like", value, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderNameNotLike(String value) {
            addCriterion("awarder_name not like", value, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderNameIn(List<String> values) {
            addCriterion("awarder_name in", values, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderNameNotIn(List<String> values) {
            addCriterion("awarder_name not in", values, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderNameBetween(String value1, String value2) {
            addCriterion("awarder_name between", value1, value2, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderNameNotBetween(String value1, String value2) {
            addCriterion("awarder_name not between", value1, value2, "awarderName");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionIsNull() {
            addCriterion("awarder_position is null");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionIsNotNull() {
            addCriterion("awarder_position is not null");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionEqualTo(String value) {
            addCriterion("awarder_position =", value, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionNotEqualTo(String value) {
            addCriterion("awarder_position <>", value, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionGreaterThan(String value) {
            addCriterion("awarder_position >", value, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionGreaterThanOrEqualTo(String value) {
            addCriterion("awarder_position >=", value, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionLessThan(String value) {
            addCriterion("awarder_position <", value, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionLessThanOrEqualTo(String value) {
            addCriterion("awarder_position <=", value, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionLike(String value) {
            addCriterion("awarder_position like", value, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionNotLike(String value) {
            addCriterion("awarder_position not like", value, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionIn(List<String> values) {
            addCriterion("awarder_position in", values, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionNotIn(List<String> values) {
            addCriterion("awarder_position not in", values, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionBetween(String value1, String value2) {
            addCriterion("awarder_position between", value1, value2, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andAwarderPositionNotBetween(String value1, String value2) {
            addCriterion("awarder_position not between", value1, value2, "awarderPosition");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeIsNull() {
            addCriterion("honoree_type is null");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeIsNotNull() {
            addCriterion("honoree_type is not null");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeEqualTo(Byte value) {
            addCriterion("honoree_type =", value, "honoreeType");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeNotEqualTo(Byte value) {
            addCriterion("honoree_type <>", value, "honoreeType");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeGreaterThan(Byte value) {
            addCriterion("honoree_type >", value, "honoreeType");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("honoree_type >=", value, "honoreeType");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeLessThan(Byte value) {
            addCriterion("honoree_type <", value, "honoreeType");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeLessThanOrEqualTo(Byte value) {
            addCriterion("honoree_type <=", value, "honoreeType");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeIn(List<Byte> values) {
            addCriterion("honoree_type in", values, "honoreeType");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeNotIn(List<Byte> values) {
            addCriterion("honoree_type not in", values, "honoreeType");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeBetween(Byte value1, Byte value2) {
            addCriterion("honoree_type between", value1, value2, "honoreeType");
            return (Criteria) this;
        }

        public Criteria andHonoreeTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("honoree_type not between", value1, value2, "honoreeType");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeIsNull() {
            addCriterion("honoree_code is null");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeIsNotNull() {
            addCriterion("honoree_code is not null");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeEqualTo(String value) {
            addCriterion("honoree_code =", value, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeNotEqualTo(String value) {
            addCriterion("honoree_code <>", value, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeGreaterThan(String value) {
            addCriterion("honoree_code >", value, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("honoree_code >=", value, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeLessThan(String value) {
            addCriterion("honoree_code <", value, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeLessThanOrEqualTo(String value) {
            addCriterion("honoree_code <=", value, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeLike(String value) {
            addCriterion("honoree_code like", value, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeNotLike(String value) {
            addCriterion("honoree_code not like", value, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeIn(List<String> values) {
            addCriterion("honoree_code in", values, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeNotIn(List<String> values) {
            addCriterion("honoree_code not in", values, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeBetween(String value1, String value2) {
            addCriterion("honoree_code between", value1, value2, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andHonoreeCodeNotBetween(String value1, String value2) {
            addCriterion("honoree_code not between", value1, value2, "honoreeCode");
            return (Criteria) this;
        }

        public Criteria andAwardReasonIsNull() {
            addCriterion("award_reason is null");
            return (Criteria) this;
        }

        public Criteria andAwardReasonIsNotNull() {
            addCriterion("award_reason is not null");
            return (Criteria) this;
        }

        public Criteria andAwardReasonEqualTo(String value) {
            addCriterion("award_reason =", value, "awardReason");
            return (Criteria) this;
        }

        public Criteria andAwardReasonNotEqualTo(String value) {
            addCriterion("award_reason <>", value, "awardReason");
            return (Criteria) this;
        }

        public Criteria andAwardReasonGreaterThan(String value) {
            addCriterion("award_reason >", value, "awardReason");
            return (Criteria) this;
        }

        public Criteria andAwardReasonGreaterThanOrEqualTo(String value) {
            addCriterion("award_reason >=", value, "awardReason");
            return (Criteria) this;
        }

        public Criteria andAwardReasonLessThan(String value) {
            addCriterion("award_reason <", value, "awardReason");
            return (Criteria) this;
        }

        public Criteria andAwardReasonLessThanOrEqualTo(String value) {
            addCriterion("award_reason <=", value, "awardReason");
            return (Criteria) this;
        }

        public Criteria andAwardReasonLike(String value) {
            addCriterion("award_reason like", value, "awardReason");
            return (Criteria) this;
        }

        public Criteria andAwardReasonNotLike(String value) {
            addCriterion("award_reason not like", value, "awardReason");
            return (Criteria) this;
        }

        public Criteria andAwardReasonIn(List<String> values) {
            addCriterion("award_reason in", values, "awardReason");
            return (Criteria) this;
        }

        public Criteria andAwardReasonNotIn(List<String> values) {
            addCriterion("award_reason not in", values, "awardReason");
            return (Criteria) this;
        }

        public Criteria andAwardReasonBetween(String value1, String value2) {
            addCriterion("award_reason between", value1, value2, "awardReason");
            return (Criteria) this;
        }

        public Criteria andAwardReasonNotBetween(String value1, String value2) {
            addCriterion("award_reason not between", value1, value2, "awardReason");
            return (Criteria) this;
        }

        public Criteria andIsRevokedIsNull() {
            addCriterion("is_revoked is null");
            return (Criteria) this;
        }

        public Criteria andIsRevokedIsNotNull() {
            addCriterion("is_revoked is not null");
            return (Criteria) this;
        }

        public Criteria andIsRevokedEqualTo(Byte value) {
            addCriterion("is_revoked =", value, "isRevoked");
            return (Criteria) this;
        }

        public Criteria andIsRevokedNotEqualTo(Byte value) {
            addCriterion("is_revoked <>", value, "isRevoked");
            return (Criteria) this;
        }

        public Criteria andIsRevokedGreaterThan(Byte value) {
            addCriterion("is_revoked >", value, "isRevoked");
            return (Criteria) this;
        }

        public Criteria andIsRevokedGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_revoked >=", value, "isRevoked");
            return (Criteria) this;
        }

        public Criteria andIsRevokedLessThan(Byte value) {
            addCriterion("is_revoked <", value, "isRevoked");
            return (Criteria) this;
        }

        public Criteria andIsRevokedLessThanOrEqualTo(Byte value) {
            addCriterion("is_revoked <=", value, "isRevoked");
            return (Criteria) this;
        }

        public Criteria andIsRevokedIn(List<Byte> values) {
            addCriterion("is_revoked in", values, "isRevoked");
            return (Criteria) this;
        }

        public Criteria andIsRevokedNotIn(List<Byte> values) {
            addCriterion("is_revoked not in", values, "isRevoked");
            return (Criteria) this;
        }

        public Criteria andIsRevokedBetween(Byte value1, Byte value2) {
            addCriterion("is_revoked between", value1, value2, "isRevoked");
            return (Criteria) this;
        }

        public Criteria andIsRevokedNotBetween(Byte value1, Byte value2) {
            addCriterion("is_revoked not between", value1, value2, "isRevoked");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeIsNull() {
            addCriterion("revoked_time is null");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeIsNotNull() {
            addCriterion("revoked_time is not null");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeEqualTo(Long value) {
            addCriterion("revoked_time =", value, "revokedTime");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeNotEqualTo(Long value) {
            addCriterion("revoked_time <>", value, "revokedTime");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeGreaterThan(Long value) {
            addCriterion("revoked_time >", value, "revokedTime");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("revoked_time >=", value, "revokedTime");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeLessThan(Long value) {
            addCriterion("revoked_time <", value, "revokedTime");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeLessThanOrEqualTo(Long value) {
            addCriterion("revoked_time <=", value, "revokedTime");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeIn(List<Long> values) {
            addCriterion("revoked_time in", values, "revokedTime");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeNotIn(List<Long> values) {
            addCriterion("revoked_time not in", values, "revokedTime");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeBetween(Long value1, Long value2) {
            addCriterion("revoked_time between", value1, value2, "revokedTime");
            return (Criteria) this;
        }

        public Criteria andRevokedTimeNotBetween(Long value1, Long value2) {
            addCriterion("revoked_time not between", value1, value2, "revokedTime");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNull() {
            addCriterion("comment_count is null");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNotNull() {
            addCriterion("comment_count is not null");
            return (Criteria) this;
        }

        public Criteria andCommentCountEqualTo(Integer value) {
            addCriterion("comment_count =", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotEqualTo(Integer value) {
            addCriterion("comment_count <>", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThan(Integer value) {
            addCriterion("comment_count >", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_count >=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThan(Integer value) {
            addCriterion("comment_count <", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThanOrEqualTo(Integer value) {
            addCriterion("comment_count <=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountIn(List<Integer> values) {
            addCriterion("comment_count in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotIn(List<Integer> values) {
            addCriterion("comment_count not in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountBetween(Integer value1, Integer value2) {
            addCriterion("comment_count between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_count not between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountIsNull() {
            addCriterion("praise_count is null");
            return (Criteria) this;
        }

        public Criteria andPraiseCountIsNotNull() {
            addCriterion("praise_count is not null");
            return (Criteria) this;
        }

        public Criteria andPraiseCountEqualTo(Integer value) {
            addCriterion("praise_count =", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountNotEqualTo(Integer value) {
            addCriterion("praise_count <>", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountGreaterThan(Integer value) {
            addCriterion("praise_count >", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("praise_count >=", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountLessThan(Integer value) {
            addCriterion("praise_count <", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountLessThanOrEqualTo(Integer value) {
            addCriterion("praise_count <=", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountIn(List<Integer> values) {
            addCriterion("praise_count in", values, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountNotIn(List<Integer> values) {
            addCriterion("praise_count not in", values, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountBetween(Integer value1, Integer value2) {
            addCriterion("praise_count between", value1, value2, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountNotBetween(Integer value1, Integer value2) {
            addCriterion("praise_count not between", value1, value2, "praiseCount");
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