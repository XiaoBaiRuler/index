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
     * @param isPrivate isPrivate
     * @return List<Node>
     */
    List<Node> findNextByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize,
                                          Integer isPrivate);

    /**
     * 获取非私有后置节点的个数
     * @param nodeId nodeId
     * @param isPrivate isPrivate
     * @return int
     */
    int countNextByNodeId(Integer nodeId, Integer isPrivate);

    /**
     * 根据标题获取非私有后置节点
     * @param nodeId nodeId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @Param isPrivate isPrivate
     * @return List<Node>
     */
    List<Node> findNextByNodeIdAndTitle(
            @Param("nodeId") Integer nodeId,
            @Param("pageNumber") Integer pageNumber,
            @Param("pageSize") Integer pageSize,
            @Param("title") String title,
            @Param("isPrivate") Integer isPrivate);

    /**
     * 获取非私有后置节点的个数
     * @param nodeId nodeId
     * @param title title
     * @param isPrivate isPrivate
     * @return int
     */
    int countNextByNodeIdAndTitle(
            @Param("nodeId") Integer nodeId,
            @Param("title") String title,
            @Param("isPrivate") Integer isPrivate);
}
