package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.FollowMapper;
import net.xiaobais.xiaobai.model.Follow;
import net.xiaobais.xiaobai.model.FollowExample;
import net.xiaobais.xiaobai.service.FollowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/12 20:58
 * @Version 1.0
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Resource
    private FollowMapper followMapper;

    @Override
    public boolean getFollowByUserIdAndFollowId(Integer userId, Integer followId) {
        FollowExample example = new FollowExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andFollowIdEqualTo(followId).andStatusEqualTo(true);
        List<Follow> follows = followMapper.selectByExample(example);
        return follows != null && follows.size() != 0;
    }

    @Override
    public int addFollowByUserIdAndFollowId(Integer userId, Integer followId) {
        FollowExample example = new FollowExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andFollowIdEqualTo(followId);
        List<Follow> follows = followMapper.selectByExample(example);
        Follow f = null;
        if (follows != null && !follows.isEmpty()){
            f = follows.get(0);
            f.setUpdateDate(new Date());
            f.setStatus(true);
            return followMapper.updateByExample(f,example);
        }
        else {
            Follow follow = new Follow();
            follow.setUserId(userId);
            follow.setFollowId(followId);
            follow.setStatus(true);
            follow.setFollowDate(new Date());
            follow.setUpdateDate(new Date());
            return followMapper.insert(follow);
        }
    }

    @Override
    public int deleteFollowByUserIdAndFollowId(Integer userId, Integer followId) {

        FollowExample example = new FollowExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andFollowIdEqualTo(followId);
        List<Follow> follows = followMapper.selectByExample(example);
        Follow f = null;
        if (follows != null && follows.size() != 0){
            f = follows.get(0);
            f.setUpdateDate(new Date());
            f.setStatus(false);
            return followMapper.updateByExample(f,example);
        }
        return -1;
    }
}
