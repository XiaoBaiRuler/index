package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.vo.MindVo;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/7 21:41
 * @Version 1.0
 */
public interface MindService {

    /**
     * 按层级查找所有节点
     * @param level level
     * @param nodeId nodeId
     * @return List<MindVo>
     */
    List<MindVo> getMindVoByLevel(Integer level, Integer nodeId);
}
