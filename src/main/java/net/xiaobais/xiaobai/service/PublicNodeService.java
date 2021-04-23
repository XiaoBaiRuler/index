package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.vo.NodeVo;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/3 12:57
 * @Version 1.0
 */
public interface PublicNodeService {

    /* all */

    /**
     * 获取网站根节点
     * @return Node
     */
    Node findIndex();

    /**
     * 通过nodeId获取公开节点
     * @param nodeId nodeId
     * @return Node
     */
    Node findPublicNodeByNodeId(Integer nodeId);

    /**
     * 根据节点ID查找节点
     * @param id id
     * @return Node
     */
    Node findNodeById(Integer id);

    /**
     * 添加点赞树
     * @param id id
     */
    void addLike(Integer id);

    /**
     * 添加收藏数
     * @param id id
     */
    void addCount(Integer id);

    /**
     * 添加node
     * @param node node
     * @return int
     */
    int insertNode(Node node);

    /* public */

    /**
     * 根据节点ID查找非私有节点
     * @param id NodeId
     * @return Node
     */
    Node findNodeByNodeIdAndNotIsPrivate(Integer id);

    /**
     * 根据用户Id和nodeId获取用户的公开内容
     * @param userId userId
     * @param nodeId nodeId
     * @return Node
     */
    Node findPublicNodeByUserIdAndNodeId(Integer userId, Integer nodeId);

    /**
     * 查找公开的最多收藏的5个
     * @param top top
     * @return List<Node>
     */
    List<Node> findNodeByTopCollect(Integer top);

    /**
     * 查找公开的最多点赞的5个
     * @param top top
     * @return List<Node>
     */
    List<Node> findNodeByTopStar(Integer top);

    /**
     * 通过userId和nodeName来查找所有收藏节点
     * @param userId userId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @return List<Node>
     */
    List<Node> findNodeByCollect(Integer userId,
                                 Integer pageNumber,
                                 Integer pageSize,
                                 String title);

    /**
     * 通过userId和nodeName来查找所有收藏节点
     * @param userId userId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @return List<Node>
     */
    List<Node> findNodeByLike(Integer userId,
                              Integer pageNumber,
                              Integer pageSize,
                              String title);

    /**
     * 通过userId和nodeName来查找所有收藏节点
     * @param userId userId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @return List<Node>
     */
    List<Node> findNodeByPublic(Integer userId,
                              Integer pageNumber,
                              Integer pageSize,
                              String title);

    /**
     * 通过userId和title获取公开节点个数
     * @param userId userId
     * @param title title
     * @return Integer
     */
    Integer countPublicNodeByUserIdAndTitle(Integer userId, String title);

    /**
     * 通过userId和title获取收藏节点个数
     * @param userId userId
     * @param title title
     * @return Integer
     */
    Integer countCollectNodeByUserIdAndTitle(Integer userId, String title);

    /**
     * 获取所有公开节点
     * @return List<NodeVo>
     */
    List<NodeVo> getAllPublicNode();

    /**
     * 根据标题查找node
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @return List<Node>
     */
    List<Node> getNodeByTitle(Integer pageNumber, Integer pageSize, String title);

    /**
     * 根据标题查找node页数
     * @param title title
     * @param pageSize pageSize
     * @return long
     */
    Long getNodeCountByTitle(String title, Integer pageSize);

}
