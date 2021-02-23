package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.Authority")
public class Authority {
    @ApiModelProperty(value="authorityId权限ID")
    private Integer authorityId;

    @ApiModelProperty(value="authorityUrl请求URL")
    private String authorityUrl;

    @ApiModelProperty(value="authorityName权限名")
    private String authorityName;

    @ApiModelProperty(value="authorityTag权限标志")
    private String authorityTag;

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityUrl() {
        return authorityUrl;
    }

    public void setAuthorityUrl(String authorityUrl) {
        this.authorityUrl = authorityUrl == null ? null : authorityUrl.trim();
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName == null ? null : authorityName.trim();
    }

    public String getAuthorityTag() {
        return authorityTag;
    }

    public void setAuthorityTag(String authorityTag) {
        this.authorityTag = authorityTag == null ? null : authorityTag.trim();
    }
}