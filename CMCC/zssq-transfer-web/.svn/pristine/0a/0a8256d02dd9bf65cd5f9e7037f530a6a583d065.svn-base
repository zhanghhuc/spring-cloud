package com.zssq.relation.pojo;

import java.util.ArrayList;
import java.util.List;

public class SysOrgInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public SysOrgInfoExample() {
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

        public Criteria andSysOrgCodeIsNull() {
            addCriterion("sys_org_code is null");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeIsNotNull() {
            addCriterion("sys_org_code is not null");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeEqualTo(String value) {
            addCriterion("sys_org_code =", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotEqualTo(String value) {
            addCriterion("sys_org_code <>", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeGreaterThan(String value) {
            addCriterion("sys_org_code >", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_org_code >=", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeLessThan(String value) {
            addCriterion("sys_org_code <", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("sys_org_code <=", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeLike(String value) {
            addCriterion("sys_org_code like", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotLike(String value) {
            addCriterion("sys_org_code not like", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeIn(List<String> values) {
            addCriterion("sys_org_code in", values, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotIn(List<String> values) {
            addCriterion("sys_org_code not in", values, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeBetween(String value1, String value2) {
            addCriterion("sys_org_code between", value1, value2, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotBetween(String value1, String value2) {
            addCriterion("sys_org_code not between", value1, value2, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeIsNull() {
            addCriterion("parent_code is null");
            return (Criteria) this;
        }

        public Criteria andParentCodeIsNotNull() {
            addCriterion("parent_code is not null");
            return (Criteria) this;
        }

        public Criteria andParentCodeEqualTo(String value) {
            addCriterion("parent_code =", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeNotEqualTo(String value) {
            addCriterion("parent_code <>", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeGreaterThan(String value) {
            addCriterion("parent_code >", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeGreaterThanOrEqualTo(String value) {
            addCriterion("parent_code >=", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeLessThan(String value) {
            addCriterion("parent_code <", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeLessThanOrEqualTo(String value) {
            addCriterion("parent_code <=", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeLike(String value) {
            addCriterion("parent_code like", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeNotLike(String value) {
            addCriterion("parent_code not like", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeIn(List<String> values) {
            addCriterion("parent_code in", values, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeNotIn(List<String> values) {
            addCriterion("parent_code not in", values, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeBetween(String value1, String value2) {
            addCriterion("parent_code between", value1, value2, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeNotBetween(String value1, String value2) {
            addCriterion("parent_code not between", value1, value2, "parentCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeIsNull() {
            addCriterion("man_org_code is null");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeIsNotNull() {
            addCriterion("man_org_code is not null");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeEqualTo(String value) {
            addCriterion("man_org_code =", value, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeNotEqualTo(String value) {
            addCriterion("man_org_code <>", value, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeGreaterThan(String value) {
            addCriterion("man_org_code >", value, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("man_org_code >=", value, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeLessThan(String value) {
            addCriterion("man_org_code <", value, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("man_org_code <=", value, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeLike(String value) {
            addCriterion("man_org_code like", value, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeNotLike(String value) {
            addCriterion("man_org_code not like", value, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeIn(List<String> values) {
            addCriterion("man_org_code in", values, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeNotIn(List<String> values) {
            addCriterion("man_org_code not in", values, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeBetween(String value1, String value2) {
            addCriterion("man_org_code between", value1, value2, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andManOrgCodeNotBetween(String value1, String value2) {
            addCriterion("man_org_code not between", value1, value2, "manOrgCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeIsNull() {
            addCriterion("src_code is null");
            return (Criteria) this;
        }

        public Criteria andSrcCodeIsNotNull() {
            addCriterion("src_code is not null");
            return (Criteria) this;
        }

        public Criteria andSrcCodeEqualTo(String value) {
            addCriterion("src_code =", value, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeNotEqualTo(String value) {
            addCriterion("src_code <>", value, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeGreaterThan(String value) {
            addCriterion("src_code >", value, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeGreaterThanOrEqualTo(String value) {
            addCriterion("src_code >=", value, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeLessThan(String value) {
            addCriterion("src_code <", value, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeLessThanOrEqualTo(String value) {
            addCriterion("src_code <=", value, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeLike(String value) {
            addCriterion("src_code like", value, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeNotLike(String value) {
            addCriterion("src_code not like", value, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeIn(List<String> values) {
            addCriterion("src_code in", values, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeNotIn(List<String> values) {
            addCriterion("src_code not in", values, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeBetween(String value1, String value2) {
            addCriterion("src_code between", value1, value2, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSrcCodeNotBetween(String value1, String value2) {
            addCriterion("src_code not between", value1, value2, "srcCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameIsNull() {
            addCriterion("sys_org_name is null");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameIsNotNull() {
            addCriterion("sys_org_name is not null");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameEqualTo(String value) {
            addCriterion("sys_org_name =", value, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameNotEqualTo(String value) {
            addCriterion("sys_org_name <>", value, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameGreaterThan(String value) {
            addCriterion("sys_org_name >", value, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("sys_org_name >=", value, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameLessThan(String value) {
            addCriterion("sys_org_name <", value, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameLessThanOrEqualTo(String value) {
            addCriterion("sys_org_name <=", value, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameLike(String value) {
            addCriterion("sys_org_name like", value, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameNotLike(String value) {
            addCriterion("sys_org_name not like", value, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameIn(List<String> values) {
            addCriterion("sys_org_name in", values, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameNotIn(List<String> values) {
            addCriterion("sys_org_name not in", values, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameBetween(String value1, String value2) {
            addCriterion("sys_org_name between", value1, value2, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgNameNotBetween(String value1, String value2) {
            addCriterion("sys_org_name not between", value1, value2, "sysOrgName");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameIsNull() {
            addCriterion("sys_org_fullname is null");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameIsNotNull() {
            addCriterion("sys_org_fullname is not null");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameEqualTo(String value) {
            addCriterion("sys_org_fullname =", value, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameNotEqualTo(String value) {
            addCriterion("sys_org_fullname <>", value, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameGreaterThan(String value) {
            addCriterion("sys_org_fullname >", value, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameGreaterThanOrEqualTo(String value) {
            addCriterion("sys_org_fullname >=", value, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameLessThan(String value) {
            addCriterion("sys_org_fullname <", value, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameLessThanOrEqualTo(String value) {
            addCriterion("sys_org_fullname <=", value, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameLike(String value) {
            addCriterion("sys_org_fullname like", value, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameNotLike(String value) {
            addCriterion("sys_org_fullname not like", value, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameIn(List<String> values) {
            addCriterion("sys_org_fullname in", values, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameNotIn(List<String> values) {
            addCriterion("sys_org_fullname not in", values, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameBetween(String value1, String value2) {
            addCriterion("sys_org_fullname between", value1, value2, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgFullnameNotBetween(String value1, String value2) {
            addCriterion("sys_org_fullname not between", value1, value2, "sysOrgFullname");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeIsNull() {
            addCriterion("sys_org_type is null");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeIsNotNull() {
            addCriterion("sys_org_type is not null");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeEqualTo(String value) {
            addCriterion("sys_org_type =", value, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeNotEqualTo(String value) {
            addCriterion("sys_org_type <>", value, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeGreaterThan(String value) {
            addCriterion("sys_org_type >", value, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_org_type >=", value, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeLessThan(String value) {
            addCriterion("sys_org_type <", value, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeLessThanOrEqualTo(String value) {
            addCriterion("sys_org_type <=", value, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeLike(String value) {
            addCriterion("sys_org_type like", value, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeNotLike(String value) {
            addCriterion("sys_org_type not like", value, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeIn(List<String> values) {
            addCriterion("sys_org_type in", values, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeNotIn(List<String> values) {
            addCriterion("sys_org_type not in", values, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeBetween(String value1, String value2) {
            addCriterion("sys_org_type between", value1, value2, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgTypeNotBetween(String value1, String value2) {
            addCriterion("sys_org_type not between", value1, value2, "sysOrgType");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderIsNull() {
            addCriterion("sys_org_order is null");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderIsNotNull() {
            addCriterion("sys_org_order is not null");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderEqualTo(String value) {
            addCriterion("sys_org_order =", value, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderNotEqualTo(String value) {
            addCriterion("sys_org_order <>", value, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderGreaterThan(String value) {
            addCriterion("sys_org_order >", value, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderGreaterThanOrEqualTo(String value) {
            addCriterion("sys_org_order >=", value, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderLessThan(String value) {
            addCriterion("sys_org_order <", value, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderLessThanOrEqualTo(String value) {
            addCriterion("sys_org_order <=", value, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderLike(String value) {
            addCriterion("sys_org_order like", value, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderNotLike(String value) {
            addCriterion("sys_org_order not like", value, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderIn(List<String> values) {
            addCriterion("sys_org_order in", values, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderNotIn(List<String> values) {
            addCriterion("sys_org_order not in", values, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderBetween(String value1, String value2) {
            addCriterion("sys_org_order between", value1, value2, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andSysOrgOrderNotBetween(String value1, String value2) {
            addCriterion("sys_org_order not between", value1, value2, "sysOrgOrder");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNull() {
            addCriterion("is_enable is null");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNotNull() {
            addCriterion("is_enable is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnableEqualTo(Byte value) {
            addCriterion("is_enable =", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotEqualTo(Byte value) {
            addCriterion("is_enable <>", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThan(Byte value) {
            addCriterion("is_enable >", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_enable >=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThan(Byte value) {
            addCriterion("is_enable <", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThanOrEqualTo(Byte value) {
            addCriterion("is_enable <=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableIn(List<Byte> values) {
            addCriterion("is_enable in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotIn(List<Byte> values) {
            addCriterion("is_enable not in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableBetween(Byte value1, Byte value2) {
            addCriterion("is_enable between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotBetween(Byte value1, Byte value2) {
            addCriterion("is_enable not between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeIsNull() {
            addCriterion("saas_tenant_code is null");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeIsNotNull() {
            addCriterion("saas_tenant_code is not null");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeEqualTo(String value) {
            addCriterion("saas_tenant_code =", value, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeNotEqualTo(String value) {
            addCriterion("saas_tenant_code <>", value, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeGreaterThan(String value) {
            addCriterion("saas_tenant_code >", value, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("saas_tenant_code >=", value, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeLessThan(String value) {
            addCriterion("saas_tenant_code <", value, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeLessThanOrEqualTo(String value) {
            addCriterion("saas_tenant_code <=", value, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeLike(String value) {
            addCriterion("saas_tenant_code like", value, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeNotLike(String value) {
            addCriterion("saas_tenant_code not like", value, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeIn(List<String> values) {
            addCriterion("saas_tenant_code in", values, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeNotIn(List<String> values) {
            addCriterion("saas_tenant_code not in", values, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeBetween(String value1, String value2) {
            addCriterion("saas_tenant_code between", value1, value2, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andSaasTenantCodeNotBetween(String value1, String value2) {
            addCriterion("saas_tenant_code not between", value1, value2, "saasTenantCode");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIsNull() {
            addCriterion("org_status is null");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIsNotNull() {
            addCriterion("org_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrgStatusEqualTo(Byte value) {
            addCriterion("org_status =", value, "orgStatus");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNotEqualTo(Byte value) {
            addCriterion("org_status <>", value, "orgStatus");
            return (Criteria) this;
        }

        public Criteria andOrgStatusGreaterThan(Byte value) {
            addCriterion("org_status >", value, "orgStatus");
            return (Criteria) this;
        }

        public Criteria andOrgStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("org_status >=", value, "orgStatus");
            return (Criteria) this;
        }

        public Criteria andOrgStatusLessThan(Byte value) {
            addCriterion("org_status <", value, "orgStatus");
            return (Criteria) this;
        }

        public Criteria andOrgStatusLessThanOrEqualTo(Byte value) {
            addCriterion("org_status <=", value, "orgStatus");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIn(List<Byte> values) {
            addCriterion("org_status in", values, "orgStatus");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNotIn(List<Byte> values) {
            addCriterion("org_status not in", values, "orgStatus");
            return (Criteria) this;
        }

        public Criteria andOrgStatusBetween(Byte value1, Byte value2) {
            addCriterion("org_status between", value1, value2, "orgStatus");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("org_status not between", value1, value2, "orgStatus");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("org_id is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("org_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(Integer value) {
            addCriterion("org_id =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(Integer value) {
            addCriterion("org_id <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(Integer value) {
            addCriterion("org_id >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("org_id >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(Integer value) {
            addCriterion("org_id <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("org_id <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<Integer> values) {
            addCriterion("org_id in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<Integer> values) {
            addCriterion("org_id not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("org_id between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("org_id not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeIsNull() {
            addCriterion("kc_parent_code is null");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeIsNotNull() {
            addCriterion("kc_parent_code is not null");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeEqualTo(String value) {
            addCriterion("kc_parent_code =", value, "kcParentCode");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeNotEqualTo(String value) {
            addCriterion("kc_parent_code <>", value, "kcParentCode");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeGreaterThan(String value) {
            addCriterion("kc_parent_code >", value, "kcParentCode");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeGreaterThanOrEqualTo(String value) {
            addCriterion("kc_parent_code >=", value, "kcParentCode");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeLessThan(String value) {
            addCriterion("kc_parent_code <", value, "kcParentCode");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeLessThanOrEqualTo(String value) {
            addCriterion("kc_parent_code <=", value, "kcParentCode");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeLike(String value) {
            addCriterion("kc_parent_code like", value, "kcParentCode");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeNotLike(String value) {
            addCriterion("kc_parent_code not like", value, "kcParentCode");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeIn(List<String> values) {
            addCriterion("kc_parent_code in", values, "kcParentCode");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeNotIn(List<String> values) {
            addCriterion("kc_parent_code not in", values, "kcParentCode");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeBetween(String value1, String value2) {
            addCriterion("kc_parent_code between", value1, value2, "kcParentCode");
            return (Criteria) this;
        }

        public Criteria andKcParentCodeNotBetween(String value1, String value2) {
            addCriterion("kc_parent_code not between", value1, value2, "kcParentCode");
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