package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.MyPreviousMapper;
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
    private MyPreviousMapper myPreviousMapper;
    @Resource
    private PreviousMapper previousMapper;
    @Resource
    private NodeService nodeService;

    @Override
    public List<Node> findPreviousByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * pageSize + 1;
        return myPreviousMapper.findNotPrivatePreviousByNodeId(
                nodeId, pageNumber, pageSize);
    }

    @Override
    public List<Node> findPreviousByNodeId(Integer nodeId) {
        PreviousExample previousExample = new PreviousExample();
        previousExample.createCriteria().andNodeIdEqualTo(nodeId);
        List<Previous> previous = previousMapper.selectByExample(previousExample);
        List<Node> nodes = new ArrayList<>();
        previous.forEach(p -> nodes.add(nodeService.findNodeByNodeIdAndNotIsPrivate(p.getPreviousId())));
        return nodes;
    }

    @Override
    public int countPreviousNode(Integer nodeId) {
        return myPreviousMapper.countNotPrivatePreviousByNodeId(nodeId);
    }

    @Override
    public List<Node> findPreviousByNodeIdAndTitle(Integer nodeId, Integer pageNumber,
                                                   Integer pageSize, String title) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * pageSize + 1;
        title = "%" + title + "%";
        return myPreviousMapper.findNotPrivatePreviousByNodeIdAndTitle(
                nodeId, pageNumber, pageSize, title
        );
    }

    @Override
    public int countPreviousNode(Integer nodeId, String title) {
        title = "%" + title + "%";
        return myPreviousMapper.countNotPrivatePreviousByNodeIdAndTitle(nodeId, title);
    }
}
