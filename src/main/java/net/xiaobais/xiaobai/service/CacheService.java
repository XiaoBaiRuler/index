package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.vo.PublicNodeVo;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/18 10:04
 * @Version 1.0
 */
public interface CacheService {

    /**
     * 通过键值查询缓存
     * @param key key
     * @return List<PublicNodeVo>
     */
    List<PublicNodeVo> getPublicNodeVoListByKey(String key);

    /**
     * 通过键值设置缓存
     * @param key key
     * @param list publicNodeVo的列表
     */
    void setPublicNodeVoListByKey(String key, List<PublicNodeVo> list);

    /**
     * 通过键值查询SimpleNode
     * @param key key
     * @return List<SimpleNodeVo>
     */
    List<SimpleNodeVo> getSimpleNodeVoListByKey(String key);

    /**
     * 通过键值key设置SimpleNodeVo列表
     * @param key key
     * @param list list
     */
    void setSimpleNodeVoListByKey(String key, List<SimpleNodeVo> list);

    /**
     * 通过键值前缀删除key删除缓存
     * @param preKey preKey
     */
    void deleteAllSimpleNodeVoList(String preKey);
}
