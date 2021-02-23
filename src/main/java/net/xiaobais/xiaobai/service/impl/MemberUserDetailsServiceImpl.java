package net.xiaobais.xiaobai.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.xiaobais.xiaobai.entity.UserEntity;
import net.xiaobais.xiaobai.mapper.MyUserAuthorityMapper;
import net.xiaobais.xiaobai.mapper.UserMapper;
import net.xiaobais.xiaobai.model.Authority;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.model.UserExample;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/19 20:47
 * @Version 1.0
 */
@Service
@Slf4j
public class MemberUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private MyUserAuthorityMapper userAuthorityMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1. 根据用户名查询数据库是否存在
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null){
            return null;
        }
        // 2. 查询对应的用户权限
        List<Authority> authorityList = userAuthorityMapper.findAuthorityByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorityList.forEach(authority ->{
            log.info("tag:{}",authority.getAuthorityTag());
            authorities.add(new SimpleGrantedAuthority(authority.getAuthorityTag()));
        });
        log.info(">>>authorities:{}<<<", authorities);
        // 3. 将该权限添加到security
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(users.get(0), userEntity);
        userEntity.setAuthorities(authorities);
        log.info("userEntity:{}",userEntity);
        return userEntity;
    }
}
