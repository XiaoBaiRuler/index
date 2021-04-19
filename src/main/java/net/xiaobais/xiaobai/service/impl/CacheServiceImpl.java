package net.xiaobais.xiaobai.service.impl;

import com.alibaba.fastjson.JSON;
import net.xiaobais.xiaobai.service.CacheService;
import net.xiaobais.xiaobai.service.RedisService;
import net.xiaobais.xiaobai.vo.PublicNodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/18 10:09
 * @Version 1.0
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.cache}")
    private String REDIS_KEY_PREFIX_CACHE;

    @Value("${redis.key.expire.cache}")
    private Long AUTH_CODE_EXPIRE_CACHE;

    @Override
    public List<PublicNodeVo> getPublicNodeVoListByKey(String key) {
        String s = redisService.get(REDIS_KEY_PREFIX_CACHE + key);
        if (s == null || "".equals(s)){
            return null;
        }
        return JSON.parseArray(s, PublicNodeVo.class);
    }

    @Override
    public void setPublicNodeVoListByKey(String key, List<PublicNodeVo> list) {
        redisService.set(REDIS_KEY_PREFIX_CACHE + key, JSON.toJSONString(list));
        redisService.expire(REDIS_KEY_PREFIX_CACHE + key, AUTH_CODE_EXPIRE_CACHE);
    }
}
