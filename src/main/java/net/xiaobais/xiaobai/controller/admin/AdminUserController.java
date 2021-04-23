package net.xiaobais.xiaobai.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/22 10:02
 * @Version 1.0
 */
@Api(tags = "后台用户管理")
@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Resource
    private UserService userService;

    @ApiOperation("跳转用户管理主页")
    @GetMapping("/toAdminUser")
    public String toAdminUser(){
        return "admin/adminUser";
    }

    @ApiOperation("分页查找所有用户信息")
    @GetMapping("/getAllUser")
    @ResponseBody
    public List<User> getAllUser(@RequestParam Integer pageNumber,
                                 @RequestParam Integer pageSize,
                                 @RequestParam String message){
        return userService.getAllUsersByPageAndMessage(pageNumber, pageSize, message);
    }

    @ApiOperation("根据用户名模糊查询用户个数")
    @GetMapping("/countAllUser")
    @ResponseBody
    public Long countAllUser(@RequestParam String message){
        return userService.countAllUsersByMessage(message);
    }
}
