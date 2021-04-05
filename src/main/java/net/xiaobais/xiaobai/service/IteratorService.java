package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Iterator;
import net.xiaobais.xiaobai.model.Node;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/13 19:45
 * @Version 1.0
 */
public interface IteratorService {

    /**
     * 通过nodeId查找迭代的节点
     * @param nodeId nodeId
     * @return List<Node>
     */
    List<Node> getIteratorByNodeId(Integer nodeId);

    /**
     * 添加迭代关系
     * @param iterator iterator
     */
    int insertIterator(Iterator iterator);

    /**
     * 通过迭代节点Id查找被迭代的节点
     * @param iteratorId iteratorId
     * @return Node
     */
    Node getNodeByIteratorId(Integer iteratorId);
}
