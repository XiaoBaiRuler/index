package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Authority;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/23 21:51
 * @Version 1.0
 */
public interface RoleAuthorityService {

    /**
     * 绑定角色和权限
     * @param roleId roleId
     * @param authorityId authorityId
     * @return int
     */
    int addRoleAuthority(Integer roleId, Integer authorityId);


    /**
     * 解除角色和权限的绑定
     * @param roleId roleId
     * @param authorityId authorityId
     * @return int
     */
    int deleteRoleAuthority(Integer roleId, Integer authorityId);


    /**
     * 根据权限查找所有权限
     * @param roleId roleId
     * @return List<Authority>
     */
    List<Authority> getAuthoritiesByRoleId(Integer roleId);
}
