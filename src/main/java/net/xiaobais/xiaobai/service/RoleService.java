package net.xiaobais.xiaobai.service;

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
}
