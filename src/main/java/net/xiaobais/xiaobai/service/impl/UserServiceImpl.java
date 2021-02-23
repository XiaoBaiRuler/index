package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.UserMapper;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.model.UserExample;
import net.xiaobais.xiaobai.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/22 15:10
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    private static final String KEY = "xiaobai";

    @Override
    public int addUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(DigestUtils.md5Hex(password + KEY));
        user.setCreateDate(new Date());
        user.setLastLoginTime(new Date());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setUserEmail(email);
        user.setUserAvatar("/images/logo.png");
        userMapper.insert(user);
        return user.getUserId();
    }

    @Override
    public boolean hasUser(String username){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return users != null && !users.isEmpty();
    }

    @Override
    public int deleteUserById(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
