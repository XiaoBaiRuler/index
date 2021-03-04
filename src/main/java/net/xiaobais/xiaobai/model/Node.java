package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="net.xiaobais.xiaobai.model.Node")
public class Node {
    @ApiModelProperty(value="nodeId节点ID")
    private Integer nodeId;

    @ApiModelProperty(value="userId所属用户")
    private Integer userId;

    @ApiModelProperty(value="nodeName节点名")
    private String nodeName;

    @ApiModelProperty(value="blogId博客ID")
    private Integer blogId;

    @ApiModelProperty(value="mapId导图ID")
    private Integer mapId;

    @ApiModelProperty(value="collect收藏数")
    private Integer collect;

    @ApiModelProperty(value="isPrivate是否私有")
    private Boolean isPrivate;

    @ApiModelProperty(value="star点赞数")
    private Integer star;

    @ApiModelProperty(value="createDate创建日期")
    private Date createDate;

    @ApiModelProperty(value="updateDate更新日期")
    private Date updateDate;

    @ApiModelProperty(value="relationship是否存在某种关系的标志位")
    private String relationship;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship == null ? null : relationship.trim();
    }
}