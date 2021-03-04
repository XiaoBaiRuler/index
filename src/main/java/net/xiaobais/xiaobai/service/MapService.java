package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Map;

/**
 * @Author xiaobai
 * @Date 2021/3/3 16:37
 * @Version 1.0
 */
public interface MapService {

    /**
     * 通过id查找Map
     * @param id id
     * @return Map
     */
    Map findMapById(Integer id);
}
