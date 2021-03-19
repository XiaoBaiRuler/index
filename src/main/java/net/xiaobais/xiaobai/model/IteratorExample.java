package net.xiaobais.xiaobai.model;

import java.util.ArrayList;
import java.util.List;

public class IteratorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IteratorExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIteratorIdIsNull() {
            addCriterion("iterator_id is null");
            return (Criteria) this;
        }

        public Criteria andIteratorIdIsNotNull() {
            addCriterion("iterator_id is not null");
            return (Criteria) this;
        }

        public Criteria andIteratorIdEqualTo(Integer value) {
            addCriterion("iterator_id =", value, "iteratorId");
            return (Criteria) this;
        }

        public Criteria andIteratorIdNotEqualTo(Integer value) {
            addCriterion("iterator_id <>", value, "iteratorId");
            return (Criteria) this;
        }

        public Criteria andIteratorIdGreaterThan(Integer value) {
            addCriterion("iterator_id >", value, "iteratorId");
            return (Criteria) this;
        }

        public Criteria andIteratorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("iterator_id >=", value, "iteratorId");
            return (Criteria) this;
        }

        public Criteria andIteratorIdLessThan(Integer value) {
            addCriterion("iterator_id <", value, "iteratorId");
            return (Criteria) this;
        }

        public Criteria andIteratorIdLessThanOrEqualTo(Integer value) {
            addCriterion("iterator_id <=", value, "iteratorId");
            return (Criteria) this;
        }

        public Criteria andIteratorIdIn(List<Integer> values) {
            addCriterion("iterator_id in", values, "iteratorId");
            return (Criteria) this;
        }

        public Criteria andIteratorIdNotIn(List<Integer> values) {
            addCriterion("iterator_id not in", values, "iteratorId");
            return (Criteria) this;
        }

        public Criteria andIteratorIdBetween(Integer value1, Integer value2) {
            addCriterion("iterator_id between", value1, value2, "iteratorId");
            return (Criteria) this;
        }

        public Criteria andIteratorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("iterator_id not between", value1, value2, "iteratorId");
            return (Criteria) this;
        }

        public Criteria andNodeIdIsNull() {
            addCriterion("node_id is null");
            return (Criteria) this;
        }

        public Criteria andNodeIdIsNotNull() {
            addCriterion("node_id is not null");
            return (Criteria) this;
        }

        public Criteria andNodeIdEqualTo(Integer value) {
            addCriterion("node_id =", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotEqualTo(Integer value) {
            addCriterion("node_id <>", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThan(Integer value) {
            addCriterion("node_id >", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("node_id >=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThan(Integer value) {
            addCriterion("node_id <", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThanOrEqualTo(Integer value) {
            addCriterion("node_id <=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdIn(List<Integer> values) {
            addCriterion("node_id in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotIn(List<Integer> values) {
            addCriterion("node_id not in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdBetween(Integer value1, Integer value2) {
            addCriterion("node_id between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("node_id not between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andIteratorNameIsNull() {
            addCriterion("iterator_name is null");
            return (Criteria) this;
        }

        public Criteria andIteratorNameIsNotNull() {
            addCriterion("iterator_name is not null");
            return (Criteria) this;
        }

        public Criteria andIteratorNameEqualTo(String value) {
            addCriterion("iterator_name =", value, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorNameNotEqualTo(String value) {
            addCriterion("iterator_name <>", value, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorNameGreaterThan(String value) {
            addCriterion("iterator_name >", value, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorNameGreaterThanOrEqualTo(String value) {
            addCriterion("iterator_name >=", value, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorNameLessThan(String value) {
            addCriterion("iterator_name <", value, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorNameLessThanOrEqualTo(String value) {
            addCriterion("iterator_name <=", value, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorNameLike(String value) {
            addCriterion("iterator_name like", value, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorNameNotLike(String value) {
            addCriterion("iterator_name not like", value, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorNameIn(List<String> values) {
            addCriterion("iterator_name in", values, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorNameNotIn(List<String> values) {
            addCriterion("iterator_name not in", values, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorNameBetween(String value1, String value2) {
            addCriterion("iterator_name between", value1, value2, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorNameNotBetween(String value1, String value2) {
            addCriterion("iterator_name not between", value1, value2, "iteratorName");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonIsNull() {
            addCriterion("iterator_reason is null");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonIsNotNull() {
            addCriterion("iterator_reason is not null");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonEqualTo(String value) {
            addCriterion("iterator_reason =", value, "iteratorReason");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonNotEqualTo(String value) {
            addCriterion("iterator_reason <>", value, "iteratorReason");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonGreaterThan(String value) {
            addCriterion("iterator_reason >", value, "iteratorReason");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonGreaterThanOrEqualTo(String value) {
            addCriterion("iterator_reason >=", value, "iteratorReason");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonLessThan(String value) {
            addCriterion("iterator_reason <", value, "iteratorReason");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonLessThanOrEqualTo(String value) {
            addCriterion("iterator_reason <=", value, "iteratorReason");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonLike(String value) {
            addCriterion("iterator_reason like", value, "iteratorReason");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonNotLike(String value) {
            addCriterion("iterator_reason not like", value, "iteratorReason");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonIn(List<String> values) {
            addCriterion("iterator_reason in", values, "iteratorReason");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonNotIn(List<String> values) {
            addCriterion("iterator_reason not in", values, "iteratorReason");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonBetween(String value1, String value2) {
            addCriterion("iterator_reason between", value1, value2, "iteratorReason");
            return (Criteria) this;
        }

        public Criteria andIteratorReasonNotBetween(String value1, String value2) {
            addCriterion("iterator_reason not between", value1, value2, "iteratorReason");
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