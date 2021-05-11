package net.xiaobais.xiaobai;

import net.xiaobais.xiaobai.service.RoleAuthorityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XiaobaiApplicationTests {

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @Test
    void contextLoads() {
        System.out.println(roleAuthorityService.getAllAuthorities(0, 1, 1, "ad"));
    }

}
