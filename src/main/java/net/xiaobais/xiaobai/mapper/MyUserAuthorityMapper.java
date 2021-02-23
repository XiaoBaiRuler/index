package net.xiaobais.xiaobai.mapper;

import net.xiaobais.xiaobai.model.Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/19 20:42
 * @Version 1.0
 */
public interface MyUserAuthorityMapper {

    /**
     * 通过用户名，查询用户的权限
     * @param username username
     * @return List<Authority>
     */
    List<Authority> findAuthorityByUsername(@Param("username")String username);
}
