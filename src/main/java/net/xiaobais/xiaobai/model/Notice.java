package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.Notice")
public class Notice {
    @ApiModelProperty(value="noticeId通知Id")
    private Integer noticeId;

    @ApiModelProperty(value="userId发送通知的人员")
    private Integer userId;

    @ApiModelProperty(value="acceptId接受通知的人员")
    private Integer acceptId;

    @ApiModelProperty(value="submitType0:发布博客通知，1:iterator通知，2：suggest通知")
    private Integer submitType;

    @ApiModelProperty(value="acceptType0:发起通知，1返回通知")
    private Boolean acceptType;

    @ApiModelProperty(value="nodeId影响节点的ID(发布博客Id)")
    private Integer nodeId;

    @ApiModelProperty(value="suggestId建议Id")
    private Integer suggestId;

    @ApiModelProperty(value="iteratorId迭代Id")
    private Integer iteratorId;

    @ApiModelProperty(value="message消息内容")
    private String message;

    @ApiModelProperty(value="dealUrl正确处理链接")
    private String dealUrl;

    @ApiModelProperty(value="errorUrl驳回请求")
    private String errorUrl;

    @ApiModelProperty(value="isDelete是否删除")
    private Boolean isDelete;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Integer acceptId) {
        this.acceptId = acceptId;
    }

    public Integer getSubmitType() {
        return submitType;
    }

    public void setSubmitType(Integer submitType) {
        this.submitType = submitType;
    }

    public Boolean getAcceptType() {
        return acceptType;
    }

    public void setAcceptType(Boolean acceptType) {
        this.acceptType = acceptType;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(Integer suggestId) {
        this.suggestId = suggestId;
    }

    public Integer getIteratorId() {
        return iteratorId;
    }

    public void setIteratorId(Integer iteratorId) {
        this.iteratorId = iteratorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getDealUrl() {
        return dealUrl;
    }

    public void setDealUrl(String dealUrl) {
        this.dealUrl = dealUrl == null ? null : dealUrl.trim();
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl == null ? null : errorUrl.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}