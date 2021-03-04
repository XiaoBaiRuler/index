package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.PreviousMapper;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.Previous;
import net.xiaobais.xiaobai.model.PreviousExample;
import net.xiaobais.xiaobai.service.NodeService;
import net.xiaobais.xiaobai.service.PreviousService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/3 16:42
 * @Version 1.0
 */
@Service
public class PreviousServiceImpl implements PreviousService {

    @Resource
    private PreviousMapper previousMapper;
    @Resource
    private NodeService nodeService;

    @Override
    public List<Node> findPreviousByNodeId(Integer nodeId) {

        PreviousExample previousExample = new PreviousExample();
        previousExample.createCriteria().andNodeIdEqualTo(nodeId);
        List<Previous> previous = previousMapper.selectByExample(previousExample);
        List<Node> nodes = new ArrayList<>();
        previous.forEach(node -> nodes.add(nodeService.findNodeById(node.getPreviousId())));
        return nodes;
    }
}
