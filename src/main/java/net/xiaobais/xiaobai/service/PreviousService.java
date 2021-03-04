package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Node;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/3 16:40
 * @Version 1.0
 */
public interface PreviousService {

    /**
     * 通过NodeId查找所有前置节点
     * @param nodeId nodeId
     * @return List<Node>
     */
    List<Node> findPreviousByNodeId(Integer nodeId);
}
