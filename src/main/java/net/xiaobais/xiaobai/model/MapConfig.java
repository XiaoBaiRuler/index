package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.MapConfig")
public class MapConfig {
    @ApiModelProperty(value="configId配置ID")
    private Integer configId;

    @ApiModelProperty(value="container导图容器对象")
    private String container;

    @ApiModelProperty(value="editable是否启用编辑")
    private Boolean editable;

    @ApiModelProperty(value="theme主题")
    private String theme;

    @ApiModelProperty(value="mode显示模式")
    private String mode;

    @ApiModelProperty(value="supportHtml是否支持节点里的HTML元素")
    private Boolean supportHtml;

    @ApiModelProperty(value="view思维导图的展览")
    private String view;

    @ApiModelProperty(value="layout思维导图的布局")
    private String layout;

    @ApiModelProperty(value="shortcut思维导图的快捷键")
    private String shortcut;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container == null ? null : container.trim();
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    public Boolean getSupportHtml() {
        return supportHtml;
    }

    public void setSupportHtml(Boolean supportHtml) {
        this.supportHtml = supportHtml;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view == null ? null : view.trim();
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout == null ? null : layout.trim();
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut == null ? null : shortcut.trim();
    }
}