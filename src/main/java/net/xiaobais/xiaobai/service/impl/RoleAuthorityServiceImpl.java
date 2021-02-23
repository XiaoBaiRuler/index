package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.RoleAuthorityMapper;
import net.xiaobais.xiaobai.model.Authority;
import net.xiaobais.xiaobai.model.RoleAuthority;
import net.xiaobais.xiaobai.model.RoleAuthorityExample;
import net.xiaobais.xiaobai.service.AuthorityService;
import net.xiaobais.xiaobai.service.RoleAuthorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/23 21:52
 * @Version 1.0
 */
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

    @Resource
    private RoleAuthorityMapper roleAuthorityMapper;

    @Resource
    private AuthorityService authorityService;

    @Override
    public int addRoleAuthority(Integer roleId, Integer authorityId) {
        RoleAuthority roleAuthority = new RoleAuthority();
        roleAuthority.setRoleId(roleId);
        roleAuthority.setAuthorityId(authorityId);
        return roleAuthorityMapper.insert(roleAuthority);
    }

    @Override
    public int deleteRoleAuthority(Integer roleId, Integer authorityId) {

        RoleAuthorityExample roleAuthorityExample = new RoleAuthorityExample();
        roleAuthorityExample.createCriteria().andRoleIdEqualTo(roleId).andAuthorityIdEqualTo(authorityId);
        return roleAuthorityMapper.deleteByExample(roleAuthorityExample);
    }

    @Override
    public List<Authority> getAuthoritiesByRoleId(Integer roleId) {

        RoleAuthorityExample roleAuthorityExample = new RoleAuthorityExample();
        roleAuthorityExample.createCriteria().andRoleIdEqualTo(roleId);
        List<RoleAuthority> roleAuthorities = roleAuthorityMapper.selectByExample(roleAuthorityExample);
        if (roleAuthorities.isEmpty()){
            return null;
        }
        List<Authority> authorities = new ArrayList<>();
        roleAuthorities.forEach( roleAuthority ->
                authorities.add(authorityService.getAuthorityById(roleAuthority.getAuthorityId()))
        );
        return authorities;
    }
}
