package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="net.xiaobais.xiaobai.model.Record")
public class Record {
    @ApiModelProperty(value="recordId记录ID")
    private Integer recordId;

    @ApiModelProperty(value="userId操作人")
    private Integer userId;

    @ApiModelProperty(value="recordDes记录描述")
    private String recordDes;

    @ApiModelProperty(value="recordDate记录时间")
    private Date recordDate;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRecordDes() {
        return recordDes;
    }

    public void setRecordDes(String recordDes) {
        this.recordDes = recordDes == null ? null : recordDes.trim();
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}