package net.xiaobais.xiaobai.service.impl;

import com.github.pagehelper.PageHelper;
import net.xiaobais.xiaobai.mapper.MyRoleAuthorityMapper;
import net.xiaobais.xiaobai.mapper.RoleAuthorityMapper;
import net.xiaobais.xiaobai.model.Authority;
import net.xiaobais.xiaobai.model.RoleAuthority;
import net.xiaobais.xiaobai.model.RoleAuthorityExample;
import net.xiaobais.xiaobai.service.AuthorityService;
import net.xiaobais.xiaobai.service.RoleAuthorityService;
import net.xiaobais.xiaobai.vo.AddAuthorityVo;
import net.xiaobais.xiaobai.vo.AdminAuthorityVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private MyRoleAuthorityMapper myRoleAuthorityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addRoleAuthority(AddAuthorityVo addAuthorityVo) throws Exception {
        Authority authority = new Authority();
        authority.setAuthorityName(addAuthorityVo.getAuthorityName());
        authority.setAuthorityUrl(addAuthorityVo.getAuthorityUrl());
        authority.setAuthorityTag(addAuthorityVo.getAuthorityTag());
        int i = authorityService.addAuthority(authority);
        if (i == -1){
            throw new Exception("添加权限失败");
        }
        RoleAuthority roleAuthority = new RoleAuthority();
        roleAuthority.setRoleId(addAuthorityVo.getRoleId());
        roleAuthority.setAuthorityId(i);
        if (roleAuthorityMapper.insert(roleAuthority) == -1){
            throw new Exception("绑定角色权限失败");
        }
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleAuthority(Integer roleId, Integer authorityId) throws Exception {
        RoleAuthorityExample roleAuthorityExample = new RoleAuthorityExample();
        roleAuthorityExample.createCriteria().andRoleIdEqualTo(roleId).andAuthorityIdEqualTo(authorityId);
        if (roleAuthorityMapper.deleteByExample(roleAuthorityExample) == -1){
            throw new Exception("解除角色和权限关系失败");
        }
        if (authorityService.deleteAuthorityById(authorityId) == -1){
            throw new Exception("删除权限失败");
        }
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

    @Override
    public List<AdminAuthorityVo> getAllAuthorities(Integer roleId, Integer pageNumber, Integer pageSize, String message) {
        message = "".equals(message) ? "%" : "%" + message + "%";
        PageHelper.startPage(pageNumber, pageSize);
        return roleId == 0 ? myRoleAuthorityMapper.getAllAuthorities(message)
                : myRoleAuthorityMapper.getAllAuthoritiesByRoleId(roleId, message);
    }

    @Override
    public Integer countAllAuthorities(Integer roleId, String message) {
        message = "".equals(message) ? "%" : "%" + message + "%";
        int l = roleId == 0 ? myRoleAuthorityMapper.countAllAuthorities(message)
                : myRoleAuthorityMapper.countAllAuthoritiesByRoleId(roleId, message);
        return l % 5 == 0 ? l / 5 : l / 5 + 1;
    }

    @Override
    public Integer updateAuthority(Authority authority) {
        return authorityService.updateAuthority(authority);
    }
}
