package com.zssq.vote.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransVoteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TransVoteExample() {
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

        public Criteria andVoteTypeIsNull() {
            addCriterion("VOTE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andVoteTypeIsNotNull() {
            addCriterion("VOTE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andVoteTypeEqualTo(Integer value) {
            addCriterion("VOTE_TYPE =", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeNotEqualTo(Integer value) {
            addCriterion("VOTE_TYPE <>", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeGreaterThan(Integer value) {
            addCriterion("VOTE_TYPE >", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOTE_TYPE >=", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeLessThan(Integer value) {
            addCriterion("VOTE_TYPE <", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeLessThanOrEqualTo(Integer value) {
            addCriterion("VOTE_TYPE <=", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeIn(List<Integer> values) {
            addCriterion("VOTE_TYPE in", values, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeNotIn(List<Integer> values) {
            addCriterion("VOTE_TYPE not in", values, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_TYPE between", value1, value2, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_TYPE not between", value1, value2, "voteType");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andVoteTitleIsNull() {
            addCriterion("VOTE_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andVoteTitleIsNotNull() {
            addCriterion("VOTE_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andVoteTitleEqualTo(String value) {
            addCriterion("VOTE_TITLE =", value, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteTitleNotEqualTo(String value) {
            addCriterion("VOTE_TITLE <>", value, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteTitleGreaterThan(String value) {
            addCriterion("VOTE_TITLE >", value, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteTitleGreaterThanOrEqualTo(String value) {
            addCriterion("VOTE_TITLE >=", value, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteTitleLessThan(String value) {
            addCriterion("VOTE_TITLE <", value, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteTitleLessThanOrEqualTo(String value) {
            addCriterion("VOTE_TITLE <=", value, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteTitleLike(String value) {
            addCriterion("VOTE_TITLE like", value, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteTitleNotLike(String value) {
            addCriterion("VOTE_TITLE not like", value, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteTitleIn(List<String> values) {
            addCriterion("VOTE_TITLE in", values, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteTitleNotIn(List<String> values) {
            addCriterion("VOTE_TITLE not in", values, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteTitleBetween(String value1, String value2) {
            addCriterion("VOTE_TITLE between", value1, value2, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteTitleNotBetween(String value1, String value2) {
            addCriterion("VOTE_TITLE not between", value1, value2, "voteTitle");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareIsNull() {
            addCriterion("VOTE_DECLARE is null");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareIsNotNull() {
            addCriterion("VOTE_DECLARE is not null");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareEqualTo(String value) {
            addCriterion("VOTE_DECLARE =", value, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareNotEqualTo(String value) {
            addCriterion("VOTE_DECLARE <>", value, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareGreaterThan(String value) {
            addCriterion("VOTE_DECLARE >", value, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareGreaterThanOrEqualTo(String value) {
            addCriterion("VOTE_DECLARE >=", value, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareLessThan(String value) {
            addCriterion("VOTE_DECLARE <", value, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareLessThanOrEqualTo(String value) {
            addCriterion("VOTE_DECLARE <=", value, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareLike(String value) {
            addCriterion("VOTE_DECLARE like", value, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareNotLike(String value) {
            addCriterion("VOTE_DECLARE not like", value, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareIn(List<String> values) {
            addCriterion("VOTE_DECLARE in", values, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareNotIn(List<String> values) {
            addCriterion("VOTE_DECLARE not in", values, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareBetween(String value1, String value2) {
            addCriterion("VOTE_DECLARE between", value1, value2, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andVoteDeclareNotBetween(String value1, String value2) {
            addCriterion("VOTE_DECLARE not between", value1, value2, "voteDeclare");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeIsNull() {
            addCriterion("OPTIONS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeIsNotNull() {
            addCriterion("OPTIONS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeEqualTo(Integer value) {
            addCriterion("OPTIONS_TYPE =", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeNotEqualTo(Integer value) {
            addCriterion("OPTIONS_TYPE <>", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeGreaterThan(Integer value) {
            addCriterion("OPTIONS_TYPE >", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("OPTIONS_TYPE >=", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeLessThan(Integer value) {
            addCriterion("OPTIONS_TYPE <", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeLessThanOrEqualTo(Integer value) {
            addCriterion("OPTIONS_TYPE <=", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeIn(List<Integer> values) {
            addCriterion("OPTIONS_TYPE in", values, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeNotIn(List<Integer> values) {
            addCriterion("OPTIONS_TYPE not in", values, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeBetween(Integer value1, Integer value2) {
            addCriterion("OPTIONS_TYPE between", value1, value2, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("OPTIONS_TYPE not between", value1, value2, "optionsType");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeIsNull() {
            addCriterion("VOTE_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeIsNotNull() {
            addCriterion("VOTE_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeEqualTo(Date value) {
            addCriterion("VOTE_END_TIME =", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeNotEqualTo(Date value) {
            addCriterion("VOTE_END_TIME <>", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeGreaterThan(Date value) {
            addCriterion("VOTE_END_TIME >", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("VOTE_END_TIME >=", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeLessThan(Date value) {
            addCriterion("VOTE_END_TIME <", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("VOTE_END_TIME <=", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeIn(List<Date> values) {
            addCriterion("VOTE_END_TIME in", values, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeNotIn(List<Date> values) {
            addCriterion("VOTE_END_TIME not in", values, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeBetween(Date value1, Date value2) {
            addCriterion("VOTE_END_TIME between", value1, value2, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("VOTE_END_TIME not between", value1, value2, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("DEL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("DEL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Integer value) {
            addCriterion("DEL_FLAG =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Integer value) {
            addCriterion("DEL_FLAG <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Integer value) {
            addCriterion("DEL_FLAG >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("DEL_FLAG >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Integer value) {
            addCriterion("DEL_FLAG <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Integer value) {
            addCriterion("DEL_FLAG <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Integer> values) {
            addCriterion("DEL_FLAG in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Integer> values) {
            addCriterion("DEL_FLAG not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Integer value1, Integer value2) {
            addCriterion("DEL_FLAG between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("DEL_FLAG not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andCmtNumIsNull() {
            addCriterion("CMT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCmtNumIsNotNull() {
            addCriterion("CMT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCmtNumEqualTo(Integer value) {
            addCriterion("CMT_NUM =", value, "cmtNum");
            return (Criteria) this;
        }

        public Criteria andCmtNumNotEqualTo(Integer value) {
            addCriterion("CMT_NUM <>", value, "cmtNum");
            return (Criteria) this;
        }

        public Criteria andCmtNumGreaterThan(Integer value) {
            addCriterion("CMT_NUM >", value, "cmtNum");
            return (Criteria) this;
        }

        public Criteria andCmtNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("CMT_NUM >=", value, "cmtNum");
            return (Criteria) this;
        }

        public Criteria andCmtNumLessThan(Integer value) {
            addCriterion("CMT_NUM <", value, "cmtNum");
            return (Criteria) this;
        }

        public Criteria andCmtNumLessThanOrEqualTo(Integer value) {
            addCriterion("CMT_NUM <=", value, "cmtNum");
            return (Criteria) this;
        }

        public Criteria andCmtNumIn(List<Integer> values) {
            addCriterion("CMT_NUM in", values, "cmtNum");
            return (Criteria) this;
        }

        public Criteria andCmtNumNotIn(List<Integer> values) {
            addCriterion("CMT_NUM not in", values, "cmtNum");
            return (Criteria) this;
        }

        public Criteria andCmtNumBetween(Integer value1, Integer value2) {
            addCriterion("CMT_NUM between", value1, value2, "cmtNum");
            return (Criteria) this;
        }

        public Criteria andCmtNumNotBetween(Integer value1, Integer value2) {
            addCriterion("CMT_NUM not between", value1, value2, "cmtNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumIsNull() {
            addCriterion("VOTE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andVoteNumIsNotNull() {
            addCriterion("VOTE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andVoteNumEqualTo(Integer value) {
            addCriterion("VOTE_NUM =", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumNotEqualTo(Integer value) {
            addCriterion("VOTE_NUM <>", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumGreaterThan(Integer value) {
            addCriterion("VOTE_NUM >", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOTE_NUM >=", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumLessThan(Integer value) {
            addCriterion("VOTE_NUM <", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumLessThanOrEqualTo(Integer value) {
            addCriterion("VOTE_NUM <=", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumIn(List<Integer> values) {
            addCriterion("VOTE_NUM in", values, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumNotIn(List<Integer> values) {
            addCriterion("VOTE_NUM not in", values, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_NUM between", value1, value2, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumNotBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_NUM not between", value1, value2, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeIsNull() {
            addCriterion("VOTE_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeIsNotNull() {
            addCriterion("VOTE_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeEqualTo(Date value) {
            addCriterion("VOTE_START_TIME =", value, "voteStartTime");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeNotEqualTo(Date value) {
            addCriterion("VOTE_START_TIME <>", value, "voteStartTime");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeGreaterThan(Date value) {
            addCriterion("VOTE_START_TIME >", value, "voteStartTime");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("VOTE_START_TIME >=", value, "voteStartTime");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeLessThan(Date value) {
            addCriterion("VOTE_START_TIME <", value, "voteStartTime");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("VOTE_START_TIME <=", value, "voteStartTime");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeIn(List<Date> values) {
            addCriterion("VOTE_START_TIME in", values, "voteStartTime");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeNotIn(List<Date> values) {
            addCriterion("VOTE_START_TIME not in", values, "voteStartTime");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeBetween(Date value1, Date value2) {
            addCriterion("VOTE_START_TIME between", value1, value2, "voteStartTime");
            return (Criteria) this;
        }

        public Criteria andVoteStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("VOTE_START_TIME not between", value1, value2, "voteStartTime");
            return (Criteria) this;
        }

        public Criteria andFlagRecoIsNull() {
            addCriterion("FLAG_RECO is null");
            return (Criteria) this;
        }

        public Criteria andFlagRecoIsNotNull() {
            addCriterion("FLAG_RECO is not null");
            return (Criteria) this;
        }

        public Criteria andFlagRecoEqualTo(Integer value) {
            addCriterion("FLAG_RECO =", value, "flagReco");
            return (Criteria) this;
        }

        public Criteria andFlagRecoNotEqualTo(Integer value) {
            addCriterion("FLAG_RECO <>", value, "flagReco");
            return (Criteria) this;
        }

        public Criteria andFlagRecoGreaterThan(Integer value) {
            addCriterion("FLAG_RECO >", value, "flagReco");
            return (Criteria) this;
        }

        public Criteria andFlagRecoGreaterThanOrEqualTo(Integer value) {
            addCriterion("FLAG_RECO >=", value, "flagReco");
            return (Criteria) this;
        }

        public Criteria andFlagRecoLessThan(Integer value) {
            addCriterion("FLAG_RECO <", value, "flagReco");
            return (Criteria) this;
        }

        public Criteria andFlagRecoLessThanOrEqualTo(Integer value) {
            addCriterion("FLAG_RECO <=", value, "flagReco");
            return (Criteria) this;
        }

        public Criteria andFlagRecoIn(List<Integer> values) {
            addCriterion("FLAG_RECO in", values, "flagReco");
            return (Criteria) this;
        }

        public Criteria andFlagRecoNotIn(List<Integer> values) {
            addCriterion("FLAG_RECO not in", values, "flagReco");
            return (Criteria) this;
        }

        public Criteria andFlagRecoBetween(Integer value1, Integer value2) {
            addCriterion("FLAG_RECO between", value1, value2, "flagReco");
            return (Criteria) this;
        }

        public Criteria andFlagRecoNotBetween(Integer value1, Integer value2) {
            addCriterion("FLAG_RECO not between", value1, value2, "flagReco");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagIsNull() {
            addCriterion("INSTEAD_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagIsNotNull() {
            addCriterion("INSTEAD_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagEqualTo(Integer value) {
            addCriterion("INSTEAD_FLAG =", value, "insteadFlag");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagNotEqualTo(Integer value) {
            addCriterion("INSTEAD_FLAG <>", value, "insteadFlag");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagGreaterThan(Integer value) {
            addCriterion("INSTEAD_FLAG >", value, "insteadFlag");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("INSTEAD_FLAG >=", value, "insteadFlag");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagLessThan(Integer value) {
            addCriterion("INSTEAD_FLAG <", value, "insteadFlag");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagLessThanOrEqualTo(Integer value) {
            addCriterion("INSTEAD_FLAG <=", value, "insteadFlag");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagIn(List<Integer> values) {
            addCriterion("INSTEAD_FLAG in", values, "insteadFlag");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagNotIn(List<Integer> values) {
            addCriterion("INSTEAD_FLAG not in", values, "insteadFlag");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagBetween(Integer value1, Integer value2) {
            addCriterion("INSTEAD_FLAG between", value1, value2, "insteadFlag");
            return (Criteria) this;
        }

        public Criteria andInsteadFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("INSTEAD_FLAG not between", value1, value2, "insteadFlag");
            return (Criteria) this;
        }

        public Criteria andInsteadUserIsNull() {
            addCriterion("INSTEAD_USER is null");
            return (Criteria) this;
        }

        public Criteria andInsteadUserIsNotNull() {
            addCriterion("INSTEAD_USER is not null");
            return (Criteria) this;
        }

        public Criteria andInsteadUserEqualTo(Integer value) {
            addCriterion("INSTEAD_USER =", value, "insteadUser");
            return (Criteria) this;
        }

        public Criteria andInsteadUserNotEqualTo(Integer value) {
            addCriterion("INSTEAD_USER <>", value, "insteadUser");
            return (Criteria) this;
        }

        public Criteria andInsteadUserGreaterThan(Integer value) {
            addCriterion("INSTEAD_USER >", value, "insteadUser");
            return (Criteria) this;
        }

        public Criteria andInsteadUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("INSTEAD_USER >=", value, "insteadUser");
            return (Criteria) this;
        }

        public Criteria andInsteadUserLessThan(Integer value) {
            addCriterion("INSTEAD_USER <", value, "insteadUser");
            return (Criteria) this;
        }

        public Criteria andInsteadUserLessThanOrEqualTo(Integer value) {
            addCriterion("INSTEAD_USER <=", value, "insteadUser");
            return (Criteria) this;
        }

        public Criteria andInsteadUserIn(List<Integer> values) {
            addCriterion("INSTEAD_USER in", values, "insteadUser");
            return (Criteria) this;
        }

        public Criteria andInsteadUserNotIn(List<Integer> values) {
            addCriterion("INSTEAD_USER not in", values, "insteadUser");
            return (Criteria) this;
        }

        public Criteria andInsteadUserBetween(Integer value1, Integer value2) {
            addCriterion("INSTEAD_USER between", value1, value2, "insteadUser");
            return (Criteria) this;
        }

        public Criteria andInsteadUserNotBetween(Integer value1, Integer value2) {
            addCriterion("INSTEAD_USER not between", value1, value2, "insteadUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andVoteDomainIsNull() {
            addCriterion("VOTE_DOMAIN is null");
            return (Criteria) this;
        }

        public Criteria andVoteDomainIsNotNull() {
            addCriterion("VOTE_DOMAIN is not null");
            return (Criteria) this;
        }

        public Criteria andVoteDomainEqualTo(Integer value) {
            addCriterion("VOTE_DOMAIN =", value, "voteDomain");
            return (Criteria) this;
        }

        public Criteria andVoteDomainNotEqualTo(Integer value) {
            addCriterion("VOTE_DOMAIN <>", value, "voteDomain");
            return (Criteria) this;
        }

        public Criteria andVoteDomainGreaterThan(Integer value) {
            addCriterion("VOTE_DOMAIN >", value, "voteDomain");
            return (Criteria) this;
        }

        public Criteria andVoteDomainGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOTE_DOMAIN >=", value, "voteDomain");
            return (Criteria) this;
        }

        public Criteria andVoteDomainLessThan(Integer value) {
            addCriterion("VOTE_DOMAIN <", value, "voteDomain");
            return (Criteria) this;
        }

        public Criteria andVoteDomainLessThanOrEqualTo(Integer value) {
            addCriterion("VOTE_DOMAIN <=", value, "voteDomain");
            return (Criteria) this;
        }

        public Criteria andVoteDomainIn(List<Integer> values) {
            addCriterion("VOTE_DOMAIN in", values, "voteDomain");
            return (Criteria) this;
        }

        public Criteria andVoteDomainNotIn(List<Integer> values) {
            addCriterion("VOTE_DOMAIN not in", values, "voteDomain");
            return (Criteria) this;
        }

        public Criteria andVoteDomainBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_DOMAIN between", value1, value2, "voteDomain");
            return (Criteria) this;
        }

        public Criteria andVoteDomainNotBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_DOMAIN not between", value1, value2, "voteDomain");
            return (Criteria) this;
        }

        public Criteria andVoteFlagIsNull() {
            addCriterion("VOTE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andVoteFlagIsNotNull() {
            addCriterion("VOTE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andVoteFlagEqualTo(Integer value) {
            addCriterion("VOTE_FLAG =", value, "voteFlag");
            return (Criteria) this;
        }

        public Criteria andVoteFlagNotEqualTo(Integer value) {
            addCriterion("VOTE_FLAG <>", value, "voteFlag");
            return (Criteria) this;
        }

        public Criteria andVoteFlagGreaterThan(Integer value) {
            addCriterion("VOTE_FLAG >", value, "voteFlag");
            return (Criteria) this;
        }

        public Criteria andVoteFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOTE_FLAG >=", value, "voteFlag");
            return (Criteria) this;
        }

        public Criteria andVoteFlagLessThan(Integer value) {
            addCriterion("VOTE_FLAG <", value, "voteFlag");
            return (Criteria) this;
        }

        public Criteria andVoteFlagLessThanOrEqualTo(Integer value) {
            addCriterion("VOTE_FLAG <=", value, "voteFlag");
            return (Criteria) this;
        }

        public Criteria andVoteFlagIn(List<Integer> values) {
            addCriterion("VOTE_FLAG in", values, "voteFlag");
            return (Criteria) this;
        }

        public Criteria andVoteFlagNotIn(List<Integer> values) {
            addCriterion("VOTE_FLAG not in", values, "voteFlag");
            return (Criteria) this;
        }

        public Criteria andVoteFlagBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_FLAG between", value1, value2, "voteFlag");
            return (Criteria) this;
        }

        public Criteria andVoteFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_FLAG not between", value1, value2, "voteFlag");
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