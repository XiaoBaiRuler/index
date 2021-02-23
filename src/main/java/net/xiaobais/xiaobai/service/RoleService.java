package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Role;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/23 18:48
 * @Version 1.0
 */
public interface RoleService {

    /**
     * 根据roleName查找roleId
     * @param roleName roleName
     * @return roleId
     */
    int getRoleIdByRoleName(String roleName);

    /**
     * 根据roleId获取权限信息
     * @param roleId roleId
     * @return Role
     */
    Role getRoleById(Integer roleId);

    /**
     * 获取所有权限
     * @return role
     */
    List<Role> getAll();

    /**
     * 更新role(必须包含roleId)
     * @param role role
     * @return int
     */
    int updateRole(Role role);
}
