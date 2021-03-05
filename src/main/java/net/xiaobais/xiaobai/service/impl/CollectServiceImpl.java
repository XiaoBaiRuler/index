package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.CollectMapper;
import net.xiaobais.xiaobai.model.Collect;
import net.xiaobais.xiaobai.model.CollectExample;
import net.xiaobais.xiaobai.service.CollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/3/4 16:52
 * @Version 1.0
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    @Override
    public void addCollect(Integer userId, Integer nodeId) {
        if(!isCollect(userId, nodeId)){
            Collect collect = new Collect();
            collect.setUserId(userId);
            collect.setCollectId(nodeId);
            collect.setCollectType("normal");
            collectMapper.insertSelective(collect);
        }
    }

    @Override
    public boolean isCollect(Integer userId, Integer nodeId) {
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andUserIdEqualTo(userId)
                .andCollectIdEqualTo(nodeId);
        return !collectMapper.selectByExample(collectExample).isEmpty();
    }
}
