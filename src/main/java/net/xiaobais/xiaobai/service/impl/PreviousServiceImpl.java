package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.MyPreviousMapper;
import net.xiaobais.xiaobai.mapper.PreviousMapper;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.Previous;
import net.xiaobais.xiaobai.model.PreviousExample;
import net.xiaobais.xiaobai.service.PreviousService;
import net.xiaobais.xiaobai.service.PrivateNodeService;
import net.xiaobais.xiaobai.service.PublicNodeService;
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

    private static final Integer SIZE = 6;

    @Override
    public List<Node> findPreviousByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize, Integer isPrivate) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * SIZE;
        return myPreviousMapper.findPreviousByNodeId(
                nodeId, pageNumber, pageSize, isPrivate);
    }
    @Override
    public List<Node> findPreviousByNodeId(Integer nodeId) {
        PreviousExample previousExample = new PreviousExample();
        previousExample.createCriteria().andNodeIdEqualTo(nodeId);
        List<Previous> previous = previousMapper.selectByExample(previousExample);
        List<Node> nodes = new ArrayList<>();
        previous.forEach(p -> {
            Node node = publicNodeService.findNodeByNodeIdAndNotIsPrivate(p.getPreviousId());
            if (node != null){
                nodes.add(node);
            }
        });
        return nodes;
    }

    @Override
    public List<Node> findPrivatePreviousByNodeIdAndUserId(Integer nodeId, Integer userId) {
        PreviousExample previousExample = new PreviousExample();
        previousExample.createCriteria().andNodeIdEqualTo(nodeId);
        List<Previous> previous = previousMapper.selectByExample(previousExample);
        List<Node> nodes = new ArrayList<>();
        previous.forEach(p -> nodes.add(privateNodeService.findNodeByNodeIdAndIsPrivateAndUserId(p.getPreviousId(), userId)));
        return nodes;
    }

    @Override
    public List<Node> findPreviousByNodeIdAndTitle(Integer nodeId, Integer pageNumber,
                                                   Integer pageSize, String title,
                                                   Integer isPrivate) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * SIZE;
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

    @Override
    public int addPrevious(Integer nodeId, Integer previousId) {
        Previous previous = new Previous();
        previous.setNodeId(nodeId);
        previous.setPreviousId(previousId);
        return previousMapper.insert(previous);
    }

    @Override
    public boolean deletePrevious(Integer nodeId) {
        PreviousExample example1 = new PreviousExample();
        example1.createCriteria().andNodeIdEqualTo(nodeId);
        int i = previousMapper.deleteByExample(example1);
        PreviousExample example2 = new PreviousExample();
        example2.createCriteria().andPreviousIdEqualTo(nodeId);
        int j = previousMapper.deleteByExample(example2);
        if (i != -1){
            return true;
        }
        return j != -1;
    }


}
