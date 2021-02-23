package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.RoleMapper;
import net.xiaobais.xiaobai.model.RoleExample;
import net.xiaobais.xiaobai.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/2/23 18:50
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public int getRoleIdByRoleName(String roleName) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleNameEqualTo(roleName);
        return roleMapper.selectByExample(roleExample).get(0).getRoleId();
    }
}
