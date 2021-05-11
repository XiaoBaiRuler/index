package net.xiaobais.xiaobai.mapper;

import net.xiaobais.xiaobai.vo.AdminAuthorityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/5/11 16:47
 * @Version 1.0
 */
public interface MyRoleAuthorityMapper {

    /**
     * 通过roleId查找所有权限
     * @param roleId roleId
     * @param message message
     * @return List<AdminAuthorityVo>
     */
    List<AdminAuthorityVo> getAllAuthoritiesByRoleId(@Param("roleId") Integer roleId,
                                                     @Param("message") String message);

    /**
     * 根据roleId统计权限个数
     * @param roleId roleId
     * @param message message
     * @return Integer
     */
    Integer countAllAuthoritiesByRoleId(@Param("roleId") Integer roleId,
                                        @Param("message") String message);

    /**
     * 查找所有权限
     * @param message message
     * @return List<AdminAuthorityVo>
     */
    List<AdminAuthorityVo> getAllAuthorities(@Param("message") String message);

    /**
     * 统计权限个数
     * @param message message
     * @return Integer
     */
    Integer countAllAuthorities(@Param("message") String message);

}
