package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.NodeMapper;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.NodeExample;
import net.xiaobais.xiaobai.service.PrivateNodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    @Override
    public int insertNode(Node node) {
        int insert = nodeMapper.insert(node);
        return insert != -1 ? node.getNodeId() : -1;
    }

    @Override
    public int updateNodeByNodeId(Integer nodeId, String nodeName) {
        Node node = new Node();
        node.setNodeId(nodeId);
        node.setNodeName(nodeName);
        node.setUpdateDate(new Date());
        return nodeMapper.updateByPrimaryKeySelective(node);
    }

    @Override
    public int deleteNodeByNodeId(Integer nodeId) {
        return nodeMapper.deleteByPrimaryKey(nodeId);
    }

}
