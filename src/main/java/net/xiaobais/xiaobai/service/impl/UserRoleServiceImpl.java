package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.UserRoleMapper;
import net.xiaobais.xiaobai.model.UserRole;
import net.xiaobais.xiaobai.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/2/23 18:53
 * @Version 1.0
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void addUserRole(Integer userId, Integer roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleMapper.insert(userRole);
    }
}
