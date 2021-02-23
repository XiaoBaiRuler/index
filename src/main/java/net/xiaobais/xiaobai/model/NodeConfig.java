package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.NodeConfig")
public class NodeConfig {
    @ApiModelProperty(value="configId配置ID")
    private Integer configId;

    @ApiModelProperty(value="nodeLevel2-6：可选 6 full")
    private Integer nodeLevel;

    @ApiModelProperty(value="nodeTypen0：思维导图模型 1：节点模式")
    private Boolean nodeType;

    @ApiModelProperty(value="isSuggest是否加建议模块")
    private Boolean isSuggest;

    @ApiModelProperty(value="isComment是否加评论模块")
    private Boolean isComment;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public Boolean getNodeType() {
        return nodeType;
    }

    public void setNodeType(Boolean nodeType) {
        this.nodeType = nodeType;
    }

    public Boolean getIsSuggest() {
        return isSuggest;
    }

    public void setIsSuggest(Boolean isSuggest) {
        this.isSuggest = isSuggest;
    }

    public Boolean getIsComment() {
        return isComment;
    }

    public void setIsComment(Boolean isComment) {
        this.isComment = isComment;
    }
}