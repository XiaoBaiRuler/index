package net.xiaobais.xiaobai.service;

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
    void addUserRole(Integer userId, Integer roleId);
}
