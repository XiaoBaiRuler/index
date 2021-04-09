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
     * @param isPrivate 0:非私有 1:私有
     * @return List<Node>
     */
    List<Node> findPreviousByNodeId(Integer nodeId,
                                    Integer pageNumber,
                                    Integer pageSize,
                                    Integer isPrivate);

    /**
     * 通过NodeId查找所有非私有前置节点
     * @param nodeId nodeId
     * @return List<Node>
     */
    List<Node> findPreviousByNodeId(Integer nodeId);

    /**
     * 通过用户id查找私有前置节点
     * @param nodeId nodeId
     * @param userId userId
     * @return List<Node>
     */
    List<Node> findPrivatePreviousByNodeIdAndUserId(Integer nodeId, Integer userId);

    /**
     * 根据nodeId和nodeName来查找所有非私有前置节点
     * @param nodeId nodeId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @param isPrivate 0:非私有 1:私有
     * @return List<Node>
     */
    List<Node> findPreviousByNodeIdAndTitle(Integer nodeId,
                                            Integer pageNumber,
                                            Integer pageSize,
                                            String title,
                                            Integer isPrivate);

    /**
     * 统计该前置节点有多少个
     * @param nodeId nodeId
     * @param isPrivate 0:非私有 1:私有
     * @return int
     */
    int countPreviousNode(Integer nodeId, Integer isPrivate);

    /**
     * 根据title统计该前置节点有多少个
     * @param nodeId nodeId
     * @param title title
     * @param isPrivate 0:非私有 1:私有
     * @return int
     */
    int countPreviousNode(Integer nodeId, String title, Integer isPrivate);

    /**
     * 添加前置关系
     * @param nodeId nodeId
     * @param previousId previousId
     * @return int
     */
    int addPrevious(Integer nodeId, Integer previousId);

    /**
     * 删除前置关系
     * @param nodeId nodeId
     * @return boolean
     */
    boolean deletePrevious(Integer nodeId);
}
