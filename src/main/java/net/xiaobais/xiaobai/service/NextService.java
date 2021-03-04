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
     * 通过id查找所有后置节点
     * @param nodeId nodeId
     * @return List<Node>
     */
    List<Node> findNextByNodeId(Integer nodeId);
}
