package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.MyPreviousMapper;
import net.xiaobais.xiaobai.mapper.PreviousMapper;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.Previous;
import net.xiaobais.xiaobai.model.PreviousExample;
import net.xiaobais.xiaobai.service.PrivateNodeService;
import net.xiaobais.xiaobai.service.PublicNodeService;
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
    private MyPreviousMapper myPreviousMapper;
    @Resource
    private PreviousMapper previousMapper;
    @Resource
    private PublicNodeService publicNodeService;
    @Resource
    private PrivateNodeService privateNodeService;

    @Override
    public List<Node> findPreviousByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize, Integer isPrivate) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * pageSize + 1;
        return myPreviousMapper.findPreviousByNodeId(
                nodeId, pageNumber, pageSize, isPrivate);
    }

    @Override
    public List<Node> findPreviousByNodeId(Integer nodeId) {
        PreviousExample previousExample = new PreviousExample();
        previousExample.createCriteria().andNodeIdEqualTo(nodeId);
        List<Previous> previous = previousMapper.selectByExample(previousExample);
        List<Node> nodes = new ArrayList<>();
        previous.forEach(p -> nodes.add(publicNodeService.findNodeByNodeIdAndNotIsPrivate(p.getPreviousId())));
        return nodes;
    }

    @Override
    public List<Node> findPrivatePreviousByNodeIdAndUserId(Integer nodeId, Integer userId) {
        PreviousExample previousExample = new PreviousExample();
        previousExample.createCriteria().andNodeIdEqualTo(nodeId);
        List<Previous> previous = previousMapper.selectByExample(previousExample);
        List<Node> nodes = new ArrayList<>();
        previous.forEach(p ->
                nodes.add(privateNodeService.findNodeByNodeIdAndIsPrivateAndUserId(p.getPreviousId(), userId)));
        return nodes;
    }

    @Override
    public List<Node> findPreviousByNodeIdAndTitle(Integer nodeId, Integer pageNumber,
                                                   Integer pageSize, String title,
                                                   Integer isPrivate) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * pageSize + 1;
        title = "%" + title + "%";
        return myPreviousMapper.findPreviousByNodeIdAndTitle(
                nodeId, pageNumber, pageSize, title, isPrivate);
    }

    @Override
    public int countPreviousNode(Integer nodeId, Integer isPrivate) {
        return myPreviousMapper.countPreviousByNodeId(nodeId, isPrivate);
    }

    @Override
    public int countPreviousNode(Integer nodeId, String title, Integer isPrivate) {
        title = "%" + title + "%";
        return myPreviousMapper.countPreviousByNodeIdAndTitle(nodeId, title, isPrivate);
    }
}
