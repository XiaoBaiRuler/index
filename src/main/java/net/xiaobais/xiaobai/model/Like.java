package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.Like")
public class Like {
    @ApiModelProperty(value="userId用户ID")
    private Integer userId;

    @ApiModelProperty(value="likeId点赞节点")
    private Integer likeId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }
}