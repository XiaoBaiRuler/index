package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author xiaobai
 * @Date 2021/2/20 12:16
 * @Version 1.0
 */
@Api(tags = "AdminAuthorityController")
@Controller
public class AdminAuthorityController {

    @ApiOperation("管理员添加权限")
    @GetMapping("/admin/addAuthority")
    public String addAuthority(){
        return "admin/addAuthority";
    }
}
