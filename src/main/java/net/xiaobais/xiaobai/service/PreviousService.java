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
     * 通过NodeId查找所有非私有前置节点
     * @param pageNumber 第几页
     * @param pageSize 页大小
     * @param nodeId nodeId
     * @return List<Node>
     */
    List<Node> findPreviousByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize);

    /**
     * 统计该前置节点有多少个
     * @param nodeId nodeId
     * @return int
     */
    int countPreviousNode(Integer nodeId);

    /**
     * 根据nodeId和nodeName来查找所有非私有前置节点
     * @param nodeId nodeId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @return List<Node>
     */
    List<Node> findPreviousByNodeIdAndTitle(Integer nodeId, Integer pageNumber, Integer pageSize, String title);
}
