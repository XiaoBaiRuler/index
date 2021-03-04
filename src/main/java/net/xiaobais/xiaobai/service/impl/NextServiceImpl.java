package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.NextMapper;
import net.xiaobais.xiaobai.model.Next;
import net.xiaobais.xiaobai.model.NextExample;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.NextService;
import net.xiaobais.xiaobai.service.NodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/3 16:54
 * @Version 1.0
 */
@Service
public class NextServiceImpl implements NextService {

    @Resource
    private NextMapper nextMapper;
    @Resource
    private NodeService nodeService;

    @Override
    public List<Node> findNextByNodeId(Integer nodeId) {

        NextExample nextExample = new NextExample();
        nextExample.createCriteria().andNodeIdEqualTo(nodeId);
        List<Next> next = nextMapper.selectByExample(nextExample);
        List<Node> nodes = new ArrayList<>();
        next.forEach(node -> nodes.add(nodeService.findNodeById(node.getNextId())));
        return nodes;
    }
}
