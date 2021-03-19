package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.Iterator")
public class Iterator {
    @ApiModelProperty(value="iteratorId迭代节点ID")
    private Integer iteratorId;

    @ApiModelProperty(value="nodeId当前节点ID")
    private Integer nodeId;

    @ApiModelProperty(value="iteratorName分支名称")
    private String iteratorName;

    @ApiModelProperty(value="iteratorReason迭代理由")
    private String iteratorReason;

    public Integer getIteratorId() {
        return iteratorId;
    }

    public void setIteratorId(Integer iteratorId) {
        this.iteratorId = iteratorId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getIteratorName() {
        return iteratorName;
    }

    public void setIteratorName(String iteratorName) {
        this.iteratorName = iteratorName == null ? null : iteratorName.trim();
    }

    public String getIteratorReason() {
        return iteratorReason;
    }

    public void setIteratorReason(String iteratorReason) {
        this.iteratorReason = iteratorReason == null ? null : iteratorReason.trim();
    }
}