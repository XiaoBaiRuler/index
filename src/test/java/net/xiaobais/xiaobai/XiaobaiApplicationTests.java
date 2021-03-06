package net.xiaobais.xiaobai;

import net.xiaobais.xiaobai.service.PreviousService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XiaobaiApplicationTests {

    @Resource
    private PreviousService previousService;

    @Test
    void contextLoads() {
        System.out.println(previousService.findPreviousByNodeIdAndTitle(1, 0, 2, "分页").size());
    }

}
