package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xiaobai
 * @Date 2021/2/19 21:04
 * @Version 1.0
 */
@Api(tags = "ErrorController")
@RestController
public class ErrorController {

    @ApiOperation("403: 权限不足")
    @RequestMapping("/error/403")
    public String error(){
        return "你当前访问的接口权限不足!";
    }

    @ApiOperation("404: 找不到页面")
    @RequestMapping("/error/404")
    public String error404(){
        return "404 no found !";
    }
}
