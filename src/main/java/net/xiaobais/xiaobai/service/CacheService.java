package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.vo.PublicNodeVo;

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
}
