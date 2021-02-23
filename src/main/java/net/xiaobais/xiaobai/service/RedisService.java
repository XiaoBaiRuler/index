package net.xiaobais.xiaobai.service;

/**
 * redis操作
 * 对象和数组都以json形式进行存储
 *
 * @Author xiaobai
 * @Date 2021/2/22 13:47
 * @Version 1.0
 */
public interface RedisService {

    /**
     * 存储数据
     * @param key key
     * @param value value
     */
    void set(String key, String value);

    /**
     * 获取数据
     * @param key key
     * @return value
     */
    String get(String key);

    /**
     * 设置超时事件
     * @param key key
     * @param expire expire
     * @return boolean
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     * @param key key
     */
    void remove(String key);

    /**
     * 自增操作
     * @param key key
     * @param delta delta
     * @return long
     */
    long increment(String key, long delta);
}
