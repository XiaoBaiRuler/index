package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.Suggest")
public class Suggest {
    @ApiModelProperty(value="suggestId建议ID")
    private Integer suggestId;

    @ApiModelProperty(value="nodeId当前节点ID")
    private Integer nodeId;

    @ApiModelProperty(value="suggestContent建议内容")
    private String suggestContent;

    public Integer getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(Integer suggestId) {
        this.suggestId = suggestId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getSuggestContent() {
        return suggestContent;
    }

    public void setSuggestContent(String suggestContent) {
        this.suggestContent = suggestContent == null ? null : suggestContent.trim();
    }
}