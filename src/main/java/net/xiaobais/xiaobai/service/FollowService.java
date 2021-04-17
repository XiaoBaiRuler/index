package net.xiaobais.xiaobai.service;

/**
 * @Author xiaobai
 * @Date 2021/4/12 20:56
 * @Version 1.0
 */
public interface FollowService {

    /**
     * 根据userId和关注的用户Id查看关注关系是否存在
     * @param userId userId
     * @param followId followId
     * @return boolean
     */
    boolean getFollowByUserIdAndFollowId(Integer userId, Integer followId);

    /**
     * 添加follow关系
     * @param userId userId
     * @param followId followId
     * @return int
     */
    int addFollowByUserIdAndFollowId(Integer userId, Integer followId);

    /**
     * 删除follow关系
     * @param userId userId
     * @param followId followId
     * @return int
     */
    int deleteFollowByUserIdAndFollowId(Integer userId, Integer followId);

}
