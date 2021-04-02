package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.NodeMapper;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.NodeExample;
import net.xiaobais.xiaobai.service.PrivateNodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/30 17:30
 * @Version 1.0
 */
@Service
public class PrivateNodeServiceImpl implements PrivateNodeService {

    @Resource
    private NodeMapper nodeMapper;

    @Override
    public Node findNodeByNodeIdAndIsPrivateAndUserId(Integer nodeId, Integer userId) {
        NodeExample nodeExample = new NodeExample();
        NodeExample.Criteria criteria = nodeExample.createCriteria().
                andNodeIdEqualTo(nodeId).andIsPrivateEqualTo(userId != 1);
        if (userId != 1){
            criteria.andUserIdEqualTo(userId);
        }
        List<Node> nodes = nodeMapper.selectByExample(nodeExample);
        return !nodes.isEmpty() ? nodes.get(0) : null;
    }

}
