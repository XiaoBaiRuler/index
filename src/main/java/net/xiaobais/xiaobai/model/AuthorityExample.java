package net.xiaobais.xiaobai.model;

import java.util.ArrayList;
import java.util.List;

public class AuthorityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AuthorityExample() {
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

        public Criteria andAuthorityIdIsNull() {
            addCriterion("authority_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdIsNotNull() {
            addCriterion("authority_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdEqualTo(Integer value) {
            addCriterion("authority_id =", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdNotEqualTo(Integer value) {
            addCriterion("authority_id <>", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdGreaterThan(Integer value) {
            addCriterion("authority_id >", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("authority_id >=", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdLessThan(Integer value) {
            addCriterion("authority_id <", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdLessThanOrEqualTo(Integer value) {
            addCriterion("authority_id <=", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdIn(List<Integer> values) {
            addCriterion("authority_id in", values, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdNotIn(List<Integer> values) {
            addCriterion("authority_id not in", values, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdBetween(Integer value1, Integer value2) {
            addCriterion("authority_id between", value1, value2, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("authority_id not between", value1, value2, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlIsNull() {
            addCriterion("authority_url is null");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlIsNotNull() {
            addCriterion("authority_url is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlEqualTo(String value) {
            addCriterion("authority_url =", value, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlNotEqualTo(String value) {
            addCriterion("authority_url <>", value, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlGreaterThan(String value) {
            addCriterion("authority_url >", value, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlGreaterThanOrEqualTo(String value) {
            addCriterion("authority_url >=", value, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlLessThan(String value) {
            addCriterion("authority_url <", value, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlLessThanOrEqualTo(String value) {
            addCriterion("authority_url <=", value, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlLike(String value) {
            addCriterion("authority_url like", value, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlNotLike(String value) {
            addCriterion("authority_url not like", value, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlIn(List<String> values) {
            addCriterion("authority_url in", values, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlNotIn(List<String> values) {
            addCriterion("authority_url not in", values, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlBetween(String value1, String value2) {
            addCriterion("authority_url between", value1, value2, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityUrlNotBetween(String value1, String value2) {
            addCriterion("authority_url not between", value1, value2, "authorityUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameIsNull() {
            addCriterion("authority_name is null");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameIsNotNull() {
            addCriterion("authority_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameEqualTo(String value) {
            addCriterion("authority_name =", value, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameNotEqualTo(String value) {
            addCriterion("authority_name <>", value, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameGreaterThan(String value) {
            addCriterion("authority_name >", value, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameGreaterThanOrEqualTo(String value) {
            addCriterion("authority_name >=", value, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameLessThan(String value) {
            addCriterion("authority_name <", value, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameLessThanOrEqualTo(String value) {
            addCriterion("authority_name <=", value, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameLike(String value) {
            addCriterion("authority_name like", value, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameNotLike(String value) {
            addCriterion("authority_name not like", value, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameIn(List<String> values) {
            addCriterion("authority_name in", values, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameNotIn(List<String> values) {
            addCriterion("authority_name not in", values, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameBetween(String value1, String value2) {
            addCriterion("authority_name between", value1, value2, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityNameNotBetween(String value1, String value2) {
            addCriterion("authority_name not between", value1, value2, "authorityName");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagIsNull() {
            addCriterion("authority_tag is null");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagIsNotNull() {
            addCriterion("authority_tag is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagEqualTo(String value) {
            addCriterion("authority_tag =", value, "authorityTag");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagNotEqualTo(String value) {
            addCriterion("authority_tag <>", value, "authorityTag");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagGreaterThan(String value) {
            addCriterion("authority_tag >", value, "authorityTag");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagGreaterThanOrEqualTo(String value) {
            addCriterion("authority_tag >=", value, "authorityTag");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagLessThan(String value) {
            addCriterion("authority_tag <", value, "authorityTag");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagLessThanOrEqualTo(String value) {
            addCriterion("authority_tag <=", value, "authorityTag");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagLike(String value) {
            addCriterion("authority_tag like", value, "authorityTag");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagNotLike(String value) {
            addCriterion("authority_tag not like", value, "authorityTag");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagIn(List<String> values) {
            addCriterion("authority_tag in", values, "authorityTag");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagNotIn(List<String> values) {
            addCriterion("authority_tag not in", values, "authorityTag");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagBetween(String value1, String value2) {
            addCriterion("authority_tag between", value1, value2, "authorityTag");
            return (Criteria) this;
        }

        public Criteria andAuthorityTagNotBetween(String value1, String value2) {
            addCriterion("authority_tag not between", value1, value2, "authorityTag");
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