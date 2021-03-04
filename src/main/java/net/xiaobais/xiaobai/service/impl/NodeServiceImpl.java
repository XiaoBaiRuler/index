package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.NodeMapper;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.NodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/3/3 13:09
 * @Version 1.0
 */
@Service
public class NodeServiceImpl implements NodeService {

    @Resource
    private NodeMapper nodeMapper;

    @Override
    public Node findIndex() {
        return nodeMapper.selectByPrimaryKey(1);
    }

    @Override
    public Node findNodeById(Integer id) {
        return nodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addLike(Integer id) {
        Node node = nodeMapper.selectByPrimaryKey(id);
        node.setStar(node.getStar() + 1);
        nodeMapper.updateByPrimaryKeySelective(node);
    }

    @Override
    public void addCount(Integer id) {
        Node node = nodeMapper.selectByPrimaryKey(id);
        node.setCollect(node.getCollect() + 1);
        nodeMapper.updateByPrimaryKeySelective(node);
    }

}
