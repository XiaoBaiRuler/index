package net.xiaobais.xiaobai.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xiaobai
 * @Date 2021/2/18 23:58
 * @Version 1.0
 */
@Configuration
@MapperScan("net.xiaobais.xiaobai.mapper")
public class MybatisConfig {
}
