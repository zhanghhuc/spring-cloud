package com.zssq.dao.pojo;

import java.util.ArrayList;
import java.util.List;

public class ForumPlatesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ForumPlatesExample() {
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

        public Criteria andForumPlatesCodeIsNull() {
            addCriterion("forum_plates_code is null");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeIsNotNull() {
            addCriterion("forum_plates_code is not null");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeEqualTo(String value) {
            addCriterion("forum_plates_code =", value, "forumPlatesCode");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeNotEqualTo(String value) {
            addCriterion("forum_plates_code <>", value, "forumPlatesCode");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeGreaterThan(String value) {
            addCriterion("forum_plates_code >", value, "forumPlatesCode");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeGreaterThanOrEqualTo(String value) {
            addCriterion("forum_plates_code >=", value, "forumPlatesCode");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeLessThan(String value) {
            addCriterion("forum_plates_code <", value, "forumPlatesCode");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeLessThanOrEqualTo(String value) {
            addCriterion("forum_plates_code <=", value, "forumPlatesCode");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeLike(String value) {
            addCriterion("forum_plates_code like", value, "forumPlatesCode");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeNotLike(String value) {
            addCriterion("forum_plates_code not like", value, "forumPlatesCode");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeIn(List<String> values) {
            addCriterion("forum_plates_code in", values, "forumPlatesCode");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeNotIn(List<String> values) {
            addCriterion("forum_plates_code not in", values, "forumPlatesCode");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeBetween(String value1, String value2) {
            addCriterion("forum_plates_code between", value1, value2, "forumPlatesCode");
            return (Criteria) this;
        }

        public Criteria andForumPlatesCodeNotBetween(String value1, String value2) {
            addCriterion("forum_plates_code not between", value1, value2, "forumPlatesCode");
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

        public Criteria andPlatesNameIsNull() {
            addCriterion("plates_name is null");
            return (Criteria) this;
        }

        public Criteria andPlatesNameIsNotNull() {
            addCriterion("plates_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlatesNameEqualTo(String value) {
            addCriterion("plates_name =", value, "platesName");
            return (Criteria) this;
        }

        public Criteria andPlatesNameNotEqualTo(String value) {
            addCriterion("plates_name <>", value, "platesName");
            return (Criteria) this;
        }

        public Criteria andPlatesNameGreaterThan(String value) {
            addCriterion("plates_name >", value, "platesName");
            return (Criteria) this;
        }

        public Criteria andPlatesNameGreaterThanOrEqualTo(String value) {
            addCriterion("plates_name >=", value, "platesName");
            return (Criteria) this;
        }

        public Criteria andPlatesNameLessThan(String value) {
            addCriterion("plates_name <", value, "platesName");
            return (Criteria) this;
        }

        public Criteria andPlatesNameLessThanOrEqualTo(String value) {
            addCriterion("plates_name <=", value, "platesName");
            return (Criteria) this;
        }

        public Criteria andPlatesNameLike(String value) {
            addCriterion("plates_name like", value, "platesName");
            return (Criteria) this;
        }

        public Criteria andPlatesNameNotLike(String value) {
            addCriterion("plates_name not like", value, "platesName");
            return (Criteria) this;
        }

        public Criteria andPlatesNameIn(List<String> values) {
            addCriterion("plates_name in", values, "platesName");
            return (Criteria) this;
        }

        public Criteria andPlatesNameNotIn(List<String> values) {
            addCriterion("plates_name not in", values, "platesName");
            return (Criteria) this;
        }

        public Criteria andPlatesNameBetween(String value1, String value2) {
            addCriterion("plates_name between", value1, value2, "platesName");
            return (Criteria) this;
        }

        public Criteria andPlatesNameNotBetween(String value1, String value2) {
            addCriterion("plates_name not between", value1, value2, "platesName");
            return (Criteria) this;
        }

        public Criteria andBelongCodeIsNull() {
            addCriterion("belong_code is null");
            return (Criteria) this;
        }

        public Criteria andBelongCodeIsNotNull() {
            addCriterion("belong_code is not null");
            return (Criteria) this;
        }

        public Criteria andBelongCodeEqualTo(String value) {
            addCriterion("belong_code =", value, "belongCode");
            return (Criteria) this;
        }

        public Criteria andBelongCodeNotEqualTo(String value) {
            addCriterion("belong_code <>", value, "belongCode");
            return (Criteria) this;
        }

        public Criteria andBelongCodeGreaterThan(String value) {
            addCriterion("belong_code >", value, "belongCode");
            return (Criteria) this;
        }

        public Criteria andBelongCodeGreaterThanOrEqualTo(String value) {
            addCriterion("belong_code >=", value, "belongCode");
            return (Criteria) this;
        }

        public Criteria andBelongCodeLessThan(String value) {
            addCriterion("belong_code <", value, "belongCode");
            return (Criteria) this;
        }

        public Criteria andBelongCodeLessThanOrEqualTo(String value) {
            addCriterion("belong_code <=", value, "belongCode");
            return (Criteria) this;
        }

        public Criteria andBelongCodeLike(String value) {
            addCriterion("belong_code like", value, "belongCode");
            return (Criteria) this;
        }

        public Criteria andBelongCodeNotLike(String value) {
            addCriterion("belong_code not like", value, "belongCode");
            return (Criteria) this;
        }

        public Criteria andBelongCodeIn(List<String> values) {
            addCriterion("belong_code in", values, "belongCode");
            return (Criteria) this;
        }

        public Criteria andBelongCodeNotIn(List<String> values) {
            addCriterion("belong_code not in", values, "belongCode");
            return (Criteria) this;
        }

        public Criteria andBelongCodeBetween(String value1, String value2) {
            addCriterion("belong_code between", value1, value2, "belongCode");
            return (Criteria) this;
        }

        public Criteria andBelongCodeNotBetween(String value1, String value2) {
            addCriterion("belong_code not between", value1, value2, "belongCode");
            return (Criteria) this;
        }

        public Criteria andAllSendIsNull() {
            addCriterion("all_send is null");
            return (Criteria) this;
        }

        public Criteria andAllSendIsNotNull() {
            addCriterion("all_send is not null");
            return (Criteria) this;
        }

        public Criteria andAllSendEqualTo(Byte value) {
            addCriterion("all_send =", value, "allSend");
            return (Criteria) this;
        }

        public Criteria andAllSendNotEqualTo(Byte value) {
            addCriterion("all_send <>", value, "allSend");
            return (Criteria) this;
        }

        public Criteria andAllSendGreaterThan(Byte value) {
            addCriterion("all_send >", value, "allSend");
            return (Criteria) this;
        }

        public Criteria andAllSendGreaterThanOrEqualTo(Byte value) {
            addCriterion("all_send >=", value, "allSend");
            return (Criteria) this;
        }

        public Criteria andAllSendLessThan(Byte value) {
            addCriterion("all_send <", value, "allSend");
            return (Criteria) this;
        }

        public Criteria andAllSendLessThanOrEqualTo(Byte value) {
            addCriterion("all_send <=", value, "allSend");
            return (Criteria) this;
        }

        public Criteria andAllSendIn(List<Byte> values) {
            addCriterion("all_send in", values, "allSend");
            return (Criteria) this;
        }

        public Criteria andAllSendNotIn(List<Byte> values) {
            addCriterion("all_send not in", values, "allSend");
            return (Criteria) this;
        }

        public Criteria andAllSendBetween(Byte value1, Byte value2) {
            addCriterion("all_send between", value1, value2, "allSend");
            return (Criteria) this;
        }

        public Criteria andAllSendNotBetween(Byte value1, Byte value2) {
            addCriterion("all_send not between", value1, value2, "allSend");
            return (Criteria) this;
        }

        public Criteria andAllReplyIsNull() {
            addCriterion("all_reply is null");
            return (Criteria) this;
        }

        public Criteria andAllReplyIsNotNull() {
            addCriterion("all_reply is not null");
            return (Criteria) this;
        }

        public Criteria andAllReplyEqualTo(Byte value) {
            addCriterion("all_reply =", value, "allReply");
            return (Criteria) this;
        }

        public Criteria andAllReplyNotEqualTo(Byte value) {
            addCriterion("all_reply <>", value, "allReply");
            return (Criteria) this;
        }

        public Criteria andAllReplyGreaterThan(Byte value) {
            addCriterion("all_reply >", value, "allReply");
            return (Criteria) this;
        }

        public Criteria andAllReplyGreaterThanOrEqualTo(Byte value) {
            addCriterion("all_reply >=", value, "allReply");
            return (Criteria) this;
        }

        public Criteria andAllReplyLessThan(Byte value) {
            addCriterion("all_reply <", value, "allReply");
            return (Criteria) this;
        }

        public Criteria andAllReplyLessThanOrEqualTo(Byte value) {
            addCriterion("all_reply <=", value, "allReply");
            return (Criteria) this;
        }

        public Criteria andAllReplyIn(List<Byte> values) {
            addCriterion("all_reply in", values, "allReply");
            return (Criteria) this;
        }

        public Criteria andAllReplyNotIn(List<Byte> values) {
            addCriterion("all_reply not in", values, "allReply");
            return (Criteria) this;
        }

        public Criteria andAllReplyBetween(Byte value1, Byte value2) {
            addCriterion("all_reply between", value1, value2, "allReply");
            return (Criteria) this;
        }

        public Criteria andAllReplyNotBetween(Byte value1, Byte value2) {
            addCriterion("all_reply not between", value1, value2, "allReply");
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