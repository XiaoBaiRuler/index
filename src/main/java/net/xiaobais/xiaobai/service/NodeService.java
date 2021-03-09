package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Node;

/**
 * @Author xiaobai
 * @Date 2021/3/3 12:57
 * @Version 1.0
 */
public interface NodeService {

    /**
     * 获取网站根节点
     * @return Node
     */
    Node findIndex();

    /**
     * 根据节点ID查找节点
     * @param id id
     * @return Node
     */
    Node findNodeById(Integer id);

    /**
     * 根据节点ID查找非私有节点
     * @param id NodeId
     * @return Node
     */
    Node findNodeByNodeIdAndNotIsPrivate(Integer id);

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
}
