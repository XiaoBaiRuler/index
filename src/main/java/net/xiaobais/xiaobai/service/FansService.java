package net.xiaobais.xiaobai.service;

/**
 * @Author xiaobai
 * @Date 2021/4/11 17:52
 * @Version 1.0
 */
public interface FansService {

    /**
     * 根据userId和fansId添加粉丝关系
     * @param userId userId
     * @param fansId fansId
     * @return int
     */
    int addFansByUserIdAndFansId(Integer userId, Integer fansId);

    /**
     * 删除粉丝关系
     * @param userId userId
     * @param fansId fanId
     * @return
     */
    int deleteFansByUserIdAndfansId(Integer userId, Integer fansId);
}
