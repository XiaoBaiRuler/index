package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author xiaobai
 * @Date 2021/2/19 21:03
 * @Version 1.0
 */
@Api(tags = "IndexController")
@Controller
public class IndexController {

    @ApiOperation("首页")
    @GetMapping({"/index","/"})
    public String index() {
        return "index";
    }
}
