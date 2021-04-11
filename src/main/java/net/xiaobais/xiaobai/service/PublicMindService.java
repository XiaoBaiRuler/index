package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.vo.MindVo;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/7 21:41
 * @Version 1.0
 */
public interface PublicMindService {

    /**
     * 按层级查找所有节点
     * @param level level
     * @param nodeId nodeId
     * @return List<MindVo>
     */
    List<MindVo> getMindVoByLevel(Integer level, Integer nodeId);

    /**
     * 查找当前节点的所有迭代节点
     * @param nodeId nodeId
     * @return List<MindVo>
     */
    List<MindVo> getIteratorMindVoByNodeId(Integer nodeId);

    /**
     * 通过userId查找最新收藏的8个博客
     * @param userId userId
     * @return List<MindVo>
     */
    List<MindVo> getCollectMindVoByUserId(Integer userId);

    /**
     * 通过userId查找最新的公开8个博客
     * @param userId userId
     * @return List<MindVo>
     */
    List<MindVo> getPublicMindVoByUserId(Integer userId);
}
