package net.xiaobais.xiaobai.service.impl;

import com.github.pagehelper.PageHelper;
import net.xiaobais.xiaobai.mapper.IteratorMapper;
import net.xiaobais.xiaobai.model.Iterator;
import net.xiaobais.xiaobai.model.IteratorExample;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.IteratorService;
import net.xiaobais.xiaobai.service.PrivateNodeService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private PrivateNodeService privateNodeService;

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
    public int insertIterator(Iterator iterator) {
        return iteratorMapper.insert(iterator);
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

    @Override
    public List<Iterator> getAllIterator(Integer pageNumber, Integer pageSize, String message) {
        IteratorExample example = new IteratorExample();
        if (!"".equals(message)){
            example.createCriteria().andIteratorReasonLike("%" + message + "%");
        }
        PageHelper.startPage(pageNumber, pageSize);
        return iteratorMapper.selectByExample(example);
    }

    @Override
    public Long countAllIterator(String message) {
        IteratorExample example = new IteratorExample();
        if (!"".equals(message)){
            example.createCriteria().andIteratorReasonLike("%" + message + "%");
        }
        long l = iteratorMapper.countByExample(example);
        return l % 8 == 0 ? l / 8 : l / 8 + 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIterator(Integer nodeId, Integer iteratorId) throws Exception {
        IteratorExample example = new IteratorExample();
        example.createCriteria().andNodeIdEqualTo(nodeId)
                .andIteratorIdEqualTo(iteratorId);
        if (iteratorMapper.deleteByExample(example) == -1){
            throw new Exception("删除迭代关系失败");
        }
        if (privateNodeService.deleteNodeByNodeId(iteratorId) == -1){
            throw new Exception("删除迭代节点失败");
        }
    }

    @Override
    public boolean updateIterator(Integer iteratorId, Integer nodeId, String reason) {
        IteratorExample example = new IteratorExample();
        example.createCriteria().andIteratorIdEqualTo(iteratorId)
                .andNodeIdEqualTo(nodeId);
        Iterator it = new Iterator();
        it.setIteratorReason(reason);
        return iteratorMapper.updateByExampleSelective(it, example) != -1;
    }

}
