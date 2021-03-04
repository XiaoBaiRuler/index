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
}
