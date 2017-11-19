package com.zssq.news.paging;

import java.util.ArrayList;
import java.util.List;

public class MybatisPaging {
	
	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public MybatisPaging() {
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

        public Criteria andInfoTitleIsNull() {
            addCriterion("info_title is null");
            return (Criteria) this;
        }

        public Criteria andInfoTitleIsNotNull() {
            addCriterion("info_title is not null");
            return (Criteria) this;
        }

        public Criteria andInfoTitleEqualTo(String value) {
            addCriterion("info_title =", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotEqualTo(String value) {
            addCriterion("info_title <>", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleGreaterThan(String value) {
            addCriterion("info_title >", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleGreaterThanOrEqualTo(String value) {
            addCriterion("info_title >=", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleLessThan(String value) {
            addCriterion("info_title <", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleLessThanOrEqualTo(String value) {
            addCriterion("info_title <=", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleLike(String value) {
            addCriterion("info_title like", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotLike(String value) {
            addCriterion("info_title not like", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleIn(List<String> values) {
            addCriterion("info_title in", values, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotIn(List<String> values) {
            addCriterion("info_title not in", values, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleBetween(String value1, String value2) {
            addCriterion("info_title between", value1, value2, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotBetween(String value1, String value2) {
            addCriterion("info_title not between", value1, value2, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextIsNull() {
            addCriterion("info_content_text is null");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextIsNotNull() {
            addCriterion("info_content_text is not null");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextEqualTo(String value) {
            addCriterion("info_content_text =", value, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextNotEqualTo(String value) {
            addCriterion("info_content_text <>", value, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextGreaterThan(String value) {
            addCriterion("info_content_text >", value, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextGreaterThanOrEqualTo(String value) {
            addCriterion("info_content_text >=", value, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextLessThan(String value) {
            addCriterion("info_content_text <", value, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextLessThanOrEqualTo(String value) {
            addCriterion("info_content_text <=", value, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextLike(String value) {
            addCriterion("info_content_text like", value, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextNotLike(String value) {
            addCriterion("info_content_text not like", value, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextIn(List<String> values) {
            addCriterion("info_content_text in", values, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextNotIn(List<String> values) {
            addCriterion("info_content_text not in", values, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextBetween(String value1, String value2) {
            addCriterion("info_content_text between", value1, value2, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentTextNotBetween(String value1, String value2) {
            addCriterion("info_content_text not between", value1, value2, "infoContentText");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlIsNull() {
            addCriterion("info_content_html is null");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlIsNotNull() {
            addCriterion("info_content_html is not null");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlEqualTo(String value) {
            addCriterion("info_content_html =", value, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlNotEqualTo(String value) {
            addCriterion("info_content_html <>", value, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlGreaterThan(String value) {
            addCriterion("info_content_html >", value, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlGreaterThanOrEqualTo(String value) {
            addCriterion("info_content_html >=", value, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlLessThan(String value) {
            addCriterion("info_content_html <", value, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlLessThanOrEqualTo(String value) {
            addCriterion("info_content_html <=", value, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlLike(String value) {
            addCriterion("info_content_html like", value, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlNotLike(String value) {
            addCriterion("info_content_html not like", value, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlIn(List<String> values) {
            addCriterion("info_content_html in", values, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlNotIn(List<String> values) {
            addCriterion("info_content_html not in", values, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlBetween(String value1, String value2) {
            addCriterion("info_content_html between", value1, value2, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoContentHtmlNotBetween(String value1, String value2) {
            addCriterion("info_content_html not between", value1, value2, "infoContentHtml");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIsNull() {
            addCriterion("info_status is null");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIsNotNull() {
            addCriterion("info_status is not null");
            return (Criteria) this;
        }

        public Criteria andInfoStatusEqualTo(Byte value) {
            addCriterion("info_status =", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNotEqualTo(Byte value) {
            addCriterion("info_status <>", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusGreaterThan(Byte value) {
            addCriterion("info_status >", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("info_status >=", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusLessThan(Byte value) {
            addCriterion("info_status <", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusLessThanOrEqualTo(Byte value) {
            addCriterion("info_status <=", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIn(List<Byte> values) {
            addCriterion("info_status in", values, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNotIn(List<Byte> values) {
            addCriterion("info_status not in", values, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusBetween(Byte value1, Byte value2) {
            addCriterion("info_status between", value1, value2, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("info_status not between", value1, value2, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeIsNull() {
            addCriterion("info_operator_code is null");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeIsNotNull() {
            addCriterion("info_operator_code is not null");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeEqualTo(String value) {
            addCriterion("info_operator_code =", value, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeNotEqualTo(String value) {
            addCriterion("info_operator_code <>", value, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeGreaterThan(String value) {
            addCriterion("info_operator_code >", value, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("info_operator_code >=", value, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeLessThan(String value) {
            addCriterion("info_operator_code <", value, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeLessThanOrEqualTo(String value) {
            addCriterion("info_operator_code <=", value, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeLike(String value) {
            addCriterion("info_operator_code like", value, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeNotLike(String value) {
            addCriterion("info_operator_code not like", value, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeIn(List<String> values) {
            addCriterion("info_operator_code in", values, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeNotIn(List<String> values) {
            addCriterion("info_operator_code not in", values, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeBetween(String value1, String value2) {
            addCriterion("info_operator_code between", value1, value2, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorCodeNotBetween(String value1, String value2) {
            addCriterion("info_operator_code not between", value1, value2, "infoOperatorCode");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameIsNull() {
            addCriterion("info_operator_name is null");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameIsNotNull() {
            addCriterion("info_operator_name is not null");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameEqualTo(String value) {
            addCriterion("info_operator_name =", value, "infoOperatorName");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameNotEqualTo(String value) {
            addCriterion("info_operator_name <>", value, "infoOperatorName");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameGreaterThan(String value) {
            addCriterion("info_operator_name >", value, "infoOperatorName");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("info_operator_name >=", value, "infoOperatorName");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameLessThan(String value) {
            addCriterion("info_operator_name <", value, "infoOperatorName");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameLessThanOrEqualTo(String value) {
            addCriterion("info_operator_name <=", value, "infoOperatorName");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameLike(String value) {
            addCriterion("info_operator_name like", value, "infoOperatorName");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameNotLike(String value) {
            addCriterion("info_operator_name not like", value, "infoOperatorName");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameIn(List<String> values) {
            addCriterion("info_operator_name in", values, "infoOperatorName");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameNotIn(List<String> values) {
            addCriterion("info_operator_name not in", values, "infoOperatorName");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameBetween(String value1, String value2) {
            addCriterion("info_operator_name between", value1, value2, "infoOperatorName");
            return (Criteria) this;
        }

        public Criteria andInfoOperatorNameNotBetween(String value1, String value2) {
            addCriterion("info_operator_name not between", value1, value2, "infoOperatorName");
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

        public Criteria andInfoUpdateTimeIsNull() {
            addCriterion("info_update_time is null");
            return (Criteria) this;
        }

        public Criteria andInfoUpdateTimeIsNotNull() {
            addCriterion("info_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andInfoUpdateTimeEqualTo(Long value) {
            addCriterion("info_update_time =", value, "infoUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInfoUpdateTimeNotEqualTo(Long value) {
            addCriterion("info_update_time <>", value, "infoUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInfoUpdateTimeGreaterThan(Long value) {
            addCriterion("info_update_time >", value, "infoUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInfoUpdateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("info_update_time >=", value, "infoUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInfoUpdateTimeLessThan(Long value) {
            addCriterion("info_update_time <", value, "infoUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInfoUpdateTimeLessThanOrEqualTo(Long value) {
            addCriterion("info_update_time <=", value, "infoUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInfoUpdateTimeIn(List<Long> values) {
            addCriterion("info_update_time in", values, "infoUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInfoUpdateTimeNotIn(List<Long> values) {
            addCriterion("info_update_time not in", values, "infoUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInfoUpdateTimeBetween(Long value1, Long value2) {
            addCriterion("info_update_time between", value1, value2, "infoUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInfoUpdateTimeNotBetween(Long value1, Long value2) {
            addCriterion("info_update_time not between", value1, value2, "infoUpdateTime");
            return (Criteria) this;
        }

        public Criteria andIsTopIsNull() {
            addCriterion("is_top is null");
            return (Criteria) this;
        }

        public Criteria andIsTopIsNotNull() {
            addCriterion("is_top is not null");
            return (Criteria) this;
        }

        public Criteria andIsTopEqualTo(Byte value) {
            addCriterion("is_top =", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotEqualTo(Byte value) {
            addCriterion("is_top <>", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopGreaterThan(Byte value) {
            addCriterion("is_top >", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_top >=", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopLessThan(Byte value) {
            addCriterion("is_top <", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopLessThanOrEqualTo(Byte value) {
            addCriterion("is_top <=", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopIn(List<Byte> values) {
            addCriterion("is_top in", values, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotIn(List<Byte> values) {
            addCriterion("is_top not in", values, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopBetween(Byte value1, Byte value2) {
            addCriterion("is_top between", value1, value2, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotBetween(Byte value1, Byte value2) {
            addCriterion("is_top not between", value1, value2, "isTop");
            return (Criteria) this;
        }

        public Criteria andInfoSortIsNull() {
            addCriterion("info_sort is null");
            return (Criteria) this;
        }

        public Criteria andInfoSortIsNotNull() {
            addCriterion("info_sort is not null");
            return (Criteria) this;
        }

        public Criteria andInfoSortEqualTo(Integer value) {
            addCriterion("info_sort =", value, "infoSort");
            return (Criteria) this;
        }

        public Criteria andInfoSortNotEqualTo(Integer value) {
            addCriterion("info_sort <>", value, "infoSort");
            return (Criteria) this;
        }

        public Criteria andInfoSortGreaterThan(Integer value) {
            addCriterion("info_sort >", value, "infoSort");
            return (Criteria) this;
        }

        public Criteria andInfoSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("info_sort >=", value, "infoSort");
            return (Criteria) this;
        }

        public Criteria andInfoSortLessThan(Integer value) {
            addCriterion("info_sort <", value, "infoSort");
            return (Criteria) this;
        }

        public Criteria andInfoSortLessThanOrEqualTo(Integer value) {
            addCriterion("info_sort <=", value, "infoSort");
            return (Criteria) this;
        }

        public Criteria andInfoSortIn(List<Integer> values) {
            addCriterion("info_sort in", values, "infoSort");
            return (Criteria) this;
        }

        public Criteria andInfoSortNotIn(List<Integer> values) {
            addCriterion("info_sort not in", values, "infoSort");
            return (Criteria) this;
        }

        public Criteria andInfoSortBetween(Integer value1, Integer value2) {
            addCriterion("info_sort between", value1, value2, "infoSort");
            return (Criteria) this;
        }

        public Criteria andInfoSortNotBetween(Integer value1, Integer value2) {
            addCriterion("info_sort not between", value1, value2, "infoSort");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountIsNull() {
            addCriterion("info_good_count is null");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountIsNotNull() {
            addCriterion("info_good_count is not null");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountEqualTo(Integer value) {
            addCriterion("info_good_count =", value, "infoGoodCount");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountNotEqualTo(Integer value) {
            addCriterion("info_good_count <>", value, "infoGoodCount");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountGreaterThan(Integer value) {
            addCriterion("info_good_count >", value, "infoGoodCount");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("info_good_count >=", value, "infoGoodCount");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountLessThan(Integer value) {
            addCriterion("info_good_count <", value, "infoGoodCount");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountLessThanOrEqualTo(Integer value) {
            addCriterion("info_good_count <=", value, "infoGoodCount");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountIn(List<Integer> values) {
            addCriterion("info_good_count in", values, "infoGoodCount");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountNotIn(List<Integer> values) {
            addCriterion("info_good_count not in", values, "infoGoodCount");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountBetween(Integer value1, Integer value2) {
            addCriterion("info_good_count between", value1, value2, "infoGoodCount");
            return (Criteria) this;
        }

        public Criteria andInfoGoodCountNotBetween(Integer value1, Integer value2) {
            addCriterion("info_good_count not between", value1, value2, "infoGoodCount");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountIsNull() {
            addCriterion("info_comment_count is null");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountIsNotNull() {
            addCriterion("info_comment_count is not null");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountEqualTo(Integer value) {
            addCriterion("info_comment_count =", value, "infoCommentCount");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountNotEqualTo(Integer value) {
            addCriterion("info_comment_count <>", value, "infoCommentCount");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountGreaterThan(Integer value) {
            addCriterion("info_comment_count >", value, "infoCommentCount");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("info_comment_count >=", value, "infoCommentCount");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountLessThan(Integer value) {
            addCriterion("info_comment_count <", value, "infoCommentCount");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountLessThanOrEqualTo(Integer value) {
            addCriterion("info_comment_count <=", value, "infoCommentCount");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountIn(List<Integer> values) {
            addCriterion("info_comment_count in", values, "infoCommentCount");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountNotIn(List<Integer> values) {
            addCriterion("info_comment_count not in", values, "infoCommentCount");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountBetween(Integer value1, Integer value2) {
            addCriterion("info_comment_count between", value1, value2, "infoCommentCount");
            return (Criteria) this;
        }

        public Criteria andInfoCommentCountNotBetween(Integer value1, Integer value2) {
            addCriterion("info_comment_count not between", value1, value2, "infoCommentCount");
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
