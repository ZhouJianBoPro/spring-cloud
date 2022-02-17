package cn.com.pro.provider.db.model;

import java.util.ArrayList;
import java.util.List;

public class FundNodeConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundNodeConfigExample() {
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

        public Criteria andNodeCodeIsNull() {
            addCriterion("node_code is null");
            return (Criteria) this;
        }

        public Criteria andNodeCodeIsNotNull() {
            addCriterion("node_code is not null");
            return (Criteria) this;
        }

        public Criteria andNodeCodeEqualTo(String value) {
            addCriterion("node_code =", value, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andNodeCodeNotEqualTo(String value) {
            addCriterion("node_code <>", value, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andNodeCodeGreaterThan(String value) {
            addCriterion("node_code >", value, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andNodeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("node_code >=", value, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andNodeCodeLessThan(String value) {
            addCriterion("node_code <", value, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andNodeCodeLessThanOrEqualTo(String value) {
            addCriterion("node_code <=", value, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andNodeCodeLike(String value) {
            addCriterion("node_code like", value, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andNodeCodeNotLike(String value) {
            addCriterion("node_code not like", value, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andNodeCodeIn(List<String> values) {
            addCriterion("node_code in", values, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andNodeCodeNotIn(List<String> values) {
            addCriterion("node_code not in", values, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andNodeCodeBetween(String value1, String value2) {
            addCriterion("node_code between", value1, value2, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andNodeCodeNotBetween(String value1, String value2) {
            addCriterion("node_code not between", value1, value2, "nodeCode");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumIsNull() {
            addCriterion("max_concurrent_num is null");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumIsNotNull() {
            addCriterion("max_concurrent_num is not null");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumEqualTo(Integer value) {
            addCriterion("max_concurrent_num =", value, "maxConcurrentNum");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumNotEqualTo(Integer value) {
            addCriterion("max_concurrent_num <>", value, "maxConcurrentNum");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumGreaterThan(Integer value) {
            addCriterion("max_concurrent_num >", value, "maxConcurrentNum");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_concurrent_num >=", value, "maxConcurrentNum");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumLessThan(Integer value) {
            addCriterion("max_concurrent_num <", value, "maxConcurrentNum");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumLessThanOrEqualTo(Integer value) {
            addCriterion("max_concurrent_num <=", value, "maxConcurrentNum");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumIn(List<Integer> values) {
            addCriterion("max_concurrent_num in", values, "maxConcurrentNum");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumNotIn(List<Integer> values) {
            addCriterion("max_concurrent_num not in", values, "maxConcurrentNum");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumBetween(Integer value1, Integer value2) {
            addCriterion("max_concurrent_num between", value1, value2, "maxConcurrentNum");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("max_concurrent_num not between", value1, value2, "maxConcurrentNum");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountIsNull() {
            addCriterion("fail_retry_count is null");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountIsNotNull() {
            addCriterion("fail_retry_count is not null");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountEqualTo(Integer value) {
            addCriterion("fail_retry_count =", value, "failRetryCount");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountNotEqualTo(Integer value) {
            addCriterion("fail_retry_count <>", value, "failRetryCount");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountGreaterThan(Integer value) {
            addCriterion("fail_retry_count >", value, "failRetryCount");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("fail_retry_count >=", value, "failRetryCount");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountLessThan(Integer value) {
            addCriterion("fail_retry_count <", value, "failRetryCount");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountLessThanOrEqualTo(Integer value) {
            addCriterion("fail_retry_count <=", value, "failRetryCount");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountIn(List<Integer> values) {
            addCriterion("fail_retry_count in", values, "failRetryCount");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountNotIn(List<Integer> values) {
            addCriterion("fail_retry_count not in", values, "failRetryCount");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountBetween(Integer value1, Integer value2) {
            addCriterion("fail_retry_count between", value1, value2, "failRetryCount");
            return (Criteria) this;
        }

        public Criteria andFailRetryCountNotBetween(Integer value1, Integer value2) {
            addCriterion("fail_retry_count not between", value1, value2, "failRetryCount");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelIsNull() {
            addCriterion("init_delay_level is null");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelIsNotNull() {
            addCriterion("init_delay_level is not null");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelEqualTo(Integer value) {
            addCriterion("init_delay_level =", value, "initDelayLevel");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelNotEqualTo(Integer value) {
            addCriterion("init_delay_level <>", value, "initDelayLevel");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelGreaterThan(Integer value) {
            addCriterion("init_delay_level >", value, "initDelayLevel");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("init_delay_level >=", value, "initDelayLevel");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelLessThan(Integer value) {
            addCriterion("init_delay_level <", value, "initDelayLevel");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelLessThanOrEqualTo(Integer value) {
            addCriterion("init_delay_level <=", value, "initDelayLevel");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelIn(List<Integer> values) {
            addCriterion("init_delay_level in", values, "initDelayLevel");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelNotIn(List<Integer> values) {
            addCriterion("init_delay_level not in", values, "initDelayLevel");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelBetween(Integer value1, Integer value2) {
            addCriterion("init_delay_level between", value1, value2, "initDelayLevel");
            return (Criteria) this;
        }

        public Criteria andInitDelayLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("init_delay_level not between", value1, value2, "initDelayLevel");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelIsNull() {
            addCriterion("retry_delay_level is null");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelIsNotNull() {
            addCriterion("retry_delay_level is not null");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelEqualTo(Integer value) {
            addCriterion("retry_delay_level =", value, "retryDelayLevel");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelNotEqualTo(Integer value) {
            addCriterion("retry_delay_level <>", value, "retryDelayLevel");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelGreaterThan(Integer value) {
            addCriterion("retry_delay_level >", value, "retryDelayLevel");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("retry_delay_level >=", value, "retryDelayLevel");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelLessThan(Integer value) {
            addCriterion("retry_delay_level <", value, "retryDelayLevel");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelLessThanOrEqualTo(Integer value) {
            addCriterion("retry_delay_level <=", value, "retryDelayLevel");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelIn(List<Integer> values) {
            addCriterion("retry_delay_level in", values, "retryDelayLevel");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelNotIn(List<Integer> values) {
            addCriterion("retry_delay_level not in", values, "retryDelayLevel");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelBetween(Integer value1, Integer value2) {
            addCriterion("retry_delay_level between", value1, value2, "retryDelayLevel");
            return (Criteria) this;
        }

        public Criteria andRetryDelayLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("retry_delay_level not between", value1, value2, "retryDelayLevel");
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