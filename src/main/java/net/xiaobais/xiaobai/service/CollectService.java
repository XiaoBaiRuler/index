package net.xiaobais.xiaobai.service;

/**
 * @Author xiaobai
 * @Date 2021/3/4 16:06
 * @Version 1.0
 */
public interface CollectService {

    /**
     * 添加收藏关系
     * @param userId userId
     * @param nodeId nodeId
     */
    void addCollect(Integer userId, Integer nodeId);

    /**
     * 判断是否收藏过
     * @param userId usrId
     * @param nodeId nodeId
     * @return boolean
     */
    boolean isCollect(Integer userId, Integer nodeId);
}
