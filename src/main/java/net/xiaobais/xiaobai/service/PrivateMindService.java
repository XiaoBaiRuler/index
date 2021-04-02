package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.vo.MindVo;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/31 10:35
 * @Version 1.0
 */
public interface PrivateMindService {

    /**
     * 获取当前用户某个节点的思维导图
     * @param level level
     * @param nodeId nodeId
     * @param userId userId
     * @return List<MindVo>
     */
     List<MindVo> getPrivateMindVoByLevel(Integer level, Integer nodeId, Integer userId);

    /**
     * 查找当前节点的所有迭代节点
     * @param nodeId nodeId
     * @param userId userId
     * @return List<MindVo>
     */
    List<MindVo> getIteratorMindVoByNodeId(Integer nodeId, Integer userId);
}
