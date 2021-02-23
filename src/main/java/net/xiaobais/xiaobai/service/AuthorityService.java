package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Authority;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/23 20:05
 * @Version 1.0
 */
public interface AuthorityService {

    /**
     * 获取所有权限
     * @return authorities
     */
    List<Authority> getAll();

    /**
     * 根据tag查找权限
     * @param authorityTag authorityTag
     * @return Authority
     */
    Authority getAuthorityByTag(String authorityTag);

    /**
     * 根据Id查找authority
     * @param authorityId authorityId
     * @return Authority
     */
    Authority getAuthorityById(Integer authorityId);

    /**
     * 根据ID删除Authority
     * @param id id
     * @return int
     */
    int deleteAuthorityById(Integer id);

    /**
     * 更新Authority(必须包含authorityID)
     * @param authority authority
     * @return int
     */
    int updateAuthority(Authority authority);

    /**
     * 添加Authority
     * @param authority authority
     * @return int
     */
    int addAuthority(Authority authority);
}
