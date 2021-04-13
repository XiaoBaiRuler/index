package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Role;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/23 17:41
 * @Version 1.0
 */
public interface UserRoleService {

    /**
     * 绑定用户和角色
     * @param userId userId
     * @param roleId roleId
     */
    int addUserRole(Integer userId, Integer roleId);

    /**
     * 解除用户和角色绑定
     * @param userId userId
     * @param roleId roleId
     */
    void deleteUserRole(Integer userId, Integer roleId);

    /**
     * 根据用户ID获取所有角色
     * @param userId userId
     * @return List<Role>
     */
    List<Role> getRoleByUserId(Integer userId);


}
