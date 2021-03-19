package net.xiaobais.xiaobai.model;

import java.util.ArrayList;
import java.util.List;

public class SuggestExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SuggestExample() {
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

        public Criteria andSuggestIdIsNull() {
            addCriterion("suggest_id is null");
            return (Criteria) this;
        }

        public Criteria andSuggestIdIsNotNull() {
            addCriterion("suggest_id is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestIdEqualTo(Integer value) {
            addCriterion("suggest_id =", value, "suggestId");
            return (Criteria) this;
        }

        public Criteria andSuggestIdNotEqualTo(Integer value) {
            addCriterion("suggest_id <>", value, "suggestId");
            return (Criteria) this;
        }

        public Criteria andSuggestIdGreaterThan(Integer value) {
            addCriterion("suggest_id >", value, "suggestId");
            return (Criteria) this;
        }

        public Criteria andSuggestIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("suggest_id >=", value, "suggestId");
            return (Criteria) this;
        }

        public Criteria andSuggestIdLessThan(Integer value) {
            addCriterion("suggest_id <", value, "suggestId");
            return (Criteria) this;
        }

        public Criteria andSuggestIdLessThanOrEqualTo(Integer value) {
            addCriterion("suggest_id <=", value, "suggestId");
            return (Criteria) this;
        }

        public Criteria andSuggestIdIn(List<Integer> values) {
            addCriterion("suggest_id in", values, "suggestId");
            return (Criteria) this;
        }

        public Criteria andSuggestIdNotIn(List<Integer> values) {
            addCriterion("suggest_id not in", values, "suggestId");
            return (Criteria) this;
        }

        public Criteria andSuggestIdBetween(Integer value1, Integer value2) {
            addCriterion("suggest_id between", value1, value2, "suggestId");
            return (Criteria) this;
        }

        public Criteria andSuggestIdNotBetween(Integer value1, Integer value2) {
            addCriterion("suggest_id not between", value1, value2, "suggestId");
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andChoiceIsNull() {
            addCriterion("choice is null");
            return (Criteria) this;
        }

        public Criteria andChoiceIsNotNull() {
            addCriterion("choice is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceEqualTo(String value) {
            addCriterion("choice =", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceNotEqualTo(String value) {
            addCriterion("choice <>", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceGreaterThan(String value) {
            addCriterion("choice >", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceGreaterThanOrEqualTo(String value) {
            addCriterion("choice >=", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceLessThan(String value) {
            addCriterion("choice <", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceLessThanOrEqualTo(String value) {
            addCriterion("choice <=", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceLike(String value) {
            addCriterion("choice like", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceNotLike(String value) {
            addCriterion("choice not like", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceIn(List<String> values) {
            addCriterion("choice in", values, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceNotIn(List<String> values) {
            addCriterion("choice not in", values, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceBetween(String value1, String value2) {
            addCriterion("choice between", value1, value2, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceNotBetween(String value1, String value2) {
            addCriterion("choice not between", value1, value2, "choice");
            return (Criteria) this;
        }

        public Criteria andQuestionIsNull() {
            addCriterion("question is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIsNotNull() {
            addCriterion("question is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionEqualTo(String value) {
            addCriterion("question =", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotEqualTo(String value) {
            addCriterion("question <>", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionGreaterThan(String value) {
            addCriterion("question >", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("question >=", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLessThan(String value) {
            addCriterion("question <", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLessThanOrEqualTo(String value) {
            addCriterion("question <=", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLike(String value) {
            addCriterion("question like", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotLike(String value) {
            addCriterion("question not like", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionIn(List<String> values) {
            addCriterion("question in", values, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotIn(List<String> values) {
            addCriterion("question not in", values, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionBetween(String value1, String value2) {
            addCriterion("question between", value1, value2, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotBetween(String value1, String value2) {
            addCriterion("question not between", value1, value2, "question");
            return (Criteria) this;
        }

        public Criteria andExtendIsNull() {
            addCriterion("extend is null");
            return (Criteria) this;
        }

        public Criteria andExtendIsNotNull() {
            addCriterion("extend is not null");
            return (Criteria) this;
        }

        public Criteria andExtendEqualTo(String value) {
            addCriterion("extend =", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendNotEqualTo(String value) {
            addCriterion("extend <>", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendGreaterThan(String value) {
            addCriterion("extend >", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendGreaterThanOrEqualTo(String value) {
            addCriterion("extend >=", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendLessThan(String value) {
            addCriterion("extend <", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendLessThanOrEqualTo(String value) {
            addCriterion("extend <=", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendLike(String value) {
            addCriterion("extend like", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendNotLike(String value) {
            addCriterion("extend not like", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendIn(List<String> values) {
            addCriterion("extend in", values, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendNotIn(List<String> values) {
            addCriterion("extend not in", values, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendBetween(String value1, String value2) {
            addCriterion("extend between", value1, value2, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendNotBetween(String value1, String value2) {
            addCriterion("extend not between", value1, value2, "extend");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionIsNull() {
            addCriterion("previous_question is null");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionIsNotNull() {
            addCriterion("previous_question is not null");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionEqualTo(String value) {
            addCriterion("previous_question =", value, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionNotEqualTo(String value) {
            addCriterion("previous_question <>", value, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionGreaterThan(String value) {
            addCriterion("previous_question >", value, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("previous_question >=", value, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionLessThan(String value) {
            addCriterion("previous_question <", value, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionLessThanOrEqualTo(String value) {
            addCriterion("previous_question <=", value, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionLike(String value) {
            addCriterion("previous_question like", value, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionNotLike(String value) {
            addCriterion("previous_question not like", value, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionIn(List<String> values) {
            addCriterion("previous_question in", values, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionNotIn(List<String> values) {
            addCriterion("previous_question not in", values, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionBetween(String value1, String value2) {
            addCriterion("previous_question between", value1, value2, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andPreviousQuestionNotBetween(String value1, String value2) {
            addCriterion("previous_question not between", value1, value2, "previousQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionIsNull() {
            addCriterion("next_question is null");
            return (Criteria) this;
        }

        public Criteria andNextQuestionIsNotNull() {
            addCriterion("next_question is not null");
            return (Criteria) this;
        }

        public Criteria andNextQuestionEqualTo(String value) {
            addCriterion("next_question =", value, "nextQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionNotEqualTo(String value) {
            addCriterion("next_question <>", value, "nextQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionGreaterThan(String value) {
            addCriterion("next_question >", value, "nextQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("next_question >=", value, "nextQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionLessThan(String value) {
            addCriterion("next_question <", value, "nextQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionLessThanOrEqualTo(String value) {
            addCriterion("next_question <=", value, "nextQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionLike(String value) {
            addCriterion("next_question like", value, "nextQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionNotLike(String value) {
            addCriterion("next_question not like", value, "nextQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionIn(List<String> values) {
            addCriterion("next_question in", values, "nextQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionNotIn(List<String> values) {
            addCriterion("next_question not in", values, "nextQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionBetween(String value1, String value2) {
            addCriterion("next_question between", value1, value2, "nextQuestion");
            return (Criteria) this;
        }

        public Criteria andNextQuestionNotBetween(String value1, String value2) {
            addCriterion("next_question not between", value1, value2, "nextQuestion");
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