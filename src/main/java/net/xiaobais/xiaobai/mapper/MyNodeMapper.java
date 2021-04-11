package net.xiaobais.xiaobai.mapper;

import net.xiaobais.xiaobai.model.Node;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/11 19:37
 * @Version 1.0
 */
public interface MyNodeMapper {

    /**
     * 通过userId统计公开的博客个数
     * @param userId userId
     * @return int
     */
    int countPublicNodeByUserId(Integer userId);

    /**
     * 通过userId统计收集的节点
     * @param userId userId
     * @return int
     */
    int countCollectNodeByUserId(Integer userId);

    /**
     * 通过userId统计点赞过的博客
     * @param userId userId
     * @return int
     */
    int countLikeNodeByUserId(Integer userId);


    /**
     * 通过userId和title获取公开节点个数
     * @param userId userId
     * @param title title
     * @return int
     */
    int countPublicNodeByUserIdAndTitle(@Param("userId") Integer userId,
                                        @Param("title") String title);

    /**
     * 通过userId和title获取收藏节点个数
     * @param userId userId
     * @param title title
     * @return int
     */
    int countCollectNodeByUserIdAndTitle(@Param("userId") Integer userId,
                                         @Param("title") String title);

    /**
     * 通过userId和title获取点赞节点个数
     * @param userId userId
     * @param title title
     * @return int
     */
    int countLikeNodeByUserIdAndTitle(@Param("userId") Integer userId,
                                      @Param("title") String title);


    /**
     * 通过userId查找所有收藏节点
     * @param userId userId
     * @param pageNumber 开始
     * @param pageSize 结束
     * @return List<Node>
     */
    List<Node> findCollectNodeByUserId(Integer userId,
                                       Integer pageNumber,
                                       Integer pageSize);

    /**
     * 通过userId和title查找所有收藏节点
     * @param userId userId
     * @param pageNumber 开始
     * @param pageSize 结束
     * @param title 节点标题
     * @return List<Node>
     */
    List<Node> findCollectNodeByUserIdAndTitle(@Param("userId") Integer userId,
                                               @Param("pageNumber") Integer pageNumber,
                                               @Param("pageSize") Integer pageSize,
                                               @Param("title") String title);

    /**
     * 通过userId查找所有点赞节点
     * @param userId userId
     * @param pageNumber 开始
     * @param pageSize 结束
     * @return List<Node>
     */
    List<Node> findLikeNodeByUserId(Integer userId,
                                    Integer pageNumber,
                                    Integer pageSize);

    /**
     * 通过userId和title查找所有点赞节点
     * @param userId userId
     * @param pageNumber 开始
     * @param pageSize 结束
     * @param title 节点标题
     * @return List<Node>
     */
    List<Node> findLikeNodeByUserIdAndTitle(@Param("userId") Integer userId,
                                            @Param("pageNumber") Integer pageNumber,
                                            @Param("pageSize") Integer pageSize,
                                            @Param("title") String title);

    /**
     * 通过userId查找所有
     * @param userId userId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @return List<Node>
     */
    List<Node> findPublicNodeByUserId(Integer userId,
                                      Integer pageNumber,
                                      Integer pageSize);

    /**
     * 通过userId查找所有
     * @param userId userId
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param title 标题
     * @return List<Node>
     */
    List<Node> findPublicNodeByUserIdAndTitle(Integer userId,
                                              Integer pageNumber,
                                              Integer pageSize,
                                              String title);
}
