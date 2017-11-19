package com.zssq.vote.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransVoteUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TransVoteUserExample() {
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

        public Criteria andVoteUserIdIsNull() {
            addCriterion("VOTE_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andVoteUserIdIsNotNull() {
            addCriterion("VOTE_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVoteUserIdEqualTo(Integer value) {
            addCriterion("VOTE_USER_ID =", value, "voteUserId");
            return (Criteria) this;
        }

        public Criteria andVoteUserIdNotEqualTo(Integer value) {
            addCriterion("VOTE_USER_ID <>", value, "voteUserId");
            return (Criteria) this;
        }

        public Criteria andVoteUserIdGreaterThan(Integer value) {
            addCriterion("VOTE_USER_ID >", value, "voteUserId");
            return (Criteria) this;
        }

        public Criteria andVoteUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOTE_USER_ID >=", value, "voteUserId");
            return (Criteria) this;
        }

        public Criteria andVoteUserIdLessThan(Integer value) {
            addCriterion("VOTE_USER_ID <", value, "voteUserId");
            return (Criteria) this;
        }

        public Criteria andVoteUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("VOTE_USER_ID <=", value, "voteUserId");
            return (Criteria) this;
        }

        public Criteria andVoteUserIdIn(List<Integer> values) {
            addCriterion("VOTE_USER_ID in", values, "voteUserId");
            return (Criteria) this;
        }

        public Criteria andVoteUserIdNotIn(List<Integer> values) {
            addCriterion("VOTE_USER_ID not in", values, "voteUserId");
            return (Criteria) this;
        }

        public Criteria andVoteUserIdBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_USER_ID between", value1, value2, "voteUserId");
            return (Criteria) this;
        }

        public Criteria andVoteUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_USER_ID not between", value1, value2, "voteUserId");
            return (Criteria) this;
        }

        public Criteria andVoteIdIsNull() {
            addCriterion("VOTE_ID is null");
            return (Criteria) this;
        }

        public Criteria andVoteIdIsNotNull() {
            addCriterion("VOTE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVoteIdEqualTo(Integer value) {
            addCriterion("VOTE_ID =", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotEqualTo(Integer value) {
            addCriterion("VOTE_ID <>", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdGreaterThan(Integer value) {
            addCriterion("VOTE_ID >", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOTE_ID >=", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdLessThan(Integer value) {
            addCriterion("VOTE_ID <", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdLessThanOrEqualTo(Integer value) {
            addCriterion("VOTE_ID <=", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdIn(List<Integer> values) {
            addCriterion("VOTE_ID in", values, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotIn(List<Integer> values) {
            addCriterion("VOTE_ID not in", values, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_ID between", value1, value2, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_ID not between", value1, value2, "voteId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andVoteDateIsNull() {
            addCriterion("VOTE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andVoteDateIsNotNull() {
            addCriterion("VOTE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andVoteDateEqualTo(Date value) {
            addCriterion("VOTE_DATE =", value, "voteDate");
            return (Criteria) this;
        }

        public Criteria andVoteDateNotEqualTo(Date value) {
            addCriterion("VOTE_DATE <>", value, "voteDate");
            return (Criteria) this;
        }

        public Criteria andVoteDateGreaterThan(Date value) {
            addCriterion("VOTE_DATE >", value, "voteDate");
            return (Criteria) this;
        }

        public Criteria andVoteDateGreaterThanOrEqualTo(Date value) {
            addCriterion("VOTE_DATE >=", value, "voteDate");
            return (Criteria) this;
        }

        public Criteria andVoteDateLessThan(Date value) {
            addCriterion("VOTE_DATE <", value, "voteDate");
            return (Criteria) this;
        }

        public Criteria andVoteDateLessThanOrEqualTo(Date value) {
            addCriterion("VOTE_DATE <=", value, "voteDate");
            return (Criteria) this;
        }

        public Criteria andVoteDateIn(List<Date> values) {
            addCriterion("VOTE_DATE in", values, "voteDate");
            return (Criteria) this;
        }

        public Criteria andVoteDateNotIn(List<Date> values) {
            addCriterion("VOTE_DATE not in", values, "voteDate");
            return (Criteria) this;
        }

        public Criteria andVoteDateBetween(Date value1, Date value2) {
            addCriterion("VOTE_DATE between", value1, value2, "voteDate");
            return (Criteria) this;
        }

        public Criteria andVoteDateNotBetween(Date value1, Date value2) {
            addCriterion("VOTE_DATE not between", value1, value2, "voteDate");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsIsNull() {
            addCriterion("VOTE_OPTIONS is null");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsIsNotNull() {
            addCriterion("VOTE_OPTIONS is not null");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsEqualTo(Integer value) {
            addCriterion("VOTE_OPTIONS =", value, "voteOptions");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsNotEqualTo(Integer value) {
            addCriterion("VOTE_OPTIONS <>", value, "voteOptions");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsGreaterThan(Integer value) {
            addCriterion("VOTE_OPTIONS >", value, "voteOptions");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOTE_OPTIONS >=", value, "voteOptions");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsLessThan(Integer value) {
            addCriterion("VOTE_OPTIONS <", value, "voteOptions");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsLessThanOrEqualTo(Integer value) {
            addCriterion("VOTE_OPTIONS <=", value, "voteOptions");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsIn(List<Integer> values) {
            addCriterion("VOTE_OPTIONS in", values, "voteOptions");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsNotIn(List<Integer> values) {
            addCriterion("VOTE_OPTIONS not in", values, "voteOptions");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_OPTIONS between", value1, value2, "voteOptions");
            return (Criteria) this;
        }

        public Criteria andVoteOptionsNotBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_OPTIONS not between", value1, value2, "voteOptions");
            return (Criteria) this;
        }

        public Criteria andTeamIdIsNull() {
            addCriterion("TEAM_ID is null");
            return (Criteria) this;
        }

        public Criteria andTeamIdIsNotNull() {
            addCriterion("TEAM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTeamIdEqualTo(Integer value) {
            addCriterion("TEAM_ID =", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotEqualTo(Integer value) {
            addCriterion("TEAM_ID <>", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThan(Integer value) {
            addCriterion("TEAM_ID >", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TEAM_ID >=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThan(Integer value) {
            addCriterion("TEAM_ID <", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThanOrEqualTo(Integer value) {
            addCriterion("TEAM_ID <=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdIn(List<Integer> values) {
            addCriterion("TEAM_ID in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotIn(List<Integer> values) {
            addCriterion("TEAM_ID not in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdBetween(Integer value1, Integer value2) {
            addCriterion("TEAM_ID between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TEAM_ID not between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Integer value) {
            addCriterion("CITY_ID =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Integer value) {
            addCriterion("CITY_ID <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Integer value) {
            addCriterion("CITY_ID >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CITY_ID >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Integer value) {
            addCriterion("CITY_ID <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("CITY_ID <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Integer> values) {
            addCriterion("CITY_ID in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Integer> values) {
            addCriterion("CITY_ID not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Integer value1, Integer value2) {
            addCriterion("CITY_ID between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CITY_ID not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNull() {
            addCriterion("PROVINCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNotNull() {
            addCriterion("PROVINCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdEqualTo(Integer value) {
            addCriterion("PROVINCE_ID =", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotEqualTo(Integer value) {
            addCriterion("PROVINCE_ID <>", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThan(Integer value) {
            addCriterion("PROVINCE_ID >", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PROVINCE_ID >=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThan(Integer value) {
            addCriterion("PROVINCE_ID <", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThanOrEqualTo(Integer value) {
            addCriterion("PROVINCE_ID <=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIn(List<Integer> values) {
            addCriterion("PROVINCE_ID in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotIn(List<Integer> values) {
            addCriterion("PROVINCE_ID not in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdBetween(Integer value1, Integer value2) {
            addCriterion("PROVINCE_ID between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PROVINCE_ID not between", value1, value2, "provinceId");
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