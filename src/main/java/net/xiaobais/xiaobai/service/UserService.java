package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.vo.PublicUserVo;

import java.util.List;

/**
 * 用户Service
 *
 * @Author xiaobai
 * @Date 2021/2/22 15:08
 * @Version 1.0
 */
public interface UserService {

    /**
     * 添加用户
     * @param username username
     * @param email email
     * @param password password
     * @return int
     */
    int addUser(String username, String email, String password);

    /**
     * 是否已存在用户
     * @param username username
     * @return boolean
     */
    boolean hasUser(String username);

    /**
     * 根据userId删除用户
     * @param userId userId
     * @return int
     */
    int deleteUserById(Integer userId);

    /**
     * 获取所有用户
     * @return List<User>
     */
    List<User> getAll();

    /**
     * 更新User(必须包含UserID)
     * @param user user
     * @return int
     */
    int updateUser(User user);

    /**
     * 检查是否用户名和密码正确
     * @param username username
     * @param password password
     * @return String
     */
    String checkPassword(String username, String password);

    /**
     * 根据userId查找用户
     * @param userId userId
     * @return User
     */
    User getUserById(Integer userId);

    /**
     * 添加个人index节点
     * @param nodeId nodeId
     * @param userId userId
     * @return int
     */
    int insertIndexId(Integer nodeId, Integer userId);

    /**
     * 通过userId获取基本信息
     * @param userId userId
     * @return PublicUserVo
     */
    PublicUserVo getPublicUserVo(Integer userId);

    /**
     * 分页获取所有用户信息
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param message message
     * @return List<User>
     */
    List<User> getAllUsersByPageAndMessage(Integer pageNumber, Integer pageSize, String message);

    /**
     * 通过message模糊查询用户个数
     * @param message message
     * @return Long
     */
    Long countAllUsersByMessage(String message);
}
