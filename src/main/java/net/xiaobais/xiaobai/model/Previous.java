package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.Previous")
public class Previous {
    @ApiModelProperty(value="nodeId当前节点ID")
    private Integer nodeId;

    @ApiModelProperty(value="previousId上一个节点ID")
    private Integer previousId;

    @ApiModelProperty(value="previousName关系名称")
    private String previousName;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getPreviousId() {
        return previousId;
    }

    public void setPreviousId(Integer previousId) {
        this.previousId = previousId;
    }

    public String getPreviousName() {
        return previousName;
    }

    public void setPreviousName(String previousName) {
        this.previousName = previousName == null ? null : previousName.trim();
    }
}