package com.zssq.relation.pojo;

import java.util.ArrayList;
import java.util.List;

public class TeamInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TeamInfoExample() {
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

        public Criteria andTeamCodeIsNull() {
            addCriterion("team_code is null");
            return (Criteria) this;
        }

        public Criteria andTeamCodeIsNotNull() {
            addCriterion("team_code is not null");
            return (Criteria) this;
        }

        public Criteria andTeamCodeEqualTo(String value) {
            addCriterion("team_code =", value, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamCodeNotEqualTo(String value) {
            addCriterion("team_code <>", value, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamCodeGreaterThan(String value) {
            addCriterion("team_code >", value, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamCodeGreaterThanOrEqualTo(String value) {
            addCriterion("team_code >=", value, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamCodeLessThan(String value) {
            addCriterion("team_code <", value, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamCodeLessThanOrEqualTo(String value) {
            addCriterion("team_code <=", value, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamCodeLike(String value) {
            addCriterion("team_code like", value, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamCodeNotLike(String value) {
            addCriterion("team_code not like", value, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamCodeIn(List<String> values) {
            addCriterion("team_code in", values, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamCodeNotIn(List<String> values) {
            addCriterion("team_code not in", values, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamCodeBetween(String value1, String value2) {
            addCriterion("team_code between", value1, value2, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamCodeNotBetween(String value1, String value2) {
            addCriterion("team_code not between", value1, value2, "teamCode");
            return (Criteria) this;
        }

        public Criteria andTeamNameIsNull() {
            addCriterion("team_name is null");
            return (Criteria) this;
        }

        public Criteria andTeamNameIsNotNull() {
            addCriterion("team_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeamNameEqualTo(String value) {
            addCriterion("team_name =", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotEqualTo(String value) {
            addCriterion("team_name <>", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameGreaterThan(String value) {
            addCriterion("team_name >", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameGreaterThanOrEqualTo(String value) {
            addCriterion("team_name >=", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLessThan(String value) {
            addCriterion("team_name <", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLessThanOrEqualTo(String value) {
            addCriterion("team_name <=", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLike(String value) {
            addCriterion("team_name like", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotLike(String value) {
            addCriterion("team_name not like", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameIn(List<String> values) {
            addCriterion("team_name in", values, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotIn(List<String> values) {
            addCriterion("team_name not in", values, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameBetween(String value1, String value2) {
            addCriterion("team_name between", value1, value2, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotBetween(String value1, String value2) {
            addCriterion("team_name not between", value1, value2, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamIntroIsNull() {
            addCriterion("team_intro is null");
            return (Criteria) this;
        }

        public Criteria andTeamIntroIsNotNull() {
            addCriterion("team_intro is not null");
            return (Criteria) this;
        }

        public Criteria andTeamIntroEqualTo(String value) {
            addCriterion("team_intro =", value, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIntroNotEqualTo(String value) {
            addCriterion("team_intro <>", value, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIntroGreaterThan(String value) {
            addCriterion("team_intro >", value, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIntroGreaterThanOrEqualTo(String value) {
            addCriterion("team_intro >=", value, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIntroLessThan(String value) {
            addCriterion("team_intro <", value, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIntroLessThanOrEqualTo(String value) {
            addCriterion("team_intro <=", value, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIntroLike(String value) {
            addCriterion("team_intro like", value, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIntroNotLike(String value) {
            addCriterion("team_intro not like", value, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIntroIn(List<String> values) {
            addCriterion("team_intro in", values, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIntroNotIn(List<String> values) {
            addCriterion("team_intro not in", values, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIntroBetween(String value1, String value2) {
            addCriterion("team_intro between", value1, value2, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIntroNotBetween(String value1, String value2) {
            addCriterion("team_intro not between", value1, value2, "teamIntro");
            return (Criteria) this;
        }

        public Criteria andTeamIconIsNull() {
            addCriterion("team_icon is null");
            return (Criteria) this;
        }

        public Criteria andTeamIconIsNotNull() {
            addCriterion("team_icon is not null");
            return (Criteria) this;
        }

        public Criteria andTeamIconEqualTo(String value) {
            addCriterion("team_icon =", value, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamIconNotEqualTo(String value) {
            addCriterion("team_icon <>", value, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamIconGreaterThan(String value) {
            addCriterion("team_icon >", value, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamIconGreaterThanOrEqualTo(String value) {
            addCriterion("team_icon >=", value, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamIconLessThan(String value) {
            addCriterion("team_icon <", value, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamIconLessThanOrEqualTo(String value) {
            addCriterion("team_icon <=", value, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamIconLike(String value) {
            addCriterion("team_icon like", value, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamIconNotLike(String value) {
            addCriterion("team_icon not like", value, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamIconIn(List<String> values) {
            addCriterion("team_icon in", values, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamIconNotIn(List<String> values) {
            addCriterion("team_icon not in", values, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamIconBetween(String value1, String value2) {
            addCriterion("team_icon between", value1, value2, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamIconNotBetween(String value1, String value2) {
            addCriterion("team_icon not between", value1, value2, "teamIcon");
            return (Criteria) this;
        }

        public Criteria andTeamTypeIsNull() {
            addCriterion("team_type is null");
            return (Criteria) this;
        }

        public Criteria andTeamTypeIsNotNull() {
            addCriterion("team_type is not null");
            return (Criteria) this;
        }

        public Criteria andTeamTypeEqualTo(Byte value) {
            addCriterion("team_type =", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeNotEqualTo(Byte value) {
            addCriterion("team_type <>", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeGreaterThan(Byte value) {
            addCriterion("team_type >", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("team_type >=", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeLessThan(Byte value) {
            addCriterion("team_type <", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeLessThanOrEqualTo(Byte value) {
            addCriterion("team_type <=", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeIn(List<Byte> values) {
            addCriterion("team_type in", values, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeNotIn(List<Byte> values) {
            addCriterion("team_type not in", values, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeBetween(Byte value1, Byte value2) {
            addCriterion("team_type between", value1, value2, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("team_type not between", value1, value2, "teamType");
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

        public Criteria andDissolveTimeIsNull() {
            addCriterion("dissolve_time is null");
            return (Criteria) this;
        }

        public Criteria andDissolveTimeIsNotNull() {
            addCriterion("dissolve_time is not null");
            return (Criteria) this;
        }

        public Criteria andDissolveTimeEqualTo(Long value) {
            addCriterion("dissolve_time =", value, "dissolveTime");
            return (Criteria) this;
        }

        public Criteria andDissolveTimeNotEqualTo(Long value) {
            addCriterion("dissolve_time <>", value, "dissolveTime");
            return (Criteria) this;
        }

        public Criteria andDissolveTimeGreaterThan(Long value) {
            addCriterion("dissolve_time >", value, "dissolveTime");
            return (Criteria) this;
        }

        public Criteria andDissolveTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("dissolve_time >=", value, "dissolveTime");
            return (Criteria) this;
        }

        public Criteria andDissolveTimeLessThan(Long value) {
            addCriterion("dissolve_time <", value, "dissolveTime");
            return (Criteria) this;
        }

        public Criteria andDissolveTimeLessThanOrEqualTo(Long value) {
            addCriterion("dissolve_time <=", value, "dissolveTime");
            return (Criteria) this;
        }

        public Criteria andDissolveTimeIn(List<Long> values) {
            addCriterion("dissolve_time in", values, "dissolveTime");
            return (Criteria) this;
        }

        public Criteria andDissolveTimeNotIn(List<Long> values) {
            addCriterion("dissolve_time not in", values, "dissolveTime");
            return (Criteria) this;
        }

        public Criteria andDissolveTimeBetween(Long value1, Long value2) {
            addCriterion("dissolve_time between", value1, value2, "dissolveTime");
            return (Criteria) this;
        }

        public Criteria andDissolveTimeNotBetween(Long value1, Long value2) {
            addCriterion("dissolve_time not between", value1, value2, "dissolveTime");
            return (Criteria) this;
        }

        public Criteria andIsDissolveIsNull() {
            addCriterion("is_dissolve is null");
            return (Criteria) this;
        }

        public Criteria andIsDissolveIsNotNull() {
            addCriterion("is_dissolve is not null");
            return (Criteria) this;
        }

        public Criteria andIsDissolveEqualTo(Byte value) {
            addCriterion("is_dissolve =", value, "isDissolve");
            return (Criteria) this;
        }

        public Criteria andIsDissolveNotEqualTo(Byte value) {
            addCriterion("is_dissolve <>", value, "isDissolve");
            return (Criteria) this;
        }

        public Criteria andIsDissolveGreaterThan(Byte value) {
            addCriterion("is_dissolve >", value, "isDissolve");
            return (Criteria) this;
        }

        public Criteria andIsDissolveGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_dissolve >=", value, "isDissolve");
            return (Criteria) this;
        }

        public Criteria andIsDissolveLessThan(Byte value) {
            addCriterion("is_dissolve <", value, "isDissolve");
            return (Criteria) this;
        }

        public Criteria andIsDissolveLessThanOrEqualTo(Byte value) {
            addCriterion("is_dissolve <=", value, "isDissolve");
            return (Criteria) this;
        }

        public Criteria andIsDissolveIn(List<Byte> values) {
            addCriterion("is_dissolve in", values, "isDissolve");
            return (Criteria) this;
        }

        public Criteria andIsDissolveNotIn(List<Byte> values) {
            addCriterion("is_dissolve not in", values, "isDissolve");
            return (Criteria) this;
        }

        public Criteria andIsDissolveBetween(Byte value1, Byte value2) {
            addCriterion("is_dissolve between", value1, value2, "isDissolve");
            return (Criteria) this;
        }

        public Criteria andIsDissolveNotBetween(Byte value1, Byte value2) {
            addCriterion("is_dissolve not between", value1, value2, "isDissolve");
            return (Criteria) this;
        }

        public Criteria andIsRecordIsNull() {
            addCriterion("is_record is null");
            return (Criteria) this;
        }

        public Criteria andIsRecordIsNotNull() {
            addCriterion("is_record is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecordEqualTo(Byte value) {
            addCriterion("is_record =", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordNotEqualTo(Byte value) {
            addCriterion("is_record <>", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordGreaterThan(Byte value) {
            addCriterion("is_record >", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_record >=", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordLessThan(Byte value) {
            addCriterion("is_record <", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordLessThanOrEqualTo(Byte value) {
            addCriterion("is_record <=", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordIn(List<Byte> values) {
            addCriterion("is_record in", values, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordNotIn(List<Byte> values) {
            addCriterion("is_record not in", values, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordBetween(Byte value1, Byte value2) {
            addCriterion("is_record between", value1, value2, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordNotBetween(Byte value1, Byte value2) {
            addCriterion("is_record not between", value1, value2, "isRecord");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlIsNull() {
            addCriterion("record_file_url is null");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlIsNotNull() {
            addCriterion("record_file_url is not null");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlEqualTo(String value) {
            addCriterion("record_file_url =", value, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlNotEqualTo(String value) {
            addCriterion("record_file_url <>", value, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlGreaterThan(String value) {
            addCriterion("record_file_url >", value, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("record_file_url >=", value, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlLessThan(String value) {
            addCriterion("record_file_url <", value, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlLessThanOrEqualTo(String value) {
            addCriterion("record_file_url <=", value, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlLike(String value) {
            addCriterion("record_file_url like", value, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlNotLike(String value) {
            addCriterion("record_file_url not like", value, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlIn(List<String> values) {
            addCriterion("record_file_url in", values, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlNotIn(List<String> values) {
            addCriterion("record_file_url not in", values, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlBetween(String value1, String value2) {
            addCriterion("record_file_url between", value1, value2, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andRecordFileUrlNotBetween(String value1, String value2) {
            addCriterion("record_file_url not between", value1, value2, "recordFileUrl");
            return (Criteria) this;
        }

        public Criteria andUserCodeIsNull() {
            addCriterion("user_code is null");
            return (Criteria) this;
        }

        public Criteria andUserCodeIsNotNull() {
            addCriterion("user_code is not null");
            return (Criteria) this;
        }

        public Criteria andUserCodeEqualTo(String value) {
            addCriterion("user_code =", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotEqualTo(String value) {
            addCriterion("user_code <>", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThan(String value) {
            addCriterion("user_code >", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("user_code >=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThan(String value) {
            addCriterion("user_code <", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThanOrEqualTo(String value) {
            addCriterion("user_code <=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLike(String value) {
            addCriterion("user_code like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotLike(String value) {
            addCriterion("user_code not like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeIn(List<String> values) {
            addCriterion("user_code in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotIn(List<String> values) {
            addCriterion("user_code not in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeBetween(String value1, String value2) {
            addCriterion("user_code between", value1, value2, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotBetween(String value1, String value2) {
            addCriterion("user_code not between", value1, value2, "userCode");
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

        public Criteria andTeamIdIsNull() {
            addCriterion("team_id is null");
            return (Criteria) this;
        }

        public Criteria andTeamIdIsNotNull() {
            addCriterion("team_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeamIdEqualTo(Integer value) {
            addCriterion("team_id =", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotEqualTo(Integer value) {
            addCriterion("team_id <>", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThan(Integer value) {
            addCriterion("team_id >", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_id >=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThan(Integer value) {
            addCriterion("team_id <", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThanOrEqualTo(Integer value) {
            addCriterion("team_id <=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdIn(List<Integer> values) {
            addCriterion("team_id in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotIn(List<Integer> values) {
            addCriterion("team_id not in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdBetween(Integer value1, Integer value2) {
            addCriterion("team_id between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("team_id not between", value1, value2, "teamId");
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