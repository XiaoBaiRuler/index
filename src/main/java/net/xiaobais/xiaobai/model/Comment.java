package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="net.xiaobais.xiaobai.model.Comment")
public class Comment {
    @ApiModelProperty(value="commentId评论ID")
    private Integer commentId;

    @ApiModelProperty(value="userId评论人ID")
    private Integer userId;

    @ApiModelProperty(value="content评论内容")
    private String content;

    @ApiModelProperty(value="nodeId评论节点ID")
    private Integer nodeId;

    @ApiModelProperty(value="parentId回复人ID(0为node)")
    private Integer parentId;

    @ApiModelProperty(value="createDate评论日期")
    private Date createDate;

    @ApiModelProperty(value="isDelete是否删除")
    private Boolean isDelete;

    @ApiModelProperty(value="updateDate更新日期")
    private Date updateDate;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}