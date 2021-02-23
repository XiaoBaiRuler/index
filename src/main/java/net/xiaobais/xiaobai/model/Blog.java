package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="net.xiaobais.xiaobai.model.Blog")
public class Blog {
    @ApiModelProperty(value="blogId博客ID")
    private Integer blogId;

    @ApiModelProperty(value="blogTitle博客标题")
    private Integer blogTitle;

    @ApiModelProperty(value="blogPicture博客图片")
    private String blogPicture;

    @ApiModelProperty(value="blogDes博客描述")
    private String blogDes;

    @ApiModelProperty(value="createDate创建日期")
    private Date createDate;

    @ApiModelProperty(value="updateDate更新日期")
    private Date updateDate;

    @ApiModelProperty(value="isComment是否有评论")
    private Boolean isComment;

    @ApiModelProperty(value="blogContent博客内容")
    private String blogContent;

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(Integer blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogPicture() {
        return blogPicture;
    }

    public void setBlogPicture(String blogPicture) {
        this.blogPicture = blogPicture == null ? null : blogPicture.trim();
    }

    public String getBlogDes() {
        return blogDes;
    }

    public void setBlogDes(String blogDes) {
        this.blogDes = blogDes == null ? null : blogDes.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getIsComment() {
        return isComment;
    }

    public void setIsComment(Boolean isComment) {
        this.isComment = isComment;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent == null ? null : blogContent.trim();
    }
}