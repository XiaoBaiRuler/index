package net.xiaobais.xiaobai;

import net.xiaobais.xiaobai.service.MindService;
import net.xiaobais.xiaobai.service.NodeService;
import net.xiaobais.xiaobai.service.PreviousService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XiaobaiApplicationTests {

    @Resource
    private PreviousService previousService;

    @Resource
    private MindService mindService;

    @Resource
    private NodeService nodeService;

    @Test
    void contextLoads() {

        System.out.println(nodeService.findNodeByTopCollect(5));
    }

}
