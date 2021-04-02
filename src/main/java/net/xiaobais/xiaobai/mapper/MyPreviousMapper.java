package net.xiaobais.xiaobai.mapper;

import net.xiaobais.xiaobai.model.Node;
import org.apache.ibatis.annotations.Param;

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
     * @param isPrivate 是否私有
     * @return List<node>
     */
    List<Node> findPreviousByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize,
                                              Integer isPrivate);

    /**
     * 统计nodeId的非私有前置Id的个数
     * @param nodeId nodeId
     * @param isPrivate 是否私有
     * @return int
     */
    int countPreviousByNodeId(Integer nodeId, Integer isPrivate);

    /**
     * 根据nodeId和nodeName来查找所有非私有前置节点
     * @param nodeId nodeId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title title
     * @param isPrivate 是否私有
     * @return List<node>
     */
    List<Node> findPreviousByNodeIdAndTitle(
            @Param("nodeId") Integer nodeId,
            @Param("pageNumber") Integer pageNumber,
            @Param("pageSize") Integer pageSize,
            @Param("title") String title,
            @Param("isPrivate") Integer isPrivate);

    /**
     * 统计nodeId的非私有前置Id的个数
     * @param nodeId nodeId
     * @param title title
     * @param isPrivate 是否私有
     * @return int
     */
    int countPreviousByNodeIdAndTitle(
            @Param("nodeId") Integer nodeId,
            @Param("title") String title,
            @Param("isPrivate") Integer isPrivate);

}
