package net.xiaobais.xiaobai;

import net.xiaobais.xiaobai.service.PrivateMindService;
import net.xiaobais.xiaobai.service.PublicMindService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.service.PreviousService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XiaobaiApplicationTests {

    @Resource
    private PreviousService previousService;

    @Resource
    private PublicMindService mindService;

    @Resource
    private PublicNodeService nodeService;

    @Resource
    private PrivateMindService privateMindService;

    @Test
    void contextLoads() {

        System.out.println(privateMindService.getPrivateMindVoByLevel(3, 1, 1));
    }

}
