package net.xiaobais.xiaobai;

import net.xiaobais.xiaobai.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XiaobaiApplicationTests {

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
    }

}
