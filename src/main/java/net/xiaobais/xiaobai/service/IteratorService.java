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

    /**
     * 分页查找迭代节点
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param message 迭代理由
     * @return List<Iterator>
     */
    List<Iterator> getAllIterator(Integer pageNumber, Integer pageSize, String message);

    /**
     * 统计迭代节点个数
     * @param message message
     * @return Long
     */
    Long countAllIterator(String message);

    /**
     * 删除迭代节点
     * @param nodeId nodeId
     * @param iteratorId iteratorId
     * @exception Exception
     */
    void deleteIterator(Integer nodeId, Integer iteratorId) throws Exception;

    /**
     * 更新迭代理由
     * @param iteratorId iteratorId
     * @param nodeId nodeId
     * @param reason reason
     * @return
     */
    boolean updateIterator(Integer iteratorId, Integer nodeId, String reason);
}
