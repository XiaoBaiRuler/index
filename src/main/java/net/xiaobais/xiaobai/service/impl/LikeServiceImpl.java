package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.LikeMapper;
import net.xiaobais.xiaobai.model.Like;
import net.xiaobais.xiaobai.model.LikeExample;
import net.xiaobais.xiaobai.service.LikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/3/4 17:19
 * @Version 1.0
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    private LikeMapper likeMapper;

    @Override
    public void addLike(Integer userId, Integer nodeId) {
        Like like = new Like();
        like.setUserId(userId);
        like.setLikeId(nodeId);
        likeMapper.insert(like);
    }

    @Override
    public boolean isLike(Integer userId, Integer nodeId) {
        LikeExample likeExample = new LikeExample();
        likeExample.createCriteria().andUserIdEqualTo(userId)
                .andLikeIdEqualTo(nodeId);
        return !likeMapper.selectByExample(likeExample).isEmpty();
    }
}
