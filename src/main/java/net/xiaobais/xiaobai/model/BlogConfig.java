package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.BlogConfig")
public class BlogConfig {
    @ApiModelProperty(value="configId配置ID")
    private Integer configId;

    @ApiModelProperty(value="isHighlight是否高亮")
    private Boolean isHighlight;

    @ApiModelProperty(value="editor1：markdown 0：富文本编辑器")
    private Boolean editor;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public Boolean getIsHighlight() {
        return isHighlight;
    }

    public void setIsHighlight(Boolean isHighlight) {
        this.isHighlight = isHighlight;
    }

    public Boolean getEditor() {
        return editor;
    }

    public void setEditor(Boolean editor) {
        this.editor = editor;
    }
}