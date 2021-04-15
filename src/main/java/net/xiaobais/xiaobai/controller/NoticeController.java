package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author xiaobai
 * @Date 2021/4/15 19:29
 * @Version 1.0
 */
@Api(tags = "NoticeController")
@Controller
@RequestMapping("/person/public")
public class NoticeController {

    @ApiOperation("跳转通知管理页面")
    @GetMapping("/toNotice")
    public String toNotice(){
        return "privateNotice";
    }
}
