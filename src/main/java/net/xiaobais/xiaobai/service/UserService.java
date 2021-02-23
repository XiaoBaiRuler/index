package net.xiaobais.xiaobai.service;

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
}
