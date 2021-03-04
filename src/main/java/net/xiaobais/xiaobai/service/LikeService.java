package net.xiaobais.xiaobai.service;

/**
 * @Author xiaobai
 * @Date 2021/3/4 17:18
 * @Version 1.0
 */
public interface LikeService {

    /**
     * 添加点赞关系
     * @param userId userId
     * @param nodeId nodeId
     */
    void addLike(Integer userId, Integer nodeId);

    /**
     * 判断是否点赞过
     * @param userId usrId
     * @param nodeId nodeId
     * @return boolean
     */
    boolean isLike(Integer userId, Integer nodeId);
}
