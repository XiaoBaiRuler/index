package net.xiaobais.xiaobai.mapper;

import net.xiaobais.xiaobai.model.Node;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/5 15:47
 * @Version 1.0
 */
public interface MyNextMapper {

    /**
     * 获取非私有后置节点
     * @param nodeId nodeId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @return List<Node>
     */
    List<Node> findNotPrivateNextByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize);

    /**
     * 获取非私有后置节点的个数
     * @param nodeId nodeId
     * @return int
     */
    int countNotPrivateNextByNodeId(Integer nodeId);

    /**
     * 根据标题获取非私有后置节点
     * @param nodeId nodeId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @return List<Node>
     */
    List<Node> findNotPrivateNextByNodeIdAndTitle(
            @Param("nodeId") Integer nodeId,
            @Param("pageNumber") Integer pageNumber,
            @Param("pageSize") Integer pageSize,
            @Param("title") String title);

    /**
     * 获取非私有后置节点的个数
     * @param nodeId nodeId
     * @param title title
     * @return int
     */
    int countNotPrivateNextByNodeIdAndTitle(
            @Param("nodeId") Integer nodeId,
            @Param("title") String title);
}
