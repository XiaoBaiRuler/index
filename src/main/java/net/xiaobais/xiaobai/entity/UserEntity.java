package net.xiaobais.xiaobai.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/19 20:33
 * @Version 1.0
 */
@Data
public class UserEntity implements UserDetails {
    private Integer userId;
    private String username;
    private String password;
    private Date createDate;
    private Date lastLoginTime;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired;
    private String userPhone;
    private String userEmail;
    private String userAvatar;

    private List<GrantedAuthority> authorities = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
