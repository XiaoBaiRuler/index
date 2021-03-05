package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Node;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/3 16:53
 * @Version 1.0
 */
public interface NextService {

    /**
     * 查找非私有后置节点
     * @param nodeId nodeId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @return List<Node>
     */
    List<Node> findNextByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize);

    /**
     * 统计非私有后置节点
     * @param nodeId nodeId
     * @return int
     */
    int countNextNode(Integer nodeId);
}
