package net.xiaobais.xiaobai.mapper;

import net.xiaobais.xiaobai.model.Node;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/5 12:53
 * @Version 1.0
 */
public interface MyPreviousMapper {

    /**
     * 通过nodeId查找非私有前置节点
     * @param nodeId nodeId
     * @param pageNumber 第几页
     * @param pageSize 页大小
     * @return List<node>
     */
    List<Node> findNotPrivatePreviousByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize);

    /**
     * 统计nodeId的非私有前置Id的个数
     * @param nodeId nodeId
     * @return int
     */
    int countNotPrivatePreviousByNodeId(Integer nodeId);

    /**
     * 根据nodeId和nodeName来查找所有非私有前置节点
     * @param nodeId nodeId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @return List<node>
     */
    List<Node> findNotPrivatePreviousByNodeIdAndTitle(Integer nodeId, Integer pageNumber, Integer pageSize, String title);
}
