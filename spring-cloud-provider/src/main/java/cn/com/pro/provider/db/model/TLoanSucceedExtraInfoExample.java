package cn.com.pro.provider.db.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TLoanSucceedExtraInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TLoanSucceedExtraInfoExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFundIdIsNull() {
            addCriterion("fund_id is null");
            return (Criteria) this;
        }

        public Criteria andFundIdIsNotNull() {
            addCriterion("fund_id is not null");
            return (Criteria) this;
        }

        public Criteria andFundIdEqualTo(String value) {
            addCriterion("fund_id =", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdNotEqualTo(String value) {
            addCriterion("fund_id <>", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdGreaterThan(String value) {
            addCriterion("fund_id >", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdGreaterThanOrEqualTo(String value) {
            addCriterion("fund_id >=", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdLessThan(String value) {
            addCriterion("fund_id <", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdLessThanOrEqualTo(String value) {
            addCriterion("fund_id <=", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdLike(String value) {
            addCriterion("fund_id like", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdNotLike(String value) {
            addCriterion("fund_id not like", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdIn(List<String> values) {
            addCriterion("fund_id in", values, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdNotIn(List<String> values) {
            addCriterion("fund_id not in", values, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdBetween(String value1, String value2) {
            addCriterion("fund_id between", value1, value2, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdNotBetween(String value1, String value2) {
            addCriterion("fund_id not between", value1, value2, "fundId");
            return (Criteria) this;
        }

        public Criteria andLoanNoIsNull() {
            addCriterion("loan_no is null");
            return (Criteria) this;
        }

        public Criteria andLoanNoIsNotNull() {
            addCriterion("loan_no is not null");
            return (Criteria) this;
        }

        public Criteria andLoanNoEqualTo(String value) {
            addCriterion("loan_no =", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoNotEqualTo(String value) {
            addCriterion("loan_no <>", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoGreaterThan(String value) {
            addCriterion("loan_no >", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoGreaterThanOrEqualTo(String value) {
            addCriterion("loan_no >=", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoLessThan(String value) {
            addCriterion("loan_no <", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoLessThanOrEqualTo(String value) {
            addCriterion("loan_no <=", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoLike(String value) {
            addCriterion("loan_no like", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoNotLike(String value) {
            addCriterion("loan_no not like", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoIn(List<String> values) {
            addCriterion("loan_no in", values, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoNotIn(List<String> values) {
            addCriterion("loan_no not in", values, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoBetween(String value1, String value2) {
            addCriterion("loan_no between", value1, value2, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoNotBetween(String value1, String value2) {
            addCriterion("loan_no not between", value1, value2, "loanNo");
            return (Criteria) this;
        }

        public Criteria andIouNoIsNull() {
            addCriterion("iou_no is null");
            return (Criteria) this;
        }

        public Criteria andIouNoIsNotNull() {
            addCriterion("iou_no is not null");
            return (Criteria) this;
        }

        public Criteria andIouNoEqualTo(String value) {
            addCriterion("iou_no =", value, "iouNo");
            return (Criteria) this;
        }

        public Criteria andIouNoNotEqualTo(String value) {
            addCriterion("iou_no <>", value, "iouNo");
            return (Criteria) this;
        }

        public Criteria andIouNoGreaterThan(String value) {
            addCriterion("iou_no >", value, "iouNo");
            return (Criteria) this;
        }

        public Criteria andIouNoGreaterThanOrEqualTo(String value) {
            addCriterion("iou_no >=", value, "iouNo");
            return (Criteria) this;
        }

        public Criteria andIouNoLessThan(String value) {
            addCriterion("iou_no <", value, "iouNo");
            return (Criteria) this;
        }

        public Criteria andIouNoLessThanOrEqualTo(String value) {
            addCriterion("iou_no <=", value, "iouNo");
            return (Criteria) this;
        }

        public Criteria andIouNoLike(String value) {
            addCriterion("iou_no like", value, "iouNo");
            return (Criteria) this;
        }

        public Criteria andIouNoNotLike(String value) {
            addCriterion("iou_no not like", value, "iouNo");
            return (Criteria) this;
        }

        public Criteria andIouNoIn(List<String> values) {
            addCriterion("iou_no in", values, "iouNo");
            return (Criteria) this;
        }

        public Criteria andIouNoNotIn(List<String> values) {
            addCriterion("iou_no not in", values, "iouNo");
            return (Criteria) this;
        }

        public Criteria andIouNoBetween(String value1, String value2) {
            addCriterion("iou_no between", value1, value2, "iouNo");
            return (Criteria) this;
        }

        public Criteria andIouNoNotBetween(String value1, String value2) {
            addCriterion("iou_no not between", value1, value2, "iouNo");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateIsNull() {
            addCriterion("penalty_year_rate is null");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateIsNotNull() {
            addCriterion("penalty_year_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateEqualTo(BigDecimal value) {
            addCriterion("penalty_year_rate =", value, "penaltyYearRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateNotEqualTo(BigDecimal value) {
            addCriterion("penalty_year_rate <>", value, "penaltyYearRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateGreaterThan(BigDecimal value) {
            addCriterion("penalty_year_rate >", value, "penaltyYearRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty_year_rate >=", value, "penaltyYearRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateLessThan(BigDecimal value) {
            addCriterion("penalty_year_rate <", value, "penaltyYearRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty_year_rate <=", value, "penaltyYearRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateIn(List<BigDecimal> values) {
            addCriterion("penalty_year_rate in", values, "penaltyYearRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateNotIn(List<BigDecimal> values) {
            addCriterion("penalty_year_rate not in", values, "penaltyYearRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty_year_rate between", value1, value2, "penaltyYearRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyYearRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty_year_rate not between", value1, value2, "penaltyYearRate");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateIsNull() {
            addCriterion("compound_year_rate is null");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateIsNotNull() {
            addCriterion("compound_year_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateEqualTo(BigDecimal value) {
            addCriterion("compound_year_rate =", value, "compoundYearRate");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateNotEqualTo(BigDecimal value) {
            addCriterion("compound_year_rate <>", value, "compoundYearRate");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateGreaterThan(BigDecimal value) {
            addCriterion("compound_year_rate >", value, "compoundYearRate");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("compound_year_rate >=", value, "compoundYearRate");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateLessThan(BigDecimal value) {
            addCriterion("compound_year_rate <", value, "compoundYearRate");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("compound_year_rate <=", value, "compoundYearRate");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateIn(List<BigDecimal> values) {
            addCriterion("compound_year_rate in", values, "compoundYearRate");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateNotIn(List<BigDecimal> values) {
            addCriterion("compound_year_rate not in", values, "compoundYearRate");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("compound_year_rate between", value1, value2, "compoundYearRate");
            return (Criteria) this;
        }

        public Criteria andCompoundYearRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("compound_year_rate not between", value1, value2, "compoundYearRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateIsNull() {
            addCriterion("penalty_day_rate is null");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateIsNotNull() {
            addCriterion("penalty_day_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateEqualTo(BigDecimal value) {
            addCriterion("penalty_day_rate =", value, "penaltyDayRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateNotEqualTo(BigDecimal value) {
            addCriterion("penalty_day_rate <>", value, "penaltyDayRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateGreaterThan(BigDecimal value) {
            addCriterion("penalty_day_rate >", value, "penaltyDayRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty_day_rate >=", value, "penaltyDayRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateLessThan(BigDecimal value) {
            addCriterion("penalty_day_rate <", value, "penaltyDayRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty_day_rate <=", value, "penaltyDayRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateIn(List<BigDecimal> values) {
            addCriterion("penalty_day_rate in", values, "penaltyDayRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateNotIn(List<BigDecimal> values) {
            addCriterion("penalty_day_rate not in", values, "penaltyDayRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty_day_rate between", value1, value2, "penaltyDayRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyDayRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty_day_rate not between", value1, value2, "penaltyDayRate");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateIsNull() {
            addCriterion("compound_day_rate is null");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateIsNotNull() {
            addCriterion("compound_day_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateEqualTo(BigDecimal value) {
            addCriterion("compound_day_rate =", value, "compoundDayRate");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateNotEqualTo(BigDecimal value) {
            addCriterion("compound_day_rate <>", value, "compoundDayRate");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateGreaterThan(BigDecimal value) {
            addCriterion("compound_day_rate >", value, "compoundDayRate");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("compound_day_rate >=", value, "compoundDayRate");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateLessThan(BigDecimal value) {
            addCriterion("compound_day_rate <", value, "compoundDayRate");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("compound_day_rate <=", value, "compoundDayRate");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateIn(List<BigDecimal> values) {
            addCriterion("compound_day_rate in", values, "compoundDayRate");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateNotIn(List<BigDecimal> values) {
            addCriterion("compound_day_rate not in", values, "compoundDayRate");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("compound_day_rate between", value1, value2, "compoundDayRate");
            return (Criteria) this;
        }

        public Criteria andCompoundDayRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("compound_day_rate not between", value1, value2, "compoundDayRate");
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

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
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

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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