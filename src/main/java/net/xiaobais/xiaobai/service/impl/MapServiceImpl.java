package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.MapMapper;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.service.MapService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/3/3 16:38
 * @Version 1.0
 */
@Service
public class MapServiceImpl implements MapService {

    @Resource
    private MapMapper mapMapper;

    @Override
    public Map findMapById(Integer id) {
        return mapMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertMap(Map map) {
        int insert = mapMapper.insert(map);
        return insert == -1 ? -1 : map.getMapId();
    }

    @Override
    public int insertMapByMapNameAndMapAuthorAndMapData(String mapName, String mapAuthor, String mapData) {
        Map map = new Map();
        map.setMapName(mapName);
        map.setMapVersion("1.0");
        map.setMapAuthor(mapAuthor);
        map.setMapData(mapData);
        int insert = mapMapper.insert(map);
        return insert == -1 ? -1 : map.getMapId();
    }

    @Override
    public int updateMapByMapId(Integer mapId, String mapData) {
        Map map = new Map();
        map.setMapData(mapData);
        map.setMapId(mapId);
        return mapMapper.updateByPrimaryKeySelective(map);
    }

    @Override
    public int deleteMapByMapId(Integer mapId) {
        return mapMapper.deleteByPrimaryKey(mapId);
    }

}
