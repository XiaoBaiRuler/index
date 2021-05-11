package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Authority;
import net.xiaobais.xiaobai.vo.AddAuthorityVo;
import net.xiaobais.xiaobai.vo.AdminAuthorityVo;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/23 21:51
 * @Version 1.0
 */
public interface RoleAuthorityService {

    /**
     * 绑定角色和权限
     * @param addAuthorityVo addAuthorityVo
     * @return int
     * @exception Exception exception
     */
    int addRoleAuthority(AddAuthorityVo addAuthorityVo) throws Exception;


    /**
     * 解除角色和权限的绑定
     * @param roleId roleId
     * @param authorityId authorityId
     * @exception Exception
     */
    void deleteRoleAuthority(Integer roleId, Integer authorityId) throws Exception;


    /**
     * 根据权限查找所有权限
     * @param roleId roleId
     * @return List<Authority>
     */
    List<Authority> getAuthoritiesByRoleId(Integer roleId);

    /**
     * 分页根据角色Id和message一起查找权限
     * @param roleId roleId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param message message
     * @return List<AdminAuthorityVo>
     */
    List<AdminAuthorityVo> getAllAuthorities(Integer roleId, Integer pageNumber, Integer pageSize, String message);

    /**
     * 统计权限个数
     * @param roleId roleId
     * @param message message
     * @return Integer
     */
    Integer countAllAuthorities(Integer roleId, String message);

    /**
     * 更新权限信息
     * @param authority authority
     * @return Integer
     */
    Integer updateAuthority(Authority authority);
}
