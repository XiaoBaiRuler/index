package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="net.xiaobais.xiaobai.model.Fans")
public class Fans {
    @ApiModelProperty(value="userId用户ID")
    private Integer userId;

    @ApiModelProperty(value="fansId粉丝ID")
    private Integer fansId;

    @ApiModelProperty(value="status是否取消关注")
    private Boolean status;

    @ApiModelProperty(value="fansDate关注日期")
    private Date fansDate;

    @ApiModelProperty(value="updateDate更新日期")
    private Date updateDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFansId() {
        return fansId;
    }

    public void setFansId(Integer fansId) {
        this.fansId = fansId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getFansDate() {
        return fansDate;
    }

    public void setFansDate(Date fansDate) {
        this.fansDate = fansDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}