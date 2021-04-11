package net.xiaobais.xiaobai.mapper;

/**
 * @Author xiaobai
 * @Date 2021/4/11 18:21
 * @Version 1.0
 */
public interface MyFansMapper {

    /**
     * 通过userId获取粉丝人数
     * @param userId userId
     * @return int
     */
    int countFansByUserId(Integer userId);
}
