package net.xiaobais.xiaobai.service.impl;

import com.alibaba.fastjson.JSON;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.CacheService;
import net.xiaobais.xiaobai.service.RedisService;
import net.xiaobais.xiaobai.vo.CommentVo;
import net.xiaobais.xiaobai.vo.MindVo;
import net.xiaobais.xiaobai.vo.PublicNodeVo;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
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

    @Override
    public List<SimpleNodeVo> getSimpleNodeVoListByKey(String key) {
        String s = redisService.get(REDIS_KEY_PREFIX_CACHE + key);
        if (s == null || "".equals(s)){
            return null;
        }
        return JSON.parseArray(s, SimpleNodeVo.class);
    }

    @Override
    public void setSimpleNodeVoListByKey(String key, List<SimpleNodeVo> list) {
        redisService.set(REDIS_KEY_PREFIX_CACHE + key, JSON.toJSONString(list));
        redisService.expire(REDIS_KEY_PREFIX_CACHE + key, AUTH_CODE_EXPIRE_CACHE);
    }

    @Override
    public void deleteAllSimpleNodeVoList(String preKey) {
        redisService.removeAll(REDIS_KEY_PREFIX_CACHE + preKey + "*");
    }

    @Override
    public void setNodeByKey(String key, Node node) {
        redisService.set(REDIS_KEY_PREFIX_CACHE + key, JSON.toJSONString(node));
        redisService.expire(REDIS_KEY_PREFIX_CACHE + key, AUTH_CODE_EXPIRE_CACHE);
    }

    @Override
    public Node getNodeByKey(String key) {
        String s = redisService.get(REDIS_KEY_PREFIX_CACHE + key);
        if (s == null || "".equals(s)){
            return null;
        }
        return JSON.parseObject(s, Node.class);
    }

    @Override
    public void deleteNodeByKey(String key) {
        redisService.remove(REDIS_KEY_PREFIX_CACHE + key);
    }

    @Override
    public void setBlogByKey(String key, Blog blog) {
        redisService.set(REDIS_KEY_PREFIX_CACHE + key, JSON.toJSONString(blog));
        redisService.expire(REDIS_KEY_PREFIX_CACHE + key, AUTH_CODE_EXPIRE_CACHE);
    }

    @Override
    public Blog getBlogByKey(String key) {
        String s = redisService.get(REDIS_KEY_PREFIX_CACHE + key);
        if (s == null || "".equals(s)){
            return null;
        }
        return JSON.parseObject(s, Blog.class);
    }

    @Override
    public void deleteBlogByKey(String key) {
        redisService.remove(REDIS_KEY_PREFIX_CACHE + key);
    }

    @Override
    public void setUserList(String key, List<User> users) {
        redisService.set(REDIS_KEY_PREFIX_CACHE + key, JSON.toJSONString(users));
        redisService.expire(REDIS_KEY_PREFIX_CACHE + key, AUTH_CODE_EXPIRE_CACHE);
    }

    @Override
    public List<User> getUserList(String key) {
        String s = redisService.get(REDIS_KEY_PREFIX_CACHE + key);
        if (s == null || "".equals(s)){
            return null;
        }
        return JSON.parseArray(s, User.class);
    }

    @Override
    public void deleteAllUserByPreKey(String preKey) {
        redisService.removeAll(REDIS_KEY_PREFIX_CACHE + preKey + "*");
    }

    @Override
    public List<MindVo> getMindListByKey(String key) {
        String s = redisService.get(REDIS_KEY_PREFIX_CACHE + key);
        if (s == null || "".equals(s)){
            return null;
        }
        return JSON.parseArray(s, MindVo.class);
    }

    @Override
    public void setMindListByKey(String key, List<MindVo> list) {
        redisService.set(REDIS_KEY_PREFIX_CACHE + key, JSON.toJSONString(list));
        redisService.expire(REDIS_KEY_PREFIX_CACHE + key, AUTH_CODE_EXPIRE_CACHE);
    }

    @Override
    public void deleteMindListByKey(String key) {
        redisService.remove(REDIS_KEY_PREFIX_CACHE + key);
    }

    @Override
    public void deleteAllMindListByKey(String preKey) {
        redisService.removeAll(REDIS_KEY_PREFIX_CACHE + preKey + "*");
    }

    @Override
    public List<CommentVo> getCommentsByKey(String key) {
        String s = redisService.get(REDIS_KEY_PREFIX_CACHE + key);
        if (s == null || "".equals(s)){
            return null;
        }
        return JSON.parseArray(s, CommentVo.class);
    }

    @Override
    public void setCommentsByKey(String key, List<CommentVo> comments) {
        redisService.set(REDIS_KEY_PREFIX_CACHE + key, JSON.toJSONString(comments));
        redisService.expire(REDIS_KEY_PREFIX_CACHE + key, AUTH_CODE_EXPIRE_CACHE);
    }

    @Override
    public void deleteCommentsByKey(String key) {
        redisService.remove(REDIS_KEY_PREFIX_CACHE + key);
    }


}
