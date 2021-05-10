package net.xiaobais.xiaobai.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author xiaobai
 * @Date 2021/2/20 12:16
 * @Version 1.0
 */
@Api(tags = "AdminAuthorityController")
@Controller
@RequestMapping("/admin")
public class AdminAuthorityController {

    @ApiOperation("管理员添加权限")
    @GetMapping("/addAuthority")
    public String addAuthority(){
        return "admin/addAuthority";
    }
}
