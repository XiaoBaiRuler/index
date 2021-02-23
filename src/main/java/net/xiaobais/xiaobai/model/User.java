package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="net.xiaobais.xiaobai.model.User")
public class User {
    @ApiModelProperty(value="userId用户ID")
    private Integer userId;

    @ApiModelProperty(value="username用户名")
    private String username;

    @ApiModelProperty(value="password密码")
    private String password;

    @ApiModelProperty(value="createDate创建日期")
    private Date createDate;

    @ApiModelProperty(value="lastLoginTime上一次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value="enabled账户是否启用")
    private Boolean enabled;

    @ApiModelProperty(value="accountNonExpired账户是否过期")
    private Boolean accountNonExpired;

    @ApiModelProperty(value="credentialsNonExpired凭证是否过期")
    private Boolean credentialsNonExpired;

    @ApiModelProperty(value="userPhone用户手机号")
    private String userPhone;

    @ApiModelProperty(value="userEmail用户邮箱")
    private String userEmail;

    @ApiModelProperty(value="userAvatar用户头像")
    private String userAvatar;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }
}