package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.IteratorMapper;
import net.xiaobais.xiaobai.model.Iterator;
import net.xiaobais.xiaobai.model.IteratorExample;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.IteratorService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/13 20:15
 * @Version 1.0
 */
@Service
public class IteratorServiceImpl implements IteratorService {

    @Resource
    private IteratorMapper iteratorMapper;
    @Resource
    private PublicNodeService nodeService;

    @Override
    public List<Node> getIteratorByNodeId(Integer nodeId) {
        IteratorExample example = new IteratorExample();
        example.createCriteria().andNodeIdEqualTo(nodeId);
        List<Iterator> iterators = iteratorMapper.selectByExample(example);
        List<Node> lists = new ArrayList<>(iterators.size());
        iterators.forEach(it -> lists.add(nodeService.findNodeById(it.getIteratorId())));
        return lists;
    }

    @Override
    public void insertIterator(Iterator iterator) {
        iteratorMapper.insert(iterator);
    }

    @Override
    public Node getNodeByIteratorId(Integer iteratorId) {
        IteratorExample example = new IteratorExample();
        example.createCriteria().andIteratorIdEqualTo(iteratorId);
        List<Iterator> iterators = iteratorMapper.selectByExample(example);
        if (!iterators.isEmpty()){
            return nodeService.findNodeById(iterators.get(0).getNodeId());
        }
        return null;
    }

}
