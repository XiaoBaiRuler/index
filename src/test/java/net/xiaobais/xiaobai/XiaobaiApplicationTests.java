package net.xiaobais.xiaobai;

import net.xiaobais.xiaobai.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XiaobaiApplicationTests {

    @Resource
    private NoticeService noticeService;

    @Test
    void contextLoads() {

        System.out.println(noticeService.getAllIteratorNotice(2, 2, 6, ""));
    }

}
