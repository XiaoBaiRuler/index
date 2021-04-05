package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Node;

/**
 * @Author xiaobai
 * @Date 2021/3/30 17:30
 * @Version 1.0
 */
public interface PrivateNodeService {

    /**
     * 查找属于UserId
     * @param nodeId nodeId
     * @param userId userId
     * @return Node
     */
    Node findNodeByNodeIdAndIsPrivateAndUserId(Integer nodeId, Integer userId);


    /**
     * 添加node
     * @param node node
     * @return int
     */
    int insertNode(Node node);
}
