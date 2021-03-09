package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.MyNextMapper;
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
    private MyNextMapper myNextMapper;
    @Resource
    private NextMapper nextMapper;
    @Resource
    private NodeService nodeService;

    @Override
    public List<Node> findNextByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * pageSize + 1;
        return myNextMapper.findNotPrivateNextByNodeId(
                nodeId, pageNumber, pageSize);
    }

    @Override
    public List<Node> findNextByNodeId(Integer nodeId) {
        NextExample nextExample = new NextExample();
        nextExample.createCriteria().andNodeIdEqualTo(nodeId);
        List<Next> next = nextMapper.selectByExample(nextExample);
        List<Node> lists = new ArrayList<>();
        next.forEach(n -> lists.add(nodeService.findNodeByNodeIdAndNotIsPrivate(n.getNextId())) );
        return lists;
    }

    @Override
    public int countNextNode(Integer nodeId) {
        return myNextMapper.countNotPrivateNextByNodeId(nodeId);
    }

    @Override
    public List<Node> findNextByNodeIdAndTitle(Integer nodeId, Integer pageNumber,
                                       Integer pageSize, String title) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * pageSize + 1;
        title = "%" + title + "%";
        return myNextMapper.findNotPrivateNextByNodeIdAndTitle(nodeId, pageNumber, pageSize, title);
    }

    @Override
    public int countNextNode(Integer nodeId, String title) {
        title = "%" + title + "%";
        return myNextMapper.countNotPrivateNextByNodeIdAndTitle(nodeId, title);
    }


}
