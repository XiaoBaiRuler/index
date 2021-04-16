package net.xiaobais.xiaobai.model;

import java.util.ArrayList;
import java.util.List;

public class NoticeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NoticeExample() {
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

        public Criteria andNoticeIdIsNull() {
            addCriterion("notice_id is null");
            return (Criteria) this;
        }

        public Criteria andNoticeIdIsNotNull() {
            addCriterion("notice_id is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeIdEqualTo(Integer value) {
            addCriterion("notice_id =", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotEqualTo(Integer value) {
            addCriterion("notice_id <>", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdGreaterThan(Integer value) {
            addCriterion("notice_id >", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("notice_id >=", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdLessThan(Integer value) {
            addCriterion("notice_id <", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdLessThanOrEqualTo(Integer value) {
            addCriterion("notice_id <=", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdIn(List<Integer> values) {
            addCriterion("notice_id in", values, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotIn(List<Integer> values) {
            addCriterion("notice_id not in", values, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdBetween(Integer value1, Integer value2) {
            addCriterion("notice_id between", value1, value2, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("notice_id not between", value1, value2, "noticeId");
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

        public Criteria andAcceptIdIsNull() {
            addCriterion("accept_id is null");
            return (Criteria) this;
        }

        public Criteria andAcceptIdIsNotNull() {
            addCriterion("accept_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptIdEqualTo(Integer value) {
            addCriterion("accept_id =", value, "acceptId");
            return (Criteria) this;
        }

        public Criteria andAcceptIdNotEqualTo(Integer value) {
            addCriterion("accept_id <>", value, "acceptId");
            return (Criteria) this;
        }

        public Criteria andAcceptIdGreaterThan(Integer value) {
            addCriterion("accept_id >", value, "acceptId");
            return (Criteria) this;
        }

        public Criteria andAcceptIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("accept_id >=", value, "acceptId");
            return (Criteria) this;
        }

        public Criteria andAcceptIdLessThan(Integer value) {
            addCriterion("accept_id <", value, "acceptId");
            return (Criteria) this;
        }

        public Criteria andAcceptIdLessThanOrEqualTo(Integer value) {
            addCriterion("accept_id <=", value, "acceptId");
            return (Criteria) this;
        }

        public Criteria andAcceptIdIn(List<Integer> values) {
            addCriterion("accept_id in", values, "acceptId");
            return (Criteria) this;
        }

        public Criteria andAcceptIdNotIn(List<Integer> values) {
            addCriterion("accept_id not in", values, "acceptId");
            return (Criteria) this;
        }

        public Criteria andAcceptIdBetween(Integer value1, Integer value2) {
            addCriterion("accept_id between", value1, value2, "acceptId");
            return (Criteria) this;
        }

        public Criteria andAcceptIdNotBetween(Integer value1, Integer value2) {
            addCriterion("accept_id not between", value1, value2, "acceptId");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeIsNull() {
            addCriterion("submit_type is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeIsNotNull() {
            addCriterion("submit_type is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeEqualTo(Integer value) {
            addCriterion("submit_type =", value, "submitType");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeNotEqualTo(Integer value) {
            addCriterion("submit_type <>", value, "submitType");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeGreaterThan(Integer value) {
            addCriterion("submit_type >", value, "submitType");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("submit_type >=", value, "submitType");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeLessThan(Integer value) {
            addCriterion("submit_type <", value, "submitType");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeLessThanOrEqualTo(Integer value) {
            addCriterion("submit_type <=", value, "submitType");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeIn(List<Integer> values) {
            addCriterion("submit_type in", values, "submitType");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeNotIn(List<Integer> values) {
            addCriterion("submit_type not in", values, "submitType");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeBetween(Integer value1, Integer value2) {
            addCriterion("submit_type between", value1, value2, "submitType");
            return (Criteria) this;
        }

        public Criteria andSubmitTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("submit_type not between", value1, value2, "submitType");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeIsNull() {
            addCriterion("accept_type is null");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeIsNotNull() {
            addCriterion("accept_type is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeEqualTo(Boolean value) {
            addCriterion("accept_type =", value, "acceptType");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeNotEqualTo(Boolean value) {
            addCriterion("accept_type <>", value, "acceptType");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeGreaterThan(Boolean value) {
            addCriterion("accept_type >", value, "acceptType");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("accept_type >=", value, "acceptType");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeLessThan(Boolean value) {
            addCriterion("accept_type <", value, "acceptType");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("accept_type <=", value, "acceptType");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeIn(List<Boolean> values) {
            addCriterion("accept_type in", values, "acceptType");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeNotIn(List<Boolean> values) {
            addCriterion("accept_type not in", values, "acceptType");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("accept_type between", value1, value2, "acceptType");
            return (Criteria) this;
        }

        public Criteria andAcceptTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("accept_type not between", value1, value2, "acceptType");
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

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andDealUrlIsNull() {
            addCriterion("deal_url is null");
            return (Criteria) this;
        }

        public Criteria andDealUrlIsNotNull() {
            addCriterion("deal_url is not null");
            return (Criteria) this;
        }

        public Criteria andDealUrlEqualTo(String value) {
            addCriterion("deal_url =", value, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andDealUrlNotEqualTo(String value) {
            addCriterion("deal_url <>", value, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andDealUrlGreaterThan(String value) {
            addCriterion("deal_url >", value, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andDealUrlGreaterThanOrEqualTo(String value) {
            addCriterion("deal_url >=", value, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andDealUrlLessThan(String value) {
            addCriterion("deal_url <", value, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andDealUrlLessThanOrEqualTo(String value) {
            addCriterion("deal_url <=", value, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andDealUrlLike(String value) {
            addCriterion("deal_url like", value, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andDealUrlNotLike(String value) {
            addCriterion("deal_url not like", value, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andDealUrlIn(List<String> values) {
            addCriterion("deal_url in", values, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andDealUrlNotIn(List<String> values) {
            addCriterion("deal_url not in", values, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andDealUrlBetween(String value1, String value2) {
            addCriterion("deal_url between", value1, value2, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andDealUrlNotBetween(String value1, String value2) {
            addCriterion("deal_url not between", value1, value2, "dealUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlIsNull() {
            addCriterion("error_url is null");
            return (Criteria) this;
        }

        public Criteria andErrorUrlIsNotNull() {
            addCriterion("error_url is not null");
            return (Criteria) this;
        }

        public Criteria andErrorUrlEqualTo(String value) {
            addCriterion("error_url =", value, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlNotEqualTo(String value) {
            addCriterion("error_url <>", value, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlGreaterThan(String value) {
            addCriterion("error_url >", value, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlGreaterThanOrEqualTo(String value) {
            addCriterion("error_url >=", value, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlLessThan(String value) {
            addCriterion("error_url <", value, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlLessThanOrEqualTo(String value) {
            addCriterion("error_url <=", value, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlLike(String value) {
            addCriterion("error_url like", value, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlNotLike(String value) {
            addCriterion("error_url not like", value, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlIn(List<String> values) {
            addCriterion("error_url in", values, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlNotIn(List<String> values) {
            addCriterion("error_url not in", values, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlBetween(String value1, String value2) {
            addCriterion("error_url between", value1, value2, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andErrorUrlNotBetween(String value1, String value2) {
            addCriterion("error_url not between", value1, value2, "errorUrl");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
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