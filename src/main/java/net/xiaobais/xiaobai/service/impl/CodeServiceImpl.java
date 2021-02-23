package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.service.CodeService;
import net.xiaobais.xiaobai.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Random;

/**
 * @Author xiaobai
 * @Date 2021/2/22 14:05
 * @Version 1.0
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public String generateCode(String email) {

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + email, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + email,
                AUTH_CODE_EXPIRE_SECONDS);
        return sb.toString();
    }

    @Override
    public boolean verifyCode(String email, String code) {

        if (StringUtils.isEmpty(code)){
            return false;
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + email);
        return code.equals(realAuthCode);
    }
}
