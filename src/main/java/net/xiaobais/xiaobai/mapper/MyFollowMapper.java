package net.xiaobais.xiaobai.mapper;

/**
 * @Author xiaobai
 * @Date 2021/4/11 19:10
 * @Version 1.0
 */
public interface MyFollowMapper {

    /**
     * 统计关注人数
     * @param userId userId
     * @return int
     */
    int countFollowByUserId(Integer userId);
}
