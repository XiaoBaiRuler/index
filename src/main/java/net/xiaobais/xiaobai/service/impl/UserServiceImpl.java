package net.xiaobais.xiaobai.service.impl;

import com.github.pagehelper.PageHelper;
import net.xiaobais.xiaobai.mapper.MyFansMapper;
import net.xiaobais.xiaobai.mapper.MyFollowMapper;
import net.xiaobais.xiaobai.mapper.MyNodeMapper;
import net.xiaobais.xiaobai.mapper.UserMapper;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.model.UserExample;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.vo.EditUserVo;
import net.xiaobais.xiaobai.vo.PublicUserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
    @Resource
    private MyFansMapper myFansMapper;
    @Resource
    private MyFollowMapper myFollowMapper;
    @Resource
    private MyNodeMapper myNodeMapper;

    private static final String KEY = "xiaobai";
    private static final String PUBLIC_USER_URL = "/public/user/";

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
        user.setUserAvatar("/img/logo.png");
        user.setIndexId(-1);
        user.setIsAuth(false);
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

    @Override
    public String checkPassword(String username, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username)
                .andPasswordEqualTo(DigestUtils.md5Hex(password + KEY));
        List<User> users = userMapper.selectByExample(userExample);
        return users != null && !users.isEmpty() ? "1" : "0";
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int insertIndexId(Integer nodeId, Integer userId) {
        User user = new User();
        user.setUserId(userId);
        user.setIndexId(nodeId);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public PublicUserVo getPublicUserVo(Integer userId) {
        PublicUserVo publicUserVo = new PublicUserVo();
        User user = userMapper.selectByPrimaryKey(userId);
        publicUserVo.setUsername(user.getUsername());
        publicUserVo.setEmail(user.getUserEmail());
        publicUserVo.setUserDesc(user.getUserDesc());
        publicUserVo.setSignTime(new SimpleDateFormat("yyyy-MM-dd").format(user.getCreateDate()));
        publicUserVo.setAvatar(user.getUserAvatar());
        publicUserVo.setUserUrl(PUBLIC_USER_URL + userId);

        publicUserVo.setFans(myFansMapper.countFansByUserId(userId));
        publicUserVo.setFollows(myFollowMapper.countFollowByUserId(userId));

        publicUserVo.setPublicBlogs(myNodeMapper.countPublicNodeByUserId(userId));
        publicUserVo.setCollectBlogs(myNodeMapper.countCollectNodeByUserId(userId));
        publicUserVo.setLikeBlogs(myNodeMapper.countLikeNodeByUserId(userId));
        publicUserVo.setAuth(user.getIsAuth());
        return publicUserVo;
    }

    @Override
    public List<User> getAllUsersByPageAndMessage(Integer pageNumber, Integer pageSize, String message) {
        UserExample example = new UserExample();
        if (!"".equals(message)){
            example.createCriteria().andUsernameLike("%" + message + "%");
        }
        PageHelper.startPage(pageNumber, pageSize);
        return userMapper.selectByExample(example);
    }

    @Override
    public Long countAllUsersByMessage(String message) {
        UserExample example = new UserExample();
        if (!"".equals(message)){
            example.createCriteria().andUsernameLike("%" + message + "%");
        }
        long l = userMapper.countByExample(example);
        return l % 8 == 0 ? l / 8 : l / 8 + 1;
    }

    @Override
    public boolean dealUser(Boolean enabled, Integer userId) {
        User user = new User();
        user.setUserId(userId);
        user.setEnabled(enabled);
        return userMapper.updateByPrimaryKeySelective(user) != -1;
    }

    @Override
    public boolean editUser(Integer userId, EditUserVo editUserVo, String avatar) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername(editUserVo.getUsername());
        user.setUserDesc(editUserVo.getUserDesc());
        user.setUserEmail(editUserVo.getUserEmail());
        user.setUserAvatar(avatar);
        user.setIsAuth(false);
        return userMapper.updateByPrimaryKeySelective(user) != -1;
    }

}
