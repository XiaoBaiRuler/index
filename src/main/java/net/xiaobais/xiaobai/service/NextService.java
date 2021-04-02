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
     * 查找所有后置节点
     * @param nodeId nodeId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param isPrivate 0:非私有 1:私有
     * @return List<Node>
     */
    List<Node> findNextByNodeId(Integer nodeId,
                                Integer pageNumber,
                                Integer pageSize,
                                Integer isPrivate);

    /**
     * 查找非私有后置节点
     * @param nodeId nodeId
     * @return List<Node>
     */
    List<Node> findNextByNodeId(Integer nodeId);

    /**
     * 通过用户id查找私有后置节点
     * @param nodeId nodeId
     * @param userId userId
     * @return List<Node>
     */
    List<Node> findPrivateNextByNodeIdAndUserId(Integer nodeId, Integer userId);

    /**
     * 通过title查找非私有后置节点
     * @param nodeId nodeId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @param isPrivate 0:非私有 1:私有
     * @return List<Node>
     */
    List<Node> findNextByNodeIdAndTitle(Integer nodeId,
                                        Integer pageNumber,
                                        Integer pageSize,
                                        String title,
                                        Integer isPrivate);


    /**
     * 统计非私有后置节点
     * @param nodeId nodeId
     * @param isPrivate 0:非私有 1:私有
     * @return int
     */
    int countNextNode(Integer nodeId, Integer isPrivate);


    /**
     * 根据title统计非私有后置节点
     * @param nodeId nodeId
     * @param title title
     * @param isPrivate 0:非私有 1:私有
     * @return int
     */
    int countNextNode(Integer nodeId, String title, Integer isPrivate);
}
