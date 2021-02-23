package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.AuthorityMapper;
import net.xiaobais.xiaobai.model.Authority;
import net.xiaobais.xiaobai.model.AuthorityExample;
import net.xiaobais.xiaobai.service.AuthorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/23 21:22
 * @Version 1.0
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Resource
    private AuthorityMapper authorityMapper;

    @Override
    public List<Authority> getAll() {
        return authorityMapper.selectByExample(new AuthorityExample());
    }

    @Override
    public Authority getAuthorityByTag(String authorityTag) {

        AuthorityExample authorityExample = new AuthorityExample();
        authorityExample.createCriteria().andAuthorityTagEqualTo(authorityTag);
        return authorityMapper.selectByExample(authorityExample).get(0);
    }

    @Override
    public int deleteAuthorityById(Integer id) {
        return authorityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateAuthority(Authority authority) {
        return authorityMapper.updateByPrimaryKey(authority);
    }

    @Override
    public int addAuthority(Authority authority) {
        return authorityMapper.insert(authority);
    }
}
