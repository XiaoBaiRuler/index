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

    /**
     * 添加map
     * @param map map
     * @return int
     */
    int insertMap(Map map);

    /**
     * 根据思维导图名和作者名和思维导图内容添加Map
     * @param mapName 思维导图名
     * @param mapAuthor 作者名
     * @param mapData 思维导图内容
     * @return int
     */
    int insertMapByMapNameAndMapAuthorAndMapData(String mapName, String mapAuthor, String mapData);

    /**
     * 通过mapId更新思维导图内容
     * @param mapId mapId
     * @param mapData mapData
     * @return int
     */
    int updateMapByMapId(Integer mapId, String mapData);

    /**
     * 通过mapId删除思维导图
     * @param mapId mapId
     * @return int
     */
    int deleteMapByMapId(Integer mapId);
}
