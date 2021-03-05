package net.xiaobais.xiaobai;

import net.xiaobais.xiaobai.mapper.MyPreviousMapper;
import net.xiaobais.xiaobai.service.PreviousService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XiaobaiApplicationTests {

    @Resource
    private PreviousService previousService;

    @Resource
    private MyPreviousMapper mapper;

    @Test
    void contextLoads() {
        System.out.println(mapper.findNotPrivatePreviousByNodeId(1, 0, 2).size());
    }

}
