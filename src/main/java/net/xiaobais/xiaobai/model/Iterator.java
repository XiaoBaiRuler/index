package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.Iterator")
public class Iterator {
    @ApiModelProperty(value="nodeId当前节点ID")
    private Integer nodeId;

    @ApiModelProperty(value="iteratorId迭代节点ID")
    private Integer iteratorId;

    @ApiModelProperty(value="iteratorName分支名称")
    private String iteratorName;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getIteratorId() {
        return iteratorId;
    }

    public void setIteratorId(Integer iteratorId) {
        this.iteratorId = iteratorId;
    }

    public String getIteratorName() {
        return iteratorName;
    }

    public void setIteratorName(String iteratorName) {
        this.iteratorName = iteratorName == null ? null : iteratorName.trim();
    }
}