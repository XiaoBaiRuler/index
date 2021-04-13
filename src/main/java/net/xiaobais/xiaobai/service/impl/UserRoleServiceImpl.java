package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.UserRoleMapper;
import net.xiaobais.xiaobai.model.Role;
import net.xiaobais.xiaobai.model.UserRole;
import net.xiaobais.xiaobai.model.UserRoleExample;
import net.xiaobais.xiaobai.service.RoleService;
import net.xiaobais.xiaobai.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/23 18:53
 * @Version 1.0
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleService roleService;

    @Override
    public int addUserRole(Integer userId, Integer roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return userRoleMapper.insert(userRole);
    }

    @Override
    public void deleteUserRole(Integer userId, Integer roleId) {

        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId).andRoleIdEqualTo(roleId);
        userRoleMapper.deleteByExample(userRoleExample);
    }

    @Override
    public List<Role> getRoleByUserId(Integer userId) {

        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        if (userRoles.isEmpty()){
            return null;
        }
        List<Role> roles = new ArrayList<>();
        userRoles.forEach(userRole ->
                roles.add(roleService.getRoleById(userRole.getRoleId())));
        return roles;
    }
}
