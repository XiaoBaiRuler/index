package net.xiaobais.xiaobai.model;

import java.util.ArrayList;
import java.util.List;

public class MapExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MapExample() {
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

        public Criteria andMapIdIsNull() {
            addCriterion("map_id is null");
            return (Criteria) this;
        }

        public Criteria andMapIdIsNotNull() {
            addCriterion("map_id is not null");
            return (Criteria) this;
        }

        public Criteria andMapIdEqualTo(Integer value) {
            addCriterion("map_id =", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdNotEqualTo(Integer value) {
            addCriterion("map_id <>", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdGreaterThan(Integer value) {
            addCriterion("map_id >", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("map_id >=", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdLessThan(Integer value) {
            addCriterion("map_id <", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdLessThanOrEqualTo(Integer value) {
            addCriterion("map_id <=", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdIn(List<Integer> values) {
            addCriterion("map_id in", values, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdNotIn(List<Integer> values) {
            addCriterion("map_id not in", values, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdBetween(Integer value1, Integer value2) {
            addCriterion("map_id between", value1, value2, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdNotBetween(Integer value1, Integer value2) {
            addCriterion("map_id not between", value1, value2, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapNameIsNull() {
            addCriterion("map_name is null");
            return (Criteria) this;
        }

        public Criteria andMapNameIsNotNull() {
            addCriterion("map_name is not null");
            return (Criteria) this;
        }

        public Criteria andMapNameEqualTo(String value) {
            addCriterion("map_name =", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameNotEqualTo(String value) {
            addCriterion("map_name <>", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameGreaterThan(String value) {
            addCriterion("map_name >", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameGreaterThanOrEqualTo(String value) {
            addCriterion("map_name >=", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameLessThan(String value) {
            addCriterion("map_name <", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameLessThanOrEqualTo(String value) {
            addCriterion("map_name <=", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameLike(String value) {
            addCriterion("map_name like", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameNotLike(String value) {
            addCriterion("map_name not like", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameIn(List<String> values) {
            addCriterion("map_name in", values, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameNotIn(List<String> values) {
            addCriterion("map_name not in", values, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameBetween(String value1, String value2) {
            addCriterion("map_name between", value1, value2, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameNotBetween(String value1, String value2) {
            addCriterion("map_name not between", value1, value2, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapAuthorIsNull() {
            addCriterion("map_author is null");
            return (Criteria) this;
        }

        public Criteria andMapAuthorIsNotNull() {
            addCriterion("map_author is not null");
            return (Criteria) this;
        }

        public Criteria andMapAuthorEqualTo(String value) {
            addCriterion("map_author =", value, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapAuthorNotEqualTo(String value) {
            addCriterion("map_author <>", value, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapAuthorGreaterThan(String value) {
            addCriterion("map_author >", value, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("map_author >=", value, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapAuthorLessThan(String value) {
            addCriterion("map_author <", value, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapAuthorLessThanOrEqualTo(String value) {
            addCriterion("map_author <=", value, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapAuthorLike(String value) {
            addCriterion("map_author like", value, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapAuthorNotLike(String value) {
            addCriterion("map_author not like", value, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapAuthorIn(List<String> values) {
            addCriterion("map_author in", values, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapAuthorNotIn(List<String> values) {
            addCriterion("map_author not in", values, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapAuthorBetween(String value1, String value2) {
            addCriterion("map_author between", value1, value2, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapAuthorNotBetween(String value1, String value2) {
            addCriterion("map_author not between", value1, value2, "mapAuthor");
            return (Criteria) this;
        }

        public Criteria andMapVersionIsNull() {
            addCriterion("map_version is null");
            return (Criteria) this;
        }

        public Criteria andMapVersionIsNotNull() {
            addCriterion("map_version is not null");
            return (Criteria) this;
        }

        public Criteria andMapVersionEqualTo(String value) {
            addCriterion("map_version =", value, "mapVersion");
            return (Criteria) this;
        }

        public Criteria andMapVersionNotEqualTo(String value) {
            addCriterion("map_version <>", value, "mapVersion");
            return (Criteria) this;
        }

        public Criteria andMapVersionGreaterThan(String value) {
            addCriterion("map_version >", value, "mapVersion");
            return (Criteria) this;
        }

        public Criteria andMapVersionGreaterThanOrEqualTo(String value) {
            addCriterion("map_version >=", value, "mapVersion");
            return (Criteria) this;
        }

        public Criteria andMapVersionLessThan(String value) {
            addCriterion("map_version <", value, "mapVersion");
            return (Criteria) this;
        }

        public Criteria andMapVersionLessThanOrEqualTo(String value) {
            addCriterion("map_version <=", value, "mapVersion");
            return (Criteria) this;
        }

        public Criteria andMapVersionLike(String value) {
            addCriterion("map_version like", value, "mapVersion");
            return (Criteria) this;
        }

        public Criteria andMapVersionNotLike(String value) {
            addCriterion("map_version not like", value, "mapVersion");
            return (Criteria) this;
        }

        public Criteria andMapVersionIn(List<String> values) {
            addCriterion("map_version in", values, "mapVersion");
            return (Criteria) this;
        }

        public Criteria andMapVersionNotIn(List<String> values) {
            addCriterion("map_version not in", values, "mapVersion");
            return (Criteria) this;
        }

        public Criteria andMapVersionBetween(String value1, String value2) {
            addCriterion("map_version between", value1, value2, "mapVersion");
            return (Criteria) this;
        }

        public Criteria andMapVersionNotBetween(String value1, String value2) {
            addCriterion("map_version not between", value1, value2, "mapVersion");
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