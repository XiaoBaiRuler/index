package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.vo.NodeVo;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/30 17:30
 * @Version 1.0
 */
public interface PrivateNodeService {

    /**
     * 查找属于UserId，管理员可以查看所有公开节点
     * @param nodeId nodeId
     * @param userId userId
     * @return Node
     */
    Node findNodeByNodeIdAndIsPrivateAndUserId(Integer nodeId, Integer userId);


    /**
     * 管理员查找用户的私有节点
     * @param nodeId nodeId
     * @return Node
     */
    Node findNodeByNodeId(Integer nodeId);


    /**
     * 添加node
     * @param node node
     * @return int
     */
    int insertNode(Node node);

    /**
     * 更新博客内容
     * @param nodeId nodeId
     * @param nodeName nodeName
     * @return int
     */
    int updateNodeByNodeId(Integer nodeId, String nodeName);

    /**
     * 根据nodeId删除节点
     * @param nodeId nodeId
     * @return int
     */
    int deleteNodeByNodeId(Integer nodeId);

    /**
     * 通过userId查找所有私有Node
     * @param userId userId
     * @return List<NodeVo>
     */
    List<NodeVo> getAllPrivate(Integer userId);

    /**
     * 通过标题查询私有节点
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @param userId userId
     * @return List<Node>
     */
    List<Node> getPrivateNodeByStr(Integer pageNumber, Integer pageSize, String title, Integer userId);

    /**
     * 按title查询私有节点个数
     * @param title title
     * @param userId userId
     * @param pageSize pageSize
     * @return long
     */
    Long getPrivateNodeCountByStr(String title, Integer userId, Integer pageSize);
}
