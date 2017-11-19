package com.zssq.news.po;

import java.util.ArrayList;
import java.util.List;

public class NewsInfoUploadExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public NewsInfoUploadExample() {
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

        public Criteria andInfoUploadCodeIsNull() {
            addCriterion("info_upload_code is null");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeIsNotNull() {
            addCriterion("info_upload_code is not null");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeEqualTo(String value) {
            addCriterion("info_upload_code =", value, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeNotEqualTo(String value) {
            addCriterion("info_upload_code <>", value, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeGreaterThan(String value) {
            addCriterion("info_upload_code >", value, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeGreaterThanOrEqualTo(String value) {
            addCriterion("info_upload_code >=", value, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeLessThan(String value) {
            addCriterion("info_upload_code <", value, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeLessThanOrEqualTo(String value) {
            addCriterion("info_upload_code <=", value, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeLike(String value) {
            addCriterion("info_upload_code like", value, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeNotLike(String value) {
            addCriterion("info_upload_code not like", value, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeIn(List<String> values) {
            addCriterion("info_upload_code in", values, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeNotIn(List<String> values) {
            addCriterion("info_upload_code not in", values, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeBetween(String value1, String value2) {
            addCriterion("info_upload_code between", value1, value2, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andInfoUploadCodeNotBetween(String value1, String value2) {
            addCriterion("info_upload_code not between", value1, value2, "infoUploadCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeIsNull() {
            addCriterion("news_code is null");
            return (Criteria) this;
        }

        public Criteria andNewsCodeIsNotNull() {
            addCriterion("news_code is not null");
            return (Criteria) this;
        }

        public Criteria andNewsCodeEqualTo(String value) {
            addCriterion("news_code =", value, "newsCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeNotEqualTo(String value) {
            addCriterion("news_code <>", value, "newsCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeGreaterThan(String value) {
            addCriterion("news_code >", value, "newsCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("news_code >=", value, "newsCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeLessThan(String value) {
            addCriterion("news_code <", value, "newsCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeLessThanOrEqualTo(String value) {
            addCriterion("news_code <=", value, "newsCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeLike(String value) {
            addCriterion("news_code like", value, "newsCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeNotLike(String value) {
            addCriterion("news_code not like", value, "newsCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeIn(List<String> values) {
            addCriterion("news_code in", values, "newsCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeNotIn(List<String> values) {
            addCriterion("news_code not in", values, "newsCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeBetween(String value1, String value2) {
            addCriterion("news_code between", value1, value2, "newsCode");
            return (Criteria) this;
        }

        public Criteria andNewsCodeNotBetween(String value1, String value2) {
            addCriterion("news_code not between", value1, value2, "newsCode");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIsNull() {
            addCriterion("info_type is null");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIsNotNull() {
            addCriterion("info_type is not null");
            return (Criteria) this;
        }

        public Criteria andInfoTypeEqualTo(Byte value) {
            addCriterion("info_type =", value, "infoType");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNotEqualTo(Byte value) {
            addCriterion("info_type <>", value, "infoType");
            return (Criteria) this;
        }

        public Criteria andInfoTypeGreaterThan(Byte value) {
            addCriterion("info_type >", value, "infoType");
            return (Criteria) this;
        }

        public Criteria andInfoTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("info_type >=", value, "infoType");
            return (Criteria) this;
        }

        public Criteria andInfoTypeLessThan(Byte value) {
            addCriterion("info_type <", value, "infoType");
            return (Criteria) this;
        }

        public Criteria andInfoTypeLessThanOrEqualTo(Byte value) {
            addCriterion("info_type <=", value, "infoType");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIn(List<Byte> values) {
            addCriterion("info_type in", values, "infoType");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNotIn(List<Byte> values) {
            addCriterion("info_type not in", values, "infoType");
            return (Criteria) this;
        }

        public Criteria andInfoTypeBetween(Byte value1, Byte value2) {
            addCriterion("info_type between", value1, value2, "infoType");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("info_type not between", value1, value2, "infoType");
            return (Criteria) this;
        }

        public Criteria andInfoPathIsNull() {
            addCriterion("info_path is null");
            return (Criteria) this;
        }

        public Criteria andInfoPathIsNotNull() {
            addCriterion("info_path is not null");
            return (Criteria) this;
        }

        public Criteria andInfoPathEqualTo(String value) {
            addCriterion("info_path =", value, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoPathNotEqualTo(String value) {
            addCriterion("info_path <>", value, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoPathGreaterThan(String value) {
            addCriterion("info_path >", value, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoPathGreaterThanOrEqualTo(String value) {
            addCriterion("info_path >=", value, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoPathLessThan(String value) {
            addCriterion("info_path <", value, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoPathLessThanOrEqualTo(String value) {
            addCriterion("info_path <=", value, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoPathLike(String value) {
            addCriterion("info_path like", value, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoPathNotLike(String value) {
            addCriterion("info_path not like", value, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoPathIn(List<String> values) {
            addCriterion("info_path in", values, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoPathNotIn(List<String> values) {
            addCriterion("info_path not in", values, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoPathBetween(String value1, String value2) {
            addCriterion("info_path between", value1, value2, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoPathNotBetween(String value1, String value2) {
            addCriterion("info_path not between", value1, value2, "infoPath");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameIsNull() {
            addCriterion("info_org_name is null");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameIsNotNull() {
            addCriterion("info_org_name is not null");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameEqualTo(String value) {
            addCriterion("info_org_name =", value, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameNotEqualTo(String value) {
            addCriterion("info_org_name <>", value, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameGreaterThan(String value) {
            addCriterion("info_org_name >", value, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("info_org_name >=", value, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameLessThan(String value) {
            addCriterion("info_org_name <", value, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameLessThanOrEqualTo(String value) {
            addCriterion("info_org_name <=", value, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameLike(String value) {
            addCriterion("info_org_name like", value, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameNotLike(String value) {
            addCriterion("info_org_name not like", value, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameIn(List<String> values) {
            addCriterion("info_org_name in", values, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameNotIn(List<String> values) {
            addCriterion("info_org_name not in", values, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameBetween(String value1, String value2) {
            addCriterion("info_org_name between", value1, value2, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgNameNotBetween(String value1, String value2) {
            addCriterion("info_org_name not between", value1, value2, "infoOrgName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameIsNull() {
            addCriterion("info_new_name is null");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameIsNotNull() {
            addCriterion("info_new_name is not null");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameEqualTo(String value) {
            addCriterion("info_new_name =", value, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameNotEqualTo(String value) {
            addCriterion("info_new_name <>", value, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameGreaterThan(String value) {
            addCriterion("info_new_name >", value, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameGreaterThanOrEqualTo(String value) {
            addCriterion("info_new_name >=", value, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameLessThan(String value) {
            addCriterion("info_new_name <", value, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameLessThanOrEqualTo(String value) {
            addCriterion("info_new_name <=", value, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameLike(String value) {
            addCriterion("info_new_name like", value, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameNotLike(String value) {
            addCriterion("info_new_name not like", value, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameIn(List<String> values) {
            addCriterion("info_new_name in", values, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameNotIn(List<String> values) {
            addCriterion("info_new_name not in", values, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameBetween(String value1, String value2) {
            addCriterion("info_new_name between", value1, value2, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoNewNameNotBetween(String value1, String value2) {
            addCriterion("info_new_name not between", value1, value2, "infoNewName");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtIsNull() {
            addCriterion("info_org_ext is null");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtIsNotNull() {
            addCriterion("info_org_ext is not null");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtEqualTo(String value) {
            addCriterion("info_org_ext =", value, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtNotEqualTo(String value) {
            addCriterion("info_org_ext <>", value, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtGreaterThan(String value) {
            addCriterion("info_org_ext >", value, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtGreaterThanOrEqualTo(String value) {
            addCriterion("info_org_ext >=", value, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtLessThan(String value) {
            addCriterion("info_org_ext <", value, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtLessThanOrEqualTo(String value) {
            addCriterion("info_org_ext <=", value, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtLike(String value) {
            addCriterion("info_org_ext like", value, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtNotLike(String value) {
            addCriterion("info_org_ext not like", value, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtIn(List<String> values) {
            addCriterion("info_org_ext in", values, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtNotIn(List<String> values) {
            addCriterion("info_org_ext not in", values, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtBetween(String value1, String value2) {
            addCriterion("info_org_ext between", value1, value2, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoOrgExtNotBetween(String value1, String value2) {
            addCriterion("info_org_ext not between", value1, value2, "infoOrgExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtIsNull() {
            addCriterion("info_new_ext is null");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtIsNotNull() {
            addCriterion("info_new_ext is not null");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtEqualTo(String value) {
            addCriterion("info_new_ext =", value, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtNotEqualTo(String value) {
            addCriterion("info_new_ext <>", value, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtGreaterThan(String value) {
            addCriterion("info_new_ext >", value, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtGreaterThanOrEqualTo(String value) {
            addCriterion("info_new_ext >=", value, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtLessThan(String value) {
            addCriterion("info_new_ext <", value, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtLessThanOrEqualTo(String value) {
            addCriterion("info_new_ext <=", value, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtLike(String value) {
            addCriterion("info_new_ext like", value, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtNotLike(String value) {
            addCriterion("info_new_ext not like", value, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtIn(List<String> values) {
            addCriterion("info_new_ext in", values, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtNotIn(List<String> values) {
            addCriterion("info_new_ext not in", values, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtBetween(String value1, String value2) {
            addCriterion("info_new_ext between", value1, value2, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoNewExtNotBetween(String value1, String value2) {
            addCriterion("info_new_ext not between", value1, value2, "infoNewExt");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeIsNull() {
            addCriterion("info_upload_time is null");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeIsNotNull() {
            addCriterion("info_upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeEqualTo(Long value) {
            addCriterion("info_upload_time =", value, "infoUploadTime");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeNotEqualTo(Long value) {
            addCriterion("info_upload_time <>", value, "infoUploadTime");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeGreaterThan(Long value) {
            addCriterion("info_upload_time >", value, "infoUploadTime");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("info_upload_time >=", value, "infoUploadTime");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeLessThan(Long value) {
            addCriterion("info_upload_time <", value, "infoUploadTime");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeLessThanOrEqualTo(Long value) {
            addCriterion("info_upload_time <=", value, "infoUploadTime");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeIn(List<Long> values) {
            addCriterion("info_upload_time in", values, "infoUploadTime");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeNotIn(List<Long> values) {
            addCriterion("info_upload_time not in", values, "infoUploadTime");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeBetween(Long value1, Long value2) {
            addCriterion("info_upload_time between", value1, value2, "infoUploadTime");
            return (Criteria) this;
        }

        public Criteria andInfoUploadTimeNotBetween(Long value1, Long value2) {
            addCriterion("info_upload_time not between", value1, value2, "infoUploadTime");
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