package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.MyNextMapper;
import net.xiaobais.xiaobai.mapper.NextMapper;
import net.xiaobais.xiaobai.model.Next;
import net.xiaobais.xiaobai.model.NextExample;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.NextService;
import net.xiaobais.xiaobai.service.PrivateNodeService;
import net.xiaobais.xiaobai.service.PublicNodeService;
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
    private PublicNodeService publicNodeService;
    @Resource
    private PrivateNodeService privateNodeService;

    private static final Integer SIZE = 6;

    @Override
    public List<Node> findNextByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize,
                                       Integer isPrivate) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * SIZE;
        return myNextMapper.findNextByNodeId(
                nodeId, pageNumber, pageSize, isPrivate);
    }

    @Override
    public List<Node> findNextByNodeId(Integer nodeId) {
        NextExample nextExample = new NextExample();
        nextExample.createCriteria().andNodeIdEqualTo(nodeId);
        List<Next> next = nextMapper.selectByExample(nextExample);
        List<Node> lists = new ArrayList<>();
        next.forEach(n -> lists.add(publicNodeService.findNodeByNodeIdAndNotIsPrivate(n.getNextId())));
        return lists;
    }

    @Override
    public List<Node> findPrivateNextByNodeIdAndUserId(Integer nodeId, Integer userId) {
        NextExample nextExample = new NextExample();
        nextExample.createCriteria().andNodeIdEqualTo(nodeId);
        List<Next> next = nextMapper.selectByExample(nextExample);
        List<Node> lists = new ArrayList<>();
        next.forEach(n -> lists.add(privateNodeService.findNodeByNodeIdAndIsPrivateAndUserId(n.getNextId(), userId)));
        return lists;
    }

    @Override
    public List<Node> findNextByNodeIdAndTitle(Integer nodeId, Integer pageNumber,
                                               Integer pageSize, String title, Integer isPrivate) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * SIZE;
        title = "%" + title + "%";
        return myNextMapper.findNextByNodeIdAndTitle(nodeId, pageNumber, pageSize, title, isPrivate);
    }


    @Override
    public int countNextNode(Integer nodeId, Integer isPrivate) {
        return myNextMapper.countNextByNodeId(nodeId, isPrivate);
    }


    @Override
    public int countNextNode(Integer nodeId, String title, Integer isPrivate) {
        title = "%" + title + "%";
        return myNextMapper.countNextByNodeIdAndTitle(nodeId, title, isPrivate);
    }

    @Override
    public int addNext(Integer nodeId, Integer nextId) {
        Next next = new Next();
        next.setNodeId(nodeId);
        next.setNextId(nextId);
        return nextMapper.insert(next);
    }


}
