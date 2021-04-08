package net.xiaobais.xiaobai;

import net.xiaobais.xiaobai.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XiaobaiApplicationTests {

    @Resource
    private CommentService commentService;

    @Test
    void contextLoads() {

        System.out.println(commentService.getAllComments(1));
    }

}
