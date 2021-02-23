package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.RoleService;
import net.xiaobais.xiaobai.service.UserRoleService;
import net.xiaobais.xiaobai.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/2/22 13:31
 * @Version 1.0
 */
@Api(tags = "SignController")
@Controller
public class SignController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;

    @ApiOperation("跳转注册页面")
    @GetMapping("/toSign")
    public String toSign(){
        return "sign";
    }

    @ApiOperation("注册处理")
    @PostMapping("/sign")
    public String sign(String username, String email, String password){

        //1. 添加用户
        int userId = userService.addUser(username, email, password);
        //2. 添加普通用户权限
        int roleId = roleService.getRoleIdByRoleName("user");
        userRoleService.addUserRole(userId, roleId);
        return "redirect:/index";
    }
}
